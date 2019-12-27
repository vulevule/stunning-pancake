package com.sep2019.cryptopaymentservice.service;

import com.sep2019.cryptopaymentservice.model.AuthToken;
import com.sep2019.cryptopaymentservice.model.BTCOrder;
import com.sep2019.cryptopaymentservice.model.Order;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class BTCService {

    private String url = "http://localhost:4200";
    private String sandboxUrl = "https://api-sandbox.coingate.com/v2/orders";
    private String clientSecret = "Token oXxtFF_dxtgbizKzJu3hQ_aQAa43DxsjKfkN_xeg";
    private String token;

    public Map<String, Object> create(Order order) {
        Map<String, Object> response = new HashMap<String, Object>();
        BTCOrder orderViaBTC = new BTCOrder();

        orderViaBTC.setOrder_id("Merchant's ID");
        orderViaBTC.setPrice_amount(Double.valueOf(order.getAmount()));
        orderViaBTC.setCancel_url(url + "/cancelbtc");
        token = UUID.randomUUID().toString();
        orderViaBTC.setSuccess_url(url + "/btcsuccess/" + token);
        // orderViaBTC.setCallback_url("http://localhost:8080/api/bitcoin/complete/payment");
        orderViaBTC.setToken(token);
//        TODO change this
        order.setToken(new AuthToken(token, new Date()));

        // CXaY7NVw4VbDTLRfQBb9C7bixEtQeXzQPJENVy5r sand
        // 8nQpsozzqQVTzgYicPx2eutAoDn4aLbr_SpYD83R token
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", clientSecret);
        ResponseEntity<BTCOrder> responseEntity = new RestTemplate().exchange(sandboxUrl, HttpMethod.POST,
                new HttpEntity<BTCOrder>(orderViaBTC, headers), BTCOrder.class);

        if (responseEntity.getStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY) {
//            order.setExecuted(false);
//            orderService.save(order);
            response.put("status", "error");
            return response;
        }
//        orderService.save(order);
        response.put("status", "success");
        response.put("redirect_url", responseEntity.getBody().getPayment_url());

        return response;
    }

    public Map<String, Object> complete(HttpServletRequest request) {
        Map<String, Object> response = new HashMap<String, Object>();
        try {
//            Order o=orderService.findOne(Long.parseLong(request.getParameter("paymentId")));
//            orderService.updateExecution(o, true);
            System.out.println("btc: completed . . . ");
            response.put("status", "success");
        } catch (RuntimeException e) {
            response.put("status", "errror");
        }
        // TODO Auto-generated method stub
        return response;
    }
}
