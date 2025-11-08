package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

public final /* synthetic */ class e implements SQLiteEventStore.Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6601a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ SQLiteEventStore f6602b;

    public /* synthetic */ e(SQLiteEventStore sQLiteEventStore, int i3) {
        this.f6601a = i3;
        this.f6602b = sQLiteEventStore;
    }

    public final Object apply(Object obj) {
        int i3 = this.f6601a;
        SQLiteEventStore sQLiteEventStore = this.f6602b;
        switch (i3) {
            case 0:
                return sQLiteEventStore.lambda$resetClientMetrics$23((SQLiteDatabase) obj);
            case 1:
                return sQLiteEventStore.lambda$cleanUp$11((Cursor) obj);
            default:
                return sQLiteEventStore.lambda$recordFailure$3((Cursor) obj);
        }
    }
}
