package com.reown.foundation.network.model;

import A.a;
import com.reown.foundation.network.model.RelayDTO;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.internal.Util;
import java.lang.reflect.Constructor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0011\u001a\u00020\fH\u0016J\u0010\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u001a\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0010X\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO_Publish_Result_AcknowledgementJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/foundation/network/model/RelayDTO$Publish$Result$Acknowledgement;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "longAdapter", "", "stringAdapter", "", "booleanAdapter", "", "constructorRef", "Ljava/lang/reflect/Constructor;", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nRelayDTO_Publish_Result_AcknowledgementJsonAdapter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RelayDTO_Publish_Result_AcknowledgementJsonAdapter.kt\ncom/reown/foundation/network/model/RelayDTO_Publish_Result_AcknowledgementJsonAdapter\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,110:1\n1#2:111\n*E\n"})
public final class RelayDTO_Publish_Result_AcknowledgementJsonAdapter extends JsonAdapter<RelayDTO.Publish.Result.Acknowledgement> {
    @NotNull
    private final JsonAdapter<Boolean> booleanAdapter;
    @Nullable
    private volatile Constructor<RelayDTO.Publish.Result.Acknowledgement> constructorRef;
    @NotNull
    private final JsonAdapter<Long> longAdapter;
    @NotNull
    private final JsonReader.Options options;
    @NotNull
    private final JsonAdapter<String> stringAdapter;

    public RelayDTO_Publish_Result_AcknowledgementJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of(TtmlNode.ATTR_ID, "jsonrpc", "result");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        this.longAdapter = a.h(moshi, Long.TYPE, TtmlNode.ATTR_ID, "adapter(...)");
        this.stringAdapter = a.h(moshi, String.class, "jsonrpc", "adapter(...)");
        this.booleanAdapter = a.h(moshi, Boolean.TYPE, "result", "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(61, "GeneratedJsonAdapter(RelayDTO.Publish.Result.Acknowledgement)");
    }

    @NotNull
    public RelayDTO.Publish.Result.Acknowledgement fromJson(@NotNull JsonReader jsonReader) {
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        jsonReader.beginObject();
        Long l2 = null;
        String str = null;
        Boolean bool = null;
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
            } else if (selectName == 2 && (bool = this.booleanAdapter.fromJson(jsonReader)) == null) {
                throw Util.unexpectedNull("result", "result", jsonReader);
            }
        }
        jsonReader.endObject();
        if (i3 != -3) {
            Constructor<RelayDTO.Publish.Result.Acknowledgement> constructor = this.constructorRef;
            if (constructor == null) {
                constructor = RelayDTO.Publish.Result.Acknowledgement.class.getDeclaredConstructor(new Class[]{Long.TYPE, String.class, Boolean.TYPE, Integer.TYPE, Util.DEFAULT_CONSTRUCTOR_MARKER});
                this.constructorRef = constructor;
                Intrinsics.checkNotNullExpressionValue(constructor, "also(...)");
            }
            if (l2 == null) {
                throw Util.missingProperty(TtmlNode.ATTR_ID, TtmlNode.ATTR_ID, jsonReader);
            } else if (bool != null) {
                RelayDTO.Publish.Result.Acknowledgement newInstance = constructor.newInstance(new Object[]{l2, str, bool, Integer.valueOf(i3), null});
                Intrinsics.checkNotNullExpressionValue(newInstance, "newInstance(...)");
                return newInstance;
            } else {
                throw Util.missingProperty("result", "result", jsonReader);
            }
        } else if (l2 != null) {
            long longValue = l2.longValue();
            Intrinsics.checkNotNull(str, "null cannot be cast to non-null type kotlin.String");
            if (bool != null) {
                return new RelayDTO.Publish.Result.Acknowledgement(longValue, str, bool.booleanValue());
            }
            throw Util.missingProperty("result", "result", jsonReader);
        } else {
            throw Util.missingProperty(TtmlNode.ATTR_ID, TtmlNode.ATTR_ID, jsonReader);
        }
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable RelayDTO.Publish.Result.Acknowledgement acknowledgement) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (acknowledgement != null) {
            jsonWriter.beginObject();
            jsonWriter.name(TtmlNode.ATTR_ID);
            this.longAdapter.toJson(jsonWriter, Long.valueOf(acknowledgement.getId()));
            jsonWriter.name("jsonrpc");
            this.stringAdapter.toJson(jsonWriter, acknowledgement.getJsonrpc());
            jsonWriter.name("result");
            this.booleanAdapter.toJson(jsonWriter, Boolean.valueOf(acknowledgement.getResult()));
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
