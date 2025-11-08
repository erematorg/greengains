package com.google.android.gms.internal.auth;

import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse;
import java.io.UnsupportedEncodingException;

public abstract class zzbz extends FastSafeParcelableJsonResponse {
    @Nullable
    public final byte[] toByteArray() {
        try {
            return toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e3) {
            Log.e("AUTH", "Error serializing object.", e3);
            return null;
        }
    }
}
