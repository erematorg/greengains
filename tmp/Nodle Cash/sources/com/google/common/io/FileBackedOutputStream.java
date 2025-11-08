package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
import javax.annotation.CheckForNull;

@GwtIncompatible
@ElementTypesAreNonnullByDefault
@Beta
@J2ktIncompatible
public final class FileBackedOutputStream extends OutputStream {
    @CheckForNull
    @GuardedBy("this")
    private File file;
    private final int fileThreshold;
    @CheckForNull
    @GuardedBy("this")
    private MemoryOutput memory;
    @GuardedBy("this")
    private OutputStream out;
    private final boolean resetOnFinalize;
    private final ByteSource source;

    public static class MemoryOutput extends ByteArrayOutputStream {
        private MemoryOutput() {
        }

        public byte[] getBuffer() {
            return this.buf;
        }

        public int getCount() {
            return this.count;
        }
    }

    public FileBackedOutputStream(int i3) {
        this(i3, false);
    }

    /* access modifiers changed from: private */
    public synchronized InputStream openInputStream() throws IOException {
        if (this.file != null) {
            return new FileInputStream(this.file);
        }
        Objects.requireNonNull(this.memory);
        return new ByteArrayInputStream(this.memory.getBuffer(), 0, this.memory.getCount());
    }

    @GuardedBy("this")
    private void update(int i3) throws IOException {
        MemoryOutput memoryOutput = this.memory;
        if (memoryOutput != null && memoryOutput.getCount() + i3 > this.fileThreshold) {
            File createTempFile = TempFileCreator.INSTANCE.createTempFile("FileBackedOutputStream");
            if (this.resetOnFinalize) {
                createTempFile.deleteOnExit();
            }
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
                fileOutputStream.write(this.memory.getBuffer(), 0, this.memory.getCount());
                fileOutputStream.flush();
                this.out = fileOutputStream;
                this.file = createTempFile;
                this.memory = null;
            } catch (IOException e3) {
                createTempFile.delete();
                throw e3;
            }
        }
    }

    public ByteSource asByteSource() {
        return this.source;
    }

    public synchronized void close() throws IOException {
        this.out.close();
    }

    public synchronized void flush() throws IOException {
        this.out.flush();
    }

    @CheckForNull
    @VisibleForTesting
    public synchronized File getFile() {
        return this.file;
    }

    public synchronized void reset() throws IOException {
        try {
            close();
            MemoryOutput memoryOutput = this.memory;
            if (memoryOutput == null) {
                this.memory = new MemoryOutput();
            } else {
                memoryOutput.reset();
            }
            this.out = this.memory;
            File file2 = this.file;
            if (file2 != null) {
                this.file = null;
                if (!file2.delete()) {
                    throw new IOException("Could not delete: " + file2);
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized void write(int i3) throws IOException {
        update(1);
        this.out.write(i3);
    }

    public FileBackedOutputStream(int i3, boolean z2) {
        Preconditions.checkArgument(i3 >= 0, "fileThreshold must be non-negative, but was %s", i3);
        this.fileThreshold = i3;
        this.resetOnFinalize = z2;
        MemoryOutput memoryOutput = new MemoryOutput();
        this.memory = memoryOutput;
        this.out = memoryOutput;
        if (z2) {
            this.source = new ByteSource() {
                public void finalize() {
                    try {
                        FileBackedOutputStream.this.reset();
                    } catch (Throwable th) {
                        th.printStackTrace(System.err);
                    }
                }

                public InputStream openStream() throws IOException {
                    return FileBackedOutputStream.this.openInputStream();
                }
            };
        } else {
            this.source = new ByteSource() {
                public InputStream openStream() throws IOException {
                    return FileBackedOutputStream.this.openInputStream();
                }
            };
        }
    }

    public synchronized void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    public synchronized void write(byte[] bArr, int i3, int i4) throws IOException {
        update(i4);
        this.out.write(bArr, i3, i4);
    }
}
