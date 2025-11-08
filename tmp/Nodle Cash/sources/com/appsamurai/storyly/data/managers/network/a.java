package com.appsamurai.storyly.data.managers.network;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

public class a extends JsonRequest<b> {
    public a(int i3, @Nullable String str, @Nullable JSONObject jSONObject, @Nullable Response.Listener<b> listener, @Nullable Response.ErrorListener errorListener) {
        super(i3, str, (String) null, listener, errorListener);
    }

    @Nullable
    public Response<b> parseNetworkResponse(@NotNull NetworkResponse networkResponse) {
        Intrinsics.checkNotNullParameter(networkResponse, "response");
        try {
            int i3 = networkResponse.statusCode;
            if (i3 == 304) {
                return Response.success(new b((JSONObject) null, networkResponse.headers, i3), HttpHeaderParser.parseCacheHeaders(networkResponse));
            }
            byte[] bArr = networkResponse.data;
            Intrinsics.checkNotNullExpressionValue(bArr, "response.data");
            String parseCharset = HttpHeaderParser.parseCharset(networkResponse.headers, "utf-8");
            Intrinsics.checkNotNullExpressionValue(parseCharset, "parseCharset(response.headers, PROTOCOL_CHARSET)");
            Charset forName = Charset.forName(parseCharset);
            Intrinsics.checkNotNullExpressionValue(forName, "Charset.forName(charsetName)");
            return Response.success(new b(new JSONObject(new String(bArr, forName)), networkResponse.headers, networkResponse.statusCode), HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (UnsupportedEncodingException e3) {
            return Response.error(new ParseError((Throwable) e3));
        } catch (JSONException e4) {
            return Response.error(new ParseError((Throwable) e4));
        } catch (Exception e5) {
            return Response.error(new VolleyError((Throwable) e5));
        }
    }
}
