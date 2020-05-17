package com.rustudor.business.mediator.comand;

import com.rustudor.Dto.ConsumptionDto;
import com.rustudor.Util.Session;
import com.rustudor.business.mediator.Request;

public class SetConsumptionCommand implements Request {
    private ConsumptionDto consumptionDto;
    private Session session;

    public SetConsumptionCommand(ConsumptionDto consumptionDto, Session session) {
        this.consumptionDto = consumptionDto;
        this.session = session;
    }

    public ConsumptionDto getConsumptionDto() {
        return consumptionDto;
    }

    public void setConsumptionDto(ConsumptionDto consumptionDto) {
        this.consumptionDto = consumptionDto;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
