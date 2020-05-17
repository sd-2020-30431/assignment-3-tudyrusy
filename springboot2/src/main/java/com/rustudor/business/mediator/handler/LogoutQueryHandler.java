package com.rustudor.business.mediator.handler;

import com.rustudor.business.Services.QueryService;
import com.rustudor.business.mediator.Handler;
import com.rustudor.business.mediator.query.LogoutQuery;
import com.rustudor.business.mediator.response.LogoutQueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogoutQueryHandler implements Handler<LogoutQuery, LogoutQueryResponse> {
    private final QueryService queryService;

    @Autowired
    public LogoutQueryHandler(QueryService queryService) {
        this.queryService = queryService;
    }

    @Override
    public LogoutQueryResponse handle(LogoutQuery logoutQuery) {
        queryService.logout(logoutQuery.getToken());
        return new LogoutQueryResponse();
    }
}
