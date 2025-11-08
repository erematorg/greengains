package com.google.android.recaptcha.internal;

final class zzma {
    private static final zzlx zza = new zzly();

    static {
        if (zzlv.zzx() && zzlv.zzy()) {
            int i3 = zzgi.zza;
        }
    }

    public static /* bridge */ /* synthetic */ int zza(byte[] bArr, int i3, int i4) {
        int i5 = i4 - i3;
        byte b3 = bArr[i3 - 1];
        if (i5 != 0) {
            if (i5 == 1) {
                byte b4 = bArr[i3];
                if (b3 > -12 || b4 > -65) {
                    return -1;
                }
                return (b4 << 8) ^ b3;
            } else if (i5 == 2) {
                byte b5 = bArr[i3];
                byte b6 = bArr[i3 + 1];
                if (b3 > -12 || b5 > -65 || b6 > -65) {
                    return -1;
                }
                return (b6 << 16) ^ ((b5 << 8) ^ b3);
            } else {
                throw new AssertionError();
            }
        } else if (b3 <= -12) {
            return b3;
        } else {
            return -1;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0020  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x001d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int zzb(java.lang.CharSequence r8, byte[] r9, int r10, int r11) {
        /*
            int r0 = r8.length()
            r1 = 0
        L_0x0005:
            int r2 = r10 + r11
            r3 = 128(0x80, float:1.794E-43)
            if (r1 >= r0) goto L_0x001b
            int r4 = r1 + r10
            if (r4 >= r2) goto L_0x001b
            char r5 = r8.charAt(r1)
            if (r5 >= r3) goto L_0x001b
            byte r2 = (byte) r5
            r9[r4] = r2
            int r1 = r1 + 1
            goto L_0x0005
        L_0x001b:
            if (r1 != r0) goto L_0x0020
            int r10 = r10 + r0
            goto L_0x00fe
        L_0x0020:
            int r10 = r10 + r1
        L_0x0021:
            if (r1 >= r0) goto L_0x00fe
            char r11 = r8.charAt(r1)
            if (r11 >= r3) goto L_0x0033
            if (r10 >= r2) goto L_0x0033
            int r4 = r10 + 1
            byte r11 = (byte) r11
            r9[r10] = r11
            r10 = r4
            goto L_0x00b9
        L_0x0033:
            r4 = 2048(0x800, float:2.87E-42)
            if (r11 >= r4) goto L_0x004e
            int r4 = r2 + -2
            if (r10 > r4) goto L_0x004e
            int r4 = r10 + 1
            int r5 = r10 + 2
            int r6 = r11 >>> 6
            r6 = r6 | 960(0x3c0, float:1.345E-42)
            byte r6 = (byte) r6
            r9[r10] = r6
            r10 = r11 & 63
            r10 = r10 | r3
            byte r10 = (byte) r10
            r9[r4] = r10
            r10 = r5
            goto L_0x00b9
        L_0x004e:
            r4 = 57343(0xdfff, float:8.0355E-41)
            r5 = 55296(0xd800, float:7.7486E-41)
            if (r11 < r5) goto L_0x0058
            if (r11 <= r4) goto L_0x0079
        L_0x0058:
            int r6 = r2 + -3
            if (r10 > r6) goto L_0x0079
            int r4 = r10 + 1
            int r5 = r10 + 2
            int r6 = r10 + 3
            int r7 = r11 >>> 12
            r7 = r7 | 480(0x1e0, float:6.73E-43)
            byte r7 = (byte) r7
            r9[r10] = r7
            int r10 = r11 >>> 6
            r10 = r10 & 63
            r10 = r10 | r3
            byte r10 = (byte) r10
            r9[r4] = r10
            r10 = r11 & 63
            r10 = r10 | r3
            byte r10 = (byte) r10
            r9[r5] = r10
            r10 = r6
            goto L_0x00b9
        L_0x0079:
            int r6 = r2 + -4
            if (r10 > r6) goto L_0x00c6
            int r4 = r1 + 1
            int r5 = r8.length()
            if (r4 == r5) goto L_0x00be
            char r1 = r8.charAt(r4)
            boolean r5 = java.lang.Character.isSurrogatePair(r11, r1)
            if (r5 == 0) goto L_0x00bd
            int r5 = r10 + 1
            int r6 = r10 + 2
            int r7 = r10 + 3
            int r11 = java.lang.Character.toCodePoint(r11, r1)
            int r1 = r11 >>> 18
            r1 = r1 | 240(0xf0, float:3.36E-43)
            byte r1 = (byte) r1
            r9[r10] = r1
            int r1 = r11 >>> 12
            r1 = r1 & 63
            r1 = r1 | r3
            byte r1 = (byte) r1
            r9[r5] = r1
            int r1 = r11 >>> 6
            r1 = r1 & 63
            r1 = r1 | r3
            byte r1 = (byte) r1
            r9[r6] = r1
            int r10 = r10 + 4
            r11 = r11 & 63
            r11 = r11 | r3
            byte r11 = (byte) r11
            r9[r7] = r11
            r1 = r4
        L_0x00b9:
            int r1 = r1 + 1
            goto L_0x0021
        L_0x00bd:
            r1 = r4
        L_0x00be:
            com.google.android.recaptcha.internal.zzlz r8 = new com.google.android.recaptcha.internal.zzlz
            int r1 = r1 + -1
            r8.<init>(r1, r0)
            throw r8
        L_0x00c6:
            if (r11 < r5) goto L_0x00e2
            if (r11 > r4) goto L_0x00e2
            int r9 = r1 + 1
            int r2 = r8.length()
            if (r9 == r2) goto L_0x00dc
            char r8 = r8.charAt(r9)
            boolean r8 = java.lang.Character.isSurrogatePair(r11, r8)
            if (r8 != 0) goto L_0x00e2
        L_0x00dc:
            com.google.android.recaptcha.internal.zzlz r8 = new com.google.android.recaptcha.internal.zzlz
            r8.<init>(r1, r0)
            throw r8
        L_0x00e2:
            java.lang.ArrayIndexOutOfBoundsException r8 = new java.lang.ArrayIndexOutOfBoundsException
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r0 = "Failed writing "
            r9.<init>(r0)
            r9.append(r11)
            java.lang.String r11 = " at index "
            r9.append(r11)
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r8.<init>(r9)
            throw r8
        L_0x00fe:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzma.zzb(java.lang.CharSequence, byte[], int, int):int");
    }

    public static int zzc(CharSequence charSequence) {
        int length = charSequence.length();
        int i3 = 0;
        int i4 = 0;
        while (i4 < length && charSequence.charAt(i4) < 128) {
            i4++;
        }
        int i5 = length;
        while (true) {
            if (i4 >= length) {
                break;
            }
            char charAt = charSequence.charAt(i4);
            if (charAt < 2048) {
                i5 += (127 - charAt) >>> 31;
                i4++;
            } else {
                int length2 = charSequence.length();
                while (i4 < length2) {
                    char charAt2 = charSequence.charAt(i4);
                    if (charAt2 < 2048) {
                        i3 += (127 - charAt2) >>> 31;
                    } else {
                        i3 += 2;
                        if (charAt2 >= 55296 && charAt2 <= 57343) {
                            if (Character.codePointAt(charSequence, i4) >= 65536) {
                                i4++;
                            } else {
                                throw new zzlz(i4, length2);
                            }
                        }
                    }
                    i4++;
                }
                i5 += i3;
            }
        }
        if (i5 >= length) {
            return i5;
        }
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (((long) i5) + 4294967296L));
    }

    public static String zzd(byte[] bArr, int i3, int i4) throws zzje {
        int i5;
        int length = bArr.length;
        if ((((length - i3) - i4) | i3 | i4) >= 0) {
            int i6 = i3 + i4;
            char[] cArr = new char[i4];
            int i7 = 0;
            while (r10 < i6) {
                byte b3 = bArr[r10];
                if (!zzlw.zzd(b3)) {
                    break;
                }
                i3 = r10 + 1;
                cArr[i7] = (char) b3;
                i7++;
            }
            int i8 = i7;
            while (r10 < i6) {
                int i9 = r10 + 1;
                byte b4 = bArr[r10];
                if (zzlw.zzd(b4)) {
                    cArr[i8] = (char) b4;
                    i8++;
                    r10 = i9;
                    while (r10 < i6) {
                        byte b5 = bArr[r10];
                        if (!zzlw.zzd(b5)) {
                            break;
                        }
                        r10++;
                        cArr[i8] = (char) b5;
                        i8++;
                    }
                } else {
                    if (b4 < -32) {
                        if (i9 < i6) {
                            i5 = i8 + 1;
                            r10 = r10 + 2;
                            zzlw.zzc(b4, bArr[i9], cArr, i8);
                        } else {
                            throw zzje.zzd();
                        }
                    } else if (b4 < -16) {
                        if (i9 < i6 - 1) {
                            i5 = i8 + 1;
                            int i10 = r10 + 2;
                            r10 = r10 + 3;
                            zzlw.zzb(b4, bArr[i9], bArr[i10], cArr, i8);
                        } else {
                            throw zzje.zzd();
                        }
                    } else if (i9 < i6 - 2) {
                        byte b6 = bArr[i9];
                        int i11 = r10 + 3;
                        byte b7 = bArr[r10 + 2];
                        r10 += 4;
                        zzlw.zza(b4, b6, b7, bArr[i11], cArr, i8);
                        i8 += 2;
                    } else {
                        throw zzje.zzd();
                    }
                    i8 = i5;
                }
            }
            return new String(cArr, 0, i8);
        }
        throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", new Object[]{Integer.valueOf(length), Integer.valueOf(i3), Integer.valueOf(i4)}));
    }

    public static boolean zze(byte[] bArr) {
        return zza.zzb(bArr, 0, bArr.length);
    }

    public static boolean zzf(byte[] bArr, int i3, int i4) {
        return zza.zzb(bArr, i3, i4);
    }
}
