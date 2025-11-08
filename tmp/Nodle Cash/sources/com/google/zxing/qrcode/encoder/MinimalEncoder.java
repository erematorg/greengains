package com.google.zxing.qrcode.encoder;

import A.a;
import androidx.constraintlayout.core.state.b;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.ECIEncoderSet;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;
import com.google.zxing.qrcode.decoder.Version;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.ClassUtils;

final class MinimalEncoder {
    /* access modifiers changed from: private */
    public final ErrorCorrectionLevel ecLevel;
    /* access modifiers changed from: private */
    public final ECIEncoderSet encoders;
    /* access modifiers changed from: private */
    public final boolean isGS1;
    /* access modifiers changed from: private */
    public final String stringToEncode;

    /* renamed from: com.google.zxing.qrcode.encoder.MinimalEncoder$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$zxing$qrcode$decoder$Mode;
        static final /* synthetic */ int[] $SwitchMap$com$google$zxing$qrcode$encoder$MinimalEncoder$VersionSize;

        /* JADX WARNING: Can't wrap try/catch for region: R(17:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|13|14|15|17|18|19|20|(3:21|22|24)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|(2:1|2)|3|5|6|7|9|10|11|12|13|14|15|17|18|19|20|(3:21|22|24)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(21:0|1|2|3|5|6|7|9|10|11|12|13|14|15|17|18|19|20|21|22|24) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0033 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x004f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0059 */
        static {
            /*
                com.google.zxing.qrcode.decoder.Mode[] r0 = com.google.zxing.qrcode.decoder.Mode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$zxing$qrcode$decoder$Mode = r0
                r1 = 1
                com.google.zxing.qrcode.decoder.Mode r2 = com.google.zxing.qrcode.decoder.Mode.KANJI     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$google$zxing$qrcode$decoder$Mode     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.zxing.qrcode.decoder.Mode r3 = com.google.zxing.qrcode.decoder.Mode.ALPHANUMERIC     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$com$google$zxing$qrcode$decoder$Mode     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.zxing.qrcode.decoder.Mode r4 = com.google.zxing.qrcode.decoder.Mode.NUMERIC     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r3 = $SwitchMap$com$google$zxing$qrcode$decoder$Mode     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.zxing.qrcode.decoder.Mode r4 = com.google.zxing.qrcode.decoder.Mode.BYTE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r5 = 4
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r3 = $SwitchMap$com$google$zxing$qrcode$decoder$Mode     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.zxing.qrcode.decoder.Mode r4 = com.google.zxing.qrcode.decoder.Mode.ECI     // Catch:{ NoSuchFieldError -> 0x003e }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r5 = 5
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                com.google.zxing.qrcode.encoder.MinimalEncoder$VersionSize[] r3 = com.google.zxing.qrcode.encoder.MinimalEncoder.VersionSize.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$com$google$zxing$qrcode$encoder$MinimalEncoder$VersionSize = r3
                com.google.zxing.qrcode.encoder.MinimalEncoder$VersionSize r4 = com.google.zxing.qrcode.encoder.MinimalEncoder.VersionSize.SMALL     // Catch:{ NoSuchFieldError -> 0x004f }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x004f }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x004f }
            L_0x004f:
                int[] r1 = $SwitchMap$com$google$zxing$qrcode$encoder$MinimalEncoder$VersionSize     // Catch:{ NoSuchFieldError -> 0x0059 }
                com.google.zxing.qrcode.encoder.MinimalEncoder$VersionSize r3 = com.google.zxing.qrcode.encoder.MinimalEncoder.VersionSize.MEDIUM     // Catch:{ NoSuchFieldError -> 0x0059 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0059 }
                r1[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0059 }
            L_0x0059:
                int[] r0 = $SwitchMap$com$google$zxing$qrcode$encoder$MinimalEncoder$VersionSize     // Catch:{ NoSuchFieldError -> 0x0063 }
                com.google.zxing.qrcode.encoder.MinimalEncoder$VersionSize r1 = com.google.zxing.qrcode.encoder.MinimalEncoder.VersionSize.LARGE     // Catch:{ NoSuchFieldError -> 0x0063 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0063 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0063 }
            L_0x0063:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.encoder.MinimalEncoder.AnonymousClass1.<clinit>():void");
        }
    }

    public final class Edge {
        /* access modifiers changed from: private */
        public final int cachedTotalSize;
        /* access modifiers changed from: private */
        public final int characterLength;
        /* access modifiers changed from: private */
        public final int charsetEncoderIndex;
        /* access modifiers changed from: private */
        public final int fromPosition;
        /* access modifiers changed from: private */
        public final Mode mode;
        /* access modifiers changed from: private */
        public final Edge previous;

        public /* synthetic */ Edge(MinimalEncoder minimalEncoder, Mode mode2, int i3, int i4, int i5, Edge edge, Version version, AnonymousClass1 r8) {
            this(mode2, i3, i4, i5, edge, version);
        }

        private Edge(Mode mode2, int i3, int i4, int i5, Edge edge, Version version) {
            this.mode = mode2;
            this.fromPosition = i3;
            Mode mode3 = Mode.BYTE;
            int i6 = (mode2 == mode3 || edge == null) ? i4 : edge.charsetEncoderIndex;
            this.charsetEncoderIndex = i6;
            this.characterLength = i5;
            this.previous = edge;
            boolean z2 = false;
            int i7 = edge != null ? edge.cachedTotalSize : 0;
            if ((mode2 == mode3 && edge == null && i6 != 0) || !(edge == null || i6 == edge.charsetEncoderIndex)) {
                z2 = true;
            }
            int i8 = 4;
            i7 = (edge == null || mode2 != edge.mode || z2) ? i7 + mode2.getCharacterCountBits(version) + 4 : i7;
            int i9 = AnonymousClass1.$SwitchMap$com$google$zxing$qrcode$decoder$Mode[mode2.ordinal()];
            if (i9 == 1) {
                i7 += 13;
            } else if (i9 == 2) {
                i7 += i5 == 1 ? 6 : 11;
            } else if (i9 == 3) {
                i7 += i5 != 1 ? i5 == 2 ? 7 : 10 : i8;
            } else if (i9 == 4) {
                i7 += MinimalEncoder.this.encoders.encode(MinimalEncoder.this.stringToEncode.substring(i3, i5 + i3), i4).length * 8;
                if (z2) {
                    i7 += 12;
                }
            }
            this.cachedTotalSize = i7;
        }
    }

    public final class ResultList {
        private final List<ResultNode> list = new ArrayList();
        /* access modifiers changed from: private */
        public final Version version;

        public final class ResultNode {
            private final int characterLength;
            private final int charsetEncoderIndex;
            private final int fromPosition;
            /* access modifiers changed from: private */
            public final Mode mode;

            public ResultNode(Mode mode2, int i3, int i4, int i5) {
                this.mode = mode2;
                this.fromPosition = i3;
                this.charsetEncoderIndex = i4;
                this.characterLength = i5;
            }

            /* access modifiers changed from: private */
            public void getBits(BitArray bitArray) throws WriterException {
                bitArray.appendBits(this.mode.getBits(), 4);
                if (this.characterLength > 0) {
                    bitArray.appendBits(getCharacterCountIndicator(), this.mode.getCharacterCountBits(ResultList.this.version));
                }
                if (this.mode == Mode.ECI) {
                    bitArray.appendBits(MinimalEncoder.this.encoders.getECIValue(this.charsetEncoderIndex), 8);
                } else if (this.characterLength > 0) {
                    String access$500 = MinimalEncoder.this.stringToEncode;
                    int i3 = this.fromPosition;
                    Encoder.appendBytes(access$500.substring(i3, this.characterLength + i3), this.mode, bitArray, MinimalEncoder.this.encoders.getCharset(this.charsetEncoderIndex));
                }
            }

            private int getCharacterCountIndicator() {
                if (this.mode != Mode.BYTE) {
                    return this.characterLength;
                }
                ECIEncoderSet access$600 = MinimalEncoder.this.encoders;
                String access$500 = MinimalEncoder.this.stringToEncode;
                int i3 = this.fromPosition;
                return access$600.encode(access$500.substring(i3, this.characterLength + i3), this.charsetEncoderIndex).length;
            }

            /* access modifiers changed from: private */
            public int getSize(Version version) {
                int i3;
                int z2;
                int characterCountBits = this.mode.getCharacterCountBits(version);
                int i4 = characterCountBits + 4;
                int i5 = AnonymousClass1.$SwitchMap$com$google$zxing$qrcode$decoder$Mode[this.mode.ordinal()];
                if (i5 != 1) {
                    int i6 = 0;
                    if (i5 == 2) {
                        int i7 = this.characterLength;
                        z2 = b.z(i7, 2, 11, i4);
                        if (i7 % 2 == 1) {
                            i6 = 6;
                        }
                    } else if (i5 == 3) {
                        int i8 = this.characterLength;
                        z2 = b.z(i8, 3, 10, i4);
                        int i9 = i8 % 3;
                        if (i9 == 1) {
                            i6 = 4;
                        } else if (i9 == 2) {
                            i6 = 7;
                        }
                    } else if (i5 != 4) {
                        return i5 != 5 ? i4 : characterCountBits + 12;
                    } else {
                        i3 = getCharacterCountIndicator() * 8;
                    }
                    return z2 + i6;
                }
                i3 = this.characterLength * 13;
                return i4 + i3;
            }

            private String makePrintable(String str) {
                StringBuilder sb = new StringBuilder();
                for (int i3 = 0; i3 < str.length(); i3++) {
                    if (str.charAt(i3) < ' ' || str.charAt(i3) > '~') {
                        sb.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
                    } else {
                        sb.append(str.charAt(i3));
                    }
                }
                return sb.toString();
            }

            public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append(this.mode);
                sb.append('(');
                if (this.mode == Mode.ECI) {
                    sb.append(MinimalEncoder.this.encoders.getCharset(this.charsetEncoderIndex).displayName());
                } else {
                    String access$500 = MinimalEncoder.this.stringToEncode;
                    int i3 = this.fromPosition;
                    sb.append(makePrintable(access$500.substring(i3, this.characterLength + i3)));
                }
                sb.append(')');
                return sb.toString();
            }
        }

        public ResultList(Version version2, Edge edge) {
            int i3;
            int i4;
            Mode mode;
            int i5 = 0;
            int i6 = 0;
            boolean z2 = false;
            while (true) {
                i3 = 1;
                if (edge == null) {
                    break;
                }
                int access$000 = i6 + edge.characterLength;
                Edge access$700 = edge.previous;
                boolean z3 = (edge.mode == Mode.BYTE && access$700 == null && edge.charsetEncoderIndex != 0) || !(access$700 == null || edge.charsetEncoderIndex == access$700.charsetEncoderIndex);
                z2 = z3 ? true : z2;
                if (access$700 == null || access$700.mode != edge.mode || z3) {
                    this.list.add(0, new ResultNode(edge.mode, edge.fromPosition, edge.charsetEncoderIndex, access$000));
                    access$000 = 0;
                }
                if (z3) {
                    this.list.add(0, new ResultNode(Mode.ECI, edge.fromPosition, edge.charsetEncoderIndex, 0));
                }
                edge = access$700;
                i6 = access$000;
            }
            if (MinimalEncoder.this.isGS1) {
                ResultNode resultNode = this.list.get(0);
                if (!(resultNode == null || resultNode.mode == (mode = Mode.ECI) || !z2)) {
                    this.list.add(0, new ResultNode(mode, 0, 0, 0));
                }
                this.list.add(this.list.get(0).mode == Mode.ECI ? 1 : i5, new ResultNode(Mode.FNC1_FIRST_POSITION, 0, 0, 0));
            }
            int versionNumber = version2.getVersionNumber();
            int i7 = AnonymousClass1.$SwitchMap$com$google$zxing$qrcode$encoder$MinimalEncoder$VersionSize[MinimalEncoder.getVersionSize(version2).ordinal()];
            if (i7 == 1) {
                i4 = 9;
            } else if (i7 != 2) {
                i3 = 27;
                i4 = 40;
            } else {
                i3 = 10;
                i4 = 26;
            }
            int size = getSize(version2);
            while (versionNumber < i4 && !Encoder.willFit(size, Version.getVersionForNumber(versionNumber), MinimalEncoder.this.ecLevel)) {
                versionNumber++;
            }
            while (versionNumber > i3 && Encoder.willFit(size, Version.getVersionForNumber(versionNumber - 1), MinimalEncoder.this.ecLevel)) {
                versionNumber--;
            }
            this.version = Version.getVersionForNumber(versionNumber);
        }

        public void getBits(BitArray bitArray) throws WriterException {
            for (ResultNode access$1300 : this.list) {
                access$1300.getBits(bitArray);
            }
        }

        public int getSize() {
            return getSize(this.version);
        }

        public Version getVersion() {
            return this.version;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            ResultNode resultNode = null;
            for (ResultNode next : this.list) {
                if (resultNode != null) {
                    sb.append(",");
                }
                sb.append(next.toString());
                resultNode = next;
            }
            return sb.toString();
        }

        private int getSize(Version version2) {
            int i3 = 0;
            for (ResultNode access$1200 : this.list) {
                i3 += access$1200.getSize(version2);
            }
            return i3;
        }
    }

    public enum VersionSize {
        SMALL("version 1-9"),
        MEDIUM("version 10-26"),
        LARGE("version 27-40");
        
        private final String description;

        private VersionSize(String str) {
            this.description = str;
        }

        public String toString() {
            return this.description;
        }
    }

    public MinimalEncoder(String str, Charset charset, boolean z2, ErrorCorrectionLevel errorCorrectionLevel) {
        this.stringToEncode = str;
        this.isGS1 = z2;
        this.encoders = new ECIEncoderSet(str, charset, -1);
        this.ecLevel = errorCorrectionLevel;
    }

    public static ResultList encode(String str, Version version, Charset charset, boolean z2, ErrorCorrectionLevel errorCorrectionLevel) throws WriterException {
        return new MinimalEncoder(str, charset, z2, errorCorrectionLevel).encode(version);
    }

    public static int getCompactedOrdinal(Mode mode) {
        int i3;
        if (mode == null || (i3 = AnonymousClass1.$SwitchMap$com$google$zxing$qrcode$decoder$Mode[mode.ordinal()]) == 1) {
            return 0;
        }
        if (i3 == 2) {
            return 1;
        }
        if (i3 == 3) {
            return 2;
        }
        if (i3 == 4) {
            return 3;
        }
        throw new IllegalStateException("Illegal mode " + mode);
    }

    public static Version getVersion(VersionSize versionSize) {
        int i3 = AnonymousClass1.$SwitchMap$com$google$zxing$qrcode$encoder$MinimalEncoder$VersionSize[versionSize.ordinal()];
        return i3 != 1 ? i3 != 2 ? Version.getVersionForNumber(40) : Version.getVersionForNumber(26) : Version.getVersionForNumber(9);
    }

    public static VersionSize getVersionSize(Version version) {
        return version.getVersionNumber() <= 9 ? VersionSize.SMALL : version.getVersionNumber() <= 26 ? VersionSize.MEDIUM : VersionSize.LARGE;
    }

    public static boolean isAlphanumeric(char c3) {
        return Encoder.getAlphanumericCode(c3) != -1;
    }

    public static boolean isDoubleByteKanji(char c3) {
        return Encoder.isOnlyDoubleByteKanji(String.valueOf(c3));
    }

    public static boolean isNumeric(char c3) {
        return c3 >= '0' && c3 <= '9';
    }

    public void addEdge(Edge[][][] edgeArr, int i3, Edge edge) {
        Edge[] edgeArr2 = edgeArr[i3 + edge.characterLength][edge.charsetEncoderIndex];
        int compactedOrdinal = getCompactedOrdinal(edge.mode);
        Edge edge2 = edgeArr2[compactedOrdinal];
        if (edge2 == null || edge2.cachedTotalSize > edge.cachedTotalSize) {
            edgeArr2[compactedOrdinal] = edge;
        }
    }

    public void addEdges(Version version, Edge[][][] edgeArr, int i3, Edge edge) {
        int i4;
        Edge[][][] edgeArr2 = edgeArr;
        int i5 = i3;
        int length = this.encoders.length();
        int priorityEncoderIndex = this.encoders.getPriorityEncoderIndex();
        if (priorityEncoderIndex < 0 || !this.encoders.canEncode(this.stringToEncode.charAt(i5), priorityEncoderIndex)) {
            priorityEncoderIndex = 0;
        } else {
            length = priorityEncoderIndex + 1;
        }
        int i6 = length;
        for (int i7 = priorityEncoderIndex; i7 < i6; i7++) {
            if (this.encoders.canEncode(this.stringToEncode.charAt(i5), i7)) {
                addEdge(edgeArr2, i5, new Edge(this, Mode.BYTE, i3, i7, 1, edge, version, (AnonymousClass1) null));
            }
        }
        Mode mode = Mode.KANJI;
        if (canEncode(mode, this.stringToEncode.charAt(i5))) {
            addEdge(edgeArr2, i5, new Edge(this, mode, i3, 0, 1, edge, version, (AnonymousClass1) null));
        }
        int length2 = this.stringToEncode.length();
        Mode mode2 = Mode.ALPHANUMERIC;
        if (canEncode(mode2, this.stringToEncode.charAt(i5))) {
            int i8 = i5 + 1;
            addEdge(edgeArr2, i5, new Edge(this, mode2, i3, 0, (i8 >= length2 || !canEncode(mode2, this.stringToEncode.charAt(i8))) ? 1 : 2, edge, version, (AnonymousClass1) null));
        }
        Mode mode3 = Mode.NUMERIC;
        if (canEncode(mode3, this.stringToEncode.charAt(i5))) {
            int i9 = i5 + 1;
            if (i9 >= length2 || !canEncode(mode3, this.stringToEncode.charAt(i9))) {
                i4 = 1;
            } else {
                int i10 = i5 + 2;
                i4 = (i10 >= length2 || !canEncode(mode3, this.stringToEncode.charAt(i10))) ? 2 : 3;
            }
            addEdge(edgeArr2, i5, new Edge(this, mode3, i3, 0, i4, edge, version, (AnonymousClass1) null));
        }
    }

    public boolean canEncode(Mode mode, char c3) {
        int i3 = AnonymousClass1.$SwitchMap$com$google$zxing$qrcode$decoder$Mode[mode.ordinal()];
        return i3 != 1 ? i3 != 2 ? i3 != 3 ? i3 == 4 : isNumeric(c3) : isAlphanumeric(c3) : isDoubleByteKanji(c3);
    }

    public ResultList encodeSpecificVersion(Version version) throws WriterException {
        int length = this.stringToEncode.length();
        int length2 = this.encoders.length();
        int[] iArr = new int[3];
        iArr[2] = 4;
        iArr[1] = length2;
        iArr[0] = length + 1;
        Edge[][][] edgeArr = (Edge[][][]) Array.newInstance(Edge.class, iArr);
        addEdges(version, edgeArr, 0, (Edge) null);
        for (int i3 = 1; i3 <= length; i3++) {
            for (int i4 = 0; i4 < this.encoders.length(); i4++) {
                for (int i5 = 0; i5 < 4; i5++) {
                    Edge edge = edgeArr[i3][i4][i5];
                    if (edge != null && i3 < length) {
                        addEdges(version, edgeArr, i3, edge);
                    }
                }
            }
        }
        int i6 = -1;
        int i7 = Integer.MAX_VALUE;
        int i8 = -1;
        for (int i9 = 0; i9 < this.encoders.length(); i9++) {
            for (int i10 = 0; i10 < 4; i10++) {
                Edge edge2 = edgeArr[length][i9][i10];
                if (edge2 != null && edge2.cachedTotalSize < i7) {
                    i7 = edge2.cachedTotalSize;
                    i6 = i9;
                    i8 = i10;
                }
            }
        }
        if (i6 >= 0) {
            return new ResultList(version, edgeArr[length][i6][i8]);
        }
        throw new WriterException(a.n(new StringBuilder("Internal error: failed to encode \""), this.stringToEncode, "\""));
    }

    public ResultList encode(Version version) throws WriterException {
        if (version == null) {
            Version[] versionArr = {getVersion(VersionSize.SMALL), getVersion(VersionSize.MEDIUM), getVersion(VersionSize.LARGE)};
            ResultList[] resultListArr = {encodeSpecificVersion(versionArr[0]), encodeSpecificVersion(versionArr[1]), encodeSpecificVersion(versionArr[2])};
            int i3 = Integer.MAX_VALUE;
            int i4 = -1;
            for (int i5 = 0; i5 < 3; i5++) {
                int size = resultListArr[i5].getSize();
                if (Encoder.willFit(size, versionArr[i5], this.ecLevel) && size < i3) {
                    i4 = i5;
                    i3 = size;
                }
            }
            if (i4 >= 0) {
                return resultListArr[i4];
            }
            throw new WriterException("Data too big for any version");
        }
        ResultList encodeSpecificVersion = encodeSpecificVersion(version);
        if (Encoder.willFit(encodeSpecificVersion.getSize(), getVersion(getVersionSize(encodeSpecificVersion.getVersion())), this.ecLevel)) {
            return encodeSpecificVersion;
        }
        throw new WriterException("Data too big for version" + version);
    }
}
