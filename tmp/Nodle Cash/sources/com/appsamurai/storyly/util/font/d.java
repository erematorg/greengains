package com.appsamurai.storyly.util.font;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.appsamurai.storyly.util.a;
import kotlin.Result;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;

public final class d implements Response.ErrorListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ CancellableContinuation<byte[]> f6314a;

    public d(CancellableContinuation<? super byte[]> cancellableContinuation) {
        this.f6314a = cancellableContinuation;
    }

    public final void onErrorResponse(VolleyError volleyError) {
        a.C0055a.a(a.f6249a, Intrinsics.stringPlus("cannot download font ", volleyError.getLocalizedMessage()), (String) null, 2);
        this.f6314a.resumeWith(Result.m8979constructorimpl((Object) null));
    }
}
