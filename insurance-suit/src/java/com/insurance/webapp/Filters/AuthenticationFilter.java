/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insurance.webapp.Filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author crazydude
 */
public class AuthenticationFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        if(servletRequest.getRequestURI().startsWith("/insurance-suit/userJsp") || 
                servletRequest.getRequestURI().startsWith("/insurance-suit/adminJsp")){
            HttpSession session = servletRequest.getSession();
           if(session.getAttribute("username") == null){
               request.getRequestDispatcher("/index.jsp").forward(servletRequest, response);
           }
           chain.doFilter(servletRequest, response);
        }
    
    }

    @Override
    public void destroy() {

    }
    
}
