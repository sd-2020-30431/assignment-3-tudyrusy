package com.rustudor.business.mediator.response;

import com.rustudor.Dto.TokenDto;
import com.rustudor.business.mediator.Response;

public class LoginQueryResponse implements Response {
    private TokenDto tokenDto;

    public LoginQueryResponse(TokenDto tokenDto) {
        this.tokenDto = tokenDto;
    }

    public TokenDto getTokenDto() {
        return tokenDto;
    }

    public void setTokenDto(TokenDto tokenDto) {
        this.tokenDto = tokenDto;
    }
}
