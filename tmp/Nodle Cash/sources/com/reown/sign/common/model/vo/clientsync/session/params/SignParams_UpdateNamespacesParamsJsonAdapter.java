package com.reown.sign.common.model.vo.clientsync.session.params;

import A.a;
import com.reown.android.internal.common.model.Namespace;
import com.reown.sign.common.model.vo.clientsync.session.params.SignParams;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import com.squareup.moshi.internal.Util;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\r\u001a\u00020\u000bH\u0016J\u0010\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u001a\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R \u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n0\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams_UpdateNamespacesParamsJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$UpdateNamespacesParams;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "mapOfStringSessionAdapter", "", "", "Lcom/reown/android/internal/common/model/Namespace$Session;", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SignParams_UpdateNamespacesParamsJsonAdapter extends JsonAdapter<SignParams.UpdateNamespacesParams> {
    @NotNull
    private final JsonAdapter<Map<String, Namespace.Session>> mapOfStringSessionAdapter;
    @NotNull
    private final JsonReader.Options options;

    public SignParams_UpdateNamespacesParamsJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of("namespaces");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        this.mapOfStringSessionAdapter = a.i(moshi, Types.newParameterizedType(Map.class, String.class, Namespace.Session.class), "namespaces", "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(55, "GeneratedJsonAdapter(SignParams.UpdateNamespacesParams)");
    }

    @NotNull
    public SignParams.UpdateNamespacesParams fromJson(@NotNull JsonReader jsonReader) {
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        jsonReader.beginObject();
        Map map = null;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(this.options);
            if (selectName == -1) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else if (selectName == 0 && (map = this.mapOfStringSessionAdapter.fromJson(jsonReader)) == null) {
                throw Util.unexpectedNull("namespaces", "namespaces", jsonReader);
            }
        }
        jsonReader.endObject();
        if (map != null) {
            return new SignParams.UpdateNamespacesParams(map);
        }
        throw Util.missingProperty("namespaces", "namespaces", jsonReader);
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable SignParams.UpdateNamespacesParams updateNamespacesParams) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (updateNamespacesParams != null) {
            jsonWriter.beginObject();
            jsonWriter.name("namespaces");
            this.mapOfStringSessionAdapter.toJson(jsonWriter, updateNamespacesParams.getNamespaces());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
