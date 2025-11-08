package com.reown.sign.common.model.vo.clientsync.session.payload;

import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonClass(generateAdapter = false)
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0001¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0001HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0001HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/reown/sign/common/model/vo/clientsync/session/payload/SessionEventVO;", "", "name", "", "data", "<init>", "(Ljava/lang/String;Ljava/lang/Object;)V", "getName", "()Ljava/lang/String;", "getData", "()Ljava/lang/Object;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SessionEventVO {
    @NotNull
    private final Object data;
    @NotNull
    private final String name;

    public SessionEventVO(@NotNull @Json(name = "name") String str, @NotNull @Json(name = "data") Object obj) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(obj, "data");
        this.name = str;
        this.data = obj;
    }

    public static /* synthetic */ SessionEventVO copy$default(SessionEventVO sessionEventVO, String str, Object obj, int i3, Object obj2) {
        if ((i3 & 1) != 0) {
            str = sessionEventVO.name;
        }
        if ((i3 & 2) != 0) {
            obj = sessionEventVO.data;
        }
        return sessionEventVO.copy(str, obj);
    }

    @NotNull
    public final String component1() {
        return this.name;
    }

    @NotNull
    public final Object component2() {
        return this.data;
    }

    @NotNull
    public final SessionEventVO copy(@NotNull @Json(name = "name") String str, @NotNull @Json(name = "data") Object obj) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(obj, "data");
        return new SessionEventVO(str, obj);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SessionEventVO)) {
            return false;
        }
        SessionEventVO sessionEventVO = (SessionEventVO) obj;
        return Intrinsics.areEqual((Object) this.name, (Object) sessionEventVO.name) && Intrinsics.areEqual(this.data, sessionEventVO.data);
    }

    @NotNull
    public final Object getData() {
        return this.data;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    public int hashCode() {
        return this.data.hashCode() + (this.name.hashCode() * 31);
    }

    @NotNull
    public String toString() {
        String str = this.name;
        Object obj = this.data;
        return "SessionEventVO(name=" + str + ", data=" + obj + ")";
    }
}
