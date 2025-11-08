package com.google.android.gms.internal.mlkit_vision_common;

import androidx.core.app.NotificationCompat;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzgp implements ObjectEncoder {
    static final zzgp zza = new zzgp();
    private static final FieldDescriptor zzb = a.q(1, FieldDescriptor.builder("deviceInfo"));
    private static final FieldDescriptor zzc = a.q(2, FieldDescriptor.builder("nnapiInfo"));
    private static final FieldDescriptor zzd = a.q(3, FieldDescriptor.builder("gpuInfo"));
    private static final FieldDescriptor zze = a.q(4, FieldDescriptor.builder("pipelineIdentifier"));
    private static final FieldDescriptor zzf = a.q(5, FieldDescriptor.builder("acceptedConfigurations"));
    private static final FieldDescriptor zzg = a.q(6, FieldDescriptor.builder("action"));
    private static final FieldDescriptor zzh = a.q(7, FieldDescriptor.builder(NotificationCompat.CATEGORY_STATUS));
    private static final FieldDescriptor zzi = a.q(8, FieldDescriptor.builder("customErrors"));
    private static final FieldDescriptor zzj = a.q(9, FieldDescriptor.builder("benchmarkStatus"));
    private static final FieldDescriptor zzk = a.q(10, FieldDescriptor.builder("validationTestResult"));
    private static final FieldDescriptor zzl = a.q(11, FieldDescriptor.builder("timestampUs"));
    private static final FieldDescriptor zzm = a.q(12, FieldDescriptor.builder("elapsedUs"));

    private zzgp() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzlo zzlo = (zzlo) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
