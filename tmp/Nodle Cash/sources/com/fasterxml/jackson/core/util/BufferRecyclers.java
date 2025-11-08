package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.io.JsonStringEncoder;
import java.lang.ref.SoftReference;

public class BufferRecyclers {
    public static final String SYSTEM_PROPERTY_TRACK_REUSABLE_BUFFERS = "com.fasterxml.jackson.core.util.BufferRecyclers.trackReusableBuffers";
    private static final ThreadLocalBufferManager _bufferRecyclerTracker;
    protected static final ThreadLocal<SoftReference<BufferRecycler>> _recyclerRef = new ThreadLocal<>();

    static {
        boolean z2;
        try {
            z2 = "true".equals(System.getProperty(SYSTEM_PROPERTY_TRACK_REUSABLE_BUFFERS));
        } catch (SecurityException unused) {
            z2 = false;
        }
        _bufferRecyclerTracker = z2 ? ThreadLocalBufferManager.instance() : null;
    }

    @Deprecated
    public static byte[] encodeAsUTF8(String str) {
        return JsonStringEncoder.getInstance().encodeAsUTF8(str);
    }

    public static BufferRecycler getBufferRecycler() {
        ThreadLocal<SoftReference<BufferRecycler>> threadLocal = _recyclerRef;
        SoftReference softReference = threadLocal.get();
        BufferRecycler bufferRecycler = softReference == null ? null : (BufferRecycler) softReference.get();
        if (bufferRecycler == null) {
            bufferRecycler = new BufferRecycler();
            ThreadLocalBufferManager threadLocalBufferManager = _bufferRecyclerTracker;
            threadLocal.set(threadLocalBufferManager != null ? threadLocalBufferManager.wrapAndTrack(bufferRecycler) : new SoftReference<>(bufferRecycler));
        }
        return bufferRecycler;
    }

    @Deprecated
    public static JsonStringEncoder getJsonStringEncoder() {
        return JsonStringEncoder.getInstance();
    }

    @Deprecated
    public static char[] quoteAsJsonText(String str) {
        return JsonStringEncoder.getInstance().quoteAsString(str);
    }

    @Deprecated
    public static byte[] quoteAsJsonUTF8(String str) {
        return JsonStringEncoder.getInstance().quoteAsUTF8(str);
    }

    public static int releaseBuffers() {
        ThreadLocalBufferManager threadLocalBufferManager = _bufferRecyclerTracker;
        if (threadLocalBufferManager != null) {
            return threadLocalBufferManager.releaseBuffers();
        }
        return -1;
    }

    @Deprecated
    public static void quoteAsJsonText(CharSequence charSequence, StringBuilder sb) {
        JsonStringEncoder.getInstance().quoteAsString(charSequence, sb);
    }
}
