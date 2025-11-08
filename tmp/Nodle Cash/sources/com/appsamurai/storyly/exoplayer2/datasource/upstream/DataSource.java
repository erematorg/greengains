package com.appsamurai.storyly.exoplayer2.datasource.upstream;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.upstream.DataReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public interface DataSource extends DataReader {

    public interface Factory {
        DataSource createDataSource();
    }

    void addTransferListener(TransferListener transferListener);

    void close() throws IOException;

    Map<String, List<String>> getResponseHeaders() {
        return Collections.emptyMap();
    }

    @Nullable
    Uri getUri();

    long open(DataSpec dataSpec) throws IOException;
}
