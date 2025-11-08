package com.reown.foundation.network.model;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.state.b;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import com.reown.android.push.notifications.PushMessagingService;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0004B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/reown/foundation/network/model/Relay;", "", "<init>", "()V", "Model", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Relay {
    @NotNull
    public static final Relay INSTANCE = new Relay();

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0006\u0004\u0005\u0006\u0007\b\tB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0006\n\u000b\f\r\u000e\u000f¨\u0006\u0010"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model;", "", "<init>", "()V", "Call", "Error", "Event", "Message", "ShutdownReason", "IrnParams", "Lcom/reown/foundation/network/model/Relay$Model$Call;", "Lcom/reown/foundation/network/model/Relay$Model$Error;", "Lcom/reown/foundation/network/model/Relay$Model$Event;", "Lcom/reown/foundation/network/model/Relay$Model$IrnParams;", "Lcom/reown/foundation/network/model/Relay$Model$Message;", "Lcom/reown/foundation/network/model/Relay$Model$ShutdownReason;", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class Model {

        @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0007\f\r\u000e\u000f\u0010\u0011\u0012B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b\u0001\u0007\u0013\u0014\u0015\u0016\u0017\u0018\u0019¨\u0006\u001a"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Call;", "Lcom/reown/foundation/network/model/Relay$Model;", "<init>", "()V", "id", "", "getId", "()J", "jsonrpc", "", "getJsonrpc", "()Ljava/lang/String;", "ProposeSession", "ApproveSession", "Publish", "Subscribe", "BatchSubscribe", "Subscription", "Unsubscribe", "Lcom/reown/foundation/network/model/Relay$Model$Call$ApproveSession;", "Lcom/reown/foundation/network/model/Relay$Model$Call$BatchSubscribe;", "Lcom/reown/foundation/network/model/Relay$Model$Call$ProposeSession;", "Lcom/reown/foundation/network/model/Relay$Model$Call$Publish;", "Lcom/reown/foundation/network/model/Relay$Model$Call$Subscribe;", "Lcom/reown/foundation/network/model/Relay$Model$Call$Subscription;", "Lcom/reown/foundation/network/model/Relay$Model$Call$Unsubscribe;", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static abstract class Call extends Model {

            @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0003\u0007\b\t¨\u0006\n"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Call$ApproveSession;", "Lcom/reown/foundation/network/model/Relay$Model$Call;", "<init>", "()V", "Request", "Acknowledgement", "JsonRpcError", "Lcom/reown/foundation/network/model/Relay$Model$Call$ApproveSession$Acknowledgement;", "Lcom/reown/foundation/network/model/Relay$Model$Call$ApproveSession$JsonRpcError;", "Lcom/reown/foundation/network/model/Relay$Model$Call$ApproveSession$Request;", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static abstract class ApproveSession extends Call {
                public /* synthetic */ ApproveSession(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00072\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Call$ApproveSession$Acknowledgement;", "Lcom/reown/foundation/network/model/Relay$Model$Call$ApproveSession;", "id", "", "jsonrpc", "", "result", "", "<init>", "(JLjava/lang/String;Z)V", "getId", "()J", "getJsonrpc", "()Ljava/lang/String;", "getResult", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
                public static final class Acknowledgement extends ApproveSession {
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
                    public final Acknowledgement copy(long j2, @NotNull String str, boolean z2) {
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
                    public Acknowledgement(long j2, @NotNull String str, boolean z2) {
                        super((DefaultConstructorMarker) null);
                        Intrinsics.checkNotNullParameter(str, "jsonrpc");
                        this.id = j2;
                        this.jsonrpc = str;
                        this.result = z2;
                    }
                }

                @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B!\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Call$ApproveSession$JsonRpcError;", "Lcom/reown/foundation/network/model/Relay$Model$Call$ApproveSession;", "jsonrpc", "", "error", "Lcom/reown/foundation/network/model/Relay$Model$Error;", "id", "", "<init>", "(Ljava/lang/String;Lcom/reown/foundation/network/model/Relay$Model$Error;J)V", "getJsonrpc", "()Ljava/lang/String;", "getError", "()Lcom/reown/foundation/network/model/Relay$Model$Error;", "getId", "()J", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
                public static final class JsonRpcError extends ApproveSession {
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
                    public final JsonRpcError copy(@NotNull String str, @NotNull Error error2, long j2) {
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
                    public JsonRpcError(@NotNull String str, @NotNull Error error2, long j2) {
                        super((DefaultConstructorMarker) null);
                        Intrinsics.checkNotNullParameter(str, "jsonrpc");
                        Intrinsics.checkNotNullParameter(error2, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
                        this.jsonrpc = str;
                        this.error = error2;
                        this.id = j2;
                    }
                }

                private ApproveSession() {
                    super((DefaultConstructorMarker) null);
                }

                @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001:\u0001\u001eB+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001f"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Call$ApproveSession$Request;", "Lcom/reown/foundation/network/model/Relay$Model$Call$ApproveSession;", "id", "", "jsonrpc", "", "method", "params", "Lcom/reown/foundation/network/model/Relay$Model$Call$ApproveSession$Request$Params;", "<init>", "(JLjava/lang/String;Ljava/lang/String;Lcom/reown/foundation/network/model/Relay$Model$Call$ApproveSession$Request$Params;)V", "getId", "()J", "getJsonrpc", "()Ljava/lang/String;", "getMethod", "getParams", "()Lcom/reown/foundation/network/model/Relay$Model$Call$ApproveSession$Request$Params;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Params", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
                public static final class Request extends ApproveSession {
                    private final long id;
                    @NotNull
                    private final String jsonrpc;
                    @NotNull
                    private final String method;
                    @NotNull
                    private final Params params;

                    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\bHÆ\u0003J;\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001e"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Call$ApproveSession$Request$Params;", "", "pairingTopic", "", "sessionTopic", "sessionProposalResponse", "sessionSettlementRequest", "correlationId", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)V", "getPairingTopic", "()Ljava/lang/String;", "getSessionTopic", "getSessionProposalResponse", "getSessionSettlementRequest", "getCorrelationId", "()J", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
                    public static final class Params {
                        private final long correlationId;
                        @NotNull
                        private final String pairingTopic;
                        @NotNull
                        private final String sessionProposalResponse;
                        @NotNull
                        private final String sessionSettlementRequest;
                        @NotNull
                        private final String sessionTopic;

                        public Params(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, long j2) {
                            Intrinsics.checkNotNullParameter(str, "pairingTopic");
                            Intrinsics.checkNotNullParameter(str2, "sessionTopic");
                            Intrinsics.checkNotNullParameter(str3, "sessionProposalResponse");
                            Intrinsics.checkNotNullParameter(str4, "sessionSettlementRequest");
                            this.pairingTopic = str;
                            this.sessionTopic = str2;
                            this.sessionProposalResponse = str3;
                            this.sessionSettlementRequest = str4;
                            this.correlationId = j2;
                        }

                        public static /* synthetic */ Params copy$default(Params params, String str, String str2, String str3, String str4, long j2, int i3, Object obj) {
                            if ((i3 & 1) != 0) {
                                str = params.pairingTopic;
                            }
                            if ((i3 & 2) != 0) {
                                str2 = params.sessionTopic;
                            }
                            String str5 = str2;
                            if ((i3 & 4) != 0) {
                                str3 = params.sessionProposalResponse;
                            }
                            String str6 = str3;
                            if ((i3 & 8) != 0) {
                                str4 = params.sessionSettlementRequest;
                            }
                            String str7 = str4;
                            if ((i3 & 16) != 0) {
                                j2 = params.correlationId;
                            }
                            return params.copy(str, str5, str6, str7, j2);
                        }

                        @NotNull
                        public final String component1() {
                            return this.pairingTopic;
                        }

                        @NotNull
                        public final String component2() {
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
                        public final Params copy(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, long j2) {
                            Intrinsics.checkNotNullParameter(str, "pairingTopic");
                            Intrinsics.checkNotNullParameter(str2, "sessionTopic");
                            Intrinsics.checkNotNullParameter(str3, "sessionProposalResponse");
                            Intrinsics.checkNotNullParameter(str4, "sessionSettlementRequest");
                            return new Params(str, str2, str3, str4, j2);
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
                        public final String getPairingTopic() {
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
                        public final String getSessionTopic() {
                            return this.sessionTopic;
                        }

                        public int hashCode() {
                            return Long.hashCode(this.correlationId) + a.i(this.sessionSettlementRequest, a.i(this.sessionProposalResponse, a.i(this.sessionTopic, this.pairingTopic.hashCode() * 31, 31), 31), 31);
                        }

                        @NotNull
                        public String toString() {
                            String str = this.pairingTopic;
                            String str2 = this.sessionTopic;
                            String str3 = this.sessionProposalResponse;
                            String str4 = this.sessionSettlementRequest;
                            long j2 = this.correlationId;
                            StringBuilder l2 = C0118y.l("Params(pairingTopic=", str, ", sessionTopic=", str2, ", sessionProposalResponse=");
                            b.w(l2, str3, ", sessionSettlementRequest=", str4, ", correlationId=");
                            return android.support.v4.media.session.a.k(j2, ")", l2);
                        }
                    }

                    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
                    public /* synthetic */ Request(long j2, String str, String str2, Params params2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                        this(j2, (i3 & 2) != 0 ? "2.0" : str, (i3 & 4) != 0 ? JsonRpcRelayKt.WC_APPROVE_SESSION : str2, params2);
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
                    public final Request copy(long j2, @NotNull String str, @NotNull String str2, @NotNull Params params2) {
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
                    public Request(long j2, @NotNull String str, @NotNull String str2, @NotNull Params params2) {
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

            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b6\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0007"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Call$BatchSubscribe;", "Lcom/reown/foundation/network/model/Relay$Model$Call;", "<init>", "()V", "Request", "Acknowledgement", "JsonRpcError", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static abstract class BatchSubscribe extends Call {
                public /* synthetic */ BatchSubscribe(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007HÆ\u0003J-\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Call$BatchSubscribe$Acknowledgement;", "Lcom/reown/foundation/network/model/Relay$Model$Call$Subscribe;", "id", "", "jsonrpc", "", "result", "", "<init>", "(JLjava/lang/String;Ljava/util/List;)V", "getId", "()J", "getJsonrpc", "()Ljava/lang/String;", "getResult", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
                public static final class Acknowledgement extends Subscribe {
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
                    public final Acknowledgement copy(long j2, @NotNull String str, @NotNull List<String> list) {
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
                    public Acknowledgement(long j2, @NotNull String str, @NotNull List<String> list) {
                        super((DefaultConstructorMarker) null);
                        Intrinsics.checkNotNullParameter(str, "jsonrpc");
                        Intrinsics.checkNotNullParameter(list, "result");
                        this.id = j2;
                        this.jsonrpc = str;
                        this.result = list;
                    }
                }

                @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B!\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Call$BatchSubscribe$JsonRpcError;", "Lcom/reown/foundation/network/model/Relay$Model$Call$Subscribe;", "jsonrpc", "", "error", "Lcom/reown/foundation/network/model/Relay$Model$Error;", "id", "", "<init>", "(Ljava/lang/String;Lcom/reown/foundation/network/model/Relay$Model$Error;J)V", "getJsonrpc", "()Ljava/lang/String;", "getError", "()Lcom/reown/foundation/network/model/Relay$Model$Error;", "getId", "()J", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
                public static final class JsonRpcError extends Subscribe {
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
                    public final JsonRpcError copy(@NotNull String str, @NotNull Error error2, long j2) {
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
                    public JsonRpcError(@NotNull String str, @NotNull Error error2, long j2) {
                        super((DefaultConstructorMarker) null);
                        Intrinsics.checkNotNullParameter(str, "jsonrpc");
                        Intrinsics.checkNotNullParameter(error2, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
                        this.jsonrpc = str;
                        this.error = error2;
                        this.id = j2;
                    }
                }

                private BatchSubscribe() {
                    super((DefaultConstructorMarker) null);
                }

                @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001:\u0001\u001eB+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001f"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Call$BatchSubscribe$Request;", "Lcom/reown/foundation/network/model/Relay$Model$Call$Subscribe;", "id", "", "jsonrpc", "", "method", "params", "Lcom/reown/foundation/network/model/Relay$Model$Call$BatchSubscribe$Request$Params;", "<init>", "(JLjava/lang/String;Ljava/lang/String;Lcom/reown/foundation/network/model/Relay$Model$Call$BatchSubscribe$Request$Params;)V", "getId", "()J", "getJsonrpc", "()Ljava/lang/String;", "getMethod", "getParams", "()Lcom/reown/foundation/network/model/Relay$Model$Call$BatchSubscribe$Request$Params;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Params", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
                public static final class Request extends Subscribe {
                    private final long id;
                    @NotNull
                    private final String jsonrpc;
                    @NotNull
                    private final String method;
                    @NotNull
                    private final Params params;

                    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\n\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0004HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Call$BatchSubscribe$Request$Params;", "", "topics", "", "", "<init>", "(Ljava/util/List;)V", "getTopics", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
                    public static final class Params {
                        @NotNull
                        private final List<String> topics;

                        public Params(@NotNull List<String> list) {
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
                        public final Params copy(@NotNull List<String> list) {
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
                        this(j2, (i3 & 2) != 0 ? "2.0" : str, (i3 & 4) != 0 ? JsonRpcRelayKt.IRN_BATCH_SUBSCRIBE : str2, params2);
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
                    public final Request copy(long j2, @NotNull String str, @NotNull String str2, @NotNull Params params2) {
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
                    public Request(long j2, @NotNull String str, @NotNull String str2, @NotNull Params params2) {
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

            @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0003\u0007\b\t¨\u0006\n"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Call$ProposeSession;", "Lcom/reown/foundation/network/model/Relay$Model$Call;", "<init>", "()V", "Request", "Acknowledgement", "JsonRpcError", "Lcom/reown/foundation/network/model/Relay$Model$Call$ProposeSession$Acknowledgement;", "Lcom/reown/foundation/network/model/Relay$Model$Call$ProposeSession$JsonRpcError;", "Lcom/reown/foundation/network/model/Relay$Model$Call$ProposeSession$Request;", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static abstract class ProposeSession extends Call {
                public /* synthetic */ ProposeSession(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00072\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Call$ProposeSession$Acknowledgement;", "Lcom/reown/foundation/network/model/Relay$Model$Call$ProposeSession;", "id", "", "jsonrpc", "", "result", "", "<init>", "(JLjava/lang/String;Z)V", "getId", "()J", "getJsonrpc", "()Ljava/lang/String;", "getResult", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
                public static final class Acknowledgement extends ProposeSession {
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
                    public final Acknowledgement copy(long j2, @NotNull String str, boolean z2) {
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
                    public Acknowledgement(long j2, @NotNull String str, boolean z2) {
                        super((DefaultConstructorMarker) null);
                        Intrinsics.checkNotNullParameter(str, "jsonrpc");
                        this.id = j2;
                        this.jsonrpc = str;
                        this.result = z2;
                    }
                }

                @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B!\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Call$ProposeSession$JsonRpcError;", "Lcom/reown/foundation/network/model/Relay$Model$Call$ProposeSession;", "jsonrpc", "", "error", "Lcom/reown/foundation/network/model/Relay$Model$Error;", "id", "", "<init>", "(Ljava/lang/String;Lcom/reown/foundation/network/model/Relay$Model$Error;J)V", "getJsonrpc", "()Ljava/lang/String;", "getError", "()Lcom/reown/foundation/network/model/Relay$Model$Error;", "getId", "()J", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
                public static final class JsonRpcError extends ProposeSession {
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
                    public final JsonRpcError copy(@NotNull String str, @NotNull Error error2, long j2) {
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
                    public JsonRpcError(@NotNull String str, @NotNull Error error2, long j2) {
                        super((DefaultConstructorMarker) null);
                        Intrinsics.checkNotNullParameter(str, "jsonrpc");
                        Intrinsics.checkNotNullParameter(error2, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
                        this.jsonrpc = str;
                        this.error = error2;
                        this.id = j2;
                    }
                }

                private ProposeSession() {
                    super((DefaultConstructorMarker) null);
                }

                @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001:\u0001\u001eB+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001f"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Call$ProposeSession$Request;", "Lcom/reown/foundation/network/model/Relay$Model$Call$ProposeSession;", "id", "", "jsonrpc", "", "method", "params", "Lcom/reown/foundation/network/model/Relay$Model$Call$ProposeSession$Request$Params;", "<init>", "(JLjava/lang/String;Ljava/lang/String;Lcom/reown/foundation/network/model/Relay$Model$Call$ProposeSession$Request$Params;)V", "getId", "()J", "getJsonrpc", "()Ljava/lang/String;", "getMethod", "getParams", "()Lcom/reown/foundation/network/model/Relay$Model$Call$ProposeSession$Request$Params;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Params", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
                public static final class Request extends ProposeSession {
                    private final long id;
                    @NotNull
                    private final String jsonrpc;
                    @NotNull
                    private final String method;
                    @NotNull
                    private final Params params;

                    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0007HÆ\u0003J1\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Call$ProposeSession$Request$Params;", "", "pairingTopic", "", "sessionProposal", "attestation", "correlationId", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)V", "getPairingTopic", "()Ljava/lang/String;", "getSessionProposal", "getAttestation", "getCorrelationId", "()J", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
                    public static final class Params {
                        @NotNull
                        private final String attestation;
                        private final long correlationId;
                        @NotNull
                        private final String pairingTopic;
                        @NotNull
                        private final String sessionProposal;

                        public Params(@NotNull String str, @NotNull String str2, @NotNull String str3, long j2) {
                            Intrinsics.checkNotNullParameter(str, "pairingTopic");
                            Intrinsics.checkNotNullParameter(str2, "sessionProposal");
                            Intrinsics.checkNotNullParameter(str3, "attestation");
                            this.pairingTopic = str;
                            this.sessionProposal = str2;
                            this.attestation = str3;
                            this.correlationId = j2;
                        }

                        public static /* synthetic */ Params copy$default(Params params, String str, String str2, String str3, long j2, int i3, Object obj) {
                            if ((i3 & 1) != 0) {
                                str = params.pairingTopic;
                            }
                            if ((i3 & 2) != 0) {
                                str2 = params.sessionProposal;
                            }
                            String str4 = str2;
                            if ((i3 & 4) != 0) {
                                str3 = params.attestation;
                            }
                            String str5 = str3;
                            if ((i3 & 8) != 0) {
                                j2 = params.correlationId;
                            }
                            return params.copy(str, str4, str5, j2);
                        }

                        @NotNull
                        public final String component1() {
                            return this.pairingTopic;
                        }

                        @NotNull
                        public final String component2() {
                            return this.sessionProposal;
                        }

                        @NotNull
                        public final String component3() {
                            return this.attestation;
                        }

                        public final long component4() {
                            return this.correlationId;
                        }

                        @NotNull
                        public final Params copy(@NotNull String str, @NotNull String str2, @NotNull String str3, long j2) {
                            Intrinsics.checkNotNullParameter(str, "pairingTopic");
                            Intrinsics.checkNotNullParameter(str2, "sessionProposal");
                            Intrinsics.checkNotNullParameter(str3, "attestation");
                            return new Params(str, str2, str3, j2);
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

                        @NotNull
                        public final String getAttestation() {
                            return this.attestation;
                        }

                        public final long getCorrelationId() {
                            return this.correlationId;
                        }

                        @NotNull
                        public final String getPairingTopic() {
                            return this.pairingTopic;
                        }

                        @NotNull
                        public final String getSessionProposal() {
                            return this.sessionProposal;
                        }

                        public int hashCode() {
                            return Long.hashCode(this.correlationId) + a.i(this.attestation, a.i(this.sessionProposal, this.pairingTopic.hashCode() * 31, 31), 31);
                        }

                        @NotNull
                        public String toString() {
                            String str = this.pairingTopic;
                            String str2 = this.sessionProposal;
                            String str3 = this.attestation;
                            long j2 = this.correlationId;
                            StringBuilder l2 = C0118y.l("Params(pairingTopic=", str, ", sessionProposal=", str2, ", attestation=");
                            l2.append(str3);
                            l2.append(", correlationId=");
                            l2.append(j2);
                            l2.append(")");
                            return l2.toString();
                        }
                    }

                    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
                    public /* synthetic */ Request(long j2, String str, String str2, Params params2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                        this(j2, (i3 & 2) != 0 ? "2.0" : str, (i3 & 4) != 0 ? JsonRpcRelayKt.WC_PROPOSE_SESSION : str2, params2);
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
                    public final Request copy(long j2, @NotNull String str, @NotNull String str2, @NotNull Params params2) {
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
                    public Request(long j2, @NotNull String str, @NotNull String str2, @NotNull Params params2) {
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

            @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0003\u0007\b\t¨\u0006\n"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Call$Publish;", "Lcom/reown/foundation/network/model/Relay$Model$Call;", "<init>", "()V", "Request", "Acknowledgement", "JsonRpcError", "Lcom/reown/foundation/network/model/Relay$Model$Call$Publish$Acknowledgement;", "Lcom/reown/foundation/network/model/Relay$Model$Call$Publish$JsonRpcError;", "Lcom/reown/foundation/network/model/Relay$Model$Call$Publish$Request;", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static abstract class Publish extends Call {
                public /* synthetic */ Publish(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00072\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Call$Publish$Acknowledgement;", "Lcom/reown/foundation/network/model/Relay$Model$Call$Publish;", "id", "", "jsonrpc", "", "result", "", "<init>", "(JLjava/lang/String;Z)V", "getId", "()J", "getJsonrpc", "()Ljava/lang/String;", "getResult", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
                public static final class Acknowledgement extends Publish {
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
                    public final Acknowledgement copy(long j2, @NotNull String str, boolean z2) {
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
                    public Acknowledgement(long j2, @NotNull String str, boolean z2) {
                        super((DefaultConstructorMarker) null);
                        Intrinsics.checkNotNullParameter(str, "jsonrpc");
                        this.id = j2;
                        this.jsonrpc = str;
                        this.result = z2;
                    }
                }

                @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B!\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Call$Publish$JsonRpcError;", "Lcom/reown/foundation/network/model/Relay$Model$Call$Publish;", "jsonrpc", "", "error", "Lcom/reown/foundation/network/model/Relay$Model$Error;", "id", "", "<init>", "(Ljava/lang/String;Lcom/reown/foundation/network/model/Relay$Model$Error;J)V", "getJsonrpc", "()Ljava/lang/String;", "getError", "()Lcom/reown/foundation/network/model/Relay$Model$Error;", "getId", "()J", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
                public static final class JsonRpcError extends Publish {
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
                    public final JsonRpcError copy(@NotNull String str, @NotNull Error error2, long j2) {
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
                    public JsonRpcError(@NotNull String str, @NotNull Error error2, long j2) {
                        super((DefaultConstructorMarker) null);
                        Intrinsics.checkNotNullParameter(str, "jsonrpc");
                        Intrinsics.checkNotNullParameter(error2, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
                        this.jsonrpc = str;
                        this.error = error2;
                        this.id = j2;
                    }
                }

                private Publish() {
                    super((DefaultConstructorMarker) null);
                }

                @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001:\u0001\u001eB+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001f"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Call$Publish$Request;", "Lcom/reown/foundation/network/model/Relay$Model$Call$Publish;", "id", "", "jsonrpc", "", "method", "params", "Lcom/reown/foundation/network/model/Relay$Model$Call$Publish$Request$Params;", "<init>", "(JLjava/lang/String;Ljava/lang/String;Lcom/reown/foundation/network/model/Relay$Model$Call$Publish$Request$Params;)V", "getId", "()J", "getJsonrpc", "()Ljava/lang/String;", "getMethod", "getParams", "()Lcom/reown/foundation/network/model/Relay$Model$Call$Publish$Request$Params;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Params", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
                public static final class Request extends Publish {
                    private final long id;
                    @NotNull
                    private final String jsonrpc;
                    @NotNull
                    private final String method;
                    @NotNull
                    private final Params params;

                    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0018\b\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001a\u001a\u00020\bHÆ\u0003J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\u0015JB\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nHÆ\u0001¢\u0006\u0002\u0010\u001dJ\u0013\u0010\u001e\u001a\u00020\n2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020\bHÖ\u0001J\t\u0010!\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0015\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0015¨\u0006\""}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Call$Publish$Request$Params;", "", "topic", "", "message", "ttl", "", "tag", "", "prompt", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;JILjava/lang/Boolean;)V", "getTopic", "()Ljava/lang/String;", "getMessage", "getTtl", "()J", "getTag", "()I", "getPrompt", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;Ljava/lang/String;JILjava/lang/Boolean;)Lcom/reown/foundation/network/model/Relay$Model$Call$Publish$Request$Params;", "equals", "other", "hashCode", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
                    public static final class Params {
                        @NotNull
                        private final String message;
                        @Nullable
                        private final Boolean prompt;
                        private final int tag;
                        @NotNull
                        private final String topic;
                        private final long ttl;

                        public Params(@NotNull String str, @NotNull String str2, long j2, int i3, @Nullable Boolean bool) {
                            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                            Intrinsics.checkNotNullParameter(str2, PushMessagingService.KEY_MESSAGE);
                            this.topic = str;
                            this.message = str2;
                            this.ttl = j2;
                            this.tag = i3;
                            this.prompt = bool;
                        }

                        public static /* synthetic */ Params copy$default(Params params, String str, String str2, long j2, int i3, Boolean bool, int i4, Object obj) {
                            if ((i4 & 1) != 0) {
                                str = params.topic;
                            }
                            if ((i4 & 2) != 0) {
                                str2 = params.message;
                            }
                            String str3 = str2;
                            if ((i4 & 4) != 0) {
                                j2 = params.ttl;
                            }
                            long j3 = j2;
                            if ((i4 & 8) != 0) {
                                i3 = params.tag;
                            }
                            int i5 = i3;
                            if ((i4 & 16) != 0) {
                                bool = params.prompt;
                            }
                            return params.copy(str, str3, j3, i5, bool);
                        }

                        @NotNull
                        public final String component1() {
                            return this.topic;
                        }

                        @NotNull
                        public final String component2() {
                            return this.message;
                        }

                        public final long component3() {
                            return this.ttl;
                        }

                        public final int component4() {
                            return this.tag;
                        }

                        @Nullable
                        public final Boolean component5() {
                            return this.prompt;
                        }

                        @NotNull
                        public final Params copy(@NotNull String str, @NotNull String str2, long j2, int i3, @Nullable Boolean bool) {
                            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                            Intrinsics.checkNotNullParameter(str2, PushMessagingService.KEY_MESSAGE);
                            return new Params(str, str2, j2, i3, bool);
                        }

                        public boolean equals(@Nullable Object obj) {
                            if (this == obj) {
                                return true;
                            }
                            if (!(obj instanceof Params)) {
                                return false;
                            }
                            Params params = (Params) obj;
                            return Intrinsics.areEqual((Object) this.topic, (Object) params.topic) && Intrinsics.areEqual((Object) this.message, (Object) params.message) && this.ttl == params.ttl && this.tag == params.tag && Intrinsics.areEqual((Object) this.prompt, (Object) params.prompt);
                        }

                        @NotNull
                        public final String getMessage() {
                            return this.message;
                        }

                        @Nullable
                        public final Boolean getPrompt() {
                            return this.prompt;
                        }

                        public final int getTag() {
                            return this.tag;
                        }

                        @NotNull
                        public final String getTopic() {
                            return this.topic;
                        }

                        public final long getTtl() {
                            return this.ttl;
                        }

                        public int hashCode() {
                            int c3 = a.c(this.tag, a.D(this.ttl, a.i(this.message, this.topic.hashCode() * 31, 31), 31), 31);
                            Boolean bool = this.prompt;
                            return c3 + (bool == null ? 0 : bool.hashCode());
                        }

                        @NotNull
                        public String toString() {
                            String str = this.topic;
                            String str2 = this.message;
                            long j2 = this.ttl;
                            int i3 = this.tag;
                            Boolean bool = this.prompt;
                            StringBuilder l2 = C0118y.l("Params(topic=", str, ", message=", str2, ", ttl=");
                            l2.append(j2);
                            l2.append(", tag=");
                            l2.append(i3);
                            l2.append(", prompt=");
                            l2.append(bool);
                            l2.append(")");
                            return l2.toString();
                        }
                    }

                    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
                    public /* synthetic */ Request(long j2, String str, String str2, Params params2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                        this(j2, (i3 & 2) != 0 ? "2.0" : str, (i3 & 4) != 0 ? JsonRpcRelayKt.IRN_PUBLISH : str2, params2);
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
                    public final Request copy(long j2, @NotNull String str, @NotNull String str2, @NotNull Params params2) {
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
                    public Request(long j2, @NotNull String str, @NotNull String str2, @NotNull Params params2) {
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

            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0006\u0007\b\t\n\u000b\f¨\u0006\r"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Call$Subscribe;", "Lcom/reown/foundation/network/model/Relay$Model$Call;", "<init>", "()V", "Request", "Acknowledgement", "JsonRpcError", "Lcom/reown/foundation/network/model/Relay$Model$Call$BatchSubscribe$Acknowledgement;", "Lcom/reown/foundation/network/model/Relay$Model$Call$BatchSubscribe$JsonRpcError;", "Lcom/reown/foundation/network/model/Relay$Model$Call$BatchSubscribe$Request;", "Lcom/reown/foundation/network/model/Relay$Model$Call$Subscribe$Acknowledgement;", "Lcom/reown/foundation/network/model/Relay$Model$Call$Subscribe$JsonRpcError;", "Lcom/reown/foundation/network/model/Relay$Model$Call$Subscribe$Request;", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static abstract class Subscribe extends Call {
                public /* synthetic */ Subscribe(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0019"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Call$Subscribe$Acknowledgement;", "Lcom/reown/foundation/network/model/Relay$Model$Call$Subscribe;", "id", "", "jsonrpc", "", "result", "<init>", "(JLjava/lang/String;Ljava/lang/String;)V", "getId", "()J", "getJsonrpc", "()Ljava/lang/String;", "getResult", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
                public static final class Acknowledgement extends Subscribe {
                    private final long id;
                    @NotNull
                    private final String jsonrpc;
                    @NotNull
                    private final String result;

                    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
                    public /* synthetic */ Acknowledgement(long j2, String str, String str2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                        this(j2, (i3 & 2) != 0 ? "2.0" : str, str2);
                    }

                    public static /* synthetic */ Acknowledgement copy$default(Acknowledgement acknowledgement, long j2, String str, String str2, int i3, Object obj) {
                        if ((i3 & 1) != 0) {
                            j2 = acknowledgement.id;
                        }
                        if ((i3 & 2) != 0) {
                            str = acknowledgement.jsonrpc;
                        }
                        if ((i3 & 4) != 0) {
                            str2 = acknowledgement.result;
                        }
                        return acknowledgement.copy(j2, str, str2);
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
                        return this.result;
                    }

                    @NotNull
                    public final Acknowledgement copy(long j2, @NotNull String str, @NotNull String str2) {
                        Intrinsics.checkNotNullParameter(str, "jsonrpc");
                        Intrinsics.checkNotNullParameter(str2, "result");
                        return new Acknowledgement(j2, str, str2);
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
                    public final String getResult() {
                        return this.result;
                    }

                    public int hashCode() {
                        return this.result.hashCode() + a.i(this.jsonrpc, Long.hashCode(this.id) * 31, 31);
                    }

                    @NotNull
                    public String toString() {
                        long j2 = this.id;
                        String str = this.jsonrpc;
                        return C0118y.j(androidx.work.impl.a.v(j2, "Acknowledgement(id=", ", jsonrpc=", str), ", result=", this.result, ")");
                    }

                    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                    public Acknowledgement(long j2, @NotNull String str, @NotNull String str2) {
                        super((DefaultConstructorMarker) null);
                        Intrinsics.checkNotNullParameter(str, "jsonrpc");
                        Intrinsics.checkNotNullParameter(str2, "result");
                        this.id = j2;
                        this.jsonrpc = str;
                        this.result = str2;
                    }
                }

                @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B!\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Call$Subscribe$JsonRpcError;", "Lcom/reown/foundation/network/model/Relay$Model$Call$Subscribe;", "jsonrpc", "", "error", "Lcom/reown/foundation/network/model/Relay$Model$Error;", "id", "", "<init>", "(Ljava/lang/String;Lcom/reown/foundation/network/model/Relay$Model$Error;J)V", "getJsonrpc", "()Ljava/lang/String;", "getError", "()Lcom/reown/foundation/network/model/Relay$Model$Error;", "getId", "()J", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
                public static final class JsonRpcError extends Subscribe {
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
                    public final JsonRpcError copy(@NotNull String str, @NotNull Error error2, long j2) {
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
                    public JsonRpcError(@NotNull String str, @NotNull Error error2, long j2) {
                        super((DefaultConstructorMarker) null);
                        Intrinsics.checkNotNullParameter(str, "jsonrpc");
                        Intrinsics.checkNotNullParameter(error2, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
                        this.jsonrpc = str;
                        this.error = error2;
                        this.id = j2;
                    }
                }

                private Subscribe() {
                    super((DefaultConstructorMarker) null);
                }

                @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001:\u0001\u001eB+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001f"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Call$Subscribe$Request;", "Lcom/reown/foundation/network/model/Relay$Model$Call$Subscribe;", "id", "", "jsonrpc", "", "method", "params", "Lcom/reown/foundation/network/model/Relay$Model$Call$Subscribe$Request$Params;", "<init>", "(JLjava/lang/String;Ljava/lang/String;Lcom/reown/foundation/network/model/Relay$Model$Call$Subscribe$Request$Params;)V", "getId", "()J", "getJsonrpc", "()Ljava/lang/String;", "getMethod", "getParams", "()Lcom/reown/foundation/network/model/Relay$Model$Call$Subscribe$Request$Params;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Params", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
                public static final class Request extends Subscribe {
                    private final long id;
                    @NotNull
                    private final String jsonrpc;
                    @NotNull
                    private final String method;
                    @NotNull
                    private final Params params;

                    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Call$Subscribe$Request$Params;", "", "topic", "", "<init>", "(Ljava/lang/String;)V", "getTopic", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
                    public static final class Params {
                        @NotNull
                        private final String topic;

                        public Params(@NotNull String str) {
                            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                            this.topic = str;
                        }

                        public static /* synthetic */ Params copy$default(Params params, String str, int i3, Object obj) {
                            if ((i3 & 1) != 0) {
                                str = params.topic;
                            }
                            return params.copy(str);
                        }

                        @NotNull
                        public final String component1() {
                            return this.topic;
                        }

                        @NotNull
                        public final Params copy(@NotNull String str) {
                            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                            return new Params(str);
                        }

                        public boolean equals(@Nullable Object obj) {
                            if (this == obj) {
                                return true;
                            }
                            return (obj instanceof Params) && Intrinsics.areEqual((Object) this.topic, (Object) ((Params) obj).topic);
                        }

                        @NotNull
                        public final String getTopic() {
                            return this.topic;
                        }

                        public int hashCode() {
                            return this.topic.hashCode();
                        }

                        @NotNull
                        public String toString() {
                            return A.a.l("Params(topic=", this.topic, ")");
                        }
                    }

                    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
                    public /* synthetic */ Request(long j2, String str, String str2, Params params2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                        this(j2, (i3 & 2) != 0 ? "2.0" : str, (i3 & 4) != 0 ? JsonRpcRelayKt.IRN_SUBSCRIBE : str2, params2);
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
                    public final Request copy(long j2, @NotNull String str, @NotNull String str2, @NotNull Params params2) {
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
                    public Request(long j2, @NotNull String str, @NotNull String str2, @NotNull Params params2) {
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

            @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0003\u0007\b\t¨\u0006\n"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Call$Subscription;", "Lcom/reown/foundation/network/model/Relay$Model$Call;", "<init>", "()V", "Request", "Acknowledgement", "JsonRpcError", "Lcom/reown/foundation/network/model/Relay$Model$Call$Subscription$Acknowledgement;", "Lcom/reown/foundation/network/model/Relay$Model$Call$Subscription$JsonRpcError;", "Lcom/reown/foundation/network/model/Relay$Model$Call$Subscription$Request;", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static abstract class Subscription extends Call {
                public /* synthetic */ Subscription(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00072\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Call$Subscription$Acknowledgement;", "Lcom/reown/foundation/network/model/Relay$Model$Call$Subscription;", "id", "", "jsonrpc", "", "result", "", "<init>", "(JLjava/lang/String;Z)V", "getId", "()J", "getJsonrpc", "()Ljava/lang/String;", "getResult", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
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
                    public final Acknowledgement copy(long j2, @NotNull String str, boolean z2) {
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
                    public Acknowledgement(long j2, @NotNull String str, boolean z2) {
                        super((DefaultConstructorMarker) null);
                        Intrinsics.checkNotNullParameter(str, "jsonrpc");
                        this.id = j2;
                        this.jsonrpc = str;
                        this.result = z2;
                    }
                }

                @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B!\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Call$Subscription$JsonRpcError;", "Lcom/reown/foundation/network/model/Relay$Model$Call$Subscription;", "jsonrpc", "", "error", "Lcom/reown/foundation/network/model/Relay$Model$Error;", "id", "", "<init>", "(Ljava/lang/String;Lcom/reown/foundation/network/model/Relay$Model$Error;J)V", "getJsonrpc", "()Ljava/lang/String;", "getError", "()Lcom/reown/foundation/network/model/Relay$Model$Error;", "getId", "()J", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
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
                    public final JsonRpcError copy(@NotNull String str, @NotNull Error error2, long j2) {
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
                    public JsonRpcError(@NotNull String str, @NotNull Error error2, long j2) {
                        super((DefaultConstructorMarker) null);
                        Intrinsics.checkNotNullParameter(str, "jsonrpc");
                        Intrinsics.checkNotNullParameter(error2, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
                        this.jsonrpc = str;
                        this.error = error2;
                        this.id = j2;
                    }
                }

                private Subscription() {
                    super((DefaultConstructorMarker) null);
                }

                @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001:\u0001)B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\t\u0010 \u001a\u00020\u0005HÆ\u0003J\t\u0010!\u001a\u00020\bHÆ\u0003J1\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010&HÖ\u0003J\t\u0010'\u001a\u00020\u0017HÖ\u0001J\t\u0010(\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0011\u0010\u0014\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000eR\u0011\u0010\u0016\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u001a\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\fR\u0013\u0010\u001c\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u000e¨\u0006*"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Call$Subscription$Request;", "Lcom/reown/foundation/network/model/Relay$Model$Call$Subscription;", "id", "", "jsonrpc", "", "method", "params", "Lcom/reown/foundation/network/model/Relay$Model$Call$Subscription$Request$Params;", "<init>", "(JLjava/lang/String;Ljava/lang/String;Lcom/reown/foundation/network/model/Relay$Model$Call$Subscription$Request$Params;)V", "getId", "()J", "getJsonrpc", "()Ljava/lang/String;", "getMethod", "getParams", "()Lcom/reown/foundation/network/model/Relay$Model$Call$Subscription$Request$Params;", "subscriptionTopic", "getSubscriptionTopic", "message", "getMessage", "tag", "", "getTag", "()I", "publishedAt", "getPublishedAt", "attestation", "getAttestation", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "toString", "Params", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
                public static final class Request extends Subscription {
                    @Nullable
                    private final String attestation;
                    private final long id;
                    @NotNull
                    private final String jsonrpc;
                    @NotNull
                    private final String message;
                    @NotNull
                    private final String method;
                    @NotNull
                    private final Params params;
                    private final long publishedAt;
                    @NotNull
                    private final String subscriptionTopic;
                    private final int tag;

                    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001:\u0001\u0015B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Call$Subscription$Request$Params;", "", "subscriptionId", "", "subscriptionData", "Lcom/reown/foundation/network/model/Relay$Model$Call$Subscription$Request$Params$SubscriptionData;", "<init>", "(Ljava/lang/String;Lcom/reown/foundation/network/model/Relay$Model$Call$Subscription$Request$Params$SubscriptionData;)V", "getSubscriptionId", "()Ljava/lang/String;", "getSubscriptionData", "()Lcom/reown/foundation/network/model/Relay$Model$Call$Subscription$Request$Params$SubscriptionData;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "SubscriptionData", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
                    public static final class Params {
                        @NotNull
                        private final SubscriptionData subscriptionData;
                        @NotNull
                        private final String subscriptionId;

                        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\tHÆ\u0003J=\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\tHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001f"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Call$Subscription$Request$Params$SubscriptionData;", "", "topic", "", "message", "publishedAt", "", "attestation", "tag", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;I)V", "getTopic", "()Ljava/lang/String;", "getMessage", "getPublishedAt", "()J", "getAttestation", "getTag", "()I", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
                        public static final class SubscriptionData {
                            @Nullable
                            private final String attestation;
                            @NotNull
                            private final String message;
                            private final long publishedAt;
                            private final int tag;
                            @NotNull
                            private final String topic;

                            public SubscriptionData(@NotNull String str, @NotNull String str2, long j2, @Nullable String str3, int i3) {
                                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                                Intrinsics.checkNotNullParameter(str2, PushMessagingService.KEY_MESSAGE);
                                this.topic = str;
                                this.message = str2;
                                this.publishedAt = j2;
                                this.attestation = str3;
                                this.tag = i3;
                            }

                            public static /* synthetic */ SubscriptionData copy$default(SubscriptionData subscriptionData, String str, String str2, long j2, String str3, int i3, int i4, Object obj) {
                                if ((i4 & 1) != 0) {
                                    str = subscriptionData.topic;
                                }
                                if ((i4 & 2) != 0) {
                                    str2 = subscriptionData.message;
                                }
                                String str4 = str2;
                                if ((i4 & 4) != 0) {
                                    j2 = subscriptionData.publishedAt;
                                }
                                long j3 = j2;
                                if ((i4 & 8) != 0) {
                                    str3 = subscriptionData.attestation;
                                }
                                String str5 = str3;
                                if ((i4 & 16) != 0) {
                                    i3 = subscriptionData.tag;
                                }
                                return subscriptionData.copy(str, str4, j3, str5, i3);
                            }

                            @NotNull
                            public final String component1() {
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
                            public final SubscriptionData copy(@NotNull String str, @NotNull String str2, long j2, @Nullable String str3, int i3) {
                                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                                Intrinsics.checkNotNullParameter(str2, PushMessagingService.KEY_MESSAGE);
                                return new SubscriptionData(str, str2, j2, str3, i3);
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
                            public final String getTopic() {
                                return this.topic;
                            }

                            public int hashCode() {
                                int D2 = a.D(this.publishedAt, a.i(this.message, this.topic.hashCode() * 31, 31), 31);
                                String str = this.attestation;
                                return Integer.hashCode(this.tag) + ((D2 + (str == null ? 0 : str.hashCode())) * 31);
                            }

                            @NotNull
                            public String toString() {
                                String str = this.topic;
                                String str2 = this.message;
                                long j2 = this.publishedAt;
                                String str3 = this.attestation;
                                int i3 = this.tag;
                                StringBuilder l2 = C0118y.l("SubscriptionData(topic=", str, ", message=", str2, ", publishedAt=");
                                l2.append(j2);
                                l2.append(", attestation=");
                                l2.append(str3);
                                l2.append(", tag=");
                                l2.append(i3);
                                l2.append(")");
                                return l2.toString();
                            }
                        }

                        public Params(@NotNull String str, @NotNull SubscriptionData subscriptionData2) {
                            Intrinsics.checkNotNullParameter(str, "subscriptionId");
                            Intrinsics.checkNotNullParameter(subscriptionData2, "subscriptionData");
                            this.subscriptionId = str;
                            this.subscriptionData = subscriptionData2;
                        }

                        public static /* synthetic */ Params copy$default(Params params, String str, SubscriptionData subscriptionData2, int i3, Object obj) {
                            if ((i3 & 1) != 0) {
                                str = params.subscriptionId;
                            }
                            if ((i3 & 2) != 0) {
                                subscriptionData2 = params.subscriptionData;
                            }
                            return params.copy(str, subscriptionData2);
                        }

                        @NotNull
                        public final String component1() {
                            return this.subscriptionId;
                        }

                        @NotNull
                        public final SubscriptionData component2() {
                            return this.subscriptionData;
                        }

                        @NotNull
                        public final Params copy(@NotNull String str, @NotNull SubscriptionData subscriptionData2) {
                            Intrinsics.checkNotNullParameter(str, "subscriptionId");
                            Intrinsics.checkNotNullParameter(subscriptionData2, "subscriptionData");
                            return new Params(str, subscriptionData2);
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
                        public final String getSubscriptionId() {
                            return this.subscriptionId;
                        }

                        public int hashCode() {
                            return this.subscriptionData.hashCode() + (this.subscriptionId.hashCode() * 31);
                        }

                        @NotNull
                        public String toString() {
                            String str = this.subscriptionId;
                            SubscriptionData subscriptionData2 = this.subscriptionData;
                            return "Params(subscriptionId=" + str + ", subscriptionData=" + subscriptionData2 + ")";
                        }
                    }

                    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
                    public /* synthetic */ Request(long j2, String str, String str2, Params params2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                        this(j2, (i3 & 2) != 0 ? "2.0" : str, (i3 & 4) != 0 ? JsonRpcRelayKt.IRN_SUBSCRIPTION : str2, params2);
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
                    public final Request copy(long j2, @NotNull String str, @NotNull String str2, @NotNull Params params2) {
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

                    @Nullable
                    public final String getAttestation() {
                        return this.attestation;
                    }

                    public long getId() {
                        return this.id;
                    }

                    @NotNull
                    public String getJsonrpc() {
                        return this.jsonrpc;
                    }

                    @NotNull
                    public final String getMessage() {
                        return this.message;
                    }

                    @NotNull
                    public final String getMethod() {
                        return this.method;
                    }

                    @NotNull
                    public final Params getParams() {
                        return this.params;
                    }

                    public final long getPublishedAt() {
                        return this.publishedAt;
                    }

                    @NotNull
                    public final String getSubscriptionTopic() {
                        return this.subscriptionTopic;
                    }

                    public final int getTag() {
                        return this.tag;
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
                    public Request(long j2, @NotNull String str, @NotNull String str2, @NotNull Params params2) {
                        super((DefaultConstructorMarker) null);
                        Intrinsics.checkNotNullParameter(str, "jsonrpc");
                        Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
                        Intrinsics.checkNotNullParameter(params2, "params");
                        this.id = j2;
                        this.jsonrpc = str;
                        this.method = str2;
                        this.params = params2;
                        this.subscriptionTopic = params2.getSubscriptionData().getTopic();
                        this.message = params2.getSubscriptionData().getMessage();
                        this.tag = params2.getSubscriptionData().getTag();
                        this.publishedAt = params2.getSubscriptionData().getPublishedAt();
                        this.attestation = params2.getSubscriptionData().getAttestation();
                    }
                }
            }

            @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0003\u0007\b\t¨\u0006\n"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Call$Unsubscribe;", "Lcom/reown/foundation/network/model/Relay$Model$Call;", "<init>", "()V", "Request", "Acknowledgement", "JsonRpcError", "Lcom/reown/foundation/network/model/Relay$Model$Call$Unsubscribe$Acknowledgement;", "Lcom/reown/foundation/network/model/Relay$Model$Call$Unsubscribe$JsonRpcError;", "Lcom/reown/foundation/network/model/Relay$Model$Call$Unsubscribe$Request;", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static abstract class Unsubscribe extends Call {
                public /* synthetic */ Unsubscribe(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00072\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Call$Unsubscribe$Acknowledgement;", "Lcom/reown/foundation/network/model/Relay$Model$Call$Unsubscribe;", "id", "", "jsonrpc", "", "result", "", "<init>", "(JLjava/lang/String;Z)V", "getId", "()J", "getJsonrpc", "()Ljava/lang/String;", "getResult", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
                public static final class Acknowledgement extends Unsubscribe {
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
                    public final Acknowledgement copy(long j2, @NotNull String str, boolean z2) {
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
                    public Acknowledgement(long j2, @NotNull String str, boolean z2) {
                        super((DefaultConstructorMarker) null);
                        Intrinsics.checkNotNullParameter(str, "jsonrpc");
                        this.id = j2;
                        this.jsonrpc = str;
                        this.result = z2;
                    }
                }

                @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B!\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Call$Unsubscribe$JsonRpcError;", "Lcom/reown/foundation/network/model/Relay$Model$Call$Unsubscribe;", "jsonrpc", "", "error", "Lcom/reown/foundation/network/model/Relay$Model$Error;", "id", "", "<init>", "(Ljava/lang/String;Lcom/reown/foundation/network/model/Relay$Model$Error;J)V", "getJsonrpc", "()Ljava/lang/String;", "getError", "()Lcom/reown/foundation/network/model/Relay$Model$Error;", "getId", "()J", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
                public static final class JsonRpcError extends Unsubscribe {
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
                    public final JsonRpcError copy(@NotNull String str, @NotNull Error error2, long j2) {
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
                    public JsonRpcError(@NotNull String str, @NotNull Error error2, long j2) {
                        super((DefaultConstructorMarker) null);
                        Intrinsics.checkNotNullParameter(str, "jsonrpc");
                        Intrinsics.checkNotNullParameter(error2, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
                        this.jsonrpc = str;
                        this.error = error2;
                        this.id = j2;
                    }
                }

                private Unsubscribe() {
                    super((DefaultConstructorMarker) null);
                }

                @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001:\u0001\u001eB+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001f"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Call$Unsubscribe$Request;", "Lcom/reown/foundation/network/model/Relay$Model$Call$Unsubscribe;", "id", "", "jsonrpc", "", "method", "params", "Lcom/reown/foundation/network/model/Relay$Model$Call$Unsubscribe$Request$Params;", "<init>", "(JLjava/lang/String;Ljava/lang/String;Lcom/reown/foundation/network/model/Relay$Model$Call$Unsubscribe$Request$Params;)V", "getId", "()J", "getJsonrpc", "()Ljava/lang/String;", "getMethod", "getParams", "()Lcom/reown/foundation/network/model/Relay$Model$Call$Unsubscribe$Request$Params;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Params", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
                public static final class Request extends Unsubscribe {
                    private final long id;
                    @NotNull
                    private final String jsonrpc;
                    @NotNull
                    private final String method;
                    @NotNull
                    private final Params params;

                    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Call$Unsubscribe$Request$Params;", "", "topic", "", "subscriptionId", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getTopic", "()Ljava/lang/String;", "getSubscriptionId", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
                    public static final class Params {
                        @NotNull
                        private final String subscriptionId;
                        @NotNull
                        private final String topic;

                        public Params(@NotNull String str, @NotNull String str2) {
                            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                            Intrinsics.checkNotNullParameter(str2, "subscriptionId");
                            this.topic = str;
                            this.subscriptionId = str2;
                        }

                        public static /* synthetic */ Params copy$default(Params params, String str, String str2, int i3, Object obj) {
                            if ((i3 & 1) != 0) {
                                str = params.topic;
                            }
                            if ((i3 & 2) != 0) {
                                str2 = params.subscriptionId;
                            }
                            return params.copy(str, str2);
                        }

                        @NotNull
                        public final String component1() {
                            return this.topic;
                        }

                        @NotNull
                        public final String component2() {
                            return this.subscriptionId;
                        }

                        @NotNull
                        public final Params copy(@NotNull String str, @NotNull String str2) {
                            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                            Intrinsics.checkNotNullParameter(str2, "subscriptionId");
                            return new Params(str, str2);
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
                        public final String getSubscriptionId() {
                            return this.subscriptionId;
                        }

                        @NotNull
                        public final String getTopic() {
                            return this.topic;
                        }

                        public int hashCode() {
                            return this.subscriptionId.hashCode() + (this.topic.hashCode() * 31);
                        }

                        @NotNull
                        public String toString() {
                            return C0118y.g("Params(topic=", this.topic, ", subscriptionId=", this.subscriptionId, ")");
                        }
                    }

                    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
                    public /* synthetic */ Request(long j2, String str, String str2, Params params2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                        this(j2, (i3 & 2) != 0 ? "2.0" : str, (i3 & 4) != 0 ? JsonRpcRelayKt.IRN_UNSUBSCRIBE : str2, params2);
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
                    public final Request copy(long j2, @NotNull String str, @NotNull String str2, @NotNull Params params2) {
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
                    public Request(long j2, @NotNull String str, @NotNull String str2, @NotNull Params params2) {
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

            public /* synthetic */ Call(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public abstract long getId();

            @NotNull
            public abstract String getJsonrpc();

            private Call() {
                super((DefaultConstructorMarker) null);
            }
        }

        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000b¨\u0006\u0018"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Error;", "Lcom/reown/foundation/network/model/Relay$Model;", "code", "", "message", "", "<init>", "(JLjava/lang/String;)V", "getCode", "()J", "getMessage", "()Ljava/lang/String;", "errorMessage", "getErrorMessage", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Error extends Model {
            private final long code;
            @NotNull
            private final String errorMessage;
            @NotNull
            private final String message;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Error(long j2, @NotNull String str) {
                super((DefaultConstructorMarker) null);
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
            public final Error copy(long j2, @NotNull String str) {
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

        @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u0004\u0005\u0006\u0007\bB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0005\t\n\u000b\f\r¨\u0006\u000e"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Event;", "Lcom/reown/foundation/network/model/Relay$Model;", "<init>", "()V", "OnConnectionOpened", "OnMessageReceived", "OnConnectionClosing", "OnConnectionClosed", "OnConnectionFailed", "Lcom/reown/foundation/network/model/Relay$Model$Event$OnConnectionClosed;", "Lcom/reown/foundation/network/model/Relay$Model$Event$OnConnectionClosing;", "Lcom/reown/foundation/network/model/Relay$Model$Event$OnConnectionFailed;", "Lcom/reown/foundation/network/model/Relay$Model$Event$OnConnectionOpened;", "Lcom/reown/foundation/network/model/Relay$Model$Event$OnMessageReceived;", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static abstract class Event extends Model {

            @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Event$OnConnectionClosed;", "Lcom/reown/foundation/network/model/Relay$Model$Event;", "shutdownReason", "Lcom/reown/foundation/network/model/Relay$Model$ShutdownReason;", "<init>", "(Lcom/reown/foundation/network/model/Relay$Model$ShutdownReason;)V", "getShutdownReason", "()Lcom/reown/foundation/network/model/Relay$Model$ShutdownReason;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class OnConnectionClosed extends Event {
                @NotNull
                private final ShutdownReason shutdownReason;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public OnConnectionClosed(@NotNull ShutdownReason shutdownReason2) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(shutdownReason2, "shutdownReason");
                    this.shutdownReason = shutdownReason2;
                }

                public static /* synthetic */ OnConnectionClosed copy$default(OnConnectionClosed onConnectionClosed, ShutdownReason shutdownReason2, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        shutdownReason2 = onConnectionClosed.shutdownReason;
                    }
                    return onConnectionClosed.copy(shutdownReason2);
                }

                @NotNull
                public final ShutdownReason component1() {
                    return this.shutdownReason;
                }

                @NotNull
                public final OnConnectionClosed copy(@NotNull ShutdownReason shutdownReason2) {
                    Intrinsics.checkNotNullParameter(shutdownReason2, "shutdownReason");
                    return new OnConnectionClosed(shutdownReason2);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return (obj instanceof OnConnectionClosed) && Intrinsics.areEqual((Object) this.shutdownReason, (Object) ((OnConnectionClosed) obj).shutdownReason);
                }

                @NotNull
                public final ShutdownReason getShutdownReason() {
                    return this.shutdownReason;
                }

                public int hashCode() {
                    return this.shutdownReason.hashCode();
                }

                @NotNull
                public String toString() {
                    ShutdownReason shutdownReason2 = this.shutdownReason;
                    return "OnConnectionClosed(shutdownReason=" + shutdownReason2 + ")";
                }
            }

            @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Event$OnConnectionClosing;", "Lcom/reown/foundation/network/model/Relay$Model$Event;", "shutdownReason", "Lcom/reown/foundation/network/model/Relay$Model$ShutdownReason;", "<init>", "(Lcom/reown/foundation/network/model/Relay$Model$ShutdownReason;)V", "getShutdownReason", "()Lcom/reown/foundation/network/model/Relay$Model$ShutdownReason;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class OnConnectionClosing extends Event {
                @NotNull
                private final ShutdownReason shutdownReason;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public OnConnectionClosing(@NotNull ShutdownReason shutdownReason2) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(shutdownReason2, "shutdownReason");
                    this.shutdownReason = shutdownReason2;
                }

                public static /* synthetic */ OnConnectionClosing copy$default(OnConnectionClosing onConnectionClosing, ShutdownReason shutdownReason2, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        shutdownReason2 = onConnectionClosing.shutdownReason;
                    }
                    return onConnectionClosing.copy(shutdownReason2);
                }

                @NotNull
                public final ShutdownReason component1() {
                    return this.shutdownReason;
                }

                @NotNull
                public final OnConnectionClosing copy(@NotNull ShutdownReason shutdownReason2) {
                    Intrinsics.checkNotNullParameter(shutdownReason2, "shutdownReason");
                    return new OnConnectionClosing(shutdownReason2);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return (obj instanceof OnConnectionClosing) && Intrinsics.areEqual((Object) this.shutdownReason, (Object) ((OnConnectionClosing) obj).shutdownReason);
                }

                @NotNull
                public final ShutdownReason getShutdownReason() {
                    return this.shutdownReason;
                }

                public int hashCode() {
                    return this.shutdownReason.hashCode();
                }

                @NotNull
                public String toString() {
                    ShutdownReason shutdownReason2 = this.shutdownReason;
                    return "OnConnectionClosing(shutdownReason=" + shutdownReason2 + ")";
                }
            }

            @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Event$OnConnectionFailed;", "Lcom/reown/foundation/network/model/Relay$Model$Event;", "throwable", "", "<init>", "(Ljava/lang/Throwable;)V", "getThrowable", "()Ljava/lang/Throwable;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class OnConnectionFailed extends Event {
                @NotNull
                private final Throwable throwable;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public OnConnectionFailed(@NotNull Throwable th) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(th, "throwable");
                    this.throwable = th;
                }

                public static /* synthetic */ OnConnectionFailed copy$default(OnConnectionFailed onConnectionFailed, Throwable th, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        th = onConnectionFailed.throwable;
                    }
                    return onConnectionFailed.copy(th);
                }

                @NotNull
                public final Throwable component1() {
                    return this.throwable;
                }

                @NotNull
                public final OnConnectionFailed copy(@NotNull Throwable th) {
                    Intrinsics.checkNotNullParameter(th, "throwable");
                    return new OnConnectionFailed(th);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return (obj instanceof OnConnectionFailed) && Intrinsics.areEqual((Object) this.throwable, (Object) ((OnConnectionFailed) obj).throwable);
                }

                @NotNull
                public final Throwable getThrowable() {
                    return this.throwable;
                }

                public int hashCode() {
                    return this.throwable.hashCode();
                }

                @NotNull
                public String toString() {
                    Throwable th = this.throwable;
                    return "OnConnectionFailed(throwable=" + th + ")";
                }
            }

            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\u00020\u0003B\u000f\u0012\u0006\u0010\u0004\u001a\u00028\u0000¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010\n\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\bJ\u001e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0004\u001a\u00028\u0000HÆ\u0001¢\u0006\u0002\u0010\fJ\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0013\u0010\u0004\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Event$OnConnectionOpened;", "WEB_SOCKET", "", "Lcom/reown/foundation/network/model/Relay$Model$Event;", "webSocket", "<init>", "(Ljava/lang/Object;)V", "getWebSocket", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "copy", "(Ljava/lang/Object;)Lcom/reown/foundation/network/model/Relay$Model$Event$OnConnectionOpened;", "equals", "", "other", "hashCode", "", "toString", "", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class OnConnectionOpened<WEB_SOCKET> extends Event {
                @NotNull
                private final WEB_SOCKET webSocket;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public OnConnectionOpened(@NotNull WEB_SOCKET web_socket) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(web_socket, "webSocket");
                    this.webSocket = web_socket;
                }

                public static /* synthetic */ OnConnectionOpened copy$default(OnConnectionOpened onConnectionOpened, WEB_SOCKET web_socket, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        web_socket = onConnectionOpened.webSocket;
                    }
                    return onConnectionOpened.copy(web_socket);
                }

                @NotNull
                public final WEB_SOCKET component1() {
                    return this.webSocket;
                }

                @NotNull
                public final OnConnectionOpened<WEB_SOCKET> copy(@NotNull WEB_SOCKET web_socket) {
                    Intrinsics.checkNotNullParameter(web_socket, "webSocket");
                    return new OnConnectionOpened<>(web_socket);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return (obj instanceof OnConnectionOpened) && Intrinsics.areEqual((Object) this.webSocket, (Object) ((OnConnectionOpened) obj).webSocket);
                }

                @NotNull
                public final WEB_SOCKET getWebSocket() {
                    return this.webSocket;
                }

                public int hashCode() {
                    return this.webSocket.hashCode();
                }

                @NotNull
                public String toString() {
                    WEB_SOCKET web_socket = this.webSocket;
                    return "OnConnectionOpened(webSocket=" + web_socket + ")";
                }
            }

            @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Event$OnMessageReceived;", "Lcom/reown/foundation/network/model/Relay$Model$Event;", "message", "Lcom/reown/foundation/network/model/Relay$Model$Message;", "<init>", "(Lcom/reown/foundation/network/model/Relay$Model$Message;)V", "getMessage", "()Lcom/reown/foundation/network/model/Relay$Model$Message;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class OnMessageReceived extends Event {
                @NotNull
                private final Message message;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public OnMessageReceived(@NotNull Message message2) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(message2, PushMessagingService.KEY_MESSAGE);
                    this.message = message2;
                }

                public static /* synthetic */ OnMessageReceived copy$default(OnMessageReceived onMessageReceived, Message message2, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        message2 = onMessageReceived.message;
                    }
                    return onMessageReceived.copy(message2);
                }

                @NotNull
                public final Message component1() {
                    return this.message;
                }

                @NotNull
                public final OnMessageReceived copy(@NotNull Message message2) {
                    Intrinsics.checkNotNullParameter(message2, PushMessagingService.KEY_MESSAGE);
                    return new OnMessageReceived(message2);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return (obj instanceof OnMessageReceived) && Intrinsics.areEqual((Object) this.message, (Object) ((OnMessageReceived) obj).message);
                }

                @NotNull
                public final Message getMessage() {
                    return this.message;
                }

                public int hashCode() {
                    return this.message.hashCode();
                }

                @NotNull
                public String toString() {
                    Message message2 = this.message;
                    return "OnMessageReceived(message=" + message2 + ")";
                }
            }

            public /* synthetic */ Event(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Event() {
                super((DefaultConstructorMarker) null);
            }
        }

        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u001d\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001Bm\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t\u0012\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0005HÆ\u0003J\u0010\u0010\"\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0016J\u0011\u0010#\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bHÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\tHÆ\u0003J\u0011\u0010%\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bHÆ\u0003J\u0011\u0010&\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bHÆ\u0003J\t\u0010'\u001a\u00020\u000eHÆ\u0003Jz\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\b\b\u0002\u0010\r\u001a\u00020\u000eHÆ\u0001¢\u0006\u0002\u0010)J\u0013\u0010*\u001a\u00020\u000e2\b\u0010+\u001a\u0004\u0018\u00010,HÖ\u0003J\t\u0010-\u001a\u00020\u0003HÖ\u0001J\t\u0010.\u001a\u00020\tHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016R\u0019\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0019\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0019R\u0019\u0010\f\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0019R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f¨\u0006/"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$IrnParams;", "Lcom/reown/foundation/network/model/Relay$Model;", "tag", "", "ttl", "", "correlationId", "rpcMethods", "", "", "chainId", "txHashes", "contractAddresses", "prompt", "", "<init>", "(IJLjava/lang/Long;Ljava/util/List;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Z)V", "getTag", "()I", "getTtl", "()J", "getCorrelationId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getRpcMethods", "()Ljava/util/List;", "getChainId", "()Ljava/lang/String;", "getTxHashes", "getContractAddresses", "getPrompt", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(IJLjava/lang/Long;Ljava/util/List;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Z)Lcom/reown/foundation/network/model/Relay$Model$IrnParams;", "equals", "other", "", "hashCode", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class IrnParams extends Model {
            @Nullable
            private final String chainId;
            @Nullable
            private final List<String> contractAddresses;
            @Nullable
            private final Long correlationId;
            private final boolean prompt;
            @Nullable
            private final List<String> rpcMethods;
            private final int tag;
            private final long ttl;
            @Nullable
            private final List<String> txHashes;

            /* JADX WARNING: Illegal instructions before constructor call */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public /* synthetic */ IrnParams(int r14, long r15, java.lang.Long r17, java.util.List r18, java.lang.String r19, java.util.List r20, java.util.List r21, boolean r22, int r23, kotlin.jvm.internal.DefaultConstructorMarker r24) {
                /*
                    r13 = this;
                    r0 = r23
                    r1 = r0 & 8
                    r2 = 0
                    if (r1 == 0) goto L_0x0009
                    r8 = r2
                    goto L_0x000b
                L_0x0009:
                    r8 = r18
                L_0x000b:
                    r1 = r0 & 16
                    if (r1 == 0) goto L_0x0011
                    r9 = r2
                    goto L_0x0013
                L_0x0011:
                    r9 = r19
                L_0x0013:
                    r1 = r0 & 32
                    if (r1 == 0) goto L_0x0019
                    r10 = r2
                    goto L_0x001b
                L_0x0019:
                    r10 = r20
                L_0x001b:
                    r1 = r0 & 64
                    if (r1 == 0) goto L_0x0021
                    r11 = r2
                    goto L_0x0023
                L_0x0021:
                    r11 = r21
                L_0x0023:
                    r0 = r0 & 128(0x80, float:1.794E-43)
                    if (r0 == 0) goto L_0x002a
                    r0 = 0
                    r12 = r0
                    goto L_0x002c
                L_0x002a:
                    r12 = r22
                L_0x002c:
                    r3 = r13
                    r4 = r14
                    r5 = r15
                    r7 = r17
                    r3.<init>(r4, r5, r7, r8, r9, r10, r11, r12)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.reown.foundation.network.model.Relay.Model.IrnParams.<init>(int, long, java.lang.Long, java.util.List, java.lang.String, java.util.List, java.util.List, boolean, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
            }

            public static /* synthetic */ IrnParams copy$default(IrnParams irnParams, int i3, long j2, Long l2, List list, String str, List list2, List list3, boolean z2, int i4, Object obj) {
                IrnParams irnParams2 = irnParams;
                int i5 = i4;
                return irnParams.copy((i5 & 1) != 0 ? irnParams2.tag : i3, (i5 & 2) != 0 ? irnParams2.ttl : j2, (i5 & 4) != 0 ? irnParams2.correlationId : l2, (i5 & 8) != 0 ? irnParams2.rpcMethods : list, (i5 & 16) != 0 ? irnParams2.chainId : str, (i5 & 32) != 0 ? irnParams2.txHashes : list2, (i5 & 64) != 0 ? irnParams2.contractAddresses : list3, (i5 & 128) != 0 ? irnParams2.prompt : z2);
            }

            public final int component1() {
                return this.tag;
            }

            public final long component2() {
                return this.ttl;
            }

            @Nullable
            public final Long component3() {
                return this.correlationId;
            }

            @Nullable
            public final List<String> component4() {
                return this.rpcMethods;
            }

            @Nullable
            public final String component5() {
                return this.chainId;
            }

            @Nullable
            public final List<String> component6() {
                return this.txHashes;
            }

            @Nullable
            public final List<String> component7() {
                return this.contractAddresses;
            }

            public final boolean component8() {
                return this.prompt;
            }

            @NotNull
            public final IrnParams copy(int i3, long j2, @Nullable Long l2, @Nullable List<String> list, @Nullable String str, @Nullable List<String> list2, @Nullable List<String> list3, boolean z2) {
                return new IrnParams(i3, j2, l2, list, str, list2, list3, z2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof IrnParams)) {
                    return false;
                }
                IrnParams irnParams = (IrnParams) obj;
                return this.tag == irnParams.tag && this.ttl == irnParams.ttl && Intrinsics.areEqual((Object) this.correlationId, (Object) irnParams.correlationId) && Intrinsics.areEqual((Object) this.rpcMethods, (Object) irnParams.rpcMethods) && Intrinsics.areEqual((Object) this.chainId, (Object) irnParams.chainId) && Intrinsics.areEqual((Object) this.txHashes, (Object) irnParams.txHashes) && Intrinsics.areEqual((Object) this.contractAddresses, (Object) irnParams.contractAddresses) && this.prompt == irnParams.prompt;
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

            public final boolean getPrompt() {
                return this.prompt;
            }

            @Nullable
            public final List<String> getRpcMethods() {
                return this.rpcMethods;
            }

            public final int getTag() {
                return this.tag;
            }

            public final long getTtl() {
                return this.ttl;
            }

            @Nullable
            public final List<String> getTxHashes() {
                return this.txHashes;
            }

            public int hashCode() {
                int D2 = a.D(this.ttl, Integer.hashCode(this.tag) * 31, 31);
                Long l2 = this.correlationId;
                int i3 = 0;
                int hashCode = (D2 + (l2 == null ? 0 : l2.hashCode())) * 31;
                List<String> list = this.rpcMethods;
                int hashCode2 = (hashCode + (list == null ? 0 : list.hashCode())) * 31;
                String str = this.chainId;
                int hashCode3 = (hashCode2 + (str == null ? 0 : str.hashCode())) * 31;
                List<String> list2 = this.txHashes;
                int hashCode4 = (hashCode3 + (list2 == null ? 0 : list2.hashCode())) * 31;
                List<String> list3 = this.contractAddresses;
                if (list3 != null) {
                    i3 = list3.hashCode();
                }
                return Boolean.hashCode(this.prompt) + ((hashCode4 + i3) * 31);
            }

            @NotNull
            public String toString() {
                int i3 = this.tag;
                long j2 = this.ttl;
                Long l2 = this.correlationId;
                List<String> list = this.rpcMethods;
                String str = this.chainId;
                List<String> list2 = this.txHashes;
                List<String> list3 = this.contractAddresses;
                boolean z2 = this.prompt;
                return "IrnParams(tag=" + i3 + ", ttl=" + j2 + ", correlationId=" + l2 + ", rpcMethods=" + list + ", chainId=" + str + ", txHashes=" + list2 + ", contractAddresses=" + list3 + ", prompt=" + z2 + ")";
            }

            public IrnParams(int i3, long j2, @Nullable Long l2, @Nullable List<String> list, @Nullable String str, @Nullable List<String> list2, @Nullable List<String> list3, boolean z2) {
                super((DefaultConstructorMarker) null);
                this.tag = i3;
                this.ttl = j2;
                this.correlationId = l2;
                this.rpcMethods = list;
                this.chainId = str;
                this.txHashes = list2;
                this.contractAddresses = list3;
                this.prompt = z2;
            }
        }

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Message;", "Lcom/reown/foundation/network/model/Relay$Model;", "<init>", "()V", "Text", "Bytes", "Lcom/reown/foundation/network/model/Relay$Model$Message$Bytes;", "Lcom/reown/foundation/network/model/Relay$Model$Message$Text;", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static abstract class Message extends Model {

            @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Message$Bytes;", "Lcom/reown/foundation/network/model/Relay$Model$Message;", "value", "", "<init>", "([B)V", "getValue", "()[B", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Bytes extends Message {
                @NotNull
                private final byte[] value;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Bytes(@NotNull byte[] bArr) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(bArr, "value");
                    this.value = bArr;
                }

                @NotNull
                public final byte[] getValue() {
                    return this.value;
                }
            }

            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$Message$Text;", "Lcom/reown/foundation/network/model/Relay$Model$Message;", "value", "", "<init>", "(Ljava/lang/String;)V", "getValue", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Text extends Message {
                @NotNull
                private final String value;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Text(@NotNull String str) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, "value");
                    this.value = str;
                }

                public static /* synthetic */ Text copy$default(Text text, String str, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        str = text.value;
                    }
                    return text.copy(str);
                }

                @NotNull
                public final String component1() {
                    return this.value;
                }

                @NotNull
                public final Text copy(@NotNull String str) {
                    Intrinsics.checkNotNullParameter(str, "value");
                    return new Text(str);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return (obj instanceof Text) && Intrinsics.areEqual((Object) this.value, (Object) ((Text) obj).value);
                }

                @NotNull
                public final String getValue() {
                    return this.value;
                }

                public int hashCode() {
                    return this.value.hashCode();
                }

                @NotNull
                public String toString() {
                    return A.a.l("Text(value=", this.value, ")");
                }
            }

            public /* synthetic */ Message(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Message() {
                super((DefaultConstructorMarker) null);
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/reown/foundation/network/model/Relay$Model$ShutdownReason;", "Lcom/reown/foundation/network/model/Relay$Model;", "code", "", "reason", "", "<init>", "(ILjava/lang/String;)V", "getCode", "()I", "getReason", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class ShutdownReason extends Model {
            private final int code;
            @NotNull
            private final String reason;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public ShutdownReason(int i3, @NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "reason");
                this.code = i3;
                this.reason = str;
            }

            public static /* synthetic */ ShutdownReason copy$default(ShutdownReason shutdownReason, int i3, String str, int i4, Object obj) {
                if ((i4 & 1) != 0) {
                    i3 = shutdownReason.code;
                }
                if ((i4 & 2) != 0) {
                    str = shutdownReason.reason;
                }
                return shutdownReason.copy(i3, str);
            }

            public final int component1() {
                return this.code;
            }

            @NotNull
            public final String component2() {
                return this.reason;
            }

            @NotNull
            public final ShutdownReason copy(int i3, @NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "reason");
                return new ShutdownReason(i3, str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof ShutdownReason)) {
                    return false;
                }
                ShutdownReason shutdownReason = (ShutdownReason) obj;
                return this.code == shutdownReason.code && Intrinsics.areEqual((Object) this.reason, (Object) shutdownReason.reason);
            }

            public final int getCode() {
                return this.code;
            }

            @NotNull
            public final String getReason() {
                return this.reason;
            }

            public int hashCode() {
                return this.reason.hashCode() + (Integer.hashCode(this.code) * 31);
            }

            @NotNull
            public String toString() {
                int i3 = this.code;
                String str = this.reason;
                return "ShutdownReason(code=" + i3 + ", reason=" + str + ")";
            }
        }

        public /* synthetic */ Model(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Model() {
        }
    }

    private Relay() {
    }
}
