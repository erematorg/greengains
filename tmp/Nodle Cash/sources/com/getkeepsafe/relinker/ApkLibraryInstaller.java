package com.getkeepsafe.relinker;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import com.getkeepsafe.relinker.ReLinker;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ApkLibraryInstaller implements ReLinker.LibraryInstaller {
    private static final int COPY_BUFFER_SIZE = 4096;
    private static final int MAX_TRIES = 5;

    public static class ZipFileInZipEntry {
        public ZipEntry zipEntry;
        public ZipFile zipFile;

        public ZipFileInZipEntry(ZipFile zipFile2, ZipEntry zipEntry2) {
            this.zipFile = zipFile2;
            this.zipEntry = zipEntry2;
        }
    }

    private void closeSilently(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    private long copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[4096];
        long j2 = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                outputStream.flush();
                return j2;
            }
            outputStream.write(bArr, 0, read);
            j2 += (long) read;
        }
    }

    private ZipFileInZipEntry findAPKWithLibrary(Context context, String[] strArr, String str, ReLinkerInstance reLinkerInstance) {
        String[] sourceDirectories = sourceDirectories(context);
        int length = sourceDirectories.length;
        int i3 = 0;
        while (true) {
            ZipFile zipFile = null;
            if (i3 >= length) {
                return null;
            }
            String str2 = sourceDirectories[i3];
            int i4 = 0;
            while (true) {
                int i5 = i4 + 1;
                if (i4 >= 5) {
                    break;
                }
                try {
                    zipFile = new ZipFile(new File(str2), 1);
                    break;
                } catch (IOException unused) {
                    i4 = i5;
                }
            }
            if (zipFile != null) {
                int i6 = 0;
                while (true) {
                    int i7 = i6 + 1;
                    if (i6 < 5) {
                        for (String append : strArr) {
                            StringBuilder sb = new StringBuilder("lib");
                            char c3 = File.separatorChar;
                            sb.append(c3);
                            sb.append(append);
                            sb.append(c3);
                            sb.append(str);
                            String sb2 = sb.toString();
                            reLinkerInstance.log("Looking for %s in APK %s...", sb2, str2);
                            ZipEntry entry = zipFile.getEntry(sb2);
                            if (entry != null) {
                                return new ZipFileInZipEntry(zipFile, entry);
                            }
                        }
                        i6 = i7;
                    } else {
                        try {
                            zipFile.close();
                            break;
                        } catch (IOException unused2) {
                        }
                    }
                }
            }
            i3++;
        }
    }

    private String[] getSupportedABIs(Context context, String str) {
        StringBuilder sb = new StringBuilder("lib");
        char c3 = File.separatorChar;
        sb.append(c3);
        sb.append("([^\\");
        sb.append(c3);
        sb.append("]*)");
        sb.append(c3);
        sb.append(str);
        Pattern compile = Pattern.compile(sb.toString());
        HashSet hashSet = new HashSet();
        for (String file : sourceDirectories(context)) {
            try {
                Enumeration<? extends ZipEntry> entries = new ZipFile(new File(file), 1).entries();
                while (entries.hasMoreElements()) {
                    Matcher matcher = compile.matcher(((ZipEntry) entries.nextElement()).getName());
                    if (matcher.matches()) {
                        hashSet.add(matcher.group(1));
                    }
                }
            } catch (IOException unused) {
            }
        }
        return (String[]) hashSet.toArray(new String[hashSet.size()]);
    }

    private String[] sourceDirectories(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        String[] strArr = applicationInfo.splitSourceDirs;
        if (strArr == null || strArr.length == 0) {
            return new String[]{applicationInfo.sourceDir};
        }
        String[] strArr2 = new String[(strArr.length + 1)];
        strArr2[0] = applicationInfo.sourceDir;
        System.arraycopy(strArr, 0, strArr2, 1, strArr.length);
        return strArr2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: com.getkeepsafe.relinker.ApkLibraryInstaller$ZipFileInZipEntry} */
    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r0v2 */
    /* JADX WARNING: type inference failed for: r0v3, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r0v4 */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0025, code lost:
        r8 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0026, code lost:
        r0 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x009f, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:?, code lost:
        r8 = new java.lang.String[]{r8.toString()};
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:?, code lost:
        r9 = r0.zipFile;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x00b3, code lost:
        if (r9 != null) goto L_0x00b5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x00b5, code lost:
        r9.close();
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:8:0x0010, B:63:0x009a] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x00b1 A[SYNTHETIC, Splitter:B:72:0x00b1] */
    @android.annotation.SuppressLint({"SetWorldReadable"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void installLibrary(android.content.Context r9, java.lang.String[] r10, java.lang.String r11, java.io.File r12, com.getkeepsafe.relinker.ReLinkerInstance r13) {
        /*
            r8 = this;
            r0 = 0
            com.getkeepsafe.relinker.ApkLibraryInstaller$ZipFileInZipEntry r1 = r8.findAPKWithLibrary(r9, r10, r11, r13)     // Catch:{ all -> 0x00ae }
            if (r1 == 0) goto L_0x009a
            r9 = 0
            r10 = r9
        L_0x0009:
            int r2 = r10 + 1
            r3 = 5
            if (r10 >= r3) goto L_0x008d
            java.lang.String r10 = "Found %s! Extracting..."
            java.lang.Object[] r3 = new java.lang.Object[]{r11}     // Catch:{ all -> 0x0025 }
            r13.log(r10, r3)     // Catch:{ all -> 0x0025 }
            boolean r10 = r12.exists()     // Catch:{ IOException -> 0x008a }
            if (r10 != 0) goto L_0x0029
            boolean r10 = r12.createNewFile()     // Catch:{ IOException -> 0x008a }
            if (r10 != 0) goto L_0x0029
            goto L_0x008a
        L_0x0025:
            r8 = move-exception
            r0 = r1
            goto L_0x00af
        L_0x0029:
            java.util.zip.ZipFile r10 = r1.zipFile     // Catch:{ FileNotFoundException -> 0x0078, IOException -> 0x0075, all -> 0x0072 }
            java.util.zip.ZipEntry r3 = r1.zipEntry     // Catch:{ FileNotFoundException -> 0x0078, IOException -> 0x0075, all -> 0x0072 }
            java.io.InputStream r10 = r10.getInputStream(r3)     // Catch:{ FileNotFoundException -> 0x0078, IOException -> 0x0075, all -> 0x0072 }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x0070, IOException -> 0x006e, all -> 0x006b }
            r3.<init>(r12)     // Catch:{ FileNotFoundException -> 0x0070, IOException -> 0x006e, all -> 0x006b }
            long r4 = r8.copy(r10, r3)     // Catch:{ FileNotFoundException -> 0x0086, IOException -> 0x0082, all -> 0x0068 }
            java.io.FileDescriptor r6 = r3.getFD()     // Catch:{ FileNotFoundException -> 0x0086, IOException -> 0x0082, all -> 0x0068 }
            r6.sync()     // Catch:{ FileNotFoundException -> 0x0086, IOException -> 0x0082, all -> 0x0068 }
            long r6 = r12.length()     // Catch:{ FileNotFoundException -> 0x0086, IOException -> 0x0082, all -> 0x0068 }
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 == 0) goto L_0x0050
            r8.closeSilently(r10)     // Catch:{ all -> 0x0025 }
        L_0x004c:
            r8.closeSilently(r3)     // Catch:{ all -> 0x0025 }
            goto L_0x008a
        L_0x0050:
            r8.closeSilently(r10)     // Catch:{ all -> 0x0025 }
            r8.closeSilently(r3)     // Catch:{ all -> 0x0025 }
            r8 = 1
            r12.setReadable(r8, r9)     // Catch:{ all -> 0x0025 }
            r12.setExecutable(r8, r9)     // Catch:{ all -> 0x0025 }
            r12.setWritable(r8)     // Catch:{ all -> 0x0025 }
            java.util.zip.ZipFile r8 = r1.zipFile     // Catch:{ IOException -> 0x0067 }
            if (r8 == 0) goto L_0x0067
            r8.close()     // Catch:{ IOException -> 0x0067 }
        L_0x0067:
            return
        L_0x0068:
            r9 = move-exception
        L_0x0069:
            r0 = r10
            goto L_0x007b
        L_0x006b:
            r9 = move-exception
            r3 = r0
            goto L_0x0069
        L_0x006e:
            r3 = r0
            goto L_0x0082
        L_0x0070:
            r3 = r0
            goto L_0x0086
        L_0x0072:
            r9 = move-exception
            r3 = r0
            goto L_0x007b
        L_0x0075:
            r10 = r0
            r3 = r10
            goto L_0x0082
        L_0x0078:
            r10 = r0
            r3 = r10
            goto L_0x0086
        L_0x007b:
            r8.closeSilently(r0)     // Catch:{ all -> 0x0025 }
            r8.closeSilently(r3)     // Catch:{ all -> 0x0025 }
            throw r9     // Catch:{ all -> 0x0025 }
        L_0x0082:
            r8.closeSilently(r10)     // Catch:{ all -> 0x0025 }
            goto L_0x004c
        L_0x0086:
            r8.closeSilently(r10)     // Catch:{ all -> 0x0025 }
            goto L_0x004c
        L_0x008a:
            r10 = r2
            goto L_0x0009
        L_0x008d:
            java.lang.String r8 = "FATAL! Couldn't extract the library from the APK!"
            r13.log((java.lang.String) r8)     // Catch:{ all -> 0x0025 }
            java.util.zip.ZipFile r8 = r1.zipFile     // Catch:{ IOException -> 0x0099 }
            if (r8 == 0) goto L_0x0099
            r8.close()     // Catch:{ IOException -> 0x0099 }
        L_0x0099:
            return
        L_0x009a:
            java.lang.String[] r8 = r8.getSupportedABIs(r9, r11)     // Catch:{ Exception -> 0x009f }
            goto L_0x00a8
        L_0x009f:
            r8 = move-exception
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x0025 }
            java.lang.String[] r8 = new java.lang.String[]{r8}     // Catch:{ all -> 0x0025 }
        L_0x00a8:
            com.getkeepsafe.relinker.MissingLibraryException r9 = new com.getkeepsafe.relinker.MissingLibraryException     // Catch:{ all -> 0x0025 }
            r9.<init>(r11, r10, r8)     // Catch:{ all -> 0x0025 }
            throw r9     // Catch:{ all -> 0x0025 }
        L_0x00ae:
            r8 = move-exception
        L_0x00af:
            if (r0 == 0) goto L_0x00b8
            java.util.zip.ZipFile r9 = r0.zipFile     // Catch:{ IOException -> 0x00b8 }
            if (r9 == 0) goto L_0x00b8
            r9.close()     // Catch:{ IOException -> 0x00b8 }
        L_0x00b8:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.getkeepsafe.relinker.ApkLibraryInstaller.installLibrary(android.content.Context, java.lang.String[], java.lang.String, java.io.File, com.getkeepsafe.relinker.ReLinkerInstance):void");
    }
}
