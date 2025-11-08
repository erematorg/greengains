package com.google.android.gms.internal.mlkit_vision_barcode;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzlf implements ObjectEncoder {
    static final zzlf zza = new zzlf();
    private static final FieldDescriptor zzb = a.o(1, FieldDescriptor.builder("inferenceCommonLogEvent"));
    private static final FieldDescriptor zzc = a.o(2, FieldDescriptor.builder("options"));
    private static final FieldDescriptor zzd = a.o(3, FieldDescriptor.builder("detectedBarcodeFormats"));
    private static final FieldDescriptor zze = a.o(4, FieldDescriptor.builder("detectedBarcodeValueTypes"));
    private static final FieldDescriptor zzf = a.o(5, FieldDescriptor.builder("imageInfo"));

    private zzlf() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzrr zzrr = (zzrr) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(zzb, (Object) zzrr.zzd());
        objectEncoderContext.add(zzc, (Object) zzrr.zze());
        objectEncoderContext.add(zzd, (Object) zzrr.zza());
        objectEncoderContext.add(zze, (Object) zzrr.zzb());
        objectEncoderContext.add(zzf, (Object) zzrr.zzc());
    }
}
