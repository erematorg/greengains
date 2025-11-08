package com.appsamurai.storyly.exoplayer2.common.ui;

import android.view.View;
import androidx.annotation.Nullable;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public final class AdOverlayInfo {
    public static final int PURPOSE_CLOSE_AD = 2;
    public static final int PURPOSE_CONTROLS = 1;
    public static final int PURPOSE_NOT_VISIBLE = 4;
    public static final int PURPOSE_OTHER = 3;
    public final int purpose;
    @Nullable
    public final String reasonDetail;
    public final View view;

    public static final class Builder {
        @Nullable
        private String detailedReason;
        private final int purpose;
        private final View view;

        public Builder(View view2, int i3) {
            this.view = view2;
            this.purpose = i3;
        }

        public AdOverlayInfo build() {
            return new AdOverlayInfo(this.view, this.purpose, this.detailedReason);
        }

        public Builder setDetailedReason(@Nullable String str) {
            this.detailedReason = str;
            return this;
        }
    }

    @Documented
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Purpose {
    }

    @Deprecated
    public AdOverlayInfo(View view2, int i3) {
        this(view2, i3, (String) null);
    }

    @Deprecated
    public AdOverlayInfo(View view2, int i3, @Nullable String str) {
        this.view = view2;
        this.purpose = i3;
        this.reasonDetail = str;
    }
}
