package com.google.android.play.core.integrity;

import android.content.Context;
import androidx.annotation.NonNull;

public class IntegrityManagerFactory {
    private IntegrityManagerFactory() {
    }

    @NonNull
    public static IntegrityManager create(Context context) {
        return v.a(context).a();
    }

    @NonNull
    public static StandardIntegrityManager createStandard(Context context) {
        return aj.a(context).a();
    }
}
