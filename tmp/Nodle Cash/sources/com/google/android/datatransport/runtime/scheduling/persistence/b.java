package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

public final /* synthetic */ class b implements SQLiteEventStore.Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6594a;

    public /* synthetic */ b(int i3) {
        this.f6594a = i3;
    }

    public final Object apply(Object obj) {
        switch (this.f6594a) {
            case 0:
                return SQLiteEventStore.lambda$getTransportContextId$2((Cursor) obj);
            case 1:
                return SQLiteEventStore.lambda$recordLogEventDropped$17((Cursor) obj);
            case 2:
                return SQLiteEventStore.lambda$loadActiveContexts$9((Cursor) obj);
            case 3:
                return SQLiteEventStore.lambda$readPayload$15((Cursor) obj);
            case 4:
                return SQLiteEventStore.lambda$loadActiveContexts$10((SQLiteDatabase) obj);
            case 5:
                return SQLiteEventStore.lambda$getNextCallTime$5((Cursor) obj);
            case 6:
                return SQLiteEventStore.lambda$ensureBeginTransaction$25((Throwable) obj);
            case 7:
                return Boolean.valueOf(((Cursor) obj).moveToNext());
            case 8:
                return SQLiteEventStore.lambda$clearDb$13((SQLiteDatabase) obj);
            default:
                return SQLiteEventStore.lambda$getDb$0((Throwable) obj);
        }
    }
}
