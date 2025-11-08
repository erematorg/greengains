package com.appsamurai.storyly.data.managers.processing;

import com.appsamurai.storyly.data.managers.network.f;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

public final class i extends Lambda implements Function1<f, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ f f4012a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public i(f fVar) {
        super(1);
        this.f4012a = fVar;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v0, resolved type: java.util.Set<com.appsamurai.storyly.data.managers.product.STRProductItem>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: com.appsamurai.storyly.data.g} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v1, resolved type: java.util.Set<com.appsamurai.storyly.data.managers.product.STRProductItem>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: java.util.Set<com.appsamurai.storyly.data.managers.product.STRProductItem>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: java.util.Set<com.appsamurai.storyly.data.managers.product.STRProductItem>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v4, resolved type: java.util.Set<com.appsamurai.storyly.data.managers.product.STRProductItem>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v5, resolved type: java.util.Set<com.appsamurai.storyly.data.managers.product.STRProductItem>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v6, resolved type: java.util.Set<com.appsamurai.storyly.data.managers.product.STRProductItem>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v7, resolved type: java.util.Set<com.appsamurai.storyly.data.managers.product.STRProductItem>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v0, resolved type: java.lang.Float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v9, resolved type: java.util.Set<com.appsamurai.storyly.data.managers.product.STRProductItem>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v8, resolved type: java.util.Set<com.appsamurai.storyly.data.managers.product.STRProductItem>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v2, resolved type: java.util.List} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v11, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v12, resolved type: java.util.Set<com.appsamurai.storyly.data.managers.product.STRProductItem>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v1, resolved type: java.lang.Float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v2, resolved type: java.lang.Float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: com.appsamurai.storyly.data.g} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v17, resolved type: com.appsamurai.storyly.data.s} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v11, resolved type: java.util.Set<com.appsamurai.storyly.data.managers.product.STRProductItem>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v15, resolved type: com.appsamurai.storyly.data.s} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v12, resolved type: java.util.Set<com.appsamurai.storyly.data.managers.product.STRProductItem>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v13, resolved type: java.util.Set<com.appsamurai.storyly.data.managers.product.STRProductItem>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v15, resolved type: java.util.Set<com.appsamurai.storyly.data.managers.product.STRProductItem>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v19, resolved type: com.appsamurai.storyly.data.s} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v16, resolved type: java.util.Set<com.appsamurai.storyly.data.managers.product.STRProductItem>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v17, resolved type: java.util.Set<com.appsamurai.storyly.data.managers.product.STRProductItem>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v17, resolved type: java.util.Set<com.appsamurai.storyly.data.managers.product.STRProductItem>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v30, resolved type: com.appsamurai.storyly.data.g} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v31, resolved type: com.appsamurai.storyly.data.s} */
    /* JADX WARNING: type inference failed for: r21v0, types: [java.util.List] */
    /* JADX WARNING: type inference failed for: r21v1 */
    /* JADX WARNING: type inference failed for: r0v26, types: [java.util.List, java.util.List<com.appsamurai.storyly.data.managers.pagination.a>] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x01c9  */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x01d1  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00ee  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object invoke(java.lang.Object r23) {
        /*
            r22 = this;
            r0 = r23
            com.appsamurai.storyly.data.managers.network.f r0 = (com.appsamurai.storyly.data.managers.network.f) r0
            java.lang.String r1 = "networkResponse"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            r1 = r22
            com.appsamurai.storyly.data.managers.processing.f r1 = r1.f4012a
            kotlin.reflect.KProperty<java.lang.Object>[] r2 = com.appsamurai.storyly.data.managers.processing.f.f3970y
            r1.getClass()
            com.appsamurai.storyly.data.managers.processing.e r2 = r0.f3921a
            int r2 = r2.ordinal()
            r3 = 1
            if (r2 == r3) goto L_0x01d8
            r4 = 2
            r5 = 6
            r6 = 0
            r7 = 0
            if (r2 == r4) goto L_0x00a3
            r4 = 3
            if (r2 == r4) goto L_0x0055
            r3 = 4
            if (r2 == r3) goto L_0x0029
            goto L_0x01dd
        L_0x0029:
            com.appsamurai.storyly.data.managers.processing.a r0 = r0.f3922b
            java.lang.String r0 = r0.f3947a
            r1.f3981k = r7
            kotlinx.serialization.json.Json r2 = r1.c()     // Catch:{ Exception -> 0x003c }
            com.appsamurai.storyly.data.managers.pagination.b$a r3 = com.appsamurai.storyly.data.managers.pagination.b.a.f3940a     // Catch:{ Exception -> 0x003c }
            java.lang.Object r0 = r2.decodeFromString(r3, r0)     // Catch:{ Exception -> 0x003c }
            com.appsamurai.storyly.data.managers.pagination.b r0 = (com.appsamurai.storyly.data.managers.pagination.b) r0     // Catch:{ Exception -> 0x003c }
            goto L_0x003d
        L_0x003c:
            r0 = r7
        L_0x003d:
            if (r0 != 0) goto L_0x0040
            goto L_0x004c
        L_0x0040:
            java.util.List<com.appsamurai.storyly.data.managers.pagination.a> r0 = r0.f3939a
            if (r0 != 0) goto L_0x0045
            goto L_0x004c
        L_0x0045:
            r1.a((java.util.List<com.appsamurai.storyly.data.managers.pagination.a>) r0)
            com.appsamurai.storyly.data.managers.processing.f.a(r1, r6, r6, r4)
            r7 = r0
        L_0x004c:
            if (r7 != 0) goto L_0x01dd
            java.lang.String r0 = "Moments group id list data parse failed"
            com.appsamurai.storyly.data.managers.processing.f.a((com.appsamurai.storyly.data.managers.processing.f) r1, (java.lang.String) r0, (boolean) r6, (boolean) r6, (int) r5)
            goto L_0x01dd
        L_0x0055:
            com.appsamurai.storyly.data.managers.processing.a r0 = r0.f3922b
            java.lang.String r0 = r0.f3947a
            kotlinx.serialization.json.Json r2 = r1.c()     // Catch:{ Exception -> 0x0066 }
            com.appsamurai.storyly.data.s$a r4 = com.appsamurai.storyly.data.s.f4195f     // Catch:{ Exception -> 0x0066 }
            java.lang.Object r0 = r2.decodeFromString(r4, r0)     // Catch:{ Exception -> 0x0066 }
            com.appsamurai.storyly.data.s r0 = (com.appsamurai.storyly.data.s) r0     // Catch:{ Exception -> 0x0066 }
            goto L_0x0067
        L_0x0066:
            r0 = r7
        L_0x0067:
            if (r0 != 0) goto L_0x006a
            goto L_0x009a
        L_0x006a:
            java.util.List<com.appsamurai.storyly.data.v> r2 = r0.f4197a
            java.util.Iterator r2 = r2.iterator()
        L_0x0070:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x0081
            java.lang.Object r4 = r2.next()
            com.appsamurai.storyly.data.v r4 = (com.appsamurai.storyly.data.v) r4
            java.lang.String r8 = r1.f3983m
            r4.f4233m = r8
            goto L_0x0070
        L_0x0081:
            com.appsamurai.storyly.data.s r2 = r1.f3981k
            if (r2 != 0) goto L_0x0086
            goto L_0x0092
        L_0x0086:
            java.util.List<com.appsamurai.storyly.data.v> r4 = r2.f4197a
            java.util.List<com.appsamurai.storyly.data.v> r7 = r0.f4197a
            java.util.List r4 = kotlin.collections.CollectionsKt.plus(r4, r7)
            r2.a(r4)
            r7 = r2
        L_0x0092:
            if (r7 != 0) goto L_0x0096
            r1.f3981k = r0
        L_0x0096:
            r1.a(r3, r3)
            r7 = r0
        L_0x009a:
            if (r7 != 0) goto L_0x01dd
            java.lang.String r0 = "Moments page data parse failed"
            com.appsamurai.storyly.data.managers.processing.f.a((com.appsamurai.storyly.data.managers.processing.f) r1, (java.lang.String) r0, (boolean) r6, (boolean) r6, (int) r5)
            goto L_0x01dd
        L_0x00a3:
            com.appsamurai.storyly.data.managers.processing.a r0 = r0.f3922b
            com.appsamurai.storyly.StorylyInit r2 = r1.i()
            java.lang.String r2 = r2.getStorylyId()
            int r2 = r2.length()
            if (r2 != 0) goto L_0x00c6
            com.appsamurai.storyly.StorylyInit r0 = r1.i()
            java.lang.String r0 = r0.getStorylyId()
            java.lang.String r2 = "Please set storylyId to a valid value. storylyId is "
            java.lang.String r0 = kotlin.jvm.internal.Intrinsics.stringPlus(r2, r0)
            com.appsamurai.storyly.data.managers.processing.f.a((com.appsamurai.storyly.data.managers.processing.f) r1, (java.lang.String) r0, (boolean) r6, (boolean) r6, (int) r5)
            goto L_0x01dd
        L_0x00c6:
            com.appsamurai.storyly.data.managers.product.b r2 = r1.e()
            java.util.Set<com.appsamurai.storyly.data.managers.product.STRProductItem> r2 = r2.f4042b
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L_0x01d3
            java.lang.String r0 = r0.f3947a
            if (r0 != 0) goto L_0x00d8
        L_0x00d6:
            r0 = r7
            goto L_0x00ea
        L_0x00d8:
            kotlinx.serialization.json.Json r2 = r1.c()     // Catch:{ Exception -> 0x00e5 }
            com.appsamurai.storyly.data.g$a r3 = com.appsamurai.storyly.data.g.a.f3720a     // Catch:{ Exception -> 0x00e5 }
            java.lang.Object r0 = r2.decodeFromString(r3, r0)     // Catch:{ Exception -> 0x00e5 }
            com.appsamurai.storyly.data.g r0 = (com.appsamurai.storyly.data.g) r0     // Catch:{ Exception -> 0x00e5 }
            goto L_0x00ea
        L_0x00e5:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x00d6
        L_0x00ea:
            if (r0 != 0) goto L_0x00ee
            goto L_0x01c7
        L_0x00ee:
            com.appsamurai.storyly.data.managers.product.b r2 = r1.e()
            java.util.List<com.appsamurai.storyly.data.f> r3 = r0.f3719a
            if (r3 != 0) goto L_0x00f8
            goto L_0x01b6
        L_0x00f8:
            java.util.ArrayList r4 = new java.util.ArrayList
            int r8 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r3, 10)
            r4.<init>(r8)
            java.util.Iterator r3 = r3.iterator()
        L_0x0105:
            boolean r8 = r3.hasNext()
            if (r8 == 0) goto L_0x01b2
            java.lang.Object r8 = r3.next()
            com.appsamurai.storyly.data.f r8 = (com.appsamurai.storyly.data.f) r8
            java.lang.String r9 = r8.f3682i
            java.lang.String r10 = ""
            if (r9 != 0) goto L_0x011a
            r19 = r10
            goto L_0x011c
        L_0x011a:
            r19 = r9
        L_0x011c:
            java.lang.String r9 = r8.f3677d
            java.util.ArrayList<java.lang.String> r15 = r8.f3679f
            java.lang.Double r11 = r8.f3680g
            if (r11 != 0) goto L_0x0128
            r11 = 0
        L_0x0125:
            r17 = r11
            goto L_0x012e
        L_0x0128:
            double r11 = r11.doubleValue()
            float r11 = (float) r11
            goto L_0x0125
        L_0x012e:
            java.lang.Double r11 = r8.f3681h
            if (r11 != 0) goto L_0x0135
            r18 = r7
            goto L_0x0140
        L_0x0135:
            double r11 = r11.doubleValue()
            float r11 = (float) r11
            java.lang.Float r11 = java.lang.Float.valueOf(r11)
            r18 = r11
        L_0x0140:
            java.lang.String r11 = r8.f3675b
            if (r11 != 0) goto L_0x0146
            r13 = r10
            goto L_0x0147
        L_0x0146:
            r13 = r11
        L_0x0147:
            java.lang.String r11 = r8.f3674a
            if (r11 != 0) goto L_0x014d
            r12 = r10
            goto L_0x014e
        L_0x014d:
            r12 = r11
        L_0x014e:
            java.lang.String r11 = r8.f3676c
            if (r11 != 0) goto L_0x0154
            r14 = r10
            goto L_0x0155
        L_0x0154:
            r14 = r11
        L_0x0155:
            java.lang.String r11 = r8.f3678e
            if (r11 != 0) goto L_0x015c
            r16 = r10
            goto L_0x015e
        L_0x015c:
            r16 = r11
        L_0x015e:
            java.util.List<com.appsamurai.storyly.data.h> r8 = r8.f3683j
            if (r8 != 0) goto L_0x0164
            r11 = r7
            goto L_0x0192
        L_0x0164:
            java.util.ArrayList r11 = new java.util.ArrayList
            int r7 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r8, 10)
            r11.<init>(r7)
            java.util.Iterator r7 = r8.iterator()
        L_0x0171:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto L_0x0192
            java.lang.Object r8 = r7.next()
            com.appsamurai.storyly.data.h r8 = (com.appsamurai.storyly.data.h) r8
            com.appsamurai.storyly.data.managers.product.STRProductVariant r5 = new com.appsamurai.storyly.data.managers.product.STRProductVariant
            java.lang.String r6 = r8.f3722a
            if (r6 != 0) goto L_0x0184
            r6 = r10
        L_0x0184:
            java.lang.String r8 = r8.f3723b
            if (r8 != 0) goto L_0x0189
            r8 = r10
        L_0x0189:
            r5.<init>(r6, r8)
            r11.add(r5)
            r5 = 6
            r6 = 0
            goto L_0x0171
        L_0x0192:
            if (r11 != 0) goto L_0x019b
            java.util.List r5 = kotlin.collections.CollectionsKt.emptyList()
            r21 = r5
            goto L_0x019d
        L_0x019b:
            r21 = r11
        L_0x019d:
            com.appsamurai.storyly.data.managers.product.STRProductItem r5 = new com.appsamurai.storyly.data.managers.product.STRProductItem
            r11 = r5
            r6 = r15
            r15 = r16
            r16 = r9
            r20 = r6
            r11.<init>(r12, r13, r14, r15, r16, r17, r18, r19, r20, r21)
            r4.add(r5)
            r5 = 6
            r6 = 0
            r7 = 0
            goto L_0x0105
        L_0x01b2:
            java.util.Set r7 = kotlin.collections.CollectionsKt.toSet(r4)
        L_0x01b6:
            if (r7 != 0) goto L_0x01bc
            java.util.Set r7 = kotlin.collections.SetsKt.emptySet()
        L_0x01bc:
            r2.getClass()
            java.lang.String r3 = "<set-?>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r3)
            r2.f4042b = r7
            r7 = r0
        L_0x01c7:
            if (r7 != 0) goto L_0x01d1
            java.lang.String r0 = "Storyly product data parse failed"
            r2 = 6
            r3 = 0
            com.appsamurai.storyly.data.managers.processing.f.a((com.appsamurai.storyly.data.managers.processing.f) r1, (java.lang.String) r0, (boolean) r3, (boolean) r3, (int) r2)
            goto L_0x01d4
        L_0x01d1:
            r3 = 0
            goto L_0x01d4
        L_0x01d3:
            r3 = r6
        L_0x01d4:
            r1.a(r3, r3)
            goto L_0x01dd
        L_0x01d8:
            com.appsamurai.storyly.data.managers.processing.a r0 = r0.f3922b
            r1.a((com.appsamurai.storyly.data.managers.processing.a) r0)
        L_0x01dd:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.managers.processing.i.invoke(java.lang.Object):java.lang.Object");
    }
}
