package com.reown.android.internal.common.json_rpc.domain.link_mode;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Base64;
import com.reown.android.internal.common.JsonRpcResponse;
import com.reown.android.internal.common.KoinApplicationKt;
import com.reown.android.internal.common.WalletConnectScopeKt;
import com.reown.android.internal.common.crypto.codec.Codec;
import com.reown.android.internal.common.json_rpc.data.JsonRpcSerializer;
import com.reown.android.internal.common.model.EnvelopeType;
import com.reown.android.internal.common.model.Participants;
import com.reown.android.internal.common.model.SDKError;
import com.reown.android.internal.common.model.TransportType;
import com.reown.android.internal.common.model.WCRequest;
import com.reown.android.internal.common.model.WCResponse;
import com.reown.android.internal.common.model.sync.ClientJsonRpc;
import com.reown.android.internal.common.model.type.ClientParams;
import com.reown.android.internal.common.model.type.JsonRpcClientSync;
import com.reown.android.internal.common.storage.rpc.JsonRpcHistory;
import com.reown.android.push.notifications.PushMessagingService;
import com.reown.foundation.common.model.Topic;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ,\u0010\u001d\u001a\u00020\u001e2\n\u0010\u001f\u001a\u0006\u0012\u0002\b\u00030 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0016J2\u0010'\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\"2\u0006\u0010(\u001a\u00020)2\u0006\u0010#\u001a\u00020$2\b\u0010*\u001a\u0004\u0018\u00010+2\u0006\u0010%\u001a\u00020&H\u0016J\u0010\u0010,\u001a\u00020\u001e2\u0006\u0010-\u001a\u00020$H\u0016J(\u0010.\u001a\u00020\u001e2\u0006\u0010/\u001a\u0002002\b\u0010!\u001a\u0004\u0018\u00010$2\u0006\u00101\u001a\u00020$H@¢\u0006\u0002\u00102J\u0016\u00103\u001a\u00020\u001e2\u0006\u00104\u001a\u000205H@¢\u0006\u0002\u00106J\u0016\u00107\u001a\u00020\u001e2\u0006\u00108\u001a\u000209H@¢\u0006\u0002\u0010:J\"\u0010;\u001a\u00020\u00102\b\u0010!\u001a\u0004\u0018\u00010$2\u0006\u0010/\u001a\u0002002\u0006\u0010<\u001a\u00020=H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000b8BX\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u0012X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u000fX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u0012X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u000fX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0012X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014¨\u0006>"}, d2 = {"Lcom/reown/android/internal/common/json_rpc/domain/link_mode/LinkModeJsonRpcInteractor;", "Lcom/reown/android/internal/common/json_rpc/domain/link_mode/LinkModeJsonRpcInteractorInterface;", "chaChaPolyCodec", "Lcom/reown/android/internal/common/crypto/codec/Codec;", "jsonRpcHistory", "Lcom/reown/android/internal/common/storage/rpc/JsonRpcHistory;", "context", "Landroid/content/Context;", "<init>", "(Lcom/reown/android/internal/common/crypto/codec/Codec;Lcom/reown/android/internal/common/storage/rpc/JsonRpcHistory;Landroid/content/Context;)V", "serializer", "Lcom/reown/android/internal/common/json_rpc/data/JsonRpcSerializer;", "getSerializer", "()Lcom/reown/android/internal/common/json_rpc/data/JsonRpcSerializer;", "_clientSyncJsonRpc", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/reown/android/internal/common/model/WCRequest;", "clientSyncJsonRpc", "Lkotlinx/coroutines/flow/SharedFlow;", "getClientSyncJsonRpc", "()Lkotlinx/coroutines/flow/SharedFlow;", "_peerResponse", "Lcom/reown/android/internal/common/model/WCResponse;", "peerResponse", "getPeerResponse", "_internalErrors", "Lcom/reown/android/internal/common/model/SDKError;", "internalErrors", "getInternalErrors", "triggerRequest", "", "payload", "Lcom/reown/android/internal/common/model/type/JsonRpcClientSync;", "topic", "Lcom/reown/foundation/common/model/Topic;", "appLink", "", "envelopeType", "Lcom/reown/android/internal/common/model/EnvelopeType;", "triggerResponse", "response", "Lcom/reown/android/internal/common/JsonRpcResponse;", "participants", "Lcom/reown/android/internal/common/model/Participants;", "dispatchEnvelope", "url", "serializeRequest", "clientJsonRpc", "Lcom/reown/android/internal/common/model/sync/ClientJsonRpc;", "envelope", "(Lcom/reown/android/internal/common/model/sync/ClientJsonRpc;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "serializeResult", "result", "Lcom/reown/android/internal/common/JsonRpcResponse$JsonRpcResult;", "(Lcom/reown/android/internal/common/JsonRpcResponse$JsonRpcResult;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "serializeError", "error", "Lcom/reown/android/internal/common/JsonRpcResponse$JsonRpcError;", "(Lcom/reown/android/internal/common/JsonRpcResponse$JsonRpcError;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWCRequest", "params", "Lcom/reown/android/internal/common/model/type/ClientParams;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nLinkModeJsonRpcInteractor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LinkModeJsonRpcInteractor.kt\ncom/reown/android/internal/common/json_rpc/domain/link_mode/LinkModeJsonRpcInteractor\n+ 2 Koin.kt\norg/koin/core/Koin\n+ 3 Scope.kt\norg/koin/core/scope/Scope\n*L\n1#1,136:1\n124#2,4:137\n142#3:141\n*S KotlinDebug\n*F\n+ 1 LinkModeJsonRpcInteractor.kt\ncom/reown/android/internal/common/json_rpc/domain/link_mode/LinkModeJsonRpcInteractor\n*L\n37#1:137,4\n37#1:141\n*E\n"})
public final class LinkModeJsonRpcInteractor implements LinkModeJsonRpcInteractorInterface {
    @NotNull
    private final MutableSharedFlow<WCRequest> _clientSyncJsonRpc;
    @NotNull
    private final MutableSharedFlow<SDKError> _internalErrors;
    @NotNull
    private final MutableSharedFlow<WCResponse> _peerResponse;
    @NotNull
    private final Codec chaChaPolyCodec;
    @NotNull
    private final SharedFlow<WCRequest> clientSyncJsonRpc;
    @NotNull
    private final Context context;
    @NotNull
    private final SharedFlow<SDKError> internalErrors;
    @NotNull
    private final JsonRpcHistory jsonRpcHistory;
    @NotNull
    private final SharedFlow<WCResponse> peerResponse;

    public LinkModeJsonRpcInteractor(@NotNull Codec codec, @NotNull JsonRpcHistory jsonRpcHistory2, @NotNull Context context2) {
        Intrinsics.checkNotNullParameter(codec, "chaChaPolyCodec");
        Intrinsics.checkNotNullParameter(jsonRpcHistory2, "jsonRpcHistory");
        Intrinsics.checkNotNullParameter(context2, "context");
        this.chaChaPolyCodec = codec;
        this.jsonRpcHistory = jsonRpcHistory2;
        this.context = context2;
        MutableSharedFlow<WCRequest> MutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(0, 0, (BufferOverflow) null, 7, (Object) null);
        this._clientSyncJsonRpc = MutableSharedFlow$default;
        this.clientSyncJsonRpc = FlowKt.asSharedFlow(MutableSharedFlow$default);
        MutableSharedFlow<WCResponse> MutableSharedFlow$default2 = SharedFlowKt.MutableSharedFlow$default(0, 0, (BufferOverflow) null, 7, (Object) null);
        this._peerResponse = MutableSharedFlow$default2;
        this.peerResponse = FlowKt.asSharedFlow(MutableSharedFlow$default2);
        MutableSharedFlow<SDKError> MutableSharedFlow$default3 = SharedFlowKt.MutableSharedFlow$default(0, 0, (BufferOverflow) null, 7, (Object) null);
        this._internalErrors = MutableSharedFlow$default3;
        this.internalErrors = FlowKt.asSharedFlow(MutableSharedFlow$default3);
    }

    /* access modifiers changed from: private */
    public final JsonRpcSerializer getSerializer() {
        return (JsonRpcSerializer) KoinApplicationKt.getWcKoinApp().getKoin().getScopeRegistry().getRootScope().get(Reflection.getOrCreateKotlinClass(JsonRpcSerializer.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
    }

    private final WCRequest getWCRequest(String str, ClientJsonRpc clientJsonRpc, ClientParams clientParams) {
        if (str == null) {
            str = Intrinsics.checkNotNullParameter(StringCompanionObject.INSTANCE, "<this>");
        }
        Topic topic = new Topic(str);
        long id = clientJsonRpc.getId();
        String method = clientJsonRpc.getMethod();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        return new WCRequest(topic, id, method, clientParams, Intrinsics.checkNotNullParameter(stringCompanionObject, "<this>"), 0, Intrinsics.checkNotNullParameter(stringCompanionObject, "<this>"), Intrinsics.checkNotNullParameter(stringCompanionObject, "<this>"), TransportType.LINK_MODE);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object serializeError(com.reown.android.internal.common.JsonRpcResponse.JsonRpcError r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof com.reown.android.internal.common.json_rpc.domain.link_mode.LinkModeJsonRpcInteractor$serializeError$1
            if (r0 == 0) goto L_0x0013
            r0 = r9
            com.reown.android.internal.common.json_rpc.domain.link_mode.LinkModeJsonRpcInteractor$serializeError$1 r0 = (com.reown.android.internal.common.json_rpc.domain.link_mode.LinkModeJsonRpcInteractor$serializeError$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.reown.android.internal.common.json_rpc.domain.link_mode.LinkModeJsonRpcInteractor$serializeError$1 r0 = new com.reown.android.internal.common.json_rpc.domain.link_mode.LinkModeJsonRpcInteractor$serializeError$1
            r0.<init>(r7, r9)
        L_0x0018:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0041
            if (r2 != r3) goto L_0x0039
            java.lang.Object r7 = r0.L$3
            com.reown.android.internal.common.model.type.ClientParams r7 = (com.reown.android.internal.common.model.type.ClientParams) r7
            java.lang.Object r7 = r0.L$2
            com.reown.android.internal.common.json_rpc.model.JsonRpcHistoryRecord r7 = (com.reown.android.internal.common.json_rpc.model.JsonRpcHistoryRecord) r7
            java.lang.Object r7 = r0.L$1
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r7 = r0.L$0
            com.reown.android.internal.common.JsonRpcResponse$JsonRpcError r7 = (com.reown.android.internal.common.JsonRpcResponse.JsonRpcError) r7
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x009e
        L_0x0039:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0041:
            kotlin.ResultKt.throwOnFailure(r9)
            com.reown.android.internal.common.json_rpc.data.JsonRpcSerializer r9 = r7.getSerializer()
            java.lang.String r9 = r9.serialize(r8)
            if (r9 == 0) goto L_0x00a1
            com.reown.android.internal.common.storage.rpc.JsonRpcHistory r2 = r7.jsonRpcHistory
            long r4 = r8.getId()
            com.reown.android.internal.common.json_rpc.model.JsonRpcHistoryRecord r2 = r2.updateRequestWithResponse(r4, r9)
            if (r2 == 0) goto L_0x009e
            com.reown.android.internal.common.json_rpc.data.JsonRpcSerializer r4 = r7.getSerializer()
            java.lang.String r5 = r2.getMethod()
            java.lang.String r6 = r2.getBody()
            com.reown.android.internal.common.model.type.ClientParams r4 = r4.deserialize(r5, r6)
            if (r4 == 0) goto L_0x0096
            kotlinx.coroutines.flow.MutableSharedFlow<com.reown.android.internal.common.model.WCResponse> r7 = r7._peerResponse
            com.reown.android.internal.common.model.WCResponse r5 = com.reown.android.internal.common.json_rpc.model.JsonRpcMapperKt.toWCResponse(r2, r8, r4)
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)
            r0.L$0 = r8
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r9)
            r0.L$1 = r8
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)
            r0.L$2 = r8
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)
            r0.L$3 = r8
            r8 = 0
            r0.I$0 = r8
            r0.label = r3
            java.lang.Object r7 = r7.emit(r5, r0)
            if (r7 != r1) goto L_0x009e
            return r1
        L_0x0096:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "LinkMode: Cannot serialize error"
            r7.<init>(r8)
            throw r7
        L_0x009e:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        L_0x00a1:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "LinkMode: Unknown result params"
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.internal.common.json_rpc.domain.link_mode.LinkModeJsonRpcInteractor.serializeError(com.reown.android.internal.common.JsonRpcResponse$JsonRpcError, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public final Object serializeRequest(ClientJsonRpc clientJsonRpc, String str, String str2, Continuation<? super Unit> continuation) {
        ClientParams deserialize;
        Object emit;
        return (!this.jsonRpcHistory.setRequest(clientJsonRpc.getId(), new Topic(str == null ? Intrinsics.checkNotNullParameter(StringCompanionObject.INSTANCE, "<this>") : str), clientJsonRpc.getMethod(), str2, TransportType.LINK_MODE) || (deserialize = getSerializer().deserialize(clientJsonRpc.getMethod(), str2)) == null || (emit = this._clientSyncJsonRpc.emit(getWCRequest(str, clientJsonRpc, deserialize), continuation)) != IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? Unit.INSTANCE : emit;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object serializeResult(com.reown.android.internal.common.JsonRpcResponse.JsonRpcResult r14, kotlin.coroutines.Continuation<? super kotlin.Unit> r15) {
        /*
            r13 = this;
            boolean r0 = r15 instanceof com.reown.android.internal.common.json_rpc.domain.link_mode.LinkModeJsonRpcInteractor$serializeResult$1
            if (r0 == 0) goto L_0x0013
            r0 = r15
            com.reown.android.internal.common.json_rpc.domain.link_mode.LinkModeJsonRpcInteractor$serializeResult$1 r0 = (com.reown.android.internal.common.json_rpc.domain.link_mode.LinkModeJsonRpcInteractor$serializeResult$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.reown.android.internal.common.json_rpc.domain.link_mode.LinkModeJsonRpcInteractor$serializeResult$1 r0 = new com.reown.android.internal.common.json_rpc.domain.link_mode.LinkModeJsonRpcInteractor$serializeResult$1
            r0.<init>(r13, r15)
        L_0x0018:
            java.lang.Object r15 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0041
            if (r2 != r3) goto L_0x0039
            java.lang.Object r13 = r0.L$3
            com.reown.android.internal.common.model.type.ClientParams r13 = (com.reown.android.internal.common.model.type.ClientParams) r13
            java.lang.Object r13 = r0.L$2
            com.reown.android.internal.common.json_rpc.model.JsonRpcHistoryRecord r13 = (com.reown.android.internal.common.json_rpc.model.JsonRpcHistoryRecord) r13
            java.lang.Object r13 = r0.L$1
            java.lang.String r13 = (java.lang.String) r13
            java.lang.Object r13 = r0.L$0
            com.reown.android.internal.common.JsonRpcResponse$JsonRpcResult r13 = (com.reown.android.internal.common.JsonRpcResponse.JsonRpcResult) r13
            kotlin.ResultKt.throwOnFailure(r15)
            goto L_0x00af
        L_0x0039:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L_0x0041:
            kotlin.ResultKt.throwOnFailure(r15)
            com.reown.android.internal.common.json_rpc.data.JsonRpcSerializer r15 = r13.getSerializer()
            java.lang.String r15 = r15.serialize(r14)
            if (r15 == 0) goto L_0x00b2
            com.reown.android.internal.common.storage.rpc.JsonRpcHistory r2 = r13.jsonRpcHistory
            long r4 = r14.getId()
            com.reown.android.internal.common.json_rpc.model.JsonRpcHistoryRecord r2 = r2.updateRequestWithResponse(r4, r15)
            if (r2 == 0) goto L_0x00af
            com.reown.android.internal.common.json_rpc.data.JsonRpcSerializer r4 = r13.getSerializer()
            java.lang.String r5 = r2.getMethod()
            java.lang.String r6 = r2.getBody()
            com.reown.android.internal.common.model.type.ClientParams r4 = r4.deserialize(r5, r6)
            if (r4 == 0) goto L_0x00a7
            kotlinx.coroutines.flow.MutableSharedFlow<com.reown.android.internal.common.model.WCResponse> r13 = r13._peerResponse
            com.reown.android.internal.common.JsonRpcResponse$JsonRpcResult r12 = new com.reown.android.internal.common.JsonRpcResponse$JsonRpcResult
            long r6 = r14.getId()
            java.lang.Object r9 = r14.getResult()
            r10 = 2
            r11 = 0
            r8 = 0
            r5 = r12
            r5.<init>(r6, r8, r9, r10, r11)
            com.reown.android.internal.common.model.WCResponse r5 = com.reown.android.internal.common.json_rpc.model.JsonRpcMapperKt.toWCResponse(r2, r12, r4)
            java.lang.Object r14 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r14)
            r0.L$0 = r14
            java.lang.Object r14 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r15)
            r0.L$1 = r14
            java.lang.Object r14 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)
            r0.L$2 = r14
            java.lang.Object r14 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)
            r0.L$3 = r14
            r14 = 0
            r0.I$0 = r14
            r0.label = r3
            java.lang.Object r13 = r13.emit(r5, r0)
            if (r13 != r1) goto L_0x00af
            return r1
        L_0x00a7:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "LinkMode: Cannot serialize result"
            r13.<init>(r14)
            throw r13
        L_0x00af:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE
            return r13
        L_0x00b2:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "LinkMode: Unknown result params"
            r13.<init>(r14)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.internal.common.json_rpc.domain.link_mode.LinkModeJsonRpcInteractor.serializeResult(com.reown.android.internal.common.JsonRpcResponse$JsonRpcResult, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public void dispatchEnvelope(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "url");
        Uri parse = Uri.parse(str);
        String queryParameter = parse.getQueryParameter("wc_ev");
        if (queryParameter != null) {
            String queryParameter2 = parse.getQueryParameter(PushMessagingService.KEY_TOPIC);
            if (queryParameter2 != null) {
                byte[] decode = Base64.decode(queryParameter, 11);
                Codec codec = this.chaChaPolyCodec;
                Topic topic = new Topic(queryParameter2);
                Intrinsics.checkNotNull(decode);
                Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new LinkModeJsonRpcInteractor$dispatchEnvelope$1(this, codec.decrypt(topic, decode), queryParameter2, (Continuation<? super LinkModeJsonRpcInteractor$dispatchEnvelope$1>) null), 3, (Object) null);
                return;
            }
            throw new IllegalStateException("LinkMode: Missing topic parameter");
        }
        throw new IllegalStateException("LinkMode: Missing wc_ev parameter");
    }

    @NotNull
    public SharedFlow<WCRequest> getClientSyncJsonRpc() {
        return this.clientSyncJsonRpc;
    }

    @NotNull
    public SharedFlow<SDKError> getInternalErrors() {
        return this.internalErrors;
    }

    @NotNull
    public SharedFlow<WCResponse> getPeerResponse() {
        return this.peerResponse;
    }

    public void triggerRequest(@NotNull JsonRpcClientSync<?> jsonRpcClientSync, @NotNull Topic topic, @NotNull String str, @NotNull EnvelopeType envelopeType) {
        Intrinsics.checkNotNullParameter(jsonRpcClientSync, "payload");
        Intrinsics.checkNotNullParameter(topic, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(str, "appLink");
        Intrinsics.checkNotNullParameter(envelopeType, "envelopeType");
        String serialize = getSerializer().serialize(jsonRpcClientSync);
        if (serialize != null) {
            if (this.jsonRpcHistory.setRequest(jsonRpcClientSync.getId(), topic, jsonRpcClientSync.getMethod(), serialize, TransportType.LINK_MODE)) {
                String encodeToString = Base64.encodeToString(Codec.encrypt$default(this.chaChaPolyCodec, topic, serialize, envelopeType, (Participants) null, 8, (Object) null), 11);
                Intent intent = new Intent("android.intent.action.VIEW");
                String value = topic.getValue();
                intent.setData(Uri.parse(str + "?wc_ev=" + encodeToString + "&topic=" + value));
                intent.addFlags(268435456);
                this.context.startActivity(intent);
                return;
            }
            return;
        }
        throw new IllegalStateException("LinkMode: Cannot serialize the request");
    }

    public void triggerResponse(@NotNull Topic topic, @NotNull JsonRpcResponse jsonRpcResponse, @NotNull String str, @Nullable Participants participants, @NotNull EnvelopeType envelopeType) {
        Intrinsics.checkNotNullParameter(topic, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(jsonRpcResponse, "response");
        Intrinsics.checkNotNullParameter(str, "appLink");
        Intrinsics.checkNotNullParameter(envelopeType, "envelopeType");
        String serialize = getSerializer().serialize(jsonRpcResponse);
        if (serialize != null) {
            String encodeToString = Base64.encodeToString(this.chaChaPolyCodec.encrypt(topic, serialize, envelopeType, participants), 11);
            Intent intent = new Intent("android.intent.action.VIEW");
            String value = topic.getValue();
            intent.setData(Uri.parse(str + "?wc_ev=" + encodeToString + "&topic=" + value));
            intent.addFlags(268435456);
            this.jsonRpcHistory.updateRequestWithResponse(jsonRpcResponse.getId(), serialize);
            this.context.startActivity(intent);
            return;
        }
        throw new IllegalStateException("LinkMode: Cannot serialize the response");
    }
}
