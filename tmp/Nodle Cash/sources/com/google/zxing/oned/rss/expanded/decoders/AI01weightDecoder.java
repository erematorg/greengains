package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;

abstract class AI01weightDecoder extends AI01decoder {
    public AI01weightDecoder(BitArray bitArray) {
        super(bitArray);
    }

    public abstract void addWeightCode(StringBuilder sb, int i3);

    public abstract int checkWeight(int i3);

    public final void encodeCompressedWeight(StringBuilder sb, int i3, int i4) {
        int extractNumericValueFromBitArray = getGeneralDecoder().extractNumericValueFromBitArray(i3, i4);
        addWeightCode(sb, extractNumericValueFromBitArray);
        int checkWeight = checkWeight(extractNumericValueFromBitArray);
        int i5 = 100000;
        for (int i6 = 0; i6 < 5; i6++) {
            if (checkWeight / i5 == 0) {
                sb.append('0');
            }
            i5 /= 10;
        }
        sb.append(checkWeight);
    }
}
