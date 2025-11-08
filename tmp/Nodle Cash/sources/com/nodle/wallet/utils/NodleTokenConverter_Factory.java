package com.nodle.wallet.utils;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
public final class NodleTokenConverter_Factory implements Factory<NodleTokenConverter> {

    public static final class InstanceHolder {
        static final NodleTokenConverter_Factory INSTANCE = new NodleTokenConverter_Factory();

        private InstanceHolder() {
        }
    }

    public static NodleTokenConverter_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static NodleTokenConverter newInstance() {
        return new NodleTokenConverter();
    }

    public NodleTokenConverter get() {
        return newInstance();
    }
}
