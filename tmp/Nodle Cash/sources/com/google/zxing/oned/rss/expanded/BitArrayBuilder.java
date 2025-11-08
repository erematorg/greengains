package com.google.zxing.oned.rss.expanded;

import android.support.v4.media.session.a;
import com.google.zxing.common.BitArray;
import java.util.List;

final class BitArrayBuilder {
    private BitArrayBuilder() {
    }

    public static BitArray buildBitArray(List<ExpandedPair> list) {
        int size = list.size() * 2;
        int i3 = size - 1;
        if (((ExpandedPair) a.h(list, 1)).getRightChar() == null) {
            i3 = size - 2;
        }
        BitArray bitArray = new BitArray(i3 * 12);
        int i4 = 0;
        int value = list.get(0).getRightChar().getValue();
        for (int i5 = 11; i5 >= 0; i5--) {
            if (((1 << i5) & value) != 0) {
                bitArray.set(i4);
            }
            i4++;
        }
        for (int i6 = 1; i6 < list.size(); i6++) {
            ExpandedPair expandedPair = list.get(i6);
            int value2 = expandedPair.getLeftChar().getValue();
            for (int i7 = 11; i7 >= 0; i7--) {
                if (((1 << i7) & value2) != 0) {
                    bitArray.set(i4);
                }
                i4++;
            }
            if (expandedPair.getRightChar() != null) {
                int value3 = expandedPair.getRightChar().getValue();
                for (int i8 = 11; i8 >= 0; i8--) {
                    if (((1 << i8) & value3) != 0) {
                        bitArray.set(i4);
                    }
                    i4++;
                }
            }
        }
        return bitArray;
    }
}
