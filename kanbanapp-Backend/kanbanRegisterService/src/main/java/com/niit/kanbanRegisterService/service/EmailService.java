package com.niit.kanbanRegisterService.service;

public interface EmailService {

    public String sendRegistrationEmail(String emailid);

    public String sendOtp(String emailid);

    public String sendInviteEmail(String emailid);

}
