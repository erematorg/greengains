package com.google.firebase.messaging;

import com.google.firebase.messaging.WithinAppServiceConnection;

public final /* synthetic */ class o implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7153a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f7154b;

    public /* synthetic */ o(Object obj, int i3) {
        this.f7153a = i3;
        this.f7154b = obj;
    }

    public final void run() {
        int i3 = this.f7153a;
        Object obj = this.f7154b;
        switch (i3) {
            case 0:
                ((SharedPreferencesQueue) obj).syncState();
                return;
            default:
                ((WithinAppServiceConnection.BindRequest) obj).lambda$arrangeTimeout$0();
                return;
        }
    }
}
