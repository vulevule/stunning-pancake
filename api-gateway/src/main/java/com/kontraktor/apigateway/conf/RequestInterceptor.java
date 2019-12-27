package com.kontraktor.apigateway.conf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class RequestInterceptor extends HandlerInterceptorAdapter
{


public boolean preHandle(
         HttpServletRequest request,
         HttpServletResponse response,
         Object handler) throws Exception {
    
    if (request.getMethod().equals("OPTIONS")) {
         response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
         response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
         response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
         response.setHeader("Access-Control-Expose-Headers", "*");
         response.setHeader("Access-Control-Allow-Credentials", "true");
         response.setHeader("Access-Control-Max-Age", "4800");
     }

     return true;
 }
}