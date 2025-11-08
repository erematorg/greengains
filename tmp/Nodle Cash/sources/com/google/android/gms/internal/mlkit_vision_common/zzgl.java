package com.google.android.gms.internal.mlkit_vision_common;

import androidx.core.app.NotificationCompat;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzgl implements ObjectEncoder {
    static final zzgl zza = new zzgl();
    private static final FieldDescriptor zzb = a.q(1, FieldDescriptor.builder("inferenceCommonLogEvent"));
    private static final FieldDescriptor zzc = a.q(2, FieldDescriptor.builder("options"));
    private static final FieldDescriptor zzd = a.q(3, FieldDescriptor.builder("inputLength"));
    private static final FieldDescriptor zze = a.q(4, FieldDescriptor.builder("outputLength"));
    private static final FieldDescriptor zzf = a.q(5, FieldDescriptor.builder("loadDictionaryErrorCode"));
    private static final FieldDescriptor zzg = a.q(6, FieldDescriptor.builder("translateResultStatusCode"));
    private static final FieldDescriptor zzh = a.q(7, FieldDescriptor.builder(NotificationCompat.CATEGORY_STATUS));
    private static final FieldDescriptor zzi = a.q(8, FieldDescriptor.builder("downloadHttpResponseCode"));

    private zzgl() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzkt zzkt = (zzkt) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
