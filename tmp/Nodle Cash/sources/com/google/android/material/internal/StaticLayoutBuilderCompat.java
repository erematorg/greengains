package com.google.android.material.internal;

import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.lang.reflect.Constructor;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
final class StaticLayoutBuilderCompat {
    static final int DEFAULT_HYPHENATION_FREQUENCY = 1;
    static final float DEFAULT_LINE_SPACING_ADD = 0.0f;
    static final float DEFAULT_LINE_SPACING_MULTIPLIER = 1.0f;
    private static final String TEXT_DIRS_CLASS = "android.text.TextDirectionHeuristics";
    private static final String TEXT_DIR_CLASS = "android.text.TextDirectionHeuristic";
    private static final String TEXT_DIR_CLASS_LTR = "LTR";
    private static final String TEXT_DIR_CLASS_RTL = "RTL";
    @Nullable
    private static Constructor<StaticLayout> constructor;
    private static boolean initialized;
    @Nullable
    private static Object textDirection;
    private Layout.Alignment alignment;
    @Nullable
    private TextUtils.TruncateAt ellipsize;
    private int end;
    private int hyphenationFrequency;
    private boolean includePad;
    private boolean isRtl;
    private float lineSpacingAdd;
    private float lineSpacingMultiplier;
    private int maxLines;
    private final TextPaint paint;
    private CharSequence source;
    private int start = 0;
    @Nullable
    private StaticLayoutBuilderConfigurer staticLayoutBuilderConfigurer;
    private final int width;

    public static class StaticLayoutBuilderCompatException extends Exception {
        public StaticLayoutBuilderCompatException(Throwable th) {
            super("Error thrown initializing StaticLayout " + th.getMessage(), th);
        }
    }

    private StaticLayoutBuilderCompat(CharSequence charSequence, TextPaint textPaint, int i3) {
        this.source = charSequence;
        this.paint = textPaint;
        this.width = i3;
        this.end = charSequence.length();
        this.alignment = Layout.Alignment.ALIGN_NORMAL;
        this.maxLines = Integer.MAX_VALUE;
        this.lineSpacingAdd = 0.0f;
        this.lineSpacingMultiplier = 1.0f;
        this.hyphenationFrequency = DEFAULT_HYPHENATION_FREQUENCY;
        this.includePad = true;
        this.ellipsize = null;
    }

    private void createConstructorWithReflection() throws StaticLayoutBuilderCompatException {
        if (!initialized) {
            try {
                Class<TextDirectionHeuristic> cls = TextDirectionHeuristic.class;
                textDirection = this.isRtl ? TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR;
                Class cls2 = Integer.TYPE;
                Class cls3 = Float.TYPE;
                Constructor<StaticLayout> declaredConstructor = StaticLayout.class.getDeclaredConstructor(new Class[]{CharSequence.class, cls2, cls2, TextPaint.class, cls2, Layout.Alignment.class, cls, cls3, cls3, Boolean.TYPE, TextUtils.TruncateAt.class, cls2, cls2});
                constructor = declaredConstructor;
                declaredConstructor.setAccessible(true);
                initialized = true;
            } catch (Exception e3) {
                throw new StaticLayoutBuilderCompatException(e3);
            }
        }
    }

    @NonNull
    public static StaticLayoutBuilderCompat obtain(@NonNull CharSequence charSequence, @NonNull TextPaint textPaint, @IntRange(from = 0) int i3) {
        return new StaticLayoutBuilderCompat(charSequence, textPaint, i3);
    }

    public StaticLayout build() throws StaticLayoutBuilderCompatException {
        if (this.source == null) {
            this.source = "";
        }
        int max = Math.max(0, this.width);
        CharSequence charSequence = this.source;
        if (this.maxLines == 1) {
            charSequence = TextUtils.ellipsize(charSequence, this.paint, (float) max, this.ellipsize);
        }
        int min = Math.min(charSequence.length(), this.end);
        this.end = min;
        if (this.isRtl && this.maxLines == 1) {
            this.alignment = Layout.Alignment.ALIGN_OPPOSITE;
        }
        StaticLayout.Builder obtain = StaticLayout.Builder.obtain(charSequence, this.start, min, this.paint, max);
        obtain.setAlignment(this.alignment);
        obtain.setIncludePad(this.includePad);
        obtain.setTextDirection(this.isRtl ? TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR);
        TextUtils.TruncateAt truncateAt = this.ellipsize;
        if (truncateAt != null) {
            obtain.setEllipsize(truncateAt);
        }
        obtain.setMaxLines(this.maxLines);
        float f2 = this.lineSpacingAdd;
        if (!(f2 == 0.0f && this.lineSpacingMultiplier == 1.0f)) {
            obtain.setLineSpacing(f2, this.lineSpacingMultiplier);
        }
        if (this.maxLines > 1) {
            obtain.setHyphenationFrequency(this.hyphenationFrequency);
        }
        StaticLayoutBuilderConfigurer staticLayoutBuilderConfigurer2 = this.staticLayoutBuilderConfigurer;
        if (staticLayoutBuilderConfigurer2 != null) {
            staticLayoutBuilderConfigurer2.configure(obtain);
        }
        return obtain.build();
    }

    @NonNull
    @CanIgnoreReturnValue
    public StaticLayoutBuilderCompat setAlignment(@NonNull Layout.Alignment alignment2) {
        this.alignment = alignment2;
        return this;
    }

    @NonNull
    @CanIgnoreReturnValue
    public StaticLayoutBuilderCompat setEllipsize(@Nullable TextUtils.TruncateAt truncateAt) {
        this.ellipsize = truncateAt;
        return this;
    }

    @NonNull
    @CanIgnoreReturnValue
    public StaticLayoutBuilderCompat setEnd(@IntRange(from = 0) int i3) {
        this.end = i3;
        return this;
    }

    @NonNull
    @CanIgnoreReturnValue
    public StaticLayoutBuilderCompat setHyphenationFrequency(int i3) {
        this.hyphenationFrequency = i3;
        return this;
    }

    @NonNull
    @CanIgnoreReturnValue
    public StaticLayoutBuilderCompat setIncludePad(boolean z2) {
        this.includePad = z2;
        return this;
    }

    public StaticLayoutBuilderCompat setIsRtl(boolean z2) {
        this.isRtl = z2;
        return this;
    }

    @NonNull
    @CanIgnoreReturnValue
    public StaticLayoutBuilderCompat setLineSpacing(float f2, float f3) {
        this.lineSpacingAdd = f2;
        this.lineSpacingMultiplier = f3;
        return this;
    }

    @NonNull
    @CanIgnoreReturnValue
    public StaticLayoutBuilderCompat setMaxLines(@IntRange(from = 0) int i3) {
        this.maxLines = i3;
        return this;
    }

    @NonNull
    @CanIgnoreReturnValue
    public StaticLayoutBuilderCompat setStart(@IntRange(from = 0) int i3) {
        this.start = i3;
        return this;
    }

    @NonNull
    @CanIgnoreReturnValue
    public StaticLayoutBuilderCompat setStaticLayoutBuilderConfigurer(@Nullable StaticLayoutBuilderConfigurer staticLayoutBuilderConfigurer2) {
        this.staticLayoutBuilderConfigurer = staticLayoutBuilderConfigurer2;
        return this;
    }
}
