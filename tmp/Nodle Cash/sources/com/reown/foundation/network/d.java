package com.reown.foundation.network;

import com.reown.foundation.common.model.Topic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class d implements Function0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Topic f7453a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ String f7454b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ long f7455c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ Long f7456d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ BaseRelayClient f7457e;

    /* renamed from: f  reason: collision with root package name */
    public final /* synthetic */ Function1 f7458f;

    public /* synthetic */ d(Topic topic, String str, long j2, Long l2, BaseRelayClient baseRelayClient, Function1 function1) {
        this.f7453a = topic;
        this.f7454b = str;
        this.f7455c = j2;
        this.f7456d = l2;
        this.f7457e = baseRelayClient;
        this.f7458f = function1;
    }

    public final Object invoke() {
        return BaseRelayClient.proposeSession$lambda$5(this.f7453a, this.f7454b, this.f7455c, this.f7456d, this.f7457e, this.f7458f);
    }
}
