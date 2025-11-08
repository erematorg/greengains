package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.support.v4.media.session.a;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import androidx.annotation.GuardedBy;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzqh;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.dataflow.qual.Pure;

public final class zzgi extends zzix {
    /* access modifiers changed from: private */
    public char zza = 0;
    /* access modifiers changed from: private */
    public long zzb = -1;
    @GuardedBy("this")
    private String zzc;
    private final zzgk zzd = new zzgk(this, 6, false, false);
    private final zzgk zze = new zzgk(this, 6, true, false);
    private final zzgk zzf = new zzgk(this, 6, false, true);
    private final zzgk zzg = new zzgk(this, 5, false, false);
    private final zzgk zzh = new zzgk(this, 5, true, false);
    private final zzgk zzi = new zzgk(this, 5, false, true);
    private final zzgk zzj = new zzgk(this, 4, false, false);
    private final zzgk zzk = new zzgk(this, 3, false, false);
    private final zzgk zzl = new zzgk(this, 2, false, false);

    public zzgi(zzhw zzhw) {
        super(zzhw);
    }

    @VisibleForTesting
    @EnsuresNonNull({"logTagDoNotUseDirectly"})
    private final String zzy() {
        String str;
        synchronized (this) {
            try {
                if (this.zzc == null) {
                    this.zzc = this.zzu.zzw() != null ? this.zzu.zzw() : "FA";
                }
                Preconditions.checkNotNull(this.zzc);
                str = this.zzc;
            } catch (Throwable th) {
                throw th;
            }
        }
        return str;
    }

    public final zzgk zzc() {
        return this.zzk;
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

    public final zzgk zzg() {
        return this.zzd;
    }

    public final zzgk zzh() {
        return this.zzf;
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

    public final zzgk zzm() {
        return this.zze;
    }

    public final zzgk zzn() {
        return this.zzj;
    }

    public final boolean zzo() {
        return false;
    }

    public final zzgk zzp() {
        return this.zzl;
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

    public final zzgk zzu() {
        return this.zzg;
    }

    public final zzgk zzv() {
        return this.zzi;
    }

    public final zzgk zzw() {
        return this.zzh;
    }

    public final String zzx() {
        Pair<String, Long> zza2;
        if (zzk().zzb == null || (zza2 = zzk().zzb.zza()) == null || zza2 == zzgu.zza) {
            return null;
        }
        return a.n(String.valueOf(zza2.second), ":", (String) zza2.first);
    }

    @Pure
    public final /* bridge */ /* synthetic */ Clock zzb() {
        return super.zzb();
    }

    @VisibleForTesting
    private static String zzb(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf != -1) {
            return str.substring(0, lastIndexOf);
        }
        if (!zzqh.zza() || !zzbj.zzcd.zza(null).booleanValue()) {
            return str;
        }
        return "";
    }

    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
    }

    public static Object zza(String str) {
        if (str == null) {
            return null;
        }
        return new zzgn(str);
    }

    public static String zza(boolean z2, String str, Object obj, Object obj2, Object obj3) {
        String str2 = "";
        if (str == null) {
            str = str2;
        }
        String zza2 = zza(z2, obj);
        String zza3 = zza(z2, obj2);
        String zza4 = zza(z2, obj3);
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
            str2 = ": ";
        }
        String str3 = ", ";
        if (!TextUtils.isEmpty(zza2)) {
            sb.append(str2);
            sb.append(zza2);
            str2 = str3;
        }
        if (!TextUtils.isEmpty(zza3)) {
            sb.append(str2);
            sb.append(zza3);
        } else {
            str3 = str2;
        }
        if (!TextUtils.isEmpty(zza4)) {
            sb.append(str3);
            sb.append(zza4);
        }
        return sb.toString();
    }

    @VisibleForTesting
    private static String zza(boolean z2, Object obj) {
        String className;
        String str = "";
        if (obj == null) {
            return str;
        }
        if (obj instanceof Integer) {
            obj = Long.valueOf((long) ((Integer) obj).intValue());
        }
        int i3 = 0;
        if (obj instanceof Long) {
            if (!z2) {
                return String.valueOf(obj);
            }
            Long l2 = (Long) obj;
            if (Math.abs(l2.longValue()) < 100) {
                return String.valueOf(obj);
            }
            if (String.valueOf(obj).charAt(0) == '-') {
                str = "-";
            }
            String valueOf = String.valueOf(Math.abs(l2.longValue()));
            long round = Math.round(Math.pow(10.0d, (double) (valueOf.length() - 1)));
            long round2 = Math.round(Math.pow(10.0d, (double) valueOf.length()) - 1.0d);
            return str + round + "..." + str + round2;
        } else if (obj instanceof Boolean) {
            return String.valueOf(obj);
        } else {
            if (obj instanceof Throwable) {
                Throwable th = (Throwable) obj;
                StringBuilder sb = new StringBuilder(z2 ? th.getClass().getName() : th.toString());
                String zzb2 = zzb(zzhw.class.getCanonicalName());
                StackTraceElement[] stackTrace = th.getStackTrace();
                int length = stackTrace.length;
                while (true) {
                    if (i3 >= length) {
                        break;
                    }
                    StackTraceElement stackTraceElement = stackTrace[i3];
                    if (!stackTraceElement.isNativeMethod() && (className = stackTraceElement.getClassName()) != null && zzb(className).equals(zzb2)) {
                        sb.append(": ");
                        sb.append(stackTraceElement);
                        break;
                    }
                    i3++;
                }
                return sb.toString();
            } else if (obj instanceof zzgn) {
                return ((zzgn) obj).zza;
            } else {
                if (z2) {
                    return "-";
                }
                return String.valueOf(obj);
            }
        }
    }

    public final void zza(int i3, boolean z2, boolean z3, String str, Object obj, Object obj2, Object obj3) {
        if (!z2 && zza(i3)) {
            zza(i3, zza(false, str, obj, obj2, obj3));
        }
        if (!z3 && i3 >= 5) {
            Preconditions.checkNotNull(str);
            zzhp zzo = this.zzu.zzo();
            if (zzo == null) {
                zza(6, "Scheduler not set. Not logging error/warn");
            } else if (!zzo.zzaf()) {
                zza(6, "Scheduler not initialized. Not logging error/warn");
            } else {
                if (i3 < 0) {
                    i3 = 0;
                }
                if (i3 >= 9) {
                    i3 = 8;
                }
                zzo.zzb((Runnable) new zzgl(this, i3, str, obj, obj2, obj3));
            }
        }
    }

    @VisibleForTesting
    public final void zza(int i3, String str) {
        Log.println(i3, zzy(), str);
    }

    @VisibleForTesting
    public final boolean zza(int i3) {
        return Log.isLoggable(zzy(), i3);
    }
}
