package com.reown.android.verify.client;

import kotlin.jvm.functions.Function0;

public final /* synthetic */ class a implements Function0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7429a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ VerifyClient f7430b;

    public /* synthetic */ a(VerifyClient verifyClient, int i3) {
        this.f7429a = i3;
        this.f7430b = verifyClient;
    }

    public final Object invoke() {
        int i3 = this.f7429a;
        VerifyClient verifyClient = this.f7430b;
        switch (i3) {
            case 0:
                return VerifyClient.verifyRepository_delegate$lambda$0(verifyClient);
            default:
                return VerifyClient.pairingController_delegate$lambda$1(verifyClient);
        }
    }
}
