package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import A1.C0219a;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.camera.camera2.interop.d;
import androidx.camera.core.impl.utils.futures.a;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.runtime.EncodedPayload;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.backends.BackendRegistry;
import com.google.android.datatransport.runtime.backends.BackendRequest;
import com.google.android.datatransport.runtime.backends.BackendResponse;
import com.google.android.datatransport.runtime.backends.TransportBackend;
import com.google.android.datatransport.runtime.firebase.transport.ClientMetrics;
import com.google.android.datatransport.runtime.firebase.transport.LogEventDropped;
import com.google.android.datatransport.runtime.logging.Logging;
import com.google.android.datatransport.runtime.scheduling.persistence.ClientHealthMetricsStore;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.scheduling.persistence.PersistedEvent;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import com.google.android.datatransport.runtime.time.Clock;
import com.google.android.datatransport.runtime.time.Monotonic;
import com.google.android.datatransport.runtime.time.WallTime;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
import javax.inject.Inject;

public class Uploader {
    private static final String CLIENT_HEALTH_METRICS_LOG_SOURCE = "GDT_CLIENT_METRICS";
    private static final String LOG_TAG = "Uploader";
    private final BackendRegistry backendRegistry;
    private final ClientHealthMetricsStore clientHealthMetricsStore;
    private final Clock clock;
    private final Context context;
    private final EventStore eventStore;
    private final Executor executor;
    private final SynchronizationGuard guard;
    private final Clock uptimeClock;
    private final WorkScheduler workScheduler;

    @Inject
    public Uploader(Context context2, BackendRegistry backendRegistry2, EventStore eventStore2, WorkScheduler workScheduler2, Executor executor2, SynchronizationGuard synchronizationGuard, @WallTime Clock clock2, @Monotonic Clock clock3, ClientHealthMetricsStore clientHealthMetricsStore2) {
        this.context = context2;
        this.backendRegistry = backendRegistry2;
        this.eventStore = eventStore2;
        this.workScheduler = workScheduler2;
        this.executor = executor2;
        this.guard = synchronizationGuard;
        this.clock = clock2;
        this.uptimeClock = clock3;
        this.clientHealthMetricsStore = clientHealthMetricsStore2;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$logAndUpdateState$2(TransportContext transportContext) {
        return Boolean.valueOf(this.eventStore.hasPendingEventsFor(transportContext));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Iterable lambda$logAndUpdateState$3(TransportContext transportContext) {
        return this.eventStore.loadBatch(transportContext);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$logAndUpdateState$4(Iterable iterable, TransportContext transportContext, long j2) {
        this.eventStore.recordFailure(iterable);
        this.eventStore.recordNextCallTime(transportContext, this.clock.getTime() + j2);
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$logAndUpdateState$5(Iterable iterable) {
        this.eventStore.recordSuccess(iterable);
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$logAndUpdateState$6() {
        this.clientHealthMetricsStore.resetClientMetrics();
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$logAndUpdateState$7(Map map) {
        for (Map.Entry entry : map.entrySet()) {
            this.clientHealthMetricsStore.recordLogEventDropped((long) ((Integer) entry.getValue()).intValue(), LogEventDropped.Reason.INVALID_PAYLOD, (String) entry.getKey());
        }
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$logAndUpdateState$8(TransportContext transportContext, long j2) {
        this.eventStore.recordNextCallTime(transportContext, this.clock.getTime() + j2);
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$upload$0(TransportContext transportContext, int i3) {
        this.workScheduler.schedule(transportContext, i3 + 1);
        return null;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't wrap try/catch for region: R(2:8|9) */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0034, code lost:
        r7.run();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0037, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0022, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        r4.workScheduler.schedule(r5, r6 + 1);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x002b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ void lambda$upload$1(com.google.android.datatransport.runtime.TransportContext r5, int r6, java.lang.Runnable r7) {
        /*
            r4 = this;
            com.google.android.datatransport.runtime.synchronization.SynchronizationGuard r0 = r4.guard     // Catch:{ SynchronizationException -> 0x002b }
            com.google.android.datatransport.runtime.scheduling.persistence.EventStore r1 = r4.eventStore     // Catch:{ SynchronizationException -> 0x002b }
            java.util.Objects.requireNonNull(r1)     // Catch:{ SynchronizationException -> 0x002b }
            A1.a r2 = new A1.a     // Catch:{ SynchronizationException -> 0x002b }
            r3 = 15
            r2.<init>(r1, r3)     // Catch:{ SynchronizationException -> 0x002b }
            r0.runCriticalSection(r2)     // Catch:{ SynchronizationException -> 0x002b }
            boolean r0 = r4.isNetworkAvailable()     // Catch:{ SynchronizationException -> 0x002b }
            if (r0 != 0) goto L_0x0024
            com.google.android.datatransport.runtime.synchronization.SynchronizationGuard r0 = r4.guard     // Catch:{ SynchronizationException -> 0x002b }
            com.appsamurai.storyly.exoplayer2.core.analytics.t r1 = new com.appsamurai.storyly.exoplayer2.core.analytics.t     // Catch:{ SynchronizationException -> 0x002b }
            r1.<init>(r4, r5, r6)     // Catch:{ SynchronizationException -> 0x002b }
            r0.runCriticalSection(r1)     // Catch:{ SynchronizationException -> 0x002b }
            goto L_0x0027
        L_0x0022:
            r4 = move-exception
            goto L_0x0034
        L_0x0024:
            r4.logAndUpdateState(r5, r6)     // Catch:{ SynchronizationException -> 0x002b }
        L_0x0027:
            r7.run()
            goto L_0x0033
        L_0x002b:
            com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler r4 = r4.workScheduler     // Catch:{ all -> 0x0022 }
            int r6 = r6 + 1
            r4.schedule(r5, r6)     // Catch:{ all -> 0x0022 }
            goto L_0x0027
        L_0x0033:
            return
        L_0x0034:
            r7.run()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader.lambda$upload$1(com.google.android.datatransport.runtime.TransportContext, int, java.lang.Runnable):void");
    }

    @VisibleForTesting
    public EventInternal createMetricsEvent(TransportBackend transportBackend) {
        SynchronizationGuard synchronizationGuard = this.guard;
        ClientHealthMetricsStore clientHealthMetricsStore2 = this.clientHealthMetricsStore;
        Objects.requireNonNull(clientHealthMetricsStore2);
        return transportBackend.decorate(EventInternal.builder().setEventMillis(this.clock.getTime()).setUptimeMillis(this.uptimeClock.getTime()).setTransportName(CLIENT_HEALTH_METRICS_LOG_SOURCE).setEncodedPayload(new EncodedPayload(Encoding.of("proto"), ((ClientMetrics) synchronizationGuard.runCriticalSection(new C0219a(clientHealthMetricsStore2, 16))).toByteArray())).build());
    }

    public boolean isNetworkAvailable() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @CanIgnoreReturnValue
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public BackendResponse logAndUpdateState(TransportContext transportContext, int i3) {
        BackendResponse send;
        TransportBackend transportBackend = this.backendRegistry.get(transportContext.getBackendName());
        long j2 = 0;
        BackendResponse ok = BackendResponse.ok(0);
        while (true) {
            long j3 = j2;
            while (((Boolean) this.guard.runCriticalSection(new b(this, transportContext, 0))).booleanValue()) {
                Iterable<PersistedEvent> iterable = (Iterable) this.guard.runCriticalSection(new b(this, transportContext, 1));
                if (!iterable.iterator().hasNext()) {
                    return ok;
                }
                if (transportBackend == null) {
                    Logging.d(LOG_TAG, "Unknown backend for %s, deleting event batch for it...", (Object) transportContext);
                    send = BackendResponse.fatalError();
                } else {
                    ArrayList arrayList = new ArrayList();
                    for (PersistedEvent event : iterable) {
                        arrayList.add(event.getEvent());
                    }
                    if (transportContext.shouldUploadClientHealthMetrics()) {
                        arrayList.add(createMetricsEvent(transportBackend));
                    }
                    send = transportBackend.send(BackendRequest.builder().setEvents(arrayList).setExtras(transportContext.getExtras()).build());
                }
                ok = send;
                if (ok.getStatus() == BackendResponse.Status.TRANSIENT_ERROR) {
                    this.guard.runCriticalSection(new c(this, (Iterable) iterable, transportContext, j3));
                    this.workScheduler.schedule(transportContext, i3 + 1, true);
                    return ok;
                }
                this.guard.runCriticalSection(new d(this, iterable, 23));
                if (ok.getStatus() == BackendResponse.Status.OK) {
                    j2 = Math.max(j3, ok.getNextRequestWaitMillis());
                    if (transportContext.shouldUploadClientHealthMetrics()) {
                        this.guard.runCriticalSection(new C0219a(this, 17));
                    }
                } else if (ok.getStatus() == BackendResponse.Status.INVALID_PAYLOAD) {
                    HashMap hashMap = new HashMap();
                    for (PersistedEvent event2 : iterable) {
                        String transportName = event2.getEvent().getTransportName();
                        if (!hashMap.containsKey(transportName)) {
                            hashMap.put(transportName, 1);
                        } else {
                            hashMap.put(transportName, Integer.valueOf(((Integer) hashMap.get(transportName)).intValue() + 1));
                        }
                    }
                    this.guard.runCriticalSection(new d(this, hashMap, 24));
                }
            }
            this.guard.runCriticalSection(new a((Object) this, (Object) transportContext, j3));
            return ok;
        }
    }

    public void upload(TransportContext transportContext, int i3, Runnable runnable) {
        this.executor.execute(new a(this, transportContext, i3, runnable));
    }
}
