package com.google.android.gms.internal.mlkit_vision_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.io.IOException;

final class zzdt implements ObjectEncoder {
    static final zzdt zza = new zzdt();
    private static final FieldDescriptor zzb = a.q(1, FieldDescriptor.builder(RemoteConfigConstants.RequestFieldKey.SDK_VERSION));
    private static final FieldDescriptor zzc = a.q(2, FieldDescriptor.builder("osBuild"));
    private static final FieldDescriptor zzd = a.q(3, FieldDescriptor.builder("brand"));
    private static final FieldDescriptor zze = a.q(4, FieldDescriptor.builder("device"));
    private static final FieldDescriptor zzf = a.q(5, FieldDescriptor.builder("hardware"));
    private static final FieldDescriptor zzg = a.q(6, FieldDescriptor.builder("manufacturer"));
    private static final FieldDescriptor zzh = a.q(7, FieldDescriptor.builder("model"));
    private static final FieldDescriptor zzi = a.q(8, FieldDescriptor.builder("product"));
    private static final FieldDescriptor zzj = a.q(9, FieldDescriptor.builder("soc"));
    private static final FieldDescriptor zzk = a.q(10, FieldDescriptor.builder("socMetaBuildId"));

    private zzdt() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzlh zzlh = (zzlh) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
