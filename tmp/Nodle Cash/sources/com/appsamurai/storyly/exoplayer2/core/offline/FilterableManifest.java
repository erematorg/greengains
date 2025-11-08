package com.appsamurai.storyly.exoplayer2.core.offline;

import com.appsamurai.storyly.exoplayer2.common.offline.StreamKey;
import java.util.List;

public interface FilterableManifest<T> {
    T copy(List<StreamKey> list);
}
