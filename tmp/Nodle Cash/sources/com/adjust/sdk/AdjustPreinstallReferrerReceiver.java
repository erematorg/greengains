package com.adjust.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AdjustPreinstallReferrerReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String stringExtra;
        if (intent != null && (stringExtra = intent.getStringExtra(Constants.EXTRA_SYSTEM_INSTALLER_REFERRER)) != null) {
            Adjust.getDefaultInstance().sendPreinstallReferrer(stringExtra, context);
        }
    }
}
