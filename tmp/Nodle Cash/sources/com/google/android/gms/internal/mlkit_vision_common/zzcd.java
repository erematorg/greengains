package com.google.android.gms.internal.mlkit_vision_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzcd implements ObjectEncoder {
    static final zzcd zza = new zzcd();
    private static final FieldDescriptor zzb = a.q(1, FieldDescriptor.builder("errorCode"));
    private static final FieldDescriptor zzc = a.q(2, FieldDescriptor.builder("isColdCall"));
    private static final FieldDescriptor zzd = a.q(3, FieldDescriptor.builder("imageInfo"));
    private static final FieldDescriptor zze = a.q(4, FieldDescriptor.builder("detectorOptions"));
    private static final FieldDescriptor zzf = a.q(5, FieldDescriptor.builder("contourDetectedFaces"));
    private static final FieldDescriptor zzg = a.q(6, FieldDescriptor.builder("nonContourDetectedFaces"));

    private zzcd() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzay zzay = (zzay) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
