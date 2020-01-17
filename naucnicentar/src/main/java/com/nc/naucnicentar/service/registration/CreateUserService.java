package com.nc.naucnicentar.service.registration;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CreateUserService implements JavaDelegate{
	@Autowired
	IdentityService identityService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
	      
	      User user = identityService.newUser("");
	      user.setEmail((String)execution.getVariable("email"));
	      user.setId((String) execution.getVariable("username"));
	      user.setPassword((String) execution.getVariable("password"));
	      user.setFirstName((String) execution.getVariable("firstname"));
	      user.setLastName((String) execution.getVariable("lastname"));
	      identityService.saveUser(user);
	      boolean reviewer =  (boolean)execution.getVariable("reviewer");
	      if (reviewer == true){
	    	  System.out.println("kreiram reviewera");
	    	  boolean adminApprovedReviewer = (boolean)execution.getVariable("approve");
		      if (adminApprovedReviewer == true){
		    	  Group revGroup = identityService.createGroupQuery().groupId("reviewers").singleResult();
		    	  identityService.createMembership(user.getId(), revGroup.getId());
		      }else{
		    	  identityService.createMembership(user.getId(), "regularUsers");
	
		      }
	      }else{
	    	  System.out.println("kreiram regular");

	    	  identityService.createMembership(user.getId(), "regularUsers");
	      }
	      
	      
	}
}
