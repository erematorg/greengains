package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.internal.base.zau;
import java.util.concurrent.atomic.AtomicReference;

public abstract class zap extends LifecycleCallback implements DialogInterface.OnCancelListener {
    protected volatile boolean zaa;
    protected final AtomicReference zab = new AtomicReference((Object) null);
    protected final GoogleApiAvailability zac;
    private final Handler zad = new zau(Looper.getMainLooper());

    @VisibleForTesting
    public zap(LifecycleFragment lifecycleFragment, GoogleApiAvailability googleApiAvailability) {
        super(lifecycleFragment);
        this.zac = googleApiAvailability;
    }

    /* access modifiers changed from: private */
    public final void zaa(ConnectionResult connectionResult, int i3) {
        this.zab.set((Object) null);
        zab(connectionResult, i3);
    }

    /* access modifiers changed from: private */
    public final void zad() {
        this.zab.set((Object) null);
        zac();
    }

    private static final int zae(@Nullable zam zam) {
        if (zam == null) {
            return -1;
        }
        return zam.zaa();
    }

    public final void onActivityResult(int i3, int i4, Intent intent) {
        zam zam = (zam) this.zab.get();
        if (i3 != 1) {
            if (i3 == 2) {
                int isGooglePlayServicesAvailable = this.zac.isGooglePlayServicesAvailable(getActivity());
                if (isGooglePlayServicesAvailable == 0) {
                    zad();
                    return;
                } else if (zam == null) {
                    return;
                } else {
                    if (zam.zab().getErrorCode() == 18 && isGooglePlayServicesAvailable == 18) {
                        return;
                    }
                }
            }
        } else if (i4 == -1) {
            zad();
            return;
        } else if (i4 == 0) {
            if (zam != null) {
                int i5 = 13;
                if (intent != null) {
                    i5 = intent.getIntExtra("<<ResolutionFailureErrorDetail>>", 13);
                }
                zaa(new ConnectionResult(i5, (PendingIntent) null, zam.zab().toString()), zae(zam));
                return;
            }
            return;
        }
        if (zam != null) {
            zaa(zam.zab(), zam.zaa());
        }
    }

    public final void onCancel(DialogInterface dialogInterface) {
        zaa(new ConnectionResult(13, (PendingIntent) null), zae((zam) this.zab.get()));
    }

    public final void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.zab.set(bundle.getBoolean("resolving_error", false) ? new zam(new ConnectionResult(bundle.getInt("failed_status"), (PendingIntent) bundle.getParcelable("failed_resolution")), bundle.getInt("failed_client_id", -1)) : null);
        }
    }

    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        zam zam = (zam) this.zab.get();
        if (zam != null) {
            bundle.putBoolean("resolving_error", true);
            bundle.putInt("failed_client_id", zam.zaa());
            bundle.putInt("failed_status", zam.zab().getErrorCode());
            bundle.putParcelable("failed_resolution", zam.zab().getResolution());
        }
    }

    public void onStart() {
        super.onStart();
        this.zaa = true;
    }

    public void onStop() {
        super.onStop();
        this.zaa = false;
    }

    public abstract void zab(ConnectionResult connectionResult, int i3);

    public abstract void zac();

    public final void zah(ConnectionResult connectionResult, int i3) {
        AtomicReference atomicReference;
        zam zam = new zam(connectionResult, i3);
        do {
            atomicReference = this.zab;
            while (!atomicReference.compareAndSet((Object) null, zam)) {
                if (atomicReference.get() != null) {
                }
            }
            this.zad.post(new zao(this, zam));
            return;
        } while (atomicReference.get() == null);
    }
}
