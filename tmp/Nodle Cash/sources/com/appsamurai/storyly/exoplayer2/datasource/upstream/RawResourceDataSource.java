package com.appsamurai.storyly.exoplayer2.datasource.upstream;

import A.a;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.PlaybackException;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;

public final class RawResourceDataSource extends BaseDataSource {
    public static final String RAW_RESOURCE_SCHEME = "rawresource";
    @Nullable
    private AssetFileDescriptor assetFileDescriptor;
    private long bytesRemaining;
    @Nullable
    private InputStream inputStream;
    private boolean opened;
    private final String packageName;
    private final Resources resources;
    @Nullable
    private Uri uri;

    public static class RawResourceDataSourceException extends DataSourceException {
        @Deprecated
        public RawResourceDataSourceException(String str) {
            super(str, (Throwable) null, 2000);
        }

        @Deprecated
        public RawResourceDataSourceException(Throwable th) {
            super(th, 2000);
        }

        public RawResourceDataSourceException(@Nullable String str, @Nullable Throwable th, int i3) {
            super(str, th, i3);
        }
    }

    public RawResourceDataSource(Context context) {
        super(false);
        this.resources = context.getResources();
        this.packageName = context.getPackageName();
    }

    public static Uri buildRawResourceUri(int i3) {
        return Uri.parse("rawresource:///" + i3);
    }

    public void close() throws RawResourceDataSourceException {
        this.uri = null;
        try {
            InputStream inputStream2 = this.inputStream;
            if (inputStream2 != null) {
                inputStream2.close();
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
                throw new RawResourceDataSourceException((String) null, e3, 2000);
            } catch (Throwable th) {
                this.assetFileDescriptor = null;
                if (this.opened) {
                    this.opened = false;
                    transferEnded();
                }
                throw th;
            }
        } catch (IOException e4) {
            throw new RawResourceDataSourceException((String) null, e4, 2000);
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
                throw new RawResourceDataSourceException((String) null, e5, 2000);
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

    public long open(DataSpec dataSpec) throws RawResourceDataSourceException {
        int i3;
        DataSpec dataSpec2 = dataSpec;
        Uri uri2 = dataSpec2.uri;
        this.uri = uri2;
        if (TextUtils.equals(RAW_RESOURCE_SCHEME, uri2.getScheme()) || (TextUtils.equals("android.resource", uri2.getScheme()) && uri2.getPathSegments().size() == 1 && ((String) Assertions.checkNotNull(uri2.getLastPathSegment())).matches("\\d+"))) {
            try {
                i3 = Integer.parseInt((String) Assertions.checkNotNull(uri2.getLastPathSegment()));
            } catch (NumberFormatException unused) {
                throw new RawResourceDataSourceException("Resource identifier must be an integer.", (Throwable) null, 1004);
            }
        } else if (TextUtils.equals("android.resource", uri2.getScheme())) {
            String str = (String) Assertions.checkNotNull(uri2.getPath());
            if (str.startsWith("/")) {
                str = str.substring(1);
            }
            String host = uri2.getHost();
            i3 = this.resources.getIdentifier(a.n(new StringBuilder(), TextUtils.isEmpty(host) ? "" : android.support.v4.media.session.a.m(host, ":"), str), "raw", this.packageName);
            if (i3 == 0) {
                throw new RawResourceDataSourceException("Resource not found.", (Throwable) null, PlaybackException.ERROR_CODE_IO_FILE_NOT_FOUND);
            }
        } else {
            throw new RawResourceDataSourceException("URI must either use scheme rawresource or android.resource", (Throwable) null, 1004);
        }
        transferInitializing(dataSpec);
        try {
            AssetFileDescriptor openRawResourceFd = this.resources.openRawResourceFd(i3);
            this.assetFileDescriptor = openRawResourceFd;
            if (openRawResourceFd != null) {
                long length = openRawResourceFd.getLength();
                FileInputStream fileInputStream = new FileInputStream(openRawResourceFd.getFileDescriptor());
                this.inputStream = fileInputStream;
                int i4 = (length > -1 ? 1 : (length == -1 ? 0 : -1));
                if (i4 != 0) {
                    try {
                        if (dataSpec2.position > length) {
                            throw new RawResourceDataSourceException((String) null, (Throwable) null, 2008);
                        }
                    } catch (RawResourceDataSourceException e3) {
                        throw e3;
                    } catch (IOException e4) {
                        throw new RawResourceDataSourceException((String) null, e4, 2000);
                    }
                }
                long startOffset = openRawResourceFd.getStartOffset();
                long skip = fileInputStream.skip(dataSpec2.position + startOffset) - startOffset;
                if (skip == dataSpec2.position) {
                    if (i4 == 0) {
                        FileChannel channel = fileInputStream.getChannel();
                        if (channel.size() == 0) {
                            this.bytesRemaining = -1;
                        } else {
                            long size = channel.size() - channel.position();
                            this.bytesRemaining = size;
                            if (size < 0) {
                                throw new RawResourceDataSourceException((String) null, (Throwable) null, 2008);
                            }
                        }
                    } else {
                        long j2 = length - skip;
                        this.bytesRemaining = j2;
                        if (j2 < 0) {
                            throw new DataSourceException(2008);
                        }
                    }
                    long j3 = dataSpec2.length;
                    if (j3 != -1) {
                        long j4 = this.bytesRemaining;
                        if (j4 != -1) {
                            j3 = Math.min(j4, j3);
                        }
                        this.bytesRemaining = j3;
                    }
                    this.opened = true;
                    transferStarted(dataSpec);
                    long j5 = dataSpec2.length;
                    return j5 != -1 ? j5 : this.bytesRemaining;
                }
                throw new RawResourceDataSourceException((String) null, (Throwable) null, 2008);
            }
            throw new RawResourceDataSourceException(android.support.v4.media.session.a.l("Resource is compressed: ", uri2), (Throwable) null, 2000);
        } catch (Resources.NotFoundException e5) {
            throw new RawResourceDataSourceException((String) null, e5, PlaybackException.ERROR_CODE_IO_FILE_NOT_FOUND);
        }
    }

    public int read(byte[] bArr, int i3, int i4) throws RawResourceDataSourceException {
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
                throw new RawResourceDataSourceException((String) null, e3, 2000);
            }
        }
        int read = ((InputStream) Util.castNonNull(this.inputStream)).read(bArr, i3, i4);
        if (read != -1) {
            long j3 = this.bytesRemaining;
            if (j3 != -1) {
                this.bytesRemaining = j3 - ((long) read);
            }
            bytesTransferred(read);
            return read;
        } else if (this.bytesRemaining == -1) {
            return -1;
        } else {
            throw new RawResourceDataSourceException("End of stream reached having not read sufficient data.", new EOFException(), 2000);
        }
    }
}
