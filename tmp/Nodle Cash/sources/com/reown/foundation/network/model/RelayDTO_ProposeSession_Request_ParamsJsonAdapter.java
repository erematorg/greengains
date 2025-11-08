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

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0010\u001a\u00020\fH\u0016J\u0010\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u001a\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO_ProposeSession_Request_ParamsJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/foundation/network/model/RelayDTO$ProposeSession$Request$Params;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "topicAtQualifierAdapter", "Lcom/reown/foundation/common/model/Topic;", "stringAdapter", "", "nullableStringAdapter", "longAdapter", "", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RelayDTO_ProposeSession_Request_ParamsJsonAdapter extends JsonAdapter<RelayDTO.ProposeSession.Request.Params> {
    @NotNull
    private final JsonAdapter<Long> longAdapter;
    @NotNull
    private final JsonAdapter<String> nullableStringAdapter;
    @NotNull
    private final JsonReader.Options options;
    @NotNull
    private final JsonAdapter<String> stringAdapter;
    @NotNull
    private final JsonAdapter<Topic> topicAtQualifierAdapter;

    public RelayDTO_ProposeSession_Request_ParamsJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of("pairingTopic", "sessionProposal", "attestation", "correlationId");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        JsonAdapter<Topic> adapter = moshi.adapter(Topic.class, SetsKt.setOf(new RelayDTO_ProposeSession_Request_ParamsJsonAdapter$annotationImpl$com_reown_foundation_common_adapters_TopicAdapter_Qualifier$0()), "pairingTopic");
        Intrinsics.checkNotNullExpressionValue(adapter, "adapter(...)");
        this.topicAtQualifierAdapter = adapter;
        Class<String> cls = String.class;
        this.stringAdapter = a.h(moshi, cls, "sessionProposal", "adapter(...)");
        this.nullableStringAdapter = a.h(moshi, cls, "attestation", "adapter(...)");
        this.longAdapter = a.h(moshi, Long.TYPE, "correlationId", "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(60, "GeneratedJsonAdapter(RelayDTO.ProposeSession.Request.Params)");
    }

    @NotNull
    public RelayDTO.ProposeSession.Request.Params fromJson(@NotNull JsonReader jsonReader) {
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        jsonReader.beginObject();
        Long l2 = null;
        Topic topic = null;
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
                str = this.stringAdapter.fromJson(jsonReader);
                if (str == null) {
                    throw Util.unexpectedNull("sessionProposal", "sessionProposal", jsonReader);
                }
            } else if (selectName == 2) {
                str2 = this.nullableStringAdapter.fromJson(jsonReader);
            } else if (selectName == 3 && (l2 = this.longAdapter.fromJson(jsonReader)) == null) {
                throw Util.unexpectedNull("correlationId", "correlationId", jsonReader);
            }
        }
        jsonReader.endObject();
        if (topic == null) {
            throw Util.missingProperty("pairingTopic", "pairingTopic", jsonReader);
        } else if (str == null) {
            throw Util.missingProperty("sessionProposal", "sessionProposal", jsonReader);
        } else if (l2 != null) {
            return new RelayDTO.ProposeSession.Request.Params(topic, str, str2, l2.longValue());
        } else {
            throw Util.missingProperty("correlationId", "correlationId", jsonReader);
        }
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable RelayDTO.ProposeSession.Request.Params params) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (params != null) {
            jsonWriter.beginObject();
            jsonWriter.name("pairingTopic");
            this.topicAtQualifierAdapter.toJson(jsonWriter, params.getPairingTopic());
            jsonWriter.name("sessionProposal");
            this.stringAdapter.toJson(jsonWriter, params.getSessionProposal());
            jsonWriter.name("attestation");
            this.nullableStringAdapter.toJson(jsonWriter, params.getAttestation());
            jsonWriter.name("correlationId");
            this.longAdapter.toJson(jsonWriter, Long.valueOf(params.getCorrelationId()));
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
