package com.google.android.material.color;

import androidx.annotation.RequiresApi;

@RequiresApi(30)
final class ColorResourcesLoaderCreator {
    private static final String TAG = "ColorResLoaderCreator";

    private ColorResourcesLoaderCreator() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0031, code lost:
        r5 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0053, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x005c, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x005d, code lost:
        if (r5 != null) goto L_0x005f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0063, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        r3.addSuppressed(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x006c, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        r5.addSuppressed(r1);
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:9:0x0025, B:19:0x0038, B:21:0x003f, B:34:0x005f, B:40:0x0068] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0075 A[SYNTHETIC, Splitter:B:49:0x0075] */
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.content.res.loader.ResourcesLoader create(@androidx.annotation.NonNull android.content.Context r5, @androidx.annotation.NonNull java.util.Map<java.lang.Integer, java.lang.Integer> r6) {
        /*
            java.lang.String r0 = "ColorResLoaderCreator"
            java.lang.String r1 = "Table created, length: "
            r2 = 0
            byte[] r5 = com.google.android.material.color.ColorResourcesTableCreator.create(r5, r6)     // Catch:{ Exception -> 0x002e }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x002e }
            r6.<init>(r1)     // Catch:{ Exception -> 0x002e }
            int r1 = r5.length     // Catch:{ Exception -> 0x002e }
            r6.append(r1)     // Catch:{ Exception -> 0x002e }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x002e }
            android.util.Log.i(r0, r6)     // Catch:{ Exception -> 0x002e }
            int r6 = r5.length     // Catch:{ Exception -> 0x002e }
            if (r6 != 0) goto L_0x001d
            return r2
        L_0x001d:
            java.io.FileDescriptor r6 = android.system.Os.memfd_create("temp.arsc", 0)     // Catch:{ all -> 0x0071 }
            if (r6 != 0) goto L_0x0033
            java.lang.String r5 = "Cannot create memory file descriptor."
            android.util.Log.w(r0, r5)     // Catch:{ all -> 0x0031 }
            if (r6 == 0) goto L_0x0030
            android.system.Os.close(r6)     // Catch:{ Exception -> 0x002e }
            goto L_0x0030
        L_0x002e:
            r5 = move-exception
            goto L_0x0079
        L_0x0030:
            return r2
        L_0x0031:
            r5 = move-exception
            goto L_0x0073
        L_0x0033:
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ all -> 0x0031 }
            r1.<init>(r6)     // Catch:{ all -> 0x0031 }
            r1.write(r5)     // Catch:{ all -> 0x0053 }
            android.os.ParcelFileDescriptor r5 = android.os.ParcelFileDescriptor.dup(r6)     // Catch:{ all -> 0x0053 }
            com.appsamurai.storyly.exoplayer2.hls.b.i()     // Catch:{ all -> 0x005c }
            android.content.res.loader.ResourcesLoader r3 = com.appsamurai.storyly.exoplayer2.hls.b.a()     // Catch:{ all -> 0x005c }
            android.content.res.loader.ResourcesProvider r4 = android.content.res.loader.ResourcesProvider.loadFromTable(r5, (android.content.res.loader.AssetsProvider) null)     // Catch:{ all -> 0x005c }
            r3.addProvider(r4)     // Catch:{ all -> 0x005c }
            if (r5 == 0) goto L_0x0055
            r5.close()     // Catch:{ all -> 0x0053 }
            goto L_0x0055
        L_0x0053:
            r5 = move-exception
            goto L_0x0068
        L_0x0055:
            r1.close()     // Catch:{ all -> 0x0031 }
            android.system.Os.close(r6)     // Catch:{ Exception -> 0x002e }
            return r3
        L_0x005c:
            r3 = move-exception
            if (r5 == 0) goto L_0x0067
            r5.close()     // Catch:{ all -> 0x0063 }
            goto L_0x0067
        L_0x0063:
            r5 = move-exception
            r3.addSuppressed(r5)     // Catch:{ all -> 0x0053 }
        L_0x0067:
            throw r3     // Catch:{ all -> 0x0053 }
        L_0x0068:
            r1.close()     // Catch:{ all -> 0x006c }
            goto L_0x0070
        L_0x006c:
            r1 = move-exception
            r5.addSuppressed(r1)     // Catch:{ all -> 0x0031 }
        L_0x0070:
            throw r5     // Catch:{ all -> 0x0031 }
        L_0x0071:
            r5 = move-exception
            r6 = r2
        L_0x0073:
            if (r6 == 0) goto L_0x0078
            android.system.Os.close(r6)     // Catch:{ Exception -> 0x002e }
        L_0x0078:
            throw r5     // Catch:{ Exception -> 0x002e }
        L_0x0079:
            java.lang.String r6 = "Failed to create the ColorResourcesTableCreator."
            android.util.Log.e(r0, r6, r5)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.color.ColorResourcesLoaderCreator.create(android.content.Context, java.util.Map):android.content.res.loader.ResourcesLoader");
    }
}
