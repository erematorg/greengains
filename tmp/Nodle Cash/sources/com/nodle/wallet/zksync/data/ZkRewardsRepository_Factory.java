package com.nodle.wallet.zksync.data;

import com.nodle.wallet.zksync.data.api.ZkRewardsApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import io.nodle.cash.core.android.utils.CoroutineDispatchers;

@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
public final class ZkRewardsRepository_Factory implements Factory<ZkRewardsRepository> {
    private final Provider<CoroutineDispatchers> coroutineDispatchersProvider;
    private final Provider<ZkRewardsApi> zkRewardsApiProvider;

    private ZkRewardsRepository_Factory(Provider<ZkRewardsApi> provider, Provider<CoroutineDispatchers> provider2) {
        this.zkRewardsApiProvider = provider;
        this.coroutineDispatchersProvider = provider2;
    }

    public static ZkRewardsRepository_Factory create(Provider<ZkRewardsApi> provider, Provider<CoroutineDispatchers> provider2) {
        return new ZkRewardsRepository_Factory(provider, provider2);
    }

    public static ZkRewardsRepository newInstance(ZkRewardsApi zkRewardsApi, CoroutineDispatchers coroutineDispatchers) {
        return new ZkRewardsRepository(zkRewardsApi, coroutineDispatchers);
    }

    public ZkRewardsRepository get() {
        return newInstance(this.zkRewardsApiProvider.get(), this.coroutineDispatchersProvider.get());
    }
}
