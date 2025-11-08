package com.appsamurai.storyly.exoplayer2.core.mediacodec;

import android.media.MediaCodec;
import com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecAdapter;

public final /* synthetic */ class a implements MediaCodec.OnFrameRenderedListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4512a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ MediaCodecAdapter.OnFrameRenderedListener f4513b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ MediaCodecAdapter f4514c;

    public /* synthetic */ a(MediaCodecAdapter mediaCodecAdapter, MediaCodecAdapter.OnFrameRenderedListener onFrameRenderedListener, int i3) {
        this.f4512a = i3;
        this.f4514c = mediaCodecAdapter;
        this.f4513b = onFrameRenderedListener;
    }

    public final void onFrameRendered(MediaCodec mediaCodec, long j2, long j3) {
        switch (this.f4512a) {
            case 0:
                ((AsynchronousMediaCodecAdapter) this.f4514c).lambda$setOnFrameRenderedListener$0(this.f4513b, mediaCodec, j2, j3);
                return;
            default:
                ((SynchronousMediaCodecAdapter) this.f4514c).lambda$setOnFrameRenderedListener$0(this.f4513b, mediaCodec, j2, j3);
                return;
        }
    }
}
