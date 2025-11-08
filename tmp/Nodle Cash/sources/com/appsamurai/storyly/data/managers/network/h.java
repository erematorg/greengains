package com.appsamurai.storyly.data.managers.network;

import android.content.Context;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.appsamurai.storyly.StoryComponent;
import com.appsamurai.storyly.analytics.e;
import com.appsamurai.storyly.data.b0;
import com.appsamurai.storyly.data.managers.product.STRCart;
import com.appsamurai.storyly.data.managers.product.STRCartItem;
import com.appsamurai.storyly.data.v;
import com.appsamurai.storyly.data.z;
import com.appsamurai.storyly.util.a;
import com.google.firebase.messaging.Constants;
import java.nio.charset.Charset;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.serialization.json.JsonElementBuildersKt;
import kotlinx.serialization.json.JsonObjectBuilder;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

public final class h {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public e f3923a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final RequestQueue f3924b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    public Function1<? super String, Unit> f3925c;
    @Nullable

    /* renamed from: d  reason: collision with root package name */
    public Function1<? super f, Unit> f3926d;

    public static final class a extends a {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ e f3927a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(e eVar, int i3, String str, Response.Listener<b> listener, Response.ErrorListener errorListener) {
            super(i3, str, (JSONObject) null, listener, errorListener);
            this.f3927a = eVar;
        }

        @NotNull
        public byte[] getBody() {
            String obj = this.f3927a.a().toString();
            Charset charset = Charsets.UTF_8;
            if (obj != null) {
                byte[] bytes = obj.getBytes(charset);
                Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
                return bytes;
            }
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }

        @NotNull
        public Map<String, String> getHeaders() {
            Map<String, String> mutableMapOf = MapsKt.mutableMapOf(TuplesKt.to("Content-Type", "application/json"), TuplesKt.to("Accept", "application/json"));
            mutableMapOf.putAll(this.f3927a.b());
            return mutableMapOf;
        }
    }

    public h(@NotNull Context context, @NotNull e eVar) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(eVar, "storylyTracker");
        this.f3923a = eVar;
        RequestQueue newRequestQueue = Volley.newRequestQueue(context);
        Intrinsics.checkNotNullExpressionValue(newRequestQueue, "newRequestQueue(context)");
        this.f3924b = newRequestQueue;
    }

    public final void a(e eVar) {
        a aVar = new a(eVar, eVar.f3917c, eVar.f3918d, new U.a(eVar, this), new U.a(this, eVar));
        aVar.setRetryPolicy(new DefaultRetryPolicy(10000, 0, 1.0f));
        aVar.setShouldCache(false);
        this.f3924b.add(aVar);
    }

    public final void b(@NotNull e eVar) {
        Intrinsics.checkNotNullParameter(eVar, "networkRequest");
        a(eVar);
    }

    /* JADX WARNING: type inference failed for: r2v6, types: [kotlin.Unit] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void a(com.appsamurai.storyly.data.managers.network.e r3, com.appsamurai.storyly.data.managers.network.h r4, com.appsamurai.storyly.data.managers.network.b r5) {
        /*
            java.lang.String r0 = "$networkRequest"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "this$0"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            int r0 = r5.f3910c
            r1 = 304(0x130, float:4.26E-43)
            r2 = 0
            if (r0 != r1) goto L_0x0032
            com.appsamurai.storyly.data.managers.processing.a r5 = r3.f3920f
            if (r5 != 0) goto L_0x0017
            goto L_0x0028
        L_0x0017:
            kotlin.jvm.functions.Function1<? super com.appsamurai.storyly.data.managers.network.f, kotlin.Unit> r0 = r4.f3926d
            if (r0 != 0) goto L_0x001c
            goto L_0x0028
        L_0x001c:
            com.appsamurai.storyly.data.managers.network.f r1 = new com.appsamurai.storyly.data.managers.network.f
            com.appsamurai.storyly.data.managers.processing.e r2 = r3.f3919e
            r1.<init>(r2, r5)
            r0.invoke(r1)
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
        L_0x0028:
            if (r2 != 0) goto L_0x0031
            java.lang.String r5 = "API data load failed:Local cache not found:304}"
            java.lang.String r0 = "Local cache not found:304"
            r4.a((com.appsamurai.storyly.data.managers.network.e) r3, (java.lang.String) r5, (java.lang.String) r0)
        L_0x0031:
            return
        L_0x0032:
            java.util.Map<java.lang.String, java.lang.String> r0 = r5.f3909b
            if (r0 != 0) goto L_0x0037
            goto L_0x0040
        L_0x0037:
            java.lang.String r1 = "Etag"
            java.lang.Object r0 = r0.get(r1)
            r2 = r0
            java.lang.String r2 = (java.lang.String) r2
        L_0x0040:
            kotlin.jvm.functions.Function1<? super com.appsamurai.storyly.data.managers.network.f, kotlin.Unit> r4 = r4.f3926d
            if (r4 != 0) goto L_0x0045
            goto L_0x005a
        L_0x0045:
            com.appsamurai.storyly.data.managers.network.f r0 = new com.appsamurai.storyly.data.managers.network.f
            com.appsamurai.storyly.data.managers.processing.e r3 = r3.f3919e
            com.appsamurai.storyly.data.managers.processing.a r1 = new com.appsamurai.storyly.data.managers.processing.a
            org.json.JSONObject r5 = r5.f3908a
            java.lang.String r5 = java.lang.String.valueOf(r5)
            r1.<init>(r5, r2)
            r0.<init>(r3, r1)
            r4.invoke(r0)
        L_0x005a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.managers.network.h.a(com.appsamurai.storyly.data.managers.network.e, com.appsamurai.storyly.data.managers.network.h, com.appsamurai.storyly.data.managers.network.b):void");
    }

    public static final void a(h hVar, e eVar, VolleyError volleyError) {
        Intrinsics.checkNotNullParameter(hVar, "this$0");
        Intrinsics.checkNotNullParameter(eVar, "$networkRequest");
        StringBuilder sb = new StringBuilder("API data load failed:");
        sb.append(volleyError);
        sb.append(AbstractJsonLexerKt.COLON);
        NetworkResponse networkResponse = volleyError.networkResponse;
        int i3 = 500;
        sb.append(networkResponse == null ? 500 : networkResponse.statusCode);
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(volleyError);
        sb3.append(AbstractJsonLexerKt.COLON);
        NetworkResponse networkResponse2 = volleyError.networkResponse;
        if (networkResponse2 != null) {
            i3 = networkResponse2.statusCode;
        }
        sb3.append(i3);
        hVar.a(eVar, sb2, sb3.toString());
    }

    public final void a(e eVar, String str, String str2) {
        String str3 = str;
        String str4 = str2;
        if (str4 != null) {
            e eVar2 = this.f3923a;
            com.appsamurai.storyly.analytics.a aVar = com.appsamurai.storyly.analytics.a.ApiLoadFailed;
            JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
            JsonElementBuildersKt.put(jsonObjectBuilder, Constants.IPC_BUNDLE_KEY_SEND_ERROR, str4);
            Unit unit = Unit.INSTANCE;
            e.a(eVar2, aVar, (v) null, (z) null, (b0) null, (StoryComponent) null, jsonObjectBuilder.build(), (Function1) null, eVar.c(), (Function1) null, (Function1) null, (STRCart) null, (STRCartItem) null, 3928);
        }
        a.C0055a.a(com.appsamurai.storyly.util.a.f6249a, str3, (String) null, 2);
        Function1<? super String, Unit> function1 = this.f3925c;
        if (function1 != null) {
            function1.invoke(str3);
        }
    }
}
