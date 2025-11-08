package com.appsamurai.storyly.data.managers.ad;

import com.appsamurai.storyly.ad.StorylyAdViewListener;
import com.appsamurai.storyly.data.v;

public final class a implements StorylyAdViewListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ b f3873a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ v f3874b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f3875c;

    public a(b bVar, v vVar, int i3) {
        this.f3873a = bVar;
        this.f3874b = vVar;
        this.f3875c = i3;
    }

    /* JADX WARNING: Removed duplicated region for block: B:113:0x01c4 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x01c1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onLoad(@org.jetbrains.annotations.NotNull com.appsamurai.storyly.ad.StorylyAdView r28) {
        /*
            r27 = this;
            r0 = r27
            r1 = r28
            java.lang.String r2 = "adView"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r2)
            com.appsamurai.storyly.data.managers.ad.b r2 = r0.f3873a
            java.util.List<com.appsamurai.storyly.ad.StorylyAdView> r2 = r2.f3883h
            r2.add(r1)
            com.appsamurai.storyly.data.managers.ad.b r2 = r0.f3873a
            com.appsamurai.storyly.data.v r3 = r0.f3874b
            int r4 = r0.f3875c
            monitor-enter(r2)
            int r5 = r2.f3880e     // Catch:{ all -> 0x0021 }
            if (r4 > r5) goto L_0x0024
            int r4 = r2.f3879d     // Catch:{ all -> 0x0021 }
            r2.a(r4, r5)     // Catch:{ all -> 0x0021 }
            goto L_0x0024
        L_0x0021:
            r0 = move-exception
            goto L_0x01f3
        L_0x0024:
            java.util.List<java.lang.String> r4 = r2.f3882g     // Catch:{ all -> 0x0021 }
            java.lang.String r5 = r3.f4221a     // Catch:{ all -> 0x0021 }
            boolean r4 = r4.contains(r5)     // Catch:{ all -> 0x0021 }
            r5 = 0
            if (r4 != 0) goto L_0x0055
            java.util.List<com.appsamurai.storyly.data.v> r3 = r2.f3878c     // Catch:{ all -> 0x0021 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x0021 }
        L_0x0035:
            boolean r4 = r3.hasNext()     // Catch:{ all -> 0x0021 }
            if (r4 == 0) goto L_0x0051
            java.lang.Object r4 = r3.next()     // Catch:{ all -> 0x0021 }
            r6 = r4
            com.appsamurai.storyly.data.v r6 = (com.appsamurai.storyly.data.v) r6     // Catch:{ all -> 0x0021 }
            java.lang.String r6 = r6.f4221a     // Catch:{ all -> 0x0021 }
            java.util.List<java.lang.String> r7 = r2.f3882g     // Catch:{ all -> 0x0021 }
            java.lang.Object r7 = kotlin.collections.CollectionsKt.firstOrNull(r7)     // Catch:{ all -> 0x0021 }
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r7)     // Catch:{ all -> 0x0021 }
            if (r6 == 0) goto L_0x0035
            goto L_0x0052
        L_0x0051:
            r4 = r5
        L_0x0052:
            r3 = r4
            com.appsamurai.storyly.data.v r3 = (com.appsamurai.storyly.data.v) r3     // Catch:{ all -> 0x0021 }
        L_0x0055:
            if (r3 != 0) goto L_0x0058
            goto L_0x005f
        L_0x0058:
            java.util.List<java.lang.String> r4 = r2.f3882g     // Catch:{ all -> 0x0021 }
            java.lang.String r6 = r3.f4221a     // Catch:{ all -> 0x0021 }
            r4.remove(r6)     // Catch:{ all -> 0x0021 }
        L_0x005f:
            monitor-exit(r2)
            if (r3 != 0) goto L_0x0063
            return
        L_0x0063:
            com.appsamurai.storyly.data.managers.ad.b r2 = r0.f3873a
            com.appsamurai.storyly.data.a r2 = r2.f3881f
            java.lang.String r4 = "adView"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r4)
            com.appsamurai.storyly.data.v r4 = new com.appsamurai.storyly.data.v
            java.lang.String r7 = "0"
            java.lang.String r8 = r28.getTitle()
            java.lang.String r9 = ""
            android.net.Uri r6 = r28.getIcon()
            java.lang.String r10 = r6.toString()
            java.lang.String r6 = "adView.getIcon().toString()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r6)
            java.lang.String r6 = "adView"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r6)
            com.appsamurai.storyly.data.z r6 = new com.appsamurai.storyly.data.z
            com.appsamurai.storyly.data.d0 r13 = new com.appsamurai.storyly.data.d0
            r13.<init>(r5, r5)
            java.lang.String r16 = r28.getTitle()
            com.appsamurai.storyly.StoryType r18 = com.appsamurai.storyly.StoryType.Ad
            com.appsamurai.storyly.ShareType r23 = com.appsamurai.storyly.ShareType.Disabled
            java.lang.String r12 = "0"
            r22 = 0
            r24 = 0
            r14 = 7000(0x1b58, double:3.4585E-320)
            r17 = 0
            r19 = 0
            r20 = 0
            r21 = 0
            r25 = 0
            r26 = 0
            r11 = r6
            r11.<init>(r12, r13, r14, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26)
            java.util.Map r11 = r28.getCustomData()
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>()
            if (r2 != 0) goto L_0x00bc
            goto L_0x01c8
        L_0x00bc:
            java.util.List<com.appsamurai.storyly.data.b0> r2 = r2.f3594d
            if (r2 != 0) goto L_0x00c2
            goto L_0x01c8
        L_0x00c2:
            java.util.ArrayList r13 = new java.util.ArrayList
            r13.<init>()
            java.util.Iterator r2 = r2.iterator()
        L_0x00cb:
            boolean r14 = r2.hasNext()
            if (r14 == 0) goto L_0x00e5
            java.lang.Object r14 = r2.next()
            com.appsamurai.storyly.data.b0 r14 = (com.appsamurai.storyly.data.b0) r14
            if (r14 != 0) goto L_0x00db
            r14 = r5
            goto L_0x00df
        L_0x00db:
            com.appsamurai.storyly.data.b0 r14 = r14.a()
        L_0x00df:
            if (r14 == 0) goto L_0x00cb
            r13.add(r14)
            goto L_0x00cb
        L_0x00e5:
            java.util.Iterator r2 = r13.iterator()
        L_0x00e9:
            boolean r5 = r2.hasNext()
            if (r5 == 0) goto L_0x01c8
            java.lang.Object r5 = r2.next()
            com.appsamurai.storyly.data.b0 r5 = (com.appsamurai.storyly.data.b0) r5
            com.appsamurai.storyly.data.a0 r13 = r5.f3615j
            boolean r14 = r13 instanceof com.appsamurai.storyly.data.x
            r15 = 1
            r16 = 0
            if (r14 == 0) goto L_0x0137
            com.appsamurai.storyly.data.x r13 = (com.appsamurai.storyly.data.x) r13
            com.appsamurai.storyly.data.x$b r14 = r13.f4271t
            int r14 = r14.ordinal()
            if (r14 == 0) goto L_0x0132
            if (r14 == r15) goto L_0x0132
            r15 = 2
            if (r14 == r15) goto L_0x010e
            goto L_0x0132
        L_0x010e:
            java.lang.String r14 = r13.f4253b
            if (r14 != 0) goto L_0x0114
            java.lang.String r14 = ""
        L_0x0114:
            java.lang.Object r14 = r11.get(r14)
            if (r14 == 0) goto L_0x011c
            r15 = 1
            goto L_0x0120
        L_0x011c:
            java.lang.String r14 = r13.f4253b
            r15 = r16
        L_0x0120:
            if (r14 == 0) goto L_0x012a
            java.lang.String r14 = (java.lang.String) r14
            r13.f4253b = r14
            r18 = r2
            goto L_0x01bf
        L_0x012a:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            java.lang.String r1 = "null cannot be cast to non-null type kotlin.String"
            r0.<init>(r1)
            throw r0
        L_0x0132:
            r18 = r2
        L_0x0134:
            r15 = 1
            goto L_0x01bf
        L_0x0137:
            boolean r14 = r13 instanceof com.appsamurai.storyly.data.o0
            if (r14 == 0) goto L_0x0161
            com.appsamurai.storyly.data.o0 r13 = (com.appsamurai.storyly.data.o0) r13
            java.lang.String r14 = r13.f4081a
            java.lang.Object r14 = r11.get(r14)
            if (r14 == 0) goto L_0x0147
            r15 = 1
            goto L_0x014b
        L_0x0147:
            java.lang.String r14 = r13.f4081a
            r15 = r16
        L_0x014b:
            if (r14 == 0) goto L_0x0159
            java.lang.String r14 = (java.lang.String) r14
            r18 = r2
            java.lang.String r2 = "<set-?>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r2)
            r13.f4081a = r14
            goto L_0x01bf
        L_0x0159:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            java.lang.String r1 = "null cannot be cast to non-null type kotlin.String"
            r0.<init>(r1)
            throw r0
        L_0x0161:
            r18 = r2
            boolean r2 = r13 instanceof com.appsamurai.storyly.data.p
            if (r2 == 0) goto L_0x018b
            com.appsamurai.storyly.data.p r13 = (com.appsamurai.storyly.data.p) r13
            java.lang.String r2 = r13.f4094a
            java.lang.Object r2 = r11.get(r2)
            if (r2 == 0) goto L_0x0173
            r15 = 1
            goto L_0x0177
        L_0x0173:
            java.lang.String r2 = r13.f4094a
            r15 = r16
        L_0x0177:
            if (r2 == 0) goto L_0x0183
            java.lang.String r2 = (java.lang.String) r2
            java.lang.String r14 = "<set-?>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r14)
            r13.f4094a = r2
            goto L_0x01bf
        L_0x0183:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            java.lang.String r1 = "null cannot be cast to non-null type kotlin.String"
            r0.<init>(r1)
            throw r0
        L_0x018b:
            boolean r2 = r13 instanceof com.appsamurai.storyly.data.n0
            if (r2 == 0) goto L_0x01b3
            com.appsamurai.storyly.data.n0 r13 = (com.appsamurai.storyly.data.n0) r13
            java.lang.String r2 = r13.f4059a
            java.lang.Object r2 = r11.get(r2)
            if (r2 == 0) goto L_0x019b
            r15 = 1
            goto L_0x019f
        L_0x019b:
            java.lang.String r2 = r13.f4059a
            r15 = r16
        L_0x019f:
            if (r2 == 0) goto L_0x01ab
            java.lang.String r2 = (java.lang.String) r2
            java.lang.String r14 = "<set-?>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r14)
            r13.f4059a = r2
            goto L_0x01bf
        L_0x01ab:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            java.lang.String r1 = "null cannot be cast to non-null type kotlin.String"
            r0.<init>(r1)
            throw r0
        L_0x01b3:
            boolean r2 = r13 instanceof com.appsamurai.storyly.data.o
            if (r2 == 0) goto L_0x01bd
            com.appsamurai.storyly.data.o r13 = (com.appsamurai.storyly.data.o) r13
            r13.f4078a = r1
            goto L_0x0134
        L_0x01bd:
            r15 = r16
        L_0x01bf:
            if (r15 == 0) goto L_0x01c4
            r12.add(r5)
        L_0x01c4:
            r2 = r18
            goto L_0x00e9
        L_0x01c8:
            com.appsamurai.storyly.data.d0 r1 = r6.f4303b
            r1.f3639a = r12
            com.appsamurai.storyly.data.z[] r1 = new com.appsamurai.storyly.data.z[]{r6}
            java.util.List r12 = kotlin.collections.CollectionsKt.mutableListOf(r1)
            com.appsamurai.storyly.StoryGroupType r14 = com.appsamurai.storyly.StoryGroupType.Ad
            r19 = 0
            r20 = 0
            r11 = 0
            r13 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r21 = 0
            r22 = 0
            r6 = r4
            r6.<init>(r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22)
            com.appsamurai.storyly.data.managers.ad.b r0 = r0.f3873a
            kotlin.jvm.functions.Function2<com.appsamurai.storyly.data.v, com.appsamurai.storyly.data.v, kotlin.Unit> r0 = r0.f3877b
            r0.invoke(r3, r4)
            return
        L_0x01f3:
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.managers.ad.a.onLoad(com.appsamurai.storyly.ad.StorylyAdView):void");
    }
}
