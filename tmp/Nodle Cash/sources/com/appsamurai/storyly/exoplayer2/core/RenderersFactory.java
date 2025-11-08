package com.appsamurai.storyly.exoplayer2.core;

import android.os.Handler;
import com.appsamurai.storyly.exoplayer2.core.audio.AudioRendererEventListener;
import com.appsamurai.storyly.exoplayer2.core.metadata.MetadataOutput;
import com.appsamurai.storyly.exoplayer2.core.text.TextOutput;
import com.appsamurai.storyly.exoplayer2.core.video.VideoRendererEventListener;

public interface RenderersFactory {
    Renderer[] createRenderers(Handler handler, VideoRendererEventListener videoRendererEventListener, AudioRendererEventListener audioRendererEventListener, TextOutput textOutput, MetadataOutput metadataOutput);
}
