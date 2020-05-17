package com.rustudor.business.mediator.handler;


import com.rustudor.business.Services.UserService;
import com.rustudor.business.mediator.Handler;
import com.rustudor.business.mediator.query.GetMReportQuery;
import com.rustudor.business.mediator.response.GetMReportQueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetMReportQueryHandler implements Handler<GetMReportQuery, GetMReportQueryResponse> {
    private final UserService userService;

    @Autowired
    public GetMReportQueryHandler(UserService userService) {
        this.userService = userService;
    }
    @Override
    public GetMReportQueryResponse handle(GetMReportQuery getMReportQuery) {
        return new GetMReportQueryResponse(userService.getMonthlyReport(getMReportQuery.getSession()));
    }
}
