package com.appsamurai.storyly.util.font;

import com.android.volley.Response;
import kotlin.Result;
import kotlinx.coroutines.CancellableContinuation;

public final class c<T> implements Response.Listener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ CancellableContinuation<byte[]> f6313a;

    public c(CancellableContinuation<? super byte[]> cancellableContinuation) {
        this.f6313a = cancellableContinuation;
    }

    public void onResponse(Object obj) {
        this.f6313a.resumeWith(Result.m8979constructorimpl((byte[]) obj));
    }
}
