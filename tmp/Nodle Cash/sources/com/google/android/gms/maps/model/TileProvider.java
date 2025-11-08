package com.google.android.gms.maps.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public interface TileProvider {
    @NonNull
    public static final Tile NO_TILE = new Tile(-1, -1, (byte[]) null);

    @Nullable
    Tile getTile(int i3, int i4, int i5);
}
