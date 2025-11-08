package com.reown.android.internal.common.storage.events;

import com.reown.android.pulse.model.Event;
import com.reown.android.pulse.model.properties.Properties;
import com.reown.android.pulse.model.properties.Props;
import com.reown.android.sdk.storage.data.dao.EventQueries;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.internal.common.storage.events.EventsRepository$insertOrAbort$2", f = "EventsRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class EventsRepository$insertOrAbort$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Props $props;
    int label;
    final /* synthetic */ EventsRepository this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public EventsRepository$insertOrAbort$2(EventsRepository eventsRepository, Props props, Continuation<? super EventsRepository$insertOrAbort$2> continuation) {
        super(2, continuation);
        this.this$0 = eventsRepository;
        this.$props = props;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new EventsRepository$insertOrAbort$2(this.this$0, this.$props, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            Event event = new Event(0, this.this$0.bundleId, 0, this.$props, 5, (DefaultConstructorMarker) null);
            EventQueries access$getEventQueries$p = this.this$0.eventQueries;
            Long boxLong = Boxing.boxLong(event.getEventId());
            String bundleId = event.getBundleId();
            long timestamp = event.getTimestamp();
            String event2 = event.getProps().getEvent();
            String type = event.getProps().getType();
            Properties properties = event.getProps().getProperties();
            String topic = properties != null ? properties.getTopic() : null;
            Properties properties2 = event.getProps().getProperties();
            List<String> trace = properties2 != null ? properties2.getTrace() : null;
            Properties properties3 = event.getProps().getProperties();
            Long correlationId = properties3 != null ? properties3.getCorrelationId() : null;
            Properties properties4 = event.getProps().getProperties();
            String clientId = properties4 != null ? properties4.getClientId() : null;
            Properties properties5 = event.getProps().getProperties();
            String direction = properties5 != null ? properties5.getDirection() : null;
            Properties properties6 = event.getProps().getProperties();
            access$getEventQueries$p.insertOrAbort(boxLong, bundleId, timestamp, event2, type, topic, trace, correlationId, clientId, direction, properties6 != null ? properties6.getUserAgent() : null);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((EventsRepository$insertOrAbort$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
