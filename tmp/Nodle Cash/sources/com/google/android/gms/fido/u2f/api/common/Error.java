package com.google.android.gms.fido.u2f.api.common;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.camera.camera2.internal.C0118y;
import java.util.Locale;
import org.apache.commons.text.StringSubstitutor;
import org.json.JSONException;
import org.json.JSONObject;

@Deprecated
public class Error {
    @VisibleForTesting
    @NonNull
    public static final String JSON_ERROR_CODE = "errorCode";
    @VisibleForTesting
    @NonNull
    public static final String JSON_ERROR_MESSAGE = "errorMessage";
    private final ErrorCode zza;
    private final String zzb;

    public Error(@NonNull ErrorCode errorCode) {
        this.zza = errorCode;
        this.zzb = null;
    }

    @NonNull
    public ErrorCode getErrorCode() {
        return this.zza;
    }

    @NonNull
    public String getErrorMessage() {
        return this.zzb;
    }

    @NonNull
    public JSONObject toJsonObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("errorCode", this.zza.getCode());
            String str = this.zzb;
            if (str != null) {
                jSONObject.put("errorMessage", str);
            }
            return jSONObject;
        } catch (JSONException e3) {
            throw new RuntimeException(e3);
        }
    }

    @NonNull
    public String toString() {
        if (this.zzb == null) {
            Locale locale = Locale.ENGLISH;
            return C0118y.c(this.zza.getCode(), "{errorCode: ", StringSubstitutor.DEFAULT_VAR_END);
        }
        Locale locale2 = Locale.ENGLISH;
        int code = this.zza.getCode();
        String str = this.zzb;
        return "{errorCode: " + code + ", errorMessage: " + str + StringSubstitutor.DEFAULT_VAR_END;
    }

    public Error(@NonNull ErrorCode errorCode, @NonNull String str) {
        this.zza = errorCode;
        this.zzb = str;
    }
}
