package com.nodle.wallet.utils;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
public final class NodleTokenFormatterImpl_Factory implements Factory<NodleTokenFormatterImpl> {

    public static final class InstanceHolder {
        static final NodleTokenFormatterImpl_Factory INSTANCE = new NodleTokenFormatterImpl_Factory();

        private InstanceHolder() {
        }
    }

    public static NodleTokenFormatterImpl_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static NodleTokenFormatterImpl newInstance() {
        return new NodleTokenFormatterImpl();
    }

    public NodleTokenFormatterImpl get() {
        return newInstance();
    }
}
