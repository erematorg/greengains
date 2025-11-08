package com.reown.android.pulse.domain;

import com.reown.android.internal.utils.Time;
import com.reown.android.pulse.data.PulseService;
import com.reown.android.pulse.model.Event;
import com.reown.android.pulse.model.SDKType;
import com.reown.android.pulse.model.properties.Props;
import com.reown.foundation.util.Logger;
import com.reown.util.UtilFunctionsKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.SupervisorKt;
import retrofit2.Response;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.pulse.domain.SendEventUseCase$send$1", f = "SendEventUseCase.kt", i = {}, l = {27}, m = "invokeSuspend", n = {}, s = {})
public final class SendEventUseCase$send$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Long $id;
    final /* synthetic */ Props $props;
    final /* synthetic */ SDKType $sdkType;
    final /* synthetic */ Long $timestamp;
    int label;
    final /* synthetic */ SendEventUseCase this$0;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.reown.android.pulse.domain.SendEventUseCase$send$1$1", f = "SendEventUseCase.kt", i = {0}, l = {30}, m = "invokeSuspend", n = {"event"}, s = {"L$0"})
    /* renamed from: com.reown.android.pulse.domain.SendEventUseCase$send$1$1  reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(sendEventUseCase, l2, l3, props, sDKType, continuation);
        }

        public final Object invokeSuspend(Object obj) {
            Event event;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i3 = this.label;
            if (i3 == 0) {
                ResultKt.throwOnFailure(obj);
                String access$getBundleId$p = sendEventUseCase.bundleId;
                Long l2 = l2;
                long longValue = l2 != null ? l2.longValue() : Time.getCurrentTimeInSeconds();
                Long l3 = l3;
                Event event2 = new Event(l3 != null ? l3.longValue() : UtilFunctionsKt.generateId(), access$getBundleId$p, longValue, props);
                PulseService access$getPulseService$p = sendEventUseCase.pulseService;
                String type = sDKType.getType();
                this.L$0 = event2;
                this.label = 1;
                Object sendEvent = access$getPulseService$p.sendEvent(type, event2, this);
                if (sendEvent == coroutine_suspended) {
                    return coroutine_suspended;
                }
                event = event2;
                obj = sendEvent;
            } else if (i3 == 1) {
                event = (Event) this.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                } catch (Exception e3) {
                    Logger access$getLogger$p = sendEventUseCase.logger;
                    String type2 = props.getType();
                    access$getLogger$p.error("Failed to send event: " + type2 + ", error: " + e3);
                }
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            if (!((Response) obj).isSuccessful()) {
                Logger access$getLogger$p2 = sendEventUseCase.logger;
                String type3 = event.getProps().getType();
                access$getLogger$p2.error("Failed to send event: " + type3);
            } else {
                Logger access$getLogger$p3 = sendEventUseCase.logger;
                String type4 = event.getProps().getType();
                access$getLogger$p3.log("Event sent successfully: " + type4);
            }
            return Unit.INSTANCE;
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SendEventUseCase$send$1(SendEventUseCase sendEventUseCase, Long l2, Long l3, Props props, SDKType sDKType, Continuation<? super SendEventUseCase$send$1> continuation) {
        super(2, continuation);
        this.this$0 = sendEventUseCase;
        this.$timestamp = l2;
        this.$id = l3;
        this.$props = props;
        this.$sdkType = sDKType;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SendEventUseCase$send$1(this.this$0, this.$timestamp, this.$id, this.$props, this.$sdkType, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            final SendEventUseCase sendEventUseCase = this.this$0;
            final Long l2 = this.$timestamp;
            final Long l3 = this.$id;
            final Props props = this.$props;
            final SDKType sDKType = this.$sdkType;
            AnonymousClass1 r3 = new AnonymousClass1((Continuation<? super AnonymousClass1>) null);
            this.label = 1;
            if (SupervisorKt.supervisorScope(r3, this) == coroutine_suspended) {
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
        return ((SendEventUseCase$send$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
