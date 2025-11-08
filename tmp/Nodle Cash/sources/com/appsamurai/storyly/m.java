package com.appsamurai.storyly;

import com.appsamurai.storyly.data.managers.processing.f;
import com.appsamurai.storyly.data.v;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

public final class m extends Lambda implements Function2<List<? extends v>, StorylyDataSource, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ StorylyView f4637a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ f f4638b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public m(StorylyView storylyView, f fVar) {
        super(2);
        this.f4637a = storylyView;
        this.f4638b = fVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0090  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object invoke(java.lang.Object r21, java.lang.Object r22) {
        /*
            r20 = this;
            r0 = r20
            r1 = r21
            java.util.List r1 = (java.util.List) r1
            r2 = r22
            com.appsamurai.storyly.StorylyDataSource r2 = (com.appsamurai.storyly.StorylyDataSource) r2
            java.lang.String r3 = "groupItems"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r3)
            java.lang.String r3 = "dataSource"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r3)
            kotlinx.serialization.json.JsonObjectBuilder r3 = new kotlinx.serialization.json.JsonObjectBuilder
            r3.<init>()
            java.lang.String r4 = r2.getValue()
            java.lang.String r5 = "d_s"
            kotlinx.serialization.json.JsonElementBuildersKt.put((kotlinx.serialization.json.JsonObjectBuilder) r3, (java.lang.String) r5, (java.lang.String) r4)
            com.appsamurai.storyly.l r4 = new com.appsamurai.storyly.l
            r4.<init>(r2, r1)
            java.lang.String r5 = "sg_ids"
            kotlinx.serialization.json.JsonElementBuildersKt.putJsonArray(r3, r5, r4)
            kotlinx.serialization.json.JsonObject r12 = r3.build()
            com.appsamurai.storyly.StorylyDataSource r3 = com.appsamurai.storyly.StorylyDataSource.MomentsAPI
            r4 = 0
            if (r2 != r3) goto L_0x0057
            java.util.Iterator r3 = r1.iterator()
        L_0x0039:
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto L_0x004d
            java.lang.Object r5 = r3.next()
            r6 = r5
            com.appsamurai.storyly.data.v r6 = (com.appsamurai.storyly.data.v) r6
            com.appsamurai.storyly.StoryGroupType r6 = r6.f4228h
            com.appsamurai.storyly.StoryGroupType r7 = com.appsamurai.storyly.StoryGroupType.MomentsDefault
            if (r6 != r7) goto L_0x0039
            goto L_0x004e
        L_0x004d:
            r5 = r4
        L_0x004e:
            com.appsamurai.storyly.data.v r5 = (com.appsamurai.storyly.data.v) r5
            if (r5 != 0) goto L_0x0053
            goto L_0x0057
        L_0x0053:
            java.lang.String r3 = r5.f4233m
            r14 = r3
            goto L_0x0058
        L_0x0057:
            r14 = r4
        L_0x0058:
            com.appsamurai.storyly.StorylyView r3 = r0.f4637a
            com.appsamurai.storyly.analytics.e r6 = r3.getStorylyTracker()
            com.appsamurai.storyly.analytics.a r7 = com.appsamurai.storyly.analytics.a.OnScreen
            r16 = 0
            r17 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r13 = 0
            r15 = 0
            r18 = 0
            r19 = 3928(0xf58, float:5.504E-42)
            com.appsamurai.storyly.analytics.e.a(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19)
            com.appsamurai.storyly.StorylyView r3 = r0.f4637a
            com.appsamurai.storyly.StorylyView$a r3 = r3.f3423l
            if (r3 != 0) goto L_0x007a
            goto L_0x0087
        L_0x007a:
            com.appsamurai.storyly.StorylyView r5 = r0.f4637a
            java.lang.String r6 = r3.f3432a
            java.lang.String r7 = r3.f3433b
            com.appsamurai.storyly.PlayMode r8 = r3.f3434c
            boolean r3 = r3.f3435d
            boolean unused = r5.a((java.lang.String) r6, (java.lang.String) r7, (com.appsamurai.storyly.PlayMode) r8, (boolean) r3)
        L_0x0087:
            com.appsamurai.storyly.StorylyView r3 = r0.f4637a
            com.appsamurai.storyly.StorylyListener r3 = r3.getStorylyListener()
            if (r3 != 0) goto L_0x0090
            goto L_0x00b6
        L_0x0090:
            com.appsamurai.storyly.StorylyView r5 = r0.f4637a
            java.util.ArrayList r6 = new java.util.ArrayList
            int r7 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r1, 10)
            r6.<init>(r7)
            java.util.Iterator r1 = r1.iterator()
        L_0x009f:
            boolean r7 = r1.hasNext()
            if (r7 == 0) goto L_0x00b3
            java.lang.Object r7 = r1.next()
            com.appsamurai.storyly.data.v r7 = (com.appsamurai.storyly.data.v) r7
            com.appsamurai.storyly.StoryGroup r7 = r7.d()
            r6.add(r7)
            goto L_0x009f
        L_0x00b3:
            r3.storylyLoaded(r5, r6, r2)
        L_0x00b6:
            com.appsamurai.storyly.data.managers.processing.f r1 = r0.f4638b
            com.appsamurai.storyly.data.managers.product.b r1 = r1.e()
            java.util.Set<com.appsamurai.storyly.data.j> r1 = r1.f4043c
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L_0x00d7
            com.appsamurai.storyly.StorylyDataSource r1 = com.appsamurai.storyly.StorylyDataSource.API
            if (r2 != r1) goto L_0x00d7
            com.appsamurai.storyly.StorylyView r1 = r0.f4637a
            com.appsamurai.storyly.data.managers.processing.e r2 = com.appsamurai.storyly.data.managers.processing.e.ProductFallbackData
            com.appsamurai.storyly.k r3 = new com.appsamurai.storyly.k
            com.appsamurai.storyly.data.managers.processing.f r0 = r0.f4638b
            r3.<init>(r1, r0)
            r0 = 2
            com.appsamurai.storyly.StorylyView.a((com.appsamurai.storyly.StorylyView) r1, (com.appsamurai.storyly.data.managers.processing.e) r2, (kotlin.jvm.functions.Function0) r4, (kotlin.jvm.functions.Function0) r3, (int) r0)
        L_0x00d7:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.m.invoke(java.lang.Object, java.lang.Object):java.lang.Object");
    }
}
