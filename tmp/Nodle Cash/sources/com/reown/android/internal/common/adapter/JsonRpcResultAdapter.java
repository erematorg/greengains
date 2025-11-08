package com.reown.android.internal.common.adapter;

import A.a;
import com.reown.android.internal.common.JsonRpcResponse;
import com.reown.android.internal.common.model.params.ChatNotifyResponseAuthParams;
import com.reown.android.internal.common.model.params.CoreNotifyParams;
import com.reown.android.internal.common.model.params.CoreSignParams;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.internal.Util;
import java.lang.reflect.Constructor;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u001d\u001a\u00020\u000eH\u0016J\u0010\u0010\u001e\u001a\u00020\u00022\u0006\u0010\u001f\u001a\u00020 H\u0016J\u001a\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0002H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u001cX\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/reown/android/internal/common/adapter/JsonRpcResultAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/android/internal/common/JsonRpcResponse$JsonRpcResult;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "getMoshi", "()Lcom/squareup/moshi/Moshi;", "options", "Lcom/squareup/moshi/JsonReader$Options;", "longAdapter", "", "stringAdapter", "", "anyAdapter", "", "approvalParamsAdapter", "Lcom/reown/android/internal/common/model/params/CoreSignParams$ApprovalParams;", "approveSessionAuthenticateParamsAdapter", "Lcom/reown/android/internal/common/model/params/CoreSignParams$SessionAuthenticateApproveParams;", "notifySubscribeUpdateParamsAdapter", "Lcom/reown/android/internal/common/model/params/CoreNotifyParams$UpdateParams;", "chatNotifyResponseAuthParamsAdapter", "Lcom/reown/android/internal/common/model/params/ChatNotifyResponseAuthParams$ResponseAuth;", "chatNotifyAuthParamsAdapter", "Lcom/reown/android/internal/common/model/params/ChatNotifyResponseAuthParams$Auth;", "constructorRef", "Ljava/lang/reflect/Constructor;", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nJsonRpcResultAdapter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JsonRpcResultAdapter.kt\ncom/reown/android/internal/common/adapter/JsonRpcResultAdapter\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,188:1\n1#2:189\n*E\n"})
public final class JsonRpcResultAdapter extends JsonAdapter<JsonRpcResponse.JsonRpcResult> {
    @NotNull
    private final JsonAdapter<Object> anyAdapter;
    @NotNull
    private final JsonAdapter<CoreSignParams.ApprovalParams> approvalParamsAdapter;
    @NotNull
    private final JsonAdapter<CoreSignParams.SessionAuthenticateApproveParams> approveSessionAuthenticateParamsAdapter;
    @NotNull
    private final JsonAdapter<ChatNotifyResponseAuthParams.Auth> chatNotifyAuthParamsAdapter;
    @NotNull
    private final JsonAdapter<ChatNotifyResponseAuthParams.ResponseAuth> chatNotifyResponseAuthParamsAdapter;
    @Nullable
    private volatile Constructor<JsonRpcResponse.JsonRpcResult> constructorRef;
    @NotNull
    private final JsonAdapter<Long> longAdapter;
    @NotNull
    private final Moshi moshi;
    @NotNull
    private final JsonAdapter<CoreNotifyParams.UpdateParams> notifySubscribeUpdateParamsAdapter;
    @NotNull
    private final JsonReader.Options options;
    @NotNull
    private final JsonAdapter<String> stringAdapter;

    public JsonRpcResultAdapter(@NotNull Moshi moshi2) {
        Intrinsics.checkNotNullParameter(moshi2, "moshi");
        this.moshi = moshi2;
        JsonReader.Options of = JsonReader.Options.of(TtmlNode.ATTR_ID, "jsonrpc", "result");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        this.longAdapter = a.h(moshi2, Long.TYPE, TtmlNode.ATTR_ID, "adapter(...)");
        this.stringAdapter = a.h(moshi2, String.class, "jsonrpc", "adapter(...)");
        this.anyAdapter = a.h(moshi2, Object.class, "result", "adapter(...)");
        this.approvalParamsAdapter = a.h(moshi2, CoreSignParams.ApprovalParams.class, "result", "adapter(...)");
        this.approveSessionAuthenticateParamsAdapter = a.h(moshi2, CoreSignParams.SessionAuthenticateApproveParams.class, "result", "adapter(...)");
        this.notifySubscribeUpdateParamsAdapter = a.h(moshi2, CoreNotifyParams.UpdateParams.class, "result", "adapter(...)");
        this.chatNotifyResponseAuthParamsAdapter = a.h(moshi2, ChatNotifyResponseAuthParams.ResponseAuth.class, "result", "adapter(...)");
        this.chatNotifyAuthParamsAdapter = a.h(moshi2, ChatNotifyResponseAuthParams.Auth.class, "result", "adapter(...)");
    }

    @NotNull
    public final Moshi getMoshi() {
        return this.moshi;
    }

    @NotNull
    public String toString() {
        return a.j(59, "GeneratedJsonAdapter(RelayDO.JsonRpcResponse.JsonRpcResult)");
    }

    @NotNull
    public JsonRpcResponse.JsonRpcResult fromJson(@NotNull JsonReader jsonReader) {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        jsonReader.beginObject();
        Long l2 = null;
        String str = null;
        Object obj6 = null;
        int i3 = -1;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(this.options);
            if (selectName == -1) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else if (selectName == 0) {
                l2 = this.longAdapter.fromJson(jsonReader);
                if (l2 == null) {
                    throw Util.unexpectedNull(TtmlNode.ATTR_ID, TtmlNode.ATTR_ID, jsonReader);
                }
            } else if (selectName == 1) {
                str = this.stringAdapter.fromJson(jsonReader);
                if (str != null) {
                    i3 = -3;
                } else {
                    throw Util.unexpectedNull("jsonrpc", "jsonrpc", jsonReader);
                }
            } else if (selectName == 2) {
                try {
                    Result.Companion companion = Result.Companion;
                    obj = Result.m8979constructorimpl(this.approvalParamsAdapter.fromJson(jsonReader.peekJson()));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.Companion;
                    obj = Result.m8979constructorimpl(ResultKt.createFailure(th));
                }
                if (Result.m8986isSuccessimpl(obj)) {
                    obj6 = this.approvalParamsAdapter.fromJson(jsonReader);
                } else {
                    try {
                        obj2 = Result.m8979constructorimpl(this.approveSessionAuthenticateParamsAdapter.fromJson(jsonReader.peekJson()));
                    } catch (Throwable th2) {
                        Result.Companion companion3 = Result.Companion;
                        obj2 = Result.m8979constructorimpl(ResultKt.createFailure(th2));
                    }
                    if (Result.m8986isSuccessimpl(obj2)) {
                        obj6 = this.approveSessionAuthenticateParamsAdapter.fromJson(jsonReader);
                    } else {
                        try {
                            obj3 = Result.m8979constructorimpl(this.notifySubscribeUpdateParamsAdapter.fromJson(jsonReader.peekJson()));
                        } catch (Throwable th3) {
                            Result.Companion companion4 = Result.Companion;
                            obj3 = Result.m8979constructorimpl(ResultKt.createFailure(th3));
                        }
                        if (Result.m8986isSuccessimpl(obj3)) {
                            obj6 = this.notifySubscribeUpdateParamsAdapter.fromJson(jsonReader);
                        } else {
                            try {
                                obj4 = Result.m8979constructorimpl(this.chatNotifyResponseAuthParamsAdapter.fromJson(jsonReader.peekJson()));
                            } catch (Throwable th4) {
                                Result.Companion companion5 = Result.Companion;
                                obj4 = Result.m8979constructorimpl(ResultKt.createFailure(th4));
                            }
                            if (Result.m8986isSuccessimpl(obj4)) {
                                obj6 = this.chatNotifyResponseAuthParamsAdapter.fromJson(jsonReader);
                            } else {
                                try {
                                    obj5 = Result.m8979constructorimpl(this.chatNotifyAuthParamsAdapter.fromJson(jsonReader.peekJson()));
                                } catch (Throwable th5) {
                                    Result.Companion companion6 = Result.Companion;
                                    obj5 = Result.m8979constructorimpl(ResultKt.createFailure(th5));
                                }
                                if (Result.m8986isSuccessimpl(obj5)) {
                                    obj6 = this.chatNotifyAuthParamsAdapter.fromJson(jsonReader);
                                } else {
                                    obj6 = this.anyAdapter.fromJson(jsonReader);
                                }
                            }
                        }
                    }
                }
            }
        }
        jsonReader.endObject();
        if (i3 != -3) {
            Constructor<JsonRpcResponse.JsonRpcResult> constructor = this.constructorRef;
            if (constructor == null) {
                constructor = JsonRpcResponse.JsonRpcResult.class.getDeclaredConstructor(new Class[]{Long.TYPE, String.class, Object.class, Integer.TYPE, Util.DEFAULT_CONSTRUCTOR_MARKER});
                this.constructorRef = constructor;
                Intrinsics.checkNotNullExpressionValue(constructor, "also(...)");
            }
            if (l2 == null) {
                throw Util.missingProperty(TtmlNode.ATTR_ID, TtmlNode.ATTR_ID, jsonReader);
            } else if (obj6 != null) {
                JsonRpcResponse.JsonRpcResult newInstance = constructor.newInstance(new Object[]{l2, str, obj6, Integer.valueOf(i3), null});
                Intrinsics.checkNotNullExpressionValue(newInstance, "newInstance(...)");
                return newInstance;
            } else {
                throw Util.missingProperty("result", "result", jsonReader);
            }
        } else if (l2 != null) {
            long longValue = l2.longValue();
            Intrinsics.checkNotNull(str, "null cannot be cast to non-null type kotlin.String");
            if (obj6 != null) {
                return new JsonRpcResponse.JsonRpcResult(longValue, str, obj6);
            }
            throw Util.missingProperty("result", "result", jsonReader);
        } else {
            throw Util.missingProperty(TtmlNode.ATTR_ID, TtmlNode.ATTR_ID, jsonReader);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0187, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0188, code lost:
        kotlin.io.CloseableKt.closeFinally(r4, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x018b, code lost:
        throw r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0059, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x005a, code lost:
        kotlin.io.CloseableKt.closeFinally(r6, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x005d, code lost:
        throw r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0087, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0088, code lost:
        kotlin.io.CloseableKt.closeFinally(r6, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x008b, code lost:
        throw r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00b5, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00b6, code lost:
        kotlin.io.CloseableKt.closeFinally(r6, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00b9, code lost:
        throw r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00e3, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00e4, code lost:
        kotlin.io.CloseableKt.closeFinally(r6, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00e7, code lost:
        throw r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0111, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0112, code lost:
        kotlin.io.CloseableKt.closeFinally(r6, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0115, code lost:
        throw r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x014d, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x014e, code lost:
        kotlin.io.CloseableKt.closeFinally(r4, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0151, code lost:
        throw r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void toJson(@org.jetbrains.annotations.NotNull com.squareup.moshi.JsonWriter r5, @org.jetbrains.annotations.Nullable com.reown.android.internal.common.JsonRpcResponse.JsonRpcResult r6) {
        /*
            r4 = this;
            java.lang.String r0 = "writer"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            if (r6 == 0) goto L_0x0199
            r5.beginObject()
            java.lang.String r0 = "id"
            r5.name(r0)
            com.squareup.moshi.JsonAdapter<java.lang.Long> r0 = r4.longAdapter
            long r1 = r6.getId()
            java.lang.Long r1 = java.lang.Long.valueOf(r1)
            r0.toJson((com.squareup.moshi.JsonWriter) r5, r1)
            java.lang.String r0 = "jsonrpc"
            r5.name(r0)
            com.squareup.moshi.JsonAdapter<java.lang.String> r0 = r4.stringAdapter
            java.lang.String r1 = r6.getJsonrpc()
            r0.toJson((com.squareup.moshi.JsonWriter) r5, r1)
            java.lang.String r0 = "result"
            r5.name(r0)
            java.lang.Object r0 = r6.getResult()
            boolean r1 = r0 instanceof com.reown.android.internal.common.model.params.CoreSignParams.ApprovalParams
            r2 = 0
            if (r1 == 0) goto L_0x003b
            com.reown.android.internal.common.model.params.CoreSignParams$ApprovalParams r0 = (com.reown.android.internal.common.model.params.CoreSignParams.ApprovalParams) r0
            goto L_0x003c
        L_0x003b:
            r0 = r2
        L_0x003c:
            if (r0 == 0) goto L_0x005e
            com.squareup.moshi.JsonAdapter<com.reown.android.internal.common.model.params.CoreSignParams$ApprovalParams> r4 = r4.approvalParamsAdapter
            java.lang.Object r6 = r6.getResult()
            java.lang.String r4 = r4.toJson(r6)
            okio.BufferedSink r6 = r5.valueSink()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)     // Catch:{ all -> 0x0057 }
            r6.writeUtf8(r4)     // Catch:{ all -> 0x0057 }
            kotlin.io.CloseableKt.closeFinally(r6, r2)
            goto L_0x0195
        L_0x0057:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0059 }
        L_0x0059:
            r5 = move-exception
            kotlin.io.CloseableKt.closeFinally(r6, r4)
            throw r5
        L_0x005e:
            java.lang.Object r0 = r6.getResult()
            boolean r1 = r0 instanceof com.reown.android.internal.common.model.params.CoreSignParams.SessionAuthenticateApproveParams
            if (r1 == 0) goto L_0x0069
            com.reown.android.internal.common.model.params.CoreSignParams$SessionAuthenticateApproveParams r0 = (com.reown.android.internal.common.model.params.CoreSignParams.SessionAuthenticateApproveParams) r0
            goto L_0x006a
        L_0x0069:
            r0 = r2
        L_0x006a:
            if (r0 == 0) goto L_0x008c
            com.squareup.moshi.JsonAdapter<com.reown.android.internal.common.model.params.CoreSignParams$SessionAuthenticateApproveParams> r4 = r4.approveSessionAuthenticateParamsAdapter
            java.lang.Object r6 = r6.getResult()
            java.lang.String r4 = r4.toJson(r6)
            okio.BufferedSink r6 = r5.valueSink()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)     // Catch:{ all -> 0x0085 }
            r6.writeUtf8(r4)     // Catch:{ all -> 0x0085 }
            kotlin.io.CloseableKt.closeFinally(r6, r2)
            goto L_0x0195
        L_0x0085:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0087 }
        L_0x0087:
            r5 = move-exception
            kotlin.io.CloseableKt.closeFinally(r6, r4)
            throw r5
        L_0x008c:
            java.lang.Object r0 = r6.getResult()
            boolean r1 = r0 instanceof com.reown.android.internal.common.model.params.ChatNotifyResponseAuthParams.ResponseAuth
            if (r1 == 0) goto L_0x0097
            com.reown.android.internal.common.model.params.ChatNotifyResponseAuthParams$ResponseAuth r0 = (com.reown.android.internal.common.model.params.ChatNotifyResponseAuthParams.ResponseAuth) r0
            goto L_0x0098
        L_0x0097:
            r0 = r2
        L_0x0098:
            if (r0 == 0) goto L_0x00ba
            com.squareup.moshi.JsonAdapter<com.reown.android.internal.common.model.params.ChatNotifyResponseAuthParams$ResponseAuth> r4 = r4.chatNotifyResponseAuthParamsAdapter
            java.lang.Object r6 = r6.getResult()
            java.lang.String r4 = r4.toJson(r6)
            okio.BufferedSink r6 = r5.valueSink()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)     // Catch:{ all -> 0x00b3 }
            r6.writeUtf8(r4)     // Catch:{ all -> 0x00b3 }
            kotlin.io.CloseableKt.closeFinally(r6, r2)
            goto L_0x0195
        L_0x00b3:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x00b5 }
        L_0x00b5:
            r5 = move-exception
            kotlin.io.CloseableKt.closeFinally(r6, r4)
            throw r5
        L_0x00ba:
            java.lang.Object r0 = r6.getResult()
            boolean r1 = r0 instanceof com.reown.android.internal.common.model.params.ChatNotifyResponseAuthParams.Auth
            if (r1 == 0) goto L_0x00c5
            com.reown.android.internal.common.model.params.ChatNotifyResponseAuthParams$Auth r0 = (com.reown.android.internal.common.model.params.ChatNotifyResponseAuthParams.Auth) r0
            goto L_0x00c6
        L_0x00c5:
            r0 = r2
        L_0x00c6:
            if (r0 == 0) goto L_0x00e8
            com.squareup.moshi.JsonAdapter<com.reown.android.internal.common.model.params.ChatNotifyResponseAuthParams$Auth> r4 = r4.chatNotifyAuthParamsAdapter
            java.lang.Object r6 = r6.getResult()
            java.lang.String r4 = r4.toJson(r6)
            okio.BufferedSink r6 = r5.valueSink()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)     // Catch:{ all -> 0x00e1 }
            r6.writeUtf8(r4)     // Catch:{ all -> 0x00e1 }
            kotlin.io.CloseableKt.closeFinally(r6, r2)
            goto L_0x0195
        L_0x00e1:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x00e3 }
        L_0x00e3:
            r5 = move-exception
            kotlin.io.CloseableKt.closeFinally(r6, r4)
            throw r5
        L_0x00e8:
            java.lang.Object r0 = r6.getResult()
            boolean r1 = r0 instanceof com.reown.android.internal.common.model.params.CoreNotifyParams.UpdateParams
            if (r1 == 0) goto L_0x00f3
            com.reown.android.internal.common.model.params.CoreNotifyParams$UpdateParams r0 = (com.reown.android.internal.common.model.params.CoreNotifyParams.UpdateParams) r0
            goto L_0x00f4
        L_0x00f3:
            r0 = r2
        L_0x00f4:
            if (r0 == 0) goto L_0x0116
            com.squareup.moshi.JsonAdapter<com.reown.android.internal.common.model.params.CoreNotifyParams$UpdateParams> r4 = r4.notifySubscribeUpdateParamsAdapter
            java.lang.Object r6 = r6.getResult()
            java.lang.String r4 = r4.toJson(r6)
            okio.BufferedSink r6 = r5.valueSink()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)     // Catch:{ all -> 0x010f }
            r6.writeUtf8(r4)     // Catch:{ all -> 0x010f }
            kotlin.io.CloseableKt.closeFinally(r6, r2)
            goto L_0x0195
        L_0x010f:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0111 }
        L_0x0111:
            r5 = move-exception
            kotlin.io.CloseableKt.closeFinally(r6, r4)
            throw r5
        L_0x0116:
            java.lang.Object r0 = r6.getResult()
            boolean r0 = r0 instanceof java.lang.String
            java.lang.String r1 = "toString(...)"
            if (r0 == 0) goto L_0x0152
            java.lang.Object r0 = r6.getResult()
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r3 = "{"
            boolean r0 = kotlin.text.StringsKt__StringsJVMKt.startsWith$default(r0, r3, false, 2, (java.lang.Object) null)
            if (r0 == 0) goto L_0x0152
            okio.BufferedSink r4 = r5.valueSink()
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ all -> 0x014b }
            java.lang.Object r6 = r6.getResult()     // Catch:{ all -> 0x014b }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ all -> 0x014b }
            r0.<init>(r6)     // Catch:{ all -> 0x014b }
            java.lang.String r6 = r0.toString()     // Catch:{ all -> 0x014b }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r1)     // Catch:{ all -> 0x014b }
            r4.writeUtf8(r6)     // Catch:{ all -> 0x014b }
            kotlin.io.CloseableKt.closeFinally(r4, r2)
            goto L_0x0195
        L_0x014b:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x014d }
        L_0x014d:
            r6 = move-exception
            kotlin.io.CloseableKt.closeFinally(r4, r5)
            throw r6
        L_0x0152:
            java.lang.Object r0 = r6.getResult()
            boolean r0 = r0 instanceof java.lang.String
            if (r0 == 0) goto L_0x018c
            java.lang.Object r0 = r6.getResult()
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r3 = "["
            boolean r0 = kotlin.text.StringsKt__StringsJVMKt.startsWith$default(r0, r3, false, 2, (java.lang.Object) null)
            if (r0 == 0) goto L_0x018c
            okio.BufferedSink r4 = r5.valueSink()
            org.json.JSONArray r0 = new org.json.JSONArray     // Catch:{ all -> 0x0185 }
            java.lang.Object r6 = r6.getResult()     // Catch:{ all -> 0x0185 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ all -> 0x0185 }
            r0.<init>(r6)     // Catch:{ all -> 0x0185 }
            java.lang.String r6 = r0.toString()     // Catch:{ all -> 0x0185 }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r1)     // Catch:{ all -> 0x0185 }
            r4.writeUtf8(r6)     // Catch:{ all -> 0x0185 }
            kotlin.io.CloseableKt.closeFinally(r4, r2)
            goto L_0x0195
        L_0x0185:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0187 }
        L_0x0187:
            r6 = move-exception
            kotlin.io.CloseableKt.closeFinally(r4, r5)
            throw r6
        L_0x018c:
            com.squareup.moshi.JsonAdapter<java.lang.Object> r4 = r4.anyAdapter
            java.lang.Object r6 = r6.getResult()
            r4.toJson((com.squareup.moshi.JsonWriter) r5, r6)
        L_0x0195:
            r5.endObject()
            return
        L_0x0199:
            java.lang.NullPointerException r4 = new java.lang.NullPointerException
            java.lang.String r5 = "value_ was null! Wrap in .nullSafe() to write nullable values."
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.internal.common.adapter.JsonRpcResultAdapter.toJson(com.squareup.moshi.JsonWriter, com.reown.android.internal.common.JsonRpcResponse$JsonRpcResult):void");
    }
}
