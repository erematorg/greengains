package com.appsamurai.storyly.config;

import androidx.annotation.Keep;
import com.appsamurai.storyly.util.formatter.b;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0017\b\b\u0018\u00002\u00020\u0001:\u0001/B7\b\u0000\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0013\u001a\u00020\u0006\u0012\u0006\u0010\u0014\u001a\u00020\u0006\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\f\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b-\u0010.J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u0002HÀ\u0003¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\t\u001a\u00020\u0006HÀ\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u000b\u001a\u00020\u0006HÀ\u0003¢\u0006\u0004\b\n\u0010\bJ\u0012\u0010\u000f\u001a\u0004\u0018\u00010\fHÀ\u0003¢\u0006\u0004\b\r\u0010\u000eJ\u0012\u0010\u0011\u001a\u0004\u0018\u00010\fHÀ\u0003¢\u0006\u0004\b\u0010\u0010\u000eJA\u0010\u0017\u001a\u00020\u00002\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0013\u001a\u00020\u00062\b\b\u0002\u0010\u0014\u001a\u00020\u00062\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\fHÆ\u0001J\t\u0010\u0018\u001a\u00020\fHÖ\u0001J\t\u0010\u001a\u001a\u00020\u0019HÖ\u0001J\u0013\u0010\u001c\u001a\u00020\u00062\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003R$\u0010\u0012\u001a\u0004\u0018\u00010\u00028\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\u001d\u001a\u0004\b\u001e\u0010\u0004\"\u0004\b\u001f\u0010 R\"\u0010\u0013\u001a\u00020\u00068\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b\u0013\u0010!\u001a\u0004\b\"\u0010\b\"\u0004\b#\u0010$R\"\u0010\u0014\u001a\u00020\u00068\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010!\u001a\u0004\b%\u0010\b\"\u0004\b&\u0010$R$\u0010\u0015\u001a\u0004\u0018\u00010\f8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b\u0015\u0010'\u001a\u0004\b(\u0010\u000e\"\u0004\b)\u0010*R$\u0010\u0016\u001a\u0004\u0018\u00010\f8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010'\u001a\u0004\b+\u0010\u000e\"\u0004\b,\u0010*¨\u00060"}, d2 = {"Lcom/appsamurai/storyly/config/StorylyProductConfig;", "", "Lcom/appsamurai/storyly/util/formatter/b;", "component1$storyly_release", "()Lcom/appsamurai/storyly/util/formatter/b;", "component1", "", "component2$storyly_release", "()Z", "component2", "component3$storyly_release", "component3", "", "component4$storyly_release", "()Ljava/lang/String;", "component4", "component5$storyly_release", "component5", "priceFormatter", "isFallbackEnabled", "isCartEnabled", "country", "language", "copy", "toString", "", "hashCode", "other", "equals", "Lcom/appsamurai/storyly/util/formatter/b;", "getPriceFormatter$storyly_release", "setPriceFormatter$storyly_release", "(Lcom/appsamurai/storyly/util/formatter/b;)V", "Z", "isFallbackEnabled$storyly_release", "setFallbackEnabled$storyly_release", "(Z)V", "isCartEnabled$storyly_release", "setCartEnabled$storyly_release", "Ljava/lang/String;", "getCountry$storyly_release", "setCountry$storyly_release", "(Ljava/lang/String;)V", "getLanguage$storyly_release", "setLanguage$storyly_release", "<init>", "(Lcom/appsamurai/storyly/util/formatter/b;ZZLjava/lang/String;Ljava/lang/String;)V", "Builder", "storyly_release"}, k = 1, mv = {1, 5, 1})
@Keep
public final class StorylyProductConfig {
    @Nullable
    private String country;
    private boolean isCartEnabled;
    private boolean isFallbackEnabled;
    @Nullable
    private String language;
    @Nullable
    private b priceFormatter;

    @Keep
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0016\u0010\u0017J\u000e\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005J\u000e\u0010\b\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005J\u0010\u0010\u000b\u001a\u00020\u00002\b\u0010\n\u001a\u0004\u0018\u00010\tJ\u0010\u0010\r\u001a\u00020\u00002\b\u0010\f\u001a\u0004\u0018\u00010\tJ\u0006\u0010\u000f\u001a\u00020\u000eR\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0012\u001a\u00020\u00058\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0014\u001a\u00020\u00058\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010\u0013R\u0018\u0010\n\u001a\u0004\u0018\u00010\t8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\n\u0010\u0015R\u0018\u0010\f\u001a\u0004\u0018\u00010\t8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\f\u0010\u0015¨\u0006\u0018"}, d2 = {"Lcom/appsamurai/storyly/config/StorylyProductConfig$Builder;", "", "Lcom/appsamurai/storyly/util/formatter/b;", "formatter", "setPriceFormatter", "", "isEnabled", "setFallbackAvailability", "setCartAvailability", "", "country", "setProductFeedCountry", "language", "setProductFeedLanguage", "Lcom/appsamurai/storyly/config/StorylyProductConfig;", "build", "priceFormatter", "Lcom/appsamurai/storyly/util/formatter/b;", "isFallbackEnabled", "Z", "isCartEnabled", "Ljava/lang/String;", "<init>", "()V", "storyly_release"}, k = 1, mv = {1, 5, 1})
    public static final class Builder {
        @Nullable
        private String country;
        private boolean isCartEnabled;
        private boolean isFallbackEnabled = true;
        @Nullable
        private String language;
        @Nullable
        private b priceFormatter;

        @NotNull
        public final StorylyProductConfig build() {
            return new StorylyProductConfig(this.priceFormatter, this.isFallbackEnabled, this.isCartEnabled, this.country, this.language);
        }

        @NotNull
        public final Builder setCartAvailability(boolean z2) {
            this.isCartEnabled = z2;
            return this;
        }

        @NotNull
        public final Builder setFallbackAvailability(boolean z2) {
            this.isFallbackEnabled = z2;
            return this;
        }

        @NotNull
        public final Builder setPriceFormatter(@NotNull b bVar) {
            Intrinsics.checkNotNullParameter(bVar, "formatter");
            this.priceFormatter = bVar;
            return this;
        }

        @NotNull
        public final Builder setProductFeedCountry(@Nullable String str) {
            this.country = str;
            return this;
        }

        @NotNull
        public final Builder setProductFeedLanguage(@Nullable String str) {
            this.language = str;
            return this;
        }
    }

    public StorylyProductConfig(@Nullable b bVar, boolean z2, boolean z3, @Nullable String str, @Nullable String str2) {
        this.priceFormatter = bVar;
        this.isFallbackEnabled = z2;
        this.isCartEnabled = z3;
        this.country = str;
        this.language = str2;
    }

    public static /* synthetic */ StorylyProductConfig copy$default(StorylyProductConfig storylyProductConfig, b bVar, boolean z2, boolean z3, String str, String str2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            bVar = storylyProductConfig.priceFormatter;
        }
        if ((i3 & 2) != 0) {
            z2 = storylyProductConfig.isFallbackEnabled;
        }
        boolean z4 = z2;
        if ((i3 & 4) != 0) {
            z3 = storylyProductConfig.isCartEnabled;
        }
        boolean z5 = z3;
        if ((i3 & 8) != 0) {
            str = storylyProductConfig.country;
        }
        String str3 = str;
        if ((i3 & 16) != 0) {
            str2 = storylyProductConfig.language;
        }
        return storylyProductConfig.copy(bVar, z4, z5, str3, str2);
    }

    @Nullable
    public final b component1$storyly_release() {
        return this.priceFormatter;
    }

    public final boolean component2$storyly_release() {
        return this.isFallbackEnabled;
    }

    public final boolean component3$storyly_release() {
        return this.isCartEnabled;
    }

    @Nullable
    public final String component4$storyly_release() {
        return this.country;
    }

    @Nullable
    public final String component5$storyly_release() {
        return this.language;
    }

    @NotNull
    public final StorylyProductConfig copy(@Nullable b bVar, boolean z2, boolean z3, @Nullable String str, @Nullable String str2) {
        return new StorylyProductConfig(bVar, z2, z3, str, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StorylyProductConfig)) {
            return false;
        }
        StorylyProductConfig storylyProductConfig = (StorylyProductConfig) obj;
        return Intrinsics.areEqual((Object) this.priceFormatter, (Object) storylyProductConfig.priceFormatter) && this.isFallbackEnabled == storylyProductConfig.isFallbackEnabled && this.isCartEnabled == storylyProductConfig.isCartEnabled && Intrinsics.areEqual((Object) this.country, (Object) storylyProductConfig.country) && Intrinsics.areEqual((Object) this.language, (Object) storylyProductConfig.language);
    }

    @Nullable
    public final String getCountry$storyly_release() {
        return this.country;
    }

    @Nullable
    public final String getLanguage$storyly_release() {
        return this.language;
    }

    @Nullable
    public final b getPriceFormatter$storyly_release() {
        return this.priceFormatter;
    }

    public int hashCode() {
        b bVar = this.priceFormatter;
        int i3 = 0;
        int hashCode = (bVar == null ? 0 : bVar.hashCode()) * 31;
        boolean z2 = this.isFallbackEnabled;
        boolean z3 = true;
        if (z2) {
            z2 = true;
        }
        int i4 = (hashCode + (z2 ? 1 : 0)) * 31;
        boolean z4 = this.isCartEnabled;
        if (!z4) {
            z3 = z4;
        }
        int i5 = (i4 + (z3 ? 1 : 0)) * 31;
        String str = this.country;
        int hashCode2 = (i5 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.language;
        if (str2 != null) {
            i3 = str2.hashCode();
        }
        return hashCode2 + i3;
    }

    public final boolean isCartEnabled$storyly_release() {
        return this.isCartEnabled;
    }

    public final boolean isFallbackEnabled$storyly_release() {
        return this.isFallbackEnabled;
    }

    public final void setCartEnabled$storyly_release(boolean z2) {
        this.isCartEnabled = z2;
    }

    public final void setCountry$storyly_release(@Nullable String str) {
        this.country = str;
    }

    public final void setFallbackEnabled$storyly_release(boolean z2) {
        this.isFallbackEnabled = z2;
    }

    public final void setLanguage$storyly_release(@Nullable String str) {
        this.language = str;
    }

    public final void setPriceFormatter$storyly_release(@Nullable b bVar) {
        this.priceFormatter = bVar;
    }

    @NotNull
    public String toString() {
        return "StorylyProductConfig(priceFormatter=" + this.priceFormatter + ", isFallbackEnabled=" + this.isFallbackEnabled + ", isCartEnabled=" + this.isCartEnabled + ", country=" + this.country + ", language=" + this.language + ')';
    }
}
