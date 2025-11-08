package com.caverock.androidsvg;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import coil.util.Utils;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class SimpleAssetResolver extends SVGExternalFileResolver {
    private static final String TAG = "SimpleAssetResolver";
    private static final Set<String> supportedFormats;
    private AssetManager assetManager;

    static {
        HashSet hashSet = new HashSet(8);
        supportedFormats = hashSet;
        hashSet.add("image/svg+xml");
        hashSet.add("image/jpeg");
        hashSet.add("image/png");
        hashSet.add("image/pjpeg");
        hashSet.add("image/gif");
        hashSet.add("image/bmp");
        hashSet.add("image/x-windows-bmp");
        hashSet.add(Utils.MIME_TYPE_WEBP);
    }

    public SimpleAssetResolver(AssetManager assetManager2) {
        this.assetManager = assetManager2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x003d A[SYNTHETIC, Splitter:B:19:0x003d] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0043 A[SYNTHETIC, Splitter:B:25:0x0043] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String getAssetAsString(java.lang.String r6) {
        /*
            r5 = this;
            r0 = 0
            android.content.res.AssetManager r5 = r5.assetManager     // Catch:{ IOException -> 0x0039, all -> 0x0037 }
            java.io.InputStream r5 = r5.open(r6)     // Catch:{ IOException -> 0x0039, all -> 0x0037 }
            java.io.InputStreamReader r6 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x0041, all -> 0x002a }
            java.lang.String r1 = "UTF-8"
            java.nio.charset.Charset r1 = java.nio.charset.Charset.forName(r1)     // Catch:{ IOException -> 0x0041, all -> 0x002a }
            r6.<init>(r5, r1)     // Catch:{ IOException -> 0x0041, all -> 0x002a }
            r1 = 4096(0x1000, float:5.74E-42)
            char[] r1 = new char[r1]     // Catch:{ IOException -> 0x0041, all -> 0x002a }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0041, all -> 0x002a }
            r2.<init>()     // Catch:{ IOException -> 0x0041, all -> 0x002a }
            int r3 = r6.read(r1)     // Catch:{ IOException -> 0x0041, all -> 0x002a }
        L_0x001f:
            if (r3 <= 0) goto L_0x002d
            r4 = 0
            r2.append(r1, r4, r3)     // Catch:{ IOException -> 0x0041, all -> 0x002a }
            int r3 = r6.read(r1)     // Catch:{ IOException -> 0x0041, all -> 0x002a }
            goto L_0x001f
        L_0x002a:
            r6 = move-exception
            r0 = r5
            goto L_0x003b
        L_0x002d:
            java.lang.String r6 = r2.toString()     // Catch:{ IOException -> 0x0041, all -> 0x002a }
            if (r5 == 0) goto L_0x0036
            r5.close()     // Catch:{ IOException -> 0x0036 }
        L_0x0036:
            return r6
        L_0x0037:
            r6 = move-exception
            goto L_0x003b
        L_0x0039:
            r5 = r0
            goto L_0x0041
        L_0x003b:
            if (r0 == 0) goto L_0x0040
            r0.close()     // Catch:{ IOException -> 0x0040 }
        L_0x0040:
            throw r6
        L_0x0041:
            if (r5 == 0) goto L_0x0046
            r5.close()     // Catch:{ IOException -> 0x0046 }
        L_0x0046:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.caverock.androidsvg.SimpleAssetResolver.getAssetAsString(java.lang.String):java.lang.String");
    }

    public boolean isFormatSupported(String str) {
        return supportedFormats.contains(str);
    }

    public String resolveCSSStyleSheet(String str) {
        Log.i(TAG, "resolveCSSStyleSheet(" + str + ")");
        return getAssetAsString(str);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:4|5|6) */
    /* JADX WARNING: Code restructure failed: missing block: B:5:?, code lost:
        r2 = r2.assetManager;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0048, code lost:
        return android.graphics.Typeface.createFromAsset(r2, r3 + ".otf");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0049, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x0031 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Typeface resolveFont(java.lang.String r3, int r4, java.lang.String r5) {
        /*
            r2 = this;
            java.lang.String r0 = "resolveFont("
            java.lang.String r1 = ","
            java.lang.StringBuilder r4 = android.support.v4.media.session.a.x(r0, r3, r1, r4, r1)
            r4.append(r5)
            java.lang.String r5 = ")"
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            java.lang.String r5 = "SimpleAssetResolver"
            android.util.Log.i(r5, r4)
            android.content.res.AssetManager r4 = r2.assetManager     // Catch:{ RuntimeException -> 0x0031 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ RuntimeException -> 0x0031 }
            r5.<init>()     // Catch:{ RuntimeException -> 0x0031 }
            r5.append(r3)     // Catch:{ RuntimeException -> 0x0031 }
            java.lang.String r0 = ".ttf"
            r5.append(r0)     // Catch:{ RuntimeException -> 0x0031 }
            java.lang.String r5 = r5.toString()     // Catch:{ RuntimeException -> 0x0031 }
            android.graphics.Typeface r2 = android.graphics.Typeface.createFromAsset(r4, r5)     // Catch:{ RuntimeException -> 0x0031 }
            return r2
        L_0x0031:
            android.content.res.AssetManager r2 = r2.assetManager     // Catch:{ RuntimeException -> 0x0049 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ RuntimeException -> 0x0049 }
            r4.<init>()     // Catch:{ RuntimeException -> 0x0049 }
            r4.append(r3)     // Catch:{ RuntimeException -> 0x0049 }
            java.lang.String r3 = ".otf"
            r4.append(r3)     // Catch:{ RuntimeException -> 0x0049 }
            java.lang.String r3 = r4.toString()     // Catch:{ RuntimeException -> 0x0049 }
            android.graphics.Typeface r2 = android.graphics.Typeface.createFromAsset(r2, r3)     // Catch:{ RuntimeException -> 0x0049 }
            return r2
        L_0x0049:
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.caverock.androidsvg.SimpleAssetResolver.resolveFont(java.lang.String, int, java.lang.String):android.graphics.Typeface");
    }

    public Bitmap resolveImage(String str) {
        Log.i(TAG, "resolveImage(" + str + ")");
        try {
            return BitmapFactory.decodeStream(this.assetManager.open(str));
        } catch (IOException unused) {
            return null;
        }
    }
}
