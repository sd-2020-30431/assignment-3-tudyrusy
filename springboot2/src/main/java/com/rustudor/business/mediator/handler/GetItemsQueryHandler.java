package com.rustudor.business.mediator.handler;

import com.rustudor.business.Services.UserService;
import com.rustudor.business.mediator.Handler;
import com.rustudor.business.mediator.query.GetItemsQuery;
import com.rustudor.business.mediator.response.GetItemsQueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetItemsQueryHandler implements Handler<GetItemsQuery, GetItemsQueryResponse> {
    private final UserService userService;

    @Autowired
    public GetItemsQueryHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public GetItemsQueryResponse handle(GetItemsQuery getItemsQuery) {
        return new GetItemsQueryResponse(userService.getItems(getItemsQuery.getSession()));
    }
}
