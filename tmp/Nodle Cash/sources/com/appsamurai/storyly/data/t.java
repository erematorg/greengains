package com.appsamurai.storyly.data;

import androidx.camera.core.impl.i;
import androidx.core.view.ViewCompat;
import com.appsamurai.storyly.StoryComponent;
import com.appsamurai.storyly.StoryEmojiComponent;
import com.appsamurai.storyly.data.c;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Required;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable
public final class t extends a0 {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final List<String> f4202a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    public String f4203b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    public c f4204c;
    @Nullable

    /* renamed from: d  reason: collision with root package name */
    public c f4205d;
    @Nullable

    /* renamed from: e  reason: collision with root package name */
    public c f4206e;
    @Nullable

    /* renamed from: f  reason: collision with root package name */
    public c f4207f;
    @Nullable

    /* renamed from: g  reason: collision with root package name */
    public final String f4208g;
    @Nullable

    /* renamed from: h  reason: collision with root package name */
    public final Map<String, Integer> f4209h;

    /* renamed from: i  reason: collision with root package name */
    public final boolean f4210i;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final class a implements GeneratedSerializer<t> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public static final a f4211a;

        /* renamed from: b  reason: collision with root package name */
        public static final /* synthetic */ SerialDescriptor f4212b;

        static {
            a aVar = new a();
            f4211a = aVar;
            PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.appsamurai.storyly.data.StorylyEmojiLayer", aVar, 9);
            pluginGeneratedSerialDescriptor.addElement("emoji_codes", false);
            pluginGeneratedSerialDescriptor.addElement("theme", true);
            pluginGeneratedSerialDescriptor.addElement("background_color", true);
            pluginGeneratedSerialDescriptor.addElement("impression_text_color", true);
            pluginGeneratedSerialDescriptor.addElement("selected_background_color", true);
            pluginGeneratedSerialDescriptor.addElement("border_color", true);
            pluginGeneratedSerialDescriptor.addElement("custom_payload", true);
            pluginGeneratedSerialDescriptor.addElement("click_counts", true);
            pluginGeneratedSerialDescriptor.addElement("is_result", true);
            f4212b = pluginGeneratedSerialDescriptor;
        }

        @NotNull
        public KSerializer<?>[] childSerializers() {
            StringSerializer stringSerializer = StringSerializer.INSTANCE;
            ArrayListSerializer arrayListSerializer = new ArrayListSerializer(stringSerializer);
            KSerializer<?> nullable = BuiltinSerializersKt.getNullable(stringSerializer);
            c.a aVar = c.f3622b;
            return new KSerializer[]{arrayListSerializer, nullable, BuiltinSerializersKt.getNullable(aVar), BuiltinSerializersKt.getNullable(aVar), BuiltinSerializersKt.getNullable(aVar), BuiltinSerializersKt.getNullable(aVar), BuiltinSerializersKt.getNullable(stringSerializer), BuiltinSerializersKt.getNullable(new LinkedHashMapSerializer(stringSerializer, IntSerializer.INSTANCE)), BooleanSerializer.INSTANCE};
        }

        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0093, code lost:
            r6 = 3;
            r11 = r11;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0094, code lost:
            r7 = 8;
            r11 = r11;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x006d, code lost:
            r11 = r11;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x006d, code lost:
            r11 = r11;
         */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object deserialize(kotlinx.serialization.encoding.Decoder r22) {
            /*
                r21 = this;
                r0 = r22
                java.lang.String r1 = "decoder"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
                kotlinx.serialization.descriptors.SerialDescriptor r1 = f4212b
                kotlinx.serialization.encoding.CompositeDecoder r0 = r0.beginStructure(r1)
                boolean r2 = r0.decodeSequentially()
                r3 = 7
                r4 = 6
                r5 = 5
                r6 = 3
                r7 = 8
                r8 = 4
                r9 = 2
                r10 = 1
                r11 = 0
                r12 = 0
                if (r2 == 0) goto L_0x005d
                kotlinx.serialization.internal.ArrayListSerializer r2 = new kotlinx.serialization.internal.ArrayListSerializer
                kotlinx.serialization.internal.StringSerializer r13 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r2.<init>(r13)
                java.lang.Object r2 = r0.decodeSerializableElement(r1, r11, r2, r12)
                java.lang.Object r10 = r0.decodeNullableSerializableElement(r1, r10, r13, r12)
                com.appsamurai.storyly.data.c$a r11 = com.appsamurai.storyly.data.c.f3622b
                java.lang.Object r9 = r0.decodeNullableSerializableElement(r1, r9, r11, r12)
                java.lang.Object r6 = r0.decodeNullableSerializableElement(r1, r6, r11, r12)
                java.lang.Object r8 = r0.decodeNullableSerializableElement(r1, r8, r11, r12)
                java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r5, r11, r12)
                java.lang.Object r4 = r0.decodeNullableSerializableElement(r1, r4, r13, r12)
                kotlinx.serialization.internal.LinkedHashMapSerializer r11 = new kotlinx.serialization.internal.LinkedHashMapSerializer
                kotlinx.serialization.internal.IntSerializer r14 = kotlinx.serialization.internal.IntSerializer.INSTANCE
                r11.<init>(r13, r14)
                java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r3, r11, r12)
                boolean r7 = r0.decodeBooleanElement(r1, r7)
                r11 = 511(0x1ff, float:7.16E-43)
                r17 = r7
                r20 = r11
                r11 = r8
                r8 = r20
                goto L_0x011c
            L_0x005d:
                r19 = r10
                r2 = r11
                r10 = r12
                r13 = r10
                r14 = r13
                r15 = r14
                r16 = r15
                r17 = r16
                r18 = r17
                r11 = r18
                r12 = r2
            L_0x006d:
                if (r19 == 0) goto L_0x010d
                int r9 = r0.decodeElementIndex(r1)
                switch(r9) {
                    case -1: goto L_0x00ff;
                    case 0: goto L_0x00e4;
                    case 1: goto L_0x00d1;
                    case 2: goto L_0x00bd;
                    case 3: goto L_0x00b2;
                    case 4: goto L_0x00a9;
                    case 5: goto L_0x00a0;
                    case 6: goto L_0x0097;
                    case 7: goto L_0x0084;
                    case 8: goto L_0x007c;
                    default: goto L_0x0076;
                }
            L_0x0076:
                kotlinx.serialization.UnknownFieldException r0 = new kotlinx.serialization.UnknownFieldException
                r0.<init>((int) r9)
                throw r0
            L_0x007c:
                boolean r2 = r0.decodeBooleanElement(r1, r7)
                r12 = r12 | 256(0x100, float:3.59E-43)
            L_0x0082:
                r9 = 2
                goto L_0x006d
            L_0x0084:
                kotlinx.serialization.internal.LinkedHashMapSerializer r9 = new kotlinx.serialization.internal.LinkedHashMapSerializer
                kotlinx.serialization.internal.StringSerializer r7 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                kotlinx.serialization.internal.IntSerializer r6 = kotlinx.serialization.internal.IntSerializer.INSTANCE
                r9.<init>(r7, r6)
                java.lang.Object r13 = r0.decodeNullableSerializableElement(r1, r3, r9, r13)
                r12 = r12 | 128(0x80, float:1.794E-43)
            L_0x0093:
                r6 = 3
            L_0x0094:
                r7 = 8
                goto L_0x0082
            L_0x0097:
                kotlinx.serialization.internal.StringSerializer r6 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                java.lang.Object r14 = r0.decodeNullableSerializableElement(r1, r4, r6, r14)
                r12 = r12 | 64
                goto L_0x0093
            L_0x00a0:
                com.appsamurai.storyly.data.c$a r6 = com.appsamurai.storyly.data.c.f3622b
                java.lang.Object r15 = r0.decodeNullableSerializableElement(r1, r5, r6, r15)
                r12 = r12 | 32
                goto L_0x0093
            L_0x00a9:
                com.appsamurai.storyly.data.c$a r6 = com.appsamurai.storyly.data.c.f3622b
                java.lang.Object r11 = r0.decodeNullableSerializableElement(r1, r8, r6, r11)
                r12 = r12 | 16
                goto L_0x0093
            L_0x00b2:
                com.appsamurai.storyly.data.c$a r6 = com.appsamurai.storyly.data.c.f3622b
                r7 = 3
                java.lang.Object r10 = r0.decodeNullableSerializableElement(r1, r7, r6, r10)
                r12 = r12 | 8
                r6 = r7
                goto L_0x0094
            L_0x00bd:
                r7 = r6
                com.appsamurai.storyly.data.c$a r6 = com.appsamurai.storyly.data.c.f3622b
                r9 = r16
                r3 = 2
                java.lang.Object r6 = r0.decodeNullableSerializableElement(r1, r3, r6, r9)
                r12 = r12 | 4
                r9 = r3
                r16 = r6
                r6 = r7
                r3 = 7
                r7 = 8
                goto L_0x006d
            L_0x00d1:
                r7 = r6
                r9 = r16
                r3 = 2
                kotlinx.serialization.internal.StringSerializer r6 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r3 = r17
                r4 = 1
                java.lang.Object r17 = r0.decodeNullableSerializableElement(r1, r4, r6, r3)
                r12 = r12 | 2
                r6 = r7
                r3 = 7
                r4 = 6
                goto L_0x0094
            L_0x00e4:
                r7 = r6
                r9 = r16
                r3 = r17
                r4 = 1
                kotlinx.serialization.internal.ArrayListSerializer r6 = new kotlinx.serialization.internal.ArrayListSerializer
                kotlinx.serialization.internal.StringSerializer r4 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r6.<init>(r4)
                r4 = r18
                r5 = 0
                java.lang.Object r18 = r0.decodeSerializableElement(r1, r5, r6, r4)
                r12 = r12 | 1
                r6 = r7
                r3 = 7
                r4 = 6
                r5 = 5
                goto L_0x0094
            L_0x00ff:
                r9 = r16
                r3 = r17
                r4 = r18
                r5 = 0
                r19 = r5
                r3 = 7
                r4 = 6
                r5 = 5
                goto L_0x0082
            L_0x010d:
                r9 = r16
                r3 = r17
                r4 = r18
                r17 = r2
                r2 = r4
                r6 = r10
                r8 = r12
                r4 = r14
                r5 = r15
                r10 = r3
                r3 = r13
            L_0x011c:
                r0.endStructure(r1)
                com.appsamurai.storyly.data.t r0 = new com.appsamurai.storyly.data.t
                r1 = r2
                java.util.List r1 = (java.util.List) r1
                java.lang.String r10 = (java.lang.String) r10
                r2 = r9
                com.appsamurai.storyly.data.c r2 = (com.appsamurai.storyly.data.c) r2
                r12 = r6
                com.appsamurai.storyly.data.c r12 = (com.appsamurai.storyly.data.c) r12
                r13 = r11
                com.appsamurai.storyly.data.c r13 = (com.appsamurai.storyly.data.c) r13
                r14 = r5
                com.appsamurai.storyly.data.c r14 = (com.appsamurai.storyly.data.c) r14
                r15 = r4
                java.lang.String r15 = (java.lang.String) r15
                r16 = r3
                java.util.Map r16 = (java.util.Map) r16
                r18 = 0
                r7 = r0
                r9 = r1
                r11 = r2
                r7.<init>(r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.t.a.deserialize(kotlinx.serialization.encoding.Decoder):java.lang.Object");
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return f4212b;
        }

        public void serialize(Encoder encoder, Object obj) {
            t tVar = (t) obj;
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter(tVar, "value");
            SerialDescriptor serialDescriptor = f4212b;
            CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
            Intrinsics.checkNotNullParameter(tVar, "self");
            Intrinsics.checkNotNullParameter(beginStructure, "output");
            Intrinsics.checkNotNullParameter(serialDescriptor, "serialDesc");
            a0.a(tVar, beginStructure, serialDescriptor);
            StringSerializer stringSerializer = StringSerializer.INSTANCE;
            beginStructure.encodeSerializableElement(serialDescriptor, 0, new ArrayListSerializer(stringSerializer), tVar.f4202a);
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 1) || tVar.f4203b != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 1, stringSerializer, tVar.f4203b);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 2) || tVar.f4204c != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 2, c.f3622b, tVar.f4204c);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 3) || tVar.f4205d != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 3, c.f3622b, tVar.f4205d);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 4) || tVar.f4206e != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 4, c.f3622b, tVar.f4206e);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 5) || tVar.f4207f != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 5, c.f3622b, tVar.f4207f);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 6) || tVar.f4208g != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 6, stringSerializer, tVar.f4208g);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 7) || tVar.f4209h != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 7, new LinkedHashMapSerializer(stringSerializer, IntSerializer.INSTANCE), tVar.f4209h);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 8) || tVar.f4210i) {
                beginStructure.encodeBooleanElement(serialDescriptor, 8, tVar.f4210i);
            }
            beginStructure.endStructure(serialDescriptor);
        }

        @NotNull
        public KSerializer<?>[] typeParametersSerializers() {
            return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ t(int i3, @Required @SerialName("emoji_codes") List list, @SerialName("theme") String str, @SerialName("background_color") c cVar, @SerialName("impression_text_color") c cVar2, @SerialName("selected_background_color") c cVar3, @SerialName("border_color") c cVar4, @SerialName("custom_payload") String str2, @SerialName("click_counts") Map map, @SerialName("is_result") boolean z2, SerializationConstructorMarker serializationConstructorMarker) {
        super(i3);
        if (1 != (i3 & 1)) {
            PluginExceptionsKt.throwMissingFieldException(i3, 1, a.f4211a.getDescriptor());
        }
        this.f4202a = list;
        if ((i3 & 2) == 0) {
            this.f4203b = null;
        } else {
            this.f4203b = str;
        }
        if ((i3 & 4) == 0) {
            this.f4204c = null;
        } else {
            this.f4204c = cVar;
        }
        if ((i3 & 8) == 0) {
            this.f4205d = null;
        } else {
            this.f4205d = cVar2;
        }
        if ((i3 & 16) == 0) {
            this.f4206e = null;
        } else {
            this.f4206e = cVar3;
        }
        if ((i3 & 32) == 0) {
            this.f4207f = null;
        } else {
            this.f4207f = cVar4;
        }
        if ((i3 & 64) == 0) {
            this.f4208g = null;
        } else {
            this.f4208g = str2;
        }
        if ((i3 & 128) == 0) {
            this.f4209h = null;
        } else {
            this.f4209h = map;
        }
        if ((i3 & 256) == 0) {
            this.f4210i = false;
        } else {
            this.f4210i = z2;
        }
    }

    public StoryComponent a(b0 b0Var) {
        Intrinsics.checkNotNullParameter(b0Var, "storylyLayerItem");
        return new StoryEmojiComponent(b0Var.f3614i, this.f4202a, -1, this.f4208g);
    }

    @NotNull
    public final c b() {
        c cVar = this.f4207f;
        if (cVar != null) {
            return cVar;
        }
        return (Intrinsics.areEqual((Object) d(), (Object) "Dark") ? com.appsamurai.storyly.config.styling.a.COLOR_424242 : com.appsamurai.storyly.config.styling.a.COLOR_E0E0E0).b();
    }

    @NotNull
    public final c c() {
        c cVar = this.f4206e;
        if (cVar == null) {
            if (!Intrinsics.areEqual((Object) d(), (Object) "Dark")) {
                cVar = new c(-1);
            }
        }
        return cVar;
    }

    public final String d() {
        String str = this.f4203b;
        if (str != null) {
            return str;
        }
        c cVar = this.f4204c;
        return (cVar != null && !Intrinsics.areEqual((Object) String.format("#%06X", new Object[]{Integer.valueOf(cVar.f3624a & ViewCompat.MEASURED_SIZE_MASK)}), (Object) "#FFFFFF")) ? "Dark" : "Light";
    }

    @NotNull
    public final c e() {
        c cVar = this.f4205d;
        if (cVar == null) {
            if (!Intrinsics.areEqual((Object) d(), (Object) "Dark")) {
                cVar = new c(ViewCompat.MEASURED_STATE_MASK);
            }
        }
        return cVar;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof t)) {
            return false;
        }
        t tVar = (t) obj;
        return Intrinsics.areEqual((Object) this.f4202a, (Object) tVar.f4202a) && Intrinsics.areEqual((Object) this.f4203b, (Object) tVar.f4203b) && Intrinsics.areEqual((Object) this.f4204c, (Object) tVar.f4204c) && Intrinsics.areEqual((Object) this.f4205d, (Object) tVar.f4205d) && Intrinsics.areEqual((Object) this.f4206e, (Object) tVar.f4206e) && Intrinsics.areEqual((Object) this.f4207f, (Object) tVar.f4207f) && Intrinsics.areEqual((Object) this.f4208g, (Object) tVar.f4208g) && Intrinsics.areEqual((Object) this.f4209h, (Object) tVar.f4209h) && this.f4210i == tVar.f4210i;
    }

    public int hashCode() {
        int hashCode = this.f4202a.hashCode() * 31;
        String str = this.f4203b;
        int i3 = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        c cVar = this.f4204c;
        int hashCode3 = (hashCode2 + (cVar == null ? 0 : Integer.hashCode(cVar.f3624a))) * 31;
        c cVar2 = this.f4205d;
        int hashCode4 = (hashCode3 + (cVar2 == null ? 0 : Integer.hashCode(cVar2.f3624a))) * 31;
        c cVar3 = this.f4206e;
        int hashCode5 = (hashCode4 + (cVar3 == null ? 0 : Integer.hashCode(cVar3.f3624a))) * 31;
        c cVar4 = this.f4207f;
        int hashCode6 = (hashCode5 + (cVar4 == null ? 0 : Integer.hashCode(cVar4.f3624a))) * 31;
        String str2 = this.f4208g;
        int hashCode7 = (hashCode6 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Map<String, Integer> map = this.f4209h;
        if (map != null) {
            i3 = map.hashCode();
        }
        int i4 = (hashCode7 + i3) * 31;
        boolean z2 = this.f4210i;
        if (z2) {
            z2 = true;
        }
        return i4 + (z2 ? 1 : 0);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder("StorylyEmojiLayer(emojiCodes=");
        sb.append(this.f4202a);
        sb.append(", theme=");
        sb.append(this.f4203b);
        sb.append(", backgroundColor=");
        sb.append(this.f4204c);
        sb.append(", impressionTextColor=");
        sb.append(this.f4205d);
        sb.append(", selectedBgColor=");
        sb.append(this.f4206e);
        sb.append(", borderColor=");
        sb.append(this.f4207f);
        sb.append(", customPayload=");
        sb.append(this.f4208g);
        sb.append(", emojiClickNumbers=");
        sb.append(this.f4209h);
        sb.append(", isResult=");
        return i.c(sb, this.f4210i, ')');
    }

    public t(@NotNull List<String> list, @Nullable String str, @Nullable c cVar, @Nullable c cVar2, @Nullable c cVar3, @Nullable c cVar4, @Nullable String str2, @Nullable Map<String, Integer> map, boolean z2) {
        Intrinsics.checkNotNullParameter(list, "emojiCodes");
        this.f4202a = list;
        this.f4203b = str;
        this.f4204c = cVar;
        this.f4205d = cVar2;
        this.f4206e = cVar3;
        this.f4207f = cVar4;
        this.f4208g = str2;
        this.f4209h = map;
        this.f4210i = z2;
    }

    @NotNull
    public StoryComponent a(@NotNull b0 b0Var, int i3) {
        Intrinsics.checkNotNullParameter(b0Var, "storylyLayerItem");
        return new StoryEmojiComponent(b0Var.f3614i, this.f4202a, i3, this.f4208g);
    }

    @NotNull
    public final c a() {
        return (Intrinsics.areEqual((Object) d(), (Object) "Dark") ? com.appsamurai.storyly.config.styling.a.COLOR_212121 : com.appsamurai.storyly.config.styling.a.COLOR_F7F7F7).b();
    }
}
