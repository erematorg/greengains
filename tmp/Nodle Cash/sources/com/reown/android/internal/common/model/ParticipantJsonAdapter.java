package com.reown.android.internal.common.model;

import A.a;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.internal.Util;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\r\u001a\u00020\nH\u0016J\u0010\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u001a\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/reown/android/internal/common/model/ParticipantJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/android/internal/common/model/Participant;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "stringAdapter", "", "appMetaDataAdapter", "Lcom/reown/android/internal/common/model/AppMetaData;", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ParticipantJsonAdapter extends JsonAdapter<Participant> {
    @NotNull
    private final JsonAdapter<AppMetaData> appMetaDataAdapter;
    @NotNull
    private final JsonReader.Options options;
    @NotNull
    private final JsonAdapter<String> stringAdapter;

    public ParticipantJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of("publicKey", TtmlNode.TAG_METADATA);
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        this.stringAdapter = a.h(moshi, String.class, "publicKey", "adapter(...)");
        this.appMetaDataAdapter = a.h(moshi, AppMetaData.class, TtmlNode.TAG_METADATA, "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(33, "GeneratedJsonAdapter(Participant)");
    }

    @NotNull
    public Participant fromJson(@NotNull JsonReader jsonReader) {
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        jsonReader.beginObject();
        String str = null;
        AppMetaData appMetaData = null;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(this.options);
            if (selectName == -1) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else if (selectName == 0) {
                str = this.stringAdapter.fromJson(jsonReader);
                if (str == null) {
                    throw Util.unexpectedNull("publicKey", "publicKey", jsonReader);
                }
            } else if (selectName == 1 && (appMetaData = this.appMetaDataAdapter.fromJson(jsonReader)) == null) {
                throw Util.unexpectedNull(TtmlNode.TAG_METADATA, TtmlNode.TAG_METADATA, jsonReader);
            }
        }
        jsonReader.endObject();
        if (str == null) {
            throw Util.missingProperty("publicKey", "publicKey", jsonReader);
        } else if (appMetaData != null) {
            return new Participant(str, appMetaData);
        } else {
            throw Util.missingProperty(TtmlNode.TAG_METADATA, TtmlNode.TAG_METADATA, jsonReader);
        }
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable Participant participant) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (participant != null) {
            jsonWriter.beginObject();
            jsonWriter.name("publicKey");
            this.stringAdapter.toJson(jsonWriter, participant.getPublicKey());
            jsonWriter.name(TtmlNode.TAG_METADATA);
            this.appMetaDataAdapter.toJson(jsonWriter, participant.getMetadata());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
