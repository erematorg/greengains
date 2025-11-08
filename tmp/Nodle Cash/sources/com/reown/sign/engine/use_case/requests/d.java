package com.reown.sign.engine.use_case.requests;

import com.reown.android.internal.common.model.AppMetaData;
import com.reown.android.internal.common.model.WCRequest;
import com.reown.android.verify.model.VerifyContext;
import com.reown.sign.common.model.vo.clientsync.session.params.SignParams;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class d implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ OnSessionRequestUseCase f7556a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ SignParams.SessionRequestParams f7557b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ WCRequest f7558c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ AppMetaData f7559d;

    public /* synthetic */ d(OnSessionRequestUseCase onSessionRequestUseCase, SignParams.SessionRequestParams sessionRequestParams, WCRequest wCRequest, AppMetaData appMetaData) {
        this.f7556a = onSessionRequestUseCase;
        this.f7557b = sessionRequestParams;
        this.f7558c = wCRequest;
        this.f7559d = appMetaData;
    }

    public final Object invoke(Object obj) {
        SignParams.SessionRequestParams sessionRequestParams = this.f7557b;
        WCRequest wCRequest = this.f7558c;
        return OnSessionRequestUseCase$invoke$2.invokeSuspend$lambda$4(this.f7556a, sessionRequestParams, wCRequest, this.f7559d, (VerifyContext) obj);
    }
}
