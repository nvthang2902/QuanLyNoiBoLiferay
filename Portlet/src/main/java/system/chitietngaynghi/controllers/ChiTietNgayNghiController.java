package system.chitietngaynghi.controllers;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
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
import system.chitietngaynghi.dto.ChiTietNgayNghiDTO;
import system.chitietngaynghi.services.ChiTietNgayNghiService;
import system.nhanvien.dto.NhanVienDTO;
import system.nhanvien.services.NhanVienService;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("VIEW")
@RequiredArgsConstructor
public class ChiTietNgayNghiController {
    private final ChiTietNgayNghiService chiTietNgayNghiService;
    private final NhanVienService nhanVienService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    @RenderMapping
    public String view(Model model, RenderRequest request, RenderResponse response)throws Exception {

        ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
        List<Role> userRoles= RoleLocalServiceUtil.getUserRoles(themeDisplay.getUserId());
        model.addAttribute("userRoles",userRoles);

        long userId= PortalUtil.getUserId(request);

        User user = PortalUtil.getUser(request);
        String emailGui = user.getEmailAddress();
        emailGui = "test@gmail.com";
        model.addAttribute("emailGui", emailGui);

        String emailNhan = user.getEmailAddress();
        emailNhan = "nguyentiendung@gmail.com";
        model.addAttribute("emailNhan", emailNhan);

        for (Role role : userRoles) {
            if (role.getName().equals("Administrator")) {

                List<ChiTietNgayNghiDTO> listView =  chiTietNgayNghiService.getCTNgayNghis();
                model.addAttribute("listView",listView);

                return "list";
            }
        }
        List<ChiTietNgayNghiDTO> chiTietNgayNghiDTOS = chiTietNgayNghiService.getCTNNByUserId(userId);
        model.addAttribute("CTNNByUser", chiTietNgayNghiDTOS);

        return "list";
    }

    @RenderMapping(params = "action=addNew")
    public String addNew(RenderRequest request, RenderResponse response,@Valid Model model,
                         @RequestParam(value = "id", defaultValue = "0") Integer id,
                         @RequestParam(required = false, defaultValue = "true") String validation) throws Exception {

        if (validation.equals("false")) {
            SessionErrors.add(request, "alert-error");
            return "/form";
        }
        long userId= PortalUtil.getUserId(request);

        User user = PortalUtil.getUser(request);
        String emailGui = user.getEmailAddress();
        emailGui = "test@gmail.com";
        model.addAttribute("emailGui", emailGui);

        String emailNhan = user.getEmailAddress();
        emailNhan = "nguyentiendung@gmail.com";
        model.addAttribute("emailNhan", emailNhan);

        ChiTietNgayNghiDTO item = new ChiTietNgayNghiDTO();

        if (id > 0) {
            item = chiTietNgayNghiService.getCTNgayNghi(id);
        }
        model.addAttribute("item", item);

        NhanVienDTO nhanVien= nhanVienService.getNhanVienByUserId(userId);
        model.addAttribute("nhanVien",nhanVien);

        return "form";
    }

    @ActionMapping(params = "action=addNew")
    public void addNew(ActionRequest actionRequest, ActionResponse actionResponse, SessionStatus sessionStatus,
                       @ModelAttribute("item")@Valid ChiTietNgayNghiDTO chiTietNgayNghiDTO, BindingResult result, Model model) {
        if(result.hasErrors()){
            long userId= PortalUtil.getUserId(actionRequest);
            NhanVienDTO nhanVien=nhanVienService.getNhanVienByUserId(userId);
            model.addAttribute("nhanVien",nhanVien);

            actionResponse.setRenderParameter("validation", "false");
            actionResponse.setRenderParameter("action", "addNew");

            return;
        }
        chiTietNgayNghiService.save(chiTietNgayNghiDTO);
        sessionStatus.setComplete();
        SessionMessages.add(actionRequest, "form-success");
        actionResponse.setRenderParameter("action","");

        SendMail.sendMail(actionRequest,actionResponse,sessionStatus);
    }

    @ActionMapping(params = "action=delete")
    public void delete(ActionRequest request, ActionResponse response, SessionStatus sessionStatus,
                       @RequestParam(value = "id", defaultValue = "0") Integer id) {
        if (id > 0) {
            chiTietNgayNghiService.delete(id);
        }
        sessionStatus.setComplete();
        SessionMessages.add(request, "form-success");
        response.setRenderParameter("action","");
    }
    @ActionMapping(params = "action=sendMail")
    public void sendMail( ActionRequest request,  ActionResponse response, SessionStatus sessionStatus,
                          @RequestParam(value = "status", defaultValue = "0") int status,
                          @RequestParam(value = "chiTietId", defaultValue = "0") Integer id) {
        if (status == 0) {
            if (id > 0) {
                chiTietNgayNghiService.acpect(id);
            }
        } else {
            if (id > 0) {
                chiTietNgayNghiService.deny(id);
            }
        }
        SendMail.sendMail(request,response,sessionStatus);
   }
}
