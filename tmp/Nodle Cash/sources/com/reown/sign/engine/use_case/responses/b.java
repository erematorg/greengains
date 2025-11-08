package com.reown.sign.engine.use_case.responses;

import com.reown.foundation.common.model.Topic;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class b implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7561a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ OnSessionProposalResponseUseCase f7562b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Topic f7563c;

    public /* synthetic */ b(OnSessionProposalResponseUseCase onSessionProposalResponseUseCase, Topic topic, int i3) {
        this.f7561a = i3;
        this.f7562b = onSessionProposalResponseUseCase;
        this.f7563c = topic;
    }

    public final Object invoke(Object obj) {
        switch (this.f7561a) {
            case 0:
                return OnSessionProposalResponseUseCase$invoke$2.invokeSuspend$lambda$0(this.f7562b, this.f7563c, (Topic) obj);
            default:
                return OnSessionProposalResponseUseCase$invoke$2.invokeSuspend$lambda$1(this.f7562b, this.f7563c, (Throwable) obj);
        }
    }
}
