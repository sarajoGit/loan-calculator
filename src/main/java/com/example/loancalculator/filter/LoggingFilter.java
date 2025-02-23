package com.example.loancalculator.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Component
public class LoggingFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(LoggingFilter.class);

    @Value("${logging.route}")
    private String loggingRoute;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        if (!request.getRequestURI().startsWith(loggingRoute)) {
            chain.doFilter(request, response);
            return;
        }

        ContentCachingRequestWrapper requestWrapper = requestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = responseWrapper(response);

        chain.doFilter(requestWrapper, responseWrapper);
        logRequest(requestWrapper);
        logResponse(responseWrapper);
    }

    private void logRequest(ContentCachingRequestWrapper request) throws IOException {
        String requestBody = new String(request.getContentAsByteArray());
        log.info("Intercepted URL: {} {}\nRequest Body: {}", request.getMethod(), request.getRequestURI(), formatJson(requestBody));
    }

    private void logResponse(ContentCachingResponseWrapper response) throws IOException {
        String responseBody = new String(response.getContentAsByteArray());
        log.info("\nResponse Body: {}", formatJson(responseBody));
        response.copyBodyToResponse();
    }

    private String formatJson(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Object jsonObj = objectMapper.readTree(json);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObj);
        } catch (Exception e) {
            return json; //return raw if formatting fails
        }
    }

    private ContentCachingRequestWrapper requestWrapper(ServletRequest request) {
        if (request instanceof ContentCachingRequestWrapper) {
            return (ContentCachingRequestWrapper) request;
        }
        return new ContentCachingRequestWrapper((HttpServletRequest) request);
    }

    private ContentCachingResponseWrapper responseWrapper(ServletResponse response) {
        if (response instanceof ContentCachingResponseWrapper) {
            return (ContentCachingResponseWrapper) response;
        }
        return new ContentCachingResponseWrapper((HttpServletResponse) response);
    }

}