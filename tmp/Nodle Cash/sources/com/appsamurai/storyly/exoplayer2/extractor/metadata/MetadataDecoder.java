package com.appsamurai.storyly.exoplayer2.extractor.metadata;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.metadata.Metadata;

public interface MetadataDecoder {
    @Nullable
    Metadata decode(MetadataInputBuffer metadataInputBuffer);
}
