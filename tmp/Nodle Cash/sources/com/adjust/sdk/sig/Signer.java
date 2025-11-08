package com.adjust.sdk.sig;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.adjust.sdk.AdjustConfig;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;

public class Signer {

    /* renamed from: a  reason: collision with root package name */
    public boolean f3341a = false;

    /* renamed from: b  reason: collision with root package name */
    public d f3342b;

    /* renamed from: c  reason: collision with root package name */
    public a f3343c;

    /* renamed from: d  reason: collision with root package name */
    public c f3344d;

    public static String getVersion() {
        return "3.61.0";
    }

    public final synchronized void a() {
        if (!this.f3341a) {
            this.f3342b = new d();
            this.f3344d = new c(Build.VERSION.SDK_INT);
            this.f3343c = new NativeLibHelper();
            this.f3341a = true;
        }
    }

    public synchronized void onResume() {
        a();
        d dVar = this.f3342b;
        a aVar = this.f3343c;
        dVar.getClass();
        if (!d.f3346a) {
            ((NativeLibHelper) aVar).a();
        }
    }

    public synchronized void sign(Context context, Map<String, String> map, String str, String str2) {
        a();
        d dVar = this.f3342b;
        c cVar = this.f3344d;
        a aVar = this.f3343c;
        dVar.getClass();
        d.a(context, cVar, aVar, map, str, str2);
    }

    public synchronized void sign(Context context, Map<String, String> map, Map<String, String> map2, Map<String, String> map3) {
        try {
            a();
            d dVar = this.f3342b;
            c cVar = this.f3344d;
            a aVar = this.f3343c;
            dVar.getClass();
            if (!(map == null || map.size() == 0 || map2 == null)) {
                if (map3 != null) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
                    boolean equals = AdjustConfig.ENVIRONMENT_SANDBOX.equals(map.get("environment"));
                    if (equals) {
                        Log.v("SignerInstance", "SDKv5 Signing all the parameters begin: " + simpleDateFormat.format(new Date(System.currentTimeMillis())));
                    }
                    HashMap hashMap = new HashMap();
                    d.a(map.keySet(), map, hashMap);
                    String str = map2.get("activity_kind");
                    String str2 = map2.get("client_sdk");
                    if (!"b".equals(map2.get("a"))) {
                        d.a(context, cVar, aVar, hashMap, str, str2);
                        if (hashMap.containsKey("signature") && hashMap.containsKey("adj_signing_id") && hashMap.containsKey("headers_id") && hashMap.containsKey("algorithm")) {
                            if (hashMap.containsKey("native_version")) {
                                Locale locale = Locale.US;
                                String str3 = "algorithm=\"" + ((String) hashMap.get("algorithm")) + "\"";
                                map3.put("authorization", "Signature " + ("signature=\"" + ((String) hashMap.get("signature")) + "\"") + "," + ("adj_signing_id=\"" + ((String) hashMap.get("adj_signing_id")) + "\"") + "," + str3 + "," + ("headers_id=\"" + ((String) hashMap.get("headers_id")) + "\"") + "," + ("native_version=\"" + ((String) hashMap.get("native_version")) + "\""));
                            }
                        }
                        Log.e("SignerInstance", "sign: Signature generation failed. Exiting...");
                    }
                    d.a(map.keySet(), map, map3);
                    d.a(new HashSet(Arrays.asList(new String[]{"network_payload", "endpoint"})), map2, map3);
                    if (equals) {
                        Log.v("SignerInstance", "SDKv5 Signing all the parameters end  : " + simpleDateFormat.format(new Date(System.currentTimeMillis())));
                    }
                }
            }
            Log.e("SignerInstance", "sign: One or more parameters are null");
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }
}
