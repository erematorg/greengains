package com.appsamurai.storyly.exoplayer2.datasource.upstream;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSource;
import java.io.IOException;

public final class PlaceholderDataSource implements DataSource {
    public static final DataSource.Factory FACTORY = new Object();
    public static final PlaceholderDataSource INSTANCE = new PlaceholderDataSource();

    private PlaceholderDataSource() {
    }

    public static /* synthetic */ PlaceholderDataSource b() {
        return new PlaceholderDataSource();
    }

    public void addTransferListener(TransferListener transferListener) {
    }

    public void close() {
    }

    @Nullable
    public Uri getUri() {
        return null;
    }

    public long open(DataSpec dataSpec) throws IOException {
        throw new IOException("PlaceholderDataSource cannot be opened");
    }

    public int read(byte[] bArr, int i3, int i4) {
        throw new UnsupportedOperationException();
    }
}
