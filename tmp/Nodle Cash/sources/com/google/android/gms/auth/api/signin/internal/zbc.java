package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.util.Log;
import androidx.loader.content.AsyncTaskLoader;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.SignInConnectionListener;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public final class zbc extends AsyncTaskLoader implements SignInConnectionListener {
    private final Semaphore zba = new Semaphore(0);
    private final Set zbb;

    public zbc(Context context, Set set) {
        super(context);
        this.zbb = set;
    }

    public final /* bridge */ /* synthetic */ Object loadInBackground() {
        int i3 = 0;
        for (GoogleApiClient maybeSignIn : this.zbb) {
            if (maybeSignIn.maybeSignIn(this)) {
                i3++;
            }
        }
        try {
            this.zba.tryAcquire(i3, 5, TimeUnit.SECONDS);
            return null;
        } catch (InterruptedException e3) {
            Log.i("GACSignInLoader", "Unexpected InterruptedException", e3);
            Thread.currentThread().interrupt();
            return null;
        }
    }

    public final void onComplete() {
        this.zba.release();
    }

    public final void onStartLoading() {
        this.zba.drainPermits();
        forceLoad();
    }
}
