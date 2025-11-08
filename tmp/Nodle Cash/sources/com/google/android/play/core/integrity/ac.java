package com.google.android.play.core.integrity;

import android.app.PendingIntent;
import android.os.Build;
import android.os.Bundle;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.play.integrity.internal.o;
import com.google.android.play.integrity.internal.q;
import com.google.firebase.messaging.Constants;
import org.apache.xerces.impl.xs.SchemaSymbols;

final class ac extends o {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ad f6726a;

    /* renamed from: b  reason: collision with root package name */
    private final q f6727b = new q("OnRequestIntegrityTokenCallback");

    /* renamed from: c  reason: collision with root package name */
    private final TaskCompletionSource f6728c;

    public ac(ad adVar, TaskCompletionSource taskCompletionSource) {
        this.f6726a = adVar;
        this.f6728c = taskCompletionSource;
    }

    public final void b(Bundle bundle) {
        this.f6726a.f6729a.v(this.f6728c);
        this.f6727b.c("onRequestIntegrityToken", new Object[0]);
        int i3 = bundle.getInt(Constants.IPC_BUNDLE_KEY_SEND_ERROR);
        if (i3 != 0) {
            this.f6728c.trySetException(new IntegrityServiceException(i3, (Throwable) null));
            return;
        }
        String string = bundle.getString(SchemaSymbols.ATTVAL_TOKEN);
        if (string == null) {
            this.f6728c.trySetException(new IntegrityServiceException(-100, (Throwable) null));
            return;
        }
        PendingIntent pendingIntent = Build.VERSION.SDK_INT >= 33 ? (PendingIntent) bundle.getParcelable("dialog.intent", PendingIntent.class) : (PendingIntent) bundle.getParcelable("dialog.intent");
        TaskCompletionSource taskCompletionSource = this.f6728c;
        a aVar = new a();
        aVar.c(string);
        aVar.b(this.f6727b);
        aVar.a(pendingIntent);
        taskCompletionSource.trySetResult(aVar.d());
    }
}
