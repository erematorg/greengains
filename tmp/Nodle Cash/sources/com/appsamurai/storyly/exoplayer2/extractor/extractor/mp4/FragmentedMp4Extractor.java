package com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4;

import android.util.Pair;
import android.util.SparseArray;
import androidx.annotation.Nullable;
import androidx.collection.SieveCacheKt;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.ParserException;
import com.appsamurai.storyly.exoplayer2.common.drm.DrmInitData;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.common.util.TimestampAdjuster;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ChunkIndex;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.Extractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorOutput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorsFactory;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.GaplessInfoHolder;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.PositionHolder;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.SeekMap;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.a;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.Atom;
import com.appsamurai.storyly.exoplayer2.extractor.metadata.emsg.EventMessageEncoder;
import com.appsamurai.storyly.exoplayer2.extractor.util.NalUnitUtil;
import com.fasterxml.jackson.dataformat.cbor.CBORConstants;
import com.google.common.base.Ascii;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class FragmentedMp4Extractor implements Extractor {
    private static final Format EMSG_FORMAT = new Format.Builder().setSampleMimeType(MimeTypes.APPLICATION_EMSG).build();
    private static final int EXTRA_TRACKS_BASE_ID = 100;
    public static final ExtractorsFactory FACTORY = new a(6);
    public static final int FLAG_ENABLE_EMSG_TRACK = 4;
    public static final int FLAG_WORKAROUND_EVERY_VIDEO_FRAME_IS_SYNC_FRAME = 1;
    public static final int FLAG_WORKAROUND_IGNORE_EDIT_LISTS = 16;
    public static final int FLAG_WORKAROUND_IGNORE_TFDT_BOX = 2;
    private static final byte[] PIFF_SAMPLE_ENCRYPTION_BOX_EXTENDED_TYPE = {-94, 57, 79, 82, 90, -101, 79, Ascii.DC4, -94, 68, 108, 66, 124, 100, -115, CBORConstants.BYTE_FALSE};
    private static final int SAMPLE_GROUP_TYPE_seig = 1936025959;
    private static final int STATE_READING_ATOM_HEADER = 0;
    private static final int STATE_READING_ATOM_PAYLOAD = 1;
    private static final int STATE_READING_ENCRYPTION_DATA = 2;
    private static final int STATE_READING_SAMPLE_CONTINUE = 4;
    private static final int STATE_READING_SAMPLE_START = 3;
    private static final String TAG = "FragmentedMp4Extractor";
    @Nullable
    private final TrackOutput additionalEmsgTrackOutput;
    @Nullable
    private ParsableByteArray atomData;
    private final ParsableByteArray atomHeader;
    private int atomHeaderBytesRead;
    private long atomSize;
    private int atomType;
    private TrackOutput[] ceaTrackOutputs;
    private final List<Format> closedCaptionFormats;
    private final ArrayDeque<Atom.ContainerAtom> containerAtoms;
    @Nullable
    private TrackBundle currentTrackBundle;
    private long durationUs;
    private TrackOutput[] emsgTrackOutputs;
    private long endOfMdatPosition;
    private final EventMessageEncoder eventMessageEncoder;
    private ExtractorOutput extractorOutput;
    private final int flags;
    private boolean haveOutputSeekMap;
    private final ParsableByteArray nalBuffer;
    private final ParsableByteArray nalPrefix;
    private final ParsableByteArray nalStartCode;
    private int parserState;
    private int pendingMetadataSampleBytes;
    private final ArrayDeque<MetadataSampleInfo> pendingMetadataSampleInfos;
    private long pendingSeekTimeUs;
    private boolean processSeiNalUnitPayload;
    private int sampleBytesWritten;
    private int sampleCurrentNalBytesRemaining;
    private int sampleSize;
    private final ParsableByteArray scratch;
    private final byte[] scratchBytes;
    private long segmentIndexEarliestPresentationTimeUs;
    @Nullable
    private final Track sideloadedTrack;
    @Nullable
    private final TimestampAdjuster timestampAdjuster;
    private final SparseArray<TrackBundle> trackBundles;

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    public static final class MetadataSampleInfo {
        public final boolean sampleTimeIsRelative;
        public final long sampleTimeUs;
        public final int size;

        public MetadataSampleInfo(long j2, boolean z2, int i3) {
            this.sampleTimeUs = j2;
            this.sampleTimeIsRelative = z2;
            this.size = i3;
        }
    }

    public static final class TrackBundle {
        private static final int SINGLE_SUBSAMPLE_ENCRYPTION_DATA_LENGTH = 8;
        public int currentSampleInTrackRun;
        public int currentSampleIndex;
        public int currentTrackRunIndex;
        /* access modifiers changed from: private */
        public boolean currentlyInFragment;
        private final ParsableByteArray defaultInitializationVector = new ParsableByteArray();
        public DefaultSampleValues defaultSampleValues;
        private final ParsableByteArray encryptionSignalByte = new ParsableByteArray(1);
        public int firstSampleToOutputIndex;
        public final TrackFragment fragment = new TrackFragment();
        public TrackSampleTable moovSampleTable;
        public final TrackOutput output;
        public final ParsableByteArray scratch = new ParsableByteArray();

        public TrackBundle(TrackOutput trackOutput, TrackSampleTable trackSampleTable, DefaultSampleValues defaultSampleValues2) {
            this.output = trackOutput;
            this.moovSampleTable = trackSampleTable;
            this.defaultSampleValues = defaultSampleValues2;
            reset(trackSampleTable, defaultSampleValues2);
        }

        public int getCurrentSampleFlags() {
            int i3 = !this.currentlyInFragment ? this.moovSampleTable.flags[this.currentSampleIndex] : this.fragment.sampleIsSyncFrameTable[this.currentSampleIndex] ? 1 : 0;
            return getEncryptionBoxIfEncrypted() != null ? i3 | 1073741824 : i3;
        }

        public long getCurrentSampleOffset() {
            return !this.currentlyInFragment ? this.moovSampleTable.offsets[this.currentSampleIndex] : this.fragment.trunDataPosition[this.currentTrackRunIndex];
        }

        public long getCurrentSamplePresentationTimeUs() {
            return !this.currentlyInFragment ? this.moovSampleTable.timestampsUs[this.currentSampleIndex] : this.fragment.getSamplePresentationTimeUs(this.currentSampleIndex);
        }

        public int getCurrentSampleSize() {
            return !this.currentlyInFragment ? this.moovSampleTable.sizes[this.currentSampleIndex] : this.fragment.sampleSizeTable[this.currentSampleIndex];
        }

        @Nullable
        public TrackEncryptionBox getEncryptionBoxIfEncrypted() {
            if (!this.currentlyInFragment) {
                return null;
            }
            int i3 = ((DefaultSampleValues) Util.castNonNull(this.fragment.header)).sampleDescriptionIndex;
            TrackEncryptionBox trackEncryptionBox = this.fragment.trackEncryptionBox;
            if (trackEncryptionBox == null) {
                trackEncryptionBox = this.moovSampleTable.track.getSampleDescriptionEncryptionBox(i3);
            }
            if (trackEncryptionBox == null || !trackEncryptionBox.isEncrypted) {
                return null;
            }
            return trackEncryptionBox;
        }

        public boolean next() {
            this.currentSampleIndex++;
            if (!this.currentlyInFragment) {
                return false;
            }
            int i3 = this.currentSampleInTrackRun + 1;
            this.currentSampleInTrackRun = i3;
            int[] iArr = this.fragment.trunLength;
            int i4 = this.currentTrackRunIndex;
            if (i3 != iArr[i4]) {
                return true;
            }
            this.currentTrackRunIndex = i4 + 1;
            this.currentSampleInTrackRun = 0;
            return false;
        }

        public int outputSampleEncryptionData(int i3, int i4) {
            ParsableByteArray parsableByteArray;
            TrackEncryptionBox encryptionBoxIfEncrypted = getEncryptionBoxIfEncrypted();
            if (encryptionBoxIfEncrypted == null) {
                return 0;
            }
            int i5 = encryptionBoxIfEncrypted.perSampleIvSize;
            if (i5 != 0) {
                parsableByteArray = this.fragment.sampleEncryptionData;
            } else {
                byte[] bArr = (byte[]) Util.castNonNull(encryptionBoxIfEncrypted.defaultInitializationVector);
                this.defaultInitializationVector.reset(bArr, bArr.length);
                ParsableByteArray parsableByteArray2 = this.defaultInitializationVector;
                i5 = bArr.length;
                parsableByteArray = parsableByteArray2;
            }
            boolean sampleHasSubsampleEncryptionTable = this.fragment.sampleHasSubsampleEncryptionTable(this.currentSampleIndex);
            boolean z2 = sampleHasSubsampleEncryptionTable || i4 != 0;
            this.encryptionSignalByte.getData()[0] = (byte) ((z2 ? 128 : 0) | i5);
            this.encryptionSignalByte.setPosition(0);
            this.output.sampleData(this.encryptionSignalByte, 1, 1);
            this.output.sampleData(parsableByteArray, i5, 1);
            if (!z2) {
                return i5 + 1;
            }
            if (!sampleHasSubsampleEncryptionTable) {
                this.scratch.reset(8);
                byte[] data = this.scratch.getData();
                data[0] = 0;
                data[1] = 1;
                data[2] = (byte) ((i4 >> 8) & 255);
                data[3] = (byte) (i4 & 255);
                data[4] = (byte) ((i3 >> 24) & 255);
                data[5] = (byte) ((i3 >> 16) & 255);
                data[6] = (byte) ((i3 >> 8) & 255);
                data[7] = (byte) (i3 & 255);
                this.output.sampleData(this.scratch, 8, 1);
                return i5 + 9;
            }
            ParsableByteArray parsableByteArray3 = this.fragment.sampleEncryptionData;
            int readUnsignedShort = parsableByteArray3.readUnsignedShort();
            parsableByteArray3.skipBytes(-2);
            int i6 = (readUnsignedShort * 6) + 2;
            if (i4 != 0) {
                this.scratch.reset(i6);
                byte[] data2 = this.scratch.getData();
                parsableByteArray3.readBytes(data2, 0, i6);
                int i7 = (((data2[2] & 255) << 8) | (data2[3] & 255)) + i4;
                data2[2] = (byte) ((i7 >> 8) & 255);
                data2[3] = (byte) (i7 & 255);
                parsableByteArray3 = this.scratch;
            }
            this.output.sampleData(parsableByteArray3, i6, 1);
            return i5 + 1 + i6;
        }

        public void reset(TrackSampleTable trackSampleTable, DefaultSampleValues defaultSampleValues2) {
            this.moovSampleTable = trackSampleTable;
            this.defaultSampleValues = defaultSampleValues2;
            this.output.format(trackSampleTable.track.format);
            resetFragmentInfo();
        }

        public void resetFragmentInfo() {
            this.fragment.reset();
            this.currentSampleIndex = 0;
            this.currentTrackRunIndex = 0;
            this.currentSampleInTrackRun = 0;
            this.firstSampleToOutputIndex = 0;
            this.currentlyInFragment = false;
        }

        public void seek(long j2) {
            int i3 = this.currentSampleIndex;
            while (true) {
                TrackFragment trackFragment = this.fragment;
                if (i3 < trackFragment.sampleCount && trackFragment.getSamplePresentationTimeUs(i3) < j2) {
                    if (this.fragment.sampleIsSyncFrameTable[i3]) {
                        this.firstSampleToOutputIndex = i3;
                    }
                    i3++;
                } else {
                    return;
                }
            }
        }

        public void skipSampleEncryptionData() {
            TrackEncryptionBox encryptionBoxIfEncrypted = getEncryptionBoxIfEncrypted();
            if (encryptionBoxIfEncrypted != null) {
                ParsableByteArray parsableByteArray = this.fragment.sampleEncryptionData;
                int i3 = encryptionBoxIfEncrypted.perSampleIvSize;
                if (i3 != 0) {
                    parsableByteArray.skipBytes(i3);
                }
                if (this.fragment.sampleHasSubsampleEncryptionTable(this.currentSampleIndex)) {
                    parsableByteArray.skipBytes(parsableByteArray.readUnsignedShort() * 6);
                }
            }
        }

        public void updateDrmInitData(DrmInitData drmInitData) {
            TrackEncryptionBox sampleDescriptionEncryptionBox = this.moovSampleTable.track.getSampleDescriptionEncryptionBox(((DefaultSampleValues) Util.castNonNull(this.fragment.header)).sampleDescriptionIndex);
            this.output.format(this.moovSampleTable.track.format.buildUpon().setDrmInitData(drmInitData.copyWithSchemeType(sampleDescriptionEncryptionBox != null ? sampleDescriptionEncryptionBox.schemeType : null)).build());
        }
    }

    public FragmentedMp4Extractor() {
        this(0);
    }

    private static int checkNonNegative(int i3) throws ParserException {
        if (i3 >= 0) {
            return i3;
        }
        throw ParserException.createForMalformedContainer("Unexpected negative value: " + i3, (Throwable) null);
    }

    private void enterReadingAtomHeaderState() {
        this.parserState = 0;
        this.atomHeaderBytesRead = 0;
    }

    private DefaultSampleValues getDefaultSampleValues(SparseArray<DefaultSampleValues> sparseArray, int i3) {
        return sparseArray.size() == 1 ? sparseArray.valueAt(0) : (DefaultSampleValues) Assertions.checkNotNull(sparseArray.get(i3));
    }

    @Nullable
    private static DrmInitData getDrmInitDataFromAtoms(List<Atom.LeafAtom> list) {
        int size = list.size();
        ArrayList arrayList = null;
        for (int i3 = 0; i3 < size; i3++) {
            Atom.LeafAtom leafAtom = list.get(i3);
            if (leafAtom.type == 1886614376) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                byte[] data = leafAtom.data.getData();
                UUID parseUuid = PsshAtomUtil.parseUuid(data);
                if (parseUuid == null) {
                    Log.w(TAG, "Skipped pssh atom (failed to extract uuid)");
                } else {
                    arrayList.add(new DrmInitData.SchemeData(parseUuid, MimeTypes.VIDEO_MP4, data));
                }
            }
        }
        if (arrayList == null) {
            return null;
        }
        return new DrmInitData((List<DrmInitData.SchemeData>) arrayList);
    }

    @Nullable
    private static TrackBundle getNextTrackBundle(SparseArray<TrackBundle> sparseArray) {
        int size = sparseArray.size();
        TrackBundle trackBundle = null;
        long j2 = Long.MAX_VALUE;
        for (int i3 = 0; i3 < size; i3++) {
            TrackBundle valueAt = sparseArray.valueAt(i3);
            if ((valueAt.currentlyInFragment || valueAt.currentSampleIndex != valueAt.moovSampleTable.sampleCount) && (!valueAt.currentlyInFragment || valueAt.currentTrackRunIndex != valueAt.fragment.trunCount)) {
                long currentSampleOffset = valueAt.getCurrentSampleOffset();
                if (currentSampleOffset < j2) {
                    trackBundle = valueAt;
                    j2 = currentSampleOffset;
                }
            }
        }
        return trackBundle;
    }

    private void initExtraTracks() {
        int i3;
        TrackOutput[] trackOutputArr = new TrackOutput[2];
        this.emsgTrackOutputs = trackOutputArr;
        TrackOutput trackOutput = this.additionalEmsgTrackOutput;
        int i4 = 0;
        if (trackOutput != null) {
            trackOutputArr[0] = trackOutput;
            i3 = 1;
        } else {
            i3 = 0;
        }
        int i5 = 100;
        if ((this.flags & 4) != 0) {
            trackOutputArr[i3] = this.extractorOutput.track(100, 5);
            i5 = 101;
            i3++;
        }
        TrackOutput[] trackOutputArr2 = (TrackOutput[]) Util.nullSafeArrayCopy(this.emsgTrackOutputs, i3);
        this.emsgTrackOutputs = trackOutputArr2;
        for (TrackOutput format : trackOutputArr2) {
            format.format(EMSG_FORMAT);
        }
        this.ceaTrackOutputs = new TrackOutput[this.closedCaptionFormats.size()];
        while (i4 < this.ceaTrackOutputs.length) {
            TrackOutput track = this.extractorOutput.track(i5, 3);
            track.format(this.closedCaptionFormats.get(i4));
            this.ceaTrackOutputs[i4] = track;
            i4++;
            i5++;
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Extractor[] lambda$static$0() {
        return new Extractor[]{new FragmentedMp4Extractor()};
    }

    private void onContainerAtomRead(Atom.ContainerAtom containerAtom) throws ParserException {
        int i3 = containerAtom.type;
        if (i3 == 1836019574) {
            onMoovContainerAtomRead(containerAtom);
        } else if (i3 == 1836019558) {
            onMoofContainerAtomRead(containerAtom);
        } else if (!this.containerAtoms.isEmpty()) {
            this.containerAtoms.peek().add(containerAtom);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v1, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void onEmsgLeafAtomRead(com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r28) {
        /*
            r27 = this;
            r0 = r27
            r1 = r28
            com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput[] r2 = r0.emsgTrackOutputs
            int r2 = r2.length
            if (r2 != 0) goto L_0x000a
            return
        L_0x000a:
            r2 = 8
            r1.setPosition(r2)
            int r2 = r28.readInt()
            int r2 = com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.Atom.parseFullAtomVersion(r2)
            r3 = 1
            r4 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r2 == 0) goto L_0x0065
            if (r2 == r3) goto L_0x0029
            java.lang.String r0 = "Skipping unsupported emsg version: "
            java.lang.String r1 = "FragmentedMp4Extractor"
            com.appsamurai.storyly.exoplayer2.core.r.a(r2, r0, r1)
            return
        L_0x0029:
            long r12 = r28.readUnsignedInt()
            long r6 = r28.readUnsignedLongToLong()
            r8 = 1000000(0xf4240, double:4.940656E-318)
            r10 = r12
            long r14 = com.appsamurai.storyly.exoplayer2.common.util.Util.scaleLargeTimestamp(r6, r8, r10)
            long r6 = r28.readUnsignedInt()
            r8 = 1000(0x3e8, double:4.94E-321)
            long r6 = com.appsamurai.storyly.exoplayer2.common.util.Util.scaleLargeTimestamp(r6, r8, r10)
            long r8 = r28.readUnsignedInt()
            java.lang.String r2 = r28.readNullTerminatedString()
            java.lang.Object r2 = com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkNotNull(r2)
            java.lang.String r2 = (java.lang.String) r2
            java.lang.String r10 = r28.readNullTerminatedString()
            java.lang.Object r10 = com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkNotNull(r10)
            java.lang.String r10 = (java.lang.String) r10
            r20 = r2
            r22 = r6
            r24 = r8
            r21 = r10
            r8 = r4
            goto L_0x00af
        L_0x0065:
            java.lang.String r2 = r28.readNullTerminatedString()
            java.lang.Object r2 = com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkNotNull(r2)
            java.lang.String r2 = (java.lang.String) r2
            java.lang.String r6 = r28.readNullTerminatedString()
            java.lang.Object r6 = com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkNotNull(r6)
            r10 = r6
            java.lang.String r10 = (java.lang.String) r10
            long r6 = r28.readUnsignedInt()
            long r11 = r28.readUnsignedInt()
            r13 = 1000000(0xf4240, double:4.940656E-318)
            r15 = r6
            long r8 = com.appsamurai.storyly.exoplayer2.common.util.Util.scaleLargeTimestamp(r11, r13, r15)
            long r11 = r0.segmentIndexEarliestPresentationTimeUs
            int r13 = (r11 > r4 ? 1 : (r11 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x0094
            long r11 = r11 + r8
            r17 = r11
            goto L_0x0096
        L_0x0094:
            r17 = r4
        L_0x0096:
            long r11 = r28.readUnsignedInt()
            r13 = 1000(0x3e8, double:4.94E-321)
            r15 = r6
            long r6 = com.appsamurai.storyly.exoplayer2.common.util.Util.scaleLargeTimestamp(r11, r13, r15)
            long r11 = r28.readUnsignedInt()
            r20 = r2
            r22 = r6
            r21 = r10
            r24 = r11
            r14 = r17
        L_0x00af:
            int r2 = r28.bytesLeft()
            byte[] r2 = new byte[r2]
            int r6 = r28.bytesLeft()
            r7 = 0
            r1.readBytes(r2, r7, r6)
            com.appsamurai.storyly.exoplayer2.extractor.metadata.emsg.EventMessage r1 = new com.appsamurai.storyly.exoplayer2.extractor.metadata.emsg.EventMessage
            r19 = r1
            r26 = r2
            r19.<init>(r20, r21, r22, r24, r26)
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r2 = new com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray
            com.appsamurai.storyly.exoplayer2.extractor.metadata.emsg.EventMessageEncoder r6 = r0.eventMessageEncoder
            byte[] r1 = r6.encode(r1)
            r2.<init>((byte[]) r1)
            int r1 = r2.bytesLeft()
            com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput[] r6 = r0.emsgTrackOutputs
            int r10 = r6.length
            r11 = r7
        L_0x00d9:
            if (r11 >= r10) goto L_0x00e6
            r12 = r6[r11]
            r2.setPosition(r7)
            r12.sampleData(r2, r1)
            int r11 = r11 + 1
            goto L_0x00d9
        L_0x00e6:
            int r2 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1))
            if (r2 != 0) goto L_0x00fa
            java.util.ArrayDeque<com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.FragmentedMp4Extractor$MetadataSampleInfo> r2 = r0.pendingMetadataSampleInfos
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.FragmentedMp4Extractor$MetadataSampleInfo r4 = new com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.FragmentedMp4Extractor$MetadataSampleInfo
            r4.<init>(r8, r3, r1)
            r2.addLast(r4)
            int r2 = r0.pendingMetadataSampleBytes
            int r2 = r2 + r1
            r0.pendingMetadataSampleBytes = r2
            goto L_0x0131
        L_0x00fa:
            java.util.ArrayDeque<com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.FragmentedMp4Extractor$MetadataSampleInfo> r2 = r0.pendingMetadataSampleInfos
            boolean r2 = r2.isEmpty()
            if (r2 != 0) goto L_0x0112
            java.util.ArrayDeque<com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.FragmentedMp4Extractor$MetadataSampleInfo> r2 = r0.pendingMetadataSampleInfos
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.FragmentedMp4Extractor$MetadataSampleInfo r3 = new com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.FragmentedMp4Extractor$MetadataSampleInfo
            r3.<init>(r14, r7, r1)
            r2.addLast(r3)
            int r2 = r0.pendingMetadataSampleBytes
            int r2 = r2 + r1
            r0.pendingMetadataSampleBytes = r2
            goto L_0x0131
        L_0x0112:
            com.appsamurai.storyly.exoplayer2.common.util.TimestampAdjuster r2 = r0.timestampAdjuster
            if (r2 == 0) goto L_0x011a
            long r14 = r2.adjustSampleTimestamp(r14)
        L_0x011a:
            com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput[] r0 = r0.emsgTrackOutputs
            int r2 = r0.length
        L_0x011d:
            if (r7 >= r2) goto L_0x0131
            r16 = r0[r7]
            r21 = 0
            r22 = 0
            r19 = 1
            r17 = r14
            r20 = r1
            r16.sampleMetadata(r17, r19, r20, r21, r22)
            int r7 = r7 + 1
            goto L_0x011d
        L_0x0131:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.FragmentedMp4Extractor.onEmsgLeafAtomRead(com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray):void");
    }

    private void onLeafAtomRead(Atom.LeafAtom leafAtom, long j2) throws ParserException {
        if (!this.containerAtoms.isEmpty()) {
            this.containerAtoms.peek().add(leafAtom);
            return;
        }
        int i3 = leafAtom.type;
        if (i3 == 1936286840) {
            Pair<Long, ChunkIndex> parseSidx = parseSidx(leafAtom.data, j2);
            this.segmentIndexEarliestPresentationTimeUs = ((Long) parseSidx.first).longValue();
            this.extractorOutput.seekMap((SeekMap) parseSidx.second);
            this.haveOutputSeekMap = true;
        } else if (i3 == 1701671783) {
            onEmsgLeafAtomRead(leafAtom.data);
        }
    }

    private void onMoofContainerAtomRead(Atom.ContainerAtom containerAtom) throws ParserException {
        parseMoof(containerAtom, this.trackBundles, this.sideloadedTrack != null, this.flags, this.scratchBytes);
        DrmInitData drmInitDataFromAtoms = getDrmInitDataFromAtoms(containerAtom.leafChildren);
        if (drmInitDataFromAtoms != null) {
            int size = this.trackBundles.size();
            for (int i3 = 0; i3 < size; i3++) {
                this.trackBundles.valueAt(i3).updateDrmInitData(drmInitDataFromAtoms);
            }
        }
        if (this.pendingSeekTimeUs != C.TIME_UNSET) {
            int size2 = this.trackBundles.size();
            for (int i4 = 0; i4 < size2; i4++) {
                this.trackBundles.valueAt(i4).seek(this.pendingSeekTimeUs);
            }
            this.pendingSeekTimeUs = C.TIME_UNSET;
        }
    }

    private void onMoovContainerAtomRead(Atom.ContainerAtom containerAtom) throws ParserException {
        int i3 = 0;
        boolean z2 = true;
        Assertions.checkState(this.sideloadedTrack == null, "Unexpected moov box.");
        DrmInitData drmInitDataFromAtoms = getDrmInitDataFromAtoms(containerAtom.leafChildren);
        Atom.ContainerAtom containerAtom2 = (Atom.ContainerAtom) Assertions.checkNotNull(containerAtom.getContainerAtomOfType(Atom.TYPE_mvex));
        SparseArray sparseArray = new SparseArray();
        int size = containerAtom2.leafChildren.size();
        long j2 = -9223372036854775807L;
        for (int i4 = 0; i4 < size; i4++) {
            Atom.LeafAtom leafAtom = containerAtom2.leafChildren.get(i4);
            int i5 = leafAtom.type;
            if (i5 == 1953654136) {
                Pair<Integer, DefaultSampleValues> parseTrex = parseTrex(leafAtom.data);
                sparseArray.put(((Integer) parseTrex.first).intValue(), (DefaultSampleValues) parseTrex.second);
            } else if (i5 == 1835362404) {
                j2 = parseMehd(leafAtom.data);
            }
        }
        List<TrackSampleTable> parseTraks = AtomParsers.parseTraks(containerAtom, new GaplessInfoHolder(), j2, drmInitDataFromAtoms, (this.flags & 16) != 0, false, new a(this, 0));
        int size2 = parseTraks.size();
        if (this.trackBundles.size() == 0) {
            while (i3 < size2) {
                TrackSampleTable trackSampleTable = parseTraks.get(i3);
                Track track = trackSampleTable.track;
                this.trackBundles.put(track.id, new TrackBundle(this.extractorOutput.track(i3, track.type), trackSampleTable, getDefaultSampleValues(sparseArray, track.id)));
                this.durationUs = Math.max(this.durationUs, track.durationUs);
                i3++;
            }
            this.extractorOutput.endTracks();
            return;
        }
        if (this.trackBundles.size() != size2) {
            z2 = false;
        }
        Assertions.checkState(z2);
        while (i3 < size2) {
            TrackSampleTable trackSampleTable2 = parseTraks.get(i3);
            Track track2 = trackSampleTable2.track;
            this.trackBundles.get(track2.id).reset(trackSampleTable2, getDefaultSampleValues(sparseArray, track2.id));
            i3++;
        }
    }

    private void outputPendingMetadataSamples(long j2) {
        while (!this.pendingMetadataSampleInfos.isEmpty()) {
            MetadataSampleInfo removeFirst = this.pendingMetadataSampleInfos.removeFirst();
            this.pendingMetadataSampleBytes -= removeFirst.size;
            long j3 = removeFirst.sampleTimeUs;
            if (removeFirst.sampleTimeIsRelative) {
                j3 += j2;
            }
            TimestampAdjuster timestampAdjuster2 = this.timestampAdjuster;
            if (timestampAdjuster2 != null) {
                j3 = timestampAdjuster2.adjustSampleTimestamp(j3);
            }
            for (TrackOutput sampleMetadata : this.emsgTrackOutputs) {
                sampleMetadata.sampleMetadata(j3, 1, removeFirst.size, this.pendingMetadataSampleBytes, (TrackOutput.CryptoData) null);
            }
        }
    }

    private static long parseMehd(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(8);
        return Atom.parseFullAtomVersion(parsableByteArray.readInt()) == 0 ? parsableByteArray.readUnsignedInt() : parsableByteArray.readUnsignedLongToLong();
    }

    private static void parseMoof(Atom.ContainerAtom containerAtom, SparseArray<TrackBundle> sparseArray, boolean z2, int i3, byte[] bArr) throws ParserException {
        int size = containerAtom.containerChildren.size();
        for (int i4 = 0; i4 < size; i4++) {
            Atom.ContainerAtom containerAtom2 = containerAtom.containerChildren.get(i4);
            if (containerAtom2.type == 1953653094) {
                parseTraf(containerAtom2, sparseArray, z2, i3, bArr);
            }
        }
    }

    private static void parseSaio(ParsableByteArray parsableByteArray, TrackFragment trackFragment) throws ParserException {
        parsableByteArray.setPosition(8);
        int readInt = parsableByteArray.readInt();
        if ((Atom.parseFullAtomFlags(readInt) & 1) == 1) {
            parsableByteArray.skipBytes(8);
        }
        int readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        if (readUnsignedIntToInt == 1) {
            trackFragment.auxiliaryDataPosition += Atom.parseFullAtomVersion(readInt) == 0 ? parsableByteArray.readUnsignedInt() : parsableByteArray.readUnsignedLongToLong();
            return;
        }
        throw ParserException.createForMalformedContainer("Unexpected saio entry count: " + readUnsignedIntToInt, (Throwable) null);
    }

    private static void parseSaiz(TrackEncryptionBox trackEncryptionBox, ParsableByteArray parsableByteArray, TrackFragment trackFragment) throws ParserException {
        int i3;
        int i4 = trackEncryptionBox.perSampleIvSize;
        parsableByteArray.setPosition(8);
        boolean z2 = true;
        if ((Atom.parseFullAtomFlags(parsableByteArray.readInt()) & 1) == 1) {
            parsableByteArray.skipBytes(8);
        }
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        if (readUnsignedIntToInt <= trackFragment.sampleCount) {
            if (readUnsignedByte == 0) {
                boolean[] zArr = trackFragment.sampleHasSubsampleEncryptionTable;
                i3 = 0;
                for (int i5 = 0; i5 < readUnsignedIntToInt; i5++) {
                    int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
                    i3 += readUnsignedByte2;
                    zArr[i5] = readUnsignedByte2 > i4;
                }
            } else {
                if (readUnsignedByte <= i4) {
                    z2 = false;
                }
                i3 = readUnsignedByte * readUnsignedIntToInt;
                Arrays.fill(trackFragment.sampleHasSubsampleEncryptionTable, 0, readUnsignedIntToInt, z2);
            }
            Arrays.fill(trackFragment.sampleHasSubsampleEncryptionTable, readUnsignedIntToInt, trackFragment.sampleCount, false);
            if (i3 > 0) {
                trackFragment.initEncryptionData(i3);
                return;
            }
            return;
        }
        StringBuilder o3 = A.a.o(readUnsignedIntToInt, "Saiz sample count ", " is greater than fragment sample count");
        o3.append(trackFragment.sampleCount);
        throw ParserException.createForMalformedContainer(o3.toString(), (Throwable) null);
    }

    private static void parseSampleGroups(Atom.ContainerAtom containerAtom, @Nullable String str, TrackFragment trackFragment) throws ParserException {
        Atom.ContainerAtom containerAtom2 = containerAtom;
        TrackFragment trackFragment2 = trackFragment;
        byte[] bArr = null;
        ParsableByteArray parsableByteArray = null;
        ParsableByteArray parsableByteArray2 = null;
        for (int i3 = 0; i3 < containerAtom2.leafChildren.size(); i3++) {
            Atom.LeafAtom leafAtom = containerAtom2.leafChildren.get(i3);
            ParsableByteArray parsableByteArray3 = leafAtom.data;
            int i4 = leafAtom.type;
            if (i4 == 1935828848) {
                parsableByteArray3.setPosition(12);
                if (parsableByteArray3.readInt() == SAMPLE_GROUP_TYPE_seig) {
                    parsableByteArray = parsableByteArray3;
                }
            } else if (i4 == 1936158820) {
                parsableByteArray3.setPosition(12);
                if (parsableByteArray3.readInt() == SAMPLE_GROUP_TYPE_seig) {
                    parsableByteArray2 = parsableByteArray3;
                }
            }
        }
        if (parsableByteArray != null && parsableByteArray2 != null) {
            parsableByteArray.setPosition(8);
            int parseFullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
            parsableByteArray.skipBytes(4);
            if (parseFullAtomVersion == 1) {
                parsableByteArray.skipBytes(4);
            }
            if (parsableByteArray.readInt() == 1) {
                parsableByteArray2.setPosition(8);
                int parseFullAtomVersion2 = Atom.parseFullAtomVersion(parsableByteArray2.readInt());
                parsableByteArray2.skipBytes(4);
                if (parseFullAtomVersion2 == 1) {
                    if (parsableByteArray2.readUnsignedInt() == 0) {
                        throw ParserException.createForUnsupportedContainerFeature("Variable length description in sgpd found (unsupported)");
                    }
                } else if (parseFullAtomVersion2 >= 2) {
                    parsableByteArray2.skipBytes(4);
                }
                if (parsableByteArray2.readUnsignedInt() == 1) {
                    parsableByteArray2.skipBytes(1);
                    int readUnsignedByte = parsableByteArray2.readUnsignedByte();
                    int i5 = (readUnsignedByte & 240) >> 4;
                    int i6 = readUnsignedByte & 15;
                    boolean z2 = parsableByteArray2.readUnsignedByte() == 1;
                    if (z2) {
                        int readUnsignedByte2 = parsableByteArray2.readUnsignedByte();
                        byte[] bArr2 = new byte[16];
                        parsableByteArray2.readBytes(bArr2, 0, 16);
                        if (readUnsignedByte2 == 0) {
                            int readUnsignedByte3 = parsableByteArray2.readUnsignedByte();
                            bArr = new byte[readUnsignedByte3];
                            parsableByteArray2.readBytes(bArr, 0, readUnsignedByte3);
                        }
                        trackFragment2.definesEncryptionData = true;
                        trackFragment2.trackEncryptionBox = new TrackEncryptionBox(z2, str, readUnsignedByte2, bArr2, i5, i6, bArr);
                        return;
                    }
                    return;
                }
                throw ParserException.createForUnsupportedContainerFeature("Entry count in sgpd != 1 (unsupported).");
            }
            throw ParserException.createForUnsupportedContainerFeature("Entry count in sbgp != 1 (unsupported).");
        }
    }

    private static void parseSenc(ParsableByteArray parsableByteArray, TrackFragment trackFragment) throws ParserException {
        parseSenc(parsableByteArray, 0, trackFragment);
    }

    private static Pair<Long, ChunkIndex> parseSidx(ParsableByteArray parsableByteArray, long j2) throws ParserException {
        long readUnsignedLongToLong;
        long readUnsignedLongToLong2;
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        parsableByteArray2.setPosition(8);
        int parseFullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        parsableByteArray2.skipBytes(4);
        long readUnsignedInt = parsableByteArray.readUnsignedInt();
        if (parseFullAtomVersion == 0) {
            readUnsignedLongToLong = parsableByteArray.readUnsignedInt();
            readUnsignedLongToLong2 = parsableByteArray.readUnsignedInt();
        } else {
            readUnsignedLongToLong = parsableByteArray.readUnsignedLongToLong();
            readUnsignedLongToLong2 = parsableByteArray.readUnsignedLongToLong();
        }
        long j3 = readUnsignedLongToLong;
        long j4 = readUnsignedLongToLong2 + j2;
        long scaleLargeTimestamp = Util.scaleLargeTimestamp(j3, 1000000, readUnsignedInt);
        parsableByteArray2.skipBytes(2);
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        int[] iArr = new int[readUnsignedShort];
        long[] jArr = new long[readUnsignedShort];
        long[] jArr2 = new long[readUnsignedShort];
        long[] jArr3 = new long[readUnsignedShort];
        long j5 = scaleLargeTimestamp;
        long j6 = j3;
        int i3 = 0;
        long j7 = j6;
        while (i3 < readUnsignedShort) {
            int readInt = parsableByteArray.readInt();
            if ((readInt & Integer.MIN_VALUE) == 0) {
                long readUnsignedInt2 = parsableByteArray.readUnsignedInt();
                iArr[i3] = readInt & Integer.MAX_VALUE;
                jArr[i3] = j4;
                jArr3[i3] = j5;
                long j8 = j7 + readUnsignedInt2;
                long[] jArr4 = jArr2;
                long[] jArr5 = jArr3;
                int i4 = readUnsignedShort;
                int[] iArr2 = iArr;
                long scaleLargeTimestamp2 = Util.scaleLargeTimestamp(j8, 1000000, readUnsignedInt);
                jArr4[i3] = scaleLargeTimestamp2 - jArr5[i3];
                parsableByteArray2.skipBytes(4);
                j4 += (long) iArr2[i3];
                i3++;
                iArr = iArr2;
                jArr3 = jArr5;
                jArr2 = jArr4;
                jArr = jArr;
                readUnsignedShort = i4;
                long j9 = scaleLargeTimestamp2;
                j7 = j8;
                j5 = j9;
            } else {
                throw ParserException.createForMalformedContainer("Unhandled indirect reference", (Throwable) null);
            }
        }
        return Pair.create(Long.valueOf(scaleLargeTimestamp), new ChunkIndex(iArr, jArr, jArr2, jArr3));
    }

    private static long parseTfdt(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(8);
        return Atom.parseFullAtomVersion(parsableByteArray.readInt()) == 1 ? parsableByteArray.readUnsignedLongToLong() : parsableByteArray.readUnsignedInt();
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [android.util.SparseArray, android.util.SparseArray<com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.FragmentedMp4Extractor$TrackBundle>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.FragmentedMp4Extractor.TrackBundle parseTfhd(com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r4, android.util.SparseArray<com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.FragmentedMp4Extractor.TrackBundle> r5, boolean r6) {
        /*
            r0 = 8
            r4.setPosition(r0)
            int r0 = r4.readInt()
            int r0 = com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.Atom.parseFullAtomFlags(r0)
            int r1 = r4.readInt()
            if (r6 == 0) goto L_0x001b
            r6 = 0
            java.lang.Object r5 = r5.valueAt(r6)
        L_0x0018:
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.FragmentedMp4Extractor$TrackBundle r5 = (com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.FragmentedMp4Extractor.TrackBundle) r5
            goto L_0x0020
        L_0x001b:
            java.lang.Object r5 = r5.get(r1)
            goto L_0x0018
        L_0x0020:
            if (r5 != 0) goto L_0x0024
            r4 = 0
            return r4
        L_0x0024:
            r6 = r0 & 1
            if (r6 == 0) goto L_0x0032
            long r1 = r4.readUnsignedLongToLong()
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.TrackFragment r6 = r5.fragment
            r6.dataPosition = r1
            r6.auxiliaryDataPosition = r1
        L_0x0032:
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.DefaultSampleValues r6 = r5.defaultSampleValues
            r1 = r0 & 2
            if (r1 == 0) goto L_0x003f
            int r1 = r4.readInt()
            int r1 = r1 + -1
            goto L_0x0041
        L_0x003f:
            int r1 = r6.sampleDescriptionIndex
        L_0x0041:
            r2 = r0 & 8
            if (r2 == 0) goto L_0x004a
            int r2 = r4.readInt()
            goto L_0x004c
        L_0x004a:
            int r2 = r6.duration
        L_0x004c:
            r3 = r0 & 16
            if (r3 == 0) goto L_0x0055
            int r3 = r4.readInt()
            goto L_0x0057
        L_0x0055:
            int r3 = r6.size
        L_0x0057:
            r0 = r0 & 32
            if (r0 == 0) goto L_0x0060
            int r4 = r4.readInt()
            goto L_0x0062
        L_0x0060:
            int r4 = r6.flags
        L_0x0062:
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.TrackFragment r6 = r5.fragment
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.DefaultSampleValues r0 = new com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.DefaultSampleValues
            r0.<init>(r1, r2, r3, r4)
            r6.header = r0
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.FragmentedMp4Extractor.parseTfhd(com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray, android.util.SparseArray, boolean):com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.FragmentedMp4Extractor$TrackBundle");
    }

    private static void parseTraf(Atom.ContainerAtom containerAtom, SparseArray<TrackBundle> sparseArray, boolean z2, int i3, byte[] bArr) throws ParserException {
        TrackBundle parseTfhd = parseTfhd(((Atom.LeafAtom) Assertions.checkNotNull(containerAtom.getLeafAtomOfType(Atom.TYPE_tfhd))).data, sparseArray, z2);
        if (parseTfhd != null) {
            TrackFragment trackFragment = parseTfhd.fragment;
            long j2 = trackFragment.nextFragmentDecodeTime;
            boolean z3 = trackFragment.nextFragmentDecodeTimeIncludesMoov;
            parseTfhd.resetFragmentInfo();
            boolean unused = parseTfhd.currentlyInFragment = true;
            Atom.LeafAtom leafAtomOfType = containerAtom.getLeafAtomOfType(Atom.TYPE_tfdt);
            if (leafAtomOfType == null || (i3 & 2) != 0) {
                trackFragment.nextFragmentDecodeTime = j2;
                trackFragment.nextFragmentDecodeTimeIncludesMoov = z3;
            } else {
                trackFragment.nextFragmentDecodeTime = parseTfdt(leafAtomOfType.data);
                trackFragment.nextFragmentDecodeTimeIncludesMoov = true;
            }
            parseTruns(containerAtom, parseTfhd, i3);
            TrackEncryptionBox sampleDescriptionEncryptionBox = parseTfhd.moovSampleTable.track.getSampleDescriptionEncryptionBox(((DefaultSampleValues) Assertions.checkNotNull(trackFragment.header)).sampleDescriptionIndex);
            Atom.LeafAtom leafAtomOfType2 = containerAtom.getLeafAtomOfType(Atom.TYPE_saiz);
            if (leafAtomOfType2 != null) {
                parseSaiz((TrackEncryptionBox) Assertions.checkNotNull(sampleDescriptionEncryptionBox), leafAtomOfType2.data, trackFragment);
            }
            Atom.LeafAtom leafAtomOfType3 = containerAtom.getLeafAtomOfType(Atom.TYPE_saio);
            if (leafAtomOfType3 != null) {
                parseSaio(leafAtomOfType3.data, trackFragment);
            }
            Atom.LeafAtom leafAtomOfType4 = containerAtom.getLeafAtomOfType(Atom.TYPE_senc);
            if (leafAtomOfType4 != null) {
                parseSenc(leafAtomOfType4.data, trackFragment);
            }
            parseSampleGroups(containerAtom, sampleDescriptionEncryptionBox != null ? sampleDescriptionEncryptionBox.schemeType : null, trackFragment);
            int size = containerAtom.leafChildren.size();
            for (int i4 = 0; i4 < size; i4++) {
                Atom.LeafAtom leafAtom = containerAtom.leafChildren.get(i4);
                if (leafAtom.type == 1970628964) {
                    parseUuid(leafAtom.data, trackFragment, bArr);
                }
            }
        }
    }

    private static Pair<Integer, DefaultSampleValues> parseTrex(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(12);
        return Pair.create(Integer.valueOf(parsableByteArray.readInt()), new DefaultSampleValues(parsableByteArray.readInt() - 1, parsableByteArray.readInt(), parsableByteArray.readInt(), parsableByteArray.readInt()));
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00af  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int parseTrun(com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.FragmentedMp4Extractor.TrackBundle r33, int r34, int r35, com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r36, int r37) throws com.appsamurai.storyly.exoplayer2.common.ParserException {
        /*
            r0 = r33
            r1 = 8
            r2 = r36
            r2.setPosition(r1)
            int r1 = r36.readInt()
            int r1 = com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.Atom.parseFullAtomFlags(r1)
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.TrackSampleTable r3 = r0.moovSampleTable
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.Track r3 = r3.track
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.TrackFragment r4 = r0.fragment
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.DefaultSampleValues r5 = r4.header
            java.lang.Object r5 = com.appsamurai.storyly.exoplayer2.common.util.Util.castNonNull(r5)
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.DefaultSampleValues r5 = (com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.DefaultSampleValues) r5
            int[] r6 = r4.trunLength
            int r7 = r36.readUnsignedIntToInt()
            r6[r34] = r7
            long[] r6 = r4.trunDataPosition
            long r7 = r4.dataPosition
            r6[r34] = r7
            r9 = r1 & 1
            if (r9 == 0) goto L_0x0039
            int r9 = r36.readInt()
            long r9 = (long) r9
            long r7 = r7 + r9
            r6[r34] = r7
        L_0x0039:
            r6 = r1 & 4
            r7 = 0
            r8 = 1
            if (r6 == 0) goto L_0x0041
            r6 = r8
            goto L_0x0042
        L_0x0041:
            r6 = r7
        L_0x0042:
            int r9 = r5.flags
            if (r6 == 0) goto L_0x004a
            int r9 = r36.readInt()
        L_0x004a:
            r10 = r1 & 256(0x100, float:3.59E-43)
            if (r10 == 0) goto L_0x0050
            r10 = r8
            goto L_0x0051
        L_0x0050:
            r10 = r7
        L_0x0051:
            r11 = r1 & 512(0x200, float:7.175E-43)
            if (r11 == 0) goto L_0x0057
            r11 = r8
            goto L_0x0058
        L_0x0057:
            r11 = r7
        L_0x0058:
            r12 = r1 & 1024(0x400, float:1.435E-42)
            if (r12 == 0) goto L_0x005e
            r12 = r8
            goto L_0x005f
        L_0x005e:
            r12 = r7
        L_0x005f:
            r1 = r1 & 2048(0x800, float:2.87E-42)
            if (r1 == 0) goto L_0x0065
            r1 = r8
            goto L_0x0066
        L_0x0065:
            r1 = r7
        L_0x0066:
            long[] r13 = r3.editListDurations
            if (r13 == 0) goto L_0x0080
            int r14 = r13.length
            if (r14 != r8) goto L_0x0080
            r13 = r13[r7]
            r15 = 0
            int r13 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r13 != 0) goto L_0x0082
            long[] r13 = r3.editListMediaTimes
            java.lang.Object r13 = com.appsamurai.storyly.exoplayer2.common.util.Util.castNonNull(r13)
            long[] r13 = (long[]) r13
            r14 = r13[r7]
            goto L_0x0083
        L_0x0080:
            r15 = 0
        L_0x0082:
            r14 = r15
        L_0x0083:
            int[] r13 = r4.sampleSizeTable
            long[] r7 = r4.samplePresentationTimesUs
            boolean[] r8 = r4.sampleIsSyncFrameTable
            int r2 = r3.type
            r17 = r9
            r9 = 2
            if (r2 != r9) goto L_0x0097
            r2 = 1
            r9 = r35 & 1
            if (r9 == 0) goto L_0x0097
            r2 = 1
            goto L_0x0098
        L_0x0097:
            r2 = 0
        L_0x0098:
            int[] r9 = r4.trunLength
            r9 = r9[r34]
            int r9 = r37 + r9
            r35 = r2
            long r2 = r3.timescale
            r24 = r7
            r25 = r8
            long r7 = r4.nextFragmentDecodeTime
            r26 = r2
            r2 = r7
            r7 = r37
        L_0x00ad:
            if (r7 >= r9) goto L_0x013a
            if (r10 == 0) goto L_0x00b6
            int r8 = r36.readInt()
            goto L_0x00b8
        L_0x00b6:
            int r8 = r5.duration
        L_0x00b8:
            int r8 = checkNonNegative(r8)
            if (r11 == 0) goto L_0x00c5
            int r18 = r36.readInt()
            r28 = r10
            goto L_0x00cb
        L_0x00c5:
            r28 = r10
            int r10 = r5.size
            r18 = r10
        L_0x00cb:
            int r10 = checkNonNegative(r18)
            if (r12 == 0) goto L_0x00da
            int r18 = r36.readInt()
            r29 = r6
            r6 = r18
            goto L_0x00e7
        L_0x00da:
            if (r7 != 0) goto L_0x00e3
            if (r6 == 0) goto L_0x00e3
            r29 = r6
            r6 = r17
            goto L_0x00e7
        L_0x00e3:
            r29 = r6
            int r6 = r5.flags
        L_0x00e7:
            if (r1 == 0) goto L_0x00f6
            int r18 = r36.readInt()
            r30 = r1
            r31 = r11
            r32 = r12
            r1 = r18
            goto L_0x00fd
        L_0x00f6:
            r30 = r1
            r31 = r11
            r32 = r12
            r1 = 0
        L_0x00fd:
            long r11 = (long) r1
            long r11 = r11 + r2
            long r18 = r11 - r14
            r20 = 1000000(0xf4240, double:4.940656E-318)
            r22 = r26
            long r11 = com.appsamurai.storyly.exoplayer2.common.util.Util.scaleLargeTimestamp(r18, r20, r22)
            r24[r7] = r11
            boolean r1 = r4.nextFragmentDecodeTimeIncludesMoov
            if (r1 != 0) goto L_0x0117
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.TrackSampleTable r1 = r0.moovSampleTable
            long r0 = r1.durationUs
            long r11 = r11 + r0
            r24[r7] = r11
        L_0x0117:
            r13[r7] = r10
            int r0 = r6 >> 16
            r1 = 1
            r0 = r0 & r1
            if (r0 != 0) goto L_0x0125
            if (r35 == 0) goto L_0x0123
            if (r7 != 0) goto L_0x0125
        L_0x0123:
            r0 = r1
            goto L_0x0126
        L_0x0125:
            r0 = 0
        L_0x0126:
            r25[r7] = r0
            long r10 = (long) r8
            long r2 = r2 + r10
            int r7 = r7 + 1
            r0 = r33
            r10 = r28
            r6 = r29
            r1 = r30
            r11 = r31
            r12 = r32
            goto L_0x00ad
        L_0x013a:
            r4.nextFragmentDecodeTime = r2
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.FragmentedMp4Extractor.parseTrun(com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.FragmentedMp4Extractor$TrackBundle, int, int, com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray, int):int");
    }

    private static void parseTruns(Atom.ContainerAtom containerAtom, TrackBundle trackBundle, int i3) throws ParserException {
        List<Atom.LeafAtom> list = containerAtom.leafChildren;
        int size = list.size();
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < size; i6++) {
            Atom.LeafAtom leafAtom = list.get(i6);
            if (leafAtom.type == 1953658222) {
                ParsableByteArray parsableByteArray = leafAtom.data;
                parsableByteArray.setPosition(12);
                int readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
                if (readUnsignedIntToInt > 0) {
                    i5 += readUnsignedIntToInt;
                    i4++;
                }
            }
        }
        trackBundle.currentTrackRunIndex = 0;
        trackBundle.currentSampleInTrackRun = 0;
        trackBundle.currentSampleIndex = 0;
        trackBundle.fragment.initTables(i4, i5);
        int i7 = 0;
        int i8 = 0;
        for (int i9 = 0; i9 < size; i9++) {
            Atom.LeafAtom leafAtom2 = list.get(i9);
            if (leafAtom2.type == 1953658222) {
                i8 = parseTrun(trackBundle, i7, i3, leafAtom2.data, i8);
                i7++;
            }
        }
    }

    private static void parseUuid(ParsableByteArray parsableByteArray, TrackFragment trackFragment, byte[] bArr) throws ParserException {
        parsableByteArray.setPosition(8);
        parsableByteArray.readBytes(bArr, 0, 16);
        if (Arrays.equals(bArr, PIFF_SAMPLE_ENCRYPTION_BOX_EXTENDED_TYPE)) {
            parseSenc(parsableByteArray, 16, trackFragment);
        }
    }

    private void processAtomEnded(long j2) throws ParserException {
        while (!this.containerAtoms.isEmpty() && this.containerAtoms.peek().endPosition == j2) {
            onContainerAtomRead(this.containerAtoms.pop());
        }
        enterReadingAtomHeaderState();
    }

    private boolean readAtomHeader(ExtractorInput extractorInput) throws IOException {
        if (this.atomHeaderBytesRead == 0) {
            if (!extractorInput.readFully(this.atomHeader.getData(), 0, 8, true)) {
                return false;
            }
            this.atomHeaderBytesRead = 8;
            this.atomHeader.setPosition(0);
            this.atomSize = this.atomHeader.readUnsignedInt();
            this.atomType = this.atomHeader.readInt();
        }
        long j2 = this.atomSize;
        if (j2 == 1) {
            extractorInput.readFully(this.atomHeader.getData(), 8, 8);
            this.atomHeaderBytesRead += 8;
            this.atomSize = this.atomHeader.readUnsignedLongToLong();
        } else if (j2 == 0) {
            long length = extractorInput.getLength();
            if (length == -1 && !this.containerAtoms.isEmpty()) {
                length = this.containerAtoms.peek().endPosition;
            }
            if (length != -1) {
                this.atomSize = (length - extractorInput.getPosition()) + ((long) this.atomHeaderBytesRead);
            }
        }
        if (this.atomSize >= ((long) this.atomHeaderBytesRead)) {
            long position = extractorInput.getPosition() - ((long) this.atomHeaderBytesRead);
            int i3 = this.atomType;
            if ((i3 == 1836019558 || i3 == 1835295092) && !this.haveOutputSeekMap) {
                this.extractorOutput.seekMap(new SeekMap.Unseekable(this.durationUs, position));
                this.haveOutputSeekMap = true;
            }
            if (this.atomType == 1836019558) {
                int size = this.trackBundles.size();
                for (int i4 = 0; i4 < size; i4++) {
                    TrackFragment trackFragment = this.trackBundles.valueAt(i4).fragment;
                    trackFragment.atomPosition = position;
                    trackFragment.auxiliaryDataPosition = position;
                    trackFragment.dataPosition = position;
                }
            }
            int i5 = this.atomType;
            if (i5 == 1835295092) {
                this.currentTrackBundle = null;
                this.endOfMdatPosition = position + this.atomSize;
                this.parserState = 2;
                return true;
            }
            if (shouldParseContainerAtom(i5)) {
                long position2 = (extractorInput.getPosition() + this.atomSize) - 8;
                this.containerAtoms.push(new Atom.ContainerAtom(this.atomType, position2));
                if (this.atomSize == ((long) this.atomHeaderBytesRead)) {
                    processAtomEnded(position2);
                } else {
                    enterReadingAtomHeaderState();
                }
            } else if (shouldParseLeafAtom(this.atomType)) {
                if (this.atomHeaderBytesRead == 8) {
                    long j3 = this.atomSize;
                    if (j3 <= SieveCacheKt.NodeLinkMask) {
                        ParsableByteArray parsableByteArray = new ParsableByteArray((int) j3);
                        System.arraycopy(this.atomHeader.getData(), 0, parsableByteArray.getData(), 0, 8);
                        this.atomData = parsableByteArray;
                        this.parserState = 1;
                    } else {
                        throw ParserException.createForUnsupportedContainerFeature("Leaf atom with length > 2147483647 (unsupported).");
                    }
                } else {
                    throw ParserException.createForUnsupportedContainerFeature("Leaf atom defines extended atom size (unsupported).");
                }
            } else if (this.atomSize <= SieveCacheKt.NodeLinkMask) {
                this.atomData = null;
                this.parserState = 1;
            } else {
                throw ParserException.createForUnsupportedContainerFeature("Skipping atom with length > 2147483647 (unsupported).");
            }
            return true;
        }
        throw ParserException.createForUnsupportedContainerFeature("Atom size less than header length (unsupported).");
    }

    private void readAtomPayload(ExtractorInput extractorInput) throws IOException {
        int i3 = ((int) this.atomSize) - this.atomHeaderBytesRead;
        ParsableByteArray parsableByteArray = this.atomData;
        if (parsableByteArray != null) {
            extractorInput.readFully(parsableByteArray.getData(), 8, i3);
            onLeafAtomRead(new Atom.LeafAtom(this.atomType, parsableByteArray), extractorInput.getPosition());
        } else {
            extractorInput.skipFully(i3);
        }
        processAtomEnded(extractorInput.getPosition());
    }

    private void readEncryptionData(ExtractorInput extractorInput) throws IOException {
        int size = this.trackBundles.size();
        long j2 = Long.MAX_VALUE;
        TrackBundle trackBundle = null;
        for (int i3 = 0; i3 < size; i3++) {
            TrackFragment trackFragment = this.trackBundles.valueAt(i3).fragment;
            if (trackFragment.sampleEncryptionDataNeedsFill) {
                long j3 = trackFragment.auxiliaryDataPosition;
                if (j3 < j2) {
                    trackBundle = this.trackBundles.valueAt(i3);
                    j2 = j3;
                }
            }
        }
        if (trackBundle == null) {
            this.parserState = 3;
            return;
        }
        int position = (int) (j2 - extractorInput.getPosition());
        if (position >= 0) {
            extractorInput.skipFully(position);
            trackBundle.fragment.fillEncryptionData(extractorInput);
            return;
        }
        throw ParserException.createForMalformedContainer("Offset to encryption data was negative.", (Throwable) null);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v0, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v19, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v25, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v29, resolved type: boolean} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean readSample(com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput r17) throws java.io.IOException {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.FragmentedMp4Extractor$TrackBundle r2 = r0.currentTrackBundle
            r3 = 0
            r4 = 0
            if (r2 != 0) goto L_0x0043
            android.util.SparseArray<com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.FragmentedMp4Extractor$TrackBundle> r2 = r0.trackBundles
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.FragmentedMp4Extractor$TrackBundle r2 = getNextTrackBundle(r2)
            if (r2 != 0) goto L_0x002a
            long r5 = r0.endOfMdatPosition
            long r7 = r17.getPosition()
            long r5 = r5 - r7
            int r2 = (int) r5
            if (r2 < 0) goto L_0x0023
            r1.skipFully(r2)
            r16.enterReadingAtomHeaderState()
            return r4
        L_0x0023:
            java.lang.String r0 = "Offset to end of mdat was negative."
            com.appsamurai.storyly.exoplayer2.common.ParserException r0 = com.appsamurai.storyly.exoplayer2.common.ParserException.createForMalformedContainer(r0, r3)
            throw r0
        L_0x002a:
            long r5 = r2.getCurrentSampleOffset()
            long r7 = r17.getPosition()
            long r5 = r5 - r7
            int r5 = (int) r5
            if (r5 >= 0) goto L_0x003e
            java.lang.String r5 = "FragmentedMp4Extractor"
            java.lang.String r6 = "Ignoring negative offset to sample data."
            com.appsamurai.storyly.exoplayer2.common.util.Log.w(r5, r6)
            r5 = r4
        L_0x003e:
            r1.skipFully(r5)
            r0.currentTrackBundle = r2
        L_0x0043:
            int r5 = r0.parserState
            r6 = 3
            r7 = 4
            r8 = 1
            if (r5 != r6) goto L_0x00b7
            int r5 = r2.getCurrentSampleSize()
            r0.sampleSize = r5
            int r9 = r2.currentSampleIndex
            int r10 = r2.firstSampleToOutputIndex
            if (r9 >= r10) goto L_0x0067
            r1.skipFully(r5)
            r2.skipSampleEncryptionData()
            boolean r1 = r2.next()
            if (r1 != 0) goto L_0x0064
            r0.currentTrackBundle = r3
        L_0x0064:
            r0.parserState = r6
            return r8
        L_0x0067:
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.TrackSampleTable r9 = r2.moovSampleTable
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.Track r9 = r9.track
            int r9 = r9.sampleTransformation
            if (r9 != r8) goto L_0x0077
            r9 = 8
            int r5 = r5 - r9
            r0.sampleSize = r5
            r1.skipFully(r9)
        L_0x0077:
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.TrackSampleTable r5 = r2.moovSampleTable
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.Track r5 = r5.track
            com.appsamurai.storyly.exoplayer2.common.Format r5 = r5.format
            java.lang.String r5 = r5.sampleMimeType
            java.lang.String r9 = "audio/ac4"
            boolean r5 = r9.equals(r5)
            if (r5 == 0) goto L_0x00a4
            int r5 = r0.sampleSize
            r9 = 7
            int r5 = r2.outputSampleEncryptionData(r5, r9)
            r0.sampleBytesWritten = r5
            int r5 = r0.sampleSize
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r10 = r0.scratch
            com.appsamurai.storyly.exoplayer2.extractor.audio.Ac4Util.getAc4SampleHeader(r5, r10)
            com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput r5 = r2.output
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r10 = r0.scratch
            r5.sampleData(r10, r9)
            int r5 = r0.sampleBytesWritten
            int r5 = r5 + r9
            r0.sampleBytesWritten = r5
            goto L_0x00ac
        L_0x00a4:
            int r5 = r0.sampleSize
            int r5 = r2.outputSampleEncryptionData(r5, r4)
            r0.sampleBytesWritten = r5
        L_0x00ac:
            int r5 = r0.sampleSize
            int r9 = r0.sampleBytesWritten
            int r5 = r5 + r9
            r0.sampleSize = r5
            r0.parserState = r7
            r0.sampleCurrentNalBytesRemaining = r4
        L_0x00b7:
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.TrackSampleTable r5 = r2.moovSampleTable
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.Track r5 = r5.track
            com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput r9 = r2.output
            long r10 = r2.getCurrentSamplePresentationTimeUs()
            com.appsamurai.storyly.exoplayer2.common.util.TimestampAdjuster r12 = r0.timestampAdjuster
            if (r12 == 0) goto L_0x00c9
            long r10 = r12.adjustSampleTimestamp(r10)
        L_0x00c9:
            r14 = r10
            int r10 = r5.nalUnitLengthFieldLength
            if (r10 == 0) goto L_0x0196
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r10 = r0.nalPrefix
            byte[] r10 = r10.getData()
            r10[r4] = r4
            r10[r8] = r4
            r11 = 2
            r10[r11] = r4
            int r11 = r5.nalUnitLengthFieldLength
            int r12 = r11 + 1
            int r11 = 4 - r11
        L_0x00e1:
            int r13 = r0.sampleBytesWritten
            int r6 = r0.sampleSize
            if (r13 >= r6) goto L_0x01a7
            int r6 = r0.sampleCurrentNalBytesRemaining
            if (r6 != 0) goto L_0x0138
            r1.readFully(r10, r11, r12)
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r6 = r0.nalPrefix
            r6.setPosition(r4)
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r6 = r0.nalPrefix
            int r6 = r6.readInt()
            if (r6 < r8) goto L_0x0131
            int r6 = r6 + -1
            r0.sampleCurrentNalBytesRemaining = r6
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r6 = r0.nalStartCode
            r6.setPosition(r4)
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r6 = r0.nalStartCode
            r9.sampleData(r6, r7)
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r6 = r0.nalPrefix
            r9.sampleData(r6, r8)
            com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput[] r6 = r0.ceaTrackOutputs
            int r6 = r6.length
            if (r6 <= 0) goto L_0x0121
            com.appsamurai.storyly.exoplayer2.common.Format r6 = r5.format
            java.lang.String r6 = r6.sampleMimeType
            byte r13 = r10[r7]
            boolean r6 = com.appsamurai.storyly.exoplayer2.extractor.util.NalUnitUtil.isNalUnitSei(r6, r13)
            if (r6 == 0) goto L_0x0121
            r6 = r8
            goto L_0x0122
        L_0x0121:
            r6 = r4
        L_0x0122:
            r0.processSeiNalUnitPayload = r6
            int r6 = r0.sampleBytesWritten
            int r6 = r6 + 5
            r0.sampleBytesWritten = r6
            int r6 = r0.sampleSize
            int r6 = r6 + r11
            r0.sampleSize = r6
            r6 = 3
            goto L_0x00e1
        L_0x0131:
            java.lang.String r0 = "Invalid NAL length"
            com.appsamurai.storyly.exoplayer2.common.ParserException r0 = com.appsamurai.storyly.exoplayer2.common.ParserException.createForMalformedContainer(r0, r3)
            throw r0
        L_0x0138:
            boolean r13 = r0.processSeiNalUnitPayload
            if (r13 == 0) goto L_0x0182
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r13 = r0.nalBuffer
            r13.reset((int) r6)
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r6 = r0.nalBuffer
            byte[] r6 = r6.getData()
            int r13 = r0.sampleCurrentNalBytesRemaining
            r1.readFully(r6, r4, r13)
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r6 = r0.nalBuffer
            int r13 = r0.sampleCurrentNalBytesRemaining
            r9.sampleData(r6, r13)
            int r6 = r0.sampleCurrentNalBytesRemaining
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r13 = r0.nalBuffer
            byte[] r13 = r13.getData()
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r7 = r0.nalBuffer
            int r7 = r7.limit()
            int r7 = com.appsamurai.storyly.exoplayer2.extractor.util.NalUnitUtil.unescapeStream(r13, r7)
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r13 = r0.nalBuffer
            com.appsamurai.storyly.exoplayer2.common.Format r8 = r5.format
            java.lang.String r8 = r8.sampleMimeType
            java.lang.String r3 = "video/hevc"
            boolean r3 = r3.equals(r8)
            r13.setPosition(r3)
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r3 = r0.nalBuffer
            r3.setLimit(r7)
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r3 = r0.nalBuffer
            com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput[] r7 = r0.ceaTrackOutputs
            com.appsamurai.storyly.exoplayer2.extractor.extractor.CeaUtil.consume(r14, r3, r7)
            goto L_0x0186
        L_0x0182:
            int r6 = r9.sampleData((com.appsamurai.storyly.exoplayer2.common.upstream.DataReader) r1, (int) r6, (boolean) r4)
        L_0x0186:
            int r3 = r0.sampleBytesWritten
            int r3 = r3 + r6
            r0.sampleBytesWritten = r3
            int r3 = r0.sampleCurrentNalBytesRemaining
            int r3 = r3 - r6
            r0.sampleCurrentNalBytesRemaining = r3
            r3 = 0
            r6 = 3
            r7 = 4
            r8 = 1
            goto L_0x00e1
        L_0x0196:
            int r3 = r0.sampleBytesWritten
            int r5 = r0.sampleSize
            if (r3 >= r5) goto L_0x01a7
            int r5 = r5 - r3
            int r3 = r9.sampleData((com.appsamurai.storyly.exoplayer2.common.upstream.DataReader) r1, (int) r5, (boolean) r4)
            int r5 = r0.sampleBytesWritten
            int r5 = r5 + r3
            r0.sampleBytesWritten = r5
            goto L_0x0196
        L_0x01a7:
            int r12 = r2.getCurrentSampleFlags()
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.TrackEncryptionBox r1 = r2.getEncryptionBoxIfEncrypted()
            if (r1 == 0) goto L_0x01b4
            com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput$CryptoData r1 = r1.cryptoData
            goto L_0x01b5
        L_0x01b4:
            r1 = 0
        L_0x01b5:
            int r13 = r0.sampleSize
            r3 = 0
            r10 = r14
            r4 = r14
            r14 = r3
            r15 = r1
            r9.sampleMetadata(r10, r12, r13, r14, r15)
            r0.outputPendingMetadataSamples(r4)
            boolean r1 = r2.next()
            if (r1 != 0) goto L_0x01cb
            r1 = 0
            r0.currentTrackBundle = r1
        L_0x01cb:
            r1 = 3
            r0.parserState = r1
            r0 = 1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4.FragmentedMp4Extractor.readSample(com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput):boolean");
    }

    private static boolean shouldParseContainerAtom(int i3) {
        return i3 == 1836019574 || i3 == 1953653099 || i3 == 1835297121 || i3 == 1835626086 || i3 == 1937007212 || i3 == 1836019558 || i3 == 1953653094 || i3 == 1836475768 || i3 == 1701082227;
    }

    private static boolean shouldParseLeafAtom(int i3) {
        return i3 == 1751411826 || i3 == 1835296868 || i3 == 1836476516 || i3 == 1936286840 || i3 == 1937011556 || i3 == 1937011827 || i3 == 1668576371 || i3 == 1937011555 || i3 == 1937011578 || i3 == 1937013298 || i3 == 1937007471 || i3 == 1668232756 || i3 == 1937011571 || i3 == 1952867444 || i3 == 1952868452 || i3 == 1953196132 || i3 == 1953654136 || i3 == 1953658222 || i3 == 1886614376 || i3 == 1935763834 || i3 == 1935763823 || i3 == 1936027235 || i3 == 1970628964 || i3 == 1935828848 || i3 == 1936158820 || i3 == 1701606260 || i3 == 1835362404 || i3 == 1701671783;
    }

    public void init(ExtractorOutput extractorOutput2) {
        this.extractorOutput = extractorOutput2;
        enterReadingAtomHeaderState();
        initExtraTracks();
        Track track = this.sideloadedTrack;
        if (track != null) {
            this.trackBundles.put(0, new TrackBundle(extractorOutput2.track(0, track.type), new TrackSampleTable(this.sideloadedTrack, new long[0], new int[0], 0, new long[0], new int[0], 0), new DefaultSampleValues(0, 0, 0, 0)));
            this.extractorOutput.endTracks();
        }
    }

    @Nullable
    public Track modifyTrack(@Nullable Track track) {
        return track;
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        while (true) {
            int i3 = this.parserState;
            if (i3 != 0) {
                if (i3 == 1) {
                    readAtomPayload(extractorInput);
                } else if (i3 == 2) {
                    readEncryptionData(extractorInput);
                } else if (readSample(extractorInput)) {
                    return 0;
                }
            } else if (!readAtomHeader(extractorInput)) {
                return -1;
            }
        }
    }

    public void release() {
    }

    public void seek(long j2, long j3) {
        int size = this.trackBundles.size();
        for (int i3 = 0; i3 < size; i3++) {
            this.trackBundles.valueAt(i3).resetFragmentInfo();
        }
        this.pendingMetadataSampleInfos.clear();
        this.pendingMetadataSampleBytes = 0;
        this.pendingSeekTimeUs = j3;
        this.containerAtoms.clear();
        enterReadingAtomHeaderState();
    }

    public boolean sniff(ExtractorInput extractorInput) throws IOException {
        return Sniffer.sniffFragmented(extractorInput);
    }

    public FragmentedMp4Extractor(int i3) {
        this(i3, (TimestampAdjuster) null);
    }

    private static void parseSenc(ParsableByteArray parsableByteArray, int i3, TrackFragment trackFragment) throws ParserException {
        parsableByteArray.setPosition(i3 + 8);
        int parseFullAtomFlags = Atom.parseFullAtomFlags(parsableByteArray.readInt());
        if ((parseFullAtomFlags & 1) == 0) {
            boolean z2 = (parseFullAtomFlags & 2) != 0;
            int readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
            if (readUnsignedIntToInt == 0) {
                Arrays.fill(trackFragment.sampleHasSubsampleEncryptionTable, 0, trackFragment.sampleCount, false);
            } else if (readUnsignedIntToInt == trackFragment.sampleCount) {
                Arrays.fill(trackFragment.sampleHasSubsampleEncryptionTable, 0, readUnsignedIntToInt, z2);
                trackFragment.initEncryptionData(parsableByteArray.bytesLeft());
                trackFragment.fillEncryptionData(parsableByteArray);
            } else {
                StringBuilder o3 = A.a.o(readUnsignedIntToInt, "Senc sample count ", " is different from fragment sample count");
                o3.append(trackFragment.sampleCount);
                throw ParserException.createForMalformedContainer(o3.toString(), (Throwable) null);
            }
        } else {
            throw ParserException.createForUnsupportedContainerFeature("Overriding TrackEncryptionBox parameters is unsupported.");
        }
    }

    public FragmentedMp4Extractor(int i3, @Nullable TimestampAdjuster timestampAdjuster2) {
        this(i3, timestampAdjuster2, (Track) null, Collections.emptyList());
    }

    public FragmentedMp4Extractor(int i3, @Nullable TimestampAdjuster timestampAdjuster2, @Nullable Track track) {
        this(i3, timestampAdjuster2, track, Collections.emptyList());
    }

    public FragmentedMp4Extractor(int i3, @Nullable TimestampAdjuster timestampAdjuster2, @Nullable Track track, List<Format> list) {
        this(i3, timestampAdjuster2, track, list, (TrackOutput) null);
    }

    public FragmentedMp4Extractor(int i3, @Nullable TimestampAdjuster timestampAdjuster2, @Nullable Track track, List<Format> list, @Nullable TrackOutput trackOutput) {
        this.flags = i3;
        this.timestampAdjuster = timestampAdjuster2;
        this.sideloadedTrack = track;
        this.closedCaptionFormats = Collections.unmodifiableList(list);
        this.additionalEmsgTrackOutput = trackOutput;
        this.eventMessageEncoder = new EventMessageEncoder();
        this.atomHeader = new ParsableByteArray(16);
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalPrefix = new ParsableByteArray(5);
        this.nalBuffer = new ParsableByteArray();
        byte[] bArr = new byte[16];
        this.scratchBytes = bArr;
        this.scratch = new ParsableByteArray(bArr);
        this.containerAtoms = new ArrayDeque<>();
        this.pendingMetadataSampleInfos = new ArrayDeque<>();
        this.trackBundles = new SparseArray<>();
        this.durationUs = C.TIME_UNSET;
        this.pendingSeekTimeUs = C.TIME_UNSET;
        this.segmentIndexEarliestPresentationTimeUs = C.TIME_UNSET;
        this.extractorOutput = ExtractorOutput.PLACEHOLDER;
        this.emsgTrackOutputs = new TrackOutput[0];
        this.ceaTrackOutputs = new TrackOutput[0];
    }
}
