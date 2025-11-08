package com.reown.android.internal.common.model.type;

import I1.C0237a;
import I1.C0238b;
import com.reown.android.internal.common.JsonRpcResponse;
import com.reown.android.internal.common.model.EnvelopeType;
import com.reown.android.internal.common.model.IrnParams;
import com.reown.android.internal.common.model.Participants;
import com.reown.android.internal.common.model.WCRequest;
import com.reown.android.internal.common.model.params.CoreSignParams;
import com.reown.android.relay.WSSConnectionState;
import com.reown.foundation.common.model.Topic;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.StateFlow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\f\u001a\u00020\rH&J<\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00102\u0014\b\u0002\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\r0\u00122\u0014\b\u0002\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\r0\u0012H&JH\u0010\u0015\u001a\u00020\r2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u001a\b\u0002\u0010\u0011\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u0017\u0012\u0004\u0012\u00020\r0\u00122\u0014\b\u0002\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\r0\u0012H&J6\u0010\u0019\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00102\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\u001a2\u0014\b\u0002\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\r0\u0012H&J>\u0010\u001b\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00102\n\u0010\u001c\u001a\u0006\u0012\u0002\b\u00030\u001d2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\u001a2\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\r0\u0012H&JV\u0010\u001e\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\u00102\u0006\u0010 \u001a\u00020\u00102\u0006\u0010!\u001a\u00020\"2\n\u0010#\u001a\u0006\u0012\u0002\b\u00030\u001d2\u0006\u0010$\u001a\u00020%2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\u001a2\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\r0\u0012H&J`\u0010&\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010'\u001a\u00020(2\n\u0010\u001c\u001a\u0006\u0012\u0002\b\u00030\u001d2\b\b\u0002\u0010)\u001a\u00020*2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010,2\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\u001a2\u0014\b\u0002\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\r0\u0012H&J\\\u0010-\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010'\u001a\u00020(2\u0006\u0010.\u001a\u00020/2\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\u001a2\u0014\b\u0002\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\r0\u00122\n\b\u0002\u0010+\u001a\u0004\u0018\u00010,2\b\b\u0002\u0010)\u001a\u00020*H&JZ\u00100\u001a\u00020\r2\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u00020(2\b\b\u0002\u0010)\u001a\u00020*2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010,2\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\r0\u00122\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\u001aH&Jb\u00100\u001a\u00020\r2\u0006\u00106\u001a\u00020%2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u00020(2\b\b\u0002\u0010)\u001a\u00020*2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010,2\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\r0\u00122\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\u001aH&J.\u00107\u001a\u00020\r2\u0006\u00101\u001a\u0002022\u0006\u00105\u001a\u00020(2\b\b\u0002\u0010)\u001a\u00020*2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010,H&Jb\u00108\u001a\u00020\r2\u0006\u00101\u001a\u0002022\u0006\u00109\u001a\u00020:2\u0006\u00105\u001a\u00020(2\b\b\u0002\u0010)\u001a\u00020*2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010,2\u0014\b\u0002\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u00020\r0\u00122\u0014\b\u0002\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\r0\u0012H&Jd\u00108\u001a\u00020\r2\u0006\u00106\u001a\u00020%2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u00109\u001a\u00020:2\u0006\u00105\u001a\u00020(2\b\b\u0002\u0010)\u001a\u00020*2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010,2\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\u001a2\u0014\b\u0002\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\r0\u0012H&R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006;À\u0006\u0003"}, d2 = {"Lcom/reown/android/internal/common/model/type/RelayJsonRpcInteractorInterface;", "Lcom/reown/android/internal/common/model/type/JsonRpcInteractorInterface;", "wssConnectionState", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/reown/android/relay/WSSConnectionState;", "getWssConnectionState", "()Lkotlinx/coroutines/flow/StateFlow;", "onResubscribe", "Lkotlinx/coroutines/flow/Flow;", "", "getOnResubscribe", "()Lkotlinx/coroutines/flow/Flow;", "checkNetworkConnectivity", "", "subscribe", "topic", "Lcom/reown/foundation/common/model/Topic;", "onSuccess", "Lkotlin/Function1;", "onFailure", "", "batchSubscribe", "topics", "", "", "unsubscribe", "Lkotlin/Function0;", "proposeSession", "payload", "Lcom/reown/android/internal/common/model/type/JsonRpcClientSync;", "approveSession", "pairingTopic", "sessionTopic", "sessionProposalResponse", "Lcom/reown/android/internal/common/model/params/CoreSignParams$ApprovalParams;", "settleRequest", "correlationId", "", "publishJsonRpcRequest", "params", "Lcom/reown/android/internal/common/model/IrnParams;", "envelopeType", "Lcom/reown/android/internal/common/model/EnvelopeType;", "participants", "Lcom/reown/android/internal/common/model/Participants;", "publishJsonRpcResponse", "response", "Lcom/reown/android/internal/common/JsonRpcResponse;", "respondWithParams", "request", "Lcom/reown/android/internal/common/model/WCRequest;", "clientParams", "Lcom/reown/android/internal/common/model/type/ClientParams;", "irnParams", "requestId", "respondWithSuccess", "respondWithError", "error", "Lcom/reown/android/internal/common/model/type/Error;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface RelayJsonRpcInteractorInterface extends JsonRpcInteractorInterface {

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    static /* synthetic */ void batchSubscribe$default(RelayJsonRpcInteractorInterface relayJsonRpcInteractorInterface, List list, Function1 function1, Function1 function12, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != 0) {
                function1 = new C0238b(16);
            }
            if ((i3 & 4) != 0) {
                function12 = new C0238b(17);
            }
            relayJsonRpcInteractorInterface.batchSubscribe(list, function1, function12);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: batchSubscribe");
    }

    /* access modifiers changed from: private */
    static Unit batchSubscribe$lambda$2(List list) {
        Intrinsics.checkNotNullParameter(list, "it");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    static Unit batchSubscribe$lambda$3(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "it");
        return Unit.INSTANCE;
    }

    static /* synthetic */ void publishJsonRpcRequest$default(RelayJsonRpcInteractorInterface relayJsonRpcInteractorInterface, Topic topic, IrnParams irnParams, JsonRpcClientSync jsonRpcClientSync, EnvelopeType envelopeType, Participants participants, Function0 function0, Function1 function1, int i3, Object obj) {
        if (obj == null) {
            relayJsonRpcInteractorInterface.publishJsonRpcRequest(topic, irnParams, jsonRpcClientSync, (i3 & 8) != 0 ? EnvelopeType.ZERO : envelopeType, (i3 & 16) != 0 ? null : participants, (i3 & 32) != 0 ? new C0237a(13) : function0, (i3 & 64) != 0 ? new C0238b(12) : function1);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: publishJsonRpcRequest");
    }

    /* access modifiers changed from: private */
    static Unit publishJsonRpcRequest$lambda$6() {
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    static Unit publishJsonRpcRequest$lambda$7(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "it");
        return Unit.INSTANCE;
    }

    static /* synthetic */ void publishJsonRpcResponse$default(RelayJsonRpcInteractorInterface relayJsonRpcInteractorInterface, Topic topic, IrnParams irnParams, JsonRpcResponse jsonRpcResponse, Function0 function0, Function1 function1, Participants participants, EnvelopeType envelopeType, int i3, Object obj) {
        if (obj == null) {
            relayJsonRpcInteractorInterface.publishJsonRpcResponse(topic, irnParams, jsonRpcResponse, (i3 & 8) != 0 ? new C0237a(14) : function0, (i3 & 16) != 0 ? new C0238b(15) : function1, (i3 & 32) != 0 ? null : participants, (i3 & 64) != 0 ? EnvelopeType.ZERO : envelopeType);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: publishJsonRpcResponse");
    }

    /* access modifiers changed from: private */
    static Unit publishJsonRpcResponse$lambda$8() {
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    static Unit publishJsonRpcResponse$lambda$9(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "it");
        return Unit.INSTANCE;
    }

    static /* synthetic */ void respondWithError$default(RelayJsonRpcInteractorInterface relayJsonRpcInteractorInterface, WCRequest wCRequest, Error error, IrnParams irnParams, EnvelopeType envelopeType, Participants participants, Function1 function1, Function1 function12, int i3, Object obj) {
        if (obj == null) {
            relayJsonRpcInteractorInterface.respondWithError(wCRequest, error, irnParams, (i3 & 8) != 0 ? EnvelopeType.ZERO : envelopeType, (i3 & 16) != 0 ? null : participants, (i3 & 32) != 0 ? new C0238b(8) : function1, (i3 & 64) != 0 ? new C0238b(9) : function12);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: respondWithError");
    }

    /* access modifiers changed from: private */
    static Unit respondWithError$lambda$12(WCRequest wCRequest) {
        Intrinsics.checkNotNullParameter(wCRequest, "it");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    static Unit respondWithError$lambda$13(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "it");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    static Unit respondWithError$lambda$14() {
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    static Unit respondWithError$lambda$15(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "it");
        return Unit.INSTANCE;
    }

    static /* synthetic */ void respondWithParams$default(RelayJsonRpcInteractorInterface relayJsonRpcInteractorInterface, WCRequest wCRequest, ClientParams clientParams, IrnParams irnParams, EnvelopeType envelopeType, Participants participants, Function1 function1, Function0 function0, int i3, Object obj) {
        if (obj == null) {
            relayJsonRpcInteractorInterface.respondWithParams(wCRequest, clientParams, irnParams, (i3 & 8) != 0 ? EnvelopeType.ZERO : envelopeType, (i3 & 16) != 0 ? null : participants, function1, (i3 & 64) != 0 ? new C0237a(10) : function0);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: respondWithParams");
    }

    /* access modifiers changed from: private */
    static Unit respondWithParams$lambda$10() {
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    static Unit respondWithParams$lambda$11() {
        return Unit.INSTANCE;
    }

    static /* synthetic */ void respondWithSuccess$default(RelayJsonRpcInteractorInterface relayJsonRpcInteractorInterface, WCRequest wCRequest, IrnParams irnParams, EnvelopeType envelopeType, Participants participants, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 4) != 0) {
                envelopeType = EnvelopeType.ZERO;
            }
            if ((i3 & 8) != 0) {
                participants = null;
            }
            relayJsonRpcInteractorInterface.respondWithSuccess(wCRequest, irnParams, envelopeType, participants);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: respondWithSuccess");
    }

    static /* synthetic */ void subscribe$default(RelayJsonRpcInteractorInterface relayJsonRpcInteractorInterface, Topic topic, Function1 function1, Function1 function12, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != 0) {
                function1 = new C0238b(13);
            }
            if ((i3 & 4) != 0) {
                function12 = new C0238b(14);
            }
            relayJsonRpcInteractorInterface.subscribe(topic, function1, function12);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: subscribe");
    }

    /* access modifiers changed from: private */
    static Unit subscribe$lambda$0(Topic topic) {
        Intrinsics.checkNotNullParameter(topic, "it");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    static Unit subscribe$lambda$1(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "it");
        return Unit.INSTANCE;
    }

    static /* synthetic */ void unsubscribe$default(RelayJsonRpcInteractorInterface relayJsonRpcInteractorInterface, Topic topic, Function0 function0, Function1 function1, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != 0) {
                function0 = new C0237a(9);
            }
            if ((i3 & 4) != 0) {
                function1 = new C0238b(11);
            }
            relayJsonRpcInteractorInterface.unsubscribe(topic, function0, function1);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: unsubscribe");
    }

    /* access modifiers changed from: private */
    static Unit unsubscribe$lambda$4() {
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    static Unit unsubscribe$lambda$5(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "it");
        return Unit.INSTANCE;
    }

    void approveSession(@NotNull Topic topic, @NotNull Topic topic2, @NotNull CoreSignParams.ApprovalParams approvalParams, @NotNull JsonRpcClientSync<?> jsonRpcClientSync, long j2, @NotNull Function0<Unit> function0, @NotNull Function1<? super Throwable, Unit> function1);

    void batchSubscribe(@NotNull List<String> list, @NotNull Function1<? super List<String>, Unit> function1, @NotNull Function1<? super Throwable, Unit> function12);

    void checkNetworkConnectivity();

    @NotNull
    Flow<Object> getOnResubscribe();

    @NotNull
    StateFlow<WSSConnectionState> getWssConnectionState();

    void proposeSession(@NotNull Topic topic, @NotNull JsonRpcClientSync<?> jsonRpcClientSync, @NotNull Function0<Unit> function0, @NotNull Function1<? super Throwable, Unit> function1);

    void publishJsonRpcRequest(@NotNull Topic topic, @NotNull IrnParams irnParams, @NotNull JsonRpcClientSync<?> jsonRpcClientSync, @NotNull EnvelopeType envelopeType, @Nullable Participants participants, @NotNull Function0<Unit> function0, @NotNull Function1<? super Throwable, Unit> function1);

    void publishJsonRpcResponse(@NotNull Topic topic, @NotNull IrnParams irnParams, @NotNull JsonRpcResponse jsonRpcResponse, @NotNull Function0<Unit> function0, @NotNull Function1<? super Throwable, Unit> function1, @Nullable Participants participants, @NotNull EnvelopeType envelopeType);

    void respondWithError(long j2, @NotNull Topic topic, @NotNull Error error, @NotNull IrnParams irnParams, @NotNull EnvelopeType envelopeType, @Nullable Participants participants, @NotNull Function0<Unit> function0, @NotNull Function1<? super Throwable, Unit> function1);

    void respondWithError(@NotNull WCRequest wCRequest, @NotNull Error error, @NotNull IrnParams irnParams, @NotNull EnvelopeType envelopeType, @Nullable Participants participants, @NotNull Function1<? super WCRequest, Unit> function1, @NotNull Function1<? super Throwable, Unit> function12);

    void respondWithParams(long j2, @NotNull Topic topic, @NotNull ClientParams clientParams, @NotNull IrnParams irnParams, @NotNull EnvelopeType envelopeType, @Nullable Participants participants, @NotNull Function1<? super Throwable, Unit> function1, @NotNull Function0<Unit> function0);

    void respondWithParams(@NotNull WCRequest wCRequest, @NotNull ClientParams clientParams, @NotNull IrnParams irnParams, @NotNull EnvelopeType envelopeType, @Nullable Participants participants, @NotNull Function1<? super Throwable, Unit> function1, @NotNull Function0<Unit> function0);

    void respondWithSuccess(@NotNull WCRequest wCRequest, @NotNull IrnParams irnParams, @NotNull EnvelopeType envelopeType, @Nullable Participants participants);

    void subscribe(@NotNull Topic topic, @NotNull Function1<? super Topic, Unit> function1, @NotNull Function1<? super Throwable, Unit> function12);

    void unsubscribe(@NotNull Topic topic, @NotNull Function0<Unit> function0, @NotNull Function1<? super Throwable, Unit> function1);

    static /* synthetic */ void respondWithParams$default(RelayJsonRpcInteractorInterface relayJsonRpcInteractorInterface, long j2, Topic topic, ClientParams clientParams, IrnParams irnParams, EnvelopeType envelopeType, Participants participants, Function1 function1, Function0 function0, int i3, Object obj) {
        int i4 = i3;
        if (obj == null) {
            relayJsonRpcInteractorInterface.respondWithParams(j2, topic, clientParams, irnParams, (i4 & 16) != 0 ? EnvelopeType.ZERO : envelopeType, (i4 & 32) != 0 ? null : participants, function1, (i4 & 128) != 0 ? new C0237a(12) : function0);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: respondWithParams");
    }

    static /* synthetic */ void respondWithError$default(RelayJsonRpcInteractorInterface relayJsonRpcInteractorInterface, long j2, Topic topic, Error error, IrnParams irnParams, EnvelopeType envelopeType, Participants participants, Function0 function0, Function1 function1, int i3, Object obj) {
        int i4 = i3;
        if (obj == null) {
            relayJsonRpcInteractorInterface.respondWithError(j2, topic, error, irnParams, (i4 & 16) != 0 ? EnvelopeType.ZERO : envelopeType, (i4 & 32) != 0 ? null : participants, (i4 & 64) != 0 ? new C0237a(11) : function0, (i4 & 128) != 0 ? new C0238b(10) : function1);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: respondWithError");
    }
}
