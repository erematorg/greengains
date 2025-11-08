package com.appsamurai.storyly.exoplayer2.extractor.text.ssa;

import android.graphics.PointF;
import android.text.Layout;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.text.Cue;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.r;
import com.appsamurai.storyly.exoplayer2.extractor.text.SimpleSubtitleDecoder;
import com.appsamurai.storyly.exoplayer2.extractor.text.Subtitle;
import com.appsamurai.storyly.exoplayer2.extractor.text.ssa.SsaStyle;
import com.google.common.base.Ascii;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class SsaDecoder extends SimpleSubtitleDecoder {
    private static final float DEFAULT_MARGIN = 0.05f;
    private static final String DIALOGUE_LINE_PREFIX = "Dialogue:";
    static final String FORMAT_LINE_PREFIX = "Format:";
    private static final Pattern SSA_TIMECODE_PATTERN = Pattern.compile("(?:(\\d+):)?(\\d+):(\\d+)[:.](\\d+)");
    static final String STYLE_LINE_PREFIX = "Style:";
    private static final String TAG = "SsaDecoder";
    @Nullable
    private final SsaDialogueFormat dialogueFormatFromInitializationData;
    private final boolean haveInitializationData;
    private float screenHeight;
    private float screenWidth;
    private Map<String, SsaStyle> styles;

    public SsaDecoder() {
        this((List<byte[]>) null);
    }

    private static int addCuePlacerholderByTime(long j2, List<Long> list, List<List<Cue>> list2) {
        int i3;
        ArrayList arrayList;
        int size = list.size() - 1;
        while (true) {
            if (size < 0) {
                i3 = 0;
                break;
            } else if (list.get(size).longValue() == j2) {
                return size;
            } else {
                if (list.get(size).longValue() < j2) {
                    i3 = size + 1;
                    break;
                }
                size--;
            }
        }
        list.add(i3, Long.valueOf(j2));
        if (i3 != 0) {
            arrayList = new ArrayList(list2.get(i3 - 1));
        }
        list2.add(i3, arrayList);
        return i3;
    }

    private static float computeDefaultLineOrPosition(int i3) {
        if (i3 == 0) {
            return DEFAULT_MARGIN;
        }
        if (i3 != 1) {
            return i3 != 2 ? -3.4028235E38f : 0.95f;
        }
        return 0.5f;
    }

    private static Cue createCue(String str, @Nullable SsaStyle ssaStyle, SsaStyle.Overrides overrides, float f2, float f3) {
        SpannableString spannableString = new SpannableString(str);
        Cue.Builder text = new Cue.Builder().setText(spannableString);
        if (ssaStyle != null) {
            if (ssaStyle.primaryColor != null) {
                spannableString.setSpan(new ForegroundColorSpan(ssaStyle.primaryColor.intValue()), 0, spannableString.length(), 33);
            }
            if (ssaStyle.borderStyle == 3 && ssaStyle.outlineColor != null) {
                spannableString.setSpan(new BackgroundColorSpan(ssaStyle.outlineColor.intValue()), 0, spannableString.length(), 33);
            }
            float f4 = ssaStyle.fontSize;
            if (!(f4 == -3.4028235E38f || f3 == -3.4028235E38f)) {
                text.setTextSize(f4 / f3, 1);
            }
            boolean z2 = ssaStyle.bold;
            if (z2 && ssaStyle.italic) {
                spannableString.setSpan(new StyleSpan(3), 0, spannableString.length(), 33);
            } else if (z2) {
                spannableString.setSpan(new StyleSpan(1), 0, spannableString.length(), 33);
            } else if (ssaStyle.italic) {
                spannableString.setSpan(new StyleSpan(2), 0, spannableString.length(), 33);
            }
            if (ssaStyle.underline) {
                spannableString.setSpan(new UnderlineSpan(), 0, spannableString.length(), 33);
            }
            if (ssaStyle.strikeout) {
                spannableString.setSpan(new StrikethroughSpan(), 0, spannableString.length(), 33);
            }
        }
        int i3 = overrides.alignment;
        if (i3 == -1) {
            i3 = ssaStyle != null ? ssaStyle.alignment : -1;
        }
        text.setTextAlignment(toTextAlignment(i3)).setPositionAnchor(toPositionAnchor(i3)).setLineAnchor(toLineAnchor(i3));
        PointF pointF = overrides.position;
        if (pointF == null || f3 == -3.4028235E38f || f2 == -3.4028235E38f) {
            text.setPosition(computeDefaultLineOrPosition(text.getPositionAnchor()));
            text.setLine(computeDefaultLineOrPosition(text.getLineAnchor()), 0);
        } else {
            text.setPosition(pointF.x / f2);
            text.setLine(overrides.position.y / f3, 0);
        }
        return text.build();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x005b, code lost:
        r3 = r12.styleIndex;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void parseDialogueLine(java.lang.String r11, com.appsamurai.storyly.exoplayer2.extractor.text.ssa.SsaDialogueFormat r12, java.util.List<java.util.List<com.appsamurai.storyly.exoplayer2.common.text.Cue>> r13, java.util.List<java.lang.Long> r14) {
        /*
            r10 = this;
            java.lang.String r0 = "Dialogue:"
            boolean r0 = r11.startsWith(r0)
            com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkArgument(r0)
            r0 = 9
            java.lang.String r0 = r11.substring(r0)
            int r1 = r12.length
            java.lang.String r2 = ","
            java.lang.String[] r0 = r0.split(r2, r1)
            int r1 = r0.length
            int r2 = r12.length
            java.lang.String r3 = "SsaDecoder"
            if (r1 == r2) goto L_0x0028
            java.lang.String r10 = "Skipping dialogue line with fewer columns than format: "
            java.lang.String r10 = r10.concat(r11)
            com.appsamurai.storyly.exoplayer2.common.util.Log.w(r3, r10)
            return
        L_0x0028:
            int r1 = r12.startTimeIndex
            r1 = r0[r1]
            long r1 = parseTimecodeUs(r1)
            r4 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r6 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            java.lang.String r7 = "Skipping invalid timing: "
            if (r6 != 0) goto L_0x0043
            java.lang.String r10 = r7.concat(r11)
            com.appsamurai.storyly.exoplayer2.common.util.Log.w(r3, r10)
            return
        L_0x0043:
            int r6 = r12.endTimeIndex
            r6 = r0[r6]
            long r8 = parseTimecodeUs(r6)
            int r4 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r4 != 0) goto L_0x0057
            java.lang.String r10 = r7.concat(r11)
            com.appsamurai.storyly.exoplayer2.common.util.Log.w(r3, r10)
            return
        L_0x0057:
            java.util.Map<java.lang.String, com.appsamurai.storyly.exoplayer2.extractor.text.ssa.SsaStyle> r11 = r10.styles
            if (r11 == 0) goto L_0x006d
            int r3 = r12.styleIndex
            r4 = -1
            if (r3 == r4) goto L_0x006d
            r3 = r0[r3]
            java.lang.String r3 = r3.trim()
            java.lang.Object r11 = r11.get(r3)
            com.appsamurai.storyly.exoplayer2.extractor.text.ssa.SsaStyle r11 = (com.appsamurai.storyly.exoplayer2.extractor.text.ssa.SsaStyle) r11
            goto L_0x006e
        L_0x006d:
            r11 = 0
        L_0x006e:
            int r12 = r12.textIndex
            r12 = r0[r12]
            com.appsamurai.storyly.exoplayer2.extractor.text.ssa.SsaStyle$Overrides r0 = com.appsamurai.storyly.exoplayer2.extractor.text.ssa.SsaStyle.Overrides.parseFromDialogue(r12)
            java.lang.String r12 = com.appsamurai.storyly.exoplayer2.extractor.text.ssa.SsaStyle.Overrides.stripStyleOverrides(r12)
            java.lang.String r3 = "\\N"
            java.lang.String r4 = "\n"
            java.lang.String r12 = r12.replace(r3, r4)
            java.lang.String r3 = "\\n"
            java.lang.String r12 = r12.replace(r3, r4)
            java.lang.String r3 = "\\h"
            java.lang.String r4 = " "
            java.lang.String r12 = r12.replace(r3, r4)
            float r3 = r10.screenWidth
            float r10 = r10.screenHeight
            com.appsamurai.storyly.exoplayer2.common.text.Cue r10 = createCue(r12, r11, r0, r3, r10)
            int r11 = addCuePlacerholderByTime(r1, r14, r13)
            int r12 = addCuePlacerholderByTime(r8, r14, r13)
        L_0x00a1:
            if (r11 >= r12) goto L_0x00af
            java.lang.Object r14 = r13.get(r11)
            java.util.List r14 = (java.util.List) r14
            r14.add(r10)
            int r11 = r11 + 1
            goto L_0x00a1
        L_0x00af:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.extractor.text.ssa.SsaDecoder.parseDialogueLine(java.lang.String, com.appsamurai.storyly.exoplayer2.extractor.text.ssa.SsaDialogueFormat, java.util.List, java.util.List):void");
    }

    private void parseEventBody(ParsableByteArray parsableByteArray, List<List<Cue>> list, List<Long> list2) {
        SsaDialogueFormat ssaDialogueFormat = this.haveInitializationData ? this.dialogueFormatFromInitializationData : null;
        while (true) {
            String readLine = parsableByteArray.readLine();
            if (readLine == null) {
                return;
            }
            if (readLine.startsWith(FORMAT_LINE_PREFIX)) {
                ssaDialogueFormat = SsaDialogueFormat.fromFormatLine(readLine);
            } else if (readLine.startsWith(DIALOGUE_LINE_PREFIX)) {
                if (ssaDialogueFormat == null) {
                    Log.w(TAG, "Skipping dialogue line before complete format: ".concat(readLine));
                } else {
                    parseDialogueLine(readLine, ssaDialogueFormat, list, list2);
                }
            }
        }
    }

    private void parseHeader(ParsableByteArray parsableByteArray) {
        while (true) {
            String readLine = parsableByteArray.readLine();
            if (readLine == null) {
                return;
            }
            if ("[Script Info]".equalsIgnoreCase(readLine)) {
                parseScriptInfo(parsableByteArray);
            } else if ("[V4+ Styles]".equalsIgnoreCase(readLine)) {
                this.styles = parseStyles(parsableByteArray);
            } else if ("[V4 Styles]".equalsIgnoreCase(readLine)) {
                Log.i(TAG, "[V4 Styles] are not supported");
            } else if ("[Events]".equalsIgnoreCase(readLine)) {
                return;
            }
        }
    }

    private void parseScriptInfo(ParsableByteArray parsableByteArray) {
        while (true) {
            String readLine = parsableByteArray.readLine();
            if (readLine == null) {
                return;
            }
            if (parsableByteArray.bytesLeft() == 0 || parsableByteArray.peekUnsignedByte() != 91) {
                String[] split = readLine.split(":");
                if (split.length == 2) {
                    String lowerCase = Ascii.toLowerCase(split[0].trim());
                    lowerCase.getClass();
                    if (lowerCase.equals("playresx")) {
                        this.screenWidth = Float.parseFloat(split[1].trim());
                    } else if (lowerCase.equals("playresy")) {
                        try {
                            this.screenHeight = Float.parseFloat(split[1].trim());
                        } catch (NumberFormatException unused) {
                        }
                    }
                }
            } else {
                return;
            }
        }
    }

    private static Map<String, SsaStyle> parseStyles(ParsableByteArray parsableByteArray) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        SsaStyle.Format format = null;
        while (true) {
            String readLine = parsableByteArray.readLine();
            if (readLine == null || (parsableByteArray.bytesLeft() != 0 && parsableByteArray.peekUnsignedByte() == 91)) {
                return linkedHashMap;
            }
            if (readLine.startsWith(FORMAT_LINE_PREFIX)) {
                format = SsaStyle.Format.fromFormatLine(readLine);
            } else if (readLine.startsWith(STYLE_LINE_PREFIX)) {
                if (format == null) {
                    Log.w(TAG, "Skipping 'Style:' line before 'Format:' line: ".concat(readLine));
                } else {
                    SsaStyle fromStyleLine = SsaStyle.fromStyleLine(readLine, format);
                    if (fromStyleLine != null) {
                        linkedHashMap.put(fromStyleLine.name, fromStyleLine);
                    }
                }
            }
        }
        return linkedHashMap;
    }

    private static long parseTimecodeUs(String str) {
        Matcher matcher = SSA_TIMECODE_PATTERN.matcher(str.trim());
        if (!matcher.matches()) {
            return C.TIME_UNSET;
        }
        return (Long.parseLong((String) Util.castNonNull(matcher.group(4))) * 10000) + (Long.parseLong((String) Util.castNonNull(matcher.group(3))) * 1000000) + (Long.parseLong((String) Util.castNonNull(matcher.group(2))) * 60000000) + (Long.parseLong((String) Util.castNonNull(matcher.group(1))) * 3600000000L);
    }

    private static int toLineAnchor(int i3) {
        switch (i3) {
            case -1:
                return Integer.MIN_VALUE;
            case 1:
            case 2:
            case 3:
                return 2;
            case 4:
            case 5:
            case 6:
                return 1;
            case 7:
            case 8:
            case 9:
                return 0;
            default:
                r.a(i3, "Unknown alignment: ", TAG);
                return Integer.MIN_VALUE;
        }
    }

    private static int toPositionAnchor(int i3) {
        switch (i3) {
            case -1:
                return Integer.MIN_VALUE;
            case 1:
            case 4:
            case 7:
                return 0;
            case 2:
            case 5:
            case 8:
                return 1;
            case 3:
            case 6:
            case 9:
                return 2;
            default:
                r.a(i3, "Unknown alignment: ", TAG);
                return Integer.MIN_VALUE;
        }
    }

    @Nullable
    private static Layout.Alignment toTextAlignment(int i3) {
        switch (i3) {
            case -1:
                return null;
            case 1:
            case 4:
            case 7:
                return Layout.Alignment.ALIGN_NORMAL;
            case 2:
            case 5:
            case 8:
                return Layout.Alignment.ALIGN_CENTER;
            case 3:
            case 6:
            case 9:
                return Layout.Alignment.ALIGN_OPPOSITE;
            default:
                r.a(i3, "Unknown alignment: ", TAG);
                return null;
        }
    }

    public Subtitle decode(byte[] bArr, int i3, boolean z2) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr, i3);
        if (!this.haveInitializationData) {
            parseHeader(parsableByteArray);
        }
        parseEventBody(parsableByteArray, arrayList, arrayList2);
        return new SsaSubtitle(arrayList, arrayList2);
    }

    public SsaDecoder(@Nullable List<byte[]> list) {
        super(TAG);
        this.screenWidth = -3.4028235E38f;
        this.screenHeight = -3.4028235E38f;
        if (list == null || list.isEmpty()) {
            this.haveInitializationData = false;
            this.dialogueFormatFromInitializationData = null;
            return;
        }
        this.haveInitializationData = true;
        String fromUtf8Bytes = Util.fromUtf8Bytes(list.get(0));
        Assertions.checkArgument(fromUtf8Bytes.startsWith(FORMAT_LINE_PREFIX));
        this.dialogueFormatFromInitializationData = (SsaDialogueFormat) Assertions.checkNotNull(SsaDialogueFormat.fromFormatLine(fromUtf8Bytes));
        parseHeader(new ParsableByteArray(list.get(1)));
    }
}
