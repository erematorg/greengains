package com.reown.android.internal.common.model.sync;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/reown/android/internal/common/model/sync/ClientJsonRpc;", "", "id", "", "jsonrpc", "", "method", "<init>", "(JLjava/lang/String;Ljava/lang/String;)V", "getId", "()J", "getJsonrpc", "()Ljava/lang/String;", "getMethod", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ClientJsonRpc {
    private final long id;
    @NotNull
    private final String jsonrpc;
    @NotNull
    private final String method;

    public ClientJsonRpc(long j2, @NotNull String str, @NotNull String str2) {
        Intrinsics.checkNotNullParameter(str, "jsonrpc");
        Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
        this.id = j2;
        this.jsonrpc = str;
        this.method = str2;
    }

    public static /* synthetic */ ClientJsonRpc copy$default(ClientJsonRpc clientJsonRpc, long j2, String str, String str2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            j2 = clientJsonRpc.id;
        }
        if ((i3 & 2) != 0) {
            str = clientJsonRpc.jsonrpc;
        }
        if ((i3 & 4) != 0) {
            str2 = clientJsonRpc.method;
        }
        return clientJsonRpc.copy(j2, str, str2);
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
    public final ClientJsonRpc copy(long j2, @NotNull String str, @NotNull String str2) {
        Intrinsics.checkNotNullParameter(str, "jsonrpc");
        Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
        return new ClientJsonRpc(j2, str, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ClientJsonRpc)) {
            return false;
        }
        ClientJsonRpc clientJsonRpc = (ClientJsonRpc) obj;
        return this.id == clientJsonRpc.id && Intrinsics.areEqual((Object) this.jsonrpc, (Object) clientJsonRpc.jsonrpc) && Intrinsics.areEqual((Object) this.method, (Object) clientJsonRpc.method);
    }

    public final long getId() {
        return this.id;
    }

    @NotNull
    public final String getJsonrpc() {
        return this.jsonrpc;
    }

    @NotNull
    public final String getMethod() {
        return this.method;
    }

    public int hashCode() {
        return this.method.hashCode() + a.i(this.jsonrpc, Long.hashCode(this.id) * 31, 31);
    }

    @NotNull
    public String toString() {
        long j2 = this.id;
        String str = this.jsonrpc;
        return C0118y.j(androidx.work.impl.a.v(j2, "ClientJsonRpc(id=", ", jsonrpc=", str), ", method=", this.method, ")");
    }
}
