package com.google.zxing.datamatrix.encoder;

final class X12Encoder extends C40Encoder {
    public void encode(EncoderContext encoderContext) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            if (!encoderContext.hasMoreCharacters()) {
                break;
            }
            char currentChar = encoderContext.getCurrentChar();
            encoderContext.pos++;
            encodeChar(currentChar, sb);
            if (sb.length() % 3 == 0) {
                C40Encoder.writeNextTriplet(encoderContext, sb);
                if (HighLevelEncoder.lookAheadTest(encoderContext.getMessage(), encoderContext.pos, getEncodingMode()) != getEncodingMode()) {
                    encoderContext.signalEncoderChange(0);
                    break;
                }
            }
        }
        handleEOD(encoderContext, sb);
    }

    public int encodeChar(char c3, StringBuilder sb) {
        if (c3 == 13) {
            sb.append(0);
        } else if (c3 == ' ') {
            sb.append(3);
        } else if (c3 == '*') {
            sb.append(1);
        } else if (c3 == '>') {
            sb.append(2);
        } else if (c3 >= '0' && c3 <= '9') {
            sb.append((char) (c3 - ','));
        } else if (c3 < 'A' || c3 > 'Z') {
            HighLevelEncoder.illegalCharacter(c3);
        } else {
            sb.append((char) (c3 - '3'));
        }
        return 1;
    }

    public int getEncodingMode() {
        return 3;
    }

    public void handleEOD(EncoderContext encoderContext, StringBuilder sb) {
        encoderContext.updateSymbolInfo();
        int dataCapacity = encoderContext.getSymbolInfo().getDataCapacity() - encoderContext.getCodewordCount();
        encoderContext.pos -= sb.length();
        if (encoderContext.getRemainingCharacters() > 1 || dataCapacity > 1 || encoderContext.getRemainingCharacters() != dataCapacity) {
            encoderContext.writeCodeword(254);
        }
        if (encoderContext.getNewEncoding() < 0) {
            encoderContext.signalEncoderChange(0);
        }
    }
}
