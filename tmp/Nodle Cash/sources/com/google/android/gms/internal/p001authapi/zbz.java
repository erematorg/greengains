package com.google.android.gms.internal.p001authapi;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.auth.api.identity.AuthorizationClient;
import com.google.android.gms.auth.api.identity.AuthorizationRequest;
import com.google.android.gms.auth.api.identity.AuthorizationResult;
import com.google.android.gms.auth.api.identity.zbc;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.tasks.Task;

/* renamed from: com.google.android.gms.internal.auth-api.zbz  reason: invalid package */
public final class zbz extends GoogleApi implements AuthorizationClient {
    private static final Api.ClientKey zba;
    private static final Api.AbstractClientBuilder zbb;
    private static final Api zbc;

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        zba = clientKey;
        zbx zbx = new zbx();
        zbb = zbx;
        zbc = new Api("Auth.Api.Identity.Authorization.API", zbx, clientKey);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zbz(@androidx.annotation.NonNull android.app.Activity r3, @androidx.annotation.NonNull com.google.android.gms.auth.api.identity.zbc r4) {
        /*
            r2 = this;
            com.google.android.gms.common.api.Api r0 = zbc
            com.google.android.gms.auth.api.identity.zbb r4 = com.google.android.gms.auth.api.identity.zbb.zbc(r4)
            java.lang.String r1 = com.google.android.gms.internal.p001authapi.zbas.zba()
            r4.zba(r1)
            com.google.android.gms.auth.api.identity.zbc r4 = r4.zbb()
            com.google.android.gms.common.api.GoogleApi$Settings r1 = com.google.android.gms.common.api.GoogleApi.Settings.DEFAULT_SETTINGS
            r2.<init>((android.app.Activity) r3, r0, r4, (com.google.android.gms.common.api.GoogleApi.Settings) r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p001authapi.zbz.<init>(android.app.Activity, com.google.android.gms.auth.api.identity.zbc):void");
    }

    public final Task<AuthorizationResult> authorize(@NonNull AuthorizationRequest authorizationRequest) {
        Preconditions.checkNotNull(authorizationRequest);
        AuthorizationRequest.Builder zba2 = AuthorizationRequest.zba(authorizationRequest);
        zba2.zbb(((zbc) getApiOptions()).zbb());
        return doRead(TaskApiCall.builder().setFeatures(zbar.zbc).run(new zbw(this, zba2.build())).setAutoResolveMissingFeatures(false).setMethodKey(1534).build());
    }

    public final AuthorizationResult getAuthorizationResultFromIntent(@Nullable Intent intent) throws ApiException {
        if (intent != null) {
            Status status = (Status) SafeParcelableSerializer.deserializeFromIntentExtra(intent, NotificationCompat.CATEGORY_STATUS, Status.CREATOR);
            if (status == null) {
                throw new ApiException(Status.RESULT_CANCELED);
            } else if (status.isSuccess()) {
                AuthorizationResult authorizationResult = (AuthorizationResult) SafeParcelableSerializer.deserializeFromIntentExtra(intent, "authorization_result", AuthorizationResult.CREATOR);
                if (authorizationResult != null) {
                    return authorizationResult;
                }
                throw new ApiException(Status.RESULT_INTERNAL_ERROR);
            } else {
                throw new ApiException(status);
            }
        } else {
            throw new ApiException(Status.RESULT_INTERNAL_ERROR);
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zbz(@androidx.annotation.NonNull android.content.Context r3, @androidx.annotation.NonNull com.google.android.gms.auth.api.identity.zbc r4) {
        /*
            r2 = this;
            com.google.android.gms.common.api.Api r0 = zbc
            com.google.android.gms.auth.api.identity.zbb r4 = com.google.android.gms.auth.api.identity.zbb.zbc(r4)
            java.lang.String r1 = com.google.android.gms.internal.p001authapi.zbas.zba()
            r4.zba(r1)
            com.google.android.gms.auth.api.identity.zbc r4 = r4.zbb()
            com.google.android.gms.common.api.GoogleApi$Settings r1 = com.google.android.gms.common.api.GoogleApi.Settings.DEFAULT_SETTINGS
            r2.<init>((android.content.Context) r3, r0, r4, (com.google.android.gms.common.api.GoogleApi.Settings) r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p001authapi.zbz.<init>(android.content.Context, com.google.android.gms.auth.api.identity.zbc):void");
    }
}
