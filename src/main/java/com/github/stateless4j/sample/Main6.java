package com.github.stateless4j.sample;

import com.github.oxo42.stateless4j.StateMachine;
import com.github.oxo42.stateless4j.StateMachineConfig;
import com.github.stateless4j.sample.factory.StateConfigFactory;
import com.github.stateless4j.sample.model.MyStateConfig;
import com.github.stateless4j.sample.model.generator.RuntimeStateConfigGenerator;

import java.util.ArrayList;
import java.util.HashMap;

public class Main6 {



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
            //initiateInvalidSequence(config);

            System.out.println("\n\n--State Machine completed --");

        } catch (Exception e) {
            System.out.println("\n\n--State Machine Failed with the following message --" + e.getMessage());
        }


    }

    // A mock of a cache ..
    static HashMap<String, String> cache = new HashMap<>();

    /**
     * Will have to push the state of the Machine to Cache .
     *
     * Persist Current state "StateMachine" to Cache .
     *
     * get Request for Transition of state .
     *
     * How do you call different Transition state ???? Maybe through request Params ,,
     *
     *THis method abstracts the call coming from a HTTP request containing
     * the valid UUID to retrieve the statemachine from cache
     *
     * @param config
     * @throws Exception
     */
    private static void initiateValidSequence(StateMachineConfig<String, String> config) throws Exception {


        fireInitialTransition(config);

        ArrayList<String> stateTransitions = new ArrayList<>();
        stateTransitions.add("COIN_INSERTED");
        stateTransitions.add("CANCEL");
        stateTransitions.add("COIN_INSERTED");
        stateTransitions.add("START");
        stateTransitions.add("PAUSE");
        stateTransitions.add("RESUME");
        stateTransitions.add("STOP");

        for (String transistion : stateTransitions) {
            fireNextransition(transistion ,config);
        }

    }


    private static void initiateInvalidSequence(StateMachineConfig<String, String> config) throws Exception {


        fireInitialTransition(config);

        ArrayList<String> stateTransitions = new ArrayList<>();
        stateTransitions.add("COIN_INSERTED");
        stateTransitions.add("START");
        stateTransitions.add("RESUME");
        stateTransitions.add("STOP");

        for (String transistion : stateTransitions) {
            fireNextransition(transistion,config);
        }

    }

    private static void fireInitialTransition(StateMachineConfig<String, String> config){
        var stateMachine = new StateMachine<>("IDLE", config);

        // first Transition is done for a Workflow by a default first method ...
        // which calls the below method fireInitialTransition() .
        stateMachine.fireInitialTransition();

        // the stateMachine object is then put to a cache...
        // here as a static object ...
        // generate UUID
        var presentState = stateMachine.getState();
        cache.put("random-config-uuid" , presentState);
    }

    private static void fireNextransition(String transistion ,StateMachineConfig<String, String> config ){

        /**
         * Next Transitions can be done after fetching the config machine fomr the cache ..
         */
        String presentState = cache.get("random-config-uuid");

        var stateMachine = new StateMachine<>(presentState, config);

        System.out.println("\n\n-Firing Trigger " + transistion + " -");
        stateMachine.fire(transistion);
        cache.put("random-config-uuid" , stateMachine.getState());
    }
    
}
