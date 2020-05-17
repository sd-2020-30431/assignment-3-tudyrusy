package com.rustudor.business.mediator.query;

import com.rustudor.business.mediator.Request;

public class ViewProfileQuery implements Request {
    private String username;

    public ViewProfileQuery(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
