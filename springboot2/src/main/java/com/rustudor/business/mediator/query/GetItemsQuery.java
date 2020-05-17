package com.rustudor.business.mediator.query;

import com.rustudor.Util.Session;
import com.rustudor.business.mediator.Request;

public class GetItemsQuery implements Request {
    private Session session;

    public GetItemsQuery(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
