package com.appsamurai.storyly.exoplayer2.core;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.PowerManager;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.util.Log;

final class WakeLockManager {
    private static final String TAG = "WakeLockManager";
    private static final String WAKE_LOCK_TAG = "ExoPlayer:WakeLockManager";
    private boolean enabled;
    @Nullable
    private final PowerManager powerManager;
    private boolean stayAwake;
    @Nullable
    private PowerManager.WakeLock wakeLock;

    public WakeLockManager(Context context) {
        this.powerManager = (PowerManager) context.getApplicationContext().getSystemService("power");
    }

    @SuppressLint({"WakelockTimeout"})
    private void updateWakeLock() {
        PowerManager.WakeLock wakeLock2 = this.wakeLock;
        if (wakeLock2 != null) {
            if (!this.enabled || !this.stayAwake) {
                wakeLock2.release();
            } else {
                wakeLock2.acquire();
            }
        }
    }

    public void setEnabled(boolean z2) {
        if (z2 && this.wakeLock == null) {
            PowerManager powerManager2 = this.powerManager;
            if (powerManager2 == null) {
                Log.w(TAG, "PowerManager is null, therefore not creating the WakeLock.");
                return;
            }
            PowerManager.WakeLock newWakeLock = powerManager2.newWakeLock(1, WAKE_LOCK_TAG);
            this.wakeLock = newWakeLock;
            newWakeLock.setReferenceCounted(false);
        }
        this.enabled = z2;
        updateWakeLock();
    }

    public void setStayAwake(boolean z2) {
        this.stayAwake = z2;
        updateWakeLock();
    }
}
