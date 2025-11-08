package com.reown.android.internal.common.model.params;

import A.a;
import com.reown.android.internal.common.model.Participant;
import com.reown.android.internal.common.model.params.CoreSignParams;
import com.reown.android.internal.common.signing.cacao.Cacao;
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

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001a\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/reown/android/internal/common/model/params/CoreSignParams_SessionAuthenticateApproveParamsJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/android/internal/common/model/params/CoreSignParams$SessionAuthenticateApproveParams;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "participantAdapter", "Lcom/reown/android/internal/common/model/Participant;", "listOfCacaoAdapter", "", "Lcom/reown/android/internal/common/signing/cacao/Cacao;", "toString", "", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CoreSignParams_SessionAuthenticateApproveParamsJsonAdapter extends JsonAdapter<CoreSignParams.SessionAuthenticateApproveParams> {
    @NotNull
    private final JsonAdapter<List<Cacao>> listOfCacaoAdapter;
    @NotNull
    private final JsonReader.Options options;
    @NotNull
    private final JsonAdapter<Participant> participantAdapter;

    public CoreSignParams_SessionAuthenticateApproveParamsJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of("responder", "cacaos");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        this.participantAdapter = a.h(moshi, Participant.class, "responder", "adapter(...)");
        this.listOfCacaoAdapter = a.i(moshi, Types.newParameterizedType(List.class, Cacao.class), "cacaos", "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(69, "GeneratedJsonAdapter(CoreSignParams.SessionAuthenticateApproveParams)");
    }

    @NotNull
    public CoreSignParams.SessionAuthenticateApproveParams fromJson(@NotNull JsonReader jsonReader) {
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        jsonReader.beginObject();
        Participant participant = null;
        List list = null;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(this.options);
            if (selectName == -1) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else if (selectName == 0) {
                participant = this.participantAdapter.fromJson(jsonReader);
                if (participant == null) {
                    throw Util.unexpectedNull("responder", "responder", jsonReader);
                }
            } else if (selectName == 1 && (list = this.listOfCacaoAdapter.fromJson(jsonReader)) == null) {
                throw Util.unexpectedNull("cacaos", "cacaos", jsonReader);
            }
        }
        jsonReader.endObject();
        if (participant == null) {
            throw Util.missingProperty("responder", "responder", jsonReader);
        } else if (list != null) {
            return new CoreSignParams.SessionAuthenticateApproveParams(participant, list);
        } else {
            throw Util.missingProperty("cacaos", "cacaos", jsonReader);
        }
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable CoreSignParams.SessionAuthenticateApproveParams sessionAuthenticateApproveParams) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (sessionAuthenticateApproveParams != null) {
            jsonWriter.beginObject();
            jsonWriter.name("responder");
            this.participantAdapter.toJson(jsonWriter, sessionAuthenticateApproveParams.getResponder());
            jsonWriter.name("cacaos");
            this.listOfCacaoAdapter.toJson(jsonWriter, sessionAuthenticateApproveParams.getCacaos());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
