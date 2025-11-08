package com.google.crypto.tink.shaded.protobuf;

import java.nio.ByteBuffer;

@CheckReturnValue
abstract class BufferAllocator {
    private static final BufferAllocator UNPOOLED = new BufferAllocator() {
        public AllocatedBuffer allocateDirectBuffer(int i3) {
            return AllocatedBuffer.wrap(ByteBuffer.allocateDirect(i3));
        }

        public AllocatedBuffer allocateHeapBuffer(int i3) {
            return AllocatedBuffer.wrap(new byte[i3]);
        }
    };

    public static BufferAllocator unpooled() {
        return UNPOOLED;
    }

    public abstract AllocatedBuffer allocateDirectBuffer(int i3);

    public abstract AllocatedBuffer allocateHeapBuffer(int i3);
}
