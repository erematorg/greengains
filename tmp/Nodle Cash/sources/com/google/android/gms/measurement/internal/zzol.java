package com.google.android.gms.measurement.internal;

import A.a;
import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.WorkerThread;
import com.amplitude.api.Constants;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzad;
import com.google.android.gms.internal.measurement.zzfn;
import com.google.android.gms.internal.measurement.zzgn;
import com.google.android.gms.internal.measurement.zzkp;
import com.google.android.gms.internal.measurement.zzlc;
import com.google.android.gms.internal.measurement.zzlk;
import com.google.android.gms.internal.measurement.zzmk;
import com.google.android.gms.internal.measurement.zzpp;
import com.google.android.gms.internal.measurement.zzqn;
import com.google.android.gms.internal.measurement.zzrl;
import com.google.android.gms.internal.measurement.zzrq;
import com.google.android.gms.internal.measurement.zzrw;
import com.google.android.gms.measurement.internal.zzjc;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.checkerframework.dataflow.qual.Pure;

public final class zzol extends zznr {
    public zzol(zznv zznv) {
        super(zznv);
    }

    public static int zza(zzgn.zzk.zza zza, String str) {
        if (zza == null) {
            return -1;
        }
        for (int i3 = 0; i3 < zza.zzd(); i3++) {
            if (str.equals(zza.zzk(i3).zzg())) {
                return i3;
            }
        }
        return -1;
    }

    public final /* bridge */ /* synthetic */ zzol g_() {
        return super.g_();
    }

    @Pure
    public final /* bridge */ /* synthetic */ Clock zzb() {
        return super.zzb();
    }

    public final boolean zzc() {
        return false;
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

    public final /* bridge */ /* synthetic */ zzv zzg() {
        return super.zzg();
    }

    public final /* bridge */ /* synthetic */ zzam zzh() {
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

    public final /* bridge */ /* synthetic */ zzhg zzm() {
        return super.zzm();
    }

    public final /* bridge */ /* synthetic */ zzms zzn() {
        return super.zzn();
    }

    public final /* bridge */ /* synthetic */ zznq zzo() {
        return super.zzo();
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

    public final List<Integer> zzu() {
        Map<String, String> zza = zzbj.zza(this.zzg.zza());
        if (zza == null || zza.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int intValue = zzbj.zzar.zza(null).intValue();
        Iterator<Map.Entry<String, String>> it = zza.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Map.Entry next = it.next();
            if (((String) next.getKey()).startsWith("measurement.id.")) {
                try {
                    int parseInt = Integer.parseInt((String) next.getValue());
                    if (parseInt != 0) {
                        arrayList.add(Integer.valueOf(parseInt));
                        if (arrayList.size() >= intValue) {
                            zzj().zzu().zza("Too many experiment IDs. Number of IDs", Integer.valueOf(arrayList.size()));
                            break;
                        }
                    } else {
                        continue;
                    }
                } catch (NumberFormatException e3) {
                    zzj().zzu().zza("Experiment ID NumberFormatException", e3);
                }
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList;
    }

    public static Object zzb(zzgn.zzf zzf, String str) {
        zzgn.zzh zza = zza(zzf, str);
        if (zza == null) {
            return null;
        }
        if (zza.zzn()) {
            return zza.zzh();
        }
        if (zza.zzl()) {
            return Long.valueOf(zza.zzd());
        }
        if (zza.zzj()) {
            return Double.valueOf(zza.zza());
        }
        if (zza.zzc() > 0) {
            return zzb(zza.zzi());
        }
        return null;
    }

    public final boolean zzc(String str) {
        if (zzpp.zza() && zze().zza(zzbj.zzcv)) {
            return false;
        }
        Preconditions.checkNotNull(str);
        zzh zze = zzh().zze(str);
        if (zze != null && zzf().zzn() && zze.zzaq() && zzm().zzk(str)) {
            return true;
        }
        return false;
    }

    public final long zza(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        return zza(str.getBytes(Charset.forName("UTF-8")));
    }

    @WorkerThread
    public final long zza(byte[] bArr) {
        Preconditions.checkNotNull(bArr);
        zzq().zzt();
        MessageDigest zzu = zzop.zzu();
        if (zzu != null) {
            return zzop.zza(zzu.digest(bArr));
        }
        zzj().zzg().zza("Failed to get MD5");
        return 0;
    }

    public final byte[] zzc(byte[] bArr) throws IOException {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr2 = new byte[1024];
            while (true) {
                int read = gZIPInputStream.read(bArr2);
                if (read > 0) {
                    byteArrayOutputStream.write(bArr2, 0, read);
                } else {
                    gZIPInputStream.close();
                    byteArrayInputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (IOException e3) {
            zzj().zzg().zza("Failed to ungzip content", e3);
            throw e3;
        }
    }

    public static boolean zzb(String str) {
        return str != null && str.matches("([+-])?([0-9]+\\.?[0-9]*|[0-9]*\\.?[0-9]+)") && str.length() <= 310;
    }

    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
    }

    public static Bundle zza(List<zzgn.zzh> list) {
        Bundle bundle = new Bundle();
        for (zzgn.zzh next : list) {
            String zzg = next.zzg();
            if (next.zzj()) {
                bundle.putDouble(zzg, next.zza());
            } else if (next.zzk()) {
                bundle.putFloat(zzg, next.zzb());
            } else if (next.zzn()) {
                bundle.putString(zzg, next.zzh());
            } else if (next.zzl()) {
                bundle.putLong(zzg, next.zzd());
            }
        }
        return bundle;
    }

    public final byte[] zzb(byte[] bArr) throws IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e3) {
            zzj().zzg().zza("Failed to gzip content", e3);
            throw e3;
        }
    }

    public static Bundle[] zzb(List<zzgn.zzh> list) {
        ArrayList arrayList = new ArrayList();
        for (zzgn.zzh next : list) {
            if (next != null) {
                Bundle bundle = new Bundle();
                for (zzgn.zzh next2 : next.zzi()) {
                    if (next2.zzn()) {
                        bundle.putString(next2.zzg(), next2.zzh());
                    } else if (next2.zzl()) {
                        bundle.putLong(next2.zzg(), next2.zzd());
                    } else if (next2.zzj()) {
                        bundle.putDouble(next2.zzg(), next2.zza());
                    }
                }
                if (!bundle.isEmpty()) {
                    arrayList.add(bundle);
                }
            }
        }
        return (Bundle[]) arrayList.toArray(new Bundle[arrayList.size()]);
    }

    private final Bundle zza(Map<String, Object> map, boolean z2) {
        Bundle bundle = new Bundle();
        for (String next : map.keySet()) {
            Object obj = map.get(next);
            if (obj == null) {
                bundle.putString(next, (String) null);
            } else if (obj instanceof Long) {
                bundle.putLong(next, ((Long) obj).longValue());
            } else if (obj instanceof Double) {
                bundle.putDouble(next, ((Double) obj).doubleValue());
            } else if (!(obj instanceof ArrayList)) {
                bundle.putString(next, obj.toString());
            } else if (z2) {
                ArrayList arrayList = (ArrayList) obj;
                ArrayList arrayList2 = new ArrayList();
                int size = arrayList.size();
                int i3 = 0;
                while (i3 < size) {
                    Object obj2 = arrayList.get(i3);
                    i3++;
                    arrayList2.add(zza((Map<String, Object>) (Map) obj2, false));
                }
                bundle.putParcelableArray(next, (Parcelable[]) arrayList2.toArray(new Parcelable[0]));
            }
        }
        return bundle;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        zzj().zzg().zza("Failed to load parcelable from buffer");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002c, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002d, code lost:
        r1.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0030, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001a, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x001c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <T extends android.os.Parcelable> T zza(byte[] r5, android.os.Parcelable.Creator<T> r6) {
        /*
            r4 = this;
            r0 = 0
            if (r5 != 0) goto L_0x0004
            return r0
        L_0x0004:
            android.os.Parcel r1 = android.os.Parcel.obtain()
            int r2 = r5.length     // Catch:{ ParseException -> 0x001c }
            r3 = 0
            r1.unmarshall(r5, r3, r2)     // Catch:{ ParseException -> 0x001c }
            r1.setDataPosition(r3)     // Catch:{ ParseException -> 0x001c }
            java.lang.Object r5 = r6.createFromParcel(r1)     // Catch:{ ParseException -> 0x001c }
            android.os.Parcelable r5 = (android.os.Parcelable) r5     // Catch:{ ParseException -> 0x001c }
            r1.recycle()
            return r5
        L_0x001a:
            r4 = move-exception
            goto L_0x002d
        L_0x001c:
            com.google.android.gms.measurement.internal.zzgi r4 = r4.zzj()     // Catch:{ all -> 0x001a }
            com.google.android.gms.measurement.internal.zzgk r4 = r4.zzg()     // Catch:{ all -> 0x001a }
            java.lang.String r5 = "Failed to load parcelable from buffer"
            r4.zza(r5)     // Catch:{ all -> 0x001a }
            r1.recycle()
            return r0
        L_0x002d:
            r1.recycle()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzol.zza(byte[], android.os.Parcelable$Creator):android.os.Parcelable");
    }

    public final zzbh zza(zzad zzad) {
        String str;
        Object obj;
        Bundle zza = zza(zzad.zzc(), true);
        if (!zza.containsKey("_o") || (obj = zza.get("_o")) == null) {
            str = "app";
        } else {
            str = obj.toString();
        }
        String str2 = str;
        String zzb = zzjf.zzb(zzad.zzb());
        if (zzb == null) {
            zzb = zzad.zzb();
        }
        return new zzbh(zzb, new zzbc(zza), str2, zzad.zza());
    }

    @TargetApi(30)
    public final zznk zza(String str, zzgn.zzk.zza zza, zzgn.zzf.zza zza2, String str2) {
        int indexOf;
        if (!zzrl.zza() || !zze().zze(str, zzbj.zzcg)) {
            return null;
        }
        long currentTimeMillis = zzb().currentTimeMillis();
        String[] split = zze().zzd(str, zzbj.zzbg).split(",");
        HashSet hashSet = new HashSet(split.length);
        int length = split.length;
        int i3 = 0;
        while (i3 < length) {
            String str3 = split[i3];
            Objects.requireNonNull(str3);
            if (hashSet.add(str3)) {
                i3++;
            } else {
                throw new IllegalArgumentException("duplicate element: " + str3);
            }
        }
        Set unmodifiableSet = Collections.unmodifiableSet(hashSet);
        zznq zzo = zzo();
        String zzf = zzo.zzm().zzf(str);
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(zzo.zze().zzd(str, zzbj.zzaz));
        if (!TextUtils.isEmpty(zzf)) {
            builder.authority(zzf + JwtUtilsKt.JWT_DELIMITER + zzo.zze().zzd(str, zzbj.zzba));
        } else {
            builder.authority(zzo.zze().zzd(str, zzbj.zzba));
        }
        builder.path(zzo.zze().zzd(str, zzbj.zzbb));
        zza(builder, "gmp_app_id", zza.zzx(), (Set<String>) unmodifiableSet);
        zza(builder, "gmp_version", "102001", (Set<String>) unmodifiableSet);
        String zzu = zza.zzu();
        zzah zze = zze();
        zzfz<Boolean> zzfz = zzbj.zzcj;
        if (zze.zze(str, zzfz) && zzm().zzp(str)) {
            zzu = "";
        }
        zza(builder, "app_instance_id", zzu, (Set<String>) unmodifiableSet);
        zza(builder, "rdid", zza.zzz(), (Set<String>) unmodifiableSet);
        zza(builder, "bundle_id", zza.zzt(), (Set<String>) unmodifiableSet);
        String zze2 = zza2.zze();
        String zza3 = zzjf.zza(zze2);
        if (!TextUtils.isEmpty(zza3)) {
            zze2 = zza3;
        }
        zza(builder, "app_event_name", zze2, (Set<String>) unmodifiableSet);
        zza(builder, "app_version", String.valueOf(zza.zzb()), (Set<String>) unmodifiableSet);
        String zzy = zza.zzy();
        if (zze().zze(str, zzfz) && zzm().zzt(str) && !TextUtils.isEmpty(zzy) && (indexOf = zzy.indexOf(JwtUtilsKt.JWT_DELIMITER)) != -1) {
            zzy = zzy.substring(0, indexOf);
        }
        zza(builder, Constants.AMP_TRACKING_OPTION_OS_VERSION, zzy, (Set<String>) unmodifiableSet);
        zza(builder, "timestamp", String.valueOf(zza2.zzc()), (Set<String>) unmodifiableSet);
        String str4 = "1";
        if (zza.zzad()) {
            zza(builder, "lat", str4, (Set<String>) unmodifiableSet);
        }
        zza(builder, "privacy_sandbox_version", String.valueOf(zza.zza()), (Set<String>) unmodifiableSet);
        zza(builder, "trigger_uri_source", str4, (Set<String>) unmodifiableSet);
        zza(builder, "trigger_uri_timestamp", String.valueOf(currentTimeMillis), (Set<String>) unmodifiableSet);
        zza(builder, "request_uuid", str2, (Set<String>) unmodifiableSet);
        List<zzgn.zzh> zzf2 = zza2.zzf();
        Bundle bundle = new Bundle();
        for (zzgn.zzh next : zzf2) {
            String zzg = next.zzg();
            if (next.zzj()) {
                bundle.putString(zzg, String.valueOf(next.zza()));
            } else if (next.zzk()) {
                bundle.putString(zzg, String.valueOf(next.zzb()));
            } else if (next.zzn()) {
                bundle.putString(zzg, next.zzh());
            } else if (next.zzl()) {
                bundle.putString(zzg, String.valueOf(next.zzd()));
            }
        }
        zza(builder, zze().zzd(str, zzbj.zzbf).split("\\|"), bundle, (Set<String>) unmodifiableSet);
        List<zzgn.zzo> zzab = zza.zzab();
        Bundle bundle2 = new Bundle();
        for (zzgn.zzo next2 : zzab) {
            String zzg2 = next2.zzg();
            if (next2.zzi()) {
                bundle2.putString(zzg2, String.valueOf(next2.zza()));
            } else if (next2.zzj()) {
                bundle2.putString(zzg2, String.valueOf(next2.zzb()));
            } else if (next2.zzm()) {
                bundle2.putString(zzg2, next2.zzh());
            } else if (next2.zzk()) {
                bundle2.putString(zzg2, String.valueOf(next2.zzc()));
            }
        }
        zza(builder, zze().zzd(str, zzbj.zzbe).split("\\|"), bundle2, (Set<String>) unmodifiableSet);
        if (!zza.zzac()) {
            str4 = SchemaSymbols.ATTVAL_FALSE_0;
        }
        zza(builder, Constants.AMP_TRACKING_OPTION_DMA, str4, (Set<String>) unmodifiableSet);
        if (!zza.zzw().isEmpty()) {
            zza(builder, "dma_cps", zza.zzw(), (Set<String>) unmodifiableSet);
        }
        if (zze().zza(zzbj.zzcl) && zza.zzae()) {
            zzgn.zza zzg3 = zza.zzg();
            if (!zzg3.zzh().isEmpty()) {
                zza(builder, "dl_gclid", zzg3.zzh(), (Set<String>) unmodifiableSet);
            }
            if (!zzg3.zzg().isEmpty()) {
                zza(builder, "dl_gbraid", zzg3.zzg(), (Set<String>) unmodifiableSet);
            }
            if (!zzg3.zzf().isEmpty()) {
                zza(builder, "dl_gs", zzg3.zzf(), (Set<String>) unmodifiableSet);
            }
            if (zzg3.zza() > 0) {
                zza(builder, "dl_ss_ts", String.valueOf(zzg3.zza()), (Set<String>) unmodifiableSet);
            }
            if (!zzg3.zzk().isEmpty()) {
                zza(builder, "mr_gclid", zzg3.zzk(), (Set<String>) unmodifiableSet);
            }
            if (!zzg3.zzj().isEmpty()) {
                zza(builder, "mr_gbraid", zzg3.zzj(), (Set<String>) unmodifiableSet);
            }
            if (!zzg3.zzi().isEmpty()) {
                zza(builder, "mr_gs", zzg3.zzi(), (Set<String>) unmodifiableSet);
            }
            if (zzg3.zzb() > 0) {
                zza(builder, "mr_click_ts", String.valueOf(zzg3.zzb()), (Set<String>) unmodifiableSet);
            }
        }
        return new zznk(builder.build().toString(), currentTimeMillis, 1);
    }

    public final zzgn.zzf zza(zzba zzba) {
        zzgn.zzf.zza zza = zzgn.zzf.zze().zza(zzba.zze);
        Iterator<String> it = zzba.zzf.iterator();
        while (it.hasNext()) {
            String next = it.next();
            zzgn.zzh.zza zza2 = zzgn.zzh.zze().zza(next);
            Object zzc = zzba.zzf.zzc(next);
            Preconditions.checkNotNull(zzc);
            zza(zza2, zzc);
            zza.zza(zza2);
        }
        if (zze().zza(zzbj.zzdm) && !TextUtils.isEmpty(zzba.zzc) && zzba.zzf.zzc("_o") == null) {
            zza.zza((zzgn.zzh) ((zzlc) zzgn.zzh.zze().zza("_o").zzb(zzba.zzc).zzai()));
        }
        return (zzgn.zzf) ((zzlc) zza.zzai());
    }

    public static zzgn.zzh zza(zzgn.zzf zzf, String str) {
        for (zzgn.zzh next : zzf.zzh()) {
            if (next.zzg().equals(str)) {
                return next;
            }
        }
        return null;
    }

    public static <BuilderT extends zzmk> BuilderT zza(BuilderT buildert, byte[] bArr) throws zzlk {
        zzkp zza = zzkp.zza();
        if (zza != null) {
            return buildert.zza(bArr, zza);
        }
        return buildert.zza(bArr);
    }

    public static Object zza(zzgn.zzf zzf, String str, Object obj) {
        Object zzb = zzb(zzf, str);
        return zzb == null ? obj : zzb;
    }

    public final String zza(zzgn.zzj zzj) {
        zzgn.zzc zzv;
        if (zzj == null) {
            return "";
        }
        StringBuilder p2 = a.p("\nbatch {\n");
        if (zzrw.zza() && zze().zza(zzbj.zzbw) && zzj.zza() > 0) {
            zzq();
            if (zzop.zzf(zzj.zza(0).zzz())) {
                if (zzj.zzh()) {
                    zza(p2, 0, "upload_subdomain", (Object) zzj.zze());
                }
                if (zzj.zzg()) {
                    zza(p2, 0, "sgtm_join_id", (Object) zzj.zzd());
                }
            }
        }
        for (zzgn.zzk next : zzj.zzf()) {
            if (next != null) {
                zza(p2, 1);
                p2.append("bundle {\n");
                if (next.zzbp()) {
                    zza(p2, 1, "protocol_version", (Object) Integer.valueOf(next.zzf()));
                }
                if (zzrq.zza() && zze().zze(next.zzz(), zzbj.zzbv) && next.zzbs()) {
                    zza(p2, 1, "session_stitching_token", (Object) next.zzao());
                }
                zza(p2, 1, Constants.AMP_TRACKING_OPTION_PLATFORM, (Object) next.zzam());
                if (next.zzbk()) {
                    zza(p2, 1, "gmp_version", (Object) Long.valueOf(next.zzn()));
                }
                if (next.zzbx()) {
                    zza(p2, 1, "uploading_gmp_version", (Object) Long.valueOf(next.zzt()));
                }
                if (next.zzbi()) {
                    zza(p2, 1, "dynamite_version", (Object) Long.valueOf(next.zzl()));
                }
                if (next.zzbb()) {
                    zza(p2, 1, "config_version", (Object) Long.valueOf(next.zzj()));
                }
                zza(p2, 1, "gmp_app_id", (Object) next.zzaj());
                zza(p2, 1, "admob_app_id", (Object) next.zzy());
                zza(p2, 1, "app_id", (Object) next.zzz());
                zza(p2, 1, "app_version", (Object) next.zzac());
                if (next.zzay()) {
                    zza(p2, 1, "app_version_major", (Object) Integer.valueOf(next.zzb()));
                }
                zza(p2, 1, "firebase_instance_id", (Object) next.zzai());
                if (next.zzbg()) {
                    zza(p2, 1, "dev_cert_hash", (Object) Long.valueOf(next.zzk()));
                }
                zza(p2, 1, "app_store", (Object) next.zzab());
                if (next.zzbw()) {
                    zza(p2, 1, "upload_timestamp_millis", (Object) Long.valueOf(next.zzs()));
                }
                if (next.zzbt()) {
                    zza(p2, 1, "start_timestamp_millis", (Object) Long.valueOf(next.zzq()));
                }
                if (next.zzbj()) {
                    zza(p2, 1, "end_timestamp_millis", (Object) Long.valueOf(next.zzm()));
                }
                if (next.zzbo()) {
                    zza(p2, 1, "previous_bundle_start_timestamp_millis", (Object) Long.valueOf(next.zzp()));
                }
                if (next.zzbn()) {
                    zza(p2, 1, "previous_bundle_end_timestamp_millis", (Object) Long.valueOf(next.zzo()));
                }
                zza(p2, 1, "app_instance_id", (Object) next.zzaa());
                zza(p2, 1, "resettable_device_id", (Object) next.zzan());
                zza(p2, 1, "ds_id", (Object) next.zzah());
                if (next.zzbm()) {
                    zza(p2, 1, "limited_ad_tracking", (Object) Boolean.valueOf(next.zzav()));
                }
                zza(p2, 1, Constants.AMP_TRACKING_OPTION_OS_VERSION, (Object) next.zzal());
                zza(p2, 1, Constants.AMP_TRACKING_OPTION_DEVICE_MODEL, (Object) next.zzag());
                zza(p2, 1, "user_default_language", (Object) next.zzap());
                if (next.zzbv()) {
                    zza(p2, 1, "time_zone_offset_minutes", (Object) Integer.valueOf(next.zzh()));
                }
                if (next.zzba()) {
                    zza(p2, 1, "bundle_sequential_index", (Object) Integer.valueOf(next.zzc()));
                }
                if (zzrw.zza()) {
                    zzq();
                    if (zzop.zzf(next.zzz()) && zze().zza(zzbj.zzbw) && next.zzbf()) {
                        zza(p2, 1, "delivery_index", (Object) Integer.valueOf(next.zzd()));
                    }
                }
                if (next.zzbr()) {
                    zza(p2, 1, "service_upload", (Object) Boolean.valueOf(next.zzaw()));
                }
                zza(p2, 1, "health_monitor", (Object) next.zzak());
                if (next.zzbq()) {
                    zza(p2, 1, "retry_counter", (Object) Integer.valueOf(next.zzg()));
                }
                if (next.zzbd()) {
                    zza(p2, 1, "consent_signals", (Object) next.zzae());
                }
                if (next.zzbl()) {
                    zza(p2, 1, "is_dma_region", (Object) Boolean.valueOf(next.zzau()));
                }
                if (next.zzbe()) {
                    zza(p2, 1, "core_platform_services", (Object) next.zzaf());
                }
                if (next.zzbc()) {
                    zza(p2, 1, "consent_diagnostics", (Object) next.zzad());
                }
                if (next.zzbu()) {
                    zza(p2, 1, "target_os_version", (Object) Long.valueOf(next.zzr()));
                }
                if (zzrl.zza() && zze().zze(next.zzz(), zzbj.zzcg)) {
                    zza(p2, 1, "ad_services_version", (Object) Integer.valueOf(next.zza()));
                    if (next.zzaz() && (zzv = next.zzv()) != null) {
                        zza(p2, 2);
                        p2.append("attribution_eligibility_status {\n");
                        zza(p2, 2, "eligible", (Object) Boolean.valueOf(zzv.zzf()));
                        zza(p2, 2, "no_access_adservices_attribution_permission", (Object) Boolean.valueOf(zzv.zzh()));
                        zza(p2, 2, "pre_r", (Object) Boolean.valueOf(zzv.zzi()));
                        zza(p2, 2, "r_extensions_too_old", (Object) Boolean.valueOf(zzv.zzj()));
                        zza(p2, 2, "adservices_extension_too_old", (Object) Boolean.valueOf(zzv.zze()));
                        zza(p2, 2, "ad_storage_not_allowed", (Object) Boolean.valueOf(zzv.zzd()));
                        zza(p2, 2, "measurement_manager_disabled", (Object) Boolean.valueOf(zzv.zzg()));
                        zza(p2, 2);
                        p2.append("}\n");
                    }
                }
                if (zzqn.zza() && zze().zza(zzbj.zzct) && next.zzax()) {
                    zzgn.zza zzu = next.zzu();
                    zza(p2, 2);
                    p2.append("ad_campaign_info {\n");
                    if (zzu.zzn()) {
                        zza(p2, 2, "deep_link_gclid", (Object) zzu.zzh());
                    }
                    if (zzu.zzm()) {
                        zza(p2, 2, "deep_link_gbraid", (Object) zzu.zzg());
                    }
                    if (zzu.zzl()) {
                        zza(p2, 2, "deep_link_gad_source", (Object) zzu.zzf());
                    }
                    if (zzu.zzo()) {
                        zza(p2, 2, "deep_link_session_millis", (Object) Long.valueOf(zzu.zza()));
                    }
                    if (zzu.zzs()) {
                        zza(p2, 2, "market_referrer_gclid", (Object) zzu.zzk());
                    }
                    if (zzu.zzr()) {
                        zza(p2, 2, "market_referrer_gbraid", (Object) zzu.zzj());
                    }
                    if (zzu.zzq()) {
                        zza(p2, 2, "market_referrer_gad_source", (Object) zzu.zzi());
                    }
                    if (zzu.zzp()) {
                        zza(p2, 2, "market_referrer_click_millis", (Object) Long.valueOf(zzu.zzb()));
                    }
                    zza(p2, 2);
                    p2.append("}\n");
                }
                List<zzgn.zzo> zzas = next.zzas();
                if (zzas != null) {
                    for (zzgn.zzo next2 : zzas) {
                        if (next2 != null) {
                            zza(p2, 2);
                            p2.append("user_property {\n");
                            Double d2 = null;
                            zza(p2, 2, "set_timestamp_millis", (Object) next2.zzl() ? Long.valueOf(next2.zzd()) : null);
                            zza(p2, 2, "name", (Object) zzi().zzc(next2.zzg()));
                            zza(p2, 2, "string_value", (Object) next2.zzh());
                            zza(p2, 2, "int_value", (Object) next2.zzk() ? Long.valueOf(next2.zzc()) : null);
                            if (next2.zzi()) {
                                d2 = Double.valueOf(next2.zza());
                            }
                            zza(p2, 2, "double_value", (Object) d2);
                            zza(p2, 2);
                            p2.append("}\n");
                        }
                    }
                }
                List<zzgn.zzd> zzaq = next.zzaq();
                next.zzz();
                if (zzaq != null) {
                    for (zzgn.zzd next3 : zzaq) {
                        if (next3 != null) {
                            zza(p2, 2);
                            p2.append("audience_membership {\n");
                            if (next3.zzg()) {
                                zza(p2, 2, "audience_id", (Object) Integer.valueOf(next3.zza()));
                            }
                            if (next3.zzh()) {
                                zza(p2, 2, "new_audience", (Object) Boolean.valueOf(next3.zzf()));
                            }
                            zza(p2, 2, "current_data", next3.zzd());
                            if (next3.zzi()) {
                                zza(p2, 2, "previous_data", next3.zze());
                            }
                            zza(p2, 2);
                            p2.append("}\n");
                        }
                    }
                }
                List<zzgn.zzf> zzar = next.zzar();
                if (zzar != null) {
                    for (zzgn.zzf next4 : zzar) {
                        if (next4 != null) {
                            zza(p2, 2);
                            p2.append("event {\n");
                            zza(p2, 2, "name", (Object) zzi().zza(next4.zzg()));
                            if (next4.zzk()) {
                                zza(p2, 2, "timestamp_millis", (Object) Long.valueOf(next4.zzd()));
                            }
                            if (next4.zzj()) {
                                zza(p2, 2, "previous_timestamp_millis", (Object) Long.valueOf(next4.zzc()));
                            }
                            if (next4.zzi()) {
                                zza(p2, 2, "count", (Object) Integer.valueOf(next4.zza()));
                            }
                            if (next4.zzb() != 0) {
                                zza(p2, 2, next4.zzh());
                            }
                            zza(p2, 2);
                            p2.append("}\n");
                        }
                    }
                }
                zza(p2, 1);
                p2.append("}\n");
            }
        }
        p2.append("} // End-of-batch\n");
        return p2.toString();
    }

    public final String zza(zzfn.zzb zzb) {
        if (zzb == null) {
            return AbstractJsonLexerKt.NULL;
        }
        StringBuilder p2 = a.p("\nevent_filter {\n");
        if (zzb.zzl()) {
            zza(p2, 0, "filter_id", (Object) Integer.valueOf(zzb.zzb()));
        }
        zza(p2, 0, "event_name", (Object) zzi().zza(zzb.zzf()));
        String zza = zza(zzb.zzh(), zzb.zzi(), zzb.zzj());
        if (!zza.isEmpty()) {
            zza(p2, 0, "filter_type", (Object) zza);
        }
        if (zzb.zzk()) {
            zza(p2, 1, "event_count_filter", zzb.zze());
        }
        if (zzb.zza() > 0) {
            p2.append("  filters {\n");
            for (zzfn.zzc zza2 : zzb.zzg()) {
                zza(p2, 2, zza2);
            }
        }
        zza(p2, 1);
        p2.append("}\n}\n");
        return p2.toString();
    }

    private static String zza(boolean z2, boolean z3, boolean z4) {
        StringBuilder sb = new StringBuilder();
        if (z2) {
            sb.append("Dynamic ");
        }
        if (z3) {
            sb.append("Sequence ");
        }
        if (z4) {
            sb.append("Session-Scoped ");
        }
        return sb.toString();
    }

    public final String zza(zzfn.zze zze) {
        if (zze == null) {
            return AbstractJsonLexerKt.NULL;
        }
        StringBuilder p2 = a.p("\nproperty_filter {\n");
        if (zze.zzi()) {
            zza(p2, 0, "filter_id", (Object) Integer.valueOf(zze.zza()));
        }
        zza(p2, 0, "property_name", (Object) zzi().zzc(zze.zze()));
        String zza = zza(zze.zzf(), zze.zzg(), zze.zzh());
        if (!zza.isEmpty()) {
            zza(p2, 0, "filter_type", (Object) zza);
        }
        zza(p2, 1, zze.zzb());
        p2.append("}\n");
        return p2.toString();
    }

    public final List<Long> zza(List<Long> list, List<Integer> list2) {
        int i3;
        ArrayList arrayList = new ArrayList(list);
        for (Integer next : list2) {
            if (next.intValue() < 0) {
                zzj().zzu().zza("Ignoring negative bit index to be cleared", next);
            } else {
                int intValue = next.intValue() / 64;
                if (intValue >= arrayList.size()) {
                    zzj().zzu().zza("Ignoring bit index greater than bitSet size", next, Integer.valueOf(arrayList.size()));
                } else {
                    arrayList.set(intValue, Long.valueOf(((Long) arrayList.get(intValue)).longValue() & (~(1 << (next.intValue() % 64)))));
                }
            }
        }
        int size = arrayList.size();
        int size2 = arrayList.size() - 1;
        while (true) {
            int i4 = size2;
            i3 = size;
            size = i4;
            if (size >= 0 && ((Long) arrayList.get(size)).longValue() == 0) {
                size2 = size - 1;
            }
        }
        return arrayList.subList(0, i3);
    }

    public static List<Long> zza(BitSet bitSet) {
        int length = (bitSet.length() + 63) / 64;
        ArrayList arrayList = new ArrayList(length);
        for (int i3 = 0; i3 < length; i3++) {
            long j2 = 0;
            for (int i4 = 0; i4 < 64; i4++) {
                int i5 = (i3 << 6) + i4;
                if (i5 >= bitSet.length()) {
                    break;
                }
                if (bitSet.get(i5)) {
                    j2 |= 1 << i4;
                }
            }
            arrayList.add(Long.valueOf(j2));
        }
        return arrayList;
    }

    public final Map<String, Object> zza(Bundle bundle, boolean z2) {
        HashMap hashMap = new HashMap();
        for (String next : bundle.keySet()) {
            Object obj = bundle.get(next);
            boolean z3 = obj instanceof Parcelable[];
            if (z3 || (obj instanceof ArrayList) || (obj instanceof Bundle)) {
                if (z2) {
                    ArrayList arrayList = new ArrayList();
                    if (z3) {
                        for (Parcelable parcelable : (Parcelable[]) obj) {
                            if (parcelable instanceof Bundle) {
                                arrayList.add(zza((Bundle) parcelable, false));
                            }
                        }
                    } else if (obj instanceof ArrayList) {
                        ArrayList arrayList2 = (ArrayList) obj;
                        int size = arrayList2.size();
                        int i3 = 0;
                        while (i3 < size) {
                            Object obj2 = arrayList2.get(i3);
                            i3++;
                            if (obj2 instanceof Bundle) {
                                arrayList.add(zza((Bundle) obj2, false));
                            }
                        }
                    } else if (obj instanceof Bundle) {
                        arrayList.add(zza((Bundle) obj, false));
                    }
                    hashMap.put(next, arrayList);
                }
            } else if (obj != null) {
                hashMap.put(next, obj);
            }
        }
        return hashMap;
    }

    public static void zza(zzgn.zzf.zza zza, String str, Object obj) {
        List<zzgn.zzh> zzf = zza.zzf();
        int i3 = 0;
        while (true) {
            if (i3 >= zzf.size()) {
                i3 = -1;
                break;
            } else if (str.equals(zzf.get(i3).zzg())) {
                break;
            } else {
                i3++;
            }
        }
        zzgn.zzh.zza zza2 = zzgn.zzh.zze().zza(str);
        if (obj instanceof Long) {
            zza2.zza(((Long) obj).longValue());
        } else if (obj instanceof String) {
            zza2.zzb((String) obj);
        } else if (obj instanceof Double) {
            zza2.zza(((Double) obj).doubleValue());
        }
        if (i3 >= 0) {
            zza.zza(i3, zza2);
        } else {
            zza.zza(zza2);
        }
    }

    private static void zza(Uri.Builder builder, String[] strArr, Bundle bundle, Set<String> set) {
        for (String split : strArr) {
            String[] split2 = split.split(",");
            String str = split2[0];
            String str2 = split2[split2.length - 1];
            String string = bundle.getString(str);
            if (string != null) {
                zza(builder, str2, string, set);
            }
        }
    }

    private static void zza(StringBuilder sb, int i3, String str, zzgn.zzm zzm) {
        if (zzm != null) {
            zza(sb, 3);
            sb.append(str);
            sb.append(" {\n");
            if (zzm.zzb() != 0) {
                zza(sb, 4);
                sb.append("results: ");
                int i4 = 0;
                for (Long next : zzm.zzi()) {
                    int i5 = i4 + 1;
                    if (i4 != 0) {
                        sb.append(", ");
                    }
                    sb.append(next);
                    i4 = i5;
                }
                sb.append(10);
            }
            if (zzm.zzd() != 0) {
                zza(sb, 4);
                sb.append("status: ");
                int i6 = 0;
                for (Long next2 : zzm.zzk()) {
                    int i7 = i6 + 1;
                    if (i6 != 0) {
                        sb.append(", ");
                    }
                    sb.append(next2);
                    i6 = i7;
                }
                sb.append(10);
            }
            if (zzm.zza() != 0) {
                zza(sb, 4);
                sb.append("dynamic_filter_timestamps: {");
                int i8 = 0;
                for (zzgn.zze next3 : zzm.zzh()) {
                    int i9 = i8 + 1;
                    if (i8 != 0) {
                        sb.append(", ");
                    }
                    sb.append(next3.zzf() ? Integer.valueOf(next3.zza()) : null);
                    sb.append(":");
                    sb.append(next3.zze() ? Long.valueOf(next3.zzb()) : null);
                    i8 = i9;
                }
                sb.append("}\n");
            }
            if (zzm.zzc() != 0) {
                zza(sb, 4);
                sb.append("sequence_filter_timestamps: {");
                int i10 = 0;
                for (zzgn.zzn next4 : zzm.zzj()) {
                    int i11 = i10 + 1;
                    if (i10 != 0) {
                        sb.append(", ");
                    }
                    sb.append(next4.zzf() ? Integer.valueOf(next4.zzb()) : null);
                    sb.append(": [");
                    int i12 = 0;
                    for (Long longValue : next4.zze()) {
                        long longValue2 = longValue.longValue();
                        int i13 = i12 + 1;
                        if (i12 != 0) {
                            sb.append(", ");
                        }
                        sb.append(longValue2);
                        i12 = i13;
                    }
                    sb.append("]");
                    i10 = i11;
                }
                sb.append("}\n");
            }
            zza(sb, 3);
            sb.append("}\n");
        }
    }

    private final void zza(StringBuilder sb, int i3, List<zzgn.zzh> list) {
        if (list != null) {
            int i4 = i3 + 1;
            for (zzgn.zzh next : list) {
                if (next != null) {
                    zza(sb, i4);
                    sb.append("param {\n");
                    Double d2 = null;
                    zza(sb, i4, "name", (Object) next.zzm() ? zzi().zzb(next.zzg()) : null);
                    zza(sb, i4, "string_value", (Object) next.zzn() ? next.zzh() : null);
                    zza(sb, i4, "int_value", (Object) next.zzl() ? Long.valueOf(next.zzd()) : null);
                    if (next.zzj()) {
                        d2 = Double.valueOf(next.zza());
                    }
                    zza(sb, i4, "double_value", (Object) d2);
                    if (next.zzc() > 0) {
                        zza(sb, i4, next.zzi());
                    }
                    zza(sb, i4);
                    sb.append("}\n");
                }
            }
        }
    }

    private final void zza(StringBuilder sb, int i3, zzfn.zzc zzc) {
        if (zzc != null) {
            zza(sb, i3);
            sb.append("filter {\n");
            if (zzc.zzg()) {
                zza(sb, i3, "complement", (Object) Boolean.valueOf(zzc.zzf()));
            }
            if (zzc.zzi()) {
                zza(sb, i3, "param_name", (Object) zzi().zzb(zzc.zze()));
            }
            if (zzc.zzj()) {
                int i4 = i3 + 1;
                zzfn.zzf zzd = zzc.zzd();
                if (zzd != null) {
                    zza(sb, i4);
                    sb.append("string_filter");
                    sb.append(" {\n");
                    if (zzd.zzj()) {
                        zza(sb, i4, "match_type", (Object) zzd.zzb().name());
                    }
                    if (zzd.zzi()) {
                        zza(sb, i4, "expression", (Object) zzd.zze());
                    }
                    if (zzd.zzh()) {
                        zza(sb, i4, "case_sensitive", (Object) Boolean.valueOf(zzd.zzg()));
                    }
                    if (zzd.zza() > 0) {
                        zza(sb, i3 + 2);
                        sb.append("expression_list {\n");
                        for (String append : zzd.zzf()) {
                            zza(sb, i3 + 3);
                            sb.append(append);
                            sb.append("\n");
                        }
                        sb.append("}\n");
                    }
                    zza(sb, i4);
                    sb.append("}\n");
                }
            }
            if (zzc.zzh()) {
                zza(sb, i3 + 1, "number_filter", zzc.zzc());
            }
            zza(sb, i3);
            sb.append("}\n");
        }
    }

    private static void zza(StringBuilder sb, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            sb.append("  ");
        }
    }

    private static void zza(StringBuilder sb, int i3, String str, zzfn.zzd zzd) {
        if (zzd != null) {
            zza(sb, i3);
            sb.append(str);
            sb.append(" {\n");
            if (zzd.zzh()) {
                zza(sb, i3, "comparison_type", (Object) zzd.zza().name());
            }
            if (zzd.zzj()) {
                zza(sb, i3, "match_as_float", (Object) Boolean.valueOf(zzd.zzg()));
            }
            if (zzd.zzi()) {
                zza(sb, i3, "comparison_value", (Object) zzd.zzd());
            }
            if (zzd.zzl()) {
                zza(sb, i3, "min_comparison_value", (Object) zzd.zzf());
            }
            if (zzd.zzk()) {
                zza(sb, i3, "max_comparison_value", (Object) zzd.zze());
            }
            zza(sb, i3);
            sb.append("}\n");
        }
    }

    private static void zza(Uri.Builder builder, String str, String str2, Set<String> set) {
        if (!set.contains(str) && !TextUtils.isEmpty(str2)) {
            builder.appendQueryParameter(str, str2);
        }
    }

    private static void zza(StringBuilder sb, int i3, String str, Object obj) {
        if (obj != null) {
            zza(sb, i3 + 1);
            sb.append(str);
            sb.append(": ");
            sb.append(obj);
            sb.append(10);
        }
    }

    public final void zza(zzgn.zzk.zza zza) {
        zzj().zzp().zza("Checking account type status for ad personalization signals");
        if (zzc(zza.zzt())) {
            zzj().zzc().zza("Turning off ad personalization due to account type");
            zzgn.zzo zzo = (zzgn.zzo) ((zzlc) zzgn.zzo.zze().zza("_npa").zzb(zzf().zzc()).zza(1).zzai());
            int i3 = 0;
            while (true) {
                if (i3 >= zza.zzd()) {
                    zza.zza(zzo);
                    break;
                } else if ("_npa".equals(zza.zzk(i3).zzg())) {
                    zza.zza(i3, zzo);
                    break;
                } else {
                    i3++;
                }
            }
            zzai zza2 = zzai.zza(zza.zzv());
            zza2.zza(zzjc.zza.AD_PERSONALIZATION, zzal.CHILD_ACCOUNT);
            zza.zzf(zza2.toString());
        }
    }

    public final void zza(zzgn.zzh.zza zza, Object obj) {
        Preconditions.checkNotNull(obj);
        zza.zze().zzc().zzb().zzd();
        if (obj instanceof String) {
            zza.zzb((String) obj);
        } else if (obj instanceof Long) {
            zza.zza(((Long) obj).longValue());
        } else if (obj instanceof Double) {
            zza.zza(((Double) obj).doubleValue());
        } else if (obj instanceof Bundle[]) {
            ArrayList arrayList = new ArrayList();
            for (Bundle bundle : (Bundle[]) obj) {
                if (bundle != null) {
                    zzgn.zzh.zza zze = zzgn.zzh.zze();
                    for (String next : bundle.keySet()) {
                        zzgn.zzh.zza zza2 = zzgn.zzh.zze().zza(next);
                        Object obj2 = bundle.get(next);
                        if (obj2 instanceof Long) {
                            zza2.zza(((Long) obj2).longValue());
                        } else if (obj2 instanceof String) {
                            zza2.zzb((String) obj2);
                        } else if (obj2 instanceof Double) {
                            zza2.zza(((Double) obj2).doubleValue());
                        }
                        zze.zza(zza2);
                    }
                    if (zze.zza() > 0) {
                        arrayList.add((zzgn.zzh) ((zzlc) zze.zzai()));
                    }
                }
            }
            zza.zza((Iterable<? extends zzgn.zzh>) arrayList);
        } else {
            zzj().zzg().zza("Ignoring invalid (type) event param value", obj);
        }
    }

    public final void zza(zzgn.zzo.zza zza, Object obj) {
        Preconditions.checkNotNull(obj);
        zza.zzc().zzb().zza();
        if (obj instanceof String) {
            zza.zzb((String) obj);
        } else if (obj instanceof Long) {
            zza.zza(((Long) obj).longValue());
        } else if (obj instanceof Double) {
            zza.zza(((Double) obj).doubleValue());
        } else {
            zzj().zzg().zza("Ignoring invalid (type) user attribute value", obj);
        }
    }

    @WorkerThread
    public static boolean zza(zzbh zzbh, zzp zzp) {
        Preconditions.checkNotNull(zzbh);
        Preconditions.checkNotNull(zzp);
        return !TextUtils.isEmpty(zzp.zzb) || !TextUtils.isEmpty(zzp.zzp);
    }

    public static boolean zza(List<Long> list, int i3) {
        if (i3 >= (list.size() << 6)) {
            return false;
        }
        return ((1 << (i3 % 64)) & list.get(i3 / 64).longValue()) != 0;
    }

    public final boolean zza(long j2, long j3) {
        return j2 == 0 || j3 <= 0 || Math.abs(zzb().currentTimeMillis() - j2) > j3;
    }
}
