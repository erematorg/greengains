package com.appsamurai.storyly.exoplayer2.datasource.upstream;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import java.util.ArrayList;

public abstract class BaseDataSource implements DataSource {
    @Nullable
    private DataSpec dataSpec;
    private final boolean isNetwork;
    private int listenerCount;
    private final ArrayList<TransferListener> listeners = new ArrayList<>(1);

    public BaseDataSource(boolean z2) {
        this.isNetwork = z2;
    }

    public final void addTransferListener(TransferListener transferListener) {
        Assertions.checkNotNull(transferListener);
        if (!this.listeners.contains(transferListener)) {
            this.listeners.add(transferListener);
            this.listenerCount++;
        }
    }

    public final void bytesTransferred(int i3) {
        DataSpec dataSpec2 = (DataSpec) Util.castNonNull(this.dataSpec);
        for (int i4 = 0; i4 < this.listenerCount; i4++) {
            this.listeners.get(i4).onBytesTransferred(this, dataSpec2, this.isNetwork, i3);
        }
    }

    public final void transferEnded() {
        DataSpec dataSpec2 = (DataSpec) Util.castNonNull(this.dataSpec);
        for (int i3 = 0; i3 < this.listenerCount; i3++) {
            this.listeners.get(i3).onTransferEnd(this, dataSpec2, this.isNetwork);
        }
        this.dataSpec = null;
    }

    public final void transferInitializing(DataSpec dataSpec2) {
        for (int i3 = 0; i3 < this.listenerCount; i3++) {
            this.listeners.get(i3).onTransferInitializing(this, dataSpec2, this.isNetwork);
        }
    }

    public final void transferStarted(DataSpec dataSpec2) {
        this.dataSpec = dataSpec2;
        for (int i3 = 0; i3 < this.listenerCount; i3++) {
            this.listeners.get(i3).onTransferStart(this, dataSpec2, this.isNetwork);
        }
    }
}
