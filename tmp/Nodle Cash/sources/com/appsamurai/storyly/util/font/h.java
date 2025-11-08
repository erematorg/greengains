package com.appsamurai.storyly.util.font;

import android.graphics.Typeface;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable
public final class h {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f6331a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    public Typeface f6332b;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final class a implements GeneratedSerializer<h> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public static final a f6333a;

        /* renamed from: b  reason: collision with root package name */
        public static final /* synthetic */ SerialDescriptor f6334b;

        static {
            a aVar = new a();
            f6333a = aVar;
            PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.appsamurai.storyly.util.font.STRFont", aVar, 1);
            pluginGeneratedSerialDescriptor.addElement("url", true);
            f6334b = pluginGeneratedSerialDescriptor;
        }

        @NotNull
        public KSerializer<?>[] childSerializers() {
            return new KSerializer[]{StringSerializer.INSTANCE};
        }

        public Object deserialize(Decoder decoder) {
            String str;
            Intrinsics.checkNotNullParameter(decoder, "decoder");
            SerialDescriptor serialDescriptor = f6334b;
            CompositeDecoder beginStructure = decoder.beginStructure(serialDescriptor);
            int i3 = 1;
            if (beginStructure.decodeSequentially()) {
                str = beginStructure.decodeStringElement(serialDescriptor, 0);
            } else {
                str = null;
                boolean z2 = true;
                int i4 = 0;
                while (z2) {
                    int decodeElementIndex = beginStructure.decodeElementIndex(serialDescriptor);
                    if (decodeElementIndex == -1) {
                        z2 = false;
                    } else if (decodeElementIndex == 0) {
                        str = beginStructure.decodeStringElement(serialDescriptor, 0);
                        i4 = 1;
                    } else {
                        throw new UnknownFieldException(decodeElementIndex);
                    }
                }
                i3 = i4;
            }
            beginStructure.endStructure(serialDescriptor);
            return new h(i3, str);
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return f6334b;
        }

        public void serialize(Encoder encoder, Object obj) {
            h hVar = (h) obj;
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter(hVar, "value");
            SerialDescriptor serialDescriptor = f6334b;
            CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
            Intrinsics.checkNotNullParameter(hVar, "self");
            Intrinsics.checkNotNullParameter(beginStructure, "output");
            Intrinsics.checkNotNullParameter(serialDescriptor, "serialDesc");
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 0) || !Intrinsics.areEqual((Object) hVar.f6331a, (Object) "http://fonts.gstatic.com/s/inter/v12/UcCO3FwrK3iLTeHuS_fvQtMwCp50KnMw2boKoduKmMEVuLyfMZhrib2Bg-4.ttf")) {
                beginStructure.encodeStringElement(serialDescriptor, 0, hVar.f6331a);
            }
            beginStructure.endStructure(serialDescriptor);
        }

        @NotNull
        public KSerializer<?>[] typeParametersSerializers() {
            return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
        }
    }

    public h() {
        this((String) null, (Typeface) null, 3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof h)) {
            return false;
        }
        h hVar = (h) obj;
        return Intrinsics.areEqual((Object) this.f6331a, (Object) hVar.f6331a) && Intrinsics.areEqual((Object) this.f6332b, (Object) hVar.f6332b);
    }

    public int hashCode() {
        int hashCode = this.f6331a.hashCode() * 31;
        Typeface typeface = this.f6332b;
        return hashCode + (typeface == null ? 0 : typeface.hashCode());
    }

    @NotNull
    public String toString() {
        return "STRFont(url=" + this.f6331a + ", typeface=" + this.f6332b + ')';
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ h(int i3, @SerialName("url") String str) {
        if ((i3 & 1) == 0) {
            this.f6331a = "http://fonts.gstatic.com/s/inter/v12/UcCO3FwrK3iLTeHuS_fvQtMwCp50KnMw2boKoduKmMEVuLyfMZhrib2Bg-4.ttf";
        } else {
            this.f6331a = str;
        }
        this.f6332b = null;
    }

    public h(@NotNull String str, @Nullable Typeface typeface) {
        Intrinsics.checkNotNullParameter(str, "url");
        this.f6331a = str;
        this.f6332b = typeface;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ h(String str, Typeface typeface, int i3) {
        this((i3 & 1) != 0 ? "http://fonts.gstatic.com/s/inter/v12/UcCO3FwrK3iLTeHuS_fvQtMwCp50KnMw2boKoduKmMEVuLyfMZhrib2Bg-4.ttf" : null, (i3 & 2) != 0 ? null : typeface);
    }
}
