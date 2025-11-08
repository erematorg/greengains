package com.reown.android.internal.common.storage.events;

import android.database.sqlite.SQLiteException;
import com.reown.android.pulse.model.Event;
import com.reown.android.pulse.model.properties.Properties;
import com.reown.android.pulse.model.properties.Props;
import com.reown.android.sdk.storage.data.dao.EventDao;
import com.reown.android.sdk.storage.data.dao.EventQueries;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H@¢\u0006\u0002\u0010\u0011J\u0016\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H@¢\u0006\u0002\u0010\u0011J$\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0017H@¢\u0006\u0002\u0010\u0019J$\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0017H@¢\u0006\u0002\u0010\u0019J\u000e\u0010\u001b\u001a\u00020\u000eH@¢\u0006\u0002\u0010\u001cJ\u001c\u0010\u001d\u001a\u00020\u000e2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0014H@¢\u0006\u0002\u0010 J\f\u0010!\u001a\u00020\u0015*\u00020\"H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0004\n\u0002\u0010\fR\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/reown/android/internal/common/storage/events/EventsRepository;", "", "eventQueries", "Lcom/reown/android/sdk/storage/data/dao/EventQueries;", "bundleId", "", "telemetryEnabled", "Lcom/reown/android/internal/common/model/TelemetryEnabled;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "<init>", "(Lcom/reown/android/sdk/storage/data/dao/EventQueries;Ljava/lang/String;ZLkotlinx/coroutines/CoroutineDispatcher;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "Z", "insertOrAbortTelemetry", "", "props", "Lcom/reown/android/pulse/model/properties/Props;", "(Lcom/reown/android/pulse/model/properties/Props;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertOrAbort", "getAllEventsWithLimitAndOffset", "", "Lcom/reown/android/pulse/model/Event;", "limit", "", "offset", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllNonTelemetryEventsWithLimitAndOffset", "deleteAllTelemetry", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteByIds", "eventIds", "", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toEvent", "Lcom/reown/android/sdk/storage/data/dao/EventDao;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nEventsRepository.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EventsRepository.kt\ncom/reown/android/internal/common/storage/events/EventsRepository\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,94:1\n1563#2:95\n1634#2,3:96\n774#2:99\n865#2,2:100\n1563#2:102\n1634#2,3:103\n*S KotlinDebug\n*F\n+ 1 EventsRepository.kt\ncom/reown/android/internal/common/storage/events/EventsRepository\n*L\n51#1:95\n51#1:96,3\n58#1:99\n58#1:100,2\n59#1:102\n59#1:103,3\n*E\n"})
public final class EventsRepository {
    /* access modifiers changed from: private */
    @NotNull
    public final String bundleId;
    @NotNull
    private final CoroutineDispatcher dispatcher;
    /* access modifiers changed from: private */
    @NotNull
    public final EventQueries eventQueries;
    /* access modifiers changed from: private */
    public final boolean telemetryEnabled;

    public /* synthetic */ EventsRepository(EventQueries eventQueries2, String str, boolean z2, CoroutineDispatcher coroutineDispatcher, DefaultConstructorMarker defaultConstructorMarker) {
        this(eventQueries2, str, z2, coroutineDispatcher);
    }

    private final Event toEvent(EventDao eventDao) {
        long event_id = eventDao.getEvent_id();
        String bundle_id = eventDao.getBundle_id();
        long timestamp = eventDao.getTimestamp();
        String event_name = eventDao.getEvent_name();
        String type = eventDao.getType();
        String topic = eventDao.getTopic();
        Properties properties = r9;
        Properties properties2 = new Properties((String) null, (String) null, (String) null, (Boolean) null, (String) null, (String) null, eventDao.getTrace(), topic, eventDao.getCorrelation_id(), eventDao.getClient_id(), eventDao.getDirection(), eventDao.getUser_agent(), 63, (DefaultConstructorMarker) null);
        return new Event(event_id, bundle_id, timestamp, new Props(event_name, type, properties));
    }

    @Nullable
    public final Object deleteAllTelemetry(@NotNull Continuation<? super Unit> continuation) throws SQLiteException {
        Object withContext = BuildersKt.withContext(this.dispatcher, new EventsRepository$deleteAllTelemetry$2(this, (Continuation<? super EventsRepository$deleteAllTelemetry$2>) null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    @Nullable
    public final Object deleteByIds(@NotNull List<Long> list, @NotNull Continuation<? super Unit> continuation) throws SQLiteException {
        Object withContext = BuildersKt.withContext(this.dispatcher, new EventsRepository$deleteByIds$2(this, list, (Continuation<? super EventsRepository$deleteByIds$2>) null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x005e A[LOOP:0: B:16:0x0058->B:18:0x005e, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object getAllEventsWithLimitAndOffset(int r9, int r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<com.reown.android.pulse.model.Event>> r11) throws android.database.sqlite.SQLiteException {
        /*
            r8 = this;
            boolean r0 = r11 instanceof com.reown.android.internal.common.storage.events.EventsRepository$getAllEventsWithLimitAndOffset$1
            if (r0 == 0) goto L_0x0013
            r0 = r11
            com.reown.android.internal.common.storage.events.EventsRepository$getAllEventsWithLimitAndOffset$1 r0 = (com.reown.android.internal.common.storage.events.EventsRepository$getAllEventsWithLimitAndOffset$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.reown.android.internal.common.storage.events.EventsRepository$getAllEventsWithLimitAndOffset$1 r0 = new com.reown.android.internal.common.storage.events.EventsRepository$getAllEventsWithLimitAndOffset$1
            r0.<init>(r8, r11)
        L_0x0018:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0031
            if (r2 != r3) goto L_0x0029
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x0049
        L_0x0029:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0031:
            kotlin.ResultKt.throwOnFailure(r11)
            com.reown.android.sdk.storage.data.dao.EventQueries r11 = r8.eventQueries
            long r4 = (long) r9
            long r6 = (long) r10
            app.cash.sqldelight.Query r11 = r11.getAllEventsWithLimitAndOffset(r4, r6)
            r0.I$0 = r9
            r0.I$1 = r10
            r0.label = r3
            java.lang.Object r11 = app.cash.sqldelight.async.coroutines.QueryExtensionsKt.awaitAsList(r11, r0)
            if (r11 != r1) goto L_0x0049
            return r1
        L_0x0049:
            java.lang.Iterable r11 = (java.lang.Iterable) r11
            java.util.ArrayList r9 = new java.util.ArrayList
            int r10 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r11, 10)
            r9.<init>(r10)
            java.util.Iterator r10 = r11.iterator()
        L_0x0058:
            boolean r11 = r10.hasNext()
            if (r11 == 0) goto L_0x006c
            java.lang.Object r11 = r10.next()
            com.reown.android.sdk.storage.data.dao.EventDao r11 = (com.reown.android.sdk.storage.data.dao.EventDao) r11
            com.reown.android.pulse.model.Event r11 = r8.toEvent(r11)
            r9.add(r11)
            goto L_0x0058
        L_0x006c:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.internal.common.storage.events.EventsRepository.getAllEventsWithLimitAndOffset(int, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x007e A[LOOP:1: B:22:0x0078->B:24:0x007e, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object getAllNonTelemetryEventsWithLimitAndOffset(int r9, int r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<com.reown.android.pulse.model.Event>> r11) throws android.database.sqlite.SQLiteException {
        /*
            r8 = this;
            boolean r0 = r11 instanceof com.reown.android.internal.common.storage.events.EventsRepository$getAllNonTelemetryEventsWithLimitAndOffset$1
            if (r0 == 0) goto L_0x0013
            r0 = r11
            com.reown.android.internal.common.storage.events.EventsRepository$getAllNonTelemetryEventsWithLimitAndOffset$1 r0 = (com.reown.android.internal.common.storage.events.EventsRepository$getAllNonTelemetryEventsWithLimitAndOffset$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.reown.android.internal.common.storage.events.EventsRepository$getAllNonTelemetryEventsWithLimitAndOffset$1 r0 = new com.reown.android.internal.common.storage.events.EventsRepository$getAllNonTelemetryEventsWithLimitAndOffset$1
            r0.<init>(r8, r11)
        L_0x0018:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0031
            if (r2 != r3) goto L_0x0029
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x0049
        L_0x0029:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0031:
            kotlin.ResultKt.throwOnFailure(r11)
            com.reown.android.sdk.storage.data.dao.EventQueries r11 = r8.eventQueries
            long r4 = (long) r9
            long r6 = (long) r10
            app.cash.sqldelight.Query r11 = r11.getAllEventsWithLimitAndOffset(r4, r6)
            r0.I$0 = r9
            r0.I$1 = r10
            r0.label = r3
            java.lang.Object r11 = app.cash.sqldelight.async.coroutines.QueryExtensionsKt.awaitAsList(r11, r0)
            if (r11 != r1) goto L_0x0049
            return r1
        L_0x0049:
            java.lang.Iterable r11 = (java.lang.Iterable) r11
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.util.Iterator r10 = r11.iterator()
        L_0x0054:
            boolean r11 = r10.hasNext()
            if (r11 == 0) goto L_0x006b
            java.lang.Object r11 = r10.next()
            r0 = r11
            com.reown.android.sdk.storage.data.dao.EventDao r0 = (com.reown.android.sdk.storage.data.dao.EventDao) r0
            java.lang.Long r0 = r0.getCorrelation_id()
            if (r0 == 0) goto L_0x0054
            r9.add(r11)
            goto L_0x0054
        L_0x006b:
            java.util.ArrayList r10 = new java.util.ArrayList
            int r11 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r9, 10)
            r10.<init>(r11)
            java.util.Iterator r9 = r9.iterator()
        L_0x0078:
            boolean r11 = r9.hasNext()
            if (r11 == 0) goto L_0x008c
            java.lang.Object r11 = r9.next()
            com.reown.android.sdk.storage.data.dao.EventDao r11 = (com.reown.android.sdk.storage.data.dao.EventDao) r11
            com.reown.android.pulse.model.Event r11 = r8.toEvent(r11)
            r10.add(r11)
            goto L_0x0078
        L_0x008c:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.internal.common.storage.events.EventsRepository.getAllNonTelemetryEventsWithLimitAndOffset(int, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public final Object insertOrAbort(@NotNull Props props, @NotNull Continuation<? super Unit> continuation) throws SQLiteException {
        Object withContext = BuildersKt.withContext(this.dispatcher, new EventsRepository$insertOrAbort$2(this, props, (Continuation<? super EventsRepository$insertOrAbort$2>) null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    @Nullable
    public final Object insertOrAbortTelemetry(@NotNull Props props, @NotNull Continuation<? super Unit> continuation) throws SQLiteException {
        Object withContext = BuildersKt.withContext(this.dispatcher, new EventsRepository$insertOrAbortTelemetry$2(this, props, (Continuation<? super EventsRepository$insertOrAbortTelemetry$2>) null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    private EventsRepository(EventQueries eventQueries2, String str, boolean z2, CoroutineDispatcher coroutineDispatcher) {
        Intrinsics.checkNotNullParameter(eventQueries2, "eventQueries");
        Intrinsics.checkNotNullParameter(str, "bundleId");
        Intrinsics.checkNotNullParameter(coroutineDispatcher, "dispatcher");
        this.eventQueries = eventQueries2;
        this.bundleId = str;
        this.telemetryEnabled = z2;
        this.dispatcher = coroutineDispatcher;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ EventsRepository(EventQueries eventQueries2, String str, boolean z2, CoroutineDispatcher coroutineDispatcher, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(eventQueries2, str, z2, (i3 & 8) != 0 ? Dispatchers.getIO() : coroutineDispatcher, (DefaultConstructorMarker) null);
    }
}
