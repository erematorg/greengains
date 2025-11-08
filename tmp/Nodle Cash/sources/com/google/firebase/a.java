package com.google.firebase;

import android.content.Context;
import com.google.firebase.heartbeatinfo.DefaultHeartBeatController;
import com.google.firebase.inject.Provider;

public final /* synthetic */ class a implements Provider {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7039a = 1;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Context f7040b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f7041c;

    public /* synthetic */ a(Context context, String str) {
        this.f7040b = context;
        this.f7041c = str;
    }

    public final Object get() {
        switch (this.f7039a) {
            case 0:
                return ((FirebaseApp) this.f7041c).lambda$new$0(this.f7040b);
            default:
                return DefaultHeartBeatController.lambda$new$2(this.f7040b, (String) this.f7041c);
        }
    }

    public /* synthetic */ a(FirebaseApp firebaseApp, Context context) {
        this.f7041c = firebaseApp;
        this.f7040b = context;
    }
}
