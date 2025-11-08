package com.appsamurai.storyly.exoplayer2.extractor.text.cea;

import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.text.Cue;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.extractor.text.Subtitle;
import com.appsamurai.storyly.exoplayer2.extractor.text.SubtitleDecoderException;
import com.appsamurai.storyly.exoplayer2.extractor.text.SubtitleInputBuffer;
import com.appsamurai.storyly.exoplayer2.extractor.text.SubtitleOutputBuffer;
import com.fasterxml.jackson.dataformat.cbor.CBORConstants;
import com.google.common.base.Ascii;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import jnr.ffi.provider.jffi.JNINativeInterface;
import org.msgpack.core.MessagePack;

public final class Cea608Decoder extends CeaDecoder {
    private static final int[] BASIC_CHARACTER_SET = {32, 33, 34, 35, 36, 37, 38, 39, 40, 41, JNINativeInterface.ReleaseStringCritical, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 233, 93, 237, 243, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, JNINativeInterface.GetDirectBufferCapacity, 247, JNINativeInterface.SetCharArrayRegion, 241, 9632};
    private static final int CC_FIELD_FLAG = 1;
    private static final byte CC_IMPLICIT_DATA_HEADER = -4;
    private static final int CC_MODE_PAINT_ON = 3;
    private static final int CC_MODE_POP_ON = 2;
    private static final int CC_MODE_ROLL_UP = 1;
    private static final int CC_MODE_UNKNOWN = 0;
    private static final int CC_TYPE_FLAG = 2;
    private static final int CC_VALID_FLAG = 4;
    private static final int[] COLUMN_INDICES = {0, 4, 8, 12, 16, 20, 24, 28};
    private static final byte CTRL_BACKSPACE = 33;
    private static final byte CTRL_CARRIAGE_RETURN = 45;
    private static final byte CTRL_DELETE_TO_END_OF_ROW = 36;
    private static final byte CTRL_END_OF_CAPTION = 47;
    private static final byte CTRL_ERASE_DISPLAYED_MEMORY = 44;
    private static final byte CTRL_ERASE_NON_DISPLAYED_MEMORY = 46;
    private static final byte CTRL_RESUME_CAPTION_LOADING = 32;
    private static final byte CTRL_RESUME_DIRECT_CAPTIONING = 41;
    private static final byte CTRL_RESUME_TEXT_DISPLAY = 43;
    private static final byte CTRL_ROLL_UP_CAPTIONS_2_ROWS = 37;
    private static final byte CTRL_ROLL_UP_CAPTIONS_3_ROWS = 38;
    private static final byte CTRL_ROLL_UP_CAPTIONS_4_ROWS = 39;
    private static final byte CTRL_TEXT_RESTART = 42;
    private static final int DEFAULT_CAPTIONS_ROW_COUNT = 4;
    public static final long MIN_DATA_CHANNEL_TIMEOUT_MS = 16000;
    private static final int NTSC_CC_CHANNEL_1 = 0;
    private static final int NTSC_CC_CHANNEL_2 = 1;
    private static final int NTSC_CC_FIELD_1 = 0;
    private static final int NTSC_CC_FIELD_2 = 1;
    private static final boolean[] ODD_PARITY_BYTE_TABLE = {false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false};
    private static final int[] ROW_INDICES = {11, 1, 3, 12, 14, 5, 7, 9};
    private static final int[] SPECIAL_CHARACTER_SET = {174, 176, 189, 191, 8482, 162, 163, 9834, 224, 32, JNINativeInterface.GetObjectRefType, JNINativeInterface.NewWeakGlobalRef, 234, 238, 244, 251};
    private static final int[] SPECIAL_ES_FR_CHARACTER_SET = {193, 201, 211, JNINativeInterface.MonitorExit, JNINativeInterface.GetStringRegion, 252, 8216, 161, 42, 39, 8212, 169, 8480, 8226, 8220, 8221, 192, 194, 199, 200, 202, 203, 235, 206, 207, 239, JNINativeInterface.SetLongArrayRegion, JNINativeInterface.MonitorEnter, 249, JNINativeInterface.GetJavaVM, 171, 187};
    private static final int[] SPECIAL_PT_DE_CHARACTER_SET = {195, JNINativeInterface.DeleteWeakGlobalRef, 205, 204, 236, JNINativeInterface.SetShortArrayRegion, 242, JNINativeInterface.SetFloatArrayRegion, 245, 123, 125, 92, 94, 95, 124, 126, 196, JNINativeInterface.ExceptionCheck, JNINativeInterface.SetDoubleArrayRegion, 246, JNINativeInterface.ReleasePrimitiveArrayCritical, 165, 164, 9474, 197, JNINativeInterface.NewDirectByteBuffer, JNINativeInterface.UnregisterNatives, 248, 9484, 9488, 9492, 9496};
    /* access modifiers changed from: private */
    public static final int[] STYLE_COLORS = {-1, -16711936, -16776961, -16711681, -65536, -256, -65281};
    private static final int STYLE_ITALICS = 7;
    private static final int STYLE_UNCHANGED = 8;
    private static final String TAG = "Cea608Decoder";
    private int captionMode;
    private int captionRowCount;
    private final ParsableByteArray ccData = new ParsableByteArray();
    private final ArrayList<CueBuilder> cueBuilders = new ArrayList<>();
    @Nullable
    private List<Cue> cues;
    private int currentChannel = 0;
    private CueBuilder currentCueBuilder = new CueBuilder(0, 4);
    private boolean isCaptionValid;
    private boolean isInCaptionService;
    private long lastCueUpdateUs;
    @Nullable
    private List<Cue> lastCues;
    private final int packetLength;
    private byte repeatableControlCc1;
    private byte repeatableControlCc2;
    private boolean repeatableControlSet;
    private final int selectedChannel;
    private final int selectedField;
    private final long validDataChannelTimeoutUs;

    public static final class CueBuilder {
        private static final int BASE_ROW = 15;
        private static final int SCREEN_CHARWIDTH = 32;
        private int captionMode;
        private int captionRowCount;
        private final StringBuilder captionStringBuilder = new StringBuilder();
        private final List<CueStyle> cueStyles = new ArrayList();
        /* access modifiers changed from: private */
        public int indent;
        private final List<SpannableString> rolledUpCaptions = new ArrayList();
        /* access modifiers changed from: private */
        public int row;
        /* access modifiers changed from: private */
        public int tabOffset;

        public static class CueStyle {
            public int start;
            public final int style;
            public final boolean underline;

            public CueStyle(int i3, boolean z2, int i4) {
                this.style = i3;
                this.underline = z2;
                this.start = i4;
            }
        }

        public CueBuilder(int i3, int i4) {
            reset(i3);
            this.captionRowCount = i4;
        }

        private SpannableString buildCurrentLine() {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.captionStringBuilder);
            int length = spannableStringBuilder.length();
            int i3 = -1;
            int i4 = -1;
            int i5 = -1;
            int i6 = -1;
            int i7 = 0;
            int i8 = 0;
            boolean z2 = false;
            while (i7 < this.cueStyles.size()) {
                CueStyle cueStyle = this.cueStyles.get(i7);
                boolean z3 = cueStyle.underline;
                int i9 = cueStyle.style;
                if (i9 != 8) {
                    boolean z4 = i9 == 7;
                    if (i9 != 7) {
                        i6 = Cea608Decoder.STYLE_COLORS[i9];
                    }
                    z2 = z4;
                }
                int i10 = cueStyle.start;
                i7++;
                if (i10 != (i7 < this.cueStyles.size() ? this.cueStyles.get(i7).start : length)) {
                    if (i3 != -1 && !z3) {
                        setUnderlineSpan(spannableStringBuilder, i3, i10);
                        i3 = -1;
                    } else if (i3 == -1 && z3) {
                        i3 = i10;
                    }
                    if (i4 != -1 && !z2) {
                        setItalicSpan(spannableStringBuilder, i4, i10);
                        i4 = -1;
                    } else if (i4 == -1 && z2) {
                        i4 = i10;
                    }
                    if (i6 != i5) {
                        setColorSpan(spannableStringBuilder, i8, i10, i5);
                        i5 = i6;
                        i8 = i10;
                    }
                }
            }
            if (!(i3 == -1 || i3 == length)) {
                setUnderlineSpan(spannableStringBuilder, i3, length);
            }
            if (!(i4 == -1 || i4 == length)) {
                setItalicSpan(spannableStringBuilder, i4, length);
            }
            if (i8 != length) {
                setColorSpan(spannableStringBuilder, i8, length, i5);
            }
            return new SpannableString(spannableStringBuilder);
        }

        private static void setColorSpan(SpannableStringBuilder spannableStringBuilder, int i3, int i4, int i5) {
            if (i5 != -1) {
                spannableStringBuilder.setSpan(new ForegroundColorSpan(i5), i3, i4, 33);
            }
        }

        private static void setItalicSpan(SpannableStringBuilder spannableStringBuilder, int i3, int i4) {
            spannableStringBuilder.setSpan(new StyleSpan(2), i3, i4, 33);
        }

        private static void setUnderlineSpan(SpannableStringBuilder spannableStringBuilder, int i3, int i4) {
            spannableStringBuilder.setSpan(new UnderlineSpan(), i3, i4, 33);
        }

        public void append(char c3) {
            if (this.captionStringBuilder.length() < 32) {
                this.captionStringBuilder.append(c3);
            }
        }

        public void backspace() {
            int length = this.captionStringBuilder.length();
            if (length > 0) {
                this.captionStringBuilder.delete(length - 1, length);
                int size = this.cueStyles.size() - 1;
                while (size >= 0) {
                    CueStyle cueStyle = this.cueStyles.get(size);
                    int i3 = cueStyle.start;
                    if (i3 == length) {
                        cueStyle.start = i3 - 1;
                        size--;
                    } else {
                        return;
                    }
                }
            }
        }

        @Nullable
        public Cue build(int i3) {
            float f2;
            int i4 = this.indent + this.tabOffset;
            int i5 = 32 - i4;
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            for (int i6 = 0; i6 < this.rolledUpCaptions.size(); i6++) {
                spannableStringBuilder.append(Util.truncateAscii(this.rolledUpCaptions.get(i6), i5));
                spannableStringBuilder.append(10);
            }
            spannableStringBuilder.append(Util.truncateAscii(buildCurrentLine(), i5));
            if (spannableStringBuilder.length() == 0) {
                return null;
            }
            int length = i5 - spannableStringBuilder.length();
            int i7 = i4 - length;
            if (i3 == Integer.MIN_VALUE) {
                i3 = (this.captionMode != 2 || (Math.abs(i7) >= 3 && length >= 0)) ? (this.captionMode != 2 || i7 <= 0) ? 0 : 2 : 1;
            }
            if (i3 != 1) {
                if (i3 == 2) {
                    i4 = 32 - length;
                }
                f2 = ((((float) i4) / 32.0f) * 0.8f) + 0.1f;
            } else {
                f2 = 0.5f;
            }
            int i8 = this.row;
            if (i8 > 7) {
                i8 -= 17;
            } else if (this.captionMode == 1) {
                i8 -= this.captionRowCount - 1;
            }
            return new Cue.Builder().setText(spannableStringBuilder).setTextAlignment(Layout.Alignment.ALIGN_NORMAL).setLine((float) i8, 1).setPosition(f2).setPositionAnchor(i3).build();
        }

        public boolean isEmpty() {
            return this.cueStyles.isEmpty() && this.rolledUpCaptions.isEmpty() && this.captionStringBuilder.length() == 0;
        }

        public void reset(int i3) {
            this.captionMode = i3;
            this.cueStyles.clear();
            this.rolledUpCaptions.clear();
            this.captionStringBuilder.setLength(0);
            this.row = 15;
            this.indent = 0;
            this.tabOffset = 0;
        }

        public void rollUp() {
            this.rolledUpCaptions.add(buildCurrentLine());
            this.captionStringBuilder.setLength(0);
            this.cueStyles.clear();
            int min = Math.min(this.captionRowCount, this.row);
            while (this.rolledUpCaptions.size() >= min) {
                this.rolledUpCaptions.remove(0);
            }
        }

        public void setCaptionMode(int i3) {
            this.captionMode = i3;
        }

        public void setCaptionRowCount(int i3) {
            this.captionRowCount = i3;
        }

        public void setStyle(int i3, boolean z2) {
            this.cueStyles.add(new CueStyle(i3, z2, this.captionStringBuilder.length()));
        }
    }

    public Cea608Decoder(String str, int i3, long j2) {
        this.validDataChannelTimeoutUs = j2 > 0 ? j2 * 1000 : -9223372036854775807L;
        this.packetLength = MimeTypes.APPLICATION_MP4CEA608.equals(str) ? 2 : 3;
        if (i3 == 1) {
            this.selectedChannel = 0;
            this.selectedField = 0;
        } else if (i3 == 2) {
            this.selectedChannel = 1;
            this.selectedField = 0;
        } else if (i3 == 3) {
            this.selectedChannel = 0;
            this.selectedField = 1;
        } else if (i3 != 4) {
            Log.w(TAG, "Invalid channel. Defaulting to CC1.");
            this.selectedChannel = 0;
            this.selectedField = 0;
        } else {
            this.selectedChannel = 1;
            this.selectedField = 1;
        }
        setCaptionMode(0);
        resetCueBuilders();
        this.isInCaptionService = true;
        this.lastCueUpdateUs = C.TIME_UNSET;
    }

    private static char getBasicChar(byte b3) {
        return (char) BASIC_CHARACTER_SET[(b3 & Byte.MAX_VALUE) + MessagePack.Code.NEGFIXINT_PREFIX];
    }

    private static int getChannel(byte b3) {
        return (b3 >> 3) & 1;
    }

    private List<Cue> getDisplayCues() {
        int size = this.cueBuilders.size();
        ArrayList arrayList = new ArrayList(size);
        int i3 = 2;
        for (int i4 = 0; i4 < size; i4++) {
            Cue build = this.cueBuilders.get(i4).build(Integer.MIN_VALUE);
            arrayList.add(build);
            if (build != null) {
                i3 = Math.min(i3, build.positionAnchor);
            }
        }
        ArrayList arrayList2 = new ArrayList(size);
        for (int i5 = 0; i5 < size; i5++) {
            Cue cue = (Cue) arrayList.get(i5);
            if (cue != null) {
                if (cue.positionAnchor != i3) {
                    cue = (Cue) Assertions.checkNotNull(this.cueBuilders.get(i5).build(i3));
                }
                arrayList2.add(cue);
            }
        }
        return arrayList2;
    }

    private static char getExtendedEsFrChar(byte b3) {
        return (char) SPECIAL_ES_FR_CHARACTER_SET[b3 & 31];
    }

    private static char getExtendedPtDeChar(byte b3) {
        return (char) SPECIAL_PT_DE_CHARACTER_SET[b3 & 31];
    }

    private static char getExtendedWestEuropeanChar(byte b3, byte b4) {
        return (b3 & 1) == 0 ? getExtendedEsFrChar(b4) : getExtendedPtDeChar(b4);
    }

    private static char getSpecialNorthAmericanChar(byte b3) {
        return (char) SPECIAL_CHARACTER_SET[b3 & Ascii.SI];
    }

    private void handleMidrowCtrl(byte b3) {
        this.currentCueBuilder.append(' ');
        this.currentCueBuilder.setStyle((b3 >> 1) & 7, (b3 & 1) == 1);
    }

    private void handleMiscCode(byte b3) {
        if (b3 == 32) {
            setCaptionMode(2);
        } else if (b3 != 41) {
            switch (b3) {
                case 37:
                    setCaptionMode(1);
                    setCaptionRowCount(2);
                    return;
                case 38:
                    setCaptionMode(1);
                    setCaptionRowCount(3);
                    return;
                case 39:
                    setCaptionMode(1);
                    setCaptionRowCount(4);
                    return;
                default:
                    int i3 = this.captionMode;
                    if (i3 != 0) {
                        if (b3 != 33) {
                            switch (b3) {
                                case 44:
                                    this.cues = Collections.emptyList();
                                    int i4 = this.captionMode;
                                    if (i4 == 1 || i4 == 3) {
                                        resetCueBuilders();
                                        return;
                                    }
                                    return;
                                case 45:
                                    if (i3 == 1 && !this.currentCueBuilder.isEmpty()) {
                                        this.currentCueBuilder.rollUp();
                                        return;
                                    }
                                    return;
                                case 46:
                                    resetCueBuilders();
                                    return;
                                case 47:
                                    this.cues = getDisplayCues();
                                    resetCueBuilders();
                                    return;
                                default:
                                    return;
                            }
                        } else {
                            this.currentCueBuilder.backspace();
                            return;
                        }
                    } else {
                        return;
                    }
            }
        } else {
            setCaptionMode(3);
        }
    }

    private void handlePreambleAddressCode(byte b3, byte b4) {
        int i3 = ROW_INDICES[b3 & 7];
        if ((b4 & 32) != 0) {
            i3++;
        }
        if (i3 != this.currentCueBuilder.row) {
            if (this.captionMode != 1 && !this.currentCueBuilder.isEmpty()) {
                CueBuilder cueBuilder = new CueBuilder(this.captionMode, this.captionRowCount);
                this.currentCueBuilder = cueBuilder;
                this.cueBuilders.add(cueBuilder);
            }
            int unused = this.currentCueBuilder.row = i3;
        }
        boolean z2 = false;
        boolean z3 = (b4 & 16) == 16;
        if ((b4 & 1) == 1) {
            z2 = true;
        }
        int i4 = (b4 >> 1) & 7;
        this.currentCueBuilder.setStyle(z3 ? 8 : i4, z2);
        if (z3) {
            int unused2 = this.currentCueBuilder.indent = COLUMN_INDICES[i4];
        }
    }

    private static boolean isCtrlCode(byte b3) {
        return (b3 & MessagePack.Code.NEGFIXINT_PREFIX) == 0;
    }

    private static boolean isExtendedWestEuropeanChar(byte b3, byte b4) {
        return (b3 & CBORConstants.BYTE_NULL) == 18 && (b4 & MessagePack.Code.NEGFIXINT_PREFIX) == 32;
    }

    private static boolean isMidrowCtrlCode(byte b3, byte b4) {
        return (b3 & 247) == 17 && (b4 & 240) == 32;
    }

    private static boolean isMiscCode(byte b3, byte b4) {
        return (b3 & CBORConstants.BYTE_NULL) == 20 && (b4 & 240) == 32;
    }

    private static boolean isPreambleAddressCode(byte b3, byte b4) {
        return (b3 & 240) == 16 && (b4 & MessagePack.Code.NIL) == 64;
    }

    private static boolean isRepeatable(byte b3) {
        return (b3 & 240) == 16;
    }

    private boolean isRepeatedCommand(boolean z2, byte b3, byte b4) {
        if (!z2 || !isRepeatable(b3)) {
            this.repeatableControlSet = false;
        } else if (this.repeatableControlSet && this.repeatableControlCc1 == b3 && this.repeatableControlCc2 == b4) {
            this.repeatableControlSet = false;
            return true;
        } else {
            this.repeatableControlSet = true;
            this.repeatableControlCc1 = b3;
            this.repeatableControlCc2 = b4;
        }
        return false;
    }

    private static boolean isServiceSwitchCommand(byte b3) {
        return (b3 & 247) == 20;
    }

    private static boolean isSpecialNorthAmericanChar(byte b3, byte b4) {
        return (b3 & 247) == 17 && (b4 & 240) == 48;
    }

    private static boolean isTabCtrlCode(byte b3, byte b4) {
        return (b3 & 247) == 23 && b4 >= 33 && b4 <= 35;
    }

    private static boolean isXdsControlCode(byte b3) {
        return 1 <= b3 && b3 <= 15;
    }

    private void maybeUpdateIsInCaptionService(byte b3, byte b4) {
        if (isXdsControlCode(b3)) {
            this.isInCaptionService = false;
        } else if (isServiceSwitchCommand(b3)) {
            if (!(b4 == 32 || b4 == 47)) {
                switch (b4) {
                    case 37:
                    case 38:
                    case 39:
                        break;
                    default:
                        switch (b4) {
                            case 41:
                                break;
                            case 42:
                            case 43:
                                this.isInCaptionService = false;
                                return;
                            default:
                                return;
                        }
                }
            }
            this.isInCaptionService = true;
        }
    }

    private void resetCueBuilders() {
        this.currentCueBuilder.reset(this.captionMode);
        this.cueBuilders.clear();
        this.cueBuilders.add(this.currentCueBuilder);
    }

    private void setCaptionMode(int i3) {
        int i4 = this.captionMode;
        if (i4 != i3) {
            this.captionMode = i3;
            if (i3 == 3) {
                for (int i5 = 0; i5 < this.cueBuilders.size(); i5++) {
                    this.cueBuilders.get(i5).setCaptionMode(i3);
                }
                return;
            }
            resetCueBuilders();
            if (i4 == 3 || i3 == 1 || i3 == 0) {
                this.cues = Collections.emptyList();
            }
        }
    }

    private void setCaptionRowCount(int i3) {
        this.captionRowCount = i3;
        this.currentCueBuilder.setCaptionRowCount(i3);
    }

    private boolean shouldClearStuckCaptions() {
        return (this.validDataChannelTimeoutUs == C.TIME_UNSET || this.lastCueUpdateUs == C.TIME_UNSET || getPositionUs() - this.lastCueUpdateUs < this.validDataChannelTimeoutUs) ? false : true;
    }

    private boolean updateAndVerifyCurrentChannel(byte b3) {
        if (isCtrlCode(b3)) {
            this.currentChannel = getChannel(b3);
        }
        return this.currentChannel == this.selectedChannel;
    }

    public Subtitle createSubtitle() {
        List<Cue> list = this.cues;
        this.lastCues = list;
        return new CeaSubtitle((List) Assertions.checkNotNull(list));
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0017 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void decode(com.appsamurai.storyly.exoplayer2.extractor.text.SubtitleInputBuffer r10) {
        /*
            r9 = this;
            java.nio.ByteBuffer r10 = r10.data
            java.lang.Object r10 = com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkNotNull(r10)
            java.nio.ByteBuffer r10 = (java.nio.ByteBuffer) r10
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r0 = r9.ccData
            byte[] r1 = r10.array()
            int r10 = r10.limit()
            r0.reset(r1, r10)
            r10 = 0
            r0 = r10
        L_0x0017:
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r1 = r9.ccData
            int r1 = r1.bytesLeft()
            int r2 = r9.packetLength
            r3 = 1
            if (r1 < r2) goto L_0x00f6
            r1 = 2
            if (r2 != r1) goto L_0x0027
            r1 = -4
            goto L_0x002e
        L_0x0027:
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r1 = r9.ccData
            int r1 = r1.readUnsignedByte()
            byte r1 = (byte) r1
        L_0x002e:
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r2 = r9.ccData
            int r2 = r2.readUnsignedByte()
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r4 = r9.ccData
            int r4 = r4.readUnsignedByte()
            r5 = r1 & 2
            if (r5 == 0) goto L_0x003f
            goto L_0x0017
        L_0x003f:
            r5 = r1 & 1
            int r6 = r9.selectedField
            if (r5 == r6) goto L_0x0046
            goto L_0x0017
        L_0x0046:
            r5 = r2 & 127(0x7f, float:1.78E-43)
            byte r5 = (byte) r5
            r6 = r4 & 127(0x7f, float:1.78E-43)
            byte r6 = (byte) r6
            if (r5 != 0) goto L_0x0051
            if (r6 != 0) goto L_0x0051
            goto L_0x0017
        L_0x0051:
            boolean r7 = r9.isCaptionValid
            r1 = r1 & 4
            r8 = 4
            if (r1 != r8) goto L_0x0064
            boolean[] r1 = ODD_PARITY_BYTE_TABLE
            boolean r2 = r1[r2]
            if (r2 == 0) goto L_0x0064
            boolean r1 = r1[r4]
            if (r1 == 0) goto L_0x0064
            r1 = r3
            goto L_0x0065
        L_0x0064:
            r1 = r10
        L_0x0065:
            r9.isCaptionValid = r1
            boolean r1 = r9.isRepeatedCommand(r1, r5, r6)
            if (r1 == 0) goto L_0x006e
            goto L_0x0017
        L_0x006e:
            boolean r1 = r9.isCaptionValid
            if (r1 != 0) goto L_0x0079
            if (r7 == 0) goto L_0x0017
            r9.resetCueBuilders()
        L_0x0077:
            r0 = r3
            goto L_0x0017
        L_0x0079:
            r9.maybeUpdateIsInCaptionService(r5, r6)
            boolean r1 = r9.isInCaptionService
            if (r1 != 0) goto L_0x0081
            goto L_0x0017
        L_0x0081:
            boolean r1 = r9.updateAndVerifyCurrentChannel(r5)
            if (r1 != 0) goto L_0x0088
            goto L_0x0017
        L_0x0088:
            boolean r0 = isCtrlCode(r5)
            if (r0 == 0) goto L_0x00df
            boolean r0 = isSpecialNorthAmericanChar(r5, r6)
            if (r0 == 0) goto L_0x009e
            com.appsamurai.storyly.exoplayer2.extractor.text.cea.Cea608Decoder$CueBuilder r0 = r9.currentCueBuilder
            char r1 = getSpecialNorthAmericanChar(r6)
            r0.append(r1)
            goto L_0x0077
        L_0x009e:
            boolean r0 = isExtendedWestEuropeanChar(r5, r6)
            if (r0 == 0) goto L_0x00b3
            com.appsamurai.storyly.exoplayer2.extractor.text.cea.Cea608Decoder$CueBuilder r0 = r9.currentCueBuilder
            r0.backspace()
            com.appsamurai.storyly.exoplayer2.extractor.text.cea.Cea608Decoder$CueBuilder r0 = r9.currentCueBuilder
            char r1 = getExtendedWestEuropeanChar(r5, r6)
            r0.append(r1)
            goto L_0x0077
        L_0x00b3:
            boolean r0 = isMidrowCtrlCode(r5, r6)
            if (r0 == 0) goto L_0x00bd
            r9.handleMidrowCtrl(r6)
            goto L_0x0077
        L_0x00bd:
            boolean r0 = isPreambleAddressCode(r5, r6)
            if (r0 == 0) goto L_0x00c7
            r9.handlePreambleAddressCode(r5, r6)
            goto L_0x0077
        L_0x00c7:
            boolean r0 = isTabCtrlCode(r5, r6)
            if (r0 == 0) goto L_0x00d5
            com.appsamurai.storyly.exoplayer2.extractor.text.cea.Cea608Decoder$CueBuilder r0 = r9.currentCueBuilder
            int r6 = r6 + -32
            int unused = r0.tabOffset = r6
            goto L_0x0077
        L_0x00d5:
            boolean r0 = isMiscCode(r5, r6)
            if (r0 == 0) goto L_0x0077
            r9.handleMiscCode(r6)
            goto L_0x0077
        L_0x00df:
            com.appsamurai.storyly.exoplayer2.extractor.text.cea.Cea608Decoder$CueBuilder r0 = r9.currentCueBuilder
            char r1 = getBasicChar(r5)
            r0.append(r1)
            r0 = r6 & 224(0xe0, float:3.14E-43)
            if (r0 == 0) goto L_0x0077
            com.appsamurai.storyly.exoplayer2.extractor.text.cea.Cea608Decoder$CueBuilder r0 = r9.currentCueBuilder
            char r1 = getBasicChar(r6)
            r0.append(r1)
            goto L_0x0077
        L_0x00f6:
            if (r0 == 0) goto L_0x010b
            int r10 = r9.captionMode
            if (r10 == r3) goto L_0x00ff
            r0 = 3
            if (r10 != r0) goto L_0x010b
        L_0x00ff:
            java.util.List r10 = r9.getDisplayCues()
            r9.cues = r10
            long r0 = r9.getPositionUs()
            r9.lastCueUpdateUs = r0
        L_0x010b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.extractor.text.cea.Cea608Decoder.decode(com.appsamurai.storyly.exoplayer2.extractor.text.SubtitleInputBuffer):void");
    }

    @Nullable
    public /* bridge */ /* synthetic */ SubtitleInputBuffer dequeueInputBuffer() throws SubtitleDecoderException {
        return super.dequeueInputBuffer();
    }

    public void flush() {
        super.flush();
        this.cues = null;
        this.lastCues = null;
        setCaptionMode(0);
        setCaptionRowCount(4);
        resetCueBuilders();
        this.isCaptionValid = false;
        this.repeatableControlSet = false;
        this.repeatableControlCc1 = 0;
        this.repeatableControlCc2 = 0;
        this.currentChannel = 0;
        this.isInCaptionService = true;
        this.lastCueUpdateUs = C.TIME_UNSET;
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

    public void release() {
    }

    public /* bridge */ /* synthetic */ void setPositionUs(long j2) {
        super.setPositionUs(j2);
    }

    @Nullable
    public SubtitleOutputBuffer dequeueOutputBuffer() throws SubtitleDecoderException {
        SubtitleOutputBuffer availableOutputBuffer;
        SubtitleOutputBuffer dequeueOutputBuffer = super.dequeueOutputBuffer();
        if (dequeueOutputBuffer != null) {
            return dequeueOutputBuffer;
        }
        if (!shouldClearStuckCaptions() || (availableOutputBuffer = getAvailableOutputBuffer()) == null) {
            return null;
        }
        this.cues = Collections.emptyList();
        this.lastCueUpdateUs = C.TIME_UNSET;
        SubtitleOutputBuffer subtitleOutputBuffer = availableOutputBuffer;
        subtitleOutputBuffer.setContent(getPositionUs(), createSubtitle(), Long.MAX_VALUE);
        return availableOutputBuffer;
    }
}
