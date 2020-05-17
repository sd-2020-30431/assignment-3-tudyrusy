package com.rustudor.business.mediator.handler;

import com.rustudor.business.Services.UserService;
import com.rustudor.business.mediator.Handler;
import com.rustudor.business.mediator.query.ViewProfileQuery;
import com.rustudor.business.mediator.response.ViewProfileQueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ViewProfileQueryHandler implements Handler<ViewProfileQuery, ViewProfileQueryResponse> {
    private final UserService userService;

    @Autowired
    public ViewProfileQueryHandler(UserService userService) {
        this.userService = userService;
    }
    @Override
    public ViewProfileQueryResponse handle(ViewProfileQuery viewProfileQuery) {
        return new ViewProfileQueryResponse(userService.findByUsername(viewProfileQuery.getUsername()));
    }
}
