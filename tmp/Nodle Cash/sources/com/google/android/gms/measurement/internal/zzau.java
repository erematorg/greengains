package com.google.android.gms.measurement.internal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import androidx.annotation.WorkerThread;
import com.google.android.gms.internal.measurement.zzcf;
import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class zzau {
    @WorkerThread
    private static Set<String> zza(SQLiteDatabase sQLiteDatabase, String str) {
        HashSet hashSet = new HashSet();
        Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT * FROM " + str + " LIMIT 0", (String[]) null);
        try {
            Collections.addAll(hashSet, rawQuery.getColumnNames());
            return hashSet;
        } finally {
            rawQuery.close();
        }
    }

    @WorkerThread
    public static void zza(zzgi zzgi, SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, String[] strArr) throws SQLiteException {
        if (zzgi != null) {
            if (!zza(zzgi, sQLiteDatabase, str)) {
                sQLiteDatabase.execSQL(str2);
            }
            try {
                Set<String> zza = zza(sQLiteDatabase, str);
                String[] split = str3.split(",");
                int length = split.length;
                int i3 = 0;
                while (i3 < length) {
                    String str4 = split[i3];
                    if (zza.remove(str4)) {
                        i3++;
                    } else {
                        throw new SQLiteException("Table " + str + " is missing required column: " + str4);
                    }
                }
                if (strArr != null) {
                    for (int i4 = 0; i4 < strArr.length; i4 += 2) {
                        if (!zza.remove(strArr[i4])) {
                            sQLiteDatabase.execSQL(strArr[i4 + 1]);
                        }
                    }
                }
                if (!zza.isEmpty()) {
                    zzgi.zzu().zza("Table has extra columns. table, columns", str, TextUtils.join(", ", zza));
                }
            } catch (SQLiteException e3) {
                zzgi.zzg().zza("Failed to verify columns on table that was just created", str);
                throw e3;
            }
        } else {
            throw new IllegalArgumentException("Monitor must not be null");
        }
    }

    public static void zza(zzgi zzgi, SQLiteDatabase sQLiteDatabase) {
        if (zzgi != null) {
            File file = new File(zzcf.zza().zza(sQLiteDatabase.getPath()));
            if (!file.setReadable(false, false)) {
                zzgi.zzu().zza("Failed to turn off database read permission");
            }
            if (!file.setWritable(false, false)) {
                zzgi.zzu().zza("Failed to turn off database write permission");
            }
            if (!file.setReadable(true, true)) {
                zzgi.zzu().zza("Failed to turn on database read permission for owner");
            }
            if (!file.setWritable(true, true)) {
                zzgi.zzu().zza("Failed to turn on database write permission for owner");
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Monitor must not be null");
    }

    @WorkerThread
    private static boolean zza(zzgi zzgi, SQLiteDatabase sQLiteDatabase, String str) {
        if (zzgi != null) {
            Cursor cursor = null;
            try {
                Cursor query = sQLiteDatabase.query("SQLITE_MASTER", new String[]{"name"}, "name=?", new String[]{str}, (String) null, (String) null, (String) null);
                boolean moveToFirst = query.moveToFirst();
                query.close();
                return moveToFirst;
            } catch (SQLiteException e3) {
                zzgi.zzu().zza("Error querying for table", str, e3);
                if (cursor == null) {
                    return false;
                }
                cursor.close();
                return false;
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } else {
            throw new IllegalArgumentException("Monitor must not be null");
        }
    }
}
