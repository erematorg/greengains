package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import A1.C0219a;
import C0.d;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import java.util.concurrent.Executor;
import javax.inject.Inject;

public class WorkInitializer {
    private final Executor executor;
    private final SynchronizationGuard guard;
    private final WorkScheduler scheduler;
    private final EventStore store;

    @Inject
    public WorkInitializer(Executor executor2, EventStore eventStore, WorkScheduler workScheduler, SynchronizationGuard synchronizationGuard) {
        this.executor = executor2;
        this.store = eventStore;
        this.scheduler = workScheduler;
        this.guard = synchronizationGuard;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$ensureContextsScheduled$0() {
        for (TransportContext schedule : this.store.loadActiveContexts()) {
            this.scheduler.schedule(schedule, 1);
        }
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$ensureContextsScheduled$1() {
        this.guard.runCriticalSection(new C0219a(this, 18));
    }

    public void ensureContextsScheduled() {
        this.executor.execute(new d(this, 27));
    }
}
