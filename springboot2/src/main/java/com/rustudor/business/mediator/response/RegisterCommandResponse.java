package com.rustudor.business.mediator.response;

import com.rustudor.business.mediator.Response;

public class RegisterCommandResponse implements Response {
    private int response;

    public RegisterCommandResponse(int response) {
        this.response = response;
    }

    public int getResponse() {
        return response;
    }

    public void setResponse(int response) {
        this.response = response;
    }
}
