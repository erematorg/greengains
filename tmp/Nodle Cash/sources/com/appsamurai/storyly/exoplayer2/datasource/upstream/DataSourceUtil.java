package com.appsamurai.storyly.exoplayer2.datasource.upstream;

import androidx.annotation.Nullable;
import androidx.compose.animation.core.a;
import java.io.IOException;
import java.util.Arrays;

public final class DataSourceUtil {
    private DataSourceUtil() {
    }

    public static void closeQuietly(@Nullable DataSource dataSource) {
        if (dataSource != null) {
            try {
                dataSource.close();
            } catch (IOException unused) {
            }
        }
    }

    public static byte[] readExactly(DataSource dataSource, int i3) throws IOException {
        byte[] bArr = new byte[i3];
        int i4 = 0;
        while (i4 < i3) {
            int read = dataSource.read(bArr, i4, i3 - i4);
            if (read != -1) {
                i4 += read;
            } else {
                throw new IllegalStateException(a.r("Not enough data could be read: ", i4, " < ", i3));
            }
        }
        return bArr;
    }

    public static byte[] readToEnd(DataSource dataSource) throws IOException {
        byte[] bArr = new byte[1024];
        int i3 = 0;
        int i4 = 0;
        while (i3 != -1) {
            if (i4 == bArr.length) {
                bArr = Arrays.copyOf(bArr, bArr.length * 2);
            }
            i3 = dataSource.read(bArr, i4, bArr.length - i4);
            if (i3 != -1) {
                i4 += i3;
            }
        }
        return Arrays.copyOf(bArr, i4);
    }
}
