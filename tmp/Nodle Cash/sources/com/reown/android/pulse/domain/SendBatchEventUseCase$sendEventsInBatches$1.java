package com.reown.android.pulse.domain;

import com.reown.android.pulse.model.Event;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.pulse.domain.SendBatchEventUseCase", f = "SendBatchEventUseCase.kt", i = {0, 0, 1, 1, 1, 2, 2, 2, 2}, l = {35, 39, 41}, m = "sendEventsInBatches", n = {"getEvents", "continueProcessing", "getEvents", "events", "continueProcessing", "getEvents", "events", "response", "continueProcessing"}, s = {"L$0", "I$0", "L$0", "L$1", "I$0", "L$0", "L$1", "L$2", "I$0"})
public final class SendBatchEventUseCase$sendEventsInBatches$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SendBatchEventUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SendBatchEventUseCase$sendEventsInBatches$1(SendBatchEventUseCase sendBatchEventUseCase, Continuation<? super SendBatchEventUseCase$sendEventsInBatches$1> continuation) {
        super(continuation);
        this.this$0 = sendBatchEventUseCase;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.sendEventsInBatches((Function1<? super Continuation<? super List<Event>>, ? extends Object>) null, this);
    }
}
