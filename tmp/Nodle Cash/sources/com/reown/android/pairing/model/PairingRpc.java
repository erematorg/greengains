package com.reown.android.pairing.model;

import androidx.compose.animation.core.a;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.reown.android.internal.common.model.type.JsonRpcClientSync;
import com.reown.android.pairing.model.PairingParams;
import com.reown.util.UtilFunctionsKt;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0003\u0010\u0004\u0001\u0002\u0007\b¨\u0006\t"}, d2 = {"Lcom/reown/android/pairing/model/PairingRpc;", "Lcom/reown/android/internal/common/model/type/JsonRpcClientSync;", "Lcom/reown/android/pairing/model/PairingParams;", "<init>", "()V", "PairingDelete", "PairingPing", "Lcom/reown/android/pairing/model/PairingRpc$PairingDelete;", "Lcom/reown/android/pairing/model/PairingRpc$PairingPing;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class PairingRpc implements JsonRpcClientSync<PairingParams> {

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B/\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0003\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0006\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001e"}, d2 = {"Lcom/reown/android/pairing/model/PairingRpc$PairingDelete;", "Lcom/reown/android/pairing/model/PairingRpc;", "id", "", "jsonrpc", "", "method", "params", "Lcom/reown/android/pairing/model/PairingParams$DeleteParams;", "<init>", "(JLjava/lang/String;Ljava/lang/String;Lcom/reown/android/pairing/model/PairingParams$DeleteParams;)V", "getId", "()J", "getJsonrpc", "()Ljava/lang/String;", "getMethod", "getParams", "()Lcom/reown/android/pairing/model/PairingParams$DeleteParams;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class PairingDelete extends PairingRpc {
        private final long id;
        @NotNull
        private final String jsonrpc;
        @NotNull
        private final String method;
        @NotNull
        private final PairingParams.DeleteParams params;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ PairingDelete(long j2, String str, String str2, PairingParams.DeleteParams deleteParams, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? UtilFunctionsKt.generateId() : j2, (i3 & 2) != 0 ? "2.0" : str, (i3 & 4) != 0 ? PairingJsonRpcMethod.WC_PAIRING_DELETE : str2, deleteParams);
        }

        public static /* synthetic */ PairingDelete copy$default(PairingDelete pairingDelete, long j2, String str, String str2, PairingParams.DeleteParams deleteParams, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                j2 = pairingDelete.id;
            }
            long j3 = j2;
            if ((i3 & 2) != 0) {
                str = pairingDelete.jsonrpc;
            }
            String str3 = str;
            if ((i3 & 4) != 0) {
                str2 = pairingDelete.method;
            }
            String str4 = str2;
            if ((i3 & 8) != 0) {
                deleteParams = pairingDelete.params;
            }
            return pairingDelete.copy(j3, str3, str4, deleteParams);
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
        public final PairingParams.DeleteParams component4() {
            return this.params;
        }

        @NotNull
        public final PairingDelete copy(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "method") String str2, @NotNull @Json(name = "params") PairingParams.DeleteParams deleteParams) {
            Intrinsics.checkNotNullParameter(str, "jsonrpc");
            Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
            Intrinsics.checkNotNullParameter(deleteParams, "params");
            return new PairingDelete(j2, str, str2, deleteParams);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof PairingDelete)) {
                return false;
            }
            PairingDelete pairingDelete = (PairingDelete) obj;
            return this.id == pairingDelete.id && Intrinsics.areEqual((Object) this.jsonrpc, (Object) pairingDelete.jsonrpc) && Intrinsics.areEqual((Object) this.method, (Object) pairingDelete.method) && Intrinsics.areEqual((Object) this.params, (Object) pairingDelete.params);
        }

        public long getId() {
            return this.id;
        }

        @NotNull
        public String getJsonrpc() {
            return this.jsonrpc;
        }

        @NotNull
        public String getMethod() {
            return this.method;
        }

        public int hashCode() {
            return this.params.hashCode() + a.i(this.method, a.i(this.jsonrpc, Long.hashCode(this.id) * 31, 31), 31);
        }

        @NotNull
        public String toString() {
            long j2 = this.id;
            String str = this.jsonrpc;
            String str2 = this.method;
            PairingParams.DeleteParams deleteParams = this.params;
            StringBuilder v2 = androidx.work.impl.a.v(j2, "PairingDelete(id=", ", jsonrpc=", str);
            v2.append(", method=");
            v2.append(str2);
            v2.append(", params=");
            v2.append(deleteParams);
            v2.append(")");
            return v2.toString();
        }

        @NotNull
        public PairingParams.DeleteParams getParams() {
            return this.params;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public PairingDelete(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "method") String str2, @NotNull @Json(name = "params") PairingParams.DeleteParams deleteParams) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "jsonrpc");
            Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
            Intrinsics.checkNotNullParameter(deleteParams, "params");
            this.id = j2;
            this.jsonrpc = str;
            this.method = str2;
            this.params = deleteParams;
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B/\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0003\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0006\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001e"}, d2 = {"Lcom/reown/android/pairing/model/PairingRpc$PairingPing;", "Lcom/reown/android/pairing/model/PairingRpc;", "id", "", "jsonrpc", "", "method", "params", "Lcom/reown/android/pairing/model/PairingParams$PingParams;", "<init>", "(JLjava/lang/String;Ljava/lang/String;Lcom/reown/android/pairing/model/PairingParams$PingParams;)V", "getId", "()J", "getJsonrpc", "()Ljava/lang/String;", "getMethod", "getParams", "()Lcom/reown/android/pairing/model/PairingParams$PingParams;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class PairingPing extends PairingRpc {
        private final long id;
        @NotNull
        private final String jsonrpc;
        @NotNull
        private final String method;
        @NotNull
        private final PairingParams.PingParams params;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ PairingPing(long j2, String str, String str2, PairingParams.PingParams pingParams, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? UtilFunctionsKt.generateId() : j2, (i3 & 2) != 0 ? "2.0" : str, (i3 & 4) != 0 ? PairingJsonRpcMethod.WC_PAIRING_PING : str2, pingParams);
        }

        public static /* synthetic */ PairingPing copy$default(PairingPing pairingPing, long j2, String str, String str2, PairingParams.PingParams pingParams, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                j2 = pairingPing.id;
            }
            long j3 = j2;
            if ((i3 & 2) != 0) {
                str = pairingPing.jsonrpc;
            }
            String str3 = str;
            if ((i3 & 4) != 0) {
                str2 = pairingPing.method;
            }
            String str4 = str2;
            if ((i3 & 8) != 0) {
                pingParams = pairingPing.params;
            }
            return pairingPing.copy(j3, str3, str4, pingParams);
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
        public final PairingParams.PingParams component4() {
            return this.params;
        }

        @NotNull
        public final PairingPing copy(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "method") String str2, @NotNull @Json(name = "params") PairingParams.PingParams pingParams) {
            Intrinsics.checkNotNullParameter(str, "jsonrpc");
            Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
            Intrinsics.checkNotNullParameter(pingParams, "params");
            return new PairingPing(j2, str, str2, pingParams);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof PairingPing)) {
                return false;
            }
            PairingPing pairingPing = (PairingPing) obj;
            return this.id == pairingPing.id && Intrinsics.areEqual((Object) this.jsonrpc, (Object) pairingPing.jsonrpc) && Intrinsics.areEqual((Object) this.method, (Object) pairingPing.method) && Intrinsics.areEqual((Object) this.params, (Object) pairingPing.params);
        }

        public long getId() {
            return this.id;
        }

        @NotNull
        public String getJsonrpc() {
            return this.jsonrpc;
        }

        @NotNull
        public String getMethod() {
            return this.method;
        }

        public int hashCode() {
            return this.params.hashCode() + a.i(this.method, a.i(this.jsonrpc, Long.hashCode(this.id) * 31, 31), 31);
        }

        @NotNull
        public String toString() {
            long j2 = this.id;
            String str = this.jsonrpc;
            String str2 = this.method;
            PairingParams.PingParams pingParams = this.params;
            StringBuilder v2 = androidx.work.impl.a.v(j2, "PairingPing(id=", ", jsonrpc=", str);
            v2.append(", method=");
            v2.append(str2);
            v2.append(", params=");
            v2.append(pingParams);
            v2.append(")");
            return v2.toString();
        }

        @NotNull
        public PairingParams.PingParams getParams() {
            return this.params;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public PairingPing(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "method") String str2, @NotNull @Json(name = "params") PairingParams.PingParams pingParams) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "jsonrpc");
            Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
            Intrinsics.checkNotNullParameter(pingParams, "params");
            this.id = j2;
            this.jsonrpc = str;
            this.method = str2;
            this.params = pingParams;
        }
    }

    public /* synthetic */ PairingRpc(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private PairingRpc() {
    }
}
