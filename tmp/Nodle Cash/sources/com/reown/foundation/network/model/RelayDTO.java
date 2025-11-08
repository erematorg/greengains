package com.reown.foundation.network.model;

import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.state.b;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import com.reown.android.push.notifications.PushMessagingService;
import com.reown.foundation.common.adapters.SubscriptionIdAdapter;
import com.reown.foundation.common.adapters.TopicAdapter;
import com.reown.foundation.common.adapters.TtlAdapter;
import com.reown.foundation.common.model.SubscriptionId;
import com.reown.foundation.common.model.Topic;
import com.reown.foundation.common.model.Ttl;
import com.reown.util.UtilFunctionsKt;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\b\f\r\u000e\u000f\u0010\u0011\u0012\u0013B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b\u0001\u0007\u0014\u0015\u0016\u0017\u0018\u0019\u001a¨\u0006\u001b"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO;", "", "<init>", "()V", "id", "", "getId", "()J", "jsonrpc", "", "getJsonrpc", "()Ljava/lang/String;", "ProposeSession", "ApproveSession", "Publish", "Subscribe", "BatchSubscribe", "Subscription", "Unsubscribe", "Error", "Lcom/reown/foundation/network/model/RelayDTO$ApproveSession;", "Lcom/reown/foundation/network/model/RelayDTO$BatchSubscribe;", "Lcom/reown/foundation/network/model/RelayDTO$ProposeSession;", "Lcom/reown/foundation/network/model/RelayDTO$Publish;", "Lcom/reown/foundation/network/model/RelayDTO$Subscribe;", "Lcom/reown/foundation/network/model/RelayDTO$Subscription;", "Lcom/reown/foundation/network/model/RelayDTO$Unsubscribe;", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class RelayDTO {

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO$ApproveSession;", "Lcom/reown/foundation/network/model/RelayDTO;", "<init>", "()V", "Request", "Result", "Lcom/reown/foundation/network/model/RelayDTO$ApproveSession$Request;", "Lcom/reown/foundation/network/model/RelayDTO$ApproveSession$Result;", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class ApproveSession extends RelayDTO {

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO$ApproveSession$Result;", "Lcom/reown/foundation/network/model/RelayDTO$ApproveSession;", "<init>", "()V", "Acknowledgement", "JsonRpcError", "Lcom/reown/foundation/network/model/RelayDTO$ApproveSession$Result$Acknowledgement;", "Lcom/reown/foundation/network/model/RelayDTO$ApproveSession$Result$JsonRpcError;", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static abstract class Result extends ApproveSession {
            public /* synthetic */ Result(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @JsonClass(generateAdapter = true)
            @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B%\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00072\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO$ApproveSession$Result$Acknowledgement;", "Lcom/reown/foundation/network/model/RelayDTO$ApproveSession$Result;", "id", "", "jsonrpc", "", "result", "", "<init>", "(JLjava/lang/String;Z)V", "getId", "()J", "getJsonrpc", "()Ljava/lang/String;", "getResult", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Acknowledgement extends Result {
                private final long id;
                @NotNull
                private final String jsonrpc;
                private final boolean result;

                /* JADX INFO: this call moved to the top of the method (can break code semantics) */
                public /* synthetic */ Acknowledgement(long j2, String str, boolean z2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                    this(j2, (i3 & 2) != 0 ? "2.0" : str, z2);
                }

                public static /* synthetic */ Acknowledgement copy$default(Acknowledgement acknowledgement, long j2, String str, boolean z2, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        j2 = acknowledgement.id;
                    }
                    if ((i3 & 2) != 0) {
                        str = acknowledgement.jsonrpc;
                    }
                    if ((i3 & 4) != 0) {
                        z2 = acknowledgement.result;
                    }
                    return acknowledgement.copy(j2, str, z2);
                }

                public final long component1() {
                    return this.id;
                }

                @NotNull
                public final String component2() {
                    return this.jsonrpc;
                }

                public final boolean component3() {
                    return this.result;
                }

                @NotNull
                public final Acknowledgement copy(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @Json(name = "result") boolean z2) {
                    Intrinsics.checkNotNullParameter(str, "jsonrpc");
                    return new Acknowledgement(j2, str, z2);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof Acknowledgement)) {
                        return false;
                    }
                    Acknowledgement acknowledgement = (Acknowledgement) obj;
                    return this.id == acknowledgement.id && Intrinsics.areEqual((Object) this.jsonrpc, (Object) acknowledgement.jsonrpc) && this.result == acknowledgement.result;
                }

                public long getId() {
                    return this.id;
                }

                @NotNull
                public String getJsonrpc() {
                    return this.jsonrpc;
                }

                public final boolean getResult() {
                    return this.result;
                }

                public int hashCode() {
                    return Boolean.hashCode(this.result) + a.i(this.jsonrpc, Long.hashCode(this.id) * 31, 31);
                }

                @NotNull
                public String toString() {
                    long j2 = this.id;
                    String str = this.jsonrpc;
                    boolean z2 = this.result;
                    StringBuilder v2 = androidx.work.impl.a.v(j2, "Acknowledgement(id=", ", jsonrpc=", str);
                    v2.append(", result=");
                    v2.append(z2);
                    v2.append(")");
                    return v2.toString();
                }

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Acknowledgement(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @Json(name = "result") boolean z2) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, "jsonrpc");
                    this.id = j2;
                    this.jsonrpc = str;
                    this.result = z2;
                }
            }

            @JsonClass(generateAdapter = true)
            @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B%\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO$ApproveSession$Result$JsonRpcError;", "Lcom/reown/foundation/network/model/RelayDTO$ApproveSession$Result;", "jsonrpc", "", "error", "Lcom/reown/foundation/network/model/RelayDTO$Error;", "id", "", "<init>", "(Ljava/lang/String;Lcom/reown/foundation/network/model/RelayDTO$Error;J)V", "getJsonrpc", "()Ljava/lang/String;", "getError", "()Lcom/reown/foundation/network/model/RelayDTO$Error;", "getId", "()J", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class JsonRpcError extends Result {
                @NotNull
                private final Error error;
                private final long id;
                @NotNull
                private final String jsonrpc;

                /* JADX INFO: this call moved to the top of the method (can break code semantics) */
                public /* synthetic */ JsonRpcError(String str, Error error2, long j2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                    this((i3 & 1) != 0 ? "2.0" : str, error2, j2);
                }

                public static /* synthetic */ JsonRpcError copy$default(JsonRpcError jsonRpcError, String str, Error error2, long j2, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        str = jsonRpcError.jsonrpc;
                    }
                    if ((i3 & 2) != 0) {
                        error2 = jsonRpcError.error;
                    }
                    if ((i3 & 4) != 0) {
                        j2 = jsonRpcError.id;
                    }
                    return jsonRpcError.copy(str, error2, j2);
                }

                @NotNull
                public final String component1() {
                    return this.jsonrpc;
                }

                @NotNull
                public final Error component2() {
                    return this.error;
                }

                public final long component3() {
                    return this.id;
                }

                @NotNull
                public final JsonRpcError copy(@NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "error") Error error2, @Json(name = "id") long j2) {
                    Intrinsics.checkNotNullParameter(str, "jsonrpc");
                    Intrinsics.checkNotNullParameter(error2, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
                    return new JsonRpcError(str, error2, j2);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof JsonRpcError)) {
                        return false;
                    }
                    JsonRpcError jsonRpcError = (JsonRpcError) obj;
                    return Intrinsics.areEqual((Object) this.jsonrpc, (Object) jsonRpcError.jsonrpc) && Intrinsics.areEqual((Object) this.error, (Object) jsonRpcError.error) && this.id == jsonRpcError.id;
                }

                @NotNull
                public final Error getError() {
                    return this.error;
                }

                public long getId() {
                    return this.id;
                }

                @NotNull
                public String getJsonrpc() {
                    return this.jsonrpc;
                }

                public int hashCode() {
                    int hashCode = this.error.hashCode();
                    return Long.hashCode(this.id) + ((hashCode + (this.jsonrpc.hashCode() * 31)) * 31);
                }

                @NotNull
                public String toString() {
                    String str = this.jsonrpc;
                    Error error2 = this.error;
                    long j2 = this.id;
                    StringBuilder sb = new StringBuilder("JsonRpcError(jsonrpc=");
                    sb.append(str);
                    sb.append(", error=");
                    sb.append(error2);
                    sb.append(", id=");
                    return android.support.v4.media.session.a.k(j2, ")", sb);
                }

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public JsonRpcError(@NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "error") Error error2, @Json(name = "id") long j2) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, "jsonrpc");
                    Intrinsics.checkNotNullParameter(error2, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
                    this.jsonrpc = str;
                    this.error = error2;
                    this.id = j2;
                }
            }

            private Result() {
                super((DefaultConstructorMarker) null);
            }
        }

        public /* synthetic */ ApproveSession(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private ApproveSession() {
            super((DefaultConstructorMarker) null);
        }

        @JsonClass(generateAdapter = true)
        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001:\u0001\u001eB/\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0003\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001f"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO$ApproveSession$Request;", "Lcom/reown/foundation/network/model/RelayDTO$ApproveSession;", "id", "", "jsonrpc", "", "method", "params", "Lcom/reown/foundation/network/model/RelayDTO$ApproveSession$Request$Params;", "<init>", "(JLjava/lang/String;Ljava/lang/String;Lcom/reown/foundation/network/model/RelayDTO$ApproveSession$Request$Params;)V", "getId", "()J", "getJsonrpc", "()Ljava/lang/String;", "getMethod", "getParams", "()Lcom/reown/foundation/network/model/RelayDTO$ApproveSession$Request$Params;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Params", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Request extends ApproveSession {
            private final long id;
            @NotNull
            private final String jsonrpc;
            @NotNull
            private final String method;
            @NotNull
            private final Params params;

            @JsonClass(generateAdapter = true)
            @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B9\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0001\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0018\u001a\u00020\tHÆ\u0003J;\u0010\u0019\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00062\b\b\u0003\u0010\u0007\u001a\u00020\u00062\b\b\u0003\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0006HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006 "}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO$ApproveSession$Request$Params;", "", "pairingTopic", "Lcom/reown/foundation/common/model/Topic;", "sessionTopic", "sessionProposalResponse", "", "sessionSettlementRequest", "correlationId", "", "<init>", "(Lcom/reown/foundation/common/model/Topic;Lcom/reown/foundation/common/model/Topic;Ljava/lang/String;Ljava/lang/String;J)V", "getPairingTopic", "()Lcom/reown/foundation/common/model/Topic;", "getSessionTopic", "getSessionProposalResponse", "()Ljava/lang/String;", "getSessionSettlementRequest", "getCorrelationId", "()J", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Params {
                private final long correlationId;
                @TopicAdapter.Qualifier
                @NotNull
                private final Topic pairingTopic;
                @NotNull
                private final String sessionProposalResponse;
                @NotNull
                private final String sessionSettlementRequest;
                @TopicAdapter.Qualifier
                @NotNull
                private final Topic sessionTopic;

                public Params(@NotNull @Json(name = "pairingTopic") Topic topic, @NotNull @Json(name = "sessionTopic") Topic topic2, @NotNull @Json(name = "sessionProposalResponse") String str, @NotNull @Json(name = "sessionSettlementRequest") String str2, @Json(name = "correlationId") long j2) {
                    Intrinsics.checkNotNullParameter(topic, "pairingTopic");
                    Intrinsics.checkNotNullParameter(topic2, "sessionTopic");
                    Intrinsics.checkNotNullParameter(str, "sessionProposalResponse");
                    Intrinsics.checkNotNullParameter(str2, "sessionSettlementRequest");
                    this.pairingTopic = topic;
                    this.sessionTopic = topic2;
                    this.sessionProposalResponse = str;
                    this.sessionSettlementRequest = str2;
                    this.correlationId = j2;
                }

                public static /* synthetic */ Params copy$default(Params params, Topic topic, Topic topic2, String str, String str2, long j2, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        topic = params.pairingTopic;
                    }
                    if ((i3 & 2) != 0) {
                        topic2 = params.sessionTopic;
                    }
                    Topic topic3 = topic2;
                    if ((i3 & 4) != 0) {
                        str = params.sessionProposalResponse;
                    }
                    String str3 = str;
                    if ((i3 & 8) != 0) {
                        str2 = params.sessionSettlementRequest;
                    }
                    String str4 = str2;
                    if ((i3 & 16) != 0) {
                        j2 = params.correlationId;
                    }
                    return params.copy(topic, topic3, str3, str4, j2);
                }

                @NotNull
                public final Topic component1() {
                    return this.pairingTopic;
                }

                @NotNull
                public final Topic component2() {
                    return this.sessionTopic;
                }

                @NotNull
                public final String component3() {
                    return this.sessionProposalResponse;
                }

                @NotNull
                public final String component4() {
                    return this.sessionSettlementRequest;
                }

                public final long component5() {
                    return this.correlationId;
                }

                @NotNull
                public final Params copy(@NotNull @Json(name = "pairingTopic") Topic topic, @NotNull @Json(name = "sessionTopic") Topic topic2, @NotNull @Json(name = "sessionProposalResponse") String str, @NotNull @Json(name = "sessionSettlementRequest") String str2, @Json(name = "correlationId") long j2) {
                    Intrinsics.checkNotNullParameter(topic, "pairingTopic");
                    Intrinsics.checkNotNullParameter(topic2, "sessionTopic");
                    Intrinsics.checkNotNullParameter(str, "sessionProposalResponse");
                    Intrinsics.checkNotNullParameter(str2, "sessionSettlementRequest");
                    return new Params(topic, topic2, str, str2, j2);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof Params)) {
                        return false;
                    }
                    Params params = (Params) obj;
                    return Intrinsics.areEqual((Object) this.pairingTopic, (Object) params.pairingTopic) && Intrinsics.areEqual((Object) this.sessionTopic, (Object) params.sessionTopic) && Intrinsics.areEqual((Object) this.sessionProposalResponse, (Object) params.sessionProposalResponse) && Intrinsics.areEqual((Object) this.sessionSettlementRequest, (Object) params.sessionSettlementRequest) && this.correlationId == params.correlationId;
                }

                public final long getCorrelationId() {
                    return this.correlationId;
                }

                @NotNull
                public final Topic getPairingTopic() {
                    return this.pairingTopic;
                }

                @NotNull
                public final String getSessionProposalResponse() {
                    return this.sessionProposalResponse;
                }

                @NotNull
                public final String getSessionSettlementRequest() {
                    return this.sessionSettlementRequest;
                }

                @NotNull
                public final Topic getSessionTopic() {
                    return this.sessionTopic;
                }

                public int hashCode() {
                    int hashCode = this.sessionTopic.hashCode();
                    return Long.hashCode(this.correlationId) + a.i(this.sessionSettlementRequest, a.i(this.sessionProposalResponse, (hashCode + (this.pairingTopic.hashCode() * 31)) * 31, 31), 31);
                }

                @NotNull
                public String toString() {
                    Topic topic = this.pairingTopic;
                    Topic topic2 = this.sessionTopic;
                    String str = this.sessionProposalResponse;
                    String str2 = this.sessionSettlementRequest;
                    long j2 = this.correlationId;
                    StringBuilder sb = new StringBuilder("Params(pairingTopic=");
                    sb.append(topic);
                    sb.append(", sessionTopic=");
                    sb.append(topic2);
                    sb.append(", sessionProposalResponse=");
                    b.w(sb, str, ", sessionSettlementRequest=", str2, ", correlationId=");
                    return android.support.v4.media.session.a.k(j2, ")", sb);
                }
            }

            /* JADX INFO: this call moved to the top of the method (can break code semantics) */
            public /* synthetic */ Request(long j2, String str, String str2, Params params2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                this((i3 & 1) != 0 ? UtilFunctionsKt.generateClientToServerId() : j2, (i3 & 2) != 0 ? "2.0" : str, (i3 & 4) != 0 ? JsonRpcRelayKt.WC_APPROVE_SESSION : str2, params2);
            }

            public static /* synthetic */ Request copy$default(Request request, long j2, String str, String str2, Params params2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    j2 = request.id;
                }
                long j3 = j2;
                if ((i3 & 2) != 0) {
                    str = request.jsonrpc;
                }
                String str3 = str;
                if ((i3 & 4) != 0) {
                    str2 = request.method;
                }
                String str4 = str2;
                if ((i3 & 8) != 0) {
                    params2 = request.params;
                }
                return request.copy(j3, str3, str4, params2);
            }

            public final long component1() {
                return this.id;
            }

            @NotNull
            public final String component2() {
                return this.jsonrpc;
            }

            @NotNull
            public final String component3() {
                return this.method;
            }

            @NotNull
            public final Params component4() {
                return this.params;
            }

            @NotNull
            public final Request copy(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "method") String str2, @NotNull @Json(name = "params") Params params2) {
                Intrinsics.checkNotNullParameter(str, "jsonrpc");
                Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
                Intrinsics.checkNotNullParameter(params2, "params");
                return new Request(j2, str, str2, params2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Request)) {
                    return false;
                }
                Request request = (Request) obj;
                return this.id == request.id && Intrinsics.areEqual((Object) this.jsonrpc, (Object) request.jsonrpc) && Intrinsics.areEqual((Object) this.method, (Object) request.method) && Intrinsics.areEqual((Object) this.params, (Object) request.params);
            }

            public long getId() {
                return this.id;
            }

            @NotNull
            public String getJsonrpc() {
                return this.jsonrpc;
            }

            @NotNull
            public final String getMethod() {
                return this.method;
            }

            @NotNull
            public final Params getParams() {
                return this.params;
            }

            public int hashCode() {
                return this.params.hashCode() + a.i(this.method, a.i(this.jsonrpc, Long.hashCode(this.id) * 31, 31), 31);
            }

            @NotNull
            public String toString() {
                long j2 = this.id;
                String str = this.jsonrpc;
                String str2 = this.method;
                Params params2 = this.params;
                StringBuilder v2 = androidx.work.impl.a.v(j2, "Request(id=", ", jsonrpc=", str);
                v2.append(", method=");
                v2.append(str2);
                v2.append(", params=");
                v2.append(params2);
                v2.append(")");
                return v2.toString();
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Request(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "method") String str2, @NotNull @Json(name = "params") Params params2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "jsonrpc");
                Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
                Intrinsics.checkNotNullParameter(params2, "params");
                this.id = j2;
                this.jsonrpc = str;
                this.method = str2;
                this.params = params2;
            }
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO$BatchSubscribe;", "Lcom/reown/foundation/network/model/RelayDTO;", "<init>", "()V", "Request", "Result", "Lcom/reown/foundation/network/model/RelayDTO$BatchSubscribe$Request;", "Lcom/reown/foundation/network/model/RelayDTO$BatchSubscribe$Result;", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class BatchSubscribe extends RelayDTO {

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO$BatchSubscribe$Result;", "Lcom/reown/foundation/network/model/RelayDTO$BatchSubscribe;", "<init>", "()V", "Acknowledgement", "JsonRpcError", "Lcom/reown/foundation/network/model/RelayDTO$BatchSubscribe$Result$Acknowledgement;", "Lcom/reown/foundation/network/model/RelayDTO$BatchSubscribe$Result$JsonRpcError;", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static abstract class Result extends BatchSubscribe {
            public /* synthetic */ Result(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @JsonClass(generateAdapter = true)
            @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B+\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0001\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007HÆ\u0003J-\u0010\u0013\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\u000e\b\u0003\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO$BatchSubscribe$Result$Acknowledgement;", "Lcom/reown/foundation/network/model/RelayDTO$BatchSubscribe$Result;", "id", "", "jsonrpc", "", "result", "", "<init>", "(JLjava/lang/String;Ljava/util/List;)V", "getId", "()J", "getJsonrpc", "()Ljava/lang/String;", "getResult", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Acknowledgement extends Result {
                private final long id;
                @NotNull
                private final String jsonrpc;
                @NotNull
                private final List<String> result;

                /* JADX INFO: this call moved to the top of the method (can break code semantics) */
                public /* synthetic */ Acknowledgement(long j2, String str, List list, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                    this(j2, (i3 & 2) != 0 ? "2.0" : str, list);
                }

                public static /* synthetic */ Acknowledgement copy$default(Acknowledgement acknowledgement, long j2, String str, List<String> list, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        j2 = acknowledgement.id;
                    }
                    if ((i3 & 2) != 0) {
                        str = acknowledgement.jsonrpc;
                    }
                    if ((i3 & 4) != 0) {
                        list = acknowledgement.result;
                    }
                    return acknowledgement.copy(j2, str, list);
                }

                public final long component1() {
                    return this.id;
                }

                @NotNull
                public final String component2() {
                    return this.jsonrpc;
                }

                @NotNull
                public final List<String> component3() {
                    return this.result;
                }

                @NotNull
                public final Acknowledgement copy(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "result") List<String> list) {
                    Intrinsics.checkNotNullParameter(str, "jsonrpc");
                    Intrinsics.checkNotNullParameter(list, "result");
                    return new Acknowledgement(j2, str, list);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof Acknowledgement)) {
                        return false;
                    }
                    Acknowledgement acknowledgement = (Acknowledgement) obj;
                    return this.id == acknowledgement.id && Intrinsics.areEqual((Object) this.jsonrpc, (Object) acknowledgement.jsonrpc) && Intrinsics.areEqual((Object) this.result, (Object) acknowledgement.result);
                }

                public long getId() {
                    return this.id;
                }

                @NotNull
                public String getJsonrpc() {
                    return this.jsonrpc;
                }

                @NotNull
                public final List<String> getResult() {
                    return this.result;
                }

                public int hashCode() {
                    return this.result.hashCode() + a.i(this.jsonrpc, Long.hashCode(this.id) * 31, 31);
                }

                @NotNull
                public String toString() {
                    long j2 = this.id;
                    String str = this.jsonrpc;
                    List<String> list = this.result;
                    StringBuilder v2 = androidx.work.impl.a.v(j2, "Acknowledgement(id=", ", jsonrpc=", str);
                    v2.append(", result=");
                    v2.append(list);
                    v2.append(")");
                    return v2.toString();
                }

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Acknowledgement(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "result") List<String> list) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, "jsonrpc");
                    Intrinsics.checkNotNullParameter(list, "result");
                    this.id = j2;
                    this.jsonrpc = str;
                    this.result = list;
                }
            }

            @JsonClass(generateAdapter = true)
            @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B%\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO$BatchSubscribe$Result$JsonRpcError;", "Lcom/reown/foundation/network/model/RelayDTO$BatchSubscribe$Result;", "jsonrpc", "", "error", "Lcom/reown/foundation/network/model/RelayDTO$Error;", "id", "", "<init>", "(Ljava/lang/String;Lcom/reown/foundation/network/model/RelayDTO$Error;J)V", "getJsonrpc", "()Ljava/lang/String;", "getError", "()Lcom/reown/foundation/network/model/RelayDTO$Error;", "getId", "()J", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class JsonRpcError extends Result {
                @NotNull
                private final Error error;
                private final long id;
                @NotNull
                private final String jsonrpc;

                /* JADX INFO: this call moved to the top of the method (can break code semantics) */
                public /* synthetic */ JsonRpcError(String str, Error error2, long j2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                    this((i3 & 1) != 0 ? "2.0" : str, error2, j2);
                }

                public static /* synthetic */ JsonRpcError copy$default(JsonRpcError jsonRpcError, String str, Error error2, long j2, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        str = jsonRpcError.jsonrpc;
                    }
                    if ((i3 & 2) != 0) {
                        error2 = jsonRpcError.error;
                    }
                    if ((i3 & 4) != 0) {
                        j2 = jsonRpcError.id;
                    }
                    return jsonRpcError.copy(str, error2, j2);
                }

                @NotNull
                public final String component1() {
                    return this.jsonrpc;
                }

                @NotNull
                public final Error component2() {
                    return this.error;
                }

                public final long component3() {
                    return this.id;
                }

                @NotNull
                public final JsonRpcError copy(@NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "error") Error error2, @Json(name = "id") long j2) {
                    Intrinsics.checkNotNullParameter(str, "jsonrpc");
                    Intrinsics.checkNotNullParameter(error2, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
                    return new JsonRpcError(str, error2, j2);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof JsonRpcError)) {
                        return false;
                    }
                    JsonRpcError jsonRpcError = (JsonRpcError) obj;
                    return Intrinsics.areEqual((Object) this.jsonrpc, (Object) jsonRpcError.jsonrpc) && Intrinsics.areEqual((Object) this.error, (Object) jsonRpcError.error) && this.id == jsonRpcError.id;
                }

                @NotNull
                public final Error getError() {
                    return this.error;
                }

                public long getId() {
                    return this.id;
                }

                @NotNull
                public String getJsonrpc() {
                    return this.jsonrpc;
                }

                public int hashCode() {
                    int hashCode = this.error.hashCode();
                    return Long.hashCode(this.id) + ((hashCode + (this.jsonrpc.hashCode() * 31)) * 31);
                }

                @NotNull
                public String toString() {
                    String str = this.jsonrpc;
                    Error error2 = this.error;
                    long j2 = this.id;
                    StringBuilder sb = new StringBuilder("JsonRpcError(jsonrpc=");
                    sb.append(str);
                    sb.append(", error=");
                    sb.append(error2);
                    sb.append(", id=");
                    return android.support.v4.media.session.a.k(j2, ")", sb);
                }

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public JsonRpcError(@NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "error") Error error2, @Json(name = "id") long j2) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, "jsonrpc");
                    Intrinsics.checkNotNullParameter(error2, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
                    this.jsonrpc = str;
                    this.error = error2;
                    this.id = j2;
                }
            }

            private Result() {
                super((DefaultConstructorMarker) null);
            }
        }

        public /* synthetic */ BatchSubscribe(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private BatchSubscribe() {
            super((DefaultConstructorMarker) null);
        }

        @JsonClass(generateAdapter = true)
        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001:\u0001\u001eB/\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0003\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001f"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO$BatchSubscribe$Request;", "Lcom/reown/foundation/network/model/RelayDTO$BatchSubscribe;", "id", "", "jsonrpc", "", "method", "params", "Lcom/reown/foundation/network/model/RelayDTO$BatchSubscribe$Request$Params;", "<init>", "(JLjava/lang/String;Ljava/lang/String;Lcom/reown/foundation/network/model/RelayDTO$BatchSubscribe$Request$Params;)V", "getId", "()J", "getJsonrpc", "()Ljava/lang/String;", "getMethod", "getParams", "()Lcom/reown/foundation/network/model/RelayDTO$BatchSubscribe$Request$Params;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Params", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Request extends BatchSubscribe {
            private final long id;
            @NotNull
            private final String jsonrpc;
            @NotNull
            private final String method;
            @NotNull
            private final Params params;

            @JsonClass(generateAdapter = true)
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u000e\b\u0001\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\n\u001a\u00020\u00002\u000e\b\u0003\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0004HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO$BatchSubscribe$Request$Params;", "", "topics", "", "", "<init>", "(Ljava/util/List;)V", "getTopics", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Params {
                @NotNull
                private final List<String> topics;

                public Params(@NotNull @Json(name = "topics") List<String> list) {
                    Intrinsics.checkNotNullParameter(list, "topics");
                    this.topics = list;
                }

                public static /* synthetic */ Params copy$default(Params params, List<String> list, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        list = params.topics;
                    }
                    return params.copy(list);
                }

                @NotNull
                public final List<String> component1() {
                    return this.topics;
                }

                @NotNull
                public final Params copy(@NotNull @Json(name = "topics") List<String> list) {
                    Intrinsics.checkNotNullParameter(list, "topics");
                    return new Params(list);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return (obj instanceof Params) && Intrinsics.areEqual((Object) this.topics, (Object) ((Params) obj).topics);
                }

                @NotNull
                public final List<String> getTopics() {
                    return this.topics;
                }

                public int hashCode() {
                    return this.topics.hashCode();
                }

                @NotNull
                public String toString() {
                    return org.spongycastle.asn1.pkcs.a.b("Params(topics=", this.topics, ")");
                }
            }

            /* JADX INFO: this call moved to the top of the method (can break code semantics) */
            public /* synthetic */ Request(long j2, String str, String str2, Params params2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                this((i3 & 1) != 0 ? UtilFunctionsKt.generateClientToServerId() : j2, (i3 & 2) != 0 ? "2.0" : str, (i3 & 4) != 0 ? JsonRpcRelayKt.IRN_BATCH_SUBSCRIBE : str2, params2);
            }

            public static /* synthetic */ Request copy$default(Request request, long j2, String str, String str2, Params params2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    j2 = request.id;
                }
                long j3 = j2;
                if ((i3 & 2) != 0) {
                    str = request.jsonrpc;
                }
                String str3 = str;
                if ((i3 & 4) != 0) {
                    str2 = request.method;
                }
                String str4 = str2;
                if ((i3 & 8) != 0) {
                    params2 = request.params;
                }
                return request.copy(j3, str3, str4, params2);
            }

            public final long component1() {
                return this.id;
            }

            @NotNull
            public final String component2() {
                return this.jsonrpc;
            }

            @NotNull
            public final String component3() {
                return this.method;
            }

            @NotNull
            public final Params component4() {
                return this.params;
            }

            @NotNull
            public final Request copy(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "method") String str2, @NotNull @Json(name = "params") Params params2) {
                Intrinsics.checkNotNullParameter(str, "jsonrpc");
                Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
                Intrinsics.checkNotNullParameter(params2, "params");
                return new Request(j2, str, str2, params2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Request)) {
                    return false;
                }
                Request request = (Request) obj;
                return this.id == request.id && Intrinsics.areEqual((Object) this.jsonrpc, (Object) request.jsonrpc) && Intrinsics.areEqual((Object) this.method, (Object) request.method) && Intrinsics.areEqual((Object) this.params, (Object) request.params);
            }

            public long getId() {
                return this.id;
            }

            @NotNull
            public String getJsonrpc() {
                return this.jsonrpc;
            }

            @NotNull
            public final String getMethod() {
                return this.method;
            }

            @NotNull
            public final Params getParams() {
                return this.params;
            }

            public int hashCode() {
                return this.params.hashCode() + a.i(this.method, a.i(this.jsonrpc, Long.hashCode(this.id) * 31, 31), 31);
            }

            @NotNull
            public String toString() {
                long j2 = this.id;
                String str = this.jsonrpc;
                String str2 = this.method;
                Params params2 = this.params;
                StringBuilder v2 = androidx.work.impl.a.v(j2, "Request(id=", ", jsonrpc=", str);
                v2.append(", method=");
                v2.append(str2);
                v2.append(", params=");
                v2.append(params2);
                v2.append(")");
                return v2.toString();
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Request(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "method") String str2, @NotNull @Json(name = "params") Params params2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "jsonrpc");
                Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
                Intrinsics.checkNotNullParameter(params2, "params");
                this.id = j2;
                this.jsonrpc = str;
                this.method = str2;
                this.params = params2;
            }
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0010\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO$Error;", "", "code", "", "message", "", "<init>", "(JLjava/lang/String;)V", "getCode", "()J", "getMessage", "()Ljava/lang/String;", "errorMessage", "getErrorMessage", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Error {
        private final long code;
        @NotNull
        private final String errorMessage;
        @NotNull
        private final String message;

        public Error(@Json(name = "code") long j2, @NotNull @Json(name = "message") String str) {
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
            this.code = j2;
            this.message = str;
            this.errorMessage = "Error code: " + j2 + "; Error message: " + str;
        }

        public static /* synthetic */ Error copy$default(Error error, long j2, String str, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                j2 = error.code;
            }
            if ((i3 & 2) != 0) {
                str = error.message;
            }
            return error.copy(j2, str);
        }

        public final long component1() {
            return this.code;
        }

        @NotNull
        public final String component2() {
            return this.message;
        }

        @NotNull
        public final Error copy(@Json(name = "code") long j2, @NotNull @Json(name = "message") String str) {
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
            return new Error(j2, str);
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

        public final long getCode() {
            return this.code;
        }

        @NotNull
        public final String getErrorMessage() {
            return this.errorMessage;
        }

        @NotNull
        public final String getMessage() {
            return this.message;
        }

        public int hashCode() {
            return this.message.hashCode() + (Long.hashCode(this.code) * 31);
        }

        @NotNull
        public String toString() {
            StringBuilder v2 = androidx.work.impl.a.v(this.code, "Error(code=", ", message=", this.message);
            v2.append(")");
            return v2.toString();
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO$ProposeSession;", "Lcom/reown/foundation/network/model/RelayDTO;", "<init>", "()V", "Request", "Result", "Lcom/reown/foundation/network/model/RelayDTO$ProposeSession$Request;", "Lcom/reown/foundation/network/model/RelayDTO$ProposeSession$Result;", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class ProposeSession extends RelayDTO {

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO$ProposeSession$Result;", "Lcom/reown/foundation/network/model/RelayDTO$ProposeSession;", "<init>", "()V", "Acknowledgement", "JsonRpcError", "Lcom/reown/foundation/network/model/RelayDTO$ProposeSession$Result$Acknowledgement;", "Lcom/reown/foundation/network/model/RelayDTO$ProposeSession$Result$JsonRpcError;", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static abstract class Result extends ProposeSession {
            public /* synthetic */ Result(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @JsonClass(generateAdapter = true)
            @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B%\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00072\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO$ProposeSession$Result$Acknowledgement;", "Lcom/reown/foundation/network/model/RelayDTO$ProposeSession$Result;", "id", "", "jsonrpc", "", "result", "", "<init>", "(JLjava/lang/String;Z)V", "getId", "()J", "getJsonrpc", "()Ljava/lang/String;", "getResult", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Acknowledgement extends Result {
                private final long id;
                @NotNull
                private final String jsonrpc;
                private final boolean result;

                /* JADX INFO: this call moved to the top of the method (can break code semantics) */
                public /* synthetic */ Acknowledgement(long j2, String str, boolean z2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                    this(j2, (i3 & 2) != 0 ? "2.0" : str, z2);
                }

                public static /* synthetic */ Acknowledgement copy$default(Acknowledgement acknowledgement, long j2, String str, boolean z2, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        j2 = acknowledgement.id;
                    }
                    if ((i3 & 2) != 0) {
                        str = acknowledgement.jsonrpc;
                    }
                    if ((i3 & 4) != 0) {
                        z2 = acknowledgement.result;
                    }
                    return acknowledgement.copy(j2, str, z2);
                }

                public final long component1() {
                    return this.id;
                }

                @NotNull
                public final String component2() {
                    return this.jsonrpc;
                }

                public final boolean component3() {
                    return this.result;
                }

                @NotNull
                public final Acknowledgement copy(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @Json(name = "result") boolean z2) {
                    Intrinsics.checkNotNullParameter(str, "jsonrpc");
                    return new Acknowledgement(j2, str, z2);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof Acknowledgement)) {
                        return false;
                    }
                    Acknowledgement acknowledgement = (Acknowledgement) obj;
                    return this.id == acknowledgement.id && Intrinsics.areEqual((Object) this.jsonrpc, (Object) acknowledgement.jsonrpc) && this.result == acknowledgement.result;
                }

                public long getId() {
                    return this.id;
                }

                @NotNull
                public String getJsonrpc() {
                    return this.jsonrpc;
                }

                public final boolean getResult() {
                    return this.result;
                }

                public int hashCode() {
                    return Boolean.hashCode(this.result) + a.i(this.jsonrpc, Long.hashCode(this.id) * 31, 31);
                }

                @NotNull
                public String toString() {
                    long j2 = this.id;
                    String str = this.jsonrpc;
                    boolean z2 = this.result;
                    StringBuilder v2 = androidx.work.impl.a.v(j2, "Acknowledgement(id=", ", jsonrpc=", str);
                    v2.append(", result=");
                    v2.append(z2);
                    v2.append(")");
                    return v2.toString();
                }

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Acknowledgement(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @Json(name = "result") boolean z2) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, "jsonrpc");
                    this.id = j2;
                    this.jsonrpc = str;
                    this.result = z2;
                }
            }

            @JsonClass(generateAdapter = true)
            @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B%\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO$ProposeSession$Result$JsonRpcError;", "Lcom/reown/foundation/network/model/RelayDTO$ProposeSession$Result;", "jsonrpc", "", "error", "Lcom/reown/foundation/network/model/RelayDTO$Error;", "id", "", "<init>", "(Ljava/lang/String;Lcom/reown/foundation/network/model/RelayDTO$Error;J)V", "getJsonrpc", "()Ljava/lang/String;", "getError", "()Lcom/reown/foundation/network/model/RelayDTO$Error;", "getId", "()J", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class JsonRpcError extends Result {
                @NotNull
                private final Error error;
                private final long id;
                @NotNull
                private final String jsonrpc;

                /* JADX INFO: this call moved to the top of the method (can break code semantics) */
                public /* synthetic */ JsonRpcError(String str, Error error2, long j2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                    this((i3 & 1) != 0 ? "2.0" : str, error2, j2);
                }

                public static /* synthetic */ JsonRpcError copy$default(JsonRpcError jsonRpcError, String str, Error error2, long j2, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        str = jsonRpcError.jsonrpc;
                    }
                    if ((i3 & 2) != 0) {
                        error2 = jsonRpcError.error;
                    }
                    if ((i3 & 4) != 0) {
                        j2 = jsonRpcError.id;
                    }
                    return jsonRpcError.copy(str, error2, j2);
                }

                @NotNull
                public final String component1() {
                    return this.jsonrpc;
                }

                @NotNull
                public final Error component2() {
                    return this.error;
                }

                public final long component3() {
                    return this.id;
                }

                @NotNull
                public final JsonRpcError copy(@NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "error") Error error2, @Json(name = "id") long j2) {
                    Intrinsics.checkNotNullParameter(str, "jsonrpc");
                    Intrinsics.checkNotNullParameter(error2, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
                    return new JsonRpcError(str, error2, j2);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof JsonRpcError)) {
                        return false;
                    }
                    JsonRpcError jsonRpcError = (JsonRpcError) obj;
                    return Intrinsics.areEqual((Object) this.jsonrpc, (Object) jsonRpcError.jsonrpc) && Intrinsics.areEqual((Object) this.error, (Object) jsonRpcError.error) && this.id == jsonRpcError.id;
                }

                @NotNull
                public final Error getError() {
                    return this.error;
                }

                public long getId() {
                    return this.id;
                }

                @NotNull
                public String getJsonrpc() {
                    return this.jsonrpc;
                }

                public int hashCode() {
                    int hashCode = this.error.hashCode();
                    return Long.hashCode(this.id) + ((hashCode + (this.jsonrpc.hashCode() * 31)) * 31);
                }

                @NotNull
                public String toString() {
                    String str = this.jsonrpc;
                    Error error2 = this.error;
                    long j2 = this.id;
                    StringBuilder sb = new StringBuilder("JsonRpcError(jsonrpc=");
                    sb.append(str);
                    sb.append(", error=");
                    sb.append(error2);
                    sb.append(", id=");
                    return android.support.v4.media.session.a.k(j2, ")", sb);
                }

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public JsonRpcError(@NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "error") Error error2, @Json(name = "id") long j2) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, "jsonrpc");
                    Intrinsics.checkNotNullParameter(error2, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
                    this.jsonrpc = str;
                    this.error = error2;
                    this.id = j2;
                }
            }

            private Result() {
                super((DefaultConstructorMarker) null);
            }
        }

        public /* synthetic */ ProposeSession(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private ProposeSession() {
            super((DefaultConstructorMarker) null);
        }

        @JsonClass(generateAdapter = true)
        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001:\u0001\u001eB/\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0003\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001f"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO$ProposeSession$Request;", "Lcom/reown/foundation/network/model/RelayDTO$ProposeSession;", "id", "", "jsonrpc", "", "method", "params", "Lcom/reown/foundation/network/model/RelayDTO$ProposeSession$Request$Params;", "<init>", "(JLjava/lang/String;Ljava/lang/String;Lcom/reown/foundation/network/model/RelayDTO$ProposeSession$Request$Params;)V", "getId", "()J", "getJsonrpc", "()Ljava/lang/String;", "getMethod", "getParams", "()Lcom/reown/foundation/network/model/RelayDTO$ProposeSession$Request$Params;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Params", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Request extends ProposeSession {
            private final long id;
            @NotNull
            private final String jsonrpc;
            @NotNull
            private final String method;
            @NotNull
            private final Params params;

            @JsonClass(generateAdapter = true)
            @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B1\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J3\u0010\u0016\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\b\u0003\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001d"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO$ProposeSession$Request$Params;", "", "pairingTopic", "Lcom/reown/foundation/common/model/Topic;", "sessionProposal", "", "attestation", "correlationId", "", "<init>", "(Lcom/reown/foundation/common/model/Topic;Ljava/lang/String;Ljava/lang/String;J)V", "getPairingTopic", "()Lcom/reown/foundation/common/model/Topic;", "getSessionProposal", "()Ljava/lang/String;", "getAttestation", "getCorrelationId", "()J", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Params {
                @Nullable
                private final String attestation;
                private final long correlationId;
                @TopicAdapter.Qualifier
                @NotNull
                private final Topic pairingTopic;
                @NotNull
                private final String sessionProposal;

                public Params(@NotNull @Json(name = "pairingTopic") Topic topic, @NotNull @Json(name = "sessionProposal") String str, @Nullable @Json(name = "attestation") String str2, @Json(name = "correlationId") long j2) {
                    Intrinsics.checkNotNullParameter(topic, "pairingTopic");
                    Intrinsics.checkNotNullParameter(str, "sessionProposal");
                    this.pairingTopic = topic;
                    this.sessionProposal = str;
                    this.attestation = str2;
                    this.correlationId = j2;
                }

                public static /* synthetic */ Params copy$default(Params params, Topic topic, String str, String str2, long j2, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        topic = params.pairingTopic;
                    }
                    if ((i3 & 2) != 0) {
                        str = params.sessionProposal;
                    }
                    String str3 = str;
                    if ((i3 & 4) != 0) {
                        str2 = params.attestation;
                    }
                    String str4 = str2;
                    if ((i3 & 8) != 0) {
                        j2 = params.correlationId;
                    }
                    return params.copy(topic, str3, str4, j2);
                }

                @NotNull
                public final Topic component1() {
                    return this.pairingTopic;
                }

                @NotNull
                public final String component2() {
                    return this.sessionProposal;
                }

                @Nullable
                public final String component3() {
                    return this.attestation;
                }

                public final long component4() {
                    return this.correlationId;
                }

                @NotNull
                public final Params copy(@NotNull @Json(name = "pairingTopic") Topic topic, @NotNull @Json(name = "sessionProposal") String str, @Nullable @Json(name = "attestation") String str2, @Json(name = "correlationId") long j2) {
                    Intrinsics.checkNotNullParameter(topic, "pairingTopic");
                    Intrinsics.checkNotNullParameter(str, "sessionProposal");
                    return new Params(topic, str, str2, j2);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof Params)) {
                        return false;
                    }
                    Params params = (Params) obj;
                    return Intrinsics.areEqual((Object) this.pairingTopic, (Object) params.pairingTopic) && Intrinsics.areEqual((Object) this.sessionProposal, (Object) params.sessionProposal) && Intrinsics.areEqual((Object) this.attestation, (Object) params.attestation) && this.correlationId == params.correlationId;
                }

                @Nullable
                public final String getAttestation() {
                    return this.attestation;
                }

                public final long getCorrelationId() {
                    return this.correlationId;
                }

                @NotNull
                public final Topic getPairingTopic() {
                    return this.pairingTopic;
                }

                @NotNull
                public final String getSessionProposal() {
                    return this.sessionProposal;
                }

                public int hashCode() {
                    int i3 = a.i(this.sessionProposal, this.pairingTopic.hashCode() * 31, 31);
                    String str = this.attestation;
                    return Long.hashCode(this.correlationId) + ((i3 + (str == null ? 0 : str.hashCode())) * 31);
                }

                @NotNull
                public String toString() {
                    Topic topic = this.pairingTopic;
                    String str = this.sessionProposal;
                    String str2 = this.attestation;
                    long j2 = this.correlationId;
                    return "Params(pairingTopic=" + topic + ", sessionProposal=" + str + ", attestation=" + str2 + ", correlationId=" + j2 + ")";
                }
            }

            /* JADX INFO: this call moved to the top of the method (can break code semantics) */
            public /* synthetic */ Request(long j2, String str, String str2, Params params2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                this((i3 & 1) != 0 ? UtilFunctionsKt.generateClientToServerId() : j2, (i3 & 2) != 0 ? "2.0" : str, (i3 & 4) != 0 ? JsonRpcRelayKt.WC_PROPOSE_SESSION : str2, params2);
            }

            public static /* synthetic */ Request copy$default(Request request, long j2, String str, String str2, Params params2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    j2 = request.id;
                }
                long j3 = j2;
                if ((i3 & 2) != 0) {
                    str = request.jsonrpc;
                }
                String str3 = str;
                if ((i3 & 4) != 0) {
                    str2 = request.method;
                }
                String str4 = str2;
                if ((i3 & 8) != 0) {
                    params2 = request.params;
                }
                return request.copy(j3, str3, str4, params2);
            }

            public final long component1() {
                return this.id;
            }

            @NotNull
            public final String component2() {
                return this.jsonrpc;
            }

            @NotNull
            public final String component3() {
                return this.method;
            }

            @NotNull
            public final Params component4() {
                return this.params;
            }

            @NotNull
            public final Request copy(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "method") String str2, @NotNull @Json(name = "params") Params params2) {
                Intrinsics.checkNotNullParameter(str, "jsonrpc");
                Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
                Intrinsics.checkNotNullParameter(params2, "params");
                return new Request(j2, str, str2, params2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Request)) {
                    return false;
                }
                Request request = (Request) obj;
                return this.id == request.id && Intrinsics.areEqual((Object) this.jsonrpc, (Object) request.jsonrpc) && Intrinsics.areEqual((Object) this.method, (Object) request.method) && Intrinsics.areEqual((Object) this.params, (Object) request.params);
            }

            public long getId() {
                return this.id;
            }

            @NotNull
            public String getJsonrpc() {
                return this.jsonrpc;
            }

            @NotNull
            public final String getMethod() {
                return this.method;
            }

            @NotNull
            public final Params getParams() {
                return this.params;
            }

            public int hashCode() {
                return this.params.hashCode() + a.i(this.method, a.i(this.jsonrpc, Long.hashCode(this.id) * 31, 31), 31);
            }

            @NotNull
            public String toString() {
                long j2 = this.id;
                String str = this.jsonrpc;
                String str2 = this.method;
                Params params2 = this.params;
                StringBuilder v2 = androidx.work.impl.a.v(j2, "Request(id=", ", jsonrpc=", str);
                v2.append(", method=");
                v2.append(str2);
                v2.append(", params=");
                v2.append(params2);
                v2.append(")");
                return v2.toString();
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Request(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "method") String str2, @NotNull @Json(name = "params") Params params2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "jsonrpc");
                Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
                Intrinsics.checkNotNullParameter(params2, "params");
                this.id = j2;
                this.jsonrpc = str;
                this.method = str2;
                this.params = params2;
            }
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO$Publish;", "Lcom/reown/foundation/network/model/RelayDTO;", "<init>", "()V", "Request", "Result", "Lcom/reown/foundation/network/model/RelayDTO$Publish$Request;", "Lcom/reown/foundation/network/model/RelayDTO$Publish$Result;", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class Publish extends RelayDTO {

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO$Publish$Result;", "Lcom/reown/foundation/network/model/RelayDTO$Publish;", "<init>", "()V", "Acknowledgement", "JsonRpcError", "Lcom/reown/foundation/network/model/RelayDTO$Publish$Result$Acknowledgement;", "Lcom/reown/foundation/network/model/RelayDTO$Publish$Result$JsonRpcError;", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static abstract class Result extends Publish {
            public /* synthetic */ Result(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @JsonClass(generateAdapter = true)
            @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B%\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00072\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO$Publish$Result$Acknowledgement;", "Lcom/reown/foundation/network/model/RelayDTO$Publish$Result;", "id", "", "jsonrpc", "", "result", "", "<init>", "(JLjava/lang/String;Z)V", "getId", "()J", "getJsonrpc", "()Ljava/lang/String;", "getResult", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Acknowledgement extends Result {
                private final long id;
                @NotNull
                private final String jsonrpc;
                private final boolean result;

                /* JADX INFO: this call moved to the top of the method (can break code semantics) */
                public /* synthetic */ Acknowledgement(long j2, String str, boolean z2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                    this(j2, (i3 & 2) != 0 ? "2.0" : str, z2);
                }

                public static /* synthetic */ Acknowledgement copy$default(Acknowledgement acknowledgement, long j2, String str, boolean z2, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        j2 = acknowledgement.id;
                    }
                    if ((i3 & 2) != 0) {
                        str = acknowledgement.jsonrpc;
                    }
                    if ((i3 & 4) != 0) {
                        z2 = acknowledgement.result;
                    }
                    return acknowledgement.copy(j2, str, z2);
                }

                public final long component1() {
                    return this.id;
                }

                @NotNull
                public final String component2() {
                    return this.jsonrpc;
                }

                public final boolean component3() {
                    return this.result;
                }

                @NotNull
                public final Acknowledgement copy(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @Json(name = "result") boolean z2) {
                    Intrinsics.checkNotNullParameter(str, "jsonrpc");
                    return new Acknowledgement(j2, str, z2);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof Acknowledgement)) {
                        return false;
                    }
                    Acknowledgement acknowledgement = (Acknowledgement) obj;
                    return this.id == acknowledgement.id && Intrinsics.areEqual((Object) this.jsonrpc, (Object) acknowledgement.jsonrpc) && this.result == acknowledgement.result;
                }

                public long getId() {
                    return this.id;
                }

                @NotNull
                public String getJsonrpc() {
                    return this.jsonrpc;
                }

                public final boolean getResult() {
                    return this.result;
                }

                public int hashCode() {
                    return Boolean.hashCode(this.result) + a.i(this.jsonrpc, Long.hashCode(this.id) * 31, 31);
                }

                @NotNull
                public String toString() {
                    long j2 = this.id;
                    String str = this.jsonrpc;
                    boolean z2 = this.result;
                    StringBuilder v2 = androidx.work.impl.a.v(j2, "Acknowledgement(id=", ", jsonrpc=", str);
                    v2.append(", result=");
                    v2.append(z2);
                    v2.append(")");
                    return v2.toString();
                }

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Acknowledgement(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @Json(name = "result") boolean z2) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, "jsonrpc");
                    this.id = j2;
                    this.jsonrpc = str;
                    this.result = z2;
                }
            }

            @JsonClass(generateAdapter = true)
            @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B%\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO$Publish$Result$JsonRpcError;", "Lcom/reown/foundation/network/model/RelayDTO$Publish$Result;", "jsonrpc", "", "error", "Lcom/reown/foundation/network/model/RelayDTO$Error;", "id", "", "<init>", "(Ljava/lang/String;Lcom/reown/foundation/network/model/RelayDTO$Error;J)V", "getJsonrpc", "()Ljava/lang/String;", "getError", "()Lcom/reown/foundation/network/model/RelayDTO$Error;", "getId", "()J", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class JsonRpcError extends Result {
                @NotNull
                private final Error error;
                private final long id;
                @NotNull
                private final String jsonrpc;

                /* JADX INFO: this call moved to the top of the method (can break code semantics) */
                public /* synthetic */ JsonRpcError(String str, Error error2, long j2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                    this((i3 & 1) != 0 ? "2.0" : str, error2, j2);
                }

                public static /* synthetic */ JsonRpcError copy$default(JsonRpcError jsonRpcError, String str, Error error2, long j2, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        str = jsonRpcError.jsonrpc;
                    }
                    if ((i3 & 2) != 0) {
                        error2 = jsonRpcError.error;
                    }
                    if ((i3 & 4) != 0) {
                        j2 = jsonRpcError.id;
                    }
                    return jsonRpcError.copy(str, error2, j2);
                }

                @NotNull
                public final String component1() {
                    return this.jsonrpc;
                }

                @NotNull
                public final Error component2() {
                    return this.error;
                }

                public final long component3() {
                    return this.id;
                }

                @NotNull
                public final JsonRpcError copy(@NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "error") Error error2, @Json(name = "id") long j2) {
                    Intrinsics.checkNotNullParameter(str, "jsonrpc");
                    Intrinsics.checkNotNullParameter(error2, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
                    return new JsonRpcError(str, error2, j2);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof JsonRpcError)) {
                        return false;
                    }
                    JsonRpcError jsonRpcError = (JsonRpcError) obj;
                    return Intrinsics.areEqual((Object) this.jsonrpc, (Object) jsonRpcError.jsonrpc) && Intrinsics.areEqual((Object) this.error, (Object) jsonRpcError.error) && this.id == jsonRpcError.id;
                }

                @NotNull
                public final Error getError() {
                    return this.error;
                }

                public long getId() {
                    return this.id;
                }

                @NotNull
                public String getJsonrpc() {
                    return this.jsonrpc;
                }

                public int hashCode() {
                    int hashCode = this.error.hashCode();
                    return Long.hashCode(this.id) + ((hashCode + (this.jsonrpc.hashCode() * 31)) * 31);
                }

                @NotNull
                public String toString() {
                    String str = this.jsonrpc;
                    Error error2 = this.error;
                    long j2 = this.id;
                    StringBuilder sb = new StringBuilder("JsonRpcError(jsonrpc=");
                    sb.append(str);
                    sb.append(", error=");
                    sb.append(error2);
                    sb.append(", id=");
                    return android.support.v4.media.session.a.k(j2, ")", sb);
                }

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public JsonRpcError(@NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "error") Error error2, @Json(name = "id") long j2) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, "jsonrpc");
                    Intrinsics.checkNotNullParameter(error2, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
                    this.jsonrpc = str;
                    this.error = error2;
                    this.id = j2;
                }
            }

            private Result() {
                super((DefaultConstructorMarker) null);
            }
        }

        public /* synthetic */ Publish(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Publish() {
            super((DefaultConstructorMarker) null);
        }

        @JsonClass(generateAdapter = true)
        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001:\u0001\u001eB/\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0003\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001f"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO$Publish$Request;", "Lcom/reown/foundation/network/model/RelayDTO$Publish;", "id", "", "jsonrpc", "", "method", "params", "Lcom/reown/foundation/network/model/RelayDTO$Publish$Request$Params;", "<init>", "(JLjava/lang/String;Ljava/lang/String;Lcom/reown/foundation/network/model/RelayDTO$Publish$Request$Params;)V", "getId", "()J", "getJsonrpc", "()Ljava/lang/String;", "getMethod", "getParams", "()Lcom/reown/foundation/network/model/RelayDTO$Publish$Request$Params;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Params", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Request extends Publish {
            private final long id;
            @NotNull
            private final String jsonrpc;
            @NotNull
            private final String method;
            @NotNull
            private final Params params;

            @JsonClass(generateAdapter = true)
            @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\b)\b\b\u0018\u00002\u00020\u0001B\u0001\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0001\u0010\b\u001a\u00020\t\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\r\u0012\u0010\b\u0001\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000f\u0012\n\b\u0001\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\u0010\b\u0001\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000f\u0012\u0010\b\u0001\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000f¢\u0006\u0004\b\u0013\u0010\u0014J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0005HÆ\u0003J\t\u0010*\u001a\u00020\u0007HÆ\u0003J\t\u0010+\u001a\u00020\tHÆ\u0003J\u0010\u0010,\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010\u001eJ\u0010\u0010-\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010!J\u0011\u0010.\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000fHÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0011\u00100\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000fHÆ\u0003J\u0011\u00101\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000fHÆ\u0003J\u0001\u00102\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00072\b\b\u0003\u0010\b\u001a\u00020\t2\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\r2\u0010\b\u0003\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000f2\n\b\u0003\u0010\u0010\u001a\u0004\u0018\u00010\u00052\u0010\b\u0003\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000f2\u0010\b\u0003\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000fHÆ\u0001¢\u0006\u0002\u00103J\u0013\u00104\u001a\u00020\u000b2\b\u00105\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00106\u001a\u00020\tHÖ\u0001J\t\u00107\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0015\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b\u001d\u0010\u001eR\u0015\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\n\n\u0002\u0010\"\u001a\u0004\b \u0010!R\u0019\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0018R\u0019\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b&\u0010$R\u0019\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b'\u0010$¨\u00068"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO$Publish$Request$Params;", "", "topic", "Lcom/reown/foundation/common/model/Topic;", "message", "", "ttl", "Lcom/reown/foundation/common/model/Ttl;", "tag", "", "prompt", "", "correlationId", "", "rpcMethods", "", "chainId", "txHashes", "contractAddresses", "<init>", "(Lcom/reown/foundation/common/model/Topic;Ljava/lang/String;Lcom/reown/foundation/common/model/Ttl;ILjava/lang/Boolean;Ljava/lang/Long;Ljava/util/List;Ljava/lang/String;Ljava/util/List;Ljava/util/List;)V", "getTopic", "()Lcom/reown/foundation/common/model/Topic;", "getMessage", "()Ljava/lang/String;", "getTtl", "()Lcom/reown/foundation/common/model/Ttl;", "getTag", "()I", "getPrompt", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getCorrelationId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getRpcMethods", "()Ljava/util/List;", "getChainId", "getTxHashes", "getContractAddresses", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "(Lcom/reown/foundation/common/model/Topic;Ljava/lang/String;Lcom/reown/foundation/common/model/Ttl;ILjava/lang/Boolean;Ljava/lang/Long;Ljava/util/List;Ljava/lang/String;Ljava/util/List;Ljava/util/List;)Lcom/reown/foundation/network/model/RelayDTO$Publish$Request$Params;", "equals", "other", "hashCode", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Params {
                @Nullable
                private final String chainId;
                @Nullable
                private final List<String> contractAddresses;
                @Nullable
                private final Long correlationId;
                @NotNull
                private final String message;
                @Nullable
                private final Boolean prompt;
                @Nullable
                private final List<String> rpcMethods;
                private final int tag;
                @TopicAdapter.Qualifier
                @NotNull
                private final Topic topic;
                @TtlAdapter.Qualifier
                @NotNull
                private final Ttl ttl;
                @Nullable
                private final List<String> txHashes;

                public Params(@NotNull @Json(name = "topic") Topic topic2, @NotNull @Json(name = "message") String str, @NotNull @Json(name = "ttl") Ttl ttl2, @Json(name = "tag") int i3, @Nullable @Json(name = "prompt") Boolean bool, @Nullable @Json(name = "correlationId") Long l2, @Nullable @Json(name = "rpcMethods") List<String> list, @Nullable @Json(name = "chainId") String str2, @Nullable @Json(name = "txHashes") List<String> list2, @Nullable @Json(name = "contractAddresses") List<String> list3) {
                    Intrinsics.checkNotNullParameter(topic2, PushMessagingService.KEY_TOPIC);
                    Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                    Intrinsics.checkNotNullParameter(ttl2, "ttl");
                    this.topic = topic2;
                    this.message = str;
                    this.ttl = ttl2;
                    this.tag = i3;
                    this.prompt = bool;
                    this.correlationId = l2;
                    this.rpcMethods = list;
                    this.chainId = str2;
                    this.txHashes = list2;
                    this.contractAddresses = list3;
                }

                public static /* synthetic */ Params copy$default(Params params, Topic topic2, String str, Ttl ttl2, int i3, Boolean bool, Long l2, List list, String str2, List list2, List list3, int i4, Object obj) {
                    Params params2 = params;
                    int i5 = i4;
                    return params.copy((i5 & 1) != 0 ? params2.topic : topic2, (i5 & 2) != 0 ? params2.message : str, (i5 & 4) != 0 ? params2.ttl : ttl2, (i5 & 8) != 0 ? params2.tag : i3, (i5 & 16) != 0 ? params2.prompt : bool, (i5 & 32) != 0 ? params2.correlationId : l2, (i5 & 64) != 0 ? params2.rpcMethods : list, (i5 & 128) != 0 ? params2.chainId : str2, (i5 & 256) != 0 ? params2.txHashes : list2, (i5 & 512) != 0 ? params2.contractAddresses : list3);
                }

                @NotNull
                public final Topic component1() {
                    return this.topic;
                }

                @Nullable
                public final List<String> component10() {
                    return this.contractAddresses;
                }

                @NotNull
                public final String component2() {
                    return this.message;
                }

                @NotNull
                public final Ttl component3() {
                    return this.ttl;
                }

                public final int component4() {
                    return this.tag;
                }

                @Nullable
                public final Boolean component5() {
                    return this.prompt;
                }

                @Nullable
                public final Long component6() {
                    return this.correlationId;
                }

                @Nullable
                public final List<String> component7() {
                    return this.rpcMethods;
                }

                @Nullable
                public final String component8() {
                    return this.chainId;
                }

                @Nullable
                public final List<String> component9() {
                    return this.txHashes;
                }

                @NotNull
                public final Params copy(@NotNull @Json(name = "topic") Topic topic2, @NotNull @Json(name = "message") String str, @NotNull @Json(name = "ttl") Ttl ttl2, @Json(name = "tag") int i3, @Nullable @Json(name = "prompt") Boolean bool, @Nullable @Json(name = "correlationId") Long l2, @Nullable @Json(name = "rpcMethods") List<String> list, @Nullable @Json(name = "chainId") String str2, @Nullable @Json(name = "txHashes") List<String> list2, @Nullable @Json(name = "contractAddresses") List<String> list3) {
                    Intrinsics.checkNotNullParameter(topic2, PushMessagingService.KEY_TOPIC);
                    Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                    Intrinsics.checkNotNullParameter(ttl2, "ttl");
                    return new Params(topic2, str, ttl2, i3, bool, l2, list, str2, list2, list3);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof Params)) {
                        return false;
                    }
                    Params params = (Params) obj;
                    return Intrinsics.areEqual((Object) this.topic, (Object) params.topic) && Intrinsics.areEqual((Object) this.message, (Object) params.message) && Intrinsics.areEqual((Object) this.ttl, (Object) params.ttl) && this.tag == params.tag && Intrinsics.areEqual((Object) this.prompt, (Object) params.prompt) && Intrinsics.areEqual((Object) this.correlationId, (Object) params.correlationId) && Intrinsics.areEqual((Object) this.rpcMethods, (Object) params.rpcMethods) && Intrinsics.areEqual((Object) this.chainId, (Object) params.chainId) && Intrinsics.areEqual((Object) this.txHashes, (Object) params.txHashes) && Intrinsics.areEqual((Object) this.contractAddresses, (Object) params.contractAddresses);
                }

                @Nullable
                public final String getChainId() {
                    return this.chainId;
                }

                @Nullable
                public final List<String> getContractAddresses() {
                    return this.contractAddresses;
                }

                @Nullable
                public final Long getCorrelationId() {
                    return this.correlationId;
                }

                @NotNull
                public final String getMessage() {
                    return this.message;
                }

                @Nullable
                public final Boolean getPrompt() {
                    return this.prompt;
                }

                @Nullable
                public final List<String> getRpcMethods() {
                    return this.rpcMethods;
                }

                public final int getTag() {
                    return this.tag;
                }

                @NotNull
                public final Topic getTopic() {
                    return this.topic;
                }

                @NotNull
                public final Ttl getTtl() {
                    return this.ttl;
                }

                @Nullable
                public final List<String> getTxHashes() {
                    return this.txHashes;
                }

                public int hashCode() {
                    int c3 = a.c(this.tag, (this.ttl.hashCode() + a.i(this.message, this.topic.hashCode() * 31, 31)) * 31, 31);
                    Boolean bool = this.prompt;
                    int i3 = 0;
                    int hashCode = (c3 + (bool == null ? 0 : bool.hashCode())) * 31;
                    Long l2 = this.correlationId;
                    int hashCode2 = (hashCode + (l2 == null ? 0 : l2.hashCode())) * 31;
                    List<String> list = this.rpcMethods;
                    int hashCode3 = (hashCode2 + (list == null ? 0 : list.hashCode())) * 31;
                    String str = this.chainId;
                    int hashCode4 = (hashCode3 + (str == null ? 0 : str.hashCode())) * 31;
                    List<String> list2 = this.txHashes;
                    int hashCode5 = (hashCode4 + (list2 == null ? 0 : list2.hashCode())) * 31;
                    List<String> list3 = this.contractAddresses;
                    if (list3 != null) {
                        i3 = list3.hashCode();
                    }
                    return hashCode5 + i3;
                }

                @NotNull
                public String toString() {
                    Topic topic2 = this.topic;
                    String str = this.message;
                    Ttl ttl2 = this.ttl;
                    int i3 = this.tag;
                    Boolean bool = this.prompt;
                    Long l2 = this.correlationId;
                    List<String> list = this.rpcMethods;
                    String str2 = this.chainId;
                    List<String> list2 = this.txHashes;
                    List<String> list3 = this.contractAddresses;
                    return "Params(topic=" + topic2 + ", message=" + str + ", ttl=" + ttl2 + ", tag=" + i3 + ", prompt=" + bool + ", correlationId=" + l2 + ", rpcMethods=" + list + ", chainId=" + str2 + ", txHashes=" + list2 + ", contractAddresses=" + list3 + ")";
                }
            }

            /* JADX INFO: this call moved to the top of the method (can break code semantics) */
            public /* synthetic */ Request(long j2, String str, String str2, Params params2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                this((i3 & 1) != 0 ? UtilFunctionsKt.generateClientToServerId() : j2, (i3 & 2) != 0 ? "2.0" : str, (i3 & 4) != 0 ? JsonRpcRelayKt.IRN_PUBLISH : str2, params2);
            }

            public static /* synthetic */ Request copy$default(Request request, long j2, String str, String str2, Params params2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    j2 = request.id;
                }
                long j3 = j2;
                if ((i3 & 2) != 0) {
                    str = request.jsonrpc;
                }
                String str3 = str;
                if ((i3 & 4) != 0) {
                    str2 = request.method;
                }
                String str4 = str2;
                if ((i3 & 8) != 0) {
                    params2 = request.params;
                }
                return request.copy(j3, str3, str4, params2);
            }

            public final long component1() {
                return this.id;
            }

            @NotNull
            public final String component2() {
                return this.jsonrpc;
            }

            @NotNull
            public final String component3() {
                return this.method;
            }

            @NotNull
            public final Params component4() {
                return this.params;
            }

            @NotNull
            public final Request copy(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "method") String str2, @NotNull @Json(name = "params") Params params2) {
                Intrinsics.checkNotNullParameter(str, "jsonrpc");
                Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
                Intrinsics.checkNotNullParameter(params2, "params");
                return new Request(j2, str, str2, params2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Request)) {
                    return false;
                }
                Request request = (Request) obj;
                return this.id == request.id && Intrinsics.areEqual((Object) this.jsonrpc, (Object) request.jsonrpc) && Intrinsics.areEqual((Object) this.method, (Object) request.method) && Intrinsics.areEqual((Object) this.params, (Object) request.params);
            }

            public long getId() {
                return this.id;
            }

            @NotNull
            public String getJsonrpc() {
                return this.jsonrpc;
            }

            @NotNull
            public final String getMethod() {
                return this.method;
            }

            @NotNull
            public final Params getParams() {
                return this.params;
            }

            public int hashCode() {
                return this.params.hashCode() + a.i(this.method, a.i(this.jsonrpc, Long.hashCode(this.id) * 31, 31), 31);
            }

            @NotNull
            public String toString() {
                long j2 = this.id;
                String str = this.jsonrpc;
                String str2 = this.method;
                Params params2 = this.params;
                StringBuilder v2 = androidx.work.impl.a.v(j2, "Request(id=", ", jsonrpc=", str);
                v2.append(", method=");
                v2.append(str2);
                v2.append(", params=");
                v2.append(params2);
                v2.append(")");
                return v2.toString();
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Request(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "method") String str2, @NotNull @Json(name = "params") Params params2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "jsonrpc");
                Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
                Intrinsics.checkNotNullParameter(params2, "params");
                this.id = j2;
                this.jsonrpc = str;
                this.method = str2;
                this.params = params2;
            }
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO$Subscribe;", "Lcom/reown/foundation/network/model/RelayDTO;", "<init>", "()V", "Request", "Result", "Lcom/reown/foundation/network/model/RelayDTO$Subscribe$Request;", "Lcom/reown/foundation/network/model/RelayDTO$Subscribe$Result;", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class Subscribe extends RelayDTO {

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO$Subscribe$Result;", "Lcom/reown/foundation/network/model/RelayDTO$Subscribe;", "<init>", "()V", "Acknowledgement", "JsonRpcError", "Lcom/reown/foundation/network/model/RelayDTO$Subscribe$Result$Acknowledgement;", "Lcom/reown/foundation/network/model/RelayDTO$Subscribe$Result$JsonRpcError;", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static abstract class Result extends Subscribe {
            public /* synthetic */ Result(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @JsonClass(generateAdapter = true)
            @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B%\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO$Subscribe$Result$Acknowledgement;", "Lcom/reown/foundation/network/model/RelayDTO$Subscribe$Result;", "id", "", "jsonrpc", "", "result", "Lcom/reown/foundation/common/model/SubscriptionId;", "<init>", "(JLjava/lang/String;Lcom/reown/foundation/common/model/SubscriptionId;)V", "getId", "()J", "getJsonrpc", "()Ljava/lang/String;", "getResult", "()Lcom/reown/foundation/common/model/SubscriptionId;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Acknowledgement extends Result {
                private final long id;
                @NotNull
                private final String jsonrpc;
                @NotNull
                @SubscriptionIdAdapter.Qualifier
                private final SubscriptionId result;

                /* JADX INFO: this call moved to the top of the method (can break code semantics) */
                public /* synthetic */ Acknowledgement(long j2, String str, SubscriptionId subscriptionId, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                    this(j2, (i3 & 2) != 0 ? "2.0" : str, subscriptionId);
                }

                public static /* synthetic */ Acknowledgement copy$default(Acknowledgement acknowledgement, long j2, String str, SubscriptionId subscriptionId, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        j2 = acknowledgement.id;
                    }
                    if ((i3 & 2) != 0) {
                        str = acknowledgement.jsonrpc;
                    }
                    if ((i3 & 4) != 0) {
                        subscriptionId = acknowledgement.result;
                    }
                    return acknowledgement.copy(j2, str, subscriptionId);
                }

                public final long component1() {
                    return this.id;
                }

                @NotNull
                public final String component2() {
                    return this.jsonrpc;
                }

                @NotNull
                public final SubscriptionId component3() {
                    return this.result;
                }

                @NotNull
                public final Acknowledgement copy(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "result") SubscriptionId subscriptionId) {
                    Intrinsics.checkNotNullParameter(str, "jsonrpc");
                    Intrinsics.checkNotNullParameter(subscriptionId, "result");
                    return new Acknowledgement(j2, str, subscriptionId);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof Acknowledgement)) {
                        return false;
                    }
                    Acknowledgement acknowledgement = (Acknowledgement) obj;
                    return this.id == acknowledgement.id && Intrinsics.areEqual((Object) this.jsonrpc, (Object) acknowledgement.jsonrpc) && Intrinsics.areEqual((Object) this.result, (Object) acknowledgement.result);
                }

                public long getId() {
                    return this.id;
                }

                @NotNull
                public String getJsonrpc() {
                    return this.jsonrpc;
                }

                @NotNull
                public final SubscriptionId getResult() {
                    return this.result;
                }

                public int hashCode() {
                    return this.result.hashCode() + a.i(this.jsonrpc, Long.hashCode(this.id) * 31, 31);
                }

                @NotNull
                public String toString() {
                    long j2 = this.id;
                    String str = this.jsonrpc;
                    SubscriptionId subscriptionId = this.result;
                    StringBuilder v2 = androidx.work.impl.a.v(j2, "Acknowledgement(id=", ", jsonrpc=", str);
                    v2.append(", result=");
                    v2.append(subscriptionId);
                    v2.append(")");
                    return v2.toString();
                }

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Acknowledgement(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "result") SubscriptionId subscriptionId) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, "jsonrpc");
                    Intrinsics.checkNotNullParameter(subscriptionId, "result");
                    this.id = j2;
                    this.jsonrpc = str;
                    this.result = subscriptionId;
                }
            }

            @JsonClass(generateAdapter = true)
            @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B%\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO$Subscribe$Result$JsonRpcError;", "Lcom/reown/foundation/network/model/RelayDTO$Subscribe$Result;", "jsonrpc", "", "error", "Lcom/reown/foundation/network/model/RelayDTO$Error;", "id", "", "<init>", "(Ljava/lang/String;Lcom/reown/foundation/network/model/RelayDTO$Error;J)V", "getJsonrpc", "()Ljava/lang/String;", "getError", "()Lcom/reown/foundation/network/model/RelayDTO$Error;", "getId", "()J", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class JsonRpcError extends Result {
                @NotNull
                private final Error error;
                private final long id;
                @NotNull
                private final String jsonrpc;

                /* JADX INFO: this call moved to the top of the method (can break code semantics) */
                public /* synthetic */ JsonRpcError(String str, Error error2, long j2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                    this((i3 & 1) != 0 ? "2.0" : str, error2, j2);
                }

                public static /* synthetic */ JsonRpcError copy$default(JsonRpcError jsonRpcError, String str, Error error2, long j2, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        str = jsonRpcError.jsonrpc;
                    }
                    if ((i3 & 2) != 0) {
                        error2 = jsonRpcError.error;
                    }
                    if ((i3 & 4) != 0) {
                        j2 = jsonRpcError.id;
                    }
                    return jsonRpcError.copy(str, error2, j2);
                }

                @NotNull
                public final String component1() {
                    return this.jsonrpc;
                }

                @NotNull
                public final Error component2() {
                    return this.error;
                }

                public final long component3() {
                    return this.id;
                }

                @NotNull
                public final JsonRpcError copy(@NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "error") Error error2, @Json(name = "id") long j2) {
                    Intrinsics.checkNotNullParameter(str, "jsonrpc");
                    Intrinsics.checkNotNullParameter(error2, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
                    return new JsonRpcError(str, error2, j2);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof JsonRpcError)) {
                        return false;
                    }
                    JsonRpcError jsonRpcError = (JsonRpcError) obj;
                    return Intrinsics.areEqual((Object) this.jsonrpc, (Object) jsonRpcError.jsonrpc) && Intrinsics.areEqual((Object) this.error, (Object) jsonRpcError.error) && this.id == jsonRpcError.id;
                }

                @NotNull
                public final Error getError() {
                    return this.error;
                }

                public long getId() {
                    return this.id;
                }

                @NotNull
                public String getJsonrpc() {
                    return this.jsonrpc;
                }

                public int hashCode() {
                    int hashCode = this.error.hashCode();
                    return Long.hashCode(this.id) + ((hashCode + (this.jsonrpc.hashCode() * 31)) * 31);
                }

                @NotNull
                public String toString() {
                    String str = this.jsonrpc;
                    Error error2 = this.error;
                    long j2 = this.id;
                    StringBuilder sb = new StringBuilder("JsonRpcError(jsonrpc=");
                    sb.append(str);
                    sb.append(", error=");
                    sb.append(error2);
                    sb.append(", id=");
                    return android.support.v4.media.session.a.k(j2, ")", sb);
                }

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public JsonRpcError(@NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "error") Error error2, @Json(name = "id") long j2) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, "jsonrpc");
                    Intrinsics.checkNotNullParameter(error2, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
                    this.jsonrpc = str;
                    this.error = error2;
                    this.id = j2;
                }
            }

            private Result() {
                super((DefaultConstructorMarker) null);
            }
        }

        public /* synthetic */ Subscribe(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Subscribe() {
            super((DefaultConstructorMarker) null);
        }

        @JsonClass(generateAdapter = true)
        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001:\u0001\u001eB/\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0003\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001f"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO$Subscribe$Request;", "Lcom/reown/foundation/network/model/RelayDTO$Subscribe;", "id", "", "jsonrpc", "", "method", "params", "Lcom/reown/foundation/network/model/RelayDTO$Subscribe$Request$Params;", "<init>", "(JLjava/lang/String;Ljava/lang/String;Lcom/reown/foundation/network/model/RelayDTO$Subscribe$Request$Params;)V", "getId", "()J", "getJsonrpc", "()Ljava/lang/String;", "getMethod", "getParams", "()Lcom/reown/foundation/network/model/RelayDTO$Subscribe$Request$Params;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Params", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Request extends Subscribe {
            private final long id;
            @NotNull
            private final String jsonrpc;
            @NotNull
            private final String method;
            @NotNull
            private final Params params;

            @JsonClass(generateAdapter = true)
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO$Subscribe$Request$Params;", "", "topic", "Lcom/reown/foundation/common/model/Topic;", "<init>", "(Lcom/reown/foundation/common/model/Topic;)V", "getTopic", "()Lcom/reown/foundation/common/model/Topic;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Params {
                @TopicAdapter.Qualifier
                @NotNull
                private final Topic topic;

                public Params(@NotNull @Json(name = "topic") Topic topic2) {
                    Intrinsics.checkNotNullParameter(topic2, PushMessagingService.KEY_TOPIC);
                    this.topic = topic2;
                }

                public static /* synthetic */ Params copy$default(Params params, Topic topic2, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        topic2 = params.topic;
                    }
                    return params.copy(topic2);
                }

                @NotNull
                public final Topic component1() {
                    return this.topic;
                }

                @NotNull
                public final Params copy(@NotNull @Json(name = "topic") Topic topic2) {
                    Intrinsics.checkNotNullParameter(topic2, PushMessagingService.KEY_TOPIC);
                    return new Params(topic2);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return (obj instanceof Params) && Intrinsics.areEqual((Object) this.topic, (Object) ((Params) obj).topic);
                }

                @NotNull
                public final Topic getTopic() {
                    return this.topic;
                }

                public int hashCode() {
                    return this.topic.hashCode();
                }

                @NotNull
                public String toString() {
                    Topic topic2 = this.topic;
                    return "Params(topic=" + topic2 + ")";
                }
            }

            /* JADX INFO: this call moved to the top of the method (can break code semantics) */
            public /* synthetic */ Request(long j2, String str, String str2, Params params2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                this((i3 & 1) != 0 ? UtilFunctionsKt.generateClientToServerId() : j2, (i3 & 2) != 0 ? "2.0" : str, (i3 & 4) != 0 ? JsonRpcRelayKt.IRN_SUBSCRIBE : str2, params2);
            }

            public static /* synthetic */ Request copy$default(Request request, long j2, String str, String str2, Params params2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    j2 = request.id;
                }
                long j3 = j2;
                if ((i3 & 2) != 0) {
                    str = request.jsonrpc;
                }
                String str3 = str;
                if ((i3 & 4) != 0) {
                    str2 = request.method;
                }
                String str4 = str2;
                if ((i3 & 8) != 0) {
                    params2 = request.params;
                }
                return request.copy(j3, str3, str4, params2);
            }

            public final long component1() {
                return this.id;
            }

            @NotNull
            public final String component2() {
                return this.jsonrpc;
            }

            @NotNull
            public final String component3() {
                return this.method;
            }

            @NotNull
            public final Params component4() {
                return this.params;
            }

            @NotNull
            public final Request copy(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "method") String str2, @NotNull @Json(name = "params") Params params2) {
                Intrinsics.checkNotNullParameter(str, "jsonrpc");
                Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
                Intrinsics.checkNotNullParameter(params2, "params");
                return new Request(j2, str, str2, params2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Request)) {
                    return false;
                }
                Request request = (Request) obj;
                return this.id == request.id && Intrinsics.areEqual((Object) this.jsonrpc, (Object) request.jsonrpc) && Intrinsics.areEqual((Object) this.method, (Object) request.method) && Intrinsics.areEqual((Object) this.params, (Object) request.params);
            }

            public long getId() {
                return this.id;
            }

            @NotNull
            public String getJsonrpc() {
                return this.jsonrpc;
            }

            @NotNull
            public final String getMethod() {
                return this.method;
            }

            @NotNull
            public final Params getParams() {
                return this.params;
            }

            public int hashCode() {
                return this.params.hashCode() + a.i(this.method, a.i(this.jsonrpc, Long.hashCode(this.id) * 31, 31), 31);
            }

            @NotNull
            public String toString() {
                long j2 = this.id;
                String str = this.jsonrpc;
                String str2 = this.method;
                Params params2 = this.params;
                StringBuilder v2 = androidx.work.impl.a.v(j2, "Request(id=", ", jsonrpc=", str);
                v2.append(", method=");
                v2.append(str2);
                v2.append(", params=");
                v2.append(params2);
                v2.append(")");
                return v2.toString();
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Request(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "method") String str2, @NotNull @Json(name = "params") Params params2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "jsonrpc");
                Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
                Intrinsics.checkNotNullParameter(params2, "params");
                this.id = j2;
                this.jsonrpc = str;
                this.method = str2;
                this.params = params2;
            }
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0004\u0006\u0007\b\t¨\u0006\n"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO$Subscription;", "Lcom/reown/foundation/network/model/RelayDTO;", "<init>", "()V", "Request", "Result", "Lcom/reown/foundation/network/model/RelayDTO$Subscription$Request;", "Lcom/reown/foundation/network/model/RelayDTO$Subscription$Result;", "Lcom/reown/foundation/network/model/RelayDTO$Subscription$Result$Acknowledgement;", "Lcom/reown/foundation/network/model/RelayDTO$Subscription$Result$JsonRpcError;", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class Subscription extends RelayDTO {

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0006"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO$Subscription$Result;", "Lcom/reown/foundation/network/model/RelayDTO$Subscription;", "<init>", "()V", "Acknowledgement", "JsonRpcError", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static abstract class Result extends Subscription {
            public /* synthetic */ Result(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @JsonClass(generateAdapter = true)
            @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B%\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00072\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO$Subscription$Result$Acknowledgement;", "Lcom/reown/foundation/network/model/RelayDTO$Subscription;", "id", "", "jsonrpc", "", "result", "", "<init>", "(JLjava/lang/String;Z)V", "getId", "()J", "getJsonrpc", "()Ljava/lang/String;", "getResult", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Acknowledgement extends Subscription {
                private final long id;
                @NotNull
                private final String jsonrpc;
                private final boolean result;

                /* JADX INFO: this call moved to the top of the method (can break code semantics) */
                public /* synthetic */ Acknowledgement(long j2, String str, boolean z2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                    this(j2, (i3 & 2) != 0 ? "2.0" : str, z2);
                }

                public static /* synthetic */ Acknowledgement copy$default(Acknowledgement acknowledgement, long j2, String str, boolean z2, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        j2 = acknowledgement.id;
                    }
                    if ((i3 & 2) != 0) {
                        str = acknowledgement.jsonrpc;
                    }
                    if ((i3 & 4) != 0) {
                        z2 = acknowledgement.result;
                    }
                    return acknowledgement.copy(j2, str, z2);
                }

                public final long component1() {
                    return this.id;
                }

                @NotNull
                public final String component2() {
                    return this.jsonrpc;
                }

                public final boolean component3() {
                    return this.result;
                }

                @NotNull
                public final Acknowledgement copy(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @Json(name = "result") boolean z2) {
                    Intrinsics.checkNotNullParameter(str, "jsonrpc");
                    return new Acknowledgement(j2, str, z2);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof Acknowledgement)) {
                        return false;
                    }
                    Acknowledgement acknowledgement = (Acknowledgement) obj;
                    return this.id == acknowledgement.id && Intrinsics.areEqual((Object) this.jsonrpc, (Object) acknowledgement.jsonrpc) && this.result == acknowledgement.result;
                }

                public long getId() {
                    return this.id;
                }

                @NotNull
                public String getJsonrpc() {
                    return this.jsonrpc;
                }

                public final boolean getResult() {
                    return this.result;
                }

                public int hashCode() {
                    return Boolean.hashCode(this.result) + a.i(this.jsonrpc, Long.hashCode(this.id) * 31, 31);
                }

                @NotNull
                public String toString() {
                    long j2 = this.id;
                    String str = this.jsonrpc;
                    boolean z2 = this.result;
                    StringBuilder v2 = androidx.work.impl.a.v(j2, "Acknowledgement(id=", ", jsonrpc=", str);
                    v2.append(", result=");
                    v2.append(z2);
                    v2.append(")");
                    return v2.toString();
                }

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Acknowledgement(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @Json(name = "result") boolean z2) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, "jsonrpc");
                    this.id = j2;
                    this.jsonrpc = str;
                    this.result = z2;
                }
            }

            @JsonClass(generateAdapter = true)
            @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B%\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO$Subscription$Result$JsonRpcError;", "Lcom/reown/foundation/network/model/RelayDTO$Subscription;", "jsonrpc", "", "error", "Lcom/reown/foundation/network/model/RelayDTO$Error;", "id", "", "<init>", "(Ljava/lang/String;Lcom/reown/foundation/network/model/RelayDTO$Error;J)V", "getJsonrpc", "()Ljava/lang/String;", "getError", "()Lcom/reown/foundation/network/model/RelayDTO$Error;", "getId", "()J", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class JsonRpcError extends Subscription {
                @NotNull
                private final Error error;
                private final long id;
                @NotNull
                private final String jsonrpc;

                /* JADX INFO: this call moved to the top of the method (can break code semantics) */
                public /* synthetic */ JsonRpcError(String str, Error error2, long j2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                    this((i3 & 1) != 0 ? "2.0" : str, error2, j2);
                }

                public static /* synthetic */ JsonRpcError copy$default(JsonRpcError jsonRpcError, String str, Error error2, long j2, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        str = jsonRpcError.jsonrpc;
                    }
                    if ((i3 & 2) != 0) {
                        error2 = jsonRpcError.error;
                    }
                    if ((i3 & 4) != 0) {
                        j2 = jsonRpcError.id;
                    }
                    return jsonRpcError.copy(str, error2, j2);
                }

                @NotNull
                public final String component1() {
                    return this.jsonrpc;
                }

                @NotNull
                public final Error component2() {
                    return this.error;
                }

                public final long component3() {
                    return this.id;
                }

                @NotNull
                public final JsonRpcError copy(@NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "error") Error error2, @Json(name = "id") long j2) {
                    Intrinsics.checkNotNullParameter(str, "jsonrpc");
                    Intrinsics.checkNotNullParameter(error2, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
                    return new JsonRpcError(str, error2, j2);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof JsonRpcError)) {
                        return false;
                    }
                    JsonRpcError jsonRpcError = (JsonRpcError) obj;
                    return Intrinsics.areEqual((Object) this.jsonrpc, (Object) jsonRpcError.jsonrpc) && Intrinsics.areEqual((Object) this.error, (Object) jsonRpcError.error) && this.id == jsonRpcError.id;
                }

                @NotNull
                public final Error getError() {
                    return this.error;
                }

                public long getId() {
                    return this.id;
                }

                @NotNull
                public String getJsonrpc() {
                    return this.jsonrpc;
                }

                public int hashCode() {
                    int hashCode = this.error.hashCode();
                    return Long.hashCode(this.id) + ((hashCode + (this.jsonrpc.hashCode() * 31)) * 31);
                }

                @NotNull
                public String toString() {
                    String str = this.jsonrpc;
                    Error error2 = this.error;
                    long j2 = this.id;
                    StringBuilder sb = new StringBuilder("JsonRpcError(jsonrpc=");
                    sb.append(str);
                    sb.append(", error=");
                    sb.append(error2);
                    sb.append(", id=");
                    return android.support.v4.media.session.a.k(j2, ")", sb);
                }

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public JsonRpcError(@NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "error") Error error2, @Json(name = "id") long j2) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, "jsonrpc");
                    Intrinsics.checkNotNullParameter(error2, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
                    this.jsonrpc = str;
                    this.error = error2;
                    this.id = j2;
                }
            }

            private Result() {
                super((DefaultConstructorMarker) null);
            }
        }

        public /* synthetic */ Subscription(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Subscription() {
            super((DefaultConstructorMarker) null);
        }

        @JsonClass(generateAdapter = true)
        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001:\u0001\u001eB/\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0003\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001f"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO$Subscription$Request;", "Lcom/reown/foundation/network/model/RelayDTO$Subscription;", "id", "", "jsonrpc", "", "method", "params", "Lcom/reown/foundation/network/model/RelayDTO$Subscription$Request$Params;", "<init>", "(JLjava/lang/String;Ljava/lang/String;Lcom/reown/foundation/network/model/RelayDTO$Subscription$Request$Params;)V", "getId", "()J", "getJsonrpc", "()Ljava/lang/String;", "getMethod", "getParams", "()Lcom/reown/foundation/network/model/RelayDTO$Subscription$Request$Params;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Params", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Request extends Subscription {
            private final long id;
            @NotNull
            private final String jsonrpc;
            @NotNull
            private final String method;
            @NotNull
            private final Params params;

            @JsonClass(generateAdapter = true)
            @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001:\u0001\u0016B\u001b\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO$Subscription$Request$Params;", "", "subscriptionId", "Lcom/reown/foundation/common/model/SubscriptionId;", "subscriptionData", "Lcom/reown/foundation/network/model/RelayDTO$Subscription$Request$Params$SubscriptionData;", "<init>", "(Lcom/reown/foundation/common/model/SubscriptionId;Lcom/reown/foundation/network/model/RelayDTO$Subscription$Request$Params$SubscriptionData;)V", "getSubscriptionId", "()Lcom/reown/foundation/common/model/SubscriptionId;", "getSubscriptionData", "()Lcom/reown/foundation/network/model/RelayDTO$Subscription$Request$Params$SubscriptionData;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "SubscriptionData", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Params {
                @NotNull
                private final SubscriptionData subscriptionData;
                @NotNull
                @SubscriptionIdAdapter.Qualifier
                private final SubscriptionId subscriptionId;

                @JsonClass(generateAdapter = true)
                @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B;\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0001\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\nHÆ\u0003J=\u0010\u001b\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00072\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00052\b\b\u0003\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020\nHÖ\u0001J\t\u0010 \u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006!"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO$Subscription$Request$Params$SubscriptionData;", "", "topic", "Lcom/reown/foundation/common/model/Topic;", "message", "", "publishedAt", "", "attestation", "tag", "", "<init>", "(Lcom/reown/foundation/common/model/Topic;Ljava/lang/String;JLjava/lang/String;I)V", "getTopic", "()Lcom/reown/foundation/common/model/Topic;", "getMessage", "()Ljava/lang/String;", "getPublishedAt", "()J", "getAttestation", "getTag", "()I", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
                public static final class SubscriptionData {
                    @Nullable
                    private final String attestation;
                    @NotNull
                    private final String message;
                    private final long publishedAt;
                    private final int tag;
                    @TopicAdapter.Qualifier
                    @NotNull
                    private final Topic topic;

                    public SubscriptionData(@NotNull @Json(name = "topic") Topic topic2, @NotNull @Json(name = "message") String str, @Json(name = "publishedAt") long j2, @Nullable @Json(name = "attestation") String str2, @Json(name = "tag") int i3) {
                        Intrinsics.checkNotNullParameter(topic2, PushMessagingService.KEY_TOPIC);
                        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                        this.topic = topic2;
                        this.message = str;
                        this.publishedAt = j2;
                        this.attestation = str2;
                        this.tag = i3;
                    }

                    public static /* synthetic */ SubscriptionData copy$default(SubscriptionData subscriptionData, Topic topic2, String str, long j2, String str2, int i3, int i4, Object obj) {
                        if ((i4 & 1) != 0) {
                            topic2 = subscriptionData.topic;
                        }
                        if ((i4 & 2) != 0) {
                            str = subscriptionData.message;
                        }
                        String str3 = str;
                        if ((i4 & 4) != 0) {
                            j2 = subscriptionData.publishedAt;
                        }
                        long j3 = j2;
                        if ((i4 & 8) != 0) {
                            str2 = subscriptionData.attestation;
                        }
                        String str4 = str2;
                        if ((i4 & 16) != 0) {
                            i3 = subscriptionData.tag;
                        }
                        return subscriptionData.copy(topic2, str3, j3, str4, i3);
                    }

                    @NotNull
                    public final Topic component1() {
                        return this.topic;
                    }

                    @NotNull
                    public final String component2() {
                        return this.message;
                    }

                    public final long component3() {
                        return this.publishedAt;
                    }

                    @Nullable
                    public final String component4() {
                        return this.attestation;
                    }

                    public final int component5() {
                        return this.tag;
                    }

                    @NotNull
                    public final SubscriptionData copy(@NotNull @Json(name = "topic") Topic topic2, @NotNull @Json(name = "message") String str, @Json(name = "publishedAt") long j2, @Nullable @Json(name = "attestation") String str2, @Json(name = "tag") int i3) {
                        Intrinsics.checkNotNullParameter(topic2, PushMessagingService.KEY_TOPIC);
                        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                        return new SubscriptionData(topic2, str, j2, str2, i3);
                    }

                    public boolean equals(@Nullable Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (!(obj instanceof SubscriptionData)) {
                            return false;
                        }
                        SubscriptionData subscriptionData = (SubscriptionData) obj;
                        return Intrinsics.areEqual((Object) this.topic, (Object) subscriptionData.topic) && Intrinsics.areEqual((Object) this.message, (Object) subscriptionData.message) && this.publishedAt == subscriptionData.publishedAt && Intrinsics.areEqual((Object) this.attestation, (Object) subscriptionData.attestation) && this.tag == subscriptionData.tag;
                    }

                    @Nullable
                    public final String getAttestation() {
                        return this.attestation;
                    }

                    @NotNull
                    public final String getMessage() {
                        return this.message;
                    }

                    public final long getPublishedAt() {
                        return this.publishedAt;
                    }

                    public final int getTag() {
                        return this.tag;
                    }

                    @NotNull
                    public final Topic getTopic() {
                        return this.topic;
                    }

                    public int hashCode() {
                        int D2 = a.D(this.publishedAt, a.i(this.message, this.topic.hashCode() * 31, 31), 31);
                        String str = this.attestation;
                        return Integer.hashCode(this.tag) + ((D2 + (str == null ? 0 : str.hashCode())) * 31);
                    }

                    @NotNull
                    public String toString() {
                        Topic topic2 = this.topic;
                        String str = this.message;
                        long j2 = this.publishedAt;
                        String str2 = this.attestation;
                        int i3 = this.tag;
                        return "SubscriptionData(topic=" + topic2 + ", message=" + str + ", publishedAt=" + j2 + ", attestation=" + str2 + ", tag=" + i3 + ")";
                    }
                }

                public Params(@NotNull @Json(name = "id") SubscriptionId subscriptionId2, @NotNull @Json(name = "data") SubscriptionData subscriptionData2) {
                    Intrinsics.checkNotNullParameter(subscriptionId2, "subscriptionId");
                    Intrinsics.checkNotNullParameter(subscriptionData2, "subscriptionData");
                    this.subscriptionId = subscriptionId2;
                    this.subscriptionData = subscriptionData2;
                }

                public static /* synthetic */ Params copy$default(Params params, SubscriptionId subscriptionId2, SubscriptionData subscriptionData2, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        subscriptionId2 = params.subscriptionId;
                    }
                    if ((i3 & 2) != 0) {
                        subscriptionData2 = params.subscriptionData;
                    }
                    return params.copy(subscriptionId2, subscriptionData2);
                }

                @NotNull
                public final SubscriptionId component1() {
                    return this.subscriptionId;
                }

                @NotNull
                public final SubscriptionData component2() {
                    return this.subscriptionData;
                }

                @NotNull
                public final Params copy(@NotNull @Json(name = "id") SubscriptionId subscriptionId2, @NotNull @Json(name = "data") SubscriptionData subscriptionData2) {
                    Intrinsics.checkNotNullParameter(subscriptionId2, "subscriptionId");
                    Intrinsics.checkNotNullParameter(subscriptionData2, "subscriptionData");
                    return new Params(subscriptionId2, subscriptionData2);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof Params)) {
                        return false;
                    }
                    Params params = (Params) obj;
                    return Intrinsics.areEqual((Object) this.subscriptionId, (Object) params.subscriptionId) && Intrinsics.areEqual((Object) this.subscriptionData, (Object) params.subscriptionData);
                }

                @NotNull
                public final SubscriptionData getSubscriptionData() {
                    return this.subscriptionData;
                }

                @NotNull
                public final SubscriptionId getSubscriptionId() {
                    return this.subscriptionId;
                }

                public int hashCode() {
                    return this.subscriptionData.hashCode() + (this.subscriptionId.hashCode() * 31);
                }

                @NotNull
                public String toString() {
                    SubscriptionId subscriptionId2 = this.subscriptionId;
                    SubscriptionData subscriptionData2 = this.subscriptionData;
                    return "Params(subscriptionId=" + subscriptionId2 + ", subscriptionData=" + subscriptionData2 + ")";
                }
            }

            /* JADX INFO: this call moved to the top of the method (can break code semantics) */
            public /* synthetic */ Request(long j2, String str, String str2, Params params2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                this((i3 & 1) != 0 ? UtilFunctionsKt.generateClientToServerId() : j2, (i3 & 2) != 0 ? "2.0" : str, (i3 & 4) != 0 ? JsonRpcRelayKt.IRN_SUBSCRIPTION : str2, params2);
            }

            public static /* synthetic */ Request copy$default(Request request, long j2, String str, String str2, Params params2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    j2 = request.id;
                }
                long j3 = j2;
                if ((i3 & 2) != 0) {
                    str = request.jsonrpc;
                }
                String str3 = str;
                if ((i3 & 4) != 0) {
                    str2 = request.method;
                }
                String str4 = str2;
                if ((i3 & 8) != 0) {
                    params2 = request.params;
                }
                return request.copy(j3, str3, str4, params2);
            }

            public final long component1() {
                return this.id;
            }

            @NotNull
            public final String component2() {
                return this.jsonrpc;
            }

            @NotNull
            public final String component3() {
                return this.method;
            }

            @NotNull
            public final Params component4() {
                return this.params;
            }

            @NotNull
            public final Request copy(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "method") String str2, @NotNull @Json(name = "params") Params params2) {
                Intrinsics.checkNotNullParameter(str, "jsonrpc");
                Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
                Intrinsics.checkNotNullParameter(params2, "params");
                return new Request(j2, str, str2, params2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Request)) {
                    return false;
                }
                Request request = (Request) obj;
                return this.id == request.id && Intrinsics.areEqual((Object) this.jsonrpc, (Object) request.jsonrpc) && Intrinsics.areEqual((Object) this.method, (Object) request.method) && Intrinsics.areEqual((Object) this.params, (Object) request.params);
            }

            public long getId() {
                return this.id;
            }

            @NotNull
            public String getJsonrpc() {
                return this.jsonrpc;
            }

            @NotNull
            public final String getMethod() {
                return this.method;
            }

            @NotNull
            public final Params getParams() {
                return this.params;
            }

            public int hashCode() {
                return this.params.hashCode() + a.i(this.method, a.i(this.jsonrpc, Long.hashCode(this.id) * 31, 31), 31);
            }

            @NotNull
            public String toString() {
                long j2 = this.id;
                String str = this.jsonrpc;
                String str2 = this.method;
                Params params2 = this.params;
                StringBuilder v2 = androidx.work.impl.a.v(j2, "Request(id=", ", jsonrpc=", str);
                v2.append(", method=");
                v2.append(str2);
                v2.append(", params=");
                v2.append(params2);
                v2.append(")");
                return v2.toString();
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Request(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "method") String str2, @NotNull @Json(name = "params") Params params2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "jsonrpc");
                Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
                Intrinsics.checkNotNullParameter(params2, "params");
                this.id = j2;
                this.jsonrpc = str;
                this.method = str2;
                this.params = params2;
            }
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO$Unsubscribe;", "Lcom/reown/foundation/network/model/RelayDTO;", "<init>", "()V", "Request", "Result", "Lcom/reown/foundation/network/model/RelayDTO$Unsubscribe$Request;", "Lcom/reown/foundation/network/model/RelayDTO$Unsubscribe$Result;", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class Unsubscribe extends RelayDTO {

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO$Unsubscribe$Result;", "Lcom/reown/foundation/network/model/RelayDTO$Unsubscribe;", "<init>", "()V", "Acknowledgement", "JsonRpcError", "Lcom/reown/foundation/network/model/RelayDTO$Unsubscribe$Result$Acknowledgement;", "Lcom/reown/foundation/network/model/RelayDTO$Unsubscribe$Result$JsonRpcError;", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static abstract class Result extends Unsubscribe {
            public /* synthetic */ Result(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @JsonClass(generateAdapter = true)
            @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B%\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00072\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO$Unsubscribe$Result$Acknowledgement;", "Lcom/reown/foundation/network/model/RelayDTO$Unsubscribe$Result;", "id", "", "jsonrpc", "", "result", "", "<init>", "(JLjava/lang/String;Z)V", "getId", "()J", "getJsonrpc", "()Ljava/lang/String;", "getResult", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Acknowledgement extends Result {
                private final long id;
                @NotNull
                private final String jsonrpc;
                private final boolean result;

                /* JADX INFO: this call moved to the top of the method (can break code semantics) */
                public /* synthetic */ Acknowledgement(long j2, String str, boolean z2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                    this(j2, (i3 & 2) != 0 ? "2.0" : str, z2);
                }

                public static /* synthetic */ Acknowledgement copy$default(Acknowledgement acknowledgement, long j2, String str, boolean z2, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        j2 = acknowledgement.id;
                    }
                    if ((i3 & 2) != 0) {
                        str = acknowledgement.jsonrpc;
                    }
                    if ((i3 & 4) != 0) {
                        z2 = acknowledgement.result;
                    }
                    return acknowledgement.copy(j2, str, z2);
                }

                public final long component1() {
                    return this.id;
                }

                @NotNull
                public final String component2() {
                    return this.jsonrpc;
                }

                public final boolean component3() {
                    return this.result;
                }

                @NotNull
                public final Acknowledgement copy(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @Json(name = "result") boolean z2) {
                    Intrinsics.checkNotNullParameter(str, "jsonrpc");
                    return new Acknowledgement(j2, str, z2);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof Acknowledgement)) {
                        return false;
                    }
                    Acknowledgement acknowledgement = (Acknowledgement) obj;
                    return this.id == acknowledgement.id && Intrinsics.areEqual((Object) this.jsonrpc, (Object) acknowledgement.jsonrpc) && this.result == acknowledgement.result;
                }

                public long getId() {
                    return this.id;
                }

                @NotNull
                public String getJsonrpc() {
                    return this.jsonrpc;
                }

                public final boolean getResult() {
                    return this.result;
                }

                public int hashCode() {
                    return Boolean.hashCode(this.result) + a.i(this.jsonrpc, Long.hashCode(this.id) * 31, 31);
                }

                @NotNull
                public String toString() {
                    long j2 = this.id;
                    String str = this.jsonrpc;
                    boolean z2 = this.result;
                    StringBuilder v2 = androidx.work.impl.a.v(j2, "Acknowledgement(id=", ", jsonrpc=", str);
                    v2.append(", result=");
                    v2.append(z2);
                    v2.append(")");
                    return v2.toString();
                }

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Acknowledgement(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @Json(name = "result") boolean z2) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, "jsonrpc");
                    this.id = j2;
                    this.jsonrpc = str;
                    this.result = z2;
                }
            }

            @JsonClass(generateAdapter = true)
            @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B%\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO$Unsubscribe$Result$JsonRpcError;", "Lcom/reown/foundation/network/model/RelayDTO$Unsubscribe$Result;", "jsonrpc", "", "error", "Lcom/reown/foundation/network/model/RelayDTO$Error;", "id", "", "<init>", "(Ljava/lang/String;Lcom/reown/foundation/network/model/RelayDTO$Error;J)V", "getJsonrpc", "()Ljava/lang/String;", "getError", "()Lcom/reown/foundation/network/model/RelayDTO$Error;", "getId", "()J", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class JsonRpcError extends Result {
                @NotNull
                private final Error error;
                private final long id;
                @NotNull
                private final String jsonrpc;

                /* JADX INFO: this call moved to the top of the method (can break code semantics) */
                public /* synthetic */ JsonRpcError(String str, Error error2, long j2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                    this((i3 & 1) != 0 ? "2.0" : str, error2, j2);
                }

                public static /* synthetic */ JsonRpcError copy$default(JsonRpcError jsonRpcError, String str, Error error2, long j2, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        str = jsonRpcError.jsonrpc;
                    }
                    if ((i3 & 2) != 0) {
                        error2 = jsonRpcError.error;
                    }
                    if ((i3 & 4) != 0) {
                        j2 = jsonRpcError.id;
                    }
                    return jsonRpcError.copy(str, error2, j2);
                }

                @NotNull
                public final String component1() {
                    return this.jsonrpc;
                }

                @NotNull
                public final Error component2() {
                    return this.error;
                }

                public final long component3() {
                    return this.id;
                }

                @NotNull
                public final JsonRpcError copy(@NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "error") Error error2, @Json(name = "id") long j2) {
                    Intrinsics.checkNotNullParameter(str, "jsonrpc");
                    Intrinsics.checkNotNullParameter(error2, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
                    return new JsonRpcError(str, error2, j2);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof JsonRpcError)) {
                        return false;
                    }
                    JsonRpcError jsonRpcError = (JsonRpcError) obj;
                    return Intrinsics.areEqual((Object) this.jsonrpc, (Object) jsonRpcError.jsonrpc) && Intrinsics.areEqual((Object) this.error, (Object) jsonRpcError.error) && this.id == jsonRpcError.id;
                }

                @NotNull
                public final Error getError() {
                    return this.error;
                }

                public long getId() {
                    return this.id;
                }

                @NotNull
                public String getJsonrpc() {
                    return this.jsonrpc;
                }

                public int hashCode() {
                    int hashCode = this.error.hashCode();
                    return Long.hashCode(this.id) + ((hashCode + (this.jsonrpc.hashCode() * 31)) * 31);
                }

                @NotNull
                public String toString() {
                    String str = this.jsonrpc;
                    Error error2 = this.error;
                    long j2 = this.id;
                    StringBuilder sb = new StringBuilder("JsonRpcError(jsonrpc=");
                    sb.append(str);
                    sb.append(", error=");
                    sb.append(error2);
                    sb.append(", id=");
                    return android.support.v4.media.session.a.k(j2, ")", sb);
                }

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public JsonRpcError(@NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "error") Error error2, @Json(name = "id") long j2) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, "jsonrpc");
                    Intrinsics.checkNotNullParameter(error2, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
                    this.jsonrpc = str;
                    this.error = error2;
                    this.id = j2;
                }
            }

            private Result() {
                super((DefaultConstructorMarker) null);
            }
        }

        public /* synthetic */ Unsubscribe(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Unsubscribe() {
            super((DefaultConstructorMarker) null);
        }

        @JsonClass(generateAdapter = true)
        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001:\u0001\u001eB/\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0003\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001f"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO$Unsubscribe$Request;", "Lcom/reown/foundation/network/model/RelayDTO$Unsubscribe;", "id", "", "jsonrpc", "", "method", "params", "Lcom/reown/foundation/network/model/RelayDTO$Unsubscribe$Request$Params;", "<init>", "(JLjava/lang/String;Ljava/lang/String;Lcom/reown/foundation/network/model/RelayDTO$Unsubscribe$Request$Params;)V", "getId", "()J", "getJsonrpc", "()Ljava/lang/String;", "getMethod", "getParams", "()Lcom/reown/foundation/network/model/RelayDTO$Unsubscribe$Request$Params;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Params", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Request extends Unsubscribe {
            private final long id;
            @NotNull
            private final String jsonrpc;
            @NotNull
            private final String method;
            @NotNull
            private final Params params;

            @JsonClass(generateAdapter = true)
            @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/reown/foundation/network/model/RelayDTO$Unsubscribe$Request$Params;", "", "topic", "Lcom/reown/foundation/common/model/Topic;", "subscriptionId", "Lcom/reown/foundation/common/model/SubscriptionId;", "<init>", "(Lcom/reown/foundation/common/model/Topic;Lcom/reown/foundation/common/model/SubscriptionId;)V", "getTopic", "()Lcom/reown/foundation/common/model/Topic;", "getSubscriptionId", "()Lcom/reown/foundation/common/model/SubscriptionId;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Params {
                @NotNull
                @SubscriptionIdAdapter.Qualifier
                private final SubscriptionId subscriptionId;
                @TopicAdapter.Qualifier
                @NotNull
                private final Topic topic;

                public Params(@NotNull @Json(name = "topic") Topic topic2, @NotNull @Json(name = "id") SubscriptionId subscriptionId2) {
                    Intrinsics.checkNotNullParameter(topic2, PushMessagingService.KEY_TOPIC);
                    Intrinsics.checkNotNullParameter(subscriptionId2, "subscriptionId");
                    this.topic = topic2;
                    this.subscriptionId = subscriptionId2;
                }

                public static /* synthetic */ Params copy$default(Params params, Topic topic2, SubscriptionId subscriptionId2, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        topic2 = params.topic;
                    }
                    if ((i3 & 2) != 0) {
                        subscriptionId2 = params.subscriptionId;
                    }
                    return params.copy(topic2, subscriptionId2);
                }

                @NotNull
                public final Topic component1() {
                    return this.topic;
                }

                @NotNull
                public final SubscriptionId component2() {
                    return this.subscriptionId;
                }

                @NotNull
                public final Params copy(@NotNull @Json(name = "topic") Topic topic2, @NotNull @Json(name = "id") SubscriptionId subscriptionId2) {
                    Intrinsics.checkNotNullParameter(topic2, PushMessagingService.KEY_TOPIC);
                    Intrinsics.checkNotNullParameter(subscriptionId2, "subscriptionId");
                    return new Params(topic2, subscriptionId2);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof Params)) {
                        return false;
                    }
                    Params params = (Params) obj;
                    return Intrinsics.areEqual((Object) this.topic, (Object) params.topic) && Intrinsics.areEqual((Object) this.subscriptionId, (Object) params.subscriptionId);
                }

                @NotNull
                public final SubscriptionId getSubscriptionId() {
                    return this.subscriptionId;
                }

                @NotNull
                public final Topic getTopic() {
                    return this.topic;
                }

                public int hashCode() {
                    return this.subscriptionId.hashCode() + (this.topic.hashCode() * 31);
                }

                @NotNull
                public String toString() {
                    Topic topic2 = this.topic;
                    SubscriptionId subscriptionId2 = this.subscriptionId;
                    return "Params(topic=" + topic2 + ", subscriptionId=" + subscriptionId2 + ")";
                }
            }

            /* JADX INFO: this call moved to the top of the method (can break code semantics) */
            public /* synthetic */ Request(long j2, String str, String str2, Params params2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                this((i3 & 1) != 0 ? UtilFunctionsKt.generateClientToServerId() : j2, (i3 & 2) != 0 ? "2.0" : str, (i3 & 4) != 0 ? JsonRpcRelayKt.IRN_UNSUBSCRIBE : str2, params2);
            }

            public static /* synthetic */ Request copy$default(Request request, long j2, String str, String str2, Params params2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    j2 = request.id;
                }
                long j3 = j2;
                if ((i3 & 2) != 0) {
                    str = request.jsonrpc;
                }
                String str3 = str;
                if ((i3 & 4) != 0) {
                    str2 = request.method;
                }
                String str4 = str2;
                if ((i3 & 8) != 0) {
                    params2 = request.params;
                }
                return request.copy(j3, str3, str4, params2);
            }

            public final long component1() {
                return this.id;
            }

            @NotNull
            public final String component2() {
                return this.jsonrpc;
            }

            @NotNull
            public final String component3() {
                return this.method;
            }

            @NotNull
            public final Params component4() {
                return this.params;
            }

            @NotNull
            public final Request copy(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "method") String str2, @NotNull @Json(name = "params") Params params2) {
                Intrinsics.checkNotNullParameter(str, "jsonrpc");
                Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
                Intrinsics.checkNotNullParameter(params2, "params");
                return new Request(j2, str, str2, params2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Request)) {
                    return false;
                }
                Request request = (Request) obj;
                return this.id == request.id && Intrinsics.areEqual((Object) this.jsonrpc, (Object) request.jsonrpc) && Intrinsics.areEqual((Object) this.method, (Object) request.method) && Intrinsics.areEqual((Object) this.params, (Object) request.params);
            }

            public long getId() {
                return this.id;
            }

            @NotNull
            public String getJsonrpc() {
                return this.jsonrpc;
            }

            @NotNull
            public final String getMethod() {
                return this.method;
            }

            @NotNull
            public final Params getParams() {
                return this.params;
            }

            public int hashCode() {
                return this.params.hashCode() + a.i(this.method, a.i(this.jsonrpc, Long.hashCode(this.id) * 31, 31), 31);
            }

            @NotNull
            public String toString() {
                long j2 = this.id;
                String str = this.jsonrpc;
                String str2 = this.method;
                Params params2 = this.params;
                StringBuilder v2 = androidx.work.impl.a.v(j2, "Request(id=", ", jsonrpc=", str);
                v2.append(", method=");
                v2.append(str2);
                v2.append(", params=");
                v2.append(params2);
                v2.append(")");
                return v2.toString();
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Request(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "method") String str2, @NotNull @Json(name = "params") Params params2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "jsonrpc");
                Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
                Intrinsics.checkNotNullParameter(params2, "params");
                this.id = j2;
                this.jsonrpc = str;
                this.method = str2;
                this.params = params2;
            }
        }
    }

    public /* synthetic */ RelayDTO(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract long getId();

    @NotNull
    public abstract String getJsonrpc();

    private RelayDTO() {
    }
}
