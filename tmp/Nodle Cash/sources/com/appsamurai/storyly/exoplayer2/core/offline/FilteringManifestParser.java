package com.appsamurai.storyly.exoplayer2.core.offline;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.offline.StreamKey;
import com.appsamurai.storyly.exoplayer2.core.offline.FilterableManifest;
import com.appsamurai.storyly.exoplayer2.core.upstream.ParsingLoadable;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public final class FilteringManifestParser<T extends FilterableManifest<T>> implements ParsingLoadable.Parser<T> {
    private final ParsingLoadable.Parser<? extends T> parser;
    @Nullable
    private final List<StreamKey> streamKeys;

    public FilteringManifestParser(ParsingLoadable.Parser<? extends T> parser2, @Nullable List<StreamKey> list) {
        this.parser = parser2;
        this.streamKeys = list;
    }

    public T parse(Uri uri, InputStream inputStream) throws IOException {
        T t2 = (FilterableManifest) this.parser.parse(uri, inputStream);
        List<StreamKey> list = this.streamKeys;
        return (list == null || list.isEmpty()) ? t2 : (FilterableManifest) t2.copy(this.streamKeys);
    }
}
