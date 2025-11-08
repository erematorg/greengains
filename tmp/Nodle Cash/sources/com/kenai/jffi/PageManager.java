package com.kenai.jffi;

import androidx.collection.SieveCacheKt;
import com.kenai.jffi.Platform;

public abstract class PageManager {
    public static final int PROT_EXEC = 4;
    public static final int PROT_READ = 1;
    public static final int PROT_WRITE = 2;
    private final Foreign foreign = Foreign.getInstance();
    private int pageSize;

    public static final class SingletonHolder {
        public static final PageManager INSTANCE = (Platform.getPlatform().getOS() == Platform.OS.WINDOWS ? new Windows() : new Unix());

        private SingletonHolder() {
        }
    }

    public static final class Unix extends PageManager {
        public long allocatePages(int i3, int i4) {
            long mmap = Foreign.mmap(0, pageSize() * ((long) i3), i4, 258, -1, 0);
            if (mmap != -1) {
                return mmap;
            }
            return 0;
        }

        public void freePages(long j2, int i3) {
            Foreign.munmap(j2, pageSize() * ((long) i3));
        }

        public void protectPages(long j2, int i3, int i4) {
            Foreign.mprotect(j2, pageSize() * ((long) i3), i4);
        }
    }

    public static final class Windows extends PageManager {
        private static int w32prot(int i3) {
            int i4;
            if ((i3 & 3) == 3) {
                i4 = 4;
            } else {
                i4 = 1;
                if ((i3 & 1) == 1) {
                    i4 = 2;
                }
            }
            return (i3 & 4) == 4 ? i4 << 4 : i4;
        }

        public long allocatePages(int i3, int i4) {
            return Foreign.VirtualAlloc(0, ((int) pageSize()) * i3, 12288, w32prot(i4));
        }

        public void freePages(long j2, int i3) {
            Foreign.VirtualFree(j2, 0, 32768);
        }

        public void protectPages(long j2, int i3, int i4) {
            Foreign.VirtualProtect(j2, ((int) pageSize()) * i3, w32prot(i4));
        }
    }

    private long calculatePageSize() {
        long pageSize2 = Foreign.pageSize();
        if (pageSize2 >= SieveCacheKt.NodeLinkMask) {
            return pageSize2;
        }
        int i3 = (int) pageSize2;
        this.pageSize = i3;
        return (long) i3;
    }

    public static PageManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public abstract long allocatePages(int i3, int i4);

    public abstract void freePages(long j2, int i3);

    public final long pageSize() {
        int i3 = this.pageSize;
        return i3 != 0 ? (long) i3 : calculatePageSize();
    }

    public abstract void protectPages(long j2, int i3, int i4);
}
