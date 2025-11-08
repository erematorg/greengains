package com.appsamurai.storyly.data;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.encoding.Encoder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable(with = a.class)
public final class k {
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public static final a f3803c = new a();
    @NotNull

    /* renamed from: d  reason: collision with root package name */
    public static final SerialDescriptor f3804d = SerialDescriptorsKt.PrimitiveSerialDescriptor("Rule", PrimitiveKind.STRING.INSTANCE);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public l f3805a;

    /* renamed from: b  reason: collision with root package name */
    public int f3806b;

    public static final class a implements KSerializer<k> {
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v15, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: com.appsamurai.storyly.data.l} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object deserialize(kotlinx.serialization.encoding.Decoder r4) {
            /*
                r3 = this;
                java.lang.String r3 = "decoder"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r3)
                boolean r3 = r4 instanceof kotlinx.serialization.json.JsonDecoder
                r0 = 0
                if (r3 == 0) goto L_0x000e
                r3 = r4
                kotlinx.serialization.json.JsonDecoder r3 = (kotlinx.serialization.json.JsonDecoder) r3
                goto L_0x000f
            L_0x000e:
                r3 = r0
            L_0x000f:
                if (r3 == 0) goto L_0x007e
                kotlinx.serialization.json.JsonElement r3 = r3.decodeJsonElement()
                kotlinx.serialization.json.JsonObject r3 = kotlinx.serialization.json.JsonElementKt.getJsonObject(r3)
                if (r3 == 0) goto L_0x001c
                goto L_0x001d
            L_0x001c:
                r3 = r0
            L_0x001d:
                if (r3 == 0) goto L_0x0076
                java.lang.String r1 = "p"
                java.lang.Object r1 = r3.get((java.lang.Object) r1)
                kotlinx.serialization.json.JsonElement r1 = (kotlinx.serialization.json.JsonElement) r1
                if (r1 != 0) goto L_0x002a
                goto L_0x0030
            L_0x002a:
                kotlinx.serialization.json.JsonPrimitive r1 = kotlinx.serialization.json.JsonElementKt.getJsonPrimitive(r1)
                if (r1 != 0) goto L_0x0032
            L_0x0030:
                r1 = r0
                goto L_0x0036
            L_0x0032:
                java.lang.Integer r1 = kotlinx.serialization.json.JsonElementKt.getIntOrNull(r1)
            L_0x0036:
                if (r1 == 0) goto L_0x006e
                int r1 = r1.intValue()
                java.lang.String r2 = "t"
                java.lang.Object r3 = r3.get((java.lang.Object) r2)
                kotlinx.serialization.json.JsonElement r3 = (kotlinx.serialization.json.JsonElement) r3
                if (r3 != 0) goto L_0x0048
                goto L_0x005e
            L_0x0048:
                kotlinx.serialization.json.JsonPrimitive r3 = kotlinx.serialization.json.JsonElementKt.getJsonPrimitive(r3)
                if (r3 != 0) goto L_0x004f
                goto L_0x005e
            L_0x004f:
                kotlinx.serialization.json.JsonDecoder r4 = (kotlinx.serialization.json.JsonDecoder) r4
                kotlinx.serialization.json.Json r4 = r4.getJson()
                com.appsamurai.storyly.data.l$a r0 = com.appsamurai.storyly.data.l.f3834b
                java.lang.Object r3 = r4.decodeFromJsonElement(r0, r3)
                r0 = r3
                com.appsamurai.storyly.data.l r0 = (com.appsamurai.storyly.data.l) r0
            L_0x005e:
                if (r0 == 0) goto L_0x0066
                com.appsamurai.storyly.data.k r3 = new com.appsamurai.storyly.data.k
                r3.<init>(r0, r1)
                return r3
            L_0x0066:
                java.lang.Exception r3 = new java.lang.Exception
                java.lang.String r4 = "No condition type found"
                r3.<init>(r4)
                throw r3
            L_0x006e:
                java.lang.Exception r3 = new java.lang.Exception
                java.lang.String r4 = "No condition payload found"
                r3.<init>(r4)
                throw r3
            L_0x0076:
                java.lang.Exception r3 = new java.lang.Exception
                java.lang.String r4 = "No jsonObject found"
                r3.<init>(r4)
                throw r3
            L_0x007e:
                java.lang.Exception r3 = new java.lang.Exception
                java.lang.String r4 = "No JsonDecoder found"
                r3.<init>(r4)
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.k.a.deserialize(kotlinx.serialization.encoding.Decoder):java.lang.Object");
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return k.f3804d;
        }

        public void serialize(Encoder encoder, Object obj) {
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter((k) obj, "value");
        }
    }

    public k(@NotNull l lVar, int i3) {
        Intrinsics.checkNotNullParameter(lVar, "type");
        this.f3805a = lVar;
        this.f3806b = i3;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof k)) {
            return false;
        }
        k kVar = (k) obj;
        return this.f3805a == kVar.f3805a && this.f3806b == kVar.f3806b;
    }

    public int hashCode() {
        return Integer.hashCode(this.f3806b) + (this.f3805a.hashCode() * 31);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder("Rule(type=");
        sb.append(this.f3805a);
        sb.append(", payload=");
        return android.support.v4.media.session.a.p(sb, this.f3806b, ')');
    }
}
