package com.reown.sign.engine.domain;

import com.reown.android.internal.common.WalletConnectScopeKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n"}, d2 = {"<anonymous>", "", "it", ""}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.domain.SignEngine$handleRelayRequestsAndResponses$1", f = "SignEngine.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class SignEngine$handleRelayRequestsAndResponses$1 extends SuspendLambda implements Function2<Object, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ SignEngine this$0;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.reown.sign.engine.domain.SignEngine$handleRelayRequestsAndResponses$1$1", f = "SignEngine.kt", i = {}, l = {221}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.reown.sign.engine.domain.SignEngine$handleRelayRequestsAndResponses$1$1  reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(signEngine, continuation);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i3 = this.label;
            if (i3 == 0) {
                ResultKt.throwOnFailure(obj);
                final SignEngine signEngine = signEngine;
                AnonymousClass1 r5 = new Function2<CoroutineScope, Continuation<? super Job>, Object>((Continuation<? super AnonymousClass1>) null) {
                    private /* synthetic */ Object L$0;
                    int label;

                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        AnonymousClass1 r02 = 

                        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                        public SignEngine$handleRelayRequestsAndResponses$1(SignEngine signEngine, Continuation<? super SignEngine$handleRelayRequestsAndResponses$1> continuation) {
                            super(2, continuation);
                            this.this$0 = signEngine;
                        }

                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new SignEngine$handleRelayRequestsAndResponses$1(this.this$0, continuation);
                        }

                        public final Object invokeSuspend(Object obj) {
                            IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            if (this.label == 0) {
                                ResultKt.throwOnFailure(obj);
                                CoroutineScope scope = WalletConnectScopeKt.getScope();
                                final SignEngine signEngine = this.this$0;
                                Job unused = BuildersKt__Builders_commonKt.launch$default(scope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1((Continuation<? super AnonymousClass1>) null), 3, (Object) null);
                                return Unit.INSTANCE;
                            }
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }

                        public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
                            return ((SignEngine$handleRelayRequestsAndResponses$1) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
                        }
                    }
