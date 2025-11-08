package com.appsamurai.storyly.data;

import com.appsamurai.storyly.data.h;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.DoubleSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable
public final class f {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public String f3674a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    public String f3675b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    public String f3676c;
    @Nullable

    /* renamed from: d  reason: collision with root package name */
    public String f3677d;
    @Nullable

    /* renamed from: e  reason: collision with root package name */
    public String f3678e;
    @Nullable

    /* renamed from: f  reason: collision with root package name */
    public ArrayList<String> f3679f;
    @Nullable

    /* renamed from: g  reason: collision with root package name */
    public Double f3680g;
    @Nullable

    /* renamed from: h  reason: collision with root package name */
    public Double f3681h;
    @Nullable

    /* renamed from: i  reason: collision with root package name */
    public String f3682i;
    @Nullable

    /* renamed from: j  reason: collision with root package name */
    public List<h> f3683j;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final class a implements GeneratedSerializer<f> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public static final a f3684a;

        /* renamed from: b  reason: collision with root package name */
        public static final /* synthetic */ SerialDescriptor f3685b;

        static {
            a aVar = new a();
            f3684a = aVar;
            PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.appsamurai.storyly.data.FallbackProduct", aVar, 10);
            pluginGeneratedSerialDescriptor.addElement("product_id", true);
            pluginGeneratedSerialDescriptor.addElement("product_group_id", true);
            pluginGeneratedSerialDescriptor.addElement("title", true);
            pluginGeneratedSerialDescriptor.addElement("description", true);
            pluginGeneratedSerialDescriptor.addElement("url", true);
            pluginGeneratedSerialDescriptor.addElement("image_urls", true);
            pluginGeneratedSerialDescriptor.addElement(FirebaseAnalytics.Param.PRICE, true);
            pluginGeneratedSerialDescriptor.addElement("sales_price", true);
            pluginGeneratedSerialDescriptor.addElement("price_currency", true);
            pluginGeneratedSerialDescriptor.addElement("variant", true);
            f3685b = pluginGeneratedSerialDescriptor;
        }

        @NotNull
        public KSerializer<?>[] childSerializers() {
            StringSerializer stringSerializer = StringSerializer.INSTANCE;
            KSerializer<?> nullable = BuiltinSerializersKt.getNullable(stringSerializer);
            KSerializer<?> nullable2 = BuiltinSerializersKt.getNullable(stringSerializer);
            KSerializer<?> nullable3 = BuiltinSerializersKt.getNullable(stringSerializer);
            KSerializer<?> nullable4 = BuiltinSerializersKt.getNullable(stringSerializer);
            KSerializer<?> nullable5 = BuiltinSerializersKt.getNullable(stringSerializer);
            KSerializer<?> nullable6 = BuiltinSerializersKt.getNullable(new ArrayListSerializer(stringSerializer));
            DoubleSerializer doubleSerializer = DoubleSerializer.INSTANCE;
            return new KSerializer[]{nullable, nullable2, nullable3, nullable4, nullable5, nullable6, BuiltinSerializersKt.getNullable(doubleSerializer), BuiltinSerializersKt.getNullable(doubleSerializer), BuiltinSerializersKt.getNullable(stringSerializer), BuiltinSerializersKt.getNullable(new ArrayListSerializer(h.a.f3724a))};
        }

        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0112, code lost:
            r3 = 9;
            r4 = 7;
            r5 = 6;
            r6 = 5;
            r8 = 8;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x0072, code lost:
            r10 = r10;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x0072, code lost:
            r10 = r10;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x0072, code lost:
            r10 = r10;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x0072, code lost:
            r10 = r10;
         */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object deserialize(kotlinx.serialization.encoding.Decoder r24) {
            /*
                r23 = this;
                r0 = r24
                java.lang.String r1 = "decoder"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
                kotlinx.serialization.descriptors.SerialDescriptor r1 = f3685b
                kotlinx.serialization.encoding.CompositeDecoder r0 = r0.beginStructure(r1)
                boolean r2 = r0.decodeSequentially()
                r3 = 9
                r4 = 7
                r5 = 6
                r6 = 5
                r7 = 3
                r8 = 8
                r9 = 4
                r10 = 2
                r11 = 1
                r12 = 0
                r13 = 0
                if (r2 == 0) goto L_0x0062
                kotlinx.serialization.internal.StringSerializer r2 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                java.lang.Object r12 = r0.decodeNullableSerializableElement(r1, r12, r2, r13)
                java.lang.Object r11 = r0.decodeNullableSerializableElement(r1, r11, r2, r13)
                java.lang.Object r10 = r0.decodeNullableSerializableElement(r1, r10, r2, r13)
                java.lang.Object r7 = r0.decodeNullableSerializableElement(r1, r7, r2, r13)
                java.lang.Object r9 = r0.decodeNullableSerializableElement(r1, r9, r2, r13)
                kotlinx.serialization.internal.ArrayListSerializer r14 = new kotlinx.serialization.internal.ArrayListSerializer
                r14.<init>(r2)
                java.lang.Object r6 = r0.decodeNullableSerializableElement(r1, r6, r14, r13)
                kotlinx.serialization.internal.DoubleSerializer r14 = kotlinx.serialization.internal.DoubleSerializer.INSTANCE
                java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r5, r14, r13)
                java.lang.Object r4 = r0.decodeNullableSerializableElement(r1, r4, r14, r13)
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r8, r2, r13)
                kotlinx.serialization.internal.ArrayListSerializer r8 = new kotlinx.serialization.internal.ArrayListSerializer
                com.appsamurai.storyly.data.h$a r14 = com.appsamurai.storyly.data.h.a.f3724a
                r8.<init>(r14)
                java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r3, r8, r13)
                r8 = 1023(0x3ff, float:1.434E-42)
                r22 = r9
                r9 = r8
                r8 = r10
                r10 = r22
                goto L_0x013d
            L_0x0062:
                r21 = r11
                r2 = r12
                r10 = r13
                r11 = r10
                r12 = r11
                r14 = r12
                r15 = r14
                r17 = r15
                r18 = r17
                r19 = r18
                r20 = r19
            L_0x0072:
                if (r21 == 0) goto L_0x0128
                int r7 = r0.decodeElementIndex(r1)
                switch(r7) {
                    case -1: goto L_0x011c;
                    case 0: goto L_0x0100;
                    case 1: goto L_0x00ea;
                    case 2: goto L_0x00d7;
                    case 3: goto L_0x00c5;
                    case 4: goto L_0x00ba;
                    case 5: goto L_0x00ac;
                    case 6: goto L_0x00a3;
                    case 7: goto L_0x009a;
                    case 8: goto L_0x0091;
                    case 9: goto L_0x0081;
                    default: goto L_0x007b;
                }
            L_0x007b:
                kotlinx.serialization.UnknownFieldException r0 = new kotlinx.serialization.UnknownFieldException
                r0.<init>((int) r7)
                throw r0
            L_0x0081:
                kotlinx.serialization.internal.ArrayListSerializer r7 = new kotlinx.serialization.internal.ArrayListSerializer
                com.appsamurai.storyly.data.h$a r9 = com.appsamurai.storyly.data.h.a.f3724a
                r7.<init>(r9)
                java.lang.Object r13 = r0.decodeNullableSerializableElement(r1, r3, r7, r13)
                r2 = r2 | 512(0x200, float:7.175E-43)
            L_0x008e:
                r7 = 3
                r9 = 4
                goto L_0x0072
            L_0x0091:
                kotlinx.serialization.internal.StringSerializer r7 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                java.lang.Object r14 = r0.decodeNullableSerializableElement(r1, r8, r7, r14)
                r2 = r2 | 256(0x100, float:3.59E-43)
                goto L_0x008e
            L_0x009a:
                kotlinx.serialization.internal.DoubleSerializer r7 = kotlinx.serialization.internal.DoubleSerializer.INSTANCE
                java.lang.Object r15 = r0.decodeNullableSerializableElement(r1, r4, r7, r15)
                r2 = r2 | 128(0x80, float:1.794E-43)
                goto L_0x008e
            L_0x00a3:
                kotlinx.serialization.internal.DoubleSerializer r7 = kotlinx.serialization.internal.DoubleSerializer.INSTANCE
                java.lang.Object r12 = r0.decodeNullableSerializableElement(r1, r5, r7, r12)
                r2 = r2 | 64
                goto L_0x008e
            L_0x00ac:
                kotlinx.serialization.internal.ArrayListSerializer r7 = new kotlinx.serialization.internal.ArrayListSerializer
                kotlinx.serialization.internal.StringSerializer r9 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r7.<init>(r9)
                java.lang.Object r11 = r0.decodeNullableSerializableElement(r1, r6, r7, r11)
                r2 = r2 | 32
                goto L_0x008e
            L_0x00ba:
                kotlinx.serialization.internal.StringSerializer r7 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r9 = 4
                java.lang.Object r10 = r0.decodeNullableSerializableElement(r1, r9, r7, r10)
                r2 = r2 | 16
            L_0x00c3:
                r7 = 3
                goto L_0x0072
            L_0x00c5:
                kotlinx.serialization.internal.StringSerializer r7 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r3 = r17
                r4 = 3
                java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r4, r7, r3)
                r2 = r2 | 8
                r17 = r3
                r7 = r4
                r3 = 9
                r4 = 7
                goto L_0x0072
            L_0x00d7:
                r3 = r17
                r4 = 3
                kotlinx.serialization.internal.StringSerializer r7 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r4 = r18
                r5 = 2
                java.lang.Object r18 = r0.decodeNullableSerializableElement(r1, r5, r7, r4)
                r2 = r2 | 4
                r3 = 9
                r4 = 7
                r5 = 6
                goto L_0x00c3
            L_0x00ea:
                r3 = r17
                r4 = r18
                r5 = 2
                kotlinx.serialization.internal.StringSerializer r7 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r5 = r19
                r6 = 1
                java.lang.Object r19 = r0.decodeNullableSerializableElement(r1, r6, r7, r5)
                r2 = r2 | 2
                r3 = 9
                r4 = 7
                r5 = 6
                r6 = 5
                goto L_0x00c3
            L_0x0100:
                r3 = r17
                r4 = r18
                r5 = r19
                r6 = 1
                kotlinx.serialization.internal.StringSerializer r7 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r6 = r20
                r8 = 0
                java.lang.Object r20 = r0.decodeNullableSerializableElement(r1, r8, r7, r6)
                r2 = r2 | 1
            L_0x0112:
                r3 = 9
                r4 = 7
                r5 = 6
                r6 = 5
                r7 = 3
                r8 = 8
                goto L_0x0072
            L_0x011c:
                r3 = r17
                r4 = r18
                r5 = r19
                r6 = r20
                r8 = 0
                r21 = r8
                goto L_0x0112
            L_0x0128:
                r3 = r17
                r4 = r18
                r5 = r19
                r6 = r20
                r9 = r2
                r7 = r3
                r8 = r4
                r3 = r13
                r2 = r14
                r4 = r15
                r22 = r11
                r11 = r5
                r5 = r12
                r12 = r6
                r6 = r22
            L_0x013d:
                r0.endStructure(r1)
                com.appsamurai.storyly.data.f r0 = new com.appsamurai.storyly.data.f
                r1 = r12
                java.lang.String r1 = (java.lang.String) r1
                java.lang.String r11 = (java.lang.String) r11
                r12 = r8
                java.lang.String r12 = (java.lang.String) r12
                r13 = r7
                java.lang.String r13 = (java.lang.String) r13
                r14 = r10
                java.lang.String r14 = (java.lang.String) r14
                r15 = r6
                java.util.ArrayList r15 = (java.util.ArrayList) r15
                r16 = r5
                java.lang.Double r16 = (java.lang.Double) r16
                r17 = r4
                java.lang.Double r17 = (java.lang.Double) r17
                r18 = r2
                java.lang.String r18 = (java.lang.String) r18
                r19 = r3
                java.util.List r19 = (java.util.List) r19
                r8 = r0
                r10 = r1
                r8.<init>((int) r9, (java.lang.String) r10, (java.lang.String) r11, (java.lang.String) r12, (java.lang.String) r13, (java.lang.String) r14, (java.util.ArrayList) r15, (java.lang.Double) r16, (java.lang.Double) r17, (java.lang.String) r18, (java.util.List) r19)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.f.a.deserialize(kotlinx.serialization.encoding.Decoder):java.lang.Object");
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return f3685b;
        }

        public void serialize(Encoder encoder, Object obj) {
            f fVar = (f) obj;
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter(fVar, "value");
            SerialDescriptor serialDescriptor = f3685b;
            CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
            Intrinsics.checkNotNullParameter(fVar, "self");
            Intrinsics.checkNotNullParameter(beginStructure, "output");
            Intrinsics.checkNotNullParameter(serialDescriptor, "serialDesc");
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 0) || fVar.f3674a != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 0, StringSerializer.INSTANCE, fVar.f3674a);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 1) || fVar.f3675b != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 1, StringSerializer.INSTANCE, fVar.f3675b);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 2) || fVar.f3676c != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 2, StringSerializer.INSTANCE, fVar.f3676c);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 3) || fVar.f3677d != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 3, StringSerializer.INSTANCE, fVar.f3677d);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 4) || fVar.f3678e != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 4, StringSerializer.INSTANCE, fVar.f3678e);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 5) || fVar.f3679f != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 5, new ArrayListSerializer(StringSerializer.INSTANCE), fVar.f3679f);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 6) || fVar.f3680g != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 6, DoubleSerializer.INSTANCE, fVar.f3680g);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 7) || fVar.f3681h != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 7, DoubleSerializer.INSTANCE, fVar.f3681h);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 8) || fVar.f3682i != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 8, StringSerializer.INSTANCE, fVar.f3682i);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 9) || fVar.f3683j != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 9, new ArrayListSerializer(h.a.f3724a), fVar.f3683j);
            }
            beginStructure.endStructure(serialDescriptor);
        }

        @NotNull
        public KSerializer<?>[] typeParametersSerializers() {
            return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
        }
    }

    public f() {
        this((String) null, (String) null, (String) null, (String) null, (String) null, (ArrayList) null, (Double) null, (Double) null, (String) null, (List) null, 1023);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        return Intrinsics.areEqual((Object) this.f3674a, (Object) fVar.f3674a) && Intrinsics.areEqual((Object) this.f3675b, (Object) fVar.f3675b) && Intrinsics.areEqual((Object) this.f3676c, (Object) fVar.f3676c) && Intrinsics.areEqual((Object) this.f3677d, (Object) fVar.f3677d) && Intrinsics.areEqual((Object) this.f3678e, (Object) fVar.f3678e) && Intrinsics.areEqual((Object) this.f3679f, (Object) fVar.f3679f) && Intrinsics.areEqual((Object) this.f3680g, (Object) fVar.f3680g) && Intrinsics.areEqual((Object) this.f3681h, (Object) fVar.f3681h) && Intrinsics.areEqual((Object) this.f3682i, (Object) fVar.f3682i) && Intrinsics.areEqual((Object) this.f3683j, (Object) fVar.f3683j);
    }

    public int hashCode() {
        String str = this.f3674a;
        int i3 = 0;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.f3675b;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.f3676c;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.f3677d;
        int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.f3678e;
        int hashCode5 = (hashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        ArrayList<String> arrayList = this.f3679f;
        int hashCode6 = (hashCode5 + (arrayList == null ? 0 : arrayList.hashCode())) * 31;
        Double d2 = this.f3680g;
        int hashCode7 = (hashCode6 + (d2 == null ? 0 : d2.hashCode())) * 31;
        Double d3 = this.f3681h;
        int hashCode8 = (hashCode7 + (d3 == null ? 0 : d3.hashCode())) * 31;
        String str6 = this.f3682i;
        int hashCode9 = (hashCode8 + (str6 == null ? 0 : str6.hashCode())) * 31;
        List<h> list = this.f3683j;
        if (list != null) {
            i3 = list.hashCode();
        }
        return hashCode9 + i3;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder("FallbackProduct(productId=");
        sb.append(this.f3674a);
        sb.append(", productGroupId=");
        sb.append(this.f3675b);
        sb.append(", title=");
        sb.append(this.f3676c);
        sb.append(", description=");
        sb.append(this.f3677d);
        sb.append(", url=");
        sb.append(this.f3678e);
        sb.append(", imageUrls=");
        sb.append(this.f3679f);
        sb.append(", price=");
        sb.append(this.f3680g);
        sb.append(", salesPrice=");
        sb.append(this.f3681h);
        sb.append(", priceCurrency=");
        sb.append(this.f3682i);
        sb.append(", variants=");
        return androidx.compose.ui.autofill.a.k(sb, this.f3683j, ')');
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ f(int i3, @SerialName("product_id") String str, @SerialName("product_group_id") String str2, @SerialName("title") String str3, @SerialName("description") String str4, @SerialName("url") String str5, @SerialName("image_urls") ArrayList arrayList, @SerialName("price") Double d2, @SerialName("sales_price") Double d3, @SerialName("price_currency") String str6, @SerialName("variant") List list) {
        if ((i3 & 1) == 0) {
            this.f3674a = null;
        } else {
            this.f3674a = str;
        }
        if ((i3 & 2) == 0) {
            this.f3675b = null;
        } else {
            this.f3675b = str2;
        }
        if ((i3 & 4) == 0) {
            this.f3676c = null;
        } else {
            this.f3676c = str3;
        }
        if ((i3 & 8) == 0) {
            this.f3677d = null;
        } else {
            this.f3677d = str4;
        }
        if ((i3 & 16) == 0) {
            this.f3678e = null;
        } else {
            this.f3678e = str5;
        }
        if ((i3 & 32) == 0) {
            this.f3679f = null;
        } else {
            this.f3679f = arrayList;
        }
        if ((i3 & 64) == 0) {
            this.f3680g = null;
        } else {
            this.f3680g = d2;
        }
        if ((i3 & 128) == 0) {
            this.f3681h = null;
        } else {
            this.f3681h = d3;
        }
        if ((i3 & 256) == 0) {
            this.f3682i = null;
        } else {
            this.f3682i = str6;
        }
        if ((i3 & 512) == 0) {
            this.f3683j = null;
        } else {
            this.f3683j = list;
        }
    }

    public f(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable ArrayList<String> arrayList, @Nullable Double d2, @Nullable Double d3, @Nullable String str6, @Nullable List<h> list) {
        this.f3674a = str;
        this.f3675b = str2;
        this.f3676c = str3;
        this.f3677d = str4;
        this.f3678e = str5;
        this.f3679f = arrayList;
        this.f3680g = d2;
        this.f3681h = d3;
        this.f3682i = str6;
        this.f3683j = list;
    }

    public /* synthetic */ f(String str, String str2, String str3, String str4, String str5, ArrayList arrayList, Double d2, Double d3, String str6, List list, int i3) {
        this((String) null, (String) null, (String) null, (String) null, (String) null, (ArrayList<String>) null, (Double) null, (Double) null, (String) null, (List<h>) null);
    }
}
