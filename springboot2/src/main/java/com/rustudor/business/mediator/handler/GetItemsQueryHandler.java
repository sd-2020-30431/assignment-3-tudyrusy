package com.rustudor.business.mediator.handler;

import com.rustudor.business.Services.QueryService;
import com.rustudor.business.mediator.Handler;
import com.rustudor.business.mediator.query.GetItemsQuery;
import com.rustudor.business.mediator.response.GetItemsQueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetItemsQueryHandler implements Handler<GetItemsQuery, GetItemsQueryResponse> {
    private final QueryService queryService;

    @Autowired
    public GetItemsQueryHandler(QueryService queryService) {
        this.queryService = queryService;
    }

    @Override
    public GetItemsQueryResponse handle(GetItemsQuery getItemsQuery) {
        return new GetItemsQueryResponse(queryService.getItems(getItemsQuery.getSession()));
    }
}
