package com.appsamurai.storyly.exoplayer2.core.drm;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.core.drm.DrmSession;
import com.appsamurai.storyly.exoplayer2.core.drm.DrmSessionEventListener;
import com.appsamurai.storyly.exoplayer2.decoder.CryptoConfig;
import java.util.Map;
import java.util.UUID;

public final class ErrorStateDrmSession implements DrmSession {
    private final DrmSession.DrmSessionException error;

    public ErrorStateDrmSession(DrmSession.DrmSessionException drmSessionException) {
        this.error = (DrmSession.DrmSessionException) Assertions.checkNotNull(drmSessionException);
    }

    public void acquire(@Nullable DrmSessionEventListener.EventDispatcher eventDispatcher) {
    }

    @Nullable
    public CryptoConfig getCryptoConfig() {
        return null;
    }

    @Nullable
    public DrmSession.DrmSessionException getError() {
        return this.error;
    }

    @Nullable
    public byte[] getOfflineLicenseKeySetId() {
        return null;
    }

    public final UUID getSchemeUuid() {
        return C.UUID_NIL;
    }

    public int getState() {
        return 1;
    }

    public boolean playClearSamplesWithoutKeys() {
        return false;
    }

    @Nullable
    public Map<String, String> queryKeyStatus() {
        return null;
    }

    public void release(@Nullable DrmSessionEventListener.EventDispatcher eventDispatcher) {
    }

    public boolean requiresSecureDecoder(String str) {
        return false;
    }
}
