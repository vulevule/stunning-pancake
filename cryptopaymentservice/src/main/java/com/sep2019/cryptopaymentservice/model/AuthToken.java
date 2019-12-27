package com.sep2019.cryptopaymentservice.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class AuthToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private Date expireDate;

    public AuthToken(String token, Date expireDate) {
        super();
        this.token = token;
        this.expireDate = expireDate;
    }
}
