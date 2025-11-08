package com.appsamurai.storyly.exoplayer2.core.upstream;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.source.LoadEventInfo;
import com.appsamurai.storyly.exoplayer2.core.upstream.Loader;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSource;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSourceInputStream;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSpec;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.StatsDataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public final class ParsingLoadable<T> implements Loader.Loadable {
    private final StatsDataSource dataSource;
    public final DataSpec dataSpec;
    public final long loadTaskId;
    private final Parser<? extends T> parser;
    @Nullable
    private volatile T result;
    public final int type;

    public interface Parser<T> {
        T parse(Uri uri, InputStream inputStream) throws IOException;
    }

    public ParsingLoadable(DataSource dataSource2, Uri uri, int i3, Parser<? extends T> parser2) {
        this(dataSource2, new DataSpec.Builder().setUri(uri).setFlags(1).build(), i3, parser2);
    }

    public static <T> T load(DataSource dataSource2, Parser<? extends T> parser2, Uri uri, int i3) throws IOException {
        ParsingLoadable parsingLoadable = new ParsingLoadable(dataSource2, uri, i3, parser2);
        parsingLoadable.load();
        return Assertions.checkNotNull(parsingLoadable.getResult());
    }

    public long bytesLoaded() {
        return this.dataSource.getBytesRead();
    }

    public final void cancelLoad() {
    }

    public Map<String, List<String>> getResponseHeaders() {
        return this.dataSource.getLastResponseHeaders();
    }

    @Nullable
    public final T getResult() {
        return this.result;
    }

    public Uri getUri() {
        return this.dataSource.getLastOpenedUri();
    }

    public ParsingLoadable(DataSource dataSource2, DataSpec dataSpec2, int i3, Parser<? extends T> parser2) {
        this.dataSource = new StatsDataSource(dataSource2);
        this.dataSpec = dataSpec2;
        this.type = i3;
        this.parser = parser2;
        this.loadTaskId = LoadEventInfo.getNewId();
    }

    public static <T> T load(DataSource dataSource2, Parser<? extends T> parser2, DataSpec dataSpec2, int i3) throws IOException {
        ParsingLoadable parsingLoadable = new ParsingLoadable(dataSource2, dataSpec2, i3, parser2);
        parsingLoadable.load();
        return Assertions.checkNotNull(parsingLoadable.getResult());
    }

    public final void load() throws IOException {
        this.dataSource.resetBytesRead();
        DataSourceInputStream dataSourceInputStream = new DataSourceInputStream(this.dataSource, this.dataSpec);
        try {
            dataSourceInputStream.open();
            this.result = this.parser.parse((Uri) Assertions.checkNotNull(this.dataSource.getUri()), dataSourceInputStream);
        } finally {
            Util.closeQuietly(dataSourceInputStream);
        }
    }
}
