package com.squareup.moshi;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.IOException;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Source;
import okio.Timeout;
import org.apache.xml.serialize.LineSeparator;
import org.slf4j.Marker;

final class JsonValueSource implements Source {
    static final ByteString STATE_C_STYLE_COMMENT = ByteString.encodeUtf8(Marker.ANY_MARKER);
    static final ByteString STATE_DOUBLE_QUOTED = ByteString.encodeUtf8("\"\\");
    static final ByteString STATE_END_OF_JSON = ByteString.EMPTY;
    static final ByteString STATE_END_OF_LINE_COMMENT = ByteString.encodeUtf8(LineSeparator.Windows);
    static final ByteString STATE_JSON = ByteString.encodeUtf8("[]{}\"'/#");
    static final ByteString STATE_SINGLE_QUOTED = ByteString.encodeUtf8("'\\");
    private final Buffer buffer;
    private boolean closed;
    private long limit;
    private final Buffer prefix;
    private final BufferedSource source;
    private int stackSize;
    private ByteString state;

    public JsonValueSource(BufferedSource bufferedSource) {
        this(bufferedSource, new Buffer(), STATE_JSON, 0);
    }

    private void advanceLimit(long j2) throws IOException {
        ByteString byteString;
        while (true) {
            long j3 = this.limit;
            if (j3 < j2 && this.state != (byteString = STATE_END_OF_JSON)) {
                if (j3 == this.buffer.size()) {
                    if (this.limit <= 0) {
                        this.source.require(1);
                    } else {
                        return;
                    }
                }
                long indexOfElement = this.buffer.indexOfElement(this.state, this.limit);
                if (indexOfElement == -1) {
                    this.limit = this.buffer.size();
                } else {
                    byte b3 = this.buffer.getByte(indexOfElement);
                    ByteString byteString2 = this.state;
                    ByteString byteString3 = STATE_JSON;
                    if (byteString2 == byteString3) {
                        if (b3 == 34) {
                            this.state = STATE_DOUBLE_QUOTED;
                            this.limit = indexOfElement + 1;
                        } else if (b3 == 35) {
                            this.state = STATE_END_OF_LINE_COMMENT;
                            this.limit = indexOfElement + 1;
                        } else if (b3 == 39) {
                            this.state = STATE_SINGLE_QUOTED;
                            this.limit = indexOfElement + 1;
                        } else if (b3 != 47) {
                            if (b3 != 91) {
                                if (b3 != 93) {
                                    if (b3 != 123) {
                                        if (b3 != 125) {
                                        }
                                    }
                                }
                                int i3 = this.stackSize - 1;
                                this.stackSize = i3;
                                if (i3 == 0) {
                                    this.state = byteString;
                                }
                                this.limit = indexOfElement + 1;
                            }
                            this.stackSize++;
                            this.limit = indexOfElement + 1;
                        } else {
                            long j4 = 2 + indexOfElement;
                            this.source.require(j4);
                            long j5 = indexOfElement + 1;
                            byte b4 = this.buffer.getByte(j5);
                            if (b4 == 47) {
                                this.state = STATE_END_OF_LINE_COMMENT;
                                this.limit = j4;
                            } else if (b4 == 42) {
                                this.state = STATE_C_STYLE_COMMENT;
                                this.limit = j4;
                            } else {
                                this.limit = j5;
                            }
                        }
                    } else if (byteString2 == STATE_SINGLE_QUOTED || byteString2 == STATE_DOUBLE_QUOTED) {
                        if (b3 == 92) {
                            long j6 = indexOfElement + 2;
                            this.source.require(j6);
                            this.limit = j6;
                        } else {
                            if (this.stackSize > 0) {
                                byteString = byteString3;
                            }
                            this.state = byteString;
                            this.limit = indexOfElement + 1;
                        }
                    } else if (byteString2 == STATE_C_STYLE_COMMENT) {
                        long j7 = 2 + indexOfElement;
                        this.source.require(j7);
                        long j8 = indexOfElement + 1;
                        if (this.buffer.getByte(j8) == 47) {
                            this.limit = j7;
                            this.state = byteString3;
                        } else {
                            this.limit = j8;
                        }
                    } else if (byteString2 == STATE_END_OF_LINE_COMMENT) {
                        this.limit = indexOfElement + 1;
                        this.state = byteString3;
                    } else {
                        throw new AssertionError();
                    }
                }
            } else {
                return;
            }
        }
    }

    public void close() throws IOException {
        this.closed = true;
    }

    public void discard() throws IOException {
        this.closed = true;
        while (this.state != STATE_END_OF_JSON) {
            advanceLimit(PlaybackStateCompat.ACTION_PLAY_FROM_URI);
            this.source.skip(this.limit);
        }
    }

    public long read(Buffer buffer2, long j2) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        } else if (j2 == 0) {
            return 0;
        } else {
            if (!this.prefix.exhausted()) {
                long read = this.prefix.read(buffer2, j2);
                long j3 = j2 - read;
                if (this.buffer.exhausted()) {
                    return read;
                }
                long read2 = read(buffer2, j3);
                return read2 != -1 ? read + read2 : read;
            }
            advanceLimit(j2);
            long j4 = this.limit;
            if (j4 != 0) {
                long min = Math.min(j2, j4);
                buffer2.write(this.buffer, min);
                this.limit -= min;
                return min;
            } else if (this.state == STATE_END_OF_JSON) {
                return -1;
            } else {
                throw new AssertionError();
            }
        }
    }

    public Timeout timeout() {
        return this.source.timeout();
    }

    public JsonValueSource(BufferedSource bufferedSource, Buffer buffer2, ByteString byteString, int i3) {
        this.limit = 0;
        this.closed = false;
        this.source = bufferedSource;
        this.buffer = bufferedSource.getBuffer();
        this.prefix = buffer2;
        this.state = byteString;
        this.stackSize = i3;
    }
}
