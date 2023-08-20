package com.ebs.ngs.hsc.hsp.system.interceptor;

import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class DateParameterInterceptor extends HandlerInterceptorAdapter {
	
	JSONObject json = null;
	
	public DateParameterInterceptor() throws Exception{
		ClassPathResource resource= new ClassPathResource("json/dateParameterPattern.json");
		json = (JSONObject)new JSONParser().parse(new InputStreamReader(resource.getInputStream(),"UTF-8"));		
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		JSONArray list=(JSONArray)json.get("list");
		
		JSONArray uriList=(JSONArray)json.get("uriList");
		
		boolean isCheckContinue=true;
		
		if(uriList!=null && uriList.size()>0) {
			
			isCheckContinue=false;
			
			for(Object uri  : uriList) {
				JSONObject jsonObject=(JSONObject)uri;
				String uriVal=(String)jsonObject.get("uri");
				if(request.getRequestURI().startsWith(uriVal)) {
					isCheckContinue=true;
					break;
				}
			}
		}
		
		
		if(isCheckContinue && list!=null) {		
		
			Enumeration<String> paramNames=request.getParameterNames();
			
			while(paramNames.hasMoreElements()) {
				boolean notAllowedDatePattern=false;
				
				String paramKey=(String)paramNames.nextElement();
				
				for(Object date : list) {
					JSONObject jsonObject=(JSONObject)date;
					String dateParamname=(String)jsonObject.get("key");
					
					if(paramKey.equals(dateParamname)) {
						
						int c=1; // 배열형 파람의 모든 값이 참인지 체크
						
						String[] paramValues=request.getParameterValues(paramKey);						
						for(String paramValue : paramValues) {													
							if(paramValue!=null && paramValue.length()>0) {
								String pattern=(String)jsonObject.get("pattern");
								SimpleDateFormat df=new SimpleDateFormat(pattern);
								
								df.setLenient(false);
								try {
									if(paramValue.length()>pattern.length()) throw new ParseException("Invalid param length",pattern.length());
									df.parse(paramValue);
									c=c&1;
								}catch(ParseException pe) {
									c=c&0;
									notAllowedDatePattern=true;
								}														
							}							
						}
						
						if(c==1) {
							//정합한 파라메터면 통과 다음파라메터 검사
							notAllowedDatePattern=false;
							break;
						}
					}
				}
				
				if(notAllowedDatePattern) {
					return false;
				}
			}		
		}
		
		return true;
	}
}