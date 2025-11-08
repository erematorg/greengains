package com.fasterxml.jackson.core.json.async;

import com.fasterxml.jackson.core.async.ByteArrayFeeder;
import com.fasterxml.jackson.core.base.ParserMinimalBase;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import java.io.IOException;
import java.io.OutputStream;

public class NonBlockingJsonParser extends NonBlockingUtf8JsonParserBase implements ByteArrayFeeder {
    private byte[] _inputBuffer = ParserMinimalBase.NO_BYTES;

    public NonBlockingJsonParser(IOContext iOContext, int i3, ByteQuadsCanonicalizer byteQuadsCanonicalizer) {
        super(iOContext, i3, byteQuadsCanonicalizer);
    }

    public void feedInput(byte[] bArr, int i3, int i4) throws IOException {
        int i5 = this._inputPtr;
        int i6 = this._inputEnd;
        if (i5 < i6) {
            _reportError("Still have %d undecoded bytes, should not call 'feedInput'", Integer.valueOf(i6 - i5));
        }
        if (i4 < i3) {
            _reportError("Input end (%d) may not be before start (%d)", Integer.valueOf(i4), Integer.valueOf(i3));
        }
        if (this._endOfInput) {
            _reportError("Already closed, can not feed more input");
        }
        this._currInputProcessed += (long) this._origBufferLen;
        this._currInputRowStart = i3 - (this._inputEnd - this._currInputRowStart);
        this._currBufferStart = i3;
        this._inputBuffer = bArr;
        this._inputPtr = i3;
        this._inputEnd = i4;
        this._origBufferLen = i4 - i3;
    }

    public byte getByteFromBuffer(int i3) {
        return this._inputBuffer[i3];
    }

    public byte getNextSignedByteFromBuffer() {
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        return bArr[i3];
    }

    public int getNextUnsignedByteFromBuffer() {
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        return bArr[i3] & 255;
    }

    public ByteArrayFeeder getNonBlockingInputFeeder() {
        return this;
    }

    public int releaseBuffered(OutputStream outputStream) throws IOException {
        int i3 = this._inputEnd;
        int i4 = this._inputPtr;
        int i5 = i3 - i4;
        if (i5 > 0) {
            outputStream.write(this._inputBuffer, i4, i5);
        }
        return i5;
    }
}
