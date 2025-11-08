package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.firebase.transport.ClientMetrics;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final /* synthetic */ class a implements SQLiteEventStore.Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6590a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ SQLiteEventStore f6591b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f6592c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ Object f6593d;

    public /* synthetic */ a(SQLiteEventStore sQLiteEventStore, Object obj, Object obj2, int i3) {
        this.f6590a = i3;
        this.f6591b = sQLiteEventStore;
        this.f6592c = obj;
        this.f6593d = obj2;
    }

    public final Object apply(Object obj) {
        switch (this.f6590a) {
            case 0:
                HashMap hashMap = (HashMap) this.f6592c;
                return this.f6591b.lambda$loadClientMetrics$20("SELECT log_source, reason, events_dropped_count FROM log_event_dropped", hashMap, (ClientMetrics.Builder) this.f6593d, (SQLiteDatabase) obj);
            case 1:
                ArrayList arrayList = (ArrayList) this.f6592c;
                return this.f6591b.lambda$loadEvents$14(arrayList, (TransportContext) this.f6593d, (Cursor) obj);
            case 2:
                return this.f6591b.lambda$persist$1((EventInternal) this.f6592c, (TransportContext) this.f6593d, (SQLiteDatabase) obj);
            default:
                return this.f6591b.lambda$loadClientMetrics$19((Map) this.f6592c, (ClientMetrics.Builder) this.f6593d, (Cursor) obj);
        }
    }
}
