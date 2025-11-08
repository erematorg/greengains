package com.appsamurai.storyly.data;

import com.appsamurai.storyly.StoryComponent;
import com.appsamurai.storyly.StoryComponentType;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.EnumSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable
public final class q0 extends a0 {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public String f4146a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    public String f4147b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    public String f4148c;
    @Nullable

    /* renamed from: d  reason: collision with root package name */
    public String f4149d;
    @NotNull

    /* renamed from: e  reason: collision with root package name */
    public final d f4150e;
    @NotNull

    /* renamed from: f  reason: collision with root package name */
    public final c f4151f;
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public final b f4152g;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final class a implements GeneratedSerializer<q0> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public static final a f4153a;

        /* renamed from: b  reason: collision with root package name */
        public static final /* synthetic */ SerialDescriptor f4154b;

        static {
            a aVar = new a();
            f4153a = aVar;
            PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.appsamurai.storyly.data.StorylyVideoLayer", aVar, 7);
            pluginGeneratedSerialDescriptor.addElement("video_url", true);
            pluginGeneratedSerialDescriptor.addElement("video_path", true);
            pluginGeneratedSerialDescriptor.addElement("thumbnail_url", true);
            pluginGeneratedSerialDescriptor.addElement("thumbnail_path", true);
            pluginGeneratedSerialDescriptor.addElement("video_type", true);
            pluginGeneratedSerialDescriptor.addElement("videoSource", true);
            pluginGeneratedSerialDescriptor.addElement("thumbnailSource", true);
            f4154b = pluginGeneratedSerialDescriptor;
        }

        @NotNull
        public KSerializer<?>[] childSerializers() {
            StringSerializer stringSerializer = StringSerializer.INSTANCE;
            return new KSerializer[]{BuiltinSerializersKt.getNullable(stringSerializer), BuiltinSerializersKt.getNullable(stringSerializer), BuiltinSerializersKt.getNullable(stringSerializer), BuiltinSerializersKt.getNullable(stringSerializer), d.f4162b, new EnumSerializer("com.appsamurai.storyly.data.StorylyVideoLayer.VideoSourceType", c.values()), new EnumSerializer("com.appsamurai.storyly.data.StorylyVideoLayer.ThumbnailSourceType", b.values())};
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0080, code lost:
            r9 = 2;
            r13 = r13;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x00d1, code lost:
            r3 = 6;
            r5 = 5;
            r7 = 3;
            r13 = r13;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0062, code lost:
            r13 = r13;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x0062, code lost:
            r13 = r13;
         */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object deserialize(kotlinx.serialization.encoding.Decoder r20) {
            /*
                r19 = this;
                r0 = r20
                java.lang.String r1 = "decoder"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
                kotlinx.serialization.descriptors.SerialDescriptor r1 = f4154b
                kotlinx.serialization.encoding.CompositeDecoder r0 = r0.beginStructure(r1)
                boolean r2 = r0.decodeSequentially()
                r3 = 6
                java.lang.String r4 = "com.appsamurai.storyly.data.StorylyVideoLayer.ThumbnailSourceType"
                r5 = 5
                java.lang.String r6 = "com.appsamurai.storyly.data.StorylyVideoLayer.VideoSourceType"
                r7 = 3
                r8 = 4
                r9 = 2
                r10 = 1
                r11 = 0
                r12 = 0
                if (r2 == 0) goto L_0x0057
                kotlinx.serialization.internal.StringSerializer r2 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                java.lang.Object r11 = r0.decodeNullableSerializableElement(r1, r11, r2, r12)
                java.lang.Object r10 = r0.decodeNullableSerializableElement(r1, r10, r2, r12)
                java.lang.Object r9 = r0.decodeNullableSerializableElement(r1, r9, r2, r12)
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r7, r2, r12)
                com.appsamurai.storyly.data.q0$d$a r7 = com.appsamurai.storyly.data.q0.d.f4162b
                java.lang.Object r7 = r0.decodeSerializableElement(r1, r8, r7, r12)
                kotlinx.serialization.internal.EnumSerializer r8 = new kotlinx.serialization.internal.EnumSerializer
                com.appsamurai.storyly.data.q0$c[] r13 = com.appsamurai.storyly.data.q0.c.values()
                r8.<init>(r6, r13)
                java.lang.Object r5 = r0.decodeSerializableElement(r1, r5, r8, r12)
                kotlinx.serialization.internal.EnumSerializer r6 = new kotlinx.serialization.internal.EnumSerializer
                com.appsamurai.storyly.data.q0$b[] r8 = com.appsamurai.storyly.data.q0.b.values()
                r6.<init>(r4, r8)
                java.lang.Object r3 = r0.decodeSerializableElement(r1, r3, r6, r12)
                r4 = 127(0x7f, float:1.78E-43)
                r13 = r5
                r5 = r4
                goto L_0x00e8
            L_0x0057:
                r18 = r10
                r2 = r11
                r11 = r12
                r13 = r11
                r14 = r13
                r15 = r14
                r16 = r15
                r17 = r16
            L_0x0062:
                if (r18 == 0) goto L_0x00dd
                int r10 = r0.decodeElementIndex(r1)
                switch(r10) {
                    case -1: goto L_0x00d5;
                    case 0: goto L_0x00c1;
                    case 1: goto L_0x00b0;
                    case 2: goto L_0x00a5;
                    case 3: goto L_0x009c;
                    case 4: goto L_0x0093;
                    case 5: goto L_0x0083;
                    case 6: goto L_0x0071;
                    default: goto L_0x006b;
                }
            L_0x006b:
                kotlinx.serialization.UnknownFieldException r0 = new kotlinx.serialization.UnknownFieldException
                r0.<init>((int) r10)
                throw r0
            L_0x0071:
                kotlinx.serialization.internal.EnumSerializer r10 = new kotlinx.serialization.internal.EnumSerializer
                com.appsamurai.storyly.data.q0$b[] r9 = com.appsamurai.storyly.data.q0.b.values()
                r10.<init>(r4, r9)
                java.lang.Object r12 = r0.decodeSerializableElement(r1, r3, r10, r12)
                r2 = r2 | 64
            L_0x0080:
                r9 = 2
            L_0x0081:
                r10 = 1
                goto L_0x0062
            L_0x0083:
                kotlinx.serialization.internal.EnumSerializer r9 = new kotlinx.serialization.internal.EnumSerializer
                com.appsamurai.storyly.data.q0$c[] r10 = com.appsamurai.storyly.data.q0.c.values()
                r9.<init>(r6, r10)
                java.lang.Object r13 = r0.decodeSerializableElement(r1, r5, r9, r13)
                r2 = r2 | 32
                goto L_0x0080
            L_0x0093:
                com.appsamurai.storyly.data.q0$d$a r9 = com.appsamurai.storyly.data.q0.d.f4162b
                java.lang.Object r14 = r0.decodeSerializableElement(r1, r8, r9, r14)
                r2 = r2 | 16
                goto L_0x0080
            L_0x009c:
                kotlinx.serialization.internal.StringSerializer r9 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                java.lang.Object r15 = r0.decodeNullableSerializableElement(r1, r7, r9, r15)
                r2 = r2 | 8
                goto L_0x0080
            L_0x00a5:
                kotlinx.serialization.internal.StringSerializer r9 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r10 = 2
                java.lang.Object r11 = r0.decodeNullableSerializableElement(r1, r10, r9, r11)
                r2 = r2 | 4
                r9 = r10
                goto L_0x0081
            L_0x00b0:
                r10 = r9
                kotlinx.serialization.internal.StringSerializer r9 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r3 = r16
                r5 = 1
                java.lang.Object r16 = r0.decodeNullableSerializableElement(r1, r5, r9, r3)
                r2 = r2 | 2
                r9 = r10
                r3 = 6
                r10 = r5
                r5 = 5
                goto L_0x0062
            L_0x00c1:
                r10 = r9
                r3 = r16
                r5 = 1
                kotlinx.serialization.internal.StringSerializer r9 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r5 = r17
                r7 = 0
                java.lang.Object r17 = r0.decodeNullableSerializableElement(r1, r7, r9, r5)
                r2 = r2 | 1
                r9 = r10
            L_0x00d1:
                r3 = 6
                r5 = 5
                r7 = 3
                goto L_0x0081
            L_0x00d5:
                r3 = r16
                r5 = r17
                r7 = 0
                r18 = r7
                goto L_0x00d1
            L_0x00dd:
                r3 = r16
                r5 = r17
                r10 = r3
                r9 = r11
                r3 = r12
                r7 = r14
                r11 = r5
                r5 = r2
                r2 = r15
            L_0x00e8:
                r0.endStructure(r1)
                com.appsamurai.storyly.data.q0 r0 = new com.appsamurai.storyly.data.q0
                r6 = r11
                java.lang.String r6 = (java.lang.String) r6
                r1 = r10
                java.lang.String r1 = (java.lang.String) r1
                r8 = r9
                java.lang.String r8 = (java.lang.String) r8
                r9 = r2
                java.lang.String r9 = (java.lang.String) r9
                r10 = r7
                com.appsamurai.storyly.data.q0$d r10 = (com.appsamurai.storyly.data.q0.d) r10
                r11 = r13
                com.appsamurai.storyly.data.q0$c r11 = (com.appsamurai.storyly.data.q0.c) r11
                r12 = r3
                com.appsamurai.storyly.data.q0$b r12 = (com.appsamurai.storyly.data.q0.b) r12
                r13 = 0
                r4 = r0
                r7 = r1
                r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.q0.a.deserialize(kotlinx.serialization.encoding.Decoder):java.lang.Object");
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return f4154b;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:34:0x00a0, code lost:
            if (r6.f4151f != (r6.f4146a != null ? com.appsamurai.storyly.data.q0.c.f4159a : r6.f4147b != null ? com.appsamurai.storyly.data.q0.c.f4160b : com.appsamurai.storyly.data.q0.c.f4159a)) goto L_0x00a2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:49:0x00da, code lost:
            if (r1 != r2) goto L_0x00dc;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void serialize(kotlinx.serialization.encoding.Encoder r5, java.lang.Object r6) {
            /*
                r4 = this;
                com.appsamurai.storyly.data.q0 r6 = (com.appsamurai.storyly.data.q0) r6
                java.lang.String r4 = "encoder"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r4)
                java.lang.String r4 = "value"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r4)
                kotlinx.serialization.descriptors.SerialDescriptor r4 = f4154b
                kotlinx.serialization.encoding.CompositeEncoder r5 = r5.beginStructure(r4)
                java.lang.String r0 = "self"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
                java.lang.String r0 = "output"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
                java.lang.String r0 = "serialDesc"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
                com.appsamurai.storyly.data.a0.a(r6, r5, r4)
                r0 = 0
                boolean r1 = r5.shouldEncodeElementDefault(r4, r0)
                if (r1 == 0) goto L_0x002d
                goto L_0x0031
            L_0x002d:
                java.lang.String r1 = r6.f4146a
                if (r1 == 0) goto L_0x0038
            L_0x0031:
                kotlinx.serialization.internal.StringSerializer r1 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                java.lang.String r2 = r6.f4146a
                r5.encodeNullableSerializableElement(r4, r0, r1, r2)
            L_0x0038:
                r0 = 1
                boolean r1 = r5.shouldEncodeElementDefault(r4, r0)
                if (r1 == 0) goto L_0x0040
                goto L_0x0044
            L_0x0040:
                java.lang.String r1 = r6.f4147b
                if (r1 == 0) goto L_0x004b
            L_0x0044:
                kotlinx.serialization.internal.StringSerializer r1 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                java.lang.String r2 = r6.f4147b
                r5.encodeNullableSerializableElement(r4, r0, r1, r2)
            L_0x004b:
                r0 = 2
                boolean r1 = r5.shouldEncodeElementDefault(r4, r0)
                if (r1 == 0) goto L_0x0053
                goto L_0x0057
            L_0x0053:
                java.lang.String r1 = r6.f4148c
                if (r1 == 0) goto L_0x005e
            L_0x0057:
                kotlinx.serialization.internal.StringSerializer r1 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                java.lang.String r2 = r6.f4148c
                r5.encodeNullableSerializableElement(r4, r0, r1, r2)
            L_0x005e:
                r0 = 3
                boolean r1 = r5.shouldEncodeElementDefault(r4, r0)
                if (r1 == 0) goto L_0x0066
                goto L_0x006a
            L_0x0066:
                java.lang.String r1 = r6.f4149d
                if (r1 == 0) goto L_0x0071
            L_0x006a:
                kotlinx.serialization.internal.StringSerializer r1 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                java.lang.String r2 = r6.f4149d
                r5.encodeNullableSerializableElement(r4, r0, r1, r2)
            L_0x0071:
                r0 = 4
                boolean r1 = r5.shouldEncodeElementDefault(r4, r0)
                if (r1 == 0) goto L_0x0079
                goto L_0x007f
            L_0x0079:
                com.appsamurai.storyly.data.q0$d r1 = r6.f4150e
                com.appsamurai.storyly.data.q0$d r2 = com.appsamurai.storyly.data.q0.d.Short
                if (r1 == r2) goto L_0x0086
            L_0x007f:
                com.appsamurai.storyly.data.q0$d$a r1 = com.appsamurai.storyly.data.q0.d.f4162b
                com.appsamurai.storyly.data.q0$d r2 = r6.f4150e
                r5.encodeSerializableElement(r4, r0, r1, r2)
            L_0x0086:
                r0 = 5
                boolean r1 = r5.shouldEncodeElementDefault(r4, r0)
                if (r1 == 0) goto L_0x008e
                goto L_0x00a2
            L_0x008e:
                com.appsamurai.storyly.data.q0$c r1 = r6.f4151f
                java.lang.String r2 = r6.f4146a
                if (r2 == 0) goto L_0x0097
                com.appsamurai.storyly.data.q0$c r2 = com.appsamurai.storyly.data.q0.c.VideoUrl
                goto L_0x00a0
            L_0x0097:
                java.lang.String r2 = r6.f4147b
                if (r2 == 0) goto L_0x009e
                com.appsamurai.storyly.data.q0$c r2 = com.appsamurai.storyly.data.q0.c.VideoPath
                goto L_0x00a0
            L_0x009e:
                com.appsamurai.storyly.data.q0$c r2 = com.appsamurai.storyly.data.q0.c.VideoUrl
            L_0x00a0:
                if (r1 == r2) goto L_0x00b2
            L_0x00a2:
                kotlinx.serialization.internal.EnumSerializer r1 = new kotlinx.serialization.internal.EnumSerializer
                com.appsamurai.storyly.data.q0$c[] r2 = com.appsamurai.storyly.data.q0.c.values()
                java.lang.String r3 = "com.appsamurai.storyly.data.StorylyVideoLayer.VideoSourceType"
                r1.<init>(r3, r2)
                com.appsamurai.storyly.data.q0$c r2 = r6.f4151f
                r5.encodeSerializableElement(r4, r0, r1, r2)
            L_0x00b2:
                r0 = 6
                boolean r1 = r5.shouldEncodeElementDefault(r4, r0)
                if (r1 == 0) goto L_0x00ba
                goto L_0x00dc
            L_0x00ba:
                com.appsamurai.storyly.data.q0$b r1 = r6.f4152g
                java.lang.String r2 = r6.f4148c
                if (r2 == 0) goto L_0x00ca
                boolean r2 = kotlin.text.StringsKt.isBlank(r2)
                if (r2 == 0) goto L_0x00c7
                goto L_0x00ca
            L_0x00c7:
                com.appsamurai.storyly.data.q0$b r2 = com.appsamurai.storyly.data.q0.b.ThumbnailUrl
                goto L_0x00da
            L_0x00ca:
                java.lang.String r2 = r6.f4149d
                if (r2 == 0) goto L_0x00d8
                boolean r2 = kotlin.text.StringsKt.isBlank(r2)
                if (r2 == 0) goto L_0x00d5
                goto L_0x00d8
            L_0x00d5:
                com.appsamurai.storyly.data.q0$b r2 = com.appsamurai.storyly.data.q0.b.ThumbnailPath
                goto L_0x00da
            L_0x00d8:
                com.appsamurai.storyly.data.q0$b r2 = com.appsamurai.storyly.data.q0.b.Undefined
            L_0x00da:
                if (r1 == r2) goto L_0x00ec
            L_0x00dc:
                kotlinx.serialization.internal.EnumSerializer r1 = new kotlinx.serialization.internal.EnumSerializer
                com.appsamurai.storyly.data.q0$b[] r2 = com.appsamurai.storyly.data.q0.b.values()
                java.lang.String r3 = "com.appsamurai.storyly.data.StorylyVideoLayer.ThumbnailSourceType"
                r1.<init>(r3, r2)
                com.appsamurai.storyly.data.q0$b r6 = r6.f4152g
                r5.encodeSerializableElement(r4, r0, r1, r6)
            L_0x00ec:
                r5.endStructure(r4)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.q0.a.serialize(kotlinx.serialization.encoding.Encoder, java.lang.Object):void");
        }

        @NotNull
        public KSerializer<?>[] typeParametersSerializers() {
            return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
        }
    }

    public enum b {
        ThumbnailUrl,
        ThumbnailPath,
        Undefined
    }

    public enum c {
        VideoUrl,
        VideoPath
    }

    @Serializable(with = a.class)
    public enum d {
        Short(SchemaSymbols.ATTVAL_SHORT),
        Long("long"),
        Live("live");
        
        @NotNull

        /* renamed from: b  reason: collision with root package name */
        public static final a f4162b = null;
        @NotNull

        /* renamed from: c  reason: collision with root package name */
        public static final SerialDescriptor f4163c = null;
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final String f4168a;

        public static final class a implements KSerializer<d> {
            public Object deserialize(Decoder decoder) {
                Intrinsics.checkNotNullParameter(decoder, "decoder");
                String decodeString = decoder.decodeString();
                d dVar = d.Long;
                if (Intrinsics.areEqual((Object) decodeString, (Object) "long")) {
                    return dVar;
                }
                return Intrinsics.areEqual((Object) decodeString, (Object) "live") ? d.Live : d.Short;
            }

            @NotNull
            public SerialDescriptor getDescriptor() {
                return d.f4163c;
            }

            public void serialize(Encoder encoder, Object obj) {
                d dVar = (d) obj;
                Intrinsics.checkNotNullParameter(encoder, "encoder");
                Intrinsics.checkNotNullParameter(dVar, "value");
                encoder.encodeString(dVar.f4168a);
            }
        }

        /* access modifiers changed from: public */
        static {
            f4162b = new a();
            f4163c = SerialDescriptorsKt.PrimitiveSerialDescriptor("StoryGroupType", PrimitiveKind.STRING.INSTANCE);
        }

        /* access modifiers changed from: public */
        d(String str) {
            this.f4168a = str;
        }
    }

    public q0() {
        this((String) null, (String) null, (String) null, (String) null, (d) null, 31);
    }

    public static q0 a(q0 q0Var, String str, String str2, String str3, String str4, d dVar, int i3) {
        d dVar2 = null;
        String str5 = (i3 & 1) != 0 ? q0Var.f4146a : null;
        String str6 = (i3 & 2) != 0 ? q0Var.f4147b : null;
        String str7 = (i3 & 4) != 0 ? q0Var.f4148c : null;
        String str8 = (i3 & 8) != 0 ? q0Var.f4149d : null;
        if ((i3 & 16) != 0) {
            dVar2 = q0Var.f4150e;
        }
        d dVar3 = dVar2;
        q0Var.getClass();
        Intrinsics.checkNotNullParameter(dVar3, "videoType");
        return new q0(str5, str6, str7, str8, dVar3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof q0)) {
            return false;
        }
        q0 q0Var = (q0) obj;
        return Intrinsics.areEqual((Object) this.f4146a, (Object) q0Var.f4146a) && Intrinsics.areEqual((Object) this.f4147b, (Object) q0Var.f4147b) && Intrinsics.areEqual((Object) this.f4148c, (Object) q0Var.f4148c) && Intrinsics.areEqual((Object) this.f4149d, (Object) q0Var.f4149d) && this.f4150e == q0Var.f4150e;
    }

    public int hashCode() {
        String str = this.f4146a;
        int i3 = 0;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.f4147b;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.f4148c;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.f4149d;
        if (str4 != null) {
            i3 = str4.hashCode();
        }
        return this.f4150e.hashCode() + ((hashCode3 + i3) * 31);
    }

    @NotNull
    public String toString() {
        return "StorylyVideoLayer(videoUrl=" + this.f4146a + ", videoPath=" + this.f4147b + ", thumbnailUrl=" + this.f4148c + ", thumbnailPath=" + this.f4149d + ", videoType=" + this.f4150e + ')';
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ q0(int i3, @SerialName("video_url") String str, @SerialName("video_path") String str2, @SerialName("thumbnail_url") String str3, @SerialName("thumbnail_path") String str4, @SerialName("video_type") d dVar, c cVar, b bVar, SerializationConstructorMarker serializationConstructorMarker) {
        super(i3);
        b bVar2;
        c cVar2;
        if ((i3 & 1) == 0) {
            this.f4146a = null;
        } else {
            this.f4146a = str;
        }
        if ((i3 & 2) == 0) {
            this.f4147b = null;
        } else {
            this.f4147b = str2;
        }
        if ((i3 & 4) == 0) {
            this.f4148c = null;
        } else {
            this.f4148c = str3;
        }
        if ((i3 & 8) == 0) {
            this.f4149d = null;
        } else {
            this.f4149d = str4;
        }
        if ((i3 & 16) == 0) {
            this.f4150e = d.Short;
        } else {
            this.f4150e = dVar;
        }
        if ((i3 & 32) == 0) {
            if (this.f4146a != null) {
                cVar2 = c.VideoUrl;
            } else if (this.f4147b != null) {
                cVar2 = c.VideoPath;
            } else {
                cVar2 = c.VideoUrl;
            }
            this.f4151f = cVar2;
        } else {
            this.f4151f = cVar;
        }
        if ((i3 & 64) == 0) {
            String str5 = this.f4148c;
            if (str5 == null || StringsKt.isBlank(str5)) {
                String str6 = this.f4149d;
                if (str6 == null || StringsKt.isBlank(str6)) {
                    bVar2 = b.Undefined;
                } else {
                    bVar2 = b.ThumbnailPath;
                }
            } else {
                bVar2 = b.ThumbnailUrl;
            }
            this.f4152g = bVar2;
            return;
        }
        this.f4152g = bVar;
    }

    @NotNull
    public StoryComponent a(@NotNull b0 b0Var) {
        Intrinsics.checkNotNullParameter(b0Var, "storylyLayerItem");
        return new StoryComponent(b0Var.f3614i, StoryComponentType.Video);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ q0(String str, String str2, String str3, String str4, d dVar, int i3) {
        this((String) null, (String) null, (String) null, (String) null, (i3 & 16) != 0 ? d.Short : null);
    }

    public q0(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @NotNull d dVar) {
        c cVar;
        b bVar;
        Intrinsics.checkNotNullParameter(dVar, "videoType");
        this.f4146a = str;
        this.f4147b = str2;
        this.f4148c = str3;
        this.f4149d = str4;
        this.f4150e = dVar;
        if (str != null) {
            cVar = c.VideoUrl;
        } else if (str2 != null) {
            cVar = c.VideoPath;
        } else {
            cVar = c.VideoUrl;
        }
        this.f4151f = cVar;
        if (str3 == null || StringsKt.isBlank(str3)) {
            String str5 = this.f4149d;
            if (str5 == null || StringsKt.isBlank(str5)) {
                bVar = b.Undefined;
            } else {
                bVar = b.ThumbnailPath;
            }
        } else {
            bVar = b.ThumbnailUrl;
        }
        this.f4152g = bVar;
    }
}
