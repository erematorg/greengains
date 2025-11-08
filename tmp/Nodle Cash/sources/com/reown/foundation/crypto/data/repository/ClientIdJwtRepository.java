package com.reown.foundation.crypto.data.repository;

import io.nodle.cash.ui.feature.walletconnect.d;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0014\b\u0002\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00070\u0006H&¨\u0006\bÀ\u0006\u0003"}, d2 = {"Lcom/reown/foundation/crypto/data/repository/ClientIdJwtRepository;", "", "generateJWT", "", "serverUrl", "getIssuerClientId", "Lkotlin/Function1;", "", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface ClientIdJwtRepository {

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    static /* synthetic */ String generateJWT$default(ClientIdJwtRepository clientIdJwtRepository, String str, Function1 function1, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != 0) {
                function1 = new d(29);
            }
            return clientIdJwtRepository.generateJWT(str, function1);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: generateJWT");
    }

    /* access modifiers changed from: private */
    static Unit generateJWT$lambda$0(String str) {
        Intrinsics.checkNotNullParameter(str, "it");
        return Unit.INSTANCE;
    }

    @NotNull
    String generateJWT(@NotNull String str, @NotNull Function1<? super String, Unit> function1);
}
