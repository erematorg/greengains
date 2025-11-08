package com.appsamurai.storyly.exoplayer2.datasource.upstream;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.PlaybackException;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.FileChannel;

public final class ContentDataSource extends BaseDataSource {
    @Nullable
    private AssetFileDescriptor assetFileDescriptor;
    private long bytesRemaining;
    @Nullable
    private FileInputStream inputStream;
    private boolean opened;
    private final ContentResolver resolver;
    @Nullable
    private Uri uri;

    public static class ContentDataSourceException extends DataSourceException {
        @Deprecated
        public ContentDataSourceException(IOException iOException) {
            this(iOException, 2000);
        }

        public ContentDataSourceException(@Nullable IOException iOException, int i3) {
            super((Throwable) iOException, i3);
        }
    }

    public ContentDataSource(Context context) {
        super(false);
        this.resolver = context.getContentResolver();
    }

    public void close() throws ContentDataSourceException {
        this.uri = null;
        try {
            FileInputStream fileInputStream = this.inputStream;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            this.inputStream = null;
            try {
                AssetFileDescriptor assetFileDescriptor2 = this.assetFileDescriptor;
                if (assetFileDescriptor2 != null) {
                    assetFileDescriptor2.close();
                }
                this.assetFileDescriptor = null;
                if (this.opened) {
                    this.opened = false;
                    transferEnded();
                }
            } catch (IOException e3) {
                throw new ContentDataSourceException(e3, 2000);
            } catch (Throwable th) {
                this.assetFileDescriptor = null;
                if (this.opened) {
                    this.opened = false;
                    transferEnded();
                }
                throw th;
            }
        } catch (IOException e4) {
            throw new ContentDataSourceException(e4, 2000);
        } catch (Throwable th2) {
            this.inputStream = null;
            try {
                AssetFileDescriptor assetFileDescriptor3 = this.assetFileDescriptor;
                if (assetFileDescriptor3 != null) {
                    assetFileDescriptor3.close();
                }
                this.assetFileDescriptor = null;
                if (this.opened) {
                    this.opened = false;
                    transferEnded();
                }
                throw th2;
            } catch (IOException e5) {
                throw new ContentDataSourceException(e5, 2000);
            } catch (Throwable th3) {
                this.assetFileDescriptor = null;
                if (this.opened) {
                    this.opened = false;
                    transferEnded();
                }
                throw th3;
            }
        }
    }

    @Nullable
    public Uri getUri() {
        return this.uri;
    }

    public long open(DataSpec dataSpec) throws ContentDataSourceException {
        AssetFileDescriptor assetFileDescriptor2;
        DataSpec dataSpec2 = dataSpec;
        int i3 = 2000;
        try {
            Uri uri2 = dataSpec2.uri;
            this.uri = uri2;
            transferInitializing(dataSpec);
            if (FirebaseAnalytics.Param.CONTENT.equals(dataSpec2.uri.getScheme())) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("android.provider.extra.ACCEPT_ORIGINAL_MEDIA_FORMAT", true);
                assetFileDescriptor2 = this.resolver.openTypedAssetFileDescriptor(uri2, "*/*", bundle);
            } else {
                assetFileDescriptor2 = this.resolver.openAssetFileDescriptor(uri2, "r");
            }
            this.assetFileDescriptor = assetFileDescriptor2;
            if (assetFileDescriptor2 != null) {
                long length = assetFileDescriptor2.getLength();
                FileInputStream fileInputStream = new FileInputStream(assetFileDescriptor2.getFileDescriptor());
                this.inputStream = fileInputStream;
                int i4 = (length > -1 ? 1 : (length == -1 ? 0 : -1));
                if (i4 != 0) {
                    if (dataSpec2.position > length) {
                        throw new ContentDataSourceException((IOException) null, 2008);
                    }
                }
                long startOffset = assetFileDescriptor2.getStartOffset();
                long j2 = length;
                long skip = fileInputStream.skip(dataSpec2.position + startOffset) - startOffset;
                if (skip == dataSpec2.position) {
                    if (i4 == 0) {
                        FileChannel channel = fileInputStream.getChannel();
                        long size = channel.size();
                        if (size == 0) {
                            this.bytesRemaining = -1;
                        } else {
                            long position = size - channel.position();
                            this.bytesRemaining = position;
                            if (position < 0) {
                                throw new ContentDataSourceException((IOException) null, 2008);
                            }
                        }
                    } else {
                        long j3 = j2 - skip;
                        this.bytesRemaining = j3;
                        if (j3 < 0) {
                            throw new ContentDataSourceException((IOException) null, 2008);
                        }
                    }
                    long j4 = dataSpec2.length;
                    if (j4 != -1) {
                        long j5 = this.bytesRemaining;
                        if (j5 != -1) {
                            j4 = Math.min(j5, j4);
                        }
                        this.bytesRemaining = j4;
                    }
                    this.opened = true;
                    transferStarted(dataSpec);
                    long j6 = dataSpec2.length;
                    return j6 != -1 ? j6 : this.bytesRemaining;
                }
                throw new ContentDataSourceException((IOException) null, 2008);
            }
            throw new ContentDataSourceException(new IOException("Could not open file descriptor for: " + uri2), 2000);
        } catch (ContentDataSourceException e3) {
            throw e3;
        } catch (IOException e4) {
            if (e4 instanceof FileNotFoundException) {
                i3 = PlaybackException.ERROR_CODE_IO_FILE_NOT_FOUND;
            }
            throw new ContentDataSourceException(e4, i3);
        }
    }

    public int read(byte[] bArr, int i3, int i4) throws ContentDataSourceException {
        if (i4 == 0) {
            return 0;
        }
        long j2 = this.bytesRemaining;
        if (j2 == 0) {
            return -1;
        }
        if (j2 != -1) {
            try {
                i4 = (int) Math.min(j2, (long) i4);
            } catch (IOException e3) {
                throw new ContentDataSourceException(e3, 2000);
            }
        }
        int read = ((FileInputStream) Util.castNonNull(this.inputStream)).read(bArr, i3, i4);
        if (read == -1) {
            return -1;
        }
        long j3 = this.bytesRemaining;
        if (j3 != -1) {
            this.bytesRemaining = j3 - ((long) read);
        }
        bytesTransferred(read);
        return read;
    }
}
