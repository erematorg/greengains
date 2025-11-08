package com.google.firebase.messaging;

import android.content.Intent;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.WithinAppServiceConnection;
import java.util.concurrent.ScheduledFuture;

public final /* synthetic */ class k implements Continuation, OnCompleteListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7144a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f7145b;

    public /* synthetic */ k(Object obj, int i3) {
        this.f7144a = i3;
        this.f7145b = obj;
    }

    public void onComplete(Task task) {
        int i3 = this.f7144a;
        Object obj = this.f7145b;
        switch (i3) {
            case 1:
                WakeLockHolder.completeWakefulIntent((Intent) obj);
                return;
            case 2:
                ((WithinAppServiceConnection.BindRequest) obj).finish();
                return;
            default:
                ((ScheduledFuture) obj).cancel(false);
                return;
        }
    }

    public Object then(Task task) {
        return ((GmsRpc) this.f7145b).lambda$extractResponseWhenComplete$0(task);
    }
}
