package com.bumptech.glide.load.engine.prefill;

import android.graphics.Bitmap;
import android.support.v4.media.session.a;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.bumptech.glide.util.Preconditions;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public final class PreFillType {
    @VisibleForTesting
    static final Bitmap.Config DEFAULT_CONFIG = Bitmap.Config.RGB_565;
    private final Bitmap.Config config;
    private final int height;
    private final int weight;
    private final int width;

    public static class Builder {
        private Bitmap.Config config;
        private final int height;
        private int weight;
        private final int width;

        public Builder(int i3) {
            this(i3, i3);
        }

        public PreFillType build() {
            return new PreFillType(this.width, this.height, this.config, this.weight);
        }

        public Bitmap.Config getConfig() {
            return this.config;
        }

        public Builder setConfig(@Nullable Bitmap.Config config2) {
            this.config = config2;
            return this;
        }

        public Builder setWeight(int i3) {
            if (i3 > 0) {
                this.weight = i3;
                return this;
            }
            throw new IllegalArgumentException("Weight must be > 0");
        }

        public Builder(int i3, int i4) {
            this.weight = 1;
            if (i3 <= 0) {
                throw new IllegalArgumentException("Width must be > 0");
            } else if (i4 > 0) {
                this.width = i3;
                this.height = i4;
            } else {
                throw new IllegalArgumentException("Height must be > 0");
            }
        }
    }

    public PreFillType(int i3, int i4, Bitmap.Config config2, int i5) {
        this.config = (Bitmap.Config) Preconditions.checkNotNull(config2, "Config must not be null");
        this.width = i3;
        this.height = i4;
        this.weight = i5;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PreFillType)) {
            return false;
        }
        PreFillType preFillType = (PreFillType) obj;
        return this.height == preFillType.height && this.width == preFillType.width && this.weight == preFillType.weight && this.config == preFillType.config;
    }

    public Bitmap.Config getConfig() {
        return this.config;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWeight() {
        return this.weight;
    }

    public int getWidth() {
        return this.width;
    }

    public int hashCode() {
        return ((this.config.hashCode() + (((this.width * 31) + this.height) * 31)) * 31) + this.weight;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("PreFillSize{width=");
        sb.append(this.width);
        sb.append(", height=");
        sb.append(this.height);
        sb.append(", config=");
        sb.append(this.config);
        sb.append(", weight=");
        return a.p(sb, this.weight, AbstractJsonLexerKt.END_OBJ);
    }
}
