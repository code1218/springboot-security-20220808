package com.study.security_junil.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AjaxFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        String xRequestedWith = httpServletRequest.getHeader("x-requested-with");
        if(!xRequestedWith.equals("XMLHttpRequest")) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<html><body><script>");
            stringBuilder.append("alert(\'잘 못 된 접근입니다.\');");
            stringBuilder.append("</script></body></html>");
            response.getWriter().print(stringBuilder.toString());
            return;
        }

        chain.doFilter(request, response);
    }
}
