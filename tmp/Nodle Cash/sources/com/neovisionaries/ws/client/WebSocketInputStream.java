package com.neovisionaries.ws.client;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

class WebSocketInputStream extends FilterInputStream {
    public WebSocketInputStream(InputStream inputStream) {
        super(inputStream);
    }

    private byte[] readPayload(long j2, boolean z2, byte[] bArr) throws IOException, WebSocketException {
        if (j2 == 0) {
            return null;
        }
        int i3 = (int) j2;
        try {
            byte[] bArr2 = new byte[i3];
            readBytes(bArr2, i3);
            if (z2) {
                WebSocketFrame.mask(bArr, bArr2);
            }
            return bArr2;
        } catch (OutOfMemoryError e3) {
            skipQuietly(j2);
            WebSocketError webSocketError = WebSocketError.INSUFFICIENT_MEMORY_FOR_PAYLOAD;
            throw new WebSocketException(webSocketError, "OutOfMemoryError occurred during a trial to allocate a memory area for a frame's payload: " + e3.getMessage(), e3);
        }
    }

    private void skipQuietly(long j2) {
        try {
            skip(j2);
        } catch (IOException unused) {
        }
    }

    public void readBytes(byte[] bArr, int i3) throws IOException, WebSocketException {
        int i4 = 0;
        while (i4 < i3) {
            int read = read(bArr, i4, i3 - i4);
            if (read > 0) {
                i4 += read;
            } else {
                throw new InsufficientDataException(i3, i4);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00d3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.neovisionaries.ws.client.WebSocketFrame readFrame() throws java.io.IOException, com.neovisionaries.ws.client.WebSocketException {
        /*
            r18 = this;
            r0 = r18
            r1 = 8
            byte[] r2 = new byte[r1]
            r3 = 2
            r0.readBytes(r2, r3)     // Catch:{ InsufficientDataException -> 0x00e0 }
            r4 = 0
            byte r5 = r2[r4]
            r6 = r5 & 128(0x80, float:1.794E-43)
            r7 = 1
            if (r6 == 0) goto L_0x0014
            r6 = r7
            goto L_0x0015
        L_0x0014:
            r6 = r4
        L_0x0015:
            r8 = r5 & 64
            if (r8 == 0) goto L_0x001b
            r8 = r7
            goto L_0x001c
        L_0x001b:
            r8 = r4
        L_0x001c:
            r9 = r5 & 32
            if (r9 == 0) goto L_0x0022
            r9 = r7
            goto L_0x0023
        L_0x0022:
            r9 = r4
        L_0x0023:
            r10 = r5 & 16
            if (r10 == 0) goto L_0x0029
            r10 = r7
            goto L_0x002a
        L_0x0029:
            r10 = r4
        L_0x002a:
            r5 = r5 & 15
            byte r11 = r2[r7]
            r12 = r11 & 128(0x80, float:1.794E-43)
            if (r12 == 0) goto L_0x0034
            r12 = r7
            goto L_0x0035
        L_0x0034:
            r12 = r4
        L_0x0035:
            r11 = r11 & 127(0x7f, float:1.78E-43)
            long r13 = (long) r11
            r15 = 126(0x7e, double:6.23E-322)
            int r11 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            r15 = 4
            if (r11 != 0) goto L_0x004f
            r0.readBytes(r2, r3)
            byte r3 = r2[r4]
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r1 = r3 << 8
            byte r2 = r2[r7]
        L_0x004a:
            r2 = r2 & 255(0xff, float:3.57E-43)
            r1 = r1 | r2
            long r13 = (long) r1
            goto L_0x009d
        L_0x004f:
            r16 = 127(0x7f, double:6.27E-322)
            int r11 = (r13 > r16 ? 1 : (r13 == r16 ? 0 : -1))
            if (r11 != 0) goto L_0x009d
            r0.readBytes(r2, r1)
            byte r4 = r2[r4]
            r11 = r4 & 128(0x80, float:1.794E-43)
            if (r11 != 0) goto L_0x0093
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r4 = r4 << 56
            byte r7 = r2[r7]
            r7 = r7 & 255(0xff, float:3.57E-43)
            int r7 = r7 << 48
            r4 = r4 | r7
            byte r3 = r2[r3]
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r3 = r3 << 40
            r3 = r3 | r4
            r4 = 3
            byte r4 = r2[r4]
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r4 = r4 << 32
            r3 = r3 | r4
            byte r4 = r2[r15]
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r4 = r4 << 24
            r3 = r3 | r4
            r4 = 5
            byte r4 = r2[r4]
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r4 = r4 << 16
            r3 = r3 | r4
            r4 = 6
            byte r4 = r2[r4]
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r1 = r4 << 8
            r1 = r1 | r3
            r3 = 7
            byte r2 = r2[r3]
            goto L_0x004a
        L_0x0093:
            com.neovisionaries.ws.client.WebSocketException r0 = new com.neovisionaries.ws.client.WebSocketException
            com.neovisionaries.ws.client.WebSocketError r1 = com.neovisionaries.ws.client.WebSocketError.INVALID_PAYLOAD_LENGTH
            java.lang.String r2 = "The payload length of a frame is invalid."
            r0.<init>((com.neovisionaries.ws.client.WebSocketError) r1, (java.lang.String) r2)
            throw r0
        L_0x009d:
            if (r12 == 0) goto L_0x00a5
            byte[] r1 = new byte[r15]
            r0.readBytes(r1, r15)
            goto L_0x00a6
        L_0x00a5:
            r1 = 0
        L_0x00a6:
            r2 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r2 = (r2 > r13 ? 1 : (r2 == r13 ? 0 : -1))
            if (r2 < 0) goto L_0x00d3
            byte[] r0 = r0.readPayload(r13, r12, r1)
            com.neovisionaries.ws.client.WebSocketFrame r1 = new com.neovisionaries.ws.client.WebSocketFrame
            r1.<init>()
            com.neovisionaries.ws.client.WebSocketFrame r1 = r1.setFin(r6)
            com.neovisionaries.ws.client.WebSocketFrame r1 = r1.setRsv1(r8)
            com.neovisionaries.ws.client.WebSocketFrame r1 = r1.setRsv2(r9)
            com.neovisionaries.ws.client.WebSocketFrame r1 = r1.setRsv3(r10)
            com.neovisionaries.ws.client.WebSocketFrame r1 = r1.setOpcode(r5)
            com.neovisionaries.ws.client.WebSocketFrame r1 = r1.setMask(r12)
            com.neovisionaries.ws.client.WebSocketFrame r0 = r1.setPayload((byte[]) r0)
            return r0
        L_0x00d3:
            r0.skipQuietly(r13)
            com.neovisionaries.ws.client.WebSocketException r0 = new com.neovisionaries.ws.client.WebSocketException
            com.neovisionaries.ws.client.WebSocketError r1 = com.neovisionaries.ws.client.WebSocketError.TOO_LONG_PAYLOAD
            java.lang.String r2 = "The payload length of a frame exceeds the maximum array size in Java."
            r0.<init>((com.neovisionaries.ws.client.WebSocketError) r1, (java.lang.String) r2)
            throw r0
        L_0x00e0:
            r0 = move-exception
            r1 = r0
            int r0 = r1.getReadByteCount()
            if (r0 != 0) goto L_0x00ee
            com.neovisionaries.ws.client.NoMoreFrameException r0 = new com.neovisionaries.ws.client.NoMoreFrameException
            r0.<init>()
            throw r0
        L_0x00ee:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neovisionaries.ws.client.WebSocketInputStream.readFrame():com.neovisionaries.ws.client.WebSocketFrame");
    }

    public String readLine() throws IOException {
        return Misc.readLine(this, "UTF-8");
    }
}
