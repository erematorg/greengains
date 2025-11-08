package com.reown.sign.json_rpc.model;

import com.reown.android.internal.common.json_rpc.model.JsonRpcHistoryRecord;
import com.reown.android.internal.common.model.Expiry;
import com.reown.foundation.common.model.Topic;
import com.reown.sign.common.model.Request;
import com.reown.sign.common.model.vo.clientsync.session.SignRpc;
import com.reown.sign.common.model.vo.clientsync.session.params.SignParams;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u001a\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00060\u0001*\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0006H\u0000\u001a\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\b0\u0001*\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0000\u001a\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\b0\u0001*\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005H\u0000¨\u0006\n"}, d2 = {"toRequest", "Lcom/reown/sign/common/model/Request;", "", "Lcom/reown/sign/common/model/vo/clientsync/session/SignRpc$SessionRequest;", "entry", "Lcom/reown/android/internal/common/json_rpc/model/JsonRpcHistoryRecord;", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionRequestParams;", "params", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionAuthenticateParams;", "Lcom/reown/sign/common/model/vo/clientsync/session/SignRpc$SessionAuthenticate;", "sign_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final /* synthetic */ class JsonRpcMapperKt {
    public static final /* synthetic */ Request toRequest(SignRpc.SessionRequest sessionRequest, JsonRpcHistoryRecord jsonRpcHistoryRecord) {
        Intrinsics.checkNotNullParameter(sessionRequest, "<this>");
        Intrinsics.checkNotNullParameter(jsonRpcHistoryRecord, "entry");
        return new Request(jsonRpcHistoryRecord.getId(), new Topic(jsonRpcHistoryRecord.getTopic()), sessionRequest.getParams().getRequest().getMethod(), sessionRequest.getParams().getChainId(), sessionRequest.getParams().getRequest().getParams(), sessionRequest.getParams().getRequest().getExpiryTimestamp() != null ? new Expiry(sessionRequest.getParams().getRequest().getExpiryTimestamp().longValue()) : null, jsonRpcHistoryRecord.getTransportType());
    }

    public static final /* synthetic */ Request toRequest(JsonRpcHistoryRecord jsonRpcHistoryRecord, SignParams.SessionRequestParams sessionRequestParams) {
        Intrinsics.checkNotNullParameter(jsonRpcHistoryRecord, "<this>");
        Intrinsics.checkNotNullParameter(sessionRequestParams, "params");
        return new Request(jsonRpcHistoryRecord.getId(), new Topic(jsonRpcHistoryRecord.getTopic()), jsonRpcHistoryRecord.getMethod(), sessionRequestParams.getChainId(), sessionRequestParams, (Expiry) null, jsonRpcHistoryRecord.getTransportType(), 32, (DefaultConstructorMarker) null);
    }

    public static final /* synthetic */ Request toRequest(JsonRpcHistoryRecord jsonRpcHistoryRecord, SignParams.SessionAuthenticateParams sessionAuthenticateParams) {
        Intrinsics.checkNotNullParameter(jsonRpcHistoryRecord, "<this>");
        Intrinsics.checkNotNullParameter(sessionAuthenticateParams, "params");
        return new Request(jsonRpcHistoryRecord.getId(), new Topic(jsonRpcHistoryRecord.getTopic()), jsonRpcHistoryRecord.getMethod(), (String) null, sessionAuthenticateParams, new Expiry(sessionAuthenticateParams.getExpiryTimestamp()), jsonRpcHistoryRecord.getTransportType());
    }

    public static final /* synthetic */ Request toRequest(SignRpc.SessionAuthenticate sessionAuthenticate, JsonRpcHistoryRecord jsonRpcHistoryRecord) {
        Intrinsics.checkNotNullParameter(sessionAuthenticate, "<this>");
        Intrinsics.checkNotNullParameter(jsonRpcHistoryRecord, "entry");
        return new Request(jsonRpcHistoryRecord.getId(), new Topic(jsonRpcHistoryRecord.getTopic()), jsonRpcHistoryRecord.getMethod(), (String) null, sessionAuthenticate.getParams(), new Expiry(sessionAuthenticate.getParams().getExpiryTimestamp()), jsonRpcHistoryRecord.getTransportType());
    }
}
