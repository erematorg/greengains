package com.reown.sign.engine.use_case.calls;

import com.reown.android.Core;
import com.reown.android.internal.common.JsonRpcResponse;
import com.reown.foundation.common.model.Topic;
import com.reown.sign.common.model.vo.proposal.ProposalVO;
import java.util.ArrayList;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class a implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7480a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f7481b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f7482c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ Object f7483d;

    public /* synthetic */ a(SessionAuthenticateUseCase sessionAuthenticateUseCase, Topic topic, Object obj, int i3) {
        this.f7480a = i3;
        this.f7481b = sessionAuthenticateUseCase;
        this.f7483d = topic;
        this.f7482c = obj;
    }

    public final Object invoke(Object obj) {
        switch (this.f7480a) {
            case 0:
                return ApproveSessionAuthenticateUseCase$approveSessionAuthenticate$2.invokeSuspend$lambda$14((ArrayList) this.f7481b, (ApproveSessionAuthenticateUseCase) this.f7482c, (Topic) this.f7483d, (Topic) obj);
            case 1:
                return DisconnectSessionUseCase$disconnect$2.invokeSuspend$lambda$1((DisconnectSessionUseCase) this.f7481b, (String) this.f7482c, (Function1) this.f7483d, (Throwable) obj);
            case 2:
                return EmitEventUseCase$emit$2.invokeSuspend$lambda$3$lambda$2((EmitEventUseCase) this.f7481b, (String) this.f7482c, (Function1) this.f7483d, (Throwable) obj);
            case 3:
                return ExtendSessionUseCase$extend$2.invokeSuspend$lambda$1((ExtendSessionUseCase) this.f7481b, (String) this.f7482c, (Function1) this.f7483d, (Throwable) obj);
            case 4:
                return PingUseCase$ping$2.invokeSuspend$lambda$1((PingUseCase) this.f7481b, (String) this.f7482c, (Function1) this.f7483d, (Throwable) obj);
            case 5:
                ProposalVO proposalVO = (ProposalVO) this.f7482c;
                return RejectSessionUseCase$reject$2.invokeSuspend$lambda$2((RejectSessionUseCase) this.f7481b, proposalVO, (Function1) this.f7483d, (Throwable) obj);
            case 6:
                return RespondSessionRequestUseCase$respondSessionRequest$2.invokeSuspend$lambda$4((RespondSessionRequestUseCase) this.f7481b, (JsonRpcResponse) this.f7482c, (Function1) this.f7483d, (Throwable) obj);
            case 7:
                return SessionUpdateUseCase$sessionUpdate$2.invokeSuspend$lambda$3$lambda$2((SessionUpdateUseCase) this.f7481b, (String) this.f7482c, (Function1) this.f7483d, (Throwable) obj);
            case 8:
                return SessionAuthenticateUseCase.authenticate$lambda$0((SessionAuthenticateUseCase) this.f7481b, (Topic) this.f7483d, (Core.Model.Pairing) this.f7482c, (Topic) obj);
            default:
                return SessionAuthenticateUseCase.authenticate$lambda$1((SessionAuthenticateUseCase) this.f7481b, (Topic) this.f7483d, (Function1) this.f7482c, (Throwable) obj);
        }
    }

    public /* synthetic */ a(Object obj, int i3, Object obj2, Object obj3) {
        this.f7480a = i3;
        this.f7481b = obj;
        this.f7482c = obj2;
        this.f7483d = obj3;
    }
}
