package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;
import java.util.HashMap;

public final /* synthetic */ class f implements SQLiteEventStore.Function, SQLiteEventStore.Producer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6603a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f6604b;

    public /* synthetic */ f(Object obj, int i3) {
        this.f6603a = i3;
        this.f6604b = obj;
    }

    public Object apply(Object obj) {
        return SQLiteEventStore.lambda$loadMetadata$16((HashMap) this.f6604b, (Cursor) obj);
    }

    public Object produce() {
        int i3 = this.f6603a;
        Object obj = this.f6604b;
        switch (i3) {
            case 1:
                return ((SQLiteDatabase) obj).beginTransaction();
            default:
                return ((SchemaManager) obj).getWritableDatabase();
        }
    }
}
