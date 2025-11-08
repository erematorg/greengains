package com.appsamurai.storyly.exoplayer2.common.ui;

import android.view.ViewGroup;
import androidx.annotation.Nullable;
import com.google.common.collect.ImmutableList;
import java.util.List;

public interface AdViewProvider {
    List<AdOverlayInfo> getAdOverlayInfos() {
        return ImmutableList.of();
    }

    @Nullable
    ViewGroup getAdViewGroup();
}
