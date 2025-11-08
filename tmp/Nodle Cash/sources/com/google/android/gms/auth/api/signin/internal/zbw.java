package com.google.android.gms.auth.api.signin.internal;

import android.os.Bundle;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import com.google.android.gms.common.api.GoogleApiClient;

final class zbw implements LoaderManager.LoaderCallbacks {
    final /* synthetic */ SignInHubActivity zba;

    public /* synthetic */ zbw(SignInHubActivity signInHubActivity, zbv zbv) {
        this.zba = signInHubActivity;
    }

    public final Loader onCreateLoader(int i3, Bundle bundle) {
        return new zbc(this.zba, GoogleApiClient.getAllClients());
    }

    public final /* bridge */ /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
        Void voidR = (Void) obj;
        SignInHubActivity signInHubActivity = this.zba;
        signInHubActivity.setResult(signInHubActivity.zbe, signInHubActivity.zbf);
        this.zba.finish();
    }

    public final void onLoaderReset(Loader loader) {
    }
}
