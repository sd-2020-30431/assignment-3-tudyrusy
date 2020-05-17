package com.rustudor.business.mediator.handler;

import com.rustudor.business.Services.CommandService;
import com.rustudor.business.Services.UserService;
import com.rustudor.business.mediator.Handler;
import com.rustudor.business.mediator.comand.AddItemCommand;
import com.rustudor.business.mediator.response.AddItemCommandResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddItemCommandHandler implements Handler<AddItemCommand, AddItemCommandResponse> {
    private final CommandService commandService;

    @Autowired
    public AddItemCommandHandler(CommandService commandService) {
        this.commandService = commandService;
    }
    @Override
    public AddItemCommandResponse handle(AddItemCommand addItemCommand) {
        commandService.addItem(addItemCommand.getItemDto(),addItemCommand.getSession());
        return new AddItemCommandResponse();
    }
}
