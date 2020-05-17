package com.rustudor.business.mediator.query;

import com.rustudor.Dto.LoginDto;
import com.rustudor.business.mediator.Request;

public class LoginQuery implements Request {
    private LoginDto loginDto;

    public LoginQuery(LoginDto loginDto) {
        this.loginDto = loginDto;
    }

    public LoginDto getLoginDto() {
        return loginDto;
    }

    public void setLoginDto(LoginDto loginDto) {
        this.loginDto = loginDto;
    }
}
