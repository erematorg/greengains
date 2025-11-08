package com.reown.sign.engine.model.tvf;

import A.a;
import com.reown.sign.engine.model.tvf.SignAndExecute;
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

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0014\u001a\u00020\nH\u0016J\u0010\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u001a\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0001X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e0\u0001X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0010\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u000e0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/reown/sign/engine/model/tvf/SignAndExecute_SuiTransactionResponseJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/sign/engine/model/tvf/SignAndExecute$SuiTransactionResponse;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "stringAdapter", "", "nullableEffectsAdapter", "Lcom/reown/sign/engine/model/tvf/SignAndExecute$Effects;", "nullableListOfEventAdapter", "", "Lcom/reown/sign/engine/model/tvf/SignAndExecute$Event;", "nullableListOfObjectChangeAdapter", "Lcom/reown/sign/engine/model/tvf/SignAndExecute$ObjectChange;", "nullableBooleanAdapter", "", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SignAndExecute_SuiTransactionResponseJsonAdapter extends JsonAdapter<SignAndExecute.SuiTransactionResponse> {
    @NotNull
    private final JsonAdapter<Boolean> nullableBooleanAdapter;
    @NotNull
    private final JsonAdapter<SignAndExecute.Effects> nullableEffectsAdapter;
    @NotNull
    private final JsonAdapter<List<SignAndExecute.Event>> nullableListOfEventAdapter;
    @NotNull
    private final JsonAdapter<List<SignAndExecute.ObjectChange>> nullableListOfObjectChangeAdapter;
    @NotNull
    private final JsonReader.Options options;
    @NotNull
    private final JsonAdapter<String> stringAdapter;

    public SignAndExecute_SuiTransactionResponseJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of("digest", "effects", "events", "object_changes", "confirmed_local_execution");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        this.stringAdapter = a.h(moshi, String.class, "digest", "adapter(...)");
        this.nullableEffectsAdapter = a.h(moshi, SignAndExecute.Effects.class, "effects", "adapter(...)");
        Class<List> cls = List.class;
        this.nullableListOfEventAdapter = a.i(moshi, Types.newParameterizedType(cls, SignAndExecute.Event.class), "events", "adapter(...)");
        this.nullableListOfObjectChangeAdapter = a.i(moshi, Types.newParameterizedType(cls, SignAndExecute.ObjectChange.class), "object_changes", "adapter(...)");
        this.nullableBooleanAdapter = a.h(moshi, Boolean.class, "confirmed_local_execution", "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(59, "GeneratedJsonAdapter(SignAndExecute.SuiTransactionResponse)");
    }

    @NotNull
    public SignAndExecute.SuiTransactionResponse fromJson(@NotNull JsonReader jsonReader) {
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        jsonReader.beginObject();
        String str = null;
        SignAndExecute.Effects effects = null;
        List list = null;
        List list2 = null;
        Boolean bool = null;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(this.options);
            if (selectName == -1) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else if (selectName == 0) {
                str = this.stringAdapter.fromJson(jsonReader);
                if (str == null) {
                    throw Util.unexpectedNull("digest", "digest", jsonReader);
                }
            } else if (selectName == 1) {
                effects = this.nullableEffectsAdapter.fromJson(jsonReader);
            } else if (selectName == 2) {
                list = this.nullableListOfEventAdapter.fromJson(jsonReader);
            } else if (selectName == 3) {
                list2 = this.nullableListOfObjectChangeAdapter.fromJson(jsonReader);
            } else if (selectName == 4) {
                bool = this.nullableBooleanAdapter.fromJson(jsonReader);
            }
        }
        jsonReader.endObject();
        if (str != null) {
            return new SignAndExecute.SuiTransactionResponse(str, effects, list, list2, bool);
        }
        throw Util.missingProperty("digest", "digest", jsonReader);
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable SignAndExecute.SuiTransactionResponse suiTransactionResponse) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (suiTransactionResponse != null) {
            jsonWriter.beginObject();
            jsonWriter.name("digest");
            this.stringAdapter.toJson(jsonWriter, suiTransactionResponse.getDigest());
            jsonWriter.name("effects");
            this.nullableEffectsAdapter.toJson(jsonWriter, suiTransactionResponse.getEffects());
            jsonWriter.name("events");
            this.nullableListOfEventAdapter.toJson(jsonWriter, suiTransactionResponse.getEvents());
            jsonWriter.name("object_changes");
            this.nullableListOfObjectChangeAdapter.toJson(jsonWriter, suiTransactionResponse.getObject_changes());
            jsonWriter.name("confirmed_local_execution");
            this.nullableBooleanAdapter.toJson(jsonWriter, suiTransactionResponse.getConfirmed_local_execution());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
