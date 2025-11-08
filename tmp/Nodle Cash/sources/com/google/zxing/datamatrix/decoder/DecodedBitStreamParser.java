package com.google.zxing.datamatrix.decoder;

import androidx.recyclerview.widget.ItemTouchHelper;
import com.fasterxml.jackson.core.JsonPointer;
import com.google.common.base.Ascii;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitSource;
import com.google.zxing.common.ECIStringBuilder;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Set;
import jnr.ffi.provider.jffi.JNINativeInterface;
import kotlin.text.Typography;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.ClassUtils;
import org.objectweb.asm.signature.SignatureVisitor;

final class DecodedBitStreamParser {
    private static final char[] C40_BASIC_SET_CHARS = {'*', '*', '*', ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private static final char[] C40_SHIFT2_SET_CHARS;
    private static final char[] TEXT_BASIC_SET_CHARS = {'*', '*', '*', ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', AbstractJsonLexerKt.UNICODE_ESC, 'v', 'w', 'x', 'y', 'z'};
    private static final char[] TEXT_SHIFT2_SET_CHARS;
    private static final char[] TEXT_SHIFT3_SET_CHARS = {'`', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', AbstractJsonLexerKt.BEGIN_OBJ, '|', AbstractJsonLexerKt.END_OBJ, '~', Ascii.MAX};

    /* renamed from: com.google.zxing.datamatrix.decoder.DecodedBitStreamParser$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$zxing$datamatrix$decoder$DecodedBitStreamParser$Mode;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.google.zxing.datamatrix.decoder.DecodedBitStreamParser$Mode[] r0 = com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.Mode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$zxing$datamatrix$decoder$DecodedBitStreamParser$Mode = r0
                com.google.zxing.datamatrix.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.Mode.C40_ENCODE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$zxing$datamatrix$decoder$DecodedBitStreamParser$Mode     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.zxing.datamatrix.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.Mode.TEXT_ENCODE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$zxing$datamatrix$decoder$DecodedBitStreamParser$Mode     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.zxing.datamatrix.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.Mode.ANSIX12_ENCODE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$zxing$datamatrix$decoder$DecodedBitStreamParser$Mode     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.zxing.datamatrix.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.Mode.EDIFACT_ENCODE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$google$zxing$datamatrix$decoder$DecodedBitStreamParser$Mode     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.zxing.datamatrix.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.Mode.BASE256_ENCODE     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$google$zxing$datamatrix$decoder$DecodedBitStreamParser$Mode     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.zxing.datamatrix.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.Mode.ECI_ENCODE     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.AnonymousClass1.<clinit>():void");
        }
    }

    public enum Mode {
        PAD_ENCODE,
        ASCII_ENCODE,
        C40_ENCODE,
        TEXT_ENCODE,
        ANSIX12_ENCODE,
        EDIFACT_ENCODE,
        BASE256_ENCODE,
        ECI_ENCODE
    }

    static {
        char[] cArr = {'!', '\"', '#', '$', '%', Typography.amp, '\'', '(', ')', '*', SignatureVisitor.EXTENDS, AbstractJsonLexerKt.COMMA, SignatureVisitor.SUPER, ClassUtils.PACKAGE_SEPARATOR_CHAR, JsonPointer.SEPARATOR, AbstractJsonLexerKt.COLON, ';', Typography.less, SignatureVisitor.INSTANCEOF, Typography.greater, '?', '@', AbstractJsonLexerKt.BEGIN_LIST, AbstractJsonLexerKt.STRING_ESC, AbstractJsonLexerKt.END_LIST, '^', '_'};
        C40_SHIFT2_SET_CHARS = cArr;
        TEXT_SHIFT2_SET_CHARS = cArr;
    }

    private DecodedBitStreamParser() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0099  */
    /* JADX WARNING: Removed duplicated region for block: B:3:0x002b  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:4:0x0030  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.zxing.common.DecoderResult decode(byte[] r13) throws com.google.zxing.FormatException {
        /*
            com.google.zxing.common.BitSource r0 = new com.google.zxing.common.BitSource
            r0.<init>(r13)
            com.google.zxing.common.ECIStringBuilder r1 = new com.google.zxing.common.ECIStringBuilder
            r2 = 100
            r1.<init>(r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r3 = 0
            java.lang.Integer r4 = java.lang.Integer.valueOf(r3)
            r2.<init>(r3)
            java.util.ArrayList r5 = new java.util.ArrayList
            r6 = 1
            java.lang.Integer r7 = java.lang.Integer.valueOf(r6)
            r5.<init>(r6)
            com.google.zxing.datamatrix.decoder.DecodedBitStreamParser$Mode r8 = com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.Mode.ASCII_ENCODE
            java.util.HashSet r9 = new java.util.HashSet
            r9.<init>()
        L_0x0027:
            com.google.zxing.datamatrix.decoder.DecodedBitStreamParser$Mode r10 = com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.Mode.ASCII_ENCODE
            if (r8 != r10) goto L_0x0030
            com.google.zxing.datamatrix.decoder.DecodedBitStreamParser$Mode r8 = decodeAsciiSegment(r0, r1, r2, r9)
            goto L_0x0059
        L_0x0030:
            int[] r11 = com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.AnonymousClass1.$SwitchMap$com$google$zxing$datamatrix$decoder$DecodedBitStreamParser$Mode
            int r8 = r8.ordinal()
            r8 = r11[r8]
            switch(r8) {
                case 1: goto L_0x0055;
                case 2: goto L_0x0051;
                case 3: goto L_0x004d;
                case 4: goto L_0x0049;
                case 5: goto L_0x0045;
                case 6: goto L_0x0040;
                default: goto L_0x003b;
            }
        L_0x003b:
            com.google.zxing.FormatException r13 = com.google.zxing.FormatException.getFormatInstance()
            throw r13
        L_0x0040:
            decodeECISegment(r0, r1)
            r3 = r6
            goto L_0x0058
        L_0x0045:
            decodeBase256Segment(r0, r1, r5)
            goto L_0x0058
        L_0x0049:
            decodeEdifactSegment(r0, r1)
            goto L_0x0058
        L_0x004d:
            decodeAnsiX12Segment(r0, r1)
            goto L_0x0058
        L_0x0051:
            decodeTextSegment(r0, r1, r9)
            goto L_0x0058
        L_0x0055:
            decodeC40Segment(r0, r1, r9)
        L_0x0058:
            r8 = r10
        L_0x0059:
            com.google.zxing.datamatrix.decoder.DecodedBitStreamParser$Mode r10 = com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.Mode.PAD_ENCODE
            if (r8 == r10) goto L_0x0063
            int r10 = r0.available()
            if (r10 > 0) goto L_0x0027
        L_0x0063:
            int r0 = r2.length()
            if (r0 <= 0) goto L_0x006c
            r1.appendCharacters(r2)
        L_0x006c:
            r0 = 5
            r2 = 4
            if (r3 == 0) goto L_0x0099
            boolean r3 = r9.contains(r4)
            if (r3 != 0) goto L_0x0097
            java.lang.Integer r3 = java.lang.Integer.valueOf(r2)
            boolean r3 = r9.contains(r3)
            if (r3 == 0) goto L_0x0081
            goto L_0x0097
        L_0x0081:
            boolean r3 = r9.contains(r7)
            if (r3 != 0) goto L_0x0094
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            boolean r0 = r9.contains(r0)
            if (r0 == 0) goto L_0x0092
            goto L_0x0094
        L_0x0092:
            r12 = r2
            goto L_0x00be
        L_0x0094:
            r6 = 6
        L_0x0095:
            r12 = r6
            goto L_0x00be
        L_0x0097:
            r12 = r0
            goto L_0x00be
        L_0x0099:
            boolean r3 = r9.contains(r4)
            if (r3 != 0) goto L_0x00bc
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            boolean r2 = r9.contains(r2)
            if (r2 == 0) goto L_0x00aa
            goto L_0x00bc
        L_0x00aa:
            boolean r2 = r9.contains(r7)
            if (r2 != 0) goto L_0x00ba
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            boolean r0 = r9.contains(r0)
            if (r0 == 0) goto L_0x0095
        L_0x00ba:
            r6 = 3
            goto L_0x0095
        L_0x00bc:
            r6 = 2
            goto L_0x0095
        L_0x00be:
            com.google.zxing.common.DecoderResult r0 = new com.google.zxing.common.DecoderResult
            java.lang.String r9 = r1.toString()
            boolean r1 = r5.isEmpty()
            if (r1 == 0) goto L_0x00cb
            r5 = 0
        L_0x00cb:
            r10 = r5
            r11 = 0
            r7 = r0
            r8 = r13
            r7.<init>(r8, r9, r10, r11, r12)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.decode(byte[]):com.google.zxing.common.DecoderResult");
    }

    private static void decodeAnsiX12Segment(BitSource bitSource, ECIStringBuilder eCIStringBuilder) throws FormatException {
        int readBits;
        int[] iArr = new int[3];
        while (bitSource.available() != 8 && (readBits = bitSource.readBits(8)) != 254) {
            parseTwoBytes(readBits, bitSource.readBits(8), iArr);
            for (int i3 = 0; i3 < 3; i3++) {
                int i4 = iArr[i3];
                if (i4 == 0) {
                    eCIStringBuilder.append((char) CharUtils.CR);
                } else if (i4 == 1) {
                    eCIStringBuilder.append('*');
                } else if (i4 == 2) {
                    eCIStringBuilder.append((char) Typography.greater);
                } else if (i4 == 3) {
                    eCIStringBuilder.append(' ');
                } else if (i4 < 14) {
                    eCIStringBuilder.append((char) (i4 + 44));
                } else if (i4 < 40) {
                    eCIStringBuilder.append((char) (i4 + 51));
                } else {
                    throw FormatException.getFormatInstance();
                }
            }
            if (bitSource.available() <= 0) {
                return;
            }
        }
    }

    private static Mode decodeAsciiSegment(BitSource bitSource, ECIStringBuilder eCIStringBuilder, StringBuilder sb, Set<Integer> set) throws FormatException {
        boolean z2 = false;
        do {
            int readBits = bitSource.readBits(8);
            if (readBits == 0) {
                throw FormatException.getFormatInstance();
            } else if (readBits <= 128) {
                if (z2) {
                    readBits += 128;
                }
                eCIStringBuilder.append((char) (readBits - 1));
                return Mode.ASCII_ENCODE;
            } else if (readBits == 129) {
                return Mode.PAD_ENCODE;
            } else {
                if (readBits <= 229) {
                    int i3 = readBits - 130;
                    if (i3 < 10) {
                        eCIStringBuilder.append('0');
                    }
                    eCIStringBuilder.append(i3);
                } else {
                    switch (readBits) {
                        case JNINativeInterface.GetDirectBufferAddress /*230*/:
                            return Mode.C40_ENCODE;
                        case JNINativeInterface.GetDirectBufferCapacity /*231*/:
                            return Mode.BASE256_ENCODE;
                        case JNINativeInterface.GetObjectRefType /*232*/:
                            set.add(Integer.valueOf(eCIStringBuilder.length()));
                            eCIStringBuilder.append(29);
                            break;
                        case 233:
                        case 234:
                            break;
                        case 235:
                            z2 = true;
                            break;
                        case 236:
                            eCIStringBuilder.append("[)>\u001e05\u001d");
                            sb.insert(0, "\u001e\u0004");
                            break;
                        case 237:
                            eCIStringBuilder.append("[)>\u001e06\u001d");
                            sb.insert(0, "\u001e\u0004");
                            break;
                        case 238:
                            return Mode.ANSIX12_ENCODE;
                        case 239:
                            return Mode.TEXT_ENCODE;
                        case 240:
                            return Mode.EDIFACT_ENCODE;
                        case 241:
                            return Mode.ECI_ENCODE;
                        default:
                            if (!(readBits == 254 && bitSource.available() == 0)) {
                                throw FormatException.getFormatInstance();
                            }
                    }
                }
            }
        } while (bitSource.available() > 0);
        return Mode.ASCII_ENCODE;
    }

    private static void decodeBase256Segment(BitSource bitSource, ECIStringBuilder eCIStringBuilder, Collection<byte[]> collection) throws FormatException {
        int byteOffset = bitSource.getByteOffset();
        int i3 = byteOffset + 2;
        int unrandomize255State = unrandomize255State(bitSource.readBits(8), byteOffset + 1);
        if (unrandomize255State == 0) {
            unrandomize255State = bitSource.available() / 8;
        } else if (unrandomize255State >= 250) {
            unrandomize255State = ((unrandomize255State - 249) * ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION) + unrandomize255State(bitSource.readBits(8), i3);
            i3 = byteOffset + 3;
        }
        if (unrandomize255State >= 0) {
            byte[] bArr = new byte[unrandomize255State];
            int i4 = 0;
            while (i4 < unrandomize255State) {
                if (bitSource.available() >= 8) {
                    bArr[i4] = (byte) unrandomize255State(bitSource.readBits(8), i3);
                    i4++;
                    i3++;
                } else {
                    throw FormatException.getFormatInstance();
                }
            }
            collection.add(bArr);
            eCIStringBuilder.append(new String(bArr, StandardCharsets.ISO_8859_1));
            return;
        }
        throw FormatException.getFormatInstance();
    }

    private static void decodeC40Segment(BitSource bitSource, ECIStringBuilder eCIStringBuilder, Set<Integer> set) throws FormatException {
        int readBits;
        int[] iArr = new int[3];
        boolean z2 = false;
        int i3 = 0;
        while (bitSource.available() != 8 && (readBits = bitSource.readBits(8)) != 254) {
            parseTwoBytes(readBits, bitSource.readBits(8), iArr);
            for (int i4 = 0; i4 < 3; i4++) {
                int i5 = iArr[i4];
                if (i3 != 0) {
                    if (i3 != 1) {
                        if (i3 == 2) {
                            char[] cArr = C40_SHIFT2_SET_CHARS;
                            if (i5 < cArr.length) {
                                char c3 = cArr[i5];
                                if (z2) {
                                    eCIStringBuilder.append((char) (c3 + 128));
                                } else {
                                    eCIStringBuilder.append(c3);
                                }
                            } else if (i5 == 27) {
                                set.add(Integer.valueOf(eCIStringBuilder.length()));
                                eCIStringBuilder.append(29);
                            } else if (i5 == 30) {
                                z2 = true;
                            } else {
                                throw FormatException.getFormatInstance();
                            }
                            i3 = 0;
                        } else if (i3 != 3) {
                            throw FormatException.getFormatInstance();
                        } else if (z2) {
                            eCIStringBuilder.append((char) (i5 + 224));
                        } else {
                            eCIStringBuilder.append((char) (i5 + 96));
                            i3 = 0;
                        }
                    } else if (z2) {
                        eCIStringBuilder.append((char) (i5 + 128));
                    } else {
                        eCIStringBuilder.append((char) i5);
                        i3 = 0;
                    }
                    z2 = false;
                    i3 = 0;
                } else if (i5 < 3) {
                    i3 = i5 + 1;
                } else {
                    char[] cArr2 = C40_BASIC_SET_CHARS;
                    if (i5 < cArr2.length) {
                        char c4 = cArr2[i5];
                        if (z2) {
                            eCIStringBuilder.append((char) (c4 + 128));
                            z2 = false;
                        } else {
                            eCIStringBuilder.append(c4);
                        }
                    } else {
                        throw FormatException.getFormatInstance();
                    }
                }
            }
            if (bitSource.available() <= 0) {
                return;
            }
        }
    }

    private static void decodeECISegment(BitSource bitSource, ECIStringBuilder eCIStringBuilder) throws FormatException {
        if (bitSource.available() >= 8) {
            int readBits = bitSource.readBits(8);
            if (readBits <= 127) {
                eCIStringBuilder.appendECI(readBits - 1);
                return;
            }
            return;
        }
        throw FormatException.getFormatInstance();
    }

    private static void decodeEdifactSegment(BitSource bitSource, ECIStringBuilder eCIStringBuilder) {
        while (bitSource.available() > 16) {
            for (int i3 = 0; i3 < 4; i3++) {
                int readBits = bitSource.readBits(6);
                if (readBits == 31) {
                    int bitOffset = 8 - bitSource.getBitOffset();
                    if (bitOffset != 8) {
                        bitSource.readBits(bitOffset);
                        return;
                    }
                    return;
                }
                if ((readBits & 32) == 0) {
                    readBits |= 64;
                }
                eCIStringBuilder.append((char) readBits);
            }
            if (bitSource.available() <= 0) {
                return;
            }
        }
    }

    private static void decodeTextSegment(BitSource bitSource, ECIStringBuilder eCIStringBuilder, Set<Integer> set) throws FormatException {
        int readBits;
        int[] iArr = new int[3];
        boolean z2 = false;
        int i3 = 0;
        while (bitSource.available() != 8 && (readBits = bitSource.readBits(8)) != 254) {
            parseTwoBytes(readBits, bitSource.readBits(8), iArr);
            for (int i4 = 0; i4 < 3; i4++) {
                int i5 = iArr[i4];
                if (i3 != 0) {
                    if (i3 != 1) {
                        if (i3 == 2) {
                            char[] cArr = TEXT_SHIFT2_SET_CHARS;
                            if (i5 < cArr.length) {
                                char c3 = cArr[i5];
                                if (z2) {
                                    eCIStringBuilder.append((char) (c3 + 128));
                                } else {
                                    eCIStringBuilder.append(c3);
                                }
                            } else if (i5 == 27) {
                                set.add(Integer.valueOf(eCIStringBuilder.length()));
                                eCIStringBuilder.append(29);
                            } else if (i5 == 30) {
                                z2 = true;
                            } else {
                                throw FormatException.getFormatInstance();
                            }
                            i3 = 0;
                        } else if (i3 == 3) {
                            char[] cArr2 = TEXT_SHIFT3_SET_CHARS;
                            if (i5 < cArr2.length) {
                                char c4 = cArr2[i5];
                                if (z2) {
                                    eCIStringBuilder.append((char) (c4 + 128));
                                } else {
                                    eCIStringBuilder.append(c4);
                                    i3 = 0;
                                }
                            } else {
                                throw FormatException.getFormatInstance();
                            }
                        } else {
                            throw FormatException.getFormatInstance();
                        }
                    } else if (z2) {
                        eCIStringBuilder.append((char) (i5 + 128));
                    } else {
                        eCIStringBuilder.append((char) i5);
                        i3 = 0;
                    }
                    z2 = false;
                    i3 = 0;
                } else if (i5 < 3) {
                    i3 = i5 + 1;
                } else {
                    char[] cArr3 = TEXT_BASIC_SET_CHARS;
                    if (i5 < cArr3.length) {
                        char c5 = cArr3[i5];
                        if (z2) {
                            eCIStringBuilder.append((char) (c5 + 128));
                            z2 = false;
                        } else {
                            eCIStringBuilder.append(c5);
                        }
                    } else {
                        throw FormatException.getFormatInstance();
                    }
                }
            }
            if (bitSource.available() <= 0) {
                return;
            }
        }
    }

    private static void parseTwoBytes(int i3, int i4, int[] iArr) {
        int i5 = ((i3 << 8) + i4) - 1;
        int i6 = i5 / 1600;
        iArr[0] = i6;
        int i7 = i5 - (i6 * 1600);
        int i8 = i7 / 40;
        iArr[1] = i8;
        iArr[2] = i7 - (i8 * 40);
    }

    private static int unrandomize255State(int i3, int i4) {
        int i5 = i3 - (((i4 * 149) % 255) + 1);
        return i5 >= 0 ? i5 : i5 + 256;
    }
}
