package com.google.android.gms.location;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.internal.location.zzaf;
import com.google.android.gms.internal.location.zzaj;

public class ActivityRecognition {
    @NonNull
    @Deprecated
    public static final Api<Api.ApiOptions.NoOptions> API = zzaj.zzb;
    @NonNull
    @Deprecated
    public static final ActivityRecognitionApi ActivityRecognitionApi = new zzaf();

    private ActivityRecognition() {
    }

    @NonNull
    public static ActivityRecognitionClient getClient(@NonNull Activity activity) {
        return new zzaj(activity);
    }

    @NonNull
    public static ActivityRecognitionClient getClient(@NonNull Context context) {
        return new zzaj(context);
    }
}
