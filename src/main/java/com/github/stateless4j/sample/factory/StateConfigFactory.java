package com.github.stateless4j.sample.factory;

import com.github.oxo42.stateless4j.StateMachineConfig;
import com.github.stateless4j.sample.model.MyStateConfig;
import com.github.stateless4j.sample.model.Permit;

import java.util.ArrayList;

/**
 * StateConfigFactory provides the logic to create a  StateMachineConfig<String, String>
 *     from an Array List of MyStateConfig .
 *
 *  MyStateConfig provides an abstraction on the actual JSON or YAML configuration.
 */
public class StateConfigFactory {

    public static StateMachineConfig<String, String> getConfig(ArrayList<MyStateConfig> myConfigList){

        var smc = new StateMachineConfig<String, String>();
        System.out.println(" - Creating new Config -");

        /**
         * Iterate on the ArrayList to create configs for each State of the MachineConfiguration .
         */
        for (MyStateConfig myConfig:myConfigList) {

            System.out.println(" - Updating the Config for - " + myConfig.getState()  + "  State .");

            var sc = smc.configure(myConfig.getState());

            if(myConfig.getSubstateOf() !=null){
                sc = sc.substateOf(myConfig.getSubstateOf());
            }

            if(myConfig.getOnEntry() != null){
                sc = sc.onEntry(myConfig.getOnEntry());
            }

            if(myConfig.getOnExit() != null){
                sc = sc.onExit(myConfig.getOnExit());
            }

            if(myConfig.getPermits() != null) {

                for (Permit permit: myConfig.getPermits()) {
                    if(permit.getPermitAction()!=null) {
                        sc.permit(permit.getPermitTrigger() , permit.getPermitState() , permit.getPermitAction());
                    }else{
                        sc.permit(permit.getPermitTrigger() , permit.getPermitState());
                    }
                }
            }

        }

        return smc;
    }
}
