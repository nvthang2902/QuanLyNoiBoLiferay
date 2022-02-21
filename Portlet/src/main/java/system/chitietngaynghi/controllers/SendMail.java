package system.chitietngaynghi.controllers;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.springframework.web.bind.support.SessionStatus;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

public class SendMail {

    public static void sendMail( ActionRequest request,  ActionResponse response, SessionStatus sessionStatus){
        String from = ParamUtil.getString(request, "from");
        String to = ParamUtil.getString(request, "to");
        String subject = ParamUtil.getString(request, "subject");
        String body = ParamUtil.getString(request, "body");
        try {
            InternetAddress fromAddress = new InternetAddress(from);
            InternetAddress toAddress = new InternetAddress(to);

            MailMessage mailMessagel = new MailMessage();
            mailMessagel.setFrom(fromAddress);
            mailMessagel.setTo(toAddress);
            mailMessagel.setSubject(subject);
            mailMessagel.setBody(body);

            MailServiceUtil.sendEmail(mailMessagel);
            sessionStatus.setComplete();
            SessionMessages.add(request, "form-success");
            response.setRenderParameter("action", "");
        } catch (AddressException e) {
            User user = null;
            try {
                user = PortalUtil.getUser(request);
            } catch (PortalException ex) {
                ex.printStackTrace();
            }
            user.getAddresses();
        }
    }
}
