package com.appsamurai.storyly.exoplayer2.core.drm;

import com.appsamurai.storyly.exoplayer2.core.drm.ExoMediaDrm;
import java.util.UUID;

public final /* synthetic */ class g implements ExoMediaDrm.Provider {
    public final ExoMediaDrm acquireExoMediaDrm(UUID uuid) {
        return FrameworkMediaDrm.lambda$static$0(uuid);
    }
}
