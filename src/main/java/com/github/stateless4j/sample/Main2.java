package com.github.stateless4j.sample;

import com.github.oxo42.stateless4j.StateMachine;
import com.github.oxo42.stateless4j.StateMachineConfig;
import com.github.stateless4j.sample.factory.StateConfigFactory;
import com.github.stateless4j.sample.model.MyStateConfig;
import com.github.stateless4j.sample.model.generator.RuntimeStateConfigGenerator;

import java.util.ArrayList;
import java.util.HashMap;

public class Main2 {



    public static void main(String[] args) {

        // The ArrayList is a POJO representation of a YAML | JSON configuration file which
        // will be used in Real World to create the SateMachineConfig
        // The Class RuntimeStateConfigGenerator Abstracts the idea of conversion of YAML or JSON to java POJO's
        ArrayList<MyStateConfig> myConfigList = RuntimeStateConfigGenerator.generateStateConfigArrayList();

        System.out.println("\n\n\n-State Machine Configuration Started- ...  ");
        // StateConfigFactory configFactory = new StateConfigFactory();
        // Create an Object of the StateMachineConfig through a factory
        // 1. Pass the ArrayList of MyStateConfig objects.
        // 2. The StateConfigFactory will create the config based on the provided Array List .
        var config = StateConfigFactory.getConfig(myConfigList);

        System.out.println("-State Machine Configuration Completed- ...  \n\n\n");

        try {

            System.out.println("\n\n--State Machine Started --");
            initiateValidSequence(config);
            System.out.println("\n\n--State Machine completed --");

        } catch (Exception e) {
            System.out.println("\n\n--State Machine Failed with the following message --" + e.getMessage());
        }


    }

    /**
     * Will have to push the state of the Machine to Cache .
     *
     * Persist Current state "StateMachine" to Cache .
     *
     * get Request for Transistion of state .
     *
     *
     * @param config
     * @throws Exception
     */
    private static void initiateValidSequence(StateMachineConfig<String, String> config) throws Exception {

        var fsm = new StateMachine<>("IDLE", config);
        System.out.println("\n\n-Firing Initial Transition-");
        fsm.fireInitialTransition();

        ArrayList<String> stateTransitions = new ArrayList<>();
        stateTransitions.add("COIN_INSERTED");
        stateTransitions.add("CANCEL");
        stateTransitions.add("COIN_INSERTED");
        stateTransitions.add("START");
        stateTransitions.add("PAUSE");
        stateTransitions.add("RESUME");
        stateTransitions.add("STOP");

        for (String transistion : stateTransitions) {
            System.out.println("\n\n-Firing Trigger " + transistion + " -");
            fsm.fire(transistion);
        }

    }





  /*  static  var fsm = new StateMachine<>("IDLE",
            StateConfigFactory.getConfig(RuntimeStateConfigGenerator
                    .generateStateConfigArrayList()));
    static {
        CurrentProcurementState currentState = new CurrentProcurementState();


    }*/

// Who s i going to call ? how is the invocation dynamic .
    //  private static CurrentProcurementState setState(CurrentProcurementState currentProcurementState){

    //  }

}
