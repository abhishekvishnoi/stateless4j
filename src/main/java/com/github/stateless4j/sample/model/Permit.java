package com.github.stateless4j.sample.model;

import com.github.oxo42.stateless4j.delegates.Action;

/**
 *  This Pojo will be created from Unmarshalling JSON | YAML to POJO's
 */
public class Permit {

    private Action permitAction;
    private String permitTrigger;
    private String permitState;

    public Action getPermitAction() {
        return permitAction;
    }

    public void setPermitAction(Action permitAction) {
        this.permitAction = permitAction;
    }

    public String getPermitTrigger() {
        return permitTrigger;
    }

    public void setPermitTrigger(String permitTrigger) {
        this.permitTrigger = permitTrigger;
    }

    public String getPermitState() {
        return permitState;
    }

    public void setPermitState(String permitState) {
        this.permitState = permitState;
    }
}
