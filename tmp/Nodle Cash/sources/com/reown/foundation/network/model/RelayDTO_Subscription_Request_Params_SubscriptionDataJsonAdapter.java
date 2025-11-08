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

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0012\u001a\u00020\fH\u0016J\u0010\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u001a\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO_Subscription_Request_Params_SubscriptionDataJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/foundation/network/model/RelayDTO$Subscription$Request$Params$SubscriptionData;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "topicAtQualifierAdapter", "Lcom/reown/foundation/common/model/Topic;", "stringAdapter", "", "longAdapter", "", "nullableStringAdapter", "intAdapter", "", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RelayDTO_Subscription_Request_Params_SubscriptionDataJsonAdapter extends JsonAdapter<RelayDTO.Subscription.Request.Params.SubscriptionData> {
    @NotNull
    private final JsonAdapter<Integer> intAdapter;
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

    public RelayDTO_Subscription_Request_Params_SubscriptionDataJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of(PushMessagingService.KEY_TOPIC, PushMessagingService.KEY_MESSAGE, "publishedAt", "attestation", PushMessagingService.KEY_TAG);
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        JsonAdapter<Topic> adapter = moshi.adapter(Topic.class, SetsKt.setOf(new RelayDTO_Subscription_Request_Params_SubscriptionDataJsonAdapter$annotationImpl$com_reown_foundation_common_adapters_TopicAdapter_Qualifier$0()), PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullExpressionValue(adapter, "adapter(...)");
        this.topicAtQualifierAdapter = adapter;
        Class<String> cls = String.class;
        this.stringAdapter = a.h(moshi, cls, PushMessagingService.KEY_MESSAGE, "adapter(...)");
        this.longAdapter = a.h(moshi, Long.TYPE, "publishedAt", "adapter(...)");
        this.nullableStringAdapter = a.h(moshi, cls, "attestation", "adapter(...)");
        this.intAdapter = a.h(moshi, Integer.TYPE, PushMessagingService.KEY_TAG, "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(75, "GeneratedJsonAdapter(RelayDTO.Subscription.Request.Params.SubscriptionData)");
    }

    @NotNull
    public RelayDTO.Subscription.Request.Params.SubscriptionData fromJson(@NotNull JsonReader jsonReader) {
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        jsonReader.beginObject();
        Long l2 = null;
        Integer num = null;
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
                    throw Util.unexpectedNull(PushMessagingService.KEY_TOPIC, PushMessagingService.KEY_TOPIC, jsonReader);
                }
            } else if (selectName == 1) {
                str = this.stringAdapter.fromJson(jsonReader);
                if (str == null) {
                    throw Util.unexpectedNull(PushMessagingService.KEY_MESSAGE, PushMessagingService.KEY_MESSAGE, jsonReader);
                }
            } else if (selectName == 2) {
                l2 = this.longAdapter.fromJson(jsonReader);
                if (l2 == null) {
                    throw Util.unexpectedNull("publishedAt", "publishedAt", jsonReader);
                }
            } else if (selectName == 3) {
                str2 = this.nullableStringAdapter.fromJson(jsonReader);
            } else if (selectName == 4 && (num = this.intAdapter.fromJson(jsonReader)) == null) {
                throw Util.unexpectedNull(PushMessagingService.KEY_TAG, PushMessagingService.KEY_TAG, jsonReader);
            }
        }
        jsonReader.endObject();
        if (topic == null) {
            throw Util.missingProperty(PushMessagingService.KEY_TOPIC, PushMessagingService.KEY_TOPIC, jsonReader);
        } else if (str == null) {
            throw Util.missingProperty(PushMessagingService.KEY_MESSAGE, PushMessagingService.KEY_MESSAGE, jsonReader);
        } else if (l2 != null) {
            long longValue = l2.longValue();
            if (num != null) {
                return new RelayDTO.Subscription.Request.Params.SubscriptionData(topic, str, longValue, str2, num.intValue());
            }
            throw Util.missingProperty(PushMessagingService.KEY_TAG, PushMessagingService.KEY_TAG, jsonReader);
        } else {
            throw Util.missingProperty("publishedAt", "publishedAt", jsonReader);
        }
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable RelayDTO.Subscription.Request.Params.SubscriptionData subscriptionData) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (subscriptionData != null) {
            jsonWriter.beginObject();
            jsonWriter.name(PushMessagingService.KEY_TOPIC);
            this.topicAtQualifierAdapter.toJson(jsonWriter, subscriptionData.getTopic());
            jsonWriter.name(PushMessagingService.KEY_MESSAGE);
            this.stringAdapter.toJson(jsonWriter, subscriptionData.getMessage());
            jsonWriter.name("publishedAt");
            this.longAdapter.toJson(jsonWriter, Long.valueOf(subscriptionData.getPublishedAt()));
            jsonWriter.name("attestation");
            this.nullableStringAdapter.toJson(jsonWriter, subscriptionData.getAttestation());
            jsonWriter.name(PushMessagingService.KEY_TAG);
            this.intAdapter.toJson(jsonWriter, Integer.valueOf(subscriptionData.getTag()));
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
