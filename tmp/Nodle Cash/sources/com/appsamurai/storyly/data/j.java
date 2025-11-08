package com.appsamurai.storyly.data;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable
public final class j {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final String f3788a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    public final String f3789b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    public final String f3790c;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final class a implements GeneratedSerializer<j> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public static final a f3791a;

        /* renamed from: b  reason: collision with root package name */
        public static final /* synthetic */ SerialDescriptor f3792b;

        static {
            a aVar = new a();
            f3791a = aVar;
            PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.appsamurai.storyly.data.ProductInformation", aVar, 3);
            pluginGeneratedSerialDescriptor.addElement(TtmlNode.ATTR_ID, true);
            pluginGeneratedSerialDescriptor.addElement("product_id", true);
            pluginGeneratedSerialDescriptor.addElement("product_group_id", true);
            f3792b = pluginGeneratedSerialDescriptor;
        }

        @NotNull
        public KSerializer<?>[] childSerializers() {
            StringSerializer stringSerializer = StringSerializer.INSTANCE;
            return new KSerializer[]{BuiltinSerializersKt.getNullable(stringSerializer), BuiltinSerializersKt.getNullable(stringSerializer), BuiltinSerializersKt.getNullable(stringSerializer)};
        }

        public Object deserialize(Decoder decoder) {
            Object obj;
            Object obj2;
            int i3;
            Object obj3;
            Intrinsics.checkNotNullParameter(decoder, "decoder");
            SerialDescriptor serialDescriptor = f3792b;
            CompositeDecoder beginStructure = decoder.beginStructure(serialDescriptor);
            Object obj4 = null;
            if (beginStructure.decodeSequentially()) {
                StringSerializer stringSerializer = StringSerializer.INSTANCE;
                obj = beginStructure.decodeNullableSerializableElement(serialDescriptor, 0, stringSerializer, null);
                obj2 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 1, stringSerializer, null);
                obj3 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 2, stringSerializer, null);
                i3 = 7;
            } else {
                boolean z2 = true;
                int i4 = 0;
                Object obj5 = null;
                Object obj6 = null;
                while (z2) {
                    int decodeElementIndex = beginStructure.decodeElementIndex(serialDescriptor);
                    if (decodeElementIndex == -1) {
                        z2 = false;
                    } else if (decodeElementIndex == 0) {
                        obj6 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 0, StringSerializer.INSTANCE, obj6);
                        i4 |= 1;
                    } else if (decodeElementIndex == 1) {
                        obj5 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 1, StringSerializer.INSTANCE, obj5);
                        i4 |= 2;
                    } else if (decodeElementIndex == 2) {
                        obj4 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 2, StringSerializer.INSTANCE, obj4);
                        i4 |= 4;
                    } else {
                        throw new UnknownFieldException(decodeElementIndex);
                    }
                }
                i3 = i4;
                obj3 = obj4;
                obj2 = obj5;
                obj = obj6;
            }
            beginStructure.endStructure(serialDescriptor);
            return new j(i3, (String) obj, (String) obj2, (String) obj3);
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return f3792b;
        }

        public void serialize(Encoder encoder, Object obj) {
            j jVar = (j) obj;
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter(jVar, "value");
            SerialDescriptor serialDescriptor = f3792b;
            CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
            Intrinsics.checkNotNullParameter(jVar, "self");
            Intrinsics.checkNotNullParameter(beginStructure, "output");
            Intrinsics.checkNotNullParameter(serialDescriptor, "serialDesc");
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 0) || jVar.f3788a != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 0, StringSerializer.INSTANCE, jVar.f3788a);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 1) || jVar.f3789b != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 1, StringSerializer.INSTANCE, jVar.f3789b);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 2) || jVar.f3790c != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 2, StringSerializer.INSTANCE, jVar.f3790c);
            }
            beginStructure.endStructure(serialDescriptor);
        }

        @NotNull
        public KSerializer<?>[] typeParametersSerializers() {
            return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
        }
    }

    public j() {
        this((String) null, (String) null, (String) null, 7);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ j(int i3, @SerialName("id") String str, @SerialName("product_id") String str2, @SerialName("product_group_id") String str3) {
        if ((i3 & 1) == 0) {
            this.f3788a = null;
        } else {
            this.f3788a = str;
        }
        if ((i3 & 2) == 0) {
            this.f3789b = null;
        } else {
            this.f3789b = str2;
        }
        if ((i3 & 4) == 0) {
            this.f3790c = null;
        } else {
            this.f3790c = str3;
        }
    }

    public j(@Nullable String str, @Nullable String str2, @Nullable String str3) {
        this.f3788a = str;
        this.f3789b = str2;
        this.f3790c = str3;
    }

    public /* synthetic */ j(String str, String str2, String str3, int i3) {
        this((String) null, (String) null, (String) null);
    }
}
