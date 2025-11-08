package com.reown.sign.common.model.vo.clientsync.session.params;

import A.a;
import com.reown.android.internal.common.model.Namespace;
import com.reown.android.internal.common.model.RelayProtocolOptions;
import com.reown.sign.common.model.vo.clientsync.common.SessionParticipant;
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
import org.apache.commons.text.lookup.StringLookupFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0014\u001a\u00020\u000fH\u0016J\u0010\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u001a\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0001X\u0004¢\u0006\u0002\n\u0000R \u0010\r\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u0001X\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0013\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e0\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams_SessionSettleParamsJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionSettleParams;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "relayProtocolOptionsAdapter", "Lcom/reown/android/internal/common/model/RelayProtocolOptions;", "sessionParticipantAdapter", "Lcom/reown/sign/common/model/vo/clientsync/common/SessionParticipant;", "mapOfStringSessionAdapter", "", "", "Lcom/reown/android/internal/common/model/Namespace$Session;", "longAdapter", "", "nullableMapOfStringStringAdapter", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SignParams_SessionSettleParamsJsonAdapter extends JsonAdapter<SignParams.SessionSettleParams> {
    @NotNull
    private final JsonAdapter<Long> longAdapter;
    @NotNull
    private final JsonAdapter<Map<String, Namespace.Session>> mapOfStringSessionAdapter;
    @NotNull
    private final JsonAdapter<Map<String, String>> nullableMapOfStringStringAdapter;
    @NotNull
    private final JsonReader.Options options;
    @NotNull
    private final JsonAdapter<RelayProtocolOptions> relayProtocolOptionsAdapter;
    @NotNull
    private final JsonAdapter<SessionParticipant> sessionParticipantAdapter;

    public SignParams_SessionSettleParamsJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of("relay", "controller", "namespaces", "expiry", "sessionProperties", "scopedProperties");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        this.relayProtocolOptionsAdapter = a.h(moshi, RelayProtocolOptions.class, "relay", "adapter(...)");
        this.sessionParticipantAdapter = a.h(moshi, SessionParticipant.class, "controller", "adapter(...)");
        Class<String> cls = String.class;
        Class<Map> cls2 = Map.class;
        this.mapOfStringSessionAdapter = a.i(moshi, Types.newParameterizedType(cls2, cls, Namespace.Session.class), "namespaces", "adapter(...)");
        this.longAdapter = a.h(moshi, Long.TYPE, "expiry", "adapter(...)");
        this.nullableMapOfStringStringAdapter = a.i(moshi, Types.newParameterizedType(cls2, cls, cls), StringLookupFactory.KEY_PROPERTIES, "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(52, "GeneratedJsonAdapter(SignParams.SessionSettleParams)");
    }

    @NotNull
    public SignParams.SessionSettleParams fromJson(@NotNull JsonReader jsonReader) {
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        jsonReader.beginObject();
        Long l2 = null;
        RelayProtocolOptions relayProtocolOptions = null;
        SessionParticipant sessionParticipant = null;
        Map map = null;
        Map map2 = null;
        Map map3 = null;
        while (jsonReader.hasNext()) {
            switch (jsonReader.selectName(this.options)) {
                case -1:
                    jsonReader.skipName();
                    jsonReader.skipValue();
                    break;
                case 0:
                    relayProtocolOptions = this.relayProtocolOptionsAdapter.fromJson(jsonReader);
                    if (relayProtocolOptions != null) {
                        break;
                    } else {
                        throw Util.unexpectedNull("relay", "relay", jsonReader);
                    }
                case 1:
                    sessionParticipant = this.sessionParticipantAdapter.fromJson(jsonReader);
                    if (sessionParticipant != null) {
                        break;
                    } else {
                        throw Util.unexpectedNull("controller", "controller", jsonReader);
                    }
                case 2:
                    map = this.mapOfStringSessionAdapter.fromJson(jsonReader);
                    if (map != null) {
                        break;
                    } else {
                        throw Util.unexpectedNull("namespaces", "namespaces", jsonReader);
                    }
                case 3:
                    l2 = this.longAdapter.fromJson(jsonReader);
                    if (l2 != null) {
                        break;
                    } else {
                        throw Util.unexpectedNull("expiry", "expiry", jsonReader);
                    }
                case 4:
                    map2 = this.nullableMapOfStringStringAdapter.fromJson(jsonReader);
                    break;
                case 5:
                    map3 = this.nullableMapOfStringStringAdapter.fromJson(jsonReader);
                    break;
            }
        }
        jsonReader.endObject();
        if (relayProtocolOptions == null) {
            throw Util.missingProperty("relay", "relay", jsonReader);
        } else if (sessionParticipant == null) {
            throw Util.missingProperty("controller", "controller", jsonReader);
        } else if (map == null) {
            throw Util.missingProperty("namespaces", "namespaces", jsonReader);
        } else if (l2 != null) {
            return new SignParams.SessionSettleParams(relayProtocolOptions, sessionParticipant, map, l2.longValue(), map2, map3);
        } else {
            throw Util.missingProperty("expiry", "expiry", jsonReader);
        }
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable SignParams.SessionSettleParams sessionSettleParams) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (sessionSettleParams != null) {
            jsonWriter.beginObject();
            jsonWriter.name("relay");
            this.relayProtocolOptionsAdapter.toJson(jsonWriter, sessionSettleParams.getRelay());
            jsonWriter.name("controller");
            this.sessionParticipantAdapter.toJson(jsonWriter, sessionSettleParams.getController());
            jsonWriter.name("namespaces");
            this.mapOfStringSessionAdapter.toJson(jsonWriter, sessionSettleParams.getNamespaces());
            jsonWriter.name("expiry");
            this.longAdapter.toJson(jsonWriter, Long.valueOf(sessionSettleParams.getExpiry()));
            jsonWriter.name("sessionProperties");
            this.nullableMapOfStringStringAdapter.toJson(jsonWriter, sessionSettleParams.getProperties());
            jsonWriter.name("scopedProperties");
            this.nullableMapOfStringStringAdapter.toJson(jsonWriter, sessionSettleParams.getScopedProperties());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
