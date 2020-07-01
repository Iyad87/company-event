package com.mobiquity.codingevents;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mobiquity.codingevents.controllers.AuthenticationController;
import com.mobiquity.codingevents.data.UserRepository;
import com.mobiquity.codingevents.models.User;

public class AuthenticationFilter extends HandlerInterceptorAdapter {
    // whitelist
    private static final List<String> whitelist = Arrays.asList("/login", "/register", "/logout", "/css");
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationController authenticationController;

    private static boolean isWhitelisted(String path) {
        for (String pathRoot : whitelist) {
            if (path.startsWith(pathRoot)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        // Don't require sign-in for whitelisted pages
        if (isWhitelisted(request.getRequestURI())) {
            // returning true indicates that the request may proceed
            return true;
        }

        // Retrieves the user’s session object, which is contained in the request
        HttpSession session = request.getSession();

        // Retrieves the User object corresponding to the given user
        User user = authenticationController.getUserFromSession(session);

        // The User is logged in
        if (user != null) {
            return true;
        }

        // The User is Not logged in
        response.sendRedirect("/login");
        return false;

    }
}
