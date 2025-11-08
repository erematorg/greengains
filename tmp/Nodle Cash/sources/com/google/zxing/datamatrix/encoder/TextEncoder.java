package com.google.zxing.datamatrix.encoder;

final class TextEncoder extends C40Encoder {
    public int encodeChar(char c3, StringBuilder sb) {
        if (c3 == ' ') {
            sb.append(3);
            return 1;
        } else if (c3 >= '0' && c3 <= '9') {
            sb.append((char) (c3 - ','));
            return 1;
        } else if (c3 >= 'a' && c3 <= 'z') {
            sb.append((char) (c3 - 'S'));
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
        } else if (c3 >= '[' && c3 <= '_') {
            sb.append(1);
            sb.append((char) (c3 - 'E'));
            return 2;
        } else if (c3 == '`') {
            sb.append(2);
            sb.append(0);
            return 2;
        } else if (c3 <= 'Z') {
            sb.append(2);
            sb.append((char) (c3 - '@'));
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

    public int getEncodingMode() {
        return 2;
    }
}
