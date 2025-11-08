package com.kenai.jffi;

import androidx.core.os.EnvironmentCompat;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public final class Library {
    public static final int GLOBAL = 8;
    public static final int LAZY = 1;
    public static final int LOCAL = 4;
    public static final int NOW = 2;
    private static final AtomicIntegerFieldUpdater<Library> UPDATER = AtomicIntegerFieldUpdater.newUpdater(Library.class, "disposed");
    private static final Map<String, WeakReference<Library>> cache = new ConcurrentHashMap();
    private static final ThreadLocal<String> lastError = new ThreadLocal<>();
    private static final Object lock = new Object();
    private volatile int disposed;
    private final Foreign foreign;
    private final long handle;
    private final String name;

    public static final class DefaultLibrary {
        /* access modifiers changed from: private */
        public static final Library INSTANCE = Library.openLibrary((String) null, 9);

        private DefaultLibrary() {
        }
    }

    private Library(Foreign foreign2, String str, long j2) {
        this.foreign = foreign2;
        this.name = str;
        this.handle = j2;
    }

    private static long dlopen(Foreign foreign2, String str, int i3) {
        try {
            return Foreign.dlopen(str, i3);
        } catch (UnsatisfiedLinkError e3) {
            lastError.set(e3.getMessage());
            return 0;
        }
    }

    public static final Library getCachedInstance(String str, int i3) {
        if (str == null) {
            return getDefault();
        }
        Map<String, WeakReference<Library>> map = cache;
        WeakReference weakReference = map.get(str);
        Library library = weakReference != null ? (Library) weakReference.get() : null;
        if (library != null) {
            return library;
        }
        Library openLibrary = openLibrary(str, i3);
        if (openLibrary == null) {
            return null;
        }
        map.put(str, new WeakReference(openLibrary));
        return openLibrary;
    }

    public static final Library getDefault() {
        return DefaultLibrary.INSTANCE;
    }

    public static final String getLastError() {
        String str = lastError.get();
        return str != null ? str : EnvironmentCompat.MEDIA_UNKNOWN;
    }

    public static final Library openLibrary(String str, int i3) {
        if (i3 == 0) {
            i3 = 5;
        }
        Foreign instance = Foreign.getInstance();
        long dlopen = dlopen(instance, str, i3);
        if (dlopen != 0) {
            return new Library(instance, str, dlopen);
        }
        return null;
    }

    public void finalize() throws Throwable {
        try {
            if (UPDATER.getAndSet(this, 1) == 0) {
                long j2 = this.handle;
                if (j2 != 0) {
                    Foreign.dlclose(j2);
                }
            }
        } finally {
            super.finalize();
        }
    }

    public final long getSymbolAddress(String str) {
        try {
            return Foreign.dlsym(this.handle, str);
        } catch (UnsatisfiedLinkError unused) {
            lastError.set(Foreign.dlerror());
            return 0;
        }
    }
}
