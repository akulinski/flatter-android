package com.kpg.flatter.eventbus;

import com.google.common.eventbus.EventBus;

public class EventBusSingleton {
    private EventBus eventBus;
    private static EventBusSingleton instancce;

    private EventBusSingleton(){

        this.eventBus = new EventBus();

    }

    /**
     * Method that handles EventBus Singleton
     * @return get Instance of EventBusSingleton available throughout the whole app
     */
    public static EventBusSingleton getInstance(){

        if(instancce == null){

            synchronized (EventBusSingleton.class){
                instancce = new EventBusSingleton();
            }

        }
        return instancce;

    }

    /**
     *
     * @return Get Guava EventBus
     */
    public EventBus getEventBus() {

        return eventBus;

    }
}
