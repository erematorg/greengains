package com.appsamurai.storyly.exoplayer2.core.upstream;

import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSpec;

public interface TimeToFirstByteEstimator {
    long getTimeToFirstByteEstimateUs();

    void onTransferInitializing(DataSpec dataSpec);

    void onTransferStart(DataSpec dataSpec);

    void reset();
}
