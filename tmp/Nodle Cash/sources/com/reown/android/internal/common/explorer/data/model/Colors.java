package com.reown.android.internal.common.explorer.data.model;

import androidx.camera.camera2.internal.C0118y;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/reown/android/internal/common/explorer/data/model/Colors;", "", "primary", "", "secondary", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getPrimary", "()Ljava/lang/String;", "getSecondary", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Colors {
    @Nullable
    private final String primary;
    @Nullable
    private final String secondary;

    public Colors(@Nullable String str, @Nullable String str2) {
        this.primary = str;
        this.secondary = str2;
    }

    public static /* synthetic */ Colors copy$default(Colors colors, String str, String str2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = colors.primary;
        }
        if ((i3 & 2) != 0) {
            str2 = colors.secondary;
        }
        return colors.copy(str, str2);
    }

    @Nullable
    public final String component1() {
        return this.primary;
    }

    @Nullable
    public final String component2() {
        return this.secondary;
    }

    @NotNull
    public final Colors copy(@Nullable String str, @Nullable String str2) {
        return new Colors(str, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Colors)) {
            return false;
        }
        Colors colors = (Colors) obj;
        return Intrinsics.areEqual((Object) this.primary, (Object) colors.primary) && Intrinsics.areEqual((Object) this.secondary, (Object) colors.secondary);
    }

    @Nullable
    public final String getPrimary() {
        return this.primary;
    }

    @Nullable
    public final String getSecondary() {
        return this.secondary;
    }

    public int hashCode() {
        String str = this.primary;
        int i3 = 0;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.secondary;
        if (str2 != null) {
            i3 = str2.hashCode();
        }
        return hashCode + i3;
    }

    @NotNull
    public String toString() {
        return C0118y.g("Colors(primary=", this.primary, ", secondary=", this.secondary, ")");
    }
}
