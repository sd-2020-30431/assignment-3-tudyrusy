package com.rustudor.business.mediator;

public interface Handler<Request, Response> {
    Response handle(Request request);
}
