package com.reown.sign.engine.use_case.requests;

import com.reown.android.internal.common.model.WCRequest;
import com.reown.android.verify.model.VerifyContext;
import com.reown.sign.common.model.vo.clientsync.session.params.SignParams;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class a implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7548a = 0;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ WCRequest f7549b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f7550c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ SignParams f7551d;

    public /* synthetic */ a(OnSessionAuthenticateUseCase onSessionAuthenticateUseCase, WCRequest wCRequest, SignParams.SessionAuthenticateParams sessionAuthenticateParams) {
        this.f7550c = onSessionAuthenticateUseCase;
        this.f7549b = wCRequest;
        this.f7551d = sessionAuthenticateParams;
    }

    public final Object invoke(Object obj) {
        VerifyContext verifyContext = (VerifyContext) obj;
        switch (this.f7548a) {
            case 0:
                return OnSessionAuthenticateUseCase$invoke$2.invokeSuspend$lambda$1((OnSessionAuthenticateUseCase) this.f7550c, this.f7549b, (SignParams.SessionAuthenticateParams) this.f7551d, verifyContext);
            default:
                return OnSessionProposalUseCase$invoke$2.invokeSuspend$lambda$4((OnSessionProposalUseCase) this.f7550c, (SignParams.SessionProposeParams) this.f7551d, this.f7549b, verifyContext);
        }
    }

    public /* synthetic */ a(OnSessionProposalUseCase onSessionProposalUseCase, SignParams.SessionProposeParams sessionProposeParams, WCRequest wCRequest) {
        this.f7550c = onSessionProposalUseCase;
        this.f7551d = sessionProposeParams;
        this.f7549b = wCRequest;
    }
}
