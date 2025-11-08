package com.appsamurai.storyly.config;

import androidx.annotation.Keep;
import com.appsamurai.storyly.data.e;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\b\b\u0018\u00002\u00020\u0001:\u0001\u0019B\u001b\b\u0000\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0005\u001a\u00020\u0002HÀ\u0003¢\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u0002HÀ\u0003¢\u0006\u0004\b\u0006\u0010\u0004J\u001f\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\b\u001a\u00020\u00022\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\t\u0010\u000b\u001a\u00020\u0002HÖ\u0001J\t\u0010\r\u001a\u00020\fHÖ\u0001J\u0013\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\"\u0010\b\u001a\u00020\u00028\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b\b\u0010\u0011\u001a\u0004\b\u0012\u0010\u0004\"\u0004\b\u0013\u0010\u0014R$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b\t\u0010\u0011\u001a\u0004\b\u0015\u0010\u0004\"\u0004\b\u0016\u0010\u0014¨\u0006\u001a"}, d2 = {"Lcom/appsamurai/storyly/config/StorylyShareConfig;", "", "", "component1$storyly_release", "()Ljava/lang/String;", "component1", "component2$storyly_release", "component2", "url", "facebookAppID", "copy", "toString", "", "hashCode", "other", "", "equals", "Ljava/lang/String;", "getUrl$storyly_release", "setUrl$storyly_release", "(Ljava/lang/String;)V", "getFacebookAppID$storyly_release", "setFacebookAppID$storyly_release", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "Builder", "storyly_release"}, k = 1, mv = {1, 5, 1})
@Keep
public final class StorylyShareConfig {
    @Nullable
    private String facebookAppID;
    @NotNull
    private String url;

    @Keep
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\f\u0010\rJ\u000e\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0002J\u0006\u0010\b\u001a\u00020\u0007R\u0016\u0010\t\u001a\u00020\u00028\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\t\u0010\nR\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u000b\u0010\n¨\u0006\u000e"}, d2 = {"Lcom/appsamurai/storyly/config/StorylyShareConfig$Builder;", "", "", "url", "setShareUrl", "id", "setFacebookAppID", "Lcom/appsamurai/storyly/config/StorylyShareConfig;", "build", "shareUrl", "Ljava/lang/String;", "facebookAppID", "<init>", "()V", "storyly_release"}, k = 1, mv = {1, 5, 1})
    public static final class Builder {
        @Nullable
        private String facebookAppID;
        @NotNull
        private String shareUrl = e.a().c();

        @NotNull
        public final StorylyShareConfig build() {
            return new StorylyShareConfig(this.shareUrl, this.facebookAppID);
        }

        @NotNull
        public final Builder setFacebookAppID(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
            this.facebookAppID = str;
            return this;
        }

        @NotNull
        public final Builder setShareUrl(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "url");
            this.shareUrl = str;
            return this;
        }
    }

    public StorylyShareConfig(@NotNull String str, @Nullable String str2) {
        Intrinsics.checkNotNullParameter(str, "url");
        this.url = str;
        this.facebookAppID = str2;
    }

    public static /* synthetic */ StorylyShareConfig copy$default(StorylyShareConfig storylyShareConfig, String str, String str2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = storylyShareConfig.url;
        }
        if ((i3 & 2) != 0) {
            str2 = storylyShareConfig.facebookAppID;
        }
        return storylyShareConfig.copy(str, str2);
    }

    @NotNull
    public final String component1$storyly_release() {
        return this.url;
    }

    @Nullable
    public final String component2$storyly_release() {
        return this.facebookAppID;
    }

    @NotNull
    public final StorylyShareConfig copy(@NotNull String str, @Nullable String str2) {
        Intrinsics.checkNotNullParameter(str, "url");
        return new StorylyShareConfig(str, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StorylyShareConfig)) {
            return false;
        }
        StorylyShareConfig storylyShareConfig = (StorylyShareConfig) obj;
        return Intrinsics.areEqual((Object) this.url, (Object) storylyShareConfig.url) && Intrinsics.areEqual((Object) this.facebookAppID, (Object) storylyShareConfig.facebookAppID);
    }

    @Nullable
    public final String getFacebookAppID$storyly_release() {
        return this.facebookAppID;
    }

    @NotNull
    public final String getUrl$storyly_release() {
        return this.url;
    }

    public int hashCode() {
        int hashCode = this.url.hashCode() * 31;
        String str = this.facebookAppID;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    public final void setFacebookAppID$storyly_release(@Nullable String str) {
        this.facebookAppID = str;
    }

    public final void setUrl$storyly_release(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.url = str;
    }

    @NotNull
    public String toString() {
        return "StorylyShareConfig(url=" + this.url + ", facebookAppID=" + this.facebookAppID + ')';
    }
}
