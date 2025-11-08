package com.reown.android.keyserver.model;

import A.a;
import androidx.core.app.NotificationCompat;
import com.google.firebase.messaging.Constants;
import com.reown.android.keyserver.model.KeyServerHttpResponse;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.internal.Util;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u000e\u001a\u00020\nH\u0016J\u0010\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u001a\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/reown/android/keyserver/model/KeyServerHttpResponse_RegisterInviteJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/android/keyserver/model/KeyServerHttpResponse$RegisterInvite;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "stringAdapter", "", "nullableErrorAdapter", "Lcom/reown/android/keyserver/model/KeyServerHttpResponse$Error;", "nullableStringAdapter", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class KeyServerHttpResponse_RegisterInviteJsonAdapter extends JsonAdapter<KeyServerHttpResponse.RegisterInvite> {
    @NotNull
    private final JsonAdapter<KeyServerHttpResponse.Error> nullableErrorAdapter;
    @NotNull
    private final JsonAdapter<String> nullableStringAdapter;
    @NotNull
    private final JsonReader.Options options;
    @NotNull
    private final JsonAdapter<String> stringAdapter;

    public KeyServerHttpResponse_RegisterInviteJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of(NotificationCompat.CATEGORY_STATUS, Constants.IPC_BUNDLE_KEY_SEND_ERROR, "value");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        Class<String> cls = String.class;
        this.stringAdapter = a.h(moshi, cls, NotificationCompat.CATEGORY_STATUS, "adapter(...)");
        this.nullableErrorAdapter = a.h(moshi, KeyServerHttpResponse.Error.class, Constants.IPC_BUNDLE_KEY_SEND_ERROR, "adapter(...)");
        this.nullableStringAdapter = a.h(moshi, cls, "value", "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(58, "GeneratedJsonAdapter(KeyServerHttpResponse.RegisterInvite)");
    }

    @NotNull
    public KeyServerHttpResponse.RegisterInvite fromJson(@NotNull JsonReader jsonReader) {
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        jsonReader.beginObject();
        String str = null;
        KeyServerHttpResponse.Error error = null;
        String str2 = null;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(this.options);
            if (selectName == -1) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else if (selectName == 0) {
                str = this.stringAdapter.fromJson(jsonReader);
                if (str == null) {
                    throw Util.unexpectedNull(NotificationCompat.CATEGORY_STATUS, NotificationCompat.CATEGORY_STATUS, jsonReader);
                }
            } else if (selectName == 1) {
                error = this.nullableErrorAdapter.fromJson(jsonReader);
            } else if (selectName == 2) {
                str2 = this.nullableStringAdapter.fromJson(jsonReader);
            }
        }
        jsonReader.endObject();
        if (str != null) {
            return new KeyServerHttpResponse.RegisterInvite(str, error, str2);
        }
        throw Util.missingProperty(NotificationCompat.CATEGORY_STATUS, NotificationCompat.CATEGORY_STATUS, jsonReader);
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable KeyServerHttpResponse.RegisterInvite registerInvite) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (registerInvite != null) {
            jsonWriter.beginObject();
            jsonWriter.name(NotificationCompat.CATEGORY_STATUS);
            this.stringAdapter.toJson(jsonWriter, registerInvite.getStatus());
            jsonWriter.name(Constants.IPC_BUNDLE_KEY_SEND_ERROR);
            this.nullableErrorAdapter.toJson(jsonWriter, registerInvite.getError());
            jsonWriter.name("value");
            this.nullableStringAdapter.toJson(jsonWriter, registerInvite.getValue());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
