package com.airbnb.lottie;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.util.Base64;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;
import androidx.annotation.WorkerThread;
import androidx.camera.camera2.interop.c;
import androidx.work.impl.a;
import com.airbnb.lottie.model.Font;
import com.airbnb.lottie.model.LottieCompositionCache;
import com.airbnb.lottie.network.NetworkCache;
import com.airbnb.lottie.parser.LottieCompositionMoshiParser;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.Utils;
import com.google.mlkit.common.sdkinternal.Constants;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import okio.BufferedSource;
import okio.Okio;
import org.json.JSONObject;

public class LottieCompositionFactory {
    private static final byte[] MAGIC = {80, 75, 3, 4};
    private static final Map<String, LottieTask<LottieComposition>> taskCache = new HashMap();
    private static final Set<LottieTaskIdleListener> taskIdleListeners = new HashSet();

    private LottieCompositionFactory() {
    }

    private static LottieTask<LottieComposition> cache(@Nullable String str, Callable<LottieResult<LottieComposition>> callable, @Nullable Runnable runnable) {
        LottieTask<LottieComposition> lottieTask = null;
        LottieComposition lottieComposition = str == null ? null : LottieCompositionCache.getInstance().get(str);
        if (lottieComposition != null) {
            lottieTask = new LottieTask<>(new i(lottieComposition));
        }
        if (str != null) {
            Map<String, LottieTask<LottieComposition>> map = taskCache;
            if (map.containsKey(str)) {
                lottieTask = map.get(str);
            }
        }
        if (lottieTask != null) {
            if (runnable != null) {
                runnable.run();
            }
            return lottieTask;
        }
        LottieTask<LottieComposition> lottieTask2 = new LottieTask<>(callable);
        if (str != null) {
            AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            lottieTask2.addListener(new j(str, atomicBoolean, 0));
            lottieTask2.addFailureListener(new j(str, atomicBoolean, 1));
            if (!atomicBoolean.get()) {
                Map<String, LottieTask<LottieComposition>> map2 = taskCache;
                map2.put(str, lottieTask2);
                if (map2.size() == 1) {
                    notifyTaskCacheIdleListeners(false);
                }
            }
        }
        return lottieTask2;
    }

    public static void clearCache(Context context) {
        taskCache.clear();
        LottieCompositionCache.getInstance().clear();
        NetworkCache networkCache = L.networkCache(context);
        if (networkCache != null) {
            networkCache.clear();
        }
    }

    @Nullable
    private static LottieImageAsset findImageAssetForFileName(LottieComposition lottieComposition, String str) {
        for (LottieImageAsset next : lottieComposition.getImages().values()) {
            if (next.getFileName().equals(str)) {
                return next;
            }
        }
        return null;
    }

    public static LottieTask<LottieComposition> fromAsset(Context context, String str) {
        return fromAsset(context, str, "asset_" + str);
    }

    @WorkerThread
    public static LottieResult<LottieComposition> fromAssetSync(Context context, String str) {
        return fromAssetSync(context, str, "asset_" + str);
    }

    @Deprecated
    public static LottieTask<LottieComposition> fromJson(JSONObject jSONObject, @Nullable String str) {
        return cache(str, new b(3, str, jSONObject), (Runnable) null);
    }

    public static LottieTask<LottieComposition> fromJsonInputStream(InputStream inputStream, @Nullable String str) {
        return cache(str, new b(2, str, inputStream), new e(inputStream, 1));
    }

    @WorkerThread
    public static LottieResult<LottieComposition> fromJsonInputStreamSync(InputStream inputStream, @Nullable String str) {
        return fromJsonInputStreamSync(inputStream, str, true);
    }

    public static LottieTask<LottieComposition> fromJsonReader(JsonReader jsonReader, @Nullable String str) {
        return cache(str, new b(1, str, jsonReader), new e(jsonReader, 0));
    }

    @WorkerThread
    public static LottieResult<LottieComposition> fromJsonReaderSync(JsonReader jsonReader, @Nullable String str) {
        return fromJsonReaderSync(jsonReader, str, true);
    }

    private static LottieResult<LottieComposition> fromJsonReaderSyncInternal(JsonReader jsonReader, @Nullable String str, boolean z2) {
        LottieComposition lottieComposition;
        if (str == null) {
            lottieComposition = null;
        } else {
            try {
                lottieComposition = LottieCompositionCache.getInstance().get(str);
            } catch (Exception e3) {
                LottieResult<LottieComposition> lottieResult = new LottieResult<>((Throwable) e3);
                if (z2) {
                    Utils.closeQuietly(jsonReader);
                }
                return lottieResult;
            } catch (Throwable th) {
                if (z2) {
                    Utils.closeQuietly(jsonReader);
                }
                throw th;
            }
        }
        if (lottieComposition != null) {
            LottieResult<LottieComposition> lottieResult2 = new LottieResult<>(lottieComposition);
            if (z2) {
                Utils.closeQuietly(jsonReader);
            }
            return lottieResult2;
        }
        LottieComposition parse = LottieCompositionMoshiParser.parse(jsonReader);
        if (str != null) {
            LottieCompositionCache.getInstance().put(str, parse);
        }
        LottieResult<LottieComposition> lottieResult3 = new LottieResult<>(parse);
        if (z2) {
            Utils.closeQuietly(jsonReader);
        }
        return lottieResult3;
    }

    public static LottieTask<LottieComposition> fromJsonString(String str, @Nullable String str2) {
        return cache(str2, new b(str, str2), (Runnable) null);
    }

    @WorkerThread
    public static LottieResult<LottieComposition> fromJsonStringSync(String str, @Nullable String str2) {
        return fromJsonReaderSync(JsonReader.of(Okio.buffer(Okio.source((InputStream) new ByteArrayInputStream(str.getBytes())))), str2);
    }

    @WorkerThread
    @Deprecated
    public static LottieResult<LottieComposition> fromJsonSync(JSONObject jSONObject, @Nullable String str) {
        return fromJsonStringSync(jSONObject.toString(), str);
    }

    public static LottieTask<LottieComposition> fromRawRes(Context context, @RawRes int i3) {
        return fromRawRes(context, i3, rawResCacheKey(context, i3));
    }

    @WorkerThread
    public static LottieResult<LottieComposition> fromRawResSync(Context context, @RawRes int i3) {
        return fromRawResSync(context, i3, rawResCacheKey(context, i3));
    }

    public static LottieTask<LottieComposition> fromUrl(Context context, String str) {
        return fromUrl(context, str, "url_" + str);
    }

    @WorkerThread
    public static LottieResult<LottieComposition> fromUrlSync(Context context, String str) {
        return fromUrlSync(context, str, str);
    }

    public static LottieTask<LottieComposition> fromZipStream(ZipInputStream zipInputStream, @Nullable String str) {
        return fromZipStream((Context) null, zipInputStream, str);
    }

    public static LottieResult<LottieComposition> fromZipStreamSync(ZipInputStream zipInputStream, @Nullable String str) {
        return fromZipStreamSync(zipInputStream, str, true);
    }

    @WorkerThread
    private static LottieResult<LottieComposition> fromZipStreamSyncInternal(Context context, ZipInputStream zipInputStream, @Nullable String str) {
        LottieComposition lottieComposition;
        FileOutputStream fileOutputStream;
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        if (str == null) {
            lottieComposition = null;
        } else {
            try {
                lottieComposition = LottieCompositionCache.getInstance().get(str);
            } catch (IOException e3) {
                return new LottieResult<>((Throwable) e3);
            }
        }
        if (lottieComposition != null) {
            return new LottieResult<>(lottieComposition);
        }
        ZipEntry nextEntry = zipInputStream.getNextEntry();
        LottieComposition lottieComposition2 = null;
        while (nextEntry != null) {
            String name = nextEntry.getName();
            if (name.contains("__MACOSX")) {
                zipInputStream.closeEntry();
            } else if (nextEntry.getName().equalsIgnoreCase(Constants.AUTOML_IMAGE_LABELING_MANIFEST_JSON_FILE_NAME)) {
                zipInputStream.closeEntry();
            } else if (nextEntry.getName().contains(".json")) {
                lottieComposition2 = fromJsonReaderSyncInternal(JsonReader.of(Okio.buffer(Okio.source((InputStream) zipInputStream))), (String) null, false).getValue();
            } else {
                if (!name.contains(".png")) {
                    if (!name.contains(".webp") && !name.contains(".jpg")) {
                        if (!name.contains(".jpeg")) {
                            if (!name.contains(".ttf")) {
                                if (!name.contains(".otf")) {
                                    zipInputStream.closeEntry();
                                }
                            }
                            String[] split = name.split("/");
                            String str2 = split[split.length - 1];
                            String str3 = str2.split("\\.")[0];
                            File file = new File(context.getCacheDir(), str2);
                            new FileOutputStream(file);
                            try {
                                fileOutputStream = new FileOutputStream(file);
                                byte[] bArr = new byte[4096];
                                while (true) {
                                    int read = zipInputStream.read(bArr);
                                    if (read == -1) {
                                        break;
                                    }
                                    fileOutputStream.write(bArr, 0, read);
                                }
                                fileOutputStream.flush();
                                fileOutputStream.close();
                            } catch (Throwable th) {
                                Logger.warning("Unable to save font " + str3 + " to the temporary file: " + str2 + ". ", th);
                            }
                            Typeface createFromFile = Typeface.createFromFile(file);
                            if (!file.delete()) {
                                Logger.warning("Failed to delete temp font file " + file.getAbsolutePath() + JwtUtilsKt.JWT_DELIMITER);
                            }
                            hashMap2.put(str3, createFromFile);
                        }
                    }
                }
                String[] split2 = name.split("/");
                hashMap.put(split2[split2.length - 1], BitmapFactory.decodeStream(zipInputStream));
            }
            nextEntry = zipInputStream.getNextEntry();
        }
        if (lottieComposition2 == null) {
            return new LottieResult<>((Throwable) new IllegalArgumentException("Unable to parse composition"));
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            LottieImageAsset findImageAssetForFileName = findImageAssetForFileName(lottieComposition2, (String) entry.getKey());
            if (findImageAssetForFileName != null) {
                findImageAssetForFileName.setBitmap(Utils.resizeBitmapIfNeeded((Bitmap) entry.getValue(), findImageAssetForFileName.getWidth(), findImageAssetForFileName.getHeight()));
            }
        }
        for (Map.Entry entry2 : hashMap2.entrySet()) {
            boolean z2 = false;
            for (Font next : lottieComposition2.getFonts().values()) {
                if (next.getFamily().equals(entry2.getKey())) {
                    next.setTypeface((Typeface) entry2.getValue());
                    z2 = true;
                }
            }
            if (!z2) {
                Logger.warning("Parsed font for " + ((String) entry2.getKey()) + " however it was not found in the animation.");
            }
        }
        if (hashMap.isEmpty()) {
            for (Map.Entry<String, LottieImageAsset> value : lottieComposition2.getImages().entrySet()) {
                LottieImageAsset lottieImageAsset = (LottieImageAsset) value.getValue();
                if (lottieImageAsset == null) {
                    return null;
                }
                String fileName = lottieImageAsset.getFileName();
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inScaled = true;
                options.inDensity = 160;
                if (fileName.startsWith("data:") && fileName.indexOf("base64,") > 0) {
                    try {
                        byte[] decode = Base64.decode(fileName.substring(fileName.indexOf(44) + 1), 0);
                        lottieImageAsset.setBitmap(BitmapFactory.decodeByteArray(decode, 0, decode.length, options));
                    } catch (IllegalArgumentException e4) {
                        Logger.warning("data URL did not have correct base64 format.", e4);
                        return null;
                    }
                }
            }
        }
        if (str != null) {
            LottieCompositionCache.getInstance().put(str, lottieComposition2);
        }
        return new LottieResult<>(lottieComposition2);
        throw th;
    }

    private static boolean isNightMode(Context context) {
        return (context.getResources().getConfiguration().uiMode & 48) == 32;
    }

    private static Boolean isZipCompressed(BufferedSource bufferedSource) {
        try {
            BufferedSource peek = bufferedSource.peek();
            for (byte b3 : MAGIC) {
                if (peek.readByte() != b3) {
                    return Boolean.FALSE;
                }
            }
            peek.close();
            return Boolean.TRUE;
        } catch (NoSuchMethodError unused) {
            return Boolean.FALSE;
        } catch (Exception e3) {
            Logger.error("Failed to check zip file header", e3);
            return Boolean.FALSE;
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ LottieResult lambda$cache$15(LottieComposition lottieComposition) throws Exception {
        return new LottieResult(lottieComposition);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$cache$16(String str, AtomicBoolean atomicBoolean, LottieComposition lottieComposition) {
        Map<String, LottieTask<LottieComposition>> map = taskCache;
        map.remove(str);
        atomicBoolean.set(true);
        if (map.size() == 0) {
            notifyTaskCacheIdleListeners(true);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$cache$17(String str, AtomicBoolean atomicBoolean, Throwable th) {
        Map<String, LottieTask<LottieComposition>> map = taskCache;
        map.remove(str);
        atomicBoolean.set(true);
        if (map.size() == 0) {
            notifyTaskCacheIdleListeners(true);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$fromJsonInputStream$6(boolean z2, InputStream inputStream) {
        if (z2) {
            Utils.closeQuietly(inputStream);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ LottieResult lambda$fromRawRes$2(WeakReference weakReference, Context context, int i3, String str) throws Exception {
        Context context2 = (Context) weakReference.get();
        if (context2 != null) {
            context = context2;
        }
        return fromRawResSync(context, i3, str);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ LottieResult lambda$fromUrl$0(Context context, String str, String str2) throws Exception {
        LottieResult<LottieComposition> fetchSync = L.networkFetcher(context).fetchSync(context, str, str2);
        if (!(str2 == null || fetchSync.getValue() == null)) {
            LottieCompositionCache.getInstance().put(str2, fetchSync.getValue());
        }
        return fetchSync;
    }

    private static void notifyTaskCacheIdleListeners(boolean z2) {
        ArrayList arrayList = new ArrayList(taskIdleListeners);
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            ((LottieTaskIdleListener) arrayList.get(i3)).onIdleChanged(z2);
        }
    }

    private static String rawResCacheKey(Context context, @RawRes int i3) {
        return a.u(new StringBuilder("rawRes"), isNightMode(context) ? "_night_" : "_day_", i3);
    }

    public static void registerLottieTaskIdleListener(LottieTaskIdleListener lottieTaskIdleListener) {
        taskIdleListeners.add(lottieTaskIdleListener);
        lottieTaskIdleListener.onIdleChanged(taskCache.size() == 0);
    }

    public static void setMaxCacheSize(int i3) {
        LottieCompositionCache.getInstance().resize(i3);
    }

    public static void unregisterLottieTaskIdleListener(LottieTaskIdleListener lottieTaskIdleListener) {
        taskIdleListeners.remove(lottieTaskIdleListener);
    }

    public static LottieTask<LottieComposition> fromJsonInputStream(InputStream inputStream, @Nullable String str, boolean z2) {
        return cache(str, new C0214l(inputStream, str, z2), new c(z2, inputStream));
    }

    @WorkerThread
    public static LottieResult<LottieComposition> fromJsonInputStreamSync(InputStream inputStream, @Nullable String str, boolean z2) {
        return fromJsonReaderSync(JsonReader.of(Okio.buffer(Okio.source(inputStream))), str, z2);
    }

    @WorkerThread
    public static LottieResult<LottieComposition> fromJsonReaderSync(JsonReader jsonReader, @Nullable String str, boolean z2) {
        return fromJsonReaderSyncInternal(jsonReader, str, z2);
    }

    public static LottieTask<LottieComposition> fromRawRes(Context context, @RawRes int i3, @Nullable String str) {
        return cache(str, new k(new WeakReference(context), context.getApplicationContext(), i3, str), (Runnable) null);
    }

    @WorkerThread
    public static LottieResult<LottieComposition> fromRawResSync(Context context, @RawRes int i3, @Nullable String str) {
        LottieComposition lottieComposition = str == null ? null : LottieCompositionCache.getInstance().get(str);
        if (lottieComposition != null) {
            return new LottieResult<>(lottieComposition);
        }
        try {
            BufferedSource buffer = Okio.buffer(Okio.source(context.getResources().openRawResource(i3)));
            if (isZipCompressed(buffer).booleanValue()) {
                return fromZipStreamSync(context, new ZipInputStream(buffer.inputStream()), str);
            }
            return fromJsonInputStreamSync(buffer.inputStream(), str);
        } catch (Resources.NotFoundException e3) {
            return new LottieResult<>((Throwable) e3);
        }
    }

    public static LottieTask<LottieComposition> fromUrl(Context context, String str, @Nullable String str2) {
        return cache(str2, new f(context, 1, str, str2), (Runnable) null);
    }

    @WorkerThread
    public static LottieResult<LottieComposition> fromUrlSync(Context context, String str, @Nullable String str2) {
        LottieComposition lottieComposition = str2 == null ? null : LottieCompositionCache.getInstance().get(str2);
        if (lottieComposition != null) {
            return new LottieResult<>(lottieComposition);
        }
        LottieResult<LottieComposition> fetchSync = L.networkFetcher(context).fetchSync(context, str, str2);
        if (!(str2 == null || fetchSync.getValue() == null)) {
            LottieCompositionCache.getInstance().put(str2, fetchSync.getValue());
        }
        return fetchSync;
    }

    public static LottieTask<LottieComposition> fromZipStream(ZipInputStream zipInputStream, @Nullable String str, boolean z2) {
        return fromZipStream((Context) null, zipInputStream, str, z2);
    }

    public static LottieResult<LottieComposition> fromZipStreamSync(ZipInputStream zipInputStream, @Nullable String str, boolean z2) {
        return fromZipStreamSync((Context) null, zipInputStream, str, z2);
    }

    public static LottieTask<LottieComposition> fromAsset(Context context, String str, @Nullable String str2) {
        return cache(str2, new f(context.getApplicationContext(), 0, str, str2), (Runnable) null);
    }

    @WorkerThread
    public static LottieResult<LottieComposition> fromAssetSync(Context context, String str, @Nullable String str2) {
        LottieComposition lottieComposition = str2 == null ? null : LottieCompositionCache.getInstance().get(str2);
        if (lottieComposition != null) {
            return new LottieResult<>(lottieComposition);
        }
        try {
            if (!str.endsWith(".zip")) {
                if (!str.endsWith(".lottie")) {
                    return fromJsonInputStreamSync(context.getAssets().open(str), str2);
                }
            }
            return fromZipStreamSync(context, new ZipInputStream(context.getAssets().open(str)), str2);
        } catch (IOException e3) {
            return new LottieResult<>((Throwable) e3);
        }
    }

    public static LottieTask<LottieComposition> fromZipStream(Context context, ZipInputStream zipInputStream, @Nullable String str) {
        return cache(str, new g(context, zipInputStream, str, 0), new h(zipInputStream, 0));
    }

    @WorkerThread
    public static LottieResult<LottieComposition> fromZipStreamSync(@Nullable Context context, ZipInputStream zipInputStream, @Nullable String str) {
        return fromZipStreamSync(context, zipInputStream, str, true);
    }

    public static LottieTask<LottieComposition> fromZipStream(Context context, ZipInputStream zipInputStream, @Nullable String str, boolean z2) {
        return cache(str, new g(context, zipInputStream, str, 1), z2 ? new h(zipInputStream, 1) : null);
    }

    @WorkerThread
    public static LottieResult<LottieComposition> fromZipStreamSync(@Nullable Context context, ZipInputStream zipInputStream, @Nullable String str, boolean z2) {
        try {
            return fromZipStreamSyncInternal(context, zipInputStream, str);
        } finally {
            if (z2) {
                Utils.closeQuietly(zipInputStream);
            }
        }
    }
}
