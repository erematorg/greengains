package com.reown.sign.json_rpc.model;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/reown/sign/json_rpc/model/JsonRpcMethod;", "", "<init>", "()V", "WC_SESSION_PROPOSE", "", "WC_SESSION_AUTHENTICATE", "WC_SESSION_SETTLE", "WC_SESSION_REQUEST", "WC_SESSION_DELETE", "WC_SESSION_PING", "WC_SESSION_EVENT", "WC_SESSION_UPDATE", "WC_SESSION_EXTEND", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class JsonRpcMethod {
    @NotNull
    public static final JsonRpcMethod INSTANCE = new JsonRpcMethod();
    @NotNull
    public static final String WC_SESSION_AUTHENTICATE = "wc_sessionAuthenticate";
    @NotNull
    public static final String WC_SESSION_DELETE = "wc_sessionDelete";
    @NotNull
    public static final String WC_SESSION_EVENT = "wc_sessionEvent";
    @NotNull
    public static final String WC_SESSION_EXTEND = "wc_sessionExtend";
    @NotNull
    public static final String WC_SESSION_PING = "wc_sessionPing";
    @NotNull
    public static final String WC_SESSION_PROPOSE = "wc_sessionPropose";
    @NotNull
    public static final String WC_SESSION_REQUEST = "wc_sessionRequest";
    @NotNull
    public static final String WC_SESSION_SETTLE = "wc_sessionSettle";
    @NotNull
    public static final String WC_SESSION_UPDATE = "wc_sessionUpdate";

    private JsonRpcMethod() {
    }
}
