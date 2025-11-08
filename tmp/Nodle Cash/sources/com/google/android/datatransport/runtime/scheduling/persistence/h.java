package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

public final /* synthetic */ class h implements SQLiteEventStore.Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6608a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ long f6609b;

    public /* synthetic */ h(long j2, int i3) {
        this.f6608a = i3;
        this.f6609b = j2;
    }

    public final Object apply(Object obj) {
        switch (this.f6608a) {
            case 0:
                return SQLiteEventStore.lambda$getTimeWindow$22(this.f6609b, (SQLiteDatabase) obj);
            default:
                return ((Cursor) obj).moveToNext();
        }
    }
}
