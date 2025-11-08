package com.neovisionaries.ws.client;

import javax.security.auth.x500.X500Principal;

final class DistinguishedNameParser {
    private int beg;
    private char[] chars;
    private int cur;
    private final String dn;
    private int end;
    private final int length;
    private int pos;

    public DistinguishedNameParser(X500Principal x500Principal) {
        String name = x500Principal.getName("RFC2253");
        this.dn = name;
        this.length = name.length();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x009b, code lost:
        return new java.lang.String(r1, r2, r8.cur - r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String escapedAV() {
        /*
            r8 = this;
            int r0 = r8.pos
            r8.beg = r0
            r8.end = r0
        L_0x0006:
            int r0 = r8.pos
            int r1 = r8.length
            if (r0 < r1) goto L_0x0019
            java.lang.String r0 = new java.lang.String
            char[] r1 = r8.chars
            int r2 = r8.beg
            int r8 = r8.end
            int r8 = r8 - r2
            r0.<init>(r1, r2, r8)
            return r0
        L_0x0019:
            char[] r1 = r8.chars
            char r2 = r1[r0]
            r3 = 44
            r4 = 43
            r5 = 59
            r6 = 32
            if (r2 == r6) goto L_0x005c
            if (r2 == r5) goto L_0x0051
            r5 = 92
            if (r2 == r5) goto L_0x003e
            if (r2 == r4) goto L_0x0051
            if (r2 == r3) goto L_0x0051
            int r3 = r8.end
            int r4 = r3 + 1
            r8.end = r4
            r1[r3] = r2
            int r0 = r0 + 1
            r8.pos = r0
            goto L_0x0006
        L_0x003e:
            int r0 = r8.end
            int r2 = r0 + 1
            r8.end = r2
            char r2 = r8.getEscaped()
            r1[r0] = r2
            int r0 = r8.pos
            int r0 = r0 + 1
            r8.pos = r0
            goto L_0x0006
        L_0x0051:
            java.lang.String r0 = new java.lang.String
            int r2 = r8.beg
            int r8 = r8.end
            int r8 = r8 - r2
            r0.<init>(r1, r2, r8)
            return r0
        L_0x005c:
            int r2 = r8.end
            r8.cur = r2
            int r0 = r0 + 1
            r8.pos = r0
            int r0 = r2 + 1
            r8.end = r0
            r1[r2] = r6
        L_0x006a:
            int r0 = r8.pos
            int r1 = r8.length
            if (r0 >= r1) goto L_0x0083
            char[] r2 = r8.chars
            char r7 = r2[r0]
            if (r7 != r6) goto L_0x0083
            int r1 = r8.end
            int r7 = r1 + 1
            r8.end = r7
            r2[r1] = r6
            int r0 = r0 + 1
            r8.pos = r0
            goto L_0x006a
        L_0x0083:
            if (r0 == r1) goto L_0x008f
            char[] r1 = r8.chars
            char r0 = r1[r0]
            if (r0 == r3) goto L_0x008f
            if (r0 == r4) goto L_0x008f
            if (r0 != r5) goto L_0x0006
        L_0x008f:
            java.lang.String r0 = new java.lang.String
            char[] r1 = r8.chars
            int r2 = r8.beg
            int r8 = r8.cur
            int r8 = r8 - r2
            r0.<init>(r1, r2, r8)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neovisionaries.ws.client.DistinguishedNameParser.escapedAV():java.lang.String");
    }

    private int getByte(int i3) {
        int i4;
        int i5;
        int i6 = i3 + 1;
        if (i6 < this.length) {
            char[] cArr = this.chars;
            char c3 = cArr[i3];
            if (c3 >= '0' && c3 <= '9') {
                i4 = c3 - '0';
            } else if (c3 >= 'a' && c3 <= 'f') {
                i4 = c3 - 'W';
            } else if (c3 < 'A' || c3 > 'F') {
                throw new IllegalStateException("Malformed DN: " + this.dn);
            } else {
                i4 = c3 - '7';
            }
            char c4 = cArr[i6];
            if (c4 >= '0' && c4 <= '9') {
                i5 = c4 - '0';
            } else if (c4 >= 'a' && c4 <= 'f') {
                i5 = c4 - 'W';
            } else if (c4 < 'A' || c4 > 'F') {
                throw new IllegalStateException("Malformed DN: " + this.dn);
            } else {
                i5 = c4 - '7';
            }
            return (i4 << 4) + i5;
        }
        throw new IllegalStateException("Malformed DN: " + this.dn);
    }

    private char getEscaped() {
        int i3 = this.pos + 1;
        this.pos = i3;
        if (i3 != this.length) {
            char c3 = this.chars[i3];
            if (!(c3 == ' ' || c3 == '%' || c3 == '\\' || c3 == '_' || c3 == '\"' || c3 == '#')) {
                switch (c3) {
                    case '*':
                    case '+':
                    case ',':
                        break;
                    default:
                        switch (c3) {
                            case ';':
                            case '<':
                            case '=':
                            case '>':
                                break;
                            default:
                                return getUTF8();
                        }
                }
            }
            return c3;
        }
        throw new IllegalStateException("Unexpected end of DN: " + this.dn);
    }

    private char getUTF8() {
        int i3;
        int i4;
        int i5 = getByte(this.pos);
        this.pos++;
        if (i5 < 128) {
            return (char) i5;
        }
        if (i5 < 192 || i5 > 247) {
            return '?';
        }
        if (i5 <= 223) {
            i4 = i5 & 31;
            i3 = 1;
        } else if (i5 <= 239) {
            i4 = i5 & 15;
            i3 = 2;
        } else {
            i4 = i5 & 7;
            i3 = 3;
        }
        for (int i6 = 0; i6 < i3; i6++) {
            int i7 = this.pos;
            int i8 = i7 + 1;
            this.pos = i8;
            if (i8 == this.length || this.chars[i8] != '\\') {
                return '?';
            }
            int i9 = i7 + 2;
            this.pos = i9;
            int i10 = getByte(i9);
            this.pos++;
            if ((i10 & 192) != 128) {
                return '?';
            }
            i4 = (i4 << 6) + (i10 & 63);
        }
        return (char) i4;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0016, code lost:
        r1 = r6.chars;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String hexAV() {
        /*
            r6 = this;
            int r0 = r6.pos
            int r1 = r0 + 4
            int r2 = r6.length
            java.lang.String r3 = "Unexpected end of DN: "
            if (r1 >= r2) goto L_0x0095
            r6.beg = r0
            int r0 = r0 + 1
            r6.pos = r0
        L_0x0010:
            int r0 = r6.pos
            int r1 = r6.length
            if (r0 == r1) goto L_0x0054
            char[] r1 = r6.chars
            char r2 = r1[r0]
            r4 = 43
            if (r2 == r4) goto L_0x0054
            r4 = 44
            if (r2 == r4) goto L_0x0054
            r4 = 59
            if (r2 != r4) goto L_0x0027
            goto L_0x0054
        L_0x0027:
            r4 = 32
            if (r2 != r4) goto L_0x0042
            r6.end = r0
            int r0 = r0 + 1
            r6.pos = r0
        L_0x0031:
            int r0 = r6.pos
            int r1 = r6.length
            if (r0 >= r1) goto L_0x0056
            char[] r1 = r6.chars
            char r1 = r1[r0]
            if (r1 != r4) goto L_0x0056
            int r0 = r0 + 1
            r6.pos = r0
            goto L_0x0031
        L_0x0042:
            r4 = 65
            if (r2 < r4) goto L_0x004f
            r4 = 70
            if (r2 > r4) goto L_0x004f
            int r2 = r2 + 32
            char r2 = (char) r2
            r1[r0] = r2
        L_0x004f:
            int r0 = r0 + 1
            r6.pos = r0
            goto L_0x0010
        L_0x0054:
            r6.end = r0
        L_0x0056:
            int r0 = r6.end
            int r1 = r6.beg
            int r0 = r0 - r1
            r2 = 5
            if (r0 < r2) goto L_0x0081
            r2 = r0 & 1
            if (r2 == 0) goto L_0x0081
            int r2 = r0 / 2
            byte[] r3 = new byte[r2]
            int r1 = r1 + 1
            r4 = 0
        L_0x0069:
            if (r4 >= r2) goto L_0x0077
            int r5 = r6.getByte(r1)
            byte r5 = (byte) r5
            r3[r4] = r5
            int r1 = r1 + 2
            int r4 = r4 + 1
            goto L_0x0069
        L_0x0077:
            java.lang.String r1 = new java.lang.String
            char[] r2 = r6.chars
            int r6 = r6.beg
            r1.<init>(r2, r6, r0)
            return r1
        L_0x0081:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r3)
            java.lang.String r6 = r6.dn
            r1.append(r6)
            java.lang.String r6 = r1.toString()
            r0.<init>(r6)
            throw r0
        L_0x0095:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r3)
            java.lang.String r6 = r6.dn
            r1.append(r6)
            java.lang.String r6 = r1.toString()
            r0.<init>(r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neovisionaries.ws.client.DistinguishedNameParser.hexAV():java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:6:0x0015 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0017  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String nextAT() {
        /*
            r6 = this;
        L_0x0000:
            int r0 = r6.pos
            int r1 = r6.length
            r2 = 32
            if (r0 >= r1) goto L_0x0013
            char[] r3 = r6.chars
            char r3 = r3[r0]
            if (r3 != r2) goto L_0x0013
            int r0 = r0 + 1
            r6.pos = r0
            goto L_0x0000
        L_0x0013:
            if (r0 != r1) goto L_0x0017
            r6 = 0
            return r6
        L_0x0017:
            r6.beg = r0
            int r0 = r0 + 1
            r6.pos = r0
        L_0x001d:
            int r0 = r6.pos
            int r1 = r6.length
            r3 = 61
            if (r0 >= r1) goto L_0x0032
            char[] r4 = r6.chars
            char r4 = r4[r0]
            if (r4 == r3) goto L_0x0032
            if (r4 == r2) goto L_0x0032
            int r0 = r0 + 1
            r6.pos = r0
            goto L_0x001d
        L_0x0032:
            java.lang.String r4 = "Unexpected end of DN: "
            if (r0 >= r1) goto L_0x00d0
            r6.end = r0
            char[] r1 = r6.chars
            char r0 = r1[r0]
            if (r0 != r2) goto L_0x006e
        L_0x003e:
            int r0 = r6.pos
            int r1 = r6.length
            if (r0 >= r1) goto L_0x0051
            char[] r5 = r6.chars
            char r5 = r5[r0]
            if (r5 == r3) goto L_0x0051
            if (r5 != r2) goto L_0x0051
            int r0 = r0 + 1
            r6.pos = r0
            goto L_0x003e
        L_0x0051:
            char[] r5 = r6.chars
            char r5 = r5[r0]
            if (r5 != r3) goto L_0x005a
            if (r0 == r1) goto L_0x005a
            goto L_0x006e
        L_0x005a:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r4)
            java.lang.String r6 = r6.dn
            r1.append(r6)
            java.lang.String r6 = r1.toString()
            r0.<init>(r6)
            throw r0
        L_0x006e:
            int r0 = r6.pos
            int r0 = r0 + 1
            r6.pos = r0
        L_0x0074:
            int r0 = r6.pos
            int r1 = r6.length
            if (r0 >= r1) goto L_0x0085
            char[] r1 = r6.chars
            char r1 = r1[r0]
            if (r1 != r2) goto L_0x0085
            int r0 = r0 + 1
            r6.pos = r0
            goto L_0x0074
        L_0x0085:
            int r0 = r6.end
            int r1 = r6.beg
            int r2 = r0 - r1
            r3 = 4
            if (r2 <= r3) goto L_0x00c5
            char[] r2 = r6.chars
            int r4 = r1 + 3
            char r4 = r2[r4]
            r5 = 46
            if (r4 != r5) goto L_0x00c5
            char r4 = r2[r1]
            r5 = 79
            if (r4 == r5) goto L_0x00a2
            r5 = 111(0x6f, float:1.56E-43)
            if (r4 != r5) goto L_0x00c5
        L_0x00a2:
            int r4 = r1 + 1
            char r4 = r2[r4]
            r5 = 73
            if (r4 == r5) goto L_0x00b2
            int r4 = r1 + 1
            char r4 = r2[r4]
            r5 = 105(0x69, float:1.47E-43)
            if (r4 != r5) goto L_0x00c5
        L_0x00b2:
            int r4 = r1 + 2
            char r4 = r2[r4]
            r5 = 68
            if (r4 == r5) goto L_0x00c2
            int r4 = r1 + 2
            char r2 = r2[r4]
            r4 = 100
            if (r2 != r4) goto L_0x00c5
        L_0x00c2:
            int r1 = r1 + r3
            r6.beg = r1
        L_0x00c5:
            java.lang.String r1 = new java.lang.String
            char[] r2 = r6.chars
            int r6 = r6.beg
            int r0 = r0 - r6
            r1.<init>(r2, r6, r0)
            return r1
        L_0x00d0:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r4)
            java.lang.String r6 = r6.dn
            r1.append(r6)
            java.lang.String r6 = r1.toString()
            r0.<init>(r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neovisionaries.ws.client.DistinguishedNameParser.nextAT():java.lang.String");
    }

    private String quotedAV() {
        int i3 = this.pos + 1;
        this.pos = i3;
        this.beg = i3;
        this.end = i3;
        while (true) {
            int i4 = this.pos;
            if (i4 != this.length) {
                char[] cArr = this.chars;
                char c3 = cArr[i4];
                if (c3 == '\"') {
                    this.pos = i4 + 1;
                    while (true) {
                        int i5 = this.pos;
                        if (i5 >= this.length || this.chars[i5] != ' ') {
                            char[] cArr2 = this.chars;
                            int i6 = this.beg;
                        } else {
                            this.pos = i5 + 1;
                        }
                    }
                    char[] cArr22 = this.chars;
                    int i62 = this.beg;
                    return new String(cArr22, i62, this.end - i62);
                }
                if (c3 == '\\') {
                    cArr[this.end] = getEscaped();
                } else {
                    cArr[this.end] = c3;
                }
                this.pos++;
                this.end++;
            } else {
                throw new IllegalStateException("Unexpected end of DN: " + this.dn);
            }
        }
    }

    public String findMostSpecific(String str) {
        this.pos = 0;
        this.beg = 0;
        this.end = 0;
        this.cur = 0;
        this.chars = this.dn.toCharArray();
        String nextAT = nextAT();
        if (nextAT == null) {
            return null;
        }
        do {
            int i3 = this.pos;
            if (i3 == this.length) {
                return null;
            }
            char c3 = this.chars[i3];
            String escapedAV = c3 != '\"' ? c3 != '#' ? (c3 == '+' || c3 == ',' || c3 == ';') ? "" : escapedAV() : hexAV() : quotedAV();
            if (str.equalsIgnoreCase(nextAT)) {
                return escapedAV;
            }
            int i4 = this.pos;
            if (i4 >= this.length) {
                return null;
            }
            char c4 = this.chars[i4];
            if (c4 == ',' || c4 == ';' || c4 == '+') {
                this.pos = i4 + 1;
                nextAT = nextAT();
            } else {
                throw new IllegalStateException("Malformed DN: " + this.dn);
            }
        } while (nextAT != null);
        throw new IllegalStateException("Malformed DN: " + this.dn);
    }
}
