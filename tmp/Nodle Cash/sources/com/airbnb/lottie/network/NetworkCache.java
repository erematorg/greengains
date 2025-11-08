package com.airbnb.lottie.network;

import A.a;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.airbnb.lottie.utils.Logger;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestrictTo({RestrictTo.Scope.LIBRARY})
public class NetworkCache {
    @NonNull
    private final LottieNetworkCacheProvider cacheProvider;

    public NetworkCache(@NonNull LottieNetworkCacheProvider lottieNetworkCacheProvider) {
        this.cacheProvider = lottieNetworkCacheProvider;
    }

    private static String filenameForUrl(String str, FileExtension fileExtension, boolean z2) {
        String tempExtension = z2 ? fileExtension.tempExtension() : fileExtension.extension;
        String replaceAll = str.replaceAll("\\W+", "");
        int length = 242 - tempExtension.length();
        if (replaceAll.length() > length) {
            replaceAll = getMD5(replaceAll, length);
        }
        return a.l("lottie_cache_", replaceAll, tempExtension);
    }

    @Nullable
    private File getCachedFile(String str) throws FileNotFoundException {
        File file = new File(parentDir(), filenameForUrl(str, FileExtension.JSON, false));
        if (file.exists()) {
            return file;
        }
        File file2 = new File(parentDir(), filenameForUrl(str, FileExtension.ZIP, false));
        if (file2.exists()) {
            return file2;
        }
        return null;
    }

    private static String getMD5(String str, int i3) {
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte valueOf : digest) {
                sb.append(String.format("%02x", new Object[]{Byte.valueOf(valueOf)}));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException unused) {
            return str.substring(0, i3);
        }
    }

    private File parentDir() {
        File cacheDir = this.cacheProvider.getCacheDir();
        if (cacheDir.isFile()) {
            cacheDir.delete();
        }
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
        return cacheDir;
    }

    public void clear() {
        File parentDir = parentDir();
        if (parentDir.exists()) {
            File[] listFiles = parentDir.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                for (File delete : listFiles) {
                    delete.delete();
                }
            }
            parentDir.delete();
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    @androidx.annotation.WorkerThread
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.util.Pair<com.airbnb.lottie.network.FileExtension, java.io.InputStream> fetch(java.lang.String r5) {
        /*
            r4 = this;
            r0 = 0
            java.io.File r4 = r4.getCachedFile(r5)     // Catch:{ FileNotFoundException -> 0x003a }
            if (r4 != 0) goto L_0x0008
            return r0
        L_0x0008:
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{  }
            r1.<init>(r4)     // Catch:{  }
            java.lang.String r0 = r4.getAbsolutePath()
            java.lang.String r2 = ".zip"
            boolean r0 = r0.endsWith(r2)
            if (r0 == 0) goto L_0x001c
            com.airbnb.lottie.network.FileExtension r0 = com.airbnb.lottie.network.FileExtension.ZIP
            goto L_0x001e
        L_0x001c:
            com.airbnb.lottie.network.FileExtension r0 = com.airbnb.lottie.network.FileExtension.JSON
        L_0x001e:
            java.lang.String r2 = "Cache hit for "
            java.lang.String r3 = " at "
            java.lang.StringBuilder r5 = android.support.v4.media.session.a.w(r2, r5, r3)
            java.lang.String r4 = r4.getAbsolutePath()
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            com.airbnb.lottie.utils.Logger.debug(r4)
            android.util.Pair r4 = new android.util.Pair
            r4.<init>(r0, r1)
            return r4
        L_0x003a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.network.NetworkCache.fetch(java.lang.String):android.util.Pair");
    }

    public void renameTempFile(String str, FileExtension fileExtension) {
        File file = new File(parentDir(), filenameForUrl(str, fileExtension, true));
        File file2 = new File(file.getAbsolutePath().replace(".temp", ""));
        boolean renameTo = file.renameTo(file2);
        Logger.debug("Copying temp file to real file (" + file2 + ")");
        if (!renameTo) {
            Logger.warning("Unable to rename cache file " + file.getAbsolutePath() + " to " + file2.getAbsolutePath() + JwtUtilsKt.JWT_DELIMITER);
        }
    }

    public File writeTempCacheFile(String str, InputStream inputStream, FileExtension fileExtension) throws IOException {
        FileOutputStream fileOutputStream;
        File file = new File(parentDir(), filenameForUrl(str, fileExtension, true));
        try {
            fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    inputStream.close();
                    return file;
                }
            }
        } catch (Throwable th) {
            inputStream.close();
            throw th;
        }
    }
}
