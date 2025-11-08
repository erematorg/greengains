package com.nodle.wallet.di;

import com.nodle.wallet.utils.NodleTokenFormatter;
import com.nodle.wallet.utils.NodleTokenFormatterImpl;
import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b'\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0006H'¨\u0006\u0007"}, d2 = {"Lcom/nodle/wallet/di/WalletModule;", "", "<init>", "()V", "nodleFormatter", "Lcom/nodle/wallet/utils/NodleTokenFormatter;", "Lcom/nodle/wallet/utils/NodleTokenFormatterImpl;", "wallet_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@InstallIn({SingletonComponent.class})
@Module
public abstract class WalletModule {
    @NotNull
    @Binds
    public abstract NodleTokenFormatter nodleFormatter(@NotNull NodleTokenFormatterImpl nodleTokenFormatterImpl);
}
