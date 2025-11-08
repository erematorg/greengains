package com.nodle.wallet.zksync;

import com.nodle.wallet.zksync.data.ZkPaymasterRepository;
import com.nodle.wallet.zksync.data.ZkRewardsRepository;
import com.nodle.wallet.zksync.utils.BridgeProposalProvider;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import io.nodle.cash.core.android.utils.CoroutineDispatchers;

@ScopeMetadata("javax.inject.Singleton")
@DaggerGenerated
@QualifierMetadata
public final class ZkWallet_Factory implements Factory<ZkWallet> {
    private final Provider<BridgeProposalProvider> bridgeProposalProvider;
    private final Provider<CoroutineDispatchers> coroutineDispatchersProvider;
    private final Provider<ZkPaymasterRepository> zkPaymasterRepositoryProvider;
    private final Provider<ZkRewardsRepository> zkRewardsRepositoryProvider;

    private ZkWallet_Factory(Provider<CoroutineDispatchers> provider, Provider<BridgeProposalProvider> provider2, Provider<ZkPaymasterRepository> provider3, Provider<ZkRewardsRepository> provider4) {
        this.coroutineDispatchersProvider = provider;
        this.bridgeProposalProvider = provider2;
        this.zkPaymasterRepositoryProvider = provider3;
        this.zkRewardsRepositoryProvider = provider4;
    }

    public static ZkWallet_Factory create(Provider<CoroutineDispatchers> provider, Provider<BridgeProposalProvider> provider2, Provider<ZkPaymasterRepository> provider3, Provider<ZkRewardsRepository> provider4) {
        return new ZkWallet_Factory(provider, provider2, provider3, provider4);
    }

    public static ZkWallet newInstance(CoroutineDispatchers coroutineDispatchers, BridgeProposalProvider bridgeProposalProvider2, ZkPaymasterRepository zkPaymasterRepository, ZkRewardsRepository zkRewardsRepository) {
        return new ZkWallet(coroutineDispatchers, bridgeProposalProvider2, zkPaymasterRepository, zkRewardsRepository);
    }

    public ZkWallet get() {
        return newInstance(this.coroutineDispatchersProvider.get(), this.bridgeProposalProvider.get(), this.zkPaymasterRepositoryProvider.get(), this.zkRewardsRepositoryProvider.get());
    }
}
