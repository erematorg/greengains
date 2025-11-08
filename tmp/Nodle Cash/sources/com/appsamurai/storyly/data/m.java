package com.appsamurai.storyly.data;

import com.appsamurai.storyly.data.j;
import com.appsamurai.storyly.data.managers.product.STRProductItem;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.encoding.Decoder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable(with = a.class)
public final class m {
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public static final a f3857b = new a();
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public static final SerialDescriptor f3858c = SerialDescriptorsKt.PrimitiveSerialDescriptor("STRProductData", PrimitiveKind.STRING.INSTANCE);
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final Map<j, List<STRProductItem>> f3859a;

    public static final class a implements KSerializer<m> {
        public Object deserialize(Decoder decoder) {
            Intrinsics.checkNotNullParameter(decoder, "decoder");
            List list = (List) decoder.decodeSerializableValue(BuiltinSerializersKt.ListSerializer(j.a.f3791a));
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10)), 16));
            for (Object next : list) {
                j jVar = (j) next;
                linkedHashMap.put(next, CollectionsKt.emptyList());
            }
            return new m(MapsKt.toMutableMap(linkedHashMap));
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return m.f3858c;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:2:0x0018, code lost:
            r2 = r2.keySet();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void serialize(kotlinx.serialization.encoding.Encoder r1, java.lang.Object r2) {
            /*
                r0 = this;
                com.appsamurai.storyly.data.m r2 = (com.appsamurai.storyly.data.m) r2
                java.lang.String r0 = "encoder"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
                java.lang.String r0 = "value"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
                com.appsamurai.storyly.data.j$a r0 = com.appsamurai.storyly.data.j.a.f3791a
                kotlinx.serialization.KSerializer r0 = kotlinx.serialization.builtins.BuiltinSerializersKt.ListSerializer(r0)
                java.util.Map<com.appsamurai.storyly.data.j, java.util.List<com.appsamurai.storyly.data.managers.product.STRProductItem>> r2 = r2.f3859a
                if (r2 != 0) goto L_0x0018
                goto L_0x001e
            L_0x0018:
                java.util.Set r2 = r2.keySet()
                if (r2 != 0) goto L_0x0020
            L_0x001e:
                r2 = 0
                goto L_0x0024
            L_0x0020:
                java.util.List r2 = kotlin.collections.CollectionsKt.toList(r2)
            L_0x0024:
                if (r2 != 0) goto L_0x002a
                java.util.List r2 = kotlin.collections.CollectionsKt.emptyList()
            L_0x002a:
                r1.encodeSerializableValue(r0, r2)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.m.a.serialize(kotlinx.serialization.encoding.Encoder, java.lang.Object):void");
        }
    }

    public m(@Nullable Map<j, List<STRProductItem>> map) {
        this.f3859a = map;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof m) && Intrinsics.areEqual((Object) this.f3859a, (Object) ((m) obj).f3859a);
    }

    public int hashCode() {
        Map<j, List<STRProductItem>> map = this.f3859a;
        if (map == null) {
            return 0;
        }
        return map.hashCode();
    }

    @NotNull
    public String toString() {
        return "STRProductData(products=" + this.f3859a + ')';
    }
}
