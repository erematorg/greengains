package com.google.android.play.integrity.internal;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public final class ac {

    /* renamed from: a  reason: collision with root package name */
    private static final Map f6813a = new HashMap();
    /* access modifiers changed from: private */

    /* renamed from: b  reason: collision with root package name */
    public final Context f6814b;
    /* access modifiers changed from: private */

    /* renamed from: c  reason: collision with root package name */
    public final q f6815c;

    /* renamed from: d  reason: collision with root package name */
    private final String f6816d;
    /* access modifiers changed from: private */

    /* renamed from: e  reason: collision with root package name */
    public final List f6817e = new ArrayList();
    @GuardedBy("attachedRemoteTasksLock")

    /* renamed from: f  reason: collision with root package name */
    private final Set f6818f = new HashSet();
    /* access modifiers changed from: private */

    /* renamed from: g  reason: collision with root package name */
    public final Object f6819g = new Object();
    /* access modifiers changed from: private */

    /* renamed from: h  reason: collision with root package name */
    public boolean f6820h;

    /* renamed from: i  reason: collision with root package name */
    private final Intent f6821i;
    /* access modifiers changed from: private */

    /* renamed from: j  reason: collision with root package name */
    public final x f6822j;

    /* renamed from: k  reason: collision with root package name */
    private final WeakReference f6823k;

    /* renamed from: l  reason: collision with root package name */
    private final IBinder.DeathRecipient f6824l = new t(this);
    /* access modifiers changed from: private */
    @GuardedBy("attachedRemoteTasksLock")

    /* renamed from: m  reason: collision with root package name */
    public final AtomicInteger f6825m = new AtomicInteger(0);
    /* access modifiers changed from: private */
    @Nullable

    /* renamed from: n  reason: collision with root package name */
    public ServiceConnection f6826n;
    /* access modifiers changed from: private */
    @Nullable

    /* renamed from: o  reason: collision with root package name */
    public IInterface f6827o;

    public ac(Context context, q qVar, String str, Intent intent, x xVar, @Nullable w wVar) {
        this.f6814b = context;
        this.f6815c = qVar;
        this.f6816d = str;
        this.f6821i = intent;
        this.f6822j = xVar;
        this.f6823k = new WeakReference((Object) null);
    }

    public static /* synthetic */ void k(ac acVar) {
        acVar.f6815c.c("reportBinderDeath", new Object[0]);
        w wVar = (w) acVar.f6823k.get();
        if (wVar != null) {
            acVar.f6815c.c("calling onBinderDied", new Object[0]);
            wVar.a();
        } else {
            acVar.f6815c.c("%s : Binder has died.", acVar.f6816d);
            for (r a2 : acVar.f6817e) {
                a2.a(acVar.w());
            }
            acVar.f6817e.clear();
        }
        synchronized (acVar.f6819g) {
            acVar.x();
        }
    }

    public static /* bridge */ /* synthetic */ void o(ac acVar, TaskCompletionSource taskCompletionSource) {
        acVar.f6818f.add(taskCompletionSource);
        taskCompletionSource.getTask().addOnCompleteListener(new s(acVar, taskCompletionSource));
    }

    public static /* bridge */ /* synthetic */ void q(ac acVar, r rVar) {
        if (acVar.f6827o == null && !acVar.f6820h) {
            acVar.f6815c.c("Initiate binding to the service.", new Object[0]);
            acVar.f6817e.add(rVar);
            ab abVar = new ab(acVar, (aa) null);
            acVar.f6826n = abVar;
            acVar.f6820h = true;
            if (!acVar.f6814b.bindService(acVar.f6821i, abVar, 1)) {
                acVar.f6815c.c("Failed to bind to the service.", new Object[0]);
                acVar.f6820h = false;
                for (r a2 : acVar.f6817e) {
                    a2.a(new ad());
                }
                acVar.f6817e.clear();
            }
        } else if (acVar.f6820h) {
            acVar.f6815c.c("Waiting to bind to the service.", new Object[0]);
            acVar.f6817e.add(rVar);
        } else {
            rVar.run();
        }
    }

    public static /* bridge */ /* synthetic */ void r(ac acVar) {
        acVar.f6815c.c("linkToDeath", new Object[0]);
        try {
            acVar.f6827o.asBinder().linkToDeath(acVar.f6824l, 0);
        } catch (RemoteException e3) {
            acVar.f6815c.b(e3, "linkToDeath failed", new Object[0]);
        }
    }

    public static /* bridge */ /* synthetic */ void s(ac acVar) {
        acVar.f6815c.c("unlinkToDeath", new Object[0]);
        acVar.f6827o.asBinder().unlinkToDeath(acVar.f6824l, 0);
    }

    private final RemoteException w() {
        return new RemoteException(String.valueOf(this.f6816d).concat(" : Binder has died."));
    }

    /* access modifiers changed from: private */
    @GuardedBy("attachedRemoteTasksLock")
    public final void x() {
        for (TaskCompletionSource trySetException : this.f6818f) {
            trySetException.trySetException(w());
        }
        this.f6818f.clear();
    }

    public final Handler c() {
        Handler handler;
        Map map = f6813a;
        synchronized (map) {
            try {
                if (!map.containsKey(this.f6816d)) {
                    HandlerThread handlerThread = new HandlerThread(this.f6816d, 10);
                    handlerThread.start();
                    map.put(this.f6816d, new Handler(handlerThread.getLooper()));
                }
                handler = (Handler) map.get(this.f6816d);
            } catch (Throwable th) {
                throw th;
            }
        }
        return handler;
    }

    @Nullable
    public final IInterface e() {
        return this.f6827o;
    }

    public final void t(r rVar, @Nullable TaskCompletionSource taskCompletionSource) {
        c().post(new u(this, rVar.c(), taskCompletionSource, rVar));
    }

    public final /* synthetic */ void u(TaskCompletionSource taskCompletionSource, Task task) {
        synchronized (this.f6819g) {
            this.f6818f.remove(taskCompletionSource);
        }
    }

    public final void v(TaskCompletionSource taskCompletionSource) {
        synchronized (this.f6819g) {
            this.f6818f.remove(taskCompletionSource);
        }
        c().post(new v(this));
    }
}
