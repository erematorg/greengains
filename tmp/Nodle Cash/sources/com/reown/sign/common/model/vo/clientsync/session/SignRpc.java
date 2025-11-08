package com.reown.sign.common.model.vo.clientsync.session;

import androidx.compose.animation.core.a;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.reown.android.internal.common.model.type.JsonRpcClientSync;
import com.reown.sign.common.model.vo.clientsync.session.params.SignParams;
import com.reown.sign.json_rpc.model.JsonRpcMethod;
import com.reown.util.UtilFunctionsKt;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\t\u0005\u0006\u0007\b\t\n\u000b\f\rB\t\b\u0004¢\u0006\u0004\b\u0003\u0010\u0004\u0001\t\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016¨\u0006\u0017"}, d2 = {"Lcom/reown/sign/common/model/vo/clientsync/session/SignRpc;", "Lcom/reown/android/internal/common/model/type/JsonRpcClientSync;", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams;", "<init>", "()V", "SessionPropose", "SessionAuthenticate", "SessionSettle", "SessionRequest", "SessionDelete", "SessionPing", "SessionEvent", "SessionUpdate", "SessionExtend", "Lcom/reown/sign/common/model/vo/clientsync/session/SignRpc$SessionAuthenticate;", "Lcom/reown/sign/common/model/vo/clientsync/session/SignRpc$SessionDelete;", "Lcom/reown/sign/common/model/vo/clientsync/session/SignRpc$SessionEvent;", "Lcom/reown/sign/common/model/vo/clientsync/session/SignRpc$SessionExtend;", "Lcom/reown/sign/common/model/vo/clientsync/session/SignRpc$SessionPing;", "Lcom/reown/sign/common/model/vo/clientsync/session/SignRpc$SessionPropose;", "Lcom/reown/sign/common/model/vo/clientsync/session/SignRpc$SessionRequest;", "Lcom/reown/sign/common/model/vo/clientsync/session/SignRpc$SessionSettle;", "Lcom/reown/sign/common/model/vo/clientsync/session/SignRpc$SessionUpdate;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class SignRpc implements JsonRpcClientSync<SignParams> {

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B/\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0003\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0006\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001e"}, d2 = {"Lcom/reown/sign/common/model/vo/clientsync/session/SignRpc$SessionAuthenticate;", "Lcom/reown/sign/common/model/vo/clientsync/session/SignRpc;", "id", "", "jsonrpc", "", "method", "params", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionAuthenticateParams;", "<init>", "(JLjava/lang/String;Ljava/lang/String;Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionAuthenticateParams;)V", "getId", "()J", "getJsonrpc", "()Ljava/lang/String;", "getMethod", "getParams", "()Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionAuthenticateParams;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SessionAuthenticate extends SignRpc {
        private final long id;
        @NotNull
        private final String jsonrpc;
        @NotNull
        private final String method;
        @NotNull
        private final SignParams.SessionAuthenticateParams params;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ SessionAuthenticate(long j2, String str, String str2, SignParams.SessionAuthenticateParams sessionAuthenticateParams, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? UtilFunctionsKt.generateId() : j2, (i3 & 2) != 0 ? "2.0" : str, (i3 & 4) != 0 ? JsonRpcMethod.WC_SESSION_AUTHENTICATE : str2, sessionAuthenticateParams);
        }

        public static /* synthetic */ SessionAuthenticate copy$default(SessionAuthenticate sessionAuthenticate, long j2, String str, String str2, SignParams.SessionAuthenticateParams sessionAuthenticateParams, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                j2 = sessionAuthenticate.id;
            }
            long j3 = j2;
            if ((i3 & 2) != 0) {
                str = sessionAuthenticate.jsonrpc;
            }
            String str3 = str;
            if ((i3 & 4) != 0) {
                str2 = sessionAuthenticate.method;
            }
            String str4 = str2;
            if ((i3 & 8) != 0) {
                sessionAuthenticateParams = sessionAuthenticate.params;
            }
            return sessionAuthenticate.copy(j3, str3, str4, sessionAuthenticateParams);
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
        public final SignParams.SessionAuthenticateParams component4() {
            return this.params;
        }

        @NotNull
        public final SessionAuthenticate copy(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "method") String str2, @NotNull @Json(name = "params") SignParams.SessionAuthenticateParams sessionAuthenticateParams) {
            Intrinsics.checkNotNullParameter(str, "jsonrpc");
            Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
            Intrinsics.checkNotNullParameter(sessionAuthenticateParams, "params");
            return new SessionAuthenticate(j2, str, str2, sessionAuthenticateParams);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SessionAuthenticate)) {
                return false;
            }
            SessionAuthenticate sessionAuthenticate = (SessionAuthenticate) obj;
            return this.id == sessionAuthenticate.id && Intrinsics.areEqual((Object) this.jsonrpc, (Object) sessionAuthenticate.jsonrpc) && Intrinsics.areEqual((Object) this.method, (Object) sessionAuthenticate.method) && Intrinsics.areEqual((Object) this.params, (Object) sessionAuthenticate.params);
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
            SignParams.SessionAuthenticateParams sessionAuthenticateParams = this.params;
            StringBuilder v2 = androidx.work.impl.a.v(j2, "SessionAuthenticate(id=", ", jsonrpc=", str);
            v2.append(", method=");
            v2.append(str2);
            v2.append(", params=");
            v2.append(sessionAuthenticateParams);
            v2.append(")");
            return v2.toString();
        }

        @NotNull
        public SignParams.SessionAuthenticateParams getParams() {
            return this.params;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SessionAuthenticate(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "method") String str2, @NotNull @Json(name = "params") SignParams.SessionAuthenticateParams sessionAuthenticateParams) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "jsonrpc");
            Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
            Intrinsics.checkNotNullParameter(sessionAuthenticateParams, "params");
            this.id = j2;
            this.jsonrpc = str;
            this.method = str2;
            this.params = sessionAuthenticateParams;
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B/\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0003\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0006\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001e"}, d2 = {"Lcom/reown/sign/common/model/vo/clientsync/session/SignRpc$SessionDelete;", "Lcom/reown/sign/common/model/vo/clientsync/session/SignRpc;", "id", "", "jsonrpc", "", "method", "params", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$DeleteParams;", "<init>", "(JLjava/lang/String;Ljava/lang/String;Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$DeleteParams;)V", "getId", "()J", "getJsonrpc", "()Ljava/lang/String;", "getMethod", "getParams", "()Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$DeleteParams;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SessionDelete extends SignRpc {
        private final long id;
        @NotNull
        private final String jsonrpc;
        @NotNull
        private final String method;
        @NotNull
        private final SignParams.DeleteParams params;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ SessionDelete(long j2, String str, String str2, SignParams.DeleteParams deleteParams, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? UtilFunctionsKt.generateId() : j2, (i3 & 2) != 0 ? "2.0" : str, (i3 & 4) != 0 ? JsonRpcMethod.WC_SESSION_DELETE : str2, deleteParams);
        }

        public static /* synthetic */ SessionDelete copy$default(SessionDelete sessionDelete, long j2, String str, String str2, SignParams.DeleteParams deleteParams, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                j2 = sessionDelete.id;
            }
            long j3 = j2;
            if ((i3 & 2) != 0) {
                str = sessionDelete.jsonrpc;
            }
            String str3 = str;
            if ((i3 & 4) != 0) {
                str2 = sessionDelete.method;
            }
            String str4 = str2;
            if ((i3 & 8) != 0) {
                deleteParams = sessionDelete.params;
            }
            return sessionDelete.copy(j3, str3, str4, deleteParams);
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
        public final SignParams.DeleteParams component4() {
            return this.params;
        }

        @NotNull
        public final SessionDelete copy(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "method") String str2, @NotNull @Json(name = "params") SignParams.DeleteParams deleteParams) {
            Intrinsics.checkNotNullParameter(str, "jsonrpc");
            Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
            Intrinsics.checkNotNullParameter(deleteParams, "params");
            return new SessionDelete(j2, str, str2, deleteParams);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SessionDelete)) {
                return false;
            }
            SessionDelete sessionDelete = (SessionDelete) obj;
            return this.id == sessionDelete.id && Intrinsics.areEqual((Object) this.jsonrpc, (Object) sessionDelete.jsonrpc) && Intrinsics.areEqual((Object) this.method, (Object) sessionDelete.method) && Intrinsics.areEqual((Object) this.params, (Object) sessionDelete.params);
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
            SignParams.DeleteParams deleteParams = this.params;
            StringBuilder v2 = androidx.work.impl.a.v(j2, "SessionDelete(id=", ", jsonrpc=", str);
            v2.append(", method=");
            v2.append(str2);
            v2.append(", params=");
            v2.append(deleteParams);
            v2.append(")");
            return v2.toString();
        }

        @NotNull
        public SignParams.DeleteParams getParams() {
            return this.params;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SessionDelete(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "method") String str2, @NotNull @Json(name = "params") SignParams.DeleteParams deleteParams) {
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
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B/\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0003\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0006\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001e"}, d2 = {"Lcom/reown/sign/common/model/vo/clientsync/session/SignRpc$SessionEvent;", "Lcom/reown/sign/common/model/vo/clientsync/session/SignRpc;", "id", "", "jsonrpc", "", "method", "params", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$EventParams;", "<init>", "(JLjava/lang/String;Ljava/lang/String;Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$EventParams;)V", "getId", "()J", "getJsonrpc", "()Ljava/lang/String;", "getMethod", "getParams", "()Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$EventParams;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SessionEvent extends SignRpc {
        private final long id;
        @NotNull
        private final String jsonrpc;
        @NotNull
        private final String method;
        @NotNull
        private final SignParams.EventParams params;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ SessionEvent(long j2, String str, String str2, SignParams.EventParams eventParams, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? UtilFunctionsKt.generateId() : j2, (i3 & 2) != 0 ? "2.0" : str, (i3 & 4) != 0 ? JsonRpcMethod.WC_SESSION_EVENT : str2, eventParams);
        }

        public static /* synthetic */ SessionEvent copy$default(SessionEvent sessionEvent, long j2, String str, String str2, SignParams.EventParams eventParams, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                j2 = sessionEvent.id;
            }
            long j3 = j2;
            if ((i3 & 2) != 0) {
                str = sessionEvent.jsonrpc;
            }
            String str3 = str;
            if ((i3 & 4) != 0) {
                str2 = sessionEvent.method;
            }
            String str4 = str2;
            if ((i3 & 8) != 0) {
                eventParams = sessionEvent.params;
            }
            return sessionEvent.copy(j3, str3, str4, eventParams);
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
        public final SignParams.EventParams component4() {
            return this.params;
        }

        @NotNull
        public final SessionEvent copy(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "method") String str2, @NotNull @Json(name = "params") SignParams.EventParams eventParams) {
            Intrinsics.checkNotNullParameter(str, "jsonrpc");
            Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
            Intrinsics.checkNotNullParameter(eventParams, "params");
            return new SessionEvent(j2, str, str2, eventParams);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SessionEvent)) {
                return false;
            }
            SessionEvent sessionEvent = (SessionEvent) obj;
            return this.id == sessionEvent.id && Intrinsics.areEqual((Object) this.jsonrpc, (Object) sessionEvent.jsonrpc) && Intrinsics.areEqual((Object) this.method, (Object) sessionEvent.method) && Intrinsics.areEqual((Object) this.params, (Object) sessionEvent.params);
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
            SignParams.EventParams eventParams = this.params;
            StringBuilder v2 = androidx.work.impl.a.v(j2, "SessionEvent(id=", ", jsonrpc=", str);
            v2.append(", method=");
            v2.append(str2);
            v2.append(", params=");
            v2.append(eventParams);
            v2.append(")");
            return v2.toString();
        }

        @NotNull
        public SignParams.EventParams getParams() {
            return this.params;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SessionEvent(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "method") String str2, @NotNull @Json(name = "params") SignParams.EventParams eventParams) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "jsonrpc");
            Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
            Intrinsics.checkNotNullParameter(eventParams, "params");
            this.id = j2;
            this.jsonrpc = str;
            this.method = str2;
            this.params = eventParams;
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B/\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0003\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0006\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001e"}, d2 = {"Lcom/reown/sign/common/model/vo/clientsync/session/SignRpc$SessionExtend;", "Lcom/reown/sign/common/model/vo/clientsync/session/SignRpc;", "id", "", "jsonrpc", "", "method", "params", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$ExtendParams;", "<init>", "(JLjava/lang/String;Ljava/lang/String;Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$ExtendParams;)V", "getId", "()J", "getJsonrpc", "()Ljava/lang/String;", "getMethod", "getParams", "()Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$ExtendParams;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SessionExtend extends SignRpc {
        private final long id;
        @NotNull
        private final String jsonrpc;
        @NotNull
        private final String method;
        @NotNull
        private final SignParams.ExtendParams params;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ SessionExtend(long j2, String str, String str2, SignParams.ExtendParams extendParams, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? UtilFunctionsKt.generateId() : j2, (i3 & 2) != 0 ? "2.0" : str, (i3 & 4) != 0 ? JsonRpcMethod.WC_SESSION_EXTEND : str2, extendParams);
        }

        public static /* synthetic */ SessionExtend copy$default(SessionExtend sessionExtend, long j2, String str, String str2, SignParams.ExtendParams extendParams, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                j2 = sessionExtend.id;
            }
            long j3 = j2;
            if ((i3 & 2) != 0) {
                str = sessionExtend.jsonrpc;
            }
            String str3 = str;
            if ((i3 & 4) != 0) {
                str2 = sessionExtend.method;
            }
            String str4 = str2;
            if ((i3 & 8) != 0) {
                extendParams = sessionExtend.params;
            }
            return sessionExtend.copy(j3, str3, str4, extendParams);
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
        public final SignParams.ExtendParams component4() {
            return this.params;
        }

        @NotNull
        public final SessionExtend copy(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "method") String str2, @NotNull @Json(name = "params") SignParams.ExtendParams extendParams) {
            Intrinsics.checkNotNullParameter(str, "jsonrpc");
            Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
            Intrinsics.checkNotNullParameter(extendParams, "params");
            return new SessionExtend(j2, str, str2, extendParams);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SessionExtend)) {
                return false;
            }
            SessionExtend sessionExtend = (SessionExtend) obj;
            return this.id == sessionExtend.id && Intrinsics.areEqual((Object) this.jsonrpc, (Object) sessionExtend.jsonrpc) && Intrinsics.areEqual((Object) this.method, (Object) sessionExtend.method) && Intrinsics.areEqual((Object) this.params, (Object) sessionExtend.params);
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
            SignParams.ExtendParams extendParams = this.params;
            StringBuilder v2 = androidx.work.impl.a.v(j2, "SessionExtend(id=", ", jsonrpc=", str);
            v2.append(", method=");
            v2.append(str2);
            v2.append(", params=");
            v2.append(extendParams);
            v2.append(")");
            return v2.toString();
        }

        @NotNull
        public SignParams.ExtendParams getParams() {
            return this.params;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SessionExtend(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "method") String str2, @NotNull @Json(name = "params") SignParams.ExtendParams extendParams) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "jsonrpc");
            Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
            Intrinsics.checkNotNullParameter(extendParams, "params");
            this.id = j2;
            this.jsonrpc = str;
            this.method = str2;
            this.params = extendParams;
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B/\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0003\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0006\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001e"}, d2 = {"Lcom/reown/sign/common/model/vo/clientsync/session/SignRpc$SessionPing;", "Lcom/reown/sign/common/model/vo/clientsync/session/SignRpc;", "id", "", "jsonrpc", "", "method", "params", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$PingParams;", "<init>", "(JLjava/lang/String;Ljava/lang/String;Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$PingParams;)V", "getId", "()J", "getJsonrpc", "()Ljava/lang/String;", "getMethod", "getParams", "()Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$PingParams;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SessionPing extends SignRpc {
        private final long id;
        @NotNull
        private final String jsonrpc;
        @NotNull
        private final String method;
        @NotNull
        private final SignParams.PingParams params;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ SessionPing(long j2, String str, String str2, SignParams.PingParams pingParams, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? UtilFunctionsKt.generateId() : j2, (i3 & 2) != 0 ? "2.0" : str, (i3 & 4) != 0 ? JsonRpcMethod.WC_SESSION_PING : str2, pingParams);
        }

        public static /* synthetic */ SessionPing copy$default(SessionPing sessionPing, long j2, String str, String str2, SignParams.PingParams pingParams, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                j2 = sessionPing.id;
            }
            long j3 = j2;
            if ((i3 & 2) != 0) {
                str = sessionPing.jsonrpc;
            }
            String str3 = str;
            if ((i3 & 4) != 0) {
                str2 = sessionPing.method;
            }
            String str4 = str2;
            if ((i3 & 8) != 0) {
                pingParams = sessionPing.params;
            }
            return sessionPing.copy(j3, str3, str4, pingParams);
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
        public final SignParams.PingParams component4() {
            return this.params;
        }

        @NotNull
        public final SessionPing copy(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "method") String str2, @NotNull @Json(name = "params") SignParams.PingParams pingParams) {
            Intrinsics.checkNotNullParameter(str, "jsonrpc");
            Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
            Intrinsics.checkNotNullParameter(pingParams, "params");
            return new SessionPing(j2, str, str2, pingParams);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SessionPing)) {
                return false;
            }
            SessionPing sessionPing = (SessionPing) obj;
            return this.id == sessionPing.id && Intrinsics.areEqual((Object) this.jsonrpc, (Object) sessionPing.jsonrpc) && Intrinsics.areEqual((Object) this.method, (Object) sessionPing.method) && Intrinsics.areEqual((Object) this.params, (Object) sessionPing.params);
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
            SignParams.PingParams pingParams = this.params;
            StringBuilder v2 = androidx.work.impl.a.v(j2, "SessionPing(id=", ", jsonrpc=", str);
            v2.append(", method=");
            v2.append(str2);
            v2.append(", params=");
            v2.append(pingParams);
            v2.append(")");
            return v2.toString();
        }

        @NotNull
        public SignParams.PingParams getParams() {
            return this.params;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SessionPing(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "method") String str2, @NotNull @Json(name = "params") SignParams.PingParams pingParams) {
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

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B/\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0003\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0006\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001e"}, d2 = {"Lcom/reown/sign/common/model/vo/clientsync/session/SignRpc$SessionPropose;", "Lcom/reown/sign/common/model/vo/clientsync/session/SignRpc;", "id", "", "jsonrpc", "", "method", "params", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionProposeParams;", "<init>", "(JLjava/lang/String;Ljava/lang/String;Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionProposeParams;)V", "getId", "()J", "getJsonrpc", "()Ljava/lang/String;", "getMethod", "getParams", "()Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionProposeParams;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SessionPropose extends SignRpc {
        private final long id;
        @NotNull
        private final String jsonrpc;
        @NotNull
        private final String method;
        @NotNull
        private final SignParams.SessionProposeParams params;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ SessionPropose(long j2, String str, String str2, SignParams.SessionProposeParams sessionProposeParams, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? UtilFunctionsKt.generateId() : j2, (i3 & 2) != 0 ? "2.0" : str, (i3 & 4) != 0 ? JsonRpcMethod.WC_SESSION_PROPOSE : str2, sessionProposeParams);
        }

        public static /* synthetic */ SessionPropose copy$default(SessionPropose sessionPropose, long j2, String str, String str2, SignParams.SessionProposeParams sessionProposeParams, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                j2 = sessionPropose.id;
            }
            long j3 = j2;
            if ((i3 & 2) != 0) {
                str = sessionPropose.jsonrpc;
            }
            String str3 = str;
            if ((i3 & 4) != 0) {
                str2 = sessionPropose.method;
            }
            String str4 = str2;
            if ((i3 & 8) != 0) {
                sessionProposeParams = sessionPropose.params;
            }
            return sessionPropose.copy(j3, str3, str4, sessionProposeParams);
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
        public final SignParams.SessionProposeParams component4() {
            return this.params;
        }

        @NotNull
        public final SessionPropose copy(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "method") String str2, @NotNull @Json(name = "params") SignParams.SessionProposeParams sessionProposeParams) {
            Intrinsics.checkNotNullParameter(str, "jsonrpc");
            Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
            Intrinsics.checkNotNullParameter(sessionProposeParams, "params");
            return new SessionPropose(j2, str, str2, sessionProposeParams);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SessionPropose)) {
                return false;
            }
            SessionPropose sessionPropose = (SessionPropose) obj;
            return this.id == sessionPropose.id && Intrinsics.areEqual((Object) this.jsonrpc, (Object) sessionPropose.jsonrpc) && Intrinsics.areEqual((Object) this.method, (Object) sessionPropose.method) && Intrinsics.areEqual((Object) this.params, (Object) sessionPropose.params);
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
            SignParams.SessionProposeParams sessionProposeParams = this.params;
            StringBuilder v2 = androidx.work.impl.a.v(j2, "SessionPropose(id=", ", jsonrpc=", str);
            v2.append(", method=");
            v2.append(str2);
            v2.append(", params=");
            v2.append(sessionProposeParams);
            v2.append(")");
            return v2.toString();
        }

        @NotNull
        public SignParams.SessionProposeParams getParams() {
            return this.params;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SessionPropose(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "method") String str2, @NotNull @Json(name = "params") SignParams.SessionProposeParams sessionProposeParams) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "jsonrpc");
            Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
            Intrinsics.checkNotNullParameter(sessionProposeParams, "params");
            this.id = j2;
            this.jsonrpc = str;
            this.method = str2;
            this.params = sessionProposeParams;
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B/\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0003\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\bHÆ\u0003J1\u0010\u001a\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eHÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\t\u0010!\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0006\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0011\u0010\u0014\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000e¨\u0006\""}, d2 = {"Lcom/reown/sign/common/model/vo/clientsync/session/SignRpc$SessionRequest;", "Lcom/reown/sign/common/model/vo/clientsync/session/SignRpc;", "id", "", "jsonrpc", "", "method", "params", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionRequestParams;", "<init>", "(JLjava/lang/String;Ljava/lang/String;Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionRequestParams;)V", "getId", "()J", "getJsonrpc", "()Ljava/lang/String;", "getMethod", "getParams", "()Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionRequestParams;", "rpcMethod", "getRpcMethod", "rpcParams", "getRpcParams", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SessionRequest extends SignRpc {
        private final long id;
        @NotNull
        private final String jsonrpc;
        @NotNull
        private final String method;
        @NotNull
        private final SignParams.SessionRequestParams params;
        @NotNull
        private final String rpcMethod;
        @NotNull
        private final String rpcParams;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ SessionRequest(long j2, String str, String str2, SignParams.SessionRequestParams sessionRequestParams, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? UtilFunctionsKt.generateId() : j2, (i3 & 2) != 0 ? "2.0" : str, (i3 & 4) != 0 ? JsonRpcMethod.WC_SESSION_REQUEST : str2, sessionRequestParams);
        }

        public static /* synthetic */ SessionRequest copy$default(SessionRequest sessionRequest, long j2, String str, String str2, SignParams.SessionRequestParams sessionRequestParams, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                j2 = sessionRequest.id;
            }
            long j3 = j2;
            if ((i3 & 2) != 0) {
                str = sessionRequest.jsonrpc;
            }
            String str3 = str;
            if ((i3 & 4) != 0) {
                str2 = sessionRequest.method;
            }
            String str4 = str2;
            if ((i3 & 8) != 0) {
                sessionRequestParams = sessionRequest.params;
            }
            return sessionRequest.copy(j3, str3, str4, sessionRequestParams);
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
        public final SignParams.SessionRequestParams component4() {
            return this.params;
        }

        @NotNull
        public final SessionRequest copy(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "method") String str2, @NotNull @Json(name = "params") SignParams.SessionRequestParams sessionRequestParams) {
            Intrinsics.checkNotNullParameter(str, "jsonrpc");
            Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
            Intrinsics.checkNotNullParameter(sessionRequestParams, "params");
            return new SessionRequest(j2, str, str2, sessionRequestParams);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SessionRequest)) {
                return false;
            }
            SessionRequest sessionRequest = (SessionRequest) obj;
            return this.id == sessionRequest.id && Intrinsics.areEqual((Object) this.jsonrpc, (Object) sessionRequest.jsonrpc) && Intrinsics.areEqual((Object) this.method, (Object) sessionRequest.method) && Intrinsics.areEqual((Object) this.params, (Object) sessionRequest.params);
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

        @NotNull
        public final String getRpcMethod() {
            return this.rpcMethod;
        }

        @NotNull
        public final String getRpcParams() {
            return this.rpcParams;
        }

        public int hashCode() {
            return this.params.hashCode() + a.i(this.method, a.i(this.jsonrpc, Long.hashCode(this.id) * 31, 31), 31);
        }

        @NotNull
        public String toString() {
            long j2 = this.id;
            String str = this.jsonrpc;
            String str2 = this.method;
            SignParams.SessionRequestParams sessionRequestParams = this.params;
            StringBuilder v2 = androidx.work.impl.a.v(j2, "SessionRequest(id=", ", jsonrpc=", str);
            v2.append(", method=");
            v2.append(str2);
            v2.append(", params=");
            v2.append(sessionRequestParams);
            v2.append(")");
            return v2.toString();
        }

        @NotNull
        public SignParams.SessionRequestParams getParams() {
            return this.params;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SessionRequest(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "method") String str2, @NotNull @Json(name = "params") SignParams.SessionRequestParams sessionRequestParams) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "jsonrpc");
            Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
            Intrinsics.checkNotNullParameter(sessionRequestParams, "params");
            this.id = j2;
            this.jsonrpc = str;
            this.method = str2;
            this.params = sessionRequestParams;
            this.rpcMethod = getParams().getRequest().getMethod();
            this.rpcParams = getParams().getRequest().getParams();
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B/\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0003\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0006\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001e"}, d2 = {"Lcom/reown/sign/common/model/vo/clientsync/session/SignRpc$SessionSettle;", "Lcom/reown/sign/common/model/vo/clientsync/session/SignRpc;", "id", "", "jsonrpc", "", "method", "params", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionSettleParams;", "<init>", "(JLjava/lang/String;Ljava/lang/String;Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionSettleParams;)V", "getId", "()J", "getJsonrpc", "()Ljava/lang/String;", "getMethod", "getParams", "()Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$SessionSettleParams;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SessionSettle extends SignRpc {
        private final long id;
        @NotNull
        private final String jsonrpc;
        @NotNull
        private final String method;
        @NotNull
        private final SignParams.SessionSettleParams params;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ SessionSettle(long j2, String str, String str2, SignParams.SessionSettleParams sessionSettleParams, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? UtilFunctionsKt.generateId() : j2, (i3 & 2) != 0 ? "2.0" : str, (i3 & 4) != 0 ? JsonRpcMethod.WC_SESSION_SETTLE : str2, sessionSettleParams);
        }

        public static /* synthetic */ SessionSettle copy$default(SessionSettle sessionSettle, long j2, String str, String str2, SignParams.SessionSettleParams sessionSettleParams, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                j2 = sessionSettle.id;
            }
            long j3 = j2;
            if ((i3 & 2) != 0) {
                str = sessionSettle.jsonrpc;
            }
            String str3 = str;
            if ((i3 & 4) != 0) {
                str2 = sessionSettle.method;
            }
            String str4 = str2;
            if ((i3 & 8) != 0) {
                sessionSettleParams = sessionSettle.params;
            }
            return sessionSettle.copy(j3, str3, str4, sessionSettleParams);
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
        public final SignParams.SessionSettleParams component4() {
            return this.params;
        }

        @NotNull
        public final SessionSettle copy(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "method") String str2, @NotNull @Json(name = "params") SignParams.SessionSettleParams sessionSettleParams) {
            Intrinsics.checkNotNullParameter(str, "jsonrpc");
            Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
            Intrinsics.checkNotNullParameter(sessionSettleParams, "params");
            return new SessionSettle(j2, str, str2, sessionSettleParams);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SessionSettle)) {
                return false;
            }
            SessionSettle sessionSettle = (SessionSettle) obj;
            return this.id == sessionSettle.id && Intrinsics.areEqual((Object) this.jsonrpc, (Object) sessionSettle.jsonrpc) && Intrinsics.areEqual((Object) this.method, (Object) sessionSettle.method) && Intrinsics.areEqual((Object) this.params, (Object) sessionSettle.params);
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
            SignParams.SessionSettleParams sessionSettleParams = this.params;
            StringBuilder v2 = androidx.work.impl.a.v(j2, "SessionSettle(id=", ", jsonrpc=", str);
            v2.append(", method=");
            v2.append(str2);
            v2.append(", params=");
            v2.append(sessionSettleParams);
            v2.append(")");
            return v2.toString();
        }

        @NotNull
        public SignParams.SessionSettleParams getParams() {
            return this.params;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SessionSettle(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "method") String str2, @NotNull @Json(name = "params") SignParams.SessionSettleParams sessionSettleParams) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "jsonrpc");
            Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
            Intrinsics.checkNotNullParameter(sessionSettleParams, "params");
            this.id = j2;
            this.jsonrpc = str;
            this.method = str2;
            this.params = sessionSettleParams;
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B/\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0003\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0006\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001e"}, d2 = {"Lcom/reown/sign/common/model/vo/clientsync/session/SignRpc$SessionUpdate;", "Lcom/reown/sign/common/model/vo/clientsync/session/SignRpc;", "id", "", "jsonrpc", "", "method", "params", "Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$UpdateNamespacesParams;", "<init>", "(JLjava/lang/String;Ljava/lang/String;Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$UpdateNamespacesParams;)V", "getId", "()J", "getJsonrpc", "()Ljava/lang/String;", "getMethod", "getParams", "()Lcom/reown/sign/common/model/vo/clientsync/session/params/SignParams$UpdateNamespacesParams;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SessionUpdate extends SignRpc {
        private final long id;
        @NotNull
        private final String jsonrpc;
        @NotNull
        private final String method;
        @NotNull
        private final SignParams.UpdateNamespacesParams params;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ SessionUpdate(long j2, String str, String str2, SignParams.UpdateNamespacesParams updateNamespacesParams, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? UtilFunctionsKt.generateId() : j2, (i3 & 2) != 0 ? "2.0" : str, (i3 & 4) != 0 ? JsonRpcMethod.WC_SESSION_UPDATE : str2, updateNamespacesParams);
        }

        public static /* synthetic */ SessionUpdate copy$default(SessionUpdate sessionUpdate, long j2, String str, String str2, SignParams.UpdateNamespacesParams updateNamespacesParams, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                j2 = sessionUpdate.id;
            }
            long j3 = j2;
            if ((i3 & 2) != 0) {
                str = sessionUpdate.jsonrpc;
            }
            String str3 = str;
            if ((i3 & 4) != 0) {
                str2 = sessionUpdate.method;
            }
            String str4 = str2;
            if ((i3 & 8) != 0) {
                updateNamespacesParams = sessionUpdate.params;
            }
            return sessionUpdate.copy(j3, str3, str4, updateNamespacesParams);
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
        public final SignParams.UpdateNamespacesParams component4() {
            return this.params;
        }

        @NotNull
        public final SessionUpdate copy(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "method") String str2, @NotNull @Json(name = "params") SignParams.UpdateNamespacesParams updateNamespacesParams) {
            Intrinsics.checkNotNullParameter(str, "jsonrpc");
            Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
            Intrinsics.checkNotNullParameter(updateNamespacesParams, "params");
            return new SessionUpdate(j2, str, str2, updateNamespacesParams);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SessionUpdate)) {
                return false;
            }
            SessionUpdate sessionUpdate = (SessionUpdate) obj;
            return this.id == sessionUpdate.id && Intrinsics.areEqual((Object) this.jsonrpc, (Object) sessionUpdate.jsonrpc) && Intrinsics.areEqual((Object) this.method, (Object) sessionUpdate.method) && Intrinsics.areEqual((Object) this.params, (Object) sessionUpdate.params);
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
            SignParams.UpdateNamespacesParams updateNamespacesParams = this.params;
            StringBuilder v2 = androidx.work.impl.a.v(j2, "SessionUpdate(id=", ", jsonrpc=", str);
            v2.append(", method=");
            v2.append(str2);
            v2.append(", params=");
            v2.append(updateNamespacesParams);
            v2.append(")");
            return v2.toString();
        }

        @NotNull
        public SignParams.UpdateNamespacesParams getParams() {
            return this.params;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SessionUpdate(@Json(name = "id") long j2, @NotNull @Json(name = "jsonrpc") String str, @NotNull @Json(name = "method") String str2, @NotNull @Json(name = "params") SignParams.UpdateNamespacesParams updateNamespacesParams) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "jsonrpc");
            Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
            Intrinsics.checkNotNullParameter(updateNamespacesParams, "params");
            this.id = j2;
            this.jsonrpc = str;
            this.method = str2;
            this.params = updateNamespacesParams;
        }
    }

    public /* synthetic */ SignRpc(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private SignRpc() {
    }
}
