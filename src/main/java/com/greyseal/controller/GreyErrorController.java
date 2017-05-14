package com.greyseal.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.greyseal.patterns.ErrorBuilder;
import com.greyseal.patterns.ResponseBuilder;

@RestController
public class GreyErrorController implements ErrorController {

    private static final String PATH = "/error";

    //@Value("${debug}")
    private boolean debug = true;

    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping(value = PATH)
    ResponseBuilder<ErrorBuilder> error(HttpServletRequest request, HttpServletResponse response) {
        // Appropriate HTTP response code (e.g. 404 or 500) is automatically set by Spring. 
        // Here we just define response body.
        ErrorBuilder error = buildError(response.getStatus(), getErrorAttributes(request, debug));
        return ResponseBuilder.build(error, error.getStatus(), error.getMessage());
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

	private Map<String, Object> getErrorAttributes(final HttpServletRequest request, final boolean includeStackTrace) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
    }
    
    public ErrorBuilder buildError(Integer status, Map<String, Object> errorAttributes) {
    	return new ErrorBuilder.Builder()
    						   .setStatus(status)
    						   .setError((String) errorAttributes.get("error"))
    						   .setMessage((String) errorAttributes.get("message"))
    						   .setTimeStamp(errorAttributes.get("timestamp").toString())
    						   .setTrace((String) errorAttributes.get("trace"))
    						   .build();
    }
}