package com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4;

import android.util.Pair;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.ParserException;
import com.appsamurai.storyly.exoplayer2.common.drm.DrmInitData;
import com.appsamurai.storyly.exoplayer2.common.metadata.Metadata;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.common.video.ColorInfo;
import com.appsamurai.storyly.exoplayer2.core.r;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorUtil;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.GaplessInfoHolder;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.Atom;
import com.appsamurai.storyly.exoplayer2.extractor.metadata.mp4.MdtaMetadataEntry;
import com.appsamurai.storyly.exoplayer2.extractor.metadata.mp4.SmtaMetadataEntry;
import com.appsamurai.storyly.exoplayer2.extractor.video.AvcConfig;
import com.appsamurai.storyly.exoplayer2.extractor.video.DolbyVisionConfig;
import com.appsamurai.storyly.exoplayer2.extractor.video.HevcConfig;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

final class AtomParsers {
    private static final int MAX_GAPLESS_TRIM_SIZE_SAMPLES = 4;
    private static final String TAG = "AtomParsers";
    private static final int TYPE_clcp = 1668047728;
    private static final int TYPE_mdta = 1835299937;
    private static final int TYPE_meta = 1835365473;
    private static final int TYPE_nclc = 1852009571;
    private static final int TYPE_nclx = 1852009592;
    private static final int TYPE_sbtl = 1935832172;
    private static final int TYPE_soun = 1936684398;
    private static final int TYPE_subt = 1937072756;
    private static final int TYPE_text = 1952807028;
    private static final int TYPE_vide = 1986618469;
    private static final byte[] opusMagic = Util.getUtf8Bytes("OpusHead");

    public static final class ChunkIterator {
        private final ParsableByteArray chunkOffsets;
        private final boolean chunkOffsetsAreLongs;
        public int index;
        public final int length;
        private int nextSamplesPerChunkChangeIndex;
        public int numSamples;
        public long offset;
        private int remainingSamplesPerChunkChanges;
        private final ParsableByteArray stsc;

        public ChunkIterator(ParsableByteArray parsableByteArray, ParsableByteArray parsableByteArray2, boolean z2) throws ParserException {
            this.stsc = parsableByteArray;
            this.chunkOffsets = parsableByteArray2;
            this.chunkOffsetsAreLongs = z2;
            parsableByteArray2.setPosition(12);
            this.length = parsableByteArray2.readUnsignedIntToInt();
            parsableByteArray.setPosition(12);
            this.remainingSamplesPerChunkChanges = parsableByteArray.readUnsignedIntToInt();
            ExtractorUtil.checkContainerInput(parsableByteArray.readInt() != 1 ? false : true, "first_chunk must be 1");
            this.index = -1;
        }

        public boolean moveNext() {
            int i3 = this.index + 1;
            this.index = i3;
            if (i3 == this.length) {
                return false;
            }
            this.offset = this.chunkOffsetsAreLongs ? this.chunkOffsets.readUnsignedLongToLong() : this.chunkOffsets.readUnsignedInt();
            if (this.index == this.nextSamplesPerChunkChangeIndex) {
                this.numSamples = this.stsc.readUnsignedIntToInt();
                this.stsc.skipBytes(4);
                int i4 = this.remainingSamplesPerChunkChanges - 1;
                this.remainingSamplesPerChunkChanges = i4;
                this.nextSamplesPerChunkChangeIndex = i4 > 0 ? this.stsc.readUnsignedIntToInt() - 1 : -1;
            }
            return true;
        }
    }

    public static final class EsdsData {
        /* access modifiers changed from: private */
        public final int bitrate;
        /* access modifiers changed from: private */
        public final byte[] initializationData;
        /* access modifiers changed from: private */
        public final String mimeType;
        /* access modifiers changed from: private */
        public final int peakBitrate;

        public EsdsData(String str, byte[] bArr, int i3, int i4) {
            this.mimeType = str;
            this.initializationData = bArr;
            this.bitrate = i3;
            this.peakBitrate = i4;
        }
    }

    public interface SampleSizeBox {
        int getFixedSampleSize();

        int getSampleCount();

        int readNextSampleSize();
    }

    public static final class StsdData {
        public static final int STSD_HEADER_SIZE = 8;
        @Nullable
        public Format format;
        public int nalUnitLengthFieldLength;
        public int requiredSampleTransformation = 0;
        public final TrackEncryptionBox[] trackEncryptionBoxes;

        public StsdData(int i3) {
            this.trackEncryptionBoxes = new TrackEncryptionBox[i3];
        }
    }

    public static final class StszSampleSizeBox implements SampleSizeBox {
        private final ParsableByteArray data;
        private final int fixedSampleSize;
        private final int sampleCount;

        public StszSampleSizeBox(Atom.LeafAtom leafAtom, Format format) {
            ParsableByteArray parsableByteArray = leafAtom.data;
            this.data = parsableByteArray;
            parsableByteArray.setPosition(12);
            int readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
            if (MimeTypes.AUDIO_RAW.equals(format.sampleMimeType)) {
                int pcmFrameSize = Util.getPcmFrameSize(format.pcmEncoding, format.channelCount);
                if (readUnsignedIntToInt == 0 || readUnsignedIntToInt % pcmFrameSize != 0) {
                    Log.w(AtomParsers.TAG, "Audio sample size mismatch. stsd sample size: " + pcmFrameSize + ", stsz sample size: " + readUnsignedIntToInt);
                    readUnsignedIntToInt = pcmFrameSize;
                }
            }
            this.fixedSampleSize = readUnsignedIntToInt == 0 ? -1 : readUnsignedIntToInt;
            this.sampleCount = parsableByteArray.readUnsignedIntToInt();
        }

        public int getFixedSampleSize() {
            return this.fixedSampleSize;
        }

        public int getSampleCount() {
            return this.sampleCount;
        }

        public int readNextSampleSize() {
            int i3 = this.fixedSampleSize;
            return i3 == -1 ? this.data.readUnsignedIntToInt() : i3;
        }
    }

    public static final class Stz2SampleSizeBox implements SampleSizeBox {
        private int currentByte;
        private final ParsableByteArray data;
        private final int fieldSize;
        private final int sampleCount;
        private int sampleIndex;

        public Stz2SampleSizeBox(Atom.LeafAtom leafAtom) {
            ParsableByteArray parsableByteArray = leafAtom.data;
            this.data = parsableByteArray;
            parsableByteArray.setPosition(12);
            this.fieldSize = parsableByteArray.readUnsignedIntToInt() & 255;
            this.sampleCount = parsableByteArray.readUnsignedIntToInt();
        }

        public int getFixedSampleSize() {
            return -1;
        }

        public int getSampleCount() {
            return this.sampleCount;
        }

        public int readNextSampleSize() {
            int i3 = this.fieldSize;
            if (i3 == 8) {
                return this.data.readUnsignedByte();
            }
            if (i3 == 16) {
                return this.data.readUnsignedShort();
            }
            int i4 = this.sampleIndex;
            this.sampleIndex = i4 + 1;
            if (i4 % 2 != 0) {
                return this.currentByte & 15;
            }
            int readUnsignedByte = this.data.readUnsignedByte();
            this.currentByte = readUnsignedByte;
            return (readUnsignedByte & 240) >> 4;
        }
    }

    public static final class TkhdData {
        /* access modifiers changed from: private */
        public final long duration;
        /* access modifiers changed from: private */
        public final int id;
        /* access modifiers changed from: private */
        public final int rotationDegrees;

        public TkhdData(int i3, long j2, int i4) {
            this.id = i3;
            this.duration = j2;
            this.rotationDegrees = i4;
        }
    }

    private AtomParsers() {
    }

    private static ByteBuffer allocateHdrStaticInfo() {
        return ByteBuffer.allocate(25).order(ByteOrder.LITTLE_ENDIAN);
    }

    private static boolean canApplyEditWithGaplessInfo(long[] jArr, long j2, long j3, long j4) {
        int length = jArr.length - 1;
        return jArr[0] <= j3 && j3 < jArr[Util.constrainValue(4, 0, length)] && jArr[Util.constrainValue(jArr.length - 4, 0, length)] < j4 && j4 <= j2;
    }

    private static int findBoxPosition(ParsableByteArray parsableByteArray, int i3, int i4, int i5) throws ParserException {
        int position = parsableByteArray.getPosition();
        ExtractorUtil.checkContainerInput(position >= i4, (String) null);
        while (position - i4 < i5) {
            parsableByteArray.setPosition(position);
            int readInt = parsableByteArray.readInt();
            ExtractorUtil.checkContainerInput(readInt > 0, "childAtomSize must be positive");
            if (parsableByteArray.readInt() == i3) {
                return position;
            }
            position += readInt;
        }
        return -1;
    }

    private static int getTrackTypeForHdlr(int i3) {
        if (i3 == TYPE_soun) {
            return 1;
        }
        if (i3 == TYPE_vide) {
            return 2;
        }
        if (i3 == TYPE_text || i3 == TYPE_sbtl || i3 == TYPE_subt || i3 == TYPE_clcp) {
            return 3;
        }
        return i3 == 1835365473 ? 5 : -1;
    }

    public static void maybeSkipRemainingMetaAtomHeaderBytes(ParsableByteArray parsableByteArray) {
        int position = parsableByteArray.getPosition();
        parsableByteArray.skipBytes(4);
        if (parsableByteArray.readInt() != 1751411826) {
            position += 4;
        }
        parsableByteArray.setPosition(position);
    }

    /* JADX WARNING: Removed duplicated region for block: B:154:0x030d A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:167:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x0166  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void parseAudioSampleEntry(com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r22, int r23, int r24, int r25, int r26, java.lang.String r27, boolean r28, @androidx.annotation.Nullable com.appsamurai.storyly.exoplayer2.common.drm.DrmInitData r29, com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.AtomParsers.StsdData r30, int r31) throws com.appsamurai.storyly.exoplayer2.common.ParserException {
        /*
            r0 = r22
            r1 = r24
            r2 = r25
            r3 = r26
            r4 = r27
            r5 = r29
            r6 = r30
            int r7 = r1 + 16
            r0.setPosition(r7)
            r7 = 6
            if (r28 == 0) goto L_0x001e
            int r9 = r22.readUnsignedShort()
            r0.skipBytes(r7)
            goto L_0x0024
        L_0x001e:
            r9 = 8
            r0.skipBytes(r9)
            r9 = 0
        L_0x0024:
            r10 = 4
            r11 = 2
            r12 = 1
            r13 = 16
            if (r9 == 0) goto L_0x0048
            if (r9 != r12) goto L_0x002e
            goto L_0x0048
        L_0x002e:
            if (r9 != r11) goto L_0x0047
            r0.skipBytes(r13)
            double r13 = r22.readDouble()
            long r13 = java.lang.Math.round(r13)
            int r7 = (int) r13
            int r9 = r22.readUnsignedIntToInt()
            r13 = 20
            r0.skipBytes(r13)
            r15 = 0
            goto L_0x0065
        L_0x0047:
            return
        L_0x0048:
            int r14 = r22.readUnsignedShort()
            r0.skipBytes(r7)
            int r7 = r22.readUnsignedFixedPoint1616()
            int r15 = r22.getPosition()
            int r15 = r15 - r10
            r0.setPosition(r15)
            int r15 = r22.readInt()
            if (r9 != r12) goto L_0x0064
            r0.skipBytes(r13)
        L_0x0064:
            r9 = r14
        L_0x0065:
            int r13 = r22.getPosition()
            r14 = 1701733217(0x656e6361, float:7.0359778E22)
            r11 = r23
            if (r11 != r14) goto L_0x0097
            android.util.Pair r14 = parseSampleEntryEncryptionData(r0, r1, r2)
            if (r14 == 0) goto L_0x0094
            java.lang.Object r11 = r14.first
            java.lang.Integer r11 = (java.lang.Integer) r11
            int r11 = r11.intValue()
            if (r5 != 0) goto L_0x0082
            r5 = 0
            goto L_0x008c
        L_0x0082:
            java.lang.Object r12 = r14.second
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.TrackEncryptionBox r12 = (com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.TrackEncryptionBox) r12
            java.lang.String r12 = r12.schemeType
            com.appsamurai.storyly.exoplayer2.common.drm.DrmInitData r5 = r5.copyWithSchemeType(r12)
        L_0x008c:
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.TrackEncryptionBox[] r12 = r6.trackEncryptionBoxes
            java.lang.Object r14 = r14.second
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.TrackEncryptionBox r14 = (com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.TrackEncryptionBox) r14
            r12[r31] = r14
        L_0x0094:
            r0.setPosition(r13)
        L_0x0097:
            r12 = 1633889587(0x61632d33, float:2.6191674E20)
            r14 = 1634492771(0x616c6163, float:2.7252807E20)
            if (r11 != r12) goto L_0x00a6
            java.lang.String r11 = "audio/ac3"
        L_0x00a1:
            r19 = r11
            r11 = -1
            goto L_0x015a
        L_0x00a6:
            r12 = 1700998451(0x65632d33, float:6.7050686E22)
            if (r11 != r12) goto L_0x00ae
            java.lang.String r11 = "audio/eac3"
            goto L_0x00a1
        L_0x00ae:
            r12 = 1633889588(0x61632d34, float:2.6191676E20)
            if (r11 != r12) goto L_0x00b6
            java.lang.String r11 = "audio/ac4"
            goto L_0x00a1
        L_0x00b6:
            r12 = 1685353315(0x64747363, float:1.803728E22)
            if (r11 != r12) goto L_0x00be
            java.lang.String r11 = "audio/vnd.dts"
            goto L_0x00a1
        L_0x00be:
            r12 = 1685353320(0x64747368, float:1.8037286E22)
            if (r11 == r12) goto L_0x0156
            r12 = 1685353324(0x6474736c, float:1.803729E22)
            if (r11 != r12) goto L_0x00ca
            goto L_0x0156
        L_0x00ca:
            r12 = 1685353317(0x64747365, float:1.8037282E22)
            if (r11 != r12) goto L_0x00d2
            java.lang.String r11 = "audio/vnd.dts.hd;profile=lbr"
            goto L_0x00a1
        L_0x00d2:
            r12 = 1685353336(0x64747378, float:1.8037304E22)
            if (r11 != r12) goto L_0x00da
            java.lang.String r11 = "audio/vnd.dts.uhd;profile=p2"
            goto L_0x00a1
        L_0x00da:
            r12 = 1935764850(0x73616d72, float:1.7860208E31)
            if (r11 != r12) goto L_0x00e2
            java.lang.String r11 = "audio/3gpp"
            goto L_0x00a1
        L_0x00e2:
            r12 = 1935767394(0x73617762, float:1.7863284E31)
            if (r11 != r12) goto L_0x00ea
            java.lang.String r11 = "audio/amr-wb"
            goto L_0x00a1
        L_0x00ea:
            r12 = 1819304813(0x6c70636d, float:1.1624469E27)
            java.lang.String r19 = "audio/raw"
            if (r11 == r12) goto L_0x0154
            r12 = 1936684916(0x736f7774, float:1.89725E31)
            if (r11 != r12) goto L_0x00f7
            goto L_0x0154
        L_0x00f7:
            r12 = 1953984371(0x74776f73, float:7.841539E31)
            if (r11 != r12) goto L_0x00ff
            r11 = 268435456(0x10000000, float:2.5243549E-29)
            goto L_0x015a
        L_0x00ff:
            r12 = 778924082(0x2e6d7032, float:5.398721E-11)
            if (r11 == r12) goto L_0x0150
            r12 = 778924083(0x2e6d7033, float:5.3987214E-11)
            if (r11 != r12) goto L_0x010a
            goto L_0x0150
        L_0x010a:
            r12 = 1835557169(0x6d686131, float:4.4948762E27)
            if (r11 != r12) goto L_0x0112
            java.lang.String r11 = "audio/mha1"
            goto L_0x00a1
        L_0x0112:
            r12 = 1835560241(0x6d686d31, float:4.495783E27)
            if (r11 != r12) goto L_0x011a
            java.lang.String r11 = "audio/mhm1"
            goto L_0x00a1
        L_0x011a:
            if (r11 != r14) goto L_0x011f
            java.lang.String r11 = "audio/alac"
            goto L_0x00a1
        L_0x011f:
            r12 = 1634492791(0x616c6177, float:2.7252842E20)
            if (r11 != r12) goto L_0x0128
            java.lang.String r11 = "audio/g711-alaw"
            goto L_0x00a1
        L_0x0128:
            r12 = 1970037111(0x756c6177, float:2.9964816E32)
            if (r11 != r12) goto L_0x0131
            java.lang.String r11 = "audio/g711-mlaw"
            goto L_0x00a1
        L_0x0131:
            r12 = 1332770163(0x4f707573, float:4.03422899E9)
            if (r11 != r12) goto L_0x013a
            java.lang.String r11 = "audio/opus"
            goto L_0x00a1
        L_0x013a:
            r12 = 1716281667(0x664c6143, float:2.4128923E23)
            if (r11 != r12) goto L_0x0143
            java.lang.String r11 = "audio/flac"
            goto L_0x00a1
        L_0x0143:
            r12 = 1835823201(0x6d6c7061, float:4.573395E27)
            if (r11 != r12) goto L_0x014c
            java.lang.String r11 = "audio/true-hd"
            goto L_0x00a1
        L_0x014c:
            r11 = -1
            r19 = 0
            goto L_0x015a
        L_0x0150:
            java.lang.String r11 = "audio/mpeg"
            goto L_0x00a1
        L_0x0154:
            r11 = 2
            goto L_0x015a
        L_0x0156:
            java.lang.String r11 = "audio/vnd.dts.hd"
            goto L_0x00a1
        L_0x015a:
            r12 = r19
            r19 = 0
            r20 = 0
            r21 = 0
        L_0x0162:
            int r10 = r13 - r1
            if (r10 >= r2) goto L_0x0309
            r0.setPosition(r13)
            int r10 = r22.readInt()
            if (r10 <= 0) goto L_0x0171
            r14 = 1
            goto L_0x0172
        L_0x0171:
            r14 = 0
        L_0x0172:
            java.lang.String r8 = "childAtomSize must be positive"
            com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorUtil.checkContainerInput(r14, r8)
            int r8 = r22.readInt()
            r14 = 1835557187(0x6d686143, float:4.4948815E27)
            if (r8 != r14) goto L_0x019c
            int r8 = r10 + -13
            byte[] r14 = new byte[r8]
            int r1 = r13 + 13
            r0.setPosition(r1)
            r1 = 0
            r0.readBytes(r14, r1, r8)
            com.google.common.collect.ImmutableList r21 = com.google.common.collect.ImmutableList.of(r14)
        L_0x0191:
            r8 = -1
        L_0x0192:
            r14 = 1
            r16 = 0
            r17 = 2
        L_0x0197:
            r18 = 1634492771(0x616c6163, float:2.7252807E20)
            goto L_0x0300
        L_0x019c:
            r1 = 1702061171(0x65736473, float:7.183675E22)
            if (r8 == r1) goto L_0x01a8
            if (r28 == 0) goto L_0x01b3
            r14 = 2002876005(0x77617665, float:4.5729223E33)
            if (r8 != r14) goto L_0x01b3
        L_0x01a8:
            r2 = 4
            r14 = 1
            r16 = 0
            r17 = 2
            r18 = 1634492771(0x616c6163, float:2.7252807E20)
            goto L_0x02ce
        L_0x01b3:
            r1 = 1684103987(0x64616333, float:1.6630662E22)
            if (r8 != r1) goto L_0x01cf
            int r1 = r13 + 8
            r0.setPosition(r1)
            java.lang.String r1 = java.lang.Integer.toString(r26)
            com.appsamurai.storyly.exoplayer2.common.Format r1 = com.appsamurai.storyly.exoplayer2.extractor.audio.Ac3Util.parseAc3AnnexFFormat(r0, r1, r4, r5)
            r6.format = r1
        L_0x01c7:
            r2 = 4
            r14 = 1
            r16 = 0
            r17 = 2
            goto L_0x02cb
        L_0x01cf:
            r1 = 1684366131(0x64656333, float:1.692581E22)
            if (r8 != r1) goto L_0x01e4
            int r1 = r13 + 8
            r0.setPosition(r1)
            java.lang.String r1 = java.lang.Integer.toString(r26)
            com.appsamurai.storyly.exoplayer2.common.Format r1 = com.appsamurai.storyly.exoplayer2.extractor.audio.Ac3Util.parseEAc3AnnexFFormat(r0, r1, r4, r5)
            r6.format = r1
            goto L_0x01c7
        L_0x01e4:
            r1 = 1684103988(0x64616334, float:1.6630663E22)
            if (r8 != r1) goto L_0x01f9
            int r1 = r13 + 8
            r0.setPosition(r1)
            java.lang.String r1 = java.lang.Integer.toString(r26)
            com.appsamurai.storyly.exoplayer2.common.Format r1 = com.appsamurai.storyly.exoplayer2.extractor.audio.Ac4Util.parseAc4AnnexEFormat(r0, r1, r4, r5)
            r6.format = r1
            goto L_0x01c7
        L_0x01f9:
            r1 = 1684892784(0x646d6c70, float:1.7518768E22)
            if (r8 != r1) goto L_0x0218
            if (r15 <= 0) goto L_0x0204
            r7 = r15
            r8 = -1
            r9 = 2
            goto L_0x0192
        L_0x0204:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Invalid sample rate for Dolby TrueHD MLP stream: "
            r0.<init>(r1)
            r0.append(r15)
            java.lang.String r0 = r0.toString()
            r14 = 0
            com.appsamurai.storyly.exoplayer2.common.ParserException r0 = com.appsamurai.storyly.exoplayer2.common.ParserException.createForMalformedContainer(r0, r14)
            throw r0
        L_0x0218:
            r14 = 0
            r1 = 1684305011(0x64647473, float:1.6856995E22)
            if (r8 != r1) goto L_0x0242
            com.appsamurai.storyly.exoplayer2.common.Format$Builder r1 = new com.appsamurai.storyly.exoplayer2.common.Format$Builder
            r1.<init>()
            com.appsamurai.storyly.exoplayer2.common.Format$Builder r1 = r1.setId((int) r3)
            com.appsamurai.storyly.exoplayer2.common.Format$Builder r1 = r1.setSampleMimeType(r12)
            com.appsamurai.storyly.exoplayer2.common.Format$Builder r1 = r1.setChannelCount(r9)
            com.appsamurai.storyly.exoplayer2.common.Format$Builder r1 = r1.setSampleRate(r7)
            com.appsamurai.storyly.exoplayer2.common.Format$Builder r1 = r1.setDrmInitData(r5)
            com.appsamurai.storyly.exoplayer2.common.Format$Builder r1 = r1.setLanguage(r4)
            com.appsamurai.storyly.exoplayer2.common.Format r1 = r1.build()
            r6.format = r1
            goto L_0x01c7
        L_0x0242:
            r1 = 1682927731(0x644f7073, float:1.5306315E22)
            if (r8 != r1) goto L_0x0260
            int r1 = r10 + -8
            byte[] r8 = opusMagic
            int r14 = r8.length
            int r14 = r14 + r1
            byte[] r14 = java.util.Arrays.copyOf(r8, r14)
            int r2 = r13 + 8
            r0.setPosition(r2)
            int r2 = r8.length
            r0.readBytes(r14, r2, r1)
            java.util.List r21 = com.appsamurai.storyly.exoplayer2.extractor.audio.OpusUtil.buildInitializationData(r14)
            goto L_0x0191
        L_0x0260:
            r1 = 1684425825(0x64664c61, float:1.6993019E22)
            if (r8 != r1) goto L_0x0292
            int r1 = r10 + -12
            int r2 = r10 + -8
            byte[] r2 = new byte[r2]
            r8 = 102(0x66, float:1.43E-43)
            r14 = 0
            r2[r14] = r8
            r8 = 76
            r14 = 1
            r2[r14] = r8
            r8 = 97
            r17 = 2
            r2[r17] = r8
            r8 = 3
            r18 = 67
            r2[r8] = r18
            int r8 = r13 + 12
            r0.setPosition(r8)
            r8 = 4
            r0.readBytes(r2, r8, r1)
            com.google.common.collect.ImmutableList r21 = com.google.common.collect.ImmutableList.of(r2)
            r8 = -1
            r16 = 0
            goto L_0x0197
        L_0x0292:
            r1 = 1634492771(0x616c6163, float:2.7252807E20)
            r2 = 4
            r14 = 1
            r17 = 2
            if (r8 != r1) goto L_0x02c9
            int r7 = r10 + -12
            byte[] r8 = new byte[r7]
            int r9 = r13 + 12
            r0.setPosition(r9)
            r9 = 0
            r0.readBytes(r8, r9, r7)
            android.util.Pair r7 = com.appsamurai.storyly.exoplayer2.common.util.CodecSpecificDataUtil.parseAlacAudioSpecificConfig(r8)
            java.lang.Object r1 = r7.first
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r1 = r1.intValue()
            java.lang.Object r7 = r7.second
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            com.google.common.collect.ImmutableList r21 = com.google.common.collect.ImmutableList.of(r8)
            r16 = r9
            r8 = -1
            r18 = 1634492771(0x616c6163, float:2.7252807E20)
            r9 = r7
            r7 = r1
            goto L_0x0300
        L_0x02c9:
            r16 = 0
        L_0x02cb:
            r8 = -1
            goto L_0x0197
        L_0x02ce:
            if (r8 != r1) goto L_0x02d3
            r1 = r13
        L_0x02d1:
            r8 = -1
            goto L_0x02d8
        L_0x02d3:
            int r1 = findBoxPosition(r0, r1, r13, r10)
            goto L_0x02d1
        L_0x02d8:
            if (r1 == r8) goto L_0x0300
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.AtomParsers$EsdsData r19 = parseEsdsFromParent(r0, r1)
            java.lang.String r12 = r19.mimeType
            byte[] r1 = r19.initializationData
            if (r1 == 0) goto L_0x0300
            java.lang.String r2 = "audio/mp4a-latm"
            boolean r2 = r2.equals(r12)
            if (r2 == 0) goto L_0x02fc
            com.appsamurai.storyly.exoplayer2.extractor.audio.AacUtil$Config r2 = com.appsamurai.storyly.exoplayer2.extractor.audio.AacUtil.parseAudioSpecificConfig(r1)
            int r7 = r2.sampleRateHz
            int r9 = r2.channelCount
            java.lang.String r2 = r2.codecs
            r20 = r2
        L_0x02fc:
            com.google.common.collect.ImmutableList r21 = com.google.common.collect.ImmutableList.of(r1)
        L_0x0300:
            int r13 = r13 + r10
            r1 = r24
            r2 = r25
            r14 = r18
            goto L_0x0162
        L_0x0309:
            com.appsamurai.storyly.exoplayer2.common.Format r0 = r6.format
            if (r0 != 0) goto L_0x0353
            if (r12 == 0) goto L_0x0353
            com.appsamurai.storyly.exoplayer2.common.Format$Builder r0 = new com.appsamurai.storyly.exoplayer2.common.Format$Builder
            r0.<init>()
            com.appsamurai.storyly.exoplayer2.common.Format$Builder r0 = r0.setId((int) r3)
            com.appsamurai.storyly.exoplayer2.common.Format$Builder r0 = r0.setSampleMimeType(r12)
            r1 = r20
            com.appsamurai.storyly.exoplayer2.common.Format$Builder r0 = r0.setCodecs(r1)
            com.appsamurai.storyly.exoplayer2.common.Format$Builder r0 = r0.setChannelCount(r9)
            com.appsamurai.storyly.exoplayer2.common.Format$Builder r0 = r0.setSampleRate(r7)
            com.appsamurai.storyly.exoplayer2.common.Format$Builder r0 = r0.setPcmEncoding(r11)
            r1 = r21
            com.appsamurai.storyly.exoplayer2.common.Format$Builder r0 = r0.setInitializationData(r1)
            com.appsamurai.storyly.exoplayer2.common.Format$Builder r0 = r0.setDrmInitData(r5)
            com.appsamurai.storyly.exoplayer2.common.Format$Builder r0 = r0.setLanguage(r4)
            if (r19 == 0) goto L_0x034d
            int r1 = r19.bitrate
            com.appsamurai.storyly.exoplayer2.common.Format$Builder r1 = r0.setAverageBitrate(r1)
            int r2 = r19.peakBitrate
            r1.setPeakBitrate(r2)
        L_0x034d:
            com.appsamurai.storyly.exoplayer2.common.Format r0 = r0.build()
            r6.format = r0
        L_0x0353:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.AtomParsers.parseAudioSampleEntry(com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray, int, int, int, int, java.lang.String, boolean, com.appsamurai.storyly.exoplayer2.common.drm.DrmInitData, com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.AtomParsers$StsdData, int):void");
    }

    @Nullable
    public static Pair<Integer, TrackEncryptionBox> parseCommonEncryptionSinfFromParent(ParsableByteArray parsableByteArray, int i3, int i4) throws ParserException {
        int i5 = i3 + 8;
        boolean z2 = false;
        int i6 = -1;
        int i7 = 0;
        String str = null;
        Integer num = null;
        while (i5 - i3 < i4) {
            parsableByteArray.setPosition(i5);
            int readInt = parsableByteArray.readInt();
            int readInt2 = parsableByteArray.readInt();
            if (readInt2 == 1718775137) {
                num = Integer.valueOf(parsableByteArray.readInt());
            } else if (readInt2 == 1935894637) {
                parsableByteArray.skipBytes(4);
                str = parsableByteArray.readString(4);
            } else if (readInt2 == 1935894633) {
                i6 = i5;
                i7 = readInt;
            }
            i5 += readInt;
        }
        if (!C.CENC_TYPE_cenc.equals(str) && !C.CENC_TYPE_cbc1.equals(str) && !C.CENC_TYPE_cens.equals(str) && !C.CENC_TYPE_cbcs.equals(str)) {
            return null;
        }
        ExtractorUtil.checkContainerInput(num != null, "frma atom is mandatory");
        ExtractorUtil.checkContainerInput(i6 != -1, "schi atom is mandatory");
        TrackEncryptionBox parseSchiFromParent = parseSchiFromParent(parsableByteArray, i6, i7, str);
        if (parseSchiFromParent != null) {
            z2 = true;
        }
        ExtractorUtil.checkContainerInput(z2, "tenc atom is mandatory");
        return Pair.create(num, (TrackEncryptionBox) Util.castNonNull(parseSchiFromParent));
    }

    @Nullable
    private static Pair<long[], long[]> parseEdts(Atom.ContainerAtom containerAtom) {
        Atom.LeafAtom leafAtomOfType = containerAtom.getLeafAtomOfType(Atom.TYPE_elst);
        if (leafAtomOfType == null) {
            return null;
        }
        ParsableByteArray parsableByteArray = leafAtomOfType.data;
        parsableByteArray.setPosition(8);
        int parseFullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        int readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        long[] jArr = new long[readUnsignedIntToInt];
        long[] jArr2 = new long[readUnsignedIntToInt];
        int i3 = 0;
        while (i3 < readUnsignedIntToInt) {
            jArr[i3] = parseFullAtomVersion == 1 ? parsableByteArray.readUnsignedLongToLong() : parsableByteArray.readUnsignedInt();
            jArr2[i3] = parseFullAtomVersion == 1 ? parsableByteArray.readLong() : (long) parsableByteArray.readInt();
            if (parsableByteArray.readShort() == 1) {
                parsableByteArray.skipBytes(2);
                i3++;
            } else {
                throw new IllegalArgumentException("Unsupported media rate.");
            }
        }
        return Pair.create(jArr, jArr2);
    }

    private static EsdsData parseEsdsFromParent(ParsableByteArray parsableByteArray, int i3) {
        parsableByteArray.setPosition(i3 + 12);
        parsableByteArray.skipBytes(1);
        parseExpandableClassSize(parsableByteArray);
        parsableByteArray.skipBytes(2);
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        if ((readUnsignedByte & 128) != 0) {
            parsableByteArray.skipBytes(2);
        }
        if ((readUnsignedByte & 64) != 0) {
            parsableByteArray.skipBytes(parsableByteArray.readUnsignedShort());
        }
        if ((readUnsignedByte & 32) != 0) {
            parsableByteArray.skipBytes(2);
        }
        parsableByteArray.skipBytes(1);
        parseExpandableClassSize(parsableByteArray);
        String mimeTypeFromMp4ObjectType = MimeTypes.getMimeTypeFromMp4ObjectType(parsableByteArray.readUnsignedByte());
        int i4 = -1;
        if (MimeTypes.AUDIO_MPEG.equals(mimeTypeFromMp4ObjectType) || MimeTypes.AUDIO_DTS.equals(mimeTypeFromMp4ObjectType) || MimeTypes.AUDIO_DTS_HD.equals(mimeTypeFromMp4ObjectType)) {
            return new EsdsData(mimeTypeFromMp4ObjectType, (byte[]) null, -1, -1);
        }
        parsableByteArray.skipBytes(4);
        int readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        int readUnsignedIntToInt2 = parsableByteArray.readUnsignedIntToInt();
        parsableByteArray.skipBytes(1);
        int parseExpandableClassSize = parseExpandableClassSize(parsableByteArray);
        byte[] bArr = new byte[parseExpandableClassSize];
        parsableByteArray.readBytes(bArr, 0, parseExpandableClassSize);
        if (readUnsignedIntToInt2 <= 0) {
            readUnsignedIntToInt2 = -1;
        }
        if (readUnsignedIntToInt > 0) {
            i4 = readUnsignedIntToInt;
        }
        return new EsdsData(mimeTypeFromMp4ObjectType, bArr, readUnsignedIntToInt2, i4);
    }

    private static int parseExpandableClassSize(ParsableByteArray parsableByteArray) {
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int i3 = readUnsignedByte & 127;
        while ((readUnsignedByte & 128) == 128) {
            readUnsignedByte = parsableByteArray.readUnsignedByte();
            i3 = (i3 << 7) | (readUnsignedByte & 127);
        }
        return i3;
    }

    private static int parseHdlr(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(16);
        return parsableByteArray.readInt();
    }

    @Nullable
    private static Metadata parseIlst(ParsableByteArray parsableByteArray, int i3) {
        parsableByteArray.skipBytes(8);
        ArrayList arrayList = new ArrayList();
        while (parsableByteArray.getPosition() < i3) {
            Metadata.Entry parseIlstElement = MetadataUtil.parseIlstElement(parsableByteArray);
            if (parseIlstElement != null) {
                arrayList.add(parseIlstElement);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new Metadata((List<? extends Metadata.Entry>) arrayList);
    }

    private static Pair<Long, String> parseMdhd(ParsableByteArray parsableByteArray) {
        int i3 = 8;
        parsableByteArray.setPosition(8);
        int parseFullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(parseFullAtomVersion == 0 ? 8 : 16);
        long readUnsignedInt = parsableByteArray.readUnsignedInt();
        if (parseFullAtomVersion == 0) {
            i3 = 4;
        }
        parsableByteArray.skipBytes(i3);
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        return Pair.create(Long.valueOf(readUnsignedInt), "" + ((char) (((readUnsignedShort >> 10) & 31) + 96)) + ((char) (((readUnsignedShort >> 5) & 31) + 96)) + ((char) ((readUnsignedShort & 31) + 96)));
    }

    @Nullable
    public static Metadata parseMdtaFromMeta(Atom.ContainerAtom containerAtom) {
        Atom.LeafAtom leafAtomOfType = containerAtom.getLeafAtomOfType(Atom.TYPE_hdlr);
        Atom.LeafAtom leafAtomOfType2 = containerAtom.getLeafAtomOfType(Atom.TYPE_keys);
        Atom.LeafAtom leafAtomOfType3 = containerAtom.getLeafAtomOfType(Atom.TYPE_ilst);
        if (leafAtomOfType == null || leafAtomOfType2 == null || leafAtomOfType3 == null || parseHdlr(leafAtomOfType.data) != TYPE_mdta) {
            return null;
        }
        ParsableByteArray parsableByteArray = leafAtomOfType2.data;
        parsableByteArray.setPosition(12);
        int readInt = parsableByteArray.readInt();
        String[] strArr = new String[readInt];
        for (int i3 = 0; i3 < readInt; i3++) {
            int readInt2 = parsableByteArray.readInt();
            parsableByteArray.skipBytes(4);
            strArr[i3] = parsableByteArray.readString(readInt2 - 8);
        }
        ParsableByteArray parsableByteArray2 = leafAtomOfType3.data;
        parsableByteArray2.setPosition(8);
        ArrayList arrayList = new ArrayList();
        while (parsableByteArray2.bytesLeft() > 8) {
            int position = parsableByteArray2.getPosition();
            int readInt3 = parsableByteArray2.readInt();
            int readInt4 = parsableByteArray2.readInt() - 1;
            if (readInt4 < 0 || readInt4 >= readInt) {
                r.a(readInt4, "Skipped metadata with unknown key index: ", TAG);
            } else {
                MdtaMetadataEntry parseMdtaMetadataEntryFromIlst = MetadataUtil.parseMdtaMetadataEntryFromIlst(parsableByteArray2, position + readInt3, strArr[readInt4]);
                if (parseMdtaMetadataEntryFromIlst != null) {
                    arrayList.add(parseMdtaMetadataEntryFromIlst);
                }
            }
            parsableByteArray2.setPosition(position + readInt3);
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new Metadata((List<? extends Metadata.Entry>) arrayList);
    }

    private static void parseMetaDataSampleEntry(ParsableByteArray parsableByteArray, int i3, int i4, int i5, StsdData stsdData) {
        parsableByteArray.setPosition(i4 + 16);
        if (i3 == 1835365492) {
            parsableByteArray.readNullTerminatedString();
            String readNullTerminatedString = parsableByteArray.readNullTerminatedString();
            if (readNullTerminatedString != null) {
                stsdData.format = new Format.Builder().setId(i5).setSampleMimeType(readNullTerminatedString).build();
            }
        }
    }

    private static long parseMvhd(ParsableByteArray parsableByteArray) {
        int i3 = 8;
        parsableByteArray.setPosition(8);
        if (Atom.parseFullAtomVersion(parsableByteArray.readInt()) != 0) {
            i3 = 16;
        }
        parsableByteArray.skipBytes(i3);
        return parsableByteArray.readUnsignedInt();
    }

    private static float parsePaspFromParent(ParsableByteArray parsableByteArray, int i3) {
        parsableByteArray.setPosition(i3 + 8);
        return ((float) parsableByteArray.readUnsignedIntToInt()) / ((float) parsableByteArray.readUnsignedIntToInt());
    }

    @Nullable
    private static byte[] parseProjFromParent(ParsableByteArray parsableByteArray, int i3, int i4) {
        int i5 = i3 + 8;
        while (i5 - i3 < i4) {
            parsableByteArray.setPosition(i5);
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == 1886547818) {
                return Arrays.copyOfRange(parsableByteArray.getData(), i5, readInt + i5);
            }
            i5 += readInt;
        }
        return null;
    }

    @Nullable
    private static Pair<Integer, TrackEncryptionBox> parseSampleEntryEncryptionData(ParsableByteArray parsableByteArray, int i3, int i4) throws ParserException {
        Pair<Integer, TrackEncryptionBox> parseCommonEncryptionSinfFromParent;
        int position = parsableByteArray.getPosition();
        while (position - i3 < i4) {
            parsableByteArray.setPosition(position);
            int readInt = parsableByteArray.readInt();
            ExtractorUtil.checkContainerInput(readInt > 0, "childAtomSize must be positive");
            if (parsableByteArray.readInt() == 1936289382 && (parseCommonEncryptionSinfFromParent = parseCommonEncryptionSinfFromParent(parsableByteArray, position, readInt)) != null) {
                return parseCommonEncryptionSinfFromParent;
            }
            position += readInt;
        }
        return null;
    }

    @Nullable
    private static TrackEncryptionBox parseSchiFromParent(ParsableByteArray parsableByteArray, int i3, int i4, String str) {
        int i5;
        int i6;
        int i7 = i3 + 8;
        while (true) {
            byte[] bArr = null;
            if (i7 - i3 >= i4) {
                return null;
            }
            parsableByteArray.setPosition(i7);
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == 1952804451) {
                int parseFullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
                parsableByteArray.skipBytes(1);
                if (parseFullAtomVersion == 0) {
                    parsableByteArray.skipBytes(1);
                    i6 = 0;
                    i5 = 0;
                } else {
                    int readUnsignedByte = parsableByteArray.readUnsignedByte();
                    i5 = readUnsignedByte & 15;
                    i6 = (readUnsignedByte & 240) >> 4;
                }
                boolean z2 = parsableByteArray.readUnsignedByte() == 1;
                int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
                byte[] bArr2 = new byte[16];
                parsableByteArray.readBytes(bArr2, 0, 16);
                if (z2 && readUnsignedByte2 == 0) {
                    int readUnsignedByte3 = parsableByteArray.readUnsignedByte();
                    bArr = new byte[readUnsignedByte3];
                    parsableByteArray.readBytes(bArr, 0, readUnsignedByte3);
                }
                return new TrackEncryptionBox(z2, str, readUnsignedByte2, bArr2, i6, i5, bArr);
            }
            i7 += readInt;
        }
    }

    @Nullable
    private static Metadata parseSmta(ParsableByteArray parsableByteArray, int i3) {
        parsableByteArray.skipBytes(12);
        while (parsableByteArray.getPosition() < i3) {
            int position = parsableByteArray.getPosition();
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() != 1935766900) {
                parsableByteArray.setPosition(position + readInt);
            } else if (readInt < 14) {
                return null;
            } else {
                parsableByteArray.skipBytes(5);
                int readUnsignedByte = parsableByteArray.readUnsignedByte();
                if (readUnsignedByte != 12 && readUnsignedByte != 13) {
                    return null;
                }
                float f2 = readUnsignedByte == 12 ? 240.0f : 120.0f;
                parsableByteArray.skipBytes(1);
                return new Metadata(new SmtaMetadataEntry(f2, parsableByteArray.readUnsignedByte()));
            }
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:107:0x0286  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0289  */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x036f  */
    /* JADX WARNING: Removed duplicated region for block: B:145:0x03ab  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.TrackSampleTable parseStbl(com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.Track r38, com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.Atom.ContainerAtom r39, com.appsamurai.storyly.exoplayer2.extractor.extractor.GaplessInfoHolder r40) throws com.appsamurai.storyly.exoplayer2.common.ParserException {
        /*
            r1 = r38
            r0 = r39
            r2 = r40
            r3 = 1937011578(0x7374737a, float:1.936741E31)
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.Atom$LeafAtom r3 = r0.getLeafAtomOfType(r3)
            if (r3 == 0) goto L_0x0017
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.AtomParsers$StszSampleSizeBox r5 = new com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.AtomParsers$StszSampleSizeBox
            com.appsamurai.storyly.exoplayer2.common.Format r6 = r1.format
            r5.<init>(r3, r6)
            goto L_0x0025
        L_0x0017:
            r3 = 1937013298(0x73747a32, float:1.9369489E31)
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.Atom$LeafAtom r3 = r0.getLeafAtomOfType(r3)
            if (r3 == 0) goto L_0x0514
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.AtomParsers$Stz2SampleSizeBox r5 = new com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.AtomParsers$Stz2SampleSizeBox
            r5.<init>(r3)
        L_0x0025:
            int r3 = r5.getSampleCount()
            r6 = 0
            if (r3 != 0) goto L_0x0040
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.TrackSampleTable r9 = new com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.TrackSampleTable
            long[] r2 = new long[r6]
            int[] r3 = new int[r6]
            long[] r5 = new long[r6]
            int[] r6 = new int[r6]
            r7 = 0
            r4 = 0
            r0 = r9
            r1 = r38
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            return r9
        L_0x0040:
            r7 = 1937007471(0x7374636f, float:1.9362445E31)
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.Atom$LeafAtom r7 = r0.getLeafAtomOfType(r7)
            r8 = 1
            if (r7 != 0) goto L_0x0059
            r7 = 1668232756(0x636f3634, float:4.4126776E21)
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.Atom$LeafAtom r7 = r0.getLeafAtomOfType(r7)
            java.lang.Object r7 = com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkNotNull(r7)
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.Atom$LeafAtom r7 = (com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.Atom.LeafAtom) r7
            r9 = r8
            goto L_0x005a
        L_0x0059:
            r9 = r6
        L_0x005a:
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r7 = r7.data
            r10 = 1937011555(0x73747363, float:1.9367382E31)
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.Atom$LeafAtom r10 = r0.getLeafAtomOfType(r10)
            java.lang.Object r10 = com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkNotNull(r10)
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.Atom$LeafAtom r10 = (com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.Atom.LeafAtom) r10
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r10 = r10.data
            r11 = 1937011827(0x73747473, float:1.9367711E31)
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.Atom$LeafAtom r11 = r0.getLeafAtomOfType(r11)
            java.lang.Object r11 = com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkNotNull(r11)
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.Atom$LeafAtom r11 = (com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.Atom.LeafAtom) r11
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r11 = r11.data
            r12 = 1937011571(0x73747373, float:1.9367401E31)
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.Atom$LeafAtom r12 = r0.getLeafAtomOfType(r12)
            if (r12 == 0) goto L_0x0086
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r12 = r12.data
            goto L_0x0087
        L_0x0086:
            r12 = 0
        L_0x0087:
            r13 = 1668576371(0x63747473, float:4.5093966E21)
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.Atom$LeafAtom r0 = r0.getLeafAtomOfType(r13)
            if (r0 == 0) goto L_0x0093
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r0 = r0.data
            goto L_0x0094
        L_0x0093:
            r0 = 0
        L_0x0094:
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.AtomParsers$ChunkIterator r13 = new com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.AtomParsers$ChunkIterator
            r13.<init>(r10, r7, r9)
            r7 = 12
            r11.setPosition(r7)
            int r9 = r11.readUnsignedIntToInt()
            int r9 = r9 - r8
            int r10 = r11.readUnsignedIntToInt()
            int r14 = r11.readUnsignedIntToInt()
            if (r0 == 0) goto L_0x00b5
            r0.setPosition(r7)
            int r15 = r0.readUnsignedIntToInt()
            goto L_0x00b6
        L_0x00b5:
            r15 = r6
        L_0x00b6:
            r4 = -1
            if (r12 == 0) goto L_0x00cd
            r12.setPosition(r7)
            int r7 = r12.readUnsignedIntToInt()
            if (r7 <= 0) goto L_0x00c9
            int r16 = r12.readUnsignedIntToInt()
            int r16 = r16 + -1
            goto L_0x00d0
        L_0x00c9:
            r16 = r4
            r12 = 0
            goto L_0x00d0
        L_0x00cd:
            r16 = r4
            r7 = r6
        L_0x00d0:
            int r6 = r5.getFixedSampleSize()
            com.appsamurai.storyly.exoplayer2.common.Format r8 = r1.format
            java.lang.String r8 = r8.sampleMimeType
            if (r6 == r4) goto L_0x00fc
            java.lang.String r4 = "audio/raw"
            boolean r4 = r4.equals(r8)
            if (r4 != 0) goto L_0x00f2
            java.lang.String r4 = "audio/g711-mlaw"
            boolean r4 = r4.equals(r8)
            if (r4 != 0) goto L_0x00f2
            java.lang.String r4 = "audio/g711-alaw"
            boolean r4 = r4.equals(r8)
            if (r4 == 0) goto L_0x00fc
        L_0x00f2:
            if (r9 != 0) goto L_0x00fc
            if (r15 != 0) goto L_0x00fc
            if (r7 != 0) goto L_0x00fc
            r39 = r7
            r4 = 1
            goto L_0x00ff
        L_0x00fc:
            r39 = r7
            r4 = 0
        L_0x00ff:
            if (r4 == 0) goto L_0x0133
            int r0 = r13.length
            long[] r4 = new long[r0]
            int[] r0 = new int[r0]
        L_0x0107:
            boolean r5 = r13.moveNext()
            if (r5 == 0) goto L_0x0118
            int r5 = r13.index
            long r9 = r13.offset
            r4[r5] = r9
            int r9 = r13.numSamples
            r0[r5] = r9
            goto L_0x0107
        L_0x0118:
            long r9 = (long) r14
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.FixedSampleSizeRechunker$Results r0 = com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.FixedSampleSizeRechunker.rechunk(r6, r4, r0, r9)
            long[] r4 = r0.offsets
            int[] r5 = r0.sizes
            int r6 = r0.maximumSize
            long[] r9 = r0.timestamps
            int[] r10 = r0.flags
            long r11 = r0.duration
            r14 = r1
            r0 = r3
            r2 = r4
            r3 = r5
            r4 = r6
            r13 = r10
            r15 = r11
            r12 = r9
            goto L_0x029c
        L_0x0133:
            long[] r4 = new long[r3]
            int[] r6 = new int[r3]
            long[] r7 = new long[r3]
            int[] r8 = new int[r3]
            r24 = r11
            r2 = r16
            r1 = 0
            r11 = 0
            r21 = 0
            r22 = 0
            r23 = 0
            r25 = 0
            r27 = 0
            r16 = r15
            r15 = r14
            r14 = r10
            r37 = r9
            r9 = r39
        L_0x0153:
            r39 = r37
            java.lang.String r10 = "AtomParsers"
            if (r1 >= r3) goto L_0x0218
            r28 = r27
            r27 = r22
            r22 = 1
        L_0x015f:
            if (r27 != 0) goto L_0x017c
            boolean r22 = r13.moveNext()
            if (r22 == 0) goto L_0x017c
            r30 = r14
            r31 = r15
            long r14 = r13.offset
            r32 = r3
            int r3 = r13.numSamples
            r27 = r3
            r28 = r14
            r14 = r30
            r15 = r31
            r3 = r32
            goto L_0x015f
        L_0x017c:
            r32 = r3
            r30 = r14
            r31 = r15
            if (r22 != 0) goto L_0x01a0
            java.lang.String r2 = "Unexpected end of chunk data"
            com.appsamurai.storyly.exoplayer2.common.util.Log.w(r10, r2)
            long[] r4 = java.util.Arrays.copyOf(r4, r1)
            int[] r6 = java.util.Arrays.copyOf(r6, r1)
            long[] r7 = java.util.Arrays.copyOf(r7, r1)
            int[] r8 = java.util.Arrays.copyOf(r8, r1)
            r3 = r1
            r2 = r21
            r1 = r27
            goto L_0x0220
        L_0x01a0:
            if (r0 == 0) goto L_0x01b3
        L_0x01a2:
            if (r23 != 0) goto L_0x01b1
            if (r16 <= 0) goto L_0x01b1
            int r23 = r0.readUnsignedIntToInt()
            int r21 = r0.readInt()
            int r16 = r16 + -1
            goto L_0x01a2
        L_0x01b1:
            int r23 = r23 + -1
        L_0x01b3:
            r3 = r21
            r4[r1] = r28
            int r10 = r5.readNextSampleSize()
            r6[r1] = r10
            if (r10 <= r11) goto L_0x01c0
            r11 = r10
        L_0x01c0:
            long r14 = (long) r3
            long r14 = r25 + r14
            r7[r1] = r14
            if (r12 != 0) goto L_0x01c9
            r10 = 1
            goto L_0x01ca
        L_0x01c9:
            r10 = 0
        L_0x01ca:
            r8[r1] = r10
            if (r1 != r2) goto L_0x01e0
            r10 = 1
            r8[r1] = r10
            int r9 = r9 + -1
            if (r9 <= 0) goto L_0x01e0
            java.lang.Object r2 = com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkNotNull(r12)
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r2 = (com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray) r2
            int r2 = r2.readUnsignedIntToInt()
            int r2 = r2 - r10
        L_0x01e0:
            r15 = r2
            r10 = r3
            r14 = r31
            long r2 = (long) r14
            long r25 = r25 + r2
            int r2 = r30 + -1
            if (r2 != 0) goto L_0x01fa
            if (r39 <= 0) goto L_0x01fa
            int r2 = r24.readUnsignedIntToInt()
            int r3 = r24.readInt()
            int r14 = r39 + -1
        L_0x01f7:
            r39 = r2
            goto L_0x01fe
        L_0x01fa:
            r3 = r14
            r14 = r39
            goto L_0x01f7
        L_0x01fe:
            r2 = r6[r1]
            r21 = r3
            long r2 = (long) r2
            long r2 = r28 + r2
            int r22 = r27 + -1
            int r1 = r1 + 1
            r27 = r2
            r2 = r15
            r15 = r21
            r3 = r32
            r21 = r10
            r37 = r14
            r14 = r39
            goto L_0x0153
        L_0x0218:
            r32 = r3
            r30 = r14
            r2 = r21
            r1 = r22
        L_0x0220:
            long r12 = (long) r2
            long r12 = r25 + r12
            if (r0 == 0) goto L_0x0235
        L_0x0225:
            if (r16 <= 0) goto L_0x0235
            int r2 = r0.readUnsignedIntToInt()
            if (r2 == 0) goto L_0x022f
            r0 = 0
            goto L_0x0236
        L_0x022f:
            r0.readInt()
            int r16 = r16 + -1
            goto L_0x0225
        L_0x0235:
            r0 = 1
        L_0x0236:
            if (r9 != 0) goto L_0x0248
            if (r30 != 0) goto L_0x0248
            if (r1 != 0) goto L_0x0248
            if (r39 != 0) goto L_0x0248
            r2 = r23
            if (r2 != 0) goto L_0x024a
            if (r0 != 0) goto L_0x0245
            goto L_0x024a
        L_0x0245:
            r14 = r38
            goto L_0x0295
        L_0x0248:
            r2 = r23
        L_0x024a:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r14 = "Inconsistent stbl box for track "
            r5.<init>(r14)
            r14 = r38
            int r15 = r14.id
            r5.append(r15)
            java.lang.String r15 = ": remainingSynchronizationSamples "
            r5.append(r15)
            r5.append(r9)
            java.lang.String r9 = ", remainingSamplesAtTimestampDelta "
            r5.append(r9)
            r9 = r30
            r5.append(r9)
            java.lang.String r9 = ", remainingSamplesInChunk "
            r5.append(r9)
            r5.append(r1)
            java.lang.String r1 = ", remainingTimestampDeltaChanges "
            r5.append(r1)
            r9 = r39
            r5.append(r9)
            java.lang.String r1 = ", remainingSamplesAtTimestampOffset "
            r5.append(r1)
            r5.append(r2)
            if (r0 != 0) goto L_0x0289
            java.lang.String r0 = ", ctts invalid"
            goto L_0x028b
        L_0x0289:
            java.lang.String r0 = ""
        L_0x028b:
            r5.append(r0)
            java.lang.String r0 = r5.toString()
            com.appsamurai.storyly.exoplayer2.common.util.Log.w(r10, r0)
        L_0x0295:
            r0 = r3
            r2 = r4
            r3 = r6
            r4 = r11
            r15 = r12
            r12 = r7
            r13 = r8
        L_0x029c:
            r7 = 1000000(0xf4240, double:4.940656E-318)
            long r9 = r14.timescale
            r5 = r15
            long r7 = com.appsamurai.storyly.exoplayer2.common.util.Util.scaleLargeTimestamp(r5, r7, r9)
            long[] r1 = r14.editListDurations
            r10 = 1000000(0xf4240, double:4.940656E-318)
            if (r1 != 0) goto L_0x02bd
            long r0 = r14.timescale
            com.appsamurai.storyly.exoplayer2.common.util.Util.scaleLargeTimestampsInPlace(r12, r10, r0)
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.TrackSampleTable r9 = new com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.TrackSampleTable
            r0 = r9
            r1 = r38
            r5 = r12
            r6 = r13
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            return r9
        L_0x02bd:
            int r1 = r1.length
            r5 = 1
            if (r1 != r5) goto L_0x035d
            int r1 = r14.type
            if (r1 != r5) goto L_0x035d
            int r1 = r12.length
            r5 = 2
            if (r1 < r5) goto L_0x035d
            long[] r1 = r14.editListMediaTimes
            java.lang.Object r1 = com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkNotNull(r1)
            long[] r1 = (long[]) r1
            r5 = 0
            r21 = r1[r5]
            long[] r1 = r14.editListDurations
            r23 = r1[r5]
            long r5 = r14.timescale
            long r7 = r14.movieTimescale
            r25 = r5
            r27 = r7
            long r5 = com.appsamurai.storyly.exoplayer2.common.util.Util.scaleLargeTimestamp(r23, r25, r27)
            long r23 = r21 + r5
            r5 = r12
            r6 = r15
            r8 = r21
            r25 = r0
            r0 = r10
            r10 = r23
            boolean r5 = canApplyEditWithGaplessInfo(r5, r6, r8, r10)
            if (r5 == 0) goto L_0x035a
            long r6 = r15 - r23
            r5 = 0
            r8 = r12[r5]
            long r26 = r21 - r8
            com.appsamurai.storyly.exoplayer2.common.Format r5 = r14.format
            int r5 = r5.sampleRate
            long r8 = (long) r5
            long r10 = r14.timescale
            r28 = r8
            r30 = r10
            long r10 = com.appsamurai.storyly.exoplayer2.common.util.Util.scaleLargeTimestamp(r26, r28, r30)
            com.appsamurai.storyly.exoplayer2.common.Format r5 = r14.format
            int r5 = r5.sampleRate
            long r8 = (long) r5
            long r0 = r14.timescale
            r39 = r4
            r4 = r10
            r10 = r0
            long r0 = com.appsamurai.storyly.exoplayer2.common.util.Util.scaleLargeTimestamp(r6, r8, r10)
            r6 = 0
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x0324
            int r8 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x0360
        L_0x0324:
            r6 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 > 0) goto L_0x0360
            int r6 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r6 > 0) goto L_0x0360
            int r4 = (int) r4
            r5 = r40
            r5.encoderDelay = r4
            int r0 = (int) r0
            r5.encoderPadding = r0
            long r0 = r14.timescale
            r4 = 1000000(0xf4240, double:4.940656E-318)
            com.appsamurai.storyly.exoplayer2.common.util.Util.scaleLargeTimestampsInPlace(r12, r4, r0)
            long[] r0 = r14.editListDurations
            r1 = 0
            r4 = r0[r1]
            r6 = 1000000(0xf4240, double:4.940656E-318)
            long r8 = r14.movieTimescale
            long r7 = com.appsamurai.storyly.exoplayer2.common.util.Util.scaleLargeTimestamp(r4, r6, r8)
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.TrackSampleTable r9 = new com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.TrackSampleTable
            r0 = r9
            r1 = r38
            r4 = r39
            r5 = r12
            r6 = r13
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            return r9
        L_0x035a:
            r39 = r4
            goto L_0x0360
        L_0x035d:
            r25 = r0
            goto L_0x035a
        L_0x0360:
            long[] r0 = r14.editListDurations
            int r1 = r0.length
            r4 = 1
            if (r1 != r4) goto L_0x03ab
            r1 = 0
            r4 = r0[r1]
            r6 = 0
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 != 0) goto L_0x03ab
            long[] r0 = r14.editListMediaTimes
            java.lang.Object r0 = com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkNotNull(r0)
            long[] r0 = (long[]) r0
            r4 = r0[r1]
            r6 = 0
        L_0x037a:
            int r0 = r12.length
            if (r6 >= r0) goto L_0x0391
            r0 = r12[r6]
            long r17 = r0 - r4
            r19 = 1000000(0xf4240, double:4.940656E-318)
            long r0 = r14.timescale
            r21 = r0
            long r0 = com.appsamurai.storyly.exoplayer2.common.util.Util.scaleLargeTimestamp(r17, r19, r21)
            r12[r6] = r0
            int r6 = r6 + 1
            goto L_0x037a
        L_0x0391:
            long r17 = r15 - r4
            r19 = 1000000(0xf4240, double:4.940656E-318)
            long r0 = r14.timescale
            r21 = r0
            long r7 = com.appsamurai.storyly.exoplayer2.common.util.Util.scaleLargeTimestamp(r17, r19, r21)
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.TrackSampleTable r9 = new com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.TrackSampleTable
            r0 = r9
            r1 = r38
            r4 = r39
            r5 = r12
            r6 = r13
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            return r9
        L_0x03ab:
            int r1 = r14.type
            r4 = 1
            if (r1 != r4) goto L_0x03b2
            r10 = 1
            goto L_0x03b3
        L_0x03b2:
            r10 = 0
        L_0x03b3:
            int r1 = r0.length
            int[] r1 = new int[r1]
            int r0 = r0.length
            int[] r0 = new int[r0]
            long[] r4 = r14.editListMediaTimes
            java.lang.Object r4 = com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkNotNull(r4)
            long[] r4 = (long[]) r4
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
        L_0x03c5:
            long[] r9 = r14.editListDurations
            int r11 = r9.length
            if (r5 >= r11) goto L_0x0426
            r11 = r2
            r15 = r3
            r2 = r4[r5]
            r21 = -1
            int r16 = (r2 > r21 ? 1 : (r2 == r21 ? 0 : -1))
            if (r16 == 0) goto L_0x0415
            r26 = r9[r5]
            r16 = r8
            long r8 = r14.timescale
            r40 = r6
            r21 = r7
            long r6 = r14.movieTimescale
            r28 = r8
            r30 = r6
            long r6 = com.appsamurai.storyly.exoplayer2.common.util.Util.scaleLargeTimestamp(r26, r28, r30)
            r8 = 1
            int r9 = com.appsamurai.storyly.exoplayer2.common.util.Util.binarySearchFloor((long[]) r12, (long) r2, (boolean) r8, (boolean) r8)
            r1[r5] = r9
            long r2 = r2 + r6
            r6 = 0
            int r2 = com.appsamurai.storyly.exoplayer2.common.util.Util.binarySearchCeil((long[]) r12, (long) r2, (boolean) r10, (boolean) r6)
            r0[r5] = r2
        L_0x03f7:
            r2 = r1[r5]
            r3 = r0[r5]
            if (r2 >= r3) goto L_0x0407
            r7 = r13[r2]
            r7 = r7 & r8
            if (r7 != 0) goto L_0x0407
            int r2 = r2 + 1
            r1[r5] = r2
            goto L_0x03f7
        L_0x0407:
            int r7 = r3 - r2
            int r7 = r7 + r21
            r9 = r16
            if (r9 == r2) goto L_0x0411
            r2 = r8
            goto L_0x0412
        L_0x0411:
            r2 = r6
        L_0x0412:
            r2 = r40 | r2
            goto L_0x041f
        L_0x0415:
            r40 = r6
            r21 = r7
            r9 = r8
            r6 = 0
            r8 = 1
            r2 = r40
            r3 = r9
        L_0x041f:
            int r5 = r5 + 1
            r6 = r2
            r8 = r3
            r2 = r11
            r3 = r15
            goto L_0x03c5
        L_0x0426:
            r11 = r2
            r15 = r3
            r40 = r6
            r3 = r25
            r6 = 0
            r8 = 1
            if (r7 == r3) goto L_0x0431
            goto L_0x0432
        L_0x0431:
            r8 = r6
        L_0x0432:
            r2 = r40 | r8
            if (r2 == 0) goto L_0x0439
            long[] r3 = new long[r7]
            goto L_0x043a
        L_0x0439:
            r3 = r11
        L_0x043a:
            if (r2 == 0) goto L_0x043f
            int[] r4 = new int[r7]
            goto L_0x0440
        L_0x043f:
            r4 = r15
        L_0x0440:
            if (r2 == 0) goto L_0x0444
            r5 = r6
            goto L_0x0446
        L_0x0444:
            r5 = r39
        L_0x0446:
            if (r2 == 0) goto L_0x044b
            int[] r8 = new int[r7]
            goto L_0x044c
        L_0x044b:
            r8 = r13
        L_0x044c:
            long[] r7 = new long[r7]
            r40 = r5
            r39 = r15
            r9 = 0
            r15 = r6
        L_0x0455:
            long[] r5 = r14.editListDurations
            int r5 = r5.length
            if (r6 >= r5) goto L_0x04f4
            long[] r5 = r14.editListMediaTimes
            r16 = r5[r6]
            r5 = r1[r6]
            r18 = r1
            r1 = r0[r6]
            r27 = r0
            if (r2 == 0) goto L_0x0478
            int r0 = r1 - r5
            java.lang.System.arraycopy(r11, r5, r3, r15, r0)
            r28 = r11
            r11 = r39
            java.lang.System.arraycopy(r11, r5, r4, r15, r0)
            java.lang.System.arraycopy(r13, r5, r8, r15, r0)
            goto L_0x047c
        L_0x0478:
            r28 = r11
            r11 = r39
        L_0x047c:
            r0 = r40
        L_0x047e:
            if (r5 >= r1) goto L_0x04ce
            r23 = 1000000(0xf4240, double:4.940656E-318)
            r29 = r0
            r39 = r1
            long r0 = r14.movieTimescale
            r21 = r9
            r25 = r0
            long r0 = com.appsamurai.storyly.exoplayer2.common.util.Util.scaleLargeTimestamp(r21, r23, r25)
            r21 = r12[r5]
            r23 = r12
            r24 = r13
            long r12 = r21 - r16
            r30 = r8
            r21 = r9
            r8 = 0
            long r31 = java.lang.Math.max(r8, r12)
            r33 = 1000000(0xf4240, double:4.940656E-318)
            long r12 = r14.timescale
            r35 = r12
            long r12 = com.appsamurai.storyly.exoplayer2.common.util.Util.scaleLargeTimestamp(r31, r33, r35)
            long r0 = r0 + r12
            r7[r15] = r0
            if (r2 == 0) goto L_0x04bc
            r0 = r4[r15]
            r1 = r29
            if (r0 <= r1) goto L_0x04be
            r0 = r11[r5]
            goto L_0x04bf
        L_0x04bc:
            r1 = r29
        L_0x04be:
            r0 = r1
        L_0x04bf:
            int r15 = r15 + 1
            int r5 = r5 + 1
            r1 = r39
            r9 = r21
            r12 = r23
            r13 = r24
            r8 = r30
            goto L_0x047e
        L_0x04ce:
            r1 = r0
            r30 = r8
            r21 = r9
            r23 = r12
            r24 = r13
            r8 = 0
            long[] r0 = r14.editListDurations
            r12 = r0[r6]
            long r12 = r21 + r12
            int r6 = r6 + 1
            r40 = r1
            r39 = r11
            r9 = r12
            r1 = r18
            r12 = r23
            r13 = r24
            r0 = r27
            r11 = r28
            r8 = r30
            goto L_0x0455
        L_0x04f4:
            r30 = r8
            r21 = r9
            r23 = 1000000(0xf4240, double:4.940656E-318)
            long r0 = r14.movieTimescale
            r25 = r0
            long r8 = com.appsamurai.storyly.exoplayer2.common.util.Util.scaleLargeTimestamp(r21, r23, r25)
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.TrackSampleTable r10 = new com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.TrackSampleTable
            r0 = r10
            r1 = r38
            r2 = r3
            r3 = r4
            r4 = r40
            r5 = r7
            r6 = r30
            r7 = r8
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            return r10
        L_0x0514:
            java.lang.String r0 = "Track has no sample table size information"
            r1 = 0
            com.appsamurai.storyly.exoplayer2.common.ParserException r0 = com.appsamurai.storyly.exoplayer2.common.ParserException.createForMalformedContainer(r0, r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.AtomParsers.parseStbl(com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.Track, com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.Atom$ContainerAtom, com.appsamurai.storyly.exoplayer2.extractor.extractor.GaplessInfoHolder):com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.TrackSampleTable");
    }

    private static StsdData parseStsd(ParsableByteArray parsableByteArray, int i3, int i4, String str, @Nullable DrmInitData drmInitData, boolean z2) throws ParserException {
        int i5;
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        int i6 = i3;
        parsableByteArray2.setPosition(12);
        int readInt = parsableByteArray.readInt();
        StsdData stsdData = new StsdData(readInt);
        for (int i7 = 0; i7 < readInt; i7++) {
            int position = parsableByteArray.getPosition();
            int readInt2 = parsableByteArray.readInt();
            ExtractorUtil.checkContainerInput(readInt2 > 0, "childAtomSize must be positive");
            int readInt3 = parsableByteArray.readInt();
            if (readInt3 == 1635148593 || readInt3 == 1635148595 || readInt3 == 1701733238 || readInt3 == 1831958048 || readInt3 == 1836070006 || readInt3 == 1752589105 || readInt3 == 1751479857 || readInt3 == 1932670515 || readInt3 == 1211250227 || readInt3 == 1987063864 || readInt3 == 1987063865 || readInt3 == 1635135537 || readInt3 == 1685479798 || readInt3 == 1685479729 || readInt3 == 1685481573 || readInt3 == 1685481521) {
                i5 = position;
                parseVideoSampleEntry(parsableByteArray, readInt3, i5, readInt2, i3, i4, drmInitData, stsdData, i7);
            } else if (readInt3 == 1836069985 || readInt3 == 1701733217 || readInt3 == 1633889587 || readInt3 == 1700998451 || readInt3 == 1633889588 || readInt3 == 1835823201 || readInt3 == 1685353315 || readInt3 == 1685353317 || readInt3 == 1685353320 || readInt3 == 1685353324 || readInt3 == 1685353336 || readInt3 == 1935764850 || readInt3 == 1935767394 || readInt3 == 1819304813 || readInt3 == 1936684916 || readInt3 == 1953984371 || readInt3 == 778924082 || readInt3 == 778924083 || readInt3 == 1835557169 || readInt3 == 1835560241 || readInt3 == 1634492771 || readInt3 == 1634492791 || readInt3 == 1970037111 || readInt3 == 1332770163 || readInt3 == 1716281667) {
                i5 = position;
                parseAudioSampleEntry(parsableByteArray, readInt3, position, readInt2, i3, str, z2, drmInitData, stsdData, i7);
            } else {
                if (readInt3 == 1414810956 || readInt3 == 1954034535 || readInt3 == 2004251764 || readInt3 == 1937010800 || readInt3 == 1664495672) {
                    parseTextSampleEntry(parsableByteArray, readInt3, position, readInt2, i3, str, stsdData);
                } else if (readInt3 == 1835365492) {
                    parseMetaDataSampleEntry(parsableByteArray2, readInt3, position, i6, stsdData);
                } else if (readInt3 == 1667329389) {
                    stsdData.format = new Format.Builder().setId(i6).setSampleMimeType(MimeTypes.APPLICATION_CAMERA_MOTION).build();
                }
                i5 = position;
            }
            parsableByteArray2.setPosition(i5 + readInt2);
        }
        return stsdData;
    }

    private static void parseTextSampleEntry(ParsableByteArray parsableByteArray, int i3, int i4, int i5, int i6, String str, StsdData stsdData) {
        parsableByteArray.setPosition(i4 + 16);
        String str2 = MimeTypes.APPLICATION_TTML;
        ImmutableList immutableList = null;
        long j2 = Long.MAX_VALUE;
        if (i3 != 1414810956) {
            if (i3 == 1954034535) {
                int i7 = i5 - 16;
                byte[] bArr = new byte[i7];
                parsableByteArray.readBytes(bArr, 0, i7);
                immutableList = ImmutableList.of(bArr);
                str2 = MimeTypes.APPLICATION_TX3G;
            } else if (i3 == 2004251764) {
                str2 = MimeTypes.APPLICATION_MP4VTT;
            } else if (i3 == 1937010800) {
                j2 = 0;
            } else if (i3 == 1664495672) {
                stsdData.requiredSampleTransformation = 1;
                str2 = MimeTypes.APPLICATION_MP4CEA608;
            } else {
                throw new IllegalStateException();
            }
        }
        stsdData.format = new Format.Builder().setId(i6).setSampleMimeType(str2).setLanguage(str).setSubsampleOffsetUs(j2).setInitializationData(immutableList).build();
    }

    private static TkhdData parseTkhd(ParsableByteArray parsableByteArray) {
        long j2;
        int i3 = 8;
        parsableByteArray.setPosition(8);
        int parseFullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(parseFullAtomVersion == 0 ? 8 : 16);
        int readInt = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        int position = parsableByteArray.getPosition();
        if (parseFullAtomVersion == 0) {
            i3 = 4;
        }
        int i4 = 0;
        int i5 = 0;
        while (true) {
            j2 = C.TIME_UNSET;
            if (i5 >= i3) {
                parsableByteArray.skipBytes(i3);
                break;
            } else if (parsableByteArray.getData()[position + i5] != -1) {
                long readUnsignedInt = parseFullAtomVersion == 0 ? parsableByteArray.readUnsignedInt() : parsableByteArray.readUnsignedLongToLong();
                if (readUnsignedInt != 0) {
                    j2 = readUnsignedInt;
                }
            } else {
                i5++;
            }
        }
        parsableByteArray.skipBytes(16);
        int readInt2 = parsableByteArray.readInt();
        int readInt3 = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        int readInt4 = parsableByteArray.readInt();
        int readInt5 = parsableByteArray.readInt();
        if (readInt2 == 0 && readInt3 == 65536 && readInt4 == -65536 && readInt5 == 0) {
            i4 = 90;
        } else if (readInt2 == 0 && readInt3 == -65536 && readInt4 == 65536 && readInt5 == 0) {
            i4 = 270;
        } else if (readInt2 == -65536 && readInt3 == 0 && readInt4 == 0 && readInt5 == -65536) {
            i4 = 180;
        }
        return new TkhdData(readInt, j2, i4);
    }

    @Nullable
    private static Track parseTrak(Atom.ContainerAtom containerAtom, Atom.LeafAtom leafAtom, long j2, @Nullable DrmInitData drmInitData, boolean z2, boolean z3) throws ParserException {
        long j3;
        Atom.LeafAtom leafAtom2;
        long[] jArr;
        long[] jArr2;
        Atom.ContainerAtom containerAtomOfType;
        Pair<long[], long[]> parseEdts;
        Atom.ContainerAtom containerAtom2 = containerAtom;
        Atom.ContainerAtom containerAtom3 = (Atom.ContainerAtom) Assertions.checkNotNull(containerAtom2.getContainerAtomOfType(Atom.TYPE_mdia));
        int trackTypeForHdlr = getTrackTypeForHdlr(parseHdlr(((Atom.LeafAtom) Assertions.checkNotNull(containerAtom3.getLeafAtomOfType(Atom.TYPE_hdlr))).data));
        if (trackTypeForHdlr == -1) {
            return null;
        }
        TkhdData parseTkhd = parseTkhd(((Atom.LeafAtom) Assertions.checkNotNull(containerAtom2.getLeafAtomOfType(Atom.TYPE_tkhd))).data);
        long j4 = C.TIME_UNSET;
        if (j2 == C.TIME_UNSET) {
            leafAtom2 = leafAtom;
            j3 = parseTkhd.duration;
        } else {
            leafAtom2 = leafAtom;
            j3 = j2;
        }
        long parseMvhd = parseMvhd(leafAtom2.data);
        if (j3 != C.TIME_UNSET) {
            j4 = Util.scaleLargeTimestamp(j3, 1000000, parseMvhd);
        }
        long j5 = j4;
        Pair<Long, String> parseMdhd = parseMdhd(((Atom.LeafAtom) Assertions.checkNotNull(containerAtom3.getLeafAtomOfType(Atom.TYPE_mdhd))).data);
        StsdData parseStsd = parseStsd(((Atom.LeafAtom) Assertions.checkNotNull(((Atom.ContainerAtom) Assertions.checkNotNull(((Atom.ContainerAtom) Assertions.checkNotNull(containerAtom3.getContainerAtomOfType(Atom.TYPE_minf))).getContainerAtomOfType(Atom.TYPE_stbl))).getLeafAtomOfType(Atom.TYPE_stsd))).data, parseTkhd.id, parseTkhd.rotationDegrees, (String) parseMdhd.second, drmInitData, z3);
        if (z2 || (containerAtomOfType = containerAtom2.getContainerAtomOfType(Atom.TYPE_edts)) == null || (parseEdts = parseEdts(containerAtomOfType)) == null) {
            jArr2 = null;
            jArr = null;
        } else {
            jArr = (long[]) parseEdts.second;
            jArr2 = (long[]) parseEdts.first;
        }
        if (parseStsd.format == null) {
            return null;
        }
        return new Track(parseTkhd.id, trackTypeForHdlr, ((Long) parseMdhd.first).longValue(), parseMvhd, j5, parseStsd.format, parseStsd.requiredSampleTransformation, parseStsd.trackEncryptionBoxes, parseStsd.nalUnitLengthFieldLength, jArr2, jArr);
    }

    public static List<TrackSampleTable> parseTraks(Atom.ContainerAtom containerAtom, GaplessInfoHolder gaplessInfoHolder, long j2, @Nullable DrmInitData drmInitData, boolean z2, boolean z3, Function<Track, Track> function) throws ParserException {
        Atom.ContainerAtom containerAtom2 = containerAtom;
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < containerAtom2.containerChildren.size(); i3++) {
            Atom.ContainerAtom containerAtom3 = containerAtom2.containerChildren.get(i3);
            if (containerAtom3.type != 1953653099) {
                GaplessInfoHolder gaplessInfoHolder2 = gaplessInfoHolder;
                Function<Track, Track> function2 = function;
            } else {
                Track apply = function.apply(parseTrak(containerAtom3, (Atom.LeafAtom) Assertions.checkNotNull(containerAtom.getLeafAtomOfType(Atom.TYPE_mvhd)), j2, drmInitData, z2, z3));
                if (apply == null) {
                    GaplessInfoHolder gaplessInfoHolder3 = gaplessInfoHolder;
                } else {
                    GaplessInfoHolder gaplessInfoHolder4 = gaplessInfoHolder;
                    arrayList.add(parseStbl(apply, (Atom.ContainerAtom) Assertions.checkNotNull(((Atom.ContainerAtom) Assertions.checkNotNull(((Atom.ContainerAtom) Assertions.checkNotNull(containerAtom3.getContainerAtomOfType(Atom.TYPE_mdia))).getContainerAtomOfType(Atom.TYPE_minf))).getContainerAtomOfType(Atom.TYPE_stbl)), gaplessInfoHolder));
                }
            }
        }
        return arrayList;
    }

    public static Pair<Metadata, Metadata> parseUdta(Atom.LeafAtom leafAtom) {
        ParsableByteArray parsableByteArray = leafAtom.data;
        parsableByteArray.setPosition(8);
        Metadata metadata = null;
        Metadata metadata2 = null;
        while (parsableByteArray.bytesLeft() >= 8) {
            int position = parsableByteArray.getPosition();
            int readInt = parsableByteArray.readInt();
            int readInt2 = parsableByteArray.readInt();
            if (readInt2 == 1835365473) {
                parsableByteArray.setPosition(position);
                metadata = parseUdtaMeta(parsableByteArray, position + readInt);
            } else if (readInt2 == 1936553057) {
                parsableByteArray.setPosition(position);
                metadata2 = parseSmta(parsableByteArray, position + readInt);
            }
            parsableByteArray.setPosition(position + readInt);
        }
        return Pair.create(metadata, metadata2);
    }

    @Nullable
    private static Metadata parseUdtaMeta(ParsableByteArray parsableByteArray, int i3) {
        parsableByteArray.skipBytes(8);
        maybeSkipRemainingMetaAtomHeaderBytes(parsableByteArray);
        while (parsableByteArray.getPosition() < i3) {
            int position = parsableByteArray.getPosition();
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == 1768715124) {
                parsableByteArray.setPosition(position);
                return parseIlst(parsableByteArray, position + readInt);
            }
            parsableByteArray.setPosition(position + readInt);
        }
        return null;
    }

    private static void parseVideoSampleEntry(ParsableByteArray parsableByteArray, int i3, int i4, int i5, int i6, int i7, @Nullable DrmInitData drmInitData, StsdData stsdData, int i8) throws ParserException {
        DrmInitData drmInitData2;
        int i9;
        int i10;
        float f2;
        byte[] bArr;
        List<byte[]> list;
        String str;
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        int i11 = i4;
        int i12 = i5;
        DrmInitData drmInitData3 = drmInitData;
        StsdData stsdData2 = stsdData;
        parsableByteArray2.setPosition(i11 + 16);
        parsableByteArray2.skipBytes(16);
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        int readUnsignedShort2 = parsableByteArray.readUnsignedShort();
        parsableByteArray2.skipBytes(50);
        int position = parsableByteArray.getPosition();
        int i13 = i3;
        if (i13 == 1701733238) {
            Pair<Integer, TrackEncryptionBox> parseSampleEntryEncryptionData = parseSampleEntryEncryptionData(parsableByteArray2, i11, i12);
            if (parseSampleEntryEncryptionData != null) {
                i13 = ((Integer) parseSampleEntryEncryptionData.first).intValue();
                drmInitData3 = drmInitData3 == null ? null : drmInitData3.copyWithSchemeType(((TrackEncryptionBox) parseSampleEntryEncryptionData.second).schemeType);
                stsdData2.trackEncryptionBoxes[i8] = (TrackEncryptionBox) parseSampleEntryEncryptionData.second;
            }
            parsableByteArray2.setPosition(position);
        }
        String str2 = MimeTypes.VIDEO_H263;
        String str3 = i13 == 1831958048 ? MimeTypes.VIDEO_MPEG : i13 == 1211250227 ? str2 : null;
        float f3 = 1.0f;
        byte[] bArr2 = null;
        String str4 = null;
        List<byte[]> list2 = null;
        int i14 = -1;
        int i15 = -1;
        int i16 = -1;
        int i17 = -1;
        ByteBuffer byteBuffer = null;
        EsdsData esdsData = null;
        boolean z2 = false;
        while (true) {
            if (position - i11 >= i12) {
                drmInitData2 = drmInitData3;
                break;
            }
            parsableByteArray2.setPosition(position);
            int position2 = parsableByteArray.getPosition();
            String str5 = str2;
            int readInt = parsableByteArray.readInt();
            if (readInt == 0) {
                drmInitData2 = drmInitData3;
                if (parsableByteArray.getPosition() - i11 == i12) {
                    break;
                }
            } else {
                drmInitData2 = drmInitData3;
            }
            ExtractorUtil.checkContainerInput(readInt > 0, "childAtomSize must be positive");
            int readInt2 = parsableByteArray.readInt();
            if (readInt2 == 1635148611) {
                ExtractorUtil.checkContainerInput(str3 == null, (String) null);
                parsableByteArray2.setPosition(position2 + 8);
                AvcConfig parse = AvcConfig.parse(parsableByteArray);
                list2 = parse.initializationData;
                stsdData2.nalUnitLengthFieldLength = parse.nalUnitLengthFieldLength;
                if (!z2) {
                    f3 = parse.pixelWidthHeightRatio;
                }
                str4 = parse.codecs;
                str = MimeTypes.VIDEO_H264;
            } else if (readInt2 == 1752589123) {
                ExtractorUtil.checkContainerInput(str3 == null, (String) null);
                parsableByteArray2.setPosition(position2 + 8);
                HevcConfig parse2 = HevcConfig.parse(parsableByteArray);
                list2 = parse2.initializationData;
                stsdData2.nalUnitLengthFieldLength = parse2.nalUnitLengthFieldLength;
                if (!z2) {
                    f3 = parse2.pixelWidthHeightRatio;
                }
                str4 = parse2.codecs;
                str = MimeTypes.VIDEO_H265;
            } else {
                if (readInt2 == 1685480259 || readInt2 == 1685485123) {
                    i9 = readUnsignedShort2;
                    i10 = i13;
                    bArr = bArr2;
                    f2 = f3;
                    list = list2;
                    DolbyVisionConfig parse3 = DolbyVisionConfig.parse(parsableByteArray);
                    if (parse3 != null) {
                        str4 = parse3.codecs;
                        str3 = MimeTypes.VIDEO_DOLBY_VISION;
                    }
                } else if (readInt2 == 1987076931) {
                    ExtractorUtil.checkContainerInput(str3 == null, (String) null);
                    str = i13 == 1987063864 ? MimeTypes.VIDEO_VP8 : MimeTypes.VIDEO_VP9;
                } else if (readInt2 == 1635135811) {
                    ExtractorUtil.checkContainerInput(str3 == null, (String) null);
                    str = MimeTypes.VIDEO_AV1;
                } else if (readInt2 == 1668050025) {
                    if (byteBuffer == null) {
                        byteBuffer = allocateHdrStaticInfo();
                    }
                    ByteBuffer byteBuffer2 = byteBuffer;
                    byteBuffer2.position(21);
                    byteBuffer2.putShort(parsableByteArray.readShort());
                    byteBuffer2.putShort(parsableByteArray.readShort());
                    byteBuffer = byteBuffer2;
                    i9 = readUnsignedShort2;
                    i10 = i13;
                    position += readInt;
                    i11 = i4;
                    i12 = i5;
                    stsdData2 = stsdData;
                    str2 = str5;
                    drmInitData3 = drmInitData2;
                    i13 = i10;
                    readUnsignedShort2 = i9;
                } else if (readInt2 == 1835295606) {
                    if (byteBuffer == null) {
                        byteBuffer = allocateHdrStaticInfo();
                    }
                    ByteBuffer byteBuffer3 = byteBuffer;
                    short readShort = parsableByteArray.readShort();
                    short readShort2 = parsableByteArray.readShort();
                    short readShort3 = parsableByteArray.readShort();
                    i10 = i13;
                    short readShort4 = parsableByteArray.readShort();
                    short readShort5 = parsableByteArray.readShort();
                    List<byte[]> list3 = list2;
                    short readShort6 = parsableByteArray.readShort();
                    byte[] bArr3 = bArr2;
                    short readShort7 = parsableByteArray.readShort();
                    float f4 = f3;
                    short readShort8 = parsableByteArray.readShort();
                    long readUnsignedInt = parsableByteArray.readUnsignedInt();
                    long readUnsignedInt2 = parsableByteArray.readUnsignedInt();
                    i9 = readUnsignedShort2;
                    byteBuffer3.position(1);
                    byteBuffer3.putShort(readShort5);
                    byteBuffer3.putShort(readShort6);
                    byteBuffer3.putShort(readShort);
                    byteBuffer3.putShort(readShort2);
                    byteBuffer3.putShort(readShort3);
                    byteBuffer3.putShort(readShort4);
                    byteBuffer3.putShort(readShort7);
                    byteBuffer3.putShort(readShort8);
                    byteBuffer3.putShort((short) ((int) (readUnsignedInt / 10000)));
                    byteBuffer3.putShort((short) ((int) (readUnsignedInt2 / 10000)));
                    byteBuffer = byteBuffer3;
                    list2 = list3;
                    bArr2 = bArr3;
                    f3 = f4;
                    position += readInt;
                    i11 = i4;
                    i12 = i5;
                    stsdData2 = stsdData;
                    str2 = str5;
                    drmInitData3 = drmInitData2;
                    i13 = i10;
                    readUnsignedShort2 = i9;
                } else {
                    i9 = readUnsignedShort2;
                    i10 = i13;
                    bArr = bArr2;
                    f2 = f3;
                    list = list2;
                    if (readInt2 == 1681012275) {
                        ExtractorUtil.checkContainerInput(str3 == null, (String) null);
                        str3 = str5;
                    } else if (readInt2 == 1702061171) {
                        ExtractorUtil.checkContainerInput(str3 == null, (String) null);
                        esdsData = parseEsdsFromParent(parsableByteArray2, position2);
                        String access$300 = esdsData.mimeType;
                        byte[] access$400 = esdsData.initializationData;
                        list2 = access$400 != null ? ImmutableList.of(access$400) : list;
                        str3 = access$300;
                        bArr2 = bArr;
                        f3 = f2;
                        position += readInt;
                        i11 = i4;
                        i12 = i5;
                        stsdData2 = stsdData;
                        str2 = str5;
                        drmInitData3 = drmInitData2;
                        i13 = i10;
                        readUnsignedShort2 = i9;
                    } else if (readInt2 == 1885434736) {
                        f3 = parsePaspFromParent(parsableByteArray2, position2);
                        list2 = list;
                        bArr2 = bArr;
                        z2 = true;
                        position += readInt;
                        i11 = i4;
                        i12 = i5;
                        stsdData2 = stsdData;
                        str2 = str5;
                        drmInitData3 = drmInitData2;
                        i13 = i10;
                        readUnsignedShort2 = i9;
                    } else if (readInt2 == 1937126244) {
                        bArr2 = parseProjFromParent(parsableByteArray2, position2, readInt);
                        list2 = list;
                        f3 = f2;
                        position += readInt;
                        i11 = i4;
                        i12 = i5;
                        stsdData2 = stsdData;
                        str2 = str5;
                        drmInitData3 = drmInitData2;
                        i13 = i10;
                        readUnsignedShort2 = i9;
                    } else if (readInt2 == 1936995172) {
                        int readUnsignedByte = parsableByteArray.readUnsignedByte();
                        parsableByteArray2.skipBytes(3);
                        if (readUnsignedByte == 0) {
                            int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
                            if (readUnsignedByte2 == 0) {
                                i14 = 0;
                            } else if (readUnsignedByte2 == 1) {
                                i14 = 1;
                            } else if (readUnsignedByte2 == 2) {
                                i14 = 2;
                            } else if (readUnsignedByte2 == 3) {
                                i14 = 3;
                            }
                        }
                    } else if (readInt2 == 1668246642) {
                        int readInt3 = parsableByteArray.readInt();
                        if (readInt3 == TYPE_nclx || readInt3 == TYPE_nclc) {
                            int readUnsignedShort3 = parsableByteArray.readUnsignedShort();
                            int readUnsignedShort4 = parsableByteArray.readUnsignedShort();
                            parsableByteArray2.skipBytes(2);
                            boolean z3 = readInt == 19 && (parsableByteArray.readUnsignedByte() & 128) != 0;
                            i15 = ColorInfo.isoColorPrimariesToColorSpace(readUnsignedShort3);
                            i16 = z3 ? 1 : 2;
                            i17 = ColorInfo.isoTransferCharacteristicsToColorTransfer(readUnsignedShort4);
                        } else {
                            Log.w(TAG, "Unsupported color type: " + Atom.getAtomTypeString(readInt3));
                        }
                    }
                }
                list2 = list;
                bArr2 = bArr;
                f3 = f2;
                position += readInt;
                i11 = i4;
                i12 = i5;
                stsdData2 = stsdData;
                str2 = str5;
                drmInitData3 = drmInitData2;
                i13 = i10;
                readUnsignedShort2 = i9;
            }
            str3 = str;
            i9 = readUnsignedShort2;
            i10 = i13;
            position += readInt;
            i11 = i4;
            i12 = i5;
            stsdData2 = stsdData;
            str2 = str5;
            drmInitData3 = drmInitData2;
            i13 = i10;
            readUnsignedShort2 = i9;
        }
        int i18 = readUnsignedShort2;
        byte[] bArr4 = bArr2;
        float f5 = f3;
        List<byte[]> list4 = list2;
        if (str3 != null) {
            Format.Builder drmInitData4 = new Format.Builder().setId(i6).setSampleMimeType(str3).setCodecs(str4).setWidth(readUnsignedShort).setHeight(i18).setPixelWidthHeightRatio(f5).setRotationDegrees(i7).setProjectionData(bArr4).setStereoMode(i14).setInitializationData(list4).setDrmInitData(drmInitData2);
            int i19 = i15;
            int i20 = i16;
            int i21 = i17;
            if (!(i19 == -1 && i20 == -1 && i21 == -1 && byteBuffer == null)) {
                drmInitData4.setColorInfo(new ColorInfo(i19, i20, i21, byteBuffer != null ? byteBuffer.array() : null));
            }
            if (esdsData != null) {
                drmInitData4.setAverageBitrate(esdsData.bitrate).setPeakBitrate(esdsData.peakBitrate);
            }
            stsdData.format = drmInitData4.build();
        }
    }
}
