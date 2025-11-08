package com.google.android.play.core.integrity;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.play.integrity.internal.ac;
import com.google.android.play.integrity.internal.d;
import com.google.android.play.integrity.internal.q;
import com.google.android.play.integrity.internal.w;
import java.util.ArrayList;

final class ax {
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    final ac f6761a;
    /* access modifiers changed from: private */

    /* renamed from: b  reason: collision with root package name */
    public final q f6762b;

    /* renamed from: c  reason: collision with root package name */
    private final String f6763c;
    /* access modifiers changed from: private */

    /* renamed from: d  reason: collision with root package name */
    public final TaskCompletionSource f6764d;

    public ax(Context context, q qVar) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.f6764d = taskCompletionSource;
        this.f6763c = context.getPackageName();
        this.f6762b = qVar;
        ac acVar = new ac(context, qVar, "ExpressIntegrityService", ay.f6765a, ap.f6745a, (w) null);
        this.f6761a = acVar;
        acVar.c().post(new aq(this, taskCompletionSource, context));
    }

    public static /* bridge */ /* synthetic */ Bundle a(ax axVar, String str, long j2, long j3) {
        Bundle bundle = new Bundle();
        bundle.putString("package.name", axVar.f6763c);
        bundle.putLong("cloud.prj", j2);
        bundle.putString("nonce", str);
        bundle.putLong("warm.up.sid", j3);
        ArrayList arrayList = new ArrayList();
        d.b(5, arrayList);
        bundle.putParcelableArrayList("event_timestamps", new ArrayList(d.a(arrayList)));
        return bundle;
    }

    public static /* bridge */ /* synthetic */ Bundle b(ax axVar, long j2) {
        Bundle bundle = new Bundle();
        bundle.putString("package.name", axVar.f6763c);
        bundle.putLong("cloud.prj", j2);
        ArrayList arrayList = new ArrayList();
        d.b(4, arrayList);
        bundle.putParcelableArrayList("event_timestamps", new ArrayList(d.a(arrayList)));
        return bundle;
    }

    public static /* bridge */ /* synthetic */ boolean g(ax axVar) {
        return axVar.f6764d.getTask().isSuccessful() && !((Boolean) axVar.f6764d.getTask().getResult()).booleanValue();
    }

    public final Task c(@Nullable String str, long j2, long j3) {
        this.f6762b.c("requestExpressIntegrityToken(%s)", Long.valueOf(j3));
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.f6761a.t(new as(this, taskCompletionSource, str, j2, j3, taskCompletionSource), taskCompletionSource);
        return taskCompletionSource.getTask();
    }

    public final Task d(long j2) {
        this.f6762b.c("warmUpIntegrityToken(%s)", Long.valueOf(j2));
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.f6761a.t(new ar(this, taskCompletionSource, j2, taskCompletionSource), taskCompletionSource);
        return taskCompletionSource.getTask();
    }
}
