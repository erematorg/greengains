package com.google.android.gms.maps.model;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.maps.zzf;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public abstract class UrlTileProvider implements TileProvider {
    private final int zza;
    private final int zzb;

    public UrlTileProvider(int i3, int i4) {
        this.zza = i3;
        this.zzb = i4;
    }

    /* JADX INFO: finally extract failed */
    @Nullable
    public final Tile getTile(int i3, int i4, int i5) {
        URL tileUrl = getTileUrl(i3, i4, i5);
        if (tileUrl == null) {
            return TileProvider.NO_TILE;
        }
        try {
            zzf.zzb(4352);
            int i6 = this.zza;
            int i7 = this.zzb;
            InputStream inputStream = tileUrl.openConnection().getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Preconditions.checkNotNull(inputStream, "from must not be null.");
            Preconditions.checkNotNull(byteArrayOutputStream, "to must not be null.");
            byte[] bArr = new byte[4096];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    Tile tile = new Tile(i6, i7, byteArrayOutputStream.toByteArray());
                    zzf.zza();
                    return tile;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
        } catch (IOException unused) {
            zzf.zza();
            return null;
        } catch (Throwable th) {
            zzf.zza();
            throw th;
        }
    }

    @Nullable
    public abstract URL getTileUrl(int i3, int i4, int i5);
}
