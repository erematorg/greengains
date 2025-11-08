package com.google.android.gms.common.moduleinstall;

import androidx.annotation.NonNull;

public interface InstallStatusListener {
    void onInstallStatusUpdated(@NonNull ModuleInstallStatusUpdate moduleInstallStatusUpdate);
}
