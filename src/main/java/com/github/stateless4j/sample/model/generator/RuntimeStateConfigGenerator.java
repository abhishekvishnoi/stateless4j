package com.github.stateless4j.sample.model.generator;

import com.github.oxo42.stateless4j.delegates.Action;
import com.github.stateless4j.sample.features.Features;
import com.github.stateless4j.sample.model.MyStateConfig;
import com.github.stateless4j.sample.model.Permit;

import java.util.ArrayList;

public class RuntimeStateConfigGenerator {

    /**
     * Create an ArrayList of MyStateConfig , The ArrayList represents the
     * StateMachineConfig present in a YAML or JSON file
     *
     */
    public static ArrayList<MyStateConfig> generateStateConfigArrayList(){

        Action startBlinking = Features::startBlinking;
        Action stopBlinking  = Features::stopBlinking;
        Action showSongsList  = Features::showSongsList;
        Action hideSongsList  = Features::hideSongsList;
        Action consumeCoin  = Features::consumeCoin;
        Action returnCoin  = Features::returnCoin;
        Action showTimer  = Features::showTimer;
        Action hideTimer  = Features::hideTimer;
        Action playSong  = Features::playSong;
        Action pauseSong  = Features::pauseSong;

        // 1. StateConfig
        var  myConfig = new MyStateConfig();

        myConfig.setState("IDLE");
        myConfig.setOnEntry(startBlinking);
        myConfig.setOnExit(stopBlinking);
        ArrayList<Permit> permits = new ArrayList<>();
        Permit permit = new Permit();
        permit.setPermitState("SELECTION");
        permit.setPermitTrigger("COIN_INSERTED");
        permits.add(permit);
        myConfig.setPermits(permits);

        // 1. StateConfig
        var  myConfig1 = new MyStateConfig();

        myConfig1.setState("SELECTION");
        myConfig1.setOnEntry(showSongsList);
        myConfig1.setOnExit(hideSongsList);
        ArrayList<Permit> permits1 = new ArrayList<>();
        Permit permit1 = new Permit();
        permit1.setPermitState("PLAYING");
        permit1.setPermitTrigger("START");
        permit1.setPermitAction(consumeCoin);
        Permit permit5 = new Permit();
        permit5.setPermitState("IDLE");
        permit5.setPermitTrigger("CANCEL");
        permit5.setPermitAction(returnCoin);
        permits1.add(permit1);
        permits1.add(permit5);
        myConfig1.setPermits(permits1);


        // 1. StateConfig
        var  myConfig2 = new MyStateConfig();

        myConfig2.setState("RUNNING");
        myConfig2.setOnEntry(showTimer);
        myConfig2.setOnExit(hideTimer);
        ArrayList<Permit> permits2 = new ArrayList<>();
        Permit permit2 = new Permit();
        permit2.setPermitState("IDLE");
        permit2.setPermitTrigger("STOP");
        permits2.add(permit2);
        myConfig2.setPermits(permits2);

        // 1. StateConfig
        var  myConfig3 = new MyStateConfig();

        myConfig3.setState("PLAYING");
        myConfig3.setSubstateOf("RUNNING");
        myConfig3.setOnEntry(playSong);
        ArrayList<Permit> permits3 = new ArrayList<>();
        Permit permit3 = new Permit();
        permit3.setPermitState("PAUSED");
        permit3.setPermitTrigger("PAUSE");
        permits3.add(permit3);
        myConfig3.setPermits(permits3);

        // 1. StateConfig
        var  myConfig4 = new MyStateConfig();

        myConfig4.setState("PAUSED");
        myConfig4.setSubstateOf("RUNNING");
        myConfig4.setOnEntry(pauseSong);
        ArrayList<Permit> permits4 = new ArrayList<>();
        Permit permit4 = new Permit();
        permit4.setPermitState("PLAYING");
        permit4.setPermitTrigger("RESUME");
        permits4.add(permit4);
        myConfig4.setPermits(permits4);

        // Creating an Array List of Configs

        ArrayList<MyStateConfig> myConfigList = new ArrayList<>();
        myConfigList.add(myConfig);
        myConfigList.add(myConfig1);
        myConfigList.add(myConfig2);
        myConfigList.add(myConfig3);
        myConfigList.add(myConfig4);

        return myConfigList;
    }
}
