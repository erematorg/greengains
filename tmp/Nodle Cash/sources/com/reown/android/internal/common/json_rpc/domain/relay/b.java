package com.reown.android.internal.common.json_rpc.domain.relay;

import io.nodle.cash.ui.feature.chat.mission.MissionSendScreenKt;
import io.nodle.cash.ui.feature.chat.transaction.TransactionSendScreenKt;
import kotlin.jvm.functions.Function0;
import okhttp3.Handshake;

public final /* synthetic */ class b implements Function0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7329a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Function0 f7330b;

    public /* synthetic */ b(Function0 function0, int i3) {
        this.f7329a = i3;
        this.f7330b = function0;
    }

    public final Object invoke() {
        int i3 = this.f7329a;
        Function0 function0 = this.f7330b;
        switch (i3) {
            case 0:
                return RelayJsonRpcInteractor.respondWithParams$lambda$24(function0);
            case 1:
                return RelayJsonRpcInteractor.respondWithParams$lambda$22(function0);
            case 2:
                return RelayJsonRpcInteractor.respondWithError$lambda$29(function0);
            case 3:
                return MissionSendScreenKt.MissionSendScreen$lambda$13$lambda$12$lambda$8$lambda$7$lambda$6$lambda$5(function0);
            case 4:
                return TransactionSendScreenKt.TransactionSendScreen$lambda$21$lambda$20$lambda$8$lambda$7$lambda$6$lambda$5(function0);
            default:
                return Handshake.peerCertificates_delegate$lambda$0(function0);
        }
    }
}
