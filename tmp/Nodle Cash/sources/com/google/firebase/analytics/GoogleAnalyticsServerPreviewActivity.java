package com.google.firebase.analytics;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.gms.internal.measurement.zzdv;

public class GoogleAnalyticsServerPreviewActivity extends Activity {
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        zzdv.zza((Context) this).zza(getIntent());
        finish();
    }
}
