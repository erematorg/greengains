package com.google.android.gms.internal.mlkit_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzfe implements ObjectEncoder {
    static final zzfe zza = new zzfe();

    static {
        a.j(4, a.f(3, a.f(2, a.f(1, FieldDescriptor.builder("supportedFormats"), "durationMs"), "errorCode"), "allowManualInput"));
    }

    private zzfe() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzlg zzlg = (zzlg) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
