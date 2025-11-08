package com.reown.sign.client;

import com.reown.sign.client.Sign;
import com.reown.sign.client.SignInterface;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class e implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7471a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f7472b;

    public /* synthetic */ e(Object obj, int i3) {
        this.f7471a = i3;
        this.f7472b = obj;
    }

    public final Object invoke(Object obj) {
        int i3 = this.f7471a;
        Object obj2 = this.f7472b;
        switch (i3) {
            case 0:
                return SignProtocol$ping$1.invokeSuspend$lambda$0((Sign.Listeners.SessionPing) obj2, (String) obj);
            case 1:
                return SignProtocol$ping$1.invokeSuspend$lambda$1((Sign.Listeners.SessionPing) obj2, (Throwable) obj);
            default:
                return SignProtocol.setDappDelegate$lambda$3((SignInterface.DappDelegate) obj2, (Sign.Model.ConnectionState) obj);
        }
    }
}
