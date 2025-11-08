package com.google.firebase.installations;

import android.util.Log;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

class CrossProcessLock {
    private static final String TAG = "CrossProcessLock";
    private final FileChannel channel;
    private final FileLock lock;

    private CrossProcessLock(FileChannel fileChannel, FileLock fileLock) {
        this.channel = fileChannel;
        this.lock = fileLock;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0030 A[SYNTHETIC, Splitter:B:15:0x0030] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0035 A[SYNTHETIC, Splitter:B:19:0x0035] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.firebase.installations.CrossProcessLock acquire(android.content.Context r4, java.lang.String r5) {
        /*
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch:{ IOException | Error | OverlappingFileLockException -> 0x0024 }
            java.io.File r4 = r4.getFilesDir()     // Catch:{ IOException | Error | OverlappingFileLockException -> 0x0024 }
            r1.<init>(r4, r5)     // Catch:{ IOException | Error | OverlappingFileLockException -> 0x0024 }
            java.io.RandomAccessFile r4 = new java.io.RandomAccessFile     // Catch:{ IOException | Error | OverlappingFileLockException -> 0x0024 }
            java.lang.String r5 = "rw"
            r4.<init>(r1, r5)     // Catch:{ IOException | Error | OverlappingFileLockException -> 0x0024 }
            java.nio.channels.FileChannel r4 = r4.getChannel()     // Catch:{ IOException | Error | OverlappingFileLockException -> 0x0024 }
            java.nio.channels.FileLock r5 = r4.lock()     // Catch:{ IOException | Error | OverlappingFileLockException -> 0x0021 }
            com.google.firebase.installations.CrossProcessLock r1 = new com.google.firebase.installations.CrossProcessLock     // Catch:{ IOException | Error | OverlappingFileLockException -> 0x001f }
            r1.<init>(r4, r5)     // Catch:{ IOException | Error | OverlappingFileLockException -> 0x001f }
            return r1
        L_0x001f:
            r1 = move-exception
            goto L_0x0027
        L_0x0021:
            r1 = move-exception
            r5 = r0
            goto L_0x0027
        L_0x0024:
            r1 = move-exception
            r4 = r0
            r5 = r4
        L_0x0027:
            java.lang.String r2 = "CrossProcessLock"
            java.lang.String r3 = "encountered error while creating and acquiring the lock, ignoring"
            android.util.Log.e(r2, r3, r1)
            if (r5 == 0) goto L_0x0033
            r5.release()     // Catch:{ IOException -> 0x0033 }
        L_0x0033:
            if (r4 == 0) goto L_0x0038
            r4.close()     // Catch:{ IOException -> 0x0038 }
        L_0x0038:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.installations.CrossProcessLock.acquire(android.content.Context, java.lang.String):com.google.firebase.installations.CrossProcessLock");
    }

    public void releaseAndClose() {
        try {
            this.lock.release();
            this.channel.close();
        } catch (IOException e3) {
            Log.e(TAG, "encountered error while releasing, ignoring", e3);
        }
    }
}
