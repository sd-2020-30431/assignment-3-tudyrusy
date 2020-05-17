package com.rustudor.business.mediator.handler;


import com.rustudor.business.Services.QueryService;
import com.rustudor.business.mediator.Handler;

import com.rustudor.business.mediator.query.GetWReportQuery;

import com.rustudor.business.mediator.response.GetWReportQueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetWReportQueryHandler implements Handler<GetWReportQuery, GetWReportQueryResponse> {
    private final QueryService queryService;

    @Autowired
    public GetWReportQueryHandler(QueryService queryService) {
        this.queryService = queryService;
    }
    @Override
    public GetWReportQueryResponse handle(GetWReportQuery getWReportQuery) {
        return new GetWReportQueryResponse(queryService.getWeeklyReport(getWReportQuery.getSession()));
    }
}
