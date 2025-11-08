package com.appsamurai.storyly.util.font;

import android.content.Context;
import android.graphics.Typeface;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.appsamurai.storyly.util.a;
import java.io.File;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.jetbrains.annotations.NotNull;

public final class f {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6321a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final RequestQueue f6322b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public final Lazy f6323c = LazyKt.lazy(new a(this));

    public static final class a extends Lambda implements Function0<File> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ f f6324a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(f fVar) {
            super(0);
            this.f6324a = fVar;
        }

        public Object invoke() {
            File file = new File(this.f6324a.f6321a.getCacheDir(), "stry-fonts");
            if (!file.exists()) {
                file.mkdir();
            }
            return file;
        }
    }

    public f(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f6321a = context;
        RequestQueue newRequestQueue = Volley.newRequestQueue(context);
        Intrinsics.checkNotNullExpressionValue(newRequestQueue, "newRequestQueue(context)");
        this.f6322b = newRequestQueue;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00b3  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00c1  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object a(com.appsamurai.storyly.util.font.f r10, com.appsamurai.storyly.util.font.h r11, kotlin.coroutines.Continuation r12) {
        /*
            r10.getClass()
            boolean r0 = r12 instanceof com.appsamurai.storyly.util.font.g
            if (r0 == 0) goto L_0x0016
            r0 = r12
            com.appsamurai.storyly.util.font.g r0 = (com.appsamurai.storyly.util.font.g) r0
            int r1 = r0.f6330f
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0016
            int r1 = r1 - r2
            r0.f6330f = r1
            goto L_0x001b
        L_0x0016:
            com.appsamurai.storyly.util.font.g r0 = new com.appsamurai.storyly.util.font.g
            r0.<init>(r10, r12)
        L_0x001b:
            java.lang.Object r12 = r0.f6328d
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.f6330f
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x0044
            if (r2 != r4) goto L_0x003c
            java.lang.Object r10 = r0.f6327c
            java.lang.String r10 = (java.lang.String) r10
            java.lang.Object r11 = r0.f6326b
            com.appsamurai.storyly.util.font.h r11 = (com.appsamurai.storyly.util.font.h) r11
            java.lang.Object r0 = r0.f6325a
            com.appsamurai.storyly.util.font.f r0 = (com.appsamurai.storyly.util.font.f) r0
            kotlin.ResultKt.throwOnFailure(r12)
            r2 = r12
            r12 = r10
            r10 = r0
            goto L_0x00ae
        L_0x003c:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x0044:
            kotlin.ResultKt.throwOnFailure(r12)
            java.lang.String r12 = r11.f6331a
            android.net.Uri r12 = android.net.Uri.parse(r12)
            java.lang.String r12 = r12.getLastPathSegment()
            if (r12 != 0) goto L_0x0056
        L_0x0053:
            r1 = r3
            goto L_0x00fa
        L_0x0056:
            android.graphics.Typeface r2 = r10.a(r12)
            if (r2 != 0) goto L_0x00f9
            java.lang.String r2 = r11.f6331a
            r0.f6325a = r10
            r0.f6326b = r11
            r0.f6327c = r12
            r0.f6330f = r4
            kotlinx.coroutines.CancellableContinuationImpl r5 = new kotlinx.coroutines.CancellableContinuationImpl
            kotlin.coroutines.Continuation r6 = kotlin.coroutines.intrinsics.IntrinsicsKt.intercepted(r0)
            r5.<init>(r6, r4)
            r5.initCancellability()
            com.appsamurai.storyly.util.font.a r6 = new com.appsamurai.storyly.util.font.a
            com.appsamurai.storyly.util.font.c r7 = new com.appsamurai.storyly.util.font.c
            r7.<init>(r5)
            com.appsamurai.storyly.util.font.d r8 = new com.appsamurai.storyly.util.font.d
            r8.<init>(r5)
            r9 = 0
            r6.<init>(r9, r2, r7, r8)
            r6.setShouldCache(r9)
            com.android.volley.DefaultRetryPolicy r2 = new com.android.volley.DefaultRetryPolicy
            r7 = 5000(0x1388, float:7.006E-42)
            r8 = 1065353216(0x3f800000, float:1.0)
            r2.<init>(r7, r4, r8)
            r6.setRetryPolicy(r2)
            com.android.volley.RequestQueue r2 = r10.f6322b
            r2.add(r6)
            com.appsamurai.storyly.util.font.b r2 = new com.appsamurai.storyly.util.font.b
            r2.<init>(r6)
            r5.invokeOnCancellation(r2)
            java.lang.Object r2 = r5.getResult()
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r2 != r4) goto L_0x00ab
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r0)
        L_0x00ab:
            if (r2 != r1) goto L_0x00ae
            goto L_0x00fa
        L_0x00ae:
            byte[] r2 = (byte[]) r2
            r0 = 2
            if (r2 != 0) goto L_0x00c1
            com.appsamurai.storyly.util.a$a r10 = com.appsamurai.storyly.util.a.f6249a
            java.lang.String r11 = r11.f6331a
            java.lang.String r12 = "not valid file "
            java.lang.String r11 = kotlin.jvm.internal.Intrinsics.stringPlus(r12, r11)
            com.appsamurai.storyly.util.a.C0055a.a(r10, r11, r3, r0)
            goto L_0x0053
        L_0x00c1:
            r10.getClass()
            java.io.File r11 = new java.io.File     // Catch:{ Exception -> 0x00e4 }
            kotlin.Lazy r1 = r10.f6323c     // Catch:{ Exception -> 0x00e4 }
            java.lang.Object r1 = r1.getValue()     // Catch:{ Exception -> 0x00e4 }
            java.io.File r1 = (java.io.File) r1     // Catch:{ Exception -> 0x00e4 }
            r11.<init>(r1, r12)     // Catch:{ Exception -> 0x00e4 }
            boolean r1 = r11.exists()     // Catch:{ Exception -> 0x00e4 }
            if (r1 == 0) goto L_0x00d8
            goto L_0x00f4
        L_0x00d8:
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x00e4 }
            r1.<init>(r11)     // Catch:{ Exception -> 0x00e4 }
            r1.write(r2)     // Catch:{ Exception -> 0x00e4 }
            r1.close()     // Catch:{ Exception -> 0x00e4 }
            goto L_0x00f4
        L_0x00e4:
            r11 = move-exception
            com.appsamurai.storyly.util.a$a r1 = com.appsamurai.storyly.util.a.f6249a
            java.lang.String r11 = r11.getLocalizedMessage()
            java.lang.String r2 = "cannot write local font "
            java.lang.String r11 = kotlin.jvm.internal.Intrinsics.stringPlus(r2, r11)
            com.appsamurai.storyly.util.a.C0055a.a(r1, r11, r3, r0)
        L_0x00f4:
            android.graphics.Typeface r1 = r10.a(r12)
            goto L_0x00fa
        L_0x00f9:
            r1 = r2
        L_0x00fa:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.util.font.f.a(com.appsamurai.storyly.util.font.f, com.appsamurai.storyly.util.font.h, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Typeface a(String str) {
        File file = new File((File) this.f6323c.getValue(), str);
        if (file.exists()) {
            try {
                return Typeface.createFromFile(file);
            } catch (Exception e3) {
                a.C0055a aVar = com.appsamurai.storyly.util.a.f6249a;
                a.C0055a.a(aVar, "cannot create typeface " + e3.getLocalizedMessage() + AbstractJsonLexerKt.END_OBJ, (String) null, 2);
            }
        }
        a.C0055a.a(com.appsamurai.storyly.util.a.f6249a, Intrinsics.stringPlus("cannot read local font - not found ", file.getAbsoluteFile()), (String) null, 2);
        return null;
    }
}
