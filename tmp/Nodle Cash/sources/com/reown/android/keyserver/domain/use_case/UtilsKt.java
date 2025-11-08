package com.reown.android.keyserver.domain.use_case;

import com.reown.android.keyserver.model.KeyServerHttpResponse;
import com.reown.android.keyserver.model.KeyServerResponse;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ResponseBody;
import retrofit2.Response;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a(\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u000e\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0004*\b\u0012\u0004\u0012\u0002H\u00030\u0005H\u0000\u001a1\u0010\u0006\u001a\u0002H\u0002\"\b\b\u0000\u0010\u0002*\u00020\u0007\"\u000e\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0004*\b\u0012\u0004\u0012\u0002H\u00030\u0005H\u0000¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"unwrapUnit", "", "K", "T", "Lcom/reown/android/keyserver/model/KeyServerHttpResponse;", "Lretrofit2/Response;", "unwrapValue", "Lcom/reown/android/keyserver/model/KeyServerResponse;", "(Lretrofit2/Response;)Lcom/reown/android/keyserver/model/KeyServerResponse;", "android_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class UtilsKt {
    public static final /* synthetic */ void unwrapUnit(Response response) {
        Intrinsics.checkNotNullParameter(response, "<this>");
        String str = null;
        if (!response.isSuccessful() || response.body() == null) {
            ResponseBody errorBody = response.errorBody();
            if (errorBody != null) {
                str = errorBody.string();
            }
            throw new Throwable(str);
        }
        Object body = response.body();
        Intrinsics.checkNotNull(body);
        if (!Intrinsics.areEqual((Object) ((KeyServerHttpResponse) body).getStatus(), (Object) "SUCCESS")) {
            Object body2 = response.body();
            Intrinsics.checkNotNull(body2);
            KeyServerHttpResponse.Error error = ((KeyServerHttpResponse) body2).getError();
            if (error != null) {
                str = error.getMessage();
            }
            throw new Throwable(str);
        }
    }

    public static final /* synthetic */ KeyServerResponse unwrapValue(Response response) {
        Intrinsics.checkNotNullParameter(response, "<this>");
        String str = null;
        if (!response.isSuccessful() || response.body() == null) {
            ResponseBody errorBody = response.errorBody();
            if (errorBody != null) {
                str = errorBody.string();
            }
            throw new Throwable(str);
        }
        Object body = response.body();
        Intrinsics.checkNotNull(body);
        if (Intrinsics.areEqual((Object) ((KeyServerHttpResponse) body).getStatus(), (Object) "SUCCESS")) {
            Object body2 = response.body();
            Intrinsics.checkNotNull(body2);
            if (((KeyServerHttpResponse) body2).getValue() != null) {
                Object body3 = response.body();
                Intrinsics.checkNotNull(body3);
                Object value = ((KeyServerHttpResponse) body3).getValue();
                Intrinsics.checkNotNull(value);
                return (KeyServerResponse) value;
            }
            throw new Throwable("Expected value is null");
        }
        Object body4 = response.body();
        Intrinsics.checkNotNull(body4);
        KeyServerHttpResponse.Error error = ((KeyServerHttpResponse) body4).getError();
        if (error != null) {
            str = error.getMessage();
        }
        throw new Throwable(str);
    }
}
