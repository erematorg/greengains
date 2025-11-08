package com.google.android.material.carousel;

import android.support.v4.media.session.a;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.animation.AnimationUtils;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class KeylineState {
    private final int firstFocalKeylineIndex;
    private final float itemSize;
    private final List<Keyline> keylines;
    private final int lastFocalKeylineIndex;

    public static final class Builder {
        private static final int NO_INDEX = -1;
        private static final float UNKNOWN_LOC = Float.MIN_VALUE;
        private final float availableSpace;
        private int firstFocalKeylineIndex = -1;
        private final float itemSize;
        private int lastFocalKeylineIndex = -1;
        private float lastKeylineMaskedSize = 0.0f;
        private int latestAnchorKeylineIndex = -1;
        private Keyline tmpFirstFocalKeyline;
        private final List<Keyline> tmpKeylines = new ArrayList();
        private Keyline tmpLastFocalKeyline;

        public Builder(float f2, float f3) {
            this.itemSize = f2;
            this.availableSpace = f3;
        }

        private static float calculateKeylineLocationForItemPosition(float f2, float f3, int i3, int i4) {
            return (((float) i4) * f3) + (f2 - (((float) i3) * f3));
        }

        @NonNull
        @CanIgnoreReturnValue
        public Builder addAnchorKeyline(float f2, @FloatRange(from = 0.0d, to = 1.0d) float f3, float f4) {
            return addKeyline(f2, f3, f4, false, true);
        }

        @NonNull
        @CanIgnoreReturnValue
        public Builder addKeyline(float f2, @FloatRange(from = 0.0d, to = 1.0d) float f3, float f4, boolean z2) {
            return addKeyline(f2, f3, f4, z2, false);
        }

        @NonNull
        @CanIgnoreReturnValue
        public Builder addKeylineRange(float f2, @FloatRange(from = 0.0d, to = 1.0d) float f3, float f4, int i3) {
            return addKeylineRange(f2, f3, f4, i3, false);
        }

        @NonNull
        public KeylineState build() {
            if (this.tmpFirstFocalKeyline != null) {
                ArrayList arrayList = new ArrayList();
                for (int i3 = 0; i3 < this.tmpKeylines.size(); i3++) {
                    Keyline keyline = this.tmpKeylines.get(i3);
                    arrayList.add(new Keyline(calculateKeylineLocationForItemPosition(this.tmpFirstFocalKeyline.locOffset, this.itemSize, this.firstFocalKeylineIndex, i3), keyline.locOffset, keyline.mask, keyline.maskedItemSize, keyline.isAnchor, keyline.cutoff, keyline.leftOrTopPaddingShift, keyline.rightOrBottomPaddingShift));
                }
                return new KeylineState(this.itemSize, arrayList, this.firstFocalKeylineIndex, this.lastFocalKeylineIndex);
            }
            throw new IllegalStateException("There must be a keyline marked as focal.");
        }

        @NonNull
        @CanIgnoreReturnValue
        public Builder addKeyline(float f2, @FloatRange(from = 0.0d, to = 1.0d) float f3, float f4) {
            return addKeyline(f2, f3, f4, false);
        }

        @NonNull
        @CanIgnoreReturnValue
        public Builder addKeylineRange(float f2, @FloatRange(from = 0.0d, to = 1.0d) float f3, float f4, int i3, boolean z2) {
            if (i3 > 0 && f4 > 0.0f) {
                for (int i4 = 0; i4 < i3; i4++) {
                    addKeyline((((float) i4) * f4) + f2, f3, f4, z2);
                }
            }
            return this;
        }

        @NonNull
        @CanIgnoreReturnValue
        public Builder addKeyline(float f2, @FloatRange(from = 0.0d, to = 1.0d) float f3, float f4, boolean z2, boolean z3, float f5, float f6, float f7) {
            if (f4 <= 0.0f) {
                return this;
            }
            if (z3) {
                if (!z2) {
                    int i3 = this.latestAnchorKeylineIndex;
                    if (i3 == -1 || i3 == 0) {
                        this.latestAnchorKeylineIndex = this.tmpKeylines.size();
                    } else {
                        throw new IllegalArgumentException("Anchor keylines must be either the first or last keyline.");
                    }
                } else {
                    throw new IllegalArgumentException("Anchor keylines cannot be focal.");
                }
            }
            Keyline keyline = new Keyline(Float.MIN_VALUE, f2, f3, f4, z3, f5, f6, f7);
            if (z2) {
                if (this.tmpFirstFocalKeyline == null) {
                    this.tmpFirstFocalKeyline = keyline;
                    this.firstFocalKeylineIndex = this.tmpKeylines.size();
                }
                if (this.lastFocalKeylineIndex != -1 && this.tmpKeylines.size() - this.lastFocalKeylineIndex > 1) {
                    throw new IllegalArgumentException("Keylines marked as focal must be placed next to each other. There cannot be non-focal keylines between focal keylines.");
                } else if (f4 == this.tmpFirstFocalKeyline.maskedItemSize) {
                    this.tmpLastFocalKeyline = keyline;
                    this.lastFocalKeylineIndex = this.tmpKeylines.size();
                } else {
                    throw new IllegalArgumentException("Keylines that are marked as focal must all have the same masked item size.");
                }
            } else if (this.tmpFirstFocalKeyline == null && keyline.maskedItemSize < this.lastKeylineMaskedSize) {
                throw new IllegalArgumentException("Keylines before the first focal keyline must be ordered by incrementing masked item size.");
            } else if (this.tmpLastFocalKeyline != null && keyline.maskedItemSize > this.lastKeylineMaskedSize) {
                throw new IllegalArgumentException("Keylines after the last focal keyline must be ordered by decreasing masked item size.");
            }
            this.lastKeylineMaskedSize = keyline.maskedItemSize;
            this.tmpKeylines.add(keyline);
            return this;
        }

        @NonNull
        @CanIgnoreReturnValue
        public Builder addKeyline(float f2, @FloatRange(from = 0.0d, to = 1.0d) float f3, float f4, boolean z2, boolean z3, float f5) {
            return addKeyline(f2, f3, f4, z2, z3, f5, 0.0f, 0.0f);
        }

        @NonNull
        @CanIgnoreReturnValue
        public Builder addKeyline(float f2, @FloatRange(from = 0.0d, to = 1.0d) float f3, float f4, boolean z2, boolean z3) {
            float f5;
            float f6 = f4 / 2.0f;
            float f7 = f2 - f6;
            float f8 = f6 + f2;
            float f9 = this.availableSpace;
            if (f8 > f9) {
                f5 = Math.abs(f8 - Math.max(f8 - f4, f9));
            } else {
                f5 = 0.0f;
                if (f7 < 0.0f) {
                    f5 = Math.abs(f7 - Math.min(f7 + f4, 0.0f));
                }
            }
            return addKeyline(f2, f3, f4, z2, z3, f5);
        }
    }

    public static final class Keyline {
        final float cutoff;
        final boolean isAnchor;
        final float leftOrTopPaddingShift;
        final float loc;
        final float locOffset;
        final float mask;
        final float maskedItemSize;
        final float rightOrBottomPaddingShift;

        public Keyline(float f2, float f3, float f4, float f5) {
            this(f2, f3, f4, f5, false, 0.0f, 0.0f, 0.0f);
        }

        public static Keyline lerp(Keyline keyline, Keyline keyline2, @FloatRange(from = 0.0d, to = 1.0d) float f2) {
            return new Keyline(AnimationUtils.lerp(keyline.loc, keyline2.loc, f2), AnimationUtils.lerp(keyline.locOffset, keyline2.locOffset, f2), AnimationUtils.lerp(keyline.mask, keyline2.mask, f2), AnimationUtils.lerp(keyline.maskedItemSize, keyline2.maskedItemSize, f2));
        }

        public Keyline(float f2, float f3, float f4, float f5, boolean z2, float f6, float f7, float f8) {
            this.loc = f2;
            this.locOffset = f3;
            this.mask = f4;
            this.maskedItemSize = f5;
            this.isAnchor = z2;
            this.cutoff = f6;
            this.leftOrTopPaddingShift = f7;
            this.rightOrBottomPaddingShift = f8;
        }
    }

    public static KeylineState lerp(KeylineState keylineState, KeylineState keylineState2, float f2) {
        if (keylineState.getItemSize() == keylineState2.getItemSize()) {
            List<Keyline> keylines2 = keylineState.getKeylines();
            List<Keyline> keylines3 = keylineState2.getKeylines();
            if (keylines2.size() == keylines3.size()) {
                ArrayList arrayList = new ArrayList();
                for (int i3 = 0; i3 < keylineState.getKeylines().size(); i3++) {
                    arrayList.add(Keyline.lerp(keylines2.get(i3), keylines3.get(i3), f2));
                }
                return new KeylineState(keylineState.getItemSize(), arrayList, AnimationUtils.lerp(keylineState.getFirstFocalKeylineIndex(), keylineState2.getFirstFocalKeylineIndex(), f2), AnimationUtils.lerp(keylineState.getLastFocalKeylineIndex(), keylineState2.getLastFocalKeylineIndex(), f2));
            }
            throw new IllegalArgumentException("Keylines being linearly interpolated must have the same number of keylines.");
        }
        throw new IllegalArgumentException("Keylines being linearly interpolated must have the same item size.");
    }

    public static KeylineState reverse(KeylineState keylineState, float f2) {
        Builder builder = new Builder(keylineState.getItemSize(), f2);
        float f3 = (f2 - keylineState.getLastKeyline().locOffset) - (keylineState.getLastKeyline().maskedItemSize / 2.0f);
        int size = keylineState.getKeylines().size() - 1;
        while (size >= 0) {
            Keyline keyline = keylineState.getKeylines().get(size);
            builder.addKeyline((keyline.maskedItemSize / 2.0f) + f3, keyline.mask, keyline.maskedItemSize, size >= keylineState.getFirstFocalKeylineIndex() && size <= keylineState.getLastFocalKeylineIndex(), keyline.isAnchor);
            f3 += keyline.maskedItemSize;
            size--;
        }
        return builder.build();
    }

    public Keyline getFirstFocalKeyline() {
        return this.keylines.get(this.firstFocalKeylineIndex);
    }

    public int getFirstFocalKeylineIndex() {
        return this.firstFocalKeylineIndex;
    }

    public Keyline getFirstKeyline() {
        return this.keylines.get(0);
    }

    @Nullable
    public Keyline getFirstNonAnchorKeyline() {
        for (int i3 = 0; i3 < this.keylines.size(); i3++) {
            Keyline keyline = this.keylines.get(i3);
            if (!keyline.isAnchor) {
                return keyline;
            }
        }
        return null;
    }

    public List<Keyline> getFocalKeylines() {
        return this.keylines.subList(this.firstFocalKeylineIndex, this.lastFocalKeylineIndex + 1);
    }

    public float getItemSize() {
        return this.itemSize;
    }

    public List<Keyline> getKeylines() {
        return this.keylines;
    }

    public Keyline getLastFocalKeyline() {
        return this.keylines.get(this.lastFocalKeylineIndex);
    }

    public int getLastFocalKeylineIndex() {
        return this.lastFocalKeylineIndex;
    }

    public Keyline getLastKeyline() {
        return (Keyline) a.h(this.keylines, 1);
    }

    @Nullable
    public Keyline getLastNonAnchorKeyline() {
        for (int size = this.keylines.size() - 1; size >= 0; size--) {
            Keyline keyline = this.keylines.get(size);
            if (!keyline.isAnchor) {
                return keyline;
            }
        }
        return null;
    }

    public int getNumberOfNonAnchorKeylines() {
        int i3 = 0;
        for (Keyline keyline : this.keylines) {
            if (keyline.isAnchor) {
                i3++;
            }
        }
        return this.keylines.size() - i3;
    }

    private KeylineState(float f2, List<Keyline> list, int i3, int i4) {
        this.itemSize = f2;
        this.keylines = Collections.unmodifiableList(list);
        this.firstFocalKeylineIndex = i3;
        this.lastFocalKeylineIndex = i4;
    }
}
