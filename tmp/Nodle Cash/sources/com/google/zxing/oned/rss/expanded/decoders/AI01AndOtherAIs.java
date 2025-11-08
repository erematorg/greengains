package com.google.zxing.oned.rss.expanded.decoders;

import A.a;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

final class AI01AndOtherAIs extends AI01decoder {
    private static final int HEADER_SIZE = 4;

    public AI01AndOtherAIs(BitArray bitArray) {
        super(bitArray);
    }

    public String parseInformation() throws NotFoundException, FormatException {
        StringBuilder p2 = a.p("(01)");
        int length = p2.length();
        p2.append(getGeneralDecoder().extractNumericValueFromBitArray(4, 4));
        encodeCompressedGtinWithoutAI(p2, 8, length);
        return getGeneralDecoder().decodeAllCodes(p2, 48);
    }
}
