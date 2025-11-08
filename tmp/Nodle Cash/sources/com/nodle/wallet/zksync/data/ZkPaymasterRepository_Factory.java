package com.nodle.wallet.zksync.data;

import com.nodle.wallet.zksync.data.api.NodlePaymasterApi;
import com.nodle.wallet.zksync.data.api.ZyfiPaymasterApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import io.nodle.cash.core.android.utils.CoroutineDispatchers;

@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
public final class ZkPaymasterRepository_Factory implements Factory<ZkPaymasterRepository> {
    private final Provider<CoroutineDispatchers> coroutineDispatchersProvider;
    private final Provider<NodlePaymasterApi> nodlePaymasterApiProvider;
    private final Provider<ZyfiPaymasterApi> zyfiPaymasterApiProvider;

    private ZkPaymasterRepository_Factory(Provider<ZyfiPaymasterApi> provider, Provider<NodlePaymasterApi> provider2, Provider<CoroutineDispatchers> provider3) {
        this.zyfiPaymasterApiProvider = provider;
        this.nodlePaymasterApiProvider = provider2;
        this.coroutineDispatchersProvider = provider3;
    }

    public static ZkPaymasterRepository_Factory create(Provider<ZyfiPaymasterApi> provider, Provider<NodlePaymasterApi> provider2, Provider<CoroutineDispatchers> provider3) {
        return new ZkPaymasterRepository_Factory(provider, provider2, provider3);
    }

    public static ZkPaymasterRepository newInstance(ZyfiPaymasterApi zyfiPaymasterApi, NodlePaymasterApi nodlePaymasterApi, CoroutineDispatchers coroutineDispatchers) {
        return new ZkPaymasterRepository(zyfiPaymasterApi, nodlePaymasterApi, coroutineDispatchers);
    }

    public ZkPaymasterRepository get() {
        return newInstance(this.zyfiPaymasterApiProvider.get(), this.nodlePaymasterApiProvider.get(), this.coroutineDispatchersProvider.get());
    }
}
