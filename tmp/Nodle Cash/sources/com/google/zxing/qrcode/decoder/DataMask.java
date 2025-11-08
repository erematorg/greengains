package com.google.zxing.qrcode.decoder;

import com.google.zxing.common.BitMatrix;

enum DataMask {
    DATA_MASK_000 {
        public boolean isMasked(int i3, int i4) {
            return ((i3 + i4) & 1) == 0;
        }
    },
    DATA_MASK_001 {
        public boolean isMasked(int i3, int i4) {
            return (i3 & 1) == 0;
        }
    },
    DATA_MASK_010 {
        public boolean isMasked(int i3, int i4) {
            return i4 % 3 == 0;
        }
    },
    DATA_MASK_011 {
        public boolean isMasked(int i3, int i4) {
            return (i3 + i4) % 3 == 0;
        }
    },
    DATA_MASK_100 {
        public boolean isMasked(int i3, int i4) {
            return (((i4 / 3) + (i3 / 2)) & 1) == 0;
        }
    },
    DATA_MASK_101 {
        public boolean isMasked(int i3, int i4) {
            return (i3 * i4) % 6 == 0;
        }
    },
    DATA_MASK_110 {
        public boolean isMasked(int i3, int i4) {
            return (i3 * i4) % 6 < 3;
        }
    },
    DATA_MASK_111 {
        public boolean isMasked(int i3, int i4) {
            return ((((i3 * i4) % 3) + (i3 + i4)) & 1) == 0;
        }
    };

    public abstract boolean isMasked(int i3, int i4);

    public final void unmaskBitMatrix(BitMatrix bitMatrix, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            for (int i5 = 0; i5 < i3; i5++) {
                if (isMasked(i4, i5)) {
                    bitMatrix.flip(i5, i4);
                }
            }
        }
    }
}
