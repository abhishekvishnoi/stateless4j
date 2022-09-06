package com.github.stateless4j.sample.model;

import com.github.oxo42.stateless4j.delegates.Action;

import java.util.ArrayList;

/**
 *  This Pojo will be created from Unmarshalling JSON | YAML to POJO's
 */
public class MyStateConfig {

    private String state;
    private Action onEntry;
    private Action onExit;
    private String substateOf;
    private ArrayList<Permit> permits;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Action getOnEntry() {
        return onEntry;
    }

    public void setOnEntry(Action onEntry) {
        this.onEntry = onEntry;
    }

    public Action getOnExit() {
        return onExit;
    }

    public void setOnExit(Action onExit) {
        this.onExit = onExit;
    }

    public String getSubstateOf() {
        return substateOf;
    }

    public void setSubstateOf(String substateOf) {
        this.substateOf = substateOf;
    }

    public ArrayList<Permit> getPermits() {
        return permits;
    }

    public void setPermits(ArrayList<Permit> permits) {
        this.permits = permits;
    }
}
