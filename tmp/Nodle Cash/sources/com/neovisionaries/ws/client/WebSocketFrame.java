package com.neovisionaries.ws.client;

import androidx.compose.ui.autofill.a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.xerces.impl.xs.SchemaSymbols;

public class WebSocketFrame {
    private boolean mFin;
    private boolean mMask;
    private int mOpcode;
    private byte[] mPayload;
    private boolean mRsv1;
    private boolean mRsv2;
    private boolean mRsv3;

    private void appendPayloadBinary(StringBuilder sb) {
        byte[] bArr;
        if (!appendPayloadCommon(sb)) {
            int i3 = 0;
            while (true) {
                bArr = this.mPayload;
                if (i3 >= bArr.length) {
                    break;
                }
                sb.append(String.format("%02X ", new Object[]{Integer.valueOf(bArr[i3] & 255)}));
                i3++;
            }
            if (bArr.length != 0) {
                sb.setLength(sb.length() - 1);
            }
        }
    }

    private void appendPayloadClose(StringBuilder sb) {
        sb.append(",CloseCode=");
        sb.append(getCloseCode());
        sb.append(",Reason=");
        String closeReason = getCloseReason();
        if (closeReason == null) {
            sb.append(AbstractJsonLexerKt.NULL);
        } else {
            a.o(sb, "\"", closeReason, "\"");
        }
    }

    private boolean appendPayloadCommon(StringBuilder sb) {
        sb.append(",Payload=");
        if (this.mPayload == null) {
            sb.append(AbstractJsonLexerKt.NULL);
            return true;
        } else if (!this.mRsv1) {
            return false;
        } else {
            sb.append("compressed");
            return true;
        }
    }

    private void appendPayloadText(StringBuilder sb) {
        if (!appendPayloadCommon(sb)) {
            sb.append("\"");
            sb.append(getPayloadText());
            sb.append("\"");
        }
    }

    private static byte[] compress(byte[] bArr, PerMessageCompressionExtension perMessageCompressionExtension) {
        try {
            return perMessageCompressionExtension.compress(bArr);
        } catch (WebSocketException unused) {
            return bArr;
        }
    }

    public static WebSocketFrame compressFrame(WebSocketFrame webSocketFrame, PerMessageCompressionExtension perMessageCompressionExtension) {
        byte[] payload;
        if (perMessageCompressionExtension == null) {
            return webSocketFrame;
        }
        if ((webSocketFrame.isTextFrame() || webSocketFrame.isBinaryFrame()) && webSocketFrame.getFin() && !webSocketFrame.getRsv1() && (payload = webSocketFrame.getPayload()) != null && payload.length != 0) {
            byte[] compress = compress(payload, perMessageCompressionExtension);
            if (payload.length <= compress.length) {
                return webSocketFrame;
            }
            webSocketFrame.setPayload(compress);
            webSocketFrame.setRsv1(true);
        }
        return webSocketFrame;
    }

    public static WebSocketFrame createBinaryFrame(byte[] bArr) {
        return new WebSocketFrame().setFin(true).setOpcode(2).setPayload(bArr);
    }

    public static WebSocketFrame createCloseFrame() {
        return new WebSocketFrame().setFin(true).setOpcode(8);
    }

    public static WebSocketFrame createContinuationFrame() {
        return new WebSocketFrame().setOpcode(0);
    }

    public static WebSocketFrame createPingFrame() {
        return new WebSocketFrame().setFin(true).setOpcode(9);
    }

    public static WebSocketFrame createPongFrame() {
        return new WebSocketFrame().setFin(true).setOpcode(10);
    }

    public static WebSocketFrame createTextFrame(String str) {
        return new WebSocketFrame().setFin(true).setOpcode(1).setPayload(str);
    }

    public static byte[] mask(byte[] bArr, byte[] bArr2) {
        if (!(bArr == null || bArr.length < 4 || bArr2 == null)) {
            for (int i3 = 0; i3 < bArr2.length; i3++) {
                bArr2[i3] = (byte) (bArr2[i3] ^ bArr[i3 % 4]);
            }
        }
        return bArr2;
    }

    private static List<WebSocketFrame> split(WebSocketFrame webSocketFrame, int i3) {
        byte[] payload = webSocketFrame.getPayload();
        boolean fin = webSocketFrame.getFin();
        ArrayList arrayList = new ArrayList();
        webSocketFrame.setFin(false).setPayload(Arrays.copyOf(payload, i3));
        arrayList.add(webSocketFrame);
        int i4 = i3;
        while (i4 < payload.length) {
            int i5 = i4 + i3;
            arrayList.add(createContinuationFrame(Arrays.copyOfRange(payload, i4, Math.min(i5, payload.length))));
            i4 = i5;
        }
        if (fin) {
            ((WebSocketFrame) a.h(arrayList, 1)).setFin(true);
        }
        return arrayList;
    }

    public static List<WebSocketFrame> splitIfNecessary(WebSocketFrame webSocketFrame, int i3, PerMessageCompressionExtension perMessageCompressionExtension) {
        if (i3 == 0 || webSocketFrame.getPayloadLength() <= i3) {
            return null;
        }
        if (webSocketFrame.isBinaryFrame() || webSocketFrame.isTextFrame()) {
            webSocketFrame = compressFrame(webSocketFrame, perMessageCompressionExtension);
            if (webSocketFrame.getPayloadLength() <= i3) {
                return null;
            }
        } else if (!webSocketFrame.isContinuationFrame()) {
            return null;
        }
        return split(webSocketFrame, i3);
    }

    public int getCloseCode() {
        byte[] bArr = this.mPayload;
        if (bArr == null || bArr.length < 2) {
            return 1005;
        }
        return (bArr[1] & 255) | ((bArr[0] & 255) << 8);
    }

    public String getCloseReason() {
        byte[] bArr = this.mPayload;
        if (bArr == null || bArr.length < 3) {
            return null;
        }
        return Misc.toStringUTF8(bArr, 2, bArr.length - 2);
    }

    public boolean getFin() {
        return this.mFin;
    }

    public boolean getMask() {
        return this.mMask;
    }

    public int getOpcode() {
        return this.mOpcode;
    }

    public byte[] getPayload() {
        return this.mPayload;
    }

    public int getPayloadLength() {
        byte[] bArr = this.mPayload;
        if (bArr == null) {
            return 0;
        }
        return bArr.length;
    }

    public String getPayloadText() {
        byte[] bArr = this.mPayload;
        if (bArr == null) {
            return null;
        }
        return Misc.toStringUTF8(bArr);
    }

    public boolean getRsv1() {
        return this.mRsv1;
    }

    public boolean getRsv2() {
        return this.mRsv2;
    }

    public boolean getRsv3() {
        return this.mRsv3;
    }

    public boolean hasPayload() {
        return this.mPayload != null;
    }

    public boolean isBinaryFrame() {
        return this.mOpcode == 2;
    }

    public boolean isCloseFrame() {
        return this.mOpcode == 8;
    }

    public boolean isContinuationFrame() {
        return this.mOpcode == 0;
    }

    public boolean isControlFrame() {
        int i3 = this.mOpcode;
        return 8 <= i3 && i3 <= 15;
    }

    public boolean isDataFrame() {
        int i3 = this.mOpcode;
        return 1 <= i3 && i3 <= 7;
    }

    public boolean isPingFrame() {
        return this.mOpcode == 9;
    }

    public boolean isPongFrame() {
        return this.mOpcode == 10;
    }

    public boolean isTextFrame() {
        return this.mOpcode == 1;
    }

    public WebSocketFrame setCloseFramePayload(int i3, String str) {
        byte[] bArr = {(byte) ((i3 >> 8) & 255), (byte) (i3 & 255)};
        if (str == null || str.length() == 0) {
            return setPayload(bArr);
        }
        byte[] bytesUTF8 = Misc.getBytesUTF8(str);
        byte[] bArr2 = new byte[(bytesUTF8.length + 2)];
        System.arraycopy(bArr, 0, bArr2, 0, 2);
        System.arraycopy(bytesUTF8, 0, bArr2, 2, bytesUTF8.length);
        return setPayload(bArr2);
    }

    public WebSocketFrame setFin(boolean z2) {
        this.mFin = z2;
        return this;
    }

    public WebSocketFrame setMask(boolean z2) {
        this.mMask = z2;
        return this;
    }

    public WebSocketFrame setOpcode(int i3) {
        this.mOpcode = i3;
        return this;
    }

    public WebSocketFrame setPayload(byte[] bArr) {
        if (bArr != null && bArr.length == 0) {
            bArr = null;
        }
        this.mPayload = bArr;
        return this;
    }

    public WebSocketFrame setRsv1(boolean z2) {
        this.mRsv1 = z2;
        return this;
    }

    public WebSocketFrame setRsv2(boolean z2) {
        this.mRsv2 = z2;
        return this;
    }

    public WebSocketFrame setRsv3(boolean z2) {
        this.mRsv3 = z2;
        return this;
    }

    public String toString() {
        StringBuilder p2 = A.a.p("WebSocketFrame(FIN=");
        boolean z2 = this.mFin;
        String str = SchemaSymbols.ATTVAL_FALSE_0;
        p2.append(z2 ? "1" : str);
        p2.append(",RSV1=");
        p2.append(this.mRsv1 ? "1" : str);
        p2.append(",RSV2=");
        p2.append(this.mRsv2 ? "1" : str);
        p2.append(",RSV3=");
        if (this.mRsv3) {
            str = "1";
        }
        p2.append(str);
        p2.append(",Opcode=");
        p2.append(Misc.toOpcodeName(this.mOpcode));
        p2.append(",Length=");
        p2.append(getPayloadLength());
        int i3 = this.mOpcode;
        if (i3 == 1) {
            appendPayloadText(p2);
        } else if (i3 == 2) {
            appendPayloadBinary(p2);
        } else if (i3 == 8) {
            appendPayloadClose(p2);
        }
        p2.append(")");
        return p2.toString();
    }

    public static WebSocketFrame createContinuationFrame(byte[] bArr) {
        return createContinuationFrame().setPayload(bArr);
    }

    public WebSocketFrame setPayload(String str) {
        if (str == null || str.length() == 0) {
            return setPayload((byte[]) null);
        }
        return setPayload(Misc.getBytesUTF8(str));
    }

    public static WebSocketFrame createCloseFrame(int i3) {
        return createCloseFrame().setCloseFramePayload(i3, (String) null);
    }

    public static WebSocketFrame createContinuationFrame(String str) {
        return createContinuationFrame().setPayload(str);
    }

    public static WebSocketFrame createPingFrame(byte[] bArr) {
        return createPingFrame().setPayload(bArr);
    }

    public static WebSocketFrame createPongFrame(byte[] bArr) {
        return createPongFrame().setPayload(bArr);
    }

    public static WebSocketFrame createCloseFrame(int i3, String str) {
        return createCloseFrame().setCloseFramePayload(i3, str);
    }

    public static WebSocketFrame createPingFrame(String str) {
        return createPingFrame().setPayload(str);
    }

    public static WebSocketFrame createPongFrame(String str) {
        return createPongFrame().setPayload(str);
    }
}
