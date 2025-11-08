package com.google.android.gms.common;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.errorprone.annotations.CheckReturnValue;
import com.google.errorprone.annotations.RestrictedInheritance;

@RestrictedInheritance(allowedOnPath = ".*javatests.*/com/google/android/gms/common/.*", explanation = "Sub classing of GMS Core's APIs are restricted to testing fakes.", link = "go/gmscore-restrictedinheritance")
@ShowFirstParty
@KeepForSdk
@CheckReturnValue
public class PackageSignatureVerifier {
    @VisibleForTesting
    @Nullable
    static volatile zzac zza;
    @Nullable
    private static zzad zzb;

    private static zzad zza(Context context) {
        zzad zzad;
        synchronized (PackageSignatureVerifier.class) {
            try {
                if (zzb == null) {
                    zzb = new zzad(context);
                }
                zzad = zzb;
            } catch (Throwable th) {
                throw th;
            }
        }
        return zzad;
    }

    @ShowFirstParty
    @NonNull
    @KeepForSdk
    public PackageVerificationResult queryPackageSignatureVerified(@NonNull Context context, @NonNull String str) {
        boolean honorsDebugCertificates = GooglePlayServicesUtilLight.honorsDebugCertificates(context);
        zza(context);
        if (zzn.zzf()) {
            String concat = String.valueOf(str).concat(true != honorsDebugCertificates ? "-0" : "-1");
            if (zza != null && zza.zza.equals(concat)) {
                return zza.zzb;
            }
            zza(context);
            zzx zzc = zzn.zzc(str, honorsDebugCertificates, false, false);
            if (zzc.zza) {
                zza = new zzac(concat, PackageVerificationResult.zzd(str, zzc.zzd));
                return zza.zzb;
            }
            Preconditions.checkNotNull(zzc.zzb);
            return PackageVerificationResult.zza(str, zzc.zzb, zzc.zzc);
        }
        throw new zzae();
    }

    @ShowFirstParty
    @NonNull
    @KeepForSdk
    public PackageVerificationResult queryPackageSignatureVerifiedWithRetry(@NonNull Context context, @NonNull String str) {
        try {
            PackageVerificationResult queryPackageSignatureVerified = queryPackageSignatureVerified(context, str);
            queryPackageSignatureVerified.zzb();
            return queryPackageSignatureVerified;
        } catch (SecurityException e3) {
            PackageVerificationResult queryPackageSignatureVerified2 = queryPackageSignatureVerified(context, str);
            if (!queryPackageSignatureVerified2.zzc()) {
                return queryPackageSignatureVerified2;
            }
            Log.e("PkgSignatureVerifier", "Got flaky result during package signature verification", e3);
            return queryPackageSignatureVerified2;
        }
    }
}
