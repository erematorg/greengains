package com.reown.sign.engine.use_case.responses;

import com.reown.android.internal.common.WalletConnectScopeKt;
import com.reown.android.internal.common.model.WCResponse;
import com.reown.sign.common.model.vo.clientsync.session.params.SignParams;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

@SourceDebugExtension({"SMAP\nOnSessionAuthenticateResponseUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 OnSessionAuthenticateResponseUseCase.kt\ncom/reown/sign/engine/use_case/responses/OnSessionAuthenticateResponseUseCase$invoke$2\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,183:1\n1761#2,3:184\n1563#2:188\n1634#2,3:189\n1869#2,2:192\n1#3:187\n*S KotlinDebug\n*F\n+ 1 OnSessionAuthenticateResponseUseCase.kt\ncom/reown/sign/engine/use_case/responses/OnSessionAuthenticateResponseUseCase$invoke$2\n*L\n78#1:184,3\n122#1:188\n122#1:189,3\n124#1:192,2\n*E\n"})
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.responses.OnSessionAuthenticateResponseUseCase$invoke$2", f = "OnSessionAuthenticateResponseUseCase.kt", i = {0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 9, 9}, l = {79, 83, 90, 99, 110, 126, 138, 160, 172, 178}, m = "invokeSuspend", n = {"$this$supervisorScope", "jsonRpcHistoryEntry", "pairingTopic", "$this$supervisorScope", "jsonRpcHistoryEntry", "pairingTopic", "$this$invokeSuspend_u24lambda_u241", "$i$a$-runCatching-OnSessionAuthenticateResponseUseCase$invoke$2$2", "$this$supervisorScope", "jsonRpcHistoryEntry", "pairingTopic", "response", "$this$supervisorScope", "jsonRpcHistoryEntry", "pairingTopic", "response", "$this$supervisorScope", "jsonRpcHistoryEntry", "pairingTopic", "response", "approveResponseParams", "$this$supervisorScope", "jsonRpcHistoryEntry", "pairingTopic", "response", "approveResponseParams", "$this$invokeSuspend_u24lambda_u249", "selfPublicKey", "peerPublicKey", "symmetricKey", "sessionTopic", "addresses", "accounts", "chains", "$i$a$-with-OnSessionAuthenticateResponseUseCase$invoke$2$5", "$this$supervisorScope", "jsonRpcHistoryEntry", "pairingTopic", "response", "approveResponseParams", "$this$invokeSuspend_u24lambda_u249", "selfPublicKey", "peerPublicKey", "symmetricKey", "sessionTopic", "addresses", "accounts", "chains", "namespace", "methods", "events", "sessionNamespaces", "requiredNamespace", "$i$a$-with-OnSessionAuthenticateResponseUseCase$invoke$2$5", "$this$supervisorScope", "jsonRpcHistoryEntry", "pairingTopic", "response", "approveResponseParams", "$this$invokeSuspend_u24lambda_u249", "selfPublicKey", "peerPublicKey", "symmetricKey", "sessionTopic", "addresses", "accounts", "chains", "namespace", "methods", "events", "authenticatedSession", "sessionNamespaces", "requiredNamespace", "transportType", "$i$a$-with-OnSessionAuthenticateResponseUseCase$invoke$2$5", "$this$supervisorScope", "jsonRpcHistoryEntry", "pairingTopic", "response", "approveResponseParams", "$this$invokeSuspend_u24lambda_u249", "selfPublicKey", "peerPublicKey", "symmetricKey", "sessionTopic", "addresses", "accounts", "chains", "namespace", "methods", "events", "authenticatedSession", "$i$a$-with-OnSessionAuthenticateResponseUseCase$invoke$2$5", "$this$supervisorScope", "e"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "I$0", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$15", "L$16", "L$17", "L$18", "L$19", "L$20", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$15", "L$16", "L$17", "L$18", "L$19", "L$20", "L$21", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$15", "L$16", "I$0", "L$0", "L$1"})
public final class OnSessionAuthenticateResponseUseCase$invoke$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ SignParams.SessionAuthenticateParams $params;
    final /* synthetic */ WCResponse $wcResponse;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$10;
    Object L$11;
    Object L$12;
    Object L$13;
    Object L$14;
    Object L$15;
    Object L$16;
    Object L$17;
    Object L$18;
    Object L$19;
    Object L$2;
    Object L$20;
    Object L$21;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    int label;
    final /* synthetic */ OnSessionAuthenticateResponseUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OnSessionAuthenticateResponseUseCase$invoke$2(OnSessionAuthenticateResponseUseCase onSessionAuthenticateResponseUseCase, WCResponse wCResponse, SignParams.SessionAuthenticateParams sessionAuthenticateParams, Continuation<? super OnSessionAuthenticateResponseUseCase$invoke$2> continuation) {
        super(2, continuation);
        this.this$0 = onSessionAuthenticateResponseUseCase;
        this.$wcResponse = wCResponse;
        this.$params = sessionAuthenticateParams;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$9$lambda$8(OnSessionAuthenticateResponseUseCase onSessionAuthenticateResponseUseCase, Throwable th) {
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new OnSessionAuthenticateResponseUseCase$invoke$2$5$2$1(onSessionAuthenticateResponseUseCase, th, (Continuation<? super OnSessionAuthenticateResponseUseCase$invoke$2$5$2$1>) null), 3, (Object) null);
        return Unit.INSTANCE;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        OnSessionAuthenticateResponseUseCase$invoke$2 onSessionAuthenticateResponseUseCase$invoke$2 = new OnSessionAuthenticateResponseUseCase$invoke$2(this.this$0, this.$wcResponse, this.$params, continuation);
        onSessionAuthenticateResponseUseCase$invoke$2.L$0 = obj;
        return onSessionAuthenticateResponseUseCase$invoke$2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v5, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v3, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v4, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v14, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v4, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v18, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v25, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v7, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v40, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v45, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v46, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v47, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v46, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v48, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v47, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v48, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v50, resolved type: java.lang.Object} */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x02e0, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:?, code lost:
        r7 = kotlin.Result.m8979constructorimpl(kotlin.Unit.INSTANCE);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
        kotlin.ResultKt.throwOnFailure(r56);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0070, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0071, code lost:
        r2 = r0;
        r15 = r6;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:194:0x0862 A[Catch:{ Exception -> 0x0964 }] */
    /* JADX WARNING: Removed duplicated region for block: B:202:0x0969  */
    /* JADX WARNING: Removed duplicated region for block: B:207:0x09ea A[Catch:{ Exception -> 0x07b9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:208:0x09ef A[Catch:{ Exception -> 0x07b9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:211:0x0a70 A[Catch:{ Exception -> 0x07b9 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:220:0x0ae1 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r56) {
        /*
            r55 = this;
            r1 = r55
            java.lang.String r2 = "Signature verification failed Session Authenticate"
            java.lang.String r3 = "Received session authenticate response - rpc entry doesn't exist: "
            java.lang.String r4 = "Received session authenticate response: "
            java.lang.Object r5 = r1.L$0
            kotlinx.coroutines.CoroutineScope r5 = (kotlinx.coroutines.CoroutineScope) r5
            java.lang.Object r6 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r7 = r1.label
            java.lang.String r8 = "SUCCESS"
            r9 = 2
            r10 = 0
            r11 = 1
            switch(r7) {
                case 0: goto L_0x0205;
                case 1: goto L_0x01f8;
                case 2: goto L_0x01e3;
                case 3: goto L_0x01d2;
                case 4: goto L_0x01c4;
                case 5: goto L_0x01af;
                case 6: goto L_0x0179;
                case 7: goto L_0x00f0;
                case 8: goto L_0x0075;
                case 9: goto L_0x002b;
                case 10: goto L_0x0022;
                default: goto L_0x001a;
            }
        L_0x001a:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x0022:
            java.lang.Object r1 = r1.L$1
            java.lang.Exception r1 = (java.lang.Exception) r1
            kotlin.ResultKt.throwOnFailure(r56)
            goto L_0x0ae2
        L_0x002b:
            java.lang.Object r2 = r1.L$16
            com.reown.sign.common.model.vo.sequence.SessionVO r2 = (com.reown.sign.common.model.vo.sequence.SessionVO) r2
            java.lang.Object r2 = r1.L$15
            java.util.List r2 = (java.util.List) r2
            java.lang.Object r2 = r1.L$14
            java.util.List r2 = (java.util.List) r2
            java.lang.Object r2 = r1.L$13
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r2 = r1.L$12
            java.util.List r2 = (java.util.List) r2
            java.lang.Object r2 = r1.L$11
            java.util.List r2 = (java.util.List) r2
            java.lang.Object r2 = r1.L$10
            java.util.List r2 = (java.util.List) r2
            java.lang.Object r2 = r1.L$9
            com.reown.foundation.common.model.Topic r2 = (com.reown.foundation.common.model.Topic) r2
            java.lang.Object r2 = r1.L$8
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r2 = r1.L$7
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r2 = r1.L$6
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r2 = r1.L$5
            com.reown.android.internal.common.model.params.CoreSignParams$SessionAuthenticateApproveParams r2 = (com.reown.android.internal.common.model.params.CoreSignParams.SessionAuthenticateApproveParams) r2
            java.lang.Object r2 = r1.L$4
            com.reown.android.internal.common.model.params.CoreSignParams$SessionAuthenticateApproveParams r2 = (com.reown.android.internal.common.model.params.CoreSignParams.SessionAuthenticateApproveParams) r2
            java.lang.Object r2 = r1.L$3
            com.reown.android.internal.common.JsonRpcResponse r2 = (com.reown.android.internal.common.JsonRpcResponse) r2
            java.lang.Object r2 = r1.L$2
            com.reown.foundation.common.model.Topic r2 = (com.reown.foundation.common.model.Topic) r2
            java.lang.Object r2 = r1.L$1
            com.reown.sign.common.model.Request r2 = (com.reown.sign.common.model.Request) r2
        L_0x006b:
            kotlin.ResultKt.throwOnFailure(r56)     // Catch:{ Exception -> 0x0070 }
            goto L_0x0ae2
        L_0x0070:
            r0 = move-exception
            r2 = r0
            r15 = r6
            goto L_0x0a80
        L_0x0075:
            int r2 = r1.I$0
            java.lang.Object r3 = r1.L$21
            com.reown.android.internal.common.model.TransportType r3 = (com.reown.android.internal.common.model.TransportType) r3
            java.lang.Object r3 = r1.L$20
            java.util.Map r3 = (java.util.Map) r3
            java.lang.Object r3 = r1.L$19
            java.util.Map r3 = (java.util.Map) r3
            java.lang.Object r3 = r1.L$18
            com.reown.sign.common.model.vo.sequence.SessionVO r3 = (com.reown.sign.common.model.vo.sequence.SessionVO) r3
            java.lang.Object r4 = r1.L$17
            java.util.List r4 = (java.util.List) r4
            java.lang.Object r7 = r1.L$16
            java.util.List r7 = (java.util.List) r7
            java.lang.Object r8 = r1.L$15
            java.lang.String r8 = (java.lang.String) r8
            java.lang.Object r9 = r1.L$14
            java.util.List r9 = (java.util.List) r9
            java.lang.Object r10 = r1.L$13
            java.util.List r10 = (java.util.List) r10
            java.lang.Object r11 = r1.L$12
            java.util.List r11 = (java.util.List) r11
            java.lang.Object r13 = r1.L$11
            com.reown.foundation.common.model.Topic r13 = (com.reown.foundation.common.model.Topic) r13
            java.lang.Object r14 = r1.L$10
            java.lang.String r14 = (java.lang.String) r14
            java.lang.Object r15 = r1.L$9
            java.lang.String r15 = (java.lang.String) r15
            java.lang.Object r12 = r1.L$8
            java.lang.String r12 = (java.lang.String) r12
            r16 = r2
            java.lang.Object r2 = r1.L$7
            com.reown.android.internal.common.model.params.CoreSignParams$SessionAuthenticateApproveParams r2 = (com.reown.android.internal.common.model.params.CoreSignParams.SessionAuthenticateApproveParams) r2
            r17 = r2
            java.lang.Object r2 = r1.L$6
            com.reown.android.internal.common.model.WCResponse r2 = (com.reown.android.internal.common.model.WCResponse) r2
            r18 = r2
            java.lang.Object r2 = r1.L$5
            com.reown.sign.engine.use_case.responses.OnSessionAuthenticateResponseUseCase r2 = (com.reown.sign.engine.use_case.responses.OnSessionAuthenticateResponseUseCase) r2
            r19 = r2
            java.lang.Object r2 = r1.L$4
            com.reown.android.internal.common.model.params.CoreSignParams$SessionAuthenticateApproveParams r2 = (com.reown.android.internal.common.model.params.CoreSignParams.SessionAuthenticateApproveParams) r2
            r20 = r2
            java.lang.Object r2 = r1.L$3
            com.reown.android.internal.common.JsonRpcResponse r2 = (com.reown.android.internal.common.JsonRpcResponse) r2
            r21 = r2
            java.lang.Object r2 = r1.L$2
            com.reown.foundation.common.model.Topic r2 = (com.reown.foundation.common.model.Topic) r2
            r22 = r2
            java.lang.Object r2 = r1.L$1
            com.reown.sign.common.model.Request r2 = (com.reown.sign.common.model.Request) r2
            kotlin.ResultKt.throwOnFailure(r56)     // Catch:{ Exception -> 0x0070 }
            r28 = r7
            r7 = r21
            r21 = r22
            r53 = r15
            r15 = r6
            r6 = r53
            r54 = r12
            r12 = r10
            r10 = r16
            r16 = r54
            goto L_0x094b
        L_0x00f0:
            int r10 = r1.I$0
            java.lang.Object r2 = r1.L$20
            java.util.Map r2 = (java.util.Map) r2
            java.lang.Object r3 = r1.L$19
            java.util.Map r3 = (java.util.Map) r3
            java.lang.Object r4 = r1.L$18
            java.util.List r4 = (java.util.List) r4
            java.lang.Object r7 = r1.L$17
            java.util.List r7 = (java.util.List) r7
            java.lang.Object r9 = r1.L$16
            java.lang.String r9 = (java.lang.String) r9
            java.lang.Object r11 = r1.L$15
            java.util.List r11 = (java.util.List) r11
            java.lang.Object r12 = r1.L$14
            java.util.List r12 = (java.util.List) r12
            java.lang.Object r13 = r1.L$13
            java.util.List r13 = (java.util.List) r13
            java.lang.Object r14 = r1.L$12
            com.reown.foundation.common.model.Topic r14 = (com.reown.foundation.common.model.Topic) r14
            java.lang.Object r15 = r1.L$11
            java.lang.String r15 = (java.lang.String) r15
            r16 = r2
            java.lang.Object r2 = r1.L$10
            java.lang.String r2 = (java.lang.String) r2
            r17 = r2
            java.lang.Object r2 = r1.L$9
            java.lang.String r2 = (java.lang.String) r2
            r18 = r2
            java.lang.Object r2 = r1.L$8
            com.reown.android.internal.common.model.params.CoreSignParams$SessionAuthenticateApproveParams r2 = (com.reown.android.internal.common.model.params.CoreSignParams.SessionAuthenticateApproveParams) r2
            r19 = r2
            java.lang.Object r2 = r1.L$7
            com.reown.android.internal.common.model.WCResponse r2 = (com.reown.android.internal.common.model.WCResponse) r2
            r20 = r2
            java.lang.Object r2 = r1.L$6
            com.reown.sign.engine.use_case.responses.OnSessionAuthenticateResponseUseCase r2 = (com.reown.sign.engine.use_case.responses.OnSessionAuthenticateResponseUseCase) r2
            r21 = r2
            java.lang.Object r2 = r1.L$5
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionAuthenticateParams r2 = (com.reown.sign.common.model.vo.clientsync.session.params.SignParams.SessionAuthenticateParams) r2
            r22 = r2
            java.lang.Object r2 = r1.L$4
            com.reown.android.internal.common.model.params.CoreSignParams$SessionAuthenticateApproveParams r2 = (com.reown.android.internal.common.model.params.CoreSignParams.SessionAuthenticateApproveParams) r2
            r23 = r2
            java.lang.Object r2 = r1.L$3
            com.reown.android.internal.common.JsonRpcResponse r2 = (com.reown.android.internal.common.JsonRpcResponse) r2
            r24 = r2
            java.lang.Object r2 = r1.L$2
            com.reown.foundation.common.model.Topic r2 = (com.reown.foundation.common.model.Topic) r2
            r25 = r2
            java.lang.Object r2 = r1.L$1
            com.reown.sign.common.model.Request r2 = (com.reown.sign.common.model.Request) r2
            kotlin.ResultKt.throwOnFailure(r56)     // Catch:{ Exception -> 0x0070 }
            r53 = r15
            r15 = r6
            r6 = r10
            r10 = r7
            r7 = r24
            r24 = r13
            r13 = r18
            r18 = r8
            r8 = r11
            r11 = r21
            r21 = r25
            r25 = r9
            r9 = r19
            r19 = r20
            r20 = r14
            r14 = r22
            r22 = r53
            goto L_0x07a0
        L_0x0179:
            java.lang.Object r2 = r1.L$12
            java.util.List r2 = (java.util.List) r2
            java.lang.Object r2 = r1.L$11
            java.util.List r2 = (java.util.List) r2
            java.lang.Object r2 = r1.L$10
            java.util.List r2 = (java.util.List) r2
            java.lang.Object r2 = r1.L$9
            com.reown.foundation.common.model.Topic r2 = (com.reown.foundation.common.model.Topic) r2
            java.lang.Object r2 = r1.L$8
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r2 = r1.L$7
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r2 = r1.L$6
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r2 = r1.L$5
            com.reown.android.internal.common.model.params.CoreSignParams$SessionAuthenticateApproveParams r2 = (com.reown.android.internal.common.model.params.CoreSignParams.SessionAuthenticateApproveParams) r2
            java.lang.Object r2 = r1.L$4
            com.reown.android.internal.common.model.params.CoreSignParams$SessionAuthenticateApproveParams r2 = (com.reown.android.internal.common.model.params.CoreSignParams.SessionAuthenticateApproveParams) r2
            java.lang.Object r2 = r1.L$3
            com.reown.android.internal.common.JsonRpcResponse r2 = (com.reown.android.internal.common.JsonRpcResponse) r2
            java.lang.Object r2 = r1.L$2
            com.reown.foundation.common.model.Topic r2 = (com.reown.foundation.common.model.Topic) r2
            java.lang.Object r2 = r1.L$1
            com.reown.sign.common.model.Request r2 = (com.reown.sign.common.model.Request) r2
            kotlin.ResultKt.throwOnFailure(r56)     // Catch:{ Exception -> 0x0070 }
            r8 = r6
            goto L_0x0667
        L_0x01af:
            java.lang.Object r2 = r1.L$4
            com.reown.android.internal.common.model.params.CoreSignParams$SessionAuthenticateApproveParams r2 = (com.reown.android.internal.common.model.params.CoreSignParams.SessionAuthenticateApproveParams) r2
            java.lang.Object r2 = r1.L$3
            com.reown.android.internal.common.JsonRpcResponse r2 = (com.reown.android.internal.common.JsonRpcResponse) r2
            java.lang.Object r2 = r1.L$2
            com.reown.foundation.common.model.Topic r2 = (com.reown.foundation.common.model.Topic) r2
            java.lang.Object r2 = r1.L$1
            com.reown.sign.common.model.Request r2 = (com.reown.sign.common.model.Request) r2
            kotlin.ResultKt.throwOnFailure(r56)     // Catch:{ Exception -> 0x0070 }
            goto L_0x04e0
        L_0x01c4:
            java.lang.Object r2 = r1.L$3
            com.reown.android.internal.common.JsonRpcResponse r2 = (com.reown.android.internal.common.JsonRpcResponse) r2
            java.lang.Object r2 = r1.L$2
            com.reown.foundation.common.model.Topic r2 = (com.reown.foundation.common.model.Topic) r2
            java.lang.Object r2 = r1.L$1
            com.reown.sign.common.model.Request r2 = (com.reown.sign.common.model.Request) r2
            goto L_0x006b
        L_0x01d2:
            java.lang.Object r2 = r1.L$3
            com.reown.android.internal.common.JsonRpcResponse r2 = (com.reown.android.internal.common.JsonRpcResponse) r2
            java.lang.Object r3 = r1.L$2
            com.reown.foundation.common.model.Topic r3 = (com.reown.foundation.common.model.Topic) r3
            java.lang.Object r4 = r1.L$1
            com.reown.sign.common.model.Request r4 = (com.reown.sign.common.model.Request) r4
            kotlin.ResultKt.throwOnFailure(r56)     // Catch:{ Exception -> 0x0070 }
            goto L_0x03c1
        L_0x01e3:
            java.lang.Object r3 = r1.L$3
            kotlinx.coroutines.CoroutineScope r3 = (kotlinx.coroutines.CoroutineScope) r3
            java.lang.Object r3 = r1.L$2
            com.reown.foundation.common.model.Topic r3 = (com.reown.foundation.common.model.Topic) r3
            java.lang.Object r4 = r1.L$1
            com.reown.sign.common.model.Request r4 = (com.reown.sign.common.model.Request) r4
            kotlin.ResultKt.throwOnFailure(r56)     // Catch:{ all -> 0x01f4 }
            goto L_0x0309
        L_0x01f4:
            r0 = move-exception
        L_0x01f5:
            r7 = r0
            goto L_0x0314
        L_0x01f8:
            java.lang.Object r2 = r1.L$2
            com.reown.foundation.common.model.Topic r2 = (com.reown.foundation.common.model.Topic) r2
            java.lang.Object r2 = r1.L$1
            com.reown.sign.common.model.Request r2 = (com.reown.sign.common.model.Request) r2
            kotlin.ResultKt.throwOnFailure(r56)     // Catch:{ Exception -> 0x0070 }
            goto L_0x02de
        L_0x0205:
            kotlin.ResultKt.throwOnFailure(r56)
            com.reown.sign.engine.use_case.responses.OnSessionAuthenticateResponseUseCase r7 = r1.this$0     // Catch:{ Exception -> 0x0a75 }
            com.reown.sign.json_rpc.domain.GetSessionAuthenticateRequest r7 = r7.getSessionAuthenticateRequest     // Catch:{ Exception -> 0x0a75 }
            com.reown.android.internal.common.model.WCResponse r12 = r1.$wcResponse     // Catch:{ Exception -> 0x0a75 }
            com.reown.android.internal.common.JsonRpcResponse r12 = r12.getResponse()     // Catch:{ Exception -> 0x0a75 }
            long r12 = r12.getId()     // Catch:{ Exception -> 0x0a75 }
            com.reown.sign.common.model.Request r7 = r7.invoke$sign_release(r12)     // Catch:{ Exception -> 0x0a75 }
            com.reown.sign.engine.use_case.responses.OnSessionAuthenticateResponseUseCase r12 = r1.this$0     // Catch:{ Exception -> 0x0a75 }
            com.reown.foundation.util.Logger r12 = r12.logger     // Catch:{ Exception -> 0x0a75 }
            com.reown.android.internal.common.model.WCResponse r13 = r1.$wcResponse     // Catch:{ Exception -> 0x0a75 }
            com.reown.foundation.common.model.Topic r13 = r13.getTopic()     // Catch:{ Exception -> 0x0a75 }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0a75 }
            r14.<init>(r4)     // Catch:{ Exception -> 0x0a75 }
            r14.append(r13)     // Catch:{ Exception -> 0x0a75 }
            java.lang.String r4 = r14.toString()     // Catch:{ Exception -> 0x0a75 }
            r12.log((java.lang.String) r4)     // Catch:{ Exception -> 0x0a75 }
            if (r7 != 0) goto L_0x0257
            com.reown.sign.engine.use_case.responses.OnSessionAuthenticateResponseUseCase r2 = r1.this$0     // Catch:{ Exception -> 0x0070 }
            com.reown.foundation.util.Logger r2 = r2.logger     // Catch:{ Exception -> 0x0070 }
            com.reown.android.internal.common.model.WCResponse r4 = r1.$wcResponse     // Catch:{ Exception -> 0x0070 }
            com.reown.foundation.common.model.Topic r4 = r4.getTopic()     // Catch:{ Exception -> 0x0070 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0070 }
            r7.<init>(r3)     // Catch:{ Exception -> 0x0070 }
            r7.append(r4)     // Catch:{ Exception -> 0x0070 }
            java.lang.String r3 = r7.toString()     // Catch:{ Exception -> 0x0070 }
            r2.error((java.lang.String) r3)     // Catch:{ Exception -> 0x0070 }
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x0070 }
            return r1
        L_0x0257:
            com.reown.foundation.common.model.Topic r3 = r7.getTopic()     // Catch:{ Exception -> 0x0a75 }
            com.reown.android.internal.common.model.TransportType r4 = r7.getTransportType()     // Catch:{ Exception -> 0x0a75 }
            com.reown.android.internal.common.model.TransportType r12 = com.reown.android.internal.common.model.TransportType.RELAY     // Catch:{ Exception -> 0x0a75 }
            if (r4 != r12) goto L_0x02e1
            com.reown.sign.engine.use_case.responses.OnSessionAuthenticateResponseUseCase r4 = r1.this$0     // Catch:{ Exception -> 0x0070 }
            com.reown.android.pairing.client.PairingInterface r4 = r4.pairingInterface     // Catch:{ Exception -> 0x0070 }
            java.util.List r4 = r4.getPairings()     // Catch:{ Exception -> 0x0070 }
            java.lang.Iterable r4 = (java.lang.Iterable) r4     // Catch:{ Exception -> 0x0070 }
            boolean r12 = r4 instanceof java.util.Collection     // Catch:{ Exception -> 0x0070 }
            if (r12 == 0) goto L_0x027d
            r12 = r4
            java.util.Collection r12 = (java.util.Collection) r12     // Catch:{ Exception -> 0x0070 }
            boolean r12 = r12.isEmpty()     // Catch:{ Exception -> 0x0070 }
            if (r12 == 0) goto L_0x027d
            goto L_0x029c
        L_0x027d:
            java.util.Iterator r4 = r4.iterator()     // Catch:{ Exception -> 0x0070 }
        L_0x0281:
            boolean r12 = r4.hasNext()     // Catch:{ Exception -> 0x0070 }
            if (r12 == 0) goto L_0x029c
            java.lang.Object r12 = r4.next()     // Catch:{ Exception -> 0x0070 }
            com.reown.android.Core$Model$Pairing r12 = (com.reown.android.Core.Model.Pairing) r12     // Catch:{ Exception -> 0x0070 }
            java.lang.String r12 = r12.getTopic()     // Catch:{ Exception -> 0x0070 }
            java.lang.String r13 = r3.getValue()     // Catch:{ Exception -> 0x0070 }
            boolean r12 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r13)     // Catch:{ Exception -> 0x0070 }
            if (r12 == 0) goto L_0x0281
            goto L_0x02e1
        L_0x029c:
            com.reown.sign.engine.use_case.responses.OnSessionAuthenticateResponseUseCase r2 = r1.this$0     // Catch:{ Exception -> 0x0070 }
            kotlinx.coroutines.flow.MutableSharedFlow r2 = r2._events     // Catch:{ Exception -> 0x0070 }
            com.reown.android.internal.common.model.SDKError r4 = new com.reown.android.internal.common.model.SDKError     // Catch:{ Exception -> 0x0070 }
            java.lang.Throwable r8 = new java.lang.Throwable     // Catch:{ Exception -> 0x0070 }
            com.reown.android.internal.common.model.WCResponse r9 = r1.$wcResponse     // Catch:{ Exception -> 0x0070 }
            com.reown.foundation.common.model.Topic r9 = r9.getTopic()     // Catch:{ Exception -> 0x0070 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0070 }
            r10.<init>()     // Catch:{ Exception -> 0x0070 }
            java.lang.String r12 = "Received session authenticate response - pairing doesn't exist topic: "
            r10.append(r12)     // Catch:{ Exception -> 0x0070 }
            r10.append(r9)     // Catch:{ Exception -> 0x0070 }
            java.lang.String r9 = r10.toString()     // Catch:{ Exception -> 0x0070 }
            r8.<init>(r9)     // Catch:{ Exception -> 0x0070 }
            r4.<init>(r8)     // Catch:{ Exception -> 0x0070 }
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r5)     // Catch:{ Exception -> 0x0070 }
            r1.L$0 = r8     // Catch:{ Exception -> 0x0070 }
            java.lang.Object r7 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)     // Catch:{ Exception -> 0x0070 }
            r1.L$1 = r7     // Catch:{ Exception -> 0x0070 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r3)     // Catch:{ Exception -> 0x0070 }
            r1.L$2 = r3     // Catch:{ Exception -> 0x0070 }
            r1.label = r11     // Catch:{ Exception -> 0x0070 }
            java.lang.Object r2 = r2.emit(r4, r1)     // Catch:{ Exception -> 0x0070 }
            if (r2 != r6) goto L_0x02de
            return r6
        L_0x02de:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x0070 }
            return r1
        L_0x02e1:
            com.reown.sign.engine.use_case.responses.OnSessionAuthenticateResponseUseCase r4 = r1.this$0     // Catch:{ Exception -> 0x0a75 }
            kotlin.Result$Companion r12 = kotlin.Result.Companion     // Catch:{ all -> 0x0310 }
            com.reown.sign.storage.authenticate.AuthenticateResponseTopicRepository r4 = r4.authenticateResponseTopicRepository     // Catch:{ all -> 0x0310 }
            java.lang.String r12 = r3.getValue()     // Catch:{ all -> 0x0310 }
            java.lang.Object r13 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r5)     // Catch:{ all -> 0x0310 }
            r1.L$0 = r13     // Catch:{ all -> 0x0310 }
            r1.L$1 = r7     // Catch:{ all -> 0x0310 }
            r1.L$2 = r3     // Catch:{ all -> 0x0310 }
            java.lang.Object r13 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r5)     // Catch:{ all -> 0x0310 }
            r1.L$3 = r13     // Catch:{ all -> 0x0310 }
            r1.I$0 = r10     // Catch:{ all -> 0x0310 }
            r1.label = r9     // Catch:{ all -> 0x0310 }
            java.lang.Object r4 = r4.delete(r12, r1)     // Catch:{ all -> 0x0310 }
            if (r4 != r6) goto L_0x0308
            return r6
        L_0x0308:
            r4 = r7
        L_0x0309:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x01f4 }
            java.lang.Object r7 = kotlin.Result.m8979constructorimpl(r7)     // Catch:{ all -> 0x01f4 }
            goto L_0x031e
        L_0x0310:
            r0 = move-exception
            r4 = r7
            goto L_0x01f5
        L_0x0314:
            kotlin.Result$Companion r12 = kotlin.Result.Companion     // Catch:{ Exception -> 0x0a75 }
            java.lang.Object r7 = kotlin.ResultKt.createFailure(r7)     // Catch:{ Exception -> 0x0a75 }
            java.lang.Object r7 = kotlin.Result.m8979constructorimpl(r7)     // Catch:{ Exception -> 0x0a75 }
        L_0x031e:
            com.reown.sign.engine.use_case.responses.OnSessionAuthenticateResponseUseCase r12 = r1.this$0     // Catch:{ Exception -> 0x0a75 }
            com.reown.android.internal.common.model.WCResponse r13 = r1.$wcResponse     // Catch:{ Exception -> 0x0a75 }
            java.lang.Throwable r7 = kotlin.Result.m8982exceptionOrNullimpl(r7)     // Catch:{ Exception -> 0x0a75 }
            if (r7 == 0) goto L_0x0344
            com.reown.foundation.util.Logger r7 = r12.logger     // Catch:{ Exception -> 0x0070 }
            com.reown.foundation.common.model.Topic r12 = r13.getTopic()     // Catch:{ Exception -> 0x0070 }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0070 }
            r13.<init>()     // Catch:{ Exception -> 0x0070 }
            java.lang.String r14 = "Received session authenticate response - failed to delete authenticate response topic: "
            r13.append(r14)     // Catch:{ Exception -> 0x0070 }
            r13.append(r12)     // Catch:{ Exception -> 0x0070 }
            java.lang.String r12 = r13.toString()     // Catch:{ Exception -> 0x0070 }
            r7.error((java.lang.String) r12)     // Catch:{ Exception -> 0x0070 }
        L_0x0344:
            com.reown.android.internal.common.model.WCResponse r7 = r1.$wcResponse     // Catch:{ Exception -> 0x0a75 }
            com.reown.android.internal.common.JsonRpcResponse r7 = r7.getResponse()     // Catch:{ Exception -> 0x0a75 }
            boolean r12 = r7 instanceof com.reown.android.internal.common.JsonRpcResponse.JsonRpcError     // Catch:{ Exception -> 0x0a75 }
            if (r12 == 0) goto L_0x043b
            com.reown.android.internal.common.model.TransportType r2 = r4.getTransportType()     // Catch:{ Exception -> 0x0070 }
            com.reown.android.internal.common.model.TransportType r9 = com.reown.android.internal.common.model.TransportType.LINK_MODE     // Catch:{ Exception -> 0x0070 }
            if (r2 != r9) goto L_0x03c2
            com.reown.sign.engine.use_case.responses.OnSessionAuthenticateResponseUseCase r2 = r1.this$0     // Catch:{ Exception -> 0x0070 }
            com.reown.android.pulse.domain.InsertEventUseCase r2 = r2.insertEventUseCase     // Catch:{ Exception -> 0x0070 }
            com.reown.android.pulse.model.properties.Props r9 = new com.reown.android.pulse.model.properties.Props     // Catch:{ Exception -> 0x0070 }
            com.reown.android.internal.common.model.Tags r10 = com.reown.android.internal.common.model.Tags.SESSION_AUTHENTICATE_LINK_MODE_RESPONSE_REJECT     // Catch:{ Exception -> 0x0070 }
            int r10 = r10.getId()     // Catch:{ Exception -> 0x0070 }
            java.lang.String r10 = java.lang.String.valueOf(r10)     // Catch:{ Exception -> 0x0070 }
            com.reown.sign.engine.use_case.responses.OnSessionAuthenticateResponseUseCase r11 = r1.this$0     // Catch:{ Exception -> 0x0070 }
            java.lang.String r26 = r11.clientId     // Catch:{ Exception -> 0x0070 }
            com.reown.android.internal.common.model.WCResponse r11 = r1.$wcResponse     // Catch:{ Exception -> 0x0070 }
            com.reown.android.internal.common.JsonRpcResponse r11 = r11.getResponse()     // Catch:{ Exception -> 0x0070 }
            long r11 = r11.getId()     // Catch:{ Exception -> 0x0070 }
            com.reown.android.pulse.model.Direction r13 = com.reown.android.pulse.model.Direction.RECEIVED     // Catch:{ Exception -> 0x0070 }
            java.lang.String r27 = r13.getState()     // Catch:{ Exception -> 0x0070 }
            com.reown.android.pulse.model.properties.Properties r13 = new com.reown.android.pulse.model.properties.Properties     // Catch:{ Exception -> 0x0070 }
            java.lang.Long r25 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r11)     // Catch:{ Exception -> 0x0070 }
            r24 = 0
            r28 = 0
            r29 = 2303(0x8ff, float:3.227E-42)
            r30 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r21 = 0
            r22 = 0
            r23 = 0
            r16 = r13
            r16.<init>(r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30)     // Catch:{ Exception -> 0x0070 }
            r9.<init>(r8, r10, r13)     // Catch:{ Exception -> 0x0070 }
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r5)     // Catch:{ Exception -> 0x0070 }
            r1.L$0 = r8     // Catch:{ Exception -> 0x0070 }
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)     // Catch:{ Exception -> 0x0070 }
            r1.L$1 = r8     // Catch:{ Exception -> 0x0070 }
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r3)     // Catch:{ Exception -> 0x0070 }
            r1.L$2 = r8     // Catch:{ Exception -> 0x0070 }
            r1.L$3 = r7     // Catch:{ Exception -> 0x0070 }
            r8 = 3
            r1.label = r8     // Catch:{ Exception -> 0x0070 }
            java.lang.Object r2 = r2.invoke(r9, r1)     // Catch:{ Exception -> 0x0070 }
            if (r2 != r6) goto L_0x03c0
            return r6
        L_0x03c0:
            r2 = r7
        L_0x03c1:
            r7 = r2
        L_0x03c2:
            com.reown.sign.engine.use_case.responses.OnSessionAuthenticateResponseUseCase r2 = r1.this$0     // Catch:{ Exception -> 0x0070 }
            com.reown.foundation.util.Logger r2 = r2.logger     // Catch:{ Exception -> 0x0070 }
            com.reown.android.internal.common.model.WCResponse r8 = r1.$wcResponse     // Catch:{ Exception -> 0x0070 }
            com.reown.foundation.common.model.Topic r8 = r8.getTopic()     // Catch:{ Exception -> 0x0070 }
            r9 = r7
            com.reown.android.internal.common.JsonRpcResponse$JsonRpcError r9 = (com.reown.android.internal.common.JsonRpcResponse.JsonRpcError) r9     // Catch:{ Exception -> 0x0070 }
            com.reown.android.internal.common.JsonRpcResponse$Error r9 = r9.getError()     // Catch:{ Exception -> 0x0070 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0070 }
            r10.<init>()     // Catch:{ Exception -> 0x0070 }
            java.lang.String r11 = "Received session authenticate response - emitting rpc error: "
            r10.append(r11)     // Catch:{ Exception -> 0x0070 }
            r10.append(r8)     // Catch:{ Exception -> 0x0070 }
            java.lang.String r8 = ", "
            r10.append(r8)     // Catch:{ Exception -> 0x0070 }
            r10.append(r9)     // Catch:{ Exception -> 0x0070 }
            java.lang.String r8 = r10.toString()     // Catch:{ Exception -> 0x0070 }
            r2.error((java.lang.String) r8)     // Catch:{ Exception -> 0x0070 }
            com.reown.sign.engine.use_case.responses.OnSessionAuthenticateResponseUseCase r2 = r1.this$0     // Catch:{ Exception -> 0x0070 }
            kotlinx.coroutines.flow.MutableSharedFlow r2 = r2._events     // Catch:{ Exception -> 0x0070 }
            com.reown.sign.engine.model.EngineDO$SessionAuthenticateResponse$Error r8 = new com.reown.sign.engine.model.EngineDO$SessionAuthenticateResponse$Error     // Catch:{ Exception -> 0x0070 }
            r9 = r7
            com.reown.android.internal.common.JsonRpcResponse$JsonRpcError r9 = (com.reown.android.internal.common.JsonRpcResponse.JsonRpcError) r9     // Catch:{ Exception -> 0x0070 }
            long r9 = r9.getId()     // Catch:{ Exception -> 0x0070 }
            r11 = r7
            com.reown.android.internal.common.JsonRpcResponse$JsonRpcError r11 = (com.reown.android.internal.common.JsonRpcResponse.JsonRpcError) r11     // Catch:{ Exception -> 0x0070 }
            com.reown.android.internal.common.JsonRpcResponse$Error r11 = r11.getError()     // Catch:{ Exception -> 0x0070 }
            int r11 = r11.getCode()     // Catch:{ Exception -> 0x0070 }
            r12 = r7
            com.reown.android.internal.common.JsonRpcResponse$JsonRpcError r12 = (com.reown.android.internal.common.JsonRpcResponse.JsonRpcError) r12     // Catch:{ Exception -> 0x0070 }
            com.reown.android.internal.common.JsonRpcResponse$Error r12 = r12.getError()     // Catch:{ Exception -> 0x0070 }
            java.lang.String r12 = r12.getMessage()     // Catch:{ Exception -> 0x0070 }
            r8.<init>(r9, r11, r12)     // Catch:{ Exception -> 0x0070 }
            java.lang.Object r9 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r5)     // Catch:{ Exception -> 0x0070 }
            r1.L$0 = r9     // Catch:{ Exception -> 0x0070 }
            java.lang.Object r4 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)     // Catch:{ Exception -> 0x0070 }
            r1.L$1 = r4     // Catch:{ Exception -> 0x0070 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r3)     // Catch:{ Exception -> 0x0070 }
            r1.L$2 = r3     // Catch:{ Exception -> 0x0070 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)     // Catch:{ Exception -> 0x0070 }
            r1.L$3 = r3     // Catch:{ Exception -> 0x0070 }
            r3 = 4
            r1.label = r3     // Catch:{ Exception -> 0x0070 }
            java.lang.Object r1 = r2.emit(r8, r1)     // Catch:{ Exception -> 0x0070 }
            if (r1 != r6) goto L_0x0ae2
            return r6
        L_0x043b:
            boolean r12 = r7 instanceof com.reown.android.internal.common.JsonRpcResponse.JsonRpcResult     // Catch:{ Exception -> 0x0a75 }
            if (r12 == 0) goto L_0x0a79
            com.reown.android.internal.common.model.TransportType r12 = r4.getTransportType()     // Catch:{ Exception -> 0x0a75 }
            com.reown.android.internal.common.model.TransportType r13 = com.reown.android.internal.common.model.TransportType.RELAY     // Catch:{ Exception -> 0x0a75 }
            if (r12 != r13) goto L_0x046a
            com.reown.sign.engine.use_case.responses.OnSessionAuthenticateResponseUseCase r12 = r1.this$0     // Catch:{ Exception -> 0x0070 }
            com.reown.android.pairing.handler.PairingControllerInterface r12 = r12.pairingController     // Catch:{ Exception -> 0x0070 }
            com.reown.android.Core$Params$UpdateMetadata r13 = new com.reown.android.Core$Params$UpdateMetadata     // Catch:{ Exception -> 0x0070 }
            java.lang.String r14 = r3.getValue()     // Catch:{ Exception -> 0x0070 }
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionAuthenticateParams r15 = r1.$params     // Catch:{ Exception -> 0x0070 }
            com.reown.sign.common.model.vo.clientsync.common.Requester r15 = r15.getRequester()     // Catch:{ Exception -> 0x0070 }
            com.reown.android.internal.common.model.AppMetaData r15 = r15.getMetadata()     // Catch:{ Exception -> 0x0070 }
            com.reown.android.Core$Model$AppMetaData r15 = com.reown.android.utils.ExtensionsKt.toClient(r15)     // Catch:{ Exception -> 0x0070 }
            com.reown.android.internal.common.model.AppMetaDataType r11 = com.reown.android.internal.common.model.AppMetaDataType.PEER     // Catch:{ Exception -> 0x0070 }
            r13.<init>(r14, r15, r11)     // Catch:{ Exception -> 0x0070 }
            r11 = 0
            com.reown.android.pairing.handler.PairingControllerInterface.updateMetadata$default(r12, r13, r11, r9, r11)     // Catch:{ Exception -> 0x0070 }
        L_0x046a:
            r9 = r7
            com.reown.android.internal.common.JsonRpcResponse$JsonRpcResult r9 = (com.reown.android.internal.common.JsonRpcResponse.JsonRpcResult) r9     // Catch:{ Exception -> 0x0a75 }
            java.lang.Object r9 = r9.getResult()     // Catch:{ Exception -> 0x0a75 }
            java.lang.String r11 = "null cannot be cast to non-null type com.reown.android.internal.common.model.params.CoreSignParams.SessionAuthenticateApproveParams"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r9, r11)     // Catch:{ Exception -> 0x0a75 }
            com.reown.android.internal.common.model.params.CoreSignParams$SessionAuthenticateApproveParams r9 = (com.reown.android.internal.common.model.params.CoreSignParams.SessionAuthenticateApproveParams) r9     // Catch:{ Exception -> 0x0a75 }
            java.util.List r11 = r9.getCacaos()     // Catch:{ Exception -> 0x0a75 }
            java.lang.Iterable r11 = (java.lang.Iterable) r11     // Catch:{ Exception -> 0x0a75 }
            com.reown.sign.engine.use_case.responses.OnSessionAuthenticateResponseUseCase r12 = r1.this$0     // Catch:{ Exception -> 0x0a75 }
            java.util.Iterator r11 = r11.iterator()     // Catch:{ Exception -> 0x0a75 }
        L_0x0484:
            boolean r13 = r11.hasNext()     // Catch:{ Exception -> 0x0a75 }
            if (r13 == 0) goto L_0x049c
            java.lang.Object r13 = r11.next()     // Catch:{ Exception -> 0x0070 }
            r14 = r13
            com.reown.android.internal.common.signing.cacao.Cacao r14 = (com.reown.android.internal.common.signing.cacao.Cacao) r14     // Catch:{ Exception -> 0x0070 }
            com.reown.android.internal.common.signing.cacao.CacaoVerifier r15 = r12.cacaoVerifier     // Catch:{ Exception -> 0x0070 }
            boolean r14 = r15.verify(r14)     // Catch:{ Exception -> 0x0070 }
            if (r14 != 0) goto L_0x0484
            goto L_0x049d
        L_0x049c:
            r13 = 0
        L_0x049d:
            if (r13 == 0) goto L_0x04e3
            com.reown.sign.engine.use_case.responses.OnSessionAuthenticateResponseUseCase r8 = r1.this$0     // Catch:{ Exception -> 0x0070 }
            com.reown.foundation.util.Logger r8 = r8.logger     // Catch:{ Exception -> 0x0070 }
            r8.error((java.lang.String) r2)     // Catch:{ Exception -> 0x0070 }
            com.reown.sign.engine.use_case.responses.OnSessionAuthenticateResponseUseCase r8 = r1.this$0     // Catch:{ Exception -> 0x0070 }
            kotlinx.coroutines.flow.MutableSharedFlow r8 = r8._events     // Catch:{ Exception -> 0x0070 }
            com.reown.android.internal.common.model.SDKError r10 = new com.reown.android.internal.common.model.SDKError     // Catch:{ Exception -> 0x0070 }
            java.lang.Throwable r11 = new java.lang.Throwable     // Catch:{ Exception -> 0x0070 }
            r11.<init>(r2)     // Catch:{ Exception -> 0x0070 }
            r10.<init>(r11)     // Catch:{ Exception -> 0x0070 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r5)     // Catch:{ Exception -> 0x0070 }
            r1.L$0 = r2     // Catch:{ Exception -> 0x0070 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)     // Catch:{ Exception -> 0x0070 }
            r1.L$1 = r2     // Catch:{ Exception -> 0x0070 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r3)     // Catch:{ Exception -> 0x0070 }
            r1.L$2 = r2     // Catch:{ Exception -> 0x0070 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)     // Catch:{ Exception -> 0x0070 }
            r1.L$3 = r2     // Catch:{ Exception -> 0x0070 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r9)     // Catch:{ Exception -> 0x0070 }
            r1.L$4 = r2     // Catch:{ Exception -> 0x0070 }
            r2 = 5
            r1.label = r2     // Catch:{ Exception -> 0x0070 }
            java.lang.Object r2 = r8.emit(r10, r1)     // Catch:{ Exception -> 0x0070 }
            if (r2 != r6) goto L_0x04e0
            return r6
        L_0x04e0:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x0070 }
            return r1
        L_0x04e3:
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionAuthenticateParams r2 = r1.$params     // Catch:{ Exception -> 0x0a75 }
            com.reown.sign.engine.use_case.responses.OnSessionAuthenticateResponseUseCase r11 = r1.this$0     // Catch:{ Exception -> 0x0a75 }
            com.reown.android.internal.common.model.WCResponse r12 = r1.$wcResponse     // Catch:{ Exception -> 0x0a75 }
            com.reown.sign.common.model.vo.clientsync.common.Requester r13 = r2.getRequester()     // Catch:{ Exception -> 0x0a75 }
            java.lang.String r13 = r13.getPublicKey()     // Catch:{ Exception -> 0x0a75 }
            java.lang.String r13 = com.reown.foundation.common.model.PublicKey.m8856constructorimpl(r13)     // Catch:{ Exception -> 0x0a75 }
            com.reown.android.internal.common.model.Participant r14 = r9.getResponder()     // Catch:{ Exception -> 0x0a75 }
            java.lang.String r14 = r14.getPublicKey()     // Catch:{ Exception -> 0x0a75 }
            java.lang.String r14 = com.reown.foundation.common.model.PublicKey.m8856constructorimpl(r14)     // Catch:{ Exception -> 0x0a75 }
            com.reown.android.internal.common.crypto.kmr.KeyManagementRepository r15 = r11.crypto     // Catch:{ Exception -> 0x0a75 }
            java.lang.String r15 = r15.m8725generateSymmetricKeyFromKeyAgreementrMsFr_I(r13, r14)     // Catch:{ Exception -> 0x0a75 }
            com.reown.android.internal.common.crypto.kmr.KeyManagementRepository r10 = r11.crypto     // Catch:{ Exception -> 0x0a75 }
            r18 = r8
            com.reown.android.internal.common.model.SymmetricKey r8 = com.reown.android.internal.common.model.SymmetricKey.m8777boximpl(r15)     // Catch:{ Exception -> 0x0a75 }
            com.reown.foundation.common.model.Topic r8 = r10.getTopicFromKey(r8)     // Catch:{ Exception -> 0x0a75 }
            com.reown.android.internal.common.crypto.kmr.KeyManagementRepository r10 = r11.crypto     // Catch:{ Exception -> 0x0a75 }
            r19 = r12
            com.reown.android.internal.common.model.SymmetricKey r12 = com.reown.android.internal.common.model.SymmetricKey.m8777boximpl(r15)     // Catch:{ Exception -> 0x0a75 }
            r20 = r6
            java.lang.String r6 = r8.getValue()     // Catch:{ Exception -> 0x07bd }
            r10.setKey(r12, r6)     // Catch:{ Exception -> 0x07bd }
            java.util.List r6 = r9.getCacaos()     // Catch:{ Exception -> 0x07bd }
            java.lang.Object r6 = kotlin.collections.CollectionsKt.first(r6)     // Catch:{ Exception -> 0x07bd }
            com.reown.android.internal.common.signing.cacao.Cacao r6 = (com.reown.android.internal.common.signing.cacao.Cacao) r6     // Catch:{ Exception -> 0x07bd }
            com.reown.android.internal.common.signing.cacao.Cacao$Payload r6 = r6.getPayload()     // Catch:{ Exception -> 0x07bd }
            java.util.List r6 = r6.getResources()     // Catch:{ Exception -> 0x07bd }
            java.util.List r6 = com.reown.android.internal.common.signing.cacao.UtilsKt.getChains(r6)     // Catch:{ Exception -> 0x07bd }
            java.util.Collection r6 = (java.util.Collection) r6     // Catch:{ Exception -> 0x07bd }
            boolean r10 = r6.isEmpty()     // Catch:{ Exception -> 0x07bd }
            if (r10 == 0) goto L_0x0557
            com.reown.sign.common.model.vo.clientsync.common.PayloadParams r6 = r2.getAuthPayload()     // Catch:{ Exception -> 0x0551 }
            java.util.List r6 = r6.getChains()     // Catch:{ Exception -> 0x0551 }
            goto L_0x0557
        L_0x0551:
            r0 = move-exception
            r2 = r0
            r15 = r20
            goto L_0x0a80
        L_0x0557:
            java.util.List r6 = (java.util.List) r6     // Catch:{ Exception -> 0x07bd }
            java.util.List r10 = r9.getCacaos()     // Catch:{ Exception -> 0x07bd }
            java.lang.Iterable r10 = (java.lang.Iterable) r10     // Catch:{ Exception -> 0x07bd }
            java.util.ArrayList r12 = new java.util.ArrayList     // Catch:{ Exception -> 0x07bd }
            r21 = r2
            int r2 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r10, 10)     // Catch:{ Exception -> 0x07bd }
            r12.<init>(r2)     // Catch:{ Exception -> 0x07bd }
            java.util.Iterator r2 = r10.iterator()     // Catch:{ Exception -> 0x07bd }
        L_0x056e:
            boolean r10 = r2.hasNext()     // Catch:{ Exception -> 0x07bd }
            if (r10 == 0) goto L_0x0593
            java.lang.Object r10 = r2.next()     // Catch:{ Exception -> 0x0551 }
            com.reown.android.internal.common.signing.cacao.Cacao r10 = (com.reown.android.internal.common.signing.cacao.Cacao) r10     // Catch:{ Exception -> 0x0551 }
            r56 = r2
            com.reown.android.internal.common.signing.cacao.Issuer r2 = new com.reown.android.internal.common.signing.cacao.Issuer     // Catch:{ Exception -> 0x0551 }
            com.reown.android.internal.common.signing.cacao.Cacao$Payload r10 = r10.getPayload()     // Catch:{ Exception -> 0x0551 }
            java.lang.String r10 = r10.getIss()     // Catch:{ Exception -> 0x0551 }
            r2.<init>(r10)     // Catch:{ Exception -> 0x0551 }
            java.lang.String r2 = r2.getAddress()     // Catch:{ Exception -> 0x0551 }
            r12.add(r2)     // Catch:{ Exception -> 0x0551 }
            r2 = r56
            goto L_0x056e
        L_0x0593:
            java.util.List r2 = kotlin.collections.CollectionsKt.distinct(r12)     // Catch:{ Exception -> 0x07bd }
            java.util.ArrayList r12 = new java.util.ArrayList     // Catch:{ Exception -> 0x07bd }
            r12.<init>()     // Catch:{ Exception -> 0x07bd }
            r10 = r6
            java.lang.Iterable r10 = (java.lang.Iterable) r10     // Catch:{ Exception -> 0x07bd }
            java.util.Iterator r10 = r10.iterator()     // Catch:{ Exception -> 0x07bd }
        L_0x05a3:
            boolean r22 = r10.hasNext()     // Catch:{ Exception -> 0x07bd }
            if (r22 == 0) goto L_0x05f0
            java.lang.Object r22 = r10.next()     // Catch:{ Exception -> 0x0551 }
            r56 = r10
            r10 = r22
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ Exception -> 0x0551 }
            r22 = r2
            java.lang.Iterable r22 = (java.lang.Iterable) r22     // Catch:{ Exception -> 0x0551 }
            java.util.Iterator r22 = r22.iterator()     // Catch:{ Exception -> 0x0551 }
        L_0x05bb:
            boolean r23 = r22.hasNext()     // Catch:{ Exception -> 0x0551 }
            if (r23 == 0) goto L_0x05ed
            java.lang.Object r23 = r22.next()     // Catch:{ Exception -> 0x0551 }
            r24 = r2
            r2 = r23
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Exception -> 0x0551 }
            r23 = r8
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0551 }
            r8.<init>()     // Catch:{ Exception -> 0x0551 }
            r8.append(r10)     // Catch:{ Exception -> 0x0551 }
            r25 = r10
            java.lang.String r10 = ":"
            r8.append(r10)     // Catch:{ Exception -> 0x0551 }
            r8.append(r2)     // Catch:{ Exception -> 0x0551 }
            java.lang.String r2 = r8.toString()     // Catch:{ Exception -> 0x0551 }
            r12.add(r2)     // Catch:{ Exception -> 0x0551 }
            r8 = r23
            r2 = r24
            r10 = r25
            goto L_0x05bb
        L_0x05ed:
            r10 = r56
            goto L_0x05a3
        L_0x05f0:
            r24 = r2
            r23 = r8
            boolean r2 = r11.areEVMAndCAIP2Chains(r6)     // Catch:{ Exception -> 0x07bd }
            if (r2 != 0) goto L_0x0673
            kotlinx.coroutines.flow.MutableSharedFlow r2 = r11._events     // Catch:{ Exception -> 0x066f }
            com.reown.android.internal.common.model.SDKError r8 = new com.reown.android.internal.common.model.SDKError     // Catch:{ Exception -> 0x066f }
            java.lang.Exception r10 = new java.lang.Exception     // Catch:{ Exception -> 0x066f }
            java.lang.String r11 = "Chains are not CAIP-2 compliant or are not EVM chains"
            r10.<init>(r11)     // Catch:{ Exception -> 0x066f }
            r8.<init>(r10)     // Catch:{ Exception -> 0x066f }
            java.lang.Object r10 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r5)     // Catch:{ Exception -> 0x066f }
            r1.L$0 = r10     // Catch:{ Exception -> 0x066f }
            java.lang.Object r4 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)     // Catch:{ Exception -> 0x066f }
            r1.L$1 = r4     // Catch:{ Exception -> 0x066f }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r3)     // Catch:{ Exception -> 0x066f }
            r1.L$2 = r3     // Catch:{ Exception -> 0x066f }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)     // Catch:{ Exception -> 0x066f }
            r1.L$3 = r3     // Catch:{ Exception -> 0x066f }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r9)     // Catch:{ Exception -> 0x066f }
            r1.L$4 = r3     // Catch:{ Exception -> 0x066f }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r9)     // Catch:{ Exception -> 0x066f }
            r1.L$5 = r3     // Catch:{ Exception -> 0x066f }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r13)     // Catch:{ Exception -> 0x066f }
            r1.L$6 = r3     // Catch:{ Exception -> 0x066f }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r14)     // Catch:{ Exception -> 0x066f }
            r1.L$7 = r3     // Catch:{ Exception -> 0x066f }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r15)     // Catch:{ Exception -> 0x066f }
            r1.L$8 = r3     // Catch:{ Exception -> 0x066f }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r23)     // Catch:{ Exception -> 0x066f }
            r1.L$9 = r3     // Catch:{ Exception -> 0x066f }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r24)     // Catch:{ Exception -> 0x066f }
            r1.L$10 = r3     // Catch:{ Exception -> 0x066f }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r12)     // Catch:{ Exception -> 0x066f }
            r1.L$11 = r3     // Catch:{ Exception -> 0x066f }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r6)     // Catch:{ Exception -> 0x066f }
            r1.L$12 = r3     // Catch:{ Exception -> 0x066f }
            r3 = 0
            r1.I$0 = r3     // Catch:{ Exception -> 0x066f }
            r3 = 6
            r1.label = r3     // Catch:{ Exception -> 0x066f }
            java.lang.Object r2 = r2.emit(r8, r1)     // Catch:{ Exception -> 0x066f }
            r8 = r20
            if (r2 != r8) goto L_0x0667
            return r8
        L_0x0667:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x066a }
            return r1
        L_0x066a:
            r0 = move-exception
        L_0x066b:
            r2 = r0
            r15 = r8
            goto L_0x0a80
        L_0x066f:
            r0 = move-exception
            r8 = r20
            goto L_0x066b
        L_0x0673:
            r8 = r20
            com.reown.android.internal.common.signing.cacao.Issuer r2 = new com.reown.android.internal.common.signing.cacao.Issuer     // Catch:{ Exception -> 0x0a71 }
            java.util.List r10 = r9.getCacaos()     // Catch:{ Exception -> 0x0a71 }
            java.lang.Object r10 = kotlin.collections.CollectionsKt.first(r10)     // Catch:{ Exception -> 0x0a71 }
            com.reown.android.internal.common.signing.cacao.Cacao r10 = (com.reown.android.internal.common.signing.cacao.Cacao) r10     // Catch:{ Exception -> 0x0a71 }
            com.reown.android.internal.common.signing.cacao.Cacao$Payload r10 = r10.getPayload()     // Catch:{ Exception -> 0x0a71 }
            java.lang.String r10 = r10.getIss()     // Catch:{ Exception -> 0x0a71 }
            r2.<init>(r10)     // Catch:{ Exception -> 0x0a71 }
            java.lang.String r2 = r2.getNamespace()     // Catch:{ Exception -> 0x0a71 }
            java.util.List r10 = r9.getCacaos()     // Catch:{ Exception -> 0x0a71 }
            java.lang.Object r10 = kotlin.collections.CollectionsKt.first(r10)     // Catch:{ Exception -> 0x0a71 }
            com.reown.android.internal.common.signing.cacao.Cacao r10 = (com.reown.android.internal.common.signing.cacao.Cacao) r10     // Catch:{ Exception -> 0x0a71 }
            com.reown.android.internal.common.signing.cacao.Cacao$Payload r10 = r10.getPayload()     // Catch:{ Exception -> 0x0a71 }
            java.util.List r10 = r10.getMethods()     // Catch:{ Exception -> 0x0a71 }
            r20 = r8
            java.lang.String r8 = "chainChanged"
            r56 = r15
            java.lang.String r15 = "accountsChanged"
            java.lang.String[] r8 = new java.lang.String[]{r8, r15}     // Catch:{ Exception -> 0x07bd }
            java.util.List r8 = kotlin.collections.CollectionsKt.listOf(r8)     // Catch:{ Exception -> 0x07bd }
            r15 = r10
            java.util.Collection r15 = (java.util.Collection) r15     // Catch:{ Exception -> 0x07bd }
            boolean r15 = r15.isEmpty()     // Catch:{ Exception -> 0x07bd }
            if (r15 != 0) goto L_0x0987
            com.reown.foundation.util.Logger r15 = r11.logger     // Catch:{ Exception -> 0x07bd }
            r22 = r14
            java.lang.String r14 = "Creating authenticated session"
            r15.log((java.lang.String) r14)     // Catch:{ Exception -> 0x07bd }
            com.reown.android.internal.common.model.Namespace$Session r14 = new com.reown.android.internal.common.model.Namespace$Session     // Catch:{ Exception -> 0x07bd }
            r14.<init>(r6, r12, r10, r8)     // Catch:{ Exception -> 0x07bd }
            kotlin.Pair r14 = kotlin.TuplesKt.to(r2, r14)     // Catch:{ Exception -> 0x07bd }
            java.util.Map r14 = kotlin.collections.MapsKt.mapOf(r14)     // Catch:{ Exception -> 0x07bd }
            java.util.List r15 = kotlin.collections.CollectionsKt.emptyList()     // Catch:{ Exception -> 0x07bd }
            r25 = r14
            com.reown.android.internal.common.model.Namespace$Proposal r14 = new com.reown.android.internal.common.model.Namespace$Proposal     // Catch:{ Exception -> 0x07bd }
            r14.<init>(r8, r6, r15)     // Catch:{ Exception -> 0x07bd }
            kotlin.Pair r14 = kotlin.TuplesKt.to(r2, r14)     // Catch:{ Exception -> 0x07bd }
            java.util.Map r14 = kotlin.collections.MapsKt.mapOf(r14)     // Catch:{ Exception -> 0x07bd }
            java.lang.Boolean r15 = r9.getLinkMode()     // Catch:{ Exception -> 0x07bd }
            r26 = r14
            r16 = 1
            java.lang.Boolean r14 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r16)     // Catch:{ Exception -> 0x07bd }
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r15, (java.lang.Object) r14)     // Catch:{ Exception -> 0x07bd }
            if (r14 == 0) goto L_0x07c1
            java.lang.String r14 = r9.getAppLink()     // Catch:{ Exception -> 0x07bd }
            if (r14 == 0) goto L_0x07c1
            int r14 = r14.length()     // Catch:{ Exception -> 0x07bd }
            if (r14 != 0) goto L_0x0706
            goto L_0x07c1
        L_0x0706:
            com.reown.sign.storage.link_mode.LinkModeStorageRepository r14 = r11.linkModeStorageRepository     // Catch:{ Exception -> 0x07bd }
            java.lang.String r15 = r9.getAppLink()     // Catch:{ Exception -> 0x07bd }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r15)     // Catch:{ Exception -> 0x07bd }
            r16 = r14
            java.lang.Object r14 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r5)     // Catch:{ Exception -> 0x07bd }
            r1.L$0 = r14     // Catch:{ Exception -> 0x07bd }
            java.lang.Object r14 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)     // Catch:{ Exception -> 0x07bd }
            r1.L$1 = r14     // Catch:{ Exception -> 0x07bd }
            r1.L$2 = r3     // Catch:{ Exception -> 0x07bd }
            r1.L$3 = r7     // Catch:{ Exception -> 0x07bd }
            r1.L$4 = r9     // Catch:{ Exception -> 0x07bd }
            r14 = r21
            r1.L$5 = r14     // Catch:{ Exception -> 0x07bd }
            r1.L$6 = r11     // Catch:{ Exception -> 0x07bd }
            r21 = r3
            r3 = r19
            r1.L$7 = r3     // Catch:{ Exception -> 0x07bd }
            r19 = r3
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r9)     // Catch:{ Exception -> 0x07bd }
            r1.L$8 = r3     // Catch:{ Exception -> 0x07bd }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r13)     // Catch:{ Exception -> 0x07bd }
            r1.L$9 = r3     // Catch:{ Exception -> 0x07bd }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r22)     // Catch:{ Exception -> 0x07bd }
            r1.L$10 = r3     // Catch:{ Exception -> 0x07bd }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r56)     // Catch:{ Exception -> 0x07bd }
            r1.L$11 = r3     // Catch:{ Exception -> 0x07bd }
            r3 = r23
            r1.L$12 = r3     // Catch:{ Exception -> 0x07bd }
            r23 = r3
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r24)     // Catch:{ Exception -> 0x07bd }
            r1.L$13 = r3     // Catch:{ Exception -> 0x07bd }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r12)     // Catch:{ Exception -> 0x07bd }
            r1.L$14 = r3     // Catch:{ Exception -> 0x07bd }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r6)     // Catch:{ Exception -> 0x07bd }
            r1.L$15 = r3     // Catch:{ Exception -> 0x07bd }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)     // Catch:{ Exception -> 0x07bd }
            r1.L$16 = r3     // Catch:{ Exception -> 0x07bd }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r10)     // Catch:{ Exception -> 0x07bd }
            r1.L$17 = r3     // Catch:{ Exception -> 0x07bd }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)     // Catch:{ Exception -> 0x07bd }
            r1.L$18 = r3     // Catch:{ Exception -> 0x07bd }
            r3 = r25
            r1.L$19 = r3     // Catch:{ Exception -> 0x07bd }
            r25 = r2
            r2 = r26
            r1.L$20 = r2     // Catch:{ Exception -> 0x07bd }
            r26 = r2
            r2 = 0
            r1.I$0 = r2     // Catch:{ Exception -> 0x07bd }
            r2 = 7
            r1.label = r2     // Catch:{ Exception -> 0x07bd }
            r2 = r16
            java.lang.Object r2 = r2.insert(r15, r1)     // Catch:{ Exception -> 0x07bd }
            r15 = r20
            if (r2 != r15) goto L_0x0792
            return r15
        L_0x0792:
            r2 = r4
            r4 = r8
            r17 = r22
            r20 = r23
            r16 = r26
            r22 = r56
            r8 = r6
            r23 = r9
            r6 = 0
        L_0x07a0:
            com.reown.android.internal.common.model.TransportType r26 = com.reown.android.internal.common.model.TransportType.LINK_MODE     // Catch:{ Exception -> 0x07b9 }
            r56 = r20
            r20 = r17
            r17 = r16
            r16 = r13
            r13 = r6
            r6 = r8
            r8 = r4
            r4 = r2
            r2 = r23
            r23 = r26
        L_0x07b2:
            r53 = r19
            r19 = r3
            r3 = r53
            goto L_0x07dc
        L_0x07b9:
            r0 = move-exception
        L_0x07ba:
            r2 = r0
            goto L_0x0a80
        L_0x07bd:
            r0 = move-exception
            r15 = r20
            goto L_0x07ba
        L_0x07c1:
            r15 = r20
            r14 = r21
            r21 = r3
            r3 = r25
            r25 = r2
            com.reown.android.internal.common.model.TransportType r2 = com.reown.android.internal.common.model.TransportType.RELAY     // Catch:{ Exception -> 0x07b9 }
            r16 = r13
            r20 = r22
            r17 = r26
            r13 = 0
            r22 = r56
            r56 = r23
            r23 = r2
            r2 = r9
            goto L_0x07b2
        L_0x07dc:
            com.reown.sign.common.model.vo.sequence.SessionVO$Companion r26 = com.reown.sign.common.model.vo.sequence.SessionVO.Companion     // Catch:{ Exception -> 0x07b9 }
            com.reown.android.internal.common.model.Participant r27 = r2.getResponder()     // Catch:{ Exception -> 0x07b9 }
            java.lang.String r27 = r27.getPublicKey()     // Catch:{ Exception -> 0x07b9 }
            java.lang.String r28 = com.reown.foundation.common.model.PublicKey.m8856constructorimpl(r27)     // Catch:{ Exception -> 0x07b9 }
            com.reown.android.internal.common.model.Participant r27 = r2.getResponder()     // Catch:{ Exception -> 0x07b9 }
            com.reown.android.internal.common.model.AppMetaData r29 = r27.getMetadata()     // Catch:{ Exception -> 0x07b9 }
            com.reown.sign.common.model.vo.clientsync.common.Requester r27 = r14.getRequester()     // Catch:{ Exception -> 0x07b9 }
            java.lang.String r27 = r27.getPublicKey()     // Catch:{ Exception -> 0x07b9 }
            java.lang.String r30 = com.reown.foundation.common.model.PublicKey.m8856constructorimpl(r27)     // Catch:{ Exception -> 0x07b9 }
            com.reown.sign.common.model.vo.clientsync.common.Requester r27 = r14.getRequester()     // Catch:{ Exception -> 0x07b9 }
            com.reown.android.internal.common.model.AppMetaData r31 = r27.getMetadata()     // Catch:{ Exception -> 0x07b9 }
            com.reown.android.internal.common.model.Participant r27 = r2.getResponder()     // Catch:{ Exception -> 0x07b9 }
            java.lang.String r27 = r27.getPublicKey()     // Catch:{ Exception -> 0x07b9 }
            java.lang.String r32 = com.reown.foundation.common.model.PublicKey.m8856constructorimpl(r27)     // Catch:{ Exception -> 0x07b9 }
            java.lang.String r35 = r21.getValue()     // Catch:{ Exception -> 0x07b9 }
            r27 = r56
            r33 = r17
            r34 = r19
            r36 = r23
            r37 = r15
            com.reown.sign.common.model.vo.sequence.SessionVO r15 = r26.m8880createAuthenticatedSessiontF0nsiE$sign_release(r27, r28, r29, r30, r31, r32, r33, r34, r35, r36)     // Catch:{ Exception -> 0x0964 }
            r26 = r13
            com.reown.android.internal.common.storage.metadata.MetadataStorageRepositoryInterface r13 = r11.metadataStorageRepository     // Catch:{ Exception -> 0x0964 }
            com.reown.sign.common.model.vo.clientsync.common.Requester r14 = r14.getRequester()     // Catch:{ Exception -> 0x0964 }
            com.reown.android.internal.common.model.AppMetaData r14 = r14.getMetadata()     // Catch:{ Exception -> 0x0964 }
            r27 = r8
            com.reown.android.internal.common.model.AppMetaDataType r8 = com.reown.android.internal.common.model.AppMetaDataType.SELF     // Catch:{ Exception -> 0x0964 }
            r28 = r10
            r10 = r56
            r13.insertOrAbortMetadata(r10, r14, r8)     // Catch:{ Exception -> 0x0964 }
            com.reown.android.internal.common.storage.metadata.MetadataStorageRepositoryInterface r8 = r11.metadataStorageRepository     // Catch:{ Exception -> 0x0964 }
            com.reown.android.internal.common.model.Participant r13 = r2.getResponder()     // Catch:{ Exception -> 0x0964 }
            com.reown.android.internal.common.model.AppMetaData r13 = r13.getMetadata()     // Catch:{ Exception -> 0x0964 }
            com.reown.android.internal.common.model.AppMetaDataType r14 = com.reown.android.internal.common.model.AppMetaDataType.PEER     // Catch:{ Exception -> 0x0964 }
            r8.insertOrAbortMetadata(r10, r13, r14)     // Catch:{ Exception -> 0x0964 }
            com.reown.sign.storage.sequence.SessionStorageRepository r8 = r11.sessionStorageRepository     // Catch:{ Exception -> 0x0964 }
            r13 = r7
            com.reown.android.internal.common.JsonRpcResponse$JsonRpcResult r13 = (com.reown.android.internal.common.JsonRpcResponse.JsonRpcResult) r13     // Catch:{ Exception -> 0x0964 }
            long r13 = r13.getId()     // Catch:{ Exception -> 0x0964 }
            r8.insertSession(r15, r13)     // Catch:{ Exception -> 0x0964 }
            com.reown.android.internal.common.model.TransportType r8 = com.reown.android.internal.common.model.TransportType.LINK_MODE     // Catch:{ Exception -> 0x0964 }
            r13 = r23
            if (r13 != r8) goto L_0x0969
            com.reown.android.pulse.domain.InsertEventUseCase r8 = r11.insertEventUseCase     // Catch:{ Exception -> 0x0964 }
            com.reown.android.pulse.model.properties.Props r14 = new com.reown.android.pulse.model.properties.Props     // Catch:{ Exception -> 0x0964 }
            com.reown.android.internal.common.model.Tags r23 = com.reown.android.internal.common.model.Tags.SESSION_AUTHENTICATE_LINK_MODE_RESPONSE_APPROVE     // Catch:{ Exception -> 0x0964 }
            int r23 = r23.getId()     // Catch:{ Exception -> 0x0964 }
            r56 = r8
            java.lang.String r8 = java.lang.String.valueOf(r23)     // Catch:{ Exception -> 0x0964 }
            java.lang.String r48 = r11.clientId     // Catch:{ Exception -> 0x0964 }
            com.reown.android.internal.common.JsonRpcResponse r23 = r3.getResponse()     // Catch:{ Exception -> 0x0964 }
            long r29 = r23.getId()     // Catch:{ Exception -> 0x0964 }
            com.reown.android.pulse.model.Direction r23 = com.reown.android.pulse.model.Direction.RECEIVED     // Catch:{ Exception -> 0x0964 }
            java.lang.String r49 = r23.getState()     // Catch:{ Exception -> 0x0964 }
            r23 = r13
            com.reown.android.pulse.model.properties.Properties r13 = new com.reown.android.pulse.model.properties.Properties     // Catch:{ Exception -> 0x0964 }
            java.lang.Long r47 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r29)     // Catch:{ Exception -> 0x0964 }
            r46 = 0
            r50 = 0
            r51 = 2303(0x8ff, float:3.227E-42)
            r52 = 0
            r39 = 0
            r40 = 0
            r41 = 0
            r42 = 0
            r43 = 0
            r44 = 0
            r45 = 0
            r38 = r13
            r38.<init>(r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52)     // Catch:{ Exception -> 0x0964 }
            r29 = r15
            r15 = r18
            r14.<init>(r15, r8, r13)     // Catch:{ Exception -> 0x0964 }
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r5)     // Catch:{ Exception -> 0x0964 }
            r1.L$0 = r8     // Catch:{ Exception -> 0x0964 }
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)     // Catch:{ Exception -> 0x0964 }
            r1.L$1 = r8     // Catch:{ Exception -> 0x0964 }
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r21)     // Catch:{ Exception -> 0x0964 }
            r1.L$2 = r8     // Catch:{ Exception -> 0x0964 }
            r1.L$3 = r7     // Catch:{ Exception -> 0x0964 }
            r1.L$4 = r2     // Catch:{ Exception -> 0x0964 }
            r1.L$5 = r11     // Catch:{ Exception -> 0x0964 }
            r1.L$6 = r3     // Catch:{ Exception -> 0x0964 }
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r9)     // Catch:{ Exception -> 0x0964 }
            r1.L$7 = r8     // Catch:{ Exception -> 0x0964 }
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r16)     // Catch:{ Exception -> 0x0964 }
            r1.L$8 = r8     // Catch:{ Exception -> 0x0964 }
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r20)     // Catch:{ Exception -> 0x0964 }
            r1.L$9 = r8     // Catch:{ Exception -> 0x0964 }
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r22)     // Catch:{ Exception -> 0x0964 }
            r1.L$10 = r8     // Catch:{ Exception -> 0x0964 }
            r1.L$11 = r10     // Catch:{ Exception -> 0x0964 }
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r24)     // Catch:{ Exception -> 0x0964 }
            r1.L$12 = r8     // Catch:{ Exception -> 0x0964 }
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r12)     // Catch:{ Exception -> 0x0964 }
            r1.L$13 = r8     // Catch:{ Exception -> 0x0964 }
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r6)     // Catch:{ Exception -> 0x0964 }
            r1.L$14 = r8     // Catch:{ Exception -> 0x0964 }
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r25)     // Catch:{ Exception -> 0x0964 }
            r1.L$15 = r8     // Catch:{ Exception -> 0x0964 }
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r28)     // Catch:{ Exception -> 0x0964 }
            r1.L$16 = r8     // Catch:{ Exception -> 0x0964 }
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r27)     // Catch:{ Exception -> 0x0964 }
            r1.L$17 = r8     // Catch:{ Exception -> 0x0964 }
            r8 = r29
            r1.L$18 = r8     // Catch:{ Exception -> 0x0964 }
            java.lang.Object r13 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r19)     // Catch:{ Exception -> 0x0964 }
            r1.L$19 = r13     // Catch:{ Exception -> 0x0964 }
            java.lang.Object r13 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r17)     // Catch:{ Exception -> 0x0964 }
            r1.L$20 = r13     // Catch:{ Exception -> 0x0964 }
            java.lang.Object r13 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r23)     // Catch:{ Exception -> 0x0964 }
            r1.L$21 = r13     // Catch:{ Exception -> 0x0964 }
            r13 = r26
            r1.I$0 = r13     // Catch:{ Exception -> 0x0964 }
            r15 = 8
            r1.label = r15     // Catch:{ Exception -> 0x0964 }
            r15 = r56
            java.lang.Object r14 = r15.invoke(r14, r1)     // Catch:{ Exception -> 0x0964 }
            r15 = r37
            if (r14 != r15) goto L_0x0931
            return r15
        L_0x0931:
            r18 = r3
            r3 = r8
            r17 = r9
            r19 = r11
            r14 = r22
            r11 = r24
            r8 = r25
            r9 = r6
            r6 = r20
            r20 = r2
            r2 = r4
            r4 = r27
            r53 = r13
            r13 = r10
            r10 = r53
        L_0x094b:
            r24 = r3
            r27 = r4
            r22 = r6
            r25 = r8
            r6 = r9
            r23 = r13
            r13 = r19
            r3 = r21
            r4 = r2
            r2 = r11
            r21 = r14
            r19 = r16
            r14 = r10
            r16 = r12
            goto L_0x09a5
        L_0x0964:
            r0 = move-exception
            r15 = r37
            goto L_0x07ba
        L_0x0969:
            r8 = r15
            r13 = r26
            r15 = r37
            r18 = r3
            r17 = r9
            r23 = r10
            r14 = r13
            r19 = r16
            r3 = r21
            r21 = r22
            r13 = r11
            r16 = r12
            r22 = r20
            r20 = r2
            r2 = r24
            r24 = r8
            goto L_0x09a5
        L_0x0987:
            r25 = r2
            r21 = r3
            r22 = r14
            r15 = r20
            r27 = r8
            r17 = r9
            r20 = r17
            r28 = r10
            r16 = r12
            r18 = r19
            r2 = r24
            r14 = 0
            r24 = 0
            r21 = r56
            r19 = r13
            r13 = r11
        L_0x09a5:
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r8 = r13.jsonRpcInteractor     // Catch:{ Exception -> 0x07b9 }
            com.reown.sign.engine.use_case.responses.a r11 = new com.reown.sign.engine.use_case.responses.a     // Catch:{ Exception -> 0x07b9 }
            r11.<init>(r13)     // Catch:{ Exception -> 0x07b9 }
            r26 = 0
            r10 = 0
            r12 = 2
            r9 = r23
            r29 = r13
            r13 = r26
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.subscribe$default(r8, r9, r10, r11, r12, r13)     // Catch:{ Exception -> 0x07b9 }
            com.reown.foundation.util.Logger r8 = r29.logger     // Catch:{ Exception -> 0x07b9 }
            com.reown.foundation.common.model.Topic r9 = r18.getTopic()     // Catch:{ Exception -> 0x07b9 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x07b9 }
            r10.<init>()     // Catch:{ Exception -> 0x07b9 }
            java.lang.String r11 = "Received session authenticate response - emitting rpc result: "
            r10.append(r11)     // Catch:{ Exception -> 0x07b9 }
            r10.append(r9)     // Catch:{ Exception -> 0x07b9 }
            java.lang.String r9 = r10.toString()     // Catch:{ Exception -> 0x07b9 }
            r8.log((java.lang.String) r9)     // Catch:{ Exception -> 0x07b9 }
            kotlinx.coroutines.flow.MutableSharedFlow r8 = r29._events     // Catch:{ Exception -> 0x07b9 }
            com.reown.sign.engine.model.EngineDO$SessionAuthenticateResponse$Result r9 = new com.reown.sign.engine.model.EngineDO$SessionAuthenticateResponse$Result     // Catch:{ Exception -> 0x07b9 }
            r10 = r7
            com.reown.android.internal.common.JsonRpcResponse$JsonRpcResult r10 = (com.reown.android.internal.common.JsonRpcResponse.JsonRpcResult) r10     // Catch:{ Exception -> 0x07b9 }
            long r10 = r10.getId()     // Catch:{ Exception -> 0x07b9 }
            java.util.List r12 = r20.getCacaos()     // Catch:{ Exception -> 0x07b9 }
            if (r24 == 0) goto L_0x09ef
            com.reown.sign.engine.model.EngineDO$Session r13 = com.reown.sign.engine.model.mapper.EngineMapperKt.toEngineDO((com.reown.sign.common.model.vo.sequence.SessionVO) r24)     // Catch:{ Exception -> 0x07b9 }
            goto L_0x09f0
        L_0x09ef:
            r13 = 0
        L_0x09f0:
            r9.<init>(r10, r12, r13)     // Catch:{ Exception -> 0x07b9 }
            java.lang.Object r10 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r5)     // Catch:{ Exception -> 0x07b9 }
            r1.L$0 = r10     // Catch:{ Exception -> 0x07b9 }
            java.lang.Object r4 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)     // Catch:{ Exception -> 0x07b9 }
            r1.L$1 = r4     // Catch:{ Exception -> 0x07b9 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r3)     // Catch:{ Exception -> 0x07b9 }
            r1.L$2 = r3     // Catch:{ Exception -> 0x07b9 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)     // Catch:{ Exception -> 0x07b9 }
            r1.L$3 = r3     // Catch:{ Exception -> 0x07b9 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r20)     // Catch:{ Exception -> 0x07b9 }
            r1.L$4 = r3     // Catch:{ Exception -> 0x07b9 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r17)     // Catch:{ Exception -> 0x07b9 }
            r1.L$5 = r3     // Catch:{ Exception -> 0x07b9 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r19)     // Catch:{ Exception -> 0x07b9 }
            r1.L$6 = r3     // Catch:{ Exception -> 0x07b9 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r22)     // Catch:{ Exception -> 0x07b9 }
            r1.L$7 = r3     // Catch:{ Exception -> 0x07b9 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r21)     // Catch:{ Exception -> 0x07b9 }
            r1.L$8 = r3     // Catch:{ Exception -> 0x07b9 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r23)     // Catch:{ Exception -> 0x07b9 }
            r1.L$9 = r3     // Catch:{ Exception -> 0x07b9 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)     // Catch:{ Exception -> 0x07b9 }
            r1.L$10 = r2     // Catch:{ Exception -> 0x07b9 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r16)     // Catch:{ Exception -> 0x07b9 }
            r1.L$11 = r2     // Catch:{ Exception -> 0x07b9 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r6)     // Catch:{ Exception -> 0x07b9 }
            r1.L$12 = r2     // Catch:{ Exception -> 0x07b9 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r25)     // Catch:{ Exception -> 0x07b9 }
            r1.L$13 = r2     // Catch:{ Exception -> 0x07b9 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r28)     // Catch:{ Exception -> 0x07b9 }
            r1.L$14 = r2     // Catch:{ Exception -> 0x07b9 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r27)     // Catch:{ Exception -> 0x07b9 }
            r1.L$15 = r2     // Catch:{ Exception -> 0x07b9 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r24)     // Catch:{ Exception -> 0x07b9 }
            r1.L$16 = r2     // Catch:{ Exception -> 0x07b9 }
            r2 = 0
            r1.L$17 = r2     // Catch:{ Exception -> 0x07b9 }
            r1.L$18 = r2     // Catch:{ Exception -> 0x07b9 }
            r1.L$19 = r2     // Catch:{ Exception -> 0x07b9 }
            r1.L$20 = r2     // Catch:{ Exception -> 0x07b9 }
            r1.L$21 = r2     // Catch:{ Exception -> 0x07b9 }
            r1.I$0 = r14     // Catch:{ Exception -> 0x07b9 }
            r2 = 9
            r1.label = r2     // Catch:{ Exception -> 0x07b9 }
            java.lang.Object r1 = r8.emit(r9, r1)     // Catch:{ Exception -> 0x07b9 }
            if (r1 != r15) goto L_0x0ae2
            return r15
        L_0x0a71:
            r0 = move-exception
            r15 = r8
            goto L_0x07ba
        L_0x0a75:
            r0 = move-exception
            r15 = r6
            goto L_0x07ba
        L_0x0a79:
            r15 = r6
            kotlin.NoWhenBranchMatchedException r2 = new kotlin.NoWhenBranchMatchedException     // Catch:{ Exception -> 0x07b9 }
            r2.<init>()     // Catch:{ Exception -> 0x07b9 }
            throw r2     // Catch:{ Exception -> 0x07b9 }
        L_0x0a80:
            com.reown.sign.engine.use_case.responses.OnSessionAuthenticateResponseUseCase r3 = r1.this$0
            com.reown.foundation.util.Logger r3 = r3.logger
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r6 = "Received session authenticate response - exception:"
            r4.<init>(r6)
            r4.append(r2)
            java.lang.String r4 = r4.toString()
            r3.error((java.lang.String) r4)
            com.reown.sign.engine.use_case.responses.OnSessionAuthenticateResponseUseCase r3 = r1.this$0
            kotlinx.coroutines.flow.MutableSharedFlow r3 = r3._events
            com.reown.android.internal.common.model.SDKError r4 = new com.reown.android.internal.common.model.SDKError
            r4.<init>(r2)
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r5)
            r1.L$0 = r5
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)
            r1.L$1 = r2
            r2 = 0
            r1.L$2 = r2
            r1.L$3 = r2
            r1.L$4 = r2
            r1.L$5 = r2
            r1.L$6 = r2
            r1.L$7 = r2
            r1.L$8 = r2
            r1.L$9 = r2
            r1.L$10 = r2
            r1.L$11 = r2
            r1.L$12 = r2
            r1.L$13 = r2
            r1.L$14 = r2
            r1.L$15 = r2
            r1.L$16 = r2
            r1.L$17 = r2
            r1.L$18 = r2
            r1.L$19 = r2
            r1.L$20 = r2
            r1.L$21 = r2
            r2 = 10
            r1.label = r2
            java.lang.Object r1 = r3.emit(r4, r1)
            if (r1 != r15) goto L_0x0ae2
            return r15
        L_0x0ae2:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.engine.use_case.responses.OnSessionAuthenticateResponseUseCase$invoke$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((OnSessionAuthenticateResponseUseCase$invoke$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
