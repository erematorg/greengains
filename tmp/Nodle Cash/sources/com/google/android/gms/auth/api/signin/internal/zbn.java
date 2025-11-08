package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public final class zbn {
    @Nullable
    private static zbn zbd;
    @VisibleForTesting
    final Storage zba;
    @VisibleForTesting
    @Nullable
    GoogleSignInAccount zbb;
    @VisibleForTesting
    @Nullable
    GoogleSignInOptions zbc;

    private zbn(Context context) {
        Storage instance = Storage.getInstance(context);
        this.zba = instance;
        this.zbb = instance.getSavedDefaultGoogleSignInAccount();
        this.zbc = instance.getSavedDefaultGoogleSignInOptions();
    }

    public static synchronized zbn zbc(@NonNull Context context) {
        zbn zbf;
        synchronized (zbn.class) {
            zbf = zbf(context.getApplicationContext());
        }
        return zbf;
    }

    private static synchronized zbn zbf(Context context) {
        synchronized (zbn.class) {
            zbn zbn = zbd;
            if (zbn != null) {
                return zbn;
            }
            zbn zbn2 = new zbn(context);
            zbd = zbn2;
            return zbn2;
        }
    }

    @Nullable
    public final synchronized GoogleSignInAccount zba() {
        return this.zbb;
    }

    @Nullable
    public final synchronized GoogleSignInOptions zbb() {
        return this.zbc;
    }

    public final synchronized void zbd() {
        this.zba.clear();
        this.zbb = null;
        this.zbc = null;
    }

    public final synchronized void zbe(GoogleSignInOptions googleSignInOptions, GoogleSignInAccount googleSignInAccount) {
        this.zba.saveDefaultGoogleSignInAccount(googleSignInAccount, googleSignInOptions);
        this.zbb = googleSignInAccount;
        this.zbc = googleSignInOptions;
    }
}
