package com.rustudor.business.mediator.handler;

import com.rustudor.business.Services.UserService;
import com.rustudor.business.mediator.Handler;
import com.rustudor.business.mediator.query.LoginQuery;
import com.rustudor.business.mediator.response.LoginQueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginQueryHandler implements Handler<LoginQuery, LoginQueryResponse> {
    private final UserService userService;

    @Autowired
    public LoginQueryHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public LoginQueryResponse handle(LoginQuery loginQuery) {
        return new LoginQueryResponse(userService.login(loginQuery.getLoginDto()));
    }
}
