package com.reown.sign.engine.use_case.calls;

import com.reown.android.internal.common.JsonRpcResponse;
import com.reown.android.internal.common.WalletConnectScopeKt;
import com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface;
import com.reown.foundation.util.Logger;
import com.reown.sign.common.model.vo.clientsync.session.SignRpc;
import com.reown.sign.storage.sequence.SessionStorageRepository;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJH\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000b0\u000f2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u000b0\u000f2\u0006\u0010\u0012\u001a\u00020\u0013H@¢\u0006\u0004\b\u0014\u0010\u0015JO\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u00182\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000b0\u000f2\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u000b0\u000fH\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ2\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u001d2\u001a\b\u0002\u0010\u001e\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020 0\u001f\u0012\u0004\u0012\u00020\u000b0\u000fH@¢\u0006\u0002\u0010!R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/reown/sign/engine/use_case/calls/PingUseCase;", "Lcom/reown/sign/engine/use_case/calls/PingUseCaseInterface;", "jsonRpcInteractor", "Lcom/reown/android/internal/common/model/type/RelayJsonRpcInteractorInterface;", "sessionStorageRepository", "Lcom/reown/sign/storage/sequence/SessionStorageRepository;", "logger", "Lcom/reown/foundation/util/Logger;", "<init>", "(Lcom/reown/android/internal/common/model/type/RelayJsonRpcInteractorInterface;Lcom/reown/sign/storage/sequence/SessionStorageRepository;Lcom/reown/foundation/util/Logger;)V", "ping", "", "topic", "", "onSuccess", "Lkotlin/Function1;", "onFailure", "", "timeout", "Lkotlin/time/Duration;", "ping-zkXUZaI", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPingSuccess", "pingPayload", "Lcom/reown/sign/common/model/vo/clientsync/session/SignRpc$SessionPing;", "onPingSuccess-gRj5Bb8", "(JLcom/reown/sign/common/model/vo/clientsync/session/SignRpc$SessionPing;Lkotlin/jvm/functions/Function1;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "collectResponse", "id", "", "onResponse", "Lkotlin/Result;", "Lcom/reown/android/internal/common/JsonRpcResponse$JsonRpcResult;", "(JLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPingUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PingUseCase.kt\ncom/reown/sign/engine/use_case/calls/PingUseCase\n+ 2 Transform.kt\nkotlinx/coroutines/flow/FlowKt__TransformKt\n+ 3 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n+ 4 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt\n*L\n1#1,95:1\n17#2:96\n19#2:100\n46#3:97\n51#3:99\n105#4:98\n*S KotlinDebug\n*F\n+ 1 PingUseCase.kt\ncom/reown/sign/engine/use_case/calls/PingUseCase\n*L\n83#1:96\n83#1:100\n83#1:97\n83#1:99\n83#1:98\n*E\n"})
public final class PingUseCase implements PingUseCaseInterface {
    /* access modifiers changed from: private */
    @NotNull
    public final RelayJsonRpcInteractorInterface jsonRpcInteractor;
    /* access modifiers changed from: private */
    @NotNull
    public final Logger logger;
    /* access modifiers changed from: private */
    @NotNull
    public final SessionStorageRepository sessionStorageRepository;

    public PingUseCase(@NotNull RelayJsonRpcInteractorInterface relayJsonRpcInteractorInterface, @NotNull SessionStorageRepository sessionStorageRepository2, @NotNull Logger logger2) {
        Intrinsics.checkNotNullParameter(relayJsonRpcInteractorInterface, "jsonRpcInteractor");
        Intrinsics.checkNotNullParameter(sessionStorageRepository2, "sessionStorageRepository");
        Intrinsics.checkNotNullParameter(logger2, "logger");
        this.jsonRpcInteractor = relayJsonRpcInteractorInterface;
        this.sessionStorageRepository = sessionStorageRepository2;
        this.logger = logger2;
    }

    /* access modifiers changed from: private */
    public final Object collectResponse(long j2, Function1<? super Result<JsonRpcResponse.JsonRpcResult>, Unit> function1, Continuation<? super Unit> continuation) {
        Object collect = new PingUseCase$collectResponse$$inlined$filter$1(this.jsonRpcInteractor.getPeerResponse(), j2).collect(new PingUseCase$collectResponse$4(function1), continuation);
        return collect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
    }

    public static /* synthetic */ Object collectResponse$default(PingUseCase pingUseCase, long j2, Function1 function1, Continuation continuation, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            function1 = new g(2);
        }
        return pingUseCase.collectResponse(j2, function1, continuation);
    }

    /* access modifiers changed from: private */
    public static final Unit collectResponse$lambda$0(Result result) {
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    /* renamed from: onPingSuccess-gRj5Bb8  reason: not valid java name */
    public final void m8886onPingSuccessgRj5Bb8(long j2, SignRpc.SessionPing sessionPing, Function1<? super String, Unit> function1, String str, Function1<? super Throwable, Unit> function12) {
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new PingUseCase$onPingSuccess$1(j2, function12, this, sessionPing, function1, str, (Continuation<? super PingUseCase$onPingSuccess$1>) null), 3, (Object) null);
    }

    @Nullable
    /* renamed from: ping-zkXUZaI  reason: not valid java name */
    public Object m8887pingzkXUZaI(@NotNull String str, @NotNull Function1<? super String, Unit> function1, @NotNull Function1<? super Throwable, Unit> function12, long j2, @NotNull Continuation<? super Unit> continuation) {
        Object supervisorScope = SupervisorKt.supervisorScope(new PingUseCase$ping$2(this, str, function12, j2, function1, (Continuation<? super PingUseCase$ping$2>) null), continuation);
        return supervisorScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? supervisorScope : Unit.INSTANCE;
    }
}
