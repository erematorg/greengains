package com.appsamurai.storyly.exoplayer2.extractor.text.ssa;

import android.graphics.Color;
import android.graphics.PointF;
import android.text.TextUtils;
import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.mediacodec.h;
import com.google.common.primitives.Ints;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class SsaStyle {
    public static final int SSA_ALIGNMENT_BOTTOM_CENTER = 2;
    public static final int SSA_ALIGNMENT_BOTTOM_LEFT = 1;
    public static final int SSA_ALIGNMENT_BOTTOM_RIGHT = 3;
    public static final int SSA_ALIGNMENT_MIDDLE_CENTER = 5;
    public static final int SSA_ALIGNMENT_MIDDLE_LEFT = 4;
    public static final int SSA_ALIGNMENT_MIDDLE_RIGHT = 6;
    public static final int SSA_ALIGNMENT_TOP_CENTER = 8;
    public static final int SSA_ALIGNMENT_TOP_LEFT = 7;
    public static final int SSA_ALIGNMENT_TOP_RIGHT = 9;
    public static final int SSA_ALIGNMENT_UNKNOWN = -1;
    public static final int SSA_BORDER_STYLE_BOX = 3;
    public static final int SSA_BORDER_STYLE_OUTLINE = 1;
    public static final int SSA_BORDER_STYLE_UNKNOWN = -1;
    private static final String TAG = "SsaStyle";
    public final int alignment;
    public final boolean bold;
    public final int borderStyle;
    public final float fontSize;
    public final boolean italic;
    public final String name;
    @ColorInt
    @Nullable
    public final Integer outlineColor;
    @ColorInt
    @Nullable
    public final Integer primaryColor;
    public final boolean strikeout;
    public final boolean underline;

    public static final class Format {
        public final int alignmentIndex;
        public final int boldIndex;
        public final int borderStyleIndex;
        public final int fontSizeIndex;
        public final int italicIndex;
        public final int length;
        public final int nameIndex;
        public final int outlineColorIndex;
        public final int primaryColorIndex;
        public final int strikeoutIndex;
        public final int underlineIndex;

        private Format(int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13) {
            this.nameIndex = i3;
            this.alignmentIndex = i4;
            this.primaryColorIndex = i5;
            this.outlineColorIndex = i6;
            this.fontSizeIndex = i7;
            this.boldIndex = i8;
            this.italicIndex = i9;
            this.underlineIndex = i10;
            this.strikeoutIndex = i11;
            this.borderStyleIndex = i12;
            this.length = i13;
        }

        /* JADX WARNING: Can't fix incorrect switch cases order */
        @androidx.annotation.Nullable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static com.appsamurai.storyly.exoplayer2.extractor.text.ssa.SsaStyle.Format fromFormatLine(java.lang.String r18) {
            /*
                r0 = 1
                r1 = 7
                r2 = r18
                java.lang.String r2 = r2.substring(r1)
                java.lang.String r3 = ","
                java.lang.String[] r2 = android.text.TextUtils.split(r2, r3)
                r3 = -1
                r4 = 0
                r7 = r3
                r8 = r7
                r9 = r8
                r10 = r9
                r11 = r10
                r12 = r11
                r13 = r12
                r14 = r13
                r15 = r14
                r16 = r15
                r5 = r4
            L_0x001c:
                int r6 = r2.length
                if (r5 >= r6) goto L_0x00c5
                r6 = r2[r5]
                java.lang.String r6 = r6.trim()
                java.lang.String r6 = com.google.common.base.Ascii.toLowerCase((java.lang.String) r6)
                r6.getClass()
                int r17 = r6.hashCode()
                switch(r17) {
                    case -1178781136: goto L_0x009f;
                    case -1026963764: goto L_0x0093;
                    case -192095652: goto L_0x0087;
                    case -70925746: goto L_0x007c;
                    case 3029637: goto L_0x0071;
                    case 3373707: goto L_0x0066;
                    case 366554320: goto L_0x005b;
                    case 767321349: goto L_0x0050;
                    case 1767875043: goto L_0x0043;
                    case 1988365454: goto L_0x0036;
                    default: goto L_0x0033;
                }
            L_0x0033:
                r1 = r3
                goto L_0x00a9
            L_0x0036:
                java.lang.String r1 = "outlinecolour"
                boolean r1 = r6.equals(r1)
                if (r1 != 0) goto L_0x003f
                goto L_0x0033
            L_0x003f:
                r1 = 9
                goto L_0x00a9
            L_0x0043:
                java.lang.String r1 = "alignment"
                boolean r1 = r6.equals(r1)
                if (r1 != 0) goto L_0x004c
                goto L_0x0033
            L_0x004c:
                r1 = 8
                goto L_0x00a9
            L_0x0050:
                java.lang.String r1 = "borderstyle"
                boolean r1 = r6.equals(r1)
                if (r1 != 0) goto L_0x0059
                goto L_0x0033
            L_0x0059:
                r1 = 7
                goto L_0x00a9
            L_0x005b:
                java.lang.String r1 = "fontsize"
                boolean r1 = r6.equals(r1)
                if (r1 != 0) goto L_0x0064
                goto L_0x0033
            L_0x0064:
                r1 = 6
                goto L_0x00a9
            L_0x0066:
                java.lang.String r1 = "name"
                boolean r1 = r6.equals(r1)
                if (r1 != 0) goto L_0x006f
                goto L_0x0033
            L_0x006f:
                r1 = 5
                goto L_0x00a9
            L_0x0071:
                java.lang.String r1 = "bold"
                boolean r1 = r6.equals(r1)
                if (r1 != 0) goto L_0x007a
                goto L_0x0033
            L_0x007a:
                r1 = 4
                goto L_0x00a9
            L_0x007c:
                java.lang.String r1 = "primarycolour"
                boolean r1 = r6.equals(r1)
                if (r1 != 0) goto L_0x0085
                goto L_0x0033
            L_0x0085:
                r1 = 3
                goto L_0x00a9
            L_0x0087:
                java.lang.String r1 = "strikeout"
                boolean r1 = r6.equals(r1)
                if (r1 != 0) goto L_0x0091
                goto L_0x0033
            L_0x0091:
                r1 = 2
                goto L_0x00a9
            L_0x0093:
                java.lang.String r1 = "underline"
                boolean r1 = r6.equals(r1)
                if (r1 != 0) goto L_0x009d
                goto L_0x0033
            L_0x009d:
                r1 = r0
                goto L_0x00a9
            L_0x009f:
                java.lang.String r1 = "italic"
                boolean r1 = r6.equals(r1)
                if (r1 != 0) goto L_0x00a8
                goto L_0x0033
            L_0x00a8:
                r1 = r4
            L_0x00a9:
                switch(r1) {
                    case 0: goto L_0x00c0;
                    case 1: goto L_0x00be;
                    case 2: goto L_0x00bc;
                    case 3: goto L_0x00ba;
                    case 4: goto L_0x00b8;
                    case 5: goto L_0x00b6;
                    case 6: goto L_0x00b4;
                    case 7: goto L_0x00b1;
                    case 8: goto L_0x00af;
                    case 9: goto L_0x00ad;
                    default: goto L_0x00ac;
                }
            L_0x00ac:
                goto L_0x00c1
            L_0x00ad:
                r10 = r5
                goto L_0x00c1
            L_0x00af:
                r8 = r5
                goto L_0x00c1
            L_0x00b1:
                r16 = r5
                goto L_0x00c1
            L_0x00b4:
                r11 = r5
                goto L_0x00c1
            L_0x00b6:
                r7 = r5
                goto L_0x00c1
            L_0x00b8:
                r12 = r5
                goto L_0x00c1
            L_0x00ba:
                r9 = r5
                goto L_0x00c1
            L_0x00bc:
                r15 = r5
                goto L_0x00c1
            L_0x00be:
                r14 = r5
                goto L_0x00c1
            L_0x00c0:
                r13 = r5
            L_0x00c1:
                int r5 = r5 + r0
                r1 = 7
                goto L_0x001c
            L_0x00c5:
                if (r7 == r3) goto L_0x00d1
                com.appsamurai.storyly.exoplayer2.extractor.text.ssa.SsaStyle$Format r0 = new com.appsamurai.storyly.exoplayer2.extractor.text.ssa.SsaStyle$Format
                int r1 = r2.length
                r6 = r0
                r17 = r1
                r6.<init>(r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17)
                goto L_0x00d2
            L_0x00d1:
                r0 = 0
            L_0x00d2:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.extractor.text.ssa.SsaStyle.Format.fromFormatLine(java.lang.String):com.appsamurai.storyly.exoplayer2.extractor.text.ssa.SsaStyle$Format");
        }
    }

    public static final class Overrides {
        private static final Pattern ALIGNMENT_OVERRIDE_PATTERN = Pattern.compile("\\\\an(\\d+)");
        private static final Pattern BRACES_PATTERN = Pattern.compile("\\{([^}]*)\\}");
        private static final Pattern MOVE_PATTERN = Pattern.compile(Util.formatInvariant("\\\\move\\(%1$s,%1$s,(%1$s),(%1$s)(?:,%1$s,%1$s)?\\)", PADDED_DECIMAL_PATTERN));
        private static final String PADDED_DECIMAL_PATTERN = "\\s*\\d+(?:\\.\\d+)?\\s*";
        private static final Pattern POSITION_PATTERN = Pattern.compile(Util.formatInvariant("\\\\pos\\((%1$s),(%1$s)\\)", PADDED_DECIMAL_PATTERN));
        private static final String TAG = "SsaStyle.Overrides";
        public final int alignment;
        @Nullable
        public final PointF position;

        private Overrides(int i3, @Nullable PointF pointF) {
            this.alignment = i3;
            this.position = pointF;
        }

        private static int parseAlignmentOverride(String str) {
            Matcher matcher = ALIGNMENT_OVERRIDE_PATTERN.matcher(str);
            if (matcher.find()) {
                return SsaStyle.parseAlignment((String) Assertions.checkNotNull(matcher.group(1)));
            }
            return -1;
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(9:4|5|6|(1:8)|9|10|(2:12|18)(1:17)|15|1) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0021 */
        /* JADX WARNING: Removed duplicated region for block: B:12:0x0027  */
        /* JADX WARNING: Removed duplicated region for block: B:17:0x0009 A[SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static com.appsamurai.storyly.exoplayer2.extractor.text.ssa.SsaStyle.Overrides parseFromDialogue(java.lang.String r5) {
            /*
                java.util.regex.Pattern r0 = BRACES_PATTERN
                java.util.regex.Matcher r5 = r0.matcher(r5)
                r0 = -1
                r1 = 0
                r2 = r0
            L_0x0009:
                boolean r3 = r5.find()
                if (r3 == 0) goto L_0x0029
                r3 = 1
                java.lang.String r3 = r5.group(r3)
                java.lang.Object r3 = com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkNotNull(r3)
                java.lang.String r3 = (java.lang.String) r3
                android.graphics.PointF r4 = parsePosition(r3)     // Catch:{ RuntimeException -> 0x0021 }
                if (r4 == 0) goto L_0x0021
                r1 = r4
            L_0x0021:
                int r3 = parseAlignmentOverride(r3)     // Catch:{ RuntimeException -> 0x0009 }
                if (r3 == r0) goto L_0x0009
                r2 = r3
                goto L_0x0009
            L_0x0029:
                com.appsamurai.storyly.exoplayer2.extractor.text.ssa.SsaStyle$Overrides r5 = new com.appsamurai.storyly.exoplayer2.extractor.text.ssa.SsaStyle$Overrides
                r5.<init>(r2, r1)
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.extractor.text.ssa.SsaStyle.Overrides.parseFromDialogue(java.lang.String):com.appsamurai.storyly.exoplayer2.extractor.text.ssa.SsaStyle$Overrides");
        }

        @Nullable
        private static PointF parsePosition(String str) {
            String str2;
            String str3;
            Matcher matcher = POSITION_PATTERN.matcher(str);
            Matcher matcher2 = MOVE_PATTERN.matcher(str);
            boolean find = matcher.find();
            boolean find2 = matcher2.find();
            if (find) {
                if (find2) {
                    Log.i(TAG, "Override has both \\pos(x,y) and \\move(x1,y1,x2,y2); using \\pos values. override='" + str + "'");
                }
                str2 = matcher.group(1);
                str3 = matcher.group(2);
            } else if (!find2) {
                return null;
            } else {
                str2 = matcher2.group(1);
                str3 = matcher2.group(2);
            }
            return new PointF(Float.parseFloat(((String) Assertions.checkNotNull(str2)).trim()), Float.parseFloat(((String) Assertions.checkNotNull(str3)).trim()));
        }

        public static String stripStyleOverrides(String str) {
            return BRACES_PATTERN.matcher(str).replaceAll("");
        }
    }

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface SsaAlignment {
    }

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface SsaBorderStyle {
    }

    private SsaStyle(String str, int i3, @ColorInt @Nullable Integer num, @ColorInt @Nullable Integer num2, float f2, boolean z2, boolean z3, boolean z4, boolean z5, int i4) {
        this.name = str;
        this.alignment = i3;
        this.primaryColor = num;
        this.outlineColor = num2;
        this.fontSize = f2;
        this.bold = z2;
        this.italic = z3;
        this.underline = z4;
        this.strikeout = z5;
        this.borderStyle = i4;
    }

    @Nullable
    public static SsaStyle fromStyleLine(String str, Format format) {
        String str2 = str;
        Format format2 = format;
        Assertions.checkArgument(str2.startsWith("Style:"));
        String[] split = TextUtils.split(str2.substring(6), ",");
        int length = split.length;
        int i3 = format2.length;
        if (length != i3) {
            Log.w(TAG, Util.formatInvariant("Skipping malformed 'Style:' line (expected %s values, found %s): '%s'", Integer.valueOf(i3), Integer.valueOf(split.length), str2));
            return null;
        }
        try {
            String trim = split[format2.nameIndex].trim();
            int i4 = format2.alignmentIndex;
            int parseAlignment = i4 != -1 ? parseAlignment(split[i4].trim()) : -1;
            int i5 = format2.primaryColorIndex;
            Integer parseColor = i5 != -1 ? parseColor(split[i5].trim()) : null;
            int i6 = format2.outlineColorIndex;
            Integer parseColor2 = i6 != -1 ? parseColor(split[i6].trim()) : null;
            int i7 = format2.fontSizeIndex;
            float parseFontSize = i7 != -1 ? parseFontSize(split[i7].trim()) : -3.4028235E38f;
            int i8 = format2.boldIndex;
            boolean z2 = i8 != -1 && parseBooleanValue(split[i8].trim());
            int i9 = format2.italicIndex;
            boolean z3 = i9 != -1 && parseBooleanValue(split[i9].trim());
            int i10 = format2.underlineIndex;
            boolean z4 = i10 != -1 && parseBooleanValue(split[i10].trim());
            int i11 = format2.strikeoutIndex;
            boolean z5 = i11 != -1 && parseBooleanValue(split[i11].trim());
            int i12 = format2.borderStyleIndex;
            return new SsaStyle(trim, parseAlignment, parseColor, parseColor2, parseFontSize, z2, z3, z4, z5, i12 != -1 ? parseBorderStyle(split[i12].trim()) : -1);
        } catch (RuntimeException e3) {
            Log.w(TAG, "Skipping malformed 'Style:' line: '" + str2 + "'", e3);
            return null;
        }
    }

    private static boolean isValidAlignment(int i3) {
        switch (i3) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return true;
            default:
                return false;
        }
    }

    private static boolean isValidBorderStyle(int i3) {
        return i3 == 1 || i3 == 3;
    }

    /* access modifiers changed from: private */
    public static int parseAlignment(String str) {
        try {
            int parseInt = Integer.parseInt(str.trim());
            if (isValidAlignment(parseInt)) {
                return parseInt;
            }
        } catch (NumberFormatException unused) {
        }
        h.a("Ignoring unknown alignment: ", str, TAG);
        return -1;
    }

    private static boolean parseBooleanValue(String str) {
        try {
            int parseInt = Integer.parseInt(str);
            return parseInt == 1 || parseInt == -1;
        } catch (NumberFormatException e3) {
            Log.w(TAG, "Failed to parse boolean value: '" + str + "'", e3);
            return false;
        }
    }

    private static int parseBorderStyle(String str) {
        try {
            int parseInt = Integer.parseInt(str.trim());
            if (isValidBorderStyle(parseInt)) {
                return parseInt;
            }
        } catch (NumberFormatException unused) {
        }
        h.a("Ignoring unknown BorderStyle: ", str, TAG);
        return -1;
    }

    @ColorInt
    @Nullable
    public static Integer parseColor(String str) {
        try {
            long parseLong = str.startsWith("&H") ? Long.parseLong(str.substring(2), 16) : Long.parseLong(str);
            Assertions.checkArgument(parseLong <= 4294967295L);
            return Integer.valueOf(Color.argb(Ints.checkedCast(((parseLong >> 24) & 255) ^ 255), Ints.checkedCast(parseLong & 255), Ints.checkedCast((parseLong >> 8) & 255), Ints.checkedCast((parseLong >> 16) & 255)));
        } catch (IllegalArgumentException e3) {
            Log.w(TAG, "Failed to parse color expression: '" + str + "'", e3);
            return null;
        }
    }

    private static float parseFontSize(String str) {
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException e3) {
            Log.w(TAG, "Failed to parse font size: '" + str + "'", e3);
            return -3.4028235E38f;
        }
    }
}
