package com.kenai.jnr.x86asm;

import io.nodle.cash.substrate.SubstrateHelper;
import java.nio.ByteBuffer;

@Deprecated
final class TrampolineWriter {
    public static final int TRAMPOLINE_ADDR = 8;
    public static final int TRAMPOLINE_JMP = 6;
    public static final int TRAMPOLINE_SIZE = 14;

    public static void writeTrampoline(ByteBuffer byteBuffer, long j2) {
        byteBuffer.put((byte) -1);
        byteBuffer.put(SubstrateHelper.NODLE_SUBSTRATE_ID);
        byteBuffer.putInt(0);
        byteBuffer.putLong(j2);
    }
}
