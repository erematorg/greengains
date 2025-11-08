package com.appsamurai.storyly.config.styling.moments;

import android.graphics.Typeface;
import androidx.annotation.Keep;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\u0006¢\u0006\u0004\b\u001b\u0010\u001cJ\u0010\u0010\u0005\u001a\u00020\u0002HÀ\u0003¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\t\u001a\u00020\u0006HÀ\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\n\u001a\u00020\u00022\b\b\u0002\u0010\u000b\u001a\u00020\u0006HÆ\u0001J\t\u0010\r\u001a\u00020\u0002HÖ\u0001J\t\u0010\u000f\u001a\u00020\u000eHÖ\u0001J\u0013\u0010\u0012\u001a\u00020\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\"\u0010\n\u001a\u00020\u00028\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u0013\u001a\u0004\b\u0014\u0010\u0004\"\u0004\b\u0015\u0010\u0016R\"\u0010\u000b\u001a\u00020\u00068\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\u0017\u001a\u0004\b\u0018\u0010\b\"\u0004\b\u0019\u0010\u001a¨\u0006\u001d"}, d2 = {"Lcom/appsamurai/storyly/config/styling/moments/MomentsCustomFont;", "", "", "component1$storyly_release", "()Ljava/lang/String;", "component1", "Landroid/graphics/Typeface;", "component2$storyly_release", "()Landroid/graphics/Typeface;", "component2", "identifier", "font", "copy", "toString", "", "hashCode", "other", "", "equals", "Ljava/lang/String;", "getIdentifier$storyly_release", "setIdentifier$storyly_release", "(Ljava/lang/String;)V", "Landroid/graphics/Typeface;", "getFont$storyly_release", "setFont$storyly_release", "(Landroid/graphics/Typeface;)V", "<init>", "(Ljava/lang/String;Landroid/graphics/Typeface;)V", "storyly_release"}, k = 1, mv = {1, 5, 1})
@Keep
public final class MomentsCustomFont {
    @NotNull
    private Typeface font;
    @NotNull
    private String identifier;

    public MomentsCustomFont(@NotNull String str, @NotNull Typeface typeface) {
        Intrinsics.checkNotNullParameter(str, "identifier");
        Intrinsics.checkNotNullParameter(typeface, "font");
        this.identifier = str;
        this.font = typeface;
    }

    public static /* synthetic */ MomentsCustomFont copy$default(MomentsCustomFont momentsCustomFont, String str, Typeface typeface, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = momentsCustomFont.identifier;
        }
        if ((i3 & 2) != 0) {
            typeface = momentsCustomFont.font;
        }
        return momentsCustomFont.copy(str, typeface);
    }

    @NotNull
    public final String component1$storyly_release() {
        return this.identifier;
    }

    @NotNull
    public final Typeface component2$storyly_release() {
        return this.font;
    }

    @NotNull
    public final MomentsCustomFont copy(@NotNull String str, @NotNull Typeface typeface) {
        Intrinsics.checkNotNullParameter(str, "identifier");
        Intrinsics.checkNotNullParameter(typeface, "font");
        return new MomentsCustomFont(str, typeface);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MomentsCustomFont)) {
            return false;
        }
        MomentsCustomFont momentsCustomFont = (MomentsCustomFont) obj;
        return Intrinsics.areEqual((Object) this.identifier, (Object) momentsCustomFont.identifier) && Intrinsics.areEqual((Object) this.font, (Object) momentsCustomFont.font);
    }

    @NotNull
    public final Typeface getFont$storyly_release() {
        return this.font;
    }

    @NotNull
    public final String getIdentifier$storyly_release() {
        return this.identifier;
    }

    public int hashCode() {
        return this.font.hashCode() + (this.identifier.hashCode() * 31);
    }

    public final void setFont$storyly_release(@NotNull Typeface typeface) {
        Intrinsics.checkNotNullParameter(typeface, "<set-?>");
        this.font = typeface;
    }

    public final void setIdentifier$storyly_release(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.identifier = str;
    }

    @NotNull
    public String toString() {
        return "MomentsCustomFont(identifier=" + this.identifier + ", font=" + this.font + ')';
    }
}
