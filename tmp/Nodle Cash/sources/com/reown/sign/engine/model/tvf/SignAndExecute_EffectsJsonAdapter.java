package com.reown.sign.engine.model.tvf;

import A.a;
import androidx.core.app.NotificationCompat;
import com.reown.sign.engine.model.tvf.SignAndExecute;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u001a\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/reown/sign/engine/model/tvf/SignAndExecute_EffectsJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/sign/engine/model/tvf/SignAndExecute$Effects;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "nullableStatusAdapter", "Lcom/reown/sign/engine/model/tvf/SignAndExecute$Status;", "nullableGasUsedAdapter", "Lcom/reown/sign/engine/model/tvf/SignAndExecute$GasUsed;", "toString", "", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SignAndExecute_EffectsJsonAdapter extends JsonAdapter<SignAndExecute.Effects> {
    @NotNull
    private final JsonAdapter<SignAndExecute.GasUsed> nullableGasUsedAdapter;
    @NotNull
    private final JsonAdapter<SignAndExecute.Status> nullableStatusAdapter;
    @NotNull
    private final JsonReader.Options options;

    public SignAndExecute_EffectsJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of(NotificationCompat.CATEGORY_STATUS, "gas_used");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        this.nullableStatusAdapter = a.h(moshi, SignAndExecute.Status.class, NotificationCompat.CATEGORY_STATUS, "adapter(...)");
        this.nullableGasUsedAdapter = a.h(moshi, SignAndExecute.GasUsed.class, "gas_used", "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(44, "GeneratedJsonAdapter(SignAndExecute.Effects)");
    }

    @NotNull
    public SignAndExecute.Effects fromJson(@NotNull JsonReader jsonReader) {
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        jsonReader.beginObject();
        SignAndExecute.Status status = null;
        SignAndExecute.GasUsed gasUsed = null;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(this.options);
            if (selectName == -1) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else if (selectName == 0) {
                status = this.nullableStatusAdapter.fromJson(jsonReader);
            } else if (selectName == 1) {
                gasUsed = this.nullableGasUsedAdapter.fromJson(jsonReader);
            }
        }
        jsonReader.endObject();
        return new SignAndExecute.Effects(status, gasUsed);
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable SignAndExecute.Effects effects) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (effects != null) {
            jsonWriter.beginObject();
            jsonWriter.name(NotificationCompat.CATEGORY_STATUS);
            this.nullableStatusAdapter.toJson(jsonWriter, effects.getStatus());
            jsonWriter.name("gas_used");
            this.nullableGasUsedAdapter.toJson(jsonWriter, effects.getGas_used());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
