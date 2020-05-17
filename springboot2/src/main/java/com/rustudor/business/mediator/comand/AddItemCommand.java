package com.rustudor.business.mediator.comand;

import com.rustudor.Dto.ItemDto;
import com.rustudor.Util.Session;
import com.rustudor.business.mediator.Request;

public class AddItemCommand implements Request {
    private ItemDto itemDto;
    private Session session;

    public AddItemCommand(ItemDto itemDto, Session session) {
        this.itemDto = itemDto;
        this.session = session;
    }

    public ItemDto getItemDto() {
        return itemDto;
    }

    public void setItemDto(ItemDto itemDto) {
        this.itemDto = itemDto;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
