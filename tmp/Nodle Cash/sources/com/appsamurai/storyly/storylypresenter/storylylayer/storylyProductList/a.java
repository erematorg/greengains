package com.appsamurai.storyly.storylypresenter.storylylayer.storylyProductList;

import androidx.annotation.ColorInt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class a {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final Integer f6047a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    public final Integer f6048b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    public final Integer f6049c;
    @Nullable

    /* renamed from: d  reason: collision with root package name */
    public final Integer f6050d;
    @Nullable

    /* renamed from: e  reason: collision with root package name */
    public final Integer f6051e;
    @Nullable

    /* renamed from: f  reason: collision with root package name */
    public final String f6052f;
    @Nullable

    /* renamed from: g  reason: collision with root package name */
    public final Boolean f6053g;
    @Nullable

    /* renamed from: h  reason: collision with root package name */
    public final Boolean f6054h;
    @Nullable

    /* renamed from: i  reason: collision with root package name */
    public final Boolean f6055i;

    public a() {
        this((Integer) null, (Integer) null, (Integer) null, (Integer) null, (Integer) null, (String) null, (Boolean) null, (Boolean) null, (Boolean) null, 511);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return Intrinsics.areEqual((Object) this.f6047a, (Object) aVar.f6047a) && Intrinsics.areEqual((Object) this.f6048b, (Object) aVar.f6048b) && Intrinsics.areEqual((Object) this.f6049c, (Object) aVar.f6049c) && Intrinsics.areEqual((Object) this.f6050d, (Object) aVar.f6050d) && Intrinsics.areEqual((Object) this.f6051e, (Object) aVar.f6051e) && Intrinsics.areEqual((Object) this.f6052f, (Object) aVar.f6052f) && Intrinsics.areEqual((Object) this.f6053g, (Object) aVar.f6053g) && Intrinsics.areEqual((Object) this.f6054h, (Object) aVar.f6054h) && Intrinsics.areEqual((Object) this.f6055i, (Object) aVar.f6055i);
    }

    public int hashCode() {
        Integer num = this.f6047a;
        int i3 = 0;
        int hashCode = (num == null ? 0 : num.hashCode()) * 31;
        Integer num2 = this.f6048b;
        int hashCode2 = (hashCode + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.f6049c;
        int hashCode3 = (hashCode2 + (num3 == null ? 0 : num3.hashCode())) * 31;
        Integer num4 = this.f6050d;
        int hashCode4 = (hashCode3 + (num4 == null ? 0 : num4.hashCode())) * 31;
        Integer num5 = this.f6051e;
        int hashCode5 = (hashCode4 + (num5 == null ? 0 : num5.hashCode())) * 31;
        String str = this.f6052f;
        int hashCode6 = (hashCode5 + (str == null ? 0 : str.hashCode())) * 31;
        Boolean bool = this.f6053g;
        int hashCode7 = (hashCode6 + (bool == null ? 0 : bool.hashCode())) * 31;
        Boolean bool2 = this.f6054h;
        int hashCode8 = (hashCode7 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        Boolean bool3 = this.f6055i;
        if (bool3 != null) {
            i3 = bool3.hashCode();
        }
        return hashCode8 + i3;
    }

    @NotNull
    public String toString() {
        return "ProductListItemEntity(borderColor=" + this.f6047a + ", bgColor=" + this.f6048b + ", textColor=" + this.f6049c + ", buttonBgColor=" + this.f6050d + ", oldPriceColor=" + this.f6051e + ", purchaseText=" + this.f6052f + ", isPriceVisible=" + this.f6053g + ", isOldPriceVisible=" + this.f6054h + ", isTitleVisible=" + this.f6055i + ')';
    }

    public a(@Nullable @ColorInt Integer num, @Nullable @ColorInt Integer num2, @Nullable @ColorInt Integer num3, @Nullable @ColorInt Integer num4, @Nullable @ColorInt Integer num5, @Nullable String str, @Nullable Boolean bool, @Nullable Boolean bool2, @Nullable Boolean bool3) {
        this.f6047a = num;
        this.f6048b = num2;
        this.f6049c = num3;
        this.f6050d = num4;
        this.f6051e = num5;
        this.f6052f = str;
        this.f6053g = bool;
        this.f6054h = bool2;
        this.f6055i = bool3;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ a(java.lang.Integer r11, java.lang.Integer r12, java.lang.Integer r13, java.lang.Integer r14, java.lang.Integer r15, java.lang.String r16, java.lang.Boolean r17, java.lang.Boolean r18, java.lang.Boolean r19, int r20) {
        /*
            r10 = this;
            r0 = r20
            r1 = r0 & 1
            r2 = 0
            if (r1 == 0) goto L_0x0012
            java.lang.String r1 = "#EEEEEE"
            int r1 = android.graphics.Color.parseColor(r1)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            goto L_0x0013
        L_0x0012:
            r1 = r2
        L_0x0013:
            r3 = r0 & 2
            if (r3 == 0) goto L_0x001d
            r3 = -1
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            goto L_0x001e
        L_0x001d:
            r3 = r2
        L_0x001e:
            r4 = r0 & 4
            r5 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            if (r4 == 0) goto L_0x0029
            java.lang.Integer r4 = java.lang.Integer.valueOf(r5)
            goto L_0x002a
        L_0x0029:
            r4 = r2
        L_0x002a:
            r6 = r0 & 8
            if (r6 == 0) goto L_0x0033
            java.lang.Integer r6 = java.lang.Integer.valueOf(r5)
            goto L_0x0034
        L_0x0033:
            r6 = r2
        L_0x0034:
            r7 = r0 & 16
            if (r7 == 0) goto L_0x003d
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            goto L_0x003e
        L_0x003d:
            r5 = r2
        L_0x003e:
            r7 = r0 & 32
            if (r7 == 0) goto L_0x0045
            java.lang.String r7 = ""
            goto L_0x0046
        L_0x0045:
            r7 = r2
        L_0x0046:
            r8 = r0 & 64
            if (r8 == 0) goto L_0x004d
            java.lang.Boolean r8 = java.lang.Boolean.TRUE
            goto L_0x004e
        L_0x004d:
            r8 = r2
        L_0x004e:
            r9 = r0 & 128(0x80, float:1.794E-43)
            if (r9 == 0) goto L_0x0055
            java.lang.Boolean r9 = java.lang.Boolean.TRUE
            goto L_0x0056
        L_0x0055:
            r9 = r2
        L_0x0056:
            r0 = r0 & 256(0x100, float:3.59E-43)
            if (r0 == 0) goto L_0x005c
            java.lang.Boolean r2 = java.lang.Boolean.TRUE
        L_0x005c:
            r11 = r1
            r12 = r3
            r13 = r4
            r14 = r6
            r15 = r5
            r16 = r7
            r17 = r8
            r18 = r9
            r19 = r2
            r10.<init>(r11, r12, r13, r14, r15, r16, r17, r18, r19)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.storylypresenter.storylylayer.storylyProductList.a.<init>(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean, int):void");
    }
}
