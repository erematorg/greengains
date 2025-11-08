package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.firebase.transport.LogEventDropped;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

public final /* synthetic */ class g implements SQLiteEventStore.Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ String f6605a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ LogEventDropped.Reason f6606b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ long f6607c;

    public /* synthetic */ g(long j2, LogEventDropped.Reason reason, String str) {
        this.f6605a = str;
        this.f6606b = reason;
        this.f6607c = j2;
    }

    public final Object apply(Object obj) {
        return SQLiteEventStore.lambda$recordLogEventDropped$18(this.f6605a, this.f6606b, this.f6607c, (SQLiteDatabase) obj);
    }
}
