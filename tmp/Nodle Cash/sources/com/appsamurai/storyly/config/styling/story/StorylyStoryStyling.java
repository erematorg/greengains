package com.appsamurai.storyly.config.styling.story;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import androidx.annotation.Keep;
import com.appsamurai.storyly.config.styling.a;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000e\n\u0002\b(\b\b\u0018\u00002\u00020\u0001:\u0001RBk\b\u0000\u0012\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010!\u001a\u00020\u0003\u0012\u0006\u0010\"\u001a\u00020\n\u0012\b\u0010#\u001a\u0004\u0018\u00010\n\u0012\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010%\u001a\u00020\u0012\u0012\u0006\u0010&\u001a\u00020\u0012\u0012\u0006\u0010'\u001a\u00020\u0012\u0012\b\u0010(\u001a\u0004\u0018\u00010\u001a\u0012\b\u0010)\u001a\u0004\u0018\u00010\u001a¢\u0006\u0004\bP\u0010QJ\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002HÀ\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\t\u001a\u00020\u0003HÀ\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\r\u001a\u00020\nHÀ\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u0012\u0010\u000f\u001a\u0004\u0018\u00010\nHÀ\u0003¢\u0006\u0004\b\u000e\u0010\fJ\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002HÀ\u0003¢\u0006\u0004\b\u0010\u0010\u0005J\u0010\u0010\u0015\u001a\u00020\u0012HÀ\u0003¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0017\u001a\u00020\u0012HÀ\u0003¢\u0006\u0004\b\u0016\u0010\u0014J\u0010\u0010\u0019\u001a\u00020\u0012HÀ\u0003¢\u0006\u0004\b\u0018\u0010\u0014J\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u001aHÀ\u0003¢\u0006\u0004\b\u001b\u0010\u001cJ\u0012\u0010\u001f\u001a\u0004\u0018\u00010\u001aHÀ\u0003¢\u0006\u0004\b\u001e\u0010\u001cJ\u0010*\u001a\u00020\u00002\u000e\b\u0002\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010!\u001a\u00020\u00032\b\b\u0002\u0010\"\u001a\u00020\n2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\n2\u000e\b\u0002\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010%\u001a\u00020\u00122\b\b\u0002\u0010&\u001a\u00020\u00122\b\b\u0002\u0010'\u001a\u00020\u00122\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u001a2\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u001aHÆ\u0001J\t\u0010,\u001a\u00020+HÖ\u0001J\t\u0010-\u001a\u00020\u0003HÖ\u0001J\u0013\u0010/\u001a\u00020\u00122\b\u0010.\u001a\u0004\u0018\u00010\u0001HÖ\u0003R(\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b \u00100\u001a\u0004\b1\u0010\u0005\"\u0004\b2\u00103R\"\u0010!\u001a\u00020\u00038\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b!\u00104\u001a\u0004\b5\u0010\b\"\u0004\b6\u00107R\"\u0010\"\u001a\u00020\n8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b\"\u00108\u001a\u0004\b9\u0010\f\"\u0004\b:\u0010;R$\u0010#\u001a\u0004\u0018\u00010\n8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b#\u00108\u001a\u0004\b<\u0010\f\"\u0004\b=\u0010;R(\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b$\u00100\u001a\u0004\b>\u0010\u0005\"\u0004\b?\u00103R\"\u0010%\u001a\u00020\u00128\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b%\u0010@\u001a\u0004\bA\u0010\u0014\"\u0004\bB\u0010CR\"\u0010&\u001a\u00020\u00128\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b&\u0010@\u001a\u0004\bD\u0010\u0014\"\u0004\bE\u0010CR\"\u0010'\u001a\u00020\u00128\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b'\u0010@\u001a\u0004\bF\u0010\u0014\"\u0004\bG\u0010CR$\u0010(\u001a\u0004\u0018\u00010\u001a8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b(\u0010H\u001a\u0004\bI\u0010\u001c\"\u0004\bJ\u0010KR$\u0010)\u001a\u0004\u0018\u00010\u001a8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b)\u0010H\u001a\u0004\bL\u0010\u001c\"\u0004\bM\u0010KR\u0016\u0010O\u001a\u00020\n8@@\u0000X\u0004¢\u0006\u0006\u001a\u0004\bN\u0010\f¨\u0006S"}, d2 = {"Lcom/appsamurai/storyly/config/styling/story/StorylyStoryStyling;", "", "", "", "component1$storyly_release", "()Ljava/util/List;", "component1", "component2$storyly_release", "()I", "component2", "Landroid/graphics/Typeface;", "component3$storyly_release", "()Landroid/graphics/Typeface;", "component3", "component4$storyly_release", "component4", "component5$storyly_release", "component5", "", "component6$storyly_release", "()Z", "component6", "component7$storyly_release", "component7", "component8$storyly_release", "component8", "Landroid/graphics/drawable/Drawable;", "component9$storyly_release", "()Landroid/graphics/drawable/Drawable;", "component9", "component10$storyly_release", "component10", "headerIconBorderColor", "titleColor", "titleTypeface", "_interactiveTypeface", "progressBarColor", "isTitleVisible", "isHeaderIconVisible", "isCloseButtonVisible", "closeButtonIcon", "shareButtonIcon", "copy", "", "toString", "hashCode", "other", "equals", "Ljava/util/List;", "getHeaderIconBorderColor$storyly_release", "setHeaderIconBorderColor$storyly_release", "(Ljava/util/List;)V", "I", "getTitleColor$storyly_release", "setTitleColor$storyly_release", "(I)V", "Landroid/graphics/Typeface;", "getTitleTypeface$storyly_release", "setTitleTypeface$storyly_release", "(Landroid/graphics/Typeface;)V", "get_interactiveTypeface$storyly_release", "set_interactiveTypeface$storyly_release", "getProgressBarColor$storyly_release", "setProgressBarColor$storyly_release", "Z", "isTitleVisible$storyly_release", "setTitleVisible$storyly_release", "(Z)V", "isHeaderIconVisible$storyly_release", "setHeaderIconVisible$storyly_release", "isCloseButtonVisible$storyly_release", "setCloseButtonVisible$storyly_release", "Landroid/graphics/drawable/Drawable;", "getCloseButtonIcon$storyly_release", "setCloseButtonIcon$storyly_release", "(Landroid/graphics/drawable/Drawable;)V", "getShareButtonIcon$storyly_release", "setShareButtonIcon$storyly_release", "getInteractiveTypeface$storyly_release", "interactiveTypeface", "<init>", "(Ljava/util/List;ILandroid/graphics/Typeface;Landroid/graphics/Typeface;Ljava/util/List;ZZZLandroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;)V", "Builder", "storyly_release"}, k = 1, mv = {1, 5, 1})
@Keep
public final class StorylyStoryStyling {
    @Nullable
    private Typeface _interactiveTypeface;
    @Nullable
    private Drawable closeButtonIcon;
    @NotNull
    private List<Integer> headerIconBorderColor;
    private boolean isCloseButtonVisible;
    private boolean isHeaderIconVisible;
    private boolean isTitleVisible;
    @NotNull
    private List<Integer> progressBarColor;
    @Nullable
    private Drawable shareButtonIcon;
    private int titleColor;
    @NotNull
    private Typeface titleTypeface;

    @Keep
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0014\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b(\u0010)J\u0014\u0010\u0005\u001a\u00020\u00002\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0003J\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\bJ\u0010\u0010\u000b\u001a\u00020\u00002\b\u0010\t\u001a\u0004\u0018\u00010\bJ\u0014\u0010\f\u001a\u00020\u00002\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002J\u000e\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\rJ\u000e\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\rJ\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\rJ\u0010\u0010\u0014\u001a\u00020\u00002\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012J\u0010\u0010\u0015\u001a\u00020\u00002\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012J\u0006\u0010\u0017\u001a\u00020\u0016R\u001c\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0016\u0010\u001a\u001a\u00020\u00038\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u001e\u0010\u001d\u001a\n \u001c*\u0004\u0018\u00010\b0\b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0018\u0010\u001f\u001a\u0004\u0018\u00010\b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010\u001eR\u001c\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b \u0010\u0019R\u0016\u0010!\u001a\u00020\r8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b!\u0010\"R\u0016\u0010#\u001a\u00020\r8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b#\u0010\"R\u0016\u0010$\u001a\u00020\r8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b$\u0010\"R\u0018\u0010%\u001a\u0004\u0018\u00010\u00128\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b%\u0010&R\u0018\u0010'\u001a\u0004\u0018\u00010\u00128\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b'\u0010&¨\u0006*"}, d2 = {"Lcom/appsamurai/storyly/config/styling/story/StorylyStoryStyling$Builder;", "", "", "", "colors", "setHeaderIconBorderColor", "color", "setTitleColor", "Landroid/graphics/Typeface;", "typeface", "setTitleTypeface", "setInteractiveTypeface", "setProgressBarColor", "", "isVisible", "setTitleVisibility", "setHeaderIconVisibility", "setCloseButtonVisibility", "Landroid/graphics/drawable/Drawable;", "icon", "setCloseButtonIcon", "setShareButtonIcon", "Lcom/appsamurai/storyly/config/styling/story/StorylyStoryStyling;", "build", "headerIconBorderColor", "Ljava/util/List;", "titleColor", "I", "kotlin.jvm.PlatformType", "titleTypeface", "Landroid/graphics/Typeface;", "interactiveTypeface", "progressBarColor", "isTitleVisible", "Z", "isHeaderIconVisible", "isCloseButtonVisible", "closeButtonIcon", "Landroid/graphics/drawable/Drawable;", "shareButtonIcon", "<init>", "()V", "storyly_release"}, k = 1, mv = {1, 5, 1})
    public static final class Builder {
        @Nullable
        private Drawable closeButtonIcon;
        @NotNull
        private List<Integer> headerIconBorderColor;
        @Nullable
        private Typeface interactiveTypeface;
        private boolean isCloseButtonVisible;
        private boolean isHeaderIconVisible;
        private boolean isTitleVisible;
        @NotNull
        private List<Integer> progressBarColor;
        @Nullable
        private Drawable shareButtonIcon;
        private int titleColor;
        private Typeface titleTypeface;

        public Builder() {
            a aVar = a.COLOR_DBDBDB;
            List<a> listOf = CollectionsKt.listOf(aVar, aVar);
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listOf, 10));
            for (a a2 : listOf) {
                arrayList.add(Integer.valueOf(a2.a()));
            }
            this.headerIconBorderColor = arrayList;
            this.titleColor = -1;
            this.titleTypeface = Typeface.DEFAULT_BOLD;
            List<a> listOf2 = CollectionsKt.listOf(a.COLOR_FFFFFF, a.COLOR_CCCCCCCC);
            ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listOf2, 10));
            for (a a3 : listOf2) {
                arrayList2.add(Integer.valueOf(a3.a()));
            }
            this.progressBarColor = arrayList2;
            this.isTitleVisible = true;
            this.isHeaderIconVisible = true;
            this.isCloseButtonVisible = true;
        }

        @NotNull
        public final StorylyStoryStyling build() {
            List<Integer> list = this.headerIconBorderColor;
            int i3 = this.titleColor;
            Typeface typeface = this.titleTypeface;
            Intrinsics.checkNotNullExpressionValue(typeface, "titleTypeface");
            return new StorylyStoryStyling(list, i3, typeface, this.interactiveTypeface, this.progressBarColor, this.isTitleVisible, this.isHeaderIconVisible, this.isCloseButtonVisible, this.closeButtonIcon, this.shareButtonIcon);
        }

        @NotNull
        public final Builder setCloseButtonIcon(@Nullable Drawable drawable) {
            this.closeButtonIcon = drawable;
            return this;
        }

        @NotNull
        public final Builder setCloseButtonVisibility(boolean z2) {
            this.isCloseButtonVisible = z2;
            return this;
        }

        @NotNull
        public final Builder setHeaderIconBorderColor(@NotNull List<Integer> list) {
            Intrinsics.checkNotNullParameter(list, "colors");
            this.headerIconBorderColor = list;
            return this;
        }

        @NotNull
        public final Builder setHeaderIconVisibility(boolean z2) {
            this.isHeaderIconVisible = z2;
            return this;
        }

        @NotNull
        public final Builder setInteractiveTypeface(@Nullable Typeface typeface) {
            this.interactiveTypeface = typeface;
            return this;
        }

        @NotNull
        public final Builder setProgressBarColor(@NotNull List<Integer> list) {
            Intrinsics.checkNotNullParameter(list, "colors");
            this.progressBarColor = list;
            return this;
        }

        @NotNull
        public final Builder setShareButtonIcon(@Nullable Drawable drawable) {
            this.shareButtonIcon = drawable;
            return this;
        }

        @NotNull
        public final Builder setTitleColor(int i3) {
            this.titleColor = i3;
            return this;
        }

        @NotNull
        public final Builder setTitleTypeface(@NotNull Typeface typeface) {
            Intrinsics.checkNotNullParameter(typeface, "typeface");
            this.titleTypeface = typeface;
            return this;
        }

        @NotNull
        public final Builder setTitleVisibility(boolean z2) {
            this.isTitleVisible = z2;
            return this;
        }
    }

    public StorylyStoryStyling(@NotNull List<Integer> list, int i3, @NotNull Typeface typeface, @Nullable Typeface typeface2, @NotNull List<Integer> list2, boolean z2, boolean z3, boolean z4, @Nullable Drawable drawable, @Nullable Drawable drawable2) {
        Intrinsics.checkNotNullParameter(list, "headerIconBorderColor");
        Intrinsics.checkNotNullParameter(typeface, "titleTypeface");
        Intrinsics.checkNotNullParameter(list2, "progressBarColor");
        this.headerIconBorderColor = list;
        this.titleColor = i3;
        this.titleTypeface = typeface;
        this._interactiveTypeface = typeface2;
        this.progressBarColor = list2;
        this.isTitleVisible = z2;
        this.isHeaderIconVisible = z3;
        this.isCloseButtonVisible = z4;
        this.closeButtonIcon = drawable;
        this.shareButtonIcon = drawable2;
    }

    public static /* synthetic */ StorylyStoryStyling copy$default(StorylyStoryStyling storylyStoryStyling, List list, int i3, Typeface typeface, Typeface typeface2, List list2, boolean z2, boolean z3, boolean z4, Drawable drawable, Drawable drawable2, int i4, Object obj) {
        StorylyStoryStyling storylyStoryStyling2 = storylyStoryStyling;
        int i5 = i4;
        return storylyStoryStyling.copy((i5 & 1) != 0 ? storylyStoryStyling2.headerIconBorderColor : list, (i5 & 2) != 0 ? storylyStoryStyling2.titleColor : i3, (i5 & 4) != 0 ? storylyStoryStyling2.titleTypeface : typeface, (i5 & 8) != 0 ? storylyStoryStyling2._interactiveTypeface : typeface2, (i5 & 16) != 0 ? storylyStoryStyling2.progressBarColor : list2, (i5 & 32) != 0 ? storylyStoryStyling2.isTitleVisible : z2, (i5 & 64) != 0 ? storylyStoryStyling2.isHeaderIconVisible : z3, (i5 & 128) != 0 ? storylyStoryStyling2.isCloseButtonVisible : z4, (i5 & 256) != 0 ? storylyStoryStyling2.closeButtonIcon : drawable, (i5 & 512) != 0 ? storylyStoryStyling2.shareButtonIcon : drawable2);
    }

    @NotNull
    public final List<Integer> component1$storyly_release() {
        return this.headerIconBorderColor;
    }

    @Nullable
    public final Drawable component10$storyly_release() {
        return this.shareButtonIcon;
    }

    public final int component2$storyly_release() {
        return this.titleColor;
    }

    @NotNull
    public final Typeface component3$storyly_release() {
        return this.titleTypeface;
    }

    @Nullable
    public final Typeface component4$storyly_release() {
        return this._interactiveTypeface;
    }

    @NotNull
    public final List<Integer> component5$storyly_release() {
        return this.progressBarColor;
    }

    public final boolean component6$storyly_release() {
        return this.isTitleVisible;
    }

    public final boolean component7$storyly_release() {
        return this.isHeaderIconVisible;
    }

    public final boolean component8$storyly_release() {
        return this.isCloseButtonVisible;
    }

    @Nullable
    public final Drawable component9$storyly_release() {
        return this.closeButtonIcon;
    }

    @NotNull
    public final StorylyStoryStyling copy(@NotNull List<Integer> list, int i3, @NotNull Typeface typeface, @Nullable Typeface typeface2, @NotNull List<Integer> list2, boolean z2, boolean z3, boolean z4, @Nullable Drawable drawable, @Nullable Drawable drawable2) {
        Intrinsics.checkNotNullParameter(list, "headerIconBorderColor");
        Intrinsics.checkNotNullParameter(typeface, "titleTypeface");
        List<Integer> list3 = list2;
        Intrinsics.checkNotNullParameter(list3, "progressBarColor");
        return new StorylyStoryStyling(list, i3, typeface, typeface2, list3, z2, z3, z4, drawable, drawable2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StorylyStoryStyling)) {
            return false;
        }
        StorylyStoryStyling storylyStoryStyling = (StorylyStoryStyling) obj;
        return Intrinsics.areEqual((Object) this.headerIconBorderColor, (Object) storylyStoryStyling.headerIconBorderColor) && this.titleColor == storylyStoryStyling.titleColor && Intrinsics.areEqual((Object) this.titleTypeface, (Object) storylyStoryStyling.titleTypeface) && Intrinsics.areEqual((Object) this._interactiveTypeface, (Object) storylyStoryStyling._interactiveTypeface) && Intrinsics.areEqual((Object) this.progressBarColor, (Object) storylyStoryStyling.progressBarColor) && this.isTitleVisible == storylyStoryStyling.isTitleVisible && this.isHeaderIconVisible == storylyStoryStyling.isHeaderIconVisible && this.isCloseButtonVisible == storylyStoryStyling.isCloseButtonVisible && Intrinsics.areEqual((Object) this.closeButtonIcon, (Object) storylyStoryStyling.closeButtonIcon) && Intrinsics.areEqual((Object) this.shareButtonIcon, (Object) storylyStoryStyling.shareButtonIcon);
    }

    @Nullable
    public final Drawable getCloseButtonIcon$storyly_release() {
        return this.closeButtonIcon;
    }

    @NotNull
    public final List<Integer> getHeaderIconBorderColor$storyly_release() {
        return this.headerIconBorderColor;
    }

    @NotNull
    public final Typeface getInteractiveTypeface$storyly_release() {
        Typeface typeface = this._interactiveTypeface;
        if (typeface != null) {
            return typeface;
        }
        Typeface typeface2 = Typeface.DEFAULT;
        Intrinsics.checkNotNullExpressionValue(typeface2, "DEFAULT");
        return typeface2;
    }

    @NotNull
    public final List<Integer> getProgressBarColor$storyly_release() {
        return this.progressBarColor;
    }

    @Nullable
    public final Drawable getShareButtonIcon$storyly_release() {
        return this.shareButtonIcon;
    }

    public final int getTitleColor$storyly_release() {
        return this.titleColor;
    }

    @NotNull
    public final Typeface getTitleTypeface$storyly_release() {
        return this.titleTypeface;
    }

    @Nullable
    public final Typeface get_interactiveTypeface$storyly_release() {
        return this._interactiveTypeface;
    }

    public int hashCode() {
        int hashCode = (this.titleTypeface.hashCode() + androidx.compose.animation.core.a.c(this.titleColor, this.headerIconBorderColor.hashCode() * 31, 31)) * 31;
        Typeface typeface = this._interactiveTypeface;
        int i3 = 0;
        int j2 = androidx.compose.animation.core.a.j(this.progressBarColor, (hashCode + (typeface == null ? 0 : typeface.hashCode())) * 31, 31);
        boolean z2 = this.isTitleVisible;
        boolean z3 = true;
        if (z2) {
            z2 = true;
        }
        int i4 = (j2 + (z2 ? 1 : 0)) * 31;
        boolean z4 = this.isHeaderIconVisible;
        if (z4) {
            z4 = true;
        }
        int i5 = (i4 + (z4 ? 1 : 0)) * 31;
        boolean z5 = this.isCloseButtonVisible;
        if (!z5) {
            z3 = z5;
        }
        int i6 = (i5 + (z3 ? 1 : 0)) * 31;
        Drawable drawable = this.closeButtonIcon;
        int hashCode2 = (i6 + (drawable == null ? 0 : drawable.hashCode())) * 31;
        Drawable drawable2 = this.shareButtonIcon;
        if (drawable2 != null) {
            i3 = drawable2.hashCode();
        }
        return hashCode2 + i3;
    }

    public final boolean isCloseButtonVisible$storyly_release() {
        return this.isCloseButtonVisible;
    }

    public final boolean isHeaderIconVisible$storyly_release() {
        return this.isHeaderIconVisible;
    }

    public final boolean isTitleVisible$storyly_release() {
        return this.isTitleVisible;
    }

    public final void setCloseButtonIcon$storyly_release(@Nullable Drawable drawable) {
        this.closeButtonIcon = drawable;
    }

    public final void setCloseButtonVisible$storyly_release(boolean z2) {
        this.isCloseButtonVisible = z2;
    }

    public final void setHeaderIconBorderColor$storyly_release(@NotNull List<Integer> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.headerIconBorderColor = list;
    }

    public final void setHeaderIconVisible$storyly_release(boolean z2) {
        this.isHeaderIconVisible = z2;
    }

    public final void setProgressBarColor$storyly_release(@NotNull List<Integer> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.progressBarColor = list;
    }

    public final void setShareButtonIcon$storyly_release(@Nullable Drawable drawable) {
        this.shareButtonIcon = drawable;
    }

    public final void setTitleColor$storyly_release(int i3) {
        this.titleColor = i3;
    }

    public final void setTitleTypeface$storyly_release(@NotNull Typeface typeface) {
        Intrinsics.checkNotNullParameter(typeface, "<set-?>");
        this.titleTypeface = typeface;
    }

    public final void setTitleVisible$storyly_release(boolean z2) {
        this.isTitleVisible = z2;
    }

    public final void set_interactiveTypeface$storyly_release(@Nullable Typeface typeface) {
        this._interactiveTypeface = typeface;
    }

    @NotNull
    public String toString() {
        return "StorylyStoryStyling(headerIconBorderColor=" + this.headerIconBorderColor + ", titleColor=" + this.titleColor + ", titleTypeface=" + this.titleTypeface + ", _interactiveTypeface=" + this._interactiveTypeface + ", progressBarColor=" + this.progressBarColor + ", isTitleVisible=" + this.isTitleVisible + ", isHeaderIconVisible=" + this.isHeaderIconVisible + ", isCloseButtonVisible=" + this.isCloseButtonVisible + ", closeButtonIcon=" + this.closeButtonIcon + ", shareButtonIcon=" + this.shareButtonIcon + ')';
    }
}
