package com.google.android.gms.internal.mlkit_vision_barcode;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzhl implements ObjectEncoder {
    static final zzhl zza = new zzhl();
    private static final FieldDescriptor zzb = a.o(1, FieldDescriptor.builder("errorCode"));
    private static final FieldDescriptor zzc = a.o(2, FieldDescriptor.builder("hasResult"));
    private static final FieldDescriptor zzd = a.o(3, FieldDescriptor.builder("isColdCall"));
    private static final FieldDescriptor zze = a.o(4, FieldDescriptor.builder("imageInfo"));
    private static final FieldDescriptor zzf = a.o(5, FieldDescriptor.builder("options"));
    private static final FieldDescriptor zzg = a.o(6, FieldDescriptor.builder("detectedBarcodeFormats"));
    private static final FieldDescriptor zzh = a.o(7, FieldDescriptor.builder("detectedBarcodeValueTypes"));

    private zzhl() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzft zzft = (zzft) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(zzb, (Object) zzft.zzc());
        objectEncoderContext.add(zzc, (Object) null);
        objectEncoderContext.add(zzd, (Object) zzft.zze());
        objectEncoderContext.add(zze, (Object) null);
        objectEncoderContext.add(zzf, (Object) zzft.zzd());
        objectEncoderContext.add(zzg, (Object) zzft.zza());
        objectEncoderContext.add(zzh, (Object) zzft.zzb());
    }
}
