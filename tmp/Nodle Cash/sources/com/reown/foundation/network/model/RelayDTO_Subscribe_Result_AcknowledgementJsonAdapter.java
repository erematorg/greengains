package com.reown.foundation.network.model;

import A.a;
import com.reown.foundation.common.model.SubscriptionId;
import com.reown.foundation.network.model.RelayDTO;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.internal.Util;
import java.lang.reflect.Constructor;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0011\u001a\u00020\fH\u0016J\u0010\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u001a\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0010X\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO_Subscribe_Result_AcknowledgementJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/foundation/network/model/RelayDTO$Subscribe$Result$Acknowledgement;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "longAdapter", "", "stringAdapter", "", "subscriptionIdAtQualifierAdapter", "Lcom/reown/foundation/common/model/SubscriptionId;", "constructorRef", "Ljava/lang/reflect/Constructor;", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nRelayDTO_Subscribe_Result_AcknowledgementJsonAdapter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RelayDTO_Subscribe_Result_AcknowledgementJsonAdapter.kt\ncom/reown/foundation/network/model/RelayDTO_Subscribe_Result_AcknowledgementJsonAdapter\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,111:1\n1#2:112\n*E\n"})
public final class RelayDTO_Subscribe_Result_AcknowledgementJsonAdapter extends JsonAdapter<RelayDTO.Subscribe.Result.Acknowledgement> {
    @Nullable
    private volatile Constructor<RelayDTO.Subscribe.Result.Acknowledgement> constructorRef;
    @NotNull
    private final JsonAdapter<Long> longAdapter;
    @NotNull
    private final JsonReader.Options options;
    @NotNull
    private final JsonAdapter<String> stringAdapter;
    @NotNull
    private final JsonAdapter<SubscriptionId> subscriptionIdAtQualifierAdapter;

    public RelayDTO_Subscribe_Result_AcknowledgementJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of(TtmlNode.ATTR_ID, "jsonrpc", "result");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        this.longAdapter = a.h(moshi, Long.TYPE, TtmlNode.ATTR_ID, "adapter(...)");
        this.stringAdapter = a.h(moshi, String.class, "jsonrpc", "adapter(...)");
        JsonAdapter<SubscriptionId> adapter = moshi.adapter(SubscriptionId.class, SetsKt.setOf(new RelayDTO_Subscribe_Result_AcknowledgementJsonAdapter$annotationImpl$com_reown_foundation_common_adapters_SubscriptionIdAdapter_Qualifier$0()), "result");
        Intrinsics.checkNotNullExpressionValue(adapter, "adapter(...)");
        this.subscriptionIdAtQualifierAdapter = adapter;
    }

    @NotNull
    public String toString() {
        return a.j(63, "GeneratedJsonAdapter(RelayDTO.Subscribe.Result.Acknowledgement)");
    }

    @NotNull
    public RelayDTO.Subscribe.Result.Acknowledgement fromJson(@NotNull JsonReader jsonReader) {
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        jsonReader.beginObject();
        Long l2 = null;
        String str = null;
        SubscriptionId subscriptionId = null;
        int i3 = -1;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(this.options);
            if (selectName == -1) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else if (selectName == 0) {
                l2 = this.longAdapter.fromJson(jsonReader);
                if (l2 == null) {
                    throw Util.unexpectedNull(TtmlNode.ATTR_ID, TtmlNode.ATTR_ID, jsonReader);
                }
            } else if (selectName == 1) {
                str = this.stringAdapter.fromJson(jsonReader);
                if (str != null) {
                    i3 = -3;
                } else {
                    throw Util.unexpectedNull("jsonrpc", "jsonrpc", jsonReader);
                }
            } else if (selectName == 2 && (subscriptionId = this.subscriptionIdAtQualifierAdapter.fromJson(jsonReader)) == null) {
                throw Util.unexpectedNull("result", "result", jsonReader);
            }
        }
        jsonReader.endObject();
        if (i3 != -3) {
            Constructor<RelayDTO.Subscribe.Result.Acknowledgement> constructor = this.constructorRef;
            if (constructor == null) {
                constructor = RelayDTO.Subscribe.Result.Acknowledgement.class.getDeclaredConstructor(new Class[]{Long.TYPE, String.class, SubscriptionId.class, Integer.TYPE, Util.DEFAULT_CONSTRUCTOR_MARKER});
                this.constructorRef = constructor;
                Intrinsics.checkNotNullExpressionValue(constructor, "also(...)");
            }
            if (l2 == null) {
                throw Util.missingProperty(TtmlNode.ATTR_ID, TtmlNode.ATTR_ID, jsonReader);
            } else if (subscriptionId != null) {
                RelayDTO.Subscribe.Result.Acknowledgement newInstance = constructor.newInstance(new Object[]{l2, str, subscriptionId, Integer.valueOf(i3), null});
                Intrinsics.checkNotNullExpressionValue(newInstance, "newInstance(...)");
                return newInstance;
            } else {
                throw Util.missingProperty("result", "result", jsonReader);
            }
        } else if (l2 != null) {
            long longValue = l2.longValue();
            Intrinsics.checkNotNull(str, "null cannot be cast to non-null type kotlin.String");
            if (subscriptionId != null) {
                return new RelayDTO.Subscribe.Result.Acknowledgement(longValue, str, subscriptionId);
            }
            throw Util.missingProperty("result", "result", jsonReader);
        } else {
            throw Util.missingProperty(TtmlNode.ATTR_ID, TtmlNode.ATTR_ID, jsonReader);
        }
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable RelayDTO.Subscribe.Result.Acknowledgement acknowledgement) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (acknowledgement != null) {
            jsonWriter.beginObject();
            jsonWriter.name(TtmlNode.ATTR_ID);
            this.longAdapter.toJson(jsonWriter, Long.valueOf(acknowledgement.getId()));
            jsonWriter.name("jsonrpc");
            this.stringAdapter.toJson(jsonWriter, acknowledgement.getJsonrpc());
            jsonWriter.name("result");
            this.subscriptionIdAtQualifierAdapter.toJson(jsonWriter, acknowledgement.getResult());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
