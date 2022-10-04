package Utils;

import Models.Student;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Properties;

public class emailService {

    public static void sendEmail(Student st) throws MessagingException, UnsupportedEncodingException {
        String fromEmail = "huynnps21313@fpt.edu.vn";
        String password = "huyhuy2205@";
        String toEmail = st.getEmail();

        String subject = "Thông báo kết quả học tập!";
        String body = "Xin chào: " + st.getName();
        body += "\nKết quả học tập của bạn là:";
        body += "\nTiếng Anh: " + st.getTienganh() + ". Tin Học: " + st.getTinhoc() + ". GDTC: " +st.getGdtc();
        body += "\nĐiểm trung bình: " + formatGrade((st.getTienganh()+st.getTinhoc()+st.getGdtc())/3);

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);

        MimeMessage msg = new MimeMessage(session);
        //set message headers
        msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
        msg.addHeader("format", "flowed");
        msg.addHeader("Content-Transfer-Encoding", "8bit");

        msg.setFrom(new InternetAddress(fromEmail, "FPT-Education"));

        msg.setReplyTo(InternetAddress.parse(fromEmail, false));

        msg.setSubject(subject, "UTF-8");

        msg.setText(body, "UTF-8");

        msg.setSentDate(new Date());

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
        Transport.send(msg);
        System.out.println("Gui mail thanh cong");
    }

     public static String formatGrade(double d) {
        DecimalFormat df = new DecimalFormat("#.#");
        return df.format(d);
    }
}
