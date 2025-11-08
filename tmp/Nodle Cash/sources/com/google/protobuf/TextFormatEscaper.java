package com.google.protobuf;

import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

final class TextFormatEscaper {

    public interface ByteSequence {
        byte byteAt(int i3);

        int size();
    }

    private TextFormatEscaper() {
    }

    public static String escapeBytes(ByteSequence byteSequence) {
        StringBuilder sb = new StringBuilder(byteSequence.size());
        for (int i3 = 0; i3 < byteSequence.size(); i3++) {
            byte byteAt = byteSequence.byteAt(i3);
            if (byteAt == 34) {
                sb.append("\\\"");
            } else if (byteAt == 39) {
                sb.append("\\'");
            } else if (byteAt != 92) {
                switch (byteAt) {
                    case 7:
                        sb.append("\\a");
                        break;
                    case 8:
                        sb.append("\\b");
                        break;
                    case 9:
                        sb.append("\\t");
                        break;
                    case 10:
                        sb.append("\\n");
                        break;
                    case 11:
                        sb.append("\\v");
                        break;
                    case 12:
                        sb.append("\\f");
                        break;
                    case 13:
                        sb.append("\\r");
                        break;
                    default:
                        if (byteAt >= 32 && byteAt <= 126) {
                            sb.append((char) byteAt);
                            break;
                        } else {
                            sb.append(AbstractJsonLexerKt.STRING_ESC);
                            sb.append((char) (((byteAt >>> 6) & 3) + 48));
                            sb.append((char) (((byteAt >>> 3) & 7) + 48));
                            sb.append((char) ((byteAt & 7) + 48));
                            break;
                        }
                        break;
                }
            } else {
                sb.append("\\\\");
            }
        }
        return sb.toString();
    }

    public static String escapeDoubleQuotesAndBackslashes(String str) {
        return str.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    public static String escapeText(String str) {
        return escapeBytes(ByteString.copyFromUtf8(str));
    }

    public static String escapeBytes(final ByteString byteString) {
        return escapeBytes((ByteSequence) new ByteSequence() {
            public byte byteAt(int i3) {
                return ByteString.this.byteAt(i3);
            }

            public int size() {
                return ByteString.this.size();
            }
        });
    }

    public static String escapeBytes(final byte[] bArr) {
        return escapeBytes((ByteSequence) new ByteSequence() {
            public byte byteAt(int i3) {
                return bArr[i3];
            }

            public int size() {
                return bArr.length;
            }
        });
    }
}
