package com.google.zxing.datamatrix.encoder;

import A.a;

final class ASCIIEncoder implements Encoder {
    private static char encodeASCIIDigits(char c3, char c4) {
        if (!HighLevelEncoder.isDigit(c3) || !HighLevelEncoder.isDigit(c4)) {
            throw new IllegalArgumentException("not digits: " + c3 + c4);
        }
        return (char) ((c4 - '0') + ((c3 - '0') * 10) + 130);
    }

    public void encode(EncoderContext encoderContext) {
        if (HighLevelEncoder.determineConsecutiveDigitCount(encoderContext.getMessage(), encoderContext.pos) >= 2) {
            encoderContext.writeCodeword(encodeASCIIDigits(encoderContext.getMessage().charAt(encoderContext.pos), encoderContext.getMessage().charAt(encoderContext.pos + 1)));
            encoderContext.pos += 2;
            return;
        }
        char currentChar = encoderContext.getCurrentChar();
        int lookAheadTest = HighLevelEncoder.lookAheadTest(encoderContext.getMessage(), encoderContext.pos, getEncodingMode());
        if (lookAheadTest != getEncodingMode()) {
            if (lookAheadTest == 1) {
                encoderContext.writeCodeword(230);
                encoderContext.signalEncoderChange(1);
            } else if (lookAheadTest == 2) {
                encoderContext.writeCodeword(239);
                encoderContext.signalEncoderChange(2);
            } else if (lookAheadTest == 3) {
                encoderContext.writeCodeword(238);
                encoderContext.signalEncoderChange(3);
            } else if (lookAheadTest == 4) {
                encoderContext.writeCodeword(240);
                encoderContext.signalEncoderChange(4);
            } else if (lookAheadTest == 5) {
                encoderContext.writeCodeword(231);
                encoderContext.signalEncoderChange(5);
            } else {
                throw new IllegalStateException(a.k("Illegal mode: ", lookAheadTest));
            }
        } else if (HighLevelEncoder.isExtendedASCII(currentChar)) {
            encoderContext.writeCodeword(235);
            encoderContext.writeCodeword((char) (currentChar - 127));
            encoderContext.pos++;
        } else {
            encoderContext.writeCodeword((char) (currentChar + 1));
            encoderContext.pos++;
        }
    }

    public int getEncodingMode() {
        return 0;
    }
}
