package com.reown.walletkit.client;

import com.reown.android.Core;
import com.reown.sign.client.Sign;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class a implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7638a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Function1 f7639b;

    public /* synthetic */ a(Function1 function1, int i3) {
        this.f7638a = i3;
        this.f7639b = function1;
    }

    public final Object invoke(Object obj) {
        int i3 = this.f7638a;
        Function1 function1 = this.f7639b;
        switch (i3) {
            case 0:
                return WalletKit$decryptMessage$1.invokeSuspend$lambda$0(function1, (Sign.Model.Message) obj);
            case 1:
                return WalletKit$decryptMessage$1.invokeSuspend$lambda$1(function1, (Sign.Model.Error) obj);
            case 2:
                return WalletKit$dispatchEnvelope$1.invokeSuspend$lambda$0(function1, (Sign.Model.Error) obj);
            case 3:
                return WalletKit.registerDeviceToken$lambda$2(function1, (Throwable) obj);
            case 4:
                return WalletKit.approveSession$lambda$9(function1, (Sign.Model.Error) obj);
            case 5:
                return WalletKit.approveSessionAuthenticate$lambda$15(function1, (Sign.Model.Error) obj);
            case 6:
                return WalletKit.disconnectSession$lambda$33(function1, (Sign.Model.Error) obj);
            case 7:
                return WalletKit.respondSessionRequest$lambda$27(function1, (Sign.Model.Error) obj);
            case 8:
                return WalletKit.extendSession$lambda$24(function1, (Sign.Model.Error) obj);
            case 9:
                return WalletKit.pair$lambda$6(function1, (Core.Model.Error) obj);
            case 10:
                return WalletKit.updateSession$lambda$21(function1, (Sign.Model.Error) obj);
            case 11:
                return WalletKit.rejectSessionAuthenticate$lambda$18(function1, (Sign.Model.Error) obj);
            case 12:
                return WalletKit.emitSessionEvent$lambda$30(function1, (Sign.Model.Error) obj);
            default:
                return WalletKit.rejectSession$lambda$12(function1, (Sign.Model.Error) obj);
        }
    }
}
