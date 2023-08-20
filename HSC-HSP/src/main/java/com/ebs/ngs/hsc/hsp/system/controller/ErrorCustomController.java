/**
 * 0. Project  : EBS 공통API 기반 고교강의 재구축 및 패밀리사이트 클라우드 전환 개발 사업
 * 1. Package : com.ebs.ngs.skeleton.web.controller
 * 2. FileName : WebController.java
 * 3. 작성자  : osc
 * 4. 작성일  : 2020. 9. 16 오후 7:37:43
 * 5. 변경이력 
 * -------------------------------------------------------------------
 *    이름		:     일자		:    변경내용 (근거자료)
 * -------------------------------------------------------------------
 *  osc 	: 	2020. 9. 16	: 신규 개발.
 */

package com.ebs.ngs.hsc.hsp.system.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class ErrorCustomController implements ErrorController {

	private static final Logger logger = LoggerFactory.getLogger(ErrorCustomController.class);

    private static final String ERROR_PATH = "/error";
 
    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
    
    @RequestMapping(value={"/error","/error.ebs"})
    public String handleError(HttpServletRequest request, Model model, Exception exception) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        HttpStatus httpStatus = HttpStatus.valueOf(Integer.valueOf(status.toString()));
        
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		Enumeration<?> enums = request.getParameterNames();
		while (enums.hasMoreElements()) {
			String paramName = (String) enums.nextElement();
			String[] parameters = request.getParameterValues(paramName);

			// Parameter가 배열일 경우
			if (parameters.length > 1) {
				parameterMap.put(paramName, parameters);
				// Parameter가 배열이 아닌 경우
			} else {
				parameterMap.put(paramName, parameters[0]);
			}
		}

        Object errorException = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        if(null != errorException) {
            StringWriter outError = new StringWriter();
            //((Throwable) errorException).printStackTrace(new PrintWriter(outError));
            model.addAttribute("outError", outError);
            model.addAttribute("errorException", errorException);
        }
		model.addAttribute("parameterMap", parameterMap);
		model.addAttribute("getRequestURI",request.getRequestURI());
		model.addAttribute("getRequestURL",request.getRequestURL());
        model.addAttribute("userAgent", request.getHeader("User-Agent").toLowerCase());
        model.addAttribute("httpStatus", httpStatus.toString());
        model.addAttribute("msg", httpStatus.getReasonPhrase());
        
        String errorCode = status.toString();
        if(null != request.getParameter("importantCode")) {
        	errorCode = String.valueOf(request.getParameter("importantCode"));
        }
        
        model.addAttribute("code", errorCode);
        
		SimpleDateFormat dd = new SimpleDateFormat( "yyyy.MM.dd HH:mm:ss" , Locale.KOREA );
		String curDtStr = dd.format(new Date());
        
        model.addAttribute("timestamp", curDtStr);
        return "error/error";
    }
 
	
}
