package com.github.stateless4j.sample.model;

public class CurrentProcurementState {

    String currentStateName;

    String desiredStateName;

    String lastTransitionStatus;

    public String getCurrentStateName() {
        return currentStateName;
    }

    public void setCurrentStateName(String currentStateName) {
        this.currentStateName = currentStateName;
    }

    public String getDesiredStateName() {
        return desiredStateName;
    }

    public void setDesiredStateName(String desiredStateName) {
        this.desiredStateName = desiredStateName;
    }

    public String getLastTransitionStatus() {
        return lastTransitionStatus;
    }

    public void setLastTransitionStatus(String lastTransitionStatus) {
        this.lastTransitionStatus = lastTransitionStatus;
    }
}
