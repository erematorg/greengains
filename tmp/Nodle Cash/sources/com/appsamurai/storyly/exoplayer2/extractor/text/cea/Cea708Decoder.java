package com.appsamurai.storyly.exoplayer2.extractor.text.cea;

import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.text.Cue;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.CodecSpecificDataUtil;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableBitArray;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.core.r;
import com.appsamurai.storyly.exoplayer2.extractor.text.Subtitle;
import com.appsamurai.storyly.exoplayer2.extractor.text.SubtitleDecoderException;
import com.appsamurai.storyly.exoplayer2.extractor.text.SubtitleInputBuffer;
import com.appsamurai.storyly.exoplayer2.extractor.text.SubtitleOutputBuffer;
import com.google.common.primitives.SignedBytes;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import kotlin.text.Typography;
import okio.Utf8;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.msgpack.core.MessagePack;

public final class Cea708Decoder extends CeaDecoder {
    private static final int CC_VALID_FLAG = 4;
    private static final int CHARACTER_BIG_CARONS = 42;
    private static final int CHARACTER_BIG_OE = 44;
    private static final int CHARACTER_BOLD_BULLET = 53;
    private static final int CHARACTER_CLOSE_DOUBLE_QUOTE = 52;
    private static final int CHARACTER_CLOSE_SINGLE_QUOTE = 50;
    private static final int CHARACTER_DIAERESIS_Y = 63;
    private static final int CHARACTER_ELLIPSIS = 37;
    private static final int CHARACTER_FIVE_EIGHTHS = 120;
    private static final int CHARACTER_HORIZONTAL_BORDER = 125;
    private static final int CHARACTER_LOWER_LEFT_BORDER = 124;
    private static final int CHARACTER_LOWER_RIGHT_BORDER = 126;
    private static final int CHARACTER_MN = 127;
    private static final int CHARACTER_NBTSP = 33;
    private static final int CHARACTER_ONE_EIGHTH = 118;
    private static final int CHARACTER_OPEN_DOUBLE_QUOTE = 51;
    private static final int CHARACTER_OPEN_SINGLE_QUOTE = 49;
    private static final int CHARACTER_SEVEN_EIGHTHS = 121;
    private static final int CHARACTER_SM = 61;
    private static final int CHARACTER_SMALL_CARONS = 58;
    private static final int CHARACTER_SMALL_OE = 60;
    private static final int CHARACTER_SOLID_BLOCK = 48;
    private static final int CHARACTER_THREE_EIGHTHS = 119;
    private static final int CHARACTER_TM = 57;
    private static final int CHARACTER_TSP = 32;
    private static final int CHARACTER_UPPER_LEFT_BORDER = 127;
    private static final int CHARACTER_UPPER_RIGHT_BORDER = 123;
    private static final int CHARACTER_VERTICAL_BORDER = 122;
    private static final int COMMAND_BS = 8;
    private static final int COMMAND_CLW = 136;
    private static final int COMMAND_CR = 13;
    private static final int COMMAND_CW0 = 128;
    private static final int COMMAND_CW1 = 129;
    private static final int COMMAND_CW2 = 130;
    private static final int COMMAND_CW3 = 131;
    private static final int COMMAND_CW4 = 132;
    private static final int COMMAND_CW5 = 133;
    private static final int COMMAND_CW6 = 134;
    private static final int COMMAND_CW7 = 135;
    private static final int COMMAND_DF0 = 152;
    private static final int COMMAND_DF1 = 153;
    private static final int COMMAND_DF2 = 154;
    private static final int COMMAND_DF3 = 155;
    private static final int COMMAND_DF4 = 156;
    private static final int COMMAND_DF5 = 157;
    private static final int COMMAND_DF6 = 158;
    private static final int COMMAND_DF7 = 159;
    private static final int COMMAND_DLC = 142;
    private static final int COMMAND_DLW = 140;
    private static final int COMMAND_DLY = 141;
    private static final int COMMAND_DSW = 137;
    private static final int COMMAND_ETX = 3;
    private static final int COMMAND_EXT1 = 16;
    private static final int COMMAND_EXT1_END = 23;
    private static final int COMMAND_EXT1_START = 17;
    private static final int COMMAND_FF = 12;
    private static final int COMMAND_HCR = 14;
    private static final int COMMAND_HDW = 138;
    private static final int COMMAND_NUL = 0;
    private static final int COMMAND_P16_END = 31;
    private static final int COMMAND_P16_START = 24;
    private static final int COMMAND_RST = 143;
    private static final int COMMAND_SPA = 144;
    private static final int COMMAND_SPC = 145;
    private static final int COMMAND_SPL = 146;
    private static final int COMMAND_SWA = 151;
    private static final int COMMAND_TGW = 139;
    private static final int DTVCC_PACKET_DATA = 2;
    private static final int DTVCC_PACKET_START = 3;
    private static final int GROUP_C0_END = 31;
    private static final int GROUP_C1_END = 159;
    private static final int GROUP_C2_END = 31;
    private static final int GROUP_C3_END = 159;
    private static final int GROUP_G0_END = 127;
    private static final int GROUP_G1_END = 255;
    private static final int GROUP_G2_END = 127;
    private static final int GROUP_G3_END = 255;
    private static final int NUM_WINDOWS = 8;
    private static final String TAG = "Cea708Decoder";
    private final ParsableBitArray captionChannelPacketData = new ParsableBitArray();
    private final ParsableByteArray ccData = new ParsableByteArray();
    private final CueInfoBuilder[] cueInfoBuilders;
    @Nullable
    private List<Cue> cues;
    private CueInfoBuilder currentCueInfoBuilder;
    @Nullable
    private DtvCcPacket currentDtvCcPacket;
    private int currentWindow;
    private final boolean isWideAspectRatio;
    @Nullable
    private List<Cue> lastCues;
    private int previousSequenceNumber = -1;
    private final int selectedServiceNumber;

    public static final class Cea708CueInfo {
        /* access modifiers changed from: private */
        public static final Comparator<Cea708CueInfo> LEAST_IMPORTANT_FIRST = new Object();
        public final Cue cue;
        public final int priority;

        public Cea708CueInfo(CharSequence charSequence, Layout.Alignment alignment, float f2, int i3, int i4, float f3, int i5, float f4, boolean z2, int i6, int i7) {
            Cue.Builder size = new Cue.Builder().setText(charSequence).setTextAlignment(alignment).setLine(f2, i3).setLineAnchor(i4).setPosition(f3).setPositionAnchor(i5).setSize(f4);
            if (z2) {
                size.setWindowColor(i6);
            }
            this.cue = size.build();
            this.priority = i7;
        }
    }

    public static final class CueInfoBuilder {
        private static final int BORDER_AND_EDGE_TYPE_NONE = 0;
        private static final int BORDER_AND_EDGE_TYPE_UNIFORM = 3;
        public static final int COLOR_SOLID_BLACK;
        public static final int COLOR_SOLID_WHITE = getArgbColorFromCeaColor(2, 2, 2, 0);
        public static final int COLOR_TRANSPARENT;
        private static final int DEFAULT_PRIORITY = 4;
        private static final int DIRECTION_BOTTOM_TO_TOP = 3;
        private static final int DIRECTION_LEFT_TO_RIGHT = 0;
        private static final int DIRECTION_RIGHT_TO_LEFT = 1;
        private static final int DIRECTION_TOP_TO_BOTTOM = 2;
        private static final int HORIZONTAL_SIZE = 209;
        private static final int JUSTIFICATION_CENTER = 2;
        private static final int JUSTIFICATION_FULL = 3;
        private static final int JUSTIFICATION_LEFT = 0;
        private static final int JUSTIFICATION_RIGHT = 1;
        private static final int MAXIMUM_ROW_COUNT = 15;
        private static final int PEN_FONT_STYLE_DEFAULT = 0;
        private static final int PEN_FONT_STYLE_MONOSPACED_WITHOUT_SERIFS = 3;
        private static final int PEN_FONT_STYLE_MONOSPACED_WITH_SERIFS = 1;
        private static final int PEN_FONT_STYLE_PROPORTIONALLY_SPACED_WITHOUT_SERIFS = 4;
        private static final int PEN_FONT_STYLE_PROPORTIONALLY_SPACED_WITH_SERIFS = 2;
        private static final int PEN_OFFSET_NORMAL = 1;
        private static final int PEN_SIZE_STANDARD = 1;
        private static final int[] PEN_STYLE_BACKGROUND;
        private static final int[] PEN_STYLE_EDGE_TYPE = {0, 0, 0, 0, 0, 3, 3};
        private static final int[] PEN_STYLE_FONT_STYLE = {0, 1, 2, 3, 4, 3, 4};
        private static final int RELATIVE_CUE_SIZE = 99;
        private static final int VERTICAL_SIZE = 74;
        private static final int[] WINDOW_STYLE_FILL;
        private static final int[] WINDOW_STYLE_JUSTIFICATION = {0, 0, 0, 0, 0, 2, 0};
        private static final int[] WINDOW_STYLE_PRINT_DIRECTION = {0, 0, 0, 0, 0, 0, 2};
        private static final int[] WINDOW_STYLE_SCROLL_DIRECTION = {3, 3, 3, 3, 3, 3, 1};
        private static final boolean[] WINDOW_STYLE_WORD_WRAP = {false, false, false, true, true, true, false};
        private int anchorId;
        private int backgroundColor;
        private int backgroundColorStartPosition;
        private final SpannableStringBuilder captionStringBuilder = new SpannableStringBuilder();
        private boolean defined;
        private int foregroundColor;
        private int foregroundColorStartPosition;
        private int horizontalAnchor;
        private int italicsStartPosition;
        private int justification;
        private int penStyleId;
        private int priority;
        private boolean relativePositioning;
        private final List<SpannableString> rolledUpCaptions = new ArrayList();
        private int row;
        private int rowCount;
        private boolean rowLock;
        private int underlineStartPosition;
        private int verticalAnchor;
        private boolean visible;
        private int windowFillColor;
        private int windowStyleId;

        static {
            int argbColorFromCeaColor = getArgbColorFromCeaColor(0, 0, 0, 0);
            COLOR_SOLID_BLACK = argbColorFromCeaColor;
            int argbColorFromCeaColor2 = getArgbColorFromCeaColor(0, 0, 0, 3);
            COLOR_TRANSPARENT = argbColorFromCeaColor2;
            int i3 = argbColorFromCeaColor;
            int i4 = argbColorFromCeaColor;
            WINDOW_STYLE_FILL = new int[]{argbColorFromCeaColor, argbColorFromCeaColor2, i3, i4, argbColorFromCeaColor2, argbColorFromCeaColor, argbColorFromCeaColor};
            PEN_STYLE_BACKGROUND = new int[]{argbColorFromCeaColor, argbColorFromCeaColor, i3, i4, argbColorFromCeaColor, argbColorFromCeaColor2, argbColorFromCeaColor2};
        }

        public CueInfoBuilder() {
            reset();
        }

        public static int getArgbColorFromCeaColor(int i3, int i4, int i5) {
            return getArgbColorFromCeaColor(i3, i4, i5, 0);
        }

        public void append(char c3) {
            if (c3 == 10) {
                this.rolledUpCaptions.add(buildSpannableString());
                this.captionStringBuilder.clear();
                if (this.italicsStartPosition != -1) {
                    this.italicsStartPosition = 0;
                }
                if (this.underlineStartPosition != -1) {
                    this.underlineStartPosition = 0;
                }
                if (this.foregroundColorStartPosition != -1) {
                    this.foregroundColorStartPosition = 0;
                }
                if (this.backgroundColorStartPosition != -1) {
                    this.backgroundColorStartPosition = 0;
                }
                while (true) {
                    if ((this.rowLock && this.rolledUpCaptions.size() >= this.rowCount) || this.rolledUpCaptions.size() >= 15) {
                        this.rolledUpCaptions.remove(0);
                    } else {
                        return;
                    }
                }
            } else {
                this.captionStringBuilder.append(c3);
            }
        }

        public void backspace() {
            int length = this.captionStringBuilder.length();
            if (length > 0) {
                this.captionStringBuilder.delete(length - 1, length);
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:19:0x0061  */
        /* JADX WARNING: Removed duplicated region for block: B:20:0x006c  */
        /* JADX WARNING: Removed duplicated region for block: B:23:0x008a  */
        /* JADX WARNING: Removed duplicated region for block: B:24:0x008c  */
        /* JADX WARNING: Removed duplicated region for block: B:30:0x0097  */
        /* JADX WARNING: Removed duplicated region for block: B:31:0x0099  */
        /* JADX WARNING: Removed duplicated region for block: B:37:0x00a5  */
        /* JADX WARNING: Removed duplicated region for block: B:38:0x00a7  */
        @androidx.annotation.Nullable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.appsamurai.storyly.exoplayer2.extractor.text.cea.Cea708Decoder.Cea708CueInfo build() {
            /*
                r14 = this;
                boolean r0 = r14.isEmpty()
                if (r0 == 0) goto L_0x0008
                r14 = 0
                return r14
            L_0x0008:
                android.text.SpannableStringBuilder r1 = new android.text.SpannableStringBuilder
                r1.<init>()
                r0 = 0
                r2 = r0
            L_0x000f:
                java.util.List<android.text.SpannableString> r3 = r14.rolledUpCaptions
                int r3 = r3.size()
                if (r2 >= r3) goto L_0x002a
                java.util.List<android.text.SpannableString> r3 = r14.rolledUpCaptions
                java.lang.Object r3 = r3.get(r2)
                java.lang.CharSequence r3 = (java.lang.CharSequence) r3
                r1.append(r3)
                r3 = 10
                r1.append(r3)
                int r2 = r2 + 1
                goto L_0x000f
            L_0x002a:
                android.text.SpannableString r2 = r14.buildSpannableString()
                r1.append(r2)
                int r2 = r14.justification
                r3 = 2
                r4 = 3
                r5 = 1
                if (r2 == 0) goto L_0x005b
                if (r2 == r5) goto L_0x0058
                if (r2 == r3) goto L_0x0055
                if (r2 != r4) goto L_0x003f
                goto L_0x005b
            L_0x003f:
                java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r2 = "Unexpected justification value: "
                r1.<init>(r2)
                int r14 = r14.justification
                r1.append(r14)
                java.lang.String r14 = r1.toString()
                r0.<init>(r14)
                throw r0
            L_0x0055:
                android.text.Layout$Alignment r2 = android.text.Layout.Alignment.ALIGN_CENTER
                goto L_0x005d
            L_0x0058:
                android.text.Layout$Alignment r2 = android.text.Layout.Alignment.ALIGN_OPPOSITE
                goto L_0x005d
            L_0x005b:
                android.text.Layout$Alignment r2 = android.text.Layout.Alignment.ALIGN_NORMAL
            L_0x005d:
                boolean r6 = r14.relativePositioning
                if (r6 == 0) goto L_0x006c
                int r6 = r14.horizontalAnchor
                float r6 = (float) r6
                r7 = 1120272384(0x42c60000, float:99.0)
                float r6 = r6 / r7
                int r8 = r14.verticalAnchor
                float r8 = (float) r8
                float r8 = r8 / r7
                goto L_0x0079
            L_0x006c:
                int r6 = r14.horizontalAnchor
                float r6 = (float) r6
                r7 = 1129381888(0x43510000, float:209.0)
                float r6 = r6 / r7
                int r7 = r14.verticalAnchor
                float r7 = (float) r7
                r8 = 1116995584(0x42940000, float:74.0)
                float r8 = r7 / r8
            L_0x0079:
                r7 = 1063675494(0x3f666666, float:0.9)
                float r6 = r6 * r7
                r9 = 1028443341(0x3d4ccccd, float:0.05)
                float r6 = r6 + r9
                float r8 = r8 * r7
                float r7 = r8 + r9
                int r8 = r14.anchorId
                int r9 = r8 / 3
                if (r9 != 0) goto L_0x008c
                r9 = r0
                goto L_0x0093
            L_0x008c:
                int r9 = r8 / 3
                if (r9 != r5) goto L_0x0092
                r9 = r5
                goto L_0x0093
            L_0x0092:
                r9 = r3
            L_0x0093:
                int r10 = r8 % 3
                if (r10 != 0) goto L_0x0099
                r8 = r0
                goto L_0x009f
            L_0x0099:
                int r8 = r8 % r4
                if (r8 != r5) goto L_0x009e
                r8 = r5
                goto L_0x009f
            L_0x009e:
                r8 = r3
            L_0x009f:
                int r10 = r14.windowFillColor
                int r3 = COLOR_SOLID_BLACK
                if (r10 == r3) goto L_0x00a7
                r11 = r5
                goto L_0x00a8
            L_0x00a7:
                r11 = r0
            L_0x00a8:
                com.appsamurai.storyly.exoplayer2.extractor.text.cea.Cea708Decoder$Cea708CueInfo r12 = new com.appsamurai.storyly.exoplayer2.extractor.text.cea.Cea708Decoder$Cea708CueInfo
                r13 = -8388609(0xffffffffff7fffff, float:-3.4028235E38)
                int r14 = r14.priority
                r4 = 0
                r0 = r12
                r3 = r7
                r5 = r9
                r7 = r8
                r8 = r13
                r9 = r11
                r11 = r14
                r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
                return r12
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.extractor.text.cea.Cea708Decoder.CueInfoBuilder.build():com.appsamurai.storyly.exoplayer2.extractor.text.cea.Cea708Decoder$Cea708CueInfo");
        }

        public SpannableString buildSpannableString() {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.captionStringBuilder);
            int length = spannableStringBuilder.length();
            if (length > 0) {
                if (this.italicsStartPosition != -1) {
                    spannableStringBuilder.setSpan(new StyleSpan(2), this.italicsStartPosition, length, 33);
                }
                if (this.underlineStartPosition != -1) {
                    spannableStringBuilder.setSpan(new UnderlineSpan(), this.underlineStartPosition, length, 33);
                }
                if (this.foregroundColorStartPosition != -1) {
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(this.foregroundColor), this.foregroundColorStartPosition, length, 33);
                }
                if (this.backgroundColorStartPosition != -1) {
                    spannableStringBuilder.setSpan(new BackgroundColorSpan(this.backgroundColor), this.backgroundColorStartPosition, length, 33);
                }
            }
            return new SpannableString(spannableStringBuilder);
        }

        public void clear() {
            this.rolledUpCaptions.clear();
            this.captionStringBuilder.clear();
            this.italicsStartPosition = -1;
            this.underlineStartPosition = -1;
            this.foregroundColorStartPosition = -1;
            this.backgroundColorStartPosition = -1;
            this.row = 0;
        }

        public void defineWindow(boolean z2, boolean z3, boolean z4, int i3, boolean z5, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
            boolean z6 = z3;
            int i11 = i9;
            int i12 = i10;
            this.defined = true;
            this.visible = z2;
            this.rowLock = z6;
            this.priority = i3;
            this.relativePositioning = z5;
            this.verticalAnchor = i4;
            this.horizontalAnchor = i5;
            this.anchorId = i8;
            int i13 = i6 + 1;
            if (this.rowCount != i13) {
                this.rowCount = i13;
                while (true) {
                    if ((!z6 || this.rolledUpCaptions.size() < this.rowCount) && this.rolledUpCaptions.size() < 15) {
                        break;
                    }
                    this.rolledUpCaptions.remove(0);
                }
            }
            if (!(i11 == 0 || this.windowStyleId == i11)) {
                this.windowStyleId = i11;
                int i14 = i11 - 1;
                setWindowAttributes(WINDOW_STYLE_FILL[i14], COLOR_TRANSPARENT, WINDOW_STYLE_WORD_WRAP[i14], 0, WINDOW_STYLE_PRINT_DIRECTION[i14], WINDOW_STYLE_SCROLL_DIRECTION[i14], WINDOW_STYLE_JUSTIFICATION[i14]);
            }
            if (i12 != 0 && this.penStyleId != i12) {
                this.penStyleId = i12;
                int i15 = i12 - 1;
                setPenAttributes(0, 1, 1, false, false, PEN_STYLE_EDGE_TYPE[i15], PEN_STYLE_FONT_STYLE[i15]);
                setPenColor(COLOR_SOLID_WHITE, PEN_STYLE_BACKGROUND[i15], COLOR_SOLID_BLACK);
            }
        }

        public boolean isDefined() {
            return this.defined;
        }

        public boolean isEmpty() {
            return !isDefined() || (this.rolledUpCaptions.isEmpty() && this.captionStringBuilder.length() == 0);
        }

        public boolean isVisible() {
            return this.visible;
        }

        public void reset() {
            clear();
            this.defined = false;
            this.visible = false;
            this.priority = 4;
            this.relativePositioning = false;
            this.verticalAnchor = 0;
            this.horizontalAnchor = 0;
            this.anchorId = 0;
            this.rowCount = 15;
            this.rowLock = true;
            this.justification = 0;
            this.windowStyleId = 0;
            this.penStyleId = 0;
            int i3 = COLOR_SOLID_BLACK;
            this.windowFillColor = i3;
            this.foregroundColor = COLOR_SOLID_WHITE;
            this.backgroundColor = i3;
        }

        public void setPenAttributes(int i3, int i4, int i5, boolean z2, boolean z3, int i6, int i7) {
            if (this.italicsStartPosition != -1) {
                if (!z2) {
                    this.captionStringBuilder.setSpan(new StyleSpan(2), this.italicsStartPosition, this.captionStringBuilder.length(), 33);
                    this.italicsStartPosition = -1;
                }
            } else if (z2) {
                this.italicsStartPosition = this.captionStringBuilder.length();
            }
            if (this.underlineStartPosition != -1) {
                if (!z3) {
                    this.captionStringBuilder.setSpan(new UnderlineSpan(), this.underlineStartPosition, this.captionStringBuilder.length(), 33);
                    this.underlineStartPosition = -1;
                }
            } else if (z3) {
                this.underlineStartPosition = this.captionStringBuilder.length();
            }
        }

        public void setPenColor(int i3, int i4, int i5) {
            if (!(this.foregroundColorStartPosition == -1 || this.foregroundColor == i3)) {
                this.captionStringBuilder.setSpan(new ForegroundColorSpan(this.foregroundColor), this.foregroundColorStartPosition, this.captionStringBuilder.length(), 33);
            }
            if (i3 != COLOR_SOLID_WHITE) {
                this.foregroundColorStartPosition = this.captionStringBuilder.length();
                this.foregroundColor = i3;
            }
            if (!(this.backgroundColorStartPosition == -1 || this.backgroundColor == i4)) {
                this.captionStringBuilder.setSpan(new BackgroundColorSpan(this.backgroundColor), this.backgroundColorStartPosition, this.captionStringBuilder.length(), 33);
            }
            if (i4 != COLOR_SOLID_BLACK) {
                this.backgroundColorStartPosition = this.captionStringBuilder.length();
                this.backgroundColor = i4;
            }
        }

        public void setPenLocation(int i3, int i4) {
            if (this.row != i3) {
                append(10);
            }
            this.row = i3;
        }

        public void setVisibility(boolean z2) {
            this.visible = z2;
        }

        public void setWindowAttributes(int i3, int i4, boolean z2, int i5, int i6, int i7, int i8) {
            this.windowFillColor = i3;
            this.justification = i8;
        }

        /* JADX WARNING: Removed duplicated region for block: B:11:0x0023  */
        /* JADX WARNING: Removed duplicated region for block: B:12:0x0025  */
        /* JADX WARNING: Removed duplicated region for block: B:14:0x0028  */
        /* JADX WARNING: Removed duplicated region for block: B:15:0x002a  */
        /* JADX WARNING: Removed duplicated region for block: B:17:0x002d  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static int getArgbColorFromCeaColor(int r4, int r5, int r6, int r7) {
            /*
                r0 = 0
                r1 = 4
                com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkIndex(r4, r0, r1)
                com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkIndex(r5, r0, r1)
                com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkIndex(r6, r0, r1)
                com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkIndex(r7, r0, r1)
                r1 = 1
                r2 = 255(0xff, float:3.57E-43)
                if (r7 == 0) goto L_0x001b
                if (r7 == r1) goto L_0x001b
                r3 = 2
                if (r7 == r3) goto L_0x001f
                r3 = 3
                if (r7 == r3) goto L_0x001d
            L_0x001b:
                r7 = r2
                goto L_0x0021
            L_0x001d:
                r7 = r0
                goto L_0x0021
            L_0x001f:
                r7 = 127(0x7f, float:1.78E-43)
            L_0x0021:
                if (r4 <= r1) goto L_0x0025
                r4 = r2
                goto L_0x0026
            L_0x0025:
                r4 = r0
            L_0x0026:
                if (r5 <= r1) goto L_0x002a
                r5 = r2
                goto L_0x002b
            L_0x002a:
                r5 = r0
            L_0x002b:
                if (r6 <= r1) goto L_0x002e
                r0 = r2
            L_0x002e:
                int r4 = android.graphics.Color.argb(r7, r4, r5, r0)
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.extractor.text.cea.Cea708Decoder.CueInfoBuilder.getArgbColorFromCeaColor(int, int, int, int):int");
        }
    }

    public static final class DtvCcPacket {
        int currentIndex = 0;
        public final byte[] packetData;
        public final int packetSize;
        public final int sequenceNumber;

        public DtvCcPacket(int i3, int i4) {
            this.sequenceNumber = i3;
            this.packetSize = i4;
            this.packetData = new byte[((i4 * 2) - 1)];
        }
    }

    public Cea708Decoder(int i3, @Nullable List<byte[]> list) {
        boolean z2 = true;
        this.selectedServiceNumber = i3 == -1 ? 1 : i3;
        this.isWideAspectRatio = (list == null || !CodecSpecificDataUtil.parseCea708InitializationData(list)) ? false : z2;
        this.cueInfoBuilders = new CueInfoBuilder[8];
        for (int i4 = 0; i4 < 8; i4++) {
            this.cueInfoBuilders[i4] = new CueInfoBuilder();
        }
        this.currentCueInfoBuilder = this.cueInfoBuilders[0];
    }

    private void finalizeCurrentPacket() {
        if (this.currentDtvCcPacket != null) {
            processCurrentPacket();
            this.currentDtvCcPacket = null;
        }
    }

    private List<Cue> getDisplayCues() {
        Cea708CueInfo build;
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < 8; i3++) {
            if (!this.cueInfoBuilders[i3].isEmpty() && this.cueInfoBuilders[i3].isVisible() && (build = this.cueInfoBuilders[i3].build()) != null) {
                arrayList.add(build);
            }
        }
        Collections.sort(arrayList, Cea708CueInfo.LEAST_IMPORTANT_FIRST);
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        for (int i4 = 0; i4 < arrayList.size(); i4++) {
            arrayList2.add(((Cea708CueInfo) arrayList.get(i4)).cue);
        }
        return Collections.unmodifiableList(arrayList2);
    }

    private void handleC0Command(int i3) {
        if (i3 == 0) {
            return;
        }
        if (i3 == 3) {
            this.cues = getDisplayCues();
        } else if (i3 != 8) {
            switch (i3) {
                case 12:
                    resetCueBuilders();
                    return;
                case 13:
                    this.currentCueInfoBuilder.append(10);
                    return;
                case 14:
                    return;
                default:
                    if (i3 >= 17 && i3 <= 23) {
                        r.a(i3, "Currently unsupported COMMAND_EXT1 Command: ", TAG);
                        this.captionChannelPacketData.skipBits(8);
                        return;
                    } else if (i3 < 24 || i3 > 31) {
                        r.a(i3, "Invalid C0 command: ", TAG);
                        return;
                    } else {
                        r.a(i3, "Currently unsupported COMMAND_P16 Command: ", TAG);
                        this.captionChannelPacketData.skipBits(16);
                        return;
                    }
            }
        } else {
            this.currentCueInfoBuilder.backspace();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0084, code lost:
        if (r2 > 8) goto L_0x0108;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x008c, code lost:
        if (r4.captionChannelPacketData.readBit() == false) goto L_0x0097;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x008e, code lost:
        r4.cueInfoBuilders[8 - r2].reset();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0097, code lost:
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00b6, code lost:
        if (r2 > 8) goto L_0x0108;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00be, code lost:
        if (r4.captionChannelPacketData.readBit() == false) goto L_0x00ca;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00c0, code lost:
        r4.cueInfoBuilders[8 - r2].setVisibility(false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00ca, code lost:
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00e4, code lost:
        if (r2 > 8) goto L_0x0108;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00ec, code lost:
        if (r4.captionChannelPacketData.readBit() == false) goto L_0x00f7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00ee, code lost:
        r4.cueInfoBuilders[8 - r2].clear();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00f7, code lost:
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void handleC1Command(int r5) {
        /*
            r4 = this;
            r0 = 16
            r1 = 8
            r2 = 1
            switch(r5) {
                case 128: goto L_0x00fa;
                case 129: goto L_0x00fa;
                case 130: goto L_0x00fa;
                case 131: goto L_0x00fa;
                case 132: goto L_0x00fa;
                case 133: goto L_0x00fa;
                case 134: goto L_0x00fa;
                case 135: goto L_0x00fa;
                case 136: goto L_0x00e4;
                case 137: goto L_0x00cd;
                case 138: goto L_0x00b6;
                case 139: goto L_0x009a;
                case 140: goto L_0x0084;
                case 141: goto L_0x007d;
                case 142: goto L_0x0108;
                case 143: goto L_0x0078;
                case 144: goto L_0x0064;
                case 145: goto L_0x004e;
                case 146: goto L_0x003a;
                case 147: goto L_0x0008;
                case 148: goto L_0x0008;
                case 149: goto L_0x0008;
                case 150: goto L_0x0008;
                case 151: goto L_0x0024;
                case 152: goto L_0x0011;
                case 153: goto L_0x0011;
                case 154: goto L_0x0011;
                case 155: goto L_0x0011;
                case 156: goto L_0x0011;
                case 157: goto L_0x0011;
                case 158: goto L_0x0011;
                case 159: goto L_0x0011;
                default: goto L_0x0008;
            }
        L_0x0008:
            java.lang.String r4 = "Invalid C1 command: "
            java.lang.String r0 = "Cea708Decoder"
            com.appsamurai.storyly.exoplayer2.core.r.a(r5, r4, r0)
            goto L_0x0108
        L_0x0011:
            int r5 = r5 + -152
            r4.handleDefineWindow(r5)
            int r0 = r4.currentWindow
            if (r0 == r5) goto L_0x0108
            r4.currentWindow = r5
            com.appsamurai.storyly.exoplayer2.extractor.text.cea.Cea708Decoder$CueInfoBuilder[] r0 = r4.cueInfoBuilders
            r5 = r0[r5]
            r4.currentCueInfoBuilder = r5
            goto L_0x0108
        L_0x0024:
            com.appsamurai.storyly.exoplayer2.extractor.text.cea.Cea708Decoder$CueInfoBuilder r5 = r4.currentCueInfoBuilder
            boolean r5 = r5.isDefined()
            if (r5 != 0) goto L_0x0035
            com.appsamurai.storyly.exoplayer2.common.util.ParsableBitArray r4 = r4.captionChannelPacketData
            r5 = 32
            r4.skipBits(r5)
            goto L_0x0108
        L_0x0035:
            r4.handleSetWindowAttributes()
            goto L_0x0108
        L_0x003a:
            com.appsamurai.storyly.exoplayer2.extractor.text.cea.Cea708Decoder$CueInfoBuilder r5 = r4.currentCueInfoBuilder
            boolean r5 = r5.isDefined()
            if (r5 != 0) goto L_0x0049
            com.appsamurai.storyly.exoplayer2.common.util.ParsableBitArray r4 = r4.captionChannelPacketData
            r4.skipBits(r0)
            goto L_0x0108
        L_0x0049:
            r4.handleSetPenLocation()
            goto L_0x0108
        L_0x004e:
            com.appsamurai.storyly.exoplayer2.extractor.text.cea.Cea708Decoder$CueInfoBuilder r5 = r4.currentCueInfoBuilder
            boolean r5 = r5.isDefined()
            if (r5 != 0) goto L_0x005f
            com.appsamurai.storyly.exoplayer2.common.util.ParsableBitArray r4 = r4.captionChannelPacketData
            r5 = 24
            r4.skipBits(r5)
            goto L_0x0108
        L_0x005f:
            r4.handleSetPenColor()
            goto L_0x0108
        L_0x0064:
            com.appsamurai.storyly.exoplayer2.extractor.text.cea.Cea708Decoder$CueInfoBuilder r5 = r4.currentCueInfoBuilder
            boolean r5 = r5.isDefined()
            if (r5 != 0) goto L_0x0073
            com.appsamurai.storyly.exoplayer2.common.util.ParsableBitArray r4 = r4.captionChannelPacketData
            r4.skipBits(r0)
            goto L_0x0108
        L_0x0073:
            r4.handleSetPenAttributes()
            goto L_0x0108
        L_0x0078:
            r4.resetCueBuilders()
            goto L_0x0108
        L_0x007d:
            com.appsamurai.storyly.exoplayer2.common.util.ParsableBitArray r4 = r4.captionChannelPacketData
            r4.skipBits(r1)
            goto L_0x0108
        L_0x0084:
            if (r2 > r1) goto L_0x0108
            com.appsamurai.storyly.exoplayer2.common.util.ParsableBitArray r5 = r4.captionChannelPacketData
            boolean r5 = r5.readBit()
            if (r5 == 0) goto L_0x0097
            com.appsamurai.storyly.exoplayer2.extractor.text.cea.Cea708Decoder$CueInfoBuilder[] r5 = r4.cueInfoBuilders
            int r0 = 8 - r2
            r5 = r5[r0]
            r5.reset()
        L_0x0097:
            int r2 = r2 + 1
            goto L_0x0084
        L_0x009a:
            r5 = r2
        L_0x009b:
            if (r5 > r1) goto L_0x0108
            com.appsamurai.storyly.exoplayer2.common.util.ParsableBitArray r0 = r4.captionChannelPacketData
            boolean r0 = r0.readBit()
            if (r0 == 0) goto L_0x00b3
            com.appsamurai.storyly.exoplayer2.extractor.text.cea.Cea708Decoder$CueInfoBuilder[] r0 = r4.cueInfoBuilders
            int r3 = 8 - r5
            r0 = r0[r3]
            boolean r3 = r0.isVisible()
            r3 = r3 ^ r2
            r0.setVisibility(r3)
        L_0x00b3:
            int r5 = r5 + 1
            goto L_0x009b
        L_0x00b6:
            if (r2 > r1) goto L_0x0108
            com.appsamurai.storyly.exoplayer2.common.util.ParsableBitArray r5 = r4.captionChannelPacketData
            boolean r5 = r5.readBit()
            if (r5 == 0) goto L_0x00ca
            com.appsamurai.storyly.exoplayer2.extractor.text.cea.Cea708Decoder$CueInfoBuilder[] r5 = r4.cueInfoBuilders
            int r0 = 8 - r2
            r5 = r5[r0]
            r0 = 0
            r5.setVisibility(r0)
        L_0x00ca:
            int r2 = r2 + 1
            goto L_0x00b6
        L_0x00cd:
            r5 = r2
        L_0x00ce:
            if (r5 > r1) goto L_0x0108
            com.appsamurai.storyly.exoplayer2.common.util.ParsableBitArray r0 = r4.captionChannelPacketData
            boolean r0 = r0.readBit()
            if (r0 == 0) goto L_0x00e1
            com.appsamurai.storyly.exoplayer2.extractor.text.cea.Cea708Decoder$CueInfoBuilder[] r0 = r4.cueInfoBuilders
            int r3 = 8 - r5
            r0 = r0[r3]
            r0.setVisibility(r2)
        L_0x00e1:
            int r5 = r5 + 1
            goto L_0x00ce
        L_0x00e4:
            if (r2 > r1) goto L_0x0108
            com.appsamurai.storyly.exoplayer2.common.util.ParsableBitArray r5 = r4.captionChannelPacketData
            boolean r5 = r5.readBit()
            if (r5 == 0) goto L_0x00f7
            com.appsamurai.storyly.exoplayer2.extractor.text.cea.Cea708Decoder$CueInfoBuilder[] r5 = r4.cueInfoBuilders
            int r0 = 8 - r2
            r5 = r5[r0]
            r5.clear()
        L_0x00f7:
            int r2 = r2 + 1
            goto L_0x00e4
        L_0x00fa:
            int r5 = r5 + -128
            int r0 = r4.currentWindow
            if (r0 == r5) goto L_0x0108
            r4.currentWindow = r5
            com.appsamurai.storyly.exoplayer2.extractor.text.cea.Cea708Decoder$CueInfoBuilder[] r0 = r4.cueInfoBuilders
            r5 = r0[r5]
            r4.currentCueInfoBuilder = r5
        L_0x0108:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.extractor.text.cea.Cea708Decoder.handleC1Command(int):void");
    }

    private void handleC2Command(int i3) {
        if (i3 > 7) {
            if (i3 <= 15) {
                this.captionChannelPacketData.skipBits(8);
            } else if (i3 <= 23) {
                this.captionChannelPacketData.skipBits(16);
            } else if (i3 <= 31) {
                this.captionChannelPacketData.skipBits(24);
            }
        }
    }

    private void handleC3Command(int i3) {
        if (i3 <= 135) {
            this.captionChannelPacketData.skipBits(32);
        } else if (i3 <= 143) {
            this.captionChannelPacketData.skipBits(40);
        } else if (i3 <= 159) {
            this.captionChannelPacketData.skipBits(2);
            this.captionChannelPacketData.skipBits(this.captionChannelPacketData.readBits(6) * 8);
        }
    }

    private void handleDefineWindow(int i3) {
        CueInfoBuilder cueInfoBuilder = this.cueInfoBuilders[i3];
        this.captionChannelPacketData.skipBits(2);
        boolean readBit = this.captionChannelPacketData.readBit();
        boolean readBit2 = this.captionChannelPacketData.readBit();
        boolean readBit3 = this.captionChannelPacketData.readBit();
        int readBits = this.captionChannelPacketData.readBits(3);
        boolean readBit4 = this.captionChannelPacketData.readBit();
        int readBits2 = this.captionChannelPacketData.readBits(7);
        int readBits3 = this.captionChannelPacketData.readBits(8);
        int readBits4 = this.captionChannelPacketData.readBits(4);
        int readBits5 = this.captionChannelPacketData.readBits(4);
        this.captionChannelPacketData.skipBits(2);
        int readBits6 = this.captionChannelPacketData.readBits(6);
        this.captionChannelPacketData.skipBits(2);
        cueInfoBuilder.defineWindow(readBit, readBit2, readBit3, readBits, readBit4, readBits2, readBits3, readBits5, readBits6, readBits4, this.captionChannelPacketData.readBits(3), this.captionChannelPacketData.readBits(3));
    }

    private void handleG0Character(int i3) {
        if (i3 == 127) {
            this.currentCueInfoBuilder.append(9835);
        } else {
            this.currentCueInfoBuilder.append((char) (i3 & 255));
        }
    }

    private void handleG1Character(int i3) {
        this.currentCueInfoBuilder.append((char) (i3 & 255));
    }

    private void handleG2Character(int i3) {
        if (i3 == 32) {
            this.currentCueInfoBuilder.append(' ');
        } else if (i3 == 33) {
            this.currentCueInfoBuilder.append(Typography.nbsp);
        } else if (i3 == 37) {
            this.currentCueInfoBuilder.append(Typography.ellipsis);
        } else if (i3 == 42) {
            this.currentCueInfoBuilder.append(352);
        } else if (i3 == 44) {
            this.currentCueInfoBuilder.append(338);
        } else if (i3 == 63) {
            this.currentCueInfoBuilder.append(376);
        } else if (i3 == 57) {
            this.currentCueInfoBuilder.append(Typography.tm);
        } else if (i3 == 58) {
            this.currentCueInfoBuilder.append(353);
        } else if (i3 == 60) {
            this.currentCueInfoBuilder.append(339);
        } else if (i3 != 61) {
            switch (i3) {
                case 48:
                    this.currentCueInfoBuilder.append(9608);
                    return;
                case 49:
                    this.currentCueInfoBuilder.append(Typography.leftSingleQuote);
                    return;
                case 50:
                    this.currentCueInfoBuilder.append(Typography.rightSingleQuote);
                    return;
                case 51:
                    this.currentCueInfoBuilder.append(Typography.leftDoubleQuote);
                    return;
                case 52:
                    this.currentCueInfoBuilder.append(Typography.rightDoubleQuote);
                    return;
                case 53:
                    this.currentCueInfoBuilder.append(Typography.bullet);
                    return;
                default:
                    switch (i3) {
                        case 118:
                            this.currentCueInfoBuilder.append(8539);
                            return;
                        case 119:
                            this.currentCueInfoBuilder.append(8540);
                            return;
                        case 120:
                            this.currentCueInfoBuilder.append(8541);
                            return;
                        case 121:
                            this.currentCueInfoBuilder.append(8542);
                            return;
                        case 122:
                            this.currentCueInfoBuilder.append(9474);
                            return;
                        case 123:
                            this.currentCueInfoBuilder.append(9488);
                            return;
                        case 124:
                            this.currentCueInfoBuilder.append(9492);
                            return;
                        case 125:
                            this.currentCueInfoBuilder.append(9472);
                            return;
                        case 126:
                            this.currentCueInfoBuilder.append(9496);
                            return;
                        case 127:
                            this.currentCueInfoBuilder.append(9484);
                            return;
                        default:
                            r.a(i3, "Invalid G2 character: ", TAG);
                            return;
                    }
            }
        } else {
            this.currentCueInfoBuilder.append(8480);
        }
    }

    private void handleG3Character(int i3) {
        if (i3 == 160) {
            this.currentCueInfoBuilder.append(13252);
            return;
        }
        r.a(i3, "Invalid G3 character: ", TAG);
        this.currentCueInfoBuilder.append('_');
    }

    private void handleSetPenAttributes() {
        this.currentCueInfoBuilder.setPenAttributes(this.captionChannelPacketData.readBits(4), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBit(), this.captionChannelPacketData.readBit(), this.captionChannelPacketData.readBits(3), this.captionChannelPacketData.readBits(3));
    }

    private void handleSetPenColor() {
        int argbColorFromCeaColor = CueInfoBuilder.getArgbColorFromCeaColor(this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2));
        int argbColorFromCeaColor2 = CueInfoBuilder.getArgbColorFromCeaColor(this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2));
        this.captionChannelPacketData.skipBits(2);
        this.currentCueInfoBuilder.setPenColor(argbColorFromCeaColor, argbColorFromCeaColor2, CueInfoBuilder.getArgbColorFromCeaColor(this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2)));
    }

    private void handleSetPenLocation() {
        this.captionChannelPacketData.skipBits(4);
        int readBits = this.captionChannelPacketData.readBits(4);
        this.captionChannelPacketData.skipBits(2);
        this.currentCueInfoBuilder.setPenLocation(readBits, this.captionChannelPacketData.readBits(6));
    }

    private void handleSetWindowAttributes() {
        int argbColorFromCeaColor = CueInfoBuilder.getArgbColorFromCeaColor(this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2));
        int readBits = this.captionChannelPacketData.readBits(2);
        int argbColorFromCeaColor2 = CueInfoBuilder.getArgbColorFromCeaColor(this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2));
        if (this.captionChannelPacketData.readBit()) {
            readBits |= 4;
        }
        boolean readBit = this.captionChannelPacketData.readBit();
        int readBits2 = this.captionChannelPacketData.readBits(2);
        int readBits3 = this.captionChannelPacketData.readBits(2);
        int readBits4 = this.captionChannelPacketData.readBits(2);
        this.captionChannelPacketData.skipBits(8);
        this.currentCueInfoBuilder.setWindowAttributes(argbColorFromCeaColor, argbColorFromCeaColor2, readBit, readBits, readBits2, readBits3, readBits4);
    }

    @RequiresNonNull({"currentDtvCcPacket"})
    private void processCurrentPacket() {
        DtvCcPacket dtvCcPacket = this.currentDtvCcPacket;
        if (dtvCcPacket.currentIndex != (dtvCcPacket.packetSize * 2) - 1) {
            Log.d(TAG, "DtvCcPacket ended prematurely; size is " + ((this.currentDtvCcPacket.packetSize * 2) - 1) + ", but current index is " + this.currentDtvCcPacket.currentIndex + " (sequence number " + this.currentDtvCcPacket.sequenceNumber + ");");
        }
        ParsableBitArray parsableBitArray = this.captionChannelPacketData;
        DtvCcPacket dtvCcPacket2 = this.currentDtvCcPacket;
        parsableBitArray.reset(dtvCcPacket2.packetData, dtvCcPacket2.currentIndex);
        boolean z2 = false;
        while (true) {
            if (this.captionChannelPacketData.bitsLeft() <= 0) {
                break;
            }
            int readBits = this.captionChannelPacketData.readBits(3);
            int readBits2 = this.captionChannelPacketData.readBits(5);
            if (readBits == 7) {
                this.captionChannelPacketData.skipBits(2);
                readBits = this.captionChannelPacketData.readBits(6);
                if (readBits < 7) {
                    r.a(readBits, "Invalid extended service number: ", TAG);
                }
            }
            if (readBits2 == 0) {
                if (readBits != 0) {
                    Log.w(TAG, "serviceNumber is non-zero (" + readBits + ") when blockSize is 0");
                }
            } else if (readBits != this.selectedServiceNumber) {
                this.captionChannelPacketData.skipBytes(readBits2);
            } else {
                int position = (readBits2 * 8) + this.captionChannelPacketData.getPosition();
                while (this.captionChannelPacketData.getPosition() < position) {
                    int readBits3 = this.captionChannelPacketData.readBits(8);
                    if (readBits3 == 16) {
                        int readBits4 = this.captionChannelPacketData.readBits(8);
                        if (readBits4 <= 31) {
                            handleC2Command(readBits4);
                        } else if (readBits4 <= 127) {
                            handleG2Character(readBits4);
                        } else if (readBits4 <= 159) {
                            handleC3Command(readBits4);
                        } else if (readBits4 <= 255) {
                            handleG3Character(readBits4);
                        } else {
                            r.a(readBits4, "Invalid extended command: ", TAG);
                        }
                    } else if (readBits3 <= 31) {
                        handleC0Command(readBits3);
                    } else if (readBits3 <= 127) {
                        handleG0Character(readBits3);
                    } else if (readBits3 <= 159) {
                        handleC1Command(readBits3);
                    } else if (readBits3 <= 255) {
                        handleG1Character(readBits3);
                    } else {
                        r.a(readBits3, "Invalid base command: ", TAG);
                    }
                    z2 = true;
                }
            }
        }
        if (z2) {
            this.cues = getDisplayCues();
        }
    }

    private void resetCueBuilders() {
        for (int i3 = 0; i3 < 8; i3++) {
            this.cueInfoBuilders[i3].reset();
        }
    }

    public Subtitle createSubtitle() {
        List<Cue> list = this.cues;
        this.lastCues = list;
        return new CeaSubtitle((List) Assertions.checkNotNull(list));
    }

    public void decode(SubtitleInputBuffer subtitleInputBuffer) {
        ByteBuffer byteBuffer = (ByteBuffer) Assertions.checkNotNull(subtitleInputBuffer.data);
        this.ccData.reset(byteBuffer.array(), byteBuffer.limit());
        while (this.ccData.bytesLeft() >= 3) {
            int readUnsignedByte = this.ccData.readUnsignedByte();
            int i3 = readUnsignedByte & 3;
            boolean z2 = false;
            boolean z3 = (readUnsignedByte & 4) == 4;
            byte readUnsignedByte2 = (byte) this.ccData.readUnsignedByte();
            byte readUnsignedByte3 = (byte) this.ccData.readUnsignedByte();
            if ((i3 == 2 || i3 == 3) && z3) {
                if (i3 == 3) {
                    finalizeCurrentPacket();
                    int i4 = (readUnsignedByte2 & MessagePack.Code.NIL) >> 6;
                    int i5 = this.previousSequenceNumber;
                    if (!(i5 == -1 || i4 == (i5 + 1) % 4)) {
                        resetCueBuilders();
                        Log.w(TAG, "Sequence number discontinuity. previous=" + this.previousSequenceNumber + " current=" + i4);
                    }
                    this.previousSequenceNumber = i4;
                    byte b3 = readUnsignedByte2 & Utf8.REPLACEMENT_BYTE;
                    if (b3 == 0) {
                        b3 = SignedBytes.MAX_POWER_OF_TWO;
                    }
                    DtvCcPacket dtvCcPacket = new DtvCcPacket(i4, b3);
                    this.currentDtvCcPacket = dtvCcPacket;
                    byte[] bArr = dtvCcPacket.packetData;
                    int i6 = dtvCcPacket.currentIndex;
                    dtvCcPacket.currentIndex = i6 + 1;
                    bArr[i6] = readUnsignedByte3;
                } else {
                    if (i3 == 2) {
                        z2 = true;
                    }
                    Assertions.checkArgument(z2);
                    DtvCcPacket dtvCcPacket2 = this.currentDtvCcPacket;
                    if (dtvCcPacket2 == null) {
                        Log.e(TAG, "Encountered DTVCC_PACKET_DATA before DTVCC_PACKET_START");
                    } else {
                        byte[] bArr2 = dtvCcPacket2.packetData;
                        int i7 = dtvCcPacket2.currentIndex;
                        int i8 = i7 + 1;
                        dtvCcPacket2.currentIndex = i8;
                        bArr2[i7] = readUnsignedByte2;
                        dtvCcPacket2.currentIndex = i7 + 2;
                        bArr2[i8] = readUnsignedByte3;
                    }
                }
                DtvCcPacket dtvCcPacket3 = this.currentDtvCcPacket;
                if (dtvCcPacket3.currentIndex == (dtvCcPacket3.packetSize * 2) - 1) {
                    finalizeCurrentPacket();
                }
            }
        }
    }

    @Nullable
    public /* bridge */ /* synthetic */ SubtitleInputBuffer dequeueInputBuffer() throws SubtitleDecoderException {
        return super.dequeueInputBuffer();
    }

    @Nullable
    public /* bridge */ /* synthetic */ SubtitleOutputBuffer dequeueOutputBuffer() throws SubtitleDecoderException {
        return super.dequeueOutputBuffer();
    }

    public void flush() {
        super.flush();
        this.cues = null;
        this.lastCues = null;
        this.currentWindow = 0;
        this.currentCueInfoBuilder = this.cueInfoBuilders[0];
        resetCueBuilders();
        this.currentDtvCcPacket = null;
    }

    public String getName() {
        return TAG;
    }

    public boolean isNewSubtitleDataAvailable() {
        return this.cues != this.lastCues;
    }

    public /* bridge */ /* synthetic */ void queueInputBuffer(SubtitleInputBuffer subtitleInputBuffer) throws SubtitleDecoderException {
        super.queueInputBuffer(subtitleInputBuffer);
    }

    public /* bridge */ /* synthetic */ void release() {
        super.release();
    }

    public /* bridge */ /* synthetic */ void setPositionUs(long j2) {
        super.setPositionUs(j2);
    }
}
