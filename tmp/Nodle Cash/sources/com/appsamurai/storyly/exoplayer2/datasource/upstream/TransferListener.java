package com.appsamurai.storyly.exoplayer2.datasource.upstream;

public interface TransferListener {
    void onBytesTransferred(DataSource dataSource, DataSpec dataSpec, boolean z2, int i3);

    void onTransferEnd(DataSource dataSource, DataSpec dataSpec, boolean z2);

    void onTransferInitializing(DataSource dataSource, DataSpec dataSpec, boolean z2);

    void onTransferStart(DataSource dataSource, DataSpec dataSpec, boolean z2);
}
