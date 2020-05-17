package com.rustudor.business.mediator.handler;

import com.rustudor.business.Services.UserService;
import com.rustudor.business.mediator.Handler;
import com.rustudor.business.mediator.query.LogoutQuery;
import com.rustudor.business.mediator.response.LogoutQueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogoutQueryHandler implements Handler<LogoutQuery, LogoutQueryResponse> {
    private final UserService userService;

    @Autowired
    public LogoutQueryHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public LogoutQueryResponse handle(LogoutQuery logoutQuery) {
        userService.logout(logoutQuery.getToken());
        return new LogoutQueryResponse();
    }
}
