package com.reown.foundation.network;

import com.reown.foundation.common.model.Topic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class b implements Function0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Topic f7439a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Topic f7440b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ String f7441c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ String f7442d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ long f7443e;

    /* renamed from: f  reason: collision with root package name */
    public final /* synthetic */ Long f7444f;

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ BaseRelayClient f7445g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Function1 f7446h;

    public /* synthetic */ b(Topic topic, Topic topic2, String str, String str2, long j2, Long l2, BaseRelayClient baseRelayClient, Function1 function1) {
        this.f7439a = topic;
        this.f7440b = topic2;
        this.f7441c = str;
        this.f7442d = str2;
        this.f7443e = j2;
        this.f7444f = l2;
        this.f7445g = baseRelayClient;
        this.f7446h = function1;
    }

    public final Object invoke() {
        return BaseRelayClient.approveSession$lambda$7(this.f7439a, this.f7440b, this.f7441c, this.f7442d, this.f7443e, this.f7444f, this.f7445g, this.f7446h);
    }
}
