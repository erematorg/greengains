package com.google.mlkit.common.sdkinternal;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.lang.ref.ReferenceQueue;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Cleaner {
    private final ReferenceQueue zza = new ReferenceQueue();
    private final Set zzb = Collections.synchronizedSet(new HashSet());

    public interface Cleanable {
        @KeepForSdk
        void clean();
    }

    private Cleaner() {
    }

    @NonNull
    @KeepForSdk
    public static Cleaner create() {
        Cleaner cleaner = new Cleaner();
        cleaner.register(cleaner, new zza());
        Thread thread = new Thread(new zzb(cleaner.zza, cleaner.zzb), "MlKitCleaner");
        thread.setDaemon(true);
        thread.start();
        return cleaner;
    }

    @NonNull
    @KeepForSdk
    public Cleanable register(@NonNull Object obj, @NonNull Runnable runnable) {
        zzd zzd = new zzd(obj, this.zza, this.zzb, runnable, (zzc) null);
        this.zzb.add(zzd);
        return zzd;
    }
}
