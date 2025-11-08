package com.reown.foundation.network.model;

import A.a;
import com.reown.android.push.notifications.PushMessagingService;
import com.reown.foundation.common.model.Topic;
import com.reown.foundation.common.model.Ttl;
import com.reown.foundation.network.model.RelayDTO;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import com.squareup.moshi.internal.Util;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0018\u001a\u00020\fH\u0016J\u0010\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u001a\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0001X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0015\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u00160\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO_Publish_Request_ParamsJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/foundation/network/model/RelayDTO$Publish$Request$Params;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "topicAtQualifierAdapter", "Lcom/reown/foundation/common/model/Topic;", "stringAdapter", "", "ttlAtQualifierAdapter", "Lcom/reown/foundation/common/model/Ttl;", "intAdapter", "", "nullableBooleanAdapter", "", "nullableLongAdapter", "", "nullableListOfStringAdapter", "", "nullableStringAdapter", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RelayDTO_Publish_Request_ParamsJsonAdapter extends JsonAdapter<RelayDTO.Publish.Request.Params> {
    @NotNull
    private final JsonAdapter<Integer> intAdapter;
    @NotNull
    private final JsonAdapter<Boolean> nullableBooleanAdapter;
    @NotNull
    private final JsonAdapter<List<String>> nullableListOfStringAdapter;
    @NotNull
    private final JsonAdapter<Long> nullableLongAdapter;
    @NotNull
    private final JsonAdapter<String> nullableStringAdapter;
    @NotNull
    private final JsonReader.Options options;
    @NotNull
    private final JsonAdapter<String> stringAdapter;
    @NotNull
    private final JsonAdapter<Topic> topicAtQualifierAdapter;
    @NotNull
    private final JsonAdapter<Ttl> ttlAtQualifierAdapter;

    public RelayDTO_Publish_Request_ParamsJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of(PushMessagingService.KEY_TOPIC, PushMessagingService.KEY_MESSAGE, "ttl", PushMessagingService.KEY_TAG, "prompt", "correlationId", "rpcMethods", "chainId", "txHashes", "contractAddresses");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        JsonAdapter<Topic> adapter = moshi.adapter(Topic.class, SetsKt.setOf(new RelayDTO_Publish_Request_ParamsJsonAdapter$annotationImpl$com_reown_foundation_common_adapters_TopicAdapter_Qualifier$0()), PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullExpressionValue(adapter, "adapter(...)");
        this.topicAtQualifierAdapter = adapter;
        Class<String> cls = String.class;
        this.stringAdapter = a.h(moshi, cls, PushMessagingService.KEY_MESSAGE, "adapter(...)");
        JsonAdapter<Ttl> adapter2 = moshi.adapter(Ttl.class, SetsKt.setOf(new RelayDTO_Publish_Request_ParamsJsonAdapter$annotationImpl$com_reown_foundation_common_adapters_TtlAdapter_Qualifier$0()), "ttl");
        Intrinsics.checkNotNullExpressionValue(adapter2, "adapter(...)");
        this.ttlAtQualifierAdapter = adapter2;
        this.intAdapter = a.h(moshi, Integer.TYPE, PushMessagingService.KEY_TAG, "adapter(...)");
        this.nullableBooleanAdapter = a.h(moshi, Boolean.class, "prompt", "adapter(...)");
        this.nullableLongAdapter = a.h(moshi, Long.class, "correlationId", "adapter(...)");
        this.nullableListOfStringAdapter = a.i(moshi, Types.newParameterizedType(List.class, cls), "rpcMethods", "adapter(...)");
        this.nullableStringAdapter = a.h(moshi, cls, "chainId", "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(53, "GeneratedJsonAdapter(RelayDTO.Publish.Request.Params)");
    }

    @NotNull
    public RelayDTO.Publish.Request.Params fromJson(@NotNull JsonReader jsonReader) {
        JsonReader jsonReader2 = jsonReader;
        Intrinsics.checkNotNullParameter(jsonReader2, "reader");
        jsonReader.beginObject();
        Integer num = null;
        Topic topic = null;
        String str = null;
        Ttl ttl = null;
        Boolean bool = null;
        Long l2 = null;
        List list = null;
        String str2 = null;
        List list2 = null;
        List list3 = null;
        while (true) {
            List list4 = list3;
            if (jsonReader.hasNext()) {
                switch (jsonReader2.selectName(this.options)) {
                    case -1:
                        jsonReader.skipName();
                        jsonReader.skipValue();
                        break;
                    case 0:
                        topic = this.topicAtQualifierAdapter.fromJson(jsonReader2);
                        if (topic == null) {
                            throw Util.unexpectedNull(PushMessagingService.KEY_TOPIC, PushMessagingService.KEY_TOPIC, jsonReader2);
                        }
                        break;
                    case 1:
                        str = this.stringAdapter.fromJson(jsonReader2);
                        if (str == null) {
                            throw Util.unexpectedNull(PushMessagingService.KEY_MESSAGE, PushMessagingService.KEY_MESSAGE, jsonReader2);
                        }
                        break;
                    case 2:
                        ttl = this.ttlAtQualifierAdapter.fromJson(jsonReader2);
                        if (ttl == null) {
                            throw Util.unexpectedNull("ttl", "ttl", jsonReader2);
                        }
                        break;
                    case 3:
                        num = this.intAdapter.fromJson(jsonReader2);
                        if (num == null) {
                            throw Util.unexpectedNull(PushMessagingService.KEY_TAG, PushMessagingService.KEY_TAG, jsonReader2);
                        }
                        break;
                    case 4:
                        bool = this.nullableBooleanAdapter.fromJson(jsonReader2);
                        break;
                    case 5:
                        l2 = this.nullableLongAdapter.fromJson(jsonReader2);
                        break;
                    case 6:
                        list = this.nullableListOfStringAdapter.fromJson(jsonReader2);
                        break;
                    case 7:
                        str2 = this.nullableStringAdapter.fromJson(jsonReader2);
                        break;
                    case 8:
                        list2 = this.nullableListOfStringAdapter.fromJson(jsonReader2);
                        break;
                    case 9:
                        list3 = this.nullableListOfStringAdapter.fromJson(jsonReader2);
                        continue;
                }
                list3 = list4;
            } else {
                jsonReader.endObject();
                if (topic == null) {
                    throw Util.missingProperty(PushMessagingService.KEY_TOPIC, PushMessagingService.KEY_TOPIC, jsonReader2);
                } else if (str == null) {
                    throw Util.missingProperty(PushMessagingService.KEY_MESSAGE, PushMessagingService.KEY_MESSAGE, jsonReader2);
                } else if (ttl == null) {
                    throw Util.missingProperty("ttl", "ttl", jsonReader2);
                } else if (num != null) {
                    return new RelayDTO.Publish.Request.Params(topic, str, ttl, num.intValue(), bool, l2, list, str2, list2, list4);
                } else {
                    throw Util.missingProperty(PushMessagingService.KEY_TAG, PushMessagingService.KEY_TAG, jsonReader2);
                }
            }
        }
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable RelayDTO.Publish.Request.Params params) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (params != null) {
            jsonWriter.beginObject();
            jsonWriter.name(PushMessagingService.KEY_TOPIC);
            this.topicAtQualifierAdapter.toJson(jsonWriter, params.getTopic());
            jsonWriter.name(PushMessagingService.KEY_MESSAGE);
            this.stringAdapter.toJson(jsonWriter, params.getMessage());
            jsonWriter.name("ttl");
            this.ttlAtQualifierAdapter.toJson(jsonWriter, params.getTtl());
            jsonWriter.name(PushMessagingService.KEY_TAG);
            this.intAdapter.toJson(jsonWriter, Integer.valueOf(params.getTag()));
            jsonWriter.name("prompt");
            this.nullableBooleanAdapter.toJson(jsonWriter, params.getPrompt());
            jsonWriter.name("correlationId");
            this.nullableLongAdapter.toJson(jsonWriter, params.getCorrelationId());
            jsonWriter.name("rpcMethods");
            this.nullableListOfStringAdapter.toJson(jsonWriter, params.getRpcMethods());
            jsonWriter.name("chainId");
            this.nullableStringAdapter.toJson(jsonWriter, params.getChainId());
            jsonWriter.name("txHashes");
            this.nullableListOfStringAdapter.toJson(jsonWriter, params.getTxHashes());
            jsonWriter.name("contractAddresses");
            this.nullableListOfStringAdapter.toJson(jsonWriter, params.getContractAddresses());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
