package com.rustudor.business.mediator.query;

import com.rustudor.Util.Session;
import com.rustudor.business.mediator.Request;

public class GetMReportQuery implements Request {
    Session session;

    public GetMReportQuery(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
