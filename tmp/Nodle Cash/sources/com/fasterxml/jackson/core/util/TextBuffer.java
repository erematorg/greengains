package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.io.NumberInput;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;

public final class TextBuffer {
    static final int MAX_SEGMENT_LEN = 65536;
    static final int MIN_SEGMENT_LEN = 500;
    static final char[] NO_CHARS = new char[0];
    private final BufferRecycler _allocator;
    private char[] _currentSegment;
    private int _currentSize;
    private boolean _hasSegments;
    private char[] _inputBuffer;
    private int _inputLen;
    private int _inputStart;
    private char[] _resultArray;
    private String _resultString;
    private int _segmentSize;
    private ArrayList<char[]> _segments;

    public TextBuffer(BufferRecycler bufferRecycler) {
        this._allocator = bufferRecycler;
    }

    private char[] buf(int i3) {
        BufferRecycler bufferRecycler = this._allocator;
        return bufferRecycler != null ? bufferRecycler.allocCharBuffer(2, i3) : new char[Math.max(i3, 500)];
    }

    private char[] carr(int i3) {
        return new char[i3];
    }

    private void clearSegments() {
        this._hasSegments = false;
        this._segments.clear();
        this._segmentSize = 0;
        this._currentSize = 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x002a, code lost:
        if (r3 > 65536) goto L_0x0026;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void expand(int r3) {
        /*
            r2 = this;
            java.util.ArrayList<char[]> r3 = r2._segments
            if (r3 != 0) goto L_0x000b
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            r2._segments = r3
        L_0x000b:
            char[] r3 = r2._currentSegment
            r0 = 1
            r2._hasSegments = r0
            java.util.ArrayList<char[]> r0 = r2._segments
            r0.add(r3)
            int r0 = r2._segmentSize
            int r1 = r3.length
            int r0 = r0 + r1
            r2._segmentSize = r0
            r0 = 0
            r2._currentSize = r0
            int r3 = r3.length
            int r0 = r3 >> 1
            int r3 = r3 + r0
            r0 = 500(0x1f4, float:7.0E-43)
            if (r3 >= r0) goto L_0x0028
        L_0x0026:
            r3 = r0
            goto L_0x002d
        L_0x0028:
            r0 = 65536(0x10000, float:9.18355E-41)
            if (r3 <= r0) goto L_0x002d
            goto L_0x0026
        L_0x002d:
            char[] r3 = r2.carr(r3)
            r2._currentSegment = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.util.TextBuffer.expand(int):void");
    }

    public static TextBuffer fromInitial(char[] cArr) {
        return new TextBuffer((BufferRecycler) null, cArr);
    }

    private char[] resultArray() {
        int i3;
        String str = this._resultString;
        if (str != null) {
            return str.toCharArray();
        }
        int i4 = this._inputStart;
        if (i4 >= 0) {
            int i5 = this._inputLen;
            return i5 < 1 ? NO_CHARS : i4 == 0 ? Arrays.copyOf(this._inputBuffer, i5) : Arrays.copyOfRange(this._inputBuffer, i4, i5 + i4);
        }
        int size = size();
        if (size < 1) {
            return NO_CHARS;
        }
        char[] carr = carr(size);
        ArrayList<char[]> arrayList = this._segments;
        if (arrayList != null) {
            int size2 = arrayList.size();
            i3 = 0;
            for (int i6 = 0; i6 < size2; i6++) {
                char[] cArr = this._segments.get(i6);
                int length = cArr.length;
                System.arraycopy(cArr, 0, carr, i3, length);
                i3 += length;
            }
        } else {
            i3 = 0;
        }
        System.arraycopy(this._currentSegment, 0, carr, i3, this._currentSize);
        return carr;
    }

    private void unshare(int i3) {
        int i4 = this._inputLen;
        this._inputLen = 0;
        char[] cArr = this._inputBuffer;
        this._inputBuffer = null;
        int i5 = this._inputStart;
        this._inputStart = -1;
        int i6 = i3 + i4;
        char[] cArr2 = this._currentSegment;
        if (cArr2 == null || i6 > cArr2.length) {
            this._currentSegment = buf(i6);
        }
        if (i4 > 0) {
            System.arraycopy(cArr, i5, this._currentSegment, 0, i4);
        }
        this._segmentSize = 0;
        this._currentSize = i4;
    }

    public void append(char c3) {
        if (this._inputStart >= 0) {
            unshare(16);
        }
        this._resultString = null;
        this._resultArray = null;
        char[] cArr = this._currentSegment;
        if (this._currentSize >= cArr.length) {
            expand(1);
            cArr = this._currentSegment;
        }
        int i3 = this._currentSize;
        this._currentSize = i3 + 1;
        cArr[i3] = c3;
    }

    public char[] contentsAsArray() {
        char[] cArr = this._resultArray;
        if (cArr != null) {
            return cArr;
        }
        char[] resultArray = resultArray();
        this._resultArray = resultArray;
        return resultArray;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001c, code lost:
        r0 = r2._currentSegment;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.math.BigDecimal contentsAsDecimal() throws java.lang.NumberFormatException {
        /*
            r2 = this;
            char[] r0 = r2._resultArray
            if (r0 == 0) goto L_0x0009
            java.math.BigDecimal r2 = com.fasterxml.jackson.core.io.NumberInput.parseBigDecimal((char[]) r0)
            return r2
        L_0x0009:
            int r0 = r2._inputStart
            if (r0 < 0) goto L_0x0018
            char[] r1 = r2._inputBuffer
            if (r1 == 0) goto L_0x0018
            int r2 = r2._inputLen
            java.math.BigDecimal r2 = com.fasterxml.jackson.core.io.NumberInput.parseBigDecimal(r1, r0, r2)
            return r2
        L_0x0018:
            int r0 = r2._segmentSize
            if (r0 != 0) goto L_0x0028
            char[] r0 = r2._currentSegment
            if (r0 == 0) goto L_0x0028
            r1 = 0
            int r2 = r2._currentSize
            java.math.BigDecimal r2 = com.fasterxml.jackson.core.io.NumberInput.parseBigDecimal(r0, r1, r2)
            return r2
        L_0x0028:
            char[] r2 = r2.contentsAsArray()
            java.math.BigDecimal r2 = com.fasterxml.jackson.core.io.NumberInput.parseBigDecimal((char[]) r2)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.util.TextBuffer.contentsAsDecimal():java.math.BigDecimal");
    }

    @Deprecated
    public double contentsAsDouble() throws NumberFormatException {
        return contentsAsDouble(false);
    }

    @Deprecated
    public float contentsAsFloat() throws NumberFormatException {
        return contentsAsFloat(false);
    }

    public int contentsAsInt(boolean z2) {
        char[] cArr;
        int i3 = this._inputStart;
        return (i3 < 0 || (cArr = this._inputBuffer) == null) ? z2 ? -NumberInput.parseInt(this._currentSegment, 1, this._currentSize - 1) : NumberInput.parseInt(this._currentSegment, 0, this._currentSize) : z2 ? -NumberInput.parseInt(cArr, i3 + 1, this._inputLen - 1) : NumberInput.parseInt(cArr, i3, this._inputLen);
    }

    public long contentsAsLong(boolean z2) {
        char[] cArr;
        int i3 = this._inputStart;
        return (i3 < 0 || (cArr = this._inputBuffer) == null) ? z2 ? -NumberInput.parseLong(this._currentSegment, 1, this._currentSize - 1) : NumberInput.parseLong(this._currentSegment, 0, this._currentSize) : z2 ? -NumberInput.parseLong(cArr, i3 + 1, this._inputLen - 1) : NumberInput.parseLong(cArr, i3, this._inputLen);
    }

    public String contentsAsString() {
        if (this._resultString == null) {
            char[] cArr = this._resultArray;
            if (cArr != null) {
                this._resultString = new String(cArr);
            } else {
                int i3 = this._inputStart;
                String str = "";
                if (i3 >= 0) {
                    int i4 = this._inputLen;
                    if (i4 < 1) {
                        this._resultString = str;
                        return str;
                    }
                    this._resultString = new String(this._inputBuffer, i3, i4);
                } else {
                    int i5 = this._segmentSize;
                    int i6 = this._currentSize;
                    if (i5 == 0) {
                        if (i6 != 0) {
                            str = new String(this._currentSegment, 0, i6);
                        }
                        this._resultString = str;
                    } else {
                        StringBuilder sb = new StringBuilder(i5 + i6);
                        ArrayList<char[]> arrayList = this._segments;
                        if (arrayList != null) {
                            int size = arrayList.size();
                            for (int i7 = 0; i7 < size; i7++) {
                                char[] cArr2 = this._segments.get(i7);
                                sb.append(cArr2, 0, cArr2.length);
                            }
                        }
                        sb.append(this._currentSegment, 0, this._currentSize);
                        this._resultString = sb.toString();
                    }
                }
            }
        }
        return this._resultString;
    }

    public int contentsToWriter(Writer writer) throws IOException {
        int i3;
        char[] cArr = this._resultArray;
        if (cArr != null) {
            writer.write(cArr);
            return this._resultArray.length;
        }
        String str = this._resultString;
        if (str != null) {
            writer.write(str);
            return this._resultString.length();
        }
        int i4 = this._inputStart;
        if (i4 >= 0) {
            int i5 = this._inputLen;
            if (i5 > 0) {
                writer.write(this._inputBuffer, i4, i5);
            }
            return i5;
        }
        ArrayList<char[]> arrayList = this._segments;
        if (arrayList != null) {
            int size = arrayList.size();
            i3 = 0;
            for (int i6 = 0; i6 < size; i6++) {
                char[] cArr2 = this._segments.get(i6);
                int length = cArr2.length;
                writer.write(cArr2, 0, length);
                i3 += length;
            }
        } else {
            i3 = 0;
        }
        int i7 = this._currentSize;
        if (i7 <= 0) {
            return i3;
        }
        writer.write(this._currentSegment, 0, i7);
        return i3 + i7;
    }

    public char[] emptyAndGetCurrentSegment() {
        this._inputStart = -1;
        this._currentSize = 0;
        this._inputLen = 0;
        this._inputBuffer = null;
        this._resultString = null;
        this._resultArray = null;
        if (this._hasSegments) {
            clearSegments();
        }
        char[] cArr = this._currentSegment;
        if (cArr != null) {
            return cArr;
        }
        char[] buf = buf(0);
        this._currentSegment = buf;
        return buf;
    }

    public void ensureNotShared() {
        if (this._inputStart >= 0) {
            unshare(16);
        }
    }

    public char[] expandCurrentSegment() {
        char[] cArr = this._currentSegment;
        int length = cArr.length;
        int i3 = (length >> 1) + length;
        if (i3 > 65536) {
            i3 = (length >> 2) + length;
        }
        char[] copyOf = Arrays.copyOf(cArr, i3);
        this._currentSegment = copyOf;
        return copyOf;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x002b, code lost:
        if (r0 > 65536) goto L_0x0027;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public char[] finishCurrentSegment() {
        /*
            r2 = this;
            java.util.ArrayList<char[]> r0 = r2._segments
            if (r0 != 0) goto L_0x000b
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r2._segments = r0
        L_0x000b:
            r0 = 1
            r2._hasSegments = r0
            java.util.ArrayList<char[]> r0 = r2._segments
            char[] r1 = r2._currentSegment
            r0.add(r1)
            char[] r0 = r2._currentSegment
            int r0 = r0.length
            int r1 = r2._segmentSize
            int r1 = r1 + r0
            r2._segmentSize = r1
            r1 = 0
            r2._currentSize = r1
            int r1 = r0 >> 1
            int r0 = r0 + r1
            r1 = 500(0x1f4, float:7.0E-43)
            if (r0 >= r1) goto L_0x0029
        L_0x0027:
            r0 = r1
            goto L_0x002e
        L_0x0029:
            r1 = 65536(0x10000, float:9.18355E-41)
            if (r0 <= r1) goto L_0x002e
            goto L_0x0027
        L_0x002e:
            char[] r0 = r2.carr(r0)
            r2._currentSegment = r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.util.TextBuffer.finishCurrentSegment():char[]");
    }

    public char[] getBufferWithoutReset() {
        return this._currentSegment;
    }

    public char[] getCurrentSegment() {
        if (this._inputStart >= 0) {
            unshare(1);
        } else {
            char[] cArr = this._currentSegment;
            if (cArr == null) {
                this._currentSegment = buf(0);
            } else if (this._currentSize >= cArr.length) {
                expand(1);
            }
        }
        return this._currentSegment;
    }

    public int getCurrentSegmentSize() {
        return this._currentSize;
    }

    public char[] getTextBuffer() {
        if (this._inputStart >= 0) {
            return this._inputBuffer;
        }
        char[] cArr = this._resultArray;
        if (cArr != null) {
            return cArr;
        }
        String str = this._resultString;
        if (str != null) {
            char[] charArray = str.toCharArray();
            this._resultArray = charArray;
            return charArray;
        } else if (this._hasSegments) {
            return contentsAsArray();
        } else {
            char[] cArr2 = this._currentSegment;
            return cArr2 == null ? NO_CHARS : cArr2;
        }
    }

    public int getTextOffset() {
        int i3 = this._inputStart;
        if (i3 >= 0) {
            return i3;
        }
        return 0;
    }

    public boolean hasTextAsCharacters() {
        return this._inputStart >= 0 || this._resultArray != null || this._resultString == null;
    }

    public void releaseBuffers() {
        char[] cArr;
        this._inputStart = -1;
        this._currentSize = 0;
        this._inputLen = 0;
        this._inputBuffer = null;
        this._resultArray = null;
        if (this._hasSegments) {
            clearSegments();
        }
        BufferRecycler bufferRecycler = this._allocator;
        if (bufferRecycler != null && (cArr = this._currentSegment) != null) {
            this._currentSegment = null;
            bufferRecycler.releaseCharBuffer(2, cArr);
        }
    }

    public void resetWith(char c3) {
        this._inputStart = -1;
        this._inputLen = 0;
        this._resultString = null;
        this._resultArray = null;
        if (this._hasSegments) {
            clearSegments();
        } else if (this._currentSegment == null) {
            this._currentSegment = buf(1);
        }
        this._currentSegment[0] = c3;
        this._segmentSize = 1;
        this._currentSize = 1;
    }

    public void resetWithCopy(char[] cArr, int i3, int i4) {
        this._inputBuffer = null;
        this._inputStart = -1;
        this._inputLen = 0;
        this._resultString = null;
        this._resultArray = null;
        if (this._hasSegments) {
            clearSegments();
        } else if (this._currentSegment == null) {
            this._currentSegment = buf(i4);
        }
        this._segmentSize = 0;
        this._currentSize = 0;
        append(cArr, i3, i4);
    }

    public void resetWithEmpty() {
        this._inputStart = -1;
        this._currentSize = 0;
        this._inputLen = 0;
        this._inputBuffer = null;
        this._resultString = null;
        this._resultArray = null;
        if (this._hasSegments) {
            clearSegments();
        }
    }

    public void resetWithShared(char[] cArr, int i3, int i4) {
        this._resultString = null;
        this._resultArray = null;
        this._inputBuffer = cArr;
        this._inputStart = i3;
        this._inputLen = i4;
        if (this._hasSegments) {
            clearSegments();
        }
    }

    public void resetWithString(String str) {
        this._inputBuffer = null;
        this._inputStart = -1;
        this._inputLen = 0;
        this._resultString = str;
        this._resultArray = null;
        if (this._hasSegments) {
            clearSegments();
        }
        this._currentSize = 0;
    }

    public String setCurrentAndReturn(int i3) {
        this._currentSize = i3;
        if (this._segmentSize > 0) {
            return contentsAsString();
        }
        String str = i3 == 0 ? "" : new String(this._currentSegment, 0, i3);
        this._resultString = str;
        return str;
    }

    public void setCurrentLength(int i3) {
        this._currentSize = i3;
    }

    public int size() {
        if (this._inputStart >= 0) {
            return this._inputLen;
        }
        char[] cArr = this._resultArray;
        if (cArr != null) {
            return cArr.length;
        }
        String str = this._resultString;
        return str != null ? str.length() : this._segmentSize + this._currentSize;
    }

    public String toString() {
        return contentsAsString();
    }

    public double contentsAsDouble(boolean z2) throws NumberFormatException {
        return NumberInput.parseDouble(contentsAsString(), z2);
    }

    public float contentsAsFloat(boolean z2) throws NumberFormatException {
        return NumberInput.parseFloat(contentsAsString(), z2);
    }

    public TextBuffer(BufferRecycler bufferRecycler, char[] cArr) {
        this._allocator = bufferRecycler;
        this._currentSegment = cArr;
        this._currentSize = cArr.length;
        this._inputStart = -1;
    }

    public char[] expandCurrentSegment(int i3) {
        char[] cArr = this._currentSegment;
        if (cArr.length >= i3) {
            return cArr;
        }
        char[] copyOf = Arrays.copyOf(cArr, i3);
        this._currentSegment = copyOf;
        return copyOf;
    }

    public void append(char[] cArr, int i3, int i4) {
        if (this._inputStart >= 0) {
            unshare(i4);
        }
        this._resultString = null;
        this._resultArray = null;
        char[] cArr2 = this._currentSegment;
        int length = cArr2.length;
        int i5 = this._currentSize;
        int i6 = length - i5;
        if (i6 >= i4) {
            System.arraycopy(cArr, i3, cArr2, i5, i4);
            this._currentSize += i4;
            return;
        }
        if (i6 > 0) {
            System.arraycopy(cArr, i3, cArr2, i5, i6);
            i3 += i6;
            i4 -= i6;
        }
        do {
            expand(i4);
            int min = Math.min(this._currentSegment.length, i4);
            System.arraycopy(cArr, i3, this._currentSegment, 0, min);
            this._currentSize += min;
            i3 += min;
            i4 -= min;
        } while (i4 > 0);
    }

    public void resetWithCopy(String str, int i3, int i4) {
        this._inputBuffer = null;
        this._inputStart = -1;
        this._inputLen = 0;
        this._resultString = null;
        this._resultArray = null;
        if (this._hasSegments) {
            clearSegments();
        } else if (this._currentSegment == null) {
            this._currentSegment = buf(i4);
        }
        this._segmentSize = 0;
        this._currentSize = 0;
        append(str, i3, i4);
    }

    public void append(String str, int i3, int i4) {
        if (this._inputStart >= 0) {
            unshare(i4);
        }
        this._resultString = null;
        this._resultArray = null;
        char[] cArr = this._currentSegment;
        int length = cArr.length;
        int i5 = this._currentSize;
        int i6 = length - i5;
        if (i6 >= i4) {
            str.getChars(i3, i3 + i4, cArr, i5);
            this._currentSize += i4;
            return;
        }
        if (i6 > 0) {
            int i7 = i3 + i6;
            str.getChars(i3, i7, cArr, i5);
            i4 -= i6;
            i3 = i7;
        }
        while (true) {
            expand(i4);
            int min = Math.min(this._currentSegment.length, i4);
            int i8 = i3 + min;
            str.getChars(i3, i8, this._currentSegment, 0);
            this._currentSize += min;
            i4 -= min;
            if (i4 > 0) {
                i3 = i8;
            } else {
                return;
            }
        }
    }
}
