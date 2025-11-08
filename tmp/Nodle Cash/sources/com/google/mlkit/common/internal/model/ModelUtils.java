package com.google.mlkit.common.internal.model;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.internal.mlkit_common.zzu;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@WorkerThread
@KeepForSdk
public class ModelUtils {
    private static final GmsLogger zza = new GmsLogger("ModelUtils", "");

    @KeepForSdk
    public static abstract class AutoMLManifest {
        @NonNull
        @KeepForSdk
        public abstract String getLabelsFile();

        @NonNull
        @KeepForSdk
        public abstract String getModelFile();

        @NonNull
        @KeepForSdk
        public abstract String getModelType();
    }

    @KeepForSdk
    public static abstract class ModelLoggingInfo {
        public static ModelLoggingInfo zza(long j2, @Nullable String str, boolean z2) {
            return new AutoValue_ModelUtils_ModelLoggingInfo(j2, zzu.zzb(str), z2);
        }

        @NonNull
        @KeepForSdk
        public abstract String getHash();

        @KeepForSdk
        public abstract long getSize();

        @KeepForSdk
        public abstract boolean isManifestModel();
    }

    private ModelUtils() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:83:0x00fc A[SYNTHETIC, Splitter:B:83:0x00fc] */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0109 A[SYNTHETIC, Splitter:B:89:0x0109] */
    @com.google.android.gms.common.annotation.KeepForSdk
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.mlkit.common.internal.model.ModelUtils.ModelLoggingInfo getModelLoggingInfo(@androidx.annotation.NonNull android.content.Context r11, @androidx.annotation.NonNull com.google.mlkit.common.model.LocalModel r12) {
        /*
            java.lang.String r0 = r12.getAssetFilePath()
            java.lang.String r1 = r12.getAbsoluteFilePath()
            android.net.Uri r2 = r12.getUri()
            java.lang.String r3 = "Failed to open model file"
            java.lang.String r4 = "ModelUtils"
            r5 = 0
            if (r0 == 0) goto L_0x0046
            boolean r6 = r12.isManifestFile()
            if (r6 == 0) goto L_0x0022
            r6 = 1
            java.lang.String r0 = zzb(r11, r0, r6)
            if (r0 == 0) goto L_0x0021
            goto L_0x0022
        L_0x0021:
            return r5
        L_0x0022:
            android.content.res.AssetManager r6 = r11.getAssets()     // Catch:{ IOException -> 0x0032 }
            android.content.res.AssetFileDescriptor r6 = r6.openFd(r0)     // Catch:{ IOException -> 0x0032 }
            long r7 = r6.getLength()     // Catch:{ all -> 0x0034 }
            r6.close()     // Catch:{ IOException -> 0x0032 }
            goto L_0x0070
        L_0x0032:
            r11 = move-exception
            goto L_0x0040
        L_0x0034:
            r11 = move-exception
            if (r6 == 0) goto L_0x003f
            r6.close()     // Catch:{ all -> 0x003b }
            goto L_0x003f
        L_0x003b:
            r12 = move-exception
            r11.addSuppressed(r12)     // Catch:{ IOException -> 0x0032 }
        L_0x003f:
            throw r11     // Catch:{ IOException -> 0x0032 }
        L_0x0040:
            com.google.android.gms.common.internal.GmsLogger r12 = zza
            r12.e(r4, r3, r11)
            return r5
        L_0x0046:
            if (r1 == 0) goto L_0x0061
            boolean r6 = r12.isManifestFile()
            if (r6 == 0) goto L_0x0057
            r6 = 0
            java.lang.String r1 = zzb(r11, r1, r6)
            if (r1 == 0) goto L_0x0056
            goto L_0x0057
        L_0x0056:
            return r5
        L_0x0057:
            java.io.File r6 = new java.io.File
            r6.<init>(r1)
            long r7 = r6.length()
            goto L_0x0070
        L_0x0061:
            if (r2 == 0) goto L_0x0128
            java.lang.String r6 = "r"
            android.content.res.AssetFileDescriptor r6 = com.google.android.gms.internal.mlkit_common.zzi.zza(r11, r2, r6)     // Catch:{ IOException -> 0x0114 }
            long r7 = r6.getLength()     // Catch:{ all -> 0x0116 }
            r6.close()     // Catch:{ IOException -> 0x0114 }
        L_0x0070:
            com.google.mlkit.common.sdkinternal.MlKitContext r6 = com.google.mlkit.common.sdkinternal.MlKitContext.getInstance()
            java.lang.Class<com.google.mlkit.common.sdkinternal.SharedPrefManager> r9 = com.google.mlkit.common.sdkinternal.SharedPrefManager.class
            java.lang.Object r6 = r6.get(r9)
            com.google.mlkit.common.sdkinternal.SharedPrefManager r6 = (com.google.mlkit.common.sdkinternal.SharedPrefManager) r6
            if (r0 == 0) goto L_0x0080
            r9 = r0
            goto L_0x008e
        L_0x0080:
            if (r1 == 0) goto L_0x0084
            r9 = r1
            goto L_0x008e
        L_0x0084:
            java.lang.Object r9 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r2)
            android.net.Uri r9 = (android.net.Uri) r9
            java.lang.String r9 = r9.toString()
        L_0x008e:
            java.lang.String r10 = r6.zzb(r9, r7)
            if (r10 == 0) goto L_0x009d
            boolean r11 = r12.isManifestFile()
            com.google.mlkit.common.internal.model.ModelUtils$ModelLoggingInfo r11 = com.google.mlkit.common.internal.model.ModelUtils.ModelLoggingInfo.zza(r7, r10, r11)
            return r11
        L_0x009d:
            java.lang.String r10 = "Failed to close model file"
            if (r0 == 0) goto L_0x00ae
            android.content.res.AssetManager r11 = r11.getAssets()     // Catch:{ IOException -> 0x00ac, all -> 0x00aa }
            java.io.InputStream r11 = r11.open(r0)     // Catch:{ IOException -> 0x00ac, all -> 0x00aa }
            goto L_0x00c9
        L_0x00aa:
            r11 = move-exception
            goto L_0x00f1
        L_0x00ac:
            r11 = move-exception
            goto L_0x00f3
        L_0x00ae:
            if (r1 == 0) goto L_0x00bb
            java.io.FileInputStream r11 = new java.io.FileInputStream     // Catch:{ IOException -> 0x00ac, all -> 0x00aa }
            java.io.File r0 = new java.io.File     // Catch:{ IOException -> 0x00ac, all -> 0x00aa }
            r0.<init>(r1)     // Catch:{ IOException -> 0x00ac, all -> 0x00aa }
            r11.<init>(r0)     // Catch:{ IOException -> 0x00ac, all -> 0x00aa }
            goto L_0x00c9
        L_0x00bb:
            java.lang.Object r0 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r2)     // Catch:{ IOException -> 0x00ac, all -> 0x00aa }
            android.net.Uri r0 = (android.net.Uri) r0     // Catch:{ IOException -> 0x00ac, all -> 0x00aa }
            int r1 = com.google.android.gms.internal.mlkit_common.zzi.zza     // Catch:{ IOException -> 0x00ac, all -> 0x00aa }
            com.google.android.gms.internal.mlkit_common.zzh r1 = com.google.android.gms.internal.mlkit_common.zzh.zza     // Catch:{ IOException -> 0x00ac, all -> 0x00aa }
            java.io.InputStream r11 = com.google.android.gms.internal.mlkit_common.zzi.zzb(r11, r0, r1)     // Catch:{ IOException -> 0x00ac, all -> 0x00aa }
        L_0x00c9:
            if (r11 == 0) goto L_0x00d4
            java.lang.String r0 = zzc(r11)     // Catch:{ IOException -> 0x00d2 }
            goto L_0x00d5
        L_0x00d0:
            r12 = move-exception
            goto L_0x00ef
        L_0x00d2:
            r12 = move-exception
            goto L_0x00f5
        L_0x00d4:
            r0 = r5
        L_0x00d5:
            if (r0 == 0) goto L_0x00da
            r6.zzc(r9, r7, r0)     // Catch:{ IOException -> 0x00d2 }
        L_0x00da:
            boolean r12 = r12.isManifestFile()     // Catch:{ IOException -> 0x00d2 }
            com.google.mlkit.common.internal.model.ModelUtils$ModelLoggingInfo r12 = com.google.mlkit.common.internal.model.ModelUtils.ModelLoggingInfo.zza(r7, r0, r12)     // Catch:{ IOException -> 0x00d2 }
            if (r11 == 0) goto L_0x00ee
            r11.close()     // Catch:{ IOException -> 0x00e8 }
            goto L_0x00ee
        L_0x00e8:
            r11 = move-exception
            com.google.android.gms.common.internal.GmsLogger r0 = zza
            r0.e(r4, r10, r11)
        L_0x00ee:
            return r12
        L_0x00ef:
            r5 = r11
            goto L_0x0107
        L_0x00f1:
            r12 = r11
            goto L_0x0107
        L_0x00f3:
            r12 = r11
            r11 = r5
        L_0x00f5:
            com.google.android.gms.common.internal.GmsLogger r0 = zza     // Catch:{ all -> 0x00d0 }
            r0.e(r4, r3, r12)     // Catch:{ all -> 0x00d0 }
            if (r11 == 0) goto L_0x0106
            r11.close()     // Catch:{ IOException -> 0x0100 }
            goto L_0x0106
        L_0x0100:
            r11 = move-exception
            com.google.android.gms.common.internal.GmsLogger r12 = zza
            r12.e(r4, r10, r11)
        L_0x0106:
            return r5
        L_0x0107:
            if (r5 == 0) goto L_0x0113
            r5.close()     // Catch:{ IOException -> 0x010d }
            goto L_0x0113
        L_0x010d:
            r11 = move-exception
            com.google.android.gms.common.internal.GmsLogger r0 = zza
            r0.e(r4, r10, r11)
        L_0x0113:
            throw r12
        L_0x0114:
            r11 = move-exception
            goto L_0x0122
        L_0x0116:
            r11 = move-exception
            if (r6 == 0) goto L_0x0121
            r6.close()     // Catch:{ all -> 0x011d }
            goto L_0x0121
        L_0x011d:
            r12 = move-exception
            r11.addSuppressed(r12)     // Catch:{ IOException -> 0x0114 }
        L_0x0121:
            throw r11     // Catch:{ IOException -> 0x0114 }
        L_0x0122:
            com.google.android.gms.common.internal.GmsLogger r12 = zza
            r12.e(r4, r3, r11)
            return r5
        L_0x0128:
            com.google.android.gms.common.internal.GmsLogger r11 = zza
            java.lang.String r12 = "Local model doesn't have any valid path."
            r11.e(r4, r12)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.internal.model.ModelUtils.getModelLoggingInfo(android.content.Context, com.google.mlkit.common.model.LocalModel):com.google.mlkit.common.internal.model.ModelUtils$ModelLoggingInfo");
    }

    @KeepForSdk
    @Nullable
    public static String getSHA256(@NonNull File file) {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(file);
            String zzc = zzc(fileInputStream);
            fileInputStream.close();
            return zzc;
        } catch (IOException e3) {
            zza.e("ModelUtils", "Failed to create FileInputStream for model: ".concat(e3.toString()));
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x002d, code lost:
        if (new java.io.File(r6).exists() == false) goto L_0x002f;
     */
    @com.google.android.gms.common.annotation.KeepForSdk
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.mlkit.common.internal.model.ModelUtils.AutoMLManifest parseManifestFile(@androidx.annotation.NonNull java.lang.String r6, boolean r7, @androidx.annotation.NonNull android.content.Context r8) {
        /*
            java.lang.String r0 = "Json string from the manifest file: "
            java.lang.String r1 = java.lang.String.valueOf(r6)
            com.google.android.gms.common.internal.GmsLogger r2 = zza
            java.lang.String r3 = "Manifest file path: "
            java.lang.String r1 = r3.concat(r1)
            java.lang.String r3 = "ModelUtils"
            r2.d(r3, r1)
            r1 = 0
            if (r7 == 0) goto L_0x0024
            android.content.res.AssetManager r4 = r8.getAssets()     // Catch:{ IOException -> 0x002f }
            java.io.InputStream r4 = r4.open(r6)     // Catch:{ IOException -> 0x002f }
            if (r4 == 0) goto L_0x0037
            r4.close()     // Catch:{ IOException -> 0x002f }
            goto L_0x0037
        L_0x0024:
            java.io.File r4 = new java.io.File
            r4.<init>(r6)
            boolean r4 = r4.exists()
            if (r4 != 0) goto L_0x0037
        L_0x002f:
            com.google.android.gms.common.internal.GmsLogger r6 = zza
            java.lang.String r7 = "Manifest file does not exist."
            r6.e(r3, r7)
            return r1
        L_0x0037:
            boolean r4 = r6.isEmpty()     // Catch:{ IOException | JSONException -> 0x0041 }
            r5 = 0
            if (r4 == 0) goto L_0x0043
            byte[] r6 = new byte[r5]     // Catch:{ IOException | JSONException -> 0x0041 }
            goto L_0x0066
        L_0x0041:
            r6 = move-exception
            goto L_0x009d
        L_0x0043:
            if (r7 == 0) goto L_0x004e
            android.content.res.AssetManager r7 = r8.getAssets()     // Catch:{ IOException | JSONException -> 0x0041 }
            java.io.InputStream r6 = r7.open(r6)     // Catch:{ IOException | JSONException -> 0x0041 }
            goto L_0x0059
        L_0x004e:
            java.io.FileInputStream r7 = new java.io.FileInputStream     // Catch:{ IOException | JSONException -> 0x0041 }
            java.io.File r8 = new java.io.File     // Catch:{ IOException | JSONException -> 0x0041 }
            r8.<init>(r6)     // Catch:{ IOException | JSONException -> 0x0041 }
            r7.<init>(r8)     // Catch:{ IOException | JSONException -> 0x0041 }
            r6 = r7
        L_0x0059:
            int r7 = r6.available()     // Catch:{ all -> 0x0091 }
            byte[] r8 = new byte[r7]     // Catch:{ all -> 0x0091 }
            r6.read(r8, r5, r7)     // Catch:{ all -> 0x0091 }
            r6.close()     // Catch:{ IOException | JSONException -> 0x0041 }
            r6 = r8
        L_0x0066:
            java.lang.String r7 = new java.lang.String     // Catch:{ IOException | JSONException -> 0x0041 }
            java.lang.String r8 = "UTF-8"
            r7.<init>(r6, r8)     // Catch:{ IOException | JSONException -> 0x0041 }
            java.lang.String r6 = r0.concat(r7)     // Catch:{ IOException | JSONException -> 0x0041 }
            r2.d(r3, r6)     // Catch:{ IOException | JSONException -> 0x0041 }
            org.json.JSONObject r6 = new org.json.JSONObject     // Catch:{ IOException | JSONException -> 0x0041 }
            r6.<init>(r7)     // Catch:{ IOException | JSONException -> 0x0041 }
            java.lang.String r7 = "modelType"
            java.lang.String r7 = r6.getString(r7)     // Catch:{ IOException | JSONException -> 0x0041 }
            java.lang.String r8 = "modelFile"
            java.lang.String r8 = r6.getString(r8)     // Catch:{ IOException | JSONException -> 0x0041 }
            java.lang.String r0 = "labelsFile"
            java.lang.String r6 = r6.getString(r0)     // Catch:{ IOException | JSONException -> 0x0041 }
            com.google.mlkit.common.internal.model.AutoValue_ModelUtils_AutoMLManifest r0 = new com.google.mlkit.common.internal.model.AutoValue_ModelUtils_AutoMLManifest     // Catch:{ IOException | JSONException -> 0x0041 }
            r0.<init>(r7, r8, r6)     // Catch:{ IOException | JSONException -> 0x0041 }
            return r0
        L_0x0091:
            r7 = move-exception
            if (r6 == 0) goto L_0x009c
            r6.close()     // Catch:{ all -> 0x0098 }
            goto L_0x009c
        L_0x0098:
            r6 = move-exception
            r7.addSuppressed(r6)     // Catch:{ IOException | JSONException -> 0x0041 }
        L_0x009c:
            throw r7     // Catch:{ IOException | JSONException -> 0x0041 }
        L_0x009d:
            com.google.android.gms.common.internal.GmsLogger r7 = zza
            java.lang.String r8 = "Error parsing the manifest file."
            r7.e(r3, r8, r6)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.internal.model.ModelUtils.parseManifestFile(java.lang.String, boolean, android.content.Context):com.google.mlkit.common.internal.model.ModelUtils$AutoMLManifest");
    }

    public static boolean zza(@NonNull File file, @NonNull String str) {
        String sha256 = getSHA256(file);
        zza.d("ModelUtils", "Calculated hash value is: ".concat(String.valueOf(sha256)));
        return str.equals(sha256);
    }

    @Nullable
    private static String zzb(Context context, String str, boolean z2) {
        AutoMLManifest parseManifestFile = parseManifestFile(str, z2, context);
        if (parseManifestFile != null) {
            return new File(new File(str).getParent(), parseManifestFile.getModelFile()).toString();
        }
        zza.e("ModelUtils", "Failed to parse manifest file.");
        return null;
    }

    @Nullable
    private static String zzc(InputStream inputStream) {
        int i3;
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-256");
            byte[] bArr = new byte[1048576];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                instance.update(bArr, 0, read);
            }
            byte[] digest = instance.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b3 : digest) {
                String hexString = Integer.toHexString(b3 & 255);
                if (hexString.length() == 1) {
                    sb.append('0');
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException unused) {
            zza.e("ModelUtils", "Do not have SHA-256 algorithm");
            return null;
        } catch (IOException unused2) {
            zza.e("ModelUtils", "Failed to read model file");
            return null;
        }
    }
}
