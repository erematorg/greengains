package com.reown.android.push.network.model;

import A.a;
import com.reown.android.push.network.model.PushResponse;
import com.reown.android.push.notifications.PushMessagingService;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.internal.Util;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u000b\u001a\u00020\nH\u0016J\u0010\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u001a\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/reown/android/push/network/model/PushResponse_ErrorJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/android/push/network/model/PushResponse$Error;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "stringAdapter", "", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PushResponse_ErrorJsonAdapter extends JsonAdapter<PushResponse.Error> {
    @NotNull
    private final JsonReader.Options options;
    @NotNull
    private final JsonAdapter<String> stringAdapter;

    public PushResponse_ErrorJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of(PushMessagingService.KEY_MESSAGE, "name");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        this.stringAdapter = a.h(moshi, String.class, PushMessagingService.KEY_MESSAGE, "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(40, "GeneratedJsonAdapter(PushResponse.Error)");
    }

    @NotNull
    public PushResponse.Error fromJson(@NotNull JsonReader jsonReader) {
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        jsonReader.beginObject();
        String str = null;
        String str2 = null;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(this.options);
            if (selectName == -1) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else if (selectName == 0) {
                str = this.stringAdapter.fromJson(jsonReader);
                if (str == null) {
                    throw Util.unexpectedNull(PushMessagingService.KEY_MESSAGE, PushMessagingService.KEY_MESSAGE, jsonReader);
                }
            } else if (selectName == 1 && (str2 = this.stringAdapter.fromJson(jsonReader)) == null) {
                throw Util.unexpectedNull("name", "name", jsonReader);
            }
        }
        jsonReader.endObject();
        if (str == null) {
            throw Util.missingProperty(PushMessagingService.KEY_MESSAGE, PushMessagingService.KEY_MESSAGE, jsonReader);
        } else if (str2 != null) {
            return new PushResponse.Error(str, str2);
        } else {
            throw Util.missingProperty("name", "name", jsonReader);
        }
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable PushResponse.Error error) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (error != null) {
            jsonWriter.beginObject();
            jsonWriter.name(PushMessagingService.KEY_MESSAGE);
            this.stringAdapter.toJson(jsonWriter, error.getMessage());
            jsonWriter.name("name");
            this.stringAdapter.toJson(jsonWriter, error.getName());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
