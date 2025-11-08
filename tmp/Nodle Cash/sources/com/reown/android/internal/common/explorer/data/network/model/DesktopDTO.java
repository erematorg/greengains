package com.reown.android.internal.common.explorer.data.network.model;

import androidx.camera.camera2.internal.C0118y;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u001f\u0010\f\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/reown/android/internal/common/explorer/data/network/model/DesktopDTO;", "", "native", "", "universal", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getNative", "()Ljava/lang/String;", "getUniversal", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DesktopDTO {
    @NotNull

    /* renamed from: native  reason: not valid java name */
    private final String f70native;
    @Nullable
    private final String universal;

    public DesktopDTO(@NotNull @Json(name = "native") String str, @Nullable @Json(name = "universal") String str2) {
        Intrinsics.checkNotNullParameter(str, "native");
        this.f70native = str;
        this.universal = str2;
    }

    public static /* synthetic */ DesktopDTO copy$default(DesktopDTO desktopDTO, String str, String str2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = desktopDTO.f70native;
        }
        if ((i3 & 2) != 0) {
            str2 = desktopDTO.universal;
        }
        return desktopDTO.copy(str, str2);
    }

    @NotNull
    public final String component1() {
        return this.f70native;
    }

    @Nullable
    public final String component2() {
        return this.universal;
    }

    @NotNull
    public final DesktopDTO copy(@NotNull @Json(name = "native") String str, @Nullable @Json(name = "universal") String str2) {
        Intrinsics.checkNotNullParameter(str, "native");
        return new DesktopDTO(str, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DesktopDTO)) {
            return false;
        }
        DesktopDTO desktopDTO = (DesktopDTO) obj;
        return Intrinsics.areEqual((Object) this.f70native, (Object) desktopDTO.f70native) && Intrinsics.areEqual((Object) this.universal, (Object) desktopDTO.universal);
    }

    @NotNull
    public final String getNative() {
        return this.f70native;
    }

    @Nullable
    public final String getUniversal() {
        return this.universal;
    }

    public int hashCode() {
        int hashCode = this.f70native.hashCode() * 31;
        String str = this.universal;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    @NotNull
    public String toString() {
        return C0118y.g("DesktopDTO(native=", this.f70native, ", universal=", this.universal, ")");
    }
}
