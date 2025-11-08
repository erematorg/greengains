package com.google.android.gms.common.api.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

@KeepForSdk
public final class BackgroundDetector implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {
    private static final BackgroundDetector zza = new BackgroundDetector();
    private final AtomicBoolean zzb = new AtomicBoolean();
    private final AtomicBoolean zzc = new AtomicBoolean();
    @GuardedBy("instance")
    private final ArrayList zzd = new ArrayList();
    @GuardedBy("instance")
    private boolean zze = false;

    @KeepForSdk
    public interface BackgroundStateChangeListener {
        @KeepForSdk
        void onBackgroundStateChanged(boolean z2);
    }

    @KeepForSdk
    private BackgroundDetector() {
    }

    @NonNull
    @KeepForSdk
    public static BackgroundDetector getInstance() {
        return zza;
    }

    @KeepForSdk
    public static void initialize(@NonNull Application application) {
        BackgroundDetector backgroundDetector = zza;
        synchronized (backgroundDetector) {
            try {
                if (!backgroundDetector.zze) {
                    application.registerActivityLifecycleCallbacks(backgroundDetector);
                    application.registerComponentCallbacks(backgroundDetector);
                    backgroundDetector.zze = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private final void zza(boolean z2) {
        synchronized (zza) {
            try {
                Iterator it = this.zzd.iterator();
                while (it.hasNext()) {
                    ((BackgroundStateChangeListener) it.next()).onBackgroundStateChanged(z2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @KeepForSdk
    public void addListener(@NonNull BackgroundStateChangeListener backgroundStateChangeListener) {
        synchronized (zza) {
            this.zzd.add(backgroundStateChangeListener);
        }
    }

    @KeepForSdk
    public boolean isInBackground() {
        return this.zzb.get();
    }

    public final void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
        AtomicBoolean atomicBoolean = this.zzc;
        boolean compareAndSet = this.zzb.compareAndSet(true, false);
        atomicBoolean.set(true);
        if (compareAndSet) {
            zza(false);
        }
    }

    public final void onActivityDestroyed(@NonNull Activity activity) {
    }

    public final void onActivityPaused(@NonNull Activity activity) {
    }

    public final void onActivityResumed(@NonNull Activity activity) {
        AtomicBoolean atomicBoolean = this.zzc;
        boolean compareAndSet = this.zzb.compareAndSet(true, false);
        atomicBoolean.set(true);
        if (compareAndSet) {
            zza(false);
        }
    }

    public final void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
    }

    public final void onActivityStarted(@NonNull Activity activity) {
    }

    public final void onActivityStopped(@NonNull Activity activity) {
    }

    public final void onConfigurationChanged(@NonNull Configuration configuration) {
    }

    public final void onLowMemory() {
    }

    public final void onTrimMemory(int i3) {
        if (i3 == 20 && this.zzb.compareAndSet(false, true)) {
            this.zzc.set(true);
            zza(true);
        }
    }

    @TargetApi(16)
    @KeepForSdk
    public boolean readCurrentStateIfPossible(boolean z2) {
        if (!this.zzc.get()) {
            if (!PlatformVersion.isAtLeastJellyBean()) {
                return z2;
            }
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
            ActivityManager.getMyMemoryState(runningAppProcessInfo);
            if (!this.zzc.getAndSet(true) && runningAppProcessInfo.importance > 100) {
                this.zzb.set(true);
            }
        }
        return isInBackground();
    }
}
