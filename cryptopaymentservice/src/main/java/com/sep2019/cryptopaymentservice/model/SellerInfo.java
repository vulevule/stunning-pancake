package com.sep2019.cryptopaymentservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Entity;

import javax.persistence.*;
import java.util.Base64;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SellerInfo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String clientId;

    @Column(nullable = false)
    private String clientSecret;

//    @OneToOne(cascade = CascadeType.ALL)
//    private AuthToken token;


    public String getClientCredentialsBase64Encoded(){
        return Base64.getEncoder().encodeToString((new String(this.getClientId()+ ":" + this.getClientSecret())).getBytes());
    }


}
