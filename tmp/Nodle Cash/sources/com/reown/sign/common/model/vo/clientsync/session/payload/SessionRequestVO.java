package com.reown.sign.common.model.vo.clientsync.session.payload;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonClass(generateAdapter = false)
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B'\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\rJ.\u0010\u0012\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0002\u0010\u0013J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\r¨\u0006\u001a"}, d2 = {"Lcom/reown/sign/common/model/vo/clientsync/session/payload/SessionRequestVO;", "", "method", "", "params", "expiryTimestamp", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;)V", "getMethod", "()Ljava/lang/String;", "getParams", "getExpiryTimestamp", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;)Lcom/reown/sign/common/model/vo/clientsync/session/payload/SessionRequestVO;", "equals", "", "other", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SessionRequestVO {
    @Nullable
    private final Long expiryTimestamp;
    @NotNull
    private final String method;
    @NotNull
    private final String params;

    public SessionRequestVO(@NotNull @Json(name = "method") String str, @NotNull @Json(name = "params") String str2, @Nullable @Json(name = "expiryTimestamp") Long l2) {
        Intrinsics.checkNotNullParameter(str, FirebaseAnalytics.Param.METHOD);
        Intrinsics.checkNotNullParameter(str2, "params");
        this.method = str;
        this.params = str2;
        this.expiryTimestamp = l2;
    }

    public static /* synthetic */ SessionRequestVO copy$default(SessionRequestVO sessionRequestVO, String str, String str2, Long l2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = sessionRequestVO.method;
        }
        if ((i3 & 2) != 0) {
            str2 = sessionRequestVO.params;
        }
        if ((i3 & 4) != 0) {
            l2 = sessionRequestVO.expiryTimestamp;
        }
        return sessionRequestVO.copy(str, str2, l2);
    }

    @NotNull
    public final String component1() {
        return this.method;
    }

    @NotNull
    public final String component2() {
        return this.params;
    }

    @Nullable
    public final Long component3() {
        return this.expiryTimestamp;
    }

    @NotNull
    public final SessionRequestVO copy(@NotNull @Json(name = "method") String str, @NotNull @Json(name = "params") String str2, @Nullable @Json(name = "expiryTimestamp") Long l2) {
        Intrinsics.checkNotNullParameter(str, FirebaseAnalytics.Param.METHOD);
        Intrinsics.checkNotNullParameter(str2, "params");
        return new SessionRequestVO(str, str2, l2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SessionRequestVO)) {
            return false;
        }
        SessionRequestVO sessionRequestVO = (SessionRequestVO) obj;
        return Intrinsics.areEqual((Object) this.method, (Object) sessionRequestVO.method) && Intrinsics.areEqual((Object) this.params, (Object) sessionRequestVO.params) && Intrinsics.areEqual((Object) this.expiryTimestamp, (Object) sessionRequestVO.expiryTimestamp);
    }

    @Nullable
    public final Long getExpiryTimestamp() {
        return this.expiryTimestamp;
    }

    @NotNull
    public final String getMethod() {
        return this.method;
    }

    @NotNull
    public final String getParams() {
        return this.params;
    }

    public int hashCode() {
        int i3 = a.i(this.params, this.method.hashCode() * 31, 31);
        Long l2 = this.expiryTimestamp;
        return i3 + (l2 == null ? 0 : l2.hashCode());
    }

    @NotNull
    public String toString() {
        String str = this.method;
        String str2 = this.params;
        Long l2 = this.expiryTimestamp;
        StringBuilder l3 = C0118y.l("SessionRequestVO(method=", str, ", params=", str2, ", expiryTimestamp=");
        l3.append(l2);
        l3.append(")");
        return l3.toString();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SessionRequestVO(String str, String str2, Long l2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i3 & 4) != 0 ? null : l2);
    }
}
