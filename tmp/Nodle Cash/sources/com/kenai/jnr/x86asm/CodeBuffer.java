package com.kenai.jnr.x86asm;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

@Deprecated
final class CodeBuffer {
    private ByteBuffer buf = ByteBuffer.allocate(128).order(ByteOrder.LITTLE_ENDIAN);

    public int capacity() {
        return this.buf.capacity();
    }

    public final void copyTo(ByteBuffer byteBuffer) {
        ByteBuffer duplicate = this.buf.duplicate();
        duplicate.flip();
        byteBuffer.put(duplicate);
    }

    public final void emitByte(byte b3) {
        this.buf.put(b3);
    }

    public final void emitDWord(int i3) {
        this.buf.putInt(i3);
    }

    public final void emitData(ByteBuffer byteBuffer, int i3) {
        ByteBuffer duplicate = byteBuffer.duplicate();
        if (duplicate.remaining() > i3) {
            duplicate.limit(duplicate.position() + i3);
        }
        this.buf.put(duplicate);
    }

    public final void emitQWord(long j2) {
        this.buf.putLong(j2);
    }

    public final void emitWord(short s3) {
        this.buf.putShort(s3);
    }

    public final void ensureSpace() {
        if (this.buf.remaining() < 16) {
            grow();
        }
    }

    public final byte getByteAt(int i3) {
        return this.buf.get(i3);
    }

    public final int getDWordAt(int i3) {
        return this.buf.getInt(i3);
    }

    public final long getQWordAt(int i3) {
        return this.buf.getLong(i3);
    }

    public final short getWordAt(int i3) {
        return this.buf.getShort(i3);
    }

    public void grow() {
        ByteBuffer order = ByteBuffer.allocate(this.buf.capacity() * 2).order(ByteOrder.LITTLE_ENDIAN);
        this.buf.flip();
        order.put(this.buf);
        this.buf = order;
    }

    public final int offset() {
        return this.buf.position();
    }

    public final void setByteAt(int i3, byte b3) {
        this.buf.put(i3, b3);
    }

    public final void setDWordAt(int i3, int i4) {
        this.buf.putInt(i3, i4);
    }

    public final void setQWordAt(int i3, long j2) {
        this.buf.putLong(i3, j2);
    }

    public final void setWordAt(int i3, short s3) {
        this.buf.putShort(i3, s3);
    }
}
