package sep.apiservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sep.apiservice.services.ClientsAPIService;

import java.util.logging.Logger;

@RestController
public class ClientsAPIController {
    @Autowired
    private ClientsAPIService clientsService;

    private Logger logger = Logger.getLogger(ClientsAPIController.class.getName());

    @GetMapping(path = "/clients/test")
    public String dummyGet() {
        return clientsService.dummyMethod();
    }
}
