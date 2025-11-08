package com.google.android.gms.internal.mlkit_vision_barcode;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzkg implements ObjectEncoder {
    static final zzkg zza = new zzkg();
    private static final FieldDescriptor zzb = a.o(1, FieldDescriptor.builder("imageFormat"));
    private static final FieldDescriptor zzc = a.o(2, FieldDescriptor.builder("originalImageSize"));
    private static final FieldDescriptor zzd = a.o(3, FieldDescriptor.builder("compressedImageSize"));
    private static final FieldDescriptor zze = a.o(4, FieldDescriptor.builder("isOdmlImage"));

    private zzkg() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzqk zzqk = (zzqk) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(zzb, (Object) zzqk.zza());
        objectEncoderContext.add(zzc, (Object) zzqk.zzb());
        objectEncoderContext.add(zzd, (Object) null);
        objectEncoderContext.add(zze, (Object) null);
    }
}
