package com.google.android.gms.cloudmessaging;

import android.os.Bundle;
import android.support.v4.media.session.a;
import android.util.Log;
import com.google.android.gms.tasks.TaskCompletionSource;
import org.apache.commons.text.StringSubstitutor;

abstract class zzs {
    final int zza;
    final TaskCompletionSource zzb = new TaskCompletionSource();
    final int zzc;
    final Bundle zzd;

    public zzs(int i3, int i4, Bundle bundle) {
        this.zza = i3;
        this.zzc = i4;
        this.zzd = bundle;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Request { what=");
        sb.append(this.zzc);
        sb.append(" id=");
        sb.append(this.zza);
        sb.append(" oneWay=");
        return a.s(sb, zzb(), StringSubstitutor.DEFAULT_VAR_END);
    }

    public abstract void zza(Bundle bundle);

    public abstract boolean zzb();

    public final void zzc(zzt zzt) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String zzs = toString();
            String obj = zzt.toString();
            Log.d("MessengerIpcClient", "Failing " + zzs + " with " + obj);
        }
        this.zzb.setException(zzt);
    }

    public final void zzd(Object obj) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String zzs = toString();
            String valueOf = String.valueOf(obj);
            Log.d("MessengerIpcClient", "Finishing " + zzs + " with " + valueOf);
        }
        this.zzb.setResult(obj);
    }
}
