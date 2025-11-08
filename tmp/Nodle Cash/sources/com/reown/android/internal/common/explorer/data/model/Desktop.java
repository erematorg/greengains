package com.reown.android.internal.common.explorer.data.model;

import androidx.camera.camera2.internal.C0118y;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u001f\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/reown/android/internal/common/explorer/data/model/Desktop;", "", "native", "", "universal", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getNative", "()Ljava/lang/String;", "getUniversal", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Desktop {
    @NotNull

    /* renamed from: native  reason: not valid java name */
    private final String f68native;
    @Nullable
    private final String universal;

    public Desktop(@NotNull String str, @Nullable String str2) {
        Intrinsics.checkNotNullParameter(str, "native");
        this.f68native = str;
        this.universal = str2;
    }

    public static /* synthetic */ Desktop copy$default(Desktop desktop, String str, String str2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = desktop.f68native;
        }
        if ((i3 & 2) != 0) {
            str2 = desktop.universal;
        }
        return desktop.copy(str, str2);
    }

    @NotNull
    public final String component1() {
        return this.f68native;
    }

    @Nullable
    public final String component2() {
        return this.universal;
    }

    @NotNull
    public final Desktop copy(@NotNull String str, @Nullable String str2) {
        Intrinsics.checkNotNullParameter(str, "native");
        return new Desktop(str, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Desktop)) {
            return false;
        }
        Desktop desktop = (Desktop) obj;
        return Intrinsics.areEqual((Object) this.f68native, (Object) desktop.f68native) && Intrinsics.areEqual((Object) this.universal, (Object) desktop.universal);
    }

    @NotNull
    public final String getNative() {
        return this.f68native;
    }

    @Nullable
    public final String getUniversal() {
        return this.universal;
    }

    public int hashCode() {
        int hashCode = this.f68native.hashCode() * 31;
        String str = this.universal;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    @NotNull
    public String toString() {
        return C0118y.g("Desktop(native=", this.f68native, ", universal=", this.universal, ")");
    }
}
