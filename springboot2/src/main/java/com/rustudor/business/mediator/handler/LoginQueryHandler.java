package com.rustudor.business.mediator.handler;

import com.rustudor.business.Services.QueryService;
import com.rustudor.business.mediator.Handler;
import com.rustudor.business.mediator.query.LoginQuery;
import com.rustudor.business.mediator.response.LoginQueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginQueryHandler implements Handler<LoginQuery, LoginQueryResponse> {
    private final QueryService queryService;

    @Autowired
    public LoginQueryHandler(QueryService queryService) {
        this.queryService = queryService;
    }

    @Override
    public LoginQueryResponse handle(LoginQuery loginQuery) {
        return new LoginQueryResponse(queryService.login(loginQuery.getLoginDto()));
    }
}
