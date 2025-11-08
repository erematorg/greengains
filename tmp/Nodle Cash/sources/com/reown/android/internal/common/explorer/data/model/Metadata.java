package com.reown.android.internal.common.explorer.data.model;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@kotlin.Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001f\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/reown/android/internal/common/explorer/data/model/Metadata;", "", "shortName", "", "colors", "Lcom/reown/android/internal/common/explorer/data/model/Colors;", "<init>", "(Ljava/lang/String;Lcom/reown/android/internal/common/explorer/data/model/Colors;)V", "getShortName", "()Ljava/lang/String;", "getColors", "()Lcom/reown/android/internal/common/explorer/data/model/Colors;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Metadata {
    @NotNull
    private final Colors colors;
    @Nullable
    private final String shortName;

    public Metadata(@Nullable String str, @NotNull Colors colors2) {
        Intrinsics.checkNotNullParameter(colors2, "colors");
        this.shortName = str;
        this.colors = colors2;
    }

    public static /* synthetic */ Metadata copy$default(Metadata metadata, String str, Colors colors2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = metadata.shortName;
        }
        if ((i3 & 2) != 0) {
            colors2 = metadata.colors;
        }
        return metadata.copy(str, colors2);
    }

    @Nullable
    public final String component1() {
        return this.shortName;
    }

    @NotNull
    public final Colors component2() {
        return this.colors;
    }

    @NotNull
    public final Metadata copy(@Nullable String str, @NotNull Colors colors2) {
        Intrinsics.checkNotNullParameter(colors2, "colors");
        return new Metadata(str, colors2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Metadata)) {
            return false;
        }
        Metadata metadata = (Metadata) obj;
        return Intrinsics.areEqual((Object) this.shortName, (Object) metadata.shortName) && Intrinsics.areEqual((Object) this.colors, (Object) metadata.colors);
    }

    @NotNull
    public final Colors getColors() {
        return this.colors;
    }

    @Nullable
    public final String getShortName() {
        return this.shortName;
    }

    public int hashCode() {
        String str = this.shortName;
        return this.colors.hashCode() + ((str == null ? 0 : str.hashCode()) * 31);
    }

    @NotNull
    public String toString() {
        String str = this.shortName;
        Colors colors2 = this.colors;
        return "Metadata(shortName=" + str + ", colors=" + colors2 + ")";
    }
}
