package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;

final class AI01320xDecoder extends AI013x0xDecoder {
    public AI01320xDecoder(BitArray bitArray) {
        super(bitArray);
    }

    public void addWeightCode(StringBuilder sb, int i3) {
        if (i3 < 10000) {
            sb.append("(3202)");
        } else {
            sb.append("(3203)");
        }
    }

    public int checkWeight(int i3) {
        return i3 < 10000 ? i3 : i3 - 10000;
    }
}
