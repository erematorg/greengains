package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

final class AI013x0x1xDecoder extends AI01weightDecoder {
    private static final int DATE_SIZE = 16;
    private static final int HEADER_SIZE = 8;
    private static final int WEIGHT_SIZE = 20;
    private final String dateCode;
    private final String firstAIdigits;

    public AI013x0x1xDecoder(BitArray bitArray, String str, String str2) {
        super(bitArray);
        this.dateCode = str2;
        this.firstAIdigits = str;
    }

    private void encodeCompressedDate(StringBuilder sb, int i3) {
        int extractNumericValueFromBitArray = getGeneralDecoder().extractNumericValueFromBitArray(i3, 16);
        if (extractNumericValueFromBitArray != 38400) {
            sb.append('(');
            sb.append(this.dateCode);
            sb.append(')');
            int i4 = extractNumericValueFromBitArray % 32;
            int i5 = extractNumericValueFromBitArray / 32;
            int i6 = (i5 % 12) + 1;
            int i7 = i5 / 12;
            if (i7 / 10 == 0) {
                sb.append('0');
            }
            sb.append(i7);
            if (i6 / 10 == 0) {
                sb.append('0');
            }
            sb.append(i6);
            if (i4 / 10 == 0) {
                sb.append('0');
            }
            sb.append(i4);
        }
    }

    public void addWeightCode(StringBuilder sb, int i3) {
        sb.append('(');
        sb.append(this.firstAIdigits);
        sb.append(i3 / 100000);
        sb.append(')');
    }

    public int checkWeight(int i3) {
        return i3 % 100000;
    }

    public String parseInformation() throws NotFoundException {
        if (getInformation().getSize() == 84) {
            StringBuilder sb = new StringBuilder();
            encodeCompressedGtin(sb, 8);
            encodeCompressedWeight(sb, 48, 20);
            encodeCompressedDate(sb, 68);
            return sb.toString();
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
