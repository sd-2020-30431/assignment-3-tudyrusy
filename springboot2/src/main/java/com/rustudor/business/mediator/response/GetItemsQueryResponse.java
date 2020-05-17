package com.rustudor.business.mediator.response;

import com.rustudor.Dto.ItemDto1;
import com.rustudor.business.mediator.Response;

import java.util.ArrayList;

public class GetItemsQueryResponse implements Response {
    ArrayList<ItemDto1> items;

    public GetItemsQueryResponse(ArrayList<ItemDto1> items) {
        this.items = items;
    }

    public ArrayList<ItemDto1> getItems() {
        return items;
    }

    public void setItems(ArrayList<ItemDto1> items) {
        this.items = items;
    }
}
