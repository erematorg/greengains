package com.reown.android.internal.common;

import androidx.compose.animation.core.a;
import com.google.firebase.messaging.Constants;
import com.reown.android.internal.common.model.type.SerializableJsonRpc;
import com.reown.android.push.notifications.PushMessagingService;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\b\t\nB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u0001\u0002\u000b\f¨\u0006\r"}, d2 = {"Lcom/reown/android/internal/common/JsonRpcResponse;", "Lcom/reown/android/internal/common/model/type/SerializableJsonRpc;", "<init>", "()V", "id", "", "getId", "()J", "JsonRpcResult", "JsonRpcError", "Error", "Lcom/reown/android/internal/common/JsonRpcResponse$JsonRpcError;", "Lcom/reown/android/internal/common/JsonRpcResponse$JsonRpcResult;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class JsonRpcResponse implements SerializableJsonRpc {

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lcom/reown/android/internal/common/JsonRpcResponse$Error;", "", "code", "", "message", "", "<init>", "(ILjava/lang/String;)V", "getCode", "()I", "getMessage", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Error {
        private final int code;
        @NotNull
        private final String message;

        public Error(int i3, @NotNull String str) {
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
            this.code = i3;
            this.message = str;
        }

        public static /* synthetic */ Error copy$default(Error error, int i3, String str, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                i3 = error.code;
            }
            if ((i4 & 2) != 0) {
                str = error.message;
            }
            return error.copy(i3, str);
        }

        public final int component1() {
            return this.code;
        }

        @NotNull
        public final String component2() {
            return this.message;
        }

        @NotNull
        public final Error copy(int i3, @NotNull String str) {
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
            return new Error(i3, str);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Error)) {
                return false;
            }
            Error error = (Error) obj;
            return this.code == error.code && Intrinsics.areEqual((Object) this.message, (Object) error.message);
        }

        public final int getCode() {
            return this.code;
        }

        @NotNull
        public final String getMessage() {
            return this.message;
        }

        public int hashCode() {
            return this.message.hashCode() + (Integer.hashCode(this.code) * 31);
        }

        @NotNull
        public String toString() {
            int i3 = this.code;
            String str = this.message;
            return "Error(code=" + i3 + ", message=" + str + ")";
        }
    }

    public /* synthetic */ JsonRpcResponse(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract long getId();

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0007HÆ\u0003J'\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\r¨\u0006\u001d"}, d2 = {"Lcom/reown/android/internal/common/JsonRpcResponse$JsonRpcError;", "Lcom/reown/android/internal/common/JsonRpcResponse;", "id", "", "jsonrpc", "", "error", "Lcom/reown/android/internal/common/JsonRpcResponse$Error;", "<init>", "(JLjava/lang/String;Lcom/reown/android/internal/common/JsonRpcResponse$Error;)V", "getId", "()J", "getJsonrpc", "()Ljava/lang/String;", "getError", "()Lcom/reown/android/internal/common/JsonRpcResponse$Error;", "errorMessage", "getErrorMessage", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class JsonRpcError extends JsonRpcResponse {
        @NotNull
        private final Error error;
        @NotNull
        private final String errorMessage;
        private final long id;
        @NotNull
        private final String jsonrpc;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ JsonRpcError(long j2, String str, Error error2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this(j2, (i3 & 2) != 0 ? "2.0" : str, error2);
        }

        public static /* synthetic */ JsonRpcError copy$default(JsonRpcError jsonRpcError, long j2, String str, Error error2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                j2 = jsonRpcError.id;
            }
            if ((i3 & 2) != 0) {
                str = jsonRpcError.jsonrpc;
            }
            if ((i3 & 4) != 0) {
                error2 = jsonRpcError.error;
            }
            return jsonRpcError.copy(j2, str, error2);
        }

        public final long component1() {
            return this.id;
        }

        @NotNull
        public final String component2() {
            return this.jsonrpc;
        }

        @NotNull
        public final Error component3() {
            return this.error;
        }

        @NotNull
        public final JsonRpcError copy(long j2, @NotNull String str, @NotNull Error error2) {
            Intrinsics.checkNotNullParameter(str, "jsonrpc");
            Intrinsics.checkNotNullParameter(error2, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
            return new JsonRpcError(j2, str, error2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof JsonRpcError)) {
                return false;
            }
            JsonRpcError jsonRpcError = (JsonRpcError) obj;
            return this.id == jsonRpcError.id && Intrinsics.areEqual((Object) this.jsonrpc, (Object) jsonRpcError.jsonrpc) && Intrinsics.areEqual((Object) this.error, (Object) jsonRpcError.error);
        }

        @NotNull
        public final Error getError() {
            return this.error;
        }

        @NotNull
        public final String getErrorMessage() {
            return this.errorMessage;
        }

        public long getId() {
            return this.id;
        }

        @NotNull
        public final String getJsonrpc() {
            return this.jsonrpc;
        }

        public int hashCode() {
            return this.error.hashCode() + a.i(this.jsonrpc, Long.hashCode(this.id) * 31, 31);
        }

        @NotNull
        public String toString() {
            long j2 = this.id;
            String str = this.jsonrpc;
            Error error2 = this.error;
            StringBuilder v2 = androidx.work.impl.a.v(j2, "JsonRpcError(id=", ", jsonrpc=", str);
            v2.append(", error=");
            v2.append(error2);
            v2.append(")");
            return v2.toString();
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public JsonRpcError(long j2, @NotNull String str, @NotNull Error error2) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "jsonrpc");
            Intrinsics.checkNotNullParameter(error2, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
            this.id = j2;
            this.jsonrpc = str;
            this.error = error2;
            this.errorMessage = com.appsamurai.storyly.exoplayer2.common.a.b(error2.getCode(), error2.getMessage(), " : code: ");
        }
    }

    @JsonClass(generateAdapter = false)
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0007HÆ\u0003J)\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/reown/android/internal/common/JsonRpcResponse$JsonRpcResult;", "Lcom/reown/android/internal/common/JsonRpcResponse;", "id", "", "jsonrpc", "", "result", "", "<init>", "(JLjava/lang/String;Ljava/lang/Object;)V", "getId", "()J", "getJsonrpc", "()Ljava/lang/String;", "getResult", "()Ljava/lang/Object;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class JsonRpcResult extends JsonRpcResponse {
        private final long id;
        @NotNull
        private final String jsonrpc;
        @Nullable
        private final Object result;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ JsonRpcResult(long j2, String str, Object obj, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this(j2, (i3 & 2) != 0 ? "2.0" : str, obj);
        }

        public static /* synthetic */ JsonRpcResult copy$default(JsonRpcResult jsonRpcResult, long j2, String str, Object obj, int i3, Object obj2) {
            if ((i3 & 1) != 0) {
                j2 = jsonRpcResult.id;
            }
            if ((i3 & 2) != 0) {
                str = jsonRpcResult.jsonrpc;
            }
            if ((i3 & 4) != 0) {
                obj = jsonRpcResult.result;
            }
            return jsonRpcResult.copy(j2, str, obj);
        }

        public final long component1() {
            return this.id;
        }

        @NotNull
        public final String component2() {
            return this.jsonrpc;
        }

        @Nullable
        public final Object component3() {
            return this.result;
        }

        @NotNull
        public final JsonRpcResult copy(long j2, @NotNull String str, @Nullable Object obj) {
            Intrinsics.checkNotNullParameter(str, "jsonrpc");
            return new JsonRpcResult(j2, str, obj);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof JsonRpcResult)) {
                return false;
            }
            JsonRpcResult jsonRpcResult = (JsonRpcResult) obj;
            return this.id == jsonRpcResult.id && Intrinsics.areEqual((Object) this.jsonrpc, (Object) jsonRpcResult.jsonrpc) && Intrinsics.areEqual(this.result, jsonRpcResult.result);
        }

        public long getId() {
            return this.id;
        }

        @NotNull
        public final String getJsonrpc() {
            return this.jsonrpc;
        }

        @Nullable
        public final Object getResult() {
            return this.result;
        }

        public int hashCode() {
            int i3 = a.i(this.jsonrpc, Long.hashCode(this.id) * 31, 31);
            Object obj = this.result;
            return i3 + (obj == null ? 0 : obj.hashCode());
        }

        @NotNull
        public String toString() {
            long j2 = this.id;
            String str = this.jsonrpc;
            Object obj = this.result;
            StringBuilder v2 = androidx.work.impl.a.v(j2, "JsonRpcResult(id=", ", jsonrpc=", str);
            v2.append(", result=");
            v2.append(obj);
            v2.append(")");
            return v2.toString();
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public JsonRpcResult(long j2, @NotNull String str, @Nullable Object obj) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "jsonrpc");
            this.id = j2;
            this.jsonrpc = str;
            this.result = obj;
        }
    }

    private JsonRpcResponse() {
    }
}
