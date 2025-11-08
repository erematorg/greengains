package com.google.zxing.oned.rss.expanded.decoders;

import A.a;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.fasterxml.jackson.core.JsonPointer;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;
import jnr.ffi.provider.jffi.JNINativeInterface;
import kotlin.text.Typography;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.lang3.ClassUtils;
import org.objectweb.asm.signature.SignatureVisitor;

final class GeneralAppIdDecoder {
    private final StringBuilder buffer = new StringBuilder();
    private final CurrentParsingState current = new CurrentParsingState();
    private final BitArray information;

    public GeneralAppIdDecoder(BitArray bitArray) {
        this.information = bitArray;
    }

    private DecodedChar decodeAlphanumeric(int i3) {
        char c3;
        int extractNumericValueFromBitArray = extractNumericValueFromBitArray(i3, 5);
        if (extractNumericValueFromBitArray == 15) {
            return new DecodedChar(i3 + 5, '$');
        }
        if (extractNumericValueFromBitArray >= 5 && extractNumericValueFromBitArray < 15) {
            return new DecodedChar(i3 + 5, (char) (extractNumericValueFromBitArray + 43));
        }
        int extractNumericValueFromBitArray2 = extractNumericValueFromBitArray(i3, 6);
        if (extractNumericValueFromBitArray2 >= 32 && extractNumericValueFromBitArray2 < 58) {
            return new DecodedChar(i3 + 6, (char) (extractNumericValueFromBitArray2 + 33));
        }
        switch (extractNumericValueFromBitArray2) {
            case 58:
                c3 = '*';
                break;
            case 59:
                c3 = AbstractJsonLexerKt.COMMA;
                break;
            case 60:
                c3 = SignatureVisitor.SUPER;
                break;
            case 61:
                c3 = ClassUtils.PACKAGE_SEPARATOR_CHAR;
                break;
            case 62:
                c3 = JsonPointer.SEPARATOR;
                break;
            default:
                throw new IllegalStateException(a.k("Decoding invalid alphanumeric value: ", extractNumericValueFromBitArray2));
        }
        return new DecodedChar(i3 + 6, c3);
    }

    private DecodedChar decodeIsoIec646(int i3) throws FormatException {
        int extractNumericValueFromBitArray = extractNumericValueFromBitArray(i3, 5);
        if (extractNumericValueFromBitArray == 15) {
            return new DecodedChar(i3 + 5, '$');
        }
        char c3 = SignatureVisitor.EXTENDS;
        if (extractNumericValueFromBitArray >= 5 && extractNumericValueFromBitArray < 15) {
            return new DecodedChar(i3 + 5, (char) (extractNumericValueFromBitArray + 43));
        }
        int extractNumericValueFromBitArray2 = extractNumericValueFromBitArray(i3, 7);
        if (extractNumericValueFromBitArray2 >= 64 && extractNumericValueFromBitArray2 < 90) {
            return new DecodedChar(i3 + 7, (char) (extractNumericValueFromBitArray2 + 1));
        }
        if (extractNumericValueFromBitArray2 >= 90 && extractNumericValueFromBitArray2 < 116) {
            return new DecodedChar(i3 + 7, (char) (extractNumericValueFromBitArray2 + 7));
        }
        switch (extractNumericValueFromBitArray(i3, 8)) {
            case JNINativeInterface.GetObjectRefType /*232*/:
                c3 = '!';
                break;
            case 233:
                c3 = '\"';
                break;
            case 234:
                c3 = '%';
                break;
            case 235:
                c3 = Typography.amp;
                break;
            case 236:
                c3 = '\'';
                break;
            case 237:
                c3 = '(';
                break;
            case 238:
                c3 = ')';
                break;
            case 239:
                c3 = '*';
                break;
            case 240:
                break;
            case 241:
                c3 = AbstractJsonLexerKt.COMMA;
                break;
            case 242:
                c3 = SignatureVisitor.SUPER;
                break;
            case 243:
                c3 = ClassUtils.PACKAGE_SEPARATOR_CHAR;
                break;
            case 244:
                c3 = JsonPointer.SEPARATOR;
                break;
            case 245:
                c3 = AbstractJsonLexerKt.COLON;
                break;
            case 246:
                c3 = ';';
                break;
            case 247:
                c3 = Typography.less;
                break;
            case 248:
                c3 = SignatureVisitor.INSTANCEOF;
                break;
            case 249:
                c3 = Typography.greater;
                break;
            case ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION:
                c3 = '?';
                break;
            case 251:
                c3 = '_';
                break;
            case 252:
                c3 = ' ';
                break;
            default:
                throw FormatException.getFormatInstance();
        }
        return new DecodedChar(i3 + 8, c3);
    }

    private DecodedNumeric decodeNumeric(int i3) throws FormatException {
        int i4 = i3 + 7;
        if (i4 > this.information.getSize()) {
            int extractNumericValueFromBitArray = extractNumericValueFromBitArray(i3, 4);
            return extractNumericValueFromBitArray == 0 ? new DecodedNumeric(this.information.getSize(), 10, 10) : new DecodedNumeric(this.information.getSize(), extractNumericValueFromBitArray - 1, 10);
        }
        int extractNumericValueFromBitArray2 = extractNumericValueFromBitArray(i3, 7) - 8;
        return new DecodedNumeric(i4, extractNumericValueFromBitArray2 / 11, extractNumericValueFromBitArray2 % 11);
    }

    private boolean isAlphaOr646ToNumericLatch(int i3) {
        int i4 = i3 + 3;
        if (i4 > this.information.getSize()) {
            return false;
        }
        while (i3 < i4) {
            if (this.information.get(i3)) {
                return false;
            }
            i3++;
        }
        return true;
    }

    private boolean isAlphaTo646ToAlphaLatch(int i3) {
        int i4;
        if (i3 + 1 > this.information.getSize()) {
            return false;
        }
        int i5 = 0;
        while (i5 < 5 && (i4 = i5 + i3) < this.information.getSize()) {
            if (i5 == 2) {
                if (!this.information.get(i3 + 2)) {
                    return false;
                }
            } else if (this.information.get(i4)) {
                return false;
            }
            i5++;
        }
        return true;
    }

    private boolean isNumericToAlphaNumericLatch(int i3) {
        int i4;
        if (i3 + 1 > this.information.getSize()) {
            return false;
        }
        int i5 = 0;
        while (i5 < 4 && (i4 = i5 + i3) < this.information.getSize()) {
            if (this.information.get(i4)) {
                return false;
            }
            i5++;
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0024, code lost:
        r5 = extractNumericValueFromBitArray(r6, 6);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean isStillAlpha(int r6) {
        /*
            r5 = this;
            int r0 = r6 + 5
            com.google.zxing.common.BitArray r1 = r5.information
            int r1 = r1.getSize()
            r2 = 0
            if (r0 <= r1) goto L_0x000c
            return r2
        L_0x000c:
            r0 = 5
            int r1 = r5.extractNumericValueFromBitArray(r6, r0)
            r3 = 1
            r4 = 16
            if (r1 < r0) goto L_0x0019
            if (r1 >= r4) goto L_0x0019
            return r3
        L_0x0019:
            int r0 = r6 + 6
            com.google.zxing.common.BitArray r1 = r5.information
            int r1 = r1.getSize()
            if (r0 <= r1) goto L_0x0024
            return r2
        L_0x0024:
            r0 = 6
            int r5 = r5.extractNumericValueFromBitArray(r6, r0)
            if (r5 < r4) goto L_0x0030
            r6 = 63
            if (r5 >= r6) goto L_0x0030
            r2 = r3
        L_0x0030:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.rss.expanded.decoders.GeneralAppIdDecoder.isStillAlpha(int):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003d, code lost:
        r4 = extractNumericValueFromBitArray(r5, 8);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean isStillIsoIec646(int r5) {
        /*
            r4 = this;
            int r0 = r5 + 5
            com.google.zxing.common.BitArray r1 = r4.information
            int r1 = r1.getSize()
            r2 = 0
            if (r0 <= r1) goto L_0x000c
            return r2
        L_0x000c:
            r0 = 5
            int r1 = r4.extractNumericValueFromBitArray(r5, r0)
            r3 = 1
            if (r1 < r0) goto L_0x0019
            r0 = 16
            if (r1 >= r0) goto L_0x0019
            return r3
        L_0x0019:
            int r0 = r5 + 7
            com.google.zxing.common.BitArray r1 = r4.information
            int r1 = r1.getSize()
            if (r0 <= r1) goto L_0x0024
            return r2
        L_0x0024:
            r0 = 7
            int r0 = r4.extractNumericValueFromBitArray(r5, r0)
            r1 = 64
            if (r0 < r1) goto L_0x0032
            r1 = 116(0x74, float:1.63E-43)
            if (r0 >= r1) goto L_0x0032
            return r3
        L_0x0032:
            int r0 = r5 + 8
            com.google.zxing.common.BitArray r1 = r4.information
            int r1 = r1.getSize()
            if (r0 <= r1) goto L_0x003d
            return r2
        L_0x003d:
            r0 = 8
            int r4 = r4.extractNumericValueFromBitArray(r5, r0)
            r5 = 232(0xe8, float:3.25E-43)
            if (r4 < r5) goto L_0x004c
            r5 = 253(0xfd, float:3.55E-43)
            if (r4 >= r5) goto L_0x004c
            r2 = r3
        L_0x004c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.rss.expanded.decoders.GeneralAppIdDecoder.isStillIsoIec646(int):boolean");
    }

    private boolean isStillNumeric(int i3) {
        if (i3 + 7 > this.information.getSize()) {
            return i3 + 4 <= this.information.getSize();
        }
        int i4 = i3;
        while (true) {
            int i5 = i3 + 3;
            if (i4 >= i5) {
                return this.information.get(i5);
            }
            if (this.information.get(i4)) {
                return true;
            }
            i4++;
        }
    }

    private BlockParsedResult parseAlphaBlock() {
        while (isStillAlpha(this.current.getPosition())) {
            DecodedChar decodeAlphanumeric = decodeAlphanumeric(this.current.getPosition());
            this.current.setPosition(decodeAlphanumeric.getNewPosition());
            if (decodeAlphanumeric.isFNC1()) {
                return new BlockParsedResult(new DecodedInformation(this.current.getPosition(), this.buffer.toString()), true);
            }
            this.buffer.append(decodeAlphanumeric.getValue());
        }
        if (isAlphaOr646ToNumericLatch(this.current.getPosition())) {
            this.current.incrementPosition(3);
            this.current.setNumeric();
        } else if (isAlphaTo646ToAlphaLatch(this.current.getPosition())) {
            if (this.current.getPosition() + 5 < this.information.getSize()) {
                this.current.incrementPosition(5);
            } else {
                this.current.setPosition(this.information.getSize());
            }
            this.current.setIsoIec646();
        }
        return new BlockParsedResult();
    }

    private DecodedInformation parseBlocks() throws FormatException {
        boolean z2;
        BlockParsedResult blockParsedResult;
        do {
            int position = this.current.getPosition();
            if (this.current.isAlpha()) {
                blockParsedResult = parseAlphaBlock();
                z2 = blockParsedResult.isFinished();
            } else if (this.current.isIsoIec646()) {
                blockParsedResult = parseIsoIec646Block();
                z2 = blockParsedResult.isFinished();
            } else {
                blockParsedResult = parseNumericBlock();
                z2 = blockParsedResult.isFinished();
            }
            if (position != this.current.getPosition() || z2) {
                break;
                break;
            }
            break;
        } while (!z2);
        return blockParsedResult.getDecodedInformation();
    }

    private BlockParsedResult parseIsoIec646Block() throws FormatException {
        while (isStillIsoIec646(this.current.getPosition())) {
            DecodedChar decodeIsoIec646 = decodeIsoIec646(this.current.getPosition());
            this.current.setPosition(decodeIsoIec646.getNewPosition());
            if (decodeIsoIec646.isFNC1()) {
                return new BlockParsedResult(new DecodedInformation(this.current.getPosition(), this.buffer.toString()), true);
            }
            this.buffer.append(decodeIsoIec646.getValue());
        }
        if (isAlphaOr646ToNumericLatch(this.current.getPosition())) {
            this.current.incrementPosition(3);
            this.current.setNumeric();
        } else if (isAlphaTo646ToAlphaLatch(this.current.getPosition())) {
            if (this.current.getPosition() + 5 < this.information.getSize()) {
                this.current.incrementPosition(5);
            } else {
                this.current.setPosition(this.information.getSize());
            }
            this.current.setAlpha();
        }
        return new BlockParsedResult();
    }

    private BlockParsedResult parseNumericBlock() throws FormatException {
        while (isStillNumeric(this.current.getPosition())) {
            DecodedNumeric decodeNumeric = decodeNumeric(this.current.getPosition());
            this.current.setPosition(decodeNumeric.getNewPosition());
            if (decodeNumeric.isFirstDigitFNC1()) {
                return new BlockParsedResult(decodeNumeric.isSecondDigitFNC1() ? new DecodedInformation(this.current.getPosition(), this.buffer.toString()) : new DecodedInformation(this.current.getPosition(), this.buffer.toString(), decodeNumeric.getSecondDigit()), true);
            }
            this.buffer.append(decodeNumeric.getFirstDigit());
            if (decodeNumeric.isSecondDigitFNC1()) {
                return new BlockParsedResult(new DecodedInformation(this.current.getPosition(), this.buffer.toString()), true);
            }
            this.buffer.append(decodeNumeric.getSecondDigit());
        }
        if (isNumericToAlphaNumericLatch(this.current.getPosition())) {
            this.current.setAlpha();
            this.current.incrementPosition(4);
        }
        return new BlockParsedResult();
    }

    public String decodeAllCodes(StringBuilder sb, int i3) throws NotFoundException, FormatException {
        String str = null;
        while (true) {
            DecodedInformation decodeGeneralPurposeField = decodeGeneralPurposeField(i3, str);
            String parseFieldsInGeneralPurpose = FieldParser.parseFieldsInGeneralPurpose(decodeGeneralPurposeField.getNewString());
            if (parseFieldsInGeneralPurpose != null) {
                sb.append(parseFieldsInGeneralPurpose);
            }
            String valueOf = decodeGeneralPurposeField.isRemaining() ? String.valueOf(decodeGeneralPurposeField.getRemainingValue()) : null;
            if (i3 == decodeGeneralPurposeField.getNewPosition()) {
                return sb.toString();
            }
            i3 = decodeGeneralPurposeField.getNewPosition();
            str = valueOf;
        }
    }

    public DecodedInformation decodeGeneralPurposeField(int i3, String str) throws FormatException {
        this.buffer.setLength(0);
        if (str != null) {
            this.buffer.append(str);
        }
        this.current.setPosition(i3);
        DecodedInformation parseBlocks = parseBlocks();
        return (parseBlocks == null || !parseBlocks.isRemaining()) ? new DecodedInformation(this.current.getPosition(), this.buffer.toString()) : new DecodedInformation(this.current.getPosition(), this.buffer.toString(), parseBlocks.getRemainingValue());
    }

    public int extractNumericValueFromBitArray(int i3, int i4) {
        return extractNumericValueFromBitArray(this.information, i3, i4);
    }

    public static int extractNumericValueFromBitArray(BitArray bitArray, int i3, int i4) {
        int i5 = 0;
        for (int i6 = 0; i6 < i4; i6++) {
            if (bitArray.get(i3 + i6)) {
                i5 |= 1 << ((i4 - i6) - 1);
            }
        }
        return i5;
    }
}
