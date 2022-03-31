package code.sns.config.filter;

import code.sns.domain.Weather;
import code.sns.weather.WeatherApi;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class WeatherFilter implements Filter {

    private final WeatherApi weatherApi;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;


        Weather weather = weatherApi.getWeather();

        httpServletRequest.setAttribute("weather",weather);

        chain.doFilter(httpServletRequest,httpServletResponse);
    }
}
