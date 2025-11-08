package com.reown.android.pulse.domain;

import com.reown.android.internal.common.storage.events.EventsRepository;
import com.reown.android.pulse.data.PulseService;
import com.reown.foundation.util.Logger;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u000e\u0010\r\u001a\u00020\u000eHB¢\u0006\u0002\u0010\u000fJ2\u0010\u0010\u001a\u00020\u000e2\"\u0010\u0011\u001a\u001e\b\u0001\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0012H@¢\u0006\u0002\u0010\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0004\n\u0002\u0010\fR\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/reown/android/pulse/domain/SendBatchEventUseCase;", "", "pulseService", "Lcom/reown/android/pulse/data/PulseService;", "eventsRepository", "Lcom/reown/android/internal/common/storage/events/EventsRepository;", "telemetryEnabled", "Lcom/reown/android/internal/common/model/TelemetryEnabled;", "logger", "Lcom/reown/foundation/util/Logger;", "<init>", "(Lcom/reown/android/pulse/data/PulseService;Lcom/reown/android/internal/common/storage/events/EventsRepository;ZLcom/reown/foundation/util/Logger;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "Z", "invoke", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendEventsInBatches", "getEvents", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "Lcom/reown/android/pulse/model/Event;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSendBatchEventUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SendBatchEventUseCase.kt\ncom/reown/android/pulse/domain/SendBatchEventUseCase\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,59:1\n1563#2:60\n1634#2,3:61\n*S KotlinDebug\n*F\n+ 1 SendBatchEventUseCase.kt\ncom/reown/android/pulse/domain/SendBatchEventUseCase\n*L\n41#1:60\n41#1:61,3\n*E\n"})
public final class SendBatchEventUseCase {
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int LIMIT = 500;
    /* access modifiers changed from: private */
    @NotNull
    public final EventsRepository eventsRepository;
    /* access modifiers changed from: private */
    @NotNull
    public final Logger logger;
    @NotNull
    private final PulseService pulseService;
    /* access modifiers changed from: private */
    public final boolean telemetryEnabled;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/reown/android/pulse/domain/SendBatchEventUseCase$Companion;", "", "<init>", "()V", "LIMIT", "", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public /* synthetic */ SendBatchEventUseCase(PulseService pulseService2, EventsRepository eventsRepository2, boolean z2, Logger logger2, DefaultConstructorMarker defaultConstructorMarker) {
        this(pulseService2, eventsRepository2, z2, logger2);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x009d A[SYNTHETIC, Splitter:B:33:0x009d] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00f3 A[Catch:{ Exception -> 0x0043 }, LOOP:0: B:41:0x00ed->B:43:0x00f3, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x011d A[Catch:{ Exception -> 0x0043 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0156  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object sendEventsInBatches(kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super java.util.List<com.reown.android.pulse.model.Event>>, ? extends java.lang.Object> r18, kotlin.coroutines.Continuation<? super kotlin.Unit> r19) {
        /*
            r17 = this;
            r1 = r17
            r0 = r19
            boolean r2 = r0 instanceof com.reown.android.pulse.domain.SendBatchEventUseCase$sendEventsInBatches$1
            if (r2 == 0) goto L_0x0017
            r2 = r0
            com.reown.android.pulse.domain.SendBatchEventUseCase$sendEventsInBatches$1 r2 = (com.reown.android.pulse.domain.SendBatchEventUseCase$sendEventsInBatches$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r3 & r4
            if (r5 == 0) goto L_0x0017
            int r3 = r3 - r4
            r2.label = r3
            goto L_0x001c
        L_0x0017:
            com.reown.android.pulse.domain.SendBatchEventUseCase$sendEventsInBatches$1 r2 = new com.reown.android.pulse.domain.SendBatchEventUseCase$sendEventsInBatches$1
            r2.<init>(r1, r0)
        L_0x001c:
            java.lang.Object r0 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            r5 = 3
            r6 = 2
            r7 = 0
            r8 = 1
            if (r4 == 0) goto L_0x0073
            if (r4 == r8) goto L_0x0069
            if (r4 == r6) goto L_0x0050
            if (r4 != r5) goto L_0x0048
            int r4 = r2.I$0
            java.lang.Object r9 = r2.L$2
            retrofit2.Response r9 = (retrofit2.Response) r9
            java.lang.Object r9 = r2.L$1
            java.util.List r9 = (java.util.List) r9
            java.lang.Object r9 = r2.L$0
            kotlin.jvm.functions.Function1 r9 = (kotlin.jvm.functions.Function1) r9
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ Exception -> 0x0043 }
            goto L_0x0139
        L_0x0043:
            r0 = move-exception
            r4 = r0
            r0 = r9
            goto L_0x013c
        L_0x0048:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0050:
            int r4 = r2.I$0
            java.lang.Object r9 = r2.L$1
            java.util.List r9 = (java.util.List) r9
            java.lang.Object r10 = r2.L$0
            kotlin.jvm.functions.Function1 r10 = (kotlin.jvm.functions.Function1) r10
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ Exception -> 0x0064 }
            r16 = r10
            r10 = r9
            r9 = r16
            goto L_0x00d3
        L_0x0064:
            r0 = move-exception
            r4 = r0
            r0 = r10
            goto L_0x013c
        L_0x0069:
            int r4 = r2.I$0
            java.lang.Object r9 = r2.L$0
            kotlin.jvm.functions.Function1 r9 = (kotlin.jvm.functions.Function1) r9
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x0092
        L_0x0073:
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r18
            r4 = r8
        L_0x0079:
            if (r4 == 0) goto L_0x0156
            r2.L$0 = r0
            r9 = 0
            r2.L$1 = r9
            r2.L$2 = r9
            r2.I$0 = r4
            r2.label = r8
            java.lang.Object r9 = r0.invoke(r2)
            if (r9 != r3) goto L_0x008d
            return r3
        L_0x008d:
            r16 = r9
            r9 = r0
            r0 = r16
        L_0x0092:
            java.util.List r0 = (java.util.List) r0
            r10 = r0
            java.util.Collection r10 = (java.util.Collection) r10
            boolean r10 = r10.isEmpty()
            if (r10 != 0) goto L_0x0138
            com.reown.foundation.util.Logger r10 = r1.logger     // Catch:{ Exception -> 0x0043 }
            int r11 = r0.size()     // Catch:{ Exception -> 0x0043 }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0043 }
            r12.<init>()     // Catch:{ Exception -> 0x0043 }
            java.lang.String r13 = "Sending batch events: "
            r12.append(r13)     // Catch:{ Exception -> 0x0043 }
            r12.append(r11)     // Catch:{ Exception -> 0x0043 }
            java.lang.String r11 = r12.toString()     // Catch:{ Exception -> 0x0043 }
            r10.log((java.lang.String) r11)     // Catch:{ Exception -> 0x0043 }
            com.reown.android.pulse.data.PulseService r10 = r1.pulseService     // Catch:{ Exception -> 0x0043 }
            com.reown.android.pulse.model.SDKType r11 = com.reown.android.pulse.model.SDKType.EVENTS     // Catch:{ Exception -> 0x0043 }
            java.lang.String r11 = r11.getType()     // Catch:{ Exception -> 0x0043 }
            r2.L$0 = r9     // Catch:{ Exception -> 0x0043 }
            r2.L$1 = r0     // Catch:{ Exception -> 0x0043 }
            r2.I$0 = r4     // Catch:{ Exception -> 0x0043 }
            r2.label = r6     // Catch:{ Exception -> 0x0043 }
            java.lang.Object r10 = r10.sendEventBatch(r11, r0, r2)     // Catch:{ Exception -> 0x0043 }
            if (r10 != r3) goto L_0x00ce
            return r3
        L_0x00ce:
            r16 = r10
            r10 = r0
            r0 = r16
        L_0x00d3:
            retrofit2.Response r0 = (retrofit2.Response) r0     // Catch:{ Exception -> 0x0043 }
            boolean r11 = r0.isSuccessful()     // Catch:{ Exception -> 0x0043 }
            if (r11 == 0) goto L_0x011e
            com.reown.android.internal.common.storage.events.EventsRepository r11 = r1.eventsRepository     // Catch:{ Exception -> 0x0043 }
            r12 = r10
            java.lang.Iterable r12 = (java.lang.Iterable) r12     // Catch:{ Exception -> 0x0043 }
            java.util.ArrayList r13 = new java.util.ArrayList     // Catch:{ Exception -> 0x0043 }
            int r14 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r12, 10)     // Catch:{ Exception -> 0x0043 }
            r13.<init>(r14)     // Catch:{ Exception -> 0x0043 }
            java.util.Iterator r12 = r12.iterator()     // Catch:{ Exception -> 0x0043 }
        L_0x00ed:
            boolean r14 = r12.hasNext()     // Catch:{ Exception -> 0x0043 }
            if (r14 == 0) goto L_0x0105
            java.lang.Object r14 = r12.next()     // Catch:{ Exception -> 0x0043 }
            com.reown.android.pulse.model.Event r14 = (com.reown.android.pulse.model.Event) r14     // Catch:{ Exception -> 0x0043 }
            long r14 = r14.getEventId()     // Catch:{ Exception -> 0x0043 }
            java.lang.Long r14 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r14)     // Catch:{ Exception -> 0x0043 }
            r13.add(r14)     // Catch:{ Exception -> 0x0043 }
            goto L_0x00ed
        L_0x0105:
            r2.L$0 = r9     // Catch:{ Exception -> 0x0043 }
            java.lang.Object r10 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r10)     // Catch:{ Exception -> 0x0043 }
            r2.L$1 = r10     // Catch:{ Exception -> 0x0043 }
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)     // Catch:{ Exception -> 0x0043 }
            r2.L$2 = r0     // Catch:{ Exception -> 0x0043 }
            r2.I$0 = r4     // Catch:{ Exception -> 0x0043 }
            r2.label = r5     // Catch:{ Exception -> 0x0043 }
            java.lang.Object r0 = r11.deleteByIds(r13, r2)     // Catch:{ Exception -> 0x0043 }
            if (r0 != r3) goto L_0x0139
            return r3
        L_0x011e:
            com.reown.foundation.util.Logger r0 = r1.logger     // Catch:{ Exception -> 0x0043 }
            int r4 = r10.size()     // Catch:{ Exception -> 0x0043 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0043 }
            r10.<init>()     // Catch:{ Exception -> 0x0043 }
            java.lang.String r11 = "Failed to send events: "
            r10.append(r11)     // Catch:{ Exception -> 0x0043 }
            r10.append(r4)     // Catch:{ Exception -> 0x0043 }
            java.lang.String r4 = r10.toString()     // Catch:{ Exception -> 0x0043 }
            r0.log((java.lang.String) r4)     // Catch:{ Exception -> 0x0043 }
        L_0x0138:
            r4 = r7
        L_0x0139:
            r0 = r9
            goto L_0x0079
        L_0x013c:
            com.reown.foundation.util.Logger r9 = r1.logger
            java.lang.String r4 = r4.getMessage()
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r11 = "Error sending batch events: "
            r10.<init>(r11)
            r10.append(r4)
            java.lang.String r4 = r10.toString()
            r9.error((java.lang.String) r4)
            r4 = r7
            goto L_0x0079
        L_0x0156:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.pulse.domain.SendBatchEventUseCase.sendEventsInBatches(kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public final Object invoke(@NotNull Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new SendBatchEventUseCase$invoke$2(this, (Continuation<? super SendBatchEventUseCase$invoke$2>) null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    private SendBatchEventUseCase(PulseService pulseService2, EventsRepository eventsRepository2, boolean z2, Logger logger2) {
        Intrinsics.checkNotNullParameter(pulseService2, "pulseService");
        Intrinsics.checkNotNullParameter(eventsRepository2, "eventsRepository");
        Intrinsics.checkNotNullParameter(logger2, "logger");
        this.pulseService = pulseService2;
        this.eventsRepository = eventsRepository2;
        this.telemetryEnabled = z2;
        this.logger = logger2;
    }
}
