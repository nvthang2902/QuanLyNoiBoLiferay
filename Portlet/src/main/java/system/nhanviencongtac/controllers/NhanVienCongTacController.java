package system.nhanviencongtac.controllers;

import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import system.congtac.dto.CongTacDTO;
import system.congtac.services.CongTacService;
import system.nhanvien.dto.NhanVienDTO;
import system.nhanviencongtac.dto.NhanVienCongTacDTO;
import system.nhanviencongtac.services.NhanVienCongTacService;
import system.nhanvienduan.dto.NhanVienDuAnDTO;
import system.nhanvienduan.services.NhanVienDuAnService;

import javax.portlet.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("VIEW")
@RequiredArgsConstructor
public class NhanVienCongTacController {

    private final NhanVienCongTacService nhanVienCongTacService;
    private final NhanVienDuAnService nhanVienDuAnService;
    private final CongTacService congTacService;

    @RenderMapping
    public String view(Model model, RenderRequest request, @RequestParam(value = "id") Integer id) throws Exception {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        long userId = PortalUtil.getUserId(request);
        long plid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), "congtac_WAR_quanlynoiboportlet");
        LiferayPortletURL renderURL = PortletURLFactoryUtil.create(request,
                "congtac_WAR_quanlynoiboportlet", plid, PortletRequest.RENDER_PHASE);
        model.addAttribute("congTacUrl", renderURL.toString());
        model.addAttribute("userId", userId);

        List<NhanVienCongTacDTO> listView = nhanVienCongTacService.getbByCongTac(id);
        List<Role> roles = themeDisplay.getUser().getRoles();
        CongTacDTO congTacDTO = congTacService.getCongTac(id);

        model.addAttribute("userRoles", roles);
        model.addAttribute("id", id);
        model.addAttribute("tenDuAn", congTacDTO.getDuAn().getTenDuAn());
        model.addAttribute("nhiemVu", congTacDTO.getNhiemVu());
        model.addAttribute("duAnId", congTacDTO.getDuAn().getId());
        model.addAttribute("nhanVienId", congTacDTO.getNhanVien().getUserId());
        model.addAttribute("listView", listView);

        return "danhsach";
    }

    @RenderMapping(params = "action=add")

    public String themMoi(RenderRequest request, Model model,
                          @RequestParam(value = "id", defaultValue = "0") Integer id,
                          @RequestParam(value = "congTac.id") Integer congTacId,
                          @RequestParam(required = false, defaultValue = "true") String validation) {

        CongTacDTO congTacDTO = congTacService.getCongTac(congTacId);
        List<NhanVienCongTacDTO> listView = nhanVienCongTacService.getbByCongTac(congTacId);
        List<NhanVienDuAnDTO> nhanViens = nhanVienDuAnService.findNhanVienByDuAn(congTacDTO.getDuAn().getId());
        List<NhanVienDTO> nhanVienDTOS = new ArrayList<>();
        List<NhanVienDTO> nhanVienCongTacs = new ArrayList<>();

        nhanViens.forEach(nhanVien -> {
            nhanVienDTOS.add(nhanVien.getNhanVien());
        });
        listView.forEach(nhanVienCongTacDTO -> {
            nhanVienDTOS.remove(nhanVienCongTacDTO.getNhanVien());
            nhanVienCongTacs.add(nhanVienCongTacDTO.getNhanVien());
        });

        model.addAttribute("listNhanVien", nhanVienDTOS);
        model.addAttribute("listView", nhanVienCongTacs);

        if (validation.equals("false")) {

            SessionErrors.add(request, "alert-error");

            return "/themmoi";
        }

        NhanVienCongTacDTO item = new NhanVienCongTacDTO();

        if (id > 0) {

            item = nhanVienCongTacService.getById(id);
        }
        model.addAttribute("tenDuAn", congTacDTO.getDuAn().getTenDuAn());
        model.addAttribute("nhiemVu", congTacDTO.getNhiemVu());
        model.addAttribute("congTacId", congTacId);
        model.addAttribute("duAnId", congTacDTO.getDuAn().getId());
        model.addAttribute("nhanVienId", congTacDTO.getNhanVien().getUserId());
        model.addAttribute("item", item);

        return "/themmoi";
    }

    @ActionMapping(params = "action=add")
    public void themMoi(ActionRequest actionRequest, ActionResponse actionResponse, SessionStatus sessionStatus,
                        @ModelAttribute("item") NhanVienCongTacDTO nhanVienCongTacDTO) throws Exception {

        String[] nhanVienIds = actionRequest.getParameterValues("nhanVien.id");
        String congTacId = actionRequest.getParameter("congTac.id");

        CongTacDTO congTacDTO = congTacService.findCongTacById(Integer.valueOf(congTacId));
        NhanVienDTO nhanVienDTO = new NhanVienDTO();


        for (String id : nhanVienIds) {

            nhanVienDTO.setId(Integer.valueOf(id));

            nhanVienCongTacDTO.setNhanVien(nhanVienDTO);
            nhanVienCongTacDTO.setCongTac(congTacDTO);

            nhanVienCongTacService.save(nhanVienCongTacDTO);
        }

        sessionStatus.setComplete();
        SessionMessages.add(actionRequest, "form-success");
        actionResponse.setRenderParameter("action", "");
        actionResponse.setRenderParameter("id", String.valueOf(nhanVienCongTacDTO.getCongTac().getId()));

    }

    @ActionMapping(params = "action=delete")
    public void xoa(ActionRequest request, ActionResponse response, SessionStatus sessionStatus,
                    @RequestParam(value = "id", defaultValue = "0") Integer id) {

        NhanVienCongTacDTO nhanVienCongTacDTO = nhanVienCongTacService.findById(id);

        if (id > 0) {

            nhanVienCongTacService.delete(id);
        }

        sessionStatus.setComplete();
        SessionMessages.add(request, "form-success");
        response.setRenderParameter("action", "");
        response.setRenderParameter("id", String.valueOf(nhanVienCongTacDTO.getCongTac().getId()));
    }
}