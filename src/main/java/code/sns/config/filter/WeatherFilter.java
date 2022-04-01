package code.sns.config.filter;

import code.sns.domain.Weather;
import code.sns.domain.dto.response.UserBirthDto;
import code.sns.service.UserService;
import code.sns.weather.WeatherApi;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class WeatherFilter implements Filter {

    private final WeatherApi weatherApi;
    private final UserService userService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;


        Weather weather = weatherApi.getWeather();
        List<UserBirthDto> birthPeople = userService.getBirthPeople(PageRequest.of(0,3));
        httpServletRequest.setAttribute("weather",weather);
        httpServletRequest.setAttribute("birthPeople",birthPeople);

        chain.doFilter(httpServletRequest,httpServletResponse);
    }
}
