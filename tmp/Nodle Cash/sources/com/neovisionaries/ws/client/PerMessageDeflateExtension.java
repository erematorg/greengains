package com.neovisionaries.ws.client;

import android.support.v4.media.session.a;
import androidx.browser.trusted.c;
import androidx.camera.camera2.internal.C0118y;
import java.util.Map;

class PerMessageDeflateExtension extends PerMessageCompressionExtension {
    private static final String CLIENT_MAX_WINDOW_BITS = "client_max_window_bits";
    private static final String CLIENT_NO_CONTEXT_TAKEOVER = "client_no_context_takeover";
    private static final byte[] COMPRESSION_TERMINATOR = {0, 0, -1, -1};
    private static final int INCOMING_SLIDING_WINDOW_MARGIN = 1024;
    private static final int MAX_BITS = 15;
    private static final int MAX_WINDOW_SIZE = 32768;
    private static final int MIN_BITS = 8;
    private static final int MIN_WINDOW_SIZE = 256;
    private static final String SERVER_MAX_WINDOW_BITS = "server_max_window_bits";
    private static final String SERVER_NO_CONTEXT_TAKEOVER = "server_no_context_takeover";
    private boolean mClientNoContextTakeover;
    private int mClientWindowSize = 32768;
    private ByteArray mIncomingSlidingWindow;
    private int mIncomingSlidingWindowBufferSize;
    private boolean mServerNoContextTakeover;
    private int mServerWindowSize = 32768;

    public PerMessageDeflateExtension() {
        super(WebSocketExtension.PERMESSAGE_DEFLATE);
    }

    private static byte[] adjustCompressedData(byte[] bArr) throws FormatException {
        ByteArray byteArray = new ByteArray(bArr.length + 1);
        byteArray.put(bArr);
        int[] iArr = new int[1];
        boolean[] zArr = new boolean[1];
        do {
        } while (skipBlock(byteArray, iArr, zArr));
        if (zArr[0]) {
            return byteArray.toBytes(0, a.d(iArr[0], 1, 8, -3));
        }
        appendEmptyBlock(byteArray, iArr);
        return byteArray.toBytes(0, a.d(iArr[0], 1, 8, 1));
    }

    private static void appendEmptyBlock(ByteArray byteArray, int[] iArr) {
        int i3 = iArr[0] % 8;
        if (i3 == 0 || i3 == 6 || i3 == 7) {
            byteArray.put(0);
        }
        iArr[0] = iArr[0] + 3;
    }

    private boolean canCompress(byte[] bArr) {
        int i3 = this.mClientWindowSize;
        return i3 == 32768 || bArr.length < i3;
    }

    private int computeWindowSize(String str, String str2) throws WebSocketException {
        int extractMaxWindowBits = extractMaxWindowBits(str, str2);
        int i3 = 256;
        for (int i4 = 8; i4 < extractMaxWindowBits; i4++) {
            i3 *= 2;
        }
        return i3;
    }

    private int extractMaxWindowBits(String str, String str2) throws WebSocketException {
        int parseMaxWindowBits = parseMaxWindowBits(str2);
        if (parseMaxWindowBits >= 0) {
            return parseMaxWindowBits;
        }
        throw new WebSocketException(WebSocketError.PERMESSAGE_DEFLATE_INVALID_MAX_WINDOW_BITS, C0118y.f("The value of ", str, " parameter of permessage-deflate extension is invalid: ", str2));
    }

    private int parseMaxWindowBits(String str) {
        if (str == null) {
            return -1;
        }
        try {
            int parseInt = Integer.parseInt(str);
            if (parseInt < 8 || 15 < parseInt) {
                return -1;
            }
            return parseInt;
        } catch (NumberFormatException unused) {
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x004b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean skipBlock(com.neovisionaries.ws.client.ByteArray r5, int[] r6, boolean[] r7) throws com.neovisionaries.ws.client.FormatException {
        /*
            boolean r0 = r5.readBit(r6)
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x000e
            r3 = r6[r2]
            int r3 = r3 - r1
            r5.clearBit(r3)
        L_0x000e:
            r3 = 2
            int r4 = r5.readBits(r6, r3)
            if (r4 == 0) goto L_0x003a
            if (r4 == r1) goto L_0x0035
            if (r4 != r3) goto L_0x001d
            skipDynamicBlock(r5, r6)
            goto L_0x0038
        L_0x001d:
            r5 = r6[r2]
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            java.lang.String r6 = "PerMessageDeflateExtension"
            java.lang.Object[] r5 = new java.lang.Object[]{r6, r5}
            java.lang.String r6 = "[%s] Bad compression type '11' at the bit index '%d'."
            java.lang.String r5 = java.lang.String.format(r6, r5)
            com.neovisionaries.ws.client.FormatException r6 = new com.neovisionaries.ws.client.FormatException
            r6.<init>(r5)
            throw r6
        L_0x0035:
            skipFixedBlock(r5, r6)
        L_0x0038:
            r3 = r2
            goto L_0x0041
        L_0x003a:
            int r3 = skipPlainBlock(r5, r6)
            if (r3 != 0) goto L_0x0038
            r3 = r1
        L_0x0041:
            int r5 = r5.length()
            r6 = r6[r2]
            int r6 = r6 / 8
            if (r5 > r6) goto L_0x004c
            r0 = r1
        L_0x004c:
            if (r0 == 0) goto L_0x0052
            if (r3 == 0) goto L_0x0052
            r7[r2] = r1
        L_0x0052:
            r5 = r0 ^ 1
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neovisionaries.ws.client.PerMessageDeflateExtension.skipBlock(com.neovisionaries.ws.client.ByteArray, int[], boolean[]):boolean");
    }

    private static void skipData(ByteArray byteArray, int[] iArr, Huffman huffman, Huffman huffman2) throws FormatException {
        while (true) {
            int readSym = huffman.readSym(byteArray, iArr);
            if (readSym != 256) {
                if (readSym < 0 || readSym > 255) {
                    DeflateUtil.readLength(byteArray, iArr, readSym);
                    DeflateUtil.readDistance(byteArray, iArr, huffman2);
                }
            } else {
                return;
            }
        }
    }

    private static void skipDynamicBlock(ByteArray byteArray, int[] iArr) throws FormatException {
        Huffman[] huffmanArr = new Huffman[2];
        DeflateUtil.readDynamicTables(byteArray, iArr, huffmanArr);
        skipData(byteArray, iArr, huffmanArr[0], huffmanArr[1]);
    }

    private static void skipFixedBlock(ByteArray byteArray, int[] iArr) throws FormatException {
        skipData(byteArray, iArr, FixedLiteralLengthHuffman.getInstance(), FixedDistanceHuffman.getInstance());
    }

    private static int skipPlainBlock(ByteArray byteArray, int[] iArr) {
        int i3 = ((iArr[0] + 7) & -8) / 8;
        int i4 = ((byteArray.get(i3 + 1) & 255) * 256) + (byteArray.get(i3) & 255);
        iArr[0] = (i3 + 4 + i4) * 8;
        return i4;
    }

    private void validateParameter(String str, String str2) throws WebSocketException {
        if (SERVER_NO_CONTEXT_TAKEOVER.equals(str)) {
            this.mServerNoContextTakeover = true;
        } else if (CLIENT_NO_CONTEXT_TAKEOVER.equals(str)) {
            this.mClientNoContextTakeover = true;
        } else if (SERVER_MAX_WINDOW_BITS.equals(str)) {
            this.mServerWindowSize = computeWindowSize(str, str2);
        } else if (CLIENT_MAX_WINDOW_BITS.equals(str)) {
            this.mClientWindowSize = computeWindowSize(str, str2);
        } else {
            throw new WebSocketException(WebSocketError.PERMESSAGE_DEFLATE_UNSUPPORTED_PARAMETER, c.a("permessage-deflate extension contains an unsupported parameter: ", str));
        }
    }

    public byte[] compress(byte[] bArr) throws WebSocketException {
        if (!canCompress(bArr)) {
            return bArr;
        }
        try {
            return adjustCompressedData(DeflateCompressor.compress(bArr));
        } catch (Exception e3) {
            throw new WebSocketException(WebSocketError.COMPRESSION_ERROR, c.a("Failed to compress the message: ", e3.getMessage()), e3);
        }
    }

    public byte[] decompress(byte[] bArr) throws WebSocketException {
        int length = bArr.length;
        byte[] bArr2 = COMPRESSION_TERMINATOR;
        ByteArray byteArray = new ByteArray(length + bArr2.length);
        byteArray.put(bArr);
        byteArray.put(bArr2);
        if (this.mIncomingSlidingWindow == null) {
            this.mIncomingSlidingWindow = new ByteArray(this.mIncomingSlidingWindowBufferSize);
        }
        int length2 = this.mIncomingSlidingWindow.length();
        try {
            DeflateDecompressor.decompress(byteArray, this.mIncomingSlidingWindow);
            byte[] bytes = this.mIncomingSlidingWindow.toBytes(length2);
            this.mIncomingSlidingWindow.shrink(this.mIncomingSlidingWindowBufferSize);
            if (this.mServerNoContextTakeover) {
                this.mIncomingSlidingWindow.clear();
            }
            return bytes;
        } catch (Exception e3) {
            throw new WebSocketException(WebSocketError.DECOMPRESSION_ERROR, c.a("Failed to decompress the message: ", e3.getMessage()), e3);
        }
    }

    public int getClientWindowSize() {
        return this.mClientWindowSize;
    }

    public int getServerWindowSize() {
        return this.mServerWindowSize;
    }

    public boolean isClientNoContextTakeover() {
        return this.mClientNoContextTakeover;
    }

    public boolean isServerNoContextTakeover() {
        return this.mServerNoContextTakeover;
    }

    public void validate() throws WebSocketException {
        for (Map.Entry next : getParameters().entrySet()) {
            validateParameter((String) next.getKey(), (String) next.getValue());
        }
        this.mIncomingSlidingWindowBufferSize = this.mServerWindowSize + 1024;
    }

    public PerMessageDeflateExtension(String str) {
        super(str);
    }
}
