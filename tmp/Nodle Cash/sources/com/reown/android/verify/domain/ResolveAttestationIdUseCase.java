package com.reown.android.verify.domain;

import S0.h;
import com.reown.android.internal.common.WalletConnectScopeKt;
import com.reown.android.internal.common.crypto.UtilsKt;
import com.reown.android.internal.common.model.Validation;
import com.reown.android.internal.common.model.WCRequest;
import com.reown.android.internal.common.storage.verify.VerifyContextStorageRepository;
import com.reown.android.verify.client.VerifyInterface;
import com.reown.android.verify.model.VerifyContext;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Charsets;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJJ\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00072\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00072\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u000b0\u0013H\u0002¢\u0006\u0002\u0010\u0015J4\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00072\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u000b0\u0013H\u0002J,\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u000b0\u0013H\u0002J,\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00072\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u000b0\u0013H\u0002J$\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u00142\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u000b0\u0013H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/reown/android/verify/domain/ResolveAttestationIdUseCase;", "", "verifyInterface", "Lcom/reown/android/verify/client/VerifyInterface;", "repository", "Lcom/reown/android/internal/common/storage/verify/VerifyContextStorageRepository;", "verifyUrl", "", "<init>", "(Lcom/reown/android/verify/client/VerifyInterface;Lcom/reown/android/internal/common/storage/verify/VerifyContextStorageRepository;Ljava/lang/String;)V", "invoke", "", "request", "Lcom/reown/android/internal/common/model/WCRequest;", "metadataUrl", "linkMode", "", "appLink", "onResolve", "Lkotlin/Function1;", "Lcom/reown/android/verify/model/VerifyContext;", "(Lcom/reown/android/internal/common/model/WCRequest;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "resolveLinkMode", "resolveVerifyV2", "resolveVerifyV1", "insertContext", "context", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ResolveAttestationIdUseCase {
    /* access modifiers changed from: private */
    @NotNull
    public final VerifyContextStorageRepository repository;
    @NotNull
    private final VerifyInterface verifyInterface;
    @NotNull
    private final String verifyUrl;

    public ResolveAttestationIdUseCase(@NotNull VerifyInterface verifyInterface2, @NotNull VerifyContextStorageRepository verifyContextStorageRepository, @NotNull String str) {
        Intrinsics.checkNotNullParameter(verifyInterface2, "verifyInterface");
        Intrinsics.checkNotNullParameter(verifyContextStorageRepository, "repository");
        Intrinsics.checkNotNullParameter(str, "verifyUrl");
        this.verifyInterface = verifyInterface2;
        this.repository = verifyContextStorageRepository;
        this.verifyUrl = str;
    }

    private final void insertContext(VerifyContext verifyContext, Function1<? super VerifyContext, Unit> function1) {
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new ResolveAttestationIdUseCase$insertContext$1(this, verifyContext, function1, (Continuation<? super ResolveAttestationIdUseCase$insertContext$1>) null), 3, (Object) null);
    }

    public static /* synthetic */ void invoke$default(ResolveAttestationIdUseCase resolveAttestationIdUseCase, WCRequest wCRequest, String str, Boolean bool, String str2, Function1 function1, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            bool = Boolean.FALSE;
        }
        Boolean bool2 = bool;
        if ((i3 & 8) != 0) {
            str2 = null;
        }
        resolveAttestationIdUseCase.invoke(wCRequest, str, bool2, str2, function1);
    }

    /* access modifiers changed from: private */
    public static final Unit invoke$lambda$0(Function1 function1, VerifyContext verifyContext) {
        Intrinsics.checkNotNullParameter(verifyContext, "verifyContext");
        function1.invoke(verifyContext);
        return Unit.INSTANCE;
    }

    private final void resolveLinkMode(WCRequest wCRequest, String str, String str2, Function1<? super VerifyContext, Unit> function1) {
        insertContext(new VerifyContext(wCRequest.getId(), str, VerifyUtilsKt.getValidation(str, str2), Intrinsics.checkNotNullParameter(StringCompanionObject.INSTANCE, "<this>"), (Boolean) null), new h(function1, 13));
    }

    /* access modifiers changed from: private */
    public static final Unit resolveLinkMode$lambda$1(Function1 function1, VerifyContext verifyContext) {
        Intrinsics.checkNotNullParameter(verifyContext, "verifyContext");
        function1.invoke(verifyContext);
        return Unit.INSTANCE;
    }

    private final void resolveVerifyV1(WCRequest wCRequest, String str, Function1<? super VerifyContext, Unit> function1) {
        VerifyInterface verifyInterface2 = this.verifyInterface;
        byte[] bytes = wCRequest.getMessage().getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        verifyInterface2.resolve(UtilsKt.sha256(bytes), str, new a(this, wCRequest, function1, 0), new a(this, wCRequest, function1, 1));
    }

    /* access modifiers changed from: private */
    public static final Unit resolveVerifyV1$lambda$7(ResolveAttestationIdUseCase resolveAttestationIdUseCase, WCRequest wCRequest, Function1 function1, VerifyResult verifyResult) {
        Intrinsics.checkNotNullParameter(verifyResult, "result");
        resolveAttestationIdUseCase.insertContext(new VerifyContext(wCRequest.getId(), verifyResult.getOrigin(), verifyResult.getValidation(), resolveAttestationIdUseCase.verifyUrl, verifyResult.isScam()), new h(function1, 17));
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit resolveVerifyV1$lambda$7$lambda$6(Function1 function1, VerifyContext verifyContext) {
        Intrinsics.checkNotNullParameter(verifyContext, "verifyContext");
        function1.invoke(verifyContext);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit resolveVerifyV1$lambda$9(ResolveAttestationIdUseCase resolveAttestationIdUseCase, WCRequest wCRequest, Function1 function1, Throwable th) {
        Intrinsics.checkNotNullParameter(th, "it");
        resolveAttestationIdUseCase.insertContext(new VerifyContext(wCRequest.getId(), Intrinsics.checkNotNullParameter(StringCompanionObject.INSTANCE, "<this>"), Validation.UNKNOWN, resolveAttestationIdUseCase.verifyUrl, (Boolean) null), new h(function1, 15));
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit resolveVerifyV1$lambda$9$lambda$8(Function1 function1, VerifyContext verifyContext) {
        Intrinsics.checkNotNullParameter(verifyContext, "verifyContext");
        function1.invoke(verifyContext);
        return Unit.INSTANCE;
    }

    private final void resolveVerifyV2(String str, WCRequest wCRequest, Function1<? super VerifyContext, Unit> function1) {
        VerifyInterface verifyInterface2 = this.verifyInterface;
        byte[] bytes = wCRequest.getEncryptedMessage().getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        String sha256 = UtilsKt.sha256(bytes);
        String attestation = wCRequest.getAttestation();
        Intrinsics.checkNotNull(attestation);
        verifyInterface2.resolveV2(sha256, attestation, str, new a(this, wCRequest, function1, 2), new a(this, wCRequest, function1, 3));
    }

    /* access modifiers changed from: private */
    public static final Unit resolveVerifyV2$lambda$3(ResolveAttestationIdUseCase resolveAttestationIdUseCase, WCRequest wCRequest, Function1 function1, VerifyResult verifyResult) {
        Intrinsics.checkNotNullParameter(verifyResult, "result");
        resolveAttestationIdUseCase.insertContext(new VerifyContext(wCRequest.getId(), verifyResult.getOrigin(), verifyResult.getValidation(), resolveAttestationIdUseCase.verifyUrl, verifyResult.isScam()), new h(function1, 14));
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit resolveVerifyV2$lambda$3$lambda$2(Function1 function1, VerifyContext verifyContext) {
        Intrinsics.checkNotNullParameter(verifyContext, "verifyContext");
        function1.invoke(verifyContext);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit resolveVerifyV2$lambda$5(ResolveAttestationIdUseCase resolveAttestationIdUseCase, WCRequest wCRequest, Function1 function1, Throwable th) {
        Intrinsics.checkNotNullParameter(th, "it");
        resolveAttestationIdUseCase.insertContext(new VerifyContext(wCRequest.getId(), Intrinsics.checkNotNullParameter(StringCompanionObject.INSTANCE, "<this>"), Validation.UNKNOWN, resolveAttestationIdUseCase.verifyUrl, (Boolean) null), new h(function1, 18));
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit resolveVerifyV2$lambda$5$lambda$4(Function1 function1, VerifyContext verifyContext) {
        Intrinsics.checkNotNullParameter(verifyContext, "verifyContext");
        function1.invoke(verifyContext);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull WCRequest wCRequest, @NotNull String str, @Nullable Boolean bool, @Nullable String str2, @NotNull Function1<? super VerifyContext, Unit> function1) {
        Intrinsics.checkNotNullParameter(wCRequest, "request");
        Intrinsics.checkNotNullParameter(str, "metadataUrl");
        Intrinsics.checkNotNullParameter(function1, "onResolve");
        if (!Intrinsics.areEqual((Object) bool, (Object) Boolean.TRUE) || str2 == null || str2.length() == 0) {
            String attestation = wCRequest.getAttestation();
            if (attestation == null || attestation.length() == 0) {
                String attestation2 = wCRequest.getAttestation();
                if (attestation2 == null || attestation2.length() != 0) {
                    resolveVerifyV1(wCRequest, str, function1);
                } else {
                    insertContext(new VerifyContext(wCRequest.getId(), Intrinsics.checkNotNullParameter(StringCompanionObject.INSTANCE, "<this>"), Validation.UNKNOWN, this.verifyUrl, (Boolean) null), new h(function1, 16));
                }
            } else {
                resolveVerifyV2(str, wCRequest, function1);
            }
        } else {
            resolveLinkMode(wCRequest, str, str2, function1);
        }
    }
}
