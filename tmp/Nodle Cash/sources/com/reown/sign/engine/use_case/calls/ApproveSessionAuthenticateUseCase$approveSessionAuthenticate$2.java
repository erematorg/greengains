package com.reown.sign.engine.use_case.calls;

import com.reown.android.internal.common.WalletConnectScopeKt;
import com.reown.android.internal.common.signing.cacao.Cacao;
import com.reown.android.pulse.model.Trace;
import com.reown.foundation.common.model.Topic;
import com.reown.foundation.util.Logger;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

@SourceDebugExtension({"SMAP\nApproveSessionAuthenticateUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ApproveSessionAuthenticateUseCase.kt\ncom/reown/sign/engine/use_case/calls/ApproveSessionAuthenticateUseCase$approveSessionAuthenticate$2\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,230:1\n1#2:231\n1740#3,3:232\n1740#3,3:235\n1563#3:238\n1634#3,3:239\n1869#3,2:242\n*S KotlinDebug\n*F\n+ 1 ApproveSessionAuthenticateUseCase.kt\ncom/reown/sign/engine/use_case/calls/ApproveSessionAuthenticateUseCase$approveSessionAuthenticate$2\n*L\n88#1:232,3\n93#1:235,3\n114#1:238\n114#1:239,3\n116#1:242,2\n*E\n"})
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.calls.ApproveSessionAuthenticateUseCase$approveSessionAuthenticate$2", f = "ApproveSessionAuthenticateUseCase.kt", i = {0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 7, 7, 7}, l = {72, 80, 89, 94, 108, 180, 221, 222}, m = "invokeSuspend", n = {"$this$supervisorScope", "trace", "jsonRpcHistoryEntry", "$this$supervisorScope", "trace", "jsonRpcHistoryEntry", "it", "$i$a$-let-ApproveSessionAuthenticateUseCase$approveSessionAuthenticate$2$3", "$this$supervisorScope", "trace", "jsonRpcHistoryEntry", "sessionAuthenticateParams", "chains", "$this$supervisorScope", "trace", "jsonRpcHistoryEntry", "sessionAuthenticateParams", "chains", "$this$supervisorScope", "trace", "jsonRpcHistoryEntry", "sessionAuthenticateParams", "chains", "receiverPublicKey", "receiverMetadata", "senderPublicKey", "symmetricKey", "responseTopic", "sessionTopic", "irnParams", "$this$supervisorScope", "trace", "jsonRpcHistoryEntry", "sessionAuthenticateParams", "chains", "receiverPublicKey", "receiverMetadata", "senderPublicKey", "symmetricKey", "responseTopic", "sessionTopic", "irnParams", "addresses", "accounts", "namespace", "methods", "events", "responseParams", "response", "$this$supervisorScope", "trace", "e", "$this$supervisorScope", "trace", "e"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$4", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$15", "L$16", "L$17", "L$18", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2"})
public final class ApproveSessionAuthenticateUseCase$approveSessionAuthenticate$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ List<Cacao> $cacaos;
    final /* synthetic */ long $id;
    final /* synthetic */ Function1<Throwable, Unit> $onFailure;
    final /* synthetic */ Function0<Unit> $onSuccess;
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
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    int label;
    final /* synthetic */ ApproveSessionAuthenticateUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ApproveSessionAuthenticateUseCase$approveSessionAuthenticate$2(ApproveSessionAuthenticateUseCase approveSessionAuthenticateUseCase, long j2, Function1<? super Throwable, Unit> function1, List<Cacao> list, Function0<Unit> function0, Continuation<? super ApproveSessionAuthenticateUseCase$approveSessionAuthenticate$2> continuation) {
        super(2, continuation);
        this.this$0 = approveSessionAuthenticateUseCase;
        this.$id = j2;
        this.$onFailure = function1;
        this.$cacaos = list;
        this.$onSuccess = function0;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$14(List list, ApproveSessionAuthenticateUseCase approveSessionAuthenticateUseCase, Topic topic, Topic topic2) {
        list.add(Trace.SessionAuthenticate.SUBSCRIBE_AUTHENTICATED_SESSION_TOPIC_SUCCESS);
        Logger access$getLogger$p = approveSessionAuthenticateUseCase.logger;
        access$getLogger$p.log("Subscribed Session Authenticate on topic: " + topic);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$16(Function1 function1, ApproveSessionAuthenticateUseCase approveSessionAuthenticateUseCase, List list, Topic topic, Topic topic2, Throwable th) {
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new ApproveSessionAuthenticateUseCase$approveSessionAuthenticate$2$11$1(approveSessionAuthenticateUseCase, list, topic, (Continuation<? super ApproveSessionAuthenticateUseCase$approveSessionAuthenticate$2$11$1>) null), 3, (Object) null);
        Logger access$getLogger$p = approveSessionAuthenticateUseCase.logger;
        access$getLogger$p.log("Subscribing Session Authenticate error on topic: " + topic2 + ", " + th);
        function1.invoke(th);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$19(List list, Function0 function0, ApproveSessionAuthenticateUseCase approveSessionAuthenticateUseCase, Topic topic, long j2) {
        list.add(Trace.SessionAuthenticate.AUTHENTICATED_SESSION_APPROVE_PUBLISH_SUCCESS);
        Logger access$getLogger$p = approveSessionAuthenticateUseCase.logger;
        access$getLogger$p.log("Session Authenticate Approve Responded on topic: " + topic);
        function0.invoke();
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new ApproveSessionAuthenticateUseCase$approveSessionAuthenticate$2$13$2(approveSessionAuthenticateUseCase, j2, (Continuation<? super ApproveSessionAuthenticateUseCase$approveSessionAuthenticate$2$13$2>) null), 3, (Object) null);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$23(CoroutineScope coroutineScope, ApproveSessionAuthenticateUseCase approveSessionAuthenticateUseCase, Topic topic, Function1 function1, List list, Topic topic2, Throwable th) {
        Object obj;
        try {
            Result.Companion companion = Result.Companion;
            approveSessionAuthenticateUseCase.crypto.removeKeys(topic.getValue());
            obj = Result.m8979constructorimpl(Unit.INSTANCE);
        } catch (Throwable th2) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.m8979constructorimpl(ResultKt.createFailure(th2));
        }
        Throwable r6 = Result.m8982exceptionOrNullimpl(obj);
        if (r6 != null) {
            approveSessionAuthenticateUseCase.logger.error(r6);
        }
        approveSessionAuthenticateUseCase.sessionStorageRepository.deleteSession(topic);
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new ApproveSessionAuthenticateUseCase$approveSessionAuthenticate$2$14$3(approveSessionAuthenticateUseCase, list, topic2, (Continuation<? super ApproveSessionAuthenticateUseCase$approveSessionAuthenticate$2$14$3>) null), 3, (Object) null);
        Logger access$getLogger$p = approveSessionAuthenticateUseCase.logger;
        access$getLogger$p.error("Error Responding Session Authenticate on topic: " + topic2 + ", error: " + th);
        function1.invoke(th);
        return Unit.INSTANCE;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ApproveSessionAuthenticateUseCase$approveSessionAuthenticate$2 approveSessionAuthenticateUseCase$approveSessionAuthenticate$2 = new ApproveSessionAuthenticateUseCase$approveSessionAuthenticate$2(this.this$0, this.$id, this.$onFailure, this.$cacaos, this.$onSuccess, continuation);
        approveSessionAuthenticateUseCase$approveSessionAuthenticate$2.L$0 = obj;
        return approveSessionAuthenticateUseCase$approveSessionAuthenticate$2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v8, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v20, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v39, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v42, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v86, resolved type: com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionAuthenticateParams} */
    /* JADX WARNING: type inference failed for: r2v73, types: [java.util.List] */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x03ac, code lost:
        throw new java.lang.Exception("Only eip155 (EVM) is supported");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:?, code lost:
        r0 = kotlin.Unit.INSTANCE;
        r1.this$0.logger.error("Invalid Cacao for Session Authenticate");
        r1.$onFailure.invoke(new java.lang.Throwable("Signature verification failed Session Authenticate, please try again"));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x0501, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        r0 = kotlin.Unit.INSTANCE;
        r1.this$0.logger.error(new com.reown.sign.common.exceptions.MissingSessionAuthenticateRequest().getMessage());
        r1.$onFailure.invoke(new com.reown.sign.common.exceptions.MissingSessionAuthenticateRequest());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x01a9, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:?, code lost:
        r7 = kotlin.Unit.INSTANCE;
        r0 = r0.logger;
        r7 = r5.getTopic();
        r8 = r5.getId();
        r0.error("Session Authenticate Request Expired: " + r7 + ", id: " + r8);
        r7 = r5.getId();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0250, code lost:
        throw new com.reown.android.internal.common.exception.RequestExpiredException("This request has expired, id: " + r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0292, code lost:
        if (((java.util.Collection) r5).isEmpty() != false) goto L_0x0313;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0312, code lost:
        throw new java.lang.Exception("Chains are not CAIP-2 compliant");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x0326, code lost:
        if (((java.util.Collection) r5).isEmpty() != false) goto L_0x03ad;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:218:0x08d6  */
    /* JADX WARNING: Removed duplicated region for block: B:223:0x094c  */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:107:0x03a5=Splitter:B:107:0x03a5, B:126:0x04e6=Splitter:B:126:0x04e6, B:41:0x0189=Splitter:B:41:0x0189, B:85:0x030b=Splitter:B:85:0x030b} */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r52) {
        /*
            r51 = this;
            r1 = r51
            r0 = 0
            java.lang.String r2 = "This request has expired, id: "
            java.lang.String r3 = "Session Authenticate Request Expired: "
            java.lang.Object r4 = r1.L$0
            kotlinx.coroutines.CoroutineScope r4 = (kotlinx.coroutines.CoroutineScope) r4
            java.lang.Object r12 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r5 = r1.label
            switch(r5) {
                case 0: goto L_0x0114;
                case 1: goto L_0x0106;
                case 2: goto L_0x00ee;
                case 3: goto L_0x00d8;
                case 4: goto L_0x00c2;
                case 5: goto L_0x008b;
                case 6: goto L_0x0038;
                case 7: goto L_0x0029;
                case 8: goto L_0x001c;
                default: goto L_0x0014;
            }
        L_0x0014:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x001c:
            java.lang.Object r0 = r1.L$2
            java.lang.Exception r0 = (java.lang.Exception) r0
            java.lang.Object r2 = r1.L$1
            java.util.List r2 = (java.util.List) r2
            kotlin.ResultKt.throwOnFailure(r52)
            goto L_0x09c3
        L_0x0029:
            java.lang.Object r0 = r1.L$2
            java.lang.Exception r0 = (java.lang.Exception) r0
            java.lang.Object r2 = r1.L$1
            java.util.List r2 = (java.util.List) r2
            kotlin.ResultKt.throwOnFailure(r52)
            r3 = r2
            r2 = r12
            goto L_0x0948
        L_0x0038:
            java.lang.Object r0 = r1.L$18
            com.reown.android.internal.common.JsonRpcResponse r0 = (com.reown.android.internal.common.JsonRpcResponse) r0
            java.lang.Object r0 = r1.L$17
            com.reown.android.internal.common.model.params.CoreSignParams$SessionAuthenticateApproveParams r0 = (com.reown.android.internal.common.model.params.CoreSignParams.SessionAuthenticateApproveParams) r0
            java.lang.Object r0 = r1.L$16
            java.util.List r0 = (java.util.List) r0
            java.lang.Object r0 = r1.L$15
            java.util.List r0 = (java.util.List) r0
            java.lang.Object r0 = r1.L$14
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Object r0 = r1.L$13
            java.util.List r0 = (java.util.List) r0
            java.lang.Object r0 = r1.L$12
            java.util.List r0 = (java.util.List) r0
            java.lang.Object r0 = r1.L$11
            com.reown.android.internal.common.model.IrnParams r0 = (com.reown.android.internal.common.model.IrnParams) r0
            java.lang.Object r0 = r1.L$10
            com.reown.foundation.common.model.Topic r0 = (com.reown.foundation.common.model.Topic) r0
            java.lang.Object r0 = r1.L$9
            com.reown.foundation.common.model.Topic r0 = (com.reown.foundation.common.model.Topic) r0
            java.lang.Object r0 = r1.L$8
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Object r0 = r1.L$7
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Object r0 = r1.L$6
            com.reown.android.internal.common.model.AppMetaData r0 = (com.reown.android.internal.common.model.AppMetaData) r0
            java.lang.Object r0 = r1.L$5
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Object r0 = r1.L$4
            java.util.List r0 = (java.util.List) r0
            java.lang.Object r0 = r1.L$3
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionAuthenticateParams r0 = (com.reown.sign.common.model.vo.clientsync.session.params.SignParams.SessionAuthenticateParams) r0
            java.lang.Object r0 = r1.L$2
            com.reown.sign.common.model.Request r0 = (com.reown.sign.common.model.Request) r0
            java.lang.Object r0 = r1.L$1
            r2 = r0
            java.util.List r2 = (java.util.List) r2
            kotlin.ResultKt.throwOnFailure(r52)     // Catch:{ Exception -> 0x0086 }
            goto L_0x09c8
        L_0x0086:
            r0 = move-exception
            r11 = r2
            r2 = r12
            goto L_0x0822
        L_0x008b:
            java.lang.Object r0 = r1.L$11
            com.reown.android.internal.common.model.IrnParams r0 = (com.reown.android.internal.common.model.IrnParams) r0
            java.lang.Object r0 = r1.L$10
            com.reown.foundation.common.model.Topic r0 = (com.reown.foundation.common.model.Topic) r0
            java.lang.Object r0 = r1.L$9
            com.reown.foundation.common.model.Topic r0 = (com.reown.foundation.common.model.Topic) r0
            java.lang.Object r0 = r1.L$8
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Object r0 = r1.L$7
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Object r0 = r1.L$6
            com.reown.android.internal.common.model.AppMetaData r0 = (com.reown.android.internal.common.model.AppMetaData) r0
            java.lang.Object r0 = r1.L$5
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Object r0 = r1.L$4
            java.util.List r0 = (java.util.List) r0
            java.lang.Object r0 = r1.L$3
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionAuthenticateParams r0 = (com.reown.sign.common.model.vo.clientsync.session.params.SignParams.SessionAuthenticateParams) r0
            java.lang.Object r0 = r1.L$2
            com.reown.sign.common.model.Request r0 = (com.reown.sign.common.model.Request) r0
            java.lang.Object r0 = r1.L$1
            r2 = r0
            java.util.List r2 = (java.util.List) r2
            kotlin.ResultKt.throwOnFailure(r52)     // Catch:{ Exception -> 0x00bd }
            goto L_0x04e6
        L_0x00bd:
            r0 = move-exception
            r3 = r2
        L_0x00bf:
            r2 = r12
            goto L_0x08bb
        L_0x00c2:
            java.lang.Object r0 = r1.L$4
            java.util.List r0 = (java.util.List) r0
            java.lang.Object r0 = r1.L$3
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionAuthenticateParams r0 = (com.reown.sign.common.model.vo.clientsync.session.params.SignParams.SessionAuthenticateParams) r0
            java.lang.Object r0 = r1.L$2
            com.reown.sign.common.model.Request r0 = (com.reown.sign.common.model.Request) r0
            java.lang.Object r0 = r1.L$1
            r2 = r0
            java.util.List r2 = (java.util.List) r2
            kotlin.ResultKt.throwOnFailure(r52)     // Catch:{ Exception -> 0x00bd }
            goto L_0x03a5
        L_0x00d8:
            java.lang.Object r0 = r1.L$4
            java.util.List r0 = (java.util.List) r0
            java.lang.Object r0 = r1.L$3
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionAuthenticateParams r0 = (com.reown.sign.common.model.vo.clientsync.session.params.SignParams.SessionAuthenticateParams) r0
            java.lang.Object r0 = r1.L$2
            com.reown.sign.common.model.Request r0 = (com.reown.sign.common.model.Request) r0
            java.lang.Object r0 = r1.L$1
            r2 = r0
            java.util.List r2 = (java.util.List) r2
            kotlin.ResultKt.throwOnFailure(r52)     // Catch:{ Exception -> 0x00bd }
            goto L_0x030b
        L_0x00ee:
            java.lang.Object r0 = r1.L$4
            com.reown.android.internal.common.model.Expiry r0 = (com.reown.android.internal.common.model.Expiry) r0
            java.lang.Object r0 = r1.L$3
            com.reown.sign.engine.use_case.calls.ApproveSessionAuthenticateUseCase r0 = (com.reown.sign.engine.use_case.calls.ApproveSessionAuthenticateUseCase) r0
            java.lang.Object r5 = r1.L$2
            com.reown.sign.common.model.Request r5 = (com.reown.sign.common.model.Request) r5
            java.lang.Object r6 = r1.L$1
            java.util.List r6 = (java.util.List) r6
            kotlin.ResultKt.throwOnFailure(r52)     // Catch:{ Exception -> 0x0103 }
            goto L_0x0216
        L_0x0103:
            r0 = move-exception
            r3 = r6
            goto L_0x00bf
        L_0x0106:
            java.lang.Object r0 = r1.L$2
            com.reown.sign.common.model.Request r0 = (com.reown.sign.common.model.Request) r0
            java.lang.Object r0 = r1.L$1
            r2 = r0
            java.util.List r2 = (java.util.List) r2
            kotlin.ResultKt.throwOnFailure(r52)     // Catch:{ Exception -> 0x00bd }
            goto L_0x0189
        L_0x0114:
            kotlin.ResultKt.throwOnFailure(r52)
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            java.lang.String r5 = "authenticated_session_approve_started"
            r11.add(r5)
            com.reown.sign.engine.use_case.calls.ApproveSessionAuthenticateUseCase r6 = r1.this$0
            com.reown.foundation.util.Logger r6 = r6.logger
            r6.log((java.lang.String) r5)
            com.reown.sign.engine.use_case.calls.ApproveSessionAuthenticateUseCase r5 = r1.this$0     // Catch:{ Exception -> 0x08b7 }
            com.reown.sign.json_rpc.domain.GetPendingSessionAuthenticateRequest r5 = r5.getPendingSessionAuthenticateRequest     // Catch:{ Exception -> 0x08b7 }
            long r6 = r1.$id     // Catch:{ Exception -> 0x08b7 }
            com.reown.sign.common.model.Request r10 = r5.invoke$sign_release(r6)     // Catch:{ Exception -> 0x08b7 }
            r9 = 1
            if (r10 != 0) goto L_0x01ae
            com.reown.sign.engine.use_case.calls.ApproveSessionAuthenticateUseCase r0 = r1.this$0     // Catch:{ Exception -> 0x01aa }
            com.reown.android.pulse.domain.InsertTelemetryEventUseCase r0 = r0.insertTelemetryEventUseCase     // Catch:{ Exception -> 0x01aa }
            com.reown.android.pulse.model.properties.Props r2 = new com.reown.android.pulse.model.properties.Props     // Catch:{ Exception -> 0x01aa }
            java.lang.String r3 = "MISSING_SESSION_AUTH_REQUEST"
            com.reown.android.pulse.model.properties.Properties r5 = new com.reown.android.pulse.model.properties.Properties     // Catch:{ Exception -> 0x01aa }
            r25 = 0
            r26 = 0
            r27 = 4031(0xfbf, float:5.649E-42)
            r28 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r22 = 0
            r23 = 0
            r24 = 0
            r14 = r5
            r21 = r11
            r14.<init>(r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28)     // Catch:{ Exception -> 0x01aa }
            r19 = 0
            r15 = 0
            r18 = 1
            r14 = r2
            r16 = r3
            r17 = r5
            r14.<init>(r15, r16, r17, r18, r19)     // Catch:{ Exception -> 0x01aa }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)     // Catch:{ Exception -> 0x01aa }
            r1.L$0 = r3     // Catch:{ Exception -> 0x01aa }
            r1.L$1 = r11     // Catch:{ Exception -> 0x01aa }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r10)     // Catch:{ Exception -> 0x01aa }
            r1.L$2 = r3     // Catch:{ Exception -> 0x01aa }
            r1.label = r9     // Catch:{ Exception -> 0x01aa }
            java.lang.Object r0 = r0.invoke(r2, r1)     // Catch:{ Exception -> 0x01aa }
            if (r0 != r12) goto L_0x0188
            return r12
        L_0x0188:
            r2 = r11
        L_0x0189:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x00bd }
            com.reown.sign.engine.use_case.calls.ApproveSessionAuthenticateUseCase r0 = r1.this$0     // Catch:{ Exception -> 0x00bd }
            com.reown.foundation.util.Logger r0 = r0.logger     // Catch:{ Exception -> 0x00bd }
            com.reown.sign.common.exceptions.MissingSessionAuthenticateRequest r3 = new com.reown.sign.common.exceptions.MissingSessionAuthenticateRequest     // Catch:{ Exception -> 0x00bd }
            r3.<init>()     // Catch:{ Exception -> 0x00bd }
            java.lang.String r3 = r3.getMessage()     // Catch:{ Exception -> 0x00bd }
            r0.error((java.lang.String) r3)     // Catch:{ Exception -> 0x00bd }
            kotlin.jvm.functions.Function1<java.lang.Throwable, kotlin.Unit> r0 = r1.$onFailure     // Catch:{ Exception -> 0x00bd }
            com.reown.sign.common.exceptions.MissingSessionAuthenticateRequest r3 = new com.reown.sign.common.exceptions.MissingSessionAuthenticateRequest     // Catch:{ Exception -> 0x00bd }
            r3.<init>()     // Catch:{ Exception -> 0x00bd }
            r0.invoke(r3)     // Catch:{ Exception -> 0x00bd }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x00bd }
            return r0
        L_0x01aa:
            r0 = move-exception
            r3 = r11
            goto L_0x00bf
        L_0x01ae:
            com.reown.android.internal.common.model.Expiry r5 = r10.getExpiry()     // Catch:{ Exception -> 0x08b7 }
            if (r5 == 0) goto L_0x0253
            com.reown.sign.engine.use_case.calls.ApproveSessionAuthenticateUseCase r6 = r1.this$0     // Catch:{ Exception -> 0x01aa }
            com.reown.android.internal.utils.CoreValidator r7 = com.reown.android.internal.utils.CoreValidator.INSTANCE     // Catch:{ Exception -> 0x01aa }
            boolean r7 = r7.isExpired(r5)     // Catch:{ Exception -> 0x01aa }
            if (r7 == 0) goto L_0x0251
            com.reown.android.pulse.domain.InsertTelemetryEventUseCase r7 = r6.insertTelemetryEventUseCase     // Catch:{ Exception -> 0x01aa }
            com.reown.android.pulse.model.properties.Props r8 = new com.reown.android.pulse.model.properties.Props     // Catch:{ Exception -> 0x01aa }
            java.lang.String r9 = "SESSION_AUTH_REQUEST_EXPIRED"
            com.reown.android.pulse.model.properties.Properties r29 = new com.reown.android.pulse.model.properties.Properties     // Catch:{ Exception -> 0x01aa }
            r25 = 0
            r26 = 0
            r27 = 4031(0xfbf, float:5.649E-42)
            r28 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r22 = 0
            r23 = 0
            r24 = 0
            r14 = r29
            r21 = r11
            r14.<init>(r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28)     // Catch:{ Exception -> 0x01aa }
            r19 = 0
            r15 = 0
            r18 = 1
            r14 = r8
            r16 = r9
            r17 = r29
            r14.<init>(r15, r16, r17, r18, r19)     // Catch:{ Exception -> 0x01aa }
            java.lang.Object r9 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)     // Catch:{ Exception -> 0x01aa }
            r1.L$0 = r9     // Catch:{ Exception -> 0x01aa }
            r1.L$1 = r11     // Catch:{ Exception -> 0x01aa }
            r1.L$2 = r10     // Catch:{ Exception -> 0x01aa }
            r1.L$3 = r6     // Catch:{ Exception -> 0x01aa }
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r5)     // Catch:{ Exception -> 0x01aa }
            r1.L$4 = r5     // Catch:{ Exception -> 0x01aa }
            r1.I$0 = r0     // Catch:{ Exception -> 0x01aa }
            r0 = 2
            r1.label = r0     // Catch:{ Exception -> 0x01aa }
            java.lang.Object r0 = r7.invoke(r8, r1)     // Catch:{ Exception -> 0x01aa }
            if (r0 != r12) goto L_0x0213
            return r12
        L_0x0213:
            r0 = r6
            r5 = r10
            r6 = r11
        L_0x0216:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x0103 }
            com.reown.foundation.util.Logger r0 = r0.logger     // Catch:{ Exception -> 0x0103 }
            com.reown.foundation.common.model.Topic r7 = r5.getTopic()     // Catch:{ Exception -> 0x0103 }
            long r8 = r5.getId()     // Catch:{ Exception -> 0x0103 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0103 }
            r10.<init>(r3)     // Catch:{ Exception -> 0x0103 }
            r10.append(r7)     // Catch:{ Exception -> 0x0103 }
            java.lang.String r3 = ", id: "
            r10.append(r3)     // Catch:{ Exception -> 0x0103 }
            r10.append(r8)     // Catch:{ Exception -> 0x0103 }
            java.lang.String r3 = r10.toString()     // Catch:{ Exception -> 0x0103 }
            r0.error((java.lang.String) r3)     // Catch:{ Exception -> 0x0103 }
            com.reown.android.internal.common.exception.RequestExpiredException r0 = new com.reown.android.internal.common.exception.RequestExpiredException     // Catch:{ Exception -> 0x0103 }
            long r7 = r5.getId()     // Catch:{ Exception -> 0x0103 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0103 }
            r3.<init>(r2)     // Catch:{ Exception -> 0x0103 }
            r3.append(r7)     // Catch:{ Exception -> 0x0103 }
            java.lang.String r2 = r3.toString()     // Catch:{ Exception -> 0x0103 }
            r0.<init>(r2)     // Catch:{ Exception -> 0x0103 }
            throw r0     // Catch:{ Exception -> 0x0103 }
        L_0x0251:
            kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x01aa }
        L_0x0253:
            java.lang.String r2 = "authenticated_session_not_expired"
            r11.add(r2)     // Catch:{ Exception -> 0x08b7 }
            java.lang.Object r2 = r10.getParams()     // Catch:{ Exception -> 0x08b7 }
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionAuthenticateParams r2 = (com.reown.sign.common.model.vo.clientsync.session.params.SignParams.SessionAuthenticateParams) r2     // Catch:{ Exception -> 0x08b7 }
            java.util.List<com.reown.android.internal.common.signing.cacao.Cacao> r3 = r1.$cacaos     // Catch:{ Exception -> 0x08b7 }
            java.lang.Object r3 = kotlin.collections.CollectionsKt.first(r3)     // Catch:{ Exception -> 0x08b7 }
            com.reown.android.internal.common.signing.cacao.Cacao r3 = (com.reown.android.internal.common.signing.cacao.Cacao) r3     // Catch:{ Exception -> 0x08b7 }
            com.reown.android.internal.common.signing.cacao.Cacao$Payload r3 = r3.getPayload()     // Catch:{ Exception -> 0x08b7 }
            java.util.List r3 = r3.getResources()     // Catch:{ Exception -> 0x08b7 }
            java.util.List r3 = com.reown.android.internal.common.signing.cacao.UtilsKt.getChains(r3)     // Catch:{ Exception -> 0x08b7 }
            java.util.Collection r3 = (java.util.Collection) r3     // Catch:{ Exception -> 0x08b7 }
            boolean r5 = r3.isEmpty()     // Catch:{ Exception -> 0x08b7 }
            if (r5 == 0) goto L_0x0282
            com.reown.sign.common.model.vo.clientsync.common.PayloadParams r3 = r2.getAuthPayload()     // Catch:{ Exception -> 0x01aa }
            java.util.List r3 = r3.getChains()     // Catch:{ Exception -> 0x01aa }
        L_0x0282:
            java.util.List r3 = (java.util.List) r3     // Catch:{ Exception -> 0x08b7 }
            r5 = r3
            java.lang.Iterable r5 = (java.lang.Iterable) r5     // Catch:{ Exception -> 0x08b7 }
            boolean r6 = r5 instanceof java.util.Collection     // Catch:{ Exception -> 0x08b7 }
            if (r6 == 0) goto L_0x0296
            r6 = r5
            java.util.Collection r6 = (java.util.Collection) r6     // Catch:{ Exception -> 0x01aa }
            boolean r6 = r6.isEmpty()     // Catch:{ Exception -> 0x01aa }
            if (r6 == 0) goto L_0x0296
            goto L_0x0313
        L_0x0296:
            java.util.Iterator r5 = r5.iterator()     // Catch:{ Exception -> 0x08b7 }
        L_0x029a:
            boolean r6 = r5.hasNext()     // Catch:{ Exception -> 0x08b7 }
            if (r6 == 0) goto L_0x0313
            java.lang.Object r6 = r5.next()     // Catch:{ Exception -> 0x01aa }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ Exception -> 0x01aa }
            com.reown.android.internal.utils.CoreValidator r7 = com.reown.android.internal.utils.CoreValidator.INSTANCE     // Catch:{ Exception -> 0x01aa }
            boolean r6 = r7.isChainIdCAIP2Compliant(r6)     // Catch:{ Exception -> 0x01aa }
            if (r6 != 0) goto L_0x029a
            com.reown.sign.engine.use_case.calls.ApproveSessionAuthenticateUseCase r0 = r1.this$0     // Catch:{ Exception -> 0x01aa }
            com.reown.android.pulse.domain.InsertTelemetryEventUseCase r0 = r0.insertTelemetryEventUseCase     // Catch:{ Exception -> 0x01aa }
            com.reown.android.pulse.model.properties.Props r5 = new com.reown.android.pulse.model.properties.Props     // Catch:{ Exception -> 0x01aa }
            java.lang.String r6 = "CHAINS_CAIP2_COMPLIANT_FAILURE"
            com.reown.android.pulse.model.properties.Properties r7 = new com.reown.android.pulse.model.properties.Properties     // Catch:{ Exception -> 0x01aa }
            r25 = 0
            r26 = 0
            r27 = 4031(0xfbf, float:5.649E-42)
            r28 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r22 = 0
            r23 = 0
            r24 = 0
            r14 = r7
            r21 = r11
            r14.<init>(r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28)     // Catch:{ Exception -> 0x01aa }
            r19 = 0
            r15 = 0
            r18 = 1
            r14 = r5
            r16 = r6
            r17 = r7
            r14.<init>(r15, r16, r17, r18, r19)     // Catch:{ Exception -> 0x01aa }
            java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)     // Catch:{ Exception -> 0x01aa }
            r1.L$0 = r6     // Catch:{ Exception -> 0x01aa }
            r1.L$1 = r11     // Catch:{ Exception -> 0x01aa }
            java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r10)     // Catch:{ Exception -> 0x01aa }
            r1.L$2 = r6     // Catch:{ Exception -> 0x01aa }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)     // Catch:{ Exception -> 0x01aa }
            r1.L$3 = r2     // Catch:{ Exception -> 0x01aa }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r3)     // Catch:{ Exception -> 0x01aa }
            r1.L$4 = r2     // Catch:{ Exception -> 0x01aa }
            r2 = 3
            r1.label = r2     // Catch:{ Exception -> 0x01aa }
            java.lang.Object r0 = r0.invoke(r5, r1)     // Catch:{ Exception -> 0x01aa }
            if (r0 != r12) goto L_0x030a
            return r12
        L_0x030a:
            r2 = r11
        L_0x030b:
            java.lang.Exception r0 = new java.lang.Exception     // Catch:{ Exception -> 0x00bd }
            java.lang.String r3 = "Chains are not CAIP-2 compliant"
            r0.<init>(r3)     // Catch:{ Exception -> 0x00bd }
            throw r0     // Catch:{ Exception -> 0x00bd }
        L_0x0313:
            java.lang.String r5 = "chains_caip2_compliant"
            r11.add(r5)     // Catch:{ Exception -> 0x08b7 }
            r5 = r3
            java.lang.Iterable r5 = (java.lang.Iterable) r5     // Catch:{ Exception -> 0x08b7 }
            boolean r6 = r5 instanceof java.util.Collection     // Catch:{ Exception -> 0x08b7 }
            if (r6 == 0) goto L_0x032a
            r6 = r5
            java.util.Collection r6 = (java.util.Collection) r6     // Catch:{ Exception -> 0x01aa }
            boolean r6 = r6.isEmpty()     // Catch:{ Exception -> 0x01aa }
            if (r6 == 0) goto L_0x032a
            goto L_0x03ad
        L_0x032a:
            java.util.Iterator r5 = r5.iterator()     // Catch:{ Exception -> 0x08b7 }
        L_0x032e:
            boolean r6 = r5.hasNext()     // Catch:{ Exception -> 0x08b7 }
            if (r6 == 0) goto L_0x03ad
            java.lang.Object r6 = r5.next()     // Catch:{ Exception -> 0x01aa }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ Exception -> 0x01aa }
            com.reown.sign.common.validator.SignValidator r7 = com.reown.sign.common.validator.SignValidator.INSTANCE     // Catch:{ Exception -> 0x01aa }
            java.lang.String r6 = r7.getNamespaceKeyFromChainId$sign_release(r6)     // Catch:{ Exception -> 0x01aa }
            java.lang.String r7 = "eip155"
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r7)     // Catch:{ Exception -> 0x01aa }
            if (r6 != 0) goto L_0x032e
            com.reown.sign.engine.use_case.calls.ApproveSessionAuthenticateUseCase r0 = r1.this$0     // Catch:{ Exception -> 0x01aa }
            com.reown.android.pulse.domain.InsertTelemetryEventUseCase r0 = r0.insertTelemetryEventUseCase     // Catch:{ Exception -> 0x01aa }
            com.reown.android.pulse.model.properties.Props r5 = new com.reown.android.pulse.model.properties.Props     // Catch:{ Exception -> 0x01aa }
            java.lang.String r6 = "CHAINS_EVM_COMPLIANT_FAILURE"
            com.reown.android.pulse.model.properties.Properties r7 = new com.reown.android.pulse.model.properties.Properties     // Catch:{ Exception -> 0x01aa }
            r25 = 0
            r26 = 0
            r27 = 4031(0xfbf, float:5.649E-42)
            r28 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r22 = 0
            r23 = 0
            r24 = 0
            r14 = r7
            r21 = r11
            r14.<init>(r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28)     // Catch:{ Exception -> 0x01aa }
            r19 = 0
            r15 = 0
            r18 = 1
            r14 = r5
            r16 = r6
            r17 = r7
            r14.<init>(r15, r16, r17, r18, r19)     // Catch:{ Exception -> 0x01aa }
            java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)     // Catch:{ Exception -> 0x01aa }
            r1.L$0 = r6     // Catch:{ Exception -> 0x01aa }
            r1.L$1 = r11     // Catch:{ Exception -> 0x01aa }
            java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r10)     // Catch:{ Exception -> 0x01aa }
            r1.L$2 = r6     // Catch:{ Exception -> 0x01aa }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)     // Catch:{ Exception -> 0x01aa }
            r1.L$3 = r2     // Catch:{ Exception -> 0x01aa }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r3)     // Catch:{ Exception -> 0x01aa }
            r1.L$4 = r2     // Catch:{ Exception -> 0x01aa }
            r2 = 4
            r1.label = r2     // Catch:{ Exception -> 0x01aa }
            java.lang.Object r0 = r0.invoke(r5, r1)     // Catch:{ Exception -> 0x01aa }
            if (r0 != r12) goto L_0x03a4
            return r12
        L_0x03a4:
            r2 = r11
        L_0x03a5:
            java.lang.Exception r0 = new java.lang.Exception     // Catch:{ Exception -> 0x00bd }
            java.lang.String r3 = "Only eip155 (EVM) is supported"
            r0.<init>(r3)     // Catch:{ Exception -> 0x00bd }
            throw r0     // Catch:{ Exception -> 0x00bd }
        L_0x03ad:
            java.lang.String r5 = "chains_evm_compliant"
            r11.add(r5)     // Catch:{ Exception -> 0x08b7 }
            com.reown.sign.common.model.vo.clientsync.common.Requester r5 = r2.getRequester()     // Catch:{ Exception -> 0x08b7 }
            java.lang.String r5 = r5.getPublicKey()     // Catch:{ Exception -> 0x08b7 }
            java.lang.String r8 = com.reown.foundation.common.model.PublicKey.m8856constructorimpl(r5)     // Catch:{ Exception -> 0x08b7 }
            com.reown.sign.common.model.vo.clientsync.common.Requester r5 = r2.getRequester()     // Catch:{ Exception -> 0x08b7 }
            com.reown.android.internal.common.model.AppMetaData r7 = r5.getMetadata()     // Catch:{ Exception -> 0x08b7 }
            com.reown.sign.engine.use_case.calls.ApproveSessionAuthenticateUseCase r5 = r1.this$0     // Catch:{ Exception -> 0x08b7 }
            com.reown.android.internal.common.crypto.kmr.KeyManagementRepository r5 = r5.crypto     // Catch:{ Exception -> 0x08b7 }
            java.lang.String r6 = r5.m8724generateAndStoreX25519KeyPairuN_RPug()     // Catch:{ Exception -> 0x08b7 }
            com.reown.sign.engine.use_case.calls.ApproveSessionAuthenticateUseCase r5 = r1.this$0     // Catch:{ Exception -> 0x08b7 }
            com.reown.android.internal.common.crypto.kmr.KeyManagementRepository r5 = r5.crypto     // Catch:{ Exception -> 0x08b7 }
            java.lang.String r29 = r5.m8725generateSymmetricKeyFromKeyAgreementrMsFr_I(r6, r8)     // Catch:{ Exception -> 0x08b7 }
            com.reown.sign.engine.use_case.calls.ApproveSessionAuthenticateUseCase r5 = r1.this$0     // Catch:{ Exception -> 0x08b7 }
            com.reown.android.internal.common.crypto.kmr.KeyManagementRepository r5 = r5.crypto     // Catch:{ Exception -> 0x08b7 }
            com.reown.foundation.common.model.PublicKey r14 = com.reown.foundation.common.model.PublicKey.m8855boximpl(r8)     // Catch:{ Exception -> 0x08b7 }
            com.reown.foundation.common.model.Topic r5 = r5.getTopicFromKey(r14)     // Catch:{ Exception -> 0x08b7 }
            com.reown.sign.engine.use_case.calls.ApproveSessionAuthenticateUseCase r14 = r1.this$0     // Catch:{ Exception -> 0x08b7 }
            com.reown.android.internal.common.crypto.kmr.KeyManagementRepository r14 = r14.crypto     // Catch:{ Exception -> 0x08b7 }
            com.reown.android.internal.common.model.SymmetricKey r15 = com.reown.android.internal.common.model.SymmetricKey.m8777boximpl(r29)     // Catch:{ Exception -> 0x08b7 }
            com.reown.foundation.common.model.Topic r15 = r14.getTopicFromKey(r15)     // Catch:{ Exception -> 0x08b7 }
            java.lang.String r14 = "create_authenticated_session_topic"
            r11.add(r14)     // Catch:{ Exception -> 0x08b7 }
            com.reown.android.internal.common.model.IrnParams r30 = new com.reown.android.internal.common.model.IrnParams     // Catch:{ Exception -> 0x08b7 }
            com.reown.android.internal.common.model.Tags r17 = com.reown.android.internal.common.model.Tags.SESSION_AUTHENTICATE_RESPONSE_APPROVE     // Catch:{ Exception -> 0x08b7 }
            com.reown.foundation.common.model.Ttl r14 = new com.reown.foundation.common.model.Ttl     // Catch:{ Exception -> 0x08b7 }
            r31 = r10
            long r9 = com.reown.android.internal.utils.Time.getDayInSeconds()     // Catch:{ Exception -> 0x08b7 }
            r14.<init>(r9)     // Catch:{ Exception -> 0x08b7 }
            long r9 = r1.$id     // Catch:{ Exception -> 0x08b7 }
            java.lang.Long r19 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r9)     // Catch:{ Exception -> 0x08b7 }
            r23 = 0
            r24 = 0
            r25 = 248(0xf8, float:3.48E-43)
            r26 = 0
            r20 = 0
            r21 = 0
            r22 = 0
            r16 = r30
            r18 = r14
            r16.<init>(r17, r18, r19, r20, r21, r22, r23, r24, r25, r26)     // Catch:{ Exception -> 0x08b7 }
            java.util.List<com.reown.android.internal.common.signing.cacao.Cacao> r9 = r1.$cacaos     // Catch:{ Exception -> 0x08b7 }
            java.lang.Iterable r9 = (java.lang.Iterable) r9     // Catch:{ Exception -> 0x08b7 }
            com.reown.sign.engine.use_case.calls.ApproveSessionAuthenticateUseCase r10 = r1.this$0     // Catch:{ Exception -> 0x08b7 }
            java.util.Iterator r9 = r9.iterator()     // Catch:{ Exception -> 0x08b7 }
        L_0x042f:
            boolean r14 = r9.hasNext()     // Catch:{ Exception -> 0x08b7 }
            if (r14 == 0) goto L_0x0449
            java.lang.Object r14 = r9.next()     // Catch:{ Exception -> 0x01aa }
            r13 = r14
            com.reown.android.internal.common.signing.cacao.Cacao r13 = (com.reown.android.internal.common.signing.cacao.Cacao) r13     // Catch:{ Exception -> 0x01aa }
            com.reown.android.internal.common.signing.cacao.CacaoVerifier r0 = r10.cacaoVerifier     // Catch:{ Exception -> 0x01aa }
            boolean r0 = r0.verify(r13)     // Catch:{ Exception -> 0x01aa }
            if (r0 != 0) goto L_0x0447
            goto L_0x044a
        L_0x0447:
            r0 = 0
            goto L_0x042f
        L_0x0449:
            r14 = 0
        L_0x044a:
            if (r14 == 0) goto L_0x0502
            com.reown.sign.engine.use_case.calls.ApproveSessionAuthenticateUseCase r0 = r1.this$0     // Catch:{ Exception -> 0x01aa }
            com.reown.android.pulse.domain.InsertTelemetryEventUseCase r0 = r0.insertTelemetryEventUseCase     // Catch:{ Exception -> 0x01aa }
            com.reown.android.pulse.model.properties.Props r9 = new com.reown.android.pulse.model.properties.Props     // Catch:{ Exception -> 0x01aa }
            java.lang.String r10 = "INVALID_CACAO"
            com.reown.android.pulse.model.properties.Properties r13 = new com.reown.android.pulse.model.properties.Properties     // Catch:{ Exception -> 0x01aa }
            java.lang.String r22 = r15.getValue()     // Catch:{ Exception -> 0x01aa }
            r25 = 0
            r26 = 0
            r27 = 3903(0xf3f, float:5.469E-42)
            r28 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r21 = 0
            r23 = 0
            r24 = 0
            r14 = r13
            r32 = r15
            r15 = r16
            r16 = r17
            r17 = r18
            r18 = r19
            r19 = r20
            r20 = r21
            r21 = r11
            r14.<init>(r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28)     // Catch:{ Exception -> 0x01aa }
            r21 = 0
            r17 = 0
            r20 = 1
            r16 = r9
            r18 = r10
            r19 = r13
            r16.<init>(r17, r18, r19, r20, r21)     // Catch:{ Exception -> 0x01aa }
            java.lang.Object r10 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)     // Catch:{ Exception -> 0x01aa }
            r1.L$0 = r10     // Catch:{ Exception -> 0x01aa }
            r1.L$1 = r11     // Catch:{ Exception -> 0x01aa }
            java.lang.Object r10 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r31)     // Catch:{ Exception -> 0x01aa }
            r1.L$2 = r10     // Catch:{ Exception -> 0x01aa }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)     // Catch:{ Exception -> 0x01aa }
            r1.L$3 = r2     // Catch:{ Exception -> 0x01aa }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r3)     // Catch:{ Exception -> 0x01aa }
            r1.L$4 = r2     // Catch:{ Exception -> 0x01aa }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)     // Catch:{ Exception -> 0x01aa }
            r1.L$5 = r2     // Catch:{ Exception -> 0x01aa }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)     // Catch:{ Exception -> 0x01aa }
            r1.L$6 = r2     // Catch:{ Exception -> 0x01aa }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r6)     // Catch:{ Exception -> 0x01aa }
            r1.L$7 = r2     // Catch:{ Exception -> 0x01aa }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r29)     // Catch:{ Exception -> 0x01aa }
            r1.L$8 = r2     // Catch:{ Exception -> 0x01aa }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r5)     // Catch:{ Exception -> 0x01aa }
            r1.L$9 = r2     // Catch:{ Exception -> 0x01aa }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r32)     // Catch:{ Exception -> 0x01aa }
            r1.L$10 = r2     // Catch:{ Exception -> 0x01aa }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r30)     // Catch:{ Exception -> 0x01aa }
            r1.L$11 = r2     // Catch:{ Exception -> 0x01aa }
            r2 = 5
            r1.label = r2     // Catch:{ Exception -> 0x01aa }
            java.lang.Object r0 = r0.invoke(r9, r1)     // Catch:{ Exception -> 0x01aa }
            if (r0 != r12) goto L_0x04e5
            return r12
        L_0x04e5:
            r2 = r11
        L_0x04e6:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x00bd }
            com.reown.sign.engine.use_case.calls.ApproveSessionAuthenticateUseCase r0 = r1.this$0     // Catch:{ Exception -> 0x00bd }
            com.reown.foundation.util.Logger r0 = r0.logger     // Catch:{ Exception -> 0x00bd }
            java.lang.String r3 = "Invalid Cacao for Session Authenticate"
            r0.error((java.lang.String) r3)     // Catch:{ Exception -> 0x00bd }
            kotlin.jvm.functions.Function1<java.lang.Throwable, kotlin.Unit> r0 = r1.$onFailure     // Catch:{ Exception -> 0x00bd }
            java.lang.Throwable r3 = new java.lang.Throwable     // Catch:{ Exception -> 0x00bd }
            java.lang.String r5 = "Signature verification failed Session Authenticate, please try again"
            r3.<init>(r5)     // Catch:{ Exception -> 0x00bd }
            r0.invoke(r3)     // Catch:{ Exception -> 0x00bd }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x00bd }
            return r0
        L_0x0502:
            r32 = r15
            java.lang.String r0 = "cacaos_verified"
            r11.add(r0)     // Catch:{ Exception -> 0x08b7 }
            java.util.List<com.reown.android.internal.common.signing.cacao.Cacao> r0 = r1.$cacaos     // Catch:{ Exception -> 0x08b7 }
            java.lang.Iterable r0 = (java.lang.Iterable) r0     // Catch:{ Exception -> 0x08b7 }
            java.util.ArrayList r9 = new java.util.ArrayList     // Catch:{ Exception -> 0x08b7 }
            int r10 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r0, 10)     // Catch:{ Exception -> 0x08b7 }
            r9.<init>(r10)     // Catch:{ Exception -> 0x08b7 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ Exception -> 0x08b7 }
        L_0x051a:
            boolean r10 = r0.hasNext()     // Catch:{ Exception -> 0x08b7 }
            if (r10 == 0) goto L_0x053b
            java.lang.Object r10 = r0.next()     // Catch:{ Exception -> 0x01aa }
            com.reown.android.internal.common.signing.cacao.Cacao r10 = (com.reown.android.internal.common.signing.cacao.Cacao) r10     // Catch:{ Exception -> 0x01aa }
            com.reown.android.internal.common.signing.cacao.Issuer r13 = new com.reown.android.internal.common.signing.cacao.Issuer     // Catch:{ Exception -> 0x01aa }
            com.reown.android.internal.common.signing.cacao.Cacao$Payload r10 = r10.getPayload()     // Catch:{ Exception -> 0x01aa }
            java.lang.String r10 = r10.getIss()     // Catch:{ Exception -> 0x01aa }
            r13.<init>(r10)     // Catch:{ Exception -> 0x01aa }
            java.lang.String r10 = r13.getAddress()     // Catch:{ Exception -> 0x01aa }
            r9.add(r10)     // Catch:{ Exception -> 0x01aa }
            goto L_0x051a
        L_0x053b:
            java.util.List r0 = kotlin.collections.CollectionsKt.distinct(r9)     // Catch:{ Exception -> 0x08b7 }
            java.util.ArrayList r13 = new java.util.ArrayList     // Catch:{ Exception -> 0x08b7 }
            r13.<init>()     // Catch:{ Exception -> 0x08b7 }
            r9 = r3
            java.lang.Iterable r9 = (java.lang.Iterable) r9     // Catch:{ Exception -> 0x08b7 }
            java.util.Iterator r9 = r9.iterator()     // Catch:{ Exception -> 0x08b7 }
        L_0x054b:
            boolean r10 = r9.hasNext()     // Catch:{ Exception -> 0x08b7 }
            if (r10 == 0) goto L_0x058a
            java.lang.Object r10 = r9.next()     // Catch:{ Exception -> 0x01aa }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ Exception -> 0x01aa }
            r14 = r0
            java.lang.Iterable r14 = (java.lang.Iterable) r14     // Catch:{ Exception -> 0x01aa }
            java.util.Iterator r14 = r14.iterator()     // Catch:{ Exception -> 0x01aa }
        L_0x055e:
            boolean r15 = r14.hasNext()     // Catch:{ Exception -> 0x01aa }
            if (r15 == 0) goto L_0x054b
            java.lang.Object r15 = r14.next()     // Catch:{ Exception -> 0x01aa }
            java.lang.String r15 = (java.lang.String) r15     // Catch:{ Exception -> 0x01aa }
            r16 = r9
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01aa }
            r9.<init>()     // Catch:{ Exception -> 0x01aa }
            r9.append(r10)     // Catch:{ Exception -> 0x01aa }
            r17 = r10
            java.lang.String r10 = ":"
            r9.append(r10)     // Catch:{ Exception -> 0x01aa }
            r9.append(r15)     // Catch:{ Exception -> 0x01aa }
            java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x01aa }
            r13.add(r9)     // Catch:{ Exception -> 0x01aa }
            r9 = r16
            r10 = r17
            goto L_0x055e
        L_0x058a:
            com.reown.android.internal.common.signing.cacao.Issuer r9 = new com.reown.android.internal.common.signing.cacao.Issuer     // Catch:{ Exception -> 0x08b7 }
            java.util.List<com.reown.android.internal.common.signing.cacao.Cacao> r10 = r1.$cacaos     // Catch:{ Exception -> 0x08b7 }
            java.lang.Object r10 = kotlin.collections.CollectionsKt.first(r10)     // Catch:{ Exception -> 0x08b7 }
            com.reown.android.internal.common.signing.cacao.Cacao r10 = (com.reown.android.internal.common.signing.cacao.Cacao) r10     // Catch:{ Exception -> 0x08b7 }
            com.reown.android.internal.common.signing.cacao.Cacao$Payload r10 = r10.getPayload()     // Catch:{ Exception -> 0x08b7 }
            java.lang.String r10 = r10.getIss()     // Catch:{ Exception -> 0x08b7 }
            r9.<init>(r10)     // Catch:{ Exception -> 0x08b7 }
            java.lang.String r10 = r9.getNamespace()     // Catch:{ Exception -> 0x08b7 }
            java.util.List<com.reown.android.internal.common.signing.cacao.Cacao> r9 = r1.$cacaos     // Catch:{ Exception -> 0x08b7 }
            java.lang.Object r9 = kotlin.collections.CollectionsKt.first(r9)     // Catch:{ Exception -> 0x08b7 }
            com.reown.android.internal.common.signing.cacao.Cacao r9 = (com.reown.android.internal.common.signing.cacao.Cacao) r9     // Catch:{ Exception -> 0x08b7 }
            com.reown.android.internal.common.signing.cacao.Cacao$Payload r9 = r9.getPayload()     // Catch:{ Exception -> 0x08b7 }
            java.util.List r9 = r9.getMethods()     // Catch:{ Exception -> 0x08b7 }
            java.lang.String r14 = "chainChanged"
            java.lang.String r15 = "accountsChanged"
            java.lang.String[] r14 = new java.lang.String[]{r14, r15}     // Catch:{ Exception -> 0x08b7 }
            java.util.List r15 = kotlin.collections.CollectionsKt.listOf(r14)     // Catch:{ Exception -> 0x08b7 }
            r14 = r9
            java.util.Collection r14 = (java.util.Collection) r14     // Catch:{ Exception -> 0x08b7 }
            boolean r14 = r14.isEmpty()     // Catch:{ Exception -> 0x08b7 }
            if (r14 != 0) goto L_0x0673
            com.reown.sign.engine.use_case.calls.ApproveSessionAuthenticateUseCase r14 = r1.this$0     // Catch:{ Exception -> 0x066f }
            com.reown.foundation.util.Logger r14 = r14.logger     // Catch:{ Exception -> 0x066f }
            r26 = r12
            java.lang.String r12 = "Creating authenticated session"
            r14.log((java.lang.String) r12)     // Catch:{ Exception -> 0x0611 }
            com.reown.android.internal.common.model.Namespace$Proposal r12 = new com.reown.android.internal.common.model.Namespace$Proposal     // Catch:{ Exception -> 0x0611 }
            r12.<init>(r9, r3, r15)     // Catch:{ Exception -> 0x0611 }
            kotlin.Pair r12 = kotlin.TuplesKt.to(r10, r12)     // Catch:{ Exception -> 0x0611 }
            java.util.Map r21 = kotlin.collections.MapsKt.mapOf(r12)     // Catch:{ Exception -> 0x0611 }
            com.reown.android.internal.common.model.Namespace$Session r12 = new com.reown.android.internal.common.model.Namespace$Session     // Catch:{ Exception -> 0x0611 }
            r12.<init>(r3, r13, r9, r15)     // Catch:{ Exception -> 0x0611 }
            kotlin.Pair r12 = kotlin.TuplesKt.to(r10, r12)     // Catch:{ Exception -> 0x0611 }
            java.util.Map r22 = kotlin.collections.MapsKt.mapOf(r12)     // Catch:{ Exception -> 0x0611 }
            java.lang.Boolean r12 = r2.getLinkMode()     // Catch:{ Exception -> 0x0611 }
            r27 = 1
            java.lang.Boolean r14 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r27)     // Catch:{ Exception -> 0x0611 }
            boolean r12 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r14)     // Catch:{ Exception -> 0x0611 }
            if (r12 == 0) goto L_0x0617
            java.lang.String r12 = r2.getAppLink()     // Catch:{ Exception -> 0x0611 }
            if (r12 == 0) goto L_0x0617
            int r12 = r12.length()     // Catch:{ Exception -> 0x0611 }
            if (r12 != 0) goto L_0x060c
            goto L_0x0617
        L_0x060c:
            com.reown.android.internal.common.model.TransportType r12 = com.reown.android.internal.common.model.TransportType.LINK_MODE     // Catch:{ Exception -> 0x0611 }
        L_0x060e:
            r24 = r12
            goto L_0x061a
        L_0x0611:
            r0 = move-exception
        L_0x0612:
            r3 = r11
            r2 = r26
            goto L_0x08bb
        L_0x0617:
            com.reown.android.internal.common.model.TransportType r12 = com.reown.android.internal.common.model.TransportType.RELAY     // Catch:{ Exception -> 0x0611 }
            goto L_0x060e
        L_0x061a:
            com.reown.sign.common.model.vo.sequence.SessionVO$Companion r14 = com.reown.sign.common.model.vo.sequence.SessionVO.Companion     // Catch:{ Exception -> 0x0611 }
            com.reown.sign.engine.use_case.calls.ApproveSessionAuthenticateUseCase r12 = r1.this$0     // Catch:{ Exception -> 0x0611 }
            com.reown.android.internal.common.model.AppMetaData r19 = r12.selfAppMetaData     // Catch:{ Exception -> 0x0611 }
            com.reown.foundation.common.model.Topic r12 = r31.getTopic()     // Catch:{ Exception -> 0x0611 }
            java.lang.String r23 = r12.getValue()     // Catch:{ Exception -> 0x0611 }
            r12 = r15
            r15 = r32
            r16 = r8
            r17 = r7
            r18 = r6
            r20 = r6
            com.reown.sign.common.model.vo.sequence.SessionVO r14 = r14.m8880createAuthenticatedSessiontF0nsiE$sign_release(r15, r16, r17, r18, r19, r20, r21, r22, r23, r24)     // Catch:{ Exception -> 0x0611 }
            com.reown.sign.engine.use_case.calls.ApproveSessionAuthenticateUseCase r15 = r1.this$0     // Catch:{ Exception -> 0x0611 }
            com.reown.android.internal.common.storage.metadata.MetadataStorageRepositoryInterface r15 = r15.metadataStorageRepository     // Catch:{ Exception -> 0x0611 }
            r52 = r8
            com.reown.sign.engine.use_case.calls.ApproveSessionAuthenticateUseCase r8 = r1.this$0     // Catch:{ Exception -> 0x0611 }
            com.reown.android.internal.common.model.AppMetaData r8 = r8.selfAppMetaData     // Catch:{ Exception -> 0x0611 }
            r16 = r9
            com.reown.android.internal.common.model.AppMetaDataType r9 = com.reown.android.internal.common.model.AppMetaDataType.SELF     // Catch:{ Exception -> 0x0611 }
            r21 = r12
            r12 = r32
            r15.insertOrAbortMetadata(r12, r8, r9)     // Catch:{ Exception -> 0x0611 }
            com.reown.sign.engine.use_case.calls.ApproveSessionAuthenticateUseCase r8 = r1.this$0     // Catch:{ Exception -> 0x0611 }
            com.reown.android.internal.common.storage.metadata.MetadataStorageRepositoryInterface r8 = r8.metadataStorageRepository     // Catch:{ Exception -> 0x0611 }
            com.reown.android.internal.common.model.AppMetaDataType r9 = com.reown.android.internal.common.model.AppMetaDataType.PEER     // Catch:{ Exception -> 0x0611 }
            r8.insertOrAbortMetadata(r12, r7, r9)     // Catch:{ Exception -> 0x0611 }
            com.reown.sign.engine.use_case.calls.ApproveSessionAuthenticateUseCase r8 = r1.this$0     // Catch:{ Exception -> 0x0611 }
            com.reown.sign.storage.sequence.SessionStorageRepository r8 = r8.sessionStorageRepository     // Catch:{ Exception -> 0x0611 }
            r15 = r10
            long r9 = r1.$id     // Catch:{ Exception -> 0x0611 }
            r8.insertSession(r14, r9)     // Catch:{ Exception -> 0x0611 }
            java.lang.String r8 = "store_authenticated_session"
            r11.add(r8)     // Catch:{ Exception -> 0x0611 }
            goto L_0x0680
        L_0x066f:
            r0 = move-exception
            r26 = r12
            goto L_0x0612
        L_0x0673:
            r52 = r8
            r16 = r9
            r26 = r12
            r21 = r15
            r12 = r32
            r27 = 1
            r15 = r10
        L_0x0680:
            com.reown.android.internal.common.model.params.CoreSignParams$SessionAuthenticateApproveParams r14 = new com.reown.android.internal.common.model.params.CoreSignParams$SessionAuthenticateApproveParams     // Catch:{ Exception -> 0x08b2 }
            com.reown.android.internal.common.model.Participant r8 = new com.reown.android.internal.common.model.Participant     // Catch:{ Exception -> 0x08b2 }
            com.reown.sign.engine.use_case.calls.ApproveSessionAuthenticateUseCase r9 = r1.this$0     // Catch:{ Exception -> 0x08b2 }
            com.reown.android.internal.common.model.AppMetaData r9 = r9.selfAppMetaData     // Catch:{ Exception -> 0x08b2 }
            r8.<init>(r6, r9)     // Catch:{ Exception -> 0x08b2 }
            java.util.List<com.reown.android.internal.common.signing.cacao.Cacao> r9 = r1.$cacaos     // Catch:{ Exception -> 0x08b2 }
            r14.<init>(r8, r9)     // Catch:{ Exception -> 0x08b2 }
            com.reown.android.internal.common.JsonRpcResponse$JsonRpcResult r22 = new com.reown.android.internal.common.JsonRpcResponse$JsonRpcResult     // Catch:{ Exception -> 0x08b2 }
            long r8 = r1.$id     // Catch:{ Exception -> 0x08b2 }
            r38 = 0
            r35 = 0
            r37 = 2
            r32 = r22
            r33 = r8
            r36 = r14
            r32.<init>(r33, r35, r36, r37, r38)     // Catch:{ Exception -> 0x08b2 }
            com.reown.sign.engine.use_case.calls.ApproveSessionAuthenticateUseCase r8 = r1.this$0     // Catch:{ Exception -> 0x08b2 }
            com.reown.android.internal.common.crypto.kmr.KeyManagementRepository r8 = r8.crypto     // Catch:{ Exception -> 0x08b2 }
            com.reown.android.internal.common.model.SymmetricKey r9 = com.reown.android.internal.common.model.SymmetricKey.m8777boximpl(r29)     // Catch:{ Exception -> 0x08b2 }
            java.lang.String r10 = r12.getValue()     // Catch:{ Exception -> 0x08b2 }
            r8.setKey(r9, r10)     // Catch:{ Exception -> 0x08b2 }
            java.lang.String r8 = "subscribing_authenticated_session_topic"
            r11.add(r8)     // Catch:{ Exception -> 0x08b2 }
            com.reown.sign.engine.use_case.calls.ApproveSessionAuthenticateUseCase r8 = r1.this$0     // Catch:{ Exception -> 0x08b2 }
            com.reown.foundation.util.Logger r8 = r8.logger     // Catch:{ Exception -> 0x08b2 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x08b2 }
            r9.<init>()     // Catch:{ Exception -> 0x08b2 }
            java.lang.String r10 = "Subscribing Session Authenticate on topic: "
            r9.append(r10)     // Catch:{ Exception -> 0x08b2 }
            r9.append(r5)     // Catch:{ Exception -> 0x08b2 }
            java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x08b2 }
            r8.log((java.lang.String) r9)     // Catch:{ Exception -> 0x08b2 }
            com.reown.sign.engine.use_case.calls.ApproveSessionAuthenticateUseCase r8 = r1.this$0     // Catch:{ Exception -> 0x08b2 }
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r10 = r8.jsonRpcInteractor     // Catch:{ Exception -> 0x08b2 }
            com.reown.sign.engine.use_case.calls.ApproveSessionAuthenticateUseCase r8 = r1.this$0     // Catch:{ Exception -> 0x08b2 }
            com.reown.sign.engine.use_case.calls.a r9 = new com.reown.sign.engine.use_case.calls.a     // Catch:{ Exception -> 0x08b2 }
            r17 = r6
            r6 = 0
            r9.<init>((java.lang.Object) r11, (int) r6, (java.lang.Object) r8, (java.lang.Object) r5)     // Catch:{ Exception -> 0x08b2 }
            kotlin.jvm.functions.Function1<java.lang.Throwable, kotlin.Unit> r6 = r1.$onFailure     // Catch:{ Exception -> 0x08b2 }
            r18 = r15
            com.reown.sign.engine.use_case.calls.b r15 = new com.reown.sign.engine.use_case.calls.b     // Catch:{ Exception -> 0x08b2 }
            r23 = r5
            r5 = r15
            r24 = r14
            r14 = r17
            r25 = r7
            r7 = r8
            r28 = r13
            r13 = r52
            r8 = r11
            r52 = r0
            r0 = r9
            r27 = r16
            r9 = r12
            r33 = r3
            r3 = r10
            r32 = r18
            r10 = r23
            r5.<init>((kotlin.jvm.functions.Function1) r6, (com.reown.sign.engine.use_case.calls.ApproveSessionAuthenticateUseCase) r7, (java.util.ArrayList) r8, (com.reown.foundation.common.model.Topic) r9, (com.reown.foundation.common.model.Topic) r10)     // Catch:{ Exception -> 0x08b2 }
            r3.subscribe(r12, r0, r15)     // Catch:{ Exception -> 0x08b2 }
            com.reown.android.internal.common.model.TransportType r0 = r31.getTransportType()     // Catch:{ Exception -> 0x08b2 }
            com.reown.android.internal.common.model.TransportType r3 = com.reown.android.internal.common.model.TransportType.LINK_MODE     // Catch:{ Exception -> 0x08b2 }
            if (r0 != r3) goto L_0x0840
            com.reown.android.internal.common.model.Redirect r0 = r25.getRedirect()     // Catch:{ Exception -> 0x082d }
            if (r0 == 0) goto L_0x0840
            boolean r0 = r0.getLinkMode()     // Catch:{ Exception -> 0x082d }
            r3 = 1
            if (r0 != r3) goto L_0x0840
            com.reown.android.internal.common.model.Redirect r0 = r25.getRedirect()     // Catch:{ Exception -> 0x082d }
            if (r0 == 0) goto L_0x072d
            java.lang.String r0 = r0.getUniversal()     // Catch:{ Exception -> 0x0611 }
            goto L_0x072e
        L_0x072d:
            r0 = 0
        L_0x072e:
            if (r0 == 0) goto L_0x0736
            int r0 = r0.length()     // Catch:{ Exception -> 0x082d }
            if (r0 != 0) goto L_0x073a
        L_0x0736:
            r2 = r26
            goto L_0x0831
        L_0x073a:
            com.reown.sign.engine.use_case.calls.ApproveSessionAuthenticateUseCase r0 = r1.this$0     // Catch:{ Exception -> 0x081f }
            com.reown.android.internal.common.json_rpc.domain.link_mode.LinkModeJsonRpcInteractorInterface r15 = r0.linkModeJsonRpcInteractor     // Catch:{ Exception -> 0x081f }
            com.reown.android.internal.common.model.Redirect r0 = r25.getRedirect()     // Catch:{ Exception -> 0x081f }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)     // Catch:{ Exception -> 0x081f }
            java.lang.String r18 = r0.getUniversal()     // Catch:{ Exception -> 0x081f }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r18)     // Catch:{ Exception -> 0x081f }
            com.reown.android.internal.common.model.Participants r0 = new com.reown.android.internal.common.model.Participants     // Catch:{ Exception -> 0x081f }
            r3 = 0
            r0.<init>(r14, r13, r3)     // Catch:{ Exception -> 0x081f }
            com.reown.android.internal.common.model.EnvelopeType r20 = com.reown.android.internal.common.model.EnvelopeType.ONE     // Catch:{ Exception -> 0x081f }
            r16 = r23
            r17 = r22
            r19 = r0
            r15.triggerResponse(r16, r17, r18, r19, r20)     // Catch:{ Exception -> 0x081f }
            com.reown.sign.engine.use_case.calls.ApproveSessionAuthenticateUseCase r0 = r1.this$0     // Catch:{ Exception -> 0x081f }
            com.reown.android.pulse.domain.InsertEventUseCase r0 = r0.insertEventUseCase     // Catch:{ Exception -> 0x081f }
            com.reown.android.pulse.model.properties.Props r3 = new com.reown.android.pulse.model.properties.Props     // Catch:{ Exception -> 0x081f }
            java.lang.String r5 = "SUCCESS"
            com.reown.android.internal.common.model.Tags r6 = com.reown.android.internal.common.model.Tags.SESSION_AUTHENTICATE_LINK_MODE_RESPONSE_APPROVE     // Catch:{ Exception -> 0x081f }
            int r6 = r6.getId()     // Catch:{ Exception -> 0x081f }
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ Exception -> 0x081f }
            com.reown.sign.engine.use_case.calls.ApproveSessionAuthenticateUseCase r7 = r1.this$0     // Catch:{ Exception -> 0x081f }
            java.lang.String r44 = r7.clientId     // Catch:{ Exception -> 0x081f }
            com.reown.android.pulse.model.Direction r7 = com.reown.android.pulse.model.Direction.SENT     // Catch:{ Exception -> 0x081f }
            java.lang.String r45 = r7.getState()     // Catch:{ Exception -> 0x081f }
            com.reown.android.pulse.model.properties.Properties r7 = new com.reown.android.pulse.model.properties.Properties     // Catch:{ Exception -> 0x081f }
            long r8 = r1.$id     // Catch:{ Exception -> 0x081f }
            java.lang.Long r43 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r8)     // Catch:{ Exception -> 0x081f }
            r42 = 0
            r46 = 0
            r47 = 2303(0x8ff, float:3.227E-42)
            r48 = 0
            r35 = 0
            r36 = 0
            r37 = 0
            r38 = 0
            r39 = 0
            r40 = 0
            r41 = 0
            r34 = r7
            r34.<init>(r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48)     // Catch:{ Exception -> 0x081f }
            r3.<init>(r5, r6, r7)     // Catch:{ Exception -> 0x081f }
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)     // Catch:{ Exception -> 0x081f }
            r1.L$0 = r5     // Catch:{ Exception -> 0x081f }
            r1.L$1 = r11     // Catch:{ Exception -> 0x081f }
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r31)     // Catch:{ Exception -> 0x081f }
            r1.L$2 = r5     // Catch:{ Exception -> 0x081f }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)     // Catch:{ Exception -> 0x081f }
            r1.L$3 = r2     // Catch:{ Exception -> 0x081f }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r33)     // Catch:{ Exception -> 0x081f }
            r1.L$4 = r2     // Catch:{ Exception -> 0x081f }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r13)     // Catch:{ Exception -> 0x081f }
            r1.L$5 = r2     // Catch:{ Exception -> 0x081f }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r25)     // Catch:{ Exception -> 0x081f }
            r1.L$6 = r2     // Catch:{ Exception -> 0x081f }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r14)     // Catch:{ Exception -> 0x081f }
            r1.L$7 = r2     // Catch:{ Exception -> 0x081f }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r29)     // Catch:{ Exception -> 0x081f }
            r1.L$8 = r2     // Catch:{ Exception -> 0x081f }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r23)     // Catch:{ Exception -> 0x081f }
            r1.L$9 = r2     // Catch:{ Exception -> 0x081f }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r12)     // Catch:{ Exception -> 0x081f }
            r1.L$10 = r2     // Catch:{ Exception -> 0x081f }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r30)     // Catch:{ Exception -> 0x081f }
            r1.L$11 = r2     // Catch:{ Exception -> 0x081f }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r52)     // Catch:{ Exception -> 0x081f }
            r1.L$12 = r2     // Catch:{ Exception -> 0x081f }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r28)     // Catch:{ Exception -> 0x081f }
            r1.L$13 = r2     // Catch:{ Exception -> 0x081f }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r32)     // Catch:{ Exception -> 0x081f }
            r1.L$14 = r2     // Catch:{ Exception -> 0x081f }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r27)     // Catch:{ Exception -> 0x081f }
            r1.L$15 = r2     // Catch:{ Exception -> 0x081f }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r21)     // Catch:{ Exception -> 0x081f }
            r1.L$16 = r2     // Catch:{ Exception -> 0x081f }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r24)     // Catch:{ Exception -> 0x081f }
            r1.L$17 = r2     // Catch:{ Exception -> 0x081f }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r22)     // Catch:{ Exception -> 0x081f }
            r1.L$18 = r2     // Catch:{ Exception -> 0x081f }
            r2 = 6
            r1.label = r2     // Catch:{ Exception -> 0x081f }
            java.lang.Object r0 = r0.invoke(r3, r1)     // Catch:{ Exception -> 0x081f }
            r2 = r26
            if (r0 != r2) goto L_0x09c8
            return r2
        L_0x081f:
            r0 = move-exception
            r2 = r26
        L_0x0822:
            kotlin.jvm.functions.Function1<java.lang.Throwable, kotlin.Unit> r3 = r1.$onFailure     // Catch:{ Exception -> 0x0829 }
            r3.invoke(r0)     // Catch:{ Exception -> 0x0829 }
            goto L_0x09c8
        L_0x0829:
            r0 = move-exception
        L_0x082a:
            r3 = r11
            goto L_0x08bb
        L_0x082d:
            r0 = move-exception
            r2 = r26
            goto L_0x082a
        L_0x0831:
            kotlin.jvm.functions.Function1<java.lang.Throwable, kotlin.Unit> r0 = r1.$onFailure     // Catch:{ Exception -> 0x0829 }
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException     // Catch:{ Exception -> 0x0829 }
            java.lang.String r5 = "App link is missing"
            r3.<init>(r5)     // Catch:{ Exception -> 0x0829 }
            r0.invoke(r3)     // Catch:{ Exception -> 0x0829 }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x0829 }
            return r0
        L_0x0840:
            r2 = r26
            java.lang.String r0 = "publishing_authenticated_session_approve"
            r11.add(r0)     // Catch:{ Exception -> 0x08af }
            com.reown.sign.engine.use_case.calls.ApproveSessionAuthenticateUseCase r0 = r1.this$0     // Catch:{ Exception -> 0x08af }
            com.reown.foundation.util.Logger r0 = r0.logger     // Catch:{ Exception -> 0x08af }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x08af }
            r3.<init>()     // Catch:{ Exception -> 0x08af }
            java.lang.String r5 = "Sending Session Authenticate Approve on topic: "
            r3.append(r5)     // Catch:{ Exception -> 0x08af }
            r10 = r23
            r3.append(r10)     // Catch:{ Exception -> 0x08af }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x08af }
            r0.log((java.lang.String) r3)     // Catch:{ Exception -> 0x08af }
            com.reown.sign.engine.use_case.calls.ApproveSessionAuthenticateUseCase r0 = r1.this$0     // Catch:{ Exception -> 0x08af }
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r0 = r0.jsonRpcInteractor     // Catch:{ Exception -> 0x08af }
            com.reown.android.internal.common.model.EnvelopeType r3 = com.reown.android.internal.common.model.EnvelopeType.ONE     // Catch:{ Exception -> 0x08af }
            com.reown.android.internal.common.model.Participants r9 = new com.reown.android.internal.common.model.Participants     // Catch:{ Exception -> 0x08af }
            r5 = 0
            r9.<init>(r14, r13, r5)     // Catch:{ Exception -> 0x08af }
            kotlin.jvm.functions.Function0<kotlin.Unit> r5 = r1.$onSuccess     // Catch:{ Exception -> 0x08af }
            com.reown.sign.engine.use_case.calls.ApproveSessionAuthenticateUseCase r7 = r1.this$0     // Catch:{ Exception -> 0x08af }
            long r13 = r1.$id     // Catch:{ Exception -> 0x08af }
            com.reown.sign.engine.use_case.calls.c r23 = new com.reown.sign.engine.use_case.calls.c     // Catch:{ Exception -> 0x08af }
            r21 = 0
            r19 = r13
            r14 = r23
            r15 = r11
            r16 = r5
            r17 = r7
            r18 = r10
            r14.<init>(r15, r16, r17, r18, r19, r21)     // Catch:{ Exception -> 0x08af }
            kotlin.jvm.functions.Function1<java.lang.Throwable, kotlin.Unit> r13 = r1.$onFailure     // Catch:{ Exception -> 0x08af }
            com.reown.sign.engine.use_case.calls.d r20 = new com.reown.sign.engine.use_case.calls.d     // Catch:{ Exception -> 0x08af }
            r5 = r20
            r6 = r4
            r8 = r12
            r12 = r9
            r9 = r13
            r13 = r10
            r10 = r11
            r14 = r11
            r11 = r13
            r5.<init>(r6, r7, r8, r9, r10, r11)     // Catch:{ Exception -> 0x08ac }
            r15 = r0
            r16 = r13
            r17 = r30
            r18 = r22
            r19 = r23
            r21 = r12
            r22 = r3
            r15.publishJsonRpcResponse(r16, r17, r18, r19, r20, r21, r22)     // Catch:{ Exception -> 0x08ac }
            goto L_0x09c8
        L_0x08ac:
            r0 = move-exception
        L_0x08ad:
            r3 = r14
            goto L_0x08bb
        L_0x08af:
            r0 = move-exception
            r14 = r11
            goto L_0x08ad
        L_0x08b2:
            r0 = move-exception
            r14 = r11
            r2 = r26
            goto L_0x08ad
        L_0x08b7:
            r0 = move-exception
            r14 = r11
            r2 = r12
            goto L_0x08ad
        L_0x08bb:
            com.reown.sign.engine.use_case.calls.ApproveSessionAuthenticateUseCase r5 = r1.this$0
            com.reown.foundation.util.Logger r5 = r5.logger
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "Error Responding Session Authenticate, error: "
            r6.<init>(r7)
            r6.append(r0)
            java.lang.String r6 = r6.toString()
            r5.error((java.lang.String) r6)
            boolean r5 = r0 instanceof com.reown.android.internal.common.exception.NoRelayConnectionException
            if (r5 == 0) goto L_0x0948
            com.reown.sign.engine.use_case.calls.ApproveSessionAuthenticateUseCase r5 = r1.this$0
            com.reown.android.pulse.domain.InsertTelemetryEventUseCase r15 = r5.insertTelemetryEventUseCase
            com.reown.android.pulse.model.properties.Props r14 = new com.reown.android.pulse.model.properties.Props
            com.reown.android.pulse.model.properties.Properties r20 = new com.reown.android.pulse.model.properties.Properties
            r16 = 0
            r17 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r13 = 0
            r18 = 0
            r19 = 0
            r21 = 4031(0xfbf, float:5.649E-42)
            r22 = 0
            r5 = r20
            r12 = r3
            r52 = r14
            r14 = r18
            r49 = r15
            r15 = r19
            r18 = r21
            r19 = r22
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19)
            java.lang.String r7 = "NO_WSS_CONNECTION"
            r9 = 1
            r5 = r52
            r8 = r20
            r5.<init>(r6, r7, r8, r9, r10)
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)
            r1.L$0 = r5
            r1.L$1 = r3
            r1.L$2 = r0
            r5 = 0
            r1.L$3 = r5
            r1.L$4 = r5
            r1.L$5 = r5
            r1.L$6 = r5
            r1.L$7 = r5
            r1.L$8 = r5
            r1.L$9 = r5
            r1.L$10 = r5
            r1.L$11 = r5
            r1.L$12 = r5
            r1.L$13 = r5
            r1.L$14 = r5
            r1.L$15 = r5
            r1.L$16 = r5
            r1.L$17 = r5
            r1.L$18 = r5
            r5 = 7
            r1.label = r5
            r6 = r52
            r5 = r49
            java.lang.Object r5 = r5.invoke(r6, r1)
            if (r5 != r2) goto L_0x0948
            return r2
        L_0x0948:
            boolean r5 = r0 instanceof com.reown.android.internal.common.exception.NoInternetConnectionException
            if (r5 == 0) goto L_0x09c3
            com.reown.sign.engine.use_case.calls.ApproveSessionAuthenticateUseCase r5 = r1.this$0
            com.reown.android.pulse.domain.InsertTelemetryEventUseCase r15 = r5.insertTelemetryEventUseCase
            com.reown.android.pulse.model.properties.Props r14 = new com.reown.android.pulse.model.properties.Props
            com.reown.android.pulse.model.properties.Properties r20 = new com.reown.android.pulse.model.properties.Properties
            r16 = 0
            r17 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r13 = 0
            r18 = 0
            r19 = 0
            r21 = 4031(0xfbf, float:5.649E-42)
            r22 = 0
            r5 = r20
            r12 = r3
            r52 = r14
            r14 = r18
            r50 = r15
            r15 = r19
            r18 = r21
            r19 = r22
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19)
            java.lang.String r7 = "NO_INTERNET_CONNECTION"
            r9 = 1
            r5 = r52
            r8 = r20
            r5.<init>(r6, r7, r8, r9, r10)
            java.lang.Object r4 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)
            r1.L$0 = r4
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r3)
            r1.L$1 = r3
            r1.L$2 = r0
            r3 = 0
            r1.L$3 = r3
            r1.L$4 = r3
            r1.L$5 = r3
            r1.L$6 = r3
            r1.L$7 = r3
            r1.L$8 = r3
            r1.L$9 = r3
            r1.L$10 = r3
            r1.L$11 = r3
            r1.L$12 = r3
            r1.L$13 = r3
            r1.L$14 = r3
            r1.L$15 = r3
            r1.L$16 = r3
            r1.L$17 = r3
            r1.L$18 = r3
            r3 = 8
            r1.label = r3
            r4 = r52
            r3 = r50
            java.lang.Object r3 = r3.invoke(r4, r1)
            if (r3 != r2) goto L_0x09c3
            return r2
        L_0x09c3:
            kotlin.jvm.functions.Function1<java.lang.Throwable, kotlin.Unit> r1 = r1.$onFailure
            r1.invoke(r0)
        L_0x09c8:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.engine.use_case.calls.ApproveSessionAuthenticateUseCase$approveSessionAuthenticate$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ApproveSessionAuthenticateUseCase$approveSessionAuthenticate$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
