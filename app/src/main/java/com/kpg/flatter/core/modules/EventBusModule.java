package com.kpg.flatter.core.modules;

import com.google.common.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Module that provides event bus and in future other related objects
 */
@Module
public final class EventBusModule {

    @Provides
    @Singleton
    public EventBus provideEventBus(){
        return new EventBus();
    }
}
