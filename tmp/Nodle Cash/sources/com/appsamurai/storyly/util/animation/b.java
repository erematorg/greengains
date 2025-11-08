package com.appsamurai.storyly.util.animation;

import android.content.Context;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class b extends View {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final List<c> f6266a = new ArrayList();
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public a f6267b = new a();
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    public com.appsamurai.storyly.util.animation.listeners.a f6268c;

    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public long f6269a = -1;
    }

    public b(@Nullable Context context) {
        super(context);
    }

    @NotNull
    public final List<c> getActiveSystems() {
        return this.f6266a;
    }

    @Nullable
    public final com.appsamurai.storyly.util.animation.listeners.a getOnParticleSystemUpdateListener() {
        return this.f6268c;
    }

    /* JADX WARNING: Removed duplicated region for block: B:49:0x0196  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x019e A[LOOP:1: B:13:0x0068->B:51:0x019e, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x01ad A[EDGE_INSN: B:65:0x01ad->B:53:0x01ad ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onDraw(@org.jetbrains.annotations.NotNull android.graphics.Canvas r20) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            java.lang.String r2 = "canvas"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r2)
            super.onDraw(r20)
            com.appsamurai.storyly.util.animation.b$a r3 = r0.f6267b
            long r4 = r3.f6269a
            r6 = -1
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 != 0) goto L_0x001c
            long r4 = java.lang.System.nanoTime()
            r3.f6269a = r4
        L_0x001c:
            long r4 = java.lang.System.nanoTime()
            long r8 = r3.f6269a
            long r8 = r4 - r8
            r10 = 1000000(0xf4240, float:1.401298E-39)
            long r10 = (long) r10
            long r8 = r8 / r10
            r3.f6269a = r4
            float r3 = (float) r8
            r4 = 1000(0x3e8, float:1.401E-42)
            float r4 = (float) r4
            float r3 = r3 / r4
            java.util.List<com.appsamurai.storyly.util.animation.c> r5 = r0.f6266a
            int r5 = r5.size()
            int r5 = r5 + -1
            if (r5 < 0) goto L_0x01d2
        L_0x003a:
            int r8 = r5 + -1
            java.util.List<com.appsamurai.storyly.util.animation.c> r9 = r0.f6266a
            java.lang.Object r9 = r9.get(r5)
            com.appsamurai.storyly.util.animation.c r9 = (com.appsamurai.storyly.util.animation.c) r9
            com.appsamurai.storyly.util.animation.emitters.c r10 = r9.f6277h
            if (r10 == 0) goto L_0x0049
            goto L_0x004f
        L_0x0049:
            java.lang.String r10 = "renderSystem"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r10)
            r10 = 0
        L_0x004f:
            r10.getClass()
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r2)
            boolean r11 = r10.f6288h
            if (r11 == 0) goto L_0x005e
            com.appsamurai.storyly.util.animation.emitters.b r11 = r10.f6287g
            r11.a((float) r3)
        L_0x005e:
            java.util.List<com.appsamurai.storyly.util.animation.a> r11 = r10.f6291k
            int r11 = r11.size()
            int r11 = r11 + -1
            if (r11 < 0) goto L_0x01a8
        L_0x0068:
            int r12 = r11 + -1
            java.util.List<com.appsamurai.storyly.util.animation.a> r13 = r10.f6291k
            java.lang.Object r13 = r13.get(r11)
            com.appsamurai.storyly.util.animation.a r13 = (com.appsamurai.storyly.util.animation.a) r13
            com.appsamurai.storyly.util.animation.models.d r14 = r10.f6290j
            r13.getClass()
            java.lang.String r15 = "force"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r15)
            r15 = 0
            r6 = 3
            com.appsamurai.storyly.util.animation.models.d r7 = com.appsamurai.storyly.util.animation.models.d.a(r14, r15, r15, r6)
            float r14 = r13.f6258i
            float r6 = r7.f6301a
            float r6 = r6 / r14
            r7.f6301a = r6
            float r6 = r7.f6302b
            float r6 = r6 / r14
            r7.f6302b = r6
            com.appsamurai.storyly.util.animation.models.d r6 = r13.f6254e
            r6.a(r7)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r2)
            boolean r6 = r13.f6256g
            if (r6 == 0) goto L_0x00af
            com.appsamurai.storyly.util.animation.models.d r6 = r13.f6254e
            float r7 = r6.f6302b
            float r14 = r13.f6257h
            int r7 = (r7 > r14 ? 1 : (r7 == r14 ? 0 : -1))
            if (r7 < 0) goto L_0x00aa
            r7 = -1082130432(0xffffffffbf800000, float:-1.0)
            int r7 = (r14 > r7 ? 1 : (r14 == r7 ? 0 : -1))
            if (r7 != 0) goto L_0x00af
        L_0x00aa:
            com.appsamurai.storyly.util.animation.models.d r7 = r13.f6255f
            r7.a(r6)
        L_0x00af:
            com.appsamurai.storyly.util.animation.models.d r6 = r13.f6255f
            r7 = 3
            com.appsamurai.storyly.util.animation.models.d r6 = com.appsamurai.storyly.util.animation.models.d.a(r6, r15, r15, r7)
            float r7 = r13.f6264o
            float r7 = r7 * r3
            float r14 = r6.f6301a
            float r14 = r14 * r7
            r6.f6301a = r14
            float r14 = r6.f6302b
            float r14 = r14 * r7
            r6.f6302b = r14
            com.appsamurai.storyly.util.animation.models.d r7 = r13.f6250a
            r7.a(r6)
            long r6 = r13.f6252c
            r14 = r8
            r16 = r9
            r8 = 0
            int r17 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r17 > 0) goto L_0x00f1
            boolean r6 = r13.f6253d
            r7 = 0
            if (r6 == 0) goto L_0x00ee
            r6 = 5
            float r6 = (float) r6
            float r6 = r6 * r3
            float r8 = r13.f6264o
            float r6 = r6 * r8
            int r8 = r13.f6265p
            float r9 = (float) r8
            float r9 = r9 - r6
            int r9 = (r9 > r15 ? 1 : (r9 == r15 ? 0 : -1))
            if (r9 >= 0) goto L_0x00e9
            r13.f6265p = r7
            goto L_0x00f7
        L_0x00e9:
            int r6 = (int) r6
            int r8 = r8 - r6
            r13.f6265p = r8
            goto L_0x00f7
        L_0x00ee:
            r13.f6265p = r7
            goto L_0x00f7
        L_0x00f1:
            float r8 = r3 * r4
            long r8 = (long) r8
            long r6 = r6 - r8
            r13.f6252c = r6
        L_0x00f7:
            float r6 = r13.f6261l
            float r6 = r6 * r3
            float r7 = r13.f6264o
            float r6 = r6 * r7
            float r7 = r13.f6262m
            float r7 = r7 + r6
            r13.f6262m = r7
            r8 = 1135869952(0x43b40000, float:360.0)
            int r7 = (r7 > r8 ? 1 : (r7 == r8 ? 0 : -1))
            if (r7 < 0) goto L_0x010a
            r13.f6262m = r15
        L_0x010a:
            float r7 = r13.f6263n
            float r7 = r7 - r6
            r13.f6263n = r7
            int r6 = (r7 > r15 ? 1 : (r7 == r15 ? 0 : -1))
            if (r6 >= 0) goto L_0x0117
            float r6 = r13.f6259j
            r13.f6263n = r6
        L_0x0117:
            com.appsamurai.storyly.util.animation.models.d r6 = r13.f6250a
            float r6 = r6.f6302b
            int r7 = r20.getHeight()
            float r7 = (float) r7
            int r6 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r6 <= 0) goto L_0x012b
            r6 = 0
            r13.f6252c = r6
        L_0x0128:
            r18 = r2
            goto L_0x018e
        L_0x012b:
            com.appsamurai.storyly.util.animation.models.d r6 = r13.f6250a
            float r6 = r6.f6301a
            int r7 = r20.getWidth()
            float r7 = (float) r7
            int r6 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r6 > 0) goto L_0x0128
            com.appsamurai.storyly.util.animation.models.d r6 = r13.f6250a
            float r7 = r6.f6301a
            float r8 = r13.f6259j
            float r7 = r7 + r8
            int r7 = (r7 > r15 ? 1 : (r7 == r15 ? 0 : -1))
            if (r7 < 0) goto L_0x0128
            float r6 = r6.f6302b
            float r6 = r6 + r8
            int r6 = (r6 > r15 ? 1 : (r6 == r15 ? 0 : -1))
            if (r6 >= 0) goto L_0x014b
            goto L_0x0128
        L_0x014b:
            android.graphics.Paint r6 = r13.f6260k
            int r7 = r13.f6265p
            r6.setAlpha(r7)
            float r6 = r13.f6263n
            float r7 = r13.f6259j
            float r6 = r6 / r7
            r7 = 1056964608(0x3f000000, float:0.5)
            float r6 = r6 - r7
            float r6 = java.lang.Math.abs(r6)
            r7 = 2
            float r7 = (float) r7
            float r6 = r6 * r7
            float r8 = r13.f6259j
            float r8 = r8 * r6
            float r8 = r8 / r7
            int r9 = r20.save()
            com.appsamurai.storyly.util.animation.models.d r15 = r13.f6250a
            r18 = r2
            float r2 = r15.f6301a
            float r2 = r2 - r8
            float r15 = r15.f6302b
            r1.translate(r2, r15)
            float r2 = r13.f6262m
            float r15 = r13.f6259j
            float r15 = r15 / r7
            r1.rotate(r2, r8, r15)
            r2 = 1065353216(0x3f800000, float:1.0)
            r1.scale(r6, r2)
            com.appsamurai.storyly.util.animation.models.b r2 = r13.f6251b
            android.graphics.Paint r6 = r13.f6260k
            float r7 = r13.f6259j
            r2.a(r1, r6, r7)
            r1.restoreToCount(r9)
        L_0x018e:
            int r2 = r13.f6265p
            float r2 = (float) r2
            r6 = 0
            int r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r2 > 0) goto L_0x019b
            java.util.List<com.appsamurai.storyly.util.animation.a> r2 = r10.f6291k
            r2.remove(r11)
        L_0x019b:
            if (r12 >= 0) goto L_0x019e
            goto L_0x01ad
        L_0x019e:
            r11 = r12
            r8 = r14
            r9 = r16
            r2 = r18
            r6 = -1
            goto L_0x0068
        L_0x01a8:
            r18 = r2
            r14 = r8
            r16 = r9
        L_0x01ad:
            boolean r2 = r16.a()
            if (r2 == 0) goto L_0x01c8
            java.util.List<com.appsamurai.storyly.util.animation.c> r2 = r0.f6266a
            r2.remove(r5)
            com.appsamurai.storyly.util.animation.listeners.a r2 = r0.f6268c
            if (r2 != 0) goto L_0x01bd
            goto L_0x01c8
        L_0x01bd:
            java.util.List<com.appsamurai.storyly.util.animation.c> r5 = r0.f6266a
            int r5 = r5.size()
            r9 = r16
            r2.b(r0, r9, r5)
        L_0x01c8:
            if (r14 >= 0) goto L_0x01cb
            goto L_0x01d2
        L_0x01cb:
            r5 = r14
            r2 = r18
            r6 = -1
            goto L_0x003a
        L_0x01d2:
            java.util.List<com.appsamurai.storyly.util.animation.c> r1 = r0.f6266a
            int r1 = r1.size()
            if (r1 == 0) goto L_0x01de
            r19.invalidate()
            goto L_0x01e4
        L_0x01de:
            com.appsamurai.storyly.util.animation.b$a r0 = r0.f6267b
            r1 = -1
            r0.f6269a = r1
        L_0x01e4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.util.animation.b.onDraw(android.graphics.Canvas):void");
    }

    public final void setOnParticleSystemUpdateListener(@Nullable com.appsamurai.storyly.util.animation.listeners.a aVar) {
        this.f6268c = aVar;
    }
}
