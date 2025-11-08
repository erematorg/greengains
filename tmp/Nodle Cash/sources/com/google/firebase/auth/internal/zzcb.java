package com.google.firebase.auth.internal;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public final class zzcb {
    private static final zzcb zza = new zzcb();
    private final zzbi zzb;
    private final zzau zzc;

    private zzcb() {
        this(zzbi.zzc(), zzau.zza());
    }

    public static zzcb zzc() {
        return zza;
    }

    public final Task<AuthResult> zza() {
        return this.zzb.zza();
    }

    public final Task<String> zzb() {
        return this.zzb.zzb();
    }

    @VisibleForTesting
    private zzcb(zzbi zzbi, zzau zzau) {
        this.zzb = zzbi;
        this.zzc = zzau;
    }

    public final void zza(Context context) {
        this.zzb.zza(context);
    }

    public final void zza(FirebaseAuth firebaseAuth) {
        this.zzb.zza(firebaseAuth);
    }

    public final boolean zza(Activity activity, TaskCompletionSource<AuthResult> taskCompletionSource, FirebaseAuth firebaseAuth) {
        return this.zzc.zza(activity, taskCompletionSource, firebaseAuth);
    }

    public final boolean zza(Activity activity, TaskCompletionSource<AuthResult> taskCompletionSource, FirebaseAuth firebaseAuth, FirebaseUser firebaseUser) {
        return this.zzc.zza(activity, taskCompletionSource, firebaseAuth, firebaseUser);
    }
}
