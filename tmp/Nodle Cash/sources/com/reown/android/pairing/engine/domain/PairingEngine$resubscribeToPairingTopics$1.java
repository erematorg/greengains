package com.reown.android.pairing.engine.domain;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n"}, d2 = {"<anonymous>", "", "it", ""}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.pairing.engine.domain.PairingEngine$resubscribeToPairingTopics$1", f = "PairingEngine.kt", i = {}, l = {307}, m = "invokeSuspend", n = {}, s = {})
public final class PairingEngine$resubscribeToPairingTopics$1 extends SuspendLambda implements Function2<Object, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ PairingEngine this$0;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.reown.android.pairing.engine.domain.PairingEngine$resubscribeToPairingTopics$1$1", f = "PairingEngine.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.reown.android.pairing.engine.domain.PairingEngine$resubscribeToPairingTopics$1$1  reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Job>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 r02 = new AnonymousClass1(pairingEngine, continuation);
            r02.L$0 = obj;
            return r02;
        }

        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineDispatcher io2 = Dispatchers.getIO();
                final PairingEngine pairingEngine = pairingEngine;
                return BuildersKt__Builders_commonKt.launch$default(coroutineScope, io2, (CoroutineStart) null, new Function2<CoroutineScope, Continuation<? super Unit>, Object>((Continuation<? super AnonymousClass1>) null) {
                    int label;

                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return 

                        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                        public PairingEngine$resubscribeToPairingTopics$1(PairingEngine pairingEngine, Continuation<? super PairingEngine$resubscribeToPairingTopics$1> continuation) {
                            super(2, continuation);
                            this.this$0 = pairingEngine;
                        }

                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new PairingEngine$resubscribeToPairingTopics$1(this.this$0, continuation);
                        }

                        public final Object invokeSuspend(Object obj) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            int i3 = this.label;
                            if (i3 == 0) {
                                ResultKt.throwOnFailure(obj);
                                final PairingEngine pairingEngine = this.this$0;
                                AnonymousClass1 r5 = new AnonymousClass1((Continuation<? super AnonymousClass1>) null);
                                this.label = 1;
                                if (SupervisorKt.supervisorScope(r5, this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            } else if (i3 == 1) {
                                ResultKt.throwOnFailure(obj);
                            } else {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            if (this.this$0.jsonRpcRequestsJob == null) {
                                PairingEngine pairingEngine2 = this.this$0;
                                pairingEngine2.jsonRpcRequestsJob = pairingEngine2.collectJsonRpcRequestsFlow();
                            }
                            return Unit.INSTANCE;
                        }

                        public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
                            return ((PairingEngine$resubscribeToPairingTopics$1) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
                        }
                    }
