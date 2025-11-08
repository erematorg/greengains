package com.reown.sign.client;

import com.reown.sign.client.Sign;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class c implements Function0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7466a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Function1 f7467b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Sign.Params f7468c;

    public /* synthetic */ c(Function1 function1, Sign.Params params, int i3) {
        this.f7466a = i3;
        this.f7467b = function1;
        this.f7468c = params;
    }

    public final Object invoke() {
        switch (this.f7466a) {
            case 0:
                return SignProtocol$approveAuthenticate$1.invokeSuspend$lambda$0(this.f7467b, (Sign.Params.ApproveAuthenticate) this.f7468c);
            case 1:
                return SignProtocol$approveSession$1.invokeSuspend$lambda$0(this.f7467b, (Sign.Params.Approve) this.f7468c);
            case 2:
                return SignProtocol$connect$1.invokeSuspend$lambda$2$lambda$0(this.f7467b, (Sign.Params.Connect) this.f7468c);
            case 3:
                return SignProtocol$connect$2.invokeSuspend$lambda$2$lambda$0(this.f7467b, (Sign.Params.ConnectParams) this.f7468c);
            case 4:
                return SignProtocol$disconnect$1.invokeSuspend$lambda$0(this.f7467b, (Sign.Params.Disconnect) this.f7468c);
            case 5:
                return SignProtocol$emit$1.invokeSuspend$lambda$0(this.f7467b, (Sign.Params.Emit) this.f7468c);
            case 6:
                return SignProtocol$extend$1.invokeSuspend$lambda$0(this.f7467b, (Sign.Params.Extend) this.f7468c);
            case 7:
                return SignProtocol$rejectAuthenticate$1.invokeSuspend$lambda$0(this.f7467b, (Sign.Params.RejectAuthenticate) this.f7468c);
            case 8:
                return SignProtocol$rejectSession$1.invokeSuspend$lambda$0(this.f7467b, (Sign.Params.Reject) this.f7468c);
            case 9:
                return SignProtocol$respond$1.invokeSuspend$lambda$0(this.f7467b, (Sign.Params.Response) this.f7468c);
            default:
                return SignProtocol$update$1.invokeSuspend$lambda$0(this.f7467b, (Sign.Params.Update) this.f7468c);
        }
    }
}
