package com.niit.kanbanRegisterService.service;


import com.niit.kanbanRegisterService.domain.User;
import com.niit.kanbanRegisterService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    public EmailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;



    @Override
    public String sendRegistrationEmail(String emailid) {
        try{
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(emailid);

            mailMessage.setText("Congratulations, You Have Successfully Registered to Kanban Board App. Please Login to Start Working");
            mailMessage.setSubject("Kanban Board App Registration Success");
            javaMailSender.send(mailMessage);
            return "Email Sent Successfully";
        }catch (Exception e){
            System.out.println(e);
            return "Error while Sending Email";
        }
    }

    @Override
    public String sendOtp(String emailid) {
        String otp="";
        String link="http://localhost:4200/resetpassword";
        User user = userRepository.findByEmailid(emailid);
        if(user !=null){try{
            for(int i=0;i<4;i++)
            {
                int randomnumber=(int)(Math.random()*10);
                otp += randomnumber;
            }
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(emailid);
            mailMessage.setText("OTP to reset your Password- "+otp+" Please " +
                    "Click on this to reset your password "+link);
            mailMessage.setSubject("OTP to reset Password");
            javaMailSender.send(mailMessage);
            userService.setOtpAndEmailid(otp, emailid);
            return otp;
        }catch (Exception e){
            System.out.println(e);
            return "Error while Sending Email";
        }
        }
        else
            return "User Not Registered";


    }

    @Override
    public String sendInviteEmail(String emailid) {
        String inviteLink="http://localhost:4200/home";
        try{
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(emailid);
            mailMessage.setText("Hello!! from Team KanbanApp, this invitation enables you to manage your workflow. "+"Click on a link to start working "+inviteLink);
            mailMessage.setSubject("Kanban Board App Service");
            javaMailSender.send(mailMessage);
            return "Invite Sent Successfully";
        }catch (Exception e){
            System.out.println(e);
            return "Error while Sending Invitation email";
        }

    }


}
