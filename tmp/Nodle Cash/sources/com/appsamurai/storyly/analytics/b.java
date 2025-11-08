package com.appsamurai.storyly.analytics;

import com.appsamurai.storyly.analytics.c;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Required;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable
public final class b {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final Integer f3502a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    public Integer f3503b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    public final List<c> f3504c;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final class a implements GeneratedSerializer<b> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public static final a f3505a;

        /* renamed from: b  reason: collision with root package name */
        public static final /* synthetic */ SerialDescriptor f3506b;

        static {
            a aVar = new a();
            f3505a = aVar;
            PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.appsamurai.storyly.analytics.MomentsAnalytic", aVar, 3);
            pluginGeneratedSerialDescriptor.addElement("view", false);
            pluginGeneratedSerialDescriptor.addElement("like", false);
            pluginGeneratedSerialDescriptor.addElement("liked_users", false);
            f3506b = pluginGeneratedSerialDescriptor;
        }

        @NotNull
        public KSerializer<?>[] childSerializers() {
            IntSerializer intSerializer = IntSerializer.INSTANCE;
            return new KSerializer[]{BuiltinSerializersKt.getNullable(intSerializer), BuiltinSerializersKt.getNullable(intSerializer), BuiltinSerializersKt.getNullable(new ArrayListSerializer(c.a.f3509a))};
        }

        public Object deserialize(Decoder decoder) {
            Object obj;
            int i3;
            Object obj2;
            Object obj3;
            Intrinsics.checkNotNullParameter(decoder, "decoder");
            SerialDescriptor serialDescriptor = f3506b;
            CompositeDecoder beginStructure = decoder.beginStructure(serialDescriptor);
            Object obj4 = null;
            if (beginStructure.decodeSequentially()) {
                IntSerializer intSerializer = IntSerializer.INSTANCE;
                obj = beginStructure.decodeNullableSerializableElement(serialDescriptor, 0, intSerializer, null);
                obj3 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 1, intSerializer, null);
                obj2 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 2, new ArrayListSerializer(c.a.f3509a), null);
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
                        obj6 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 0, IntSerializer.INSTANCE, obj6);
                        i4 |= 1;
                    } else if (decodeElementIndex == 1) {
                        obj5 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 1, IntSerializer.INSTANCE, obj5);
                        i4 |= 2;
                    } else if (decodeElementIndex == 2) {
                        obj4 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 2, new ArrayListSerializer(c.a.f3509a), obj4);
                        i4 |= 4;
                    } else {
                        throw new UnknownFieldException(decodeElementIndex);
                    }
                }
                i3 = i4;
                obj2 = obj4;
                obj3 = obj5;
                obj = obj6;
            }
            beginStructure.endStructure(serialDescriptor);
            return new b(i3, (Integer) obj, (Integer) obj3, (List) obj2);
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return f3506b;
        }

        public void serialize(Encoder encoder, Object obj) {
            b bVar = (b) obj;
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter(bVar, "value");
            SerialDescriptor serialDescriptor = f3506b;
            CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
            Intrinsics.checkNotNullParameter(bVar, "self");
            Intrinsics.checkNotNullParameter(beginStructure, "output");
            Intrinsics.checkNotNullParameter(serialDescriptor, "serialDesc");
            IntSerializer intSerializer = IntSerializer.INSTANCE;
            beginStructure.encodeNullableSerializableElement(serialDescriptor, 0, intSerializer, bVar.f3502a);
            beginStructure.encodeNullableSerializableElement(serialDescriptor, 1, intSerializer, bVar.f3503b);
            beginStructure.encodeNullableSerializableElement(serialDescriptor, 2, new ArrayListSerializer(c.a.f3509a), bVar.f3504c);
            beginStructure.endStructure(serialDescriptor);
        }

        @NotNull
        public KSerializer<?>[] typeParametersSerializers() {
            return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ b(int i3, @Required @SerialName("view") Integer num, @Required @SerialName("like") Integer num2, @Required @SerialName("liked_users") List list) {
        if (7 != (i3 & 7)) {
            PluginExceptionsKt.throwMissingFieldException(i3, 7, a.f3505a.getDescriptor());
        }
        this.f3502a = num;
        this.f3503b = num2;
        this.f3504c = list;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return Intrinsics.areEqual((Object) this.f3502a, (Object) bVar.f3502a) && Intrinsics.areEqual((Object) this.f3503b, (Object) bVar.f3503b) && Intrinsics.areEqual((Object) this.f3504c, (Object) bVar.f3504c);
    }

    public int hashCode() {
        Integer num = this.f3502a;
        int i3 = 0;
        int hashCode = (num == null ? 0 : num.hashCode()) * 31;
        Integer num2 = this.f3503b;
        int hashCode2 = (hashCode + (num2 == null ? 0 : num2.hashCode())) * 31;
        List<c> list = this.f3504c;
        if (list != null) {
            i3 = list.hashCode();
        }
        return hashCode2 + i3;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder("MomentsAnalytic(view=");
        sb.append(this.f3502a);
        sb.append(", like=");
        sb.append(this.f3503b);
        sb.append(", users=");
        return androidx.compose.ui.autofill.a.k(sb, this.f3504c, ')');
    }

    public b(@Nullable Integer num, @Nullable Integer num2, @Nullable List<c> list) {
        this.f3502a = num;
        this.f3503b = num2;
        this.f3504c = list;
    }
}
