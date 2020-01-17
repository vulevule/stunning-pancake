package com.nc.naucnicentar.service.registration;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service
public class EmailService implements JavaDelegate{
	@Autowired
	IdentityService identityService;
	@Autowired
    public JavaMailSender emailSender;
	@Override
	public void execute(DelegateExecution execution) throws Exception {
	    String to = (String)execution.getVariable("to");
	    
	    String subject = (String)execution.getVariable("subject");
	    Integer verificationCode = (Integer)execution.getVariable("verificationCode");
		SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(to);
        message.setSubject(subject); 
        message.setText("Your verification code is " + verificationCode);
        emailSender.send(message);

	}
	      
	      
	
}
