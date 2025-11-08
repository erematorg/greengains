package com.google.android.gms.common.api.internal;

import A.a;
import android.util.Log;
import android.util.SparseArray;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import org.apache.commons.lang3.StringUtils;

public final class zak extends zap {
    private final SparseArray zad = new SparseArray();

    private zak(LifecycleFragment lifecycleFragment) {
        super(lifecycleFragment, GoogleApiAvailability.getInstance());
        this.mLifecycleFragment.addCallback("AutoManageHelper", this);
    }

    public static zak zaa(LifecycleActivity lifecycleActivity) {
        LifecycleFragment fragment = LifecycleCallback.getFragment(lifecycleActivity);
        zak zak = (zak) fragment.getCallbackOrNull("AutoManageHelper", zak.class);
        return zak != null ? zak : new zak(fragment);
    }

    @Nullable
    private final zaj zai(int i3) {
        if (this.zad.size() <= i3) {
            return null;
        }
        SparseArray sparseArray = this.zad;
        return (zaj) sparseArray.get(sparseArray.keyAt(i3));
    }

    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        for (int i3 = 0; i3 < this.zad.size(); i3++) {
            zaj zai = zai(i3);
            if (zai != null) {
                printWriter.append(str).append("GoogleApiClient #").print(zai.zaa);
                printWriter.println(":");
                zai.zab.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
            }
        }
    }

    public final void onStart() {
        super.onStart();
        SparseArray sparseArray = this.zad;
        boolean z2 = this.zaa;
        String valueOf = String.valueOf(sparseArray);
        Log.d("AutoManageHelper", "onStart " + z2 + StringUtils.SPACE + valueOf);
        if (this.zab.get() == null) {
            for (int i3 = 0; i3 < this.zad.size(); i3++) {
                zaj zai = zai(i3);
                if (zai != null) {
                    zai.zab.connect();
                }
            }
        }
    }

    public final void onStop() {
        super.onStop();
        for (int i3 = 0; i3 < this.zad.size(); i3++) {
            zaj zai = zai(i3);
            if (zai != null) {
                zai.zab.disconnect();
            }
        }
    }

    public final void zab(ConnectionResult connectionResult, int i3) {
        Log.w("AutoManageHelper", "Unresolved error while connecting client. Stopping auto-manage.");
        if (i3 < 0) {
            Log.wtf("AutoManageHelper", "AutoManageLifecycleHelper received onErrorResolutionFailed callback but no failing client ID is set", new Exception());
            return;
        }
        zaj zaj = (zaj) this.zad.get(i3);
        if (zaj != null) {
            zae(i3);
            GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = zaj.zac;
            if (onConnectionFailedListener != null) {
                onConnectionFailedListener.onConnectionFailed(connectionResult);
            }
        }
    }

    public final void zac() {
        for (int i3 = 0; i3 < this.zad.size(); i3++) {
            zaj zai = zai(i3);
            if (zai != null) {
                zai.zab.connect();
            }
        }
    }

    public final void zad(int i3, GoogleApiClient googleApiClient, @Nullable GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        Preconditions.checkNotNull(googleApiClient, "GoogleApiClient instance cannot be null");
        int indexOfKey = this.zad.indexOfKey(i3);
        Preconditions.checkState(indexOfKey < 0, a.k("Already managing a GoogleApiClient with id ", i3));
        zam zam = (zam) this.zab.get();
        boolean z2 = this.zaa;
        String valueOf = String.valueOf(zam);
        Log.d("AutoManageHelper", "starting AutoManage for client " + i3 + StringUtils.SPACE + z2 + StringUtils.SPACE + valueOf);
        zaj zaj = new zaj(this, i3, googleApiClient, onConnectionFailedListener);
        googleApiClient.registerConnectionFailedListener(zaj);
        this.zad.put(i3, zaj);
        if (this.zaa && zam == null) {
            Log.d("AutoManageHelper", "connecting ".concat(googleApiClient.toString()));
            googleApiClient.connect();
        }
    }

    public final void zae(int i3) {
        zaj zaj = (zaj) this.zad.get(i3);
        this.zad.remove(i3);
        if (zaj != null) {
            zaj.zab.unregisterConnectionFailedListener(zaj);
            zaj.zab.disconnect();
        }
    }
}
