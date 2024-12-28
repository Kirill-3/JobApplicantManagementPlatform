package com.team14.clientProject.accountManagementTests;

import com.team14.clientProject.loginPage.CustomAuthenticationSuccessHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CustomAuthenticationSuccessHandlerTests {

    @InjectMocks
    private CustomAuthenticationSuccessHandler successHandler;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private Authentication authentication;

    @Mock
    private HttpSession session;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testOnAuthenticationSuccess() throws Exception {
        String username = "john_doe";
        int userId = 1;

        UserDetails userDetails = User.withUsername(username).password("password").roles("USER").build();

        when(authentication.getName()).thenReturn(username);
        when(jdbcTemplate.queryForObject(any(String.class), any(Object[].class), eq(int.class)))
                .thenReturn(userId);
        when(request.getSession()).thenReturn(session);

        successHandler.onAuthenticationSuccess(request, response, authentication);

        verify(session).setAttribute("userId", userId);
        verify(response).sendRedirect("/home");
    }
}