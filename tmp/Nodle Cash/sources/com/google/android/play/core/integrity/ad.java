package com.google.android.play.core.integrity;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Base64;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.android.play.integrity.internal.ac;
import com.google.android.play.integrity.internal.ag;
import com.google.android.play.integrity.internal.d;
import com.google.android.play.integrity.internal.q;
import com.google.android.play.integrity.internal.w;
import java.util.ArrayList;

final class ad {
    @VisibleForTesting
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    final ac f6729a;
    /* access modifiers changed from: private */

    /* renamed from: b  reason: collision with root package name */
    public final q f6730b;

    /* renamed from: c  reason: collision with root package name */
    private final String f6731c;

    public ad(Context context, q qVar) {
        this.f6731c = context.getPackageName();
        this.f6730b = qVar;
        if (!ag.a(context)) {
            qVar.a("Phonesky is not installed.", new Object[0]);
            this.f6729a = null;
            return;
        }
        this.f6729a = new ac(context, qVar, "IntegrityService", ae.f6732a, aa.f6720a, (w) null);
    }

    public static /* bridge */ /* synthetic */ Bundle a(ad adVar, byte[] bArr, Long l2, Parcelable parcelable) {
        Bundle bundle = new Bundle();
        bundle.putString("package.name", adVar.f6731c);
        bundle.putByteArray("nonce", bArr);
        bundle.putInt("playcore.integrity.version.major", 1);
        bundle.putInt("playcore.integrity.version.minor", 2);
        bundle.putInt("playcore.integrity.version.patch", 0);
        if (l2 != null) {
            bundle.putLong("cloud.prj", l2.longValue());
        }
        ArrayList arrayList = new ArrayList();
        d.b(3, arrayList);
        bundle.putParcelableArrayList("event_timestamps", new ArrayList(d.a(arrayList)));
        return bundle;
    }

    public final Task b(IntegrityTokenRequest integrityTokenRequest) {
        if (this.f6729a == null) {
            return Tasks.forException(new IntegrityServiceException(-2, (Throwable) null));
        }
        try {
            byte[] decode = Base64.decode(integrityTokenRequest.nonce(), 10);
            Long cloudProjectNumber = integrityTokenRequest.cloudProjectNumber();
            integrityTokenRequest.a();
            this.f6730b.c("requestIntegrityToken(%s)", integrityTokenRequest);
            TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
            this.f6729a.t(new ab(this, taskCompletionSource, decode, cloudProjectNumber, (Parcelable) null, taskCompletionSource, integrityTokenRequest), taskCompletionSource);
            return taskCompletionSource.getTask();
        } catch (IllegalArgumentException e3) {
            return Tasks.forException(new IntegrityServiceException(-13, e3));
        }
    }
}
