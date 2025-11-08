package com.appsamurai.storyly.data.managers.product;

import androidx.annotation.Keep;
import com.appsamurai.storyly.data.managers.product.STRProductVariant;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.FloatSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonElementBuildersKt;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonObjectBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b%\n\u0002\u0018\u0002\n\u0002\b\u0006\b\b\u0018\u0000 [2\u00020\u0001:\u0002\\]Bk\u0012\u0006\u0010%\u001a\u00020\u0016\u0012\u0006\u0010&\u001a\u00020\u0016\u0012\u0006\u0010'\u001a\u00020\u0016\u0012\u0006\u0010(\u001a\u00020\u0016\u0012\b\u0010)\u001a\u0004\u0018\u00010\u0016\u0012\u0006\u0010*\u001a\u00020\u001c\u0012\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u001c\u0012\u0006\u0010,\u001a\u00020\u0016\u0012\u000e\u0010-\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010!\u0012\f\u0010.\u001a\b\u0012\u0004\u0012\u00020#0!¢\u0006\u0004\bU\u0010VB\u0001\b\u0017\u0012\u0006\u0010W\u001a\u000202\u0012\b\u0010%\u001a\u0004\u0018\u00010\u0016\u0012\b\u0010&\u001a\u0004\u0018\u00010\u0016\u0012\b\u0010'\u001a\u0004\u0018\u00010\u0016\u0012\b\u0010(\u001a\u0004\u0018\u00010\u0016\u0012\b\u0010)\u001a\u0004\u0018\u00010\u0016\u0012\u0006\u0010*\u001a\u00020\u001c\u0012\b\u0010+\u001a\u0004\u0018\u00010\u001c\u0012\b\u0010,\u001a\u0004\u0018\u00010\u0016\u0012\u000e\u0010-\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010!\u0012\u000e\u0010.\u001a\n\u0012\u0004\u0012\u00020#\u0018\u00010!\u0012\b\u0010Y\u001a\u0004\u0018\u00010X¢\u0006\u0004\bU\u0010ZJ!\u0010\b\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005HÇ\u0001J\u000f\u0010\f\u001a\u00020\tH\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u0019\u0010\u0010\u001a\u00020\t2\b\u0010\r\u001a\u0004\u0018\u00010\u0000H\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0011H\u0000¢\u0006\u0004\b\u0013\u0010\u0014J\t\u0010\u0017\u001a\u00020\u0016HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0016HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0016HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0016HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0016HÆ\u0003J\t\u0010\u001d\u001a\u00020\u001cHÆ\u0003J\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u001cHÆ\u0003¢\u0006\u0004\b\u001e\u0010\u001fJ\t\u0010 \u001a\u00020\u0016HÆ\u0003J\u0011\u0010\"\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010!HÆ\u0003J\u000f\u0010$\u001a\b\u0012\u0004\u0012\u00020#0!HÆ\u0003J\u0001\u0010/\u001a\u00020\u00002\b\b\u0002\u0010%\u001a\u00020\u00162\b\b\u0002\u0010&\u001a\u00020\u00162\b\b\u0002\u0010'\u001a\u00020\u00162\b\b\u0002\u0010(\u001a\u00020\u00162\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u00162\b\b\u0002\u0010*\u001a\u00020\u001c2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u001c2\b\b\u0002\u0010,\u001a\u00020\u00162\u0010\b\u0002\u0010-\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010!2\u000e\b\u0002\u0010.\u001a\b\u0012\u0004\u0012\u00020#0!HÆ\u0001¢\u0006\u0004\b/\u00100J\t\u00101\u001a\u00020\u0016HÖ\u0001J\t\u00103\u001a\u000202HÖ\u0001J\u0013\u00105\u001a\u00020\t2\b\u00104\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\"\u0010%\u001a\u00020\u00168\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b%\u00106\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\"\u0010&\u001a\u00020\u00168\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b&\u00106\u001a\u0004\b;\u00108\"\u0004\b<\u0010:R\"\u0010'\u001a\u00020\u00168\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b'\u00106\u001a\u0004\b=\u00108\"\u0004\b>\u0010:R\"\u0010(\u001a\u00020\u00168\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b(\u00106\u001a\u0004\b?\u00108\"\u0004\b@\u0010:R$\u0010)\u001a\u0004\u0018\u00010\u00168\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b)\u00106\u001a\u0004\bA\u00108\"\u0004\bB\u0010:R\"\u0010*\u001a\u00020\u001c8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b*\u0010C\u001a\u0004\bD\u0010E\"\u0004\bF\u0010GR$\u0010+\u001a\u0004\u0018\u00010\u001c8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b+\u0010H\u001a\u0004\bI\u0010\u001f\"\u0004\bJ\u0010KR\"\u0010,\u001a\u00020\u00168\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b,\u00106\u001a\u0004\bL\u00108\"\u0004\bM\u0010:R*\u0010-\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010!8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b-\u0010N\u001a\u0004\bO\u0010P\"\u0004\bQ\u0010RR(\u0010.\u001a\b\u0012\u0004\u0012\u00020#0!8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b.\u0010N\u001a\u0004\bS\u0010P\"\u0004\bT\u0010R¨\u0006^"}, d2 = {"Lcom/appsamurai/storyly/data/managers/product/STRProductItem;", "", "self", "Lkotlinx/serialization/encoding/CompositeEncoder;", "output", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "serialDesc", "", "write$Self", "", "hasSpecialPrice$storyly_release", "()Z", "hasSpecialPrice", "item", "isContentSame$storyly_release", "(Lcom/appsamurai/storyly/data/managers/product/STRProductItem;)Z", "isContentSame", "Lkotlinx/serialization/json/JsonObjectBuilder;", "jsonBuilder", "serialize$storyly_release", "(Lkotlinx/serialization/json/JsonObjectBuilder;)V", "serialize", "", "component1", "component2", "component3", "component4", "component5", "", "component6", "component7", "()Ljava/lang/Float;", "component8", "", "component9", "Lcom/appsamurai/storyly/data/managers/product/STRProductVariant;", "component10", "productId", "productGroupId", "title", "url", "desc", "price", "salesPrice", "currency", "imageUrls", "variants", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;FLjava/lang/Float;Ljava/lang/String;Ljava/util/List;Ljava/util/List;)Lcom/appsamurai/storyly/data/managers/product/STRProductItem;", "toString", "", "hashCode", "other", "equals", "Ljava/lang/String;", "getProductId", "()Ljava/lang/String;", "setProductId", "(Ljava/lang/String;)V", "getProductGroupId", "setProductGroupId", "getTitle", "setTitle", "getUrl", "setUrl", "getDesc", "setDesc", "F", "getPrice", "()F", "setPrice", "(F)V", "Ljava/lang/Float;", "getSalesPrice", "setSalesPrice", "(Ljava/lang/Float;)V", "getCurrency", "setCurrency", "Ljava/util/List;", "getImageUrls", "()Ljava/util/List;", "setImageUrls", "(Ljava/util/List;)V", "getVariants", "setVariants", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;FLjava/lang/Float;Ljava/lang/String;Ljava/util/List;Ljava/util/List;)V", "seen1", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "serializationConstructorMarker", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;FLjava/lang/Float;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "Companion", "a", "b", "storyly_release"}, k = 1, mv = {1, 5, 1})
@Serializable
public final class STRProductItem {
    @NotNull
    public static final b Companion = new b();
    @NotNull
    private String currency;
    @Nullable
    private String desc;
    @Nullable
    private List<String> imageUrls;
    private float price;
    @NotNull
    private String productGroupId;
    @NotNull
    private String productId;
    @Nullable
    private Float salesPrice;
    @NotNull
    private String title;
    @NotNull
    private String url;
    @NotNull
    private List<STRProductVariant> variants;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final class a implements GeneratedSerializer<STRProductItem> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public static final a f4034a;

        /* renamed from: b  reason: collision with root package name */
        public static final /* synthetic */ SerialDescriptor f4035b;

        static {
            a aVar = new a();
            f4034a = aVar;
            PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.appsamurai.storyly.data.managers.product.STRProductItem", aVar, 10);
            pluginGeneratedSerialDescriptor.addElement("productId", false);
            pluginGeneratedSerialDescriptor.addElement("productGroupId", false);
            pluginGeneratedSerialDescriptor.addElement("title", false);
            pluginGeneratedSerialDescriptor.addElement("url", false);
            pluginGeneratedSerialDescriptor.addElement("desc", false);
            pluginGeneratedSerialDescriptor.addElement(FirebaseAnalytics.Param.PRICE, false);
            pluginGeneratedSerialDescriptor.addElement("salesPrice", true);
            pluginGeneratedSerialDescriptor.addElement(FirebaseAnalytics.Param.CURRENCY, false);
            pluginGeneratedSerialDescriptor.addElement("imageUrls", false);
            pluginGeneratedSerialDescriptor.addElement("variants", false);
            f4035b = pluginGeneratedSerialDescriptor;
        }

        @NotNull
        public KSerializer<?>[] childSerializers() {
            StringSerializer stringSerializer = StringSerializer.INSTANCE;
            KSerializer<?> nullable = BuiltinSerializersKt.getNullable(stringSerializer);
            FloatSerializer floatSerializer = FloatSerializer.INSTANCE;
            return new KSerializer[]{stringSerializer, stringSerializer, stringSerializer, stringSerializer, nullable, floatSerializer, BuiltinSerializersKt.getNullable(floatSerializer), stringSerializer, BuiltinSerializersKt.getNullable(new ArrayListSerializer(stringSerializer)), new ArrayListSerializer(STRProductVariant.a.f4036a)};
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0099, code lost:
            r7 = 3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x00e1, code lost:
            r3 = 9;
         */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object deserialize(kotlinx.serialization.encoding.Decoder r36) {
            /*
                r35 = this;
                r0 = r36
                java.lang.String r1 = "decoder"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
                kotlinx.serialization.descriptors.SerialDescriptor r1 = f4035b
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
                if (r2 == 0) goto L_0x006a
                java.lang.String r2 = r0.decodeStringElement(r1, r12)
                java.lang.String r11 = r0.decodeStringElement(r1, r11)
                java.lang.String r10 = r0.decodeStringElement(r1, r10)
                java.lang.String r7 = r0.decodeStringElement(r1, r7)
                kotlinx.serialization.internal.StringSerializer r12 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                java.lang.Object r9 = r0.decodeNullableSerializableElement(r1, r9, r12, r13)
                float r6 = r0.decodeFloatElement(r1, r6)
                kotlinx.serialization.internal.FloatSerializer r14 = kotlinx.serialization.internal.FloatSerializer.INSTANCE
                java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r5, r14, r13)
                java.lang.String r4 = r0.decodeStringElement(r1, r4)
                kotlinx.serialization.internal.ArrayListSerializer r14 = new kotlinx.serialization.internal.ArrayListSerializer
                r14.<init>(r12)
                java.lang.Object r8 = r0.decodeNullableSerializableElement(r1, r8, r14, r13)
                kotlinx.serialization.internal.ArrayListSerializer r12 = new kotlinx.serialization.internal.ArrayListSerializer
                com.appsamurai.storyly.data.managers.product.STRProductVariant$a r14 = com.appsamurai.storyly.data.managers.product.STRProductVariant.a.f4036a
                r12.<init>(r14)
                java.lang.Object r3 = r0.decodeSerializableElement(r1, r3, r12, r13)
                r12 = 1023(0x3ff, float:1.434E-42)
                r24 = r2
                r31 = r4
                r29 = r6
                r27 = r7
                r26 = r10
                r25 = r11
                r23 = r12
                goto L_0x0104
            L_0x006a:
                r2 = 0
                r21 = r11
                r11 = r13
                r14 = r11
                r15 = r14
                r16 = r15
                r17 = r16
                r18 = r17
                r19 = r18
                r20 = r19
                r13 = r12
                r12 = r20
            L_0x007d:
                if (r21 == 0) goto L_0x00f2
                int r10 = r0.decodeElementIndex(r1)
                switch(r10) {
                    case -1: goto L_0x00ed;
                    case 0: goto L_0x00e4;
                    case 1: goto L_0x00d9;
                    case 2: goto L_0x00d1;
                    case 3: goto L_0x00ca;
                    case 4: goto L_0x00c1;
                    case 5: goto L_0x00ba;
                    case 6: goto L_0x00b1;
                    case 7: goto L_0x00aa;
                    case 8: goto L_0x009c;
                    case 9: goto L_0x008c;
                    default: goto L_0x0086;
                }
            L_0x0086:
                kotlinx.serialization.UnknownFieldException r0 = new kotlinx.serialization.UnknownFieldException
                r0.<init>((int) r10)
                throw r0
            L_0x008c:
                kotlinx.serialization.internal.ArrayListSerializer r10 = new kotlinx.serialization.internal.ArrayListSerializer
                com.appsamurai.storyly.data.managers.product.STRProductVariant$a r7 = com.appsamurai.storyly.data.managers.product.STRProductVariant.a.f4036a
                r10.<init>(r7)
                java.lang.Object r14 = r0.decodeSerializableElement(r1, r3, r10, r14)
                r13 = r13 | 512(0x200, float:7.175E-43)
            L_0x0099:
                r7 = 3
            L_0x009a:
                r10 = 2
                goto L_0x007d
            L_0x009c:
                kotlinx.serialization.internal.ArrayListSerializer r7 = new kotlinx.serialization.internal.ArrayListSerializer
                kotlinx.serialization.internal.StringSerializer r10 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r7.<init>(r10)
                java.lang.Object r15 = r0.decodeNullableSerializableElement(r1, r8, r7, r15)
                r13 = r13 | 256(0x100, float:3.59E-43)
                goto L_0x0099
            L_0x00aa:
                java.lang.String r20 = r0.decodeStringElement(r1, r4)
                r13 = r13 | 128(0x80, float:1.794E-43)
                goto L_0x0099
            L_0x00b1:
                kotlinx.serialization.internal.FloatSerializer r7 = kotlinx.serialization.internal.FloatSerializer.INSTANCE
                java.lang.Object r12 = r0.decodeNullableSerializableElement(r1, r5, r7, r12)
                r13 = r13 | 64
                goto L_0x0099
            L_0x00ba:
                float r2 = r0.decodeFloatElement(r1, r6)
                r13 = r13 | 32
                goto L_0x0099
            L_0x00c1:
                kotlinx.serialization.internal.StringSerializer r7 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                java.lang.Object r11 = r0.decodeNullableSerializableElement(r1, r9, r7, r11)
                r13 = r13 | 16
                goto L_0x0099
            L_0x00ca:
                java.lang.String r19 = r0.decodeStringElement(r1, r7)
                r13 = r13 | 8
                goto L_0x009a
            L_0x00d1:
                r10 = 2
                java.lang.String r18 = r0.decodeStringElement(r1, r10)
                r13 = r13 | 4
                goto L_0x007d
            L_0x00d9:
                r3 = 1
                r10 = 2
                java.lang.String r17 = r0.decodeStringElement(r1, r3)
                r13 = r13 | 2
            L_0x00e1:
                r3 = 9
                goto L_0x007d
            L_0x00e4:
                r3 = 0
                r10 = 2
                java.lang.String r16 = r0.decodeStringElement(r1, r3)
                r13 = r13 | 1
                goto L_0x00e1
            L_0x00ed:
                r3 = 0
                r10 = 2
                r21 = r3
                goto L_0x00e1
            L_0x00f2:
                r29 = r2
                r9 = r11
                r5 = r12
                r23 = r13
                r3 = r14
                r8 = r15
                r24 = r16
                r25 = r17
                r26 = r18
                r27 = r19
                r31 = r20
            L_0x0104:
                r0.endStructure(r1)
                com.appsamurai.storyly.data.managers.product.STRProductItem r0 = new com.appsamurai.storyly.data.managers.product.STRProductItem
                r28 = r9
                java.lang.String r28 = (java.lang.String) r28
                r30 = r5
                java.lang.Float r30 = (java.lang.Float) r30
                r32 = r8
                java.util.List r32 = (java.util.List) r32
                r33 = r3
                java.util.List r33 = (java.util.List) r33
                r34 = 0
                r22 = r0
                r22.<init>((int) r23, (java.lang.String) r24, (java.lang.String) r25, (java.lang.String) r26, (java.lang.String) r27, (java.lang.String) r28, (float) r29, (java.lang.Float) r30, (java.lang.String) r31, (java.util.List) r32, (java.util.List) r33, (kotlinx.serialization.internal.SerializationConstructorMarker) r34)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.managers.product.STRProductItem.a.deserialize(kotlinx.serialization.encoding.Decoder):java.lang.Object");
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return f4035b;
        }

        public void serialize(Encoder encoder, Object obj) {
            STRProductItem sTRProductItem = (STRProductItem) obj;
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter(sTRProductItem, "value");
            SerialDescriptor serialDescriptor = f4035b;
            CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
            STRProductItem.write$Self(sTRProductItem, beginStructure, serialDescriptor);
            beginStructure.endStructure(serialDescriptor);
        }

        @NotNull
        public KSerializer<?>[] typeParametersSerializers() {
            return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
        }
    }

    public static final class b {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ STRProductItem(int i3, String str, String str2, String str3, String str4, String str5, float f2, Float f3, String str6, List list, List list2, SerializationConstructorMarker serializationConstructorMarker) {
        if (959 != (i3 & 959)) {
            PluginExceptionsKt.throwMissingFieldException(i3, 959, a.f4034a.getDescriptor());
        }
        this.productId = str;
        this.productGroupId = str2;
        this.title = str3;
        this.url = str4;
        this.desc = str5;
        this.price = f2;
        if ((i3 & 64) == 0) {
            this.salesPrice = null;
        } else {
            this.salesPrice = f3;
        }
        this.currency = str6;
        this.imageUrls = list;
        this.variants = list2;
    }

    public static /* synthetic */ STRProductItem copy$default(STRProductItem sTRProductItem, String str, String str2, String str3, String str4, String str5, float f2, Float f3, String str6, List list, List list2, int i3, Object obj) {
        STRProductItem sTRProductItem2 = sTRProductItem;
        int i4 = i3;
        return sTRProductItem.copy((i4 & 1) != 0 ? sTRProductItem2.productId : str, (i4 & 2) != 0 ? sTRProductItem2.productGroupId : str2, (i4 & 4) != 0 ? sTRProductItem2.title : str3, (i4 & 8) != 0 ? sTRProductItem2.url : str4, (i4 & 16) != 0 ? sTRProductItem2.desc : str5, (i4 & 32) != 0 ? sTRProductItem2.price : f2, (i4 & 64) != 0 ? sTRProductItem2.salesPrice : f3, (i4 & 128) != 0 ? sTRProductItem2.currency : str6, (i4 & 256) != 0 ? sTRProductItem2.imageUrls : list, (i4 & 512) != 0 ? sTRProductItem2.variants : list2);
    }

    @JvmStatic
    public static final void write$Self(@NotNull STRProductItem sTRProductItem, @NotNull CompositeEncoder compositeEncoder, @NotNull SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(sTRProductItem, "self");
        Intrinsics.checkNotNullParameter(compositeEncoder, "output");
        Intrinsics.checkNotNullParameter(serialDescriptor, "serialDesc");
        compositeEncoder.encodeStringElement(serialDescriptor, 0, sTRProductItem.productId);
        compositeEncoder.encodeStringElement(serialDescriptor, 1, sTRProductItem.productGroupId);
        compositeEncoder.encodeStringElement(serialDescriptor, 2, sTRProductItem.title);
        compositeEncoder.encodeStringElement(serialDescriptor, 3, sTRProductItem.url);
        StringSerializer stringSerializer = StringSerializer.INSTANCE;
        compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 4, stringSerializer, sTRProductItem.desc);
        compositeEncoder.encodeFloatElement(serialDescriptor, 5, sTRProductItem.price);
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 6) || sTRProductItem.salesPrice != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 6, FloatSerializer.INSTANCE, sTRProductItem.salesPrice);
        }
        compositeEncoder.encodeStringElement(serialDescriptor, 7, sTRProductItem.currency);
        compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 8, new ArrayListSerializer(stringSerializer), sTRProductItem.imageUrls);
        compositeEncoder.encodeSerializableElement(serialDescriptor, 9, new ArrayListSerializer(STRProductVariant.a.f4036a), sTRProductItem.variants);
    }

    @NotNull
    public final String component1() {
        return this.productId;
    }

    @NotNull
    public final List<STRProductVariant> component10() {
        return this.variants;
    }

    @NotNull
    public final String component2() {
        return this.productGroupId;
    }

    @NotNull
    public final String component3() {
        return this.title;
    }

    @NotNull
    public final String component4() {
        return this.url;
    }

    @Nullable
    public final String component5() {
        return this.desc;
    }

    public final float component6() {
        return this.price;
    }

    @Nullable
    public final Float component7() {
        return this.salesPrice;
    }

    @NotNull
    public final String component8() {
        return this.currency;
    }

    @Nullable
    public final List<String> component9() {
        return this.imageUrls;
    }

    @NotNull
    public final STRProductItem copy(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @Nullable String str5, float f2, @Nullable Float f3, @NotNull String str6, @Nullable List<String> list, @NotNull List<STRProductVariant> list2) {
        Intrinsics.checkNotNullParameter(str, "productId");
        Intrinsics.checkNotNullParameter(str2, "productGroupId");
        Intrinsics.checkNotNullParameter(str3, "title");
        String str7 = str4;
        Intrinsics.checkNotNullParameter(str7, "url");
        String str8 = str6;
        Intrinsics.checkNotNullParameter(str8, FirebaseAnalytics.Param.CURRENCY);
        List<STRProductVariant> list3 = list2;
        Intrinsics.checkNotNullParameter(list3, "variants");
        return new STRProductItem(str, str2, str3, str7, str5, f2, f3, str8, list, list3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof STRProductItem)) {
            return false;
        }
        STRProductItem sTRProductItem = (STRProductItem) obj;
        return Intrinsics.areEqual((Object) this.productId, (Object) sTRProductItem.productId) && Intrinsics.areEqual((Object) this.productGroupId, (Object) sTRProductItem.productGroupId) && Intrinsics.areEqual((Object) this.title, (Object) sTRProductItem.title) && Intrinsics.areEqual((Object) this.url, (Object) sTRProductItem.url) && Intrinsics.areEqual((Object) this.desc, (Object) sTRProductItem.desc) && Intrinsics.areEqual((Object) Float.valueOf(this.price), (Object) Float.valueOf(sTRProductItem.price)) && Intrinsics.areEqual((Object) this.salesPrice, (Object) sTRProductItem.salesPrice) && Intrinsics.areEqual((Object) this.currency, (Object) sTRProductItem.currency) && Intrinsics.areEqual((Object) this.imageUrls, (Object) sTRProductItem.imageUrls) && Intrinsics.areEqual((Object) this.variants, (Object) sTRProductItem.variants);
    }

    @NotNull
    public final String getCurrency() {
        return this.currency;
    }

    @Nullable
    public final String getDesc() {
        return this.desc;
    }

    @Nullable
    public final List<String> getImageUrls() {
        return this.imageUrls;
    }

    public final float getPrice() {
        return this.price;
    }

    @NotNull
    public final String getProductGroupId() {
        return this.productGroupId;
    }

    @NotNull
    public final String getProductId() {
        return this.productId;
    }

    @Nullable
    public final Float getSalesPrice() {
        return this.salesPrice;
    }

    @NotNull
    public final String getTitle() {
        return this.title;
    }

    @NotNull
    public final String getUrl() {
        return this.url;
    }

    @NotNull
    public final List<STRProductVariant> getVariants() {
        return this.variants;
    }

    public final boolean hasSpecialPrice$storyly_release() {
        return !Intrinsics.areEqual(this.salesPrice, this.price) && this.salesPrice != null;
    }

    public int hashCode() {
        int i3 = androidx.compose.animation.core.a.i(this.url, androidx.compose.animation.core.a.i(this.title, androidx.compose.animation.core.a.i(this.productGroupId, this.productId.hashCode() * 31, 31), 31), 31);
        String str = this.desc;
        int i4 = 0;
        int c3 = android.support.v4.media.session.a.c(this.price, (i3 + (str == null ? 0 : str.hashCode())) * 31, 31);
        Float f2 = this.salesPrice;
        int i5 = androidx.compose.animation.core.a.i(this.currency, (c3 + (f2 == null ? 0 : f2.hashCode())) * 31, 31);
        List<String> list = this.imageUrls;
        if (list != null) {
            i4 = list.hashCode();
        }
        return this.variants.hashCode() + ((i5 + i4) * 31);
    }

    public final boolean isContentSame$storyly_release(@Nullable STRProductItem sTRProductItem) {
        String str = null;
        if (Intrinsics.areEqual((Object) this.imageUrls, (Object) sTRProductItem == null ? null : sTRProductItem.imageUrls)) {
            String str2 = this.title;
            if (sTRProductItem != null) {
                str = sTRProductItem.title;
            }
            return Intrinsics.areEqual((Object) str2, (Object) str) && this.price == sTRProductItem.price && Intrinsics.areEqual(this.salesPrice, sTRProductItem.salesPrice) && Intrinsics.areEqual((Object) this.currency, (Object) sTRProductItem.currency) && Intrinsics.areEqual((Object) this.variants, (Object) sTRProductItem.variants);
        }
    }

    public final void serialize$storyly_release(@NotNull JsonObjectBuilder jsonObjectBuilder) {
        List list;
        Intrinsics.checkNotNullParameter(jsonObjectBuilder, "jsonBuilder");
        JsonElementBuildersKt.put(jsonObjectBuilder, "productId", this.productId);
        JsonElementBuildersKt.put(jsonObjectBuilder, "productGroupId", this.productGroupId);
        JsonElementBuildersKt.put(jsonObjectBuilder, FirebaseAnalytics.Param.PRICE, (Number) Float.valueOf(this.price));
        JsonElementBuildersKt.put(jsonObjectBuilder, "desc", this.desc);
        JsonElementBuildersKt.put(jsonObjectBuilder, FirebaseAnalytics.Param.CURRENCY, this.currency);
        JsonElementBuildersKt.put(jsonObjectBuilder, "salesPrice", (Number) this.salesPrice);
        JsonElementBuildersKt.put(jsonObjectBuilder, "title", this.title);
        JsonElementBuildersKt.put(jsonObjectBuilder, "url", this.url);
        List<String> list2 = this.imageUrls;
        if (list2 == null) {
            list = null;
        } else {
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
            for (String JsonPrimitive : list2) {
                arrayList.add(JsonElementKt.JsonPrimitive(JsonPrimitive));
            }
            list = arrayList;
        }
        if (list == null) {
            list = CollectionsKt.emptyList();
        }
        jsonObjectBuilder.put("imageUrls", new JsonArray(list));
        List<STRProductVariant> list3 = this.variants;
        ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list3, 10));
        for (STRProductVariant sTRProductVariant : list3) {
            JsonObjectBuilder jsonObjectBuilder2 = new JsonObjectBuilder();
            JsonElementBuildersKt.put(jsonObjectBuilder2, "name", sTRProductVariant.getName());
            JsonElementBuildersKt.put(jsonObjectBuilder2, "value", sTRProductVariant.getValue());
            arrayList2.add(jsonObjectBuilder2.build());
        }
        jsonObjectBuilder.put("variants", new JsonArray(arrayList2));
    }

    public final void setCurrency(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.currency = str;
    }

    public final void setDesc(@Nullable String str) {
        this.desc = str;
    }

    public final void setImageUrls(@Nullable List<String> list) {
        this.imageUrls = list;
    }

    public final void setPrice(float f2) {
        this.price = f2;
    }

    public final void setProductGroupId(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.productGroupId = str;
    }

    public final void setProductId(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.productId = str;
    }

    public final void setSalesPrice(@Nullable Float f2) {
        this.salesPrice = f2;
    }

    public final void setTitle(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }

    public final void setUrl(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.url = str;
    }

    public final void setVariants(@NotNull List<STRProductVariant> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.variants = list;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder("STRProductItem(productId=");
        sb.append(this.productId);
        sb.append(", productGroupId=");
        sb.append(this.productGroupId);
        sb.append(", title=");
        sb.append(this.title);
        sb.append(", url=");
        sb.append(this.url);
        sb.append(", desc=");
        sb.append(this.desc);
        sb.append(", price=");
        sb.append(this.price);
        sb.append(", salesPrice=");
        sb.append(this.salesPrice);
        sb.append(", currency=");
        sb.append(this.currency);
        sb.append(", imageUrls=");
        sb.append(this.imageUrls);
        sb.append(", variants=");
        return androidx.compose.ui.autofill.a.k(sb, this.variants, ')');
    }

    public STRProductItem(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @Nullable String str5, float f2, @Nullable Float f3, @NotNull String str6, @Nullable List<String> list, @NotNull List<STRProductVariant> list2) {
        Intrinsics.checkNotNullParameter(str, "productId");
        Intrinsics.checkNotNullParameter(str2, "productGroupId");
        Intrinsics.checkNotNullParameter(str3, "title");
        Intrinsics.checkNotNullParameter(str4, "url");
        Intrinsics.checkNotNullParameter(str6, FirebaseAnalytics.Param.CURRENCY);
        Intrinsics.checkNotNullParameter(list2, "variants");
        this.productId = str;
        this.productGroupId = str2;
        this.title = str3;
        this.url = str4;
        this.desc = str5;
        this.price = f2;
        this.salesPrice = f3;
        this.currency = str6;
        this.imageUrls = list;
        this.variants = list2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ STRProductItem(String str, String str2, String str3, String str4, String str5, float f2, Float f3, String str6, List list, List list2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, f2, (i3 & 64) != 0 ? null : f3, str6, list, list2);
    }
}
