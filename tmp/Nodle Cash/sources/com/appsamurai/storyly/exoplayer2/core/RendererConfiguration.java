package com.appsamurai.storyly.exoplayer2.core;

import androidx.annotation.Nullable;

public final class RendererConfiguration {
    public static final RendererConfiguration DEFAULT = new RendererConfiguration(false);
    public final boolean tunneling;

    public RendererConfiguration(boolean z2) {
        this.tunneling = z2;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || RendererConfiguration.class != obj.getClass()) {
            return false;
        }
        return this.tunneling == ((RendererConfiguration) obj).tunneling;
    }

    public int hashCode() {
        return this.tunneling ^ true ? 1 : 0;
    }
}
