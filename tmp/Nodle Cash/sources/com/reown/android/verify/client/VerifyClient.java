package com.reown.android.verify.client;

import com.reown.android.internal.common.KoinApplicationKt;
import com.reown.android.internal.common.di.b;
import com.reown.android.pairing.handler.PairingControllerInterface;
import com.reown.android.verify.domain.VerifyRepository;
import com.reown.android.verify.domain.VerifyResult;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import org.jetbrains.annotations.NotNull;
import org.koin.core.KoinApplication;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;
import org.koin.dsl.ModuleDSLKt;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\u0013\u001a\u00020\u0014H\u0016J@\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00140\u001a2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u00140\u001aH\u0016JH\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00140\u001a2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u00140\u001aH\u0016J2\u0010 \u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00172\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00140!2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u00140\u001aH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8BX\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0002¢\u0006\f\n\u0004\b\u0012\u0010\r\u001a\u0004\b\u0010\u0010\u0011¨\u0006\""}, d2 = {"Lcom/reown/android/verify/client/VerifyClient;", "Lcom/reown/android/verify/client/VerifyInterface;", "koinApp", "Lorg/koin/core/KoinApplication;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "<init>", "(Lorg/koin/core/KoinApplication;Lkotlinx/coroutines/CoroutineScope;)V", "verifyRepository", "Lcom/reown/android/verify/domain/VerifyRepository;", "getVerifyRepository", "()Lcom/reown/android/verify/domain/VerifyRepository;", "verifyRepository$delegate", "Lkotlin/Lazy;", "pairingController", "Lcom/reown/android/pairing/handler/PairingControllerInterface;", "getPairingController", "()Lcom/reown/android/pairing/handler/PairingControllerInterface;", "pairingController$delegate", "initialize", "", "resolve", "attestationId", "", "metadataUrl", "onSuccess", "Lkotlin/Function1;", "Lcom/reown/android/verify/domain/VerifyResult;", "onError", "", "resolveV2", "attestationJWT", "register", "Lkotlin/Function0;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nVerifyClient.kt\nKotlin\n*S Kotlin\n*F\n+ 1 VerifyClient.kt\ncom/reown/android/verify/client/VerifyClient\n+ 2 Koin.kt\norg/koin/core/Koin\n+ 3 Scope.kt\norg/koin/core/scope/Scope\n*L\n1#1,50:1\n124#2,4:51\n124#2,4:56\n142#3:55\n142#3:60\n*S KotlinDebug\n*F\n+ 1 VerifyClient.kt\ncom/reown/android/verify/client/VerifyClient\n*L\n18#1:51,4\n19#1:56,4\n18#1:55\n19#1:60\n*E\n"})
public final class VerifyClient implements VerifyInterface {
    @NotNull
    private final KoinApplication koinApp;
    @NotNull
    private final Lazy pairingController$delegate;
    @NotNull
    private final CoroutineScope scope;
    @NotNull
    private final Lazy verifyRepository$delegate;

    public VerifyClient() {
        this((KoinApplication) null, (CoroutineScope) null, 3, (DefaultConstructorMarker) null);
    }

    /* access modifiers changed from: private */
    public final PairingControllerInterface getPairingController() {
        return (PairingControllerInterface) this.pairingController$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public final VerifyRepository getVerifyRepository() {
        return (VerifyRepository) this.verifyRepository$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public static final PairingControllerInterface pairingController_delegate$lambda$1(VerifyClient verifyClient) {
        return (PairingControllerInterface) verifyClient.koinApp.getKoin().getScopeRegistry().getRootScope().get(Reflection.getOrCreateKotlinClass(PairingControllerInterface.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
    }

    /* access modifiers changed from: private */
    public static final VerifyRepository verifyRepository_delegate$lambda$0(VerifyClient verifyClient) {
        return (VerifyRepository) verifyClient.koinApp.getKoin().getScopeRegistry().getRootScope().get(Reflection.getOrCreateKotlinClass(VerifyRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
    }

    public void initialize() {
        this.koinApp.modules(ModuleDSLKt.module$default(false, new b(6), 1, (Object) null));
        Job unused = BuildersKt__Builders_commonKt.launch$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new VerifyClient$initialize$1(this, (Continuation<? super VerifyClient$initialize$1>) null), 3, (Object) null);
    }

    public void register(@NotNull String str, @NotNull Function0<Unit> function0, @NotNull Function1<? super Throwable, Unit> function1) {
        Intrinsics.checkNotNullParameter(str, "attestationId");
        Intrinsics.checkNotNullParameter(function0, "onSuccess");
        Intrinsics.checkNotNullParameter(function1, "onError");
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    public void resolve(@NotNull String str, @NotNull String str2, @NotNull Function1<? super VerifyResult, Unit> function1, @NotNull Function1<? super Throwable, Unit> function12) {
        Intrinsics.checkNotNullParameter(str, "attestationId");
        Intrinsics.checkNotNullParameter(str2, "metadataUrl");
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        try {
            getVerifyRepository().resolve(str, str2, function1, function12);
        } catch (Exception e3) {
            function12.invoke(e3);
        }
    }

    public void resolveV2(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull Function1<? super VerifyResult, Unit> function1, @NotNull Function1<? super Throwable, Unit> function12) {
        Intrinsics.checkNotNullParameter(str, "attestationId");
        Intrinsics.checkNotNullParameter(str2, "attestationJWT");
        Intrinsics.checkNotNullParameter(str3, "metadataUrl");
        Intrinsics.checkNotNullParameter(function1, "onSuccess");
        Intrinsics.checkNotNullParameter(function12, "onError");
        try {
            getVerifyRepository().resolveV2(str, str2, str3, function1, function12);
        } catch (Exception e3) {
            function12.invoke(e3);
        }
    }

    public VerifyClient(@NotNull KoinApplication koinApplication, @NotNull CoroutineScope coroutineScope) {
        Intrinsics.checkNotNullParameter(koinApplication, "koinApp");
        Intrinsics.checkNotNullParameter(coroutineScope, "scope");
        this.koinApp = koinApplication;
        this.scope = coroutineScope;
        this.verifyRepository$delegate = LazyKt.lazy(new a(this, 0));
        this.pairingController$delegate = LazyKt.lazy(new a(this, 1));
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ VerifyClient(KoinApplication koinApplication, CoroutineScope coroutineScope, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? KoinApplicationKt.getWcKoinApp() : koinApplication, (i3 & 2) != 0 ? CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null).plus(Dispatchers.getDefault())) : coroutineScope);
    }
}
