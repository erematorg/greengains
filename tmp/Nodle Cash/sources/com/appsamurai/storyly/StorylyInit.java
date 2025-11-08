package com.appsamurai.storyly;

import androidx.annotation.Keep;
import com.appsamurai.storyly.config.StorylyConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\b\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0004¢\u0006\u0004\b\u0015\u0010\u0016J\t\u0010\u0003\u001a\u00020\u0002HÆ\u0003J\t\u0010\u0005\u001a\u00020\u0004HÆ\u0003J\u001d\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u0004HÆ\u0001J\t\u0010\t\u001a\u00020\u0002HÖ\u0001J\t\u0010\u000b\u001a\u00020\nHÖ\u0001J\u0013\u0010\u000e\u001a\u00020\r2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\u0006\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0007\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0017"}, d2 = {"Lcom/appsamurai/storyly/StorylyInit;", "", "", "component1", "Lcom/appsamurai/storyly/config/StorylyConfig;", "component2", "storylyId", "config", "copy", "toString", "", "hashCode", "other", "", "equals", "Ljava/lang/String;", "getStorylyId", "()Ljava/lang/String;", "Lcom/appsamurai/storyly/config/StorylyConfig;", "getConfig", "()Lcom/appsamurai/storyly/config/StorylyConfig;", "<init>", "(Ljava/lang/String;Lcom/appsamurai/storyly/config/StorylyConfig;)V", "storyly_release"}, k = 1, mv = {1, 5, 1})
@Keep
public final class StorylyInit {
    @NotNull
    private final StorylyConfig config;
    @NotNull
    private final String storylyId;

    public StorylyInit(@NotNull String str, @NotNull StorylyConfig storylyConfig) {
        Intrinsics.checkNotNullParameter(str, "storylyId");
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        this.storylyId = str;
        this.config = storylyConfig;
    }

    public static /* synthetic */ StorylyInit copy$default(StorylyInit storylyInit, String str, StorylyConfig storylyConfig, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = storylyInit.storylyId;
        }
        if ((i3 & 2) != 0) {
            storylyConfig = storylyInit.config;
        }
        return storylyInit.copy(str, storylyConfig);
    }

    @NotNull
    public final String component1() {
        return this.storylyId;
    }

    @NotNull
    public final StorylyConfig component2() {
        return this.config;
    }

    @NotNull
    public final StorylyInit copy(@NotNull String str, @NotNull StorylyConfig storylyConfig) {
        Intrinsics.checkNotNullParameter(str, "storylyId");
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        return new StorylyInit(str, storylyConfig);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StorylyInit)) {
            return false;
        }
        StorylyInit storylyInit = (StorylyInit) obj;
        return Intrinsics.areEqual((Object) this.storylyId, (Object) storylyInit.storylyId) && Intrinsics.areEqual((Object) this.config, (Object) storylyInit.config);
    }

    @NotNull
    public final StorylyConfig getConfig() {
        return this.config;
    }

    @NotNull
    public final String getStorylyId() {
        return this.storylyId;
    }

    public int hashCode() {
        return this.config.hashCode() + (this.storylyId.hashCode() * 31);
    }

    @NotNull
    public String toString() {
        return "StorylyInit(storylyId=" + this.storylyId + ", config=" + this.config + ')';
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ StorylyInit(String str, StorylyConfig storylyConfig, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i3 & 2) != 0 ? new StorylyConfig.Builder().build() : storylyConfig);
    }
}
