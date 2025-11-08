package com.appsamurai.storyly.storylypresenter;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

public final class a1 extends Lambda implements Function1<Integer, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ o f4744a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public a1(o oVar) {
        super(1);
        this.f4744a = oVar;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v14, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: java.util.List} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v16, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: java.util.List} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object invoke(java.lang.Object r6) {
        /*
            r5 = this;
            java.lang.Integer r6 = (java.lang.Integer) r6
            com.appsamurai.storyly.storylypresenter.o r0 = r5.f4744a
            com.appsamurai.storyly.storylypresenter.o$a r1 = r0.f5051f
            com.appsamurai.storyly.storylypresenter.o$a r2 = com.appsamurai.storyly.storylypresenter.o.a.Buffering
            if (r1 == r2) goto L_0x000c
            goto L_0x00c4
        L_0x000c:
            com.appsamurai.storyly.external.a r0 = r0.f5038A
            r1 = 0
            if (r0 != 0) goto L_0x0017
            java.lang.String r0 = "loadingView"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r0 = r1
        L_0x0017:
            r0.a()
            com.appsamurai.storyly.storylypresenter.o r0 = r5.f4744a
            com.appsamurai.storyly.storylypresenter.o$a r2 = com.appsamurai.storyly.storylypresenter.o.a.Loaded
            r0.f5051f = r2
            if (r6 != 0) goto L_0x0023
            goto L_0x0031
        L_0x0023:
            int r6 = r6.intValue()
            com.appsamurai.storyly.data.z r0 = r0.getStorylyItem()
            if (r0 != 0) goto L_0x002e
            goto L_0x0031
        L_0x002e:
            long r2 = (long) r6
            r0.f4304c = r2
        L_0x0031:
            com.appsamurai.storyly.storylypresenter.o r6 = r5.f4744a
            boolean r0 = r6.f5044I
            if (r0 == 0) goto L_0x003a
            r6.l()
        L_0x003a:
            com.appsamurai.storyly.storylypresenter.o r6 = r5.f4744a
            com.appsamurai.storyly.data.managers.cache.c r0 = r6.f5048c
            com.appsamurai.storyly.data.v r6 = r6.getStorylyGroupItem$storyly_release()
            com.appsamurai.storyly.storylypresenter.o r5 = r5.f4744a
            com.appsamurai.storyly.data.z r5 = r5.getStorylyItem()
            r0.getClass()
            if (r6 != 0) goto L_0x004f
            goto L_0x00c4
        L_0x004f:
            if (r5 != 0) goto L_0x0053
            goto L_0x00c4
        L_0x0053:
            java.util.List r2 = r0.a()
            com.appsamurai.storyly.data.managers.cache.a r3 = new com.appsamurai.storyly.data.managers.cache.a
            r3.<init>(r6)
            java.lang.Object r2 = com.appsamurai.storyly.util.j.a(r2, r3)
            com.appsamurai.storyly.data.v r2 = (com.appsamurai.storyly.data.v) r2
            java.util.List<com.appsamurai.storyly.data.z> r3 = r6.f4226f
            com.appsamurai.storyly.data.managers.cache.b r4 = new com.appsamurai.storyly.data.managers.cache.b
            r4.<init>(r5)
            java.lang.Object r5 = com.appsamurai.storyly.util.j.a(r3, r4)
            com.appsamurai.storyly.data.z r5 = (com.appsamurai.storyly.data.z) r5
            if (r5 == 0) goto L_0x0088
            java.util.Map<java.lang.String, ? extends java.util.Map<java.lang.String, ? extends java.util.List<java.lang.String>>> r2 = r0.f3888b
            java.lang.String r6 = r6.f4221a
            java.lang.Object r6 = r2.get(r6)
            java.util.Map r6 = (java.util.Map) r6
            if (r6 != 0) goto L_0x007e
            goto L_0x00a8
        L_0x007e:
            java.lang.String r5 = r5.f4302a
            java.lang.Object r5 = r6.get(r5)
            r1 = r5
            java.util.List r1 = (java.util.List) r1
            goto L_0x00a8
        L_0x0088:
            if (r2 == 0) goto L_0x00a8
            java.util.Map<java.lang.String, ? extends java.util.Map<java.lang.String, ? extends java.util.List<java.lang.String>>> r5 = r0.f3888b
            java.lang.String r6 = r2.f4221a
            java.lang.Object r5 = r5.get(r6)
            java.util.Map r5 = (java.util.Map) r5
            if (r5 != 0) goto L_0x0097
            goto L_0x00a8
        L_0x0097:
            java.util.List<com.appsamurai.storyly.data.z> r6 = r2.f4226f
            java.lang.Object r6 = kotlin.collections.CollectionsKt.first(r6)
            com.appsamurai.storyly.data.z r6 = (com.appsamurai.storyly.data.z) r6
            java.lang.String r6 = r6.f4302a
            java.lang.Object r5 = r5.get(r6)
            r1 = r5
            java.util.List r1 = (java.util.List) r1
        L_0x00a8:
            if (r1 != 0) goto L_0x00ab
            goto L_0x00c4
        L_0x00ab:
            java.util.List<java.lang.String> r5 = r0.f3891e
            int r6 = r0.f3892f
            java.lang.Object r5 = kotlin.collections.CollectionsKt.getOrNull(r5, r6)
            boolean r5 = kotlin.collections.CollectionsKt.contains(r1, r5)
            r5 = r5 ^ 1
            r0.a(r5)
            java.util.List<java.lang.String> r5 = r0.f3891e
            r5.addAll(r1)
            r0.b()
        L_0x00c4:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.storylypresenter.a1.invoke(java.lang.Object):java.lang.Object");
    }
}
