package com.nodle.wallet.zksync.utils;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import io.nodle.cash.core.android.utils.CoroutineDispatchers;
import io.nodle.cash.substrate.SubstrateEnvironmentRepository;

@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
public final class BridgeProposalProvider_Factory implements Factory<BridgeProposalProvider> {
    private final Provider<CoroutineDispatchers> coroutineDispatchersProvider;
    private final Provider<SubstrateEnvironmentRepository> substrateEnvironmentRepositoryProvider;

    private BridgeProposalProvider_Factory(Provider<SubstrateEnvironmentRepository> provider, Provider<CoroutineDispatchers> provider2) {
        this.substrateEnvironmentRepositoryProvider = provider;
        this.coroutineDispatchersProvider = provider2;
    }

    public static BridgeProposalProvider_Factory create(Provider<SubstrateEnvironmentRepository> provider, Provider<CoroutineDispatchers> provider2) {
        return new BridgeProposalProvider_Factory(provider, provider2);
    }

    public static BridgeProposalProvider newInstance(SubstrateEnvironmentRepository substrateEnvironmentRepository, CoroutineDispatchers coroutineDispatchers) {
        return new BridgeProposalProvider(substrateEnvironmentRepository, coroutineDispatchers);
    }

    public BridgeProposalProvider get() {
        return newInstance(this.substrateEnvironmentRepositoryProvider.get(), this.coroutineDispatchersProvider.get());
    }
}
