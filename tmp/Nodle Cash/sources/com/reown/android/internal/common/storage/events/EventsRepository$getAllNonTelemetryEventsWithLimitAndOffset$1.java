package com.reown.android.internal.common.storage.events;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.internal.common.storage.events.EventsRepository", f = "EventsRepository.kt", i = {0, 0}, l = {57}, m = "getAllNonTelemetryEventsWithLimitAndOffset", n = {"limit", "offset"}, s = {"I$0", "I$1"})
public final class EventsRepository$getAllNonTelemetryEventsWithLimitAndOffset$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ EventsRepository this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public EventsRepository$getAllNonTelemetryEventsWithLimitAndOffset$1(EventsRepository eventsRepository, Continuation<? super EventsRepository$getAllNonTelemetryEventsWithLimitAndOffset$1> continuation) {
        super(continuation);
        this.this$0 = eventsRepository;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getAllNonTelemetryEventsWithLimitAndOffset(0, 0, this);
    }
}
