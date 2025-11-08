package com.reown.sign.engine.use_case.calls;

import com.reown.android.Core;
import com.reown.android.internal.common.model.Expiry;
import com.reown.foundation.common.model.Topic;
import com.reown.sign.common.model.vo.clientsync.session.SignRpc;
import com.reown.sign.engine.model.EngineDO;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.SupervisorKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.calls.SessionAuthenticateUseCase$authenticate$4", f = "SessionAuthenticateUseCase.kt", i = {}, l = {139}, m = "invokeSuspend", n = {}, s = {})
public final class SessionAuthenticateUseCase$authenticate$4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ SignRpc.SessionAuthenticate $authRequest;
    final /* synthetic */ Function1<Throwable, Unit> $onFailure;
    final /* synthetic */ Function1<String, Unit> $onSuccess;
    final /* synthetic */ Map<String, EngineDO.Namespace.Proposal> $optionalNamespaces;
    final /* synthetic */ Core.Model.Pairing $pairing;
    final /* synthetic */ Expiry $requestExpiry;
    final /* synthetic */ Topic $responseTopic;
    int label;
    final /* synthetic */ SessionAuthenticateUseCase this$0;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.reown.sign.engine.use_case.calls.SessionAuthenticateUseCase$authenticate$4$1", f = "SessionAuthenticateUseCase.kt", i = {0, 1, 1, 2, 2, 2, 3, 3, 3, 3}, l = {140, 141, 143, 144}, m = "invokeSuspend", n = {"$this$supervisorScope", "$this$supervisorScope", "sessionAuthenticateDeferred", "$this$supervisorScope", "sessionAuthenticateDeferred", "sessionProposeDeferred", "$this$supervisorScope", "sessionAuthenticateDeferred", "sessionProposeDeferred", "sessionAuthenticateResult"}, s = {"L$0", "L$0", "L$1", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3"})
    /* renamed from: com.reown.sign.engine.use_case.calls.SessionAuthenticateUseCase$authenticate$4$1  reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 r02 = new AnonymousClass1(sessionAuthenticateUseCase, pairing, sessionAuthenticate, topic, expiry, map, function1, function12, continuation);
            r02.L$0 = obj;
            return r02;
        }

        /* JADX WARNING: Removed duplicated region for block: B:18:0x008b A[RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:24:0x00e1 A[RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:29:0x00f4  */
        /* JADX WARNING: Removed duplicated region for block: B:30:0x0100  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object invokeSuspend(java.lang.Object r18) {
            /*
                r17 = this;
                r6 = r17
                java.lang.Object r0 = r6.L$0
                r13 = r0
                kotlinx.coroutines.CoroutineScope r13 = (kotlinx.coroutines.CoroutineScope) r13
                java.lang.Object r14 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r0 = r6.label
                r15 = 0
                r12 = 4
                r11 = 3
                r7 = 2
                r1 = 1
                if (r0 == 0) goto L_0x0059
                if (r0 == r1) goto L_0x004d
                if (r0 == r7) goto L_0x003a
                if (r0 == r11) goto L_0x002e
                if (r0 != r12) goto L_0x0026
                java.lang.Object r0 = r6.L$3
                kotlin.ResultKt.throwOnFailure(r18)
                r3 = r0
                r0 = r18
                goto L_0x00e2
            L_0x0026:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
                r0.<init>(r1)
                throw r0
            L_0x002e:
                java.lang.Object r0 = r6.L$2
                java.lang.Object r1 = r6.L$1
                kotlin.ResultKt.throwOnFailure(r18)
                r3 = r18
                r2 = r12
                goto L_0x00b1
            L_0x003a:
                java.lang.Object r0 = r6.L$1
                kotlin.ResultKt.throwOnFailure(r18)
                r1 = r18
                kotlin.Result r1 = (kotlin.Result) r1
                java.lang.Object r1 = r1.m8988unboximpl()
            L_0x0047:
                r16 = r1
                r1 = r0
                r0 = r16
                goto L_0x008c
            L_0x004d:
                kotlin.ResultKt.throwOnFailure(r18)
                r0 = r18
                kotlin.Result r0 = (kotlin.Result) r0
                java.lang.Object r0 = r0.m8988unboximpl()
                goto L_0x0077
            L_0x0059:
                kotlin.ResultKt.throwOnFailure(r18)
                com.reown.sign.engine.use_case.calls.SessionAuthenticateUseCase r0 = r4
                com.reown.android.Core$Model$Pairing r2 = r5
                com.reown.sign.common.model.vo.clientsync.session.SignRpc$SessionAuthenticate r3 = r6
                com.reown.foundation.common.model.Topic r4 = r7
                com.reown.android.internal.common.model.Expiry r5 = r8
                r6.L$0 = r13
                r6.label = r1
                r1 = r2
                r2 = r3
                r3 = r4
                r4 = r5
                r5 = r17
                java.lang.Object r0 = r0.m8893publishSessionAuthenticateDeferredyxL6bBk(r1, r2, r3, r4, r5)
                if (r0 != r14) goto L_0x0077
                return r14
            L_0x0077:
                com.reown.sign.engine.use_case.calls.SessionAuthenticateUseCase r1 = r4
                com.reown.android.Core$Model$Pairing r2 = r5
                java.util.Map<java.lang.String, com.reown.sign.engine.model.EngineDO$Namespace$Proposal> r3 = r9
                com.reown.foundation.common.model.Topic r4 = r7
                r6.L$0 = r13
                r6.L$1 = r0
                r6.label = r7
                java.lang.Object r1 = r1.m8894publishSessionProposeDeferredBWLJW6A(r2, r3, r4, r6)
                if (r1 != r14) goto L_0x0047
                return r14
            L_0x008c:
                com.reown.sign.engine.use_case.calls.SessionAuthenticateUseCase$authenticate$4$1$sessionAuthenticateResult$1 r10 = new com.reown.sign.engine.use_case.calls.SessionAuthenticateUseCase$authenticate$4$1$sessionAuthenticateResult$1
                r10.<init>(r1, r15)
                r2 = 3
                r3 = 0
                r8 = 0
                r9 = 0
                r7 = r13
                r4 = r11
                r11 = r2
                r2 = r12
                r12 = r3
                kotlinx.coroutines.Deferred r3 = kotlinx.coroutines.BuildersKt__Builders_commonKt.async$default(r7, r8, r9, r10, r11, r12)
                r6.L$0 = r13
                java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r1)
                r6.L$1 = r5
                r6.L$2 = r0
                r6.label = r4
                java.lang.Object r3 = r3.await(r6)
                if (r3 != r14) goto L_0x00b1
                return r14
            L_0x00b1:
                kotlin.Result r3 = (kotlin.Result) r3
                java.lang.Object r3 = r3.m8988unboximpl()
                com.reown.sign.engine.use_case.calls.SessionAuthenticateUseCase$authenticate$4$1$sessionProposeResult$1 r10 = new com.reown.sign.engine.use_case.calls.SessionAuthenticateUseCase$authenticate$4$1$sessionProposeResult$1
                r10.<init>(r0, r15)
                r11 = 3
                r12 = 0
                r8 = 0
                r9 = 0
                r7 = r13
                kotlinx.coroutines.Deferred r4 = kotlinx.coroutines.BuildersKt__Builders_commonKt.async$default(r7, r8, r9, r10, r11, r12)
                java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r13)
                r6.L$0 = r5
                java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r1)
                r6.L$1 = r1
                java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)
                r6.L$2 = r0
                r6.L$3 = r3
                r6.label = r2
                java.lang.Object r0 = r4.await(r6)
                if (r0 != r14) goto L_0x00e2
                return r14
            L_0x00e2:
                kotlin.Result r0 = (kotlin.Result) r0
                java.lang.Object r0 = r0.m8988unboximpl()
                boolean r1 = kotlin.Result.m8986isSuccessimpl(r3)
                if (r1 == 0) goto L_0x0100
                boolean r1 = kotlin.Result.m8986isSuccessimpl(r0)
                if (r1 == 0) goto L_0x0100
                kotlin.jvm.functions.Function1<java.lang.String, kotlin.Unit> r0 = r10
                com.reown.android.Core$Model$Pairing r1 = r5
                java.lang.String r1 = r1.getUri()
                r0.invoke(r1)
                goto L_0x013e
            L_0x0100:
                boolean r1 = kotlin.Result.m8985isFailureimpl(r3)
                if (r1 == 0) goto L_0x0119
                kotlin.jvm.functions.Function1<java.lang.Throwable, kotlin.Unit> r0 = r11
                java.lang.Throwable r1 = kotlin.Result.m8982exceptionOrNullimpl(r3)
                if (r1 != 0) goto L_0x0115
                java.lang.Throwable r1 = new java.lang.Throwable
                java.lang.String r2 = "Session authenticate failed"
                r1.<init>(r2)
            L_0x0115:
                r0.invoke(r1)
                goto L_0x013e
            L_0x0119:
                boolean r1 = kotlin.Result.m8985isFailureimpl(r0)
                if (r1 == 0) goto L_0x0132
                kotlin.jvm.functions.Function1<java.lang.Throwable, kotlin.Unit> r1 = r11
                java.lang.Throwable r0 = kotlin.Result.m8982exceptionOrNullimpl(r0)
                if (r0 != 0) goto L_0x012e
                java.lang.Throwable r0 = new java.lang.Throwable
                java.lang.String r2 = "Session proposal as a fallback failed"
                r0.<init>(r2)
            L_0x012e:
                r1.invoke(r0)
                goto L_0x013e
            L_0x0132:
                kotlin.jvm.functions.Function1<java.lang.Throwable, kotlin.Unit> r0 = r11
                java.lang.Throwable r1 = new java.lang.Throwable
                java.lang.String r2 = "Session authenticate failed, please try again"
                r1.<init>(r2)
                r0.invoke(r1)
            L_0x013e:
                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.engine.use_case.calls.SessionAuthenticateUseCase$authenticate$4.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SessionAuthenticateUseCase$authenticate$4(SessionAuthenticateUseCase sessionAuthenticateUseCase, Core.Model.Pairing pairing, SignRpc.SessionAuthenticate sessionAuthenticate, Topic topic, Expiry expiry, Map<String, EngineDO.Namespace.Proposal> map, Function1<? super String, Unit> function1, Function1<? super Throwable, Unit> function12, Continuation<? super SessionAuthenticateUseCase$authenticate$4> continuation) {
        super(2, continuation);
        this.this$0 = sessionAuthenticateUseCase;
        this.$pairing = pairing;
        this.$authRequest = sessionAuthenticate;
        this.$responseTopic = topic;
        this.$requestExpiry = expiry;
        this.$optionalNamespaces = map;
        this.$onSuccess = function1;
        this.$onFailure = function12;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SessionAuthenticateUseCase$authenticate$4(this.this$0, this.$pairing, this.$authRequest, this.$responseTopic, this.$requestExpiry, this.$optionalNamespaces, this.$onSuccess, this.$onFailure, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            final SessionAuthenticateUseCase sessionAuthenticateUseCase = this.this$0;
            final Core.Model.Pairing pairing = this.$pairing;
            final SignRpc.SessionAuthenticate sessionAuthenticate = this.$authRequest;
            final Topic topic = this.$responseTopic;
            final Expiry expiry = this.$requestExpiry;
            final Map<String, EngineDO.Namespace.Proposal> map = this.$optionalNamespaces;
            final Function1<String, Unit> function1 = this.$onSuccess;
            final Function1<Throwable, Unit> function12 = this.$onFailure;
            AnonymousClass1 r3 = new AnonymousClass1((Continuation<? super AnonymousClass1>) null);
            this.label = 1;
            if (SupervisorKt.supervisorScope(r3, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i3 == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SessionAuthenticateUseCase$authenticate$4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
