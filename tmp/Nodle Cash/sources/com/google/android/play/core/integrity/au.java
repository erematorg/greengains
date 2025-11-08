package com.google.android.play.core.integrity;

import android.app.PendingIntent;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.play.integrity.internal.q;
import com.google.firebase.messaging.Constants;
import org.apache.xerces.impl.xs.SchemaSymbols;

final class au extends at {

    /* renamed from: c  reason: collision with root package name */
    private final q f6758c = new q("OnRequestIntegrityTokenCallback");

    public au(ax axVar, TaskCompletionSource taskCompletionSource) {
        super(axVar, taskCompletionSource);
    }

    public final void c(Bundle bundle) throws RemoteException {
        super.c(bundle);
        this.f6758c.c("onRequestExpressIntegrityToken", new Object[0]);
        int i3 = bundle.getInt(Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        if (i3 != 0) {
            this.f6756a.trySetException(new StandardIntegrityException(i3, (Throwable) null));
            return;
        }
        PendingIntent pendingIntent = Build.VERSION.SDK_INT >= 33 ? (PendingIntent) bundle.getParcelable("dialog.intent", PendingIntent.class) : (PendingIntent) bundle.getParcelable("dialog.intent");
        TaskCompletionSource taskCompletionSource = this.f6756a;
        b bVar = new b();
        bVar.c(bundle.getString(SchemaSymbols.ATTVAL_TOKEN));
        bVar.b(this.f6758c);
        bVar.a(pendingIntent);
        taskCompletionSource.trySetResult(bVar.d());
    }
}
