package com.reown.sign.engine.domain.wallet_service;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007H@¢\u0006\u0002\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/reown/sign/engine/domain/wallet_service/WalletServiceRequester;", "", "okHttpClient", "Lokhttp3/OkHttpClient;", "<init>", "(Lokhttp3/OkHttpClient;)V", "request", "", "sessionRequest", "Lcom/reown/sign/common/model/vo/clientsync/session/SignRpc$SessionRequest;", "walletServiceUri", "(Lcom/reown/sign/common/model/vo/clientsync/session/SignRpc$SessionRequest;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class WalletServiceRequester {
    @NotNull
    private final OkHttpClient okHttpClient;

    public WalletServiceRequester(@NotNull OkHttpClient okHttpClient2) {
        Intrinsics.checkNotNullParameter(okHttpClient2, "okHttpClient");
        this.okHttpClient = okHttpClient2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x00ae, code lost:
        r3 = r3.string();
     */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object request(@org.jetbrains.annotations.NotNull com.reown.sign.common.model.vo.clientsync.session.SignRpc.SessionRequest r4, @org.jetbrains.annotations.NotNull java.lang.String r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.String> r6) {
        /*
            r3 = this;
            org.json.JSONObject r6 = new org.json.JSONObject
            r6.<init>()
            java.lang.String r0 = "jsonrpc"
            java.lang.String r1 = "2.0"
            r6.put(r0, r1)
            long r0 = r4.getId()
            java.lang.String r2 = "id"
            r6.put(r2, r0)
            java.lang.String r0 = "method"
            java.lang.String r1 = r4.getRpcMethod()
            r6.put(r0, r1)
            org.json.JSONObject r0 = new org.json.JSONObject
            java.lang.String r4 = r4.getRpcParams()
            r0.<init>(r4)
            java.lang.String r4 = "params"
            r6.put(r4, r0)
            okhttp3.Request$Builder r4 = new okhttp3.Request$Builder
            r4.<init>()
            okhttp3.Request$Builder r4 = r4.url((java.lang.String) r5)
            okhttp3.RequestBody$Companion r5 = okhttp3.RequestBody.Companion
            java.lang.String r6 = r6.toString()
            java.lang.String r0 = "toString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r0)
            okhttp3.MediaType$Companion r0 = okhttp3.MediaType.Companion
            java.lang.String r1 = "application/json"
            okhttp3.MediaType r0 = r0.get(r1)
            okhttp3.RequestBody r5 = r5.create((java.lang.String) r6, (okhttp3.MediaType) r0)
            okhttp3.Request$Builder r4 = r4.post(r5)
            java.lang.String r5 = "Content-Type"
            okhttp3.Request$Builder r4 = r4.header(r5, r1)
            java.lang.String r5 = "Accept"
            okhttp3.Request$Builder r4 = r4.header(r5, r1)
            okhttp3.Request r4 = r4.build()
            okhttp3.OkHttpClient r3 = r3.okHttpClient
            okhttp3.Call r3 = r3.newCall(r4)
            okhttp3.Response r3 = r3.execute()
            boolean r4 = r3.isSuccessful()
            if (r4 != 0) goto L_0x00a8
            okhttp3.ResponseBody r3 = r3.body()
            r4 = 0
            if (r3 == 0) goto L_0x0094
            java.lang.String r3 = r3.string()
            if (r3 == 0) goto L_0x0094
            org.json.JSONObject r5 = new org.json.JSONObject
            r5.<init>(r3)
            java.lang.String r3 = "error"
            boolean r6 = r5.has(r3)
            if (r6 == 0) goto L_0x0094
            org.json.JSONObject r3 = r5.getJSONObject(r3)
            java.lang.String r4 = "message"
            java.lang.String r4 = r3.optString(r4)
        L_0x0094:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "Failed to send request to wallet service: "
            r5.<init>(r6)
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            r3.<init>(r4)
            throw r3
        L_0x00a8:
            okhttp3.ResponseBody r3 = r3.body()
            if (r3 == 0) goto L_0x00b4
            java.lang.String r3 = r3.string()
            if (r3 != 0) goto L_0x00b6
        L_0x00b4:
            java.lang.String r3 = ""
        L_0x00b6:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.engine.domain.wallet_service.WalletServiceRequester.request(com.reown.sign.common.model.vo.clientsync.session.SignRpc$SessionRequest, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
