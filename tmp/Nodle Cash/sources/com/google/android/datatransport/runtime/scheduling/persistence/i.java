package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.scheduling.persistence.SchemaManager;

public final /* synthetic */ class i implements SchemaManager.Migration {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6610a;

    public /* synthetic */ i(int i3) {
        this.f6610a = i3;
    }

    public final void upgrade(SQLiteDatabase sQLiteDatabase) {
        switch (this.f6610a) {
            case 0:
                SchemaManager.lambda$static$0(sQLiteDatabase);
                return;
            case 1:
                SchemaManager.lambda$static$1(sQLiteDatabase);
                return;
            case 2:
                sQLiteDatabase.execSQL("ALTER TABLE events ADD COLUMN payload_encoding TEXT");
                return;
            case 3:
                SchemaManager.lambda$static$3(sQLiteDatabase);
                return;
            case 4:
                SchemaManager.lambda$static$4(sQLiteDatabase);
                return;
            case 5:
                sQLiteDatabase.execSQL("ALTER TABLE events ADD COLUMN product_id INTEGER");
                return;
            default:
                SchemaManager.lambda$static$6(sQLiteDatabase);
                return;
        }
    }
}
