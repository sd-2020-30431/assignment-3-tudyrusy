package com.rustudor.business.mediator.comand;

import com.rustudor.Dto.FullUserDto;
import com.rustudor.business.mediator.Request;

public class RegisterCommand implements Request {
    private FullUserDto fullUserDto;

    public RegisterCommand(FullUserDto fullUserDto) {
        this.fullUserDto = fullUserDto;
    }

    public FullUserDto getFullUserDto() {
        return fullUserDto;
    }

    public void setFullUserDto(FullUserDto fullUserDto) {
        this.fullUserDto = fullUserDto;
    }
}
