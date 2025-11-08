package com.reown.android.internal.common.json_rpc.domain.relay;

import com.reown.android.internal.common.storage.push_messages.PushMessagesRepository;
import com.reown.foundation.common.model.Topic;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.SupervisorKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor$unsubscribe$1$1$1", f = "RelayJsonRpcInteractor.kt", i = {}, l = {320}, m = "invokeSuspend", n = {}, s = {})
public final class RelayJsonRpcInteractor$unsubscribe$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function0<Unit> $onSuccess;
    final /* synthetic */ Topic $topic;
    int label;
    final /* synthetic */ RelayJsonRpcInteractor this$0;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor$unsubscribe$1$1$1$1", f = "RelayJsonRpcInteractor.kt", i = {}, l = {323}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor$unsubscribe$1$1$1$1  reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(relayJsonRpcInteractor, topic, function0, continuation);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i3 = this.label;
            if (i3 == 0) {
                ResultKt.throwOnFailure(obj);
                relayJsonRpcInteractor.jsonRpcHistory.deleteRecordsByTopic(topic);
                relayJsonRpcInteractor.subscriptions.remove(topic.getValue());
                PushMessagesRepository access$getPushMessageStorage$p = relayJsonRpcInteractor.pushMessageStorage;
                String value = topic.getValue();
                this.label = 1;
                if (access$getPushMessageStorage$p.deletePushMessagesByTopic(value, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i3 == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            function0.invoke();
            return Unit.INSTANCE;
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RelayJsonRpcInteractor$unsubscribe$1$1$1(RelayJsonRpcInteractor relayJsonRpcInteractor, Topic topic, Function0<Unit> function0, Continuation<? super RelayJsonRpcInteractor$unsubscribe$1$1$1> continuation) {
        super(2, continuation);
        this.this$0 = relayJsonRpcInteractor;
        this.$topic = topic;
        this.$onSuccess = function0;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RelayJsonRpcInteractor$unsubscribe$1$1$1(this.this$0, this.$topic, this.$onSuccess, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            final RelayJsonRpcInteractor relayJsonRpcInteractor = this.this$0;
            final Topic topic = this.$topic;
            final Function0<Unit> function0 = this.$onSuccess;
            AnonymousClass1 r7 = new AnonymousClass1((Continuation<? super AnonymousClass1>) null);
            this.label = 1;
            if (SupervisorKt.supervisorScope(r7, this) == coroutine_suspended) {
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
        return ((RelayJsonRpcInteractor$unsubscribe$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
