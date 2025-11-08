package com.appsamurai.storyly.config.styling.moments;

import android.graphics.Typeface;
import androidx.annotation.Keep;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\b\b\u0018\u00002\u00020\u0001:\u0001\u0018B\u001d\b\u0000\u0012\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00040\b¢\u0006\u0004\b\u0016\u0010\u0017J\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u0005\u0010\u0006J\u001c\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00040\bHÀ\u0003¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\r\u001a\u00020\u00002\u0014\b\u0002\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00040\bHÆ\u0001J\t\u0010\u000e\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0010\u001a\u00020\u000fHÖ\u0001J\u0013\u0010\u0013\u001a\u00020\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003R(\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00040\b8\u0000@\u0000X\u0004¢\u0006\f\n\u0004\b\f\u0010\u0014\u001a\u0004\b\u0015\u0010\n¨\u0006\u0019"}, d2 = {"Lcom/appsamurai/storyly/config/styling/moments/StorylyMomentsTextStyling;", "", "", "fontName", "Landroid/graphics/Typeface;", "getMomentsCustomFont$storyly_release", "(Ljava/lang/String;)Landroid/graphics/Typeface;", "getMomentsCustomFont", "", "component1$storyly_release", "()Ljava/util/Map;", "component1", "customFontMap", "copy", "toString", "", "hashCode", "other", "", "equals", "Ljava/util/Map;", "getCustomFontMap$storyly_release", "<init>", "(Ljava/util/Map;)V", "Builder", "storyly_release"}, k = 1, mv = {1, 5, 1})
@Keep
public final class StorylyMomentsTextStyling {
    @NotNull
    private final Map<String, Typeface> customFontMap;

    @Keep
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\r\u0010\u000eJ\u0014\u0010\u0005\u001a\u00020\u00002\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002J\u0006\u0010\u0007\u001a\u00020\u0006R\"\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/appsamurai/storyly/config/styling/moments/StorylyMomentsTextStyling$Builder;", "", "", "Lcom/appsamurai/storyly/config/styling/moments/MomentsCustomFont;", "fonts", "setCustomFonts", "Lcom/appsamurai/storyly/config/styling/moments/StorylyMomentsTextStyling;", "build", "", "", "Landroid/graphics/Typeface;", "customFontMap", "Ljava/util/Map;", "<init>", "()V", "storyly_release"}, k = 1, mv = {1, 5, 1})
    public static final class Builder {
        @NotNull
        private Map<String, Typeface> customFontMap = new LinkedHashMap();

        @NotNull
        public final StorylyMomentsTextStyling build() {
            return new StorylyMomentsTextStyling(this.customFontMap);
        }

        @NotNull
        public final Builder setCustomFonts(@NotNull List<MomentsCustomFont> list) {
            Intrinsics.checkNotNullParameter(list, "fonts");
            for (MomentsCustomFont momentsCustomFont : list) {
                this.customFontMap.put(momentsCustomFont.getIdentifier$storyly_release(), momentsCustomFont.getFont$storyly_release());
            }
            return this;
        }
    }

    public StorylyMomentsTextStyling(@NotNull Map<String, ? extends Typeface> map) {
        Intrinsics.checkNotNullParameter(map, "customFontMap");
        this.customFontMap = map;
    }

    public static /* synthetic */ StorylyMomentsTextStyling copy$default(StorylyMomentsTextStyling storylyMomentsTextStyling, Map<String, Typeface> map, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            map = storylyMomentsTextStyling.customFontMap;
        }
        return storylyMomentsTextStyling.copy(map);
    }

    @NotNull
    public final Map<String, Typeface> component1$storyly_release() {
        return this.customFontMap;
    }

    @NotNull
    public final StorylyMomentsTextStyling copy(@NotNull Map<String, ? extends Typeface> map) {
        Intrinsics.checkNotNullParameter(map, "customFontMap");
        return new StorylyMomentsTextStyling(map);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof StorylyMomentsTextStyling) && Intrinsics.areEqual((Object) this.customFontMap, (Object) ((StorylyMomentsTextStyling) obj).customFontMap);
    }

    @NotNull
    public final Map<String, Typeface> getCustomFontMap$storyly_release() {
        return this.customFontMap;
    }

    @Nullable
    public final Typeface getMomentsCustomFont$storyly_release(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "fontName");
        return this.customFontMap.get(str);
    }

    public int hashCode() {
        return this.customFontMap.hashCode();
    }

    @NotNull
    public String toString() {
        return "StorylyMomentsTextStyling(customFontMap=" + this.customFontMap + ')';
    }
}
