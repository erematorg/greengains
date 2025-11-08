package com.appsamurai.storyly.exoplayer2.core;

import android.content.Context;
import android.net.wifi.WifiManager;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.util.Log;

final class WifiLockManager {
    private static final String TAG = "WifiLockManager";
    private static final String WIFI_LOCK_TAG = "ExoPlayer:WifiLockManager";
    private boolean enabled;
    private boolean stayAwake;
    @Nullable
    private WifiManager.WifiLock wifiLock;
    @Nullable
    private final WifiManager wifiManager;

    public WifiLockManager(Context context) {
        this.wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
    }

    private void updateWifiLock() {
        WifiManager.WifiLock wifiLock2 = this.wifiLock;
        if (wifiLock2 != null) {
            if (!this.enabled || !this.stayAwake) {
                wifiLock2.release();
            } else {
                wifiLock2.acquire();
            }
        }
    }

    public void setEnabled(boolean z2) {
        if (z2 && this.wifiLock == null) {
            WifiManager wifiManager2 = this.wifiManager;
            if (wifiManager2 == null) {
                Log.w(TAG, "WifiManager is null, therefore not creating the WifiLock.");
                return;
            }
            WifiManager.WifiLock createWifiLock = wifiManager2.createWifiLock(3, WIFI_LOCK_TAG);
            this.wifiLock = createWifiLock;
            createWifiLock.setReferenceCounted(false);
        }
        this.enabled = z2;
        updateWifiLock();
    }

    public void setStayAwake(boolean z2) {
        this.stayAwake = z2;
        updateWifiLock();
    }
}
