package com.appsamurai.storyly.exoplayer2.core.source.chunk;

import android.content.Context;
import android.net.Uri;
import android.os.Parcel;
import android.text.Editable;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.util.TimestampAdjuster;
import com.appsamurai.storyly.exoplayer2.core.analytics.PlayerId;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.ChunkExtractor;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSpec;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.cache.CacheKeyFactory;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.DefaultExtractorsFactory;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.mp3.Mp3Extractor;
import com.appsamurai.storyly.exoplayer2.extractor.metadata.id3.Id3Decoder;
import com.appsamurai.storyly.exoplayer2.hls.HlsExtractorFactory;
import com.appsamurai.storyly.exoplayer2.hls.HlsMediaChunkExtractor;
import com.appsamurai.storyly.exoplayer2.hls.MediaParserHlsMediaChunkExtractor;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.mlkit_common.zzay;
import com.google.android.gms.internal.mlkit_vision_barcode.zzfa;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdn;
import com.google.android.gms.internal.mlkit_vision_common.zzae;
import com.google.android.material.carousel.MaskableFrameLayout;
import com.google.android.material.shape.CornerSize;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.recaptcha.internal.zzhh;
import com.google.crypto.tink.shaded.protobuf.CodedOutputStream;
import com.google.firebase.FirebaseCommonRegistrar;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public final /* synthetic */ class a implements ChunkExtractor.Factory, CacheKeyFactory, DefaultExtractorsFactory.ExtensionLoader.ConstructorSupplier, Id3Decoder.FramePredicate, HlsExtractorFactory, ShapeAppearanceModel.CornerSizeUnaryOperator, TextInputLayout.LengthCounter, LibraryVersionComponent.VersionExtractor {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4556a;

    public /* synthetic */ a(int i3) {
        this.f4556a = i3;
    }

    public static int b(int i3, int i4, int i5) {
        return zzdn.zzA(i3) + i4 + i5;
    }

    public static int c(int i3, int i4, int i5, int i6) {
        return (i5 - (i3 * i4)) * i6;
    }

    public static long d(long j2, long j3, long j4, long j5) {
        return ((j2 * j3) + j4) * j5;
    }

    public static IObjectWrapper e(Parcel parcel) {
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
        parcel.recycle();
        return asInterface;
    }

    public static FieldDescriptor.Builder f(int i3, FieldDescriptor.Builder builder, String str) {
        zzay zzay = new zzay();
        zzay.zza(i3);
        builder.withProperty(zzay.zzb()).build();
        return FieldDescriptor.builder(str);
    }

    public static FieldDescriptor g(int i3, FieldDescriptor.Builder builder) {
        zzay zzay = new zzay();
        zzay.zza(i3);
        return builder.withProperty(zzay.zzb()).build();
    }

    public static String h(IOException iOException, StringBuilder sb) {
        sb.append(iOException.getMessage());
        return sb.toString();
    }

    public static StringBuilder i(long j2, String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder(str);
        sb.append(str2);
        sb.append(str3);
        sb.append(j2);
        return sb;
    }

    public static void j(int i3, FieldDescriptor.Builder builder) {
        zzay zzay = new zzay();
        zzay.zza(i3);
        builder.withProperty(zzay.zzb()).build();
    }

    public static void k(HashSet hashSet, String str, String str2, String str3, String str4) {
        hashSet.add(str);
        hashSet.add(str2);
        hashSet.add(str3);
        hashSet.add(str4);
    }

    public static int l(int i3, int i4, int i5) {
        return zzhh.zzy(i3) + i4 + i5;
    }

    public static int m(int i3, int i4, int i5, int i6) {
        return CodedOutputStream.computeUInt32SizeNoTag(i3) + i4 + i5 + i6;
    }

    public static FieldDescriptor.Builder n(int i3, FieldDescriptor.Builder builder, String str) {
        zzfa zzfa = new zzfa();
        zzfa.zza(i3);
        builder.withProperty(zzfa.zzb()).build();
        return FieldDescriptor.builder(str);
    }

    public static FieldDescriptor o(int i3, FieldDescriptor.Builder builder) {
        zzfa zzfa = new zzfa();
        zzfa.zza(i3);
        return builder.withProperty(zzfa.zzb()).build();
    }

    public static void p(int i3, FieldDescriptor.Builder builder) {
        zzfa zzfa = new zzfa();
        zzfa.zza(i3);
        builder.withProperty(zzfa.zzb()).build();
    }

    public static FieldDescriptor q(int i3, FieldDescriptor.Builder builder) {
        zzae zzae = new zzae();
        zzae.zza(i3);
        return builder.withProperty(zzae.zzb()).build();
    }

    public CornerSize apply(CornerSize cornerSize) {
        return MaskableFrameLayout.lambda$setShapeAppearanceModel$0(cornerSize);
    }

    public String buildCacheKey(DataSpec dataSpec) {
        return CacheKeyFactory.lambda$static$0(dataSpec);
    }

    public int countLength(Editable editable) {
        return TextInputLayout.lambda$new$0(editable);
    }

    public HlsMediaChunkExtractor createExtractor(Uri uri, Format format, List list, TimestampAdjuster timestampAdjuster, Map map, ExtractorInput extractorInput, PlayerId playerId) {
        return MediaParserHlsMediaChunkExtractor.lambda$static$0(uri, format, list, timestampAdjuster, map, extractorInput, playerId);
    }

    public ChunkExtractor createProgressiveMediaExtractor(int i3, Format format, boolean z2, List list, TrackOutput trackOutput, PlayerId playerId) {
        switch (this.f4556a) {
            case 0:
                return BundledChunkExtractor.lambda$static$0(i3, format, z2, list, trackOutput, playerId);
            default:
                return MediaParserChunkExtractor.lambda$static$0(i3, format, z2, list, trackOutput, playerId);
        }
    }

    public boolean evaluate(int i3, int i4, int i5, int i6, int i7) {
        switch (this.f4556a) {
            case 5:
                return Mp3Extractor.lambda$static$1(i3, i4, i5, i6, i7);
            default:
                return Id3Decoder.lambda$static$0(i3, i4, i5, i6, i7);
        }
    }

    public String extract(Object obj) {
        Context context = (Context) obj;
        switch (this.f4556a) {
            case 26:
                return FirebaseCommonRegistrar.lambda$getComponents$0(context);
            case 27:
                return FirebaseCommonRegistrar.lambda$getComponents$1(context);
            case 28:
                return FirebaseCommonRegistrar.lambda$getComponents$2(context);
            default:
                return FirebaseCommonRegistrar.lambda$getComponents$3(context);
        }
    }

    public Constructor getConstructor() {
        switch (this.f4556a) {
            case 3:
                return DefaultExtractorsFactory.getFlacExtractorConstructor();
            default:
                return DefaultExtractorsFactory.getMidiExtractorConstructor();
        }
    }
}
