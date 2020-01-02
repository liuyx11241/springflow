package com.github.liuyx11241.springflow.showcase;

import com.github.liuyx11241.springflow.config.SpecificFlowDecider;
import com.github.liuyx11241.springflow.stereotype.SpecificFlow;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class HeaderDeciderFilter implements Filter, SpecificFlowDecider {

    private ThreadLocal<String> country = new ThreadLocal<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        country.set(req.getHeader("X-MOCK-COUNTRY"));
        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    @Override
    public Class<? extends SpecificFlow> currentSpecific() {
        if ("de".equalsIgnoreCase(country.get())) {
            return GermanySpecific.class;
        }
        return ItalySpecific.class;
    }
}
