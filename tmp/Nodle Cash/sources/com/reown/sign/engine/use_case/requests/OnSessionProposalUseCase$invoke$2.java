package com.reown.sign.engine.use_case.requests;

import com.reown.android.internal.common.WalletConnectScopeKt;
import com.reown.android.internal.common.model.WCRequest;
import com.reown.android.verify.model.VerifyContext;
import com.reown.foundation.common.model.Topic;
import com.reown.foundation.util.Logger;
import com.reown.sign.common.model.vo.clientsync.session.params.SignParams;
import com.reown.sign.engine.model.EngineDO;
import com.reown.sign.engine.model.mapper.EngineMapperKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

@SourceDebugExtension({"SMAP\nOnSessionProposalUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 OnSessionProposalUseCase.kt\ncom/reown/sign/engine/use_case/requests/OnSessionProposalUseCase$invoke$2\n+ 2 SignValidator.kt\ncom/reown/sign/common/validator/SignValidator\n*L\n1#1,105:1\n29#2,8:106\n29#2,8:114\n70#2,4:122\n*S KotlinDebug\n*F\n+ 1 OnSessionProposalUseCase.kt\ncom/reown/sign/engine/use_case/requests/OnSessionProposalUseCase$invoke$2\n*L\n59#1:106,8\n66#1:114,8\n74#1:122,4\n*E\n"})
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.requests.OnSessionProposalUseCase$invoke$2", f = "OnSessionProposalUseCase.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 10, 10, 10, 11, 11}, l = {61, 61, 61, 61, 61, 68, 68, 68, 68, 68, 76, 99}, m = "invokeSuspend", n = {"irnParams", "this_$iv", "namespaces$iv", "error", "$i$f$validateProposalNamespaces$sign_release", "$i$a$-validateProposalNamespaces$sign_release-OnSessionProposalUseCase$invoke$2$1", "irnParams", "this_$iv", "namespaces$iv", "error", "$i$f$validateProposalNamespaces$sign_release", "$i$a$-validateProposalNamespaces$sign_release-OnSessionProposalUseCase$invoke$2$1", "irnParams", "this_$iv", "namespaces$iv", "error", "$i$f$validateProposalNamespaces$sign_release", "$i$a$-validateProposalNamespaces$sign_release-OnSessionProposalUseCase$invoke$2$1", "irnParams", "this_$iv", "namespaces$iv", "error", "$i$f$validateProposalNamespaces$sign_release", "$i$a$-validateProposalNamespaces$sign_release-OnSessionProposalUseCase$invoke$2$1", "irnParams", "this_$iv", "namespaces$iv", "error", "$i$f$validateProposalNamespaces$sign_release", "$i$a$-validateProposalNamespaces$sign_release-OnSessionProposalUseCase$invoke$2$1", "irnParams", "this_$iv", "namespaces$iv", "error", "$i$f$validateProposalNamespaces$sign_release", "$i$a$-validateProposalNamespaces$sign_release-OnSessionProposalUseCase$invoke$2$2", "irnParams", "this_$iv", "namespaces$iv", "error", "$i$f$validateProposalNamespaces$sign_release", "$i$a$-validateProposalNamespaces$sign_release-OnSessionProposalUseCase$invoke$2$2", "irnParams", "this_$iv", "namespaces$iv", "error", "$i$f$validateProposalNamespaces$sign_release", "$i$a$-validateProposalNamespaces$sign_release-OnSessionProposalUseCase$invoke$2$2", "irnParams", "this_$iv", "namespaces$iv", "error", "$i$f$validateProposalNamespaces$sign_release", "$i$a$-validateProposalNamespaces$sign_release-OnSessionProposalUseCase$invoke$2$2", "irnParams", "this_$iv", "namespaces$iv", "error", "$i$f$validateProposalNamespaces$sign_release", "$i$a$-validateProposalNamespaces$sign_release-OnSessionProposalUseCase$invoke$2$2", "irnParams", "it", "this_$iv", "properties$iv", "error", "$i$a$-let-OnSessionProposalUseCase$invoke$2$3", "$i$f$validateProperties$sign_release", "$i$a$-validateProperties$sign_release-OnSessionProposalUseCase$invoke$2$3$1", "irnParams", "e"}, s = {"L$0", "L$1", "L$2", "L$5", "I$0", "I$1", "L$0", "L$1", "L$2", "L$5", "I$0", "I$1", "L$0", "L$1", "L$2", "L$5", "I$0", "I$1", "L$0", "L$1", "L$2", "L$5", "I$0", "I$1", "L$0", "L$1", "L$2", "L$5", "I$0", "I$1", "L$0", "L$1", "L$2", "L$5", "I$0", "I$1", "L$0", "L$1", "L$2", "L$5", "I$0", "I$1", "L$0", "L$1", "L$2", "L$5", "I$0", "I$1", "L$0", "L$1", "L$2", "L$5", "I$0", "I$1", "L$0", "L$1", "L$2", "L$5", "I$0", "I$1", "L$0", "L$3", "L$4", "L$5", "L$6", "I$0", "I$1", "I$2", "L$0", "L$1"})
public final class OnSessionProposalUseCase$invoke$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ SignParams.SessionProposeParams $payloadParams;
    final /* synthetic */ WCRequest $request;
    int I$0;
    int I$1;
    int I$2;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;
    final /* synthetic */ OnSessionProposalUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OnSessionProposalUseCase$invoke$2(WCRequest wCRequest, OnSessionProposalUseCase onSessionProposalUseCase, SignParams.SessionProposeParams sessionProposeParams, Continuation<? super OnSessionProposalUseCase$invoke$2> continuation) {
        super(2, continuation);
        this.$request = wCRequest;
        this.this$0 = onSessionProposalUseCase;
        this.$payloadParams = sessionProposeParams;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$4(OnSessionProposalUseCase onSessionProposalUseCase, SignParams.SessionProposeParams sessionProposeParams, WCRequest wCRequest, VerifyContext verifyContext) {
        Logger access$getLogger$p = onSessionProposalUseCase.logger;
        long currentTimeMillis = System.currentTimeMillis();
        access$getLogger$p.log("Session proposal attestation resolved: " + currentTimeMillis);
        EngineDO.SessionProposalEvent sessionProposalEvent = new EngineDO.SessionProposalEvent(EngineMapperKt.toEngineDO(sessionProposeParams, wCRequest.getTopic()), Intrinsics.checkNotNullParameter(verifyContext, "<this>"));
        Logger access$getLogger$p2 = onSessionProposalUseCase.logger;
        Topic topic = wCRequest.getTopic();
        access$getLogger$p2.log("Session proposal received on topic: " + topic + " - emitting");
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new OnSessionProposalUseCase$invoke$2$4$1(onSessionProposalUseCase, sessionProposalEvent, (Continuation<? super OnSessionProposalUseCase$invoke$2$4$1>) null), 3, (Object) null);
        return Unit.INSTANCE;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new OnSessionProposalUseCase$invoke$2(this.$request, this.this$0, this.$payloadParams, continuation);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:109:?, code lost:
        com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r3.jsonRpcInteractor, r6, com.reown.sign.engine.model.mapper.EngineMapperKt.toPeerError(r0), r2, (com.reown.android.internal.common.model.EnvelopeType) null, (com.reown.android.internal.common.model.Participants) null, (kotlin.jvm.functions.Function1) null, (kotlin.jvm.functions.Function1) null, 120, (java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x04f2, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:?, code lost:
        com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r3.jsonRpcInteractor, r6, com.reown.sign.engine.model.mapper.EngineMapperKt.toPeerError(r0), r2, (com.reown.android.internal.common.model.EnvelopeType) null, (com.reown.android.internal.common.model.Participants) null, (kotlin.jvm.functions.Function1) null, (kotlin.jvm.functions.Function1) null, 120, (java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x059c, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:?, code lost:
        com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r3.jsonRpcInteractor, r6, com.reown.sign.engine.model.mapper.EngineMapperKt.toPeerError(r0), r2, (com.reown.android.internal.common.model.EnvelopeType) null, (com.reown.android.internal.common.model.Participants) null, (kotlin.jvm.functions.Function1) null, (kotlin.jvm.functions.Function1) null, 120, (java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x0637, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:?, code lost:
        com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r3.jsonRpcInteractor, r6, com.reown.sign.engine.model.mapper.EngineMapperKt.toPeerError(r0), r2, (com.reown.android.internal.common.model.EnvelopeType) null, (com.reown.android.internal.common.model.Participants) null, (kotlin.jvm.functions.Function1) null, (kotlin.jvm.functions.Function1) null, 120, (java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x06d3, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:?, code lost:
        com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r3.jsonRpcInteractor, r6, com.reown.sign.engine.model.mapper.EngineMapperKt.toPeerError(r0), r2, (com.reown.android.internal.common.model.EnvelopeType) null, (com.reown.android.internal.common.model.Participants) null, (kotlin.jvm.functions.Function1) null, (kotlin.jvm.functions.Function1) null, 120, (java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x076f, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:?, code lost:
        com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r3.jsonRpcInteractor, r6, com.reown.sign.engine.model.mapper.EngineMapperKt.toPeerError(r0), r2, (com.reown.android.internal.common.model.EnvelopeType) null, (com.reown.android.internal.common.model.Participants) null, (kotlin.jvm.functions.Function1) null, (kotlin.jvm.functions.Function1) null, 120, (java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x080b, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:?, code lost:
        com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r3.jsonRpcInteractor, r6, com.reown.sign.engine.model.mapper.EngineMapperKt.toPeerError(r0), r2, (com.reown.android.internal.common.model.EnvelopeType) null, (com.reown.android.internal.common.model.Participants) null, (kotlin.jvm.functions.Function1) null, (kotlin.jvm.functions.Function1) null, 120, (java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:0x08be, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:191:0x09e0, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0267, code lost:
        com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r3.jsonRpcInteractor, r8, com.reown.sign.engine.model.mapper.EngineMapperKt.toPeerError(r0), r5, (com.reown.android.internal.common.model.EnvelopeType) null, (com.reown.android.internal.common.model.Participants) null, (kotlin.jvm.functions.Function1) null, (kotlin.jvm.functions.Function1) null, 120, (java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x027d, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0303, code lost:
        com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r3.jsonRpcInteractor, r8, com.reown.sign.engine.model.mapper.EngineMapperKt.toPeerError(r0), r5, (com.reown.android.internal.common.model.EnvelopeType) null, (com.reown.android.internal.common.model.Participants) null, (kotlin.jvm.functions.Function1) null, (kotlin.jvm.functions.Function1) null, 120, (java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0319, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x039e, code lost:
        com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r3.jsonRpcInteractor, r8, com.reown.sign.engine.model.mapper.EngineMapperKt.toPeerError(r0), r5, (com.reown.android.internal.common.model.EnvelopeType) null, (com.reown.android.internal.common.model.Participants) null, (kotlin.jvm.functions.Function1) null, (kotlin.jvm.functions.Function1) null, 120, (java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x03b4, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:?, code lost:
        com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r3.jsonRpcInteractor, r6, com.reown.sign.engine.model.mapper.EngineMapperKt.toPeerError(r0), r2, (com.reown.android.internal.common.model.EnvelopeType) null, (com.reown.android.internal.common.model.Participants) null, (kotlin.jvm.functions.Function1) null, (kotlin.jvm.functions.Function1) null, 120, (java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x0451, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x0452, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0453, code lost:
        r5 = r2;
     */
    /* JADX WARNING: Removed duplicated region for block: B:191:0x09e0 A[RETURN] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:133:0x0622=Splitter:B:133:0x0622, B:163:0x07f6=Splitter:B:163:0x07f6, B:123:0x0587=Splitter:B:123:0x0587, B:175:0x08a9=Splitter:B:175:0x08a9, B:153:0x075a=Splitter:B:153:0x075a, B:94:0x043c=Splitter:B:94:0x043c, B:108:0x04dd=Splitter:B:108:0x04dd, B:143:0x06be=Splitter:B:143:0x06be} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r36) {
        /*
            r35 = this;
            r1 = r35
            java.lang.String r0 = "Resolving session proposal attestation: "
            java.lang.String r2 = "Session proposal received error: session properties validation: "
            java.lang.String r3 = "Session proposal received: "
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r5 = r1.label
            switch(r5) {
                case 0: goto L_0x017f;
                case 1: goto L_0x0161;
                case 2: goto L_0x0143;
                case 3: goto L_0x0125;
                case 4: goto L_0x0106;
                case 5: goto L_0x00e7;
                case 6: goto L_0x00c8;
                case 7: goto L_0x00a9;
                case 8: goto L_0x008a;
                case 9: goto L_0x006b;
                case 10: goto L_0x004c;
                case 11: goto L_0x0026;
                case 12: goto L_0x0019;
                default: goto L_0x0011;
            }
        L_0x0011:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0019:
            java.lang.Object r0 = r1.L$1
            java.lang.Exception r0 = (java.lang.Exception) r0
            java.lang.Object r0 = r1.L$0
            com.reown.android.internal.common.model.IrnParams r0 = (com.reown.android.internal.common.model.IrnParams) r0
            kotlin.ResultKt.throwOnFailure(r36)
            goto L_0x09e1
        L_0x0026:
            java.lang.Object r0 = r1.L$6
            com.reown.sign.engine.model.ValidationError r0 = (com.reown.sign.engine.model.ValidationError) r0
            java.lang.Object r2 = r1.L$5
            java.util.Map r2 = (java.util.Map) r2
            java.lang.Object r2 = r1.L$4
            com.reown.sign.common.validator.SignValidator r2 = (com.reown.sign.common.validator.SignValidator) r2
            java.lang.Object r2 = r1.L$3
            java.util.Map r2 = (java.util.Map) r2
            java.lang.Object r2 = r1.L$2
            com.reown.android.internal.common.model.WCRequest r2 = (com.reown.android.internal.common.model.WCRequest) r2
            java.lang.Object r3 = r1.L$1
            com.reown.sign.engine.use_case.requests.OnSessionProposalUseCase r3 = (com.reown.sign.engine.use_case.requests.OnSessionProposalUseCase) r3
            java.lang.Object r5 = r1.L$0
            com.reown.android.internal.common.model.IrnParams r5 = (com.reown.android.internal.common.model.IrnParams) r5
            kotlin.ResultKt.throwOnFailure(r36)     // Catch:{ Exception -> 0x0049 }
            r6 = r2
            r2 = r5
            goto L_0x08a9
        L_0x0049:
            r0 = move-exception
            goto L_0x095b
        L_0x004c:
            java.lang.Object r0 = r1.L$5
            com.reown.sign.engine.model.ValidationError r0 = (com.reown.sign.engine.model.ValidationError) r0
            java.lang.Object r2 = r1.L$4
            com.reown.android.internal.common.model.WCRequest r2 = (com.reown.android.internal.common.model.WCRequest) r2
            java.lang.Object r3 = r1.L$3
            com.reown.sign.engine.use_case.requests.OnSessionProposalUseCase r3 = (com.reown.sign.engine.use_case.requests.OnSessionProposalUseCase) r3
            java.lang.Object r5 = r1.L$2
            java.util.Map r5 = (java.util.Map) r5
            java.lang.Object r5 = r1.L$1
            com.reown.sign.common.validator.SignValidator r5 = (com.reown.sign.common.validator.SignValidator) r5
            java.lang.Object r5 = r1.L$0
            com.reown.android.internal.common.model.IrnParams r5 = (com.reown.android.internal.common.model.IrnParams) r5
            kotlin.ResultKt.throwOnFailure(r36)     // Catch:{ Exception -> 0x0049 }
            r6 = r2
            r2 = r5
            goto L_0x07f6
        L_0x006b:
            java.lang.Object r0 = r1.L$5
            com.reown.sign.engine.model.ValidationError r0 = (com.reown.sign.engine.model.ValidationError) r0
            java.lang.Object r2 = r1.L$4
            com.reown.android.internal.common.model.WCRequest r2 = (com.reown.android.internal.common.model.WCRequest) r2
            java.lang.Object r3 = r1.L$3
            com.reown.sign.engine.use_case.requests.OnSessionProposalUseCase r3 = (com.reown.sign.engine.use_case.requests.OnSessionProposalUseCase) r3
            java.lang.Object r5 = r1.L$2
            java.util.Map r5 = (java.util.Map) r5
            java.lang.Object r5 = r1.L$1
            com.reown.sign.common.validator.SignValidator r5 = (com.reown.sign.common.validator.SignValidator) r5
            java.lang.Object r5 = r1.L$0
            com.reown.android.internal.common.model.IrnParams r5 = (com.reown.android.internal.common.model.IrnParams) r5
            kotlin.ResultKt.throwOnFailure(r36)     // Catch:{ Exception -> 0x0049 }
            r6 = r2
            r2 = r5
            goto L_0x075a
        L_0x008a:
            java.lang.Object r0 = r1.L$5
            com.reown.sign.engine.model.ValidationError r0 = (com.reown.sign.engine.model.ValidationError) r0
            java.lang.Object r2 = r1.L$4
            com.reown.android.internal.common.model.WCRequest r2 = (com.reown.android.internal.common.model.WCRequest) r2
            java.lang.Object r3 = r1.L$3
            com.reown.sign.engine.use_case.requests.OnSessionProposalUseCase r3 = (com.reown.sign.engine.use_case.requests.OnSessionProposalUseCase) r3
            java.lang.Object r5 = r1.L$2
            java.util.Map r5 = (java.util.Map) r5
            java.lang.Object r5 = r1.L$1
            com.reown.sign.common.validator.SignValidator r5 = (com.reown.sign.common.validator.SignValidator) r5
            java.lang.Object r5 = r1.L$0
            com.reown.android.internal.common.model.IrnParams r5 = (com.reown.android.internal.common.model.IrnParams) r5
            kotlin.ResultKt.throwOnFailure(r36)     // Catch:{ Exception -> 0x0049 }
            r6 = r2
            r2 = r5
            goto L_0x06be
        L_0x00a9:
            java.lang.Object r0 = r1.L$5
            com.reown.sign.engine.model.ValidationError r0 = (com.reown.sign.engine.model.ValidationError) r0
            java.lang.Object r2 = r1.L$4
            com.reown.android.internal.common.model.WCRequest r2 = (com.reown.android.internal.common.model.WCRequest) r2
            java.lang.Object r3 = r1.L$3
            com.reown.sign.engine.use_case.requests.OnSessionProposalUseCase r3 = (com.reown.sign.engine.use_case.requests.OnSessionProposalUseCase) r3
            java.lang.Object r5 = r1.L$2
            java.util.Map r5 = (java.util.Map) r5
            java.lang.Object r5 = r1.L$1
            com.reown.sign.common.validator.SignValidator r5 = (com.reown.sign.common.validator.SignValidator) r5
            java.lang.Object r5 = r1.L$0
            com.reown.android.internal.common.model.IrnParams r5 = (com.reown.android.internal.common.model.IrnParams) r5
            kotlin.ResultKt.throwOnFailure(r36)     // Catch:{ Exception -> 0x0049 }
            r6 = r2
            r2 = r5
            goto L_0x0622
        L_0x00c8:
            java.lang.Object r0 = r1.L$5
            com.reown.sign.engine.model.ValidationError r0 = (com.reown.sign.engine.model.ValidationError) r0
            java.lang.Object r2 = r1.L$4
            com.reown.android.internal.common.model.WCRequest r2 = (com.reown.android.internal.common.model.WCRequest) r2
            java.lang.Object r3 = r1.L$3
            com.reown.sign.engine.use_case.requests.OnSessionProposalUseCase r3 = (com.reown.sign.engine.use_case.requests.OnSessionProposalUseCase) r3
            java.lang.Object r5 = r1.L$2
            java.util.Map r5 = (java.util.Map) r5
            java.lang.Object r5 = r1.L$1
            com.reown.sign.common.validator.SignValidator r5 = (com.reown.sign.common.validator.SignValidator) r5
            java.lang.Object r5 = r1.L$0
            com.reown.android.internal.common.model.IrnParams r5 = (com.reown.android.internal.common.model.IrnParams) r5
            kotlin.ResultKt.throwOnFailure(r36)     // Catch:{ Exception -> 0x0049 }
            r6 = r2
            r2 = r5
            goto L_0x0587
        L_0x00e7:
            java.lang.Object r0 = r1.L$5
            com.reown.sign.engine.model.ValidationError r0 = (com.reown.sign.engine.model.ValidationError) r0
            java.lang.Object r2 = r1.L$4
            com.reown.android.internal.common.model.WCRequest r2 = (com.reown.android.internal.common.model.WCRequest) r2
            java.lang.Object r3 = r1.L$3
            com.reown.sign.engine.use_case.requests.OnSessionProposalUseCase r3 = (com.reown.sign.engine.use_case.requests.OnSessionProposalUseCase) r3
            java.lang.Object r5 = r1.L$2
            java.util.Map r5 = (java.util.Map) r5
            java.lang.Object r5 = r1.L$1
            com.reown.sign.common.validator.SignValidator r5 = (com.reown.sign.common.validator.SignValidator) r5
            java.lang.Object r5 = r1.L$0
            com.reown.android.internal.common.model.IrnParams r5 = (com.reown.android.internal.common.model.IrnParams) r5
            kotlin.ResultKt.throwOnFailure(r36)     // Catch:{ Exception -> 0x0049 }
            r6 = r2
            r2 = r5
            goto L_0x04dd
        L_0x0106:
            java.lang.Object r0 = r1.L$5
            com.reown.sign.engine.model.ValidationError r0 = (com.reown.sign.engine.model.ValidationError) r0
            java.lang.Object r2 = r1.L$4
            com.reown.android.internal.common.model.WCRequest r2 = (com.reown.android.internal.common.model.WCRequest) r2
            java.lang.Object r3 = r1.L$3
            com.reown.sign.engine.use_case.requests.OnSessionProposalUseCase r3 = (com.reown.sign.engine.use_case.requests.OnSessionProposalUseCase) r3
            java.lang.Object r5 = r1.L$2
            java.util.Map r5 = (java.util.Map) r5
            java.lang.Object r5 = r1.L$1
            com.reown.sign.common.validator.SignValidator r5 = (com.reown.sign.common.validator.SignValidator) r5
            java.lang.Object r5 = r1.L$0
            com.reown.android.internal.common.model.IrnParams r5 = (com.reown.android.internal.common.model.IrnParams) r5
            kotlin.ResultKt.throwOnFailure(r36)     // Catch:{ Exception -> 0x0049 }
            r6 = r2
            r2 = r5
            goto L_0x043c
        L_0x0125:
            java.lang.Object r0 = r1.L$5
            com.reown.sign.engine.model.ValidationError r0 = (com.reown.sign.engine.model.ValidationError) r0
            java.lang.Object r2 = r1.L$4
            com.reown.android.internal.common.model.WCRequest r2 = (com.reown.android.internal.common.model.WCRequest) r2
            java.lang.Object r3 = r1.L$3
            com.reown.sign.engine.use_case.requests.OnSessionProposalUseCase r3 = (com.reown.sign.engine.use_case.requests.OnSessionProposalUseCase) r3
            java.lang.Object r5 = r1.L$2
            java.util.Map r5 = (java.util.Map) r5
            java.lang.Object r5 = r1.L$1
            com.reown.sign.common.validator.SignValidator r5 = (com.reown.sign.common.validator.SignValidator) r5
            java.lang.Object r5 = r1.L$0
            com.reown.android.internal.common.model.IrnParams r5 = (com.reown.android.internal.common.model.IrnParams) r5
            kotlin.ResultKt.throwOnFailure(r36)     // Catch:{ Exception -> 0x0049 }
            r8 = r2
            goto L_0x039e
        L_0x0143:
            java.lang.Object r0 = r1.L$5
            com.reown.sign.engine.model.ValidationError r0 = (com.reown.sign.engine.model.ValidationError) r0
            java.lang.Object r2 = r1.L$4
            com.reown.android.internal.common.model.WCRequest r2 = (com.reown.android.internal.common.model.WCRequest) r2
            java.lang.Object r3 = r1.L$3
            com.reown.sign.engine.use_case.requests.OnSessionProposalUseCase r3 = (com.reown.sign.engine.use_case.requests.OnSessionProposalUseCase) r3
            java.lang.Object r5 = r1.L$2
            java.util.Map r5 = (java.util.Map) r5
            java.lang.Object r5 = r1.L$1
            com.reown.sign.common.validator.SignValidator r5 = (com.reown.sign.common.validator.SignValidator) r5
            java.lang.Object r5 = r1.L$0
            com.reown.android.internal.common.model.IrnParams r5 = (com.reown.android.internal.common.model.IrnParams) r5
            kotlin.ResultKt.throwOnFailure(r36)     // Catch:{ Exception -> 0x0049 }
            r8 = r2
            goto L_0x0303
        L_0x0161:
            java.lang.Object r0 = r1.L$5
            com.reown.sign.engine.model.ValidationError r0 = (com.reown.sign.engine.model.ValidationError) r0
            java.lang.Object r2 = r1.L$4
            com.reown.android.internal.common.model.WCRequest r2 = (com.reown.android.internal.common.model.WCRequest) r2
            java.lang.Object r3 = r1.L$3
            com.reown.sign.engine.use_case.requests.OnSessionProposalUseCase r3 = (com.reown.sign.engine.use_case.requests.OnSessionProposalUseCase) r3
            java.lang.Object r5 = r1.L$2
            java.util.Map r5 = (java.util.Map) r5
            java.lang.Object r5 = r1.L$1
            com.reown.sign.common.validator.SignValidator r5 = (com.reown.sign.common.validator.SignValidator) r5
            java.lang.Object r5 = r1.L$0
            com.reown.android.internal.common.model.IrnParams r5 = (com.reown.android.internal.common.model.IrnParams) r5
            kotlin.ResultKt.throwOnFailure(r36)     // Catch:{ Exception -> 0x0049 }
            r8 = r2
            goto L_0x0267
        L_0x017f:
            kotlin.ResultKt.throwOnFailure(r36)
            com.reown.android.internal.common.model.IrnParams r5 = new com.reown.android.internal.common.model.IrnParams
            com.reown.android.internal.common.model.Tags r8 = com.reown.android.internal.common.model.Tags.SESSION_PROPOSE_RESPONSE_AUTO_REJECT
            com.reown.foundation.common.model.Ttl r9 = new com.reown.foundation.common.model.Ttl
            long r10 = com.reown.android.internal.utils.Time.getFiveMinutesInSeconds()
            r9.<init>(r10)
            com.reown.android.internal.common.model.WCRequest r7 = r1.$request
            long r10 = r7.getId()
            java.lang.Long r10 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r10)
            r14 = 0
            r15 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r16 = 248(0xf8, float:3.48E-43)
            r17 = 0
            r7 = r5
            r7.<init>(r8, r9, r10, r11, r12, r13, r14, r15, r16, r17)
            com.reown.sign.engine.use_case.requests.OnSessionProposalUseCase r7 = r1.this$0     // Catch:{ Exception -> 0x0049 }
            com.reown.android.internal.common.model.WCRequest r8 = r1.$request     // Catch:{ Exception -> 0x0049 }
            boolean r7 = r7.isSessionAuthenticateImplemented(r8)     // Catch:{ Exception -> 0x0049 }
            if (r7 == 0) goto L_0x01be
            com.reown.sign.engine.use_case.requests.OnSessionProposalUseCase r0 = r1.this$0     // Catch:{ Exception -> 0x0049 }
            com.reown.foundation.util.Logger r0 = r0.logger     // Catch:{ Exception -> 0x0049 }
            java.lang.String r2 = "Session proposal received error: pairing supports authenticated sessions"
            r0.error((java.lang.String) r2)     // Catch:{ Exception -> 0x0049 }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x0049 }
            return r0
        L_0x01be:
            com.reown.sign.engine.use_case.requests.OnSessionProposalUseCase r7 = r1.this$0     // Catch:{ Exception -> 0x0049 }
            com.reown.foundation.util.Logger r7 = r7.logger     // Catch:{ Exception -> 0x0049 }
            com.reown.android.internal.common.model.WCRequest r8 = r1.$request     // Catch:{ Exception -> 0x0049 }
            com.reown.foundation.common.model.Topic r8 = r8.getTopic()     // Catch:{ Exception -> 0x0049 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0049 }
            r9.<init>(r3)     // Catch:{ Exception -> 0x0049 }
            r9.append(r8)     // Catch:{ Exception -> 0x0049 }
            java.lang.String r3 = r9.toString()     // Catch:{ Exception -> 0x0049 }
            r7.log((java.lang.String) r3)     // Catch:{ Exception -> 0x0049 }
            com.reown.sign.common.validator.SignValidator r3 = com.reown.sign.common.validator.SignValidator.INSTANCE     // Catch:{ Exception -> 0x0049 }
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionProposeParams r7 = r1.$payloadParams     // Catch:{ Exception -> 0x0049 }
            java.util.Map r7 = r7.getRequiredNamespaces()     // Catch:{ Exception -> 0x0049 }
            com.reown.sign.engine.use_case.requests.OnSessionProposalUseCase r8 = r1.this$0     // Catch:{ Exception -> 0x0049 }
            com.reown.android.internal.common.model.WCRequest r9 = r1.$request     // Catch:{ Exception -> 0x0049 }
            boolean r10 = r3.areNamespacesKeysProperlyFormatted(r7)     // Catch:{ Exception -> 0x0049 }
            r11 = 1
            r12 = 0
            java.lang.String r13 = "Session proposal received error: required namespace validation: "
            if (r10 != 0) goto L_0x027e
            com.reown.sign.engine.model.ValidationError$UnsupportedNamespaceKey r0 = com.reown.sign.engine.model.ValidationError.UnsupportedNamespaceKey.INSTANCE     // Catch:{ Exception -> 0x0049 }
            com.reown.foundation.util.Logger r2 = r8.logger     // Catch:{ Exception -> 0x0049 }
            java.lang.String r10 = r0.getMessage()     // Catch:{ Exception -> 0x0049 }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0049 }
            r14.<init>(r13)     // Catch:{ Exception -> 0x0049 }
            r14.append(r10)     // Catch:{ Exception -> 0x0049 }
            java.lang.String r10 = r14.toString()     // Catch:{ Exception -> 0x0049 }
            r2.error((java.lang.String) r10)     // Catch:{ Exception -> 0x0049 }
            com.reown.android.pulse.domain.InsertTelemetryEventUseCase r2 = r8.insertEventUseCase     // Catch:{ Exception -> 0x0049 }
            com.reown.android.pulse.model.properties.Props r10 = new com.reown.android.pulse.model.properties.Props     // Catch:{ Exception -> 0x0049 }
            java.lang.String r15 = "REQUIRED_NAMESPACE_VALIDATION_FAILURE"
            com.reown.android.pulse.model.properties.Properties r31 = new com.reown.android.pulse.model.properties.Properties     // Catch:{ Exception -> 0x0049 }
            com.reown.foundation.common.model.Topic r13 = r9.getTopic()     // Catch:{ Exception -> 0x0049 }
            java.lang.String r24 = r13.getValue()     // Catch:{ Exception -> 0x0049 }
            r27 = 0
            r28 = 0
            r29 = 3967(0xf7f, float:5.559E-42)
            r30 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r21 = 0
            r22 = 0
            r23 = 0
            r25 = 0
            r26 = 0
            r16 = r31
            r16.<init>(r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30)     // Catch:{ Exception -> 0x0049 }
            r18 = 0
            r14 = 0
            r17 = 1
            r13 = r10
            r16 = r31
            r13.<init>(r14, r15, r16, r17, r18)     // Catch:{ Exception -> 0x0049 }
            r1.L$0 = r5     // Catch:{ Exception -> 0x0049 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r3)     // Catch:{ Exception -> 0x0049 }
            r1.L$1 = r3     // Catch:{ Exception -> 0x0049 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)     // Catch:{ Exception -> 0x0049 }
            r1.L$2 = r3     // Catch:{ Exception -> 0x0049 }
            r1.L$3 = r8     // Catch:{ Exception -> 0x0049 }
            r1.L$4 = r9     // Catch:{ Exception -> 0x0049 }
            r1.L$5 = r0     // Catch:{ Exception -> 0x0049 }
            r1.I$0 = r12     // Catch:{ Exception -> 0x0049 }
            r1.I$1 = r12     // Catch:{ Exception -> 0x0049 }
            r1.label = r11     // Catch:{ Exception -> 0x0049 }
            java.lang.Object r2 = r2.invoke(r10, r1)     // Catch:{ Exception -> 0x0049 }
            if (r2 != r4) goto L_0x0265
            return r4
        L_0x0265:
            r3 = r8
            r8 = r9
        L_0x0267:
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r7 = r3.jsonRpcInteractor     // Catch:{ Exception -> 0x0049 }
            com.reown.sign.common.exceptions.PeerError r9 = com.reown.sign.engine.model.mapper.EngineMapperKt.toPeerError(r0)     // Catch:{ Exception -> 0x0049 }
            r13 = 0
            r14 = 0
            r15 = 120(0x78, float:1.68E-43)
            r16 = 0
            r11 = 0
            r12 = 0
            r10 = r5
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r7, r8, r9, r10, r11, r12, r13, r14, r15, r16)     // Catch:{ Exception -> 0x0049 }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x0049 }
            return r0
        L_0x027e:
            boolean r10 = r3.areChainsDefined(r7)     // Catch:{ Exception -> 0x0049 }
            r14 = 2
            java.lang.String r15 = "Chains must not be null"
            if (r10 != 0) goto L_0x031a
            com.reown.sign.engine.model.ValidationError$UnsupportedChains r0 = new com.reown.sign.engine.model.ValidationError$UnsupportedChains     // Catch:{ Exception -> 0x0049 }
            r0.<init>(r15)     // Catch:{ Exception -> 0x0049 }
            com.reown.foundation.util.Logger r2 = r8.logger     // Catch:{ Exception -> 0x0049 }
            java.lang.String r10 = r0.getMessage()     // Catch:{ Exception -> 0x0049 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0049 }
            r11.<init>(r13)     // Catch:{ Exception -> 0x0049 }
            r11.append(r10)     // Catch:{ Exception -> 0x0049 }
            java.lang.String r10 = r11.toString()     // Catch:{ Exception -> 0x0049 }
            r2.error((java.lang.String) r10)     // Catch:{ Exception -> 0x0049 }
            com.reown.android.pulse.domain.InsertTelemetryEventUseCase r2 = r8.insertEventUseCase     // Catch:{ Exception -> 0x0049 }
            com.reown.android.pulse.model.properties.Props r10 = new com.reown.android.pulse.model.properties.Props     // Catch:{ Exception -> 0x0049 }
            java.lang.String r17 = "REQUIRED_NAMESPACE_VALIDATION_FAILURE"
            com.reown.android.pulse.model.properties.Properties r11 = new com.reown.android.pulse.model.properties.Properties     // Catch:{ Exception -> 0x0049 }
            com.reown.foundation.common.model.Topic r13 = r9.getTopic()     // Catch:{ Exception -> 0x0049 }
            java.lang.String r26 = r13.getValue()     // Catch:{ Exception -> 0x0049 }
            r29 = 0
            r30 = 0
            r31 = 3967(0xf7f, float:5.559E-42)
            r32 = 0
            r19 = 0
            r20 = 0
            r21 = 0
            r22 = 0
            r23 = 0
            r24 = 0
            r25 = 0
            r27 = 0
            r28 = 0
            r18 = r11
            r18.<init>(r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32)     // Catch:{ Exception -> 0x0049 }
            r20 = 0
            r16 = 0
            r19 = 1
            r15 = r10
            r18 = r11
            r15.<init>(r16, r17, r18, r19, r20)     // Catch:{ Exception -> 0x0049 }
            r1.L$0 = r5     // Catch:{ Exception -> 0x0049 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r3)     // Catch:{ Exception -> 0x0049 }
            r1.L$1 = r3     // Catch:{ Exception -> 0x0049 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)     // Catch:{ Exception -> 0x0049 }
            r1.L$2 = r3     // Catch:{ Exception -> 0x0049 }
            r1.L$3 = r8     // Catch:{ Exception -> 0x0049 }
            r1.L$4 = r9     // Catch:{ Exception -> 0x0049 }
            r1.L$5 = r0     // Catch:{ Exception -> 0x0049 }
            r1.I$0 = r12     // Catch:{ Exception -> 0x0049 }
            r1.I$1 = r12     // Catch:{ Exception -> 0x0049 }
            r1.label = r14     // Catch:{ Exception -> 0x0049 }
            java.lang.Object r2 = r2.invoke(r10, r1)     // Catch:{ Exception -> 0x0049 }
            if (r2 != r4) goto L_0x0301
            return r4
        L_0x0301:
            r3 = r8
            r8 = r9
        L_0x0303:
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r7 = r3.jsonRpcInteractor     // Catch:{ Exception -> 0x0049 }
            com.reown.sign.common.exceptions.PeerError r9 = com.reown.sign.engine.model.mapper.EngineMapperKt.toPeerError(r0)     // Catch:{ Exception -> 0x0049 }
            r13 = 0
            r14 = 0
            r15 = 120(0x78, float:1.68E-43)
            r16 = 0
            r11 = 0
            r12 = 0
            r10 = r5
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r7, r8, r9, r10, r11, r12, r13, r14, r15, r16)     // Catch:{ Exception -> 0x0049 }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x0049 }
            return r0
        L_0x031a:
            boolean r10 = r3.areChainsNotEmpty(r7)     // Catch:{ Exception -> 0x0049 }
            java.lang.String r11 = "Chains must not be empty"
            if (r10 != 0) goto L_0x03b5
            com.reown.sign.engine.model.ValidationError$UnsupportedChains r0 = new com.reown.sign.engine.model.ValidationError$UnsupportedChains     // Catch:{ Exception -> 0x0049 }
            r0.<init>(r11)     // Catch:{ Exception -> 0x0049 }
            com.reown.foundation.util.Logger r2 = r8.logger     // Catch:{ Exception -> 0x0049 }
            java.lang.String r10 = r0.getMessage()     // Catch:{ Exception -> 0x0049 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0049 }
            r11.<init>(r13)     // Catch:{ Exception -> 0x0049 }
            r11.append(r10)     // Catch:{ Exception -> 0x0049 }
            java.lang.String r10 = r11.toString()     // Catch:{ Exception -> 0x0049 }
            r2.error((java.lang.String) r10)     // Catch:{ Exception -> 0x0049 }
            com.reown.android.pulse.domain.InsertTelemetryEventUseCase r2 = r8.insertEventUseCase     // Catch:{ Exception -> 0x0049 }
            com.reown.android.pulse.model.properties.Props r10 = new com.reown.android.pulse.model.properties.Props     // Catch:{ Exception -> 0x0049 }
            java.lang.String r15 = "REQUIRED_NAMESPACE_VALIDATION_FAILURE"
            com.reown.android.pulse.model.properties.Properties r11 = new com.reown.android.pulse.model.properties.Properties     // Catch:{ Exception -> 0x0049 }
            com.reown.foundation.common.model.Topic r13 = r9.getTopic()     // Catch:{ Exception -> 0x0049 }
            java.lang.String r24 = r13.getValue()     // Catch:{ Exception -> 0x0049 }
            r27 = 0
            r28 = 0
            r29 = 3967(0xf7f, float:5.559E-42)
            r30 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r21 = 0
            r22 = 0
            r23 = 0
            r25 = 0
            r26 = 0
            r16 = r11
            r16.<init>(r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30)     // Catch:{ Exception -> 0x0049 }
            r18 = 0
            r14 = 0
            r17 = 1
            r13 = r10
            r16 = r11
            r13.<init>(r14, r15, r16, r17, r18)     // Catch:{ Exception -> 0x0049 }
            r1.L$0 = r5     // Catch:{ Exception -> 0x0049 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r3)     // Catch:{ Exception -> 0x0049 }
            r1.L$1 = r3     // Catch:{ Exception -> 0x0049 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)     // Catch:{ Exception -> 0x0049 }
            r1.L$2 = r3     // Catch:{ Exception -> 0x0049 }
            r1.L$3 = r8     // Catch:{ Exception -> 0x0049 }
            r1.L$4 = r9     // Catch:{ Exception -> 0x0049 }
            r1.L$5 = r0     // Catch:{ Exception -> 0x0049 }
            r1.I$0 = r12     // Catch:{ Exception -> 0x0049 }
            r1.I$1 = r12     // Catch:{ Exception -> 0x0049 }
            r3 = 3
            r1.label = r3     // Catch:{ Exception -> 0x0049 }
            java.lang.Object r2 = r2.invoke(r10, r1)     // Catch:{ Exception -> 0x0049 }
            if (r2 != r4) goto L_0x039c
            return r4
        L_0x039c:
            r3 = r8
            r8 = r9
        L_0x039e:
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r7 = r3.jsonRpcInteractor     // Catch:{ Exception -> 0x0049 }
            com.reown.sign.common.exceptions.PeerError r9 = com.reown.sign.engine.model.mapper.EngineMapperKt.toPeerError(r0)     // Catch:{ Exception -> 0x0049 }
            r13 = 0
            r14 = 0
            r15 = 120(0x78, float:1.68E-43)
            r16 = 0
            r11 = 0
            r12 = 0
            r10 = r5
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r7, r8, r9, r10, r11, r12, r13, r14, r15, r16)     // Catch:{ Exception -> 0x0049 }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x0049 }
            return r0
        L_0x03b5:
            boolean r10 = r3.areChainIdsValid(r7)     // Catch:{ Exception -> 0x0049 }
            java.lang.String r6 = "Chains must be CAIP-2 compliant"
            if (r10 != 0) goto L_0x0456
            com.reown.sign.engine.model.ValidationError$UnsupportedChains r0 = new com.reown.sign.engine.model.ValidationError$UnsupportedChains     // Catch:{ Exception -> 0x0049 }
            r0.<init>(r6)     // Catch:{ Exception -> 0x0049 }
            com.reown.foundation.util.Logger r2 = r8.logger     // Catch:{ Exception -> 0x0049 }
            java.lang.String r6 = r0.getMessage()     // Catch:{ Exception -> 0x0049 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0049 }
            r10.<init>(r13)     // Catch:{ Exception -> 0x0049 }
            r10.append(r6)     // Catch:{ Exception -> 0x0049 }
            java.lang.String r6 = r10.toString()     // Catch:{ Exception -> 0x0049 }
            r2.error((java.lang.String) r6)     // Catch:{ Exception -> 0x0049 }
            com.reown.android.pulse.domain.InsertTelemetryEventUseCase r2 = r8.insertEventUseCase     // Catch:{ Exception -> 0x0049 }
            com.reown.android.pulse.model.properties.Props r6 = new com.reown.android.pulse.model.properties.Props     // Catch:{ Exception -> 0x0049 }
            java.lang.String r19 = "REQUIRED_NAMESPACE_VALIDATION_FAILURE"
            com.reown.android.pulse.model.properties.Properties r10 = new com.reown.android.pulse.model.properties.Properties     // Catch:{ Exception -> 0x0049 }
            com.reown.foundation.common.model.Topic r11 = r9.getTopic()     // Catch:{ Exception -> 0x0049 }
            java.lang.String r28 = r11.getValue()     // Catch:{ Exception -> 0x0049 }
            r31 = 0
            r32 = 0
            r33 = 3967(0xf7f, float:5.559E-42)
            r34 = 0
            r21 = 0
            r22 = 0
            r23 = 0
            r24 = 0
            r25 = 0
            r26 = 0
            r27 = 0
            r29 = 0
            r30 = 0
            r20 = r10
            r20.<init>(r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34)     // Catch:{ Exception -> 0x0049 }
            r22 = 0
            r18 = 0
            r21 = 1
            r17 = r6
            r20 = r10
            r17.<init>(r18, r19, r20, r21, r22)     // Catch:{ Exception -> 0x0049 }
            r1.L$0 = r5     // Catch:{ Exception -> 0x0049 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r3)     // Catch:{ Exception -> 0x0049 }
            r1.L$1 = r3     // Catch:{ Exception -> 0x0049 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)     // Catch:{ Exception -> 0x0049 }
            r1.L$2 = r3     // Catch:{ Exception -> 0x0049 }
            r1.L$3 = r8     // Catch:{ Exception -> 0x0049 }
            r1.L$4 = r9     // Catch:{ Exception -> 0x0049 }
            r1.L$5 = r0     // Catch:{ Exception -> 0x0049 }
            r1.I$0 = r12     // Catch:{ Exception -> 0x0049 }
            r1.I$1 = r12     // Catch:{ Exception -> 0x0049 }
            r3 = 4
            r1.label = r3     // Catch:{ Exception -> 0x0049 }
            java.lang.Object r2 = r2.invoke(r6, r1)     // Catch:{ Exception -> 0x0049 }
            if (r2 != r4) goto L_0x0439
            return r4
        L_0x0439:
            r2 = r5
            r3 = r8
            r6 = r9
        L_0x043c:
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r5 = r3.jsonRpcInteractor     // Catch:{ Exception -> 0x0452 }
            com.reown.sign.common.exceptions.PeerError r7 = com.reown.sign.engine.model.mapper.EngineMapperKt.toPeerError(r0)     // Catch:{ Exception -> 0x0452 }
            r11 = 0
            r12 = 0
            r13 = 120(0x78, float:1.68E-43)
            r14 = 0
            r9 = 0
            r10 = 0
            r8 = r2
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)     // Catch:{ Exception -> 0x0452 }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x0452 }
            return r0
        L_0x0452:
            r0 = move-exception
            r5 = r2
            goto L_0x095b
        L_0x0456:
            boolean r10 = r3.areChainsInMatchingNamespace(r7)     // Catch:{ Exception -> 0x0049 }
            java.lang.String r14 = "Chains must be defined in matching namespace"
            if (r10 != 0) goto L_0x04f3
            com.reown.sign.engine.model.ValidationError$UnsupportedChains r0 = new com.reown.sign.engine.model.ValidationError$UnsupportedChains     // Catch:{ Exception -> 0x0049 }
            r0.<init>(r14)     // Catch:{ Exception -> 0x0049 }
            com.reown.foundation.util.Logger r2 = r8.logger     // Catch:{ Exception -> 0x0049 }
            java.lang.String r6 = r0.getMessage()     // Catch:{ Exception -> 0x0049 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0049 }
            r10.<init>(r13)     // Catch:{ Exception -> 0x0049 }
            r10.append(r6)     // Catch:{ Exception -> 0x0049 }
            java.lang.String r6 = r10.toString()     // Catch:{ Exception -> 0x0049 }
            r2.error((java.lang.String) r6)     // Catch:{ Exception -> 0x0049 }
            com.reown.android.pulse.domain.InsertTelemetryEventUseCase r2 = r8.insertEventUseCase     // Catch:{ Exception -> 0x0049 }
            com.reown.android.pulse.model.properties.Props r6 = new com.reown.android.pulse.model.properties.Props     // Catch:{ Exception -> 0x0049 }
            java.lang.String r19 = "REQUIRED_NAMESPACE_VALIDATION_FAILURE"
            com.reown.android.pulse.model.properties.Properties r10 = new com.reown.android.pulse.model.properties.Properties     // Catch:{ Exception -> 0x0049 }
            com.reown.foundation.common.model.Topic r11 = r9.getTopic()     // Catch:{ Exception -> 0x0049 }
            java.lang.String r28 = r11.getValue()     // Catch:{ Exception -> 0x0049 }
            r31 = 0
            r32 = 0
            r33 = 3967(0xf7f, float:5.559E-42)
            r34 = 0
            r21 = 0
            r22 = 0
            r23 = 0
            r24 = 0
            r25 = 0
            r26 = 0
            r27 = 0
            r29 = 0
            r30 = 0
            r20 = r10
            r20.<init>(r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34)     // Catch:{ Exception -> 0x0049 }
            r22 = 0
            r18 = 0
            r21 = 1
            r17 = r6
            r20 = r10
            r17.<init>(r18, r19, r20, r21, r22)     // Catch:{ Exception -> 0x0049 }
            r1.L$0 = r5     // Catch:{ Exception -> 0x0049 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r3)     // Catch:{ Exception -> 0x0049 }
            r1.L$1 = r3     // Catch:{ Exception -> 0x0049 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)     // Catch:{ Exception -> 0x0049 }
            r1.L$2 = r3     // Catch:{ Exception -> 0x0049 }
            r1.L$3 = r8     // Catch:{ Exception -> 0x0049 }
            r1.L$4 = r9     // Catch:{ Exception -> 0x0049 }
            r1.L$5 = r0     // Catch:{ Exception -> 0x0049 }
            r1.I$0 = r12     // Catch:{ Exception -> 0x0049 }
            r1.I$1 = r12     // Catch:{ Exception -> 0x0049 }
            r3 = 5
            r1.label = r3     // Catch:{ Exception -> 0x0049 }
            java.lang.Object r2 = r2.invoke(r6, r1)     // Catch:{ Exception -> 0x0049 }
            if (r2 != r4) goto L_0x04da
            return r4
        L_0x04da:
            r2 = r5
            r3 = r8
            r6 = r9
        L_0x04dd:
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r5 = r3.jsonRpcInteractor     // Catch:{ Exception -> 0x0452 }
            com.reown.sign.common.exceptions.PeerError r7 = com.reown.sign.engine.model.mapper.EngineMapperKt.toPeerError(r0)     // Catch:{ Exception -> 0x0452 }
            r11 = 0
            r12 = 0
            r13 = 120(0x78, float:1.68E-43)
            r14 = 0
            r9 = 0
            r10 = 0
            r8 = r2
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)     // Catch:{ Exception -> 0x0452 }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x0452 }
            return r0
        L_0x04f3:
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionProposeParams r7 = r1.$payloadParams     // Catch:{ Exception -> 0x0049 }
            java.util.Map r7 = r7.getOptionalNamespaces()     // Catch:{ Exception -> 0x0049 }
            if (r7 != 0) goto L_0x04ff
            java.util.Map r7 = kotlin.collections.MapsKt.emptyMap()     // Catch:{ Exception -> 0x0049 }
        L_0x04ff:
            com.reown.sign.engine.use_case.requests.OnSessionProposalUseCase r8 = r1.this$0     // Catch:{ Exception -> 0x0049 }
            com.reown.android.internal.common.model.WCRequest r9 = r1.$request     // Catch:{ Exception -> 0x0049 }
            boolean r10 = r3.areNamespacesKeysProperlyFormatted(r7)     // Catch:{ Exception -> 0x0049 }
            java.lang.String r13 = "Session proposal received error: optional namespace validation: "
            if (r10 != 0) goto L_0x059d
            com.reown.sign.engine.model.ValidationError$UnsupportedNamespaceKey r0 = com.reown.sign.engine.model.ValidationError.UnsupportedNamespaceKey.INSTANCE     // Catch:{ Exception -> 0x0049 }
            com.reown.foundation.util.Logger r2 = r8.logger     // Catch:{ Exception -> 0x0049 }
            java.lang.String r6 = r0.getMessage()     // Catch:{ Exception -> 0x0049 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0049 }
            r10.<init>(r13)     // Catch:{ Exception -> 0x0049 }
            r10.append(r6)     // Catch:{ Exception -> 0x0049 }
            java.lang.String r6 = r10.toString()     // Catch:{ Exception -> 0x0049 }
            r2.error((java.lang.String) r6)     // Catch:{ Exception -> 0x0049 }
            com.reown.android.pulse.domain.InsertTelemetryEventUseCase r2 = r8.insertEventUseCase     // Catch:{ Exception -> 0x0049 }
            com.reown.android.pulse.model.properties.Props r6 = new com.reown.android.pulse.model.properties.Props     // Catch:{ Exception -> 0x0049 }
            java.lang.String r19 = "OPTIONAL_NAMESPACE_VALIDATION_FAILURE"
            com.reown.android.pulse.model.properties.Properties r10 = new com.reown.android.pulse.model.properties.Properties     // Catch:{ Exception -> 0x0049 }
            com.reown.foundation.common.model.Topic r11 = r9.getTopic()     // Catch:{ Exception -> 0x0049 }
            java.lang.String r28 = r11.getValue()     // Catch:{ Exception -> 0x0049 }
            r31 = 0
            r32 = 0
            r33 = 3967(0xf7f, float:5.559E-42)
            r34 = 0
            r21 = 0
            r22 = 0
            r23 = 0
            r24 = 0
            r25 = 0
            r26 = 0
            r27 = 0
            r29 = 0
            r30 = 0
            r20 = r10
            r20.<init>(r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34)     // Catch:{ Exception -> 0x0049 }
            r22 = 0
            r18 = 0
            r21 = 1
            r17 = r6
            r20 = r10
            r17.<init>(r18, r19, r20, r21, r22)     // Catch:{ Exception -> 0x0049 }
            r1.L$0 = r5     // Catch:{ Exception -> 0x0049 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r3)     // Catch:{ Exception -> 0x0049 }
            r1.L$1 = r3     // Catch:{ Exception -> 0x0049 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)     // Catch:{ Exception -> 0x0049 }
            r1.L$2 = r3     // Catch:{ Exception -> 0x0049 }
            r1.L$3 = r8     // Catch:{ Exception -> 0x0049 }
            r1.L$4 = r9     // Catch:{ Exception -> 0x0049 }
            r1.L$5 = r0     // Catch:{ Exception -> 0x0049 }
            r1.I$0 = r12     // Catch:{ Exception -> 0x0049 }
            r1.I$1 = r12     // Catch:{ Exception -> 0x0049 }
            r3 = 6
            r1.label = r3     // Catch:{ Exception -> 0x0049 }
            java.lang.Object r2 = r2.invoke(r6, r1)     // Catch:{ Exception -> 0x0049 }
            if (r2 != r4) goto L_0x0584
            return r4
        L_0x0584:
            r2 = r5
            r3 = r8
            r6 = r9
        L_0x0587:
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r5 = r3.jsonRpcInteractor     // Catch:{ Exception -> 0x0452 }
            com.reown.sign.common.exceptions.PeerError r7 = com.reown.sign.engine.model.mapper.EngineMapperKt.toPeerError(r0)     // Catch:{ Exception -> 0x0452 }
            r11 = 0
            r12 = 0
            r13 = 120(0x78, float:1.68E-43)
            r14 = 0
            r9 = 0
            r10 = 0
            r8 = r2
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)     // Catch:{ Exception -> 0x0452 }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x0452 }
            return r0
        L_0x059d:
            boolean r10 = r3.areChainsDefined(r7)     // Catch:{ Exception -> 0x0049 }
            if (r10 != 0) goto L_0x0638
            com.reown.sign.engine.model.ValidationError$UnsupportedChains r0 = new com.reown.sign.engine.model.ValidationError$UnsupportedChains     // Catch:{ Exception -> 0x0049 }
            r0.<init>(r15)     // Catch:{ Exception -> 0x0049 }
            com.reown.foundation.util.Logger r2 = r8.logger     // Catch:{ Exception -> 0x0049 }
            java.lang.String r6 = r0.getMessage()     // Catch:{ Exception -> 0x0049 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0049 }
            r10.<init>(r13)     // Catch:{ Exception -> 0x0049 }
            r10.append(r6)     // Catch:{ Exception -> 0x0049 }
            java.lang.String r6 = r10.toString()     // Catch:{ Exception -> 0x0049 }
            r2.error((java.lang.String) r6)     // Catch:{ Exception -> 0x0049 }
            com.reown.android.pulse.domain.InsertTelemetryEventUseCase r2 = r8.insertEventUseCase     // Catch:{ Exception -> 0x0049 }
            com.reown.android.pulse.model.properties.Props r6 = new com.reown.android.pulse.model.properties.Props     // Catch:{ Exception -> 0x0049 }
            java.lang.String r19 = "OPTIONAL_NAMESPACE_VALIDATION_FAILURE"
            com.reown.android.pulse.model.properties.Properties r10 = new com.reown.android.pulse.model.properties.Properties     // Catch:{ Exception -> 0x0049 }
            com.reown.foundation.common.model.Topic r11 = r9.getTopic()     // Catch:{ Exception -> 0x0049 }
            java.lang.String r28 = r11.getValue()     // Catch:{ Exception -> 0x0049 }
            r31 = 0
            r32 = 0
            r33 = 3967(0xf7f, float:5.559E-42)
            r34 = 0
            r21 = 0
            r22 = 0
            r23 = 0
            r24 = 0
            r25 = 0
            r26 = 0
            r27 = 0
            r29 = 0
            r30 = 0
            r20 = r10
            r20.<init>(r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34)     // Catch:{ Exception -> 0x0049 }
            r22 = 0
            r18 = 0
            r21 = 1
            r17 = r6
            r20 = r10
            r17.<init>(r18, r19, r20, r21, r22)     // Catch:{ Exception -> 0x0049 }
            r1.L$0 = r5     // Catch:{ Exception -> 0x0049 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r3)     // Catch:{ Exception -> 0x0049 }
            r1.L$1 = r3     // Catch:{ Exception -> 0x0049 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)     // Catch:{ Exception -> 0x0049 }
            r1.L$2 = r3     // Catch:{ Exception -> 0x0049 }
            r1.L$3 = r8     // Catch:{ Exception -> 0x0049 }
            r1.L$4 = r9     // Catch:{ Exception -> 0x0049 }
            r1.L$5 = r0     // Catch:{ Exception -> 0x0049 }
            r1.I$0 = r12     // Catch:{ Exception -> 0x0049 }
            r1.I$1 = r12     // Catch:{ Exception -> 0x0049 }
            r3 = 7
            r1.label = r3     // Catch:{ Exception -> 0x0049 }
            java.lang.Object r2 = r2.invoke(r6, r1)     // Catch:{ Exception -> 0x0049 }
            if (r2 != r4) goto L_0x061f
            return r4
        L_0x061f:
            r2 = r5
            r3 = r8
            r6 = r9
        L_0x0622:
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r5 = r3.jsonRpcInteractor     // Catch:{ Exception -> 0x0452 }
            com.reown.sign.common.exceptions.PeerError r7 = com.reown.sign.engine.model.mapper.EngineMapperKt.toPeerError(r0)     // Catch:{ Exception -> 0x0452 }
            r11 = 0
            r12 = 0
            r13 = 120(0x78, float:1.68E-43)
            r14 = 0
            r9 = 0
            r10 = 0
            r8 = r2
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)     // Catch:{ Exception -> 0x0452 }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x0452 }
            return r0
        L_0x0638:
            boolean r10 = r3.areChainsNotEmpty(r7)     // Catch:{ Exception -> 0x0049 }
            if (r10 != 0) goto L_0x06d4
            com.reown.sign.engine.model.ValidationError$UnsupportedChains r0 = new com.reown.sign.engine.model.ValidationError$UnsupportedChains     // Catch:{ Exception -> 0x0049 }
            r0.<init>(r11)     // Catch:{ Exception -> 0x0049 }
            com.reown.foundation.util.Logger r2 = r8.logger     // Catch:{ Exception -> 0x0049 }
            java.lang.String r6 = r0.getMessage()     // Catch:{ Exception -> 0x0049 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0049 }
            r10.<init>(r13)     // Catch:{ Exception -> 0x0049 }
            r10.append(r6)     // Catch:{ Exception -> 0x0049 }
            java.lang.String r6 = r10.toString()     // Catch:{ Exception -> 0x0049 }
            r2.error((java.lang.String) r6)     // Catch:{ Exception -> 0x0049 }
            com.reown.android.pulse.domain.InsertTelemetryEventUseCase r2 = r8.insertEventUseCase     // Catch:{ Exception -> 0x0049 }
            com.reown.android.pulse.model.properties.Props r6 = new com.reown.android.pulse.model.properties.Props     // Catch:{ Exception -> 0x0049 }
            java.lang.String r19 = "OPTIONAL_NAMESPACE_VALIDATION_FAILURE"
            com.reown.android.pulse.model.properties.Properties r10 = new com.reown.android.pulse.model.properties.Properties     // Catch:{ Exception -> 0x0049 }
            com.reown.foundation.common.model.Topic r11 = r9.getTopic()     // Catch:{ Exception -> 0x0049 }
            java.lang.String r28 = r11.getValue()     // Catch:{ Exception -> 0x0049 }
            r31 = 0
            r32 = 0
            r33 = 3967(0xf7f, float:5.559E-42)
            r34 = 0
            r21 = 0
            r22 = 0
            r23 = 0
            r24 = 0
            r25 = 0
            r26 = 0
            r27 = 0
            r29 = 0
            r30 = 0
            r20 = r10
            r20.<init>(r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34)     // Catch:{ Exception -> 0x0049 }
            r22 = 0
            r18 = 0
            r21 = 1
            r17 = r6
            r20 = r10
            r17.<init>(r18, r19, r20, r21, r22)     // Catch:{ Exception -> 0x0049 }
            r1.L$0 = r5     // Catch:{ Exception -> 0x0049 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r3)     // Catch:{ Exception -> 0x0049 }
            r1.L$1 = r3     // Catch:{ Exception -> 0x0049 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)     // Catch:{ Exception -> 0x0049 }
            r1.L$2 = r3     // Catch:{ Exception -> 0x0049 }
            r1.L$3 = r8     // Catch:{ Exception -> 0x0049 }
            r1.L$4 = r9     // Catch:{ Exception -> 0x0049 }
            r1.L$5 = r0     // Catch:{ Exception -> 0x0049 }
            r1.I$0 = r12     // Catch:{ Exception -> 0x0049 }
            r1.I$1 = r12     // Catch:{ Exception -> 0x0049 }
            r3 = 8
            r1.label = r3     // Catch:{ Exception -> 0x0049 }
            java.lang.Object r2 = r2.invoke(r6, r1)     // Catch:{ Exception -> 0x0049 }
            if (r2 != r4) goto L_0x06bb
            return r4
        L_0x06bb:
            r2 = r5
            r3 = r8
            r6 = r9
        L_0x06be:
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r5 = r3.jsonRpcInteractor     // Catch:{ Exception -> 0x0452 }
            com.reown.sign.common.exceptions.PeerError r7 = com.reown.sign.engine.model.mapper.EngineMapperKt.toPeerError(r0)     // Catch:{ Exception -> 0x0452 }
            r11 = 0
            r12 = 0
            r13 = 120(0x78, float:1.68E-43)
            r14 = 0
            r9 = 0
            r10 = 0
            r8 = r2
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)     // Catch:{ Exception -> 0x0452 }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x0452 }
            return r0
        L_0x06d4:
            boolean r10 = r3.areChainIdsValid(r7)     // Catch:{ Exception -> 0x0049 }
            if (r10 != 0) goto L_0x0770
            com.reown.sign.engine.model.ValidationError$UnsupportedChains r0 = new com.reown.sign.engine.model.ValidationError$UnsupportedChains     // Catch:{ Exception -> 0x0049 }
            r0.<init>(r6)     // Catch:{ Exception -> 0x0049 }
            com.reown.foundation.util.Logger r2 = r8.logger     // Catch:{ Exception -> 0x0049 }
            java.lang.String r6 = r0.getMessage()     // Catch:{ Exception -> 0x0049 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0049 }
            r10.<init>(r13)     // Catch:{ Exception -> 0x0049 }
            r10.append(r6)     // Catch:{ Exception -> 0x0049 }
            java.lang.String r6 = r10.toString()     // Catch:{ Exception -> 0x0049 }
            r2.error((java.lang.String) r6)     // Catch:{ Exception -> 0x0049 }
            com.reown.android.pulse.domain.InsertTelemetryEventUseCase r2 = r8.insertEventUseCase     // Catch:{ Exception -> 0x0049 }
            com.reown.android.pulse.model.properties.Props r6 = new com.reown.android.pulse.model.properties.Props     // Catch:{ Exception -> 0x0049 }
            java.lang.String r19 = "OPTIONAL_NAMESPACE_VALIDATION_FAILURE"
            com.reown.android.pulse.model.properties.Properties r10 = new com.reown.android.pulse.model.properties.Properties     // Catch:{ Exception -> 0x0049 }
            com.reown.foundation.common.model.Topic r11 = r9.getTopic()     // Catch:{ Exception -> 0x0049 }
            java.lang.String r28 = r11.getValue()     // Catch:{ Exception -> 0x0049 }
            r31 = 0
            r32 = 0
            r33 = 3967(0xf7f, float:5.559E-42)
            r34 = 0
            r21 = 0
            r22 = 0
            r23 = 0
            r24 = 0
            r25 = 0
            r26 = 0
            r27 = 0
            r29 = 0
            r30 = 0
            r20 = r10
            r20.<init>(r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34)     // Catch:{ Exception -> 0x0049 }
            r22 = 0
            r18 = 0
            r21 = 1
            r17 = r6
            r20 = r10
            r17.<init>(r18, r19, r20, r21, r22)     // Catch:{ Exception -> 0x0049 }
            r1.L$0 = r5     // Catch:{ Exception -> 0x0049 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r3)     // Catch:{ Exception -> 0x0049 }
            r1.L$1 = r3     // Catch:{ Exception -> 0x0049 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)     // Catch:{ Exception -> 0x0049 }
            r1.L$2 = r3     // Catch:{ Exception -> 0x0049 }
            r1.L$3 = r8     // Catch:{ Exception -> 0x0049 }
            r1.L$4 = r9     // Catch:{ Exception -> 0x0049 }
            r1.L$5 = r0     // Catch:{ Exception -> 0x0049 }
            r1.I$0 = r12     // Catch:{ Exception -> 0x0049 }
            r1.I$1 = r12     // Catch:{ Exception -> 0x0049 }
            r3 = 9
            r1.label = r3     // Catch:{ Exception -> 0x0049 }
            java.lang.Object r2 = r2.invoke(r6, r1)     // Catch:{ Exception -> 0x0049 }
            if (r2 != r4) goto L_0x0757
            return r4
        L_0x0757:
            r2 = r5
            r3 = r8
            r6 = r9
        L_0x075a:
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r5 = r3.jsonRpcInteractor     // Catch:{ Exception -> 0x0452 }
            com.reown.sign.common.exceptions.PeerError r7 = com.reown.sign.engine.model.mapper.EngineMapperKt.toPeerError(r0)     // Catch:{ Exception -> 0x0452 }
            r11 = 0
            r12 = 0
            r13 = 120(0x78, float:1.68E-43)
            r14 = 0
            r9 = 0
            r10 = 0
            r8 = r2
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)     // Catch:{ Exception -> 0x0452 }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x0452 }
            return r0
        L_0x0770:
            boolean r6 = r3.areChainsInMatchingNamespace(r7)     // Catch:{ Exception -> 0x0049 }
            if (r6 != 0) goto L_0x080c
            com.reown.sign.engine.model.ValidationError$UnsupportedChains r0 = new com.reown.sign.engine.model.ValidationError$UnsupportedChains     // Catch:{ Exception -> 0x0049 }
            r0.<init>(r14)     // Catch:{ Exception -> 0x0049 }
            com.reown.foundation.util.Logger r2 = r8.logger     // Catch:{ Exception -> 0x0049 }
            java.lang.String r6 = r0.getMessage()     // Catch:{ Exception -> 0x0049 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0049 }
            r10.<init>(r13)     // Catch:{ Exception -> 0x0049 }
            r10.append(r6)     // Catch:{ Exception -> 0x0049 }
            java.lang.String r6 = r10.toString()     // Catch:{ Exception -> 0x0049 }
            r2.error((java.lang.String) r6)     // Catch:{ Exception -> 0x0049 }
            com.reown.android.pulse.domain.InsertTelemetryEventUseCase r2 = r8.insertEventUseCase     // Catch:{ Exception -> 0x0049 }
            com.reown.android.pulse.model.properties.Props r6 = new com.reown.android.pulse.model.properties.Props     // Catch:{ Exception -> 0x0049 }
            java.lang.String r19 = "OPTIONAL_NAMESPACE_VALIDATION_FAILURE"
            com.reown.android.pulse.model.properties.Properties r10 = new com.reown.android.pulse.model.properties.Properties     // Catch:{ Exception -> 0x0049 }
            com.reown.foundation.common.model.Topic r11 = r9.getTopic()     // Catch:{ Exception -> 0x0049 }
            java.lang.String r28 = r11.getValue()     // Catch:{ Exception -> 0x0049 }
            r31 = 0
            r32 = 0
            r33 = 3967(0xf7f, float:5.559E-42)
            r34 = 0
            r21 = 0
            r22 = 0
            r23 = 0
            r24 = 0
            r25 = 0
            r26 = 0
            r27 = 0
            r29 = 0
            r30 = 0
            r20 = r10
            r20.<init>(r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34)     // Catch:{ Exception -> 0x0049 }
            r22 = 0
            r18 = 0
            r21 = 1
            r17 = r6
            r20 = r10
            r17.<init>(r18, r19, r20, r21, r22)     // Catch:{ Exception -> 0x0049 }
            r1.L$0 = r5     // Catch:{ Exception -> 0x0049 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r3)     // Catch:{ Exception -> 0x0049 }
            r1.L$1 = r3     // Catch:{ Exception -> 0x0049 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)     // Catch:{ Exception -> 0x0049 }
            r1.L$2 = r3     // Catch:{ Exception -> 0x0049 }
            r1.L$3 = r8     // Catch:{ Exception -> 0x0049 }
            r1.L$4 = r9     // Catch:{ Exception -> 0x0049 }
            r1.L$5 = r0     // Catch:{ Exception -> 0x0049 }
            r1.I$0 = r12     // Catch:{ Exception -> 0x0049 }
            r1.I$1 = r12     // Catch:{ Exception -> 0x0049 }
            r3 = 10
            r1.label = r3     // Catch:{ Exception -> 0x0049 }
            java.lang.Object r2 = r2.invoke(r6, r1)     // Catch:{ Exception -> 0x0049 }
            if (r2 != r4) goto L_0x07f3
            return r4
        L_0x07f3:
            r2 = r5
            r3 = r8
            r6 = r9
        L_0x07f6:
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r5 = r3.jsonRpcInteractor     // Catch:{ Exception -> 0x0452 }
            com.reown.sign.common.exceptions.PeerError r7 = com.reown.sign.engine.model.mapper.EngineMapperKt.toPeerError(r0)     // Catch:{ Exception -> 0x0452 }
            r11 = 0
            r12 = 0
            r13 = 120(0x78, float:1.68E-43)
            r14 = 0
            r9 = 0
            r10 = 0
            r8 = r2
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)     // Catch:{ Exception -> 0x0452 }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x0452 }
            return r0
        L_0x080c:
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionProposeParams r6 = r1.$payloadParams     // Catch:{ Exception -> 0x0049 }
            java.util.Map r6 = r6.getProperties()     // Catch:{ Exception -> 0x0049 }
            if (r6 == 0) goto L_0x08c1
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionProposeParams r7 = r1.$payloadParams     // Catch:{ Exception -> 0x0049 }
            com.reown.sign.engine.use_case.requests.OnSessionProposalUseCase r8 = r1.this$0     // Catch:{ Exception -> 0x0049 }
            com.reown.android.internal.common.model.WCRequest r9 = r1.$request     // Catch:{ Exception -> 0x0049 }
            java.util.Map r7 = r7.getProperties()     // Catch:{ Exception -> 0x0049 }
            boolean r10 = r7.isEmpty()     // Catch:{ Exception -> 0x0049 }
            if (r10 == 0) goto L_0x08bf
            com.reown.sign.engine.model.ValidationError$InvalidSessionProperties r0 = com.reown.sign.engine.model.ValidationError.InvalidSessionProperties.INSTANCE     // Catch:{ Exception -> 0x0049 }
            com.reown.foundation.util.Logger r10 = r8.logger     // Catch:{ Exception -> 0x0049 }
            java.lang.String r11 = r0.getMessage()     // Catch:{ Exception -> 0x0049 }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0049 }
            r13.<init>(r2)     // Catch:{ Exception -> 0x0049 }
            r13.append(r11)     // Catch:{ Exception -> 0x0049 }
            java.lang.String r2 = r13.toString()     // Catch:{ Exception -> 0x0049 }
            r10.error((java.lang.String) r2)     // Catch:{ Exception -> 0x0049 }
            com.reown.android.pulse.domain.InsertTelemetryEventUseCase r2 = r8.insertEventUseCase     // Catch:{ Exception -> 0x0049 }
            com.reown.android.pulse.model.properties.Props r10 = new com.reown.android.pulse.model.properties.Props     // Catch:{ Exception -> 0x0049 }
            java.lang.String r19 = "SESSION_PROPERTIES_VALIDATION_FAILURE"
            com.reown.android.pulse.model.properties.Properties r11 = new com.reown.android.pulse.model.properties.Properties     // Catch:{ Exception -> 0x0049 }
            com.reown.foundation.common.model.Topic r13 = r9.getTopic()     // Catch:{ Exception -> 0x0049 }
            java.lang.String r28 = r13.getValue()     // Catch:{ Exception -> 0x0049 }
            r31 = 0
            r32 = 0
            r33 = 3967(0xf7f, float:5.559E-42)
            r34 = 0
            r21 = 0
            r22 = 0
            r23 = 0
            r24 = 0
            r25 = 0
            r26 = 0
            r27 = 0
            r29 = 0
            r30 = 0
            r20 = r11
            r20.<init>(r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34)     // Catch:{ Exception -> 0x0049 }
            r22 = 0
            r18 = 0
            r21 = 1
            r17 = r10
            r20 = r11
            r17.<init>(r18, r19, r20, r21, r22)     // Catch:{ Exception -> 0x0049 }
            r1.L$0 = r5     // Catch:{ Exception -> 0x0049 }
            r1.L$1 = r8     // Catch:{ Exception -> 0x0049 }
            r1.L$2 = r9     // Catch:{ Exception -> 0x0049 }
            java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r6)     // Catch:{ Exception -> 0x0049 }
            r1.L$3 = r6     // Catch:{ Exception -> 0x0049 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r3)     // Catch:{ Exception -> 0x0049 }
            r1.L$4 = r3     // Catch:{ Exception -> 0x0049 }
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)     // Catch:{ Exception -> 0x0049 }
            r1.L$5 = r3     // Catch:{ Exception -> 0x0049 }
            r1.L$6 = r0     // Catch:{ Exception -> 0x0049 }
            r1.I$0 = r12     // Catch:{ Exception -> 0x0049 }
            r1.I$1 = r12     // Catch:{ Exception -> 0x0049 }
            r1.I$2 = r12     // Catch:{ Exception -> 0x0049 }
            r3 = 11
            r1.label = r3     // Catch:{ Exception -> 0x0049 }
            java.lang.Object r2 = r2.invoke(r10, r1)     // Catch:{ Exception -> 0x0049 }
            if (r2 != r4) goto L_0x08a6
            return r4
        L_0x08a6:
            r2 = r5
            r3 = r8
            r6 = r9
        L_0x08a9:
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r5 = r3.jsonRpcInteractor     // Catch:{ Exception -> 0x0452 }
            com.reown.sign.common.exceptions.PeerError r7 = com.reown.sign.engine.model.mapper.EngineMapperKt.toPeerError(r0)     // Catch:{ Exception -> 0x0452 }
            r11 = 0
            r12 = 0
            r13 = 120(0x78, float:1.68E-43)
            r14 = 0
            r9 = 0
            r10 = 0
            r8 = r2
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)     // Catch:{ Exception -> 0x0452 }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x0452 }
            return r0
        L_0x08bf:
            kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x0049 }
        L_0x08c1:
            com.reown.sign.engine.use_case.requests.OnSessionProposalUseCase r2 = r1.this$0     // Catch:{ Exception -> 0x0049 }
            com.reown.sign.storage.proposal.ProposalStorageRepository r2 = r2.proposalStorageRepository     // Catch:{ Exception -> 0x0049 }
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionProposeParams r3 = r1.$payloadParams     // Catch:{ Exception -> 0x0049 }
            com.reown.android.internal.common.model.WCRequest r6 = r1.$request     // Catch:{ Exception -> 0x0049 }
            com.reown.foundation.common.model.Topic r6 = r6.getTopic()     // Catch:{ Exception -> 0x0049 }
            com.reown.android.internal.common.model.WCRequest r7 = r1.$request     // Catch:{ Exception -> 0x0049 }
            long r7 = r7.getId()     // Catch:{ Exception -> 0x0049 }
            com.reown.sign.common.model.vo.proposal.ProposalVO r3 = com.reown.sign.engine.model.mapper.EngineMapperKt.toVO(r3, r6, r7)     // Catch:{ Exception -> 0x0049 }
            r2.insertProposal$sign_release(r3)     // Catch:{ Exception -> 0x0049 }
            com.reown.sign.engine.use_case.requests.OnSessionProposalUseCase r2 = r1.this$0     // Catch:{ Exception -> 0x0049 }
            com.reown.android.pairing.handler.PairingControllerInterface r2 = r2.pairingController     // Catch:{ Exception -> 0x0049 }
            com.reown.android.Core$Params$RequestReceived r3 = new com.reown.android.Core$Params$RequestReceived     // Catch:{ Exception -> 0x0049 }
            com.reown.android.internal.common.model.WCRequest r6 = r1.$request     // Catch:{ Exception -> 0x0049 }
            com.reown.foundation.common.model.Topic r6 = r6.getTopic()     // Catch:{ Exception -> 0x0049 }
            java.lang.String r6 = r6.getValue()     // Catch:{ Exception -> 0x0049 }
            r3.<init>(r6)     // Catch:{ Exception -> 0x0049 }
            r6 = 0
            r7 = 2
            com.reown.android.pairing.handler.PairingControllerInterface.setRequestReceived$default(r2, r3, r6, r7, r6)     // Catch:{ Exception -> 0x0049 }
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionProposeParams r2 = r1.$payloadParams     // Catch:{ Exception -> 0x0049 }
            com.reown.android.internal.common.model.SessionProposer r2 = r2.getProposer()     // Catch:{ Exception -> 0x0049 }
            com.reown.android.internal.common.model.AppMetaData r2 = r2.getMetadata()     // Catch:{ Exception -> 0x0049 }
            java.lang.String r8 = r2.getUrl()     // Catch:{ Exception -> 0x0049 }
            com.reown.sign.engine.use_case.requests.OnSessionProposalUseCase r2 = r1.this$0     // Catch:{ Exception -> 0x0049 }
            com.reown.foundation.util.Logger r2 = r2.logger     // Catch:{ Exception -> 0x0049 }
            long r6 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x0049 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0049 }
            r3.<init>(r0)     // Catch:{ Exception -> 0x0049 }
            r3.append(r6)     // Catch:{ Exception -> 0x0049 }
            java.lang.String r0 = r3.toString()     // Catch:{ Exception -> 0x0049 }
            r2.log((java.lang.String) r0)     // Catch:{ Exception -> 0x0049 }
            com.reown.sign.engine.use_case.requests.OnSessionProposalUseCase r0 = r1.this$0     // Catch:{ Exception -> 0x0049 }
            com.reown.android.verify.domain.ResolveAttestationIdUseCase r6 = r0.resolveAttestationIdUseCase     // Catch:{ Exception -> 0x0049 }
            com.reown.android.internal.common.model.WCRequest r7 = r1.$request     // Catch:{ Exception -> 0x0049 }
            com.reown.android.internal.common.model.TransportType r0 = r7.getTransportType()     // Catch:{ Exception -> 0x0049 }
            com.reown.android.internal.common.model.TransportType r2 = com.reown.android.internal.common.model.TransportType.LINK_MODE     // Catch:{ Exception -> 0x0049 }
            if (r0 != r2) goto L_0x092f
            r11 = 1
            goto L_0x0930
        L_0x092f:
            r11 = r12
        L_0x0930:
            java.lang.Boolean r9 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r11)     // Catch:{ Exception -> 0x0049 }
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionProposeParams r0 = r1.$payloadParams     // Catch:{ Exception -> 0x0049 }
            com.reown.android.internal.common.model.SessionProposer r0 = r0.getProposer()     // Catch:{ Exception -> 0x0049 }
            com.reown.android.internal.common.model.AppMetaData r0 = r0.getMetadata()     // Catch:{ Exception -> 0x0049 }
            com.reown.android.internal.common.model.Redirect r0 = r0.getRedirect()     // Catch:{ Exception -> 0x0049 }
            if (r0 == 0) goto L_0x094a
            java.lang.String r0 = r0.getUniversal()     // Catch:{ Exception -> 0x0049 }
            r10 = r0
            goto L_0x094b
        L_0x094a:
            r10 = 0
        L_0x094b:
            com.reown.sign.engine.use_case.requests.OnSessionProposalUseCase r0 = r1.this$0     // Catch:{ Exception -> 0x0049 }
            com.reown.sign.common.model.vo.clientsync.session.params.SignParams$SessionProposeParams r2 = r1.$payloadParams     // Catch:{ Exception -> 0x0049 }
            com.reown.android.internal.common.model.WCRequest r3 = r1.$request     // Catch:{ Exception -> 0x0049 }
            com.reown.sign.engine.use_case.requests.a r11 = new com.reown.sign.engine.use_case.requests.a     // Catch:{ Exception -> 0x0049 }
            r11.<init>((com.reown.sign.engine.use_case.requests.OnSessionProposalUseCase) r0, (com.reown.sign.common.model.vo.clientsync.session.params.SignParams.SessionProposeParams) r2, (com.reown.android.internal.common.model.WCRequest) r3)     // Catch:{ Exception -> 0x0049 }
            r6.invoke(r7, r8, r9, r10, r11)     // Catch:{ Exception -> 0x0049 }
            goto L_0x09e1
        L_0x095b:
            com.reown.sign.engine.use_case.requests.OnSessionProposalUseCase r2 = r1.this$0
            com.reown.foundation.util.Logger r2 = r2.logger
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r6 = "Session proposal received error: "
            r3.<init>(r6)
            r3.append(r0)
            java.lang.String r3 = r3.toString()
            r2.error((java.lang.String) r3)
            com.reown.sign.engine.use_case.requests.OnSessionProposalUseCase r2 = r1.this$0
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface r17 = r2.jsonRpcInteractor
            com.reown.android.internal.common.model.WCRequest r2 = r1.$request
            com.reown.android.internal.common.exception.Uncategorized$GenericError r3 = new com.reown.android.internal.common.exception.Uncategorized$GenericError
            java.lang.String r6 = r0.getMessage()
            com.reown.android.internal.common.model.WCRequest r7 = r1.$request
            com.reown.foundation.common.model.Topic r7 = r7.getTopic()
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "Cannot handle a session proposal: "
            r8.<init>(r9)
            r8.append(r6)
            java.lang.String r6 = ", topic: "
            r8.append(r6)
            r8.append(r7)
            java.lang.String r6 = r8.toString()
            r3.<init>(r6)
            r23 = 0
            r24 = 0
            r21 = 0
            r22 = 0
            r25 = 120(0x78, float:1.68E-43)
            r26 = 0
            r18 = r2
            r19 = r3
            r20 = r5
            com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface.respondWithError$default(r17, r18, r19, r20, r21, r22, r23, r24, r25, r26)
            com.reown.sign.engine.use_case.requests.OnSessionProposalUseCase r2 = r1.this$0
            kotlinx.coroutines.flow.MutableSharedFlow r2 = r2._events
            com.reown.android.internal.common.model.SDKError r3 = new com.reown.android.internal.common.model.SDKError
            r3.<init>(r0)
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r5)
            r1.L$0 = r5
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)
            r1.L$1 = r0
            r5 = 0
            r1.L$2 = r5
            r1.L$3 = r5
            r1.L$4 = r5
            r1.L$5 = r5
            r1.L$6 = r5
            r0 = 12
            r1.label = r0
            java.lang.Object r0 = r2.emit(r3, r1)
            if (r0 != r4) goto L_0x09e1
            return r4
        L_0x09e1:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.engine.use_case.requests.OnSessionProposalUseCase$invoke$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((OnSessionProposalUseCase$invoke$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
