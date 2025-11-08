package com.reown.android.internal.common.storage.events;

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
@DebugMetadata(c = "com.reown.android.internal.common.storage.events.EventsRepository$deleteAllTelemetry$2", f = "EventsRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class EventsRepository$deleteAllTelemetry$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ EventsRepository this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public EventsRepository$deleteAllTelemetry$2(EventsRepository eventsRepository, Continuation<? super EventsRepository$deleteAllTelemetry$2> continuation) {
        super(2, continuation);
        this.this$0 = eventsRepository;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new EventsRepository$deleteAllTelemetry$2(this.this$0, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0.eventQueries.deleteAllTelemetry();
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((EventsRepository$deleteAllTelemetry$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
