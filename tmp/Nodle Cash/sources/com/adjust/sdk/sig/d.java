package com.adjust.sdk.sig;

import android.content.Context;
import android.util.Log;
import com.adjust.sdk.AdjustConfig;
import com.google.common.base.Ascii;
import java.security.InvalidKeyException;
import java.security.UnrecoverableKeyException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class d {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f3346a = false;

    public static void a(Set set, Map map, Map map2) {
        Iterator it = set.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (map.containsKey(str)) {
                map2.put(str, (String) map.get(str));
            }
        }
    }

    public static void a(Context context, c cVar, a aVar, Map map, String str, String str2) {
        byte[] bArr;
        if (f3346a) {
            Log.e("SignerInstance", "sign: library received error. It has locked down");
        } else if (map == null || map.size() == 0 || str == null || str2 == null) {
            Log.e("SignerInstance", "sign: One or more parameters are null");
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            boolean equals = AdjustConfig.ENVIRONMENT_SANDBOX.equals(map.get("environment"));
            if (equals) {
                Log.v("SignerInstance", "Signing all the parameters begin: " + simpleDateFormat.format(new Date(System.currentTimeMillis())));
            }
            map.put("activity_kind", str);
            map.put("client_sdk", str2);
            int i3 = 2;
            while (true) {
                if (i3 <= 0) {
                    bArr = null;
                    break;
                }
                try {
                    cVar.b(context);
                    bArr = cVar.a(context, map.toString().getBytes("UTF-8"));
                    break;
                } catch (b e3) {
                    Log.e("SignerInstance", "sign: Api is less than JellyBean-4-18");
                    f3346a = true;
                    map.remove("activity_kind");
                    map.remove("client_sdk");
                    throw e3;
                } catch (InvalidKeyException | UnrecoverableKeyException e4) {
                    Log.e("SignerInstance", "sign: Received a retriable exception: " + e4.getMessage(), e4);
                    Log.e("SignerInstance", "sign: Attempting retry #" + i3);
                    i3 += -1;
                    cVar.a(context);
                } catch (Exception e5) {
                    Log.e("SignerInstance", "sign: Received an Exception: " + e5.getMessage(), e5);
                    map.remove("activity_kind");
                    map.remove("client_sdk");
                    throw e5;
                }
            }
            if (i3 == 0) {
                f3346a = true;
                map.remove("activity_kind");
                map.remove("client_sdk");
                return;
            }
            if (equals) {
                Log.v("SignerInstance", "Calling native begin: " + simpleDateFormat.format(new Date(System.currentTimeMillis())));
            }
            byte[] a2 = ((NativeLibHelper) aVar).a(context, map, bArr, cVar.f3345a);
            if (equals) {
                Log.v("SignerInstance", "Calling native end  : " + simpleDateFormat.format(new Date(System.currentTimeMillis())));
            }
            if (a2 == null) {
                Log.e("SignerInstance", "sign: Returned an null signature. Exiting...");
                map.remove("activity_kind");
                map.remove("client_sdk");
                return;
            }
            int length = a2.length;
            char[] cArr = e.f3347a;
            char[] cArr2 = new char[(length * 2)];
            for (int i4 = 0; i4 < length; i4++) {
                byte b3 = a2[i4];
                int i5 = i4 * 2;
                char[] cArr3 = e.f3347a;
                cArr2[i5] = cArr3[(b3 & 255) >>> 4];
                cArr2[i5 + 1] = cArr3[b3 & Ascii.SI];
            }
            map.put("signature", new String(cArr2));
            map.remove("activity_kind");
            map.remove("client_sdk");
            if (equals) {
                Log.v("SignerInstance", "Signing all the parameters end  : " + simpleDateFormat.format(new Date(System.currentTimeMillis())));
            }
        }
    }
}
