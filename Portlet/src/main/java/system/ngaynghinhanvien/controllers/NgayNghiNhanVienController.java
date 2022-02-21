package system.ngaynghinhanvien.controllers;

import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import system.chiphiphatsinh.dto.ChiPhiPhatSinhDTO;
import system.congtac.dto.CongTacDTO;
import system.duan.dto.DuAnDTO;
import system.duan.services.DuAnService;
import system.ngaynghinhanvien.dto.NgayNghiNhanVienDTO;
import system.ngaynghinhanvien.services.NgayNghiNhanVienService;
import system.nhanvien.dto.NhanVienDTO;
import system.nhanvien.services.NhanVienService;
import system.vaitro.dto.VaiTroDTO;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("VIEW")
@RequiredArgsConstructor
public class NgayNghiNhanVienController {
    private final NgayNghiNhanVienService ngayNghiNhanVienService;
    private final NhanVienService nhanVienService;

    @RenderMapping
    public String view(Model model, RenderRequest request, RenderResponse response,
                       @RequestParam(value = "id", defaultValue = "0", required = false) int nam) throws Exception {

        long userId = PortalUtil.getUserId(request);

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        List<Role> userRoles = RoleLocalServiceUtil.getUserRoles(themeDisplay.getUserId());
        model.addAttribute("userRoles", userRoles);

        for (Role role : userRoles) {
            if (role.getName().equals("Administrator")) {
                if (nam > 0) {
                    List<NgayNghiNhanVienDTO> namList = ngayNghiNhanVienService.getNam(nam);
                    model.addAttribute("namList", namList);

                    return "list";
                }
                List<NgayNghiNhanVienDTO> listView = ngayNghiNhanVienService.gets();
                model.addAttribute("listView", listView);

                return "list";

            }
        }
        List<NgayNghiNhanVienDTO> ngayNghiNhanVienDTOS = ngayNghiNhanVienService.getNNNVByUserId(userId);
        model.addAttribute("ngayNghiByUser", ngayNghiNhanVienDTOS);

        return "list";
    }

    @RenderMapping(params = "action=addNew")
    public String addNew(RenderRequest request, RenderResponse response, Model model,
                         @RequestParam(value = "id", defaultValue = "0") Integer id,
                         @RequestParam(required = false, defaultValue = "true") String validation) throws Exception {

        NgayNghiNhanVienDTO item = new NgayNghiNhanVienDTO();
        long userId = PortalUtil.getUserId(request);

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        List<Role> userRoles = RoleLocalServiceUtil.getUserRoles(themeDisplay.getUserId());
        model.addAttribute("userRoles", userRoles);
        for (Role role : userRoles) {
            if (role.getName().equals("Administrator")) {

                if (validation.equals("false")) {
                    SessionErrors.add(request, "alert-error");
                    return "/form";
                }
                if (id > 0) {
                    item = ngayNghiNhanVienService.getById(id);
                }
                List<NhanVienDTO> nhanVienDTOS = nhanVienService.getNhanViens();
                model.addAttribute("nhanVien", nhanVienDTOS);

                model.addAttribute("item", item);

                return "/form";
            }
        }
        NgayNghiNhanVienDTO ngayNghiNhanVienDTO = ngayNghiNhanVienService.findById(id);

        if (ngayNghiNhanVienDTO != null) {
            item = ngayNghiNhanVienDTO;
        }
        item.setId(id);
        model.addAttribute("item", item);

        NhanVienDTO nhanVien = nhanVienService.findByNhanVienUserId(userId);
        model.addAttribute("nhanVien", nhanVien);

        return "form";

    }

    @ActionMapping(params = "action=addNew")
    public void addNew(ActionRequest actionRequest, ActionResponse actionResponse, SessionStatus sessionStatus,
                       @ModelAttribute("item") NgayNghiNhanVienDTO ngayNghiNhanVienDTO, BindingResult result, Model model) throws Exception {

            List<NhanVienDTO> nhanViens = nhanVienService.getNhanViens();
            for (int i = 0; i < nhanViens.size(); i++) {
                ngayNghiNhanVienDTO.setNhanVien(nhanViens.get(i));
                ngayNghiNhanVienService.save(ngayNghiNhanVienDTO);
            }
            sessionStatus.setComplete();
            SessionMessages.add(actionRequest, "form-success");
            actionResponse.setRenderParameter("action", "");

    }
    @RenderMapping(params = "action=bangChamCong")
    public String bangChamCong(RenderRequest request, RenderResponse response, Model model,
                          @RequestParam(value = "id", defaultValue = "0") Integer id,
                          @RequestParam(required = false, defaultValue = "true") String validation) throws Exception {
        if (validation.equals("false")) {
            SessionErrors.add(request, "alert-error");
            return "/bangchamcong";
        }
        List<NgayNghiNhanVienDTO> listView = ngayNghiNhanVienService.gets();
        model.addAttribute("listView", listView);
        return "/bangchamcong";
    }
    @ActionMapping(params = "action=bangChamCong")
    public String bangChamCong(ActionRequest actionRequest, ActionResponse actionResponse, SessionStatus sessionStatus,
                           BindingResult result, Model model) throws Exception {

        List<NgayNghiNhanVienDTO> listView = ngayNghiNhanVienService.gets();
        model.addAttribute("listView", listView);

        return "bangchamcong";
    }

    @ActionMapping(params = "action=delete")
    public void xoa(ActionRequest request, ActionResponse response, SessionStatus sessionStatus,
                    @RequestParam(value = "id", defaultValue = "0") Integer id) {
        if (id > 0) {
            ngayNghiNhanVienService.delete(id);
        }
        sessionStatus.setComplete();
        SessionMessages.add(request, "form-success");
        response.setRenderParameter("action", "");
    }

    @RenderMapping(params = "action=bangLuong")
    public String bangLuong(RenderRequest request, RenderResponse response, Model model,
                               @RequestParam(value = "id", defaultValue = "0") Integer id,
                               @RequestParam(required = false, defaultValue = "true") String validation) throws Exception {
        if (validation.equals("false")) {
            SessionErrors.add(request, "alert-error");
            return "/bangluong";
        }
        List<NgayNghiNhanVienDTO> listView = ngayNghiNhanVienService.gets();
        model.addAttribute("listView", listView);
        return "/bangluong";
    }
    @ActionMapping(params = "action=bangLuong")
    public String bangLuong(ActionRequest actionRequest, ActionResponse actionResponse, SessionStatus sessionStatus,
                               BindingResult result, Model model) throws Exception {

        List<NgayNghiNhanVienDTO> listView = ngayNghiNhanVienService.gets();
        model.addAttribute("listView", listView);

        return "bangluong";
    }


}