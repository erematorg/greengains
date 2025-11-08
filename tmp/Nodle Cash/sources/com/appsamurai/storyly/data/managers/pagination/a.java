package com.appsamurai.storyly.data.managers.pagination;

import com.appsamurai.storyly.StoryGroupType;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Required;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;

@Serializable
public final class a {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f3931a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final List<String> f3932b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public final StoryGroupType f3933c;

    /* renamed from: d  reason: collision with root package name */
    public final boolean f3934d;

    /* renamed from: e  reason: collision with root package name */
    public int f3935e;

    /* renamed from: f  reason: collision with root package name */
    public boolean f3936f;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    /* renamed from: com.appsamurai.storyly.data.managers.pagination.a$a  reason: collision with other inner class name */
    public static final class C0010a implements GeneratedSerializer<a> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public static final C0010a f3937a;

        /* renamed from: b  reason: collision with root package name */
        public static final /* synthetic */ SerialDescriptor f3938b;

        static {
            C0010a aVar = new C0010a();
            f3937a = aVar;
            PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.appsamurai.storyly.data.managers.pagination.GroupMetaData", aVar, 6);
            pluginGeneratedSerialDescriptor.addElement("sg", false);
            pluginGeneratedSerialDescriptor.addElement("s", false);
            pluginGeneratedSerialDescriptor.addElement("type", true);
            pluginGeneratedSerialDescriptor.addElement("pinned", true);
            pluginGeneratedSerialDescriptor.addElement("order", true);
            pluginGeneratedSerialDescriptor.addElement("hasSeen", true);
            f3938b = pluginGeneratedSerialDescriptor;
        }

        @NotNull
        public KSerializer<?>[] childSerializers() {
            StringSerializer stringSerializer = StringSerializer.INSTANCE;
            ArrayListSerializer arrayListSerializer = new ArrayListSerializer(stringSerializer);
            BooleanSerializer booleanSerializer = BooleanSerializer.INSTANCE;
            return new KSerializer[]{stringSerializer, arrayListSerializer, StoryGroupType.StoryGroupTypeDeserializer, booleanSerializer, IntSerializer.INSTANCE, booleanSerializer};
        }

        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0096, code lost:
            r3 = 5;
         */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object deserialize(kotlinx.serialization.encoding.Decoder r25) {
            /*
                r24 = this;
                r0 = r25
                java.lang.String r1 = "decoder"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
                kotlinx.serialization.descriptors.SerialDescriptor r1 = f3938b
                kotlinx.serialization.encoding.CompositeDecoder r0 = r0.beginStructure(r1)
                boolean r2 = r0.decodeSequentially()
                r3 = 5
                r4 = 3
                r5 = 4
                r6 = 2
                r7 = 1
                r8 = 0
                r9 = 0
                if (r2 == 0) goto L_0x0049
                java.lang.String r2 = r0.decodeStringElement(r1, r8)
                kotlinx.serialization.internal.ArrayListSerializer r8 = new kotlinx.serialization.internal.ArrayListSerializer
                kotlinx.serialization.internal.StringSerializer r10 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r8.<init>(r10)
                java.lang.Object r7 = r0.decodeSerializableElement(r1, r7, r8, r9)
                com.appsamurai.storyly.StoryGroupType$StoryGroupTypeDeserializer r8 = com.appsamurai.storyly.StoryGroupType.StoryGroupTypeDeserializer
                java.lang.Object r6 = r0.decodeSerializableElement(r1, r6, r8, r9)
                boolean r4 = r0.decodeBooleanElement(r1, r4)
                int r5 = r0.decodeIntElement(r1, r5)
                boolean r3 = r0.decodeBooleanElement(r1, r3)
                r8 = 63
                r18 = r2
                r23 = r3
                r21 = r4
                r22 = r5
                r17 = r8
                goto L_0x00a8
            L_0x0049:
                r15 = r7
                r2 = r8
                r10 = r2
                r11 = r10
                r12 = r9
                r13 = r12
                r14 = r13
                r9 = r11
            L_0x0051:
                if (r15 == 0) goto L_0x009c
                int r8 = r0.decodeElementIndex(r1)
                switch(r8) {
                    case -1: goto L_0x0098;
                    case 0: goto L_0x008e;
                    case 1: goto L_0x007f;
                    case 2: goto L_0x0076;
                    case 3: goto L_0x006f;
                    case 4: goto L_0x0068;
                    case 5: goto L_0x0060;
                    default: goto L_0x005a;
                }
            L_0x005a:
                kotlinx.serialization.UnknownFieldException r0 = new kotlinx.serialization.UnknownFieldException
                r0.<init>((int) r8)
                throw r0
            L_0x0060:
                boolean r2 = r0.decodeBooleanElement(r1, r3)
                r11 = r11 | 32
            L_0x0066:
                r8 = 0
                goto L_0x0051
            L_0x0068:
                int r10 = r0.decodeIntElement(r1, r5)
                r11 = r11 | 16
                goto L_0x0066
            L_0x006f:
                boolean r9 = r0.decodeBooleanElement(r1, r4)
                r11 = r11 | 8
                goto L_0x0066
            L_0x0076:
                com.appsamurai.storyly.StoryGroupType$StoryGroupTypeDeserializer r8 = com.appsamurai.storyly.StoryGroupType.StoryGroupTypeDeserializer
                java.lang.Object r12 = r0.decodeSerializableElement(r1, r6, r8, r12)
                r11 = r11 | 4
                goto L_0x0066
            L_0x007f:
                kotlinx.serialization.internal.ArrayListSerializer r8 = new kotlinx.serialization.internal.ArrayListSerializer
                kotlinx.serialization.internal.StringSerializer r3 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r8.<init>(r3)
                java.lang.Object r13 = r0.decodeSerializableElement(r1, r7, r8, r13)
                r11 = r11 | 2
                r3 = 5
                goto L_0x0066
            L_0x008e:
                r3 = 0
                java.lang.String r14 = r0.decodeStringElement(r1, r3)
                r11 = r11 | 1
                r8 = r3
            L_0x0096:
                r3 = 5
                goto L_0x0051
            L_0x0098:
                r3 = 0
                r8 = r3
                r15 = r8
                goto L_0x0096
            L_0x009c:
                r23 = r2
                r21 = r9
                r22 = r10
                r17 = r11
                r6 = r12
                r7 = r13
                r18 = r14
            L_0x00a8:
                r0.endStructure(r1)
                com.appsamurai.storyly.data.managers.pagination.a r0 = new com.appsamurai.storyly.data.managers.pagination.a
                r19 = r7
                java.util.List r19 = (java.util.List) r19
                r20 = r6
                com.appsamurai.storyly.StoryGroupType r20 = (com.appsamurai.storyly.StoryGroupType) r20
                r16 = r0
                r16.<init>(r17, r18, r19, r20, r21, r22, r23)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.managers.pagination.a.C0010a.deserialize(kotlinx.serialization.encoding.Decoder):java.lang.Object");
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return f3938b;
        }

        public void serialize(Encoder encoder, Object obj) {
            a aVar = (a) obj;
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter(aVar, "value");
            SerialDescriptor serialDescriptor = f3938b;
            CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
            Intrinsics.checkNotNullParameter(aVar, "self");
            Intrinsics.checkNotNullParameter(beginStructure, "output");
            Intrinsics.checkNotNullParameter(serialDescriptor, "serialDesc");
            beginStructure.encodeStringElement(serialDescriptor, 0, aVar.f3931a);
            beginStructure.encodeSerializableElement(serialDescriptor, 1, new ArrayListSerializer(StringSerializer.INSTANCE), aVar.f3932b);
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 2) || aVar.f3933c != StoryGroupType.MomentsDefault) {
                beginStructure.encodeSerializableElement(serialDescriptor, 2, StoryGroupType.StoryGroupTypeDeserializer, aVar.f3933c);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 3) || aVar.f3934d) {
                beginStructure.encodeBooleanElement(serialDescriptor, 3, aVar.f3934d);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 4) || aVar.f3935e != 0) {
                beginStructure.encodeIntElement(serialDescriptor, 4, aVar.f3935e);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 5) || aVar.f3936f) {
                beginStructure.encodeBooleanElement(serialDescriptor, 5, aVar.f3936f);
            }
            beginStructure.endStructure(serialDescriptor);
        }

        @NotNull
        public KSerializer<?>[] typeParametersSerializers() {
            return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ a(int i3, @Required @SerialName("sg") String str, @Required @SerialName("s") List list, StoryGroupType storyGroupType, boolean z2, int i4, boolean z3) {
        if (3 != (i3 & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i3, 3, C0010a.f3937a.getDescriptor());
        }
        this.f3931a = str;
        this.f3932b = list;
        if ((i3 & 4) == 0) {
            this.f3933c = StoryGroupType.MomentsDefault;
        } else {
            this.f3933c = storyGroupType;
        }
        if ((i3 & 8) == 0) {
            this.f3934d = false;
        } else {
            this.f3934d = z2;
        }
        if ((i3 & 16) == 0) {
            this.f3935e = 0;
        } else {
            this.f3935e = i4;
        }
        if ((i3 & 32) == 0) {
            this.f3936f = false;
        } else {
            this.f3936f = z3;
        }
    }

    public a(@NotNull String str, @NotNull List<String> list, @NotNull StoryGroupType storyGroupType, boolean z2, int i3) {
        Intrinsics.checkNotNullParameter(str, "storyGroupId");
        Intrinsics.checkNotNullParameter(list, "storyIds");
        Intrinsics.checkNotNullParameter(storyGroupType, "type");
        this.f3931a = str;
        this.f3932b = list;
        this.f3933c = storyGroupType;
        this.f3934d = z2;
        this.f3935e = i3;
    }
}
