package com.android.volley.toolbox;

import androidx.annotation.Nullable;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonObjectRequest extends JsonRequest<JSONObject> {
    public JsonObjectRequest(String str, Response.Listener<JSONObject> listener, @Nullable Response.ErrorListener errorListener) {
        super(0, str, (String) null, listener, errorListener);
    }

    public Response<JSONObject> parseNetworkResponse(NetworkResponse networkResponse) {
        try {
            return Response.success(new JSONObject(new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers, "utf-8"))), HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (UnsupportedEncodingException e3) {
            return Response.error(new ParseError((Throwable) e3));
        } catch (JSONException e4) {
            return Response.error(new ParseError((Throwable) e4));
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @Deprecated
    public JsonObjectRequest(String str, @Nullable JSONObject jSONObject, Response.Listener<JSONObject> listener, @Nullable Response.ErrorListener errorListener) {
        super(jSONObject == null ? 0 : 1, str, jSONObject != null ? jSONObject.toString() : null, listener, errorListener);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JsonObjectRequest(int i3, String str, @Nullable JSONObject jSONObject, Response.Listener<JSONObject> listener, @Nullable Response.ErrorListener errorListener) {
        super(i3, str, jSONObject != null ? jSONObject.toString() : null, listener, errorListener);
    }
}
