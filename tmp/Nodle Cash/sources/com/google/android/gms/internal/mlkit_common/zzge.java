package com.google.android.gms.internal.mlkit_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzge implements ObjectEncoder {
    static final zzge zza = new zzge();

    static {
        a.j(10, a.f(9, a.f(8, a.f(7, a.f(6, a.f(5, a.f(4, a.f(3, a.f(2, a.f(1, FieldDescriptor.builder("durationMs"), "errorCode"), "isColdCall"), "autoManageModelOnBackground"), "autoManageModelOnLowMemory"), "isNnApiEnabled"), "eventsCount"), "otherErrors"), "remoteConfigValueForAcceleration"), "isAccelerated"));
    }

    private zzge() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzmf zzmf = (zzmf) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
