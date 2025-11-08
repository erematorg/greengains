package com.google.android.gms.common.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.concurrent.ConcurrentHashMap;

@KeepForSdk
@Deprecated
public class LibraryVersion {
    private static final GmsLogger zza = new GmsLogger("LibraryVersion", "");
    private static final LibraryVersion zzb = new LibraryVersion();
    private final ConcurrentHashMap zzc = new ConcurrentHashMap();

    @NonNull
    @KeepForSdk
    public static LibraryVersion getInstance() {
        return zzb;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v0, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: java.io.Closeable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v0, resolved type: java.io.Closeable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v6, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v7, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v10, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v12, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r2v1, types: [java.io.Closeable] */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00ac  */
    @androidx.annotation.NonNull
    @com.google.android.gms.common.annotation.KeepForSdk
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getVersion(@androidx.annotation.NonNull java.lang.String r9) {
        /*
            r8 = this;
            java.lang.String r0 = "LibraryVersion"
            java.lang.String r1 = "Failed to get app version for libraryName: "
            java.lang.String r2 = "/"
            java.lang.String r3 = "Please provide a valid libraryName"
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r9, r3)
            java.util.concurrent.ConcurrentHashMap r3 = r8.zzc
            boolean r3 = r3.containsKey(r9)
            if (r3 == 0) goto L_0x001c
            java.util.concurrent.ConcurrentHashMap r8 = r8.zzc
            java.lang.Object r8 = r8.get(r9)
            java.lang.String r8 = (java.lang.String) r8
            return r8
        L_0x001c:
            java.util.Properties r3 = new java.util.Properties
            r3.<init>()
            r4 = 0
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x007a }
            r5.<init>(r2)     // Catch:{ IOException -> 0x007a }
            r5.append(r9)     // Catch:{ IOException -> 0x007a }
            java.lang.String r2 = ".properties"
            r5.append(r2)     // Catch:{ IOException -> 0x007a }
            java.lang.String r2 = r5.toString()     // Catch:{ IOException -> 0x007a }
            java.lang.Class<com.google.android.gms.common.internal.LibraryVersion> r5 = com.google.android.gms.common.internal.LibraryVersion.class
            java.io.InputStream r2 = r5.getResourceAsStream(r2)     // Catch:{ IOException -> 0x007a }
            if (r2 == 0) goto L_0x0066
            r3.load(r2)     // Catch:{ IOException -> 0x0061, all -> 0x005f }
            java.lang.String r5 = "version"
            java.lang.String r4 = r3.getProperty(r5, r4)     // Catch:{ IOException -> 0x0061, all -> 0x005f }
            com.google.android.gms.common.internal.GmsLogger r3 = zza     // Catch:{ IOException -> 0x0061, all -> 0x005f }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0061, all -> 0x005f }
            r5.<init>()     // Catch:{ IOException -> 0x0061, all -> 0x005f }
            r5.append(r9)     // Catch:{ IOException -> 0x0061, all -> 0x005f }
            java.lang.String r6 = " version is "
            r5.append(r6)     // Catch:{ IOException -> 0x0061, all -> 0x005f }
            r5.append(r4)     // Catch:{ IOException -> 0x0061, all -> 0x005f }
            java.lang.String r5 = r5.toString()     // Catch:{ IOException -> 0x0061, all -> 0x005f }
            r3.v(r0, r5)     // Catch:{ IOException -> 0x0061, all -> 0x005f }
            goto L_0x0094
        L_0x005f:
            r8 = move-exception
            goto L_0x0078
        L_0x0061:
            r3 = move-exception
            r7 = r4
            r4 = r2
            r2 = r7
            goto L_0x0080
        L_0x0066:
            com.google.android.gms.common.internal.GmsLogger r3 = zza     // Catch:{ IOException -> 0x0061, all -> 0x005f }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0061, all -> 0x005f }
            r5.<init>(r1)     // Catch:{ IOException -> 0x0061, all -> 0x005f }
            r5.append(r9)     // Catch:{ IOException -> 0x0061, all -> 0x005f }
            java.lang.String r5 = r5.toString()     // Catch:{ IOException -> 0x0061, all -> 0x005f }
            r3.w(r0, r5)     // Catch:{ IOException -> 0x0061, all -> 0x005f }
            goto L_0x0094
        L_0x0078:
            r4 = r2
            goto L_0x00aa
        L_0x007a:
            r2 = move-exception
            r3 = r2
            goto L_0x007f
        L_0x007d:
            r8 = move-exception
            goto L_0x00aa
        L_0x007f:
            r2 = r4
        L_0x0080:
            com.google.android.gms.common.internal.GmsLogger r5 = zza     // Catch:{ all -> 0x007d }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x007d }
            r6.<init>(r1)     // Catch:{ all -> 0x007d }
            r6.append(r9)     // Catch:{ all -> 0x007d }
            java.lang.String r1 = r6.toString()     // Catch:{ all -> 0x007d }
            r5.e(r0, r1, r3)     // Catch:{ all -> 0x007d }
            r7 = r4
            r4 = r2
            r2 = r7
        L_0x0094:
            if (r2 == 0) goto L_0x0099
            com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r2)
        L_0x0099:
            if (r4 != 0) goto L_0x00a4
            com.google.android.gms.common.internal.GmsLogger r1 = zza
            java.lang.String r2 = ".properties file is dropped during release process. Failure to read app version is expected during Google internal testing where locally-built libraries are used"
            r1.d(r0, r2)
            java.lang.String r4 = "UNKNOWN"
        L_0x00a4:
            java.util.concurrent.ConcurrentHashMap r8 = r8.zzc
            r8.put(r9, r4)
            return r4
        L_0x00aa:
            if (r4 == 0) goto L_0x00af
            com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r4)
        L_0x00af:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.LibraryVersion.getVersion(java.lang.String):java.lang.String");
    }
}
