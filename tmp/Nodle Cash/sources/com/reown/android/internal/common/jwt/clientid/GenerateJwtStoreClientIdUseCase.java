package com.reown.android.internal.common.jwt.clientid;

import D1.b;
import android.content.SharedPreferences;
import com.reown.android.internal.common.di.CoreNetworkModuleKt;
import com.reown.android.utils.ExtensionsKt;
import com.reown.foundation.crypto.data.repository.ClientIdJwtRepository;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0011\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/reown/android/internal/common/jwt/clientid/GenerateJwtStoreClientIdUseCase;", "", "clientIdJwtRepository", "Lcom/reown/foundation/crypto/data/repository/ClientIdJwtRepository;", "sharedPreferences", "Landroid/content/SharedPreferences;", "<init>", "(Lcom/reown/foundation/crypto/data/repository/ClientIdJwtRepository;Landroid/content/SharedPreferences;)V", "invoke", "", "relayUrl", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nGenerateJwtStoreClientIdUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GenerateJwtStoreClientIdUseCase.kt\ncom/reown/android/internal/common/jwt/clientid/GenerateJwtStoreClientIdUseCase\n+ 2 SharedPreferences.kt\nandroidx/core/content/SharedPreferencesKt\n*L\n1#1,22:1\n39#2,12:23\n*S KotlinDebug\n*F\n+ 1 GenerateJwtStoreClientIdUseCase.kt\ncom/reown/android/internal/common/jwt/clientid/GenerateJwtStoreClientIdUseCase\n*L\n18#1:23,12\n*E\n"})
public final class GenerateJwtStoreClientIdUseCase {
    @NotNull
    private final ClientIdJwtRepository clientIdJwtRepository;
    @NotNull
    private final SharedPreferences sharedPreferences;

    public GenerateJwtStoreClientIdUseCase(@NotNull ClientIdJwtRepository clientIdJwtRepository2, @NotNull SharedPreferences sharedPreferences2) {
        Intrinsics.checkNotNullParameter(clientIdJwtRepository2, "clientIdJwtRepository");
        Intrinsics.checkNotNullParameter(sharedPreferences2, "sharedPreferences");
        this.clientIdJwtRepository = clientIdJwtRepository2;
        this.sharedPreferences = sharedPreferences2;
    }

    /* access modifiers changed from: private */
    public static final Unit invoke$lambda$1(GenerateJwtStoreClientIdUseCase generateJwtStoreClientIdUseCase, String str) {
        Intrinsics.checkNotNullParameter(str, CoreNetworkModuleKt.KEY_CLIENT_ID);
        SharedPreferences.Editor edit = generateJwtStoreClientIdUseCase.sharedPreferences.edit();
        edit.putString(CoreNetworkModuleKt.KEY_CLIENT_ID, str);
        edit.apply();
        return Unit.INSTANCE;
    }

    @NotNull
    public final String invoke(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "relayUrl");
        return this.clientIdJwtRepository.generateJWT(ExtensionsKt.strippedUrl(str), new b(this, 6));
    }
}
