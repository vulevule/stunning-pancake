package sep.apiservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sep.apiservice.services.PaymentsAPIService;

import java.util.logging.Logger;

@RestController
public class PaymentsAPIController {
    @Autowired
    private PaymentsAPIService paymentsService;

    private Logger logger = Logger.getLogger(PaymentsAPIController.class.getName());

    @GetMapping(path = "/payments/test")
    public String dummyGet() {
        return paymentsService.dummyMethod();
    }
}
