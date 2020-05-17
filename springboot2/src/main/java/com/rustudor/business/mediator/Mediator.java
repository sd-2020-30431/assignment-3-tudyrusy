package com.rustudor.business.mediator;

import com.rustudor.business.mediator.handler.*;
import com.rustudor.business.mediator.query.*;
import org.springframework.beans.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;

import java.util.HashMap;
import java.util.Map;

@Component
public class Mediator implements ApplicationContextAware{
    private ApplicationContext applicationContext;
    private final Map<Class<? extends Request>,Class<? extends Handler<? extends Request,? extends Response>>> map;

    public Mediator() {
        map = new HashMap<>();

        map.put(LoginQuery.class, LoginQueryHandler.class);
        map.put(GetItemsQuery.class, GetItemsQueryHandler.class);
        map.put(LogoutQuery.class, LogoutQueryHandler.class);
        map.put(GetMReportQuery.class, GetMReportQueryHandler.class);
        map.put(GetWReportQuery.class, GetWReportQueryHandler.class);
        map.put(ViewProfileQuery.class,ViewProfileQueryHandler.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public <T extends Request, R extends Response> Handler<T, R> getHandler(T request) {
        Class<? extends Handler<? extends Request, ? extends Response>> hType = map.get(request.getClass());
        return (Handler<T, R>) applicationContext.getBean(hType);
    }
}
