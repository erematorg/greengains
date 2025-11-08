package com.kenai.jffi;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public final class NativeMethods {
    private static final Map<Class, NativeMethods> registeredMethods = new WeakHashMap();
    private final ResourceHolder memory;

    public static final class ResourceHolder {
        private final long memory;
        private final MemoryIO mm;

        public ResourceHolder(MemoryIO memoryIO, long j2) {
            this.mm = memoryIO;
            this.memory = j2;
        }

        public void finalize() throws Throwable {
            try {
                this.mm.freeMemory(this.memory);
            } catch (Throwable th) {
                super.finalize();
                throw th;
            }
            super.finalize();
        }
    }

    private NativeMethods(ResourceHolder resourceHolder) {
        this.memory = resourceHolder;
    }

    public static final synchronized void register(Class cls, List<NativeMethod> list) {
        Class<NativeMethods> cls2;
        int i3;
        Class cls3 = cls;
        Class<NativeMethods> cls4 = NativeMethods.class;
        synchronized (cls4) {
            try {
                Iterator<NativeMethod> it = list.iterator();
                int i4 = 0;
                int i5 = 0;
                while (true) {
                    i3 = 1;
                    if (!it.hasNext()) {
                        break;
                    }
                    NativeMethod next = it.next();
                    i5 = next.signature.getBytes().length + 1 + next.name.getBytes().length + 1 + i5;
                }
                int addressSize = Platform.getPlatform().addressSize() / 8;
                MemoryIO instance = MemoryIO.getInstance();
                int size = list.size() * 3 * addressSize;
                long allocateMemory = instance.allocateMemory((long) (i5 + size), true);
                if (allocateMemory != 0) {
                    NativeMethods nativeMethods = new NativeMethods(new ResourceHolder(instance, allocateMemory));
                    Iterator<NativeMethod> it2 = list.iterator();
                    while (it2.hasNext()) {
                        NativeMethod next2 = it2.next();
                        byte[] bytes = next2.name.getBytes();
                        long j2 = ((long) size) + allocateMemory;
                        long j3 = j2;
                        int length = bytes.length + i3 + size;
                        Iterator<NativeMethod> it3 = it2;
                        NativeMethod nativeMethod = next2;
                        instance.putZeroTerminatedByteArray(j2, bytes, 0, bytes.length);
                        byte[] bytes2 = nativeMethod.signature.getBytes();
                        long j4 = allocateMemory + ((long) length);
                        int length2 = length + bytes2.length + 1;
                        cls2 = cls4;
                        long j5 = j4;
                        try {
                            instance.putZeroTerminatedByteArray(j4, bytes2, 0, bytes2.length);
                            instance.putAddress(((long) i4) + allocateMemory, j3);
                            int i6 = i4 + addressSize;
                            instance.putAddress(((long) i6) + allocateMemory, j5);
                            int i7 = i6 + addressSize;
                            instance.putAddress(((long) i7) + allocateMemory, nativeMethod.function);
                            i4 = i7 + addressSize;
                            Class cls5 = cls;
                            size = length2;
                            it2 = it3;
                            i3 = 1;
                            cls4 = cls2;
                        } catch (Throwable th) {
                            th = th;
                            throw th;
                        }
                    }
                    cls2 = cls4;
                    Class cls6 = cls;
                    if (Foreign.getInstance().registerNatives(cls6, allocateMemory, list.size()) == 0) {
                        registeredMethods.put(cls6, nativeMethods);
                        return;
                    }
                    throw new RuntimeException("failed to register native methods");
                }
                Class<NativeMethods> cls7 = cls4;
                throw new OutOfMemoryError("could not allocate native memory");
            } catch (Throwable th2) {
                th = th2;
                cls2 = cls4;
                throw th;
            }
        }
    }

    public static final synchronized void unregister(Class cls) {
        synchronized (NativeMethods.class) {
            Map<Class, NativeMethods> map = registeredMethods;
            if (!map.containsKey(cls)) {
                throw new IllegalArgumentException("methods were not registered on class via NativeMethods.register");
            } else if (Foreign.getInstance().unregisterNatives(cls) == 0) {
                map.remove(cls);
            } else {
                throw new RuntimeException("failed to unregister native methods");
            }
        }
    }
}
