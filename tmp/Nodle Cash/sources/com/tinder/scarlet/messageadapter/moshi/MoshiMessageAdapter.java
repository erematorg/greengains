package com.tinder.scarlet.messageadapter.moshi;

import androidx.camera.core.impl.i;
import com.reown.android.push.notifications.PushMessagingService;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonQualifier;
import com.squareup.moshi.Moshi;
import com.tinder.scarlet.Message;
import com.tinder.scarlet.MessageAdapter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \r*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0002\r\u000eB\u0015\b\u0002\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J\u0015\u0010\u0006\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020\bH\u0016¢\u0006\u0002\u0010\tJ\u0015\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\fR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/tinder/scarlet/messageadapter/moshi/MoshiMessageAdapter;", "T", "Lcom/tinder/scarlet/MessageAdapter;", "jsonAdapter", "Lcom/squareup/moshi/JsonAdapter;", "(Lcom/squareup/moshi/JsonAdapter;)V", "fromMessage", "message", "Lcom/tinder/scarlet/Message;", "(Lcom/tinder/scarlet/Message;)Ljava/lang/Object;", "toMessage", "data", "(Ljava/lang/Object;)Lcom/tinder/scarlet/Message;", "Companion", "Factory", "scarlet-message-adapter-moshi"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class MoshiMessageAdapter<T> implements MessageAdapter<T> {
    @NotNull
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final Moshi DEFAULT_MOSHI = new Moshi.Builder().build();
    private static final ByteString UTF8_BOM = ByteString.decodeHex("EFBBBF");
    @NotNull
    private final JsonAdapter<T> jsonAdapter;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \u0005*\u0004\u0018\u00010\u00070\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/tinder/scarlet/messageadapter/moshi/MoshiMessageAdapter$Companion;", "", "()V", "DEFAULT_MOSHI", "Lcom/squareup/moshi/Moshi;", "kotlin.jvm.PlatformType", "UTF8_BOM", "Lokio/ByteString;", "scarlet-message-adapter-moshi"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    @SourceDebugExtension({"SMAP\nMoshiMessageAdapter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MoshiMessageAdapter.kt\ncom/tinder/scarlet/messageadapter/moshi/MoshiMessageAdapter$Factory\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,93:1\n3792#2:94\n4307#2,2:95\n*S KotlinDebug\n*F\n+ 1 MoshiMessageAdapter.kt\ncom/tinder/scarlet/messageadapter/moshi/MoshiMessageAdapter$Factory\n*L\n70#1:94\n70#1:95,2\n*E\n"})
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u001b\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0012B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J'\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0016¢\u0006\u0002\u0010\u000eJ!\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\r0\u00102\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002¢\u0006\u0002\u0010\u0011R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/tinder/scarlet/messageadapter/moshi/MoshiMessageAdapter$Factory;", "Lcom/tinder/scarlet/MessageAdapter$Factory;", "moshi", "Lcom/squareup/moshi/Moshi;", "config", "Lcom/tinder/scarlet/messageadapter/moshi/MoshiMessageAdapter$Factory$Config;", "(Lcom/squareup/moshi/Moshi;Lcom/tinder/scarlet/messageadapter/moshi/MoshiMessageAdapter$Factory$Config;)V", "create", "Lcom/tinder/scarlet/MessageAdapter;", "type", "Ljava/lang/reflect/Type;", "annotations", "", "", "(Ljava/lang/reflect/Type;[Ljava/lang/annotation/Annotation;)Lcom/tinder/scarlet/MessageAdapter;", "filterJsonAnnotations", "", "([Ljava/lang/annotation/Annotation;)Ljava/util/Set;", "Config", "scarlet-message-adapter-moshi"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Factory implements MessageAdapter.Factory {
        @NotNull
        private final Config config;
        @NotNull
        private final Moshi moshi;

        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00032\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/tinder/scarlet/messageadapter/moshi/MoshiMessageAdapter$Factory$Config;", "", "lenient", "", "serializeNull", "failOnUnknown", "(ZZZ)V", "getFailOnUnknown", "()Z", "getLenient", "getSerializeNull", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "scarlet-message-adapter-moshi"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static final class Config {
            private final boolean failOnUnknown;
            private final boolean lenient;
            private final boolean serializeNull;

            public Config() {
                this(false, false, false, 7, (DefaultConstructorMarker) null);
            }

            public static /* synthetic */ Config copy$default(Config config, boolean z2, boolean z3, boolean z4, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    z2 = config.lenient;
                }
                if ((i3 & 2) != 0) {
                    z3 = config.serializeNull;
                }
                if ((i3 & 4) != 0) {
                    z4 = config.failOnUnknown;
                }
                return config.copy(z2, z3, z4);
            }

            public final boolean component1() {
                return this.lenient;
            }

            public final boolean component2() {
                return this.serializeNull;
            }

            public final boolean component3() {
                return this.failOnUnknown;
            }

            @NotNull
            public final Config copy(boolean z2, boolean z3, boolean z4) {
                return new Config(z2, z3, z4);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Config)) {
                    return false;
                }
                Config config = (Config) obj;
                return this.lenient == config.lenient && this.serializeNull == config.serializeNull && this.failOnUnknown == config.failOnUnknown;
            }

            public final boolean getFailOnUnknown() {
                return this.failOnUnknown;
            }

            public final boolean getLenient() {
                return this.lenient;
            }

            public final boolean getSerializeNull() {
                return this.serializeNull;
            }

            public int hashCode() {
                boolean z2 = this.lenient;
                boolean z3 = true;
                if (z2) {
                    z2 = true;
                }
                int i3 = (z2 ? 1 : 0) * true;
                boolean z4 = this.serializeNull;
                if (z4) {
                    z4 = true;
                }
                int i4 = (i3 + (z4 ? 1 : 0)) * 31;
                boolean z5 = this.failOnUnknown;
                if (!z5) {
                    z3 = z5;
                }
                return i4 + (z3 ? 1 : 0);
            }

            @NotNull
            public String toString() {
                StringBuilder sb = new StringBuilder("Config(lenient=");
                sb.append(this.lenient);
                sb.append(", serializeNull=");
                sb.append(this.serializeNull);
                sb.append(", failOnUnknown=");
                return i.c(sb, this.failOnUnknown, ')');
            }

            public Config(boolean z2, boolean z3, boolean z4) {
                this.lenient = z2;
                this.serializeNull = z3;
                this.failOnUnknown = z4;
            }

            /* JADX INFO: this call moved to the top of the method (can break code semantics) */
            public /* synthetic */ Config(boolean z2, boolean z3, boolean z4, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                this((i3 & 1) != 0 ? false : z2, (i3 & 2) != 0 ? false : z3, (i3 & 4) != 0 ? false : z4);
            }
        }

        public Factory() {
            this((Moshi) null, (Config) null, 3, (DefaultConstructorMarker) null);
        }

        private final Set<Annotation> filterJsonAnnotations(Annotation[] annotationArr) {
            ArrayList arrayList = new ArrayList();
            for (Annotation annotation : annotationArr) {
                if (JvmClassMappingKt.getJavaClass(JvmClassMappingKt.getAnnotationClass(annotation)).isAnnotationPresent(JsonQualifier.class)) {
                    arrayList.add(annotation);
                }
            }
            return CollectionsKt.toSet(arrayList);
        }

        @NotNull
        public MessageAdapter<?> create(@NotNull Type type, @NotNull Annotation[] annotationArr) {
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(annotationArr, "annotations");
            JsonAdapter<T> adapter = this.moshi.adapter(type, (Set<? extends Annotation>) filterJsonAnnotations(annotationArr));
            Config config2 = this.config;
            if (config2.getLenient()) {
                adapter = adapter.lenient();
            }
            if (config2.getSerializeNull()) {
                adapter = adapter.serializeNulls();
            }
            if (config2.getFailOnUnknown()) {
                adapter = adapter.failOnUnknown();
            }
            Intrinsics.checkNotNullExpressionValue(adapter, "adapter");
            return new MoshiMessageAdapter(adapter, (DefaultConstructorMarker) null);
        }

        public Factory(@NotNull Moshi moshi2, @NotNull Config config2) {
            Intrinsics.checkNotNullParameter(moshi2, "moshi");
            Intrinsics.checkNotNullParameter(config2, "config");
            this.moshi = moshi2;
            this.config = config2;
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public /* synthetic */ Factory(com.squareup.moshi.Moshi r7, com.tinder.scarlet.messageadapter.moshi.MoshiMessageAdapter.Factory.Config r8, int r9, kotlin.jvm.internal.DefaultConstructorMarker r10) {
            /*
                r6 = this;
                r10 = r9 & 1
                if (r10 == 0) goto L_0x000d
                com.squareup.moshi.Moshi r7 = com.tinder.scarlet.messageadapter.moshi.MoshiMessageAdapter.DEFAULT_MOSHI
                java.lang.String r10 = "DEFAULT_MOSHI"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r10)
            L_0x000d:
                r9 = r9 & 2
                if (r9 == 0) goto L_0x001c
                com.tinder.scarlet.messageadapter.moshi.MoshiMessageAdapter$Factory$Config r8 = new com.tinder.scarlet.messageadapter.moshi.MoshiMessageAdapter$Factory$Config
                r4 = 7
                r5 = 0
                r1 = 0
                r2 = 0
                r3 = 0
                r0 = r8
                r0.<init>(r1, r2, r3, r4, r5)
            L_0x001c:
                r6.<init>(r7, r8)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tinder.scarlet.messageadapter.moshi.MoshiMessageAdapter.Factory.<init>(com.squareup.moshi.Moshi, com.tinder.scarlet.messageadapter.moshi.MoshiMessageAdapter$Factory$Config, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
        }
    }

    public /* synthetic */ MoshiMessageAdapter(JsonAdapter jsonAdapter2, DefaultConstructorMarker defaultConstructorMarker) {
        this(jsonAdapter2);
    }

    public T fromMessage(@NotNull Message message) {
        String str;
        Intrinsics.checkNotNullParameter(message, PushMessagingService.KEY_MESSAGE);
        if (message instanceof Message.Text) {
            str = ((Message.Text) message).getValue();
        } else if (message instanceof Message.Bytes) {
            Message.Bytes bytes = (Message.Bytes) message;
            ByteString of = ByteString.of(bytes.getValue(), 0, bytes.getValue().length);
            ByteString byteString = UTF8_BOM;
            str = of.startsWith(byteString) ? of.substring(byteString.size()).utf8() : of.utf8();
        } else {
            throw new NoWhenBranchMatchedException();
        }
        T fromJson = this.jsonAdapter.fromJson(str);
        Intrinsics.checkNotNull(fromJson);
        return fromJson;
    }

    @NotNull
    public Message toMessage(T t2) {
        String json = this.jsonAdapter.toJson(t2);
        Intrinsics.checkNotNullExpressionValue(json, "stringValue");
        return new Message.Text(json);
    }

    private MoshiMessageAdapter(JsonAdapter<T> jsonAdapter2) {
        this.jsonAdapter = jsonAdapter2;
    }
}
