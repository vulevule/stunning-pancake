package com.sep2019.cryptopaymentservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// https://developer.coingate.com/v2/docs/create-order from here
public class BTCOrder implements Serializable {
    private String order_id;
    private Double price_amount;
    private String price_currency = "BTC";
    private String receive_currency = "DO_NOT_CONVERT";
    private String title;
    private String description;
    private String callback_url;
    private String cancel_url;
    private String success_url;
    private String payment_url;
    private String token;
}
