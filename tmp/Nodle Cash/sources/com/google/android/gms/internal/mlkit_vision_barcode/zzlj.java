package com.google.android.gms.internal.mlkit_vision_barcode;

import androidx.core.app.NotificationCompat;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzlj implements ObjectEncoder {
    static final zzlj zza = new zzlj();

    static {
        a.p(3, a.n(2, a.n(1, FieldDescriptor.builder(NotificationCompat.CATEGORY_STATUS), "filename"), "lineNumber"));
    }

    private zzlj() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzrw zzrw = (zzrw) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
