package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.support.v4.media.session.a;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.Preconditions;

public final class zae extends zai {
    protected final BaseImplementation.ApiMethodImpl zaa;

    public zae(int i3, BaseImplementation.ApiMethodImpl apiMethodImpl) {
        super(i3);
        this.zaa = (BaseImplementation.ApiMethodImpl) Preconditions.checkNotNull(apiMethodImpl, "Null methods are not runnable.");
    }

    public final void zad(@NonNull Status status) {
        try {
            this.zaa.setFailedResult(status);
        } catch (IllegalStateException e3) {
            Log.w("ApiCallRunner", "Exception reporting failure", e3);
        }
    }

    public final void zae(@NonNull Exception exc) {
        try {
            this.zaa.setFailedResult(new Status(10, a.n(exc.getClass().getSimpleName(), ": ", exc.getLocalizedMessage())));
        } catch (IllegalStateException e3) {
            Log.w("ApiCallRunner", "Exception reporting failure", e3);
        }
    }

    public final void zaf(zabq zabq) throws DeadObjectException {
        try {
            this.zaa.run(zabq.zaf());
        } catch (RuntimeException e3) {
            zae(e3);
        }
    }

    public final void zag(@NonNull zaad zaad, boolean z2) {
        zaad.zac(this.zaa, z2);
    }
}
