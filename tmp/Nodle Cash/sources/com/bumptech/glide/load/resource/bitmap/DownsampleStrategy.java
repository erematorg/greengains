package com.bumptech.glide.load.resource.bitmap;

import com.bumptech.glide.load.Option;

public abstract class DownsampleStrategy {
    public static final DownsampleStrategy AT_LEAST = new AtLeast();
    public static final DownsampleStrategy AT_MOST = new AtMost();
    public static final DownsampleStrategy CENTER_INSIDE = new CenterInside();
    public static final DownsampleStrategy CENTER_OUTSIDE;
    public static final DownsampleStrategy DEFAULT;
    public static final DownsampleStrategy FIT_CENTER = new FitCenter();
    static final boolean IS_BITMAP_FACTORY_SCALING_SUPPORTED = true;
    public static final DownsampleStrategy NONE = new None();
    public static final Option<DownsampleStrategy> OPTION;

    public static class AtLeast extends DownsampleStrategy {
        public SampleSizeRounding getSampleSizeRounding(int i3, int i4, int i5, int i6) {
            return SampleSizeRounding.QUALITY;
        }

        public float getScaleFactor(int i3, int i4, int i5, int i6) {
            int min = Math.min(i4 / i6, i3 / i5);
            if (min == 0) {
                return 1.0f;
            }
            return 1.0f / ((float) Integer.highestOneBit(min));
        }
    }

    public static class AtMost extends DownsampleStrategy {
        public SampleSizeRounding getSampleSizeRounding(int i3, int i4, int i5, int i6) {
            return SampleSizeRounding.MEMORY;
        }

        public float getScaleFactor(int i3, int i4, int i5, int i6) {
            int ceil = (int) Math.ceil((double) Math.max(((float) i4) / ((float) i6), ((float) i3) / ((float) i5)));
            int i7 = 1;
            int max = Math.max(1, Integer.highestOneBit(ceil));
            if (max >= ceil) {
                i7 = 0;
            }
            return 1.0f / ((float) (max << i7));
        }
    }

    public static class CenterInside extends DownsampleStrategy {
        public SampleSizeRounding getSampleSizeRounding(int i3, int i4, int i5, int i6) {
            return getScaleFactor(i3, i4, i5, i6) == 1.0f ? SampleSizeRounding.QUALITY : DownsampleStrategy.FIT_CENTER.getSampleSizeRounding(i3, i4, i5, i6);
        }

        public float getScaleFactor(int i3, int i4, int i5, int i6) {
            return Math.min(1.0f, DownsampleStrategy.FIT_CENTER.getScaleFactor(i3, i4, i5, i6));
        }
    }

    public static class CenterOutside extends DownsampleStrategy {
        public SampleSizeRounding getSampleSizeRounding(int i3, int i4, int i5, int i6) {
            return SampleSizeRounding.QUALITY;
        }

        public float getScaleFactor(int i3, int i4, int i5, int i6) {
            return Math.max(((float) i5) / ((float) i3), ((float) i6) / ((float) i4));
        }
    }

    public static class FitCenter extends DownsampleStrategy {
        public SampleSizeRounding getSampleSizeRounding(int i3, int i4, int i5, int i6) {
            return DownsampleStrategy.IS_BITMAP_FACTORY_SCALING_SUPPORTED ? SampleSizeRounding.QUALITY : SampleSizeRounding.MEMORY;
        }

        public float getScaleFactor(int i3, int i4, int i5, int i6) {
            if (DownsampleStrategy.IS_BITMAP_FACTORY_SCALING_SUPPORTED) {
                return Math.min(((float) i5) / ((float) i3), ((float) i6) / ((float) i4));
            }
            int max = Math.max(i4 / i6, i3 / i5);
            if (max == 0) {
                return 1.0f;
            }
            return 1.0f / ((float) Integer.highestOneBit(max));
        }
    }

    public static class None extends DownsampleStrategy {
        public SampleSizeRounding getSampleSizeRounding(int i3, int i4, int i5, int i6) {
            return SampleSizeRounding.QUALITY;
        }

        public float getScaleFactor(int i3, int i4, int i5, int i6) {
            return 1.0f;
        }
    }

    public enum SampleSizeRounding {
        MEMORY,
        QUALITY
    }

    static {
        CenterOutside centerOutside = new CenterOutside();
        CENTER_OUTSIDE = centerOutside;
        DEFAULT = centerOutside;
        OPTION = Option.memory("com.bumptech.glide.load.resource.bitmap.Downsampler.DownsampleStrategy", centerOutside);
    }

    public abstract SampleSizeRounding getSampleSizeRounding(int i3, int i4, int i5, int i6);

    public abstract float getScaleFactor(int i3, int i4, int i5, int i6);
}
