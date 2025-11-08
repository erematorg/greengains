package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

public final /* synthetic */ class d implements SQLiteEventStore.Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6598a = 0;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ long f6599b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f6600c;

    public /* synthetic */ d(TransportContext transportContext, long j2) {
        this.f6599b = j2;
        this.f6600c = transportContext;
    }

    public final Object apply(Object obj) {
        SQLiteDatabase sQLiteDatabase = (SQLiteDatabase) obj;
        switch (this.f6598a) {
            case 0:
                return SQLiteEventStore.lambda$recordNextCallTime$7(this.f6599b, (TransportContext) this.f6600c, sQLiteDatabase);
            default:
                return ((SQLiteEventStore) this.f6600c).lambda$cleanUp$12(this.f6599b, sQLiteDatabase);
        }
    }

    public /* synthetic */ d(SQLiteEventStore sQLiteEventStore, long j2) {
        this.f6600c = sQLiteEventStore;
        this.f6599b = j2;
    }
}
