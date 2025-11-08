package com.appsamurai.storyly.exoplayer2.datasource.upstream;

import android.net.Uri;
import android.system.ErrnoException;
import android.system.OsConstants;
import android.text.TextUtils;
import androidx.annotation.DoNotInline;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.camera.camera2.internal.C0118y;
import com.appsamurai.storyly.exoplayer2.common.PlaybackException;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public final class FileDataSource extends BaseDataSource {
    private long bytesRemaining;
    @Nullable
    private RandomAccessFile file;
    private boolean opened;
    @Nullable
    private Uri uri;

    @RequiresApi(21)
    public static final class Api21 {
        private Api21() {
        }

        /* access modifiers changed from: private */
        @DoNotInline
        public static boolean isPermissionError(@Nullable Throwable th) {
            return (th instanceof ErrnoException) && ((ErrnoException) th).errno == OsConstants.EACCES;
        }
    }

    public static final class Factory implements DataSource.Factory {
        @Nullable
        private TransferListener listener;

        public Factory setListener(@Nullable TransferListener transferListener) {
            this.listener = transferListener;
            return this;
        }

        public FileDataSource createDataSource() {
            FileDataSource fileDataSource = new FileDataSource();
            TransferListener transferListener = this.listener;
            if (transferListener != null) {
                fileDataSource.addTransferListener(transferListener);
            }
            return fileDataSource;
        }
    }

    public static class FileDataSourceException extends DataSourceException {
        @Deprecated
        public FileDataSourceException(Exception exc) {
            super((Throwable) exc, 2000);
        }

        @Deprecated
        public FileDataSourceException(String str, IOException iOException) {
            super(str, iOException, 2000);
        }

        public FileDataSourceException(Throwable th, int i3) {
            super(th, i3);
        }

        public FileDataSourceException(@Nullable String str, @Nullable Throwable th, int i3) {
            super(str, th, i3);
        }
    }

    public FileDataSource() {
        super(false);
    }

    private static RandomAccessFile openLocalFile(Uri uri2) throws FileDataSourceException {
        int i3 = PlaybackException.ERROR_CODE_IO_NO_PERMISSION;
        try {
            return new RandomAccessFile((String) Assertions.checkNotNull(uri2.getPath()), "r");
        } catch (FileNotFoundException e3) {
            if (!TextUtils.isEmpty(uri2.getQuery()) || !TextUtils.isEmpty(uri2.getFragment())) {
                String path = uri2.getPath();
                String query = uri2.getQuery();
                String fragment = uri2.getFragment();
                StringBuilder l2 = C0118y.l("uri has query and/or fragment, which are not supported. Did you call Uri.parse() on a string containing '?' or '#'? Use Uri.fromFile(new File(path)) to avoid this. path=", path, ",query=", query, ",fragment=");
                l2.append(fragment);
                throw new FileDataSourceException(l2.toString(), e3, 1004);
            }
            if (Util.SDK_INT < 21 || !Api21.isPermissionError(e3.getCause())) {
                i3 = PlaybackException.ERROR_CODE_IO_FILE_NOT_FOUND;
            }
            throw new FileDataSourceException((Throwable) e3, i3);
        } catch (SecurityException e4) {
            throw new FileDataSourceException((Throwable) e4, (int) PlaybackException.ERROR_CODE_IO_NO_PERMISSION);
        } catch (RuntimeException e5) {
            throw new FileDataSourceException((Throwable) e5, 2000);
        }
    }

    public void close() throws FileDataSourceException {
        this.uri = null;
        try {
            RandomAccessFile randomAccessFile = this.file;
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            this.file = null;
            if (this.opened) {
                this.opened = false;
                transferEnded();
            }
        } catch (IOException e3) {
            throw new FileDataSourceException((Throwable) e3, 2000);
        } catch (Throwable th) {
            this.file = null;
            if (this.opened) {
                this.opened = false;
                transferEnded();
            }
            throw th;
        }
    }

    @Nullable
    public Uri getUri() {
        return this.uri;
    }

    public long open(DataSpec dataSpec) throws FileDataSourceException {
        Uri uri2 = dataSpec.uri;
        this.uri = uri2;
        transferInitializing(dataSpec);
        RandomAccessFile openLocalFile = openLocalFile(uri2);
        this.file = openLocalFile;
        try {
            openLocalFile.seek(dataSpec.position);
            long j2 = dataSpec.length;
            if (j2 == -1) {
                j2 = this.file.length() - dataSpec.position;
            }
            this.bytesRemaining = j2;
            if (j2 >= 0) {
                this.opened = true;
                transferStarted(dataSpec);
                return this.bytesRemaining;
            }
            throw new FileDataSourceException((String) null, (Throwable) null, 2008);
        } catch (IOException e3) {
            throw new FileDataSourceException((Throwable) e3, 2000);
        }
    }

    public int read(byte[] bArr, int i3, int i4) throws FileDataSourceException {
        if (i4 == 0) {
            return 0;
        }
        if (this.bytesRemaining == 0) {
            return -1;
        }
        try {
            int read = ((RandomAccessFile) Util.castNonNull(this.file)).read(bArr, i3, (int) Math.min(this.bytesRemaining, (long) i4));
            if (read > 0) {
                this.bytesRemaining -= (long) read;
                bytesTransferred(read);
            }
            return read;
        } catch (IOException e3) {
            throw new FileDataSourceException((Throwable) e3, 2000);
        }
    }
}
