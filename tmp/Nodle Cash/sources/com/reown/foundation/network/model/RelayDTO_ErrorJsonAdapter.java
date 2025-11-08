package com.reown.foundation.network.model;

import A.a;
import com.reown.android.push.notifications.PushMessagingService;
import com.reown.foundation.network.model.RelayDTO;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.internal.Util;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\r\u001a\u00020\fH\u0016J\u0010\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u001a\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO_ErrorJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/foundation/network/model/RelayDTO$Error;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "longAdapter", "", "stringAdapter", "", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RelayDTO_ErrorJsonAdapter extends JsonAdapter<RelayDTO.Error> {
    @NotNull
    private final JsonAdapter<Long> longAdapter;
    @NotNull
    private final JsonReader.Options options;
    @NotNull
    private final JsonAdapter<String> stringAdapter;

    public RelayDTO_ErrorJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of("code", PushMessagingService.KEY_MESSAGE);
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        this.longAdapter = a.h(moshi, Long.TYPE, "code", "adapter(...)");
        this.stringAdapter = a.h(moshi, String.class, PushMessagingService.KEY_MESSAGE, "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(36, "GeneratedJsonAdapter(RelayDTO.Error)");
    }

    @NotNull
    public RelayDTO.Error fromJson(@NotNull JsonReader jsonReader) {
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        jsonReader.beginObject();
        Long l2 = null;
        String str = null;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(this.options);
            if (selectName == -1) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else if (selectName == 0) {
                l2 = this.longAdapter.fromJson(jsonReader);
                if (l2 == null) {
                    throw Util.unexpectedNull("code", "code", jsonReader);
                }
            } else if (selectName == 1 && (str = this.stringAdapter.fromJson(jsonReader)) == null) {
                throw Util.unexpectedNull(PushMessagingService.KEY_MESSAGE, PushMessagingService.KEY_MESSAGE, jsonReader);
            }
        }
        jsonReader.endObject();
        if (l2 != null) {
            long longValue = l2.longValue();
            if (str != null) {
                return new RelayDTO.Error(longValue, str);
            }
            throw Util.missingProperty(PushMessagingService.KEY_MESSAGE, PushMessagingService.KEY_MESSAGE, jsonReader);
        }
        throw Util.missingProperty("code", "code", jsonReader);
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable RelayDTO.Error error) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (error != null) {
            jsonWriter.beginObject();
            jsonWriter.name("code");
            this.longAdapter.toJson(jsonWriter, Long.valueOf(error.getCode()));
            jsonWriter.name(PushMessagingService.KEY_MESSAGE);
            this.stringAdapter.toJson(jsonWriter, error.getMessage());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
