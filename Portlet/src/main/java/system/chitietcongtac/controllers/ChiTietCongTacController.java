package system.chitietcongtac.controllers;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.io.ByteArrayFileInputStream;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import system.chitietcongtac.dto.ChiTietCongTacDTO;
import system.chitietcongtac.services.ChiTietCongTacService;
import system.congtac.dto.CongTacDTO;
import system.congtac.services.CongTacService;
import system.hoadon.dto.HoaDonDTO;
import system.hoadon.services.HoaDonService;
import system.nhanvien.dto.NhanVienDTO;
import system.nhanvien.services.NhanVienService;
import system.nhanvienduan.dto.NhanVienDuAnDTO;
import system.nhanvienduan.services.NhanVienDuAnService;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.portlet.*;
import javax.validation.Valid;
import java.io.File;
import java.util.*;

@Controller
@RequestMapping("VIEW")
@RequiredArgsConstructor
public class ChiTietCongTacController {

    private final ChiTietCongTacService chiTietCongTacService;
    private final NhanVienDuAnService nhanVienDuAnService;
    private final CongTacService congTacService;
    private final NhanVienService nhanVienService;
    private final HoaDonService hoaDonService;

    @RenderMapping
    public String view(Model model, RenderRequest request, @RequestParam(value = "id") Integer id) throws Exception {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        long userId = PortalUtil.getUserId(request);
        long plid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), "congtac_WAR_quanlynoiboportlet");
        LiferayPortletURL renderURL = PortletURLFactoryUtil.create(request,
                "congtac_WAR_quanlynoiboportlet", plid, PortletRequest.RENDER_PHASE);

        model.addAttribute("congTacUrl", renderURL.toString());
        model.addAttribute("userId", userId);

        String emailAdmin = PortalUtil.getPortalProperties().getProperty("admin.email.from.address");
        User user = PortalUtil.getUser(request);
        String emailGui = user.getEmailAddress();
        CongTacDTO congTacDTO = congTacService.getCongTac(id);
        User nguoiPhuTrach = UserLocalServiceUtil.getUser(congTacDTO.getNhanVien().getUserId());
        String emailNhan = nguoiPhuTrach.getEmailAddress();
        // Hard code de tranh spam
        emailGui = "duongnhan2611@gmail.com";
        emailNhan = "phamngocanh011020@gmail.com";

        model.addAttribute("emailGui", emailGui);
        model.addAttribute("emailNhan", emailNhan);
        model.addAttribute("emailAdmin", emailAdmin);

        NhanVienDTO nhanVienDTO = nhanVienService.findByNhanVienUserId(userId);
        List<NhanVienDuAnDTO> nhanViens = nhanVienDuAnService.findNhanVienDuAnByNhanVien(nhanVienDTO.getId());
        List<NhanVienDuAnDTO> nhanVienDuAns = nhanVienDuAnService.findNhanVienByDuAn(congTacDTO.getDuAn().getId());
        List<NhanVienDuAnDTO> nhanVienDuAnDTOS = new ArrayList<>();

        nhanVienDuAns.forEach(nhanVienDuAnDTO -> {

            if (nhanVienDuAnDTO.getVaiTro().getTenVaiTro().equals("PM")) {

                nhanVienDuAnDTOS.add(nhanVienDuAnDTO);
            }

        });

        nhanViens.forEach(nhanVien -> {
            if (nhanVien.getVaiTro().getTenVaiTro().equals("PM")) {
                List<NhanVienDuAnDTO> nhanVienDuAnDTOList = new ArrayList<>();
                nhanVienDuAnDTOList.add(nhanVien);
                model.addAttribute("nhanVienDuAnDTOList", nhanVienDuAnDTOList);
            }
        });

        List<ChiTietCongTacDTO> listView = chiTietCongTacService.getByCongTac(id);
        List<Role> userRoles = themeDisplay.getUser().getRoles();
        String imageUrl = hoaDonService.getImageUrl();

        model.addAttribute("imageUrl", imageUrl);
        model.addAttribute("nhanVienDuAns", nhanVienDuAnDTOS);
        model.addAttribute("userRoles", userRoles);
        model.addAttribute("id", id);
        model.addAttribute("tenDuAn", congTacDTO.getDuAn().getTenDuAn());
        model.addAttribute("nhiemVu", congTacDTO.getNhiemVu());
        model.addAttribute("duAnId", congTacDTO.getDuAn().getId());
        model.addAttribute("nhanVienId", congTacDTO.getNhanVien().getUserId());
        model.addAttribute("trangThai", congTacDTO.getTrangThai());
        model.addAttribute("listView", listView);

        return "danhsach";
    }

    @RenderMapping(params = "action=add")

    public String themMoi(RenderRequest request, Model model,
                          @RequestParam(value = "id", defaultValue = "0") Integer id,
                          @RequestParam(value = "congTac.id") Integer congTacId,
                          @RequestParam(required = false, defaultValue = "true") String validation) {

        CongTacDTO congTacDTO = congTacService.getCongTac(congTacId);
        List<NhanVienDuAnDTO> listNhanVien = nhanVienDuAnService.findNhanVienByDuAn(congTacDTO.getDuAn().getId());
        model.addAttribute("listNhanVien", listNhanVien);
        model.addAttribute("chiTietCongTac", new ChiTietCongTacDTO());
        model.addAttribute("tenDuAn", congTacDTO.getDuAn().getTenDuAn());
        model.addAttribute("nhiemVu", congTacDTO.getNhiemVu());
        model.addAttribute("congTacId", congTacId);
        model.addAttribute("duAnId", congTacDTO.getDuAn().getId());
        model.addAttribute("nhanVienId", congTacDTO.getNhanVien().getUserId());
        model.addAttribute("trangThai", congTacDTO.getTrangThai());

        if (validation.equals("false")) {

            SessionErrors.add(request, "alert-error");

            return "/themmoi";
        }

        ChiTietCongTacDTO item = new ChiTietCongTacDTO();

        if (id > 0) {

            item = chiTietCongTacService.getById(id);
        }

        model.addAttribute("item", item);

        return "/themmoi";
    }

    @ActionMapping(params = "action=add")
    public void themMoi(ActionRequest actionRequest, ActionResponse actionResponse, SessionStatus sessionStatus,
                        @ModelAttribute("item") @Valid ChiTietCongTacDTO chiTietCongTacDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            actionResponse.setRenderParameter("validation", "false");
            actionResponse.setRenderParameter("action", "add");
            actionResponse.setRenderParameter("congTac.id", String.valueOf(chiTietCongTacDTO.getCongTac().getId()));

            return;
        }

        String[] tenChiPhis = actionRequest.getParameterValues("tenChiPhi");
        String[] donViTinhs = actionRequest.getParameterValues("donViTinh");
        String[] soLuongs = actionRequest.getParameterValues("soLuong");
        String[] donGias = actionRequest.getParameterValues("donGia");
        String[] thanhTiens = actionRequest.getParameterValues("thanhTien");
        String congTacId = actionRequest.getParameter("congTac.id");

        CongTacDTO congTacDTO = congTacService.findCongTacById(Integer.valueOf(congTacId));

        for (int i = 0; i < tenChiPhis.length; i++) {

            chiTietCongTacDTO.setCongTac(congTacDTO);
            chiTietCongTacDTO.setTenChiPhi(tenChiPhis[i]);
            chiTietCongTacDTO.setDonViTinh(donViTinhs[i]);
            chiTietCongTacDTO.setSoLuong(Integer.valueOf(soLuongs[i]));
            chiTietCongTacDTO.setDonGia(Double.valueOf(donGias[i]));
            chiTietCongTacDTO.setThanhTien(Double.valueOf(thanhTiens[i]));

            chiTietCongTacService.save(chiTietCongTacDTO);
        }

        sessionStatus.setComplete();
        SessionMessages.add(actionRequest, "form-success");
        actionResponse.setRenderParameter("action", "");
        actionResponse.setRenderParameter("id", String.valueOf(chiTietCongTacDTO.getCongTac().getId()));

    }

    @RenderMapping(params = "action=upload")
    public String uploadFile(RenderRequest request, Model model,
                             @RequestParam(value = "id", defaultValue = "0") Integer id,
                             @RequestParam(required = false, defaultValue = "true") String validation) {

        ChiTietCongTacDTO chiTietCongTacDTO = chiTietCongTacService.getById(id);
        model.addAttribute("congTacId", chiTietCongTacDTO.getCongTac().getId());
        model.addAttribute("id", id);

        if (validation.equals("false")) {

            SessionErrors.add(request, "alert-error");

            return "/hoadon";
        }

        return "/hoadon";
    }

    @ActionMapping(params = "action=upload")
    public void uploadFile(ActionRequest actionRequest, ActionResponse actionResponse, SessionStatus sessionStatus) {

        UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(actionRequest);
        ByteArrayFileInputStream inputStream = null;
        String id = actionRequest.getParameter("id");
        File file = uploadPortletRequest.getFile("file");
        file.renameTo(file);

        if (!file.exists()) {
            actionResponse.setRenderParameter("validation", "false");
            actionResponse.setRenderParameter("action", "upload");
            actionResponse.setRenderParameter("id", id);
        }
        if ((file != null) && file.exists()) {

            hoaDonService.uploadHoaDon(file);
            HoaDonDTO hoaDonDTO = hoaDonService.findByName(file.getName());
            ChiTietCongTacDTO chiTietCongTac = chiTietCongTacService.getById(Integer.valueOf(id));

            chiTietCongTac.setHoaDon(String.valueOf(hoaDonDTO.getId()));
            chiTietCongTacService.save(chiTietCongTac);

            StreamUtil.cleanUp(inputStream);
            sessionStatus.setComplete();
            SessionMessages.add(actionRequest, "form-success");
            actionResponse.setRenderParameter("action", "");
            actionResponse.setRenderParameter("id", String.valueOf(chiTietCongTac.getCongTac().getId()));
        }


    }

    @ActionMapping(params = "action=delete")
    public void xoa(ActionRequest request, ActionResponse response, SessionStatus sessionStatus,
                    @RequestParam(value = "id", defaultValue = "0") Integer id) {

        ChiTietCongTacDTO chiTietCongTacDTO = chiTietCongTacService.findById(id);

        if (id > 0) {

            chiTietCongTacService.delete(id);
        }

        sessionStatus.setComplete();
        SessionMessages.add(request, "form-success");
        response.setRenderParameter("action", "");
        response.setRenderParameter("id", String.valueOf(chiTietCongTacDTO.getCongTac().getId()));

    }

    @ActionMapping(params = "action=sendMail")
    public void duyetCongTac(ActionRequest request, ActionResponse response, SessionStatus sessionStatus,
                             @RequestParam(value = "status", defaultValue = "0") int status,
                             @RequestParam(value = "congTacId", defaultValue = "0") Integer id) throws PortalException {

        switch (status) {
            case 0:
                chiTietCongTacService.dongY(id);
                break;
            case 1:
                chiTietCongTacService.tuChoi(id);
                break;
            case 2:
                chiTietCongTacService.choHoanUng(id);
                break;
            case 3:
                chiTietCongTacService.daHoanUng(id);
                break;
        }

        String from = ParamUtil.getString(request, "from");
        String to = ParamUtil.getString(request, "to");
        String subject = ParamUtil.getString(request, "subject");
        String body = ParamUtil.getString(request, "body");

        try {
            InternetAddress fromAddress = new InternetAddress(from);
            InternetAddress toAddress = new InternetAddress(to);

            MailMessage mailMessage = new MailMessage();
            mailMessage.setFrom(fromAddress);
            mailMessage.setTo(toAddress);
            mailMessage.setSubject(subject);
            mailMessage.setBody(body);

            MailServiceUtil.sendEmail(mailMessage);
            sessionStatus.setComplete();
            SessionMessages.add(request, "form-success");
            response.setRenderParameter("action", "");
            CongTacDTO congTacDTO = congTacService.findCongTacById(id);
            response.setRenderParameter("id", String.valueOf(congTacDTO.getId()));
        } catch (AddressException e) {
        }

        User user = PortalUtil.getUser(request);
        user.getAddresses();

    }

    @ActionMapping(params = "action=duyetPhi")
    public void duyetPhi(ActionResponse response,
                         @RequestParam(value = "status", defaultValue = "0") int status,
                         @RequestParam(value = "chiTietCongTacId", defaultValue = "0") Integer id) {

        ChiTietCongTacDTO chiTietCongTacDTO = chiTietCongTacService.findById(id);
        if (status == 0) {
            chiTietCongTacService.duyet(id);
        } else {
            chiTietCongTacService.khongDuyet(id);
        }
        response.setRenderParameter("id", String.valueOf(chiTietCongTacDTO.getCongTac().getId()));
    }

    @ActionMapping(params = "action=hoanThanh")
    public void hoanThanh(ActionResponse response,
                          @RequestParam(value = "status", defaultValue = "0") int status,
                          @RequestParam(value = "congTacId", defaultValue = "0") Integer id) {

        if (status == 3) {
            chiTietCongTacService.daHoanUng(id);
        }

        response.setRenderParameter("id", String.valueOf(id));
    }

}