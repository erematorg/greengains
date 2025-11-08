package com.google.zxing.datamatrix.encoder;

class C40Encoder implements Encoder {
    private int backtrackOneCharacter(EncoderContext encoderContext, StringBuilder sb, StringBuilder sb2, int i3) {
        int length = sb.length();
        sb.delete(length - i3, length);
        encoderContext.pos--;
        int encodeChar = encodeChar(encoderContext.getCurrentChar(), sb2);
        encoderContext.resetSymbolInfo();
        return encodeChar;
    }

    private static String encodeToCodewords(CharSequence charSequence) {
        int charAt = charSequence.charAt(1) * '(';
        int charAt2 = charSequence.charAt(2) + charAt + (charSequence.charAt(0) * 1600) + 1;
        return new String(new char[]{(char) (charAt2 / 256), (char) (charAt2 % 256)});
    }

    public static void writeNextTriplet(EncoderContext encoderContext, StringBuilder sb) {
        encoderContext.writeCodewords(encodeToCodewords(sb));
        sb.delete(0, 3);
    }

    public void encode(EncoderContext encoderContext) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            if (!encoderContext.hasMoreCharacters()) {
                break;
            }
            char currentChar = encoderContext.getCurrentChar();
            encoderContext.pos++;
            int encodeChar = encodeChar(currentChar, sb);
            int codewordCount = encoderContext.getCodewordCount() + ((sb.length() / 3) * 2);
            encoderContext.updateSymbolInfo(codewordCount);
            int dataCapacity = encoderContext.getSymbolInfo().getDataCapacity() - codewordCount;
            if (encoderContext.hasMoreCharacters()) {
                if (sb.length() % 3 == 0 && HighLevelEncoder.lookAheadTest(encoderContext.getMessage(), encoderContext.pos, getEncodingMode()) != getEncodingMode()) {
                    encoderContext.signalEncoderChange(0);
                    break;
                }
            } else {
                StringBuilder sb2 = new StringBuilder();
                if (sb.length() % 3 == 2 && dataCapacity != 2) {
                    encodeChar = backtrackOneCharacter(encoderContext, sb, sb2, encodeChar);
                }
                while (sb.length() % 3 == 1 && (encodeChar > 3 || dataCapacity != 1)) {
                    encodeChar = backtrackOneCharacter(encoderContext, sb, sb2, encodeChar);
                }
            }
        }
        handleEOD(encoderContext, sb);
    }

    public int encodeChar(char c3, StringBuilder sb) {
        if (c3 == ' ') {
            sb.append(3);
            return 1;
        } else if (c3 >= '0' && c3 <= '9') {
            sb.append((char) (c3 - ','));
            return 1;
        } else if (c3 >= 'A' && c3 <= 'Z') {
            sb.append((char) (c3 - '3'));
            return 1;
        } else if (c3 < ' ') {
            sb.append(0);
            sb.append(c3);
            return 2;
        } else if (c3 <= '/') {
            sb.append(1);
            sb.append((char) (c3 - '!'));
            return 2;
        } else if (c3 <= '@') {
            sb.append(1);
            sb.append((char) (c3 - '+'));
            return 2;
        } else if (c3 <= '_') {
            sb.append(1);
            sb.append((char) (c3 - 'E'));
            return 2;
        } else if (c3 <= 127) {
            sb.append(2);
            sb.append((char) (c3 - '`'));
            return 2;
        } else {
            sb.append("\u0001\u001e");
            return encodeChar((char) (c3 - 128), sb) + 2;
        }
    }

    public void encodeMaximal(EncoderContext encoderContext) {
        StringBuilder sb = new StringBuilder();
        int i3 = encoderContext.pos;
        int i4 = 0;
        int i5 = 0;
        while (encoderContext.hasMoreCharacters()) {
            char currentChar = encoderContext.getCurrentChar();
            encoderContext.pos++;
            i5 = encodeChar(currentChar, sb);
            if (sb.length() % 3 == 0) {
                i3 = encoderContext.pos;
                i4 = sb.length();
            }
        }
        if (i4 != sb.length()) {
            int codewordCount = encoderContext.getCodewordCount() + ((sb.length() / 3) * 2) + 1;
            encoderContext.updateSymbolInfo(codewordCount);
            int dataCapacity = encoderContext.getSymbolInfo().getDataCapacity() - codewordCount;
            int length = sb.length() % 3;
            if ((length == 2 && dataCapacity != 2) || (length == 1 && (i5 > 3 || dataCapacity != 1))) {
                sb.setLength(i4);
                encoderContext.pos = i3;
            }
        }
        if (sb.length() > 0) {
            encoderContext.writeCodeword(230);
        }
        handleEOD(encoderContext, sb);
    }

    public int getEncodingMode() {
        return 1;
    }

    public void handleEOD(EncoderContext encoderContext, StringBuilder sb) {
        int length = sb.length() % 3;
        int codewordCount = encoderContext.getCodewordCount() + ((sb.length() / 3) * 2);
        encoderContext.updateSymbolInfo(codewordCount);
        int dataCapacity = encoderContext.getSymbolInfo().getDataCapacity() - codewordCount;
        if (length == 2) {
            sb.append(0);
            while (sb.length() >= 3) {
                writeNextTriplet(encoderContext, sb);
            }
            if (encoderContext.hasMoreCharacters()) {
                encoderContext.writeCodeword(254);
            }
        } else if (dataCapacity == 1 && length == 1) {
            while (sb.length() >= 3) {
                writeNextTriplet(encoderContext, sb);
            }
            if (encoderContext.hasMoreCharacters()) {
                encoderContext.writeCodeword(254);
            }
            encoderContext.pos--;
        } else if (length == 0) {
            while (sb.length() >= 3) {
                writeNextTriplet(encoderContext, sb);
            }
            if (dataCapacity > 0 || encoderContext.hasMoreCharacters()) {
                encoderContext.writeCodeword(254);
            }
        } else {
            throw new IllegalStateException("Unexpected case. Please report!");
        }
        encoderContext.signalEncoderChange(0);
    }
}
