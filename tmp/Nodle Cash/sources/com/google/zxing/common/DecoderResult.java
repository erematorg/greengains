package com.google.zxing.common;

import java.util.List;

public final class DecoderResult {
    private final List<byte[]> byteSegments;
    private final String ecLevel;
    private Integer erasures;
    private Integer errorsCorrected;
    private int numBits;
    private Object other;
    private final byte[] rawBytes;
    private final int structuredAppendParity;
    private final int structuredAppendSequenceNumber;
    private final int symbologyModifier;
    private final String text;

    public DecoderResult(byte[] bArr, String str, List<byte[]> list, String str2) {
        this(bArr, str, list, str2, -1, -1, 0);
    }

    public List<byte[]> getByteSegments() {
        return this.byteSegments;
    }

    public String getECLevel() {
        return this.ecLevel;
    }

    public Integer getErasures() {
        return this.erasures;
    }

    public Integer getErrorsCorrected() {
        return this.errorsCorrected;
    }

    public int getNumBits() {
        return this.numBits;
    }

    public Object getOther() {
        return this.other;
    }

    public byte[] getRawBytes() {
        return this.rawBytes;
    }

    public int getStructuredAppendParity() {
        return this.structuredAppendParity;
    }

    public int getStructuredAppendSequenceNumber() {
        return this.structuredAppendSequenceNumber;
    }

    public int getSymbologyModifier() {
        return this.symbologyModifier;
    }

    public String getText() {
        return this.text;
    }

    public boolean hasStructuredAppend() {
        return this.structuredAppendParity >= 0 && this.structuredAppendSequenceNumber >= 0;
    }

    public void setErasures(Integer num) {
        this.erasures = num;
    }

    public void setErrorsCorrected(Integer num) {
        this.errorsCorrected = num;
    }

    public void setNumBits(int i3) {
        this.numBits = i3;
    }

    public void setOther(Object obj) {
        this.other = obj;
    }

    public DecoderResult(byte[] bArr, String str, List<byte[]> list, String str2, int i3) {
        this(bArr, str, list, str2, -1, -1, i3);
    }

    public DecoderResult(byte[] bArr, String str, List<byte[]> list, String str2, int i3, int i4) {
        this(bArr, str, list, str2, i3, i4, 0);
    }

    public DecoderResult(byte[] bArr, String str, List<byte[]> list, String str2, int i3, int i4, int i5) {
        int i6;
        this.rawBytes = bArr;
        if (bArr == null) {
            i6 = 0;
        } else {
            i6 = bArr.length * 8;
        }
        this.numBits = i6;
        this.text = str;
        this.byteSegments = list;
        this.ecLevel = str2;
        this.structuredAppendParity = i4;
        this.structuredAppendSequenceNumber = i3;
        this.symbologyModifier = i5;
    }
}
