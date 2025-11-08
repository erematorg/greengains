package com.appsamurai.storyly.exoplayer2.extractor.extractor.ogg;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.ParserException;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.VorbisUtil;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ogg.StreamReader;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;

final class VorbisReader extends StreamReader {
    @Nullable
    private VorbisUtil.CommentHeader commentHeader;
    private int previousPacketBlockSize;
    private boolean seenFirstAudioPacket;
    @Nullable
    private VorbisUtil.VorbisIdHeader vorbisIdHeader;
    @Nullable
    private VorbisSetup vorbisSetup;

    public static final class VorbisSetup {
        public final VorbisUtil.CommentHeader commentHeader;
        public final int iLogModes;
        public final VorbisUtil.VorbisIdHeader idHeader;
        public final VorbisUtil.Mode[] modes;
        public final byte[] setupHeaderData;

        public VorbisSetup(VorbisUtil.VorbisIdHeader vorbisIdHeader, VorbisUtil.CommentHeader commentHeader2, byte[] bArr, VorbisUtil.Mode[] modeArr, int i3) {
            this.idHeader = vorbisIdHeader;
            this.commentHeader = commentHeader2;
            this.setupHeaderData = bArr;
            this.modes = modeArr;
            this.iLogModes = i3;
        }
    }

    @VisibleForTesting
    public static void appendNumberOfSamples(ParsableByteArray parsableByteArray, long j2) {
        if (parsableByteArray.capacity() < parsableByteArray.limit() + 4) {
            parsableByteArray.reset(Arrays.copyOf(parsableByteArray.getData(), parsableByteArray.limit() + 4));
        } else {
            parsableByteArray.setLimit(parsableByteArray.limit() + 4);
        }
        byte[] data = parsableByteArray.getData();
        data[parsableByteArray.limit() - 4] = (byte) ((int) (j2 & 255));
        data[parsableByteArray.limit() - 3] = (byte) ((int) ((j2 >>> 8) & 255));
        data[parsableByteArray.limit() - 2] = (byte) ((int) ((j2 >>> 16) & 255));
        data[parsableByteArray.limit() - 1] = (byte) ((int) ((j2 >>> 24) & 255));
    }

    private static int decodeBlockSize(byte b3, VorbisSetup vorbisSetup2) {
        return !vorbisSetup2.modes[readBits(b3, vorbisSetup2.iLogModes, 1)].blockFlag ? vorbisSetup2.idHeader.blockSize0 : vorbisSetup2.idHeader.blockSize1;
    }

    @VisibleForTesting
    public static int readBits(byte b3, int i3, int i4) {
        return (b3 >> i4) & (255 >>> (8 - i3));
    }

    public static boolean verifyBitstreamType(ParsableByteArray parsableByteArray) {
        try {
            return VorbisUtil.verifyVorbisHeaderCapturePattern(1, parsableByteArray, true);
        } catch (ParserException unused) {
            return false;
        }
    }

    public void onSeekEnd(long j2) {
        super.onSeekEnd(j2);
        int i3 = 0;
        this.seenFirstAudioPacket = j2 != 0;
        VorbisUtil.VorbisIdHeader vorbisIdHeader2 = this.vorbisIdHeader;
        if (vorbisIdHeader2 != null) {
            i3 = vorbisIdHeader2.blockSize0;
        }
        this.previousPacketBlockSize = i3;
    }

    public long preparePayload(ParsableByteArray parsableByteArray) {
        int i3 = 0;
        if ((parsableByteArray.getData()[0] & 1) == 1) {
            return -1;
        }
        int decodeBlockSize = decodeBlockSize(parsableByteArray.getData()[0], (VorbisSetup) Assertions.checkStateNotNull(this.vorbisSetup));
        if (this.seenFirstAudioPacket) {
            i3 = (this.previousPacketBlockSize + decodeBlockSize) / 4;
        }
        long j2 = (long) i3;
        appendNumberOfSamples(parsableByteArray, j2);
        this.seenFirstAudioPacket = true;
        this.previousPacketBlockSize = decodeBlockSize;
        return j2;
    }

    @EnsuresNonNullIf(expression = {"#3.format"}, result = false)
    public boolean readHeaders(ParsableByteArray parsableByteArray, long j2, StreamReader.SetupData setupData) throws IOException {
        if (this.vorbisSetup != null) {
            Assertions.checkNotNull(setupData.format);
            return false;
        }
        VorbisSetup readSetupHeaders = readSetupHeaders(parsableByteArray);
        this.vorbisSetup = readSetupHeaders;
        if (readSetupHeaders == null) {
            return true;
        }
        VorbisUtil.VorbisIdHeader vorbisIdHeader2 = readSetupHeaders.idHeader;
        ArrayList arrayList = new ArrayList();
        arrayList.add(vorbisIdHeader2.data);
        arrayList.add(readSetupHeaders.setupHeaderData);
        setupData.format = new Format.Builder().setSampleMimeType(MimeTypes.AUDIO_VORBIS).setAverageBitrate(vorbisIdHeader2.bitrateNominal).setPeakBitrate(vorbisIdHeader2.bitrateMaximum).setChannelCount(vorbisIdHeader2.channels).setSampleRate(vorbisIdHeader2.sampleRate).setInitializationData(arrayList).setMetadata(VorbisUtil.parseVorbisComments(ImmutableList.copyOf((E[]) readSetupHeaders.commentHeader.comments))).build();
        return true;
    }

    @VisibleForTesting
    @Nullable
    public VorbisSetup readSetupHeaders(ParsableByteArray parsableByteArray) throws IOException {
        VorbisUtil.VorbisIdHeader vorbisIdHeader2 = this.vorbisIdHeader;
        if (vorbisIdHeader2 == null) {
            this.vorbisIdHeader = VorbisUtil.readVorbisIdentificationHeader(parsableByteArray);
            return null;
        }
        VorbisUtil.CommentHeader commentHeader2 = this.commentHeader;
        if (commentHeader2 == null) {
            this.commentHeader = VorbisUtil.readVorbisCommentHeader(parsableByteArray);
            return null;
        }
        byte[] bArr = new byte[parsableByteArray.limit()];
        System.arraycopy(parsableByteArray.getData(), 0, bArr, 0, parsableByteArray.limit());
        VorbisUtil.Mode[] readVorbisModes = VorbisUtil.readVorbisModes(parsableByteArray, vorbisIdHeader2.channels);
        return new VorbisSetup(vorbisIdHeader2, commentHeader2, bArr, readVorbisModes, VorbisUtil.iLog(readVorbisModes.length - 1));
    }

    public void reset(boolean z2) {
        super.reset(z2);
        if (z2) {
            this.vorbisSetup = null;
            this.vorbisIdHeader = null;
            this.commentHeader = null;
        }
        this.previousPacketBlockSize = 0;
        this.seenFirstAudioPacket = false;
    }
}
