package com.appsamurai.storyly.exoplayer2.datasource.upstream;

import androidx.annotation.Nullable;
import java.io.IOException;

public class DataSourceException extends IOException {
    @Deprecated
    public static final int POSITION_OUT_OF_RANGE = 2008;
    public final int reason;

    public DataSourceException(int i3) {
        this.reason = i3;
    }

    public static boolean isCausedByPositionOutOfRange(IOException iOException) {
        Throwable th;
        while (th != null) {
            if ((th instanceof DataSourceException) && ((DataSourceException) th).reason == 2008) {
                return true;
            }
            Throwable cause = th.getCause();
            th = iOException;
            th = cause;
        }
        return false;
    }

    public DataSourceException(@Nullable Throwable th, int i3) {
        super(th);
        this.reason = i3;
    }

    public DataSourceException(@Nullable String str, int i3) {
        super(str);
        this.reason = i3;
    }

    public DataSourceException(@Nullable String str, @Nullable Throwable th, int i3) {
        super(str, th);
        this.reason = i3;
    }
}
