package com.google.android.gms.internal.mlkit_vision_barcode;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzkw implements ObjectEncoder {
    static final zzkw zza = new zzkw();

    static {
        a.p(7, a.n(6, a.n(5, a.n(4, a.n(3, a.n(2, a.n(1, FieldDescriptor.builder("options"), "roughDownloadDurationMs"), "errorCode"), "exactDownloadDurationMs"), "downloadStatus"), "downloadFailureStatus"), "mddDownloadErrorCodes"));
    }

    private zzkw() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzrg zzrg = (zzrg) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
