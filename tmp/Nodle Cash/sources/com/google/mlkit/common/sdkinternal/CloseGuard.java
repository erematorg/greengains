package com.google.mlkit.common.sdkinternal;

import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.mlkit_common.zzmm;
import com.google.android.gms.internal.mlkit_common.zzmn;
import com.google.android.gms.internal.mlkit_common.zzmv;
import com.google.android.gms.internal.mlkit_common.zzmw;
import com.google.android.gms.internal.mlkit_common.zzsh;
import com.google.android.gms.internal.mlkit_common.zzsk;
import com.google.android.gms.internal.mlkit_common.zzss;
import com.google.mlkit.common.sdkinternal.Cleaner;
import java.io.Closeable;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

@KeepForSdk
public class CloseGuard implements Closeable {
    @KeepForSdk
    public static final int API_TRANSLATE = 1;
    private final AtomicBoolean zza = new AtomicBoolean();
    private final String zzb;
    private final Cleaner.Cleanable zzc;

    @KeepForSdk
    public static class Factory {
        private final Cleaner zza;

        public Factory(@NonNull Cleaner cleaner) {
            this.zza = cleaner;
        }

        @NonNull
        @KeepForSdk
        public CloseGuard create(@NonNull Object obj, int i3, @NonNull Runnable runnable) {
            return new CloseGuard(obj, i3, this.zza, runnable, zzss.zzb("common"));
        }
    }

    public CloseGuard(Object obj, int i3, Cleaner cleaner, Runnable runnable, zzsh zzsh) {
        this.zzb = obj.toString();
        this.zzc = cleaner.register(obj, new zze(this, i3, zzsh, runnable));
    }

    public final void close() {
        this.zza.set(true);
        this.zzc.clean();
    }

    public final /* synthetic */ void zza(int i3, zzsh zzsh, Runnable runnable) {
        if (!this.zza.get()) {
            String str = this.zzb;
            Locale locale = Locale.ENGLISH;
            Log.e("MlKitCloseGuard", str + " has not been closed");
            zzmw zzmw = new zzmw();
            zzmn zzmn = new zzmn();
            zzmn.zzb(zzmm.zzb(i3));
            zzmw.zzh(zzmn.zzc());
            zzsh.zzd(zzsk.zzf(zzmw), zzmv.HANDLE_LEAKED);
        }
        runnable.run();
    }
}
