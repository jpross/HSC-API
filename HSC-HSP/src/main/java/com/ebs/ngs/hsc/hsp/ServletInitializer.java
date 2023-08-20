package com.ebs.ngs.hsc.hsp;

import javax.servlet.ServletContext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.context.WebApplicationContext;

import com.ebs.ngs.hsc.hsp.system.helper.AppModuleHelper;

@SpringBootApplication
@ComponentScan({"com.ebs.ngs.core", "com.ebs.ngs.hsc.hsp", "com.ebs.ngs.api.itp", "com.ebs.biz.api.common"})
public class ServletInitializer extends SpringBootServletInitializer {

    //@Bean
    // public MultipartResolver multipartResolver() {
    //    return new CommonsMultipartResolver();
    //}
    
	// War packagging
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ServletInitializer.class);
	}
	
	// War packageging
	@Override
	protected WebApplicationContext createRootApplicationContext(ServletContext servletContext) {
		WebApplicationContext webAppContext = super.createRootApplicationContext(servletContext);
		AppModuleHelper.Init(webAppContext.getAutowireCapableBeanFactory());
		return webAppContext;
	}
	
	// bootWar 실행시
	public static void main(String[] args) {
        SpringApplication.run(ServletInitializer.class, args);
        
		ConfigurableApplicationContext cac = SpringApplication.run(ServletInitializer.class, args);
		AppModuleHelper.Init(cac.getAutowireCapableBeanFactory());
    }
	
}