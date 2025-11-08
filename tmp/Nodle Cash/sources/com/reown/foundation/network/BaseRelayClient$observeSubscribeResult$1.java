package com.reown.foundation.network;

import androidx.compose.animation.core.a;
import com.reown.foundation.network.model.Relay;
import com.reown.foundation.network.model.RelayDTO;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.TimeoutCancellationException;
import kotlinx.coroutines.TimeoutKt;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.foundation.network.BaseRelayClient$observeSubscribeResult$1", f = "BaseRelayClient.kt", i = {0}, l = {295}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
public final class BaseRelayClient$observeSubscribeResult$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $id;
    final /* synthetic */ Function1<Result<Relay.Model.Call.Subscribe.Acknowledgement>, Unit> $onResult;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ BaseRelayClient this$0;

    @SourceDebugExtension({"SMAP\nBaseRelayClient.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BaseRelayClient.kt\ncom/reown/foundation/network/BaseRelayClient$observeSubscribeResult$1$1\n+ 2 Transform.kt\nkotlinx/coroutines/flow/FlowKt__TransformKt\n+ 3 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n+ 4 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt\n*L\n1#1,533:1\n35#2:534\n20#2:535\n22#2:539\n20#2:540\n22#2:544\n50#3:536\n55#3:538\n50#3:541\n55#3:543\n106#4:537\n106#4:542\n*S KotlinDebug\n*F\n+ 1 BaseRelayClient.kt\ncom/reown/foundation/network/BaseRelayClient$observeSubscribeResult$1$1\n*L\n299#1:534\n299#1:535\n299#1:539\n301#1:540\n301#1:544\n299#1:536\n299#1:538\n301#1:541\n301#1:543\n299#1:537\n301#1:542\n*E\n"})
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/reown/foundation/network/model/RelayDTO$Subscribe$Result;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.reown.foundation.network.BaseRelayClient$observeSubscribeResult$1$1", f = "BaseRelayClient.kt", i = {}, l = {302}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.reown.foundation.network.BaseRelayClient$observeSubscribeResult$1$1  reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super RelayDTO.Subscribe.Result>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(baseRelayClient, j2, function1, continuation);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i3 = this.label;
            if (i3 == 0) {
                ResultKt.throwOnFailure(obj);
                if (baseRelayClient.isLoggingEnabled()) {
                    long j2 = j2;
                    long currentTimeMillis = System.currentTimeMillis();
                    StringBuilder u3 = a.u("ObserveSubscribeResult: ", j2, "; timestamp: ");
                    u3.append(currentTimeMillis);
                    System.out.println(u3.toString());
                }
                MutableSharedFlow access$getResultState$p = baseRelayClient.resultState;
                final BaseRelayClient baseRelayClient = baseRelayClient;
                BaseRelayClient$observeSubscribeResult$1$1$invokeSuspend$$inlined$filterIsInstance$1 baseRelayClient$observeSubscribeResult$1$1$invokeSuspend$$inlined$filterIsInstance$1 = new BaseRelayClient$observeSubscribeResult$1$1$invokeSuspend$$inlined$filterIsInstance$1(FlowKt.onEach(access$getResultState$p, new Function2<RelayDTO, Continuation<? super Unit>, Object>((Continuation<? super AnonymousClass1>) null) {
                    /* synthetic */ Object L$0;
                    int label;

                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        AnonymousClass1 r02 = 

                        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                        public BaseRelayClient$observeSubscribeResult$1(Function1<? super Result<Relay.Model.Call.Subscribe.Acknowledgement>, Unit> function1, BaseRelayClient baseRelayClient, long j2, Continuation<? super BaseRelayClient$observeSubscribeResult$1> continuation) {
                            super(2, continuation);
                            this.$onResult = function1;
                            this.this$0 = baseRelayClient;
                            this.$id = j2;
                        }

                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            BaseRelayClient$observeSubscribeResult$1 baseRelayClient$observeSubscribeResult$1 = new BaseRelayClient$observeSubscribeResult$1(this.$onResult, this.this$0, this.$id, continuation);
                            baseRelayClient$observeSubscribeResult$1.L$0 = obj;
                            return baseRelayClient$observeSubscribeResult$1;
                        }

                        public final Object invokeSuspend(Object obj) {
                            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            int i3 = this.label;
                            if (i3 == 0) {
                                ResultKt.throwOnFailure(obj);
                                final BaseRelayClient baseRelayClient = this.this$0;
                                final long j2 = this.$id;
                                final Function1<Result<Relay.Model.Call.Subscribe.Acknowledgement>, Unit> function1 = this.$onResult;
                                AnonymousClass1 r4 = new AnonymousClass1((Continuation<? super AnonymousClass1>) null);
                                this.L$0 = coroutineScope;
                                this.label = 1;
                                if (TimeoutKt.withTimeout(60000, r4, this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            } else if (i3 == 1) {
                                try {
                                    ResultKt.throwOnFailure(obj);
                                } catch (TimeoutCancellationException e3) {
                                    Function1<Result<Relay.Model.Call.Subscribe.Acknowledgement>, Unit> function12 = this.$onResult;
                                    Result.Companion companion = Result.Companion;
                                    function12.invoke(Result.m8978boximpl(Result.m8979constructorimpl(ResultKt.createFailure(e3))));
                                    this.this$0.cancelJobIfActive(coroutineScope);
                                } catch (Exception e4) {
                                    Function1<Result<Relay.Model.Call.Subscribe.Acknowledgement>, Unit> function13 = this.$onResult;
                                    Result.Companion companion2 = Result.Companion;
                                    function13.invoke(Result.m8978boximpl(Result.m8979constructorimpl(ResultKt.createFailure(e4))));
                                    this.this$0.cancelJobIfActive(coroutineScope);
                                }
                            } else {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            return Unit.INSTANCE;
                        }

                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((BaseRelayClient$observeSubscribeResult$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }
                    }
