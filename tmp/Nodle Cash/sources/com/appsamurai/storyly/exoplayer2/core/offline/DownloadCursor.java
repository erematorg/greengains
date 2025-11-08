package com.appsamurai.storyly.exoplayer2.core.offline;

import java.io.Closeable;

public interface DownloadCursor extends Closeable {
    void close();

    int getCount();

    Download getDownload();

    int getPosition();

    boolean isAfterLast() {
        return getCount() == 0 || getPosition() == getCount();
    }

    boolean isBeforeFirst() {
        return getCount() == 0 || getPosition() == -1;
    }

    boolean isClosed();

    boolean isFirst() {
        return getPosition() == 0 && getCount() != 0;
    }

    boolean isLast() {
        int count = getCount();
        return getPosition() == count + -1 && count != 0;
    }

    boolean moveToFirst() {
        return moveToPosition(0);
    }

    boolean moveToLast() {
        return moveToPosition(getCount() - 1);
    }

    boolean moveToNext() {
        return moveToPosition(getPosition() + 1);
    }

    boolean moveToPosition(int i3);

    boolean moveToPrevious() {
        return moveToPosition(getPosition() - 1);
    }
}
