package com.reown.android.push.network.model;

import A.a;
import androidx.core.app.NotificationCompat;
import com.reown.android.push.network.model.PushResponse;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import com.squareup.moshi.internal.Util;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0010\u001a\u00020\u000fH\u0016J\u0010\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u001a\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\r\u0018\u00010\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/reown/android/push/network/model/PushResponseJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/android/push/network/model/PushResponse;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "nullableListOfErrorAdapter", "", "Lcom/reown/android/push/network/model/PushResponse$Error;", "nullableListOfFieldAdapter", "Lcom/reown/android/push/network/model/PushResponse$Field;", "stringAdapter", "", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PushResponseJsonAdapter extends JsonAdapter<PushResponse> {
    @NotNull
    private final JsonAdapter<List<PushResponse.Error>> nullableListOfErrorAdapter;
    @NotNull
    private final JsonAdapter<List<PushResponse.Field>> nullableListOfFieldAdapter;
    @NotNull
    private final JsonReader.Options options;
    @NotNull
    private final JsonAdapter<String> stringAdapter;

    public PushResponseJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of("errors", "fields", NotificationCompat.CATEGORY_STATUS);
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        Class<List> cls = List.class;
        this.nullableListOfErrorAdapter = a.i(moshi, Types.newParameterizedType(cls, PushResponse.Error.class), "errors", "adapter(...)");
        this.nullableListOfFieldAdapter = a.i(moshi, Types.newParameterizedType(cls, PushResponse.Field.class), "fields", "adapter(...)");
        this.stringAdapter = a.h(moshi, String.class, NotificationCompat.CATEGORY_STATUS, "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(34, "GeneratedJsonAdapter(PushResponse)");
    }

    @NotNull
    public PushResponse fromJson(@NotNull JsonReader jsonReader) {
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        jsonReader.beginObject();
        List list = null;
        List list2 = null;
        String str = null;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(this.options);
            if (selectName == -1) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else if (selectName == 0) {
                list = this.nullableListOfErrorAdapter.fromJson(jsonReader);
            } else if (selectName == 1) {
                list2 = this.nullableListOfFieldAdapter.fromJson(jsonReader);
            } else if (selectName == 2 && (str = this.stringAdapter.fromJson(jsonReader)) == null) {
                throw Util.unexpectedNull(NotificationCompat.CATEGORY_STATUS, NotificationCompat.CATEGORY_STATUS, jsonReader);
            }
        }
        jsonReader.endObject();
        if (str != null) {
            return new PushResponse(list, list2, str);
        }
        throw Util.missingProperty(NotificationCompat.CATEGORY_STATUS, NotificationCompat.CATEGORY_STATUS, jsonReader);
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable PushResponse pushResponse) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (pushResponse != null) {
            jsonWriter.beginObject();
            jsonWriter.name("errors");
            this.nullableListOfErrorAdapter.toJson(jsonWriter, pushResponse.getErrors());
            jsonWriter.name("fields");
            this.nullableListOfFieldAdapter.toJson(jsonWriter, pushResponse.getFields());
            jsonWriter.name(NotificationCompat.CATEGORY_STATUS);
            this.stringAdapter.toJson(jsonWriter, pushResponse.getStatus());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
