package com.appsamurai.storyly;

import androidx.annotation.Keep;
import androidx.autofill.HintConstants;
import com.amplitude.api.AmplitudeClient;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
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
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0006\b\b\u0018\u0000 &2\u00020\u0001:\u0002'(B+\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b \u0010!B?\b\u0017\u0012\u0006\u0010\"\u001a\u00020\u0012\u0012\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\t\u0012\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\t\u0012\n\b\u0001\u0010\u000f\u001a\u0004\u0018\u00010\t\u0012\b\u0010$\u001a\u0004\u0018\u00010#¢\u0006\u0004\b \u0010%J!\u0010\b\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005HÇ\u0001J\u000b\u0010\n\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\tHÆ\u0003J-\u0010\u0010\u001a\u00020\u00002\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\tHÆ\u0001J\t\u0010\u0011\u001a\u00020\tHÖ\u0001J\t\u0010\u0013\u001a\u00020\u0012HÖ\u0001J\u0013\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003R$\u0010\r\u001a\u0004\u0018\u00010\t8\u0006@\u0007X\u0004¢\u0006\u0012\n\u0004\b\r\u0010\u0017\u0012\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u0018\u0010\u0019R$\u0010\u000e\u001a\u0004\u0018\u00010\t8\u0006@\u0007X\u0004¢\u0006\u0012\n\u0004\b\u000e\u0010\u0017\u0012\u0004\b\u001d\u0010\u001b\u001a\u0004\b\u001c\u0010\u0019R$\u0010\u000f\u001a\u0004\u0018\u00010\t8\u0006@\u0007X\u0004¢\u0006\u0012\n\u0004\b\u000f\u0010\u0017\u0012\u0004\b\u001f\u0010\u001b\u001a\u0004\b\u001e\u0010\u0019¨\u0006)"}, d2 = {"Lcom/appsamurai/storyly/MomentsUser;", "", "self", "Lkotlinx/serialization/encoding/CompositeEncoder;", "output", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "serialDesc", "", "write$Self", "", "component1", "component2", "component3", "userId", "avatarURL", "username", "copy", "toString", "", "hashCode", "other", "", "equals", "Ljava/lang/String;", "getUserId", "()Ljava/lang/String;", "getUserId$annotations", "()V", "getAvatarURL", "getAvatarURL$annotations", "getUsername", "getUsername$annotations", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "seen1", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "serializationConstructorMarker", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "Companion", "a", "b", "storyly_release"}, k = 1, mv = {1, 5, 1})
@Serializable
public final class MomentsUser {
    @NotNull
    public static final b Companion = new b();
    @Nullable
    private final String avatarURL;
    @Nullable
    private final String userId;
    @Nullable
    private final String username;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final class a implements GeneratedSerializer<MomentsUser> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public static final a f3407a;

        /* renamed from: b  reason: collision with root package name */
        public static final /* synthetic */ SerialDescriptor f3408b;

        static {
            a aVar = new a();
            f3407a = aVar;
            PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.appsamurai.storyly.MomentsUser", aVar, 3);
            pluginGeneratedSerialDescriptor.addElement(AmplitudeClient.USER_ID_KEY, true);
            pluginGeneratedSerialDescriptor.addElement("avatar_url", true);
            pluginGeneratedSerialDescriptor.addElement(HintConstants.AUTOFILL_HINT_USERNAME, true);
            f3408b = pluginGeneratedSerialDescriptor;
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
            Object obj4;
            Intrinsics.checkNotNullParameter(decoder, "decoder");
            SerialDescriptor serialDescriptor = f3408b;
            CompositeDecoder beginStructure = decoder.beginStructure(serialDescriptor);
            Object obj5 = null;
            if (beginStructure.decodeSequentially()) {
                StringSerializer stringSerializer = StringSerializer.INSTANCE;
                obj2 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 0, stringSerializer, null);
                Object decodeNullableSerializableElement = beginStructure.decodeNullableSerializableElement(serialDescriptor, 1, stringSerializer, null);
                obj3 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 2, stringSerializer, null);
                obj = decodeNullableSerializableElement;
                i3 = 7;
            } else {
                boolean z2 = true;
                int i4 = 0;
                Object obj6 = null;
                Object obj7 = null;
                while (z2) {
                    int decodeElementIndex = beginStructure.decodeElementIndex(serialDescriptor);
                    if (decodeElementIndex == -1) {
                        z2 = false;
                        obj4 = obj6;
                    } else if (decodeElementIndex == 0) {
                        obj7 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 0, StringSerializer.INSTANCE, obj7);
                        i4 |= 1;
                        obj4 = obj6;
                    } else if (decodeElementIndex == 1) {
                        i4 |= 2;
                        obj4 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 1, StringSerializer.INSTANCE, obj6);
                    } else if (decodeElementIndex == 2) {
                        obj5 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 2, StringSerializer.INSTANCE, obj5);
                        i4 |= 4;
                        obj4 = obj6;
                    } else {
                        throw new UnknownFieldException(decodeElementIndex);
                    }
                    obj6 = obj4;
                }
                i3 = i4;
                obj3 = obj5;
                obj2 = obj7;
                obj = obj6;
            }
            beginStructure.endStructure(serialDescriptor);
            return new MomentsUser(i3, (String) obj2, (String) obj, (String) obj3, (SerializationConstructorMarker) null);
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return f3408b;
        }

        public void serialize(Encoder encoder, Object obj) {
            MomentsUser momentsUser = (MomentsUser) obj;
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter(momentsUser, "value");
            SerialDescriptor serialDescriptor = f3408b;
            CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
            MomentsUser.write$Self(momentsUser, beginStructure, serialDescriptor);
            beginStructure.endStructure(serialDescriptor);
        }

        @NotNull
        public KSerializer<?>[] typeParametersSerializers() {
            return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
        }
    }

    public static final class b {
    }

    public MomentsUser() {
        this((String) null, (String) null, (String) null, 7, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ MomentsUser copy$default(MomentsUser momentsUser, String str, String str2, String str3, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = momentsUser.userId;
        }
        if ((i3 & 2) != 0) {
            str2 = momentsUser.avatarURL;
        }
        if ((i3 & 4) != 0) {
            str3 = momentsUser.username;
        }
        return momentsUser.copy(str, str2, str3);
    }

    @SerialName("avatar_url")
    public static /* synthetic */ void getAvatarURL$annotations() {
    }

    @SerialName("user_id")
    public static /* synthetic */ void getUserId$annotations() {
    }

    @SerialName("username")
    public static /* synthetic */ void getUsername$annotations() {
    }

    @JvmStatic
    public static final void write$Self(@NotNull MomentsUser momentsUser, @NotNull CompositeEncoder compositeEncoder, @NotNull SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(momentsUser, "self");
        Intrinsics.checkNotNullParameter(compositeEncoder, "output");
        Intrinsics.checkNotNullParameter(serialDescriptor, "serialDesc");
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 0) || momentsUser.userId != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 0, StringSerializer.INSTANCE, momentsUser.userId);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 1) || momentsUser.avatarURL != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 1, StringSerializer.INSTANCE, momentsUser.avatarURL);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 2) || momentsUser.username != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 2, StringSerializer.INSTANCE, momentsUser.username);
        }
    }

    @Nullable
    public final String component1() {
        return this.userId;
    }

    @Nullable
    public final String component2() {
        return this.avatarURL;
    }

    @Nullable
    public final String component3() {
        return this.username;
    }

    @NotNull
    public final MomentsUser copy(@Nullable String str, @Nullable String str2, @Nullable String str3) {
        return new MomentsUser(str, str2, str3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MomentsUser)) {
            return false;
        }
        MomentsUser momentsUser = (MomentsUser) obj;
        return Intrinsics.areEqual((Object) this.userId, (Object) momentsUser.userId) && Intrinsics.areEqual((Object) this.avatarURL, (Object) momentsUser.avatarURL) && Intrinsics.areEqual((Object) this.username, (Object) momentsUser.username);
    }

    @Nullable
    public final String getAvatarURL() {
        return this.avatarURL;
    }

    @Nullable
    public final String getUserId() {
        return this.userId;
    }

    @Nullable
    public final String getUsername() {
        return this.username;
    }

    public int hashCode() {
        String str = this.userId;
        int i3 = 0;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.avatarURL;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.username;
        if (str3 != null) {
            i3 = str3.hashCode();
        }
        return hashCode2 + i3;
    }

    @NotNull
    public String toString() {
        return "MomentsUser(userId=" + this.userId + ", avatarURL=" + this.avatarURL + ", username=" + this.username + ')';
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ MomentsUser(int i3, @SerialName("user_id") String str, @SerialName("avatar_url") String str2, @SerialName("username") String str3, SerializationConstructorMarker serializationConstructorMarker) {
        if ((i3 & 1) == 0) {
            this.userId = null;
        } else {
            this.userId = str;
        }
        if ((i3 & 2) == 0) {
            this.avatarURL = null;
        } else {
            this.avatarURL = str2;
        }
        if ((i3 & 4) == 0) {
            this.username = null;
        } else {
            this.username = str3;
        }
    }

    public MomentsUser(@Nullable String str, @Nullable String str2, @Nullable String str3) {
        this.userId = str;
        this.avatarURL = str2;
        this.username = str3;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ MomentsUser(String str, String str2, String str3, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? null : str, (i3 & 2) != 0 ? null : str2, (i3 & 4) != 0 ? null : str3);
    }
}
