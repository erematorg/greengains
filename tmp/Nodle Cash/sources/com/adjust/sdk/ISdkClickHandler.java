package com.adjust.sdk;

import com.adjust.sdk.network.IActivityPackageSender;

public interface ISdkClickHandler {
    void init(IActivityHandler iActivityHandler, boolean z2, IActivityPackageSender iActivityPackageSender);

    void pauseSending();

    void resumeSending();

    void sendPreinstallPayload(String str, String str2);

    void sendReftagReferrers();

    void sendSdkClick(ActivityPackage activityPackage);

    void teardown();
}
