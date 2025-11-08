package com.reown.sign.common.model.vo.clientsync.session.params;

import A.a;
import com.reown.android.internal.common.model.Namespace;
import com.reown.android.internal.common.model.RelayProtocolOptions;
import com.reown.android.internal.common.model.SessionProposer;
import com.reown.sign.common.model.vo.clientsync.session.params.SignParams;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import com.squareup.moshi.internal.Util;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.text.lookup.StringLookupFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0016\u001a\u00020\u000bH\u0016J\u0010\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u001a\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R \u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\"\u0010\r\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u0001X\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0013\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams_SessionProposeParamsJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionProposeParams;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "mapOfStringProposalAdapter", "", "", "Lcom/reown/android/internal/common/model/Namespace$Proposal;", "nullableMapOfStringProposalAdapter", "listOfRelayProtocolOptionsAdapter", "", "Lcom/reown/android/internal/common/model/RelayProtocolOptions;", "sessionProposerAdapter", "Lcom/reown/android/internal/common/model/SessionProposer;", "nullableMapOfStringStringAdapter", "nullableLongAdapter", "", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SignParams_SessionProposeParamsJsonAdapter extends JsonAdapter<SignParams.SessionProposeParams> {
    @NotNull
    private final JsonAdapter<List<RelayProtocolOptions>> listOfRelayProtocolOptionsAdapter;
    @NotNull
    private final JsonAdapter<Map<String, Namespace.Proposal>> mapOfStringProposalAdapter;
    @NotNull
    private final JsonAdapter<Long> nullableLongAdapter;
    @NotNull
    private final JsonAdapter<Map<String, Namespace.Proposal>> nullableMapOfStringProposalAdapter;
    @NotNull
    private final JsonAdapter<Map<String, String>> nullableMapOfStringStringAdapter;
    @NotNull
    private final JsonReader.Options options;
    @NotNull
    private final JsonAdapter<SessionProposer> sessionProposerAdapter;

    public SignParams_SessionProposeParamsJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of("requiredNamespaces", "optionalNamespaces", "relays", "proposer", "sessionProperties", "scopedProperties", "expiryTimestamp");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        Class<String> cls = String.class;
        Class<Namespace.Proposal> cls2 = Namespace.Proposal.class;
        Class<Map> cls3 = Map.class;
        this.mapOfStringProposalAdapter = a.i(moshi, Types.newParameterizedType(cls3, cls, cls2), "requiredNamespaces", "adapter(...)");
        this.nullableMapOfStringProposalAdapter = a.i(moshi, Types.newParameterizedType(cls3, cls, cls2), "optionalNamespaces", "adapter(...)");
        this.listOfRelayProtocolOptionsAdapter = a.i(moshi, Types.newParameterizedType(List.class, RelayProtocolOptions.class), "relays", "adapter(...)");
        this.sessionProposerAdapter = a.h(moshi, SessionProposer.class, "proposer", "adapter(...)");
        this.nullableMapOfStringStringAdapter = a.i(moshi, Types.newParameterizedType(cls3, cls, cls), StringLookupFactory.KEY_PROPERTIES, "adapter(...)");
        this.nullableLongAdapter = a.h(moshi, Long.class, "expiryTimestamp", "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(53, "GeneratedJsonAdapter(SignParams.SessionProposeParams)");
    }

    @NotNull
    public SignParams.SessionProposeParams fromJson(@NotNull JsonReader jsonReader) {
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        jsonReader.beginObject();
        Map map = null;
        Map map2 = null;
        List list = null;
        SessionProposer sessionProposer = null;
        Map map3 = null;
        Map map4 = null;
        Long l2 = null;
        while (jsonReader.hasNext()) {
            switch (jsonReader.selectName(this.options)) {
                case -1:
                    jsonReader.skipName();
                    jsonReader.skipValue();
                    break;
                case 0:
                    map = this.mapOfStringProposalAdapter.fromJson(jsonReader);
                    if (map != null) {
                        break;
                    } else {
                        throw Util.unexpectedNull("requiredNamespaces", "requiredNamespaces", jsonReader);
                    }
                case 1:
                    map2 = this.nullableMapOfStringProposalAdapter.fromJson(jsonReader);
                    break;
                case 2:
                    list = this.listOfRelayProtocolOptionsAdapter.fromJson(jsonReader);
                    if (list != null) {
                        break;
                    } else {
                        throw Util.unexpectedNull("relays", "relays", jsonReader);
                    }
                case 3:
                    sessionProposer = this.sessionProposerAdapter.fromJson(jsonReader);
                    if (sessionProposer != null) {
                        break;
                    } else {
                        throw Util.unexpectedNull("proposer", "proposer", jsonReader);
                    }
                case 4:
                    map3 = this.nullableMapOfStringStringAdapter.fromJson(jsonReader);
                    break;
                case 5:
                    map4 = this.nullableMapOfStringStringAdapter.fromJson(jsonReader);
                    break;
                case 6:
                    l2 = this.nullableLongAdapter.fromJson(jsonReader);
                    break;
            }
        }
        jsonReader.endObject();
        if (map == null) {
            throw Util.missingProperty("requiredNamespaces", "requiredNamespaces", jsonReader);
        } else if (list == null) {
            throw Util.missingProperty("relays", "relays", jsonReader);
        } else if (sessionProposer != null) {
            return new SignParams.SessionProposeParams(map, map2, list, sessionProposer, map3, map4, l2);
        } else {
            throw Util.missingProperty("proposer", "proposer", jsonReader);
        }
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable SignParams.SessionProposeParams sessionProposeParams) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (sessionProposeParams != null) {
            jsonWriter.beginObject();
            jsonWriter.name("requiredNamespaces");
            this.mapOfStringProposalAdapter.toJson(jsonWriter, sessionProposeParams.getRequiredNamespaces());
            jsonWriter.name("optionalNamespaces");
            this.nullableMapOfStringProposalAdapter.toJson(jsonWriter, sessionProposeParams.getOptionalNamespaces());
            jsonWriter.name("relays");
            this.listOfRelayProtocolOptionsAdapter.toJson(jsonWriter, sessionProposeParams.getRelays());
            jsonWriter.name("proposer");
            this.sessionProposerAdapter.toJson(jsonWriter, sessionProposeParams.getProposer());
            jsonWriter.name("sessionProperties");
            this.nullableMapOfStringStringAdapter.toJson(jsonWriter, sessionProposeParams.getProperties());
            jsonWriter.name("scopedProperties");
            this.nullableMapOfStringStringAdapter.toJson(jsonWriter, sessionProposeParams.getScopedProperties());
            jsonWriter.name("expiryTimestamp");
            this.nullableLongAdapter.toJson(jsonWriter, sessionProposeParams.getExpiryTimestamp());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
