package com.nodle.wallet.token;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
public final class NodleTokenProvider_Factory implements Factory<NodleTokenProvider> {

    public static final class InstanceHolder {
        static final NodleTokenProvider_Factory INSTANCE = new NodleTokenProvider_Factory();

        private InstanceHolder() {
        }
    }

    public static NodleTokenProvider_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static NodleTokenProvider newInstance() {
        return new NodleTokenProvider();
    }

    public NodleTokenProvider get() {
        return newInstance();
    }
}
