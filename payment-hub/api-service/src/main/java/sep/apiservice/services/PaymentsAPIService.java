package sep.apiservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@Service
public class PaymentsAPIService {
    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    private final String serviceUrl = "http://PAYMENTS-SERVICE";

    private Logger logger = Logger.getLogger(PaymentsAPIService.class.getName());

    public String dummyMethod() {
        return "Hello from " + PaymentsAPIService.class.getName();
    }

    public String dummyRequest() {
        return restTemplate.getForObject(serviceUrl + "/test", String.class);
    }
}
