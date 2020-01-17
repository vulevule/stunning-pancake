package com.nc.naucnicentar.service.registration;

import java.util.List;
import java.util.Random;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nc.naucnicentar.util.FormSubmissionDto;

@Service
public class RegistrationValidation implements JavaDelegate{
	@Autowired
	IdentityService identityService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		  
	      List<FormSubmissionDto> registration = (List<FormSubmissionDto>)execution.getVariable("registration");
	      System.out.println(registration);
	      String username = "";
	      String password = "";
	      String firstname = "";
	      String lastname = "";
	      String email = "";
	      String city = "";
	      String state = "";
	      String reviewer = "";
	      
	     
	      for (FormSubmissionDto formField : registration) {
			if (formField.getFieldId().equals("usename")){
				username = formField.getFieldValue();
			}
			else if (formField.getFieldId().equals("password")){
				password = formField.getFieldValue();
			}
			else if (formField.getFieldId().equals("email")){
				email = formField.getFieldValue();
			}
			else if (formField.getFieldId().equals("city")){
				city = formField.getFieldValue();
			}
			else if (formField.getFieldId().equals("state")){
				state = formField.getFieldValue();
			}
			else if (formField.getFieldId().equals("firstname")){
				firstname = formField.getFieldValue();
			}
			else if (formField.getFieldId().equals("lastname")){
				lastname = formField.getFieldValue();
			}
			else if (formField.getFieldId().equals("reviewer")){
				reviewer = formField.getFieldValue();
			}
	      }
	      if (username.equals(password)){
	    	  execution.setVariable("isValid", false);
	      }else{
	    	  execution.setVariable("to", email);
	    	  Random r = new Random();
	    	  execution.setVariable("subject","Registration code");
	    	  execution.setVariable("verificationCode", r.nextInt(999999));
	    	  execution.setVariable("isValid", true);

	      }
	}
}
