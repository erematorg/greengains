package com.google.android.gms.internal.mlkit_vision_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzeh implements ObjectEncoder {
    static final zzeh zza = new zzeh();
    private static final FieldDescriptor zzb = a.q(1, FieldDescriptor.builder("durationMs"));
    private static final FieldDescriptor zzc = a.q(2, FieldDescriptor.builder("errorCode"));
    private static final FieldDescriptor zzd = a.q(3, FieldDescriptor.builder("isColdCall"));
    private static final FieldDescriptor zze = a.q(4, FieldDescriptor.builder("autoManageModelOnBackground"));
    private static final FieldDescriptor zzf = a.q(5, FieldDescriptor.builder("autoManageModelOnLowMemory"));
    private static final FieldDescriptor zzg = a.q(6, FieldDescriptor.builder("isNnApiEnabled"));
    private static final FieldDescriptor zzh = a.q(7, FieldDescriptor.builder("eventsCount"));
    private static final FieldDescriptor zzi = a.q(8, FieldDescriptor.builder("otherErrors"));
    private static final FieldDescriptor zzj = a.q(9, FieldDescriptor.builder("remoteConfigValueForAcceleration"));
    private static final FieldDescriptor zzk = a.q(10, FieldDescriptor.builder("isAccelerated"));

    private zzeh() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzim zzim = (zzim) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
