package com.reown.sign.engine.use_case.calls;

import com.reown.sign.common.model.vo.proposal.ProposalVO;
import java.util.ArrayList;
import kotlin.jvm.functions.Function0;

public final /* synthetic */ class e implements Function0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Function0 f7502a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ ArrayList f7503b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ ApproveSessionUseCase f7504c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ String f7505d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ ProposalVO f7506e;

    public /* synthetic */ e(Function0 function0, ArrayList arrayList, ApproveSessionUseCase approveSessionUseCase, String str, ProposalVO proposalVO) {
        this.f7502a = function0;
        this.f7503b = arrayList;
        this.f7504c = approveSessionUseCase;
        this.f7505d = str;
        this.f7506e = proposalVO;
    }

    public final Object invoke() {
        ProposalVO proposalVO = this.f7506e;
        return ApproveSessionUseCase$approve$2.invokeSuspend$lambda$5(this.f7502a, this.f7503b, this.f7504c, this.f7505d, proposalVO);
    }
}
