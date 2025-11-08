package com.appsamurai.storyly.storylypresenter.storylylayer;

import com.appsamurai.storyly.data.b0;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;

public final /* synthetic */ class a0 extends FunctionReferenceImpl implements Function2<List<? extends b0>, List<? extends b0>, Unit> {
    public a0(Object obj) {
        super(2, obj, x.class, "updateLayers", "updateLayers(Ljava/util/List;Ljava/util/List;)V", 0);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: com.appsamurai.storyly.data.o} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: com.appsamurai.storyly.data.q} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v9, resolved type: com.appsamurai.storyly.data.i0} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v12, resolved type: com.appsamurai.storyly.data.z} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v14, resolved type: com.appsamurai.storyly.data.k0} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v16, resolved type: com.appsamurai.storyly.data.e0} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v19, resolved type: com.appsamurai.storyly.data.z} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v21, resolved type: com.appsamurai.storyly.data.t} */
    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r3v3 */
    /* JADX WARNING: type inference failed for: r3v4, types: [kotlin.jvm.functions.Function1] */
    /* JADX WARNING: type inference failed for: r3v5 */
    /* JADX WARNING: type inference failed for: r3v13 */
    /* JADX WARNING: type inference failed for: r3v15 */
    /* JADX WARNING: type inference failed for: r3v18 */
    /* JADX WARNING: type inference failed for: r3v20 */
    /* JADX WARNING: type inference failed for: r3v25 */
    /* JADX WARNING: type inference failed for: r3v26 */
    /* JADX WARNING: type inference failed for: r3v27 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object invoke(java.lang.Object r7, java.lang.Object r8) {
        /*
            r6 = this;
            java.util.List r7 = (java.util.List) r7
            java.util.List r8 = (java.util.List) r8
            java.lang.String r0 = "p0"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.lang.String r0 = "p1"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            java.lang.Object r6 = r6.receiver
            com.appsamurai.storyly.storylypresenter.storylylayer.x r6 = (com.appsamurai.storyly.storylypresenter.storylylayer.x) r6
            r6.getClass()
            java.util.Iterator r8 = r8.iterator()
        L_0x0019:
            boolean r0 = r8.hasNext()
            if (r0 == 0) goto L_0x0033
            java.lang.Object r0 = r8.next()
            com.appsamurai.storyly.data.b0 r0 = (com.appsamurai.storyly.data.b0) r0
            com.appsamurai.storyly.storylypresenter.storylylayer.x$a r1 = r6.f6222u
            if (r1 != 0) goto L_0x002a
            goto L_0x0019
        L_0x002a:
            com.appsamurai.storyly.storylypresenter.storylylayer.m1 r2 = new com.appsamurai.storyly.storylypresenter.storylylayer.m1
            r2.<init>(r0, r6)
            r1.b(r2)
            goto L_0x0019
        L_0x0033:
            boolean r8 = r6.f6221t
            if (r8 == 0) goto L_0x004d
            java.util.concurrent.atomic.AtomicInteger r8 = new java.util.concurrent.atomic.AtomicInteger
            int r0 = r7.size()
            r8.<init>(r0)
            r6.f6218q = r8
            java.util.concurrent.atomic.AtomicInteger r8 = new java.util.concurrent.atomic.AtomicInteger
            int r0 = r7.size()
            r8.<init>(r0)
            r6.f6219r = r8
        L_0x004d:
            java.util.Iterator r7 = r7.iterator()
        L_0x0051:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto L_0x05d2
            java.lang.Object r8 = r7.next()
            com.appsamurai.storyly.data.b0 r8 = (com.appsamurai.storyly.data.b0) r8
            boolean r0 = r6.f6220s
            if (r0 == 0) goto L_0x006e
            com.appsamurai.storyly.storylypresenter.storylylayer.x$a r6 = r6.f6222u
            if (r6 != 0) goto L_0x0067
            goto L_0x05d2
        L_0x0067:
            com.appsamurai.storyly.storylypresenter.storylylayer.n1 r7 = com.appsamurai.storyly.storylypresenter.storylylayer.n1.f5889a
            r6.b(r7)
            goto L_0x05d2
        L_0x006e:
            com.appsamurai.storyly.data.a0 r0 = r8.f3615j
            boolean r1 = r0 instanceof com.appsamurai.storyly.data.n0
            if (r1 == 0) goto L_0x0093
            com.appsamurai.storyly.storylypresenter.storylylayer.d2 r0 = new com.appsamurai.storyly.storylypresenter.storylylayer.d2
            android.content.Context r1 = r6.f6202a
            com.appsamurai.storyly.config.StorylyConfig r2 = r6.f6204c
            r0.<init>(r1, r2)
            r6.a((com.appsamurai.storyly.data.b0) r8, (com.appsamurai.storyly.storylypresenter.storylylayer.o1) r0)
            kotlin.jvm.functions.Function1 r1 = r6.c()
            r0.setOnUserActionClick$storyly_release(r1)
            com.appsamurai.storyly.storylypresenter.storylylayer.c1 r1 = new com.appsamurai.storyly.storylypresenter.storylylayer.c1
            r1.<init>(r6, r0)
            r0.setOnLayerLoad$storyly_release(r1)
            r0.a((com.appsamurai.storyly.data.b0) r8)
            goto L_0x0051
        L_0x0093:
            boolean r1 = r0 instanceof com.appsamurai.storyly.data.p
            if (r1 == 0) goto L_0x00b6
            com.appsamurai.storyly.storylypresenter.storylylayer.h r0 = new com.appsamurai.storyly.storylypresenter.storylylayer.h
            android.content.Context r1 = r6.f6202a
            com.appsamurai.storyly.config.StorylyConfig r2 = r6.f6204c
            r0.<init>(r1, r2)
            r6.a((com.appsamurai.storyly.data.b0) r8, (com.appsamurai.storyly.storylypresenter.storylylayer.o1) r0)
            kotlin.jvm.functions.Function1 r1 = r6.c()
            r0.setOnUserActionClick$storyly_release(r1)
            com.appsamurai.storyly.storylypresenter.storylylayer.e0 r1 = new com.appsamurai.storyly.storylypresenter.storylylayer.e0
            r1.<init>(r6, r0)
            r0.setOnLayerLoad$storyly_release(r1)
            r0.a((com.appsamurai.storyly.data.b0) r8)
            goto L_0x0051
        L_0x00b6:
            boolean r1 = r0 instanceof com.appsamurai.storyly.data.o0
            if (r1 == 0) goto L_0x00d5
            com.appsamurai.storyly.storylypresenter.storylylayer.g2 r0 = new com.appsamurai.storyly.storylypresenter.storylylayer.g2
            android.content.Context r1 = r6.f6202a
            com.appsamurai.storyly.config.StorylyConfig r2 = r6.f6204c
            com.appsamurai.storyly.data.v r3 = r6.f6206e
            r0.<init>(r1, r2, r3)
            r6.a((com.appsamurai.storyly.data.b0) r8, (com.appsamurai.storyly.storylypresenter.storylylayer.o1) r0)
            com.appsamurai.storyly.storylypresenter.storylylayer.d1 r1 = new com.appsamurai.storyly.storylypresenter.storylylayer.d1
            r1.<init>(r6, r0)
            r0.setOnLayerLoad$storyly_release(r1)
            r0.a((com.appsamurai.storyly.data.b0) r8)
            goto L_0x0051
        L_0x00d5:
            boolean r1 = r0 instanceof com.appsamurai.storyly.data.t
            java.lang.String r2 = "storylyLayerItem"
            r3 = 0
            if (r1 == 0) goto L_0x0123
            com.appsamurai.storyly.storylypresenter.storylylayer.p r0 = new com.appsamurai.storyly.storylypresenter.storylylayer.p
            android.content.Context r1 = r6.f6202a
            com.appsamurai.storyly.config.StorylyConfig r4 = r6.f6204c
            r0.<init>(r1, r4)
            r6.a((com.appsamurai.storyly.data.b0) r8, (com.appsamurai.storyly.storylypresenter.storylylayer.o1) r0)
            com.appsamurai.storyly.storylypresenter.storylylayer.i0 r1 = new com.appsamurai.storyly.storylypresenter.storylylayer.i0
            r1.<init>(r6, r0)
            r0.setOnLayerLoad$storyly_release(r1)
            com.appsamurai.storyly.storylypresenter.storylylayer.j0 r1 = new com.appsamurai.storyly.storylypresenter.storylylayer.j0
            r1.<init>(r6)
            r0.setOnLayerLoadFail$storyly_release(r1)
            kotlin.jvm.functions.Function5 r1 = r6.f()
            r0.setOnUserReaction$storyly_release(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r2)
            com.appsamurai.storyly.data.a0 r1 = r8.f3615j
            boolean r2 = r1 instanceof com.appsamurai.storyly.data.t
            if (r2 == 0) goto L_0x010c
            r3 = r1
            com.appsamurai.storyly.data.t r3 = (com.appsamurai.storyly.data.t) r3
        L_0x010c:
            if (r3 != 0) goto L_0x0110
            goto L_0x0051
        L_0x0110:
            r0.f5908n = r3
            r0.setStorylyLayerItem$storyly_release(r8)
            float r8 = r8.f3613h
            r0.setRotation(r8)
            kotlin.jvm.functions.Function0 r8 = r0.getOnLayerLoad$storyly_release()
            r8.invoke()
            goto L_0x0051
        L_0x0123:
            boolean r1 = r0 instanceof com.appsamurai.storyly.data.x
            java.lang.String r4 = "storylyItem"
            if (r1 == 0) goto L_0x0166
            com.appsamurai.storyly.storylypresenter.storylylayer.w r0 = new com.appsamurai.storyly.storylypresenter.storylylayer.w
            android.content.Context r1 = r6.f6202a
            com.appsamurai.storyly.data.v r2 = r6.f6206e
            r0.<init>(r1, r2)
            r6.a((com.appsamurai.storyly.data.b0) r8, (com.appsamurai.storyly.storylypresenter.storylylayer.o1) r0)
            com.appsamurai.storyly.storylypresenter.storylylayer.k0 r1 = new com.appsamurai.storyly.storylypresenter.storylylayer.k0
            r1.<init>(r6, r0)
            r0.setOnLayerLoad$storyly_release(r1)
            com.appsamurai.storyly.storylypresenter.storylylayer.l0 r1 = new com.appsamurai.storyly.storylypresenter.storylylayer.l0
            r1.<init>(r6, r0)
            r0.setOnImageReady$storyly_release(r1)
            com.appsamurai.storyly.storylypresenter.storylylayer.m0 r1 = new com.appsamurai.storyly.storylypresenter.storylylayer.m0
            r1.<init>(r6)
            r0.setOnLayerLoadFail$storyly_release(r1)
            kotlin.jvm.functions.Function1 r1 = r6.c()
            r0.setOnUserActionClick$storyly_release(r1)
            com.appsamurai.storyly.data.z r1 = r6.f6225x
            if (r1 != 0) goto L_0x015d
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            goto L_0x015e
        L_0x015d:
            r3 = r1
        L_0x015e:
            r0.setStorylyItem$storyly_release(r3)
            r0.a((com.appsamurai.storyly.data.b0) r8)
            goto L_0x0051
        L_0x0166:
            boolean r1 = r0 instanceof com.appsamurai.storyly.data.e0
            java.lang.String r5 = "storylyLayer"
            if (r1 == 0) goto L_0x028f
            com.appsamurai.storyly.storylypresenter.storylylayer.r1 r0 = new com.appsamurai.storyly.storylypresenter.storylylayer.r1
            android.content.Context r1 = r6.f6202a
            com.appsamurai.storyly.config.StorylyConfig r4 = r6.f6204c
            r0.<init>(r1, r4)
            r6.a((com.appsamurai.storyly.data.b0) r8, (com.appsamurai.storyly.storylypresenter.storylylayer.o1) r0)
            kotlin.jvm.functions.Function5 r1 = r6.f()
            r0.setOnUserReaction$storyly_release(r1)
            com.appsamurai.storyly.storylypresenter.storylylayer.r0 r1 = new com.appsamurai.storyly.storylypresenter.storylylayer.r0
            r1.<init>(r6, r0)
            r0.setOnLayerLoad$storyly_release(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r2)
            com.appsamurai.storyly.data.a0 r1 = r8.f3615j
            boolean r2 = r1 instanceof com.appsamurai.storyly.data.e0
            if (r2 == 0) goto L_0x0194
            com.appsamurai.storyly.data.e0 r1 = (com.appsamurai.storyly.data.e0) r1
            goto L_0x0195
        L_0x0194:
            r1 = r3
        L_0x0195:
            if (r1 != 0) goto L_0x0199
            goto L_0x0051
        L_0x0199:
            r0.f6001w = r1
            r0.setStorylyLayerItem$storyly_release(r8)
            android.widget.FrameLayout r1 = r0.f5987i
            r2 = 0
            if (r1 != 0) goto L_0x01a4
            goto L_0x01a7
        L_0x01a4:
            r1.setBackgroundColor(r2)
        L_0x01a7:
            android.view.View r1 = r0.f5988j
            com.appsamurai.storyly.data.e0 r4 = r0.f6001w
            if (r4 != 0) goto L_0x01b1
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
            r4 = r3
        L_0x01b1:
            com.appsamurai.storyly.data.c r4 = r4.a()
            int r4 = r4.f3624a
            r1.setBackgroundColor(r4)
            android.widget.Button r1 = r0.f5990l
            r1.setBackgroundColor(r2)
            android.widget.Button r1 = r0.f5989k
            r1.setBackgroundColor(r2)
            android.widget.TextView r1 = r0.f5991m
            com.appsamurai.storyly.data.e0 r2 = r0.f6001w
            if (r2 != 0) goto L_0x01ce
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
            r2 = r3
        L_0x01ce:
            com.appsamurai.storyly.data.c r4 = r2.f3659n
            if (r4 != 0) goto L_0x01d8
            com.appsamurai.storyly.data.c r4 = r2.f3655j
            if (r4 != 0) goto L_0x01d8
            com.appsamurai.storyly.data.c r4 = r2.f3671z
        L_0x01d8:
            int r2 = r4.f3624a
            r1.setTextColor(r2)
            android.widget.TextView r1 = r0.f5991m
            com.appsamurai.storyly.data.e0 r2 = r0.f6001w
            if (r2 != 0) goto L_0x01e7
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
            r2 = r3
        L_0x01e7:
            java.lang.String r2 = r2.f3650e
            r1.setText(r2)
            android.widget.TextView r1 = r0.f5991m
            java.util.List<java.lang.Float> r2 = r0.f5997s
            com.appsamurai.storyly.data.e0 r4 = r0.f6001w
            if (r4 != 0) goto L_0x01f8
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
            r4 = r3
        L_0x01f8:
            int r4 = r4.f3653h
            java.lang.Object r2 = r2.get(r4)
            java.lang.Number r2 = (java.lang.Number) r2
            float r2 = r2.floatValue()
            r4 = 1
            r1.setTextSize(r4, r2)
            android.widget.TextView r1 = r0.f5991m
            com.appsamurai.storyly.config.StorylyConfig r2 = r0.f5985g
            com.appsamurai.storyly.config.styling.story.StorylyStoryStyling r2 = r2.getStory$storyly_release()
            android.graphics.Typeface r2 = r2.getInteractiveTypeface$storyly_release()
            r1.setTypeface(r2)
            android.widget.TextView r1 = r0.f5991m
            com.appsamurai.storyly.data.e0 r2 = r0.f6001w
            if (r2 != 0) goto L_0x0221
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
            r2 = r3
        L_0x0221:
            boolean r2 = r2.f3665t
            com.appsamurai.storyly.data.e0 r4 = r0.f6001w
            if (r4 != 0) goto L_0x022b
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
            r4 = r3
        L_0x022b:
            boolean r4 = r4.f3666u
            com.appsamurai.storyly.util.c.a(r1, r2, r4)
            android.widget.Button r1 = r0.f5989k
            com.appsamurai.storyly.config.StorylyConfig r2 = r0.f5985g
            com.appsamurai.storyly.config.styling.story.StorylyStoryStyling r2 = r2.getStory$storyly_release()
            android.graphics.Typeface r2 = r2.getInteractiveTypeface$storyly_release()
            r1.setTypeface(r2)
            android.widget.Button r1 = r0.f5989k
            com.appsamurai.storyly.data.e0 r2 = r0.f6001w
            if (r2 != 0) goto L_0x0249
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
            r2 = r3
        L_0x0249:
            boolean r2 = r2.f3667v
            com.appsamurai.storyly.data.e0 r4 = r0.f6001w
            if (r4 != 0) goto L_0x0253
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
            r4 = r3
        L_0x0253:
            boolean r4 = r4.f3668w
            com.appsamurai.storyly.util.c.a(r1, r2, r4)
            android.widget.Button r1 = r0.f5990l
            com.appsamurai.storyly.config.StorylyConfig r2 = r0.f5985g
            com.appsamurai.storyly.config.styling.story.StorylyStoryStyling r2 = r2.getStory$storyly_release()
            android.graphics.Typeface r2 = r2.getInteractiveTypeface$storyly_release()
            r1.setTypeface(r2)
            android.widget.Button r1 = r0.f5990l
            com.appsamurai.storyly.data.e0 r2 = r0.f6001w
            if (r2 != 0) goto L_0x0271
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
            r2 = r3
        L_0x0271:
            boolean r2 = r2.f3667v
            com.appsamurai.storyly.data.e0 r4 = r0.f6001w
            if (r4 != 0) goto L_0x027b
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
            goto L_0x027c
        L_0x027b:
            r3 = r4
        L_0x027c:
            boolean r3 = r3.f3668w
            com.appsamurai.storyly.util.c.a(r1, r2, r3)
            float r8 = r8.f3613h
            r0.setRotation(r8)
            kotlin.jvm.functions.Function0 r8 = r0.getOnLayerLoad$storyly_release()
            r8.invoke()
            goto L_0x0051
        L_0x028f:
            boolean r1 = r0 instanceof com.appsamurai.storyly.data.k0
            if (r1 == 0) goto L_0x02e2
            com.appsamurai.storyly.storylypresenter.storylylayer.b2 r0 = new com.appsamurai.storyly.storylypresenter.storylylayer.b2
            android.content.Context r1 = r6.f6202a
            com.appsamurai.storyly.config.StorylyConfig r4 = r6.f6204c
            r0.<init>(r1, r4)
            r6.a((com.appsamurai.storyly.data.b0) r8, (com.appsamurai.storyly.storylypresenter.storylylayer.o1) r0)
            com.appsamurai.storyly.storylypresenter.storylylayer.a1 r1 = new com.appsamurai.storyly.storylypresenter.storylylayer.a1
            r1.<init>(r6, r0)
            r0.setOnLayerLoad$storyly_release(r1)
            kotlin.jvm.functions.Function5 r1 = r6.f()
            r0.setOnUserReaction$storyly_release(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r2)
            com.appsamurai.storyly.data.a0 r1 = r8.f3615j
            boolean r2 = r1 instanceof com.appsamurai.storyly.data.k0
            if (r2 == 0) goto L_0x02ba
            com.appsamurai.storyly.data.k0 r1 = (com.appsamurai.storyly.data.k0) r1
            goto L_0x02bb
        L_0x02ba:
            r1 = r3
        L_0x02bb:
            if (r1 != 0) goto L_0x02bf
            goto L_0x0051
        L_0x02bf:
            r0.f5633j = r1
            r0.setStorylyLayerItem$storyly_release(r8)
            android.widget.TextView r1 = r0.f5645v
            com.appsamurai.storyly.data.k0 r2 = r0.f5633j
            if (r2 != 0) goto L_0x02ce
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
            goto L_0x02cf
        L_0x02ce:
            r3 = r2
        L_0x02cf:
            java.lang.String r2 = r3.f3809c
            r1.setText(r2)
            float r8 = r8.f3613h
            r0.setRotation(r8)
            kotlin.jvm.functions.Function0 r8 = r0.getOnLayerLoad$storyly_release()
            r8.invoke()
            goto L_0x0051
        L_0x02e2:
            boolean r1 = r0 instanceof com.appsamurai.storyly.data.r
            if (r1 == 0) goto L_0x0317
            com.appsamurai.storyly.data.v r0 = r6.f6206e
            if (r0 != 0) goto L_0x02ec
            goto L_0x0051
        L_0x02ec:
            com.appsamurai.storyly.storylypresenter.storylylayer.m r1 = new com.appsamurai.storyly.storylypresenter.storylylayer.m
            android.content.Context r2 = r6.f6202a
            com.appsamurai.storyly.data.z r5 = r6.f6225x
            if (r5 != 0) goto L_0x02f8
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            goto L_0x02f9
        L_0x02f8:
            r3 = r5
        L_0x02f9:
            java.lang.String r0 = r0.f4221a
            com.appsamurai.storyly.config.StorylyConfig r4 = r6.f6204c
            r1.<init>(r2, r3, r0, r4)
            r6.a((com.appsamurai.storyly.data.b0) r8, (com.appsamurai.storyly.storylypresenter.storylylayer.o1) r1)
            com.appsamurai.storyly.storylypresenter.storylylayer.h0 r0 = new com.appsamurai.storyly.storylypresenter.storylylayer.h0
            r0.<init>(r6, r1)
            r1.setOnLayerLoad$storyly_release(r0)
            kotlin.jvm.functions.Function5 r0 = r6.f()
            r1.setOnUserReaction$storyly_release(r0)
            r1.a((com.appsamurai.storyly.data.b0) r8)
            goto L_0x0051
        L_0x0317:
            boolean r1 = r0 instanceof com.appsamurai.storyly.data.l0
            if (r1 == 0) goto L_0x0349
            com.appsamurai.storyly.storylypresenter.storylylayer.c2 r0 = new com.appsamurai.storyly.storylypresenter.storylylayer.c2
            android.content.Context r1 = r6.f6202a
            com.appsamurai.storyly.config.StorylyConfig r2 = r6.f6204c
            r0.<init>(r1, r2)
            r6.a((com.appsamurai.storyly.data.b0) r8, (com.appsamurai.storyly.storylypresenter.storylylayer.o1) r0)
            com.appsamurai.storyly.storylypresenter.storylylayer.b1 r1 = new com.appsamurai.storyly.storylypresenter.storylylayer.b1
            r1.<init>(r6, r0)
            r0.setOnLayerLoad$storyly_release(r1)
            kotlin.jvm.functions.Function5 r1 = r6.f()
            r0.setOnUserReaction$storyly_release(r1)
            kotlin.jvm.functions.Function0 r1 = r6.e()
            r0.setOnUserInteractionStarted$storyly_release(r1)
            kotlin.jvm.functions.Function0 r1 = r6.d()
            r0.setOnUserInteractionEnded$storyly_release(r1)
            r0.a((com.appsamurai.storyly.data.b0) r8)
            goto L_0x0051
        L_0x0349:
            boolean r1 = r0 instanceof com.appsamurai.storyly.data.i0
            if (r1 == 0) goto L_0x03a3
            com.appsamurai.storyly.storylypresenter.storylylayer.u1 r0 = new com.appsamurai.storyly.storylypresenter.storylylayer.u1
            android.content.Context r1 = r6.f6202a
            com.appsamurai.storyly.config.StorylyConfig r4 = r6.f6204c
            r0.<init>(r1, r4)
            r6.a((com.appsamurai.storyly.data.b0) r8, (com.appsamurai.storyly.storylypresenter.storylylayer.o1) r0)
            com.appsamurai.storyly.storylypresenter.storylylayer.x0 r1 = new com.appsamurai.storyly.storylypresenter.storylylayer.x0
            r1.<init>(r6, r0)
            r0.setOnLayerLoad$storyly_release(r1)
            kotlin.jvm.functions.Function5 r1 = r6.f()
            r0.setOnUserReaction$storyly_release(r1)
            kotlin.jvm.functions.Function0 r1 = r6.e()
            r0.setOnUserInteractionStarted$storyly_release(r1)
            kotlin.jvm.functions.Function0 r1 = r6.d()
            r0.setOnUserInteractionEnded$storyly_release(r1)
            com.appsamurai.storyly.storylypresenter.storylylayer.y0 r1 = new com.appsamurai.storyly.storylypresenter.storylylayer.y0
            r1.<init>(r6)
            r0.setOnUserTapPoint$storyly_release(r1)
            kotlin.jvm.functions.Function1 r1 = r6.c()
            r0.setOnUserActionClick$storyly_release(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r2)
            com.appsamurai.storyly.data.a0 r1 = r8.f3615j
            boolean r2 = r1 instanceof com.appsamurai.storyly.data.i0
            if (r2 == 0) goto L_0x0391
            r3 = r1
            com.appsamurai.storyly.data.i0 r3 = (com.appsamurai.storyly.data.i0) r3
        L_0x0391:
            if (r3 != 0) goto L_0x0395
            goto L_0x0051
        L_0x0395:
            r0.f6162s = r3
            r0.setStorylyLayerItem$storyly_release(r8)
            kotlin.jvm.functions.Function0 r8 = r0.getOnLayerLoad$storyly_release()
            r8.invoke()
            goto L_0x0051
        L_0x03a3:
            boolean r1 = r0 instanceof com.appsamurai.storyly.data.f0
            if (r1 == 0) goto L_0x03de
            com.appsamurai.storyly.storylypresenter.storylylayer.s1 r0 = new com.appsamurai.storyly.storylypresenter.storylylayer.s1
            android.content.Context r1 = r6.f6202a
            com.appsamurai.storyly.config.StorylyConfig r2 = r6.f6204c
            r0.<init>(r1, r2)
            r6.a((com.appsamurai.storyly.data.b0) r8, (com.appsamurai.storyly.storylypresenter.storylylayer.o1) r0)
            com.appsamurai.storyly.storylypresenter.storylylayer.s0 r1 = new com.appsamurai.storyly.storylypresenter.storylylayer.s0
            r1.<init>(r6)
            r0.setOnLayerLoadFail$storyly_release(r1)
            com.appsamurai.storyly.storylypresenter.storylylayer.t0 r1 = new com.appsamurai.storyly.storylypresenter.storylylayer.t0
            r1.<init>(r6, r0)
            r0.setOnImageReady$storyly_release(r1)
            kotlin.jvm.functions.Function5 r1 = r6.f()
            r0.setOnUserReaction$storyly_release(r1)
            kotlin.jvm.functions.Function1 r1 = r6.c()
            r0.setOnUserActionClick$storyly_release(r1)
            com.appsamurai.storyly.storylypresenter.storylylayer.u0 r1 = new com.appsamurai.storyly.storylypresenter.storylylayer.u0
            r1.<init>(r6, r0)
            r0.setOnLayerLoad$storyly_release(r1)
            r0.a((com.appsamurai.storyly.data.b0) r8)
            goto L_0x0051
        L_0x03de:
            boolean r1 = r0 instanceof com.appsamurai.storyly.data.h0
            if (r1 == 0) goto L_0x0418
            com.appsamurai.storyly.storylypresenter.storylylayer.storylyProductList.i r0 = new com.appsamurai.storyly.storylypresenter.storylylayer.storylyProductList.i
            android.content.Context r1 = r6.f6202a
            com.appsamurai.storyly.config.StorylyConfig r2 = r6.f6204c
            r0.<init>(r1, r2)
            r6.a((com.appsamurai.storyly.data.b0) r8, (com.appsamurai.storyly.storylypresenter.storylylayer.o1) r0)
            com.appsamurai.storyly.storylypresenter.storylylayer.v0 r1 = new com.appsamurai.storyly.storylypresenter.storylylayer.v0
            r1.<init>(r6, r0)
            r0.setOnLayerLoad$storyly_release(r1)
            com.appsamurai.storyly.storylypresenter.storylylayer.w0 r1 = new com.appsamurai.storyly.storylypresenter.storylylayer.w0
            r1.<init>(r6)
            r0.setOnLayerLoadFail$storyly_release(r1)
            kotlin.jvm.functions.Function0 r1 = r6.e()
            r0.setOnUserInteractionStarted$storyly_release(r1)
            kotlin.jvm.functions.Function0 r1 = r6.d()
            r0.setOnUserInteractionEnded$storyly_release(r1)
            kotlin.jvm.functions.Function1 r1 = r6.c()
            r0.setOnProductClick$storyly_release(r1)
            r0.a((com.appsamurai.storyly.data.b0) r8)
            goto L_0x0051
        L_0x0418:
            boolean r1 = r0 instanceof com.appsamurai.storyly.data.j0
            if (r1 == 0) goto L_0x043c
            com.appsamurai.storyly.storylypresenter.storylylayer.a2 r0 = new com.appsamurai.storyly.storylypresenter.storylylayer.a2
            android.content.Context r1 = r6.f6202a
            com.appsamurai.storyly.config.StorylyConfig r2 = r6.f6204c
            r0.<init>(r1, r2)
            r6.a((com.appsamurai.storyly.data.b0) r8, (com.appsamurai.storyly.storylypresenter.storylylayer.o1) r0)
            kotlin.jvm.functions.Function5 r1 = r6.f()
            r0.setOnUserReaction$storyly_release(r1)
            com.appsamurai.storyly.storylypresenter.storylylayer.z0 r1 = new com.appsamurai.storyly.storylypresenter.storylylayer.z0
            r1.<init>(r6, r0)
            r0.setOnLayerLoad$storyly_release(r1)
            r0.a((com.appsamurai.storyly.data.b0) r8)
            goto L_0x0051
        L_0x043c:
            boolean r1 = r0 instanceof com.appsamurai.storyly.data.q
            if (r1 == 0) goto L_0x0494
            com.appsamurai.storyly.storylypresenter.storylylayer.i r0 = new com.appsamurai.storyly.storylypresenter.storylylayer.i
            android.content.Context r1 = r6.f6202a
            com.appsamurai.storyly.config.StorylyConfig r4 = r6.f6204c
            r0.<init>(r1, r4)
            r6.a((com.appsamurai.storyly.data.b0) r8, (com.appsamurai.storyly.storylypresenter.storylylayer.o1) r0)
            kotlin.jvm.functions.Function0 r1 = r6.e()
            r0.setOnUserInteractionStarted$storyly_release(r1)
            kotlin.jvm.functions.Function0 r1 = r6.d()
            r0.setOnUserInteractionEnded$storyly_release(r1)
            kotlin.jvm.functions.Function5 r1 = r6.f()
            r0.setOnUserReaction$storyly_release(r1)
            com.appsamurai.storyly.storylypresenter.storylylayer.f0 r1 = new com.appsamurai.storyly.storylypresenter.storylylayer.f0
            r1.<init>(r6, r0)
            r0.setOnLayerLoad$storyly_release(r1)
            com.appsamurai.storyly.storylypresenter.storylylayer.g0 r1 = new com.appsamurai.storyly.storylypresenter.storylylayer.g0
            r1.<init>(r6)
            r0.setOnExtractBackgroundBitmap$storyly_release(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r2)
            com.appsamurai.storyly.data.a0 r1 = r8.f3615j
            boolean r2 = r1 instanceof com.appsamurai.storyly.data.q
            if (r2 == 0) goto L_0x047d
            r3 = r1
            com.appsamurai.storyly.data.q r3 = (com.appsamurai.storyly.data.q) r3
        L_0x047d:
            if (r3 != 0) goto L_0x0481
            goto L_0x0051
        L_0x0481:
            r0.f5784h = r3
            r0.setStorylyLayerItem$storyly_release(r8)
            float r8 = r8.f3613h
            r0.setRotation(r8)
            kotlin.jvm.functions.Function0 r8 = r0.getOnLayerLoad$storyly_release()
            r8.invoke()
            goto L_0x0051
        L_0x0494:
            boolean r1 = r0 instanceof com.appsamurai.storyly.data.q0
            if (r1 == 0) goto L_0x04f7
            com.appsamurai.storyly.data.v r0 = r6.f6206e
            if (r0 != 0) goto L_0x049e
            goto L_0x0051
        L_0x049e:
            com.appsamurai.storyly.storylypresenter.storylylayer.r r1 = new com.appsamurai.storyly.storylypresenter.storylylayer.r
            android.content.Context r2 = r6.f6202a
            com.appsamurai.storyly.data.z r5 = r6.f6225x
            if (r5 != 0) goto L_0x04aa
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r5 = r3
        L_0x04aa:
            r1.<init>(r2, r5, r0)
            r6.a((com.appsamurai.storyly.data.b0) r8, (com.appsamurai.storyly.storylypresenter.storylylayer.o1) r1)
            com.appsamurai.storyly.storylypresenter.storylylayer.e1 r0 = new com.appsamurai.storyly.storylypresenter.storylylayer.e1
            r0.<init>(r6, r1)
            r1.setOnLayerLoad$storyly_release(r0)
            com.appsamurai.storyly.storylypresenter.storylylayer.f1 r0 = new com.appsamurai.storyly.storylypresenter.storylylayer.f1
            r0.<init>(r6)
            r1.setOnLayerLoadFail$storyly_release(r0)
            com.appsamurai.storyly.storylypresenter.storylylayer.g1 r0 = new com.appsamurai.storyly.storylypresenter.storylylayer.g1
            r0.<init>(r6, r1)
            r1.setOnVideoReady$storyly_release(r0)
            kotlin.jvm.functions.Function0<kotlin.Unit> r0 = r6.f6214m
            if (r0 == 0) goto L_0x04cd
            goto L_0x04d3
        L_0x04cd:
            java.lang.String r0 = "onBufferEnd"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r0 = r3
        L_0x04d3:
            r1.setOnBufferEnd$storyly_release(r0)
            kotlin.jvm.functions.Function0<kotlin.Unit> r0 = r6.f6213l
            if (r0 == 0) goto L_0x04db
            goto L_0x04e1
        L_0x04db:
            java.lang.String r0 = "onBufferStart"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r0 = r3
        L_0x04e1:
            r1.setOnBufferStart$storyly_release(r0)
            kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> r0 = r6.f6215n
            if (r0 == 0) goto L_0x04ea
            r3 = r0
            goto L_0x04ef
        L_0x04ea:
            java.lang.String r0 = "onSessionTimeUpdated"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
        L_0x04ef:
            r1.setOnSessionTimeUpdated$storyly_release(r3)
            r1.a((com.appsamurai.storyly.data.b0) r8)
            goto L_0x0051
        L_0x04f7:
            boolean r1 = r0 instanceof com.appsamurai.storyly.data.o
            if (r1 == 0) goto L_0x056a
            com.appsamurai.storyly.storylypresenter.storylylayer.g r0 = new com.appsamurai.storyly.storylypresenter.storylylayer.g
            android.content.Context r1 = r6.f6202a
            r0.<init>(r1)
            r6.a((com.appsamurai.storyly.data.b0) r8, (com.appsamurai.storyly.storylypresenter.storylylayer.o1) r0)
            com.appsamurai.storyly.storylypresenter.storylylayer.c0 r1 = new com.appsamurai.storyly.storylypresenter.storylylayer.c0
            r1.<init>(r6, r0)
            r0.setOnLayerLoad$storyly_release(r1)
            com.appsamurai.storyly.storylypresenter.storylylayer.d0 r1 = new com.appsamurai.storyly.storylypresenter.storylylayer.d0
            r1.<init>(r6, r0)
            r0.setOnAdReady$storyly_release(r1)
            kotlin.jvm.functions.Function1 r1 = r6.c()
            r0.setOnUserActionClick$storyly_release(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r2)
            com.appsamurai.storyly.data.a0 r1 = r8.f3615j
            boolean r2 = r1 instanceof com.appsamurai.storyly.data.o
            if (r2 == 0) goto L_0x0528
            com.appsamurai.storyly.data.o r1 = (com.appsamurai.storyly.data.o) r1
            goto L_0x0529
        L_0x0528:
            r1 = r3
        L_0x0529:
            if (r1 != 0) goto L_0x052d
            goto L_0x0051
        L_0x052d:
            r0.f5734g = r1
            r0.setStorylyLayerItem$storyly_release(r8)
            com.appsamurai.storyly.data.o r1 = r0.f5734g
            if (r1 != 0) goto L_0x053a
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
            goto L_0x053b
        L_0x053a:
            r3 = r1
        L_0x053b:
            com.appsamurai.storyly.ad.StorylyAdView r1 = r3.f4078a
            r0.f5735h = r1
            if (r1 != 0) goto L_0x0542
            goto L_0x054a
        L_0x0542:
            com.appsamurai.storyly.storylypresenter.storylylayer.f r2 = new com.appsamurai.storyly.storylypresenter.storylylayer.f
            r2.<init>(r0, r8)
            r1.setOnActionClicked(r2)
        L_0x054a:
            kotlin.jvm.functions.Function0 r8 = r0.getOnLayerLoad$storyly_release()
            r8.invoke()
            kotlin.jvm.functions.Function1 r8 = r0.getOnAdReady$storyly_release()
            com.appsamurai.storyly.ad.StorylyAdView r0 = r0.f5735h
            if (r0 != 0) goto L_0x055c
            r0 = 7000(0x1b58, float:9.809E-42)
            goto L_0x0561
        L_0x055c:
            long r0 = r0.load()
            int r0 = (int) r0
        L_0x0561:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r8.invoke(r0)
            goto L_0x0051
        L_0x056a:
            boolean r1 = r0 instanceof com.appsamurai.storyly.data.c0
            if (r1 == 0) goto L_0x059e
            com.appsamurai.storyly.storylypresenter.storylylayer.p1 r0 = new com.appsamurai.storyly.storylypresenter.storylylayer.p1
            android.content.Context r1 = r6.f6202a
            com.appsamurai.storyly.data.v r2 = r6.f6206e
            com.appsamurai.storyly.config.StorylyConfig r3 = r6.f6204c
            r0.<init>(r1, r2, r3)
            r6.a((com.appsamurai.storyly.data.b0) r8, (com.appsamurai.storyly.storylypresenter.storylylayer.o1) r0)
            com.appsamurai.storyly.storylypresenter.storylylayer.q0 r1 = new com.appsamurai.storyly.storylypresenter.storylylayer.q0
            r1.<init>(r6, r0)
            r0.setOnLayerLoad$storyly_release(r1)
            kotlin.jvm.functions.Function1 r1 = r6.c()
            r0.setOnUserActionClick$storyly_release(r1)
            kotlin.jvm.functions.Function0 r1 = r6.d()
            r0.setOnUserInteractionEnded$storyly_release(r1)
            kotlin.jvm.functions.Function0 r1 = r6.e()
            r0.setOnUserInteractionStarted$storyly_release(r1)
            r0.a((com.appsamurai.storyly.data.b0) r8)
            goto L_0x0051
        L_0x059e:
            boolean r0 = r0 instanceof com.appsamurai.storyly.data.y
            if (r0 == 0) goto L_0x0051
            com.appsamurai.storyly.storylypresenter.storylylayer.u r0 = new com.appsamurai.storyly.storylypresenter.storylylayer.u
            android.content.Context r1 = r6.f6202a
            com.appsamurai.storyly.config.StorylyConfig r2 = r6.f6204c
            r0.<init>(r1, r2)
            r6.a((com.appsamurai.storyly.data.b0) r8, (com.appsamurai.storyly.storylypresenter.storylylayer.o1) r0)
            com.appsamurai.storyly.storylypresenter.storylylayer.n0 r1 = new com.appsamurai.storyly.storylypresenter.storylylayer.n0
            r1.<init>(r6, r0)
            r0.setOnLayerLoad$storyly_release(r1)
            com.appsamurai.storyly.storylypresenter.storylylayer.o0 r1 = new com.appsamurai.storyly.storylypresenter.storylylayer.o0
            r1.<init>(r6, r0)
            r0.setOnImageReady$storyly_release(r1)
            kotlin.jvm.functions.Function5 r1 = r6.f()
            r0.setOnUserReaction$storyly_release(r1)
            com.appsamurai.storyly.storylypresenter.storylylayer.p0 r1 = new com.appsamurai.storyly.storylypresenter.storylylayer.p0
            r1.<init>(r6)
            r0.setOnLayerLoadFail$storyly_release(r1)
            r0.a((com.appsamurai.storyly.data.b0) r8)
            goto L_0x0051
        L_0x05d2:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.storylypresenter.storylylayer.a0.invoke(java.lang.Object, java.lang.Object):java.lang.Object");
    }
}
