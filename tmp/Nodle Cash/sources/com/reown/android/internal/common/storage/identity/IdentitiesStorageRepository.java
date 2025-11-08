package com.reown.android.internal.common.storage.identity;

import com.reown.android.internal.common.model.AccountId;
import com.reown.android.internal.common.signing.cacao.Cacao;
import com.reown.android.sdk.storage.data.dao.IdentitiesQueries;
import com.squareup.moshi.Moshi;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J0\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H@¢\u0006\u0004\b\u0015\u0010\u0016J\u0016\u0010\u0017\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH@¢\u0006\u0002\u0010\u0018J\u0018\u0010\u0019\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000eH@¢\u0006\u0004\b\u001a\u0010\u0018J\u0018\u0010\u001b\u001a\u0004\u0018\u00010\u00122\u0006\u0010\r\u001a\u00020\u000eH@¢\u0006\u0002\u0010\u0018R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/reown/android/internal/common/storage/identity/IdentitiesStorageRepository;", "", "identities", "Lcom/reown/android/sdk/storage/data/dao/IdentitiesQueries;", "moshiBuilder", "Lcom/squareup/moshi/Moshi$Builder;", "<init>", "(Lcom/reown/android/sdk/storage/data/dao/IdentitiesQueries;Lcom/squareup/moshi/Moshi$Builder;)V", "moshi", "Lcom/squareup/moshi/Moshi;", "kotlin.jvm.PlatformType", "insertIdentity", "", "identityPublicKey", "", "accountId", "Lcom/reown/android/internal/common/model/AccountId;", "cacaoPayload", "Lcom/reown/android/internal/common/signing/cacao/Cacao$Payload;", "isOwner", "", "insertIdentity-RiN_R_Q", "(Ljava/lang/String;Ljava/lang/String;Lcom/reown/android/internal/common/signing/cacao/Cacao$Payload;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "removeIdentity", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAccountId", "getAccountId-yrRQGmQ", "getCacaoPayloadByIdentity", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nIdentitiesStorageRepository.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IdentitiesStorageRepository.kt\ncom/reown/android/internal/common/storage/identity/IdentitiesStorageRepository\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,21:1\n1#2:22\n*E\n"})
public final class IdentitiesStorageRepository {
    @NotNull
    private final IdentitiesQueries identities;
    private final Moshi moshi;

    public IdentitiesStorageRepository(@NotNull IdentitiesQueries identitiesQueries, @NotNull Moshi.Builder builder) {
        Intrinsics.checkNotNullParameter(identitiesQueries, "identities");
        Intrinsics.checkNotNullParameter(builder, "moshiBuilder");
        this.identities = identitiesQueries;
        this.moshi = builder.build();
    }

    @Nullable
    /* renamed from: getAccountId-yrRQGmQ  reason: not valid java name */
    public final Object m8805getAccountIdyrRQGmQ(@NotNull String str, @NotNull Continuation<? super AccountId> continuation) {
        return AccountId.m8750constructorimpl(this.identities.getAccountIdByIdentity(str).executeAsOne());
    }

    @Nullable
    public final Object getCacaoPayloadByIdentity(@NotNull String str, @NotNull Continuation<? super Cacao.Payload> continuation) {
        Object obj;
        try {
            Result.Companion companion = Result.Companion;
            String cacao_payload = this.identities.getCacaoPayloadByIdentity(str).executeAsOne().getCacao_payload();
            obj = Result.m8979constructorimpl(cacao_payload != null ? this.moshi.adapter(Cacao.Payload.class).fromJson(cacao_payload) : null);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.m8979constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m8985isFailureimpl(obj)) {
            return null;
        }
        return obj;
    }

    @Nullable
    /* renamed from: insertIdentity-RiN_R_Q  reason: not valid java name */
    public final Object m8806insertIdentityRiN_R_Q(@NotNull String str, @NotNull String str2, @NotNull Cacao.Payload payload, boolean z2, @NotNull Continuation<? super Unit> continuation) {
        this.identities.insertOrAbortIdentity(str, str2, this.moshi.adapter(Cacao.Payload.class).toJson(payload), Boxing.boxBoolean(z2));
        return Unit.INSTANCE;
    }

    @Nullable
    public final Object removeIdentity(@NotNull String str, @NotNull Continuation<? super Unit> continuation) {
        this.identities.removeIdentity(str);
        return Unit.INSTANCE;
    }
}
