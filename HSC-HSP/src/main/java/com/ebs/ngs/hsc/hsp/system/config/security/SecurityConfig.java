package com.ebs.ngs.hsc.hsp.system.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;

import com.ebs.ngs.core.config.security.SecurityBaseConfig;
import com.ebs.ngs.core.exception.ExceptionGlobalPath;

/**
 * JWT 기반 SpringSecurity 활성화
 * @author jisuk choi
 */
@Configuration
public class SecurityConfig extends SecurityBaseConfig {
	
	protected void configure(final HttpSecurity http) throws Exception {
		
		//csrf때문에 ajax가 안되서 잠시 주석처리하고 csrf disabled함.
		http.csrf()
		.and()
			.authorizeRequests() // 다음 리퀘스트에 대한 사용권한 체크
			.antMatchers("/**").permitAll() // 
			.anyRequest().authenticated()	// 그외 나머지 요청은 모두 인증된 회원만 접근 가능하도록 설정
			;
		
	}
	
	/**
     * Spring Security 제외항목
     */
    @Override
    public void configure(WebSecurity web) {
    	web.ignoring()
        .antMatchers(
                "/",
                "/favicon.ico",
                "/assets/**/*",
                "/static/**/*",
                "/**/*.html", 
                "/**/*.scss",
                "/**/*.css", 
                "/**/*.js", 
                "/**/*.map", 
                "/**/*.ttf", 
                "/**/*.woff", 
                "/**/*.woff2", 
                "/**/*.png",
                "/**/*.jpg", 
                "/**/*.gif",
                "/**/*.ajax",
                "/**/*.ebs",
                "/**")
        .antMatchers(ExceptionGlobalPath.C_ROOT_PATH.concat("/*"))
        .antMatchers("/error")
        // 샘플링
        .antMatchers("/auth/**")
        ;
    }

}
