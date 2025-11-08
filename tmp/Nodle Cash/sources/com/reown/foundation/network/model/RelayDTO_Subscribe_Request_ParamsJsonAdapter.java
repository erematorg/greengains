package com.reown.foundation.network.model;

import A.a;
import com.reown.android.push.notifications.PushMessagingService;
import com.reown.foundation.common.model.Topic;
import com.reown.foundation.network.model.RelayDTO;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.internal.Util;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u001a\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO_Subscribe_Request_ParamsJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/foundation/network/model/RelayDTO$Subscribe$Request$Params;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "topicAtQualifierAdapter", "Lcom/reown/foundation/common/model/Topic;", "toString", "", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RelayDTO_Subscribe_Request_ParamsJsonAdapter extends JsonAdapter<RelayDTO.Subscribe.Request.Params> {
    @NotNull
    private final JsonReader.Options options;
    @NotNull
    private final JsonAdapter<Topic> topicAtQualifierAdapter;

    public RelayDTO_Subscribe_Request_ParamsJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of(PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        JsonAdapter<Topic> adapter = moshi.adapter(Topic.class, SetsKt.setOf(new RelayDTO_Subscribe_Request_ParamsJsonAdapter$annotationImpl$com_reown_foundation_common_adapters_TopicAdapter_Qualifier$0()), PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullExpressionValue(adapter, "adapter(...)");
        this.topicAtQualifierAdapter = adapter;
    }

    @NotNull
    public String toString() {
        return a.j(55, "GeneratedJsonAdapter(RelayDTO.Subscribe.Request.Params)");
    }

    @NotNull
    public RelayDTO.Subscribe.Request.Params fromJson(@NotNull JsonReader jsonReader) {
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        jsonReader.beginObject();
        Topic topic = null;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(this.options);
            if (selectName == -1) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else if (selectName == 0 && (topic = this.topicAtQualifierAdapter.fromJson(jsonReader)) == null) {
                throw Util.unexpectedNull(PushMessagingService.KEY_TOPIC, PushMessagingService.KEY_TOPIC, jsonReader);
            }
        }
        jsonReader.endObject();
        if (topic != null) {
            return new RelayDTO.Subscribe.Request.Params(topic);
        }
        throw Util.missingProperty(PushMessagingService.KEY_TOPIC, PushMessagingService.KEY_TOPIC, jsonReader);
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable RelayDTO.Subscribe.Request.Params params) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (params != null) {
            jsonWriter.beginObject();
            jsonWriter.name(PushMessagingService.KEY_TOPIC);
            this.topicAtQualifierAdapter.toJson(jsonWriter, params.getTopic());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
