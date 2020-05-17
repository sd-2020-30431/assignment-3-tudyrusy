package com.rustudor.business.mediator.handler;


import com.rustudor.business.Services.UserService;
import com.rustudor.business.mediator.Handler;

import com.rustudor.business.mediator.query.GetWReportQuery;

import com.rustudor.business.mediator.response.GetWReportQueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetWReportQueryHandler implements Handler<GetWReportQuery, GetWReportQueryResponse> {
    private final UserService userService;

    @Autowired
    public GetWReportQueryHandler(UserService userService) {
        this.userService = userService;
    }
    @Override
    public GetWReportQueryResponse handle(GetWReportQuery getWReportQuery) {
        return new GetWReportQueryResponse(userService.getWeeklyReport(getWReportQuery.getSession()));
    }
}
