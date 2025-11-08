package com.appsamurai.storyly.data;

import com.appsamurai.storyly.MomentsUser;
import com.appsamurai.storyly.ShareType;
import com.appsamurai.storyly.Story;
import com.appsamurai.storyly.StoryGroup;
import com.appsamurai.storyly.StoryGroupBadgeStyle;
import com.appsamurai.storyly.StoryGroupStyle;
import com.appsamurai.storyly.StoryGroupType;
import com.appsamurai.storyly.StoryType;
import com.appsamurai.storyly.util.k;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import kotlin.Lazy;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.Charsets;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.encoding.Encoder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable(with = a.class)
public final class v {
    @NotNull

    /* renamed from: A  reason: collision with root package name */
    public static final SerialDescriptor f4219A = SerialDescriptorsKt.PrimitiveSerialDescriptor("StorylyGroupItem", PrimitiveKind.STRING.INSTANCE);
    @NotNull

    /* renamed from: z  reason: collision with root package name */
    public static final a f4220z = new a();
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f4221a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public String f4222b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public final String f4223c;
    @NotNull

    /* renamed from: d  reason: collision with root package name */
    public String f4224d;

    /* renamed from: e  reason: collision with root package name */
    public int f4225e;
    @NotNull

    /* renamed from: f  reason: collision with root package name */
    public List<z> f4226f;
    @Nullable

    /* renamed from: g  reason: collision with root package name */
    public String f4227g;
    @NotNull

    /* renamed from: h  reason: collision with root package name */
    public final StoryGroupType f4228h;
    @Nullable

    /* renamed from: i  reason: collision with root package name */
    public Set<String> f4229i;

    /* renamed from: j  reason: collision with root package name */
    public boolean f4230j;
    @Nullable

    /* renamed from: k  reason: collision with root package name */
    public final String f4231k;
    @Nullable

    /* renamed from: l  reason: collision with root package name */
    public final Map<String, String> f4232l;
    @Nullable

    /* renamed from: m  reason: collision with root package name */
    public String f4233m;
    @Nullable

    /* renamed from: n  reason: collision with root package name */
    public MomentsUser f4234n;
    @Nullable

    /* renamed from: o  reason: collision with root package name */
    public Integer f4235o;
    @Nullable

    /* renamed from: p  reason: collision with root package name */
    public w f4236p;

    /* renamed from: q  reason: collision with root package name */
    public boolean f4237q;
    @Nullable

    /* renamed from: r  reason: collision with root package name */
    public Integer f4238r;
    @Nullable

    /* renamed from: s  reason: collision with root package name */
    public final Long f4239s;
    @Nullable

    /* renamed from: t  reason: collision with root package name */
    public z f4240t;
    @Nullable

    /* renamed from: u  reason: collision with root package name */
    public Integer f4241u;
    @Nullable

    /* renamed from: v  reason: collision with root package name */
    public Map<String, String> f4242v;

    /* renamed from: w  reason: collision with root package name */
    public boolean f4243w;

    /* renamed from: x  reason: collision with root package name */
    public boolean f4244x;
    @NotNull

    /* renamed from: y  reason: collision with root package name */
    public final Lazy f4245y;

    public static final class a implements KSerializer<v> {

        /* renamed from: com.appsamurai.storyly.data.v$a$a  reason: collision with other inner class name */
        public /* synthetic */ class C0012a {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[StoryGroupType.values().length];
                iArr[StoryGroupType.MomentsDefault.ordinal()] = 1;
                $EnumSwitchMapping$0 = iArr;
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v12, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: com.appsamurai.storyly.data.w} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* JADX WARNING: Removed duplicated region for block: B:147:0x02c7  */
        /* JADX WARNING: Removed duplicated region for block: B:31:0x0086  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object deserialize(kotlinx.serialization.encoding.Decoder r22) {
            /*
                r21 = this;
                r0 = r22
                java.lang.String r1 = "decoder"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
                boolean r1 = r0 instanceof kotlinx.serialization.json.JsonDecoder
                if (r1 == 0) goto L_0x000f
                r1 = r0
                kotlinx.serialization.json.JsonDecoder r1 = (kotlinx.serialization.json.JsonDecoder) r1
                goto L_0x0010
            L_0x000f:
                r1 = 0
            L_0x0010:
                if (r1 == 0) goto L_0x02d7
                kotlinx.serialization.json.JsonElement r1 = r1.decodeJsonElement()
                kotlinx.serialization.json.JsonObject r1 = kotlinx.serialization.json.JsonElementKt.getJsonObject(r1)
                if (r1 == 0) goto L_0x001d
                goto L_0x001e
            L_0x001d:
                r1 = 0
            L_0x001e:
                if (r1 == 0) goto L_0x02cf
                kotlinx.serialization.json.JsonDecoder r0 = (kotlinx.serialization.json.JsonDecoder) r0
                kotlinx.serialization.json.Json r3 = r0.getJson()
                com.appsamurai.storyly.StoryGroupType$StoryGroupTypeDeserializer r4 = com.appsamurai.storyly.StoryGroupType.StoryGroupTypeDeserializer
                kotlinx.serialization.KSerializer r4 = r4.serializer()
                java.lang.String r5 = "type"
                java.lang.Object r5 = r1.get((java.lang.Object) r5)
                kotlinx.serialization.json.JsonElement r5 = (kotlinx.serialization.json.JsonElement) r5
                if (r5 != 0) goto L_0x0038
                goto L_0x0045
            L_0x0038:
                kotlinx.serialization.json.JsonPrimitive r5 = kotlinx.serialization.json.JsonElementKt.getJsonPrimitive(r5)
                if (r5 != 0) goto L_0x003f
                goto L_0x0045
            L_0x003f:
                java.lang.String r5 = r5.toString()
                if (r5 != 0) goto L_0x0047
            L_0x0045:
                java.lang.String r5 = "\"default\""
            L_0x0047:
                java.lang.Object r3 = r3.decodeFromString(r4, r5)
                r12 = r3
                com.appsamurai.storyly.StoryGroupType r12 = (com.appsamurai.storyly.StoryGroupType) r12
                java.lang.String r3 = "group_id"
                java.lang.Object r3 = r1.get((java.lang.Object) r3)
                kotlinx.serialization.json.JsonElement r3 = (kotlinx.serialization.json.JsonElement) r3
                if (r3 != 0) goto L_0x005a
                r3 = 0
                goto L_0x005e
            L_0x005a:
                kotlinx.serialization.json.JsonPrimitive r3 = kotlinx.serialization.json.JsonElementKt.getJsonPrimitive(r3)
            L_0x005e:
                int[] r4 = com.appsamurai.storyly.data.v.a.C0012a.$EnumSwitchMapping$0
                int r5 = r12.ordinal()
                r4 = r4[r5]
                r5 = 1
                if (r4 != r5) goto L_0x0073
                if (r3 != 0) goto L_0x006d
                r5 = 0
                goto L_0x0084
            L_0x006d:
                java.lang.String r3 = kotlinx.serialization.json.JsonElementKt.getContentOrNull(r3)
            L_0x0071:
                r5 = r3
                goto L_0x0084
            L_0x0073:
                if (r3 != 0) goto L_0x0077
                r3 = 0
                goto L_0x007f
            L_0x0077:
                int r3 = kotlinx.serialization.json.JsonElementKt.getInt(r3)
                java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            L_0x007f:
                java.lang.String r3 = java.lang.String.valueOf(r3)
                goto L_0x0071
            L_0x0084:
                if (r5 == 0) goto L_0x02c7
                java.lang.String r3 = "title"
                java.lang.Object r3 = r1.get((java.lang.Object) r3)
                kotlinx.serialization.json.JsonElement r3 = (kotlinx.serialization.json.JsonElement) r3
                if (r3 != 0) goto L_0x0092
                goto L_0x0098
            L_0x0092:
                kotlinx.serialization.json.JsonPrimitive r3 = kotlinx.serialization.json.JsonElementKt.getJsonPrimitive(r3)
                if (r3 != 0) goto L_0x009a
            L_0x0098:
                r6 = 0
                goto L_0x009f
            L_0x009a:
                java.lang.String r3 = kotlinx.serialization.json.JsonElementKt.getContentOrNull(r3)
                r6 = r3
            L_0x009f:
                if (r6 == 0) goto L_0x02bf
                java.lang.String r3 = "media_host"
                java.lang.Object r3 = r1.get((java.lang.Object) r3)
                kotlinx.serialization.json.JsonElement r3 = (kotlinx.serialization.json.JsonElement) r3
                if (r3 != 0) goto L_0x00ac
                goto L_0x00b2
            L_0x00ac:
                kotlinx.serialization.json.JsonPrimitive r3 = kotlinx.serialization.json.JsonElementKt.getJsonPrimitive(r3)
                if (r3 != 0) goto L_0x00b4
            L_0x00b2:
                r7 = 0
                goto L_0x00b9
            L_0x00b4:
                java.lang.String r3 = kotlinx.serialization.json.JsonElementKt.getContentOrNull(r3)
                r7 = r3
            L_0x00b9:
                if (r7 == 0) goto L_0x02b7
                java.lang.String r3 = "icon_image_url"
                java.lang.Object r3 = r1.get((java.lang.Object) r3)
                kotlinx.serialization.json.JsonElement r3 = (kotlinx.serialization.json.JsonElement) r3
                if (r3 != 0) goto L_0x00c6
                goto L_0x00cc
            L_0x00c6:
                kotlinx.serialization.json.JsonPrimitive r3 = kotlinx.serialization.json.JsonElementKt.getJsonPrimitive(r3)
                if (r3 != 0) goto L_0x00ce
            L_0x00cc:
                r8 = 0
                goto L_0x00d3
            L_0x00ce:
                java.lang.String r3 = kotlinx.serialization.json.JsonElementKt.getContentOrNull(r3)
                r8 = r3
            L_0x00d3:
                if (r8 == 0) goto L_0x02af
                java.lang.String r3 = "order"
                java.lang.Object r3 = r1.get((java.lang.Object) r3)
                kotlinx.serialization.json.JsonElement r3 = (kotlinx.serialization.json.JsonElement) r3
                if (r3 != 0) goto L_0x00e0
                goto L_0x00e6
            L_0x00e0:
                kotlinx.serialization.json.JsonPrimitive r3 = kotlinx.serialization.json.JsonElementKt.getJsonPrimitive(r3)
                if (r3 != 0) goto L_0x00e8
            L_0x00e6:
                r3 = 0
                goto L_0x00ec
            L_0x00e8:
                java.lang.Integer r3 = kotlinx.serialization.json.JsonElementKt.getIntOrNull(r3)
            L_0x00ec:
                if (r3 == 0) goto L_0x02a7
                int r9 = r3.intValue()
                java.lang.String r3 = "stories"
                java.lang.Object r3 = r1.get((java.lang.Object) r3)
                kotlinx.serialization.json.JsonElement r3 = (kotlinx.serialization.json.JsonElement) r3
                if (r3 != 0) goto L_0x00fd
                goto L_0x0103
            L_0x00fd:
                kotlinx.serialization.json.JsonArray r3 = kotlinx.serialization.json.JsonElementKt.getJsonArray(r3)
                if (r3 != 0) goto L_0x0105
            L_0x0103:
                r10 = 0
                goto L_0x0133
            L_0x0105:
                java.util.ArrayList r4 = new java.util.ArrayList
                int r10 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r3, 10)
                r4.<init>(r10)
                java.util.Iterator r3 = r3.iterator()
            L_0x0112:
                boolean r10 = r3.hasNext()
                if (r10 == 0) goto L_0x012e
                java.lang.Object r10 = r3.next()
                kotlinx.serialization.json.JsonElement r10 = (kotlinx.serialization.json.JsonElement) r10
                kotlinx.serialization.json.Json r11 = r0.getJson()
                com.appsamurai.storyly.data.z$a r13 = com.appsamurai.storyly.data.z.f4300u
                java.lang.Object r10 = r11.decodeFromJsonElement(r13, r10)
                com.appsamurai.storyly.data.z r10 = (com.appsamurai.storyly.data.z) r10
                r4.add(r10)
                goto L_0x0112
            L_0x012e:
                java.util.List r3 = kotlin.collections.CollectionsKt.toMutableList(r4)
                r10 = r3
            L_0x0133:
                if (r10 == 0) goto L_0x029f
                java.lang.String r3 = "cover_image_url"
                java.lang.Object r3 = r1.get((java.lang.Object) r3)
                kotlinx.serialization.json.JsonElement r3 = (kotlinx.serialization.json.JsonElement) r3
                if (r3 != 0) goto L_0x0140
                goto L_0x0146
            L_0x0140:
                kotlinx.serialization.json.JsonPrimitive r3 = kotlinx.serialization.json.JsonElementKt.getJsonPrimitive(r3)
                if (r3 != 0) goto L_0x0148
            L_0x0146:
                r11 = 0
                goto L_0x014d
            L_0x0148:
                java.lang.String r3 = kotlinx.serialization.json.JsonElementKt.getContentOrNull(r3)
                r11 = r3
            L_0x014d:
                java.lang.String r3 = "segments"
                java.lang.Object r3 = r1.get((java.lang.Object) r3)
                kotlinx.serialization.json.JsonElement r3 = (kotlinx.serialization.json.JsonElement) r3
                if (r3 != 0) goto L_0x0158
                goto L_0x015e
            L_0x0158:
                kotlinx.serialization.json.JsonArray r3 = kotlinx.serialization.json.JsonElementKt.getJsonArray(r3)
                if (r3 != 0) goto L_0x0160
            L_0x015e:
                r13 = 0
                goto L_0x018a
            L_0x0160:
                java.util.ArrayList r4 = new java.util.ArrayList
                int r13 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r3, 10)
                r4.<init>(r13)
                java.util.Iterator r3 = r3.iterator()
            L_0x016d:
                boolean r13 = r3.hasNext()
                if (r13 == 0) goto L_0x0185
                java.lang.Object r13 = r3.next()
                kotlinx.serialization.json.JsonElement r13 = (kotlinx.serialization.json.JsonElement) r13
                kotlinx.serialization.json.JsonPrimitive r13 = kotlinx.serialization.json.JsonElementKt.getJsonPrimitive(r13)
                java.lang.String r13 = r13.getContent()
                r4.add(r13)
                goto L_0x016d
            L_0x0185:
                java.util.Set r3 = kotlin.collections.CollectionsKt.toSet(r4)
                r13 = r3
            L_0x018a:
                java.lang.String r3 = "pinned"
                java.lang.Object r3 = r1.get((java.lang.Object) r3)
                kotlinx.serialization.json.JsonElement r3 = (kotlinx.serialization.json.JsonElement) r3
                if (r3 != 0) goto L_0x0195
                goto L_0x01a2
            L_0x0195:
                kotlinx.serialization.json.JsonPrimitive r3 = kotlinx.serialization.json.JsonElementKt.getJsonPrimitive(r3)
                if (r3 != 0) goto L_0x019c
                goto L_0x01a2
            L_0x019c:
                java.lang.Boolean r3 = kotlinx.serialization.json.JsonElementKt.getBooleanOrNull(r3)
                if (r3 != 0) goto L_0x01a5
            L_0x01a2:
                r3 = 0
            L_0x01a3:
                r14 = r3
                goto L_0x01aa
            L_0x01a5:
                boolean r3 = r3.booleanValue()
                goto L_0x01a3
            L_0x01aa:
                java.lang.String r3 = "end_date"
                java.lang.Object r3 = r1.get((java.lang.Object) r3)
                kotlinx.serialization.json.JsonElement r3 = (kotlinx.serialization.json.JsonElement) r3
                if (r3 != 0) goto L_0x01b5
                goto L_0x01bb
            L_0x01b5:
                kotlinx.serialization.json.JsonPrimitive r3 = kotlinx.serialization.json.JsonElementKt.getJsonPrimitive(r3)
                if (r3 != 0) goto L_0x01bd
            L_0x01bb:
                r15 = 0
                goto L_0x01c2
            L_0x01bd:
                java.lang.String r3 = kotlinx.serialization.json.JsonElementKt.getContentOrNull(r3)
                r15 = r3
            L_0x01c2:
                java.lang.String r3 = "ugc_token"
                java.lang.Object r3 = r1.get((java.lang.Object) r3)
                kotlinx.serialization.json.JsonElement r3 = (kotlinx.serialization.json.JsonElement) r3
                if (r3 != 0) goto L_0x01ce
                goto L_0x01d4
            L_0x01ce:
                kotlinx.serialization.json.JsonPrimitive r3 = kotlinx.serialization.json.JsonElementKt.getJsonPrimitive(r3)
                if (r3 != 0) goto L_0x01d7
            L_0x01d4:
                r17 = 0
                goto L_0x01dd
            L_0x01d7:
                java.lang.String r3 = kotlinx.serialization.json.JsonElementKt.getContentOrNull(r3)
                r17 = r3
            L_0x01dd:
                java.lang.String r3 = "moments_user"
                java.lang.Object r3 = r1.get((java.lang.Object) r3)
                kotlinx.serialization.json.JsonElement r3 = (kotlinx.serialization.json.JsonElement) r3
                if (r3 != 0) goto L_0x01e8
                goto L_0x01ee
            L_0x01e8:
                kotlinx.serialization.json.JsonObject r3 = kotlinx.serialization.json.JsonElementKt.getJsonObject(r3)
                if (r3 != 0) goto L_0x01f1
            L_0x01ee:
                r18 = 0
                goto L_0x0204
            L_0x01f1:
                kotlinx.serialization.json.Json r4 = r0.getJson()
                com.appsamurai.storyly.MomentsUser$b r16 = com.appsamurai.storyly.MomentsUser.Companion
                r16.getClass()
                com.appsamurai.storyly.MomentsUser$a r2 = com.appsamurai.storyly.MomentsUser.a.f3407a
                java.lang.Object r2 = r4.decodeFromJsonElement(r2, r3)
                com.appsamurai.storyly.MomentsUser r2 = (com.appsamurai.storyly.MomentsUser) r2
                r18 = r2
            L_0x0204:
                java.lang.String r2 = "max_sg_count"
                java.lang.Object r2 = r1.get((java.lang.Object) r2)
                kotlinx.serialization.json.JsonElement r2 = (kotlinx.serialization.json.JsonElement) r2
                if (r2 != 0) goto L_0x020f
                goto L_0x0215
            L_0x020f:
                kotlinx.serialization.json.JsonPrimitive r2 = kotlinx.serialization.json.JsonElementKt.getJsonPrimitive(r2)
                if (r2 != 0) goto L_0x0218
            L_0x0215:
                r19 = 0
                goto L_0x021e
            L_0x0218:
                java.lang.Integer r2 = kotlinx.serialization.json.JsonElementKt.getIntOrNull(r2)
                r19 = r2
            L_0x021e:
                java.lang.String r2 = "thematic_icons"
                java.lang.Object r2 = r1.get((java.lang.Object) r2)
                kotlinx.serialization.json.JsonElement r2 = (kotlinx.serialization.json.JsonElement) r2
                if (r2 != 0) goto L_0x022a
                goto L_0x0230
            L_0x022a:
                kotlinx.serialization.json.JsonObject r2 = kotlinx.serialization.json.JsonElementKt.getJsonObject(r2)
                if (r2 != 0) goto L_0x0233
            L_0x0230:
                r16 = 0
                goto L_0x0274
            L_0x0233:
                java.util.ArrayList r3 = new java.util.ArrayList
                int r4 = r2.size()
                r3.<init>(r4)
                java.util.Set r2 = r2.entrySet()
                java.util.Iterator r2 = r2.iterator()
            L_0x0244:
                boolean r4 = r2.hasNext()
                if (r4 == 0) goto L_0x026e
                java.lang.Object r4 = r2.next()
                java.util.Map$Entry r4 = (java.util.Map.Entry) r4
                r22 = r2
                java.lang.Object r2 = r4.getKey()
                java.lang.Object r4 = r4.getValue()
                kotlinx.serialization.json.JsonElement r4 = (kotlinx.serialization.json.JsonElement) r4
                kotlinx.serialization.json.JsonPrimitive r4 = kotlinx.serialization.json.JsonElementKt.getJsonPrimitive(r4)
                java.lang.String r4 = r4.getContent()
                kotlin.Pair r2 = kotlin.TuplesKt.to(r2, r4)
                r3.add(r2)
                r2 = r22
                goto L_0x0244
            L_0x026e:
                java.util.Map r2 = kotlin.collections.MapsKt.toMap(r3)
                r16 = r2
            L_0x0274:
                java.lang.String r2 = "style"
                java.lang.Object r1 = r1.get((java.lang.Object) r2)
                kotlinx.serialization.json.JsonElement r1 = (kotlinx.serialization.json.JsonElement) r1
                if (r1 != 0) goto L_0x0280
                goto L_0x0286
            L_0x0280:
                kotlinx.serialization.json.JsonObject r1 = kotlinx.serialization.json.JsonElementKt.getJsonObject(r1)
                if (r1 != 0) goto L_0x0289
            L_0x0286:
                r20 = 0
                goto L_0x0298
            L_0x0289:
                kotlinx.serialization.json.Json r0 = r0.getJson()
                com.appsamurai.storyly.data.w$a r2 = com.appsamurai.storyly.data.w.a.f4250a
                java.lang.Object r0 = r0.decodeFromJsonElement(r2, r1)
                r2 = r0
                com.appsamurai.storyly.data.w r2 = (com.appsamurai.storyly.data.w) r2
                r20 = r2
            L_0x0298:
                com.appsamurai.storyly.data.v r0 = new com.appsamurai.storyly.data.v
                r4 = r0
                r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)
                return r0
            L_0x029f:
                java.lang.Exception r0 = new java.lang.Exception
                java.lang.String r1 = "StorylyGroupItem Deserialize Exception: No stories found"
                r0.<init>(r1)
                throw r0
            L_0x02a7:
                java.lang.Exception r0 = new java.lang.Exception
                java.lang.String r1 = "StorylyGroupItem Deserialize Exception: No order found"
                r0.<init>(r1)
                throw r0
            L_0x02af:
                java.lang.Exception r0 = new java.lang.Exception
                java.lang.String r1 = "StorylyGroupItem Deserialize Exception: No icon_image_url found"
                r0.<init>(r1)
                throw r0
            L_0x02b7:
                java.lang.Exception r0 = new java.lang.Exception
                java.lang.String r1 = "StorylyGroupItem Deserialize Exception: No media_host found"
                r0.<init>(r1)
                throw r0
            L_0x02bf:
                java.lang.Exception r0 = new java.lang.Exception
                java.lang.String r1 = "StorylyGroupItem Deserialize Exception: No title found"
                r0.<init>(r1)
                throw r0
            L_0x02c7:
                java.lang.Exception r0 = new java.lang.Exception
                java.lang.String r1 = "StorylyGroupItem Deserialize Exception: No group_id found"
                r0.<init>(r1)
                throw r0
            L_0x02cf:
                java.lang.Exception r0 = new java.lang.Exception
                java.lang.String r1 = "StorylyGroupItem Deserialize Exception: No jsonObject found"
                r0.<init>(r1)
                throw r0
            L_0x02d7:
                java.lang.Exception r0 = new java.lang.Exception
                java.lang.String r1 = "StorylyGroupItem Deserialize Exception: No JsonDecoder found"
                r0.<init>(r1)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.v.a.deserialize(kotlinx.serialization.encoding.Decoder):java.lang.Object");
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return v.f4219A;
        }

        public void serialize(Encoder encoder, Object obj) {
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter((v) obj, "value");
        }
    }

    public static final class b extends Lambda implements Function0<String> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ v f4246a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public b(v vVar) {
            super(0);
            this.f4246a = vVar;
        }

        public Object invoke() {
            Map<String, String> map = this.f4246a.f4242v;
            if (map == null) {
                return null;
            }
            SortedMap<String, String> sortedMap = MapsKt.toSortedMap(map);
            ArrayList arrayList = new ArrayList(sortedMap.size());
            for (Map.Entry next : sortedMap.entrySet()) {
                arrayList.add(new Pair(next.getKey(), next.getValue()));
            }
            String obj = arrayList.toString();
            Intrinsics.checkNotNullParameter(obj, "str");
            MessageDigest instance = MessageDigest.getInstance("MD5");
            byte[] bytes = obj.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
            byte[] digest = instance.digest(bytes);
            Intrinsics.checkNotNullExpressionValue(digest, "getInstance(\"MD5\").diges…yteArray(Charsets.UTF_8))");
            Intrinsics.checkNotNullParameter(digest, "<this>");
            return ArraysKt___ArraysKt.joinToString$default(digest, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) k.f6341a, 30, (Object) null);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0062, code lost:
        r2 = com.appsamurai.storyly.util.h.a().parse(r7);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public v(@org.jetbrains.annotations.NotNull java.lang.String r10, @org.jetbrains.annotations.NotNull java.lang.String r11, @org.jetbrains.annotations.NotNull java.lang.String r12, @org.jetbrains.annotations.NotNull java.lang.String r13, int r14, @org.jetbrains.annotations.NotNull java.util.List<com.appsamurai.storyly.data.z> r15, @org.jetbrains.annotations.Nullable java.lang.String r16, @org.jetbrains.annotations.NotNull com.appsamurai.storyly.StoryGroupType r17, @org.jetbrains.annotations.Nullable java.util.Set<java.lang.String> r18, boolean r19, @org.jetbrains.annotations.Nullable java.lang.String r20, @org.jetbrains.annotations.Nullable java.util.Map<java.lang.String, java.lang.String> r21, @org.jetbrains.annotations.Nullable java.lang.String r22, @org.jetbrains.annotations.Nullable com.appsamurai.storyly.MomentsUser r23, @org.jetbrains.annotations.Nullable java.lang.Integer r24, @org.jetbrains.annotations.Nullable com.appsamurai.storyly.data.w r25) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r11
            r3 = r12
            r4 = r13
            r5 = r15
            r6 = r17
            r7 = r20
            java.lang.String r8 = "groupId"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r8)
            java.lang.String r8 = "title"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r8)
            java.lang.String r8 = "mediaHost"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r8)
            java.lang.String r8 = "iconImageUrl"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r8)
            java.lang.String r8 = "stories"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r15, r8)
            java.lang.String r8 = "type"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r8)
            r9.<init>()
            r0.f4221a = r1
            r0.f4222b = r2
            r0.f4223c = r3
            r0.f4224d = r4
            r1 = r14
            r0.f4225e = r1
            r0.f4226f = r5
            r1 = r16
            r0.f4227g = r1
            r0.f4228h = r6
            r1 = r18
            r0.f4229i = r1
            r1 = r19
            r0.f4230j = r1
            r0.f4231k = r7
            r1 = r21
            r0.f4232l = r1
            r1 = r22
            r0.f4233m = r1
            r1 = r23
            r0.f4234n = r1
            r1 = r24
            r0.f4235o = r1
            r1 = r25
            r0.f4236p = r1
            r1 = 0
            if (r7 != 0) goto L_0x0062
            goto L_0x006c
        L_0x0062:
            java.text.SimpleDateFormat r2 = com.appsamurai.storyly.util.h.a()
            java.util.Date r2 = r2.parse(r7)
            if (r2 != 0) goto L_0x006e
        L_0x006c:
            r2 = r1
            goto L_0x0076
        L_0x006e:
            long r2 = r2.getTime()
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
        L_0x0076:
            if (r2 != 0) goto L_0x0079
            goto L_0x007a
        L_0x0079:
            r1 = r2
        L_0x007a:
            r0.f4239s = r1
            com.appsamurai.storyly.data.v$b r1 = new com.appsamurai.storyly.data.v$b
            r1.<init>(r9)
            kotlin.Lazy r1 = kotlin.LazyKt.lazy(r1)
            r0.f4245y = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.v.<init>(java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, java.util.List, java.lang.String, com.appsamurai.storyly.StoryGroupType, java.util.Set, boolean, java.lang.String, java.util.Map, java.lang.String, com.appsamurai.storyly.MomentsUser, java.lang.Integer, com.appsamurai.storyly.data.w):void");
    }

    @NotNull
    public final v a() {
        List list;
        z zVar;
        ArrayList arrayList;
        ArrayList arrayList2;
        com.appsamurai.storyly.analytics.b bVar;
        Long l2;
        ShareType shareType;
        String str;
        int i3;
        String str2 = this.f4221a;
        String str3 = this.f4222b;
        String str4 = this.f4223c;
        String str5 = this.f4224d;
        int i4 = this.f4225e;
        ArrayList arrayList3 = new ArrayList();
        List<z> list2 = this.f4226f;
        ArrayList arrayList4 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            z zVar2 = (z) it.next();
            String str6 = zVar2.f4302a;
            d0 d0Var = zVar2.f4303b;
            d0Var.getClass();
            ArrayList arrayList5 = new ArrayList();
            List<b0> list3 = d0Var.f3639a;
            if (list3 == null) {
                list = null;
            } else {
                ArrayList arrayList6 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list3, 10));
                for (b0 b0Var : list3) {
                    arrayList6.add(b0Var == null ? null : b0Var.a());
                }
                list = arrayList6;
            }
            if (list == null) {
                list = CollectionsKt.emptyList();
            }
            arrayList5.addAll(list);
            Unit unit = Unit.INSTANCE;
            d0 d0Var2 = new d0(arrayList5, d0Var.f3640b);
            long j2 = zVar2.f4304c;
            String str7 = zVar2.f4305d;
            int i5 = zVar2.f4306e;
            Iterator<T> it2 = it;
            StoryType storyType = zVar2.f4307f;
            long j3 = j2;
            String str8 = zVar2.f4308g;
            String str9 = zVar2.f4309h;
            int i6 = i4;
            String str10 = zVar2.f4310i;
            String str11 = str5;
            String str12 = zVar2.f4311j;
            String str13 = str4;
            ShareType shareType2 = zVar2.f4312k;
            String str14 = str3;
            Long l3 = zVar2.f4313l;
            String str15 = str2;
            com.appsamurai.storyly.analytics.b bVar2 = zVar2.f4314m;
            ArrayList arrayList7 = new ArrayList();
            String str16 = str9;
            List<? extends List<n>> list4 = zVar2.f4315n;
            if (list4 == null) {
                bVar = bVar2;
                l2 = l3;
                shareType = shareType2;
                arrayList2 = arrayList3;
                arrayList = arrayList4;
                zVar = zVar2;
                i3 = i5;
                str = str7;
            } else {
                i3 = i5;
                str = str7;
                ArrayList arrayList8 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list4, 10));
                Iterator<T> it3 = list4.iterator();
                while (it3.hasNext()) {
                    List list5 = (List) it3.next();
                    Iterator<T> it4 = it3;
                    ArrayList arrayList9 = new ArrayList();
                    ArrayList arrayList10 = arrayList3;
                    ArrayList arrayList11 = arrayList4;
                    ArrayList arrayList12 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list5, 10));
                    Iterator it5 = list5.iterator();
                    while (it5.hasNext()) {
                        n nVar = (n) it5.next();
                        Iterator it6 = it5;
                        n nVar2 = new n(nVar.f4052a, nVar.f4053b, nVar.f4054c, nVar.f4055d);
                        nVar2.f4056e = nVar.f4056e;
                        arrayList12.add(nVar2);
                        it5 = it6;
                        shareType2 = shareType2;
                        l3 = l3;
                        bVar2 = bVar2;
                        zVar2 = zVar2;
                    }
                    com.appsamurai.storyly.analytics.b bVar3 = bVar2;
                    Long l4 = l3;
                    ShareType shareType3 = shareType2;
                    z zVar3 = zVar2;
                    arrayList9.addAll(arrayList12);
                    arrayList8.add(arrayList9);
                    it3 = it4;
                    arrayList3 = arrayList10;
                    arrayList4 = arrayList11;
                }
                bVar = bVar2;
                l2 = l3;
                shareType = shareType2;
                arrayList2 = arrayList3;
                arrayList = arrayList4;
                zVar = zVar2;
                arrayList7.addAll(arrayList8);
            }
            Unit unit2 = Unit.INSTANCE;
            z zVar4 = new z(str6, d0Var2, j3, str, i3, storyType, str8, str16, str10, str12, shareType, l2, bVar, arrayList7);
            z zVar5 = zVar;
            zVar4.f4317p = zVar5.f4317p;
            zVar4.f4320s = zVar5.f4320s;
            zVar4.f4316o = zVar5.f4316o;
            zVar4.f4318q = zVar5.f4318q;
            ArrayList arrayList13 = arrayList;
            arrayList13.add(zVar4);
            arrayList4 = arrayList13;
            it = it2;
            i4 = i6;
            str5 = str11;
            str4 = str13;
            str3 = str14;
            str2 = str15;
            arrayList3 = arrayList2;
        }
        String str17 = str2;
        String str18 = str3;
        String str19 = str4;
        String str20 = str5;
        int i7 = i4;
        arrayList3.addAll(arrayList4);
        Unit unit3 = Unit.INSTANCE;
        String str21 = this.f4227g;
        StoryGroupType storyGroupType = this.f4228h;
        Set<String> set = this.f4229i;
        Set<T> set2 = set == null ? null : CollectionsKt.toSet(set);
        boolean z2 = this.f4230j;
        String str22 = this.f4231k;
        Map<String, String> map = this.f4232l;
        String str23 = this.f4233m;
        MomentsUser momentsUser = this.f4234n;
        Integer num = this.f4235o;
        w wVar = this.f4236p;
        v vVar = r1;
        v vVar2 = new v(str17, str18, str19, str20, i7, arrayList3, str21, storyGroupType, set2, z2, str22, map, str23, momentsUser, num, wVar == null ? null : new w(wVar.f4247a, wVar.f4248b, wVar.f4249c));
        v vVar3 = vVar;
        vVar3.f4238r = this.f4238r;
        vVar3.f4240t = this.f4240t;
        vVar3.f4241u = this.f4241u;
        vVar3.f4237q = this.f4237q;
        vVar3.f4242v = this.f4242v;
        vVar3.f4244x = this.f4244x;
        vVar3.f4243w = this.f4243w;
        return vVar3;
    }

    public final int b() {
        Integer num = this.f4238r;
        if (num != null) {
            return num.intValue();
        }
        Iterator<z> it = this.f4226f.iterator();
        int i3 = 0;
        while (true) {
            if (!it.hasNext()) {
                i3 = -1;
                break;
            } else if (!it.next().f4320s) {
                break;
            } else {
                i3++;
            }
        }
        return Math.max(i3, 0);
    }

    public final boolean c() {
        if (!this.f4243w && !this.f4244x) {
            List<z> list = this.f4226f;
            if (!(list instanceof Collection) || !list.isEmpty()) {
                for (z zVar : list) {
                    if (zVar.f4318q) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @NotNull
    public final StoryGroup d() {
        LinkedHashMap linkedHashMap;
        StoryGroupType storyGroupType;
        MomentsUser momentsUser;
        StoryGroupStyle storyGroupStyle;
        ArrayList arrayList;
        StoryGroupBadgeStyle storyGroupBadgeStyle;
        Integer num;
        boolean e02 = StringsKt__StringsJVMKt.startsWith$default(this.f4224d, "http", false, 2, (Object) null);
        String str = this.f4221a;
        String str2 = this.f4222b;
        String stringPlus = e02 ? this.f4224d : Intrinsics.stringPlus(this.f4223c, this.f4224d);
        Map<String, String> map = this.f4232l;
        if (map == null) {
            linkedHashMap = null;
        } else {
            linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(map.size()));
            for (Map.Entry entry : map.entrySet()) {
                linkedHashMap.put(entry.getKey(), Intrinsics.stringPlus(this.f4223c, entry.getValue()));
            }
        }
        String str3 = this.f4227g;
        String stringPlus2 = str3 == null ? null : Intrinsics.stringPlus(this.f4223c, str3);
        int i3 = this.f4225e;
        boolean z2 = this.f4237q;
        List<z> list = this.f4226f;
        ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
        for (z a2 : list) {
            Story a3 = a2.a();
            String previewUrl = a3.getMedia().getPreviewUrl();
            if (previewUrl != null) {
                a3.getMedia().setPreviewUrl(Intrinsics.stringPlus(this.f4223c, previewUrl));
            }
            arrayList2.add(a3);
        }
        boolean z3 = this.f4230j;
        StoryGroupType storyGroupType2 = this.f4228h;
        MomentsUser momentsUser2 = this.f4234n;
        w wVar = this.f4236p;
        if (wVar == null) {
            storyGroupType = storyGroupType2;
            momentsUser = momentsUser2;
            storyGroupStyle = null;
        } else {
            List<c> list2 = wVar.f4247a;
            if (list2 == null) {
                arrayList = null;
            } else {
                arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
                for (c cVar : list2) {
                    arrayList.add(Integer.valueOf(cVar.f3624a));
                }
            }
            c cVar2 = wVar.f4248b;
            Integer valueOf = cVar2 == null ? null : Integer.valueOf(cVar2.f3624a);
            u uVar = wVar.f4249c;
            if (uVar == null) {
                storyGroupType = storyGroupType2;
                momentsUser = momentsUser2;
                storyGroupBadgeStyle = null;
            } else {
                String str4 = uVar.f4213a;
                momentsUser = momentsUser2;
                c cVar3 = uVar.f4214b;
                if (cVar3 == null) {
                    storyGroupType = storyGroupType2;
                    num = null;
                } else {
                    num = Integer.valueOf(cVar3.f3624a);
                    storyGroupType = storyGroupType2;
                }
                c cVar4 = uVar.f4215c;
                storyGroupBadgeStyle = new StoryGroupBadgeStyle(str4, num, cVar4 == null ? null : Integer.valueOf(cVar4.f3624a), uVar.f4216d);
            }
            storyGroupStyle = new StoryGroupStyle(arrayList, valueOf, storyGroupBadgeStyle);
        }
        return new StoryGroup(str, str2, stringPlus, linkedHashMap, stringPlus2, i3, z2, arrayList2, z3, storyGroupType, momentsUser, storyGroupStyle);
    }

    @NotNull
    public final StoryGroupType e() {
        return this.f4228h;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof v)) {
            return false;
        }
        v vVar = (v) obj;
        return Intrinsics.areEqual((Object) this.f4221a, (Object) vVar.f4221a) && Intrinsics.areEqual((Object) this.f4222b, (Object) vVar.f4222b) && Intrinsics.areEqual((Object) this.f4223c, (Object) vVar.f4223c) && Intrinsics.areEqual((Object) this.f4224d, (Object) vVar.f4224d) && this.f4225e == vVar.f4225e && Intrinsics.areEqual((Object) this.f4226f, (Object) vVar.f4226f) && Intrinsics.areEqual((Object) this.f4227g, (Object) vVar.f4227g) && this.f4228h == vVar.f4228h && Intrinsics.areEqual((Object) this.f4229i, (Object) vVar.f4229i) && this.f4230j == vVar.f4230j && Intrinsics.areEqual((Object) this.f4231k, (Object) vVar.f4231k) && Intrinsics.areEqual((Object) this.f4232l, (Object) vVar.f4232l) && Intrinsics.areEqual((Object) this.f4233m, (Object) vVar.f4233m) && Intrinsics.areEqual((Object) this.f4234n, (Object) vVar.f4234n) && Intrinsics.areEqual((Object) this.f4235o, (Object) vVar.f4235o) && Intrinsics.areEqual((Object) this.f4236p, (Object) vVar.f4236p);
    }

    public int hashCode() {
        int j2 = androidx.compose.animation.core.a.j(this.f4226f, androidx.compose.animation.core.a.c(this.f4225e, androidx.compose.animation.core.a.i(this.f4224d, androidx.compose.animation.core.a.i(this.f4223c, androidx.compose.animation.core.a.i(this.f4222b, this.f4221a.hashCode() * 31, 31), 31), 31), 31), 31);
        String str = this.f4227g;
        int i3 = 0;
        int hashCode = (this.f4228h.hashCode() + ((j2 + (str == null ? 0 : str.hashCode())) * 31)) * 31;
        Set<String> set = this.f4229i;
        int hashCode2 = (hashCode + (set == null ? 0 : set.hashCode())) * 31;
        boolean z2 = this.f4230j;
        if (z2) {
            z2 = true;
        }
        int i4 = (hashCode2 + (z2 ? 1 : 0)) * 31;
        String str2 = this.f4231k;
        int hashCode3 = (i4 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Map<String, String> map = this.f4232l;
        int hashCode4 = (hashCode3 + (map == null ? 0 : map.hashCode())) * 31;
        String str3 = this.f4233m;
        int hashCode5 = (hashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
        MomentsUser momentsUser = this.f4234n;
        int hashCode6 = (hashCode5 + (momentsUser == null ? 0 : momentsUser.hashCode())) * 31;
        Integer num = this.f4235o;
        int hashCode7 = (hashCode6 + (num == null ? 0 : num.hashCode())) * 31;
        w wVar = this.f4236p;
        if (wVar != null) {
            i3 = wVar.hashCode();
        }
        return hashCode7 + i3;
    }

    @NotNull
    public String toString() {
        return "StorylyGroupItem(groupId=" + this.f4221a + ", title=" + this.f4222b + ", mediaHost=" + this.f4223c + ", iconImageUrl=" + this.f4224d + ", order=" + this.f4225e + ", stories=" + this.f4226f + ", coverImageUrl=" + this.f4227g + ", type=" + this.f4228h + ", segments=" + this.f4229i + ", pinned=" + this.f4230j + ", endDate=" + this.f4231k + ", thematicIcons=" + this.f4232l + ", momentsToken=" + this.f4233m + ", momentsUser=" + this.f4234n + ", maxGroupCount=" + this.f4235o + ", style=" + this.f4236p + ')';
    }
}
