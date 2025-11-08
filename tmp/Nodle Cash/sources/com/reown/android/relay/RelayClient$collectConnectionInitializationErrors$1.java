package com.reown.android.relay;

import com.reown.foundation.network.model.Relay;
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
import kotlinx.coroutines.flow.SharedFlow;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.relay.RelayClient$collectConnectionInitializationErrors$1", f = "RelayClient.kt", i = {}, l = {59}, m = "invokeSuspend", n = {}, s = {})
public final class RelayClient$collectConnectionInitializationErrors$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1<Throwable, Unit> $onError;
    int label;
    final /* synthetic */ RelayClient this$0;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.reown.android.relay.RelayClient$collectConnectionInitializationErrors$1$1", f = "RelayClient.kt", i = {}, l = {61}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.reown.android.relay.RelayClient$collectConnectionInitializationErrors$1$1  reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(relayClient, function1, continuation);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i3 = this.label;
            if (i3 == 0) {
                ResultKt.throwOnFailure(obj);
                SharedFlow<Relay.Model.Event> eventsFlow = relayClient.getEventsFlow();
                final Function1<Throwable, Unit> function1 = function1;
                AnonymousClass1 r12 = new Function2<Relay.Model.Event, Continuation<? super Boolean>, Object>((Continuation<? super AnonymousClass1>) null) {
                    /* synthetic */ Object L$0;
                    int label;

                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        AnonymousClass1 r02 = 

                        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                        public RelayClient$collectConnectionInitializationErrors$1(RelayClient relayClient, Function1<? super Throwable, Unit> function1, Continuation<? super RelayClient$collectConnectionInitializationErrors$1> continuation) {
                            super(2, continuation);
                            this.this$0 = relayClient;
                            this.$onError = function1;
                        }

                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new RelayClient$collectConnectionInitializationErrors$1(this.this$0, this.$onError, continuation);
                        }

                        public final Object invokeSuspend(Object obj) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            int i3 = this.label;
                            if (i3 == 0) {
                                ResultKt.throwOnFailure(obj);
                                final RelayClient relayClient = this.this$0;
                                final Function1<Throwable, Unit> function1 = this.$onError;
                                AnonymousClass1 r6 = new AnonymousClass1((Continuation<? super AnonymousClass1>) null);
                                this.label = 1;
                                if (SupervisorKt.supervisorScope(r6, this) == coroutine_suspended) {
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
                            return ((RelayClient$collectConnectionInitializationErrors$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }
                    }
