package com.google.android.datatransport.cct;

import com.google.android.datatransport.cct.CctTransportBackend;
import com.google.android.datatransport.runtime.retries.Function;

public final /* synthetic */ class a implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ CctTransportBackend f6578a;

    public /* synthetic */ a(CctTransportBackend cctTransportBackend) {
        this.f6578a = cctTransportBackend;
    }

    public final Object apply(Object obj) {
        return this.f6578a.doSend((CctTransportBackend.HttpRequest) obj);
    }
}
