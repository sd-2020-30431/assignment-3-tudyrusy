package com.rustudor.business.mediator.comand;

import com.rustudor.Util.Session;
import com.rustudor.business.mediator.Request;

public class SetGoalCommand implements Request {
    private int goal;
    private Session session;

    public SetGoalCommand(int goal, Session session) {
        this.goal = goal;
        this.session = session;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
