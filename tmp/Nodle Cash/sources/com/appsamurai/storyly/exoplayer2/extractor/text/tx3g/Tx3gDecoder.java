package com.appsamurai.storyly.exoplayer2.extractor.text.tx3g;

import A.a;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import androidx.camera.core.impl.i;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.text.Cue;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.extractor.text.SimpleSubtitleDecoder;
import com.appsamurai.storyly.exoplayer2.extractor.text.Subtitle;
import com.appsamurai.storyly.exoplayer2.extractor.text.SubtitleDecoderException;
import com.google.common.base.Ascii;
import com.google.common.base.Charsets;
import java.util.List;

public final class Tx3gDecoder extends SimpleSubtitleDecoder {
    private static final char BOM_UTF16_BE = '﻿';
    private static final char BOM_UTF16_LE = '￾';
    private static final int DEFAULT_COLOR = -1;
    private static final int DEFAULT_FONT_FACE = 0;
    private static final String DEFAULT_FONT_FAMILY = "sans-serif";
    private static final float DEFAULT_VERTICAL_PLACEMENT = 0.85f;
    private static final int FONT_FACE_BOLD = 1;
    private static final int FONT_FACE_ITALIC = 2;
    private static final int FONT_FACE_UNDERLINE = 4;
    private static final int SIZE_ATOM_HEADER = 8;
    private static final int SIZE_BOM_UTF16 = 2;
    private static final int SIZE_SHORT = 2;
    private static final int SIZE_STYLE_RECORD = 12;
    private static final int SPAN_PRIORITY_HIGH = 0;
    private static final int SPAN_PRIORITY_LOW = 16711680;
    private static final String TAG = "Tx3gDecoder";
    private static final String TX3G_SERIF = "Serif";
    private static final int TYPE_STYL = 1937013100;
    private static final int TYPE_TBOX = 1952608120;
    private final int calculatedVideoTrackHeight;
    private final boolean customVerticalPlacement;
    private final int defaultColorRgba;
    private final int defaultFontFace;
    private final String defaultFontFamily;
    private final float defaultVerticalPlacement;
    private final ParsableByteArray parsableByteArray = new ParsableByteArray();

    public Tx3gDecoder(List<byte[]> list) {
        super(TAG);
        String str = "sans-serif";
        boolean z2 = false;
        if (list.size() == 1 && (list.get(0).length == 48 || list.get(0).length == 53)) {
            byte[] bArr = list.get(0);
            this.defaultFontFace = bArr[24];
            this.defaultColorRgba = ((bArr[26] & 255) << Ascii.CAN) | ((bArr[27] & 255) << 16) | ((bArr[28] & 255) << 8) | (bArr[29] & 255);
            this.defaultFontFamily = TX3G_SERIF.equals(Util.fromUtf8Bytes(bArr, 43, bArr.length - 43)) ? C.SERIF_NAME : str;
            int i3 = bArr[25] * Ascii.DC4;
            this.calculatedVideoTrackHeight = i3;
            z2 = (bArr[0] & 32) != 0 ? true : z2;
            this.customVerticalPlacement = z2;
            if (z2) {
                this.defaultVerticalPlacement = Util.constrainValue(((float) ((bArr[11] & 255) | ((bArr[10] & 255) << 8))) / ((float) i3), 0.0f, 0.95f);
            } else {
                this.defaultVerticalPlacement = DEFAULT_VERTICAL_PLACEMENT;
            }
        } else {
            this.defaultFontFace = 0;
            this.defaultColorRgba = -1;
            this.defaultFontFamily = str;
            this.customVerticalPlacement = false;
            this.defaultVerticalPlacement = DEFAULT_VERTICAL_PLACEMENT;
            this.calculatedVideoTrackHeight = -1;
        }
    }

    private void applyStyleRecord(ParsableByteArray parsableByteArray2, SpannableStringBuilder spannableStringBuilder) throws SubtitleDecoderException {
        assertTrue(parsableByteArray2.bytesLeft() >= 12);
        int readUnsignedShort = parsableByteArray2.readUnsignedShort();
        int readUnsignedShort2 = parsableByteArray2.readUnsignedShort();
        parsableByteArray2.skipBytes(2);
        int readUnsignedByte = parsableByteArray2.readUnsignedByte();
        parsableByteArray2.skipBytes(1);
        int readInt = parsableByteArray2.readInt();
        if (readUnsignedShort2 > spannableStringBuilder.length()) {
            StringBuilder o3 = a.o(readUnsignedShort2, "Truncating styl end (", ") to cueText.length() (");
            o3.append(spannableStringBuilder.length());
            o3.append(").");
            Log.w(TAG, o3.toString());
            readUnsignedShort2 = spannableStringBuilder.length();
        }
        if (readUnsignedShort >= readUnsignedShort2) {
            Log.w(TAG, i.a(readUnsignedShort, readUnsignedShort2, "Ignoring styl with start (", ") >= end (", ")."));
            return;
        }
        SpannableStringBuilder spannableStringBuilder2 = spannableStringBuilder;
        int i3 = readUnsignedShort;
        int i4 = readUnsignedShort2;
        attachFontFace(spannableStringBuilder2, readUnsignedByte, this.defaultFontFace, i3, i4, 0);
        attachColor(spannableStringBuilder2, readInt, this.defaultColorRgba, i3, i4, 0);
    }

    private static void assertTrue(boolean z2) throws SubtitleDecoderException {
        if (!z2) {
            throw new SubtitleDecoderException("Unexpected subtitle format.");
        }
    }

    private static void attachColor(SpannableStringBuilder spannableStringBuilder, int i3, int i4, int i5, int i6, int i7) {
        if (i3 != i4) {
            spannableStringBuilder.setSpan(new ForegroundColorSpan((i3 >>> 8) | ((i3 & 255) << 24)), i5, i6, i7 | 33);
        }
    }

    private static void attachFontFace(SpannableStringBuilder spannableStringBuilder, int i3, int i4, int i5, int i6, int i7) {
        if (i3 != i4) {
            int i8 = i7 | 33;
            boolean z2 = true;
            boolean z3 = (i3 & 1) != 0;
            boolean z4 = (i3 & 2) != 0;
            if (z3) {
                if (z4) {
                    spannableStringBuilder.setSpan(new StyleSpan(3), i5, i6, i8);
                } else {
                    spannableStringBuilder.setSpan(new StyleSpan(1), i5, i6, i8);
                }
            } else if (z4) {
                spannableStringBuilder.setSpan(new StyleSpan(2), i5, i6, i8);
            }
            if ((i3 & 4) == 0) {
                z2 = false;
            }
            if (z2) {
                spannableStringBuilder.setSpan(new UnderlineSpan(), i5, i6, i8);
            }
            if (!z2 && !z3 && !z4) {
                spannableStringBuilder.setSpan(new StyleSpan(0), i5, i6, i8);
            }
        }
    }

    private static void attachFontFamily(SpannableStringBuilder spannableStringBuilder, String str, int i3, int i4) {
        if (str != "sans-serif") {
            spannableStringBuilder.setSpan(new TypefaceSpan(str), i3, i4, 16711713);
        }
    }

    private static String readSubtitleText(ParsableByteArray parsableByteArray2) throws SubtitleDecoderException {
        char peekChar;
        assertTrue(parsableByteArray2.bytesLeft() >= 2);
        int readUnsignedShort = parsableByteArray2.readUnsignedShort();
        return readUnsignedShort == 0 ? "" : (parsableByteArray2.bytesLeft() < 2 || !((peekChar = parsableByteArray2.peekChar()) == 65279 || peekChar == 65534)) ? parsableByteArray2.readString(readUnsignedShort, Charsets.UTF_8) : parsableByteArray2.readString(readUnsignedShort, Charsets.UTF_16);
    }

    public Subtitle decode(byte[] bArr, int i3, boolean z2) throws SubtitleDecoderException {
        this.parsableByteArray.reset(bArr, i3);
        String readSubtitleText = readSubtitleText(this.parsableByteArray);
        if (readSubtitleText.isEmpty()) {
            return Tx3gSubtitle.EMPTY;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(readSubtitleText);
        SpannableStringBuilder spannableStringBuilder2 = spannableStringBuilder;
        attachFontFace(spannableStringBuilder2, this.defaultFontFace, 0, 0, spannableStringBuilder.length(), SPAN_PRIORITY_LOW);
        attachColor(spannableStringBuilder2, this.defaultColorRgba, -1, 0, spannableStringBuilder.length(), SPAN_PRIORITY_LOW);
        attachFontFamily(spannableStringBuilder, this.defaultFontFamily, 0, spannableStringBuilder.length());
        float f2 = this.defaultVerticalPlacement;
        while (this.parsableByteArray.bytesLeft() >= 8) {
            int position = this.parsableByteArray.getPosition();
            int readInt = this.parsableByteArray.readInt();
            int readInt2 = this.parsableByteArray.readInt();
            boolean z3 = true;
            if (readInt2 == TYPE_STYL) {
                if (this.parsableByteArray.bytesLeft() < 2) {
                    z3 = false;
                }
                assertTrue(z3);
                int readUnsignedShort = this.parsableByteArray.readUnsignedShort();
                for (int i4 = 0; i4 < readUnsignedShort; i4++) {
                    applyStyleRecord(this.parsableByteArray, spannableStringBuilder);
                }
            } else if (readInt2 == TYPE_TBOX && this.customVerticalPlacement) {
                if (this.parsableByteArray.bytesLeft() < 2) {
                    z3 = false;
                }
                assertTrue(z3);
                f2 = Util.constrainValue(((float) this.parsableByteArray.readUnsignedShort()) / ((float) this.calculatedVideoTrackHeight), 0.0f, 0.95f);
            }
            this.parsableByteArray.setPosition(position + readInt);
        }
        return new Tx3gSubtitle(new Cue.Builder().setText(spannableStringBuilder).setLine(f2, 0).setLineAnchor(0).build());
    }
}
