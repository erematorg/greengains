package com.reown.sign.common.model.vo.clientsync.session.params;

import A.a;
import com.reown.sign.common.model.vo.clientsync.common.PayloadParams;
import com.reown.sign.common.model.vo.clientsync.common.Requester;
import com.reown.sign.common.model.vo.clientsync.session.params.SignParams;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.internal.Util;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u001a\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams_SessionAuthenticateParamsJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionAuthenticateParams;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "requesterAdapter", "Lcom/reown/sign/common/model/vo/clientsync/common/Requester;", "payloadParamsAdapter", "Lcom/reown/sign/common/model/vo/clientsync/common/PayloadParams;", "longAdapter", "", "toString", "", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SignParams_SessionAuthenticateParamsJsonAdapter extends JsonAdapter<SignParams.SessionAuthenticateParams> {
    @NotNull
    private final JsonAdapter<Long> longAdapter;
    @NotNull
    private final JsonReader.Options options;
    @NotNull
    private final JsonAdapter<PayloadParams> payloadParamsAdapter;
    @NotNull
    private final JsonAdapter<Requester> requesterAdapter;

    public SignParams_SessionAuthenticateParamsJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of("requester", "authPayload", "expiryTimestamp");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        this.requesterAdapter = a.h(moshi, Requester.class, "requester", "adapter(...)");
        this.payloadParamsAdapter = a.h(moshi, PayloadParams.class, "authPayload", "adapter(...)");
        this.longAdapter = a.h(moshi, Long.TYPE, "expiryTimestamp", "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(58, "GeneratedJsonAdapter(SignParams.SessionAuthenticateParams)");
    }

    @NotNull
    public SignParams.SessionAuthenticateParams fromJson(@NotNull JsonReader jsonReader) {
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        jsonReader.beginObject();
        Requester requester = null;
        PayloadParams payloadParams = null;
        Long l2 = null;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(this.options);
            if (selectName == -1) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else if (selectName == 0) {
                requester = this.requesterAdapter.fromJson(jsonReader);
                if (requester == null) {
                    throw Util.unexpectedNull("requester", "requester", jsonReader);
                }
            } else if (selectName == 1) {
                payloadParams = this.payloadParamsAdapter.fromJson(jsonReader);
                if (payloadParams == null) {
                    throw Util.unexpectedNull("authPayload", "authPayload", jsonReader);
                }
            } else if (selectName == 2 && (l2 = this.longAdapter.fromJson(jsonReader)) == null) {
                throw Util.unexpectedNull("expiryTimestamp", "expiryTimestamp", jsonReader);
            }
        }
        jsonReader.endObject();
        if (requester == null) {
            throw Util.missingProperty("requester", "requester", jsonReader);
        } else if (payloadParams == null) {
            throw Util.missingProperty("authPayload", "authPayload", jsonReader);
        } else if (l2 != null) {
            return new SignParams.SessionAuthenticateParams(requester, payloadParams, l2.longValue());
        } else {
            throw Util.missingProperty("expiryTimestamp", "expiryTimestamp", jsonReader);
        }
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable SignParams.SessionAuthenticateParams sessionAuthenticateParams) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (sessionAuthenticateParams != null) {
            jsonWriter.beginObject();
            jsonWriter.name("requester");
            this.requesterAdapter.toJson(jsonWriter, sessionAuthenticateParams.getRequester());
            jsonWriter.name("authPayload");
            this.payloadParamsAdapter.toJson(jsonWriter, sessionAuthenticateParams.getAuthPayload());
            jsonWriter.name("expiryTimestamp");
            this.longAdapter.toJson(jsonWriter, Long.valueOf(sessionAuthenticateParams.getExpiryTimestamp()));
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
