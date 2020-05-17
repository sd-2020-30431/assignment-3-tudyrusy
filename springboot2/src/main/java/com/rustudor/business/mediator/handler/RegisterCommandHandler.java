package com.rustudor.business.mediator.handler;

import com.rustudor.business.Services.CommandService;
import com.rustudor.business.mediator.Handler;
import com.rustudor.business.mediator.comand.RegisterCommand;
import com.rustudor.business.mediator.response.RegisterCommandResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterCommandHandler implements Handler<RegisterCommand, RegisterCommandResponse> {
    private final CommandService commandService;

    @Autowired
    public RegisterCommandHandler(CommandService commandService) {
        this.commandService = commandService;
    }
    @Override
    public RegisterCommandResponse handle(RegisterCommand registerCommand) {
        return new RegisterCommandResponse(commandService.register(registerCommand.getFullUserDto()));
    }
}
