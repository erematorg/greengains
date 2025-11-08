package com.google.android.recaptcha.internal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class zzaz extends SQLiteOpenHelper {
    @NotNull
    public static final zzax zza;
    private static final int zzb;
    /* access modifiers changed from: private */
    @Nullable
    public static zzaz zzc;

    static {
        zzax zzax = new zzax((DefaultConstructorMarker) null);
        zza = zzax;
        zzb = zzax.zzb("18.4.0");
    }

    public /* synthetic */ zzaz(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        super(context, "cesdb", (SQLiteDatabase.CursorFactory) null, zzb);
    }

    public final void onCreate(@NotNull SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE ce (id INTEGER PRIMARY KEY,ts BIGINT NOT NULL,ss TEXT NOT NULL)");
    }

    public final void onDowngrade(@NotNull SQLiteDatabase sQLiteDatabase, int i3, int i4) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ce");
        sQLiteDatabase.execSQL("CREATE TABLE ce (id INTEGER PRIMARY KEY,ts BIGINT NOT NULL,ss TEXT NOT NULL)");
    }

    public final void onUpgrade(@NotNull SQLiteDatabase sQLiteDatabase, int i3, int i4) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ce");
        sQLiteDatabase.execSQL("CREATE TABLE ce (id INTEGER PRIMARY KEY,ts BIGINT NOT NULL,ss TEXT NOT NULL)");
    }

    public final int zza(@NotNull List list) {
        if (list.isEmpty()) {
            return 0;
        }
        return getWritableDatabase().delete("ce", "id IN ".concat(String.valueOf(CollectionsKt___CollectionsKt.joinToString$default(list, ", ", "(", ")", 0, (CharSequence) null, zzay.zza, 24, (Object) null))), (String[]) null);
    }

    public final int zzb() {
        Cursor rawQuery = getReadableDatabase().rawQuery("SELECT COUNT(*) FROM ce", (String[]) null);
        int i3 = -1;
        try {
            if (rawQuery.moveToNext()) {
                i3 = rawQuery.getInt(0);
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            rawQuery.close();
            throw th;
        }
        rawQuery.close();
        return i3;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:6|7) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0050, code lost:
        r8.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0053, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0046, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
        r0 = kotlin.collections.CollectionsKt.emptyList();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0048 */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List zzd() {
        /*
            r8 = this;
            android.database.sqlite.SQLiteDatabase r0 = r8.getReadableDatabase()
            r6 = 0
            java.lang.String r7 = "ts ASC"
            java.lang.String r1 = "ce"
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            android.database.Cursor r8 = r0.query(r1, r2, r3, r4, r5, r6, r7)
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
        L_0x0016:
            boolean r1 = r8.moveToNext()     // Catch:{ Exception -> 0x0048 }
            if (r1 == 0) goto L_0x004c
            java.lang.String r1 = "id"
            int r1 = r8.getColumnIndexOrThrow(r1)     // Catch:{ Exception -> 0x0048 }
            int r1 = r8.getInt(r1)     // Catch:{ Exception -> 0x0048 }
            java.lang.String r2 = "ss"
            int r2 = r8.getColumnIndexOrThrow(r2)     // Catch:{ Exception -> 0x0048 }
            java.lang.String r2 = r8.getString(r2)     // Catch:{ Exception -> 0x0048 }
            java.lang.String r3 = "ts"
            int r3 = r8.getColumnIndexOrThrow(r3)     // Catch:{ Exception -> 0x0048 }
            long r3 = r8.getLong(r3)     // Catch:{ Exception -> 0x0048 }
            com.google.android.recaptcha.internal.zzba r5 = new com.google.android.recaptcha.internal.zzba     // Catch:{ Exception -> 0x0048 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)     // Catch:{ Exception -> 0x0048 }
            r5.<init>(r2, r3, r1)     // Catch:{ Exception -> 0x0048 }
            r0.add(r5)     // Catch:{ Exception -> 0x0048 }
            goto L_0x0016
        L_0x0046:
            r0 = move-exception
            goto L_0x0050
        L_0x0048:
            java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()     // Catch:{ all -> 0x0046 }
        L_0x004c:
            r8.close()
            return r0
        L_0x0050:
            r8.close()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzaz.zzd():java.util.List");
    }

    public final boolean zzf(@NotNull zzba zzba) {
        return zza(CollectionsKt.listOf(zzba)) == 1;
    }
}
