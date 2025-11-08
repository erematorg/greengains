package com.google.android.gms.internal.mlkit_common;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.system.StructStat;
import androidx.core.content.ContextCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;
import org.apache.commons.text.lookup.StringLookupFactory;

public final class zzi {
    public static final /* synthetic */ int zza = 0;
    private static final String[] zzb = {"com.android.", "com.google.", "com.chrome.", "com.nest.", "com.waymo.", "com.waze"};
    private static final String[] zzc;
    private static final String[] zzd = {"", "", "com.google.android.apps.docs.storage.legacy"};

    static {
        String str = Build.HARDWARE;
        zzc = new String[]{SVGParser.XML_STYLESHEET_ATTR_MEDIA, (str.equals("goldfish") || str.equals("ranchu")) ? "androidx.test.services.storage.runfiles" : ""};
    }

    public static AssetFileDescriptor zza(Context context, Uri uri, String str) throws FileNotFoundException {
        zzh zzh = zzh.zza;
        ContentResolver contentResolver = context.getContentResolver();
        Uri zzc2 = zzc(uri);
        String scheme = zzc2.getScheme();
        if ("android.resource".equals(scheme)) {
            return contentResolver.openAssetFileDescriptor(zzc2, "r");
        }
        if (FirebaseAnalytics.Param.CONTENT.equals(scheme)) {
            if (zzj(context, zzc2, 1, zzh)) {
                AssetFileDescriptor openAssetFileDescriptor = contentResolver.openAssetFileDescriptor(zzc2, "r");
                zzd(openAssetFileDescriptor);
                return openAssetFileDescriptor;
            }
            throw new FileNotFoundException("Can't open content uri.");
        } else if (StringLookupFactory.KEY_FILE.equals(scheme)) {
            AssetFileDescriptor openAssetFileDescriptor2 = contentResolver.openAssetFileDescriptor(zzc2, "r");
            zzd(openAssetFileDescriptor2);
            try {
                zzi(context, openAssetFileDescriptor2.getParcelFileDescriptor(), zzc2, zzh);
                return openAssetFileDescriptor2;
            } catch (FileNotFoundException e3) {
                zzg(openAssetFileDescriptor2, e3);
                throw e3;
            } catch (IOException e4) {
                FileNotFoundException fileNotFoundException = new FileNotFoundException("Validation failed.");
                fileNotFoundException.initCause(e4);
                zzg(openAssetFileDescriptor2, fileNotFoundException);
                throw fileNotFoundException;
            }
        } else {
            throw new FileNotFoundException("Unsupported scheme");
        }
    }

    public static InputStream zzb(Context context, Uri uri, zzh zzh) throws FileNotFoundException {
        ContentResolver contentResolver = context.getContentResolver();
        Uri zzc2 = zzc(uri);
        String scheme = zzc2.getScheme();
        if ("android.resource".equals(scheme)) {
            return contentResolver.openInputStream(zzc2);
        }
        if (FirebaseAnalytics.Param.CONTENT.equals(scheme)) {
            if (zzj(context, zzc2, 1, zzh)) {
                InputStream openInputStream = contentResolver.openInputStream(zzc2);
                zzd(openInputStream);
                return openInputStream;
            }
            throw new FileNotFoundException("Can't open content uri.");
        } else if (StringLookupFactory.KEY_FILE.equals(scheme)) {
            try {
                ParcelFileDescriptor openFileDescriptor = contentResolver.openFileDescriptor(Uri.fromFile(new File(zzc2.getPath()).getCanonicalFile()), "r");
                try {
                    zzi(context, openFileDescriptor, zzc2, zzh);
                    return new ParcelFileDescriptor.AutoCloseInputStream(openFileDescriptor);
                } catch (FileNotFoundException e3) {
                    zzh(openFileDescriptor, e3);
                    throw e3;
                } catch (IOException e4) {
                    FileNotFoundException fileNotFoundException = new FileNotFoundException("Validation failed.");
                    fileNotFoundException.initCause(e4);
                    zzh(openFileDescriptor, fileNotFoundException);
                    throw fileNotFoundException;
                }
            } catch (IOException e5) {
                FileNotFoundException fileNotFoundException2 = new FileNotFoundException("Canonicalization failed.");
                fileNotFoundException2.initCause(e5);
                throw fileNotFoundException2;
            }
        } else {
            throw new FileNotFoundException("Unsupported scheme");
        }
    }

    private static Uri zzc(Uri uri) {
        return Build.VERSION.SDK_INT < 30 ? Uri.parse(uri.toString()) : uri;
    }

    private static Object zzd(Object obj) throws FileNotFoundException {
        if (obj != null) {
            return obj;
        }
        throw new FileNotFoundException("Content resolver returned null value.");
    }

    private static String zze(File file) throws IOException {
        String canonicalPath = file.getCanonicalPath();
        return !canonicalPath.endsWith("/") ? canonicalPath.concat("/") : canonicalPath;
    }

    private static void zzf(ParcelFileDescriptor parcelFileDescriptor, String str) throws IOException {
        try {
            StructStat fstat = Os.fstat(parcelFileDescriptor.getFileDescriptor());
            try {
                StructStat lstat = Os.lstat(str);
                if (OsConstants.S_ISLNK(lstat.st_mode)) {
                    throw new FileNotFoundException("Can't open file: ".concat(String.valueOf(str)));
                } else if (fstat.st_dev != lstat.st_dev || fstat.st_ino != lstat.st_ino) {
                    throw new FileNotFoundException("Can't open file: ".concat(String.valueOf(str)));
                }
            } catch (ErrnoException e3) {
                throw new IOException(e3);
            }
        } catch (ErrnoException e4) {
            throw new IOException(e4);
        }
    }

    private static void zzg(AssetFileDescriptor assetFileDescriptor, FileNotFoundException fileNotFoundException) {
        try {
            assetFileDescriptor.close();
        } catch (IOException e3) {
            fileNotFoundException.addSuppressed(e3);
        }
    }

    private static void zzh(ParcelFileDescriptor parcelFileDescriptor, FileNotFoundException fileNotFoundException) {
        try {
            parcelFileDescriptor.close();
        } catch (IOException e3) {
            fileNotFoundException.addSuppressed(e3);
        }
    }

    private static void zzi(Context context, ParcelFileDescriptor parcelFileDescriptor, Uri uri, zzh zzh) throws IOException {
        File dataDir;
        String canonicalPath = new File(uri.getPath()).getCanonicalPath();
        zzf(parcelFileDescriptor, canonicalPath);
        if (!canonicalPath.startsWith("/proc/") && !canonicalPath.startsWith("/data/misc/")) {
            boolean unused = zzh.zzc;
            File dataDir2 = ContextCompat.getDataDir(context);
            boolean z2 = true;
            if (dataDir2 == null ? !canonicalPath.startsWith(zze(Environment.getDataDirectory())) : !canonicalPath.startsWith(zze(dataDir2))) {
                Context createDeviceProtectedStorageContext = ContextCompat.createDeviceProtectedStorageContext(context);
                if (createDeviceProtectedStorageContext == null || (dataDir = ContextCompat.getDataDir(createDeviceProtectedStorageContext)) == null || !canonicalPath.startsWith(zze(dataDir))) {
                    File[] zzk = zzk(new zzb(context));
                    int length = zzk.length;
                    int i3 = 0;
                    while (true) {
                        if (i3 < length) {
                            File file = zzk[i3];
                            if (file != null && canonicalPath.startsWith(zze(file))) {
                                break;
                            }
                            i3++;
                        } else {
                            File[] zzk2 = zzk(new zzc(context));
                            int length2 = zzk2.length;
                            int i4 = 0;
                            while (true) {
                                if (i4 < length2) {
                                    File file2 = zzk2[i4];
                                    if (file2 != null && canonicalPath.startsWith(zze(file2))) {
                                        break;
                                    }
                                    i4++;
                                } else {
                                    z2 = false;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            if (z2 == zzh.zzb) {
                return;
            }
        }
        throw new FileNotFoundException("Can't open file: ".concat(canonicalPath));
    }

    private static boolean zzj(Context context, Uri uri, int i3, zzh zzh) {
        String authority = uri.getAuthority();
        ProviderInfo resolveContentProvider = context.getPackageManager().resolveContentProvider(authority, 0);
        if (resolveContentProvider == null) {
            int lastIndexOf = authority.lastIndexOf(64);
            if (lastIndexOf >= 0) {
                authority = authority.substring(lastIndexOf + 1);
                resolveContentProvider = context.getPackageManager().resolveContentProvider(authority, 0);
            }
            if (resolveContentProvider == null) {
                return !zzh.zzb;
            }
        }
        if (zzh.zzc(zzh, context, new zzj(uri, resolveContentProvider, authority)) - 1 == 1) {
            return false;
        }
        if (context.getPackageName().equals(resolveContentProvider.packageName)) {
            return zzh.zzb;
        }
        if (zzh.zzb) {
            return false;
        }
        if (context.checkUriPermission(uri, Process.myPid(), Process.myUid(), 1) != 0 && resolveContentProvider.exported) {
            String[] strArr = zzc;
            int length = strArr.length;
            for (int i4 = 0; i4 < 2; i4++) {
                if (strArr[i4].equals(authority)) {
                    return true;
                }
            }
            String[] strArr2 = zzd;
            int length2 = strArr2.length;
            for (int i5 = 0; i5 < 3; i5++) {
                if (strArr2[i5].equals(authority)) {
                    return true;
                }
            }
            String[] strArr3 = zzb;
            for (int i6 = 0; i6 < 6; i6++) {
                String str = strArr3[i6];
                if (str.charAt(str.length() - 1) == '.') {
                    if (resolveContentProvider.packageName.startsWith(str)) {
                        return false;
                    }
                } else if (resolveContentProvider.packageName.equals(str)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static File[] zzk(Callable callable) {
        try {
            return (File[]) callable.call();
        } catch (NullPointerException e3) {
            throw e3;
        } catch (Exception e4) {
            throw new RuntimeException(e4);
        }
    }
}
