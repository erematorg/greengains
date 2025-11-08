package com.google.android.gms.common;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import android.os.StrictMode;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.zzaf;
import com.google.android.gms.common.internal.zzag;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.util.Hex;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.errorprone.annotations.CheckReturnValue;
import java.security.MessageDigest;

@CheckReturnValue
final class zzn {
    static final zzl zza = new zzf(zzj.zze("0\u0005È0\u0003° \u0003\u0002\u0001\u0002\u0002\u0014\u0010e\bsù/Qí"));
    static final zzl zzb = new zzg(zzj.zze("0\u0006\u00040\u0003ì \u0003\u0002\u0001\u0002\u0002\u0014\u0003£²­×árÊkì"));
    static final zzl zzc = new zzh(zzj.zze("0\u0004C0\u0003+ \u0003\u0002\u0001\u0002\u0002\t\u0000ÂàFdJ00"));
    static final zzl zzd = new zzi(zzj.zze("0\u0004¨0\u0003 \u0003\u0002\u0001\u0002\u0002\t\u0000Õ¸l}ÓNõ0"));
    private static volatile zzag zze;
    private static final Object zzf = new Object();
    private static Context zzg;

    public static zzx zza(String str, zzj zzj, boolean z2, boolean z3) {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            return zzh(str, zzj, z2, z3);
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }

    public static zzx zzb(String str, boolean z2, boolean z3, boolean z4) {
        return zzi(str, z2, false, false, true);
    }

    public static zzx zzc(String str, boolean z2, boolean z3, boolean z4) {
        return zzi(str, z2, false, false, false);
    }

    public static /* synthetic */ String zzd(boolean z2, String str, zzj zzj) throws Exception {
        String str2 = (z2 || !zzh(str, zzj, true, false).zza) ? "not allowed" : "debug cert rejected";
        MessageDigest zza2 = AndroidUtilsLight.zza("SHA-256");
        Preconditions.checkNotNull(zza2);
        String bytesToStringLowercase = Hex.bytesToStringLowercase(zza2.digest(zzj.zzf()));
        return str2 + ": pkg=" + str + ", sha256=" + bytesToStringLowercase + ", atk=" + z2 + ", ver=12451000.false";
    }

    public static synchronized void zze(Context context) {
        synchronized (zzn.class) {
            if (zzg != null) {
                Log.w("GoogleCertificates", "GoogleCertificates has been initialized already");
            } else if (context != null) {
                zzg = context.getApplicationContext();
            }
        }
    }

    public static boolean zzf() {
        boolean z2;
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            zzj();
            z2 = zze.zzg();
        } catch (RemoteException | DynamiteModule.LoadingException e3) {
            Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e3);
            z2 = false;
        } catch (Throwable th) {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            throw th;
        }
        StrictMode.setThreadPolicy(allowThreadDiskReads);
        return z2;
    }

    public static boolean zzg() {
        boolean z2;
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            zzj();
            z2 = zze.zzi();
        } catch (RemoteException | DynamiteModule.LoadingException e3) {
            Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e3);
            z2 = false;
        } catch (Throwable th) {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            throw th;
        }
        StrictMode.setThreadPolicy(allowThreadDiskReads);
        return z2;
    }

    private static zzx zzh(String str, zzj zzj, boolean z2, boolean z3) {
        try {
            zzj();
            Preconditions.checkNotNull(zzg);
            try {
                return zze.zzh(new zzs(str, zzj, z2, z3), ObjectWrapper.wrap(zzg.getPackageManager())) ? zzx.zzb() : new zzv(new zze(z2, str, zzj), (zzu) null);
            } catch (RemoteException e3) {
                Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e3);
                return zzx.zzd("module call", e3);
            }
        } catch (DynamiteModule.LoadingException e4) {
            Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e4);
            return zzx.zzd("module init: ".concat(String.valueOf(e4.getMessage())), e4);
        }
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [com.google.android.gms.dynamic.IObjectWrapper, android.os.IBinder] */
    private static zzx zzi(String str, boolean z2, boolean z3, boolean z4, boolean z5) {
        zzx zzx;
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            Preconditions.checkNotNull(zzg);
            try {
                zzj();
                zzo zzo = new zzo(str, z2, false, ObjectWrapper.wrap(zzg), false, true);
                zzq zze2 = z5 ? zze.zze(zzo) : zze.zzf(zzo);
                if (zze2.zzb()) {
                    zzx = zzx.zzf(zze2.zzc());
                } else {
                    String zza2 = zze2.zza();
                    PackageManager.NameNotFoundException nameNotFoundException = zze2.zzd() == 4 ? new PackageManager.NameNotFoundException() : null;
                    if (zza2 == null) {
                        zza2 = "error checking package certificate";
                    }
                    zzx = zzx.zzg(zze2.zzc(), zze2.zzd(), zza2, nameNotFoundException);
                }
            } catch (DynamiteModule.LoadingException e3) {
                Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e3);
                zzx = zzx.zzd("module init: ".concat(String.valueOf(e3.getMessage())), e3);
            }
        } catch (RemoteException e4) {
            Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e4);
            zzx = zzx.zzd("module call", e4);
        } catch (Throwable th) {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            throw th;
        }
        StrictMode.setThreadPolicy(allowThreadDiskReads);
        return zzx;
    }

    private static void zzj() throws DynamiteModule.LoadingException {
        if (zze == null) {
            Preconditions.checkNotNull(zzg);
            synchronized (zzf) {
                try {
                    if (zze == null) {
                        zze = zzaf.zzb(DynamiteModule.load(zzg, DynamiteModule.PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING, "com.google.android.gms.googlecertificates").instantiate("com.google.android.gms.common.GoogleCertificatesImpl"));
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }
}
