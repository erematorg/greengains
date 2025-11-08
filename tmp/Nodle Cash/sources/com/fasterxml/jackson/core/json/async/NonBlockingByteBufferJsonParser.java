package com.fasterxml.jackson.core.json.async;

import com.fasterxml.jackson.core.async.ByteBufferFeeder;
import com.fasterxml.jackson.core.async.NonBlockingInputFeeder;
import com.fasterxml.jackson.core.base.ParserMinimalBase;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;

public class NonBlockingByteBufferJsonParser extends NonBlockingUtf8JsonParserBase implements ByteBufferFeeder {
    private ByteBuffer _inputBuffer = ByteBuffer.wrap(ParserMinimalBase.NO_BYTES);

    public NonBlockingByteBufferJsonParser(IOContext iOContext, int i3, ByteQuadsCanonicalizer byteQuadsCanonicalizer) {
        super(iOContext, i3, byteQuadsCanonicalizer);
    }

    public void feedInput(ByteBuffer byteBuffer) throws IOException {
        int i3 = this._inputPtr;
        int i4 = this._inputEnd;
        if (i3 < i4) {
            _reportError("Still have %d undecoded bytes, should not call 'feedInput'", Integer.valueOf(i4 - i3));
        }
        int position = byteBuffer.position();
        int limit = byteBuffer.limit();
        if (limit < position) {
            _reportError("Input end (%d) may not be before start (%d)", Integer.valueOf(limit), Integer.valueOf(position));
        }
        if (this._endOfInput) {
            _reportError("Already closed, can not feed more input");
        }
        this._currInputProcessed += (long) this._origBufferLen;
        this._currInputRowStart = position - (this._inputEnd - this._currInputRowStart);
        this._currBufferStart = position;
        this._inputBuffer = byteBuffer;
        this._inputPtr = position;
        this._inputEnd = limit;
        this._origBufferLen = limit - position;
    }

    public byte getByteFromBuffer(int i3) {
        return this._inputBuffer.get(i3);
    }

    public byte getNextSignedByteFromBuffer() {
        ByteBuffer byteBuffer = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        return byteBuffer.get(i3);
    }

    public int getNextUnsignedByteFromBuffer() {
        ByteBuffer byteBuffer = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        return byteBuffer.get(i3) & 255;
    }

    public NonBlockingInputFeeder getNonBlockingInputFeeder() {
        return this;
    }

    public int releaseBuffered(OutputStream outputStream) throws IOException {
        int i3 = this._inputEnd - this._inputPtr;
        if (i3 > 0) {
            Channels.newChannel(outputStream).write(this._inputBuffer);
        }
        return i3;
    }
}
