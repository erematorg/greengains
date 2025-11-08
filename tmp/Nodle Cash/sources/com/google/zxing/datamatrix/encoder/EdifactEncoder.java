package com.google.zxing.datamatrix.encoder;

final class EdifactEncoder implements Encoder {
    private static void encodeChar(char c3, StringBuilder sb) {
        if (c3 >= ' ' && c3 <= '?') {
            sb.append(c3);
        } else if (c3 < '@' || c3 > '^') {
            HighLevelEncoder.illegalCharacter(c3);
        } else {
            sb.append((char) (c3 - '@'));
        }
    }

    private static String encodeToCodewords(CharSequence charSequence) {
        int length = charSequence.length();
        if (length != 0) {
            char c3 = 0;
            char charAt = charSequence.charAt(0);
            char charAt2 = length >= 2 ? charSequence.charAt(1) : 0;
            char charAt3 = length >= 3 ? charSequence.charAt(2) : 0;
            if (length >= 4) {
                c3 = charSequence.charAt(3);
            }
            int i3 = (charAt << 18) + (charAt2 << 12) + (charAt3 << 6) + c3;
            char c4 = (char) ((i3 >> 8) & 255);
            char c5 = (char) (i3 & 255);
            StringBuilder sb = new StringBuilder(3);
            sb.append((char) ((i3 >> 16) & 255));
            if (length >= 2) {
                sb.append(c4);
            }
            if (length >= 3) {
                sb.append(c5);
            }
            return sb.toString();
        }
        throw new IllegalStateException("StringBuilder must not be empty");
    }

    private static void handleEOD(EncoderContext encoderContext, CharSequence charSequence) {
        try {
            int length = charSequence.length();
            if (length != 0) {
                boolean z2 = true;
                if (length == 1) {
                    encoderContext.updateSymbolInfo();
                    int dataCapacity = encoderContext.getSymbolInfo().getDataCapacity() - encoderContext.getCodewordCount();
                    int remainingCharacters = encoderContext.getRemainingCharacters();
                    if (remainingCharacters > dataCapacity) {
                        encoderContext.updateSymbolInfo(encoderContext.getCodewordCount() + 1);
                        dataCapacity = encoderContext.getSymbolInfo().getDataCapacity() - encoderContext.getCodewordCount();
                    }
                    if (remainingCharacters <= dataCapacity && dataCapacity <= 2) {
                        encoderContext.signalEncoderChange(0);
                        return;
                    }
                }
                if (length <= 4) {
                    int i3 = length - 1;
                    String encodeToCodewords = encodeToCodewords(charSequence);
                    if (encoderContext.hasMoreCharacters() || i3 > 2) {
                        z2 = false;
                    }
                    if (i3 <= 2) {
                        encoderContext.updateSymbolInfo(encoderContext.getCodewordCount() + i3);
                        if (encoderContext.getSymbolInfo().getDataCapacity() - encoderContext.getCodewordCount() >= 3) {
                            encoderContext.updateSymbolInfo(encoderContext.getCodewordCount() + encodeToCodewords.length());
                            z2 = false;
                        }
                    }
                    if (z2) {
                        encoderContext.resetSymbolInfo();
                        encoderContext.pos -= i3;
                    } else {
                        encoderContext.writeCodewords(encodeToCodewords);
                    }
                    encoderContext.signalEncoderChange(0);
                    return;
                }
                throw new IllegalStateException("Count must not exceed 4");
            }
        } finally {
            encoderContext.signalEncoderChange(0);
        }
    }

    public void encode(EncoderContext encoderContext) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            if (!encoderContext.hasMoreCharacters()) {
                break;
            }
            encodeChar(encoderContext.getCurrentChar(), sb);
            encoderContext.pos++;
            if (sb.length() >= 4) {
                encoderContext.writeCodewords(encodeToCodewords(sb));
                sb.delete(0, 4);
                if (HighLevelEncoder.lookAheadTest(encoderContext.getMessage(), encoderContext.pos, getEncodingMode()) != getEncodingMode()) {
                    encoderContext.signalEncoderChange(0);
                    break;
                }
            }
        }
        sb.append(31);
        handleEOD(encoderContext, sb);
    }

    public int getEncodingMode() {
        return 4;
    }
}
