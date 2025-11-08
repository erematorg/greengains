package com.nodle.wallet.token;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
public final class DefaultTokensProvider_Factory implements Factory<DefaultTokensProvider> {

    public static final class InstanceHolder {
        static final DefaultTokensProvider_Factory INSTANCE = new DefaultTokensProvider_Factory();

        private InstanceHolder() {
        }
    }

    public static DefaultTokensProvider_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static DefaultTokensProvider newInstance() {
        return new DefaultTokensProvider();
    }

    public DefaultTokensProvider get() {
        return newInstance();
    }
}
