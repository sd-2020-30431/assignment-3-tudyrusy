package com.rustudor.business.mediator.handler;

import com.rustudor.business.Services.CommandService;
import com.rustudor.business.mediator.Handler;
import com.rustudor.business.mediator.comand.SetConsumptionCommand;
import com.rustudor.business.mediator.response.SetConsumptionCommandResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SetConsumptionCommandHandler implements Handler<SetConsumptionCommand, SetConsumptionCommandResponse> {
    private final CommandService commandService;

    @Autowired
    public SetConsumptionCommandHandler(CommandService commandService) {
        this.commandService = commandService;
    }
    @Override
    public SetConsumptionCommandResponse handle(SetConsumptionCommand setConsumptionCommand) {
        commandService.setConsumption(setConsumptionCommand.getConsumptionDto(),setConsumptionCommand.getSession());
        return new SetConsumptionCommandResponse();
    }
}
