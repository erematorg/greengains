package com.reown.foundation.network.model;

import A.a;
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

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u000f\u001a\u00020\fH\u0016J\u0010\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001a\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO_ApproveSession_Request_ParamsJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/foundation/network/model/RelayDTO$ApproveSession$Request$Params;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "topicAtQualifierAdapter", "Lcom/reown/foundation/common/model/Topic;", "stringAdapter", "", "longAdapter", "", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RelayDTO_ApproveSession_Request_ParamsJsonAdapter extends JsonAdapter<RelayDTO.ApproveSession.Request.Params> {
    @NotNull
    private final JsonAdapter<Long> longAdapter;
    @NotNull
    private final JsonReader.Options options;
    @NotNull
    private final JsonAdapter<String> stringAdapter;
    @NotNull
    private final JsonAdapter<Topic> topicAtQualifierAdapter;

    public RelayDTO_ApproveSession_Request_ParamsJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of("pairingTopic", "sessionTopic", "sessionProposalResponse", "sessionSettlementRequest", "correlationId");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        JsonAdapter<Topic> adapter = moshi.adapter(Topic.class, SetsKt.setOf(new RelayDTO_ApproveSession_Request_ParamsJsonAdapter$annotationImpl$com_reown_foundation_common_adapters_TopicAdapter_Qualifier$0()), "pairingTopic");
        Intrinsics.checkNotNullExpressionValue(adapter, "adapter(...)");
        this.topicAtQualifierAdapter = adapter;
        this.stringAdapter = a.h(moshi, String.class, "sessionProposalResponse", "adapter(...)");
        this.longAdapter = a.h(moshi, Long.TYPE, "correlationId", "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(60, "GeneratedJsonAdapter(RelayDTO.ApproveSession.Request.Params)");
    }

    @NotNull
    public RelayDTO.ApproveSession.Request.Params fromJson(@NotNull JsonReader jsonReader) {
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        jsonReader.beginObject();
        Long l2 = null;
        Topic topic = null;
        Topic topic2 = null;
        String str = null;
        String str2 = null;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(this.options);
            if (selectName == -1) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else if (selectName == 0) {
                topic = this.topicAtQualifierAdapter.fromJson(jsonReader);
                if (topic == null) {
                    throw Util.unexpectedNull("pairingTopic", "pairingTopic", jsonReader);
                }
            } else if (selectName == 1) {
                topic2 = this.topicAtQualifierAdapter.fromJson(jsonReader);
                if (topic2 == null) {
                    throw Util.unexpectedNull("sessionTopic", "sessionTopic", jsonReader);
                }
            } else if (selectName == 2) {
                str = this.stringAdapter.fromJson(jsonReader);
                if (str == null) {
                    throw Util.unexpectedNull("sessionProposalResponse", "sessionProposalResponse", jsonReader);
                }
            } else if (selectName == 3) {
                str2 = this.stringAdapter.fromJson(jsonReader);
                if (str2 == null) {
                    throw Util.unexpectedNull("sessionSettlementRequest", "sessionSettlementRequest", jsonReader);
                }
            } else if (selectName == 4 && (l2 = this.longAdapter.fromJson(jsonReader)) == null) {
                throw Util.unexpectedNull("correlationId", "correlationId", jsonReader);
            }
        }
        jsonReader.endObject();
        if (topic == null) {
            throw Util.missingProperty("pairingTopic", "pairingTopic", jsonReader);
        } else if (topic2 == null) {
            throw Util.missingProperty("sessionTopic", "sessionTopic", jsonReader);
        } else if (str == null) {
            throw Util.missingProperty("sessionProposalResponse", "sessionProposalResponse", jsonReader);
        } else if (str2 == null) {
            throw Util.missingProperty("sessionSettlementRequest", "sessionSettlementRequest", jsonReader);
        } else if (l2 != null) {
            return new RelayDTO.ApproveSession.Request.Params(topic, topic2, str, str2, l2.longValue());
        } else {
            throw Util.missingProperty("correlationId", "correlationId", jsonReader);
        }
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable RelayDTO.ApproveSession.Request.Params params) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (params != null) {
            jsonWriter.beginObject();
            jsonWriter.name("pairingTopic");
            this.topicAtQualifierAdapter.toJson(jsonWriter, params.getPairingTopic());
            jsonWriter.name("sessionTopic");
            this.topicAtQualifierAdapter.toJson(jsonWriter, params.getSessionTopic());
            jsonWriter.name("sessionProposalResponse");
            this.stringAdapter.toJson(jsonWriter, params.getSessionProposalResponse());
            jsonWriter.name("sessionSettlementRequest");
            this.stringAdapter.toJson(jsonWriter, params.getSessionSettlementRequest());
            jsonWriter.name("correlationId");
            this.longAdapter.toJson(jsonWriter, Long.valueOf(params.getCorrelationId()));
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
