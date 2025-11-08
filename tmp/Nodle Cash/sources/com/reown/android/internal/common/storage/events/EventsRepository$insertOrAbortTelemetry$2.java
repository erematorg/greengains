package com.reown.android.internal.common.storage.events;

import com.reown.android.pulse.model.properties.Props;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.internal.common.storage.events.EventsRepository$insertOrAbortTelemetry$2", f = "EventsRepository.kt", i = {}, l = {24}, m = "invokeSuspend", n = {}, s = {})
public final class EventsRepository$insertOrAbortTelemetry$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Props $props;
    int label;
    final /* synthetic */ EventsRepository this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public EventsRepository$insertOrAbortTelemetry$2(EventsRepository eventsRepository, Props props, Continuation<? super EventsRepository$insertOrAbortTelemetry$2> continuation) {
        super(2, continuation);
        this.this$0 = eventsRepository;
        this.$props = props;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new EventsRepository$insertOrAbortTelemetry$2(this.this$0, this.$props, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            if (this.this$0.telemetryEnabled) {
                EventsRepository eventsRepository = this.this$0;
                Props props = this.$props;
                this.label = 1;
                if (eventsRepository.insertOrAbort(props, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else if (i3 == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((EventsRepository$insertOrAbortTelemetry$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
