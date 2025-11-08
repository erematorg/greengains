package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

public final /* synthetic */ class c implements SQLiteEventStore.Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6595a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ SQLiteEventStore f6596b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f6597c;

    public /* synthetic */ c(SQLiteEventStore sQLiteEventStore, Object obj, int i3) {
        this.f6595a = i3;
        this.f6596b = sQLiteEventStore;
        this.f6597c = obj;
    }

    public final Object apply(Object obj) {
        SQLiteDatabase sQLiteDatabase = (SQLiteDatabase) obj;
        switch (this.f6595a) {
            case 0:
                return this.f6596b.lambda$hasPendingEventsFor$6((TransportContext) this.f6597c, sQLiteDatabase);
            case 1:
                return this.f6596b.lambda$loadBatch$8((TransportContext) this.f6597c, sQLiteDatabase);
            default:
                return this.f6596b.lambda$recordFailure$4((String) this.f6597c, "SELECT COUNT(*), transport_name FROM events WHERE num_attempts >= 16 GROUP BY transport_name", sQLiteDatabase);
        }
    }
}
