package com.uber.h3core;

import java.io.File;

public final class a {

    /* renamed from: a  reason: collision with root package name */
    public static volatile File f7651a;

    /* renamed from: com.uber.h3core.a$a  reason: collision with other inner class name */
    public enum C0067a {
        ANDROID(".so"),
        DARWIN(".dylib"),
        WINDOWS(".dll"),
        LINUX(".so");
        

        /* renamed from: a  reason: collision with root package name */
        public final String f7657a;

        /* access modifiers changed from: public */
        C0067a(String str) {
            this.f7657a = str;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0094, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0099, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        r6.addSuppressed(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x009d, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00a9, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00aa, code lost:
        if (r1 != null) goto L_0x00ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00b0, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        r6.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00b4, code lost:
        throw r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized com.uber.h3core.NativeMethods a(com.uber.h3core.a.C0067a r6, java.lang.String r7) {
        /*
            java.lang.String r0 = "No native resource found at "
            java.lang.String r1 = "/"
            java.lang.String r2 = "libh3-java"
            java.lang.Class<com.uber.h3core.a> r3 = com.uber.h3core.a.class
            monitor-enter(r3)
            java.io.File r4 = f7651a     // Catch:{ all -> 0x008f }
            if (r4 != 0) goto L_0x00b5
            java.lang.String r4 = r6.name()     // Catch:{ all -> 0x008f }
            java.lang.String r4 = r4.toLowerCase()     // Catch:{ all -> 0x008f }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x008f }
            r5.<init>()     // Catch:{ all -> 0x008f }
            r5.append(r4)     // Catch:{ all -> 0x008f }
            java.lang.String r4 = "-"
            r5.append(r4)     // Catch:{ all -> 0x008f }
            r5.append(r7)     // Catch:{ all -> 0x008f }
            java.lang.String r7 = r5.toString()     // Catch:{ all -> 0x008f }
            java.lang.String r4 = r6.f7657a     // Catch:{ all -> 0x008f }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x008f }
            r5.<init>(r2)     // Catch:{ all -> 0x008f }
            r5.append(r4)     // Catch:{ all -> 0x008f }
            java.lang.String r2 = r5.toString()     // Catch:{ all -> 0x008f }
            java.lang.String r6 = r6.f7657a     // Catch:{ all -> 0x008f }
            java.lang.String r4 = "libh3-java"
            java.io.File r6 = java.io.File.createTempFile(r4, r6)     // Catch:{ all -> 0x008f }
            r6.deleteOnExit()     // Catch:{ all -> 0x008f }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x008f }
            r4.<init>(r1)     // Catch:{ all -> 0x008f }
            r4.append(r7)     // Catch:{ all -> 0x008f }
            java.lang.String r7 = "/"
            r4.append(r7)     // Catch:{ all -> 0x008f }
            r4.append(r2)     // Catch:{ all -> 0x008f }
            java.lang.String r7 = r4.toString()     // Catch:{ all -> 0x008f }
            r1 = 1
            r6.setReadable(r1)     // Catch:{ all -> 0x008f }
            r6.setWritable(r1, r1)     // Catch:{ all -> 0x008f }
            r6.setExecutable(r1, r1)     // Catch:{ all -> 0x008f }
            java.lang.Class<com.uber.h3core.a> r1 = com.uber.h3core.a.class
            java.io.InputStream r1 = r1.getResourceAsStream(r7)     // Catch:{ all -> 0x008f }
            if (r1 == 0) goto L_0x009e
            java.io.FileOutputStream r7 = new java.io.FileOutputStream     // Catch:{ all -> 0x0091 }
            r7.<init>(r6)     // Catch:{ all -> 0x0091 }
            r0 = 4096(0x1000, float:5.74E-42)
            byte[] r0 = new byte[r0]     // Catch:{ all -> 0x007d }
        L_0x0071:
            int r2 = r1.read(r0)     // Catch:{ all -> 0x007d }
            r4 = -1
            if (r2 == r4) goto L_0x007f
            r4 = 0
            r7.write(r0, r4, r2)     // Catch:{ all -> 0x007d }
            goto L_0x0071
        L_0x007d:
            r6 = move-exception
            goto L_0x0093
        L_0x007f:
            r7.close()     // Catch:{ all -> 0x0091 }
            r1.close()     // Catch:{ all -> 0x008f }
            java.lang.String r7 = r6.getCanonicalPath()     // Catch:{ all -> 0x008f }
            java.lang.System.load(r7)     // Catch:{ all -> 0x008f }
            f7651a = r6     // Catch:{ all -> 0x008f }
            goto L_0x00b5
        L_0x008f:
            r6 = move-exception
            goto L_0x00bc
        L_0x0091:
            r6 = move-exception
            goto L_0x00a8
        L_0x0093:
            throw r6     // Catch:{ all -> 0x0094 }
        L_0x0094:
            r0 = move-exception
            r7.close()     // Catch:{ all -> 0x0099 }
            goto L_0x009d
        L_0x0099:
            r7 = move-exception
            r6.addSuppressed(r7)     // Catch:{ all -> 0x0091 }
        L_0x009d:
            throw r0     // Catch:{ all -> 0x0091 }
        L_0x009e:
            java.lang.UnsatisfiedLinkError r6 = new java.lang.UnsatisfiedLinkError     // Catch:{ all -> 0x0091 }
            java.lang.String r7 = r0.concat(r7)     // Catch:{ all -> 0x0091 }
            r6.<init>(r7)     // Catch:{ all -> 0x0091 }
            throw r6     // Catch:{ all -> 0x0091 }
        L_0x00a8:
            throw r6     // Catch:{ all -> 0x00a9 }
        L_0x00a9:
            r7 = move-exception
            if (r1 == 0) goto L_0x00b4
            r1.close()     // Catch:{ all -> 0x00b0 }
            goto L_0x00b4
        L_0x00b0:
            r0 = move-exception
            r6.addSuppressed(r0)     // Catch:{ all -> 0x008f }
        L_0x00b4:
            throw r7     // Catch:{ all -> 0x008f }
        L_0x00b5:
            com.uber.h3core.NativeMethods r6 = new com.uber.h3core.NativeMethods     // Catch:{ all -> 0x008f }
            r6.<init>()     // Catch:{ all -> 0x008f }
            monitor-exit(r3)
            return r6
        L_0x00bc:
            monitor-exit(r3)     // Catch:{ all -> 0x008f }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uber.h3core.a.a(com.uber.h3core.a$a, java.lang.String):com.uber.h3core.NativeMethods");
    }
}
