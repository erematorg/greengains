package com.google.zxing.datamatrix.encoder;

import A.a;
import androidx.recyclerview.widget.ItemTouchHelper;

final class Base256Encoder implements Encoder {
    private static char randomize255State(char c3, int i3) {
        int i4 = ((i3 * 149) % 255) + 1 + c3;
        return i4 <= 255 ? (char) i4 : (char) (i4 - 256);
    }

    public void encode(EncoderContext encoderContext) {
        StringBuilder sb = new StringBuilder();
        sb.append(0);
        while (true) {
            if (!encoderContext.hasMoreCharacters()) {
                break;
            }
            sb.append(encoderContext.getCurrentChar());
            encoderContext.pos++;
            if (HighLevelEncoder.lookAheadTest(encoderContext.getMessage(), encoderContext.pos, getEncodingMode()) != getEncodingMode()) {
                encoderContext.signalEncoderChange(0);
                break;
            }
        }
        int length = sb.length() - 1;
        int codewordCount = encoderContext.getCodewordCount() + length + 1;
        encoderContext.updateSymbolInfo(codewordCount);
        boolean z2 = encoderContext.getSymbolInfo().getDataCapacity() - codewordCount > 0;
        if (encoderContext.hasMoreCharacters() || z2) {
            if (length <= 249) {
                sb.setCharAt(0, (char) length);
            } else if (length <= 1555) {
                sb.setCharAt(0, (char) ((length / ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION) + 249));
                sb.insert(1, (char) (length % ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION));
            } else {
                throw new IllegalStateException(a.k("Message length not in valid ranges: ", length));
            }
        }
        int length2 = sb.length();
        for (int i3 = 0; i3 < length2; i3++) {
            encoderContext.writeCodeword(randomize255State(sb.charAt(i3), encoderContext.getCodewordCount() + 1));
        }
    }

    public int getEncodingMode() {
        return 5;
    }
}
