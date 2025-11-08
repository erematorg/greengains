package com.google.android.gms.measurement.internal;

import A.a;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import androidx.browser.trusted.c;
import androidx.camera.camera2.internal.C0118y;
import androidx.collection.ArrayMap;
import androidx.emoji2.emojipicker.StickyVariantProvider;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzfn;
import com.google.android.gms.internal.measurement.zzgn;
import com.google.android.gms.internal.measurement.zzlc;
import com.google.android.gms.internal.measurement.zzrw;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.xml.serialize.LineSeparator;
import org.slf4j.Marker;

final class zzam extends zznr {
    /* access modifiers changed from: private */
    public static final String[] zza = {"last_bundled_timestamp", "ALTER TABLE events ADD COLUMN last_bundled_timestamp INTEGER;", "last_bundled_day", "ALTER TABLE events ADD COLUMN last_bundled_day INTEGER;", "last_sampled_complex_event_id", "ALTER TABLE events ADD COLUMN last_sampled_complex_event_id INTEGER;", "last_sampling_rate", "ALTER TABLE events ADD COLUMN last_sampling_rate INTEGER;", "last_exempt_from_sampling", "ALTER TABLE events ADD COLUMN last_exempt_from_sampling INTEGER;", "current_session_count", "ALTER TABLE events ADD COLUMN current_session_count INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zzb = {"origin", "ALTER TABLE user_attributes ADD COLUMN origin TEXT;"};
    /* access modifiers changed from: private */
    public static final String[] zzc = {"app_version", "ALTER TABLE apps ADD COLUMN app_version TEXT;", "app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;", "gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;", "dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;", "measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;", "last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;", "day", "ALTER TABLE apps ADD COLUMN day INTEGER;", "daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;", "daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;", "daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;", "remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;", "config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;", "failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;", "app_version_int", "ALTER TABLE apps ADD COLUMN app_version_int INTEGER;", "firebase_instance_id", "ALTER TABLE apps ADD COLUMN firebase_instance_id TEXT;", "daily_error_events_count", "ALTER TABLE apps ADD COLUMN daily_error_events_count INTEGER;", "daily_realtime_events_count", "ALTER TABLE apps ADD COLUMN daily_realtime_events_count INTEGER;", "health_monitor_sample", "ALTER TABLE apps ADD COLUMN health_monitor_sample TEXT;", "android_id", "ALTER TABLE apps ADD COLUMN android_id INTEGER;", "adid_reporting_enabled", "ALTER TABLE apps ADD COLUMN adid_reporting_enabled INTEGER;", "ssaid_reporting_enabled", "ALTER TABLE apps ADD COLUMN ssaid_reporting_enabled INTEGER;", "admob_app_id", "ALTER TABLE apps ADD COLUMN admob_app_id TEXT;", "linked_admob_app_id", "ALTER TABLE apps ADD COLUMN linked_admob_app_id TEXT;", "dynamite_version", "ALTER TABLE apps ADD COLUMN dynamite_version INTEGER;", "safelisted_events", "ALTER TABLE apps ADD COLUMN safelisted_events TEXT;", "ga_app_id", "ALTER TABLE apps ADD COLUMN ga_app_id TEXT;", "config_last_modified_time", "ALTER TABLE apps ADD COLUMN config_last_modified_time TEXT;", "e_tag", "ALTER TABLE apps ADD COLUMN e_tag TEXT;", "session_stitching_token", "ALTER TABLE apps ADD COLUMN session_stitching_token TEXT;", "sgtm_upload_enabled", "ALTER TABLE apps ADD COLUMN sgtm_upload_enabled INTEGER;", "target_os_version", "ALTER TABLE apps ADD COLUMN target_os_version INTEGER;", "session_stitching_token_hash", "ALTER TABLE apps ADD COLUMN session_stitching_token_hash INTEGER;", "ad_services_version", "ALTER TABLE apps ADD COLUMN ad_services_version INTEGER;", "unmatched_first_open_without_ad_id", "ALTER TABLE apps ADD COLUMN unmatched_first_open_without_ad_id INTEGER;", "npa_metadata_value", "ALTER TABLE apps ADD COLUMN npa_metadata_value INTEGER;", "attribution_eligibility_status", "ALTER TABLE apps ADD COLUMN attribution_eligibility_status INTEGER;", "sgtm_preview_key", "ALTER TABLE apps ADD COLUMN sgtm_preview_key TEXT;", "dma_consent_state", "ALTER TABLE apps ADD COLUMN dma_consent_state INTEGER;", "daily_realtime_dcu_count", "ALTER TABLE apps ADD COLUMN daily_realtime_dcu_count INTEGER;", "bundle_delivery_index", "ALTER TABLE apps ADD COLUMN bundle_delivery_index INTEGER;", "serialized_npa_metadata", "ALTER TABLE apps ADD COLUMN serialized_npa_metadata TEXT;", "unmatched_pfo", "ALTER TABLE apps ADD COLUMN unmatched_pfo INTEGER;", "unmatched_uwa", "ALTER TABLE apps ADD COLUMN unmatched_uwa INTEGER;", "ad_campaign_info", "ALTER TABLE apps ADD COLUMN ad_campaign_info BLOB;"};
    /* access modifiers changed from: private */
    public static final String[] zzd = {"realtime", "ALTER TABLE raw_events ADD COLUMN realtime INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zze = {"has_realtime", "ALTER TABLE queue ADD COLUMN has_realtime INTEGER;", "retry_count", "ALTER TABLE queue ADD COLUMN retry_count INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zzf = {"session_scoped", "ALTER TABLE event_filters ADD COLUMN session_scoped BOOLEAN;"};
    /* access modifiers changed from: private */
    public static final String[] zzh = {"session_scoped", "ALTER TABLE property_filters ADD COLUMN session_scoped BOOLEAN;"};
    /* access modifiers changed from: private */
    public static final String[] zzi = {"previous_install_count", "ALTER TABLE app2 ADD COLUMN previous_install_count INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zzj = {"consent_source", "ALTER TABLE consent_settings ADD COLUMN consent_source INTEGER;", "dma_consent_settings", "ALTER TABLE consent_settings ADD COLUMN dma_consent_settings TEXT;", "storage_consent_at_bundling", "ALTER TABLE consent_settings ADD COLUMN storage_consent_at_bundling TEXT;"};
    /* access modifiers changed from: private */
    public static final String[] zzk = {"idempotent", "CREATE INDEX IF NOT EXISTS trigger_uris_index ON trigger_uris (app_id);"};
    private final zzav zzl = new zzav(this, zza(), "google_app_measurement.db");
    /* access modifiers changed from: private */
    public final zznl zzm = new zznl(zzb());

    public zzam(zznv zznv) {
        super(zznv);
    }

    private final String zzao() {
        long currentTimeMillis = zzb().currentTimeMillis();
        zznt zznt = zznt.GOOGLE_SIGNAL;
        return C0118y.g("app_id=? AND (", "(upload_type = " + zznt.zza() + " AND (ABS(creation_timestamp - " + currentTimeMillis + ") <= CAST(" + zzah.zzm() + " AS INTEGER)))", " OR", "(upload_type != " + zznt.zza() + " AND (ABS(creation_timestamp - " + currentTimeMillis + ") <= CAST(" + zzah.zzo() + " AS INTEGER)))", ")");
    }

    public final long b_() {
        Cursor cursor = null;
        try {
            cursor = e_().rawQuery("select rowid from raw_events order by rowid desc limit 1;", (String[]) null);
            if (!cursor.moveToFirst()) {
                cursor.close();
                return -1;
            }
            long j2 = cursor.getLong(0);
            cursor.close();
            return j2;
        } catch (SQLiteException e3) {
            zzj().zzg().zza("Error querying raw events", e3);
            if (cursor != null) {
                cursor.close();
            }
            return -1;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    @WorkerThread
    public final long c_() {
        return zza("select max(bundle_end_timestamp) from queue", (String[]) null, 0);
    }

    @WorkerThread
    public final long d_() {
        return zza("select max(timestamp) from raw_events", (String[]) null, 0);
    }

    @VisibleForTesting
    @WorkerThread
    public final SQLiteDatabase e_() {
        zzt();
        try {
            return this.zzl.getWritableDatabase();
        } catch (SQLiteException e3) {
            zzj().zzu().zza("Error opening database", e3);
            throw e3;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x003c  */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String f_() {
        /*
            r4 = this;
            android.database.sqlite.SQLiteDatabase r0 = r4.e_()
            r1 = 0
            java.lang.String r2 = "select app_id from queue order by has_realtime desc, rowid asc limit 1;"
            android.database.Cursor r0 = r0.rawQuery(r2, r1)     // Catch:{ SQLiteException -> 0x0025, all -> 0x0023 }
            boolean r2 = r0.moveToFirst()     // Catch:{ SQLiteException -> 0x001d }
            if (r2 == 0) goto L_0x001f
            r2 = 0
            java.lang.String r4 = r0.getString(r2)     // Catch:{ SQLiteException -> 0x001d }
            r0.close()
            return r4
        L_0x001a:
            r4 = move-exception
            r1 = r0
            goto L_0x003a
        L_0x001d:
            r2 = move-exception
            goto L_0x0027
        L_0x001f:
            r0.close()
            return r1
        L_0x0023:
            r4 = move-exception
            goto L_0x003a
        L_0x0025:
            r2 = move-exception
            r0 = r1
        L_0x0027:
            com.google.android.gms.measurement.internal.zzgi r4 = r4.zzj()     // Catch:{ all -> 0x001a }
            com.google.android.gms.measurement.internal.zzgk r4 = r4.zzg()     // Catch:{ all -> 0x001a }
            java.lang.String r3 = "Database error getting next bundle app id"
            r4.zza(r3, r2)     // Catch:{ all -> 0x001a }
            if (r0 == 0) goto L_0x0039
            r0.close()
        L_0x0039:
            return r1
        L_0x003a:
            if (r1 == 0) goto L_0x003f
            r1.close()
        L_0x003f:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzam.f_():java.lang.String");
    }

    @VisibleForTesting
    public final boolean zzaa() {
        return zza().getDatabasePath("google_app_measurement.db").exists();
    }

    /* JADX INFO: finally extract failed */
    @VisibleForTesting
    @WorkerThread
    public final long zzb(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzt();
        zzal();
        SQLiteDatabase e_ = e_();
        e_.beginTransaction();
        long j2 = 0;
        try {
            long zza2 = zza("select " + str2 + " from app2 where app_id=?", new String[]{str}, -1);
            if (zza2 == -1) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("app_id", str);
                contentValues.put("first_open_count", 0);
                contentValues.put("previous_install_count", 0);
                if (e_.insertWithOnConflict("app2", (String) null, contentValues, 5) == -1) {
                    zzj().zzg().zza("Failed to insert column (got -1). appId", zzgi.zza(str), str2);
                    e_.endTransaction();
                    return -1;
                }
                zza2 = 0;
            }
            try {
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("app_id", str);
                contentValues2.put(str2, Long.valueOf(1 + zza2));
                if (((long) e_.update("app2", contentValues2, "app_id = ?", new String[]{str})) == 0) {
                    zzj().zzg().zza("Failed to update column (got 0). appId", zzgi.zza(str), str2);
                    e_.endTransaction();
                    return -1;
                }
                e_.setTransactionSuccessful();
                e_.endTransaction();
                return zza2;
            } catch (SQLiteException e3) {
                long j3 = zza2;
                e = e3;
                j2 = j3;
                try {
                    zzj().zzg().zza("Error inserting column. appId", zzgi.zza(str), str2, e);
                    e_.endTransaction();
                    return j2;
                } catch (Throwable th) {
                    e_.endTransaction();
                    throw th;
                }
            }
        } catch (SQLiteException e4) {
            e = e4;
            zzj().zzg().zza("Error inserting column. appId", zzgi.zza(str), str2, e);
            e_.endTransaction();
            return j2;
        }
    }

    public final boolean zzc() {
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0086  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.os.Bundle zzd(java.lang.String r6) {
        /*
            r5 = this;
            r5.zzt()
            r5.zzal()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r5.e_()     // Catch:{ SQLiteException -> 0x006f, all -> 0x006d }
            java.lang.String r2 = "select parameters from default_event_params where app_id=?"
            java.lang.String[] r3 = new java.lang.String[]{r6}     // Catch:{ SQLiteException -> 0x006f, all -> 0x006d }
            android.database.Cursor r1 = r1.rawQuery(r2, r3)     // Catch:{ SQLiteException -> 0x006f, all -> 0x006d }
            boolean r2 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x002f }
            if (r2 != 0) goto L_0x0031
            com.google.android.gms.measurement.internal.zzgi r6 = r5.zzj()     // Catch:{ SQLiteException -> 0x002f }
            com.google.android.gms.measurement.internal.zzgk r6 = r6.zzp()     // Catch:{ SQLiteException -> 0x002f }
            java.lang.String r2 = "Default event parameters not found"
            r6.zza(r2)     // Catch:{ SQLiteException -> 0x002f }
            r1.close()
            return r0
        L_0x002c:
            r5 = move-exception
            r0 = r1
            goto L_0x0084
        L_0x002f:
            r6 = move-exception
            goto L_0x0071
        L_0x0031:
            r2 = 0
            byte[] r2 = r1.getBlob(r2)     // Catch:{ SQLiteException -> 0x002f }
            com.google.android.gms.internal.measurement.zzgn$zzf$zza r3 = com.google.android.gms.internal.measurement.zzgn.zzf.zze()     // Catch:{ IOException -> 0x0057 }
            com.google.android.gms.internal.measurement.zzmk r2 = com.google.android.gms.measurement.internal.zzol.zza(r3, (byte[]) r2)     // Catch:{ IOException -> 0x0057 }
            com.google.android.gms.internal.measurement.zzgn$zzf$zza r2 = (com.google.android.gms.internal.measurement.zzgn.zzf.zza) r2     // Catch:{ IOException -> 0x0057 }
            com.google.android.gms.internal.measurement.zzml r2 = r2.zzai()     // Catch:{ IOException -> 0x0057 }
            com.google.android.gms.internal.measurement.zzlc r2 = (com.google.android.gms.internal.measurement.zzlc) r2     // Catch:{ IOException -> 0x0057 }
            com.google.android.gms.internal.measurement.zzgn$zzf r2 = (com.google.android.gms.internal.measurement.zzgn.zzf) r2     // Catch:{ IOException -> 0x0057 }
            r5.g_()     // Catch:{ SQLiteException -> 0x002f }
            java.util.List r6 = r2.zzh()     // Catch:{ SQLiteException -> 0x002f }
            android.os.Bundle r5 = com.google.android.gms.measurement.internal.zzol.zza((java.util.List<com.google.android.gms.internal.measurement.zzgn.zzh>) r6)     // Catch:{ SQLiteException -> 0x002f }
            r1.close()
            return r5
        L_0x0057:
            r2 = move-exception
            com.google.android.gms.measurement.internal.zzgi r3 = r5.zzj()     // Catch:{ SQLiteException -> 0x002f }
            com.google.android.gms.measurement.internal.zzgk r3 = r3.zzg()     // Catch:{ SQLiteException -> 0x002f }
            java.lang.String r4 = "Failed to retrieve default event parameters. appId"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r6)     // Catch:{ SQLiteException -> 0x002f }
            r3.zza(r4, r6, r2)     // Catch:{ SQLiteException -> 0x002f }
            r1.close()
            return r0
        L_0x006d:
            r5 = move-exception
            goto L_0x0084
        L_0x006f:
            r6 = move-exception
            r1 = r0
        L_0x0071:
            com.google.android.gms.measurement.internal.zzgi r5 = r5.zzj()     // Catch:{ all -> 0x002c }
            com.google.android.gms.measurement.internal.zzgk r5 = r5.zzg()     // Catch:{ all -> 0x002c }
            java.lang.String r2 = "Error selecting default event parameters"
            r5.zza(r2, r6)     // Catch:{ all -> 0x002c }
            if (r1 == 0) goto L_0x0083
            r1.close()
        L_0x0083:
            return r0
        L_0x0084:
            if (r0 == 0) goto L_0x0089
            r0.close()
        L_0x0089:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzam.zzd(java.lang.String):android.os.Bundle");
    }

    /* JADX WARNING: Removed duplicated region for block: B:104:0x030f A[Catch:{ SQLiteException -> 0x00b8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0320 A[Catch:{ SQLiteException -> 0x00b8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x036a A[Catch:{ SQLiteException -> 0x00b8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x0396  */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x039c  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0134 A[Catch:{ SQLiteException -> 0x00b8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x017f A[Catch:{ SQLiteException -> 0x00b8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0183 A[Catch:{ SQLiteException -> 0x00b8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x01b7 A[Catch:{ SQLiteException -> 0x00b8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x01d5 A[Catch:{ SQLiteException -> 0x00b8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x01d8 A[Catch:{ SQLiteException -> 0x00b8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x01e7 A[Catch:{ SQLiteException -> 0x00b8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x023d A[Catch:{ SQLiteException -> 0x00b8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x02c5 A[Catch:{ SQLiteException -> 0x00b8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x02c7 A[Catch:{ SQLiteException -> 0x00b8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x02d3 A[Catch:{ SQLiteException -> 0x00b8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x02d5 A[Catch:{ SQLiteException -> 0x00b8 }] */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzh zze(java.lang.String r51) {
        /*
            r50 = this;
            r1 = r50
            r2 = r51
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r51)
            r50.zzt()
            r50.zzal()
            r3 = 0
            android.database.sqlite.SQLiteDatabase r4 = r50.e_()     // Catch:{ SQLiteException -> 0x0381, all -> 0x037f }
            java.lang.String r5 = "apps"
            java.lang.String r6 = "app_instance_id"
            java.lang.String r7 = "gmp_app_id"
            java.lang.String r8 = "resettable_device_id_hash"
            java.lang.String r9 = "last_bundle_index"
            java.lang.String r10 = "last_bundle_start_timestamp"
            java.lang.String r11 = "last_bundle_end_timestamp"
            java.lang.String r12 = "app_version"
            java.lang.String r13 = "app_store"
            java.lang.String r14 = "gmp_version"
            java.lang.String r15 = "dev_cert_hash"
            java.lang.String r16 = "measurement_enabled"
            java.lang.String r17 = "day"
            java.lang.String r18 = "daily_public_events_count"
            java.lang.String r19 = "daily_events_count"
            java.lang.String r20 = "daily_conversions_count"
            java.lang.String r21 = "config_fetched_time"
            java.lang.String r22 = "failed_config_fetch_time"
            java.lang.String r23 = "app_version_int"
            java.lang.String r24 = "firebase_instance_id"
            java.lang.String r25 = "daily_error_events_count"
            java.lang.String r26 = "daily_realtime_events_count"
            java.lang.String r27 = "health_monitor_sample"
            java.lang.String r28 = "android_id"
            java.lang.String r29 = "adid_reporting_enabled"
            java.lang.String r30 = "admob_app_id"
            java.lang.String r31 = "dynamite_version"
            java.lang.String r32 = "safelisted_events"
            java.lang.String r33 = "ga_app_id"
            java.lang.String r34 = "session_stitching_token"
            java.lang.String r35 = "sgtm_upload_enabled"
            java.lang.String r36 = "target_os_version"
            java.lang.String r37 = "session_stitching_token_hash"
            java.lang.String r38 = "ad_services_version"
            java.lang.String r39 = "unmatched_first_open_without_ad_id"
            java.lang.String r40 = "npa_metadata_value"
            java.lang.String r41 = "attribution_eligibility_status"
            java.lang.String r42 = "sgtm_preview_key"
            java.lang.String r43 = "dma_consent_state"
            java.lang.String r44 = "daily_realtime_dcu_count"
            java.lang.String r45 = "bundle_delivery_index"
            java.lang.String r46 = "serialized_npa_metadata"
            java.lang.String r47 = "unmatched_pfo"
            java.lang.String r48 = "unmatched_uwa"
            java.lang.String r49 = "ad_campaign_info"
            java.lang.String[] r6 = new java.lang.String[]{r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49}     // Catch:{ SQLiteException -> 0x0381, all -> 0x037f }
            java.lang.String r7 = "app_id=?"
            java.lang.String[] r8 = new java.lang.String[]{r51}     // Catch:{ SQLiteException -> 0x0381, all -> 0x037f }
            r10 = 0
            r11 = 0
            r9 = 0
            android.database.Cursor r4 = r4.query(r5, r6, r7, r8, r9, r10, r11)     // Catch:{ SQLiteException -> 0x0381, all -> 0x037f }
            boolean r0 = r4.moveToFirst()     // Catch:{ SQLiteException -> 0x00b8 }
            if (r0 != 0) goto L_0x0087
            r4.close()
            return r3
        L_0x0087:
            com.google.android.gms.measurement.internal.zzh r0 = new com.google.android.gms.measurement.internal.zzh     // Catch:{ SQLiteException -> 0x00b8 }
            com.google.android.gms.measurement.internal.zznv r5 = r1.zzg     // Catch:{ SQLiteException -> 0x00b8 }
            com.google.android.gms.measurement.internal.zzhw r5 = r5.zzk()     // Catch:{ SQLiteException -> 0x00b8 }
            r0.<init>(r5, r2)     // Catch:{ SQLiteException -> 0x00b8 }
            boolean r5 = com.google.android.gms.internal.measurement.zzpd.zza()     // Catch:{ SQLiteException -> 0x00b8 }
            r6 = 0
            if (r5 == 0) goto L_0x00bb
            com.google.android.gms.measurement.internal.zzah r5 = r50.zze()     // Catch:{ SQLiteException -> 0x00b8 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r7 = com.google.android.gms.measurement.internal.zzbj.zzdc     // Catch:{ SQLiteException -> 0x00b8 }
            boolean r5 = r5.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r7)     // Catch:{ SQLiteException -> 0x00b8 }
            if (r5 == 0) goto L_0x00bb
            com.google.android.gms.measurement.internal.zznv r5 = r1.zzg     // Catch:{ SQLiteException -> 0x00b8 }
            com.google.android.gms.measurement.internal.zzjc r5 = r5.zzb((java.lang.String) r2)     // Catch:{ SQLiteException -> 0x00b8 }
            com.google.android.gms.measurement.internal.zzjc$zza r7 = com.google.android.gms.measurement.internal.zzjc.zza.ANALYTICS_STORAGE     // Catch:{ SQLiteException -> 0x00b8 }
            boolean r5 = r5.zza((com.google.android.gms.measurement.internal.zzjc.zza) r7)     // Catch:{ SQLiteException -> 0x00b8 }
            if (r5 == 0) goto L_0x00c2
            goto L_0x00bb
        L_0x00b4:
            r0 = move-exception
            r3 = r4
            goto L_0x039a
        L_0x00b8:
            r0 = move-exception
            goto L_0x0383
        L_0x00bb:
            java.lang.String r5 = r4.getString(r6)     // Catch:{ SQLiteException -> 0x00b8 }
            r0.zzb((java.lang.String) r5)     // Catch:{ SQLiteException -> 0x00b8 }
        L_0x00c2:
            r5 = 1
            java.lang.String r7 = r4.getString(r5)     // Catch:{ SQLiteException -> 0x00b8 }
            r0.zzf((java.lang.String) r7)     // Catch:{ SQLiteException -> 0x00b8 }
            boolean r7 = com.google.android.gms.internal.measurement.zzpd.zza()     // Catch:{ SQLiteException -> 0x00b8 }
            if (r7 == 0) goto L_0x00ea
            com.google.android.gms.measurement.internal.zzah r7 = r50.zze()     // Catch:{ SQLiteException -> 0x00b8 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r8 = com.google.android.gms.measurement.internal.zzbj.zzdc     // Catch:{ SQLiteException -> 0x00b8 }
            boolean r7 = r7.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r8)     // Catch:{ SQLiteException -> 0x00b8 }
            if (r7 == 0) goto L_0x00ea
            com.google.android.gms.measurement.internal.zznv r7 = r1.zzg     // Catch:{ SQLiteException -> 0x00b8 }
            com.google.android.gms.measurement.internal.zzjc r7 = r7.zzb((java.lang.String) r2)     // Catch:{ SQLiteException -> 0x00b8 }
            com.google.android.gms.measurement.internal.zzjc$zza r8 = com.google.android.gms.measurement.internal.zzjc.zza.AD_STORAGE     // Catch:{ SQLiteException -> 0x00b8 }
            boolean r7 = r7.zza((com.google.android.gms.measurement.internal.zzjc.zza) r8)     // Catch:{ SQLiteException -> 0x00b8 }
            if (r7 == 0) goto L_0x00f2
        L_0x00ea:
            r7 = 2
            java.lang.String r7 = r4.getString(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r0.zzh((java.lang.String) r7)     // Catch:{ SQLiteException -> 0x00b8 }
        L_0x00f2:
            r7 = 3
            long r7 = r4.getLong(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r0.zzq(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r7 = 4
            long r7 = r4.getLong(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r0.zzr(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r7 = 5
            long r7 = r4.getLong(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r0.zzp(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r7 = 6
            java.lang.String r7 = r4.getString(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r0.zzd((java.lang.String) r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r7 = 7
            java.lang.String r7 = r4.getString(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r0.zzc((java.lang.String) r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r7 = 8
            long r7 = r4.getLong(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r0.zzn(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r7 = 9
            long r7 = r4.getLong(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r0.zzk((long) r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r7 = 10
            boolean r8 = r4.isNull(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            if (r8 != 0) goto L_0x013d
            int r7 = r4.getInt(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            if (r7 == 0) goto L_0x013b
            goto L_0x013d
        L_0x013b:
            r7 = r6
            goto L_0x013e
        L_0x013d:
            r7 = r5
        L_0x013e:
            r0.zzb((boolean) r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r7 = 11
            long r7 = r4.getLong(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r0.zzj((long) r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r7 = 12
            long r7 = r4.getLong(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r0.zzh((long) r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r7 = 13
            long r7 = r4.getLong(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r0.zzg((long) r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r7 = 14
            long r7 = r4.getLong(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r0.zze((long) r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r7 = 15
            long r7 = r4.getLong(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r0.zzd((long) r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r7 = 16
            long r7 = r4.getLong(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r0.zzm(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r7 = 17
            boolean r8 = r4.isNull(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            if (r8 == 0) goto L_0x0183
            r7 = -2147483648(0xffffffff80000000, double:NaN)
            goto L_0x0188
        L_0x0183:
            int r7 = r4.getInt(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            long r7 = (long) r7     // Catch:{ SQLiteException -> 0x00b8 }
        L_0x0188:
            r0.zzb((long) r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r7 = 18
            java.lang.String r7 = r4.getString(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r0.zze((java.lang.String) r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r7 = 19
            long r7 = r4.getLong(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r0.zzf((long) r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r7 = 20
            long r7 = r4.getLong(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r0.zzi((long) r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r7 = 21
            java.lang.String r7 = r4.getString(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r0.zzg((java.lang.String) r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r7 = 23
            boolean r8 = r4.isNull(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            if (r8 != 0) goto L_0x01c0
            int r7 = r4.getInt(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            if (r7 == 0) goto L_0x01be
            goto L_0x01c0
        L_0x01be:
            r7 = r6
            goto L_0x01c1
        L_0x01c0:
            r7 = r5
        L_0x01c1:
            r0.zza((boolean) r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r7 = 24
            java.lang.String r7 = r4.getString(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r0.zza((java.lang.String) r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r7 = 25
            boolean r8 = r4.isNull(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            if (r8 == 0) goto L_0x01d8
            r7 = 0
            goto L_0x01dc
        L_0x01d8:
            long r7 = r4.getLong(r7)     // Catch:{ SQLiteException -> 0x00b8 }
        L_0x01dc:
            r0.zzl(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r7 = 26
            boolean r8 = r4.isNull(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            if (r8 != 0) goto L_0x01f9
            java.lang.String r7 = r4.getString(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            java.lang.String r8 = ","
            r9 = -1
            java.lang.String[] r7 = r7.split(r8, r9)     // Catch:{ SQLiteException -> 0x00b8 }
            java.util.List r7 = java.util.Arrays.asList(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r0.zza((java.util.List<java.lang.String>) r7)     // Catch:{ SQLiteException -> 0x00b8 }
        L_0x01f9:
            boolean r7 = com.google.android.gms.internal.measurement.zzpd.zza()     // Catch:{ SQLiteException -> 0x00b8 }
            if (r7 == 0) goto L_0x0219
            com.google.android.gms.measurement.internal.zzah r7 = r50.zze()     // Catch:{ SQLiteException -> 0x00b8 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r8 = com.google.android.gms.measurement.internal.zzbj.zzdc     // Catch:{ SQLiteException -> 0x00b8 }
            boolean r7 = r7.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r8)     // Catch:{ SQLiteException -> 0x00b8 }
            if (r7 == 0) goto L_0x0219
            com.google.android.gms.measurement.internal.zznv r7 = r1.zzg     // Catch:{ SQLiteException -> 0x00b8 }
            com.google.android.gms.measurement.internal.zzjc r7 = r7.zzb((java.lang.String) r2)     // Catch:{ SQLiteException -> 0x00b8 }
            com.google.android.gms.measurement.internal.zzjc$zza r8 = com.google.android.gms.measurement.internal.zzjc.zza.ANALYTICS_STORAGE     // Catch:{ SQLiteException -> 0x00b8 }
            boolean r7 = r7.zza((com.google.android.gms.measurement.internal.zzjc.zza) r8)     // Catch:{ SQLiteException -> 0x00b8 }
            if (r7 == 0) goto L_0x0222
        L_0x0219:
            r7 = 28
            java.lang.String r7 = r4.getString(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r0.zzj((java.lang.String) r7)     // Catch:{ SQLiteException -> 0x00b8 }
        L_0x0222:
            boolean r7 = com.google.android.gms.internal.measurement.zzrw.zza()     // Catch:{ SQLiteException -> 0x00b8 }
            if (r7 == 0) goto L_0x026f
            com.google.android.gms.measurement.internal.zzah r7 = r50.zze()     // Catch:{ SQLiteException -> 0x00b8 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r8 = com.google.android.gms.measurement.internal.zzbj.zzbw     // Catch:{ SQLiteException -> 0x00b8 }
            boolean r7 = r7.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r8)     // Catch:{ SQLiteException -> 0x00b8 }
            if (r7 == 0) goto L_0x026f
            r50.zzq()     // Catch:{ SQLiteException -> 0x00b8 }
            boolean r7 = com.google.android.gms.measurement.internal.zzop.zzf(r51)     // Catch:{ SQLiteException -> 0x00b8 }
            if (r7 == 0) goto L_0x026f
            r7 = 29
            boolean r8 = r4.isNull(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            if (r8 != 0) goto L_0x024d
            int r7 = r4.getInt(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            if (r7 == 0) goto L_0x024d
            r7 = r5
            goto L_0x024e
        L_0x024d:
            r7 = r6
        L_0x024e:
            r0.zzc((boolean) r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r7 = 39
            long r7 = r4.getLong(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r0.zzo(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            com.google.android.gms.measurement.internal.zzah r7 = r50.zze()     // Catch:{ SQLiteException -> 0x00b8 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r8 = com.google.android.gms.measurement.internal.zzbj.zzbx     // Catch:{ SQLiteException -> 0x00b8 }
            boolean r7 = r7.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r8)     // Catch:{ SQLiteException -> 0x00b8 }
            if (r7 == 0) goto L_0x026f
            r7 = 36
            java.lang.String r7 = r4.getString(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r0.zzk((java.lang.String) r7)     // Catch:{ SQLiteException -> 0x00b8 }
        L_0x026f:
            r7 = 30
            long r7 = r4.getLong(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r0.zzt(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r7 = 31
            long r7 = r4.getLong(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r0.zzs(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            boolean r7 = com.google.android.gms.internal.measurement.zzrl.zza()     // Catch:{ SQLiteException -> 0x00b8 }
            if (r7 == 0) goto L_0x02a5
            com.google.android.gms.measurement.internal.zzah r7 = r50.zze()     // Catch:{ SQLiteException -> 0x00b8 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r8 = com.google.android.gms.measurement.internal.zzbj.zzcg     // Catch:{ SQLiteException -> 0x00b8 }
            boolean r7 = r7.zze(r2, r8)     // Catch:{ SQLiteException -> 0x00b8 }
            if (r7 == 0) goto L_0x02a5
            r7 = 32
            int r7 = r4.getInt(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r0.zza((int) r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r7 = 35
            long r7 = r4.getLong(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            r0.zzc((long) r7)     // Catch:{ SQLiteException -> 0x00b8 }
        L_0x02a5:
            boolean r7 = com.google.android.gms.internal.measurement.zzpo.zza()     // Catch:{ SQLiteException -> 0x00b8 }
            if (r7 == 0) goto L_0x02cb
            com.google.android.gms.measurement.internal.zzah r7 = r50.zze()     // Catch:{ SQLiteException -> 0x00b8 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r8 = com.google.android.gms.measurement.internal.zzbj.zzcr     // Catch:{ SQLiteException -> 0x00b8 }
            boolean r7 = r7.zze(r2, r8)     // Catch:{ SQLiteException -> 0x00b8 }
            if (r7 == 0) goto L_0x02cb
            r7 = 33
            boolean r8 = r4.isNull(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            if (r8 != 0) goto L_0x02c7
            int r7 = r4.getInt(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            if (r7 == 0) goto L_0x02c7
            r7 = r5
            goto L_0x02c8
        L_0x02c7:
            r7 = r6
        L_0x02c8:
            r0.zzd((boolean) r7)     // Catch:{ SQLiteException -> 0x00b8 }
        L_0x02cb:
            r7 = 34
            boolean r8 = r4.isNull(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            if (r8 == 0) goto L_0x02d5
            r5 = r3
            goto L_0x02e0
        L_0x02d5:
            int r7 = r4.getInt(r7)     // Catch:{ SQLiteException -> 0x00b8 }
            if (r7 == 0) goto L_0x02dc
            r6 = r5
        L_0x02dc:
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r6)     // Catch:{ SQLiteException -> 0x00b8 }
        L_0x02e0:
            r0.zza((java.lang.Boolean) r5)     // Catch:{ SQLiteException -> 0x00b8 }
            r5 = 37
            int r5 = r4.getInt(r5)     // Catch:{ SQLiteException -> 0x00b8 }
            r0.zzc((int) r5)     // Catch:{ SQLiteException -> 0x00b8 }
            r5 = 38
            int r5 = r4.getInt(r5)     // Catch:{ SQLiteException -> 0x00b8 }
            r0.zzb((int) r5)     // Catch:{ SQLiteException -> 0x00b8 }
            boolean r5 = com.google.android.gms.internal.measurement.zzox.zza()     // Catch:{ SQLiteException -> 0x00b8 }
            if (r5 == 0) goto L_0x0314
            com.google.android.gms.measurement.internal.zzah r5 = r50.zze()     // Catch:{ SQLiteException -> 0x00b8 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r6 = com.google.android.gms.measurement.internal.zzbj.zzcw     // Catch:{ SQLiteException -> 0x00b8 }
            boolean r5 = r5.zze(r2, r6)     // Catch:{ SQLiteException -> 0x00b8 }
            if (r5 == 0) goto L_0x0314
            r5 = 40
            java.lang.String r5 = r4.getString(r5)     // Catch:{ SQLiteException -> 0x00b8 }
            if (r5 != 0) goto L_0x0311
            java.lang.String r5 = ""
        L_0x0311:
            r0.zzi((java.lang.String) r5)     // Catch:{ SQLiteException -> 0x00b8 }
        L_0x0314:
            com.google.android.gms.measurement.internal.zzah r5 = r50.zze()     // Catch:{ SQLiteException -> 0x00b8 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r6 = com.google.android.gms.measurement.internal.zzbj.zzcz     // Catch:{ SQLiteException -> 0x00b8 }
            boolean r5 = r5.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r6)     // Catch:{ SQLiteException -> 0x00b8 }
            if (r5 == 0) goto L_0x0346
            r5 = 41
            boolean r6 = r4.isNull(r5)     // Catch:{ SQLiteException -> 0x00b8 }
            if (r6 != 0) goto L_0x0333
            long r5 = r4.getLong(r5)     // Catch:{ SQLiteException -> 0x00b8 }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ SQLiteException -> 0x00b8 }
            r0.zza((java.lang.Long) r5)     // Catch:{ SQLiteException -> 0x00b8 }
        L_0x0333:
            r5 = 42
            boolean r6 = r4.isNull(r5)     // Catch:{ SQLiteException -> 0x00b8 }
            if (r6 != 0) goto L_0x0346
            long r5 = r4.getLong(r5)     // Catch:{ SQLiteException -> 0x00b8 }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ SQLiteException -> 0x00b8 }
            r0.zzb((java.lang.Long) r5)     // Catch:{ SQLiteException -> 0x00b8 }
        L_0x0346:
            boolean r5 = com.google.android.gms.internal.measurement.zzqn.zza()     // Catch:{ SQLiteException -> 0x00b8 }
            if (r5 == 0) goto L_0x0361
            com.google.android.gms.measurement.internal.zzah r5 = r50.zze()     // Catch:{ SQLiteException -> 0x00b8 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r6 = com.google.android.gms.measurement.internal.zzbj.zzct     // Catch:{ SQLiteException -> 0x00b8 }
            boolean r5 = r5.zze(r2, r6)     // Catch:{ SQLiteException -> 0x00b8 }
            if (r5 == 0) goto L_0x0361
            r5 = 43
            byte[] r5 = r4.getBlob(r5)     // Catch:{ SQLiteException -> 0x00b8 }
            r0.zza((byte[]) r5)     // Catch:{ SQLiteException -> 0x00b8 }
        L_0x0361:
            r0.zzao()     // Catch:{ SQLiteException -> 0x00b8 }
            boolean r5 = r4.moveToNext()     // Catch:{ SQLiteException -> 0x00b8 }
            if (r5 == 0) goto L_0x037b
            com.google.android.gms.measurement.internal.zzgi r5 = r50.zzj()     // Catch:{ SQLiteException -> 0x00b8 }
            com.google.android.gms.measurement.internal.zzgk r5 = r5.zzg()     // Catch:{ SQLiteException -> 0x00b8 }
            java.lang.String r6 = "Got multiple records for app, expected one. appId"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r51)     // Catch:{ SQLiteException -> 0x00b8 }
            r5.zza(r6, r7)     // Catch:{ SQLiteException -> 0x00b8 }
        L_0x037b:
            r4.close()
            return r0
        L_0x037f:
            r0 = move-exception
            goto L_0x039a
        L_0x0381:
            r0 = move-exception
            r4 = r3
        L_0x0383:
            com.google.android.gms.measurement.internal.zzgi r1 = r50.zzj()     // Catch:{ all -> 0x00b4 }
            com.google.android.gms.measurement.internal.zzgk r1 = r1.zzg()     // Catch:{ all -> 0x00b4 }
            java.lang.String r5 = "Error querying app. appId"
            java.lang.Object r2 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r51)     // Catch:{ all -> 0x00b4 }
            r1.zza(r5, r2, r0)     // Catch:{ all -> 0x00b4 }
            if (r4 == 0) goto L_0x0399
            r4.close()
        L_0x0399:
            return r3
        L_0x039a:
            if (r3 == 0) goto L_0x039f
            r3.close()
        L_0x039f:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzam.zze(java.lang.String):com.google.android.gms.measurement.internal.zzh");
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0089  */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzao zzf(java.lang.String r10) {
        /*
            r9 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r10)
            r9.zzt()
            r9.zzal()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r9.e_()     // Catch:{ SQLiteException -> 0x006e, all -> 0x006c }
            java.lang.String r2 = "apps"
            java.lang.String r3 = "remote_config"
            java.lang.String r4 = "config_last_modified_time"
            java.lang.String r5 = "e_tag"
            java.lang.String[] r3 = new java.lang.String[]{r3, r4, r5}     // Catch:{ SQLiteException -> 0x006e, all -> 0x006c }
            java.lang.String r4 = "app_id=?"
            java.lang.String[] r5 = new java.lang.String[]{r10}     // Catch:{ SQLiteException -> 0x006e, all -> 0x006c }
            r7 = 0
            r8 = 0
            r6 = 0
            android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x006e, all -> 0x006c }
            boolean r2 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x005b }
            if (r2 != 0) goto L_0x0031
            r1.close()
            return r0
        L_0x0031:
            r2 = 0
            byte[] r2 = r1.getBlob(r2)     // Catch:{ SQLiteException -> 0x005b }
            r3 = 1
            java.lang.String r3 = r1.getString(r3)     // Catch:{ SQLiteException -> 0x005b }
            r4 = 2
            java.lang.String r4 = r1.getString(r4)     // Catch:{ SQLiteException -> 0x005b }
            boolean r5 = r1.moveToNext()     // Catch:{ SQLiteException -> 0x005b }
            if (r5 == 0) goto L_0x005d
            com.google.android.gms.measurement.internal.zzgi r5 = r9.zzj()     // Catch:{ SQLiteException -> 0x005b }
            com.google.android.gms.measurement.internal.zzgk r5 = r5.zzg()     // Catch:{ SQLiteException -> 0x005b }
            java.lang.String r6 = "Got multiple records for app config, expected one. appId"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r10)     // Catch:{ SQLiteException -> 0x005b }
            r5.zza(r6, r7)     // Catch:{ SQLiteException -> 0x005b }
            goto L_0x005d
        L_0x0058:
            r9 = move-exception
            r0 = r1
            goto L_0x0087
        L_0x005b:
            r2 = move-exception
            goto L_0x0070
        L_0x005d:
            if (r2 != 0) goto L_0x0063
            r1.close()
            return r0
        L_0x0063:
            com.google.android.gms.measurement.internal.zzao r5 = new com.google.android.gms.measurement.internal.zzao     // Catch:{ SQLiteException -> 0x005b }
            r5.<init>(r2, r3, r4)     // Catch:{ SQLiteException -> 0x005b }
            r1.close()
            return r5
        L_0x006c:
            r9 = move-exception
            goto L_0x0087
        L_0x006e:
            r2 = move-exception
            r1 = r0
        L_0x0070:
            com.google.android.gms.measurement.internal.zzgi r9 = r9.zzj()     // Catch:{ all -> 0x0058 }
            com.google.android.gms.measurement.internal.zzgk r9 = r9.zzg()     // Catch:{ all -> 0x0058 }
            java.lang.String r3 = "Error querying remote config. appId"
            java.lang.Object r10 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r10)     // Catch:{ all -> 0x0058 }
            r9.zza(r3, r10, r2)     // Catch:{ all -> 0x0058 }
            if (r1 == 0) goto L_0x0086
            r1.close()
        L_0x0086:
            return r0
        L_0x0087:
            if (r0 == 0) goto L_0x008c
            r0.close()
        L_0x008c:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzam.zzf(java.lang.String):com.google.android.gms.measurement.internal.zzao");
    }

    public final zzaz zzg(String str) {
        Preconditions.checkNotNull(str);
        zzt();
        zzal();
        return zzaz.zza(zza("select dma_consent_settings from consent_settings where app_id=? limit 1;", new String[]{str}, ""));
    }

    public final zzjc zzh(String str) {
        Preconditions.checkNotNull(str);
        zzt();
        zzal();
        return zzjc.zzb(zza("select storage_consent_at_bundling from consent_settings where app_id=? limit 1;", new String[]{str}, ""));
    }

    public final zzjc zzi(String str) {
        Preconditions.checkNotNull(str);
        zzt();
        zzal();
        zzjc zzjc = (zzjc) zza("select consent_state, consent_source from consent_settings where app_id=? limit 1;", new String[]{str}, new zzap());
        return zzjc == null ? zzjc.zza : zzjc;
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x00e6 A[Catch:{ IOException -> 0x0097 }] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x016b  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0171  */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzog zzj(java.lang.String r15) {
        /*
            r14 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r15)
            r14.zzt()
            r14.zzal()
            boolean r0 = com.google.android.gms.internal.measurement.zzrw.zza()
            r1 = 0
            if (r0 == 0) goto L_0x001d
            com.google.android.gms.measurement.internal.zzah r0 = r14.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzbj.zzbz
            boolean r0 = r0.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r2)
            if (r0 != 0) goto L_0x001d
            return r1
        L_0x001d:
            android.database.sqlite.SQLiteDatabase r2 = r14.e_()     // Catch:{ SQLiteException -> 0x015a, all -> 0x0158 }
            java.lang.String r3 = "upload_queue"
            java.lang.String r4 = "rowId"
            java.lang.String r5 = "app_id"
            java.lang.String r6 = "measurement_batch"
            java.lang.String r7 = "upload_uri"
            java.lang.String r8 = "upload_headers"
            java.lang.String r9 = "upload_type"
            java.lang.String r10 = "retry_count"
            java.lang.String[] r4 = new java.lang.String[]{r4, r5, r6, r7, r8, r9, r10}     // Catch:{ SQLiteException -> 0x015a, all -> 0x0158 }
            java.lang.String r5 = r14.zzao()     // Catch:{ SQLiteException -> 0x015a, all -> 0x0158 }
            java.lang.String[] r6 = new java.lang.String[]{r15}     // Catch:{ SQLiteException -> 0x015a, all -> 0x0158 }
            java.lang.String r9 = "creation_timestamp ASC"
            java.lang.String r10 = "1"
            r7 = 0
            r8 = 0
            android.database.Cursor r0 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x015a, all -> 0x0158 }
            boolean r2 = r0.moveToFirst()     // Catch:{ SQLiteException -> 0x0071 }
            if (r2 != 0) goto L_0x0051
            r0.close()
            return r1
        L_0x0051:
            r2 = 3
            java.lang.String r2 = r0.getString(r2)     // Catch:{ SQLiteException -> 0x0071 }
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ SQLiteException -> 0x0071 }
            if (r3 == 0) goto L_0x0074
            com.google.android.gms.measurement.internal.zzgi r2 = r14.zzj()     // Catch:{ SQLiteException -> 0x0071 }
            com.google.android.gms.measurement.internal.zzgk r2 = r2.zzc()     // Catch:{ SQLiteException -> 0x0071 }
            java.lang.String r3 = "Upload uri is null or empty. Destination is unknown. Dropping batch. "
            r2.zza(r3)     // Catch:{ SQLiteException -> 0x0071 }
            r0.close()
            return r1
        L_0x006d:
            r14 = move-exception
            r1 = r0
            goto L_0x016f
        L_0x0071:
            r2 = move-exception
            goto L_0x015c
        L_0x0074:
            com.google.android.gms.internal.measurement.zzgn$zzj$zza r3 = com.google.android.gms.internal.measurement.zzgn.zzj.zzb()     // Catch:{ IOException -> 0x0097 }
            r4 = 2
            byte[] r5 = r0.getBlob(r4)     // Catch:{ IOException -> 0x0097 }
            com.google.android.gms.internal.measurement.zzmk r3 = com.google.android.gms.measurement.internal.zzol.zza(r3, (byte[]) r5)     // Catch:{ IOException -> 0x0097 }
            com.google.android.gms.internal.measurement.zzgn$zzj$zza r3 = (com.google.android.gms.internal.measurement.zzgn.zzj.zza) r3     // Catch:{ IOException -> 0x0097 }
            com.google.android.gms.measurement.internal.zznt[] r5 = com.google.android.gms.measurement.internal.zznt.values()     // Catch:{ IOException -> 0x0097 }
            r6 = 5
            int r6 = r0.getInt(r6)     // Catch:{ IOException -> 0x0097 }
            r5 = r5[r6]     // Catch:{ IOException -> 0x0097 }
            com.google.android.gms.measurement.internal.zznt r6 = com.google.android.gms.measurement.internal.zznt.SGTM     // Catch:{ IOException -> 0x0097 }
            if (r5 == r6) goto L_0x009a
            com.google.android.gms.measurement.internal.zznt r6 = com.google.android.gms.measurement.internal.zznt.GOOGLE_ANALYTICS     // Catch:{ IOException -> 0x0097 }
            if (r5 != r6) goto L_0x00d9
            goto L_0x009a
        L_0x0097:
            r2 = move-exception
            goto L_0x0147
        L_0x009a:
            r6 = 6
            int r7 = r0.getInt(r6)     // Catch:{ IOException -> 0x0097 }
            if (r7 <= 0) goto L_0x00d9
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ IOException -> 0x0097 }
            r7.<init>()     // Catch:{ IOException -> 0x0097 }
            java.util.List r8 = r3.zzd()     // Catch:{ IOException -> 0x0097 }
            java.util.Iterator r8 = r8.iterator()     // Catch:{ IOException -> 0x0097 }
        L_0x00ae:
            boolean r9 = r8.hasNext()     // Catch:{ IOException -> 0x0097 }
            if (r9 == 0) goto L_0x00d3
            java.lang.Object r9 = r8.next()     // Catch:{ IOException -> 0x0097 }
            com.google.android.gms.internal.measurement.zzgn$zzk r9 = (com.google.android.gms.internal.measurement.zzgn.zzk) r9     // Catch:{ IOException -> 0x0097 }
            com.google.android.gms.internal.measurement.zzlc$zzb r9 = r9.zzcd()     // Catch:{ IOException -> 0x0097 }
            com.google.android.gms.internal.measurement.zzgn$zzk$zza r9 = (com.google.android.gms.internal.measurement.zzgn.zzk.zza) r9     // Catch:{ IOException -> 0x0097 }
            int r10 = r0.getInt(r6)     // Catch:{ IOException -> 0x0097 }
            r9.zzi((int) r10)     // Catch:{ IOException -> 0x0097 }
            com.google.android.gms.internal.measurement.zzml r9 = r9.zzai()     // Catch:{ IOException -> 0x0097 }
            com.google.android.gms.internal.measurement.zzlc r9 = (com.google.android.gms.internal.measurement.zzlc) r9     // Catch:{ IOException -> 0x0097 }
            com.google.android.gms.internal.measurement.zzgn$zzk r9 = (com.google.android.gms.internal.measurement.zzgn.zzk) r9     // Catch:{ IOException -> 0x0097 }
            r7.add(r9)     // Catch:{ IOException -> 0x0097 }
            goto L_0x00ae
        L_0x00d3:
            r3.zzb()     // Catch:{ IOException -> 0x0097 }
            r3.zza((java.lang.Iterable<? extends com.google.android.gms.internal.measurement.zzgn.zzk>) r7)     // Catch:{ IOException -> 0x0097 }
        L_0x00d9:
            java.util.HashMap r6 = new java.util.HashMap     // Catch:{ IOException -> 0x0097 }
            r6.<init>()     // Catch:{ IOException -> 0x0097 }
            r7 = 4
            java.lang.String r7 = r0.getString(r7)     // Catch:{ IOException -> 0x0097 }
            r8 = 0
            if (r7 == 0) goto L_0x011a
            java.lang.String r9 = "\r\n"
            java.lang.String[] r7 = r7.split(r9)     // Catch:{ IOException -> 0x0097 }
            int r9 = r7.length     // Catch:{ IOException -> 0x0097 }
            r10 = r8
        L_0x00ee:
            if (r10 >= r9) goto L_0x011a
            r11 = r7[r10]     // Catch:{ IOException -> 0x0097 }
            boolean r12 = r11.isEmpty()     // Catch:{ IOException -> 0x0097 }
            if (r12 != 0) goto L_0x011a
            java.lang.String r12 = "="
            java.lang.String[] r12 = r11.split(r12, r4)     // Catch:{ IOException -> 0x0097 }
            int r13 = r12.length     // Catch:{ IOException -> 0x0097 }
            if (r13 == r4) goto L_0x010f
            com.google.android.gms.measurement.internal.zzgi r4 = r14.zzj()     // Catch:{ IOException -> 0x0097 }
            com.google.android.gms.measurement.internal.zzgk r4 = r4.zzg()     // Catch:{ IOException -> 0x0097 }
            java.lang.String r7 = "Invalid upload header: "
            r4.zza(r7, r11)     // Catch:{ IOException -> 0x0097 }
            goto L_0x011a
        L_0x010f:
            r11 = r12[r8]     // Catch:{ IOException -> 0x0097 }
            r13 = 1
            r12 = r12[r13]     // Catch:{ IOException -> 0x0097 }
            r6.put(r11, r12)     // Catch:{ IOException -> 0x0097 }
            int r10 = r10 + 1
            goto L_0x00ee
        L_0x011a:
            com.google.android.gms.measurement.internal.zzoj r4 = new com.google.android.gms.measurement.internal.zzoj     // Catch:{ IOException -> 0x0097 }
            r4.<init>()     // Catch:{ IOException -> 0x0097 }
            long r7 = r0.getLong(r8)     // Catch:{ IOException -> 0x0097 }
            com.google.android.gms.measurement.internal.zzoj r4 = r4.zza((long) r7)     // Catch:{ IOException -> 0x0097 }
            com.google.android.gms.internal.measurement.zzml r3 = r3.zzai()     // Catch:{ IOException -> 0x0097 }
            com.google.android.gms.internal.measurement.zzlc r3 = (com.google.android.gms.internal.measurement.zzlc) r3     // Catch:{ IOException -> 0x0097 }
            com.google.android.gms.internal.measurement.zzgn$zzj r3 = (com.google.android.gms.internal.measurement.zzgn.zzj) r3     // Catch:{ IOException -> 0x0097 }
            com.google.android.gms.measurement.internal.zzoj r3 = r4.zza((com.google.android.gms.internal.measurement.zzgn.zzj) r3)     // Catch:{ IOException -> 0x0097 }
            com.google.android.gms.measurement.internal.zzoj r2 = r3.zza((java.lang.String) r2)     // Catch:{ IOException -> 0x0097 }
            com.google.android.gms.measurement.internal.zzoj r2 = r2.zza((java.util.Map<java.lang.String, java.lang.String>) r6)     // Catch:{ IOException -> 0x0097 }
            com.google.android.gms.measurement.internal.zzoj r2 = r2.zza((com.google.android.gms.measurement.internal.zznt) r5)     // Catch:{ IOException -> 0x0097 }
            com.google.android.gms.measurement.internal.zzog r14 = r2.zza()     // Catch:{ IOException -> 0x0097 }
            r0.close()
            return r14
        L_0x0147:
            com.google.android.gms.measurement.internal.zzgi r3 = r14.zzj()     // Catch:{ SQLiteException -> 0x0071 }
            com.google.android.gms.measurement.internal.zzgk r3 = r3.zzg()     // Catch:{ SQLiteException -> 0x0071 }
            java.lang.String r4 = "Failed to queued MeasurementBatch from upload_queue. appId"
            r3.zza(r4, r15, r2)     // Catch:{ SQLiteException -> 0x0071 }
            r0.close()
            return r1
        L_0x0158:
            r14 = move-exception
            goto L_0x016f
        L_0x015a:
            r2 = move-exception
            r0 = r1
        L_0x015c:
            com.google.android.gms.measurement.internal.zzgi r14 = r14.zzj()     // Catch:{ all -> 0x006d }
            com.google.android.gms.measurement.internal.zzgk r14 = r14.zzg()     // Catch:{ all -> 0x006d }
            java.lang.String r3 = "Error to querying MeasurementBatch from upload_queue. appId"
            r14.zza(r3, r15, r2)     // Catch:{ all -> 0x006d }
            if (r0 == 0) goto L_0x016e
            r0.close()
        L_0x016e:
            return r1
        L_0x016f:
            if (r1 == 0) goto L_0x0174
            r1.close()
        L_0x0174:
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzam.zzj(java.lang.String):com.google.android.gms.measurement.internal.zzog");
    }

    public final List<zznk> zzk(String str) {
        Preconditions.checkNotEmpty(str);
        zzt();
        zzal();
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            cursor = e_().query("trigger_uris", new String[]{"trigger_uri", "timestamp_millis", "source"}, "app_id=?", new String[]{str}, (String) null, (String) null, "rowid", (String) null);
            if (!cursor.moveToFirst()) {
                cursor.close();
                return arrayList;
            }
            do {
                String string = cursor.getString(0);
                if (string == null) {
                    string = "";
                }
                arrayList.add(new zznk(string, cursor.getLong(1), cursor.getInt(2)));
            } while (cursor.moveToNext());
            cursor.close();
            return arrayList;
        } catch (SQLiteException e3) {
            zzj().zzg().zza("Error querying trigger uris. appId", zzgi.zza(str), e3);
            List<zznk> emptyList = Collections.emptyList();
            if (cursor != null) {
                cursor.close();
            }
            return emptyList;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    @WorkerThread
    public final List<zzom> zzl(String str) {
        Preconditions.checkNotEmpty(str);
        zzt();
        zzal();
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            cursor = e_().query("user_attributes", new String[]{"name", "origin", "set_timestamp", "value"}, "app_id=?", new String[]{str}, (String) null, (String) null, "rowid", "1000");
            if (!cursor.moveToFirst()) {
                cursor.close();
                return arrayList;
            }
            do {
                String string = cursor.getString(0);
                String string2 = cursor.getString(1);
                if (string2 == null) {
                    string2 = "";
                }
                String str2 = string2;
                long j2 = cursor.getLong(2);
                Object zza2 = zza(cursor, 3);
                if (zza2 == null) {
                    zzj().zzg().zza("Read invalid user property value, ignoring it. appId", zzgi.zza(str));
                } else {
                    arrayList.add(new zzom(str, str2, string, j2, zza2));
                }
            } while (cursor.moveToNext());
            cursor.close();
            return arrayList;
        } catch (SQLiteException e3) {
            zzj().zzg().zza("Error querying user properties. appId", zzgi.zza(str), e3);
            List<zzom> emptyList = Collections.emptyList();
            if (cursor != null) {
                cursor.close();
            }
            return emptyList;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public final Map<Integer, zzgn.zzm> zzm(String str) {
        zzal();
        zzt();
        Preconditions.checkNotEmpty(str);
        Cursor cursor = null;
        try {
            cursor = e_().query("audience_filter_values", new String[]{"audience_id", "current_results"}, "app_id=?", new String[]{str}, (String) null, (String) null, (String) null);
            if (!cursor.moveToFirst()) {
                Map<Integer, zzgn.zzm> emptyMap = Collections.emptyMap();
                cursor.close();
                return emptyMap;
            }
            ArrayMap arrayMap = new ArrayMap();
            do {
                int i3 = cursor.getInt(0);
                try {
                    arrayMap.put(Integer.valueOf(i3), (zzgn.zzm) ((zzlc) ((zzgn.zzm.zza) zzol.zza(zzgn.zzm.zze(), cursor.getBlob(1))).zzai()));
                } catch (IOException e3) {
                    zzj().zzg().zza("Failed to merge filter results. appId, audienceId, error", zzgi.zza(str), Integer.valueOf(i3), e3);
                }
            } while (cursor.moveToNext());
            cursor.close();
            return arrayMap;
        } catch (SQLiteException e4) {
            zzj().zzg().zza("Database error querying filter results. appId", zzgi.zza(str), e4);
            Map<Integer, zzgn.zzm> emptyMap2 = Collections.emptyMap();
            if (cursor != null) {
                cursor.close();
            }
            return emptyMap2;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public final Map<Integer, List<zzfn.zzb>> zzn(String str) {
        Preconditions.checkNotEmpty(str);
        ArrayMap arrayMap = new ArrayMap();
        Cursor cursor = null;
        try {
            cursor = e_().query("event_filters", new String[]{"audience_id", "data"}, "app_id=?", new String[]{str}, (String) null, (String) null, (String) null);
            if (!cursor.moveToFirst()) {
                Map<Integer, List<zzfn.zzb>> emptyMap = Collections.emptyMap();
                cursor.close();
                return emptyMap;
            }
            do {
                try {
                    zzfn.zzb zzb2 = (zzfn.zzb) ((zzlc) ((zzfn.zzb.zza) zzol.zza(zzfn.zzb.zzc(), cursor.getBlob(1))).zzai());
                    if (zzb2.zzk()) {
                        int i3 = cursor.getInt(0);
                        List list = (List) arrayMap.get(Integer.valueOf(i3));
                        if (list == null) {
                            list = new ArrayList();
                            arrayMap.put(Integer.valueOf(i3), list);
                        }
                        list.add(zzb2);
                    }
                } catch (IOException e3) {
                    zzj().zzg().zza("Failed to merge filter. appId", zzgi.zza(str), e3);
                }
            } while (cursor.moveToNext());
            cursor.close();
            return arrayMap;
        } catch (SQLiteException e4) {
            zzj().zzg().zza("Database error querying filters. appId", zzgi.zza(str), e4);
            Map<Integer, List<zzfn.zzb>> emptyMap2 = Collections.emptyMap();
            if (cursor != null) {
                cursor.close();
            }
            return emptyMap2;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public final Map<Integer, List<Integer>> zzo(String str) {
        zzal();
        zzt();
        Preconditions.checkNotEmpty(str);
        ArrayMap arrayMap = new ArrayMap();
        Cursor cursor = null;
        try {
            cursor = e_().rawQuery("select audience_id, filter_id from event_filters where app_id = ? and session_scoped = 1 UNION select audience_id, filter_id from property_filters where app_id = ? and session_scoped = 1;", new String[]{str, str});
            if (!cursor.moveToFirst()) {
                Map<Integer, List<Integer>> emptyMap = Collections.emptyMap();
                cursor.close();
                return emptyMap;
            }
            do {
                int i3 = cursor.getInt(0);
                List list = (List) arrayMap.get(Integer.valueOf(i3));
                if (list == null) {
                    list = new ArrayList();
                    arrayMap.put(Integer.valueOf(i3), list);
                }
                list.add(Integer.valueOf(cursor.getInt(1)));
            } while (cursor.moveToNext());
            cursor.close();
            return arrayMap;
        } catch (SQLiteException e3) {
            zzj().zzg().zza("Database error querying scoped filters. appId", zzgi.zza(str), e3);
            Map<Integer, List<Integer>> emptyMap2 = Collections.emptyMap();
            if (cursor != null) {
                cursor.close();
            }
            return emptyMap2;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    @WorkerThread
    public final void zzp() {
        zzal();
        e_().beginTransaction();
    }

    public final void zzq(String str) {
        zzbd zzd2;
        zzi("events_snapshot", str);
        Cursor cursor = null;
        try {
            cursor = e_().query("events", (String[]) Collections.singletonList("name").toArray(new String[0]), "app_id=?", new String[]{str}, (String) null, (String) null, (String) null);
            if (!cursor.moveToFirst()) {
                cursor.close();
                return;
            }
            do {
                String string = cursor.getString(0);
                if (!(string == null || (zzd2 = zzd(str, string)) == null)) {
                    zza("events_snapshot", zzd2);
                }
            } while (cursor.moveToNext());
            cursor.close();
        } catch (SQLiteException e3) {
            zzj().zzg().zza("Error creating snapshot. appId", zzgi.zza(str), e3);
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x009f, code lost:
        if ("_v".equals(r0) != false) goto L_0x00a1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00e1  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00fa  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00ff A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzr(java.lang.String r20) {
        /*
            r19 = this;
            r1 = r19
            r2 = r20
            java.lang.String r3 = "events_snapshot"
            java.util.ArrayList r0 = new java.util.ArrayList
            java.lang.String r4 = "name"
            java.lang.String r5 = "lifetime_count"
            java.lang.String[] r4 = new java.lang.String[]{r4, r5}
            java.util.List r4 = java.util.Arrays.asList(r4)
            r0.<init>(r4)
            java.lang.String r4 = "_f"
            com.google.android.gms.measurement.internal.zzbd r5 = r1.zzd(r2, r4)
            java.lang.String r6 = "_v"
            com.google.android.gms.measurement.internal.zzbd r7 = r1.zzd(r2, r6)
            java.lang.String r8 = "events"
            r1.zzi(r8, r2)
            r9 = 0
            r10 = 0
            android.database.sqlite.SQLiteDatabase r11 = r19.e_()     // Catch:{ SQLiteException -> 0x00cc, all -> 0x00c9 }
            java.lang.String r12 = "events_snapshot"
            java.lang.String[] r13 = new java.lang.String[r9]     // Catch:{ SQLiteException -> 0x00cc, all -> 0x00c9 }
            java.lang.Object[] r0 = r0.toArray(r13)     // Catch:{ SQLiteException -> 0x00cc, all -> 0x00c9 }
            r13 = r0
            java.lang.String[] r13 = (java.lang.String[]) r13     // Catch:{ SQLiteException -> 0x00cc, all -> 0x00c9 }
            java.lang.String r14 = "app_id=?"
            java.lang.String[] r15 = new java.lang.String[]{r20}     // Catch:{ SQLiteException -> 0x00cc, all -> 0x00c9 }
            r17 = 0
            r18 = 0
            r16 = 0
            android.database.Cursor r10 = r11.query(r12, r13, r14, r15, r16, r17, r18)     // Catch:{ SQLiteException -> 0x00cc, all -> 0x00c9 }
            boolean r0 = r10.moveToFirst()     // Catch:{ SQLiteException -> 0x00cc, all -> 0x00c9 }
            if (r0 != 0) goto L_0x0061
            r10.close()
            if (r5 == 0) goto L_0x0058
            r1.zza((java.lang.String) r8, (com.google.android.gms.measurement.internal.zzbd) r5)
            goto L_0x005d
        L_0x0058:
            if (r7 == 0) goto L_0x005d
            r1.zza((java.lang.String) r8, (com.google.android.gms.measurement.internal.zzbd) r7)
        L_0x005d:
            r1.zzi(r3, r2)
            return
        L_0x0061:
            r11 = r9
            r12 = r11
        L_0x0063:
            java.lang.String r0 = r10.getString(r9)     // Catch:{ SQLiteException -> 0x0091, all -> 0x008d }
            com.google.android.gms.measurement.internal.zzah r13 = r19.zze()     // Catch:{ SQLiteException -> 0x0091, all -> 0x008d }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r14 = com.google.android.gms.measurement.internal.zzbj.zzde     // Catch:{ SQLiteException -> 0x0091, all -> 0x008d }
            boolean r13 = r13.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r14)     // Catch:{ SQLiteException -> 0x0091, all -> 0x008d }
            r14 = 1
            if (r13 == 0) goto L_0x0094
            long r15 = r10.getLong(r14)     // Catch:{ SQLiteException -> 0x0091, all -> 0x008d }
            r17 = 1
            int r13 = (r15 > r17 ? 1 : (r15 == r17 ? 0 : -1))
            if (r13 < 0) goto L_0x00a2
            boolean r13 = r4.equals(r0)     // Catch:{ SQLiteException -> 0x0091, all -> 0x008d }
            if (r13 == 0) goto L_0x0086
        L_0x0084:
            r11 = r14
            goto L_0x00a2
        L_0x0086:
            boolean r13 = r6.equals(r0)     // Catch:{ SQLiteException -> 0x0091, all -> 0x008d }
            if (r13 == 0) goto L_0x00a2
            goto L_0x00a1
        L_0x008d:
            r0 = move-exception
            r9 = r11
            goto L_0x00f8
        L_0x0091:
            r0 = move-exception
            r9 = r11
            goto L_0x00ce
        L_0x0094:
            boolean r13 = r4.equals(r0)     // Catch:{ SQLiteException -> 0x0091, all -> 0x008d }
            if (r13 == 0) goto L_0x009b
            goto L_0x0084
        L_0x009b:
            boolean r13 = r6.equals(r0)     // Catch:{ SQLiteException -> 0x0091, all -> 0x008d }
            if (r13 == 0) goto L_0x00a2
        L_0x00a1:
            r12 = r14
        L_0x00a2:
            if (r0 == 0) goto L_0x00ad
            com.google.android.gms.measurement.internal.zzbd r0 = r1.zzc(r3, r2, r0)     // Catch:{ SQLiteException -> 0x0091, all -> 0x008d }
            if (r0 == 0) goto L_0x00ad
            r1.zza((java.lang.String) r8, (com.google.android.gms.measurement.internal.zzbd) r0)     // Catch:{ SQLiteException -> 0x0091, all -> 0x008d }
        L_0x00ad:
            boolean r0 = r10.moveToNext()     // Catch:{ SQLiteException -> 0x0091, all -> 0x008d }
            if (r0 != 0) goto L_0x0063
            r10.close()
            if (r11 != 0) goto L_0x00be
            if (r5 == 0) goto L_0x00be
            r1.zza((java.lang.String) r8, (com.google.android.gms.measurement.internal.zzbd) r5)
            goto L_0x00c5
        L_0x00be:
            if (r12 != 0) goto L_0x00c5
            if (r7 == 0) goto L_0x00c5
            r1.zza((java.lang.String) r8, (com.google.android.gms.measurement.internal.zzbd) r7)
        L_0x00c5:
            r1.zzi(r3, r2)
            return
        L_0x00c9:
            r0 = move-exception
            r12 = r9
            goto L_0x00f8
        L_0x00cc:
            r0 = move-exception
            r12 = r9
        L_0x00ce:
            com.google.android.gms.measurement.internal.zzgi r4 = r19.zzj()     // Catch:{ all -> 0x00f7 }
            com.google.android.gms.measurement.internal.zzgk r4 = r4.zzg()     // Catch:{ all -> 0x00f7 }
            java.lang.String r6 = "Error querying snapshot. appId"
            java.lang.Object r11 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r20)     // Catch:{ all -> 0x00f7 }
            r4.zza(r6, r11, r0)     // Catch:{ all -> 0x00f7 }
            if (r10 == 0) goto L_0x00e4
            r10.close()
        L_0x00e4:
            if (r9 != 0) goto L_0x00ec
            if (r5 == 0) goto L_0x00ec
            r1.zza((java.lang.String) r8, (com.google.android.gms.measurement.internal.zzbd) r5)
            goto L_0x00f3
        L_0x00ec:
            if (r12 != 0) goto L_0x00f3
            if (r7 == 0) goto L_0x00f3
            r1.zza((java.lang.String) r8, (com.google.android.gms.measurement.internal.zzbd) r7)
        L_0x00f3:
            r1.zzi(r3, r2)
            return
        L_0x00f7:
            r0 = move-exception
        L_0x00f8:
            if (r10 == 0) goto L_0x00fd
            r10.close()
        L_0x00fd:
            if (r9 != 0) goto L_0x0106
            if (r5 != 0) goto L_0x0102
            goto L_0x0106
        L_0x0102:
            r1.zza((java.lang.String) r8, (com.google.android.gms.measurement.internal.zzbd) r5)
            goto L_0x010d
        L_0x0106:
            if (r12 != 0) goto L_0x010d
            if (r7 == 0) goto L_0x010d
            r1.zza((java.lang.String) r8, (com.google.android.gms.measurement.internal.zzbd) r7)
        L_0x010d:
            r1.zzi(r3, r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzam.zzr(java.lang.String):void");
    }

    @WorkerThread
    public final boolean zzs(String str) {
        return (!zzrw.zza() || zze().zza(zzbj.zzbz)) && zzb(c.a("SELECT COUNT(1) > 0 FROM upload_queue WHERE ", zzao()), new String[]{str}) != 0;
    }

    @WorkerThread
    public final void zzu() {
        zzal();
        e_().endTransaction();
    }

    @WorkerThread
    public final void zzv() {
        int delete;
        zzt();
        zzal();
        if (zzaa()) {
            long zza2 = zzn().zza.zza();
            long elapsedRealtime = zzb().elapsedRealtime();
            if (Math.abs(elapsedRealtime - zza2) > zzah.zzp()) {
                zzn().zza.zza(elapsedRealtime);
                zzt();
                zzal();
                if (zzaa() && (delete = e_().delete("queue", "abs(bundle_end_timestamp - ?) > cast(? as integer)", new String[]{String.valueOf(zzb().currentTimeMillis()), String.valueOf(zzah.zzo())})) > 0) {
                    zzj().zzp().zza("Deleted stale rows. rowsDeleted", Integer.valueOf(delete));
                }
            }
        }
    }

    @WorkerThread
    public final void zzw() {
        zzal();
        e_().setTransactionSuccessful();
    }

    public final boolean zzx() {
        return zzb("select count(1) > 0 from raw_events", (String[]) null) != 0;
    }

    public final boolean zzy() {
        return zzb("select count(1) > 0 from queue where has_realtime = 1", (String[]) null) != 0;
    }

    public final boolean zzz() {
        return zzb("select count(1) > 0 from raw_events where realtime = 1", (String[]) null) != 0;
    }

    @WorkerThread
    public final int zza(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzt();
        zzal();
        try {
            return e_().delete("conditional_properties", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e3) {
            zzj().zzg().zza("Error deleting conditional property", zzgi.zza(str), zzi().zzc(str2), e3);
            return 0;
        }
    }

    public final long zzc(String str) {
        Preconditions.checkNotEmpty(str);
        return zza("select count(1) from events where app_id=? and name not like '!_%' escape '!'", new String[]{str}, 0);
    }

    public final void zzp(String str) {
        zzt();
        zzal();
        try {
            e_().execSQL("delete from default_event_params where app_id=?", new String[]{str});
        } catch (SQLiteException e3) {
            zzj().zzg().zza("Error clearing default event params", e3);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0116  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x011c  */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzaf zzc(java.lang.String r27, java.lang.String r28) {
        /*
            r26 = this;
            r7 = r28
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r27)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r28)
            r26.zzt()
            r26.zzal()
            r8 = 0
            android.database.sqlite.SQLiteDatabase r9 = r26.e_()     // Catch:{ SQLiteException -> 0x00f9, all -> 0x00f7 }
            java.lang.String r10 = "conditional_properties"
            java.lang.String r11 = "origin"
            java.lang.String r12 = "value"
            java.lang.String r13 = "active"
            java.lang.String r14 = "trigger_event_name"
            java.lang.String r15 = "trigger_timeout"
            java.lang.String r16 = "timed_out_event"
            java.lang.String r17 = "creation_timestamp"
            java.lang.String r18 = "triggered_event"
            java.lang.String r19 = "triggered_timestamp"
            java.lang.String r20 = "time_to_live"
            java.lang.String r21 = "expired_event"
            java.lang.String[] r11 = new java.lang.String[]{r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21}     // Catch:{ SQLiteException -> 0x00f9, all -> 0x00f7 }
            java.lang.String r12 = "app_id=? and name=?"
            java.lang.String[] r13 = new java.lang.String[]{r27, r28}     // Catch:{ SQLiteException -> 0x00f9, all -> 0x00f7 }
            r15 = 0
            r16 = 0
            r14 = 0
            android.database.Cursor r9 = r9.query(r10, r11, r12, r13, r14, r15, r16)     // Catch:{ SQLiteException -> 0x00f9, all -> 0x00f7 }
            boolean r0 = r9.moveToFirst()     // Catch:{ SQLiteException -> 0x0056 }
            if (r0 != 0) goto L_0x0047
            r9.close()
            return r8
        L_0x0047:
            r0 = 0
            java.lang.String r1 = r9.getString(r0)     // Catch:{ SQLiteException -> 0x0056 }
            if (r1 != 0) goto L_0x0050
            java.lang.String r1 = ""
        L_0x0050:
            r12 = r1
            goto L_0x0059
        L_0x0052:
            r0 = move-exception
            r8 = r9
            goto L_0x011a
        L_0x0056:
            r0 = move-exception
            goto L_0x00fb
        L_0x0059:
            r1 = 1
            r14 = r26
            java.lang.Object r5 = r14.zza((android.database.Cursor) r9, (int) r1)     // Catch:{ SQLiteException -> 0x0056 }
            r2 = 2
            int r2 = r9.getInt(r2)     // Catch:{ SQLiteException -> 0x0056 }
            if (r2 == 0) goto L_0x006a
            r16 = r1
            goto L_0x006c
        L_0x006a:
            r16 = r0
        L_0x006c:
            r0 = 3
            java.lang.String r17 = r9.getString(r0)     // Catch:{ SQLiteException -> 0x0056 }
            r0 = 4
            long r19 = r9.getLong(r0)     // Catch:{ SQLiteException -> 0x0056 }
            com.google.android.gms.measurement.internal.zzol r0 = r26.g_()     // Catch:{ SQLiteException -> 0x0056 }
            r1 = 5
            byte[] r1 = r9.getBlob(r1)     // Catch:{ SQLiteException -> 0x0056 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzbh> r2 = com.google.android.gms.measurement.internal.zzbh.CREATOR     // Catch:{ SQLiteException -> 0x0056 }
            android.os.Parcelable r0 = r0.zza((byte[]) r1, r2)     // Catch:{ SQLiteException -> 0x0056 }
            r18 = r0
            com.google.android.gms.measurement.internal.zzbh r18 = (com.google.android.gms.measurement.internal.zzbh) r18     // Catch:{ SQLiteException -> 0x0056 }
            r0 = 6
            long r21 = r9.getLong(r0)     // Catch:{ SQLiteException -> 0x0056 }
            com.google.android.gms.measurement.internal.zzol r0 = r26.g_()     // Catch:{ SQLiteException -> 0x0056 }
            r1 = 7
            byte[] r1 = r9.getBlob(r1)     // Catch:{ SQLiteException -> 0x0056 }
            android.os.Parcelable r0 = r0.zza((byte[]) r1, r2)     // Catch:{ SQLiteException -> 0x0056 }
            com.google.android.gms.measurement.internal.zzbh r0 = (com.google.android.gms.measurement.internal.zzbh) r0     // Catch:{ SQLiteException -> 0x0056 }
            r1 = 8
            long r3 = r9.getLong(r1)     // Catch:{ SQLiteException -> 0x0056 }
            r1 = 9
            long r23 = r9.getLong(r1)     // Catch:{ SQLiteException -> 0x0056 }
            com.google.android.gms.measurement.internal.zzol r1 = r26.g_()     // Catch:{ SQLiteException -> 0x0056 }
            r6 = 10
            byte[] r6 = r9.getBlob(r6)     // Catch:{ SQLiteException -> 0x0056 }
            android.os.Parcelable r1 = r1.zza((byte[]) r6, r2)     // Catch:{ SQLiteException -> 0x0056 }
            r25 = r1
            com.google.android.gms.measurement.internal.zzbh r25 = (com.google.android.gms.measurement.internal.zzbh) r25     // Catch:{ SQLiteException -> 0x0056 }
            com.google.android.gms.measurement.internal.zzok r13 = new com.google.android.gms.measurement.internal.zzok     // Catch:{ SQLiteException -> 0x0056 }
            r1 = r13
            r2 = r28
            r6 = r12
            r1.<init>(r2, r3, r5, r6)     // Catch:{ SQLiteException -> 0x0056 }
            com.google.android.gms.measurement.internal.zzaf r1 = new com.google.android.gms.measurement.internal.zzaf     // Catch:{ SQLiteException -> 0x0056 }
            r10 = r1
            r11 = r27
            r14 = r21
            r21 = r0
            r22 = r23
            r24 = r25
            r10.<init>(r11, r12, r13, r14, r16, r17, r18, r19, r21, r22, r24)     // Catch:{ SQLiteException -> 0x0056 }
            boolean r0 = r9.moveToNext()     // Catch:{ SQLiteException -> 0x0056 }
            if (r0 == 0) goto L_0x00f3
            com.google.android.gms.measurement.internal.zzgi r0 = r26.zzj()     // Catch:{ SQLiteException -> 0x0056 }
            com.google.android.gms.measurement.internal.zzgk r0 = r0.zzg()     // Catch:{ SQLiteException -> 0x0056 }
            java.lang.String r2 = "Got multiple records for conditional property, expected one"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r27)     // Catch:{ SQLiteException -> 0x0056 }
            com.google.android.gms.measurement.internal.zzgh r4 = r26.zzi()     // Catch:{ SQLiteException -> 0x0056 }
            java.lang.String r4 = r4.zzc(r7)     // Catch:{ SQLiteException -> 0x0056 }
            r0.zza(r2, r3, r4)     // Catch:{ SQLiteException -> 0x0056 }
        L_0x00f3:
            r9.close()
            return r1
        L_0x00f7:
            r0 = move-exception
            goto L_0x011a
        L_0x00f9:
            r0 = move-exception
            r9 = r8
        L_0x00fb:
            com.google.android.gms.measurement.internal.zzgi r1 = r26.zzj()     // Catch:{ all -> 0x0052 }
            com.google.android.gms.measurement.internal.zzgk r1 = r1.zzg()     // Catch:{ all -> 0x0052 }
            java.lang.String r2 = "Error querying conditional property"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r27)     // Catch:{ all -> 0x0052 }
            com.google.android.gms.measurement.internal.zzgh r4 = r26.zzi()     // Catch:{ all -> 0x0052 }
            java.lang.String r4 = r4.zzc(r7)     // Catch:{ all -> 0x0052 }
            r1.zza(r2, r3, r4, r0)     // Catch:{ all -> 0x0052 }
            if (r9 == 0) goto L_0x0119
            r9.close()
        L_0x0119:
            return r8
        L_0x011a:
            if (r8 == 0) goto L_0x011f
            r8.close()
        L_0x011f:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzam.zzc(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzaf");
    }

    private final void zzi(String str, String str2) {
        Preconditions.checkNotEmpty(str2);
        zzt();
        zzal();
        try {
            e_().delete(str, "app_id=?", new String[]{str2});
        } catch (SQLiteException e3) {
            zzj().zzg().zza("Error deleting snapshot. appId", zzgi.zza(str2), e3);
        }
    }

    public final Map<Integer, List<zzfn.zze>> zzg(String str, String str2) {
        zzal();
        zzt();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        ArrayMap arrayMap = new ArrayMap();
        Cursor cursor = null;
        try {
            cursor = e_().query("property_filters", new String[]{"audience_id", "data"}, "app_id=? AND property_name=?", new String[]{str, str2}, (String) null, (String) null, (String) null);
            if (!cursor.moveToFirst()) {
                Map<Integer, List<zzfn.zze>> emptyMap = Collections.emptyMap();
                cursor.close();
                return emptyMap;
            }
            do {
                try {
                    zzfn.zze zze2 = (zzfn.zze) ((zzlc) ((zzfn.zze.zza) zzol.zza(zzfn.zze.zzc(), cursor.getBlob(1))).zzai());
                    int i3 = cursor.getInt(0);
                    List list = (List) arrayMap.get(Integer.valueOf(i3));
                    if (list == null) {
                        list = new ArrayList();
                        arrayMap.put(Integer.valueOf(i3), list);
                    }
                    list.add(zze2);
                } catch (IOException e3) {
                    zzj().zzg().zza("Failed to merge filter", zzgi.zza(str), e3);
                }
            } while (cursor.moveToNext());
            cursor.close();
            return arrayMap;
        } catch (SQLiteException e4) {
            zzj().zzg().zza("Database error querying filters. appId", zzgi.zza(str), e4);
            Map<Integer, List<zzfn.zze>> emptyMap2 = Collections.emptyMap();
            if (cursor != null) {
                cursor.close();
            }
            return emptyMap2;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    @WorkerThread
    public final void zzh(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzt();
        zzal();
        try {
            e_().delete("user_attributes", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e3) {
            zzj().zzg().zza("Error deleting user property. appId", zzgi.zza(str), zzi().zzc(str2), e3);
        }
    }

    public final long zza(String str) {
        Preconditions.checkNotEmpty(str);
        zzt();
        zzal();
        try {
            return (long) e_().delete("raw_events", "rowid in (select rowid from raw_events where app_id=? order by rowid desc limit -1 offset ?)", new String[]{str, String.valueOf(Math.max(0, Math.min(1000000, zze().zzb(str, zzbj.zzp))))});
        } catch (SQLiteException e3) {
            zzj().zzg().zza("Error deleting over the limit events. appId", zzgi.zza(str), e3);
            return 0;
        }
    }

    @WorkerThread
    public final zzbd zzd(String str, String str2) {
        return zzc("events", str, str2);
    }

    public final Map<Integer, List<zzfn.zzb>> zzf(String str, String str2) {
        zzal();
        zzt();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        ArrayMap arrayMap = new ArrayMap();
        Cursor cursor = null;
        try {
            cursor = e_().query("event_filters", new String[]{"audience_id", "data"}, "app_id=? AND event_name=?", new String[]{str, str2}, (String) null, (String) null, (String) null);
            if (!cursor.moveToFirst()) {
                Map<Integer, List<zzfn.zzb>> emptyMap = Collections.emptyMap();
                cursor.close();
                return emptyMap;
            }
            do {
                try {
                    zzfn.zzb zzb2 = (zzfn.zzb) ((zzlc) ((zzfn.zzb.zza) zzol.zza(zzfn.zzb.zzc(), cursor.getBlob(1))).zzai());
                    int i3 = cursor.getInt(0);
                    List list = (List) arrayMap.get(Integer.valueOf(i3));
                    if (list == null) {
                        list = new ArrayList();
                        arrayMap.put(Integer.valueOf(i3), list);
                    }
                    list.add(zzb2);
                } catch (IOException e3) {
                    zzj().zzg().zza("Failed to merge filter. appId", zzgi.zza(str), e3);
                }
            } while (cursor.moveToNext());
            cursor.close();
            return arrayMap;
        } catch (SQLiteException e4) {
            zzj().zzg().zza("Database error querying filters. appId", zzgi.zza(str), e4);
            Map<Integer, List<zzfn.zzb>> emptyMap2 = Collections.emptyMap();
            if (cursor != null) {
                cursor.close();
            }
            return emptyMap2;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public final long zza(zzgn.zzk zzk2) throws IOException {
        zzt();
        zzal();
        Preconditions.checkNotNull(zzk2);
        Preconditions.checkNotEmpty(zzk2.zzz());
        byte[] zzca = zzk2.zzca();
        long zza2 = g_().zza(zzca);
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzk2.zzz());
        contentValues.put("metadata_fingerprint", Long.valueOf(zza2));
        contentValues.put(TtmlNode.TAG_METADATA, zzca);
        try {
            e_().insertWithOnConflict("raw_events_metadata", (String) null, contentValues, 4);
            return zza2;
        } catch (SQLiteException e3) {
            zzj().zzg().zza("Error storing raw event metadata. appId", zzgi.zza(zzk2.zzz()), e3);
            throw e3;
        }
    }

    @WorkerThread
    public final long zzb(String str) {
        Preconditions.checkNotEmpty(str);
        zzt();
        zzal();
        return zza("select first_open_count from app2 where app_id=?", new String[]{str}, -1);
    }

    @WorkerThread
    private final long zzb(String str, String[] strArr) {
        Cursor cursor = null;
        try {
            cursor = e_().rawQuery(str, strArr);
            if (cursor.moveToFirst()) {
                long j2 = cursor.getLong(0);
                cursor.close();
                return j2;
            }
            throw new SQLiteException("Database returned empty set");
        } catch (SQLiteException e3) {
            zzj().zzg().zza("Database error", str, e3);
            throw e3;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:51:0x0126  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x012c  */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.measurement.internal.zzbd zzc(java.lang.String r29, java.lang.String r30, java.lang.String r31) {
        /*
            r28 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r30)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r31)
            r28.zzt()
            r28.zzal()
            java.util.ArrayList r0 = new java.util.ArrayList
            java.lang.String r8 = "last_exempt_from_sampling"
            java.lang.String r9 = "current_session_count"
            java.lang.String r1 = "lifetime_count"
            java.lang.String r2 = "current_bundle_count"
            java.lang.String r3 = "last_fire_timestamp"
            java.lang.String r4 = "last_bundled_timestamp"
            java.lang.String r5 = "last_bundled_day"
            java.lang.String r6 = "last_sampled_complex_event_id"
            java.lang.String r7 = "last_sampling_rate"
            java.lang.String[] r1 = new java.lang.String[]{r1, r2, r3, r4, r5, r6, r7, r8, r9}
            java.util.List r1 = java.util.Arrays.asList(r1)
            r0.<init>(r1)
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r28.e_()     // Catch:{ SQLiteException -> 0x0107, all -> 0x0105 }
            r10 = 0
            java.lang.String[] r3 = new java.lang.String[r10]     // Catch:{ SQLiteException -> 0x0107, all -> 0x0105 }
            java.lang.Object[] r0 = r0.toArray(r3)     // Catch:{ SQLiteException -> 0x0107, all -> 0x0105 }
            r4 = r0
            java.lang.String[] r4 = (java.lang.String[]) r4     // Catch:{ SQLiteException -> 0x0107, all -> 0x0105 }
            java.lang.String r5 = "app_id=? and name=?"
            java.lang.String[] r6 = new java.lang.String[]{r30, r31}     // Catch:{ SQLiteException -> 0x0107, all -> 0x0105 }
            r8 = 0
            r9 = 0
            r7 = 0
            r3 = r29
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ SQLiteException -> 0x0107, all -> 0x0105 }
            boolean r0 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x00cb }
            if (r0 != 0) goto L_0x0053
            r2.close()
            return r1
        L_0x0053:
            long r14 = r2.getLong(r10)     // Catch:{ SQLiteException -> 0x00cb }
            r0 = 1
            long r16 = r2.getLong(r0)     // Catch:{ SQLiteException -> 0x00cb }
            r3 = 2
            long r20 = r2.getLong(r3)     // Catch:{ SQLiteException -> 0x00cb }
            r3 = 3
            boolean r4 = r2.isNull(r3)     // Catch:{ SQLiteException -> 0x00cb }
            r5 = 0
            if (r4 == 0) goto L_0x006d
            r22 = r5
            goto L_0x0073
        L_0x006d:
            long r3 = r2.getLong(r3)     // Catch:{ SQLiteException -> 0x00cb }
            r22 = r3
        L_0x0073:
            r3 = 4
            boolean r4 = r2.isNull(r3)     // Catch:{ SQLiteException -> 0x00cb }
            if (r4 == 0) goto L_0x007d
            r24 = r1
            goto L_0x0087
        L_0x007d:
            long r3 = r2.getLong(r3)     // Catch:{ SQLiteException -> 0x00cb }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ SQLiteException -> 0x00cb }
            r24 = r3
        L_0x0087:
            r3 = 5
            boolean r4 = r2.isNull(r3)     // Catch:{ SQLiteException -> 0x00cb }
            if (r4 == 0) goto L_0x0091
            r25 = r1
            goto L_0x009b
        L_0x0091:
            long r3 = r2.getLong(r3)     // Catch:{ SQLiteException -> 0x00cb }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ SQLiteException -> 0x00cb }
            r25 = r3
        L_0x009b:
            r3 = 6
            boolean r4 = r2.isNull(r3)     // Catch:{ SQLiteException -> 0x00cb }
            if (r4 == 0) goto L_0x00a5
            r26 = r1
            goto L_0x00af
        L_0x00a5:
            long r3 = r2.getLong(r3)     // Catch:{ SQLiteException -> 0x00cb }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ SQLiteException -> 0x00cb }
            r26 = r3
        L_0x00af:
            r3 = 7
            boolean r4 = r2.isNull(r3)     // Catch:{ SQLiteException -> 0x00cb }
            if (r4 != 0) goto L_0x00cd
            long r3 = r2.getLong(r3)     // Catch:{ SQLiteException -> 0x00cb }
            r7 = 1
            int r3 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r3 != 0) goto L_0x00c1
            r10 = r0
        L_0x00c1:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r10)     // Catch:{ SQLiteException -> 0x00cb }
            r27 = r0
            goto L_0x00cf
        L_0x00c8:
            r0 = move-exception
            r1 = r2
            goto L_0x012a
        L_0x00cb:
            r0 = move-exception
            goto L_0x0109
        L_0x00cd:
            r27 = r1
        L_0x00cf:
            r0 = 8
            boolean r3 = r2.isNull(r0)     // Catch:{ SQLiteException -> 0x00cb }
            if (r3 == 0) goto L_0x00da
            r18 = r5
            goto L_0x00e0
        L_0x00da:
            long r3 = r2.getLong(r0)     // Catch:{ SQLiteException -> 0x00cb }
            r18 = r3
        L_0x00e0:
            com.google.android.gms.measurement.internal.zzbd r0 = new com.google.android.gms.measurement.internal.zzbd     // Catch:{ SQLiteException -> 0x00cb }
            r11 = r0
            r12 = r30
            r13 = r31
            r11.<init>(r12, r13, r14, r16, r18, r20, r22, r24, r25, r26, r27)     // Catch:{ SQLiteException -> 0x00cb }
            boolean r3 = r2.moveToNext()     // Catch:{ SQLiteException -> 0x00cb }
            if (r3 == 0) goto L_0x0101
            com.google.android.gms.measurement.internal.zzgi r3 = r28.zzj()     // Catch:{ SQLiteException -> 0x00cb }
            com.google.android.gms.measurement.internal.zzgk r3 = r3.zzg()     // Catch:{ SQLiteException -> 0x00cb }
            java.lang.String r4 = "Got multiple records for event aggregates, expected one. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r30)     // Catch:{ SQLiteException -> 0x00cb }
            r3.zza(r4, r5)     // Catch:{ SQLiteException -> 0x00cb }
        L_0x0101:
            r2.close()
            return r0
        L_0x0105:
            r0 = move-exception
            goto L_0x012a
        L_0x0107:
            r0 = move-exception
            r2 = r1
        L_0x0109:
            com.google.android.gms.measurement.internal.zzgi r3 = r28.zzj()     // Catch:{ all -> 0x00c8 }
            com.google.android.gms.measurement.internal.zzgk r3 = r3.zzg()     // Catch:{ all -> 0x00c8 }
            java.lang.String r4 = "Error querying events. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r30)     // Catch:{ all -> 0x00c8 }
            com.google.android.gms.measurement.internal.zzgh r6 = r28.zzi()     // Catch:{ all -> 0x00c8 }
            r7 = r31
            java.lang.String r6 = r6.zza((java.lang.String) r7)     // Catch:{ all -> 0x00c8 }
            r3.zza(r4, r5, r6, r0)     // Catch:{ all -> 0x00c8 }
            if (r2 == 0) goto L_0x0129
            r2.close()
        L_0x0129:
            return r1
        L_0x012a:
            if (r1 == 0) goto L_0x012f
            r1.close()
        L_0x012f:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzam.zzc(java.lang.String, java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzbd");
    }

    @WorkerThread
    private final long zza(String str, String[] strArr, long j2) {
        Cursor cursor = null;
        try {
            Cursor rawQuery = e_().rawQuery(str, strArr);
            if (rawQuery.moveToFirst()) {
                long j3 = rawQuery.getLong(0);
                rawQuery.close();
                return j3;
            }
            rawQuery.close();
            return j2;
        } catch (SQLiteException e3) {
            zzj().zzg().zza("Database error", str, e3);
            throw e3;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:50:0x011a  */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.measurement.internal.zzom> zzb(java.lang.String r23, java.lang.String r24, java.lang.String r25) {
        /*
            r22 = this;
            r0 = r25
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r23)
            r22.zzt()
            r22.zzal()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r2 = 0
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x00fd }
            r4 = 3
            r3.<init>(r4)     // Catch:{ SQLiteException -> 0x00fd }
            r12 = r23
            r3.add(r12)     // Catch:{ SQLiteException -> 0x00f7 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x00f7 }
            java.lang.String r6 = "app_id=?"
            r5.<init>(r6)     // Catch:{ SQLiteException -> 0x00f7 }
            boolean r6 = android.text.TextUtils.isEmpty(r24)     // Catch:{ SQLiteException -> 0x00f7 }
            if (r6 != 0) goto L_0x003c
            r6 = r24
            r3.add(r6)     // Catch:{ SQLiteException -> 0x0037 }
            java.lang.String r7 = " and origin=?"
            r5.append(r7)     // Catch:{ SQLiteException -> 0x0037 }
            goto L_0x003e
        L_0x0034:
            r0 = move-exception
            goto L_0x011e
        L_0x0037:
            r0 = move-exception
            r13 = r22
            goto L_0x0103
        L_0x003c:
            r6 = r24
        L_0x003e:
            boolean r7 = android.text.TextUtils.isEmpty(r25)     // Catch:{ SQLiteException -> 0x0037 }
            if (r7 != 0) goto L_0x005d
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x0037 }
            r7.<init>()     // Catch:{ SQLiteException -> 0x0037 }
            r7.append(r0)     // Catch:{ SQLiteException -> 0x0037 }
            java.lang.String r8 = "*"
            r7.append(r8)     // Catch:{ SQLiteException -> 0x0037 }
            java.lang.String r7 = r7.toString()     // Catch:{ SQLiteException -> 0x0037 }
            r3.add(r7)     // Catch:{ SQLiteException -> 0x0037 }
            java.lang.String r7 = " and name glob ?"
            r5.append(r7)     // Catch:{ SQLiteException -> 0x0037 }
        L_0x005d:
            int r7 = r3.size()     // Catch:{ SQLiteException -> 0x0037 }
            java.lang.String[] r7 = new java.lang.String[r7]     // Catch:{ SQLiteException -> 0x0037 }
            java.lang.Object[] r3 = r3.toArray(r7)     // Catch:{ SQLiteException -> 0x0037 }
            r17 = r3
            java.lang.String[] r17 = (java.lang.String[]) r17     // Catch:{ SQLiteException -> 0x0037 }
            android.database.sqlite.SQLiteDatabase r13 = r22.e_()     // Catch:{ SQLiteException -> 0x0037 }
            java.lang.String r14 = "user_attributes"
            java.lang.String r3 = "name"
            java.lang.String r7 = "set_timestamp"
            java.lang.String r8 = "value"
            java.lang.String r9 = "origin"
            java.lang.String[] r15 = new java.lang.String[]{r3, r7, r8, r9}     // Catch:{ SQLiteException -> 0x0037 }
            java.lang.String r16 = r5.toString()     // Catch:{ SQLiteException -> 0x0037 }
            java.lang.String r20 = "rowid"
            java.lang.String r21 = "1001"
            r18 = 0
            r19 = 0
            android.database.Cursor r2 = r13.query(r14, r15, r16, r17, r18, r19, r20, r21)     // Catch:{ SQLiteException -> 0x0037 }
            boolean r3 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x0037 }
            if (r3 != 0) goto L_0x0097
            r2.close()
            return r1
        L_0x0097:
            int r3 = r1.size()     // Catch:{ SQLiteException -> 0x0037 }
            r5 = 1000(0x3e8, float:1.401E-42)
            if (r3 < r5) goto L_0x00b1
            com.google.android.gms.measurement.internal.zzgi r0 = r22.zzj()     // Catch:{ SQLiteException -> 0x0037 }
            com.google.android.gms.measurement.internal.zzgk r0 = r0.zzg()     // Catch:{ SQLiteException -> 0x0037 }
            java.lang.String r3 = "Read more than the max allowed user properties, ignoring excess"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r5)     // Catch:{ SQLiteException -> 0x0037 }
            r0.zza(r3, r4)     // Catch:{ SQLiteException -> 0x0037 }
            goto L_0x00ef
        L_0x00b1:
            r3 = 0
            java.lang.String r8 = r2.getString(r3)     // Catch:{ SQLiteException -> 0x0037 }
            r3 = 1
            long r9 = r2.getLong(r3)     // Catch:{ SQLiteException -> 0x0037 }
            r3 = 2
            r13 = r22
            java.lang.Object r11 = r13.zza((android.database.Cursor) r2, (int) r3)     // Catch:{ SQLiteException -> 0x00f5 }
            java.lang.String r3 = r2.getString(r4)     // Catch:{ SQLiteException -> 0x00f5 }
            if (r11 != 0) goto L_0x00dd
            com.google.android.gms.measurement.internal.zzgi r5 = r22.zzj()     // Catch:{ SQLiteException -> 0x00da }
            com.google.android.gms.measurement.internal.zzgk r5 = r5.zzg()     // Catch:{ SQLiteException -> 0x00da }
            java.lang.String r6 = "(2)Read invalid user property value, ignoring it"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r23)     // Catch:{ SQLiteException -> 0x00da }
            r5.zza(r6, r7, r3, r0)     // Catch:{ SQLiteException -> 0x00da }
            goto L_0x00e9
        L_0x00da:
            r0 = move-exception
            r6 = r3
            goto L_0x0103
        L_0x00dd:
            com.google.android.gms.measurement.internal.zzom r14 = new com.google.android.gms.measurement.internal.zzom     // Catch:{ SQLiteException -> 0x00da }
            r5 = r14
            r6 = r23
            r7 = r3
            r5.<init>(r6, r7, r8, r9, r11)     // Catch:{ SQLiteException -> 0x00da }
            r1.add(r14)     // Catch:{ SQLiteException -> 0x00da }
        L_0x00e9:
            boolean r5 = r2.moveToNext()     // Catch:{ SQLiteException -> 0x00da }
            if (r5 != 0) goto L_0x00f3
        L_0x00ef:
            r2.close()
            return r1
        L_0x00f3:
            r6 = r3
            goto L_0x0097
        L_0x00f5:
            r0 = move-exception
            goto L_0x0103
        L_0x00f7:
            r0 = move-exception
            r13 = r22
        L_0x00fa:
            r6 = r24
            goto L_0x0103
        L_0x00fd:
            r0 = move-exception
            r13 = r22
            r12 = r23
            goto L_0x00fa
        L_0x0103:
            com.google.android.gms.measurement.internal.zzgi r1 = r22.zzj()     // Catch:{ all -> 0x0034 }
            com.google.android.gms.measurement.internal.zzgk r1 = r1.zzg()     // Catch:{ all -> 0x0034 }
            java.lang.String r3 = "(2)Error querying user properties"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r23)     // Catch:{ all -> 0x0034 }
            r1.zza(r3, r4, r6, r0)     // Catch:{ all -> 0x0034 }
            java.util.List r0 = java.util.Collections.emptyList()     // Catch:{ all -> 0x0034 }
            if (r2 == 0) goto L_0x011d
            r2.close()
        L_0x011d:
            return r0
        L_0x011e:
            if (r2 == 0) goto L_0x0123
            r2.close()
        L_0x0123:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzam.zzb(java.lang.String, java.lang.String, java.lang.String):java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0086  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x008c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.util.Pair<com.google.android.gms.internal.measurement.zzgn.zzf, java.lang.Long> zza(java.lang.String r6, java.lang.Long r7) {
        /*
            r5 = this;
            r5.zzt()
            r5.zzal()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r5.e_()     // Catch:{ SQLiteException -> 0x0075, all -> 0x0073 }
            java.lang.String r2 = "select main_event, children_to_process from main_event_params where app_id=? and event_id=?"
            java.lang.String r3 = java.lang.String.valueOf(r7)     // Catch:{ SQLiteException -> 0x0075, all -> 0x0073 }
            java.lang.String[] r3 = new java.lang.String[]{r6, r3}     // Catch:{ SQLiteException -> 0x0075, all -> 0x0073 }
            android.database.Cursor r1 = r1.rawQuery(r2, r3)     // Catch:{ SQLiteException -> 0x0075, all -> 0x0073 }
            boolean r2 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x0033 }
            if (r2 != 0) goto L_0x0035
            com.google.android.gms.measurement.internal.zzgi r6 = r5.zzj()     // Catch:{ SQLiteException -> 0x0033 }
            com.google.android.gms.measurement.internal.zzgk r6 = r6.zzp()     // Catch:{ SQLiteException -> 0x0033 }
            java.lang.String r7 = "Main event not found"
            r6.zza(r7)     // Catch:{ SQLiteException -> 0x0033 }
            r1.close()
            return r0
        L_0x0030:
            r5 = move-exception
            r0 = r1
            goto L_0x008a
        L_0x0033:
            r6 = move-exception
            goto L_0x0077
        L_0x0035:
            r2 = 0
            byte[] r2 = r1.getBlob(r2)     // Catch:{ SQLiteException -> 0x0033 }
            r3 = 1
            long r3 = r1.getLong(r3)     // Catch:{ SQLiteException -> 0x0033 }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ SQLiteException -> 0x0033 }
            com.google.android.gms.internal.measurement.zzgn$zzf$zza r4 = com.google.android.gms.internal.measurement.zzgn.zzf.zze()     // Catch:{ IOException -> 0x005d }
            com.google.android.gms.internal.measurement.zzmk r2 = com.google.android.gms.measurement.internal.zzol.zza(r4, (byte[]) r2)     // Catch:{ IOException -> 0x005d }
            com.google.android.gms.internal.measurement.zzgn$zzf$zza r2 = (com.google.android.gms.internal.measurement.zzgn.zzf.zza) r2     // Catch:{ IOException -> 0x005d }
            com.google.android.gms.internal.measurement.zzml r2 = r2.zzai()     // Catch:{ IOException -> 0x005d }
            com.google.android.gms.internal.measurement.zzlc r2 = (com.google.android.gms.internal.measurement.zzlc) r2     // Catch:{ IOException -> 0x005d }
            com.google.android.gms.internal.measurement.zzgn$zzf r2 = (com.google.android.gms.internal.measurement.zzgn.zzf) r2     // Catch:{ IOException -> 0x005d }
            android.util.Pair r5 = android.util.Pair.create(r2, r3)     // Catch:{ SQLiteException -> 0x0033 }
            r1.close()
            return r5
        L_0x005d:
            r2 = move-exception
            com.google.android.gms.measurement.internal.zzgi r3 = r5.zzj()     // Catch:{ SQLiteException -> 0x0033 }
            com.google.android.gms.measurement.internal.zzgk r3 = r3.zzg()     // Catch:{ SQLiteException -> 0x0033 }
            java.lang.String r4 = "Failed to merge main event. appId, eventId"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r6)     // Catch:{ SQLiteException -> 0x0033 }
            r3.zza(r4, r6, r7, r2)     // Catch:{ SQLiteException -> 0x0033 }
            r1.close()
            return r0
        L_0x0073:
            r5 = move-exception
            goto L_0x008a
        L_0x0075:
            r6 = move-exception
            r1 = r0
        L_0x0077:
            com.google.android.gms.measurement.internal.zzgi r5 = r5.zzj()     // Catch:{ all -> 0x0030 }
            com.google.android.gms.measurement.internal.zzgk r5 = r5.zzg()     // Catch:{ all -> 0x0030 }
            java.lang.String r7 = "Error selecting main event"
            r5.zza(r7, r6)     // Catch:{ all -> 0x0030 }
            if (r1 == 0) goto L_0x0089
            r1.close()
        L_0x0089:
            return r0
        L_0x008a:
            if (r0 == 0) goto L_0x008f
            r0.close()
        L_0x008f:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzam.zza(java.lang.String, java.lang.Long):android.util.Pair");
    }

    @WorkerThread
    public final zzar zza(long j2, String str, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
        return zza(j2, str, 1, false, false, z4, false, z6, z7);
    }

    @WorkerThread
    public final zzar zza(long j2, String str, long j3, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
        Preconditions.checkNotEmpty(str);
        zzt();
        zzal();
        String[] strArr = {str};
        zzar zzar = new zzar();
        Cursor cursor = null;
        try {
            SQLiteDatabase e_ = e_();
            cursor = e_.query("apps", new String[]{"day", "daily_events_count", "daily_public_events_count", "daily_conversions_count", "daily_error_events_count", "daily_realtime_events_count", "daily_realtime_dcu_count"}, "app_id=?", new String[]{str}, (String) null, (String) null, (String) null);
            if (!cursor.moveToFirst()) {
                zzj().zzu().zza("Not updating daily counts, app is not known. appId", zzgi.zza(str));
                cursor.close();
                return zzar;
            }
            if (cursor.getLong(0) == j2) {
                zzar.zzb = cursor.getLong(1);
                zzar.zza = cursor.getLong(2);
                zzar.zzc = cursor.getLong(3);
                zzar.zzd = cursor.getLong(4);
                zzar.zze = cursor.getLong(5);
                zzar.zzf = cursor.getLong(6);
            }
            if (z2) {
                zzar.zzb += j3;
            }
            if (z3) {
                zzar.zza += j3;
            }
            if (z4) {
                zzar.zzc += j3;
            }
            if (z5) {
                zzar.zzd += j3;
            }
            if (z6) {
                zzar.zze += j3;
            }
            if (z7) {
                zzar.zzf += j3;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("day", Long.valueOf(j2));
            contentValues.put("daily_public_events_count", Long.valueOf(zzar.zza));
            contentValues.put("daily_events_count", Long.valueOf(zzar.zzb));
            contentValues.put("daily_conversions_count", Long.valueOf(zzar.zzc));
            contentValues.put("daily_error_events_count", Long.valueOf(zzar.zzd));
            contentValues.put("daily_realtime_events_count", Long.valueOf(zzar.zze));
            contentValues.put("daily_realtime_dcu_count", Long.valueOf(zzar.zzf));
            e_.update("apps", contentValues, "app_id=?", strArr);
            cursor.close();
            return zzar;
        } catch (SQLiteException e3) {
            zzj().zzg().zza("Error updating daily counts. appId", zzgi.zza(str), e3);
            if (cursor != null) {
                cursor.close();
            }
            return zzar;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0097  */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzom zze(java.lang.String r11, java.lang.String r12) {
        /*
            r10 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r11)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r12)
            r10.zzt()
            r10.zzal()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r10.e_()     // Catch:{ SQLiteException -> 0x0074, all -> 0x0072 }
            java.lang.String r2 = "user_attributes"
            java.lang.String r3 = "set_timestamp"
            java.lang.String r4 = "value"
            java.lang.String r5 = "origin"
            java.lang.String[] r3 = new java.lang.String[]{r3, r4, r5}     // Catch:{ SQLiteException -> 0x0074, all -> 0x0072 }
            java.lang.String r4 = "app_id=? and name=?"
            java.lang.String[] r5 = new java.lang.String[]{r11, r12}     // Catch:{ SQLiteException -> 0x0074, all -> 0x0072 }
            r7 = 0
            r8 = 0
            r6 = 0
            android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x0074, all -> 0x0072 }
            boolean r2 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x006c }
            if (r2 != 0) goto L_0x0034
            r1.close()
            return r0
        L_0x0034:
            r2 = 0
            long r7 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x006c }
            r2 = 1
            java.lang.Object r9 = r10.zza((android.database.Cursor) r1, (int) r2)     // Catch:{ SQLiteException -> 0x006c }
            if (r9 != 0) goto L_0x0044
            r1.close()
            return r0
        L_0x0044:
            r2 = 2
            java.lang.String r5 = r1.getString(r2)     // Catch:{ SQLiteException -> 0x006c }
            com.google.android.gms.measurement.internal.zzom r2 = new com.google.android.gms.measurement.internal.zzom     // Catch:{ SQLiteException -> 0x006c }
            r3 = r2
            r4 = r11
            r6 = r12
            r3.<init>(r4, r5, r6, r7, r9)     // Catch:{ SQLiteException -> 0x006c }
            boolean r3 = r1.moveToNext()     // Catch:{ SQLiteException -> 0x006c }
            if (r3 == 0) goto L_0x006e
            com.google.android.gms.measurement.internal.zzgi r3 = r10.zzj()     // Catch:{ SQLiteException -> 0x006c }
            com.google.android.gms.measurement.internal.zzgk r3 = r3.zzg()     // Catch:{ SQLiteException -> 0x006c }
            java.lang.String r4 = "Got multiple records for user property, expected one. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r11)     // Catch:{ SQLiteException -> 0x006c }
            r3.zza(r4, r5)     // Catch:{ SQLiteException -> 0x006c }
            goto L_0x006e
        L_0x0069:
            r10 = move-exception
            r0 = r1
            goto L_0x0095
        L_0x006c:
            r2 = move-exception
            goto L_0x0076
        L_0x006e:
            r1.close()
            return r2
        L_0x0072:
            r10 = move-exception
            goto L_0x0095
        L_0x0074:
            r2 = move-exception
            r1 = r0
        L_0x0076:
            com.google.android.gms.measurement.internal.zzgi r3 = r10.zzj()     // Catch:{ all -> 0x0069 }
            com.google.android.gms.measurement.internal.zzgk r3 = r3.zzg()     // Catch:{ all -> 0x0069 }
            java.lang.String r4 = "Error querying user property. appId"
            java.lang.Object r11 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r11)     // Catch:{ all -> 0x0069 }
            com.google.android.gms.measurement.internal.zzgh r10 = r10.zzi()     // Catch:{ all -> 0x0069 }
            java.lang.String r10 = r10.zzc(r12)     // Catch:{ all -> 0x0069 }
            r3.zza(r4, r11, r10, r2)     // Catch:{ all -> 0x0069 }
            if (r1 == 0) goto L_0x0094
            r1.close()
        L_0x0094:
            return r0
        L_0x0095:
            if (r0 == 0) goto L_0x009a
            r0.close()
        L_0x009a:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzam.zze(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzom");
    }

    public final void zzb(String str, zzjc zzjc) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(zzjc);
        zzt();
        zzal();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("consent_state", zzjc.zzh());
        contentValues.put("consent_source", Integer.valueOf(zzjc.zza()));
        zza("consent_settings", "app_id", contentValues);
    }

    private final boolean zzb(String str, List<Integer> list) {
        Preconditions.checkNotEmpty(str);
        zzal();
        zzt();
        SQLiteDatabase e_ = e_();
        try {
            long zzb2 = zzb("select count(1) from audience_filter_values where app_id=?", new String[]{str});
            int max = Math.max(0, Math.min(2000, zze().zzb(str, zzbj.zzah)));
            if (zzb2 <= ((long) max)) {
                return false;
            }
            ArrayList arrayList = new ArrayList();
            for (int i3 = 0; i3 < list.size(); i3++) {
                Integer num = list.get(i3);
                if (num == null) {
                    return false;
                }
                arrayList.add(Integer.toString(num.intValue()));
            }
            if (e_.delete("audience_filter_values", a.l("audience_id in (select audience_id from audience_filter_values where app_id=? and audience_id not in ", a.l("(", TextUtils.join(",", arrayList), ")"), " order by rowid desc limit -1 offset ?)"), new String[]{str, Integer.toString(max)}) > 0) {
                return true;
            }
            return false;
        } catch (SQLiteException e3) {
            zzj().zzg().zza("Database error querying filters. appId", zzgi.zza(str), e3);
            return false;
        }
    }

    @VisibleForTesting
    @WorkerThread
    private final Object zza(Cursor cursor, int i3) {
        int type = cursor.getType(i3);
        if (type == 0) {
            zzj().zzg().zza("Loaded invalid null value from database");
            return null;
        } else if (type == 1) {
            return Long.valueOf(cursor.getLong(i3));
        } else {
            if (type == 2) {
                return Double.valueOf(cursor.getDouble(i3));
            }
            if (type == 3) {
                return cursor.getString(i3);
            }
            if (type != 4) {
                zzj().zzg().zza("Loaded invalid unknown value type, ignoring it", Integer.valueOf(type));
                return null;
            }
            zzj().zzg().zza("Loaded invalid blob type value, ignoring it");
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0046  */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final <T> T zza(java.lang.String r3, java.lang.String[] r4, com.google.android.gms.measurement.internal.zzas<T> r5) {
        /*
            r2 = this;
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r2.e_()     // Catch:{ SQLiteException -> 0x002f, all -> 0x002d }
            android.database.Cursor r3 = r1.rawQuery(r3, r4)     // Catch:{ SQLiteException -> 0x002f, all -> 0x002d }
            boolean r4 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x0023 }
            if (r4 != 0) goto L_0x0025
            com.google.android.gms.measurement.internal.zzgi r4 = r2.zzj()     // Catch:{ SQLiteException -> 0x0023 }
            com.google.android.gms.measurement.internal.zzgk r4 = r4.zzp()     // Catch:{ SQLiteException -> 0x0023 }
            java.lang.String r5 = "No data found"
            r4.zza(r5)     // Catch:{ SQLiteException -> 0x0023 }
            r3.close()
            return r0
        L_0x0020:
            r2 = move-exception
            r0 = r3
            goto L_0x0044
        L_0x0023:
            r4 = move-exception
            goto L_0x0031
        L_0x0025:
            java.lang.Object r2 = r5.zza(r3)     // Catch:{ SQLiteException -> 0x0023 }
            r3.close()
            return r2
        L_0x002d:
            r2 = move-exception
            goto L_0x0044
        L_0x002f:
            r4 = move-exception
            r3 = r0
        L_0x0031:
            com.google.android.gms.measurement.internal.zzgi r2 = r2.zzj()     // Catch:{ all -> 0x0020 }
            com.google.android.gms.measurement.internal.zzgk r2 = r2.zzg()     // Catch:{ all -> 0x0020 }
            java.lang.String r5 = "Error querying database."
            r2.zza(r5, r4)     // Catch:{ all -> 0x0020 }
            if (r3 == 0) goto L_0x0043
            r3.close()
        L_0x0043:
            return r0
        L_0x0044:
            if (r0 == 0) goto L_0x0049
            r0.close()
        L_0x0049:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzam.zza(java.lang.String, java.lang.String[], com.google.android.gms.measurement.internal.zzas):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0057  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zza(long r4) {
        /*
            r3 = this;
            r3.zzt()
            r3.zzal()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r3.e_()     // Catch:{ SQLiteException -> 0x0040, all -> 0x003e }
            java.lang.String r2 = "select app_id from apps where app_id in (select distinct app_id from raw_events) and config_fetched_time < ? order by failed_config_fetch_time limit 1;"
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ SQLiteException -> 0x0040, all -> 0x003e }
            java.lang.String[] r4 = new java.lang.String[]{r4}     // Catch:{ SQLiteException -> 0x0040, all -> 0x003e }
            android.database.Cursor r4 = r1.rawQuery(r2, r4)     // Catch:{ SQLiteException -> 0x0040, all -> 0x003e }
            boolean r5 = r4.moveToFirst()     // Catch:{ SQLiteException -> 0x0033 }
            if (r5 != 0) goto L_0x0035
            com.google.android.gms.measurement.internal.zzgi r5 = r3.zzj()     // Catch:{ SQLiteException -> 0x0033 }
            com.google.android.gms.measurement.internal.zzgk r5 = r5.zzp()     // Catch:{ SQLiteException -> 0x0033 }
            java.lang.String r1 = "No expired configs for apps with pending events"
            r5.zza(r1)     // Catch:{ SQLiteException -> 0x0033 }
            r4.close()
            return r0
        L_0x0030:
            r3 = move-exception
            r0 = r4
            goto L_0x0055
        L_0x0033:
            r5 = move-exception
            goto L_0x0042
        L_0x0035:
            r5 = 0
            java.lang.String r3 = r4.getString(r5)     // Catch:{ SQLiteException -> 0x0033 }
            r4.close()
            return r3
        L_0x003e:
            r3 = move-exception
            goto L_0x0055
        L_0x0040:
            r5 = move-exception
            r4 = r0
        L_0x0042:
            com.google.android.gms.measurement.internal.zzgi r3 = r3.zzj()     // Catch:{ all -> 0x0030 }
            com.google.android.gms.measurement.internal.zzgk r3 = r3.zzg()     // Catch:{ all -> 0x0030 }
            java.lang.String r1 = "Error selecting expired configs"
            r3.zza(r1, r5)     // Catch:{ all -> 0x0030 }
            if (r4 == 0) goto L_0x0054
            r4.close()
        L_0x0054:
            return r0
        L_0x0055:
            if (r0 == 0) goto L_0x005a
            r0.close()
        L_0x005a:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzam.zza(long):java.lang.String");
    }

    @WorkerThread
    private final String zza(String str, String[] strArr, String str2) {
        Cursor cursor = null;
        try {
            Cursor rawQuery = e_().rawQuery(str, strArr);
            if (rawQuery.moveToFirst()) {
                String string = rawQuery.getString(0);
                rawQuery.close();
                return string;
            }
            rawQuery.close();
            return str2;
        } catch (SQLiteException e3) {
            zzj().zzg().zza("Database error", str, e3);
            throw e3;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    @WorkerThread
    public final List<Pair<zzgn.zzk, Long>> zza(String str, int i3, int i4) {
        long j2;
        long j3;
        int i5 = i4;
        zzt();
        zzal();
        int i6 = 1;
        Preconditions.checkArgument(i3 > 0);
        Preconditions.checkArgument(i5 > 0);
        Preconditions.checkNotEmpty(str);
        Cursor cursor = null;
        try {
            cursor = e_().query("queue", new String[]{"rowid", "data", "retry_count"}, "app_id=?", new String[]{str}, (String) null, (String) null, "rowid", String.valueOf(i3));
            if (!cursor.moveToFirst()) {
                List<Pair<zzgn.zzk, Long>> emptyList = Collections.emptyList();
                cursor.close();
                return emptyList;
            }
            ArrayList arrayList = new ArrayList();
            int i7 = 0;
            while (true) {
                long j4 = cursor.getLong(0);
                try {
                    byte[] zzc2 = g_().zzc(cursor.getBlob(i6));
                    if (!arrayList.isEmpty() && zzc2.length + i7 > i5) {
                        break;
                    }
                    try {
                        zzgn.zzk.zza zza2 = (zzgn.zzk.zza) zzol.zza(zzgn.zzk.zzw(), zzc2);
                        if (!arrayList.isEmpty()) {
                            zzgn.zzk zzk2 = (zzgn.zzk) ((Pair) arrayList.get(0)).first;
                            zzgn.zzk zzk3 = (zzgn.zzk) ((zzlc) zza2.zzai());
                            if (!zzk2.zzae().equals(zzk3.zzae()) || !zzk2.zzad().equals(zzk3.zzad()) || zzk2.zzau() != zzk3.zzau() || !zzk2.zzaf().equals(zzk3.zzaf())) {
                                break;
                            }
                            Iterator<zzgn.zzo> it = zzk2.zzas().iterator();
                            while (true) {
                                j2 = -1;
                                if (!it.hasNext()) {
                                    j3 = -1;
                                    break;
                                }
                                zzgn.zzo next = it.next();
                                if ("_npa".equals(next.zzg())) {
                                    j3 = next.zzc();
                                    break;
                                }
                            }
                            Iterator<zzgn.zzo> it2 = zzk3.zzas().iterator();
                            while (true) {
                                if (!it2.hasNext()) {
                                    break;
                                }
                                zzgn.zzo next2 = it2.next();
                                if ("_npa".equals(next2.zzg())) {
                                    j2 = next2.zzc();
                                    break;
                                }
                            }
                            if (j3 != j2) {
                                break;
                            }
                        }
                        if (!cursor.isNull(2)) {
                            zza2.zzi(cursor.getInt(2));
                        }
                        i7 += zzc2.length;
                        arrayList.add(Pair.create((zzgn.zzk) ((zzlc) zza2.zzai()), Long.valueOf(j4)));
                    } catch (IOException e3) {
                        zzj().zzg().zza("Failed to merge queued bundle. appId", zzgi.zza(str), e3);
                    }
                    if (!cursor.moveToNext() || i7 > i5) {
                        break;
                    }
                    i6 = 1;
                } catch (IOException e4) {
                    zzj().zzg().zza("Failed to unzip queued bundle. appId", zzgi.zza(str), e4);
                }
            }
            cursor.close();
            return arrayList;
        } catch (SQLiteException e5) {
            zzj().zzg().zza("Error querying bundles. appId", zzgi.zza(str), e5);
            List<Pair<zzgn.zzk, Long>> emptyList2 = Collections.emptyList();
            if (cursor != null) {
                cursor.close();
            }
            return emptyList2;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    @WorkerThread
    public final List<zzaf> zza(String str, String str2, String str3) {
        Preconditions.checkNotEmpty(str);
        zzt();
        zzal();
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(str);
        StringBuilder sb = new StringBuilder("app_id=?");
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
            sb.append(" and origin=?");
        }
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(str3 + Marker.ANY_MARKER);
            sb.append(" and name glob ?");
        }
        return zza(sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    public final List<zzaf> zza(String str, String[] strArr) {
        zzt();
        zzal();
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            cursor = e_().query("conditional_properties", new String[]{"app_id", "origin", "name", "value", AppMeasurementSdk.ConditionalUserProperty.ACTIVE, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, "timed_out_event", AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, "triggered_event", AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, "expired_event"}, str, strArr, (String) null, (String) null, "rowid", "1001");
            if (!cursor.moveToFirst()) {
                cursor.close();
                return arrayList;
            }
            while (true) {
                if (arrayList.size() < 1000) {
                    boolean z2 = false;
                    String string = cursor.getString(0);
                    String string2 = cursor.getString(1);
                    String string3 = cursor.getString(2);
                    Object zza2 = zza(cursor, 3);
                    if (cursor.getInt(4) != 0) {
                        z2 = true;
                    }
                    String string4 = cursor.getString(5);
                    long j2 = cursor.getLong(6);
                    zzol g_ = g_();
                    byte[] blob = cursor.getBlob(7);
                    Parcelable.Creator creator = zzbh.CREATOR;
                    boolean z3 = z2;
                    zzaf zzaf = r3;
                    zzaf zzaf2 = new zzaf(string, string2, new zzok(string3, cursor.getLong(10), zza2, string2), cursor.getLong(8), z3, string4, (zzbh) g_.zza(blob, creator), j2, (zzbh) g_().zza(cursor.getBlob(9), creator), cursor.getLong(11), (zzbh) g_().zza(cursor.getBlob(12), creator));
                    arrayList.add(zzaf);
                    if (!cursor.moveToNext()) {
                        break;
                    }
                } else {
                    zzj().zzg().zza("Read more than the max allowed conditional properties, ignoring extra", 1000);
                    break;
                }
            }
            cursor.close();
            return arrayList;
        } catch (SQLiteException e3) {
            zzj().zzg().zza("Error querying conditional user property value", e3);
            List<zzaf> emptyList = Collections.emptyList();
            if (cursor != null) {
                cursor.close();
            }
            return emptyList;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    @VisibleForTesting
    @WorkerThread
    public final void zza(List<Long> list) {
        zzt();
        zzal();
        Preconditions.checkNotNull(list);
        Preconditions.checkNotZero(list.size());
        if (zzaa()) {
            String l2 = a.l("(", TextUtils.join(",", list), ")");
            if (zzb(a.l("SELECT COUNT(1) FROM queue WHERE rowid IN ", l2, " AND retry_count =  2147483647 LIMIT 1"), (String[]) null) > 0) {
                zzj().zzu().zza("The number of upload retries exceeds the limit. Will remain unchanged.");
            }
            try {
                SQLiteDatabase e_ = e_();
                e_.execSQL("UPDATE queue SET retry_count = IFNULL(retry_count, 0) + 1 WHERE rowid IN " + l2 + " AND (retry_count IS NULL OR retry_count < 2147483647)");
            } catch (SQLiteException e3) {
                zzj().zzg().zza("Error incrementing retry count. error", e3);
            }
        }
    }

    @WorkerThread
    public final void zza(Long l2) {
        zzt();
        zzal();
        Preconditions.checkNotNull(l2);
        if ((!zzrw.zza() || zze().zza(zzbj.zzbz)) && zzaa()) {
            if (zzb("SELECT COUNT(1) FROM upload_queue WHERE rowid = " + l2 + " AND retry_count =  2147483647 LIMIT 1", (String[]) null) > 0) {
                zzj().zzu().zza("The number of upload retries exceeds the limit. Will remain unchanged.");
            }
            try {
                SQLiteDatabase e_ = e_();
                e_.execSQL("UPDATE upload_queue SET retry_count = retry_count + 1 WHERE rowid = " + l2 + " AND retry_count < 2147483647");
            } catch (SQLiteException e3) {
                zzj().zzg().zza("Error incrementing retry count. error", e3);
            }
        }
    }

    @WorkerThread
    private static void zza(ContentValues contentValues, String str, Object obj) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(obj);
        if (obj instanceof String) {
            contentValues.put(str, (String) obj);
        } else if (obj instanceof Long) {
            contentValues.put(str, (Long) obj);
        } else if (obj instanceof Double) {
            contentValues.put(str, (Double) obj);
        } else {
            throw new IllegalArgumentException("Invalid value type");
        }
    }

    @WorkerThread
    public final void zza(String str, List<zzfn.zza> list) {
        boolean z2;
        boolean z3;
        String str2 = str;
        List<zzfn.zza> list2 = list;
        Preconditions.checkNotNull(list);
        for (int i3 = 0; i3 < list.size(); i3++) {
            zzfn.zza.C0059zza zza2 = (zzfn.zza.C0059zza) list2.get(i3).zzcd();
            if (zza2.zza() != 0) {
                for (int i4 = 0; i4 < zza2.zza(); i4++) {
                    zzfn.zzb.zza zza3 = (zzfn.zzb.zza) zza2.zza(i4).zzcd();
                    zzfn.zzb.zza zza4 = (zzfn.zzb.zza) ((zzlc.zzb) zza3.clone());
                    String zzb2 = zzjf.zzb(zza3.zzb());
                    if (zzb2 != null) {
                        zza4.zza(zzb2);
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    for (int i5 = 0; i5 < zza3.zza(); i5++) {
                        zzfn.zzc zza5 = zza3.zza(i5);
                        String zza6 = zzje.zza(zza5.zze());
                        if (zza6 != null) {
                            zza4.zza(i5, (zzfn.zzc) ((zzlc) ((zzfn.zzc.zza) zza5.zzcd()).zza(zza6).zzai()));
                            z3 = true;
                        }
                    }
                    if (z3) {
                        zzfn.zza.C0059zza zza7 = zza2.zza(i4, zza4);
                        list2.set(i3, (zzfn.zza) ((zzlc) zza7.zzai()));
                        zza2 = zza7;
                    }
                }
            }
            if (zza2.zzb() != 0) {
                for (int i6 = 0; i6 < zza2.zzb(); i6++) {
                    zzfn.zze zzb3 = zza2.zzb(i6);
                    String zza8 = zzjh.zza(zzb3.zze());
                    if (zza8 != null) {
                        zza2 = zza2.zza(i6, ((zzfn.zze.zza) zzb3.zzcd()).zza(zza8));
                        list2.set(i3, (zzfn.zza) ((zzlc) zza2.zzai()));
                    }
                }
            }
        }
        zzal();
        zzt();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(list);
        SQLiteDatabase e_ = e_();
        e_.beginTransaction();
        try {
            zzal();
            zzt();
            Preconditions.checkNotEmpty(str);
            SQLiteDatabase e_2 = e_();
            e_2.delete("property_filters", "app_id=?", new String[]{str});
            e_2.delete("event_filters", "app_id=?", new String[]{str});
            for (zzfn.zza next : list) {
                zzal();
                zzt();
                Preconditions.checkNotEmpty(str);
                Preconditions.checkNotNull(next);
                if (!next.zzg()) {
                    zzj().zzu().zza("Audience with no ID. appId", zzgi.zza(str));
                } else {
                    int zza9 = next.zza();
                    Iterator<zzfn.zzb> it = next.zze().iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (!it.next().zzl()) {
                                zzj().zzu().zza("Event filter with no ID. Audience definition ignored. appId, audienceId", zzgi.zza(str), Integer.valueOf(zza9));
                                break;
                            }
                        } else {
                            Iterator<zzfn.zze> it2 = next.zzf().iterator();
                            while (true) {
                                if (it2.hasNext()) {
                                    if (!it2.next().zzi()) {
                                        zzj().zzu().zza("Property filter with no ID. Audience definition ignored. appId, audienceId", zzgi.zza(str), Integer.valueOf(zza9));
                                        break;
                                    }
                                } else {
                                    Iterator<zzfn.zzb> it3 = next.zze().iterator();
                                    while (true) {
                                        if (it3.hasNext()) {
                                            if (!zza(str2, zza9, it3.next())) {
                                                z2 = false;
                                                break;
                                            }
                                        } else {
                                            z2 = true;
                                            break;
                                        }
                                    }
                                    if (z2) {
                                        Iterator<zzfn.zze> it4 = next.zzf().iterator();
                                        while (true) {
                                            if (it4.hasNext()) {
                                                if (!zza(str2, zza9, it4.next())) {
                                                    z2 = false;
                                                    break;
                                                }
                                            } else {
                                                break;
                                            }
                                        }
                                    }
                                    if (!z2) {
                                        zzal();
                                        zzt();
                                        Preconditions.checkNotEmpty(str);
                                        SQLiteDatabase e_3 = e_();
                                        e_3.delete("property_filters", "app_id=? and audience_id=?", new String[]{str2, String.valueOf(zza9)});
                                        e_3.delete("event_filters", "app_id=? and audience_id=?", new String[]{str2, String.valueOf(zza9)});
                                    }
                                }
                            }
                        }
                    }
                }
            }
            ArrayList arrayList = new ArrayList();
            for (zzfn.zza next2 : list) {
                arrayList.add(next2.zzg() ? Integer.valueOf(next2.zza()) : null);
            }
            zzb(str2, (List<Integer>) arrayList);
            e_.setTransactionSuccessful();
            e_.endTransaction();
        } catch (Throwable th) {
            e_.endTransaction();
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0043, code lost:
        if (r7.zzg.zzb(r0).zza(com.google.android.gms.measurement.internal.zzjc.zza.ANALYTICS_STORAGE) != false) goto L_0x0045;
     */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0254  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x02b9  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x02fd  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x034a A[Catch:{ SQLiteException -> 0x035c }] */
    /* JADX WARNING: Removed duplicated region for block: B:81:? A[RETURN, SYNTHETIC] */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.measurement.internal.zzh r8, boolean r9, boolean r10) {
        /*
            r7 = this;
            java.lang.String r10 = "apps"
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r8)
            r7.zzt()
            r7.zzal()
            java.lang.String r0 = r8.zzac()
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r0)
            android.content.ContentValues r1 = new android.content.ContentValues
            r1.<init>()
            java.lang.String r2 = "app_id"
            r1.put(r2, r0)
            boolean r2 = com.google.android.gms.internal.measurement.zzpd.zza()
            java.lang.String r3 = "app_instance_id"
            r4 = 0
            if (r2 == 0) goto L_0x0045
            com.google.android.gms.measurement.internal.zzah r2 = r7.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r5 = com.google.android.gms.measurement.internal.zzbj.zzdc
            boolean r2 = r2.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r5)
            if (r2 == 0) goto L_0x0045
            if (r9 == 0) goto L_0x0037
            r1.put(r3, r4)
            goto L_0x004c
        L_0x0037:
            com.google.android.gms.measurement.internal.zznv r9 = r7.zzg
            com.google.android.gms.measurement.internal.zzjc r9 = r9.zzb((java.lang.String) r0)
            com.google.android.gms.measurement.internal.zzjc$zza r2 = com.google.android.gms.measurement.internal.zzjc.zza.ANALYTICS_STORAGE
            boolean r9 = r9.zza((com.google.android.gms.measurement.internal.zzjc.zza) r2)
            if (r9 == 0) goto L_0x004c
        L_0x0045:
            java.lang.String r9 = r8.zzad()
            r1.put(r3, r9)
        L_0x004c:
            java.lang.String r9 = "gmp_app_id"
            java.lang.String r2 = r8.zzah()
            r1.put(r9, r2)
            boolean r9 = com.google.android.gms.internal.measurement.zzpd.zza()
            if (r9 == 0) goto L_0x0075
            com.google.android.gms.measurement.internal.zzah r9 = r7.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzbj.zzdc
            boolean r9 = r9.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r2)
            if (r9 == 0) goto L_0x0075
            com.google.android.gms.measurement.internal.zznv r9 = r7.zzg
            com.google.android.gms.measurement.internal.zzjc r9 = r9.zzb((java.lang.String) r0)
            com.google.android.gms.measurement.internal.zzjc$zza r2 = com.google.android.gms.measurement.internal.zzjc.zza.AD_STORAGE
            boolean r9 = r9.zza((com.google.android.gms.measurement.internal.zzjc.zza) r2)
            if (r9 == 0) goto L_0x007e
        L_0x0075:
            java.lang.String r9 = "resettable_device_id_hash"
            java.lang.String r2 = r8.zzaj()
            r1.put(r9, r2)
        L_0x007e:
            long r2 = r8.zzt()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "last_bundle_index"
            r1.put(r2, r9)
            long r2 = r8.zzu()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "last_bundle_start_timestamp"
            r1.put(r2, r9)
            long r2 = r8.zzs()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "last_bundle_end_timestamp"
            r1.put(r2, r9)
            java.lang.String r9 = "app_version"
            java.lang.String r2 = r8.zzaf()
            r1.put(r9, r2)
            java.lang.String r9 = "app_store"
            java.lang.String r2 = r8.zzae()
            r1.put(r9, r2)
            long r2 = r8.zzq()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "gmp_version"
            r1.put(r2, r9)
            long r2 = r8.zzn()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "dev_cert_hash"
            r1.put(r2, r9)
            boolean r9 = r8.zzar()
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r9)
            java.lang.String r2 = "measurement_enabled"
            r1.put(r2, r9)
            long r2 = r8.zzm()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "day"
            r1.put(r2, r9)
            long r2 = r8.zzk()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "daily_public_events_count"
            r1.put(r2, r9)
            long r2 = r8.zzj()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "daily_events_count"
            r1.put(r2, r9)
            long r2 = r8.zzh()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "daily_conversions_count"
            r1.put(r2, r9)
            long r2 = r8.zzg()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "config_fetched_time"
            r1.put(r2, r9)
            long r2 = r8.zzp()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "failed_config_fetch_time"
            r1.put(r2, r9)
            long r2 = r8.zze()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "app_version_int"
            r1.put(r2, r9)
            java.lang.String r9 = "firebase_instance_id"
            java.lang.String r2 = r8.zzag()
            r1.put(r9, r2)
            long r2 = r8.zzi()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "daily_error_events_count"
            r1.put(r2, r9)
            long r2 = r8.zzl()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "daily_realtime_events_count"
            r1.put(r2, r9)
            java.lang.String r9 = "health_monitor_sample"
            java.lang.String r2 = r8.zzai()
            r1.put(r9, r2)
            long r2 = r8.zzd()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "android_id"
            r1.put(r2, r9)
            boolean r9 = r8.zzaq()
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r9)
            java.lang.String r2 = "adid_reporting_enabled"
            r1.put(r2, r9)
            java.lang.String r9 = "admob_app_id"
            java.lang.String r2 = r8.zzaa()
            r1.put(r9, r2)
            long r2 = r8.zzo()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "dynamite_version"
            r1.put(r2, r9)
            boolean r9 = com.google.android.gms.internal.measurement.zzpd.zza()
            if (r9 == 0) goto L_0x01b5
            com.google.android.gms.measurement.internal.zzah r9 = r7.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzbj.zzdc
            boolean r9 = r9.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r2)
            if (r9 == 0) goto L_0x01b5
            com.google.android.gms.measurement.internal.zznv r9 = r7.zzg
            com.google.android.gms.measurement.internal.zzjc r9 = r9.zzb((java.lang.String) r0)
            com.google.android.gms.measurement.internal.zzjc$zza r2 = com.google.android.gms.measurement.internal.zzjc.zza.ANALYTICS_STORAGE
            boolean r9 = r9.zza((com.google.android.gms.measurement.internal.zzjc.zza) r2)
            if (r9 == 0) goto L_0x01be
        L_0x01b5:
            java.lang.String r9 = "session_stitching_token"
            java.lang.String r2 = r8.zzal()
            r1.put(r9, r2)
        L_0x01be:
            boolean r9 = r8.zzat()
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r9)
            java.lang.String r2 = "sgtm_upload_enabled"
            r1.put(r2, r9)
            long r2 = r8.zzw()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "target_os_version"
            r1.put(r2, r9)
            long r2 = r8.zzv()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "session_stitching_token_hash"
            r1.put(r2, r9)
            boolean r9 = com.google.android.gms.internal.measurement.zzrl.zza()
            if (r9 == 0) goto L_0x0211
            com.google.android.gms.measurement.internal.zzah r9 = r7.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzbj.zzcg
            boolean r9 = r9.zze(r0, r2)
            if (r9 == 0) goto L_0x0211
            int r9 = r8.zza()
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            java.lang.String r2 = "ad_services_version"
            r1.put(r2, r9)
            long r2 = r8.zzf()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "attribution_eligibility_status"
            r1.put(r2, r9)
        L_0x0211:
            boolean r9 = com.google.android.gms.internal.measurement.zzpo.zza()
            if (r9 == 0) goto L_0x0230
            com.google.android.gms.measurement.internal.zzah r9 = r7.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzbj.zzcr
            boolean r9 = r9.zze(r0, r2)
            if (r9 == 0) goto L_0x0230
            boolean r9 = r8.zzau()
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r9)
            java.lang.String r2 = "unmatched_first_open_without_ad_id"
            r1.put(r2, r9)
        L_0x0230:
            java.lang.String r9 = "npa_metadata_value"
            java.lang.Boolean r2 = r8.zzx()
            r1.put(r9, r2)
            boolean r9 = com.google.android.gms.internal.measurement.zzrw.zza()
            if (r9 == 0) goto L_0x0261
            com.google.android.gms.measurement.internal.zzah r9 = r7.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzbj.zzbw
            boolean r9 = r9.zze(r0, r2)
            if (r9 == 0) goto L_0x0261
            r7.zzq()
            boolean r9 = com.google.android.gms.measurement.internal.zzop.zzf(r0)
            if (r9 == 0) goto L_0x0261
            long r2 = r8.zzr()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "bundle_delivery_index"
            r1.put(r2, r9)
        L_0x0261:
            boolean r9 = com.google.android.gms.internal.measurement.zzrw.zza()
            if (r9 == 0) goto L_0x027c
            com.google.android.gms.measurement.internal.zzah r9 = r7.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzbj.zzbx
            boolean r9 = r9.zze(r0, r2)
            if (r9 == 0) goto L_0x027c
            java.lang.String r9 = "sgtm_preview_key"
            java.lang.String r2 = r8.zzam()
            r1.put(r9, r2)
        L_0x027c:
            int r9 = r8.zzc()
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            java.lang.String r2 = "dma_consent_state"
            r1.put(r2, r9)
            int r9 = r8.zzb()
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            java.lang.String r2 = "daily_realtime_dcu_count"
            r1.put(r2, r9)
            boolean r9 = com.google.android.gms.internal.measurement.zzox.zza()
            if (r9 == 0) goto L_0x02b1
            com.google.android.gms.measurement.internal.zzah r9 = r7.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzbj.zzcw
            boolean r9 = r9.zze(r0, r2)
            if (r9 == 0) goto L_0x02b1
            java.lang.String r9 = "serialized_npa_metadata"
            java.lang.String r2 = r8.zzak()
            r1.put(r9, r2)
        L_0x02b1:
            java.util.List r9 = r8.zzan()
            java.lang.String r2 = "safelisted_events"
            if (r9 == 0) goto L_0x02d6
            boolean r3 = r9.isEmpty()
            if (r3 == 0) goto L_0x02cd
            com.google.android.gms.measurement.internal.zzgi r9 = r7.zzj()
            com.google.android.gms.measurement.internal.zzgk r9 = r9.zzu()
            java.lang.String r3 = "Safelisted events should not be an empty list. appId"
            r9.zza(r3, r0)
            goto L_0x02d6
        L_0x02cd:
            java.lang.String r3 = ","
            java.lang.String r9 = android.text.TextUtils.join(r3, r9)
            r1.put(r2, r9)
        L_0x02d6:
            boolean r9 = com.google.android.gms.internal.measurement.zzpv.zza()
            if (r9 == 0) goto L_0x02f1
            com.google.android.gms.measurement.internal.zzah r9 = r7.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r3 = com.google.android.gms.measurement.internal.zzbj.zzbt
            boolean r9 = r9.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r3)
            if (r9 == 0) goto L_0x02f1
            boolean r9 = r1.containsKey(r2)
            if (r9 != 0) goto L_0x02f1
            r1.put(r2, r4)
        L_0x02f1:
            com.google.android.gms.measurement.internal.zzah r9 = r7.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzbj.zzcz
            boolean r9 = r9.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r2)
            if (r9 == 0) goto L_0x030f
            java.lang.String r9 = "unmatched_pfo"
            java.lang.Long r2 = r8.zzy()
            r1.put(r9, r2)
            java.lang.String r9 = "unmatched_uwa"
            java.lang.Long r2 = r8.zzz()
            r1.put(r9, r2)
        L_0x030f:
            boolean r9 = com.google.android.gms.internal.measurement.zzqn.zza()
            if (r9 == 0) goto L_0x032a
            com.google.android.gms.measurement.internal.zzah r9 = r7.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzbj.zzct
            boolean r9 = r9.zze(r0, r2)
            if (r9 == 0) goto L_0x032a
            java.lang.String r9 = "ad_campaign_info"
            byte[] r8 = r8.zzav()
            r1.put(r9, r8)
        L_0x032a:
            android.database.sqlite.SQLiteDatabase r8 = r7.e_()     // Catch:{ SQLiteException -> 0x035c }
            java.lang.String r9 = "app_id = ?"
            java.lang.String[] r2 = new java.lang.String[]{r0}     // Catch:{ SQLiteException -> 0x035c }
            int r9 = r8.update(r10, r1, r9, r2)     // Catch:{ SQLiteException -> 0x035c }
            long r2 = (long) r9     // Catch:{ SQLiteException -> 0x035c }
            r5 = 0
            int r9 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r9 != 0) goto L_0x035e
            r9 = 5
            long r8 = r8.insertWithOnConflict(r10, r4, r1, r9)     // Catch:{ SQLiteException -> 0x035c }
            r1 = -1
            int r8 = (r8 > r1 ? 1 : (r8 == r1 ? 0 : -1))
            if (r8 != 0) goto L_0x035e
            com.google.android.gms.measurement.internal.zzgi r8 = r7.zzj()     // Catch:{ SQLiteException -> 0x035c }
            com.google.android.gms.measurement.internal.zzgk r8 = r8.zzg()     // Catch:{ SQLiteException -> 0x035c }
            java.lang.String r9 = "Failed to insert/update app (got -1). appId"
            java.lang.Object r10 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r0)     // Catch:{ SQLiteException -> 0x035c }
            r8.zza(r9, r10)     // Catch:{ SQLiteException -> 0x035c }
            goto L_0x035e
        L_0x035c:
            r8 = move-exception
            goto L_0x035f
        L_0x035e:
            return
        L_0x035f:
            com.google.android.gms.measurement.internal.zzgi r7 = r7.zzj()
            com.google.android.gms.measurement.internal.zzgk r7 = r7.zzg()
            java.lang.String r9 = "Error storing app. appId"
            java.lang.Object r10 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r0)
            r7.zza(r9, r10, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzam.zza(com.google.android.gms.measurement.internal.zzh, boolean, boolean):void");
    }

    public final void zza(String str, zzaz zzaz) {
        zzjc zzjc;
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(zzaz);
        zzt();
        zzal();
        if (zze().zza(zzbj.zzcq) && zzi(str) == (zzjc = zzjc.zza)) {
            zzb(str, zzjc);
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("dma_consent_settings", zzaz.zzf());
        zza("consent_settings", "app_id", contentValues);
    }

    @WorkerThread
    public final void zza(zzbd zzbd) {
        zza("events", zzbd);
    }

    @WorkerThread
    private final void zza(String str, zzbd zzbd) {
        Preconditions.checkNotNull(zzbd);
        zzt();
        zzal();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzbd.zza);
        contentValues.put("name", zzbd.zzb);
        contentValues.put("lifetime_count", Long.valueOf(zzbd.zzc));
        contentValues.put("current_bundle_count", Long.valueOf(zzbd.zzd));
        contentValues.put("last_fire_timestamp", Long.valueOf(zzbd.zzf));
        contentValues.put("last_bundled_timestamp", Long.valueOf(zzbd.zzg));
        contentValues.put("last_bundled_day", zzbd.zzh);
        contentValues.put("last_sampled_complex_event_id", zzbd.zzi);
        contentValues.put("last_sampling_rate", zzbd.zzj);
        contentValues.put("current_session_count", Long.valueOf(zzbd.zze));
        Boolean bool = zzbd.zzk;
        contentValues.put("last_exempt_from_sampling", (bool == null || !bool.booleanValue()) ? null : 1L);
        try {
            if (e_().insertWithOnConflict(str, (String) null, contentValues, 5) == -1) {
                zzj().zzg().zza("Failed to insert/update event aggregates (got -1). appId", zzgi.zza(zzbd.zza));
            }
        } catch (SQLiteException e3) {
            zzj().zzg().zza("Error storing event aggregates. appId", zzgi.zza(zzbd.zza), e3);
        }
    }

    @WorkerThread
    private final void zza(String str, String str2, ContentValues contentValues) {
        try {
            SQLiteDatabase e_ = e_();
            String asString = contentValues.getAsString(str2);
            if (asString == null) {
                zzj().zzh().zza("Value of the primary key is not set.", zzgi.zza(str2));
                return;
            }
            if (((long) e_.update(str, contentValues, str2 + " = ?", new String[]{asString})) == 0 && e_.insertWithOnConflict(str, (String) null, contentValues, 5) == -1) {
                zzj().zzg().zza("Failed to insert/update table (got -1). key", zzgi.zza(str), zzgi.zza(str2));
            }
        } catch (SQLiteException e3) {
            zzj().zzg().zza("Error storing into table. key", zzgi.zza(str), zzgi.zza(str2), e3);
        }
    }

    public final void zza(String str, zzjc zzjc) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(zzjc);
        zzt();
        zzal();
        zzb(str, zzi(str));
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("storage_consent_at_bundling", zzjc.zzh());
        zza("consent_settings", "app_id", contentValues);
    }

    @WorkerThread
    public final boolean zza(zzgn.zzk zzk2, boolean z2) {
        zzt();
        zzal();
        Preconditions.checkNotNull(zzk2);
        Preconditions.checkNotEmpty(zzk2.zzz());
        Preconditions.checkState(zzk2.zzbj());
        zzv();
        long currentTimeMillis = zzb().currentTimeMillis();
        if (zzk2.zzm() < currentTimeMillis - zzah.zzo() || zzk2.zzm() > zzah.zzo() + currentTimeMillis) {
            zzj().zzu().zza("Storing bundle outside of the max uploading time span. appId, now, timestamp", zzgi.zza(zzk2.zzz()), Long.valueOf(currentTimeMillis), Long.valueOf(zzk2.zzm()));
        }
        try {
            byte[] zzb2 = g_().zzb(zzk2.zzca());
            zzj().zzp().zza("Saving bundle, size", Integer.valueOf(zzb2.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zzk2.zzz());
            contentValues.put("bundle_end_timestamp", Long.valueOf(zzk2.zzm()));
            contentValues.put("data", zzb2);
            contentValues.put("has_realtime", Integer.valueOf(z2 ? 1 : 0));
            if (zzk2.zzbq()) {
                contentValues.put("retry_count", Integer.valueOf(zzk2.zzg()));
            }
            try {
                if (e_().insert("queue", (String) null, contentValues) != -1) {
                    return true;
                }
                zzj().zzg().zza("Failed to insert bundle (got -1). appId", zzgi.zza(zzk2.zzz()));
                return false;
            } catch (SQLiteException e3) {
                zzj().zzg().zza("Error storing bundle. appId", zzgi.zza(zzk2.zzz()), e3);
                return false;
            }
        } catch (IOException e4) {
            zzj().zzg().zza("Data loss. Failed to serialize bundle. appId", zzgi.zza(zzk2.zzz()), e4);
            return false;
        }
    }

    @WorkerThread
    private final boolean zza(String str, int i3, zzfn.zzb zzb2) {
        zzal();
        zzt();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzb2);
        Integer num = null;
        if (zzb2.zzf().isEmpty()) {
            zzgk zzu = zzj().zzu();
            Object zza2 = zzgi.zza(str);
            Integer valueOf = Integer.valueOf(i3);
            if (zzb2.zzl()) {
                num = Integer.valueOf(zzb2.zzb());
            }
            zzu.zza("Event filter had no event name. Audience definition ignored. appId, audienceId, filterId", zza2, valueOf, String.valueOf(num));
            return false;
        }
        byte[] zzca = zzb2.zzca();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("audience_id", Integer.valueOf(i3));
        contentValues.put("filter_id", zzb2.zzl() ? Integer.valueOf(zzb2.zzb()) : null);
        contentValues.put("event_name", zzb2.zzf());
        contentValues.put("session_scoped", zzb2.zzm() ? Boolean.valueOf(zzb2.zzj()) : null);
        contentValues.put("data", zzca);
        try {
            if (e_().insertWithOnConflict("event_filters", (String) null, contentValues, 5) != -1) {
                return true;
            }
            zzj().zzg().zza("Failed to insert event filter (got -1). appId", zzgi.zza(str));
            return true;
        } catch (SQLiteException e3) {
            zzj().zzg().zza("Error storing event filter. appId", zzgi.zza(str), e3);
            return false;
        }
    }

    @WorkerThread
    private final boolean zza(String str, int i3, zzfn.zze zze2) {
        zzal();
        zzt();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zze2);
        Integer num = null;
        if (zze2.zze().isEmpty()) {
            zzgk zzu = zzj().zzu();
            Object zza2 = zzgi.zza(str);
            Integer valueOf = Integer.valueOf(i3);
            if (zze2.zzi()) {
                num = Integer.valueOf(zze2.zza());
            }
            zzu.zza("Property filter had no property name. Audience definition ignored. appId, audienceId, filterId", zza2, valueOf, String.valueOf(num));
            return false;
        }
        byte[] zzca = zze2.zzca();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("audience_id", Integer.valueOf(i3));
        contentValues.put("filter_id", zze2.zzi() ? Integer.valueOf(zze2.zza()) : null);
        contentValues.put("property_name", zze2.zze());
        contentValues.put("session_scoped", zze2.zzj() ? Boolean.valueOf(zze2.zzh()) : null);
        contentValues.put("data", zzca);
        try {
            if (e_().insertWithOnConflict("property_filters", (String) null, contentValues, 5) != -1) {
                return true;
            }
            zzj().zzg().zza("Failed to insert property filter (got -1). appId", zzgi.zza(str));
            return false;
        } catch (SQLiteException e3) {
            zzj().zzg().zza("Error storing property filter. appId", zzgi.zza(str), e3);
            return false;
        }
    }

    public final boolean zza(zzba zzba, long j2, boolean z2) {
        zzt();
        zzal();
        Preconditions.checkNotNull(zzba);
        Preconditions.checkNotEmpty(zzba.zza);
        byte[] zzca = g_().zza(zzba).zzca();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzba.zza);
        contentValues.put("name", zzba.zzb);
        contentValues.put("timestamp", Long.valueOf(zzba.zzd));
        contentValues.put("metadata_fingerprint", Long.valueOf(j2));
        contentValues.put("data", zzca);
        contentValues.put("realtime", Integer.valueOf(z2 ? 1 : 0));
        try {
            if (e_().insert("raw_events", (String) null, contentValues) != -1) {
                return true;
            }
            zzj().zzg().zza("Failed to insert raw event (got -1). appId", zzgi.zza(zzba.zza));
            return false;
        } catch (SQLiteException e3) {
            zzj().zzg().zza("Error storing raw event. appId", zzgi.zza(zzba.zza), e3);
            return false;
        }
    }

    @WorkerThread
    public final boolean zza(String str, zznk zznk) {
        zzt();
        zzal();
        Preconditions.checkNotNull(zznk);
        Preconditions.checkNotEmpty(str);
        long currentTimeMillis = zzb().currentTimeMillis();
        if (zznk.zzb < currentTimeMillis - zzah.zzo() || zznk.zzb > zzah.zzo() + currentTimeMillis) {
            zzj().zzu().zza("Storing trigger URI outside of the max retention time span. appId, now, timestamp", zzgi.zza(str), Long.valueOf(currentTimeMillis), Long.valueOf(zznk.zzb));
        }
        zzj().zzp().zza("Saving trigger URI");
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("trigger_uri", zznk.zza);
        contentValues.put("source", Integer.valueOf(zznk.zzc));
        contentValues.put("timestamp_millis", Long.valueOf(zznk.zzb));
        try {
            if (e_().insert("trigger_uris", (String) null, contentValues) != -1) {
                return true;
            }
            zzj().zzg().zza("Failed to insert trigger URI (got -1). appId", zzgi.zza(str));
            return false;
        } catch (SQLiteException e3) {
            zzj().zzg().zza("Error storing trigger URI. appId", zzgi.zza(str), e3);
            return false;
        }
    }

    @WorkerThread
    public final boolean zza(String str, zzgn.zzj zzj2, String str2, Map<String, String> map, zznt zznt) {
        zzt();
        zzal();
        Preconditions.checkNotNull(zzj2);
        Preconditions.checkNotEmpty(str);
        if (zzrw.zza() && !zze().zza(zzbj.zzbz)) {
            return false;
        }
        zzt();
        zzal();
        if (zzaa()) {
            long zza2 = zzn().zzb.zza();
            long elapsedRealtime = zzb().elapsedRealtime();
            if (Math.abs(elapsedRealtime - zza2) > zzah.zzp()) {
                zzn().zzb.zza(elapsedRealtime);
                zzt();
                zzal();
                if (zzaa()) {
                    SQLiteDatabase e_ = e_();
                    zzb().currentTimeMillis();
                    zznt zznt2 = zznt.GOOGLE_SIGNAL;
                    zznt2.zza();
                    zzah.zzm();
                    zznt2.zza();
                    zzah.zzo();
                    int delete = e_.delete("upload_queue", "ABS(creation_timestamp - ?) > CAST(? as integer)", new String[0]);
                    if (delete > 0) {
                        zzj().zzp().zza("Deleted stale MeasurementBatch rows from upload_queue. rowsDeleted", Integer.valueOf(delete));
                    }
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry next : map.entrySet()) {
            arrayList.add(((String) next.getKey()) + StickyVariantProvider.KEY_VALUE_DELIMITER + ((String) next.getValue()));
        }
        byte[] zzca = zzj2.zzca();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("measurement_batch", zzca);
        contentValues.put("upload_uri", str2);
        StringBuilder sb = new StringBuilder();
        int size = arrayList.size();
        if (size > 0) {
            sb.append((CharSequence) arrayList.get(0));
            int i3 = 1;
            while (i3 < size) {
                sb.append(LineSeparator.Windows);
                Object obj = arrayList.get(i3);
                i3++;
                sb.append((CharSequence) obj);
            }
        }
        contentValues.put("upload_headers", sb.toString());
        contentValues.put("upload_type", Integer.valueOf(zznt.zza()));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, Long.valueOf(zzb().currentTimeMillis()));
        contentValues.put("retry_count", 0);
        try {
            if (e_().insert("upload_queue", (String) null, contentValues) != -1) {
                return true;
            }
            zzj().zzg().zza("Failed to insert MeasurementBatch (got -1) to upload_queue. appId", str);
            return false;
        } catch (SQLiteException e3) {
            zzj().zzg().zza("Error storing MeasurementBatch to upload_queue. appId", str, e3);
            return false;
        }
    }

    public final boolean zza(String str, Long l2, long j2, zzgn.zzf zzf2) {
        zzt();
        zzal();
        Preconditions.checkNotNull(zzf2);
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(l2);
        byte[] zzca = zzf2.zzca();
        zzj().zzp().zza("Saving complex main event, appId, data size", zzi().zza(str), Integer.valueOf(zzca.length));
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("event_id", l2);
        contentValues.put("children_to_process", Long.valueOf(j2));
        contentValues.put("main_event", zzca);
        try {
            if (e_().insertWithOnConflict("main_event_params", (String) null, contentValues, 5) != -1) {
                return true;
            }
            zzj().zzg().zza("Failed to insert complex main event (got -1). appId", zzgi.zza(str));
            return false;
        } catch (SQLiteException e3) {
            zzj().zzg().zza("Error storing complex main event. appId", zzgi.zza(str), e3);
            return false;
        }
    }

    @WorkerThread
    public final boolean zza(zzaf zzaf) {
        Preconditions.checkNotNull(zzaf);
        zzt();
        zzal();
        String str = zzaf.zza;
        Preconditions.checkNotNull(str);
        if (zze(str, zzaf.zzc.zza) == null && zzb("SELECT COUNT(1) FROM conditional_properties WHERE app_id=?", new String[]{str}) >= 1000) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("origin", zzaf.zzb);
        contentValues.put("name", zzaf.zzc.zza);
        zza(contentValues, "value", Preconditions.checkNotNull(zzaf.zzc.zza()));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.ACTIVE, Boolean.valueOf(zzaf.zze));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, zzaf.zzf);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, Long.valueOf(zzaf.zzh));
        zzq();
        contentValues.put("timed_out_event", zzop.zza((Parcelable) zzaf.zzg));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, Long.valueOf(zzaf.zzd));
        zzq();
        contentValues.put("triggered_event", zzop.zza((Parcelable) zzaf.zzi));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, Long.valueOf(zzaf.zzc.zzb));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, Long.valueOf(zzaf.zzj));
        zzq();
        contentValues.put("expired_event", zzop.zza((Parcelable) zzaf.zzk));
        try {
            if (e_().insertWithOnConflict("conditional_properties", (String) null, contentValues, 5) != -1) {
                return true;
            }
            zzj().zzg().zza("Failed to insert/update conditional user property (got -1)", zzgi.zza(str));
            return true;
        } catch (SQLiteException e3) {
            zzj().zzg().zza("Error storing conditional user property", zzgi.zza(str), e3);
            return true;
        }
    }

    public final boolean zza(String str, Bundle bundle) {
        zzt();
        zzal();
        byte[] zzca = g_().zza(new zzba(this.zzu, "", str, "dep", 0, 0, bundle)).zzca();
        zzj().zzp().zza("Saving default event parameters, appId, data size", zzi().zza(str), Integer.valueOf(zzca.length));
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("parameters", zzca);
        try {
            if (e_().insertWithOnConflict("default_event_params", (String) null, contentValues, 5) != -1) {
                return true;
            }
            zzj().zzg().zza("Failed to insert default event parameters (got -1). appId", zzgi.zza(str));
            return false;
        } catch (SQLiteException e3) {
            zzj().zzg().zza("Error storing default event parameters. appId", zzgi.zza(str), e3);
            return false;
        }
    }

    public final boolean zza(long j2, zzba zzba, long j3, boolean z2) {
        zzt();
        zzal();
        Preconditions.checkNotNull(zzba);
        Preconditions.checkNotEmpty(zzba.zza);
        byte[] zzca = g_().zza(zzba).zzca();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzba.zza);
        contentValues.put("name", zzba.zzb);
        contentValues.put("timestamp", Long.valueOf(zzba.zzd));
        contentValues.put("metadata_fingerprint", Long.valueOf(j3));
        contentValues.put("data", zzca);
        contentValues.put("realtime", Integer.valueOf(z2 ? 1 : 0));
        try {
            long update = (long) e_().update("raw_events", contentValues, "rowid = ?", new String[]{String.valueOf(j2)});
            if (update == 1) {
                return true;
            }
            zzj().zzg().zza("Failed to update raw event. appId, updatedRows", zzgi.zza(zzba.zza), Long.valueOf(update));
            return false;
        } catch (SQLiteException e3) {
            zzj().zzg().zza("Error updating raw event. appId", zzgi.zza(zzba.zza), e3);
            return false;
        }
    }

    @WorkerThread
    public final boolean zza(zzom zzom) {
        Preconditions.checkNotNull(zzom);
        zzt();
        zzal();
        if (zze(zzom.zza, zzom.zzc) == null) {
            if (zzop.zzh(zzom.zzc)) {
                if (zzb("select count(1) from user_attributes where app_id=? and name not like '!_%' escape '!'", new String[]{zzom.zza}) >= ((long) zze().zza(zzom.zza, zzbj.zzai, 25, 100))) {
                    return false;
                }
            } else if (!"_npa".equals(zzom.zzc) && zzb("select count(1) from user_attributes where app_id=? and origin=? AND name like '!_%' escape '!'", new String[]{zzom.zza, zzom.zzb}) >= 25) {
                return false;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzom.zza);
        contentValues.put("origin", zzom.zzb);
        contentValues.put("name", zzom.zzc);
        contentValues.put("set_timestamp", Long.valueOf(zzom.zzd));
        zza(contentValues, "value", zzom.zze);
        try {
            if (e_().insertWithOnConflict("user_attributes", (String) null, contentValues, 5) != -1) {
                return true;
            }
            zzj().zzg().zza("Failed to insert/update user property (got -1). appId", zzgi.zza(zzom.zza));
            return true;
        } catch (SQLiteException e3) {
            zzj().zzg().zza("Error storing user property. appId", zzgi.zza(zzom.zza), e3);
            return true;
        }
    }
}
