package com.github.stateless4j.sample;

import com.github.oxo42.stateless4j.StateMachine;
import com.github.oxo42.stateless4j.StateMachineConfig;
import com.github.stateless4j.sample.factory.StateConfigFactory;
import com.github.stateless4j.sample.model.MyStateConfig;
import com.github.stateless4j.sample.model.generator.RuntimeStateConfigGenerator;

import java.util.ArrayList;

public class Main1 {

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
        var config =   StateConfigFactory.getConfig(myConfigList);

        System.out.println("-State Machine Configuration Completed- ...  \n\n\n");

        try {

            System.out.println("\n\n--State Machine Started --");
            initiateValidSequence(config);
            //initiateFaultSequence(config);
            System.out.println("\n\n--State Machine completed --");

        } catch (Exception e) {
            System.out.println("\n\n--State Machine Failed with the following message --" + e.getMessage());
           // e.printStackTrace();
        }


    }


    private static void initiateValidSequence(StateMachineConfig<String , String> config) throws  Exception{

        var fsm = new StateMachine<>("IDLE", config);

        System.out.println("\n\n-Firing Initial Transition-");
        fsm.fireInitialTransition();

        System.out.println("\n\n-Firing Trigger COIN_INSERTED-");
        fsm.fire("COIN_INSERTED");

        System.out.println("\n\n-Firing Trigger CANCEL-");
        fsm.fire("CANCEL");

        System.out.println("\n\n-Firing Trigger COIN_INSERTED-");
        fsm.fire("COIN_INSERTED");

        System.out.println("\n\n-Firing Trigger START-");
        fsm.fire("START");

        System.out.println("\n\n-Firing Trigger PAUSE-");
        fsm.fire("PAUSE");

        System.out.println("\n\n-Firing Trigger RESUME-");
        fsm.fire("RESUME");

        System.out.println("\n\n-Firing Trigger STOP-");
        fsm.fire("STOP");
    }

    private static void initiateFaultSequence(StateMachineConfig<String , String> config)  throws  Exception{

        var fsm = new StateMachine<>("IDLE", config);

        System.out.println("\n\n-Firing Initial Transition-");
        fsm.fireInitialTransition();

        System.out.println("\n\n-Firing Trigger COIN_INSERTED-");
        fsm.fire("START");

        System.out.println("\n\n-Firing Trigger CANCEL-");
        fsm.fire("CANCEL");

        System.out.println("\n\n-Firing Trigger COIN_INSERTED-");
        fsm.fire("COIN_INSERTED");

        System.out.println("\n\n-Firing Trigger START-");
        fsm.fire("START");

        System.out.println("\n\n-Firing Trigger PAUSE-");
        fsm.fire("PAUSE");

        System.out.println("\n\n-Firing Trigger RESUME-");
        fsm.fire("RESUME");

        System.out.println("\n\n-Firing Trigger STOP-");
        fsm.fire("STOP");
    }

}
