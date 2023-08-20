/**
 * 0. Project  : EBS 공통API 기반 고교강의 재구축 및 패밀리사이트 클라우드 전환 개발 사업
 * 1. Package : com.ebs.ngs.skeleton.web.config
 * 2. FileName : WebMvcConfig.java
 * 3. 작성자  : osc
 * 4. 작성일  : 2020. 9. 15 오후 1:29:53
 * 5. 변경이력 
 * -------------------------------------------------------------------
 *    이름		:     일자		:    변경내용 (근거자료)
 * -------------------------------------------------------------------
 *  osc 	: 	2020. 9. 15	: 신규 개발.
 */

package com.ebs.ngs.hsc.hsp.system.config.mvc;

import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mobile.device.DeviceHandlerMethodArgumentResolver;
import org.springframework.mobile.device.DeviceResolverHandlerInterceptor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import com.ebs.ngs.core.config.WebMvcBaseConfig;
import com.ebs.ngs.hsc.hsp.system.resolver.ApiHelperArgumentResolver;
import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;

import lombok.RequiredArgsConstructor;

/**
* <pre>
* WebMvcConfig 활성화
* </pre>
* 
* @Author  : osc
* @Date    : 2020. 9. 15
* @Version : 1.0
*/
@RequiredArgsConstructor
@Configuration
public class WebMvcConfig extends WebMvcBaseConfig {
	
	private final ApiHelperArgumentResolver apiHelperArgumentResolver;
	
   @Override
   public void addInterceptors(InterceptorRegistry registry) {
       // Add Device Check Interceptor
       registry.addInterceptor(new DeviceResolverHandlerInterceptor());
   }
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(apiHelperArgumentResolver);
		argumentResolvers.add(new DeviceHandlerMethodArgumentResolver());
	}
	
	/**
	 * XSS
	 */
	@Bean
   public FilterRegistrationBean<XssEscapeServletFilter> filterRegistrationBean() {
       FilterRegistrationBean<XssEscapeServletFilter> filterRegistration = new FilterRegistrationBean<>();
       filterRegistration.setFilter(new XssEscapeServletFilter());
       filterRegistration.setOrder(1);
       return filterRegistration;
   }
	
	
}
