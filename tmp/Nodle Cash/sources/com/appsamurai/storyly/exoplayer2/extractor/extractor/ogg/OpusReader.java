package com.appsamurai.storyly.exoplayer2.extractor.extractor.ogg;

import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.ParserException;
import com.appsamurai.storyly.exoplayer2.common.metadata.Metadata;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.extractor.audio.OpusUtil;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.VorbisUtil;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ogg.StreamReader;
import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import java.util.List;
import okio.Utf8;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;

final class OpusReader extends StreamReader {
    private static final byte[] OPUS_COMMENT_HEADER_SIGNATURE = {79, 112, 117, 115, 84, 97, 103, 115};
    private static final byte[] OPUS_ID_HEADER_SIGNATURE = {79, 112, 117, 115, 72, 101, 97, 100};
    private boolean firstCommentHeaderSeen;

    private long getPacketDurationUs(byte[] bArr) {
        byte b3;
        byte b4 = bArr[0];
        byte b5 = b4 & 255;
        byte b6 = b4 & 3;
        if (b6 != 0) {
            b3 = 2;
            if (!(b6 == 1 || b6 == 2)) {
                b3 = bArr[1] & Utf8.REPLACEMENT_BYTE;
            }
        } else {
            b3 = 1;
        }
        int i3 = b5 >> 3;
        int i4 = i3 & 3;
        return ((long) b3) * ((long) (i3 >= 16 ? 2500 << i4 : i3 >= 12 ? 10000 << (i3 & 1) : i4 == 3 ? 60000 : 10000 << i4));
    }

    private static boolean peekPacketStartsWith(ParsableByteArray parsableByteArray, byte[] bArr) {
        if (parsableByteArray.bytesLeft() < bArr.length) {
            return false;
        }
        int position = parsableByteArray.getPosition();
        byte[] bArr2 = new byte[bArr.length];
        parsableByteArray.readBytes(bArr2, 0, bArr.length);
        parsableByteArray.setPosition(position);
        return Arrays.equals(bArr2, bArr);
    }

    public static boolean verifyBitstreamType(ParsableByteArray parsableByteArray) {
        return peekPacketStartsWith(parsableByteArray, OPUS_ID_HEADER_SIGNATURE);
    }

    public long preparePayload(ParsableByteArray parsableByteArray) {
        return convertTimeToGranule(getPacketDurationUs(parsableByteArray.getData()));
    }

    @EnsuresNonNullIf(expression = {"#3.format"}, result = false)
    public boolean readHeaders(ParsableByteArray parsableByteArray, long j2, StreamReader.SetupData setupData) throws ParserException {
        if (peekPacketStartsWith(parsableByteArray, OPUS_ID_HEADER_SIGNATURE)) {
            byte[] copyOf = Arrays.copyOf(parsableByteArray.getData(), parsableByteArray.limit());
            int channelCount = OpusUtil.getChannelCount(copyOf);
            List<byte[]> buildInitializationData = OpusUtil.buildInitializationData(copyOf);
            if (setupData.format != null) {
                return true;
            }
            setupData.format = new Format.Builder().setSampleMimeType(MimeTypes.AUDIO_OPUS).setChannelCount(channelCount).setSampleRate(OpusUtil.SAMPLE_RATE).setInitializationData(buildInitializationData).build();
            return true;
        }
        byte[] bArr = OPUS_COMMENT_HEADER_SIGNATURE;
        if (peekPacketStartsWith(parsableByteArray, bArr)) {
            Assertions.checkStateNotNull(setupData.format);
            if (this.firstCommentHeaderSeen) {
                return true;
            }
            this.firstCommentHeaderSeen = true;
            parsableByteArray.skipBytes(bArr.length);
            Metadata parseVorbisComments = VorbisUtil.parseVorbisComments(ImmutableList.copyOf((E[]) VorbisUtil.readVorbisCommentHeader(parsableByteArray, false, false).comments));
            if (parseVorbisComments == null) {
                return true;
            }
            setupData.format = setupData.format.buildUpon().setMetadata(parseVorbisComments.copyWithAppendedEntriesFrom(setupData.format.metadata)).build();
            return true;
        }
        Assertions.checkStateNotNull(setupData.format);
        return false;
    }

    public void reset(boolean z2) {
        super.reset(z2);
        if (z2) {
            this.firstCommentHeaderSeen = false;
        }
    }
}
