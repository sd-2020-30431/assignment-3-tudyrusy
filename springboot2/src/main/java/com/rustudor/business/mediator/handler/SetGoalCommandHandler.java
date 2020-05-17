package com.rustudor.business.mediator.handler;

import com.rustudor.business.Services.CommandService;
import com.rustudor.business.mediator.Handler;
import com.rustudor.business.mediator.comand.SetGoalCommand;
import com.rustudor.business.mediator.response.SetGoalCommandResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SetGoalCommandHandler implements Handler<SetGoalCommand, SetGoalCommandResponse> {
    private final CommandService commandService;

    @Autowired
    public SetGoalCommandHandler(CommandService commandService) {
        this.commandService = commandService;
    }
    @Override
    public SetGoalCommandResponse handle(SetGoalCommand setGoalCommand) {
        commandService.setGoal(setGoalCommand.getGoal(),setGoalCommand.getSession());
        return new SetGoalCommandResponse();
    }
}
