package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.common.util.Clock;
import org.checkerframework.dataflow.qual.Pure;

public final class zzgf extends zzf {
    private final zzge zza = new zzge(this, zza(), "google_app_measurement_local.db");
    private boolean zzb;

    public zzgf(zzhw zzhw) {
        super(zzhw);
    }

    private static long zza(SQLiteDatabase sQLiteDatabase) {
        Cursor cursor = null;
        try {
            Cursor query = sQLiteDatabase.query("messages", new String[]{"rowid"}, "type=?", new String[]{ExifInterface.GPS_MEASUREMENT_3D}, (String) null, (String) null, "rowid desc", "1");
            if (query.moveToFirst()) {
                long j2 = query.getLong(0);
                query.close();
                return j2;
            }
            query.close();
            return -1;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    @VisibleForTesting
    @WorkerThread
    private final SQLiteDatabase zzad() throws SQLiteException {
        if (this.zzb) {
            return null;
        }
        SQLiteDatabase writableDatabase = this.zza.getWritableDatabase();
        if (writableDatabase != null) {
            return writableDatabase;
        }
        this.zzb = true;
        return null;
    }

    @VisibleForTesting
    private final boolean zzae() {
        return zza().getDatabasePath("google_app_measurement_local.db").exists();
    }

    @WorkerThread
    public final void zzaa() {
        int delete;
        zzt();
        try {
            SQLiteDatabase zzad = zzad();
            if (zzad != null && (delete = zzad.delete("messages", (String) null, (String[]) null)) > 0) {
                zzj().zzp().zza("Reset local analytics data. records", Integer.valueOf(delete));
            }
        } catch (SQLiteException e3) {
            zzj().zzg().zza("Error resetting local analytics data. error", e3);
        }
    }

    @WorkerThread
    public final boolean zzab() {
        return zza(3, new byte[0]);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0086, code lost:
        r3 = r3 + 1;
     */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzac() {
        /*
            r10 = this;
            java.lang.String r0 = "Error deleting app launch break from local database"
            r10.zzt()
            boolean r1 = r10.zzb
            r2 = 0
            if (r1 == 0) goto L_0x000b
            return r2
        L_0x000b:
            boolean r1 = r10.zzae()
            if (r1 != 0) goto L_0x0012
            return r2
        L_0x0012:
            r1 = 5
            r4 = r1
            r3 = r2
        L_0x0015:
            if (r3 >= r1) goto L_0x008f
            r5 = 1
            r6 = 0
            android.database.sqlite.SQLiteDatabase r6 = r10.zzad()     // Catch:{ SQLiteFullException -> 0x002b, SQLiteDatabaseLockedException -> 0x0068, SQLiteException -> 0x0029 }
            if (r6 != 0) goto L_0x002d
            r10.zzb = r5     // Catch:{ SQLiteFullException -> 0x002b, SQLiteDatabaseLockedException -> 0x0068, SQLiteException -> 0x0029 }
            if (r6 == 0) goto L_0x0026
            r6.close()
        L_0x0026:
            return r2
        L_0x0027:
            r10 = move-exception
            goto L_0x0089
        L_0x0029:
            r7 = move-exception
            goto L_0x004a
        L_0x002b:
            r7 = move-exception
            goto L_0x0074
        L_0x002d:
            r6.beginTransaction()     // Catch:{ SQLiteFullException -> 0x002b, SQLiteDatabaseLockedException -> 0x0068, SQLiteException -> 0x0029 }
            java.lang.String r7 = "messages"
            java.lang.String r8 = "type == ?"
            r9 = 3
            java.lang.String r9 = java.lang.Integer.toString(r9)     // Catch:{ SQLiteFullException -> 0x002b, SQLiteDatabaseLockedException -> 0x0068, SQLiteException -> 0x0029 }
            java.lang.String[] r9 = new java.lang.String[]{r9}     // Catch:{ SQLiteFullException -> 0x002b, SQLiteDatabaseLockedException -> 0x0068, SQLiteException -> 0x0029 }
            r6.delete(r7, r8, r9)     // Catch:{ SQLiteFullException -> 0x002b, SQLiteDatabaseLockedException -> 0x0068, SQLiteException -> 0x0029 }
            r6.setTransactionSuccessful()     // Catch:{ SQLiteFullException -> 0x002b, SQLiteDatabaseLockedException -> 0x0068, SQLiteException -> 0x0029 }
            r6.endTransaction()     // Catch:{ SQLiteFullException -> 0x002b, SQLiteDatabaseLockedException -> 0x0068, SQLiteException -> 0x0029 }
            r6.close()
            return r5
        L_0x004a:
            if (r6 == 0) goto L_0x0055
            boolean r8 = r6.inTransaction()     // Catch:{ all -> 0x0027 }
            if (r8 == 0) goto L_0x0055
            r6.endTransaction()     // Catch:{ all -> 0x0027 }
        L_0x0055:
            com.google.android.gms.measurement.internal.zzgi r8 = r10.zzj()     // Catch:{ all -> 0x0027 }
            com.google.android.gms.measurement.internal.zzgk r8 = r8.zzg()     // Catch:{ all -> 0x0027 }
            r8.zza(r0, r7)     // Catch:{ all -> 0x0027 }
            r10.zzb = r5     // Catch:{ all -> 0x0027 }
            if (r6 == 0) goto L_0x0086
            r6.close()
            goto L_0x0086
        L_0x0068:
            long r7 = (long) r4
            android.os.SystemClock.sleep(r7)     // Catch:{ all -> 0x0027 }
            int r4 = r4 + 20
            if (r6 == 0) goto L_0x0086
            r6.close()
            goto L_0x0086
        L_0x0074:
            com.google.android.gms.measurement.internal.zzgi r8 = r10.zzj()     // Catch:{ all -> 0x0027 }
            com.google.android.gms.measurement.internal.zzgk r8 = r8.zzg()     // Catch:{ all -> 0x0027 }
            r8.zza(r0, r7)     // Catch:{ all -> 0x0027 }
            r10.zzb = r5     // Catch:{ all -> 0x0027 }
            if (r6 == 0) goto L_0x0086
            r6.close()
        L_0x0086:
            int r3 = r3 + 1
            goto L_0x0015
        L_0x0089:
            if (r6 == 0) goto L_0x008e
            r6.close()
        L_0x008e:
            throw r10
        L_0x008f:
            com.google.android.gms.measurement.internal.zzgi r10 = r10.zzj()
            com.google.android.gms.measurement.internal.zzgk r10 = r10.zzu()
            java.lang.String r0 = "Error deleting app launch break from local database in reasonable time"
            r10.zza(r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzgf.zzac():boolean");
    }

    @Pure
    public final /* bridge */ /* synthetic */ Clock zzb() {
        return super.zzb();
    }

    public final /* bridge */ /* synthetic */ zza zzc() {
        return super.zzc();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzac zzd() {
        return super.zzd();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzah zze() {
        return super.zze();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzbb zzf() {
        return super.zzf();
    }

    public final /* bridge */ /* synthetic */ zzgc zzg() {
        return super.zzg();
    }

    public final /* bridge */ /* synthetic */ zzgf zzh() {
        return super.zzh();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgh zzi() {
        return super.zzi();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgi zzj() {
        return super.zzj();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgu zzk() {
        return super.zzk();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzhp zzl() {
        return super.zzl();
    }

    public final /* bridge */ /* synthetic */ zzjk zzm() {
        return super.zzm();
    }

    public final /* bridge */ /* synthetic */ zzlg zzn() {
        return super.zzn();
    }

    public final /* bridge */ /* synthetic */ zzlp zzo() {
        return super.zzo();
    }

    public final /* bridge */ /* synthetic */ zznb zzp() {
        return super.zzp();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzop zzq() {
        return super.zzq();
    }

    public final /* bridge */ /* synthetic */ void zzr() {
        super.zzr();
    }

    public final /* bridge */ /* synthetic */ void zzs() {
        super.zzs();
    }

    public final /* bridge */ /* synthetic */ void zzt() {
        super.zzt();
    }

    public final boolean zzz() {
        return false;
    }

    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:72|73|74|75) */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:87|88|89|90) */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:59|60|61|62|172) */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x01ad, code lost:
        if (r15.inTransaction() != false) goto L_0x01af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x01af, code lost:
        r15.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x01b3, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x01b4, code lost:
        r3 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x01c6, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x01cb, code lost:
        r15.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x01d9, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x01de, code lost:
        r15.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x0203, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x0208, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002e, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002f, code lost:
        r5 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0032, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0036, code lost:
        r5 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0039, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:?, code lost:
        zzj().zzg().zza("Failed to load event from local database");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:?, code lost:
        r11.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:?, code lost:
        zzj().zzg().zza("Failed to load user property from local database");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:?, code lost:
        r11.recycle();
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:?, code lost:
        zzj().zzg().zza("Failed to load conditional user property from local database");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:?, code lost:
        r11.recycle();
        r0 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:59:0x00b6 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:72:0x00e6 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:87:0x011d */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x01a9 A[SYNTHETIC, Splitter:B:123:0x01a9] */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x01c6  */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x01cb  */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x01d9  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x01de  */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x01f4  */
    /* JADX WARNING: Removed duplicated region for block: B:151:0x01f9  */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x0203  */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x0208  */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x01fc A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:166:0x01fc A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:168:0x01fc A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x002e A[ExcHandler: all (th java.lang.Throwable), Splitter:B:12:0x0026] */
    /* JADX WARNING: Removed duplicated region for block: B:22:? A[ExcHandler: SQLiteDatabaseLockedException (unused android.database.sqlite.SQLiteDatabaseLockedException), SYNTHETIC, Splitter:B:12:0x0026] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable> zza(int r22) {
        /*
            r21 = this;
            r1 = r21
            java.lang.String r2 = "Error reading entries from local database"
            r21.zzt()
            boolean r0 = r1.zzb
            r3 = 0
            if (r0 == 0) goto L_0x000d
            return r3
        L_0x000d:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            boolean r0 = r21.zzae()
            if (r0 != 0) goto L_0x0019
            return r4
        L_0x0019:
            r5 = 5
            r6 = 0
            r8 = r5
            r7 = r6
        L_0x001d:
            if (r7 >= r5) goto L_0x020c
            r9 = 1
            android.database.sqlite.SQLiteDatabase r15 = r21.zzad()     // Catch:{ SQLiteFullException -> 0x01e2, SQLiteDatabaseLockedException -> 0x01cf, SQLiteException -> 0x01a4, all -> 0x01a1 }
            if (r15 != 0) goto L_0x003d
            r1.zzb = r9     // Catch:{ SQLiteFullException -> 0x0039, SQLiteDatabaseLockedException -> 0x0036, SQLiteException -> 0x0032, all -> 0x002e }
            if (r15 == 0) goto L_0x002d
            r15.close()
        L_0x002d:
            return r3
        L_0x002e:
            r0 = move-exception
        L_0x002f:
            r5 = r15
            goto L_0x0201
        L_0x0032:
            r0 = move-exception
        L_0x0033:
            r10 = r3
            goto L_0x01a7
        L_0x0036:
            r5 = r15
            goto L_0x019f
        L_0x0039:
            r0 = move-exception
        L_0x003a:
            r10 = r3
            goto L_0x01e5
        L_0x003d:
            r15.beginTransaction()     // Catch:{ SQLiteFullException -> 0x019b, SQLiteDatabaseLockedException -> 0x0036, SQLiteException -> 0x0197, all -> 0x002e }
            long r10 = zza((android.database.sqlite.SQLiteDatabase) r15)     // Catch:{ SQLiteFullException -> 0x019b, SQLiteDatabaseLockedException -> 0x0036, SQLiteException -> 0x0197, all -> 0x002e }
            r19 = -1
            int r0 = (r10 > r19 ? 1 : (r10 == r19 ? 0 : -1))
            if (r0 == 0) goto L_0x0057
            java.lang.String r0 = "rowid<?"
            java.lang.String[] r12 = new java.lang.String[r9]     // Catch:{ SQLiteFullException -> 0x0039, SQLiteDatabaseLockedException -> 0x0036, SQLiteException -> 0x0032, all -> 0x002e }
            java.lang.String r10 = java.lang.String.valueOf(r10)     // Catch:{ SQLiteFullException -> 0x0039, SQLiteDatabaseLockedException -> 0x0036, SQLiteException -> 0x0032, all -> 0x002e }
            r12[r6] = r10     // Catch:{ SQLiteFullException -> 0x0039, SQLiteDatabaseLockedException -> 0x0036, SQLiteException -> 0x0032, all -> 0x002e }
            r13 = r0
            r14 = r12
            goto L_0x0059
        L_0x0057:
            r13 = r3
            r14 = r13
        L_0x0059:
            java.lang.String r11 = "messages"
            java.lang.String r0 = "rowid"
            java.lang.String r10 = "type"
            java.lang.String r12 = "entry"
            java.lang.String[] r12 = new java.lang.String[]{r0, r10, r12}     // Catch:{ SQLiteFullException -> 0x019b, SQLiteDatabaseLockedException -> 0x0036, SQLiteException -> 0x0197, all -> 0x002e }
            java.lang.String r17 = "rowid asc"
            r0 = 100
            java.lang.String r18 = java.lang.Integer.toString(r0)     // Catch:{ SQLiteFullException -> 0x019b, SQLiteDatabaseLockedException -> 0x0036, SQLiteException -> 0x0197, all -> 0x002e }
            r0 = 0
            r16 = 0
            r10 = r15
            r5 = r15
            r15 = r0
            android.database.Cursor r10 = r10.query(r11, r12, r13, r14, r15, r16, r17, r18)     // Catch:{ SQLiteFullException -> 0x0193, SQLiteDatabaseLockedException -> 0x019f, SQLiteException -> 0x018f, all -> 0x018c }
        L_0x0077:
            boolean r0 = r10.moveToNext()     // Catch:{ SQLiteFullException -> 0x00b0, SQLiteDatabaseLockedException -> 0x018a, SQLiteException -> 0x00ac, all -> 0x00a8 }
            if (r0 == 0) goto L_0x015a
            long r19 = r10.getLong(r6)     // Catch:{ SQLiteFullException -> 0x00b0, SQLiteDatabaseLockedException -> 0x018a, SQLiteException -> 0x00ac, all -> 0x00a8 }
            int r0 = r10.getInt(r9)     // Catch:{ SQLiteFullException -> 0x00b0, SQLiteDatabaseLockedException -> 0x018a, SQLiteException -> 0x00ac, all -> 0x00a8 }
            r11 = 2
            byte[] r12 = r10.getBlob(r11)     // Catch:{ SQLiteFullException -> 0x00b0, SQLiteDatabaseLockedException -> 0x018a, SQLiteException -> 0x00ac, all -> 0x00a8 }
            if (r0 != 0) goto L_0x00cb
            android.os.Parcel r11 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x00b0, SQLiteDatabaseLockedException -> 0x018a, SQLiteException -> 0x00ac, all -> 0x00a8 }
            int r0 = r12.length     // Catch:{ ParseException -> 0x00b6 }
            r11.unmarshall(r12, r6, r0)     // Catch:{ ParseException -> 0x00b6 }
            r11.setDataPosition(r6)     // Catch:{ ParseException -> 0x00b6 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzbh> r0 = com.google.android.gms.measurement.internal.zzbh.CREATOR     // Catch:{ ParseException -> 0x00b6 }
            java.lang.Object r0 = r0.createFromParcel(r11)     // Catch:{ ParseException -> 0x00b6 }
            com.google.android.gms.measurement.internal.zzbh r0 = (com.google.android.gms.measurement.internal.zzbh) r0     // Catch:{ ParseException -> 0x00b6 }
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x00b0, SQLiteDatabaseLockedException -> 0x018a, SQLiteException -> 0x00ac, all -> 0x00a8 }
            if (r0 == 0) goto L_0x0077
            r4.add(r0)     // Catch:{ SQLiteFullException -> 0x00b0, SQLiteDatabaseLockedException -> 0x018a, SQLiteException -> 0x00ac, all -> 0x00a8 }
            goto L_0x0077
        L_0x00a8:
            r0 = move-exception
            r3 = r10
            goto L_0x0201
        L_0x00ac:
            r0 = move-exception
        L_0x00ad:
            r15 = r5
            goto L_0x01a7
        L_0x00b0:
            r0 = move-exception
        L_0x00b1:
            r15 = r5
            goto L_0x01e5
        L_0x00b4:
            r0 = move-exception
            goto L_0x00c7
        L_0x00b6:
            com.google.android.gms.measurement.internal.zzgi r0 = r21.zzj()     // Catch:{ all -> 0x00b4 }
            com.google.android.gms.measurement.internal.zzgk r0 = r0.zzg()     // Catch:{ all -> 0x00b4 }
            java.lang.String r12 = "Failed to load event from local database"
            r0.zza(r12)     // Catch:{ all -> 0x00b4 }
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x00b0, SQLiteDatabaseLockedException -> 0x018a, SQLiteException -> 0x00ac, all -> 0x00a8 }
            goto L_0x0077
        L_0x00c7:
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x00b0, SQLiteDatabaseLockedException -> 0x018a, SQLiteException -> 0x00ac, all -> 0x00a8 }
            throw r0     // Catch:{ SQLiteFullException -> 0x00b0, SQLiteDatabaseLockedException -> 0x018a, SQLiteException -> 0x00ac, all -> 0x00a8 }
        L_0x00cb:
            if (r0 != r9) goto L_0x0102
            android.os.Parcel r11 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x00b0, SQLiteDatabaseLockedException -> 0x018a, SQLiteException -> 0x00ac, all -> 0x00a8 }
            int r0 = r12.length     // Catch:{ ParseException -> 0x00e6 }
            r11.unmarshall(r12, r6, r0)     // Catch:{ ParseException -> 0x00e6 }
            r11.setDataPosition(r6)     // Catch:{ ParseException -> 0x00e6 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzok> r0 = com.google.android.gms.measurement.internal.zzok.CREATOR     // Catch:{ ParseException -> 0x00e6 }
            java.lang.Object r0 = r0.createFromParcel(r11)     // Catch:{ ParseException -> 0x00e6 }
            com.google.android.gms.measurement.internal.zzok r0 = (com.google.android.gms.measurement.internal.zzok) r0     // Catch:{ ParseException -> 0x00e6 }
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x00b0, SQLiteDatabaseLockedException -> 0x018a, SQLiteException -> 0x00ac, all -> 0x00a8 }
            goto L_0x00f7
        L_0x00e4:
            r0 = move-exception
            goto L_0x00fe
        L_0x00e6:
            com.google.android.gms.measurement.internal.zzgi r0 = r21.zzj()     // Catch:{ all -> 0x00e4 }
            com.google.android.gms.measurement.internal.zzgk r0 = r0.zzg()     // Catch:{ all -> 0x00e4 }
            java.lang.String r12 = "Failed to load user property from local database"
            r0.zza(r12)     // Catch:{ all -> 0x00e4 }
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x00b0, SQLiteDatabaseLockedException -> 0x018a, SQLiteException -> 0x00ac, all -> 0x00a8 }
            r0 = r3
        L_0x00f7:
            if (r0 == 0) goto L_0x0077
            r4.add(r0)     // Catch:{ SQLiteFullException -> 0x00b0, SQLiteDatabaseLockedException -> 0x018a, SQLiteException -> 0x00ac, all -> 0x00a8 }
            goto L_0x0077
        L_0x00fe:
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x00b0, SQLiteDatabaseLockedException -> 0x018a, SQLiteException -> 0x00ac, all -> 0x00a8 }
            throw r0     // Catch:{ SQLiteFullException -> 0x00b0, SQLiteDatabaseLockedException -> 0x018a, SQLiteException -> 0x00ac, all -> 0x00a8 }
        L_0x0102:
            if (r0 != r11) goto L_0x0139
            android.os.Parcel r11 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x00b0, SQLiteDatabaseLockedException -> 0x018a, SQLiteException -> 0x00ac, all -> 0x00a8 }
            int r0 = r12.length     // Catch:{ ParseException -> 0x011d }
            r11.unmarshall(r12, r6, r0)     // Catch:{ ParseException -> 0x011d }
            r11.setDataPosition(r6)     // Catch:{ ParseException -> 0x011d }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzaf> r0 = com.google.android.gms.measurement.internal.zzaf.CREATOR     // Catch:{ ParseException -> 0x011d }
            java.lang.Object r0 = r0.createFromParcel(r11)     // Catch:{ ParseException -> 0x011d }
            com.google.android.gms.measurement.internal.zzaf r0 = (com.google.android.gms.measurement.internal.zzaf) r0     // Catch:{ ParseException -> 0x011d }
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x00b0, SQLiteDatabaseLockedException -> 0x018a, SQLiteException -> 0x00ac, all -> 0x00a8 }
            goto L_0x012e
        L_0x011b:
            r0 = move-exception
            goto L_0x0135
        L_0x011d:
            com.google.android.gms.measurement.internal.zzgi r0 = r21.zzj()     // Catch:{ all -> 0x011b }
            com.google.android.gms.measurement.internal.zzgk r0 = r0.zzg()     // Catch:{ all -> 0x011b }
            java.lang.String r12 = "Failed to load conditional user property from local database"
            r0.zza(r12)     // Catch:{ all -> 0x011b }
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x00b0, SQLiteDatabaseLockedException -> 0x018a, SQLiteException -> 0x00ac, all -> 0x00a8 }
            r0 = r3
        L_0x012e:
            if (r0 == 0) goto L_0x0077
            r4.add(r0)     // Catch:{ SQLiteFullException -> 0x00b0, SQLiteDatabaseLockedException -> 0x018a, SQLiteException -> 0x00ac, all -> 0x00a8 }
            goto L_0x0077
        L_0x0135:
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x00b0, SQLiteDatabaseLockedException -> 0x018a, SQLiteException -> 0x00ac, all -> 0x00a8 }
            throw r0     // Catch:{ SQLiteFullException -> 0x00b0, SQLiteDatabaseLockedException -> 0x018a, SQLiteException -> 0x00ac, all -> 0x00a8 }
        L_0x0139:
            r11 = 3
            if (r0 != r11) goto L_0x014b
            com.google.android.gms.measurement.internal.zzgi r0 = r21.zzj()     // Catch:{ SQLiteFullException -> 0x00b0, SQLiteDatabaseLockedException -> 0x018a, SQLiteException -> 0x00ac, all -> 0x00a8 }
            com.google.android.gms.measurement.internal.zzgk r0 = r0.zzu()     // Catch:{ SQLiteFullException -> 0x00b0, SQLiteDatabaseLockedException -> 0x018a, SQLiteException -> 0x00ac, all -> 0x00a8 }
            java.lang.String r11 = "Skipping app launch break"
            r0.zza(r11)     // Catch:{ SQLiteFullException -> 0x00b0, SQLiteDatabaseLockedException -> 0x018a, SQLiteException -> 0x00ac, all -> 0x00a8 }
            goto L_0x0077
        L_0x014b:
            com.google.android.gms.measurement.internal.zzgi r0 = r21.zzj()     // Catch:{ SQLiteFullException -> 0x00b0, SQLiteDatabaseLockedException -> 0x018a, SQLiteException -> 0x00ac, all -> 0x00a8 }
            com.google.android.gms.measurement.internal.zzgk r0 = r0.zzg()     // Catch:{ SQLiteFullException -> 0x00b0, SQLiteDatabaseLockedException -> 0x018a, SQLiteException -> 0x00ac, all -> 0x00a8 }
            java.lang.String r11 = "Unknown record type in local database"
            r0.zza(r11)     // Catch:{ SQLiteFullException -> 0x00b0, SQLiteDatabaseLockedException -> 0x018a, SQLiteException -> 0x00ac, all -> 0x00a8 }
            goto L_0x0077
        L_0x015a:
            java.lang.String r0 = "messages"
            java.lang.String r11 = "rowid <= ?"
            java.lang.String r12 = java.lang.Long.toString(r19)     // Catch:{ SQLiteFullException -> 0x00b0, SQLiteDatabaseLockedException -> 0x018a, SQLiteException -> 0x00ac, all -> 0x00a8 }
            java.lang.String[] r12 = new java.lang.String[]{r12}     // Catch:{ SQLiteFullException -> 0x00b0, SQLiteDatabaseLockedException -> 0x018a, SQLiteException -> 0x00ac, all -> 0x00a8 }
            int r0 = r5.delete(r0, r11, r12)     // Catch:{ SQLiteFullException -> 0x00b0, SQLiteDatabaseLockedException -> 0x018a, SQLiteException -> 0x00ac, all -> 0x00a8 }
            int r11 = r4.size()     // Catch:{ SQLiteFullException -> 0x00b0, SQLiteDatabaseLockedException -> 0x018a, SQLiteException -> 0x00ac, all -> 0x00a8 }
            if (r0 >= r11) goto L_0x017d
            com.google.android.gms.measurement.internal.zzgi r0 = r21.zzj()     // Catch:{ SQLiteFullException -> 0x00b0, SQLiteDatabaseLockedException -> 0x018a, SQLiteException -> 0x00ac, all -> 0x00a8 }
            com.google.android.gms.measurement.internal.zzgk r0 = r0.zzg()     // Catch:{ SQLiteFullException -> 0x00b0, SQLiteDatabaseLockedException -> 0x018a, SQLiteException -> 0x00ac, all -> 0x00a8 }
            java.lang.String r11 = "Fewer entries removed from local database than expected"
            r0.zza(r11)     // Catch:{ SQLiteFullException -> 0x00b0, SQLiteDatabaseLockedException -> 0x018a, SQLiteException -> 0x00ac, all -> 0x00a8 }
        L_0x017d:
            r5.setTransactionSuccessful()     // Catch:{ SQLiteFullException -> 0x00b0, SQLiteDatabaseLockedException -> 0x018a, SQLiteException -> 0x00ac, all -> 0x00a8 }
            r5.endTransaction()     // Catch:{ SQLiteFullException -> 0x00b0, SQLiteDatabaseLockedException -> 0x018a, SQLiteException -> 0x00ac, all -> 0x00a8 }
            r10.close()
            r5.close()
            return r4
        L_0x018a:
            r15 = r5
            goto L_0x01d1
        L_0x018c:
            r0 = move-exception
            goto L_0x0201
        L_0x018f:
            r0 = move-exception
            r10 = r3
            goto L_0x00ad
        L_0x0193:
            r0 = move-exception
            r10 = r3
            goto L_0x00b1
        L_0x0197:
            r0 = move-exception
            r5 = r15
            goto L_0x0033
        L_0x019b:
            r0 = move-exception
            r5 = r15
            goto L_0x003a
        L_0x019f:
            r10 = r3
            goto L_0x018a
        L_0x01a1:
            r0 = move-exception
            r5 = r3
            goto L_0x0201
        L_0x01a4:
            r0 = move-exception
            r10 = r3
            r15 = r10
        L_0x01a7:
            if (r15 == 0) goto L_0x01b7
            boolean r5 = r15.inTransaction()     // Catch:{ all -> 0x01b3 }
            if (r5 == 0) goto L_0x01b7
            r15.endTransaction()     // Catch:{ all -> 0x01b3 }
            goto L_0x01b7
        L_0x01b3:
            r0 = move-exception
            r3 = r10
            goto L_0x002f
        L_0x01b7:
            com.google.android.gms.measurement.internal.zzgi r5 = r21.zzj()     // Catch:{ all -> 0x01b3 }
            com.google.android.gms.measurement.internal.zzgk r5 = r5.zzg()     // Catch:{ all -> 0x01b3 }
            r5.zza(r2, r0)     // Catch:{ all -> 0x01b3 }
            r1.zzb = r9     // Catch:{ all -> 0x01b3 }
            if (r10 == 0) goto L_0x01c9
            r10.close()
        L_0x01c9:
            if (r15 == 0) goto L_0x01fc
            r15.close()
            goto L_0x01fc
        L_0x01cf:
            r10 = r3
            r15 = r10
        L_0x01d1:
            long r11 = (long) r8
            android.os.SystemClock.sleep(r11)     // Catch:{ all -> 0x01b3 }
            int r8 = r8 + 20
            if (r10 == 0) goto L_0x01dc
            r10.close()
        L_0x01dc:
            if (r15 == 0) goto L_0x01fc
            r15.close()
            goto L_0x01fc
        L_0x01e2:
            r0 = move-exception
            r10 = r3
            r15 = r10
        L_0x01e5:
            com.google.android.gms.measurement.internal.zzgi r5 = r21.zzj()     // Catch:{ all -> 0x01b3 }
            com.google.android.gms.measurement.internal.zzgk r5 = r5.zzg()     // Catch:{ all -> 0x01b3 }
            r5.zza(r2, r0)     // Catch:{ all -> 0x01b3 }
            r1.zzb = r9     // Catch:{ all -> 0x01b3 }
            if (r10 == 0) goto L_0x01f7
            r10.close()
        L_0x01f7:
            if (r15 == 0) goto L_0x01fc
            r15.close()
        L_0x01fc:
            int r7 = r7 + 1
            r5 = 5
            goto L_0x001d
        L_0x0201:
            if (r3 == 0) goto L_0x0206
            r3.close()
        L_0x0206:
            if (r5 == 0) goto L_0x020b
            r5.close()
        L_0x020b:
            throw r0
        L_0x020c:
            com.google.android.gms.measurement.internal.zzgi r0 = r21.zzj()
            com.google.android.gms.measurement.internal.zzgk r0 = r0.zzu()
            java.lang.String r1 = "Failed to read events from database in reasonable time"
            r0.zza(r1)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzgf.zza(int):java.util.List");
    }

    public final boolean zza(zzaf zzaf) {
        zzq();
        byte[] zza2 = zzop.zza((Parcelable) zzaf);
        if (zza2.length <= 131072) {
            return zza(2, zza2);
        }
        zzj().zzm().zza("Conditional user property too long for local database. Sending directly to service");
        return false;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v4, resolved type: android.database.sqlite.SQLiteDatabase} */
    /* JADX WARNING: type inference failed for: r7v0 */
    /* JADX WARNING: type inference failed for: r7v1, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r7v2, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r7v3, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r7v5 */
    /* JADX WARNING: type inference failed for: r7v7 */
    /* JADX WARNING: type inference failed for: r7v8 */
    /* JADX WARNING: type inference failed for: r7v9 */
    /* JADX WARNING: type inference failed for: r7v14 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x006b A[SYNTHETIC, Splitter:B:34:0x006b] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00c5 A[SYNTHETIC, Splitter:B:50:0x00c5] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00e9  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x00f6  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x00fb  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0113  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0118  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0123  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0128  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x00a9 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x011b A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x011b A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x011b A[SYNTHETIC] */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zza(int r17, byte[] r18) {
        /*
            r16 = this;
            r1 = r16
            r16.zzt()
            boolean r0 = r1.zzb
            r2 = 0
            if (r0 == 0) goto L_0x000b
            return r2
        L_0x000b:
            android.content.ContentValues r3 = new android.content.ContentValues
            r3.<init>()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r17)
            java.lang.String r4 = "type"
            r3.put(r4, r0)
            java.lang.String r0 = "entry"
            r4 = r18
            r3.put(r0, r4)
            r4 = 5
            r5 = r2
            r6 = r4
        L_0x0023:
            if (r5 >= r4) goto L_0x012c
            r7 = 0
            r8 = 1
            android.database.sqlite.SQLiteDatabase r9 = r16.zzad()     // Catch:{ SQLiteFullException -> 0x00ff, SQLiteDatabaseLockedException -> 0x00ed, SQLiteException -> 0x00c1, all -> 0x00be }
            if (r9 != 0) goto L_0x0040
            r1.zzb = r8     // Catch:{ SQLiteFullException -> 0x003d, SQLiteDatabaseLockedException -> 0x00ee, SQLiteException -> 0x0038 }
            if (r9 == 0) goto L_0x0034
            r9.close()
        L_0x0034:
            return r2
        L_0x0035:
            r0 = move-exception
            goto L_0x0121
        L_0x0038:
            r0 = move-exception
            r10 = r7
        L_0x003a:
            r7 = r9
            goto L_0x00c3
        L_0x003d:
            r0 = move-exception
            goto L_0x0101
        L_0x0040:
            r9.beginTransaction()     // Catch:{ SQLiteFullException -> 0x003d, SQLiteDatabaseLockedException -> 0x00ee, SQLiteException -> 0x0038 }
            java.lang.String r0 = "select count(1) from messages"
            android.database.Cursor r10 = r9.rawQuery(r0, r7)     // Catch:{ SQLiteFullException -> 0x003d, SQLiteDatabaseLockedException -> 0x00ee, SQLiteException -> 0x0038 }
            if (r10 == 0) goto L_0x0060
            boolean r0 = r10.moveToFirst()     // Catch:{ SQLiteFullException -> 0x005c, SQLiteDatabaseLockedException -> 0x00bc, SQLiteException -> 0x005a, all -> 0x0056 }
            if (r0 == 0) goto L_0x0060
            long r11 = r10.getLong(r2)     // Catch:{ SQLiteFullException -> 0x005c, SQLiteDatabaseLockedException -> 0x00bc, SQLiteException -> 0x005a, all -> 0x0056 }
            goto L_0x0062
        L_0x0056:
            r0 = move-exception
        L_0x0057:
            r7 = r10
            goto L_0x0121
        L_0x005a:
            r0 = move-exception
            goto L_0x003a
        L_0x005c:
            r0 = move-exception
            r7 = r10
            goto L_0x0101
        L_0x0060:
            r11 = 0
        L_0x0062:
            r13 = 100000(0x186a0, double:4.94066E-319)
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            java.lang.String r13 = "messages"
            if (r0 < 0) goto L_0x00a9
            com.google.android.gms.measurement.internal.zzgi r0 = r16.zzj()     // Catch:{ SQLiteFullException -> 0x005c, SQLiteDatabaseLockedException -> 0x00bc, SQLiteException -> 0x005a, all -> 0x0056 }
            com.google.android.gms.measurement.internal.zzgk r0 = r0.zzg()     // Catch:{ SQLiteFullException -> 0x005c, SQLiteDatabaseLockedException -> 0x00bc, SQLiteException -> 0x005a, all -> 0x0056 }
            java.lang.String r14 = "Data loss, local db full"
            r0.zza(r14)     // Catch:{ SQLiteFullException -> 0x005c, SQLiteDatabaseLockedException -> 0x00bc, SQLiteException -> 0x005a, all -> 0x0056 }
            r14 = 100001(0x186a1, double:4.9407E-319)
            long r14 = r14 - r11
            java.lang.String r0 = "rowid in (select rowid from messages order by rowid asc limit ?)"
            java.lang.String r11 = java.lang.Long.toString(r14)     // Catch:{ SQLiteFullException -> 0x005c, SQLiteDatabaseLockedException -> 0x00bc, SQLiteException -> 0x005a, all -> 0x0056 }
            java.lang.String[] r11 = new java.lang.String[]{r11}     // Catch:{ SQLiteFullException -> 0x005c, SQLiteDatabaseLockedException -> 0x00bc, SQLiteException -> 0x005a, all -> 0x0056 }
            int r0 = r9.delete(r13, r0, r11)     // Catch:{ SQLiteFullException -> 0x005c, SQLiteDatabaseLockedException -> 0x00bc, SQLiteException -> 0x005a, all -> 0x0056 }
            long r11 = (long) r0     // Catch:{ SQLiteFullException -> 0x005c, SQLiteDatabaseLockedException -> 0x00bc, SQLiteException -> 0x005a, all -> 0x0056 }
            int r0 = (r11 > r14 ? 1 : (r11 == r14 ? 0 : -1))
            if (r0 == 0) goto L_0x00a9
            com.google.android.gms.measurement.internal.zzgi r0 = r16.zzj()     // Catch:{ SQLiteFullException -> 0x005c, SQLiteDatabaseLockedException -> 0x00bc, SQLiteException -> 0x005a, all -> 0x0056 }
            com.google.android.gms.measurement.internal.zzgk r0 = r0.zzg()     // Catch:{ SQLiteFullException -> 0x005c, SQLiteDatabaseLockedException -> 0x00bc, SQLiteException -> 0x005a, all -> 0x0056 }
            java.lang.String r4 = "Different delete count than expected in local db. expected, received, difference"
            java.lang.Long r2 = java.lang.Long.valueOf(r14)     // Catch:{ SQLiteFullException -> 0x005c, SQLiteDatabaseLockedException -> 0x00bc, SQLiteException -> 0x005a, all -> 0x0056 }
            java.lang.Long r8 = java.lang.Long.valueOf(r11)     // Catch:{ SQLiteFullException -> 0x005c, SQLiteDatabaseLockedException -> 0x00bc, SQLiteException -> 0x005a, all -> 0x0056 }
            long r14 = r14 - r11
            java.lang.Long r11 = java.lang.Long.valueOf(r14)     // Catch:{ SQLiteFullException -> 0x005c, SQLiteDatabaseLockedException -> 0x00bc, SQLiteException -> 0x005a, all -> 0x0056 }
            r0.zza(r4, r2, r8, r11)     // Catch:{ SQLiteFullException -> 0x005c, SQLiteDatabaseLockedException -> 0x00bc, SQLiteException -> 0x005a, all -> 0x0056 }
        L_0x00a9:
            r9.insertOrThrow(r13, r7, r3)     // Catch:{ SQLiteFullException -> 0x005c, SQLiteDatabaseLockedException -> 0x00bc, SQLiteException -> 0x005a, all -> 0x0056 }
            r9.setTransactionSuccessful()     // Catch:{ SQLiteFullException -> 0x005c, SQLiteDatabaseLockedException -> 0x00bc, SQLiteException -> 0x005a, all -> 0x0056 }
            r9.endTransaction()     // Catch:{ SQLiteFullException -> 0x005c, SQLiteDatabaseLockedException -> 0x00bc, SQLiteException -> 0x005a, all -> 0x0056 }
            if (r10 == 0) goto L_0x00b7
            r10.close()
        L_0x00b7:
            r9.close()
            r1 = 1
            return r1
        L_0x00bc:
            r7 = r10
            goto L_0x00ee
        L_0x00be:
            r0 = move-exception
            r9 = r7
            goto L_0x0121
        L_0x00c1:
            r0 = move-exception
            r10 = r7
        L_0x00c3:
            if (r7 == 0) goto L_0x00d2
            boolean r2 = r7.inTransaction()     // Catch:{ all -> 0x00cf }
            if (r2 == 0) goto L_0x00d2
            r7.endTransaction()     // Catch:{ all -> 0x00cf }
            goto L_0x00d2
        L_0x00cf:
            r0 = move-exception
            r9 = r7
            goto L_0x0057
        L_0x00d2:
            com.google.android.gms.measurement.internal.zzgi r2 = r16.zzj()     // Catch:{ all -> 0x00cf }
            com.google.android.gms.measurement.internal.zzgk r2 = r2.zzg()     // Catch:{ all -> 0x00cf }
            java.lang.String r4 = "Error writing entry to local database"
            r2.zza(r4, r0)     // Catch:{ all -> 0x00cf }
            r2 = 1
            r1.zzb = r2     // Catch:{ all -> 0x00cf }
            if (r10 == 0) goto L_0x00e7
            r10.close()
        L_0x00e7:
            if (r7 == 0) goto L_0x011b
            r7.close()
            goto L_0x011b
        L_0x00ed:
            r9 = r7
        L_0x00ee:
            long r10 = (long) r6
            android.os.SystemClock.sleep(r10)     // Catch:{ all -> 0x0035 }
            int r6 = r6 + 20
            if (r7 == 0) goto L_0x00f9
            r7.close()
        L_0x00f9:
            if (r9 == 0) goto L_0x011b
            r9.close()
            goto L_0x011b
        L_0x00ff:
            r0 = move-exception
            r9 = r7
        L_0x0101:
            com.google.android.gms.measurement.internal.zzgi r2 = r16.zzj()     // Catch:{ all -> 0x0035 }
            com.google.android.gms.measurement.internal.zzgk r2 = r2.zzg()     // Catch:{ all -> 0x0035 }
            java.lang.String r4 = "Error writing entry; local database full"
            r2.zza(r4, r0)     // Catch:{ all -> 0x0035 }
            r2 = 1
            r1.zzb = r2     // Catch:{ all -> 0x0035 }
            if (r7 == 0) goto L_0x0116
            r7.close()
        L_0x0116:
            if (r9 == 0) goto L_0x011b
            r9.close()
        L_0x011b:
            int r5 = r5 + 1
            r2 = 0
            r4 = 5
            goto L_0x0023
        L_0x0121:
            if (r7 == 0) goto L_0x0126
            r7.close()
        L_0x0126:
            if (r9 == 0) goto L_0x012b
            r9.close()
        L_0x012b:
            throw r0
        L_0x012c:
            com.google.android.gms.measurement.internal.zzgi r0 = r16.zzj()
            com.google.android.gms.measurement.internal.zzgk r0 = r0.zzp()
            java.lang.String r1 = "Failed to write entry to local database"
            r0.zza(r1)
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzgf.zza(int, byte[]):boolean");
    }

    public final boolean zza(zzbh zzbh) {
        Parcel obtain = Parcel.obtain();
        zzbh.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zza(0, marshall);
        }
        zzj().zzm().zza("Event is too long for local database. Sending event directly to service");
        return false;
    }

    public final boolean zza(zzok zzok) {
        Parcel obtain = Parcel.obtain();
        zzok.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zza(1, marshall);
        }
        zzj().zzm().zza("User property too long for local database. Sending directly to service");
        return false;
    }
}
