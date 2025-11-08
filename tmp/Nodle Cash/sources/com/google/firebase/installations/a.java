package com.google.firebase.installations;

public final /* synthetic */ class a implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7118a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ FirebaseInstallations f7119b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ boolean f7120c;

    public /* synthetic */ a(FirebaseInstallations firebaseInstallations, boolean z2, int i3) {
        this.f7118a = i3;
        this.f7119b = firebaseInstallations;
        this.f7120c = z2;
    }

    public final void run() {
        switch (this.f7118a) {
            case 0:
                this.f7119b.lambda$doRegistrationOrRefresh$3(this.f7120c);
                return;
            default:
                this.f7119b.lambda$getToken$2(this.f7120c);
                return;
        }
    }
}
