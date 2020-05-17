package com.rustudor.business.mediator.query;

import com.rustudor.business.mediator.Request;

public class LogoutQuery implements Request {
    private String token;

    public LogoutQuery(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
