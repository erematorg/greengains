package com.appsamurai.storyly.data.managers.product;

import androidx.annotation.Keep;
import java.util.regex.Pattern;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.EnumSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\b\b\u0018\u0000 32\u00020\u0001:\u000245B\u0017\u0012\u0006\u0010\u0012\u001a\u00020\u000f\u0012\u0006\u0010\u0013\u001a\u00020\u000f¢\u0006\u0004\b-\u0010.BA\b\u0017\u0012\u0006\u0010/\u001a\u00020\u0015\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u000f\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u000f\u0012\u0006\u0010 \u001a\u00020\u0002\u0012\b\u0010'\u001a\u0004\u0018\u00010&\u0012\b\u00101\u001a\u0004\u0018\u000100¢\u0006\u0004\b-\u00102J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0004\u001a\u00020\u0002H\u0002J!\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bHÇ\u0001J\u000f\u0010\u000e\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0010\u001a\u00020\u000fHÆ\u0003J\t\u0010\u0011\u001a\u00020\u000fHÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0012\u001a\u00020\u000f2\b\b\u0002\u0010\u0013\u001a\u00020\u000fHÆ\u0001J\t\u0010\u0014\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0016\u001a\u00020\u0015HÖ\u0001J\u0013\u0010\u0018\u001a\u00020\u00022\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\"\u0010\u0012\u001a\u00020\u000f8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\"\u0010\u0013\u001a\u00020\u000f8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0013\u0010\u0019\u001a\u0004\b\u001e\u0010\u001b\"\u0004\b\u001f\u0010\u001dR\"\u0010 \u001a\u00020\u00028\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\"\u0010'\u001a\u00020&8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,¨\u00066"}, d2 = {"Lcom/appsamurai/storyly/data/managers/product/STRProductVariant;", "", "", "isHex", "isUrl", "self", "Lkotlinx/serialization/encoding/CompositeEncoder;", "output", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "serialDesc", "", "write$Self", "copy$storyly_release", "()Lcom/appsamurai/storyly/data/managers/product/STRProductVariant;", "copy", "", "component1", "component2", "name", "value", "toString", "", "hashCode", "other", "equals", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getValue", "setValue", "isEnabled", "Z", "isEnabled$storyly_release", "()Z", "setEnabled$storyly_release", "(Z)V", "Lcom/appsamurai/storyly/data/managers/product/c;", "sourceType", "Lcom/appsamurai/storyly/data/managers/product/c;", "getSourceType$storyly_release", "()Lcom/appsamurai/storyly/data/managers/product/c;", "setSourceType$storyly_release", "(Lcom/appsamurai/storyly/data/managers/product/c;)V", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "seen1", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "serializationConstructorMarker", "(ILjava/lang/String;Ljava/lang/String;ZLcom/appsamurai/storyly/data/managers/product/c;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "Companion", "a", "b", "storyly_release"}, k = 1, mv = {1, 5, 1})
@Serializable
public final class STRProductVariant {
    @NotNull
    public static final b Companion = new b();
    private boolean isEnabled;
    @NotNull
    private String name;
    @NotNull
    private c sourceType;
    @NotNull
    private String value;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final class a implements GeneratedSerializer<STRProductVariant> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public static final a f4036a;

        /* renamed from: b  reason: collision with root package name */
        public static final /* synthetic */ SerialDescriptor f4037b;

        static {
            a aVar = new a();
            f4036a = aVar;
            PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.appsamurai.storyly.data.managers.product.STRProductVariant", aVar, 4);
            pluginGeneratedSerialDescriptor.addElement("name", false);
            pluginGeneratedSerialDescriptor.addElement("value", false);
            pluginGeneratedSerialDescriptor.addElement("isEnabled", true);
            pluginGeneratedSerialDescriptor.addElement("sourceType", false);
            f4037b = pluginGeneratedSerialDescriptor;
        }

        @NotNull
        public KSerializer<?>[] childSerializers() {
            EnumSerializer enumSerializer = new EnumSerializer("com.appsamurai.storyly.data.managers.product.VariantSourceType", c.values());
            StringSerializer stringSerializer = StringSerializer.INSTANCE;
            return new KSerializer[]{stringSerializer, stringSerializer, BooleanSerializer.INSTANCE, enumSerializer};
        }

        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object deserialize(kotlinx.serialization.encoding.Decoder r21) {
            /*
                r20 = this;
                r0 = r21
                java.lang.String r1 = "decoder"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
                kotlinx.serialization.descriptors.SerialDescriptor r1 = f4037b
                kotlinx.serialization.encoding.CompositeDecoder r0 = r0.beginStructure(r1)
                boolean r2 = r0.decodeSequentially()
                java.lang.String r3 = "com.appsamurai.storyly.data.managers.product.VariantSourceType"
                r4 = 3
                r5 = 2
                r6 = 1
                r7 = 0
                r8 = 0
                if (r2 == 0) goto L_0x003c
                java.lang.String r2 = r0.decodeStringElement(r1, r7)
                java.lang.String r6 = r0.decodeStringElement(r1, r6)
                boolean r5 = r0.decodeBooleanElement(r1, r5)
                kotlinx.serialization.internal.EnumSerializer r7 = new kotlinx.serialization.internal.EnumSerializer
                com.appsamurai.storyly.data.managers.product.c[] r9 = com.appsamurai.storyly.data.managers.product.c.values()
                r7.<init>(r3, r9)
                java.lang.Object r3 = r0.decodeSerializableElement(r1, r4, r7, r8)
                r4 = 15
                r15 = r2
                r14 = r4
                r17 = r5
                r16 = r6
                goto L_0x0087
            L_0x003c:
                r12 = r6
                r2 = r7
                r9 = r8
                r10 = r9
                r11 = r10
                r8 = r2
            L_0x0042:
                if (r12 == 0) goto L_0x0080
                int r13 = r0.decodeElementIndex(r1)
                r14 = -1
                if (r13 == r14) goto L_0x007e
                if (r13 == 0) goto L_0x0077
                if (r13 == r6) goto L_0x0070
                if (r13 == r5) goto L_0x0069
                if (r13 != r4) goto L_0x0063
                kotlinx.serialization.internal.EnumSerializer r13 = new kotlinx.serialization.internal.EnumSerializer
                com.appsamurai.storyly.data.managers.product.c[] r14 = com.appsamurai.storyly.data.managers.product.c.values()
                r13.<init>(r3, r14)
                java.lang.Object r9 = r0.decodeSerializableElement(r1, r4, r13, r9)
                r2 = r2 | 8
                goto L_0x0042
            L_0x0063:
                kotlinx.serialization.UnknownFieldException r0 = new kotlinx.serialization.UnknownFieldException
                r0.<init>((int) r13)
                throw r0
            L_0x0069:
                boolean r8 = r0.decodeBooleanElement(r1, r5)
                r2 = r2 | 4
                goto L_0x0042
            L_0x0070:
                java.lang.String r11 = r0.decodeStringElement(r1, r6)
                r2 = r2 | 2
                goto L_0x0042
            L_0x0077:
                java.lang.String r10 = r0.decodeStringElement(r1, r7)
                r2 = r2 | 1
                goto L_0x0042
            L_0x007e:
                r12 = r7
                goto L_0x0042
            L_0x0080:
                r14 = r2
                r17 = r8
                r3 = r9
                r15 = r10
                r16 = r11
            L_0x0087:
                r0.endStructure(r1)
                com.appsamurai.storyly.data.managers.product.STRProductVariant r0 = new com.appsamurai.storyly.data.managers.product.STRProductVariant
                r18 = r3
                com.appsamurai.storyly.data.managers.product.c r18 = (com.appsamurai.storyly.data.managers.product.c) r18
                r19 = 0
                r13 = r0
                r13.<init>(r14, r15, r16, r17, r18, r19)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.managers.product.STRProductVariant.a.deserialize(kotlinx.serialization.encoding.Decoder):java.lang.Object");
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return f4037b;
        }

        public void serialize(Encoder encoder, Object obj) {
            STRProductVariant sTRProductVariant = (STRProductVariant) obj;
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter(sTRProductVariant, "value");
            SerialDescriptor serialDescriptor = f4037b;
            CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
            STRProductVariant.write$Self(sTRProductVariant, beginStructure, serialDescriptor);
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
    public /* synthetic */ STRProductVariant(int i3, String str, String str2, boolean z2, c cVar, SerializationConstructorMarker serializationConstructorMarker) {
        c cVar2;
        if (11 != (i3 & 11)) {
            PluginExceptionsKt.throwMissingFieldException(i3, 11, a.f4036a.getDescriptor());
        }
        this.name = str;
        this.value = str2;
        if ((i3 & 4) == 0) {
            this.isEnabled = true;
        } else {
            this.isEnabled = z2;
        }
        this.sourceType = cVar;
        if (isHex()) {
            cVar2 = c.Color;
        } else if (isUrl()) {
            cVar2 = c.ImageUrl;
        } else {
            cVar2 = c.Raw;
        }
        this.sourceType = cVar2;
    }

    public static /* synthetic */ STRProductVariant copy$default(STRProductVariant sTRProductVariant, String str, String str2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = sTRProductVariant.name;
        }
        if ((i3 & 2) != 0) {
            str2 = sTRProductVariant.value;
        }
        return sTRProductVariant.copy(str, str2);
    }

    private final boolean isHex() {
        return Pattern.compile("^#(?:[0-9a-fA-F]{3}){1,2}$").matcher(this.value).matches();
    }

    private final boolean isUrl() {
        return Pattern.compile("^(https?|ftp)://[^\\s/$.?#].[^\\s]*$").matcher(this.value).matches();
    }

    @JvmStatic
    public static final void write$Self(@NotNull STRProductVariant sTRProductVariant, @NotNull CompositeEncoder compositeEncoder, @NotNull SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(sTRProductVariant, "self");
        Intrinsics.checkNotNullParameter(compositeEncoder, "output");
        Intrinsics.checkNotNullParameter(serialDescriptor, "serialDesc");
        compositeEncoder.encodeStringElement(serialDescriptor, 0, sTRProductVariant.name);
        compositeEncoder.encodeStringElement(serialDescriptor, 1, sTRProductVariant.value);
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 2) || !sTRProductVariant.isEnabled) {
            compositeEncoder.encodeBooleanElement(serialDescriptor, 2, sTRProductVariant.isEnabled);
        }
        compositeEncoder.encodeSerializableElement(serialDescriptor, 3, new EnumSerializer("com.appsamurai.storyly.data.managers.product.VariantSourceType", c.values()), sTRProductVariant.sourceType);
    }

    @NotNull
    public final String component1() {
        return this.name;
    }

    @NotNull
    public final String component2() {
        return this.value;
    }

    @NotNull
    public final STRProductVariant copy(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(str2, "value");
        return new STRProductVariant(str, str2);
    }

    @NotNull
    public final STRProductVariant copy$storyly_release() {
        STRProductVariant sTRProductVariant = new STRProductVariant(this.name, this.value);
        sTRProductVariant.setEnabled$storyly_release(isEnabled$storyly_release());
        sTRProductVariant.setSourceType$storyly_release(getSourceType$storyly_release());
        return sTRProductVariant;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof STRProductVariant)) {
            return false;
        }
        STRProductVariant sTRProductVariant = (STRProductVariant) obj;
        return Intrinsics.areEqual((Object) this.name, (Object) sTRProductVariant.name) && Intrinsics.areEqual((Object) this.value, (Object) sTRProductVariant.value);
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final c getSourceType$storyly_release() {
        return this.sourceType;
    }

    @NotNull
    public final String getValue() {
        return this.value;
    }

    public int hashCode() {
        return this.value.hashCode() + (this.name.hashCode() * 31);
    }

    public final boolean isEnabled$storyly_release() {
        return this.isEnabled;
    }

    public final void setEnabled$storyly_release(boolean z2) {
        this.isEnabled = z2;
    }

    public final void setName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    public final void setSourceType$storyly_release(@NotNull c cVar) {
        Intrinsics.checkNotNullParameter(cVar, "<set-?>");
        this.sourceType = cVar;
    }

    public final void setValue(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.value = str;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder("STRProductVariant(name=");
        sb.append(this.name);
        sb.append(", value=");
        return androidx.compose.animation.core.a.o(')', this.value, sb);
    }

    public STRProductVariant(@NotNull String str, @NotNull String str2) {
        c cVar;
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(str2, "value");
        this.name = str;
        this.value = str2;
        this.isEnabled = true;
        if (isHex()) {
            cVar = c.Color;
        } else if (isUrl()) {
            cVar = c.ImageUrl;
        } else {
            cVar = c.Raw;
        }
        this.sourceType = cVar;
    }
}
