package com.google.android.gms.auth.api.proxy;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.ShowFirstParty;

@ShowFirstParty
@KeepForSdk
public interface ProxyApi {

    @ShowFirstParty
    @KeepForSdk
    public interface ProxyResult extends Result {
        @NonNull
        @KeepForSdk
        ProxyResponse getResponse();
    }

    @ShowFirstParty
    @KeepForSdk
    public interface SpatulaHeaderResult extends Result {
        @ShowFirstParty
        @NonNull
        @KeepForSdk
        String getSpatulaHeader();
    }

    @ShowFirstParty
    @NonNull
    @KeepForSdk
    @Deprecated
    PendingResult<SpatulaHeaderResult> getSpatulaHeader(@NonNull GoogleApiClient googleApiClient);

    @NonNull
    @KeepForSdk
    @Deprecated
    PendingResult<ProxyResult> performProxyRequest(@NonNull GoogleApiClient googleApiClient, @NonNull ProxyRequest proxyRequest);
}
