package com.appsamurai.storyly.util.font;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class a extends Request<byte[]> {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final Response.Listener<byte[]> f6311a;

    public a(int i3, @Nullable String str, @Nullable Response.Listener<byte[]> listener, @Nullable Response.ErrorListener errorListener) {
        super(i3, str, errorListener);
        this.f6311a = listener;
    }

    public void deliverResponse(Object obj) {
        byte[] bArr = (byte[]) obj;
        Response.Listener<byte[]> listener = this.f6311a;
        if (listener != null) {
            listener.onResponse(bArr);
        }
    }

    @NotNull
    public String getBodyContentType() {
        return "application/octet-stream";
    }

    @Nullable
    public Response<byte[]> parseNetworkResponse(@NotNull NetworkResponse networkResponse) {
        Intrinsics.checkNotNullParameter(networkResponse, "response");
        return Response.success(networkResponse.data, HttpHeaderParser.parseCacheHeaders(networkResponse));
    }
}
