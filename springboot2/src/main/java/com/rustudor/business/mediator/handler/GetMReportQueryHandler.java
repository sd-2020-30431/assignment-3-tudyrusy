package com.rustudor.business.mediator.handler;


import com.rustudor.business.Services.QueryService;
import com.rustudor.business.mediator.Handler;
import com.rustudor.business.mediator.query.GetMReportQuery;
import com.rustudor.business.mediator.response.GetMReportQueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetMReportQueryHandler implements Handler<GetMReportQuery, GetMReportQueryResponse> {
    private final QueryService queryService;

    @Autowired
    public GetMReportQueryHandler(QueryService queryService) {
        this.queryService = queryService;
    }
    @Override
    public GetMReportQueryResponse handle(GetMReportQuery getMReportQuery) {
        return new GetMReportQueryResponse(queryService.getMonthlyReport(getMReportQuery.getSession()));
    }
}
