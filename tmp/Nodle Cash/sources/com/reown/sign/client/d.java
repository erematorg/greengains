package com.reown.sign.client;

import com.reown.android.Core;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class d implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7469a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Function1 f7470b;

    public /* synthetic */ d(Function1 function1, int i3) {
        this.f7469a = i3;
        this.f7470b = function1;
    }

    public final Object invoke(Object obj) {
        int i3 = this.f7469a;
        Function1 function1 = this.f7470b;
        switch (i3) {
            case 0:
                return SignProtocol$approveAuthenticate$1.invokeSuspend$lambda$1(function1, (Throwable) obj);
            case 1:
                return SignProtocol$approveSession$1.invokeSuspend$lambda$1(function1, (Throwable) obj);
            case 2:
                return SignProtocol$authenticate$1.invokeSuspend$lambda$0(function1, (String) obj);
            case 3:
                return SignProtocol$authenticate$1.invokeSuspend$lambda$1(function1, (Throwable) obj);
            case 4:
                return SignProtocol$connect$1.invokeSuspend$lambda$2$lambda$1(function1, (Throwable) obj);
            case 5:
                return SignProtocol$connect$2.invokeSuspend$lambda$2$lambda$1(function1, (Throwable) obj);
            case 6:
                return SignProtocol$decryptMessage$1.invokeSuspend$lambda$0(function1, (Core.Model.Message) obj);
            case 7:
                return SignProtocol$decryptMessage$1.invokeSuspend$lambda$1(function1, (Throwable) obj);
            case 8:
                return SignProtocol$disconnect$1.invokeSuspend$lambda$1(function1, (Throwable) obj);
            case 9:
                return SignProtocol$emit$1.invokeSuspend$lambda$1(function1, (Throwable) obj);
            case 10:
                return SignProtocol$extend$1.invokeSuspend$lambda$1(function1, (Throwable) obj);
            case 11:
                return SignProtocol$rejectAuthenticate$1.invokeSuspend$lambda$1(function1, (Throwable) obj);
            case 12:
                return SignProtocol$rejectSession$1.invokeSuspend$lambda$1(function1, (Throwable) obj);
            case 13:
                return SignProtocol$request$1.invokeSuspend$lambda$1(function1, (Throwable) obj);
            case 14:
                return SignProtocol$respond$1.invokeSuspend$lambda$1(function1, (Throwable) obj);
            default:
                return SignProtocol$update$1.invokeSuspend$lambda$1(function1, (Throwable) obj);
        }
    }
}
