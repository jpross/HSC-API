package com.ebs.ngs.hsc.hsp.web.common.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class ApiBaseUrl {

    public static String apiSso;
    public static String apiLms;
    public static String apiBrd;
    public static String apiItp;
    public static String apiSrh;
    public static String apiAnl;
 
    @Value("${apiSso}")
    public void setApiSso(String apiSso) {
        this.apiSso = apiSso;
    }
 
    @Value("${apiLms}")
    public void setApiLms(String apiLms) {
        this.apiLms = apiLms;
    }
 
    @Value("${apiBrd}")
    public void setApiBrd(String apiBrd) {
        this.apiBrd = apiBrd;
    }
 
    @Value("${apiItp}")
    public void setApiItp(String apiItp) {
        this.apiItp = apiItp;
    }
 
    @Value("${apiSrh}")
    public void setApiSrh(String apiSrh) {
        this.apiSrh = apiSrh;
    }
 
    @Value("${apiAnl}")
    public void setApiAnl(String apiAnl) {
        this.apiAnl = apiAnl;
    }
 
}

