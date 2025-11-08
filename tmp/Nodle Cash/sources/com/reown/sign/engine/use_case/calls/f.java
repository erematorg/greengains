package com.reown.sign.engine.use_case.calls;

import com.reown.android.internal.common.model.WCRequest;
import com.reown.foundation.common.model.Topic;
import com.reown.sign.common.model.vo.proposal.ProposalVO;
import java.util.ArrayList;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class f implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7507a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f7508b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f7509c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ Object f7510d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ Object f7511e;

    public /* synthetic */ f(int i3, Object obj, Object obj2, Object obj3, Object obj4) {
        this.f7507a = i3;
        this.f7508b = obj;
        this.f7509c = obj2;
        this.f7510d = obj3;
        this.f7511e = obj4;
    }

    public final Object invoke(Object obj) {
        switch (this.f7507a) {
            case 0:
                ArrayList arrayList = (ArrayList) this.f7510d;
                return ApproveSessionUseCase$approve$2.invokeSuspend$lambda$6((Function1) this.f7508b, (ApproveSessionUseCase) this.f7509c, arrayList, (Topic) this.f7511e, (Throwable) obj);
            default:
                ProposalVO proposalVO = (ProposalVO) this.f7509c;
                Function0 function0 = (Function0) this.f7510d;
                return RejectSessionUseCase$reject$2.invokeSuspend$lambda$1((RejectSessionUseCase) this.f7508b, proposalVO, function0, (String) this.f7511e, (WCRequest) obj);
        }
    }
}
