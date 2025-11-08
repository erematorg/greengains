package com.appsamurai.storyly.data;

import com.appsamurai.storyly.data.c;
import com.appsamurai.storyly.data.u;
import java.util.List;
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
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable
public final class w {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final List<c> f4247a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    public final c f4248b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    public final u f4249c;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final class a implements GeneratedSerializer<w> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public static final a f4250a;

        /* renamed from: b  reason: collision with root package name */
        public static final /* synthetic */ SerialDescriptor f4251b;

        static {
            a aVar = new a();
            f4250a = aVar;
            PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.appsamurai.storyly.data.StorylyGroupStyle", aVar, 3);
            pluginGeneratedSerialDescriptor.addElement("b_u_c", true);
            pluginGeneratedSerialDescriptor.addElement("t_u_c", true);
            pluginGeneratedSerialDescriptor.addElement("badge", true);
            f4251b = pluginGeneratedSerialDescriptor;
        }

        @NotNull
        public KSerializer<?>[] childSerializers() {
            c.a aVar = c.f3622b;
            return new KSerializer[]{BuiltinSerializersKt.getNullable(new ArrayListSerializer(aVar)), BuiltinSerializersKt.getNullable(aVar), BuiltinSerializersKt.getNullable(u.a.f4217a)};
        }

        public Object deserialize(Decoder decoder) {
            int i3;
            Object obj;
            Object obj2;
            Object obj3;
            Intrinsics.checkNotNullParameter(decoder, "decoder");
            SerialDescriptor serialDescriptor = f4251b;
            CompositeDecoder beginStructure = decoder.beginStructure(serialDescriptor);
            Object obj4 = null;
            if (beginStructure.decodeSequentially()) {
                c.a aVar = c.f3622b;
                obj3 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 0, new ArrayListSerializer(aVar), null);
                obj = beginStructure.decodeNullableSerializableElement(serialDescriptor, 1, aVar, null);
                obj2 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 2, u.a.f4217a, null);
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
                        obj6 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 0, new ArrayListSerializer(c.f3622b), obj6);
                        i4 |= 1;
                    } else if (decodeElementIndex == 1) {
                        obj5 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 1, c.f3622b, obj5);
                        i4 |= 2;
                    } else if (decodeElementIndex == 2) {
                        obj4 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 2, u.a.f4217a, obj4);
                        i4 |= 4;
                    } else {
                        throw new UnknownFieldException(decodeElementIndex);
                    }
                }
                i3 = i4;
                obj2 = obj4;
                obj = obj5;
                obj3 = obj6;
            }
            beginStructure.endStructure(serialDescriptor);
            return new w(i3, (List) obj3, (c) obj, (u) obj2);
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return f4251b;
        }

        public void serialize(Encoder encoder, Object obj) {
            w wVar = (w) obj;
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter(wVar, "value");
            SerialDescriptor serialDescriptor = f4251b;
            CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
            Intrinsics.checkNotNullParameter(wVar, "self");
            Intrinsics.checkNotNullParameter(beginStructure, "output");
            Intrinsics.checkNotNullParameter(serialDescriptor, "serialDesc");
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 0) || wVar.f4247a != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 0, new ArrayListSerializer(c.f3622b), wVar.f4247a);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 1) || wVar.f4248b != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 1, c.f3622b, wVar.f4248b);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 2) || wVar.f4249c != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 2, u.a.f4217a, wVar.f4249c);
            }
            beginStructure.endStructure(serialDescriptor);
        }

        @NotNull
        public KSerializer<?>[] typeParametersSerializers() {
            return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
        }
    }

    public w() {
        this((List) null, (c) null, (u) null, 7);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof w)) {
            return false;
        }
        w wVar = (w) obj;
        return Intrinsics.areEqual((Object) this.f4247a, (Object) wVar.f4247a) && Intrinsics.areEqual((Object) this.f4248b, (Object) wVar.f4248b) && Intrinsics.areEqual((Object) this.f4249c, (Object) wVar.f4249c);
    }

    public int hashCode() {
        List<c> list = this.f4247a;
        int i3 = 0;
        int hashCode = (list == null ? 0 : list.hashCode()) * 31;
        c cVar = this.f4248b;
        int hashCode2 = (hashCode + (cVar == null ? 0 : Integer.hashCode(cVar.f3624a))) * 31;
        u uVar = this.f4249c;
        if (uVar != null) {
            i3 = uVar.hashCode();
        }
        return hashCode2 + i3;
    }

    @NotNull
    public String toString() {
        return "StorylyGroupStyle(borderUnseenColors=" + this.f4247a + ", textUnseenColor=" + this.f4248b + ", badge=" + this.f4249c + ')';
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ w(int i3, @SerialName("b_u_c") List list, @SerialName("t_u_c") c cVar, @SerialName("badge") u uVar) {
        if ((i3 & 1) == 0) {
            this.f4247a = null;
        } else {
            this.f4247a = list;
        }
        if ((i3 & 2) == 0) {
            this.f4248b = null;
        } else {
            this.f4248b = cVar;
        }
        if ((i3 & 4) == 0) {
            this.f4249c = null;
        } else {
            this.f4249c = uVar;
        }
    }

    public w(@Nullable List<c> list, @Nullable c cVar, @Nullable u uVar) {
        this.f4247a = list;
        this.f4248b = cVar;
        this.f4249c = uVar;
    }

    public /* synthetic */ w(List list, c cVar, u uVar, int i3) {
        this((List<c>) null, (c) null, (u) null);
    }
}
