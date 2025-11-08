package com.appsamurai.storyly.exoplayer2.extractor.extractor.mkv;

import android.util.Pair;
import android.util.SparseArray;
import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.ParserException;
import com.appsamurai.storyly.exoplayer2.common.drm.DrmInitData;
import com.appsamurai.storyly.exoplayer2.common.upstream.DataReader;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.LongArray;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.common.video.ColorInfo;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ChunkIndex;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.Extractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorOutput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorsFactory;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.PositionHolder;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.SeekMap;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.TrueHdSampleRechunker;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.a;
import com.appsamurai.storyly.exoplayer2.extractor.util.NalUnitUtil;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.lang3.CharUtils;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.web3j.tx.ChainId;

public class MatroskaExtractor implements Extractor {
    private static final int BLOCK_ADDITIONAL_ID_VP9_ITU_T_35 = 4;
    private static final int BLOCK_ADD_ID_TYPE_DVCC = 1685480259;
    private static final int BLOCK_ADD_ID_TYPE_DVVC = 1685485123;
    private static final int BLOCK_STATE_DATA = 2;
    private static final int BLOCK_STATE_HEADER = 1;
    private static final int BLOCK_STATE_START = 0;
    private static final String CODEC_ID_AAC = "A_AAC";
    private static final String CODEC_ID_AC3 = "A_AC3";
    private static final String CODEC_ID_ACM = "A_MS/ACM";
    private static final String CODEC_ID_ASS = "S_TEXT/ASS";
    private static final String CODEC_ID_AV1 = "V_AV1";
    private static final String CODEC_ID_DTS = "A_DTS";
    private static final String CODEC_ID_DTS_EXPRESS = "A_DTS/EXPRESS";
    private static final String CODEC_ID_DTS_LOSSLESS = "A_DTS/LOSSLESS";
    private static final String CODEC_ID_DVBSUB = "S_DVBSUB";
    private static final String CODEC_ID_E_AC3 = "A_EAC3";
    private static final String CODEC_ID_FLAC = "A_FLAC";
    private static final String CODEC_ID_FOURCC = "V_MS/VFW/FOURCC";
    private static final String CODEC_ID_H264 = "V_MPEG4/ISO/AVC";
    private static final String CODEC_ID_H265 = "V_MPEGH/ISO/HEVC";
    private static final String CODEC_ID_MP2 = "A_MPEG/L2";
    private static final String CODEC_ID_MP3 = "A_MPEG/L3";
    private static final String CODEC_ID_MPEG2 = "V_MPEG2";
    private static final String CODEC_ID_MPEG4_AP = "V_MPEG4/ISO/AP";
    private static final String CODEC_ID_MPEG4_ASP = "V_MPEG4/ISO/ASP";
    private static final String CODEC_ID_MPEG4_SP = "V_MPEG4/ISO/SP";
    private static final String CODEC_ID_OPUS = "A_OPUS";
    private static final String CODEC_ID_PCM_FLOAT = "A_PCM/FLOAT/IEEE";
    private static final String CODEC_ID_PCM_INT_BIG = "A_PCM/INT/BIG";
    private static final String CODEC_ID_PCM_INT_LIT = "A_PCM/INT/LIT";
    private static final String CODEC_ID_PGS = "S_HDMV/PGS";
    private static final String CODEC_ID_SUBRIP = "S_TEXT/UTF8";
    private static final String CODEC_ID_THEORA = "V_THEORA";
    private static final String CODEC_ID_TRUEHD = "A_TRUEHD";
    private static final String CODEC_ID_VOBSUB = "S_VOBSUB";
    private static final String CODEC_ID_VORBIS = "A_VORBIS";
    private static final String CODEC_ID_VP8 = "V_VP8";
    private static final String CODEC_ID_VP9 = "V_VP9";
    private static final String CODEC_ID_VTT = "S_TEXT/WEBVTT";
    private static final String DOC_TYPE_MATROSKA = "matroska";
    private static final String DOC_TYPE_WEBM = "webm";
    private static final int ENCRYPTION_IV_SIZE = 8;
    public static final ExtractorsFactory FACTORY = new a(4);
    public static final int FLAG_DISABLE_SEEK_FOR_CUES = 1;
    private static final int FOURCC_COMPRESSION_DIVX = 1482049860;
    private static final int FOURCC_COMPRESSION_H263 = 859189832;
    private static final int FOURCC_COMPRESSION_VC1 = 826496599;
    private static final int ID_AUDIO = 225;
    private static final int ID_AUDIO_BIT_DEPTH = 25188;
    private static final int ID_BLOCK = 161;
    private static final int ID_BLOCK_ADDITIONAL = 165;
    private static final int ID_BLOCK_ADDITIONS = 30113;
    private static final int ID_BLOCK_ADDITION_MAPPING = 16868;
    private static final int ID_BLOCK_ADD_ID = 238;
    private static final int ID_BLOCK_ADD_ID_EXTRA_DATA = 16877;
    private static final int ID_BLOCK_ADD_ID_TYPE = 16871;
    private static final int ID_BLOCK_DURATION = 155;
    private static final int ID_BLOCK_GROUP = 160;
    private static final int ID_BLOCK_MORE = 166;
    private static final int ID_CHANNELS = 159;
    private static final int ID_CLUSTER = 524531317;
    private static final int ID_CODEC_DELAY = 22186;
    private static final int ID_CODEC_ID = 134;
    private static final int ID_CODEC_PRIVATE = 25506;
    private static final int ID_COLOUR = 21936;
    private static final int ID_COLOUR_PRIMARIES = 21947;
    private static final int ID_COLOUR_RANGE = 21945;
    private static final int ID_COLOUR_TRANSFER = 21946;
    private static final int ID_CONTENT_COMPRESSION = 20532;
    private static final int ID_CONTENT_COMPRESSION_ALGORITHM = 16980;
    private static final int ID_CONTENT_COMPRESSION_SETTINGS = 16981;
    private static final int ID_CONTENT_ENCODING = 25152;
    private static final int ID_CONTENT_ENCODINGS = 28032;
    private static final int ID_CONTENT_ENCODING_ORDER = 20529;
    private static final int ID_CONTENT_ENCODING_SCOPE = 20530;
    private static final int ID_CONTENT_ENCRYPTION = 20533;
    private static final int ID_CONTENT_ENCRYPTION_AES_SETTINGS = 18407;
    private static final int ID_CONTENT_ENCRYPTION_AES_SETTINGS_CIPHER_MODE = 18408;
    private static final int ID_CONTENT_ENCRYPTION_ALGORITHM = 18401;
    private static final int ID_CONTENT_ENCRYPTION_KEY_ID = 18402;
    private static final int ID_CUES = 475249515;
    private static final int ID_CUE_CLUSTER_POSITION = 241;
    private static final int ID_CUE_POINT = 187;
    private static final int ID_CUE_TIME = 179;
    private static final int ID_CUE_TRACK_POSITIONS = 183;
    private static final int ID_DEFAULT_DURATION = 2352003;
    private static final int ID_DISCARD_PADDING = 30114;
    private static final int ID_DISPLAY_HEIGHT = 21690;
    private static final int ID_DISPLAY_UNIT = 21682;
    private static final int ID_DISPLAY_WIDTH = 21680;
    private static final int ID_DOC_TYPE = 17026;
    private static final int ID_DOC_TYPE_READ_VERSION = 17029;
    private static final int ID_DURATION = 17545;
    private static final int ID_EBML = 440786851;
    private static final int ID_EBML_READ_VERSION = 17143;
    private static final int ID_FLAG_DEFAULT = 136;
    private static final int ID_FLAG_FORCED = 21930;
    private static final int ID_INFO = 357149030;
    private static final int ID_LANGUAGE = 2274716;
    private static final int ID_LUMNINANCE_MAX = 21977;
    private static final int ID_LUMNINANCE_MIN = 21978;
    private static final int ID_MASTERING_METADATA = 21968;
    private static final int ID_MAX_BLOCK_ADDITION_ID = 21998;
    private static final int ID_MAX_CLL = 21948;
    private static final int ID_MAX_FALL = 21949;
    private static final int ID_NAME = 21358;
    private static final int ID_PIXEL_HEIGHT = 186;
    private static final int ID_PIXEL_WIDTH = 176;
    private static final int ID_PRIMARY_B_CHROMATICITY_X = 21973;
    private static final int ID_PRIMARY_B_CHROMATICITY_Y = 21974;
    private static final int ID_PRIMARY_G_CHROMATICITY_X = 21971;
    private static final int ID_PRIMARY_G_CHROMATICITY_Y = 21972;
    private static final int ID_PRIMARY_R_CHROMATICITY_X = 21969;
    private static final int ID_PRIMARY_R_CHROMATICITY_Y = 21970;
    private static final int ID_PROJECTION = 30320;
    private static final int ID_PROJECTION_POSE_PITCH = 30324;
    private static final int ID_PROJECTION_POSE_ROLL = 30325;
    private static final int ID_PROJECTION_POSE_YAW = 30323;
    private static final int ID_PROJECTION_PRIVATE = 30322;
    private static final int ID_PROJECTION_TYPE = 30321;
    private static final int ID_REFERENCE_BLOCK = 251;
    private static final int ID_SAMPLING_FREQUENCY = 181;
    private static final int ID_SEEK = 19899;
    private static final int ID_SEEK_HEAD = 290298740;
    private static final int ID_SEEK_ID = 21419;
    private static final int ID_SEEK_POSITION = 21420;
    private static final int ID_SEEK_PRE_ROLL = 22203;
    private static final int ID_SEGMENT = 408125543;
    private static final int ID_SEGMENT_INFO = 357149030;
    private static final int ID_SIMPLE_BLOCK = 163;
    private static final int ID_STEREO_MODE = 21432;
    private static final int ID_TIMECODE_SCALE = 2807729;
    private static final int ID_TIME_CODE = 231;
    private static final int ID_TRACKS = 374648427;
    private static final int ID_TRACK_ENTRY = 174;
    private static final int ID_TRACK_NUMBER = 215;
    private static final int ID_TRACK_TYPE = 131;
    private static final int ID_VIDEO = 224;
    private static final int ID_WHITE_POINT_CHROMATICITY_X = 21975;
    private static final int ID_WHITE_POINT_CHROMATICITY_Y = 21976;
    private static final int LACING_EBML = 3;
    private static final int LACING_FIXED_SIZE = 2;
    private static final int LACING_NONE = 0;
    private static final int LACING_XIPH = 1;
    private static final int OPUS_MAX_INPUT_SIZE = 5760;
    /* access modifiers changed from: private */
    public static final byte[] SSA_DIALOGUE_FORMAT = Util.getUtf8Bytes("Format: Start, End, ReadOrder, Layer, Style, Name, MarginL, MarginR, MarginV, Effect, Text");
    private static final byte[] SSA_PREFIX = {68, 105, 97, 108, 111, 103, 117, 101, 58, 32, 48, 58, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 58, 48, 48, 58, 48, 48, 58, 48, 48, 44};
    private static final int SSA_PREFIX_END_TIMECODE_OFFSET = 21;
    private static final String SSA_TIMECODE_FORMAT = "%01d:%02d:%02d:%02d";
    private static final long SSA_TIMECODE_LAST_VALUE_SCALING_FACTOR = 10000;
    private static final byte[] SUBRIP_PREFIX = {49, 10, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 32, 45, 45, ChainId.ETHEREUM_CLASSIC_TESTNET, 32, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 10};
    private static final int SUBRIP_PREFIX_END_TIMECODE_OFFSET = 19;
    private static final String SUBRIP_TIMECODE_FORMAT = "%02d:%02d:%02d,%03d";
    private static final long SUBRIP_TIMECODE_LAST_VALUE_SCALING_FACTOR = 1000;
    private static final String TAG = "MatroskaExtractor";
    /* access modifiers changed from: private */
    public static final Map<String, Integer> TRACK_NAME_TO_ROTATION_DEGREES;
    private static final int TRACK_TYPE_AUDIO = 2;
    private static final int UNSET_ENTRY_ID = -1;
    private static final int VORBIS_MAX_INPUT_SIZE = 8192;
    private static final byte[] VTT_PREFIX = {87, 69, 66, 86, 84, 84, 10, 10, 48, 48, 58, 48, 48, 58, 48, 48, 46, 48, 48, 48, 32, 45, 45, ChainId.ETHEREUM_CLASSIC_TESTNET, 32, 48, 48, 58, 48, 48, 58, 48, 48, 46, 48, 48, 48, 10};
    private static final int VTT_PREFIX_END_TIMECODE_OFFSET = 25;
    private static final String VTT_TIMECODE_FORMAT = "%02d:%02d:%02d.%03d";
    private static final long VTT_TIMECODE_LAST_VALUE_SCALING_FACTOR = 1000;
    private static final int WAVE_FORMAT_EXTENSIBLE = 65534;
    private static final int WAVE_FORMAT_PCM = 1;
    private static final int WAVE_FORMAT_SIZE = 18;
    /* access modifiers changed from: private */
    public static final UUID WAVE_SUBFORMAT_PCM = new UUID(72057594037932032L, -9223371306706625679L);
    private int blockAdditionalId;
    private long blockDurationUs;
    private int blockFlags;
    private long blockGroupDiscardPaddingNs;
    private boolean blockHasReferenceBlock;
    private int blockSampleCount;
    private int blockSampleIndex;
    private int[] blockSampleSizes;
    private int blockState;
    private long blockTimeUs;
    private int blockTrackNumber;
    private int blockTrackNumberLength;
    private long clusterTimecodeUs;
    @Nullable
    private LongArray cueClusterPositions;
    @Nullable
    private LongArray cueTimesUs;
    private long cuesContentPosition;
    @Nullable
    private Track currentTrack;
    private long durationTimecode;
    private long durationUs;
    private final ParsableByteArray encryptionInitializationVector;
    private final ParsableByteArray encryptionSubsampleData;
    private ByteBuffer encryptionSubsampleDataBuffer;
    private ExtractorOutput extractorOutput;
    private boolean haveOutputSample;
    private final ParsableByteArray nalLength;
    private final ParsableByteArray nalStartCode;
    private final EbmlReader reader;
    private int sampleBytesRead;
    private int sampleBytesWritten;
    private int sampleCurrentNalBytesRemaining;
    private boolean sampleEncodingHandled;
    private boolean sampleInitializationVectorRead;
    private int samplePartitionCount;
    private boolean samplePartitionCountRead;
    private byte sampleSignalByte;
    private boolean sampleSignalByteRead;
    private final ParsableByteArray sampleStrippedBytes;
    private final ParsableByteArray scratch;
    private int seekEntryId;
    private final ParsableByteArray seekEntryIdBytes;
    private long seekEntryPosition;
    private boolean seekForCues;
    private final boolean seekForCuesEnabled;
    private long seekPositionAfterBuildingCues;
    private boolean seenClusterPositionForCurrentCuePoint;
    private long segmentContentPosition;
    private long segmentContentSize;
    private boolean sentSeekMap;
    private final ParsableByteArray subtitleSample;
    private final ParsableByteArray supplementalData;
    private long timecodeScale;
    private final SparseArray<Track> tracks;
    private final VarintReader varintReader;
    private final ParsableByteArray vorbisNumPageSamples;

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    public final class InnerEbmlProcessor implements EbmlProcessor {
        private InnerEbmlProcessor() {
        }

        public void binaryElement(int i3, int i4, ExtractorInput extractorInput) throws IOException {
            MatroskaExtractor.this.binaryElement(i3, i4, extractorInput);
        }

        public void endMasterElement(int i3) throws ParserException {
            MatroskaExtractor.this.endMasterElement(i3);
        }

        public void floatElement(int i3, double d2) throws ParserException {
            MatroskaExtractor.this.floatElement(i3, d2);
        }

        public int getElementType(int i3) {
            return MatroskaExtractor.this.getElementType(i3);
        }

        public void integerElement(int i3, long j2) throws ParserException {
            MatroskaExtractor.this.integerElement(i3, j2);
        }

        public boolean isLevel1Element(int i3) {
            return MatroskaExtractor.this.isLevel1Element(i3);
        }

        public void startMasterElement(int i3, long j2, long j3) throws ParserException {
            MatroskaExtractor.this.startMasterElement(i3, j2, j3);
        }

        public void stringElement(int i3, String str) throws ParserException {
            MatroskaExtractor.this.stringElement(i3, str);
        }
    }

    public static final class Track {
        private static final int DEFAULT_MAX_CLL = 1000;
        private static final int DEFAULT_MAX_FALL = 200;
        private static final int DISPLAY_UNIT_PIXELS = 0;
        private static final int MAX_CHROMATICITY = 50000;
        public int audioBitDepth = -1;
        /* access modifiers changed from: private */
        public int blockAddIdType;
        public int channelCount = 1;
        public long codecDelayNs = 0;
        public String codecId;
        public byte[] codecPrivate;
        public int colorRange = -1;
        public int colorSpace = -1;
        public int colorTransfer = -1;
        public TrackOutput.CryptoData cryptoData;
        public int defaultSampleDurationNs;
        public int displayHeight = -1;
        public int displayUnit = 0;
        public int displayWidth = -1;
        public byte[] dolbyVisionConfigBytes;
        public DrmInitData drmInitData;
        public boolean flagDefault = true;
        public boolean flagForced;
        public boolean hasColorInfo = false;
        public boolean hasContentEncryption;
        public int height = -1;
        /* access modifiers changed from: private */
        public String language = "eng";
        public int maxBlockAdditionId;
        public int maxContentLuminance = 1000;
        public int maxFrameAverageLuminance = 200;
        public float maxMasteringLuminance = -1.0f;
        public float minMasteringLuminance = -1.0f;
        public int nalUnitLengthFieldLength;
        public String name;
        public int number;
        public TrackOutput output;
        public float primaryBChromaticityX = -1.0f;
        public float primaryBChromaticityY = -1.0f;
        public float primaryGChromaticityX = -1.0f;
        public float primaryGChromaticityY = -1.0f;
        public float primaryRChromaticityX = -1.0f;
        public float primaryRChromaticityY = -1.0f;
        public byte[] projectionData = null;
        public float projectionPosePitch = 0.0f;
        public float projectionPoseRoll = 0.0f;
        public float projectionPoseYaw = 0.0f;
        public int projectionType = -1;
        public int sampleRate = 8000;
        public byte[] sampleStrippedBytes;
        public long seekPreRollNs = 0;
        public int stereoMode = -1;
        public TrueHdSampleRechunker trueHdSampleRechunker;
        public int type;
        public float whitePointChromaticityX = -1.0f;
        public float whitePointChromaticityY = -1.0f;
        public int width = -1;

        /* access modifiers changed from: private */
        @EnsuresNonNull({"output"})
        public void assertOutputInitialized() {
            Assertions.checkNotNull(this.output);
        }

        @EnsuresNonNull({"codecPrivate"})
        private byte[] getCodecPrivate(String str) throws ParserException {
            byte[] bArr = this.codecPrivate;
            if (bArr != null) {
                return bArr;
            }
            throw ParserException.createForMalformedContainer("Missing CodecPrivate for codec " + str, (Throwable) null);
        }

        @Nullable
        private byte[] getHdrStaticInfo() {
            if (this.primaryRChromaticityX == -1.0f || this.primaryRChromaticityY == -1.0f || this.primaryGChromaticityX == -1.0f || this.primaryGChromaticityY == -1.0f || this.primaryBChromaticityX == -1.0f || this.primaryBChromaticityY == -1.0f || this.whitePointChromaticityX == -1.0f || this.whitePointChromaticityY == -1.0f || this.maxMasteringLuminance == -1.0f || this.minMasteringLuminance == -1.0f) {
                return null;
            }
            byte[] bArr = new byte[25];
            ByteBuffer order = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
            order.put((byte) 0);
            order.putShort((short) ((int) ((this.primaryRChromaticityX * 50000.0f) + 0.5f)));
            order.putShort((short) ((int) ((this.primaryRChromaticityY * 50000.0f) + 0.5f)));
            order.putShort((short) ((int) ((this.primaryGChromaticityX * 50000.0f) + 0.5f)));
            order.putShort((short) ((int) ((this.primaryGChromaticityY * 50000.0f) + 0.5f)));
            order.putShort((short) ((int) ((this.primaryBChromaticityX * 50000.0f) + 0.5f)));
            order.putShort((short) ((int) ((this.primaryBChromaticityY * 50000.0f) + 0.5f)));
            order.putShort((short) ((int) ((this.whitePointChromaticityX * 50000.0f) + 0.5f)));
            order.putShort((short) ((int) ((this.whitePointChromaticityY * 50000.0f) + 0.5f)));
            order.putShort((short) ((int) (this.maxMasteringLuminance + 0.5f)));
            order.putShort((short) ((int) (this.minMasteringLuminance + 0.5f)));
            order.putShort((short) this.maxContentLuminance);
            order.putShort((short) this.maxFrameAverageLuminance);
            return bArr;
        }

        private static Pair<String, List<byte[]>> parseFourCcPrivate(ParsableByteArray parsableByteArray) throws ParserException {
            try {
                parsableByteArray.skipBytes(16);
                long readLittleEndianUnsignedInt = parsableByteArray.readLittleEndianUnsignedInt();
                if (readLittleEndianUnsignedInt == 1482049860) {
                    return new Pair<>(MimeTypes.VIDEO_DIVX, (Object) null);
                }
                if (readLittleEndianUnsignedInt == 859189832) {
                    return new Pair<>(MimeTypes.VIDEO_H263, (Object) null);
                }
                if (readLittleEndianUnsignedInt == 826496599) {
                    byte[] data = parsableByteArray.getData();
                    for (int position = parsableByteArray.getPosition() + 20; position < data.length - 4; position++) {
                        if (data[position] == 0 && data[position + 1] == 0 && data[position + 2] == 1 && data[position + 3] == 15) {
                            return new Pair<>(MimeTypes.VIDEO_VC1, Collections.singletonList(Arrays.copyOfRange(data, position, data.length)));
                        }
                    }
                    throw ParserException.createForMalformedContainer("Failed to find FourCC VC1 initialization data", (Throwable) null);
                }
                Log.w(MatroskaExtractor.TAG, "Unknown FourCC. Setting mimeType to video/x-unknown");
                return new Pair<>(MimeTypes.VIDEO_UNKNOWN, (Object) null);
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw ParserException.createForMalformedContainer("Error parsing FourCC private data", (Throwable) null);
            }
        }

        private static boolean parseMsAcmCodecPrivate(ParsableByteArray parsableByteArray) throws ParserException {
            try {
                int readLittleEndianUnsignedShort = parsableByteArray.readLittleEndianUnsignedShort();
                if (readLittleEndianUnsignedShort == 1) {
                    return true;
                }
                if (readLittleEndianUnsignedShort != 65534) {
                    return false;
                }
                parsableByteArray.setPosition(24);
                return parsableByteArray.readLong() == MatroskaExtractor.WAVE_SUBFORMAT_PCM.getMostSignificantBits() && parsableByteArray.readLong() == MatroskaExtractor.WAVE_SUBFORMAT_PCM.getLeastSignificantBits();
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw ParserException.createForMalformedContainer("Error parsing MS/ACM codec private", (Throwable) null);
            }
        }

        private static List<byte[]> parseVorbisCodecPrivate(byte[] bArr) throws ParserException {
            byte b3;
            byte b4;
            try {
                if (bArr[0] == 2) {
                    int i3 = 0;
                    int i4 = 1;
                    while (true) {
                        b3 = bArr[i4];
                        if ((b3 & 255) != 255) {
                            break;
                        }
                        i3 += 255;
                        i4++;
                    }
                    int i5 = i4 + 1;
                    int i6 = i3 + (b3 & 255);
                    int i7 = 0;
                    while (true) {
                        b4 = bArr[i5];
                        if ((b4 & 255) != 255) {
                            break;
                        }
                        i7 += 255;
                        i5++;
                    }
                    int i8 = i5 + 1;
                    int i9 = i7 + (b4 & 255);
                    if (bArr[i8] == 1) {
                        byte[] bArr2 = new byte[i6];
                        System.arraycopy(bArr, i8, bArr2, 0, i6);
                        int i10 = i8 + i6;
                        if (bArr[i10] == 3) {
                            int i11 = i10 + i9;
                            if (bArr[i11] == 5) {
                                byte[] bArr3 = new byte[(bArr.length - i11)];
                                System.arraycopy(bArr, i11, bArr3, 0, bArr.length - i11);
                                ArrayList arrayList = new ArrayList(2);
                                arrayList.add(bArr2);
                                arrayList.add(bArr3);
                                return arrayList;
                            }
                            throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", (Throwable) null);
                        }
                        throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", (Throwable) null);
                    }
                    throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", (Throwable) null);
                }
                throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", (Throwable) null);
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", (Throwable) null);
            }
        }

        /* access modifiers changed from: private */
        public boolean samplesHaveSupplementalData(boolean z2) {
            return MatroskaExtractor.CODEC_ID_OPUS.equals(this.codecId) ? z2 : this.maxBlockAdditionId > 0;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.util.ArrayList} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: com.google.common.collect.ImmutableList} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: com.google.common.collect.ImmutableList} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v13, resolved type: com.google.common.collect.ImmutableList} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v19, resolved type: com.google.common.collect.ImmutableList} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v23, resolved type: com.google.common.collect.ImmutableList} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v24, resolved type: com.google.common.collect.ImmutableList} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v19, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v13, resolved type: java.lang.String} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v40, resolved type: com.google.common.collect.ImmutableList} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v43, resolved type: java.util.ArrayList} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v65, resolved type: com.google.common.collect.ImmutableList} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v66, resolved type: com.google.common.collect.ImmutableList} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v70, resolved type: com.google.common.collect.ImmutableList} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v72, resolved type: com.google.common.collect.ImmutableList} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v75, resolved type: com.google.common.collect.ImmutableList} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v77, resolved type: com.google.common.collect.ImmutableList} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v78, resolved type: com.google.common.collect.ImmutableList} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v80, resolved type: com.google.common.collect.ImmutableList} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v81, resolved type: com.google.common.collect.ImmutableList} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v82, resolved type: com.google.common.collect.ImmutableList} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v84, resolved type: java.util.ArrayList} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v85, resolved type: java.util.ArrayList} */
        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* JADX WARNING: Code restructure failed: missing block: B:106:0x0229, code lost:
            r13 = -1;
            r1 = r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:108:0x0239, code lost:
            r1 = r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:109:0x023a, code lost:
            r6 = -1;
            r1 = r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:111:0x023e, code lost:
            r1 = null;
            r2 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:116:0x0269, code lost:
            r6 = -1;
            r13 = -1;
            r19 = r2;
            r2 = r1;
            r1 = r19;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:121:0x02a3, code lost:
            r1 = null;
            r2 = null;
            r16 = com.appsamurai.storyly.exoplayer2.common.util.MimeTypes.AUDIO_UNKNOWN;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:122:0x02a8, code lost:
            r1 = null;
            r2 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:124:0x02ab, code lost:
            r6 = -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:148:0x0393, code lost:
            r2 = null;
            r1 = r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:150:0x0399, code lost:
            r1 = null;
            r2 = null;
            r6 = 4096;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:167:0x0412, code lost:
            r4 = r0.dolbyVisionConfigBytes;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:168:0x0414, code lost:
            if (r4 == null) goto L_0x0426;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:169:0x0416, code lost:
            r4 = com.appsamurai.storyly.exoplayer2.extractor.video.DolbyVisionConfig.parse(new com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray(r4));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:170:0x041f, code lost:
            if (r4 == null) goto L_0x0426;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:171:0x0421, code lost:
            r2 = r4.codecs;
            r16 = com.appsamurai.storyly.exoplayer2.common.util.MimeTypes.VIDEO_DOLBY_VISION;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:172:0x0426, code lost:
            r4 = r16;
            r14 = r0.flagDefault;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:173:0x042c, code lost:
            if (r0.flagForced == false) goto L_0x0430;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:174:0x042e, code lost:
            r5 = 2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:175:0x0430, code lost:
            r5 = 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:176:0x0431, code lost:
            r5 = r5 | r14;
            r14 = new com.appsamurai.storyly.exoplayer2.common.Format.Builder();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:177:0x043b, code lost:
            if (com.appsamurai.storyly.exoplayer2.common.util.MimeTypes.isAudio(r4) == false) goto L_0x044f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:178:0x043d, code lost:
            r14.setChannelCount(r0.channelCount).setSampleRate(r0.sampleRate).setPcmEncoding(r13);
            r3 = 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:180:0x0453, code lost:
            if (com.appsamurai.storyly.exoplayer2.common.util.MimeTypes.isVideo(r4) == false) goto L_0x0529;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:182:0x0457, code lost:
            if (r0.displayUnit != 0) goto L_0x046b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:183:0x0459, code lost:
            r7 = r0.displayWidth;
            r8 = -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:184:0x045c, code lost:
            if (r7 != -1) goto L_0x0460;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:185:0x045e, code lost:
            r7 = r0.width;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:186:0x0460, code lost:
            r0.displayWidth = r7;
            r7 = r0.displayHeight;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:187:0x0464, code lost:
            if (r7 != -1) goto L_0x0468;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:188:0x0466, code lost:
            r7 = r0.height;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:189:0x0468, code lost:
            r0.displayHeight = r7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:190:0x046b, code lost:
            r8 = -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:191:0x046c, code lost:
            r7 = r0.displayWidth;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:192:0x046e, code lost:
            if (r7 == r8) goto L_0x047e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:193:0x0470, code lost:
            r9 = r0.displayHeight;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:194:0x0472, code lost:
            if (r9 == r8) goto L_0x047e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:195:0x0474, code lost:
            r7 = ((float) (r0.height * r7)) / ((float) (r0.width * r9));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:196:0x047e, code lost:
            r7 = -1.0f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:198:0x0482, code lost:
            if (r0.hasColorInfo == false) goto L_0x0494;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:199:0x0484, code lost:
            r3 = new com.appsamurai.storyly.exoplayer2.common.video.ColorInfo(r0.colorSpace, r0.colorRange, r0.colorTransfer, getHdrStaticInfo());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:201:0x0496, code lost:
            if (r0.name == null) goto L_0x04b4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:203:0x04a2, code lost:
            if (com.appsamurai.storyly.exoplayer2.extractor.extractor.mkv.MatroskaExtractor.access$600().containsKey(r0.name) == false) goto L_0x04b4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:204:0x04a4, code lost:
            r8 = ((java.lang.Integer) com.appsamurai.storyly.exoplayer2.extractor.extractor.mkv.MatroskaExtractor.access$600().get(r0.name)).intValue();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:206:0x04b6, code lost:
            if (r0.projectionType != 0) goto L_0x0504;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:208:0x04bf, code lost:
            if (java.lang.Float.compare(r0.projectionPoseYaw, 0.0f) != 0) goto L_0x0504;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:210:0x04c7, code lost:
            if (java.lang.Float.compare(r0.projectionPosePitch, 0.0f) != 0) goto L_0x0504;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:212:0x04cf, code lost:
            if (java.lang.Float.compare(r0.projectionPoseRoll, 0.0f) != 0) goto L_0x04d3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:213:0x04d1, code lost:
            r8 = 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:215:0x04db, code lost:
            if (java.lang.Float.compare(r0.projectionPosePitch, 90.0f) != 0) goto L_0x04e0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:216:0x04dd, code lost:
            r8 = 90;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:218:0x04e8, code lost:
            if (java.lang.Float.compare(r0.projectionPosePitch, -180.0f) == 0) goto L_0x0502;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:220:0x04f2, code lost:
            if (java.lang.Float.compare(r0.projectionPosePitch, 180.0f) != 0) goto L_0x04f5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:222:0x04fd, code lost:
            if (java.lang.Float.compare(r0.projectionPosePitch, -90.0f) != 0) goto L_0x0504;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:223:0x04ff, code lost:
            r8 = 270;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:224:0x0502, code lost:
            r8 = 180;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:225:0x0504, code lost:
            r14.setWidth(r0.width).setHeight(r0.height).setPixelWidthHeightRatio(r7).setRotationDegrees(r8).setProjectionData(r0.projectionData).setStereoMode(r0.stereoMode).setColorInfo(r3);
            r3 = 2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:227:0x052d, code lost:
            if (com.appsamurai.storyly.exoplayer2.common.util.MimeTypes.APPLICATION_SUBRIP.equals(r4) != false) goto L_0x0555;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:229:0x0533, code lost:
            if (com.appsamurai.storyly.exoplayer2.common.util.MimeTypes.TEXT_SSA.equals(r4) != false) goto L_0x0555;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:231:0x0539, code lost:
            if (com.appsamurai.storyly.exoplayer2.common.util.MimeTypes.TEXT_VTT.equals(r4) != false) goto L_0x0555;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:233:0x053f, code lost:
            if (com.appsamurai.storyly.exoplayer2.common.util.MimeTypes.APPLICATION_VOBSUB.equals(r4) != false) goto L_0x0555;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:235:0x0545, code lost:
            if (com.appsamurai.storyly.exoplayer2.common.util.MimeTypes.APPLICATION_PGS.equals(r4) != false) goto L_0x0555;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:237:0x054b, code lost:
            if (com.appsamurai.storyly.exoplayer2.common.util.MimeTypes.APPLICATION_DVBSUBS.equals(r4) == false) goto L_0x054e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:239:0x0554, code lost:
            throw com.appsamurai.storyly.exoplayer2.common.ParserException.createForMalformedContainer("Unexpected MIME type.", (java.lang.Throwable) null);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:240:0x0555, code lost:
            r3 = 3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:242:0x0558, code lost:
            if (r0.name == null) goto L_0x056b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:244:0x0564, code lost:
            if (com.appsamurai.storyly.exoplayer2.extractor.extractor.mkv.MatroskaExtractor.access$600().containsKey(r0.name) != false) goto L_0x056b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:245:0x0566, code lost:
            r14.setLabel(r0.name);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:246:0x056b, code lost:
            r1 = r14.setId(r22).setSampleMimeType(r4).setMaxInputSize(r6).setLanguage(r0.language).setSelectionFlags(r5).setInitializationData(r1).setCodecs(r2).setDrmInitData(r0.drmInitData).build();
            r2 = r21.track(r0.number, r3);
            r0.output = r2;
            r2.format(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:247:0x05a2, code lost:
            return;
         */
        /* JADX WARNING: Multi-variable type inference failed */
        @org.checkerframework.checker.nullness.qual.RequiresNonNull({"codecId"})
        @org.checkerframework.checker.nullness.qual.EnsuresNonNull({"this.output"})
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void initializeOutput(com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorOutput r21, int r22) throws com.appsamurai.storyly.exoplayer2.common.ParserException {
            /*
                r20 = this;
                r0 = r20
                r1 = 32
                r2 = 16
                java.lang.String r5 = r0.codecId
                r5.getClass()
                java.lang.String r7 = "application/dvbsubs"
                java.lang.String r8 = "application/vobsub"
                java.lang.String r9 = "application/pgs"
                java.lang.String r10 = "text/x-ssa"
                java.lang.String r11 = "text/vtt"
                java.lang.String r12 = "application/x-subrip"
                r14 = 8
                r15 = 3
                java.lang.String r4 = ". Setting mimeType to audio/x-unknown"
                java.lang.String r16 = "audio/raw"
                java.lang.String r6 = "MatroskaExtractor"
                java.lang.String r17 = "audio/x-unknown"
                r3 = 0
                int r18 = r5.hashCode()
                switch(r18) {
                    case -2095576542: goto L_0x01d4;
                    case -2095575984: goto L_0x01c8;
                    case -1985379776: goto L_0x01bc;
                    case -1784763192: goto L_0x01b0;
                    case -1730367663: goto L_0x01a4;
                    case -1482641358: goto L_0x0198;
                    case -1482641357: goto L_0x018c;
                    case -1373388978: goto L_0x0180;
                    case -933872740: goto L_0x0173;
                    case -538363189: goto L_0x0165;
                    case -538363109: goto L_0x0157;
                    case -425012669: goto L_0x0149;
                    case -356037306: goto L_0x013b;
                    case 62923557: goto L_0x012d;
                    case 62923603: goto L_0x011f;
                    case 62927045: goto L_0x0111;
                    case 82318131: goto L_0x0104;
                    case 82338133: goto L_0x00f6;
                    case 82338134: goto L_0x00e8;
                    case 99146302: goto L_0x00da;
                    case 444813526: goto L_0x00cc;
                    case 542569478: goto L_0x00be;
                    case 635596514: goto L_0x00b0;
                    case 725948237: goto L_0x00a3;
                    case 725957860: goto L_0x0096;
                    case 738597099: goto L_0x0089;
                    case 855502857: goto L_0x007c;
                    case 1045209816: goto L_0x006f;
                    case 1422270023: goto L_0x0062;
                    case 1809237540: goto L_0x0055;
                    case 1950749482: goto L_0x0048;
                    case 1950789798: goto L_0x003b;
                    case 1951062397: goto L_0x002f;
                    default: goto L_0x002c;
                }
            L_0x002c:
                r5 = -1
                goto L_0x01df
            L_0x002f:
                java.lang.String r13 = "A_OPUS"
                boolean r5 = r5.equals(r13)
                if (r5 != 0) goto L_0x0038
                goto L_0x002c
            L_0x0038:
                r5 = r1
                goto L_0x01df
            L_0x003b:
                java.lang.String r13 = "A_FLAC"
                boolean r5 = r5.equals(r13)
                if (r5 != 0) goto L_0x0044
                goto L_0x002c
            L_0x0044:
                r5 = 31
                goto L_0x01df
            L_0x0048:
                java.lang.String r13 = "A_EAC3"
                boolean r5 = r5.equals(r13)
                if (r5 != 0) goto L_0x0051
                goto L_0x002c
            L_0x0051:
                r5 = 30
                goto L_0x01df
            L_0x0055:
                java.lang.String r13 = "V_MPEG2"
                boolean r5 = r5.equals(r13)
                if (r5 != 0) goto L_0x005e
                goto L_0x002c
            L_0x005e:
                r5 = 29
                goto L_0x01df
            L_0x0062:
                java.lang.String r13 = "S_TEXT/UTF8"
                boolean r5 = r5.equals(r13)
                if (r5 != 0) goto L_0x006b
                goto L_0x002c
            L_0x006b:
                r5 = 28
                goto L_0x01df
            L_0x006f:
                java.lang.String r13 = "S_TEXT/WEBVTT"
                boolean r5 = r5.equals(r13)
                if (r5 != 0) goto L_0x0078
                goto L_0x002c
            L_0x0078:
                r5 = 27
                goto L_0x01df
            L_0x007c:
                java.lang.String r13 = "V_MPEGH/ISO/HEVC"
                boolean r5 = r5.equals(r13)
                if (r5 != 0) goto L_0x0085
                goto L_0x002c
            L_0x0085:
                r5 = 26
                goto L_0x01df
            L_0x0089:
                java.lang.String r13 = "S_TEXT/ASS"
                boolean r5 = r5.equals(r13)
                if (r5 != 0) goto L_0x0092
                goto L_0x002c
            L_0x0092:
                r5 = 25
                goto L_0x01df
            L_0x0096:
                java.lang.String r13 = "A_PCM/INT/LIT"
                boolean r5 = r5.equals(r13)
                if (r5 != 0) goto L_0x009f
                goto L_0x002c
            L_0x009f:
                r5 = 24
                goto L_0x01df
            L_0x00a3:
                java.lang.String r13 = "A_PCM/INT/BIG"
                boolean r5 = r5.equals(r13)
                if (r5 != 0) goto L_0x00ac
                goto L_0x002c
            L_0x00ac:
                r5 = 23
                goto L_0x01df
            L_0x00b0:
                java.lang.String r13 = "A_PCM/FLOAT/IEEE"
                boolean r5 = r5.equals(r13)
                if (r5 != 0) goto L_0x00ba
                goto L_0x002c
            L_0x00ba:
                r5 = 22
                goto L_0x01df
            L_0x00be:
                java.lang.String r13 = "A_DTS/EXPRESS"
                boolean r5 = r5.equals(r13)
                if (r5 != 0) goto L_0x00c8
                goto L_0x002c
            L_0x00c8:
                r5 = 21
                goto L_0x01df
            L_0x00cc:
                java.lang.String r13 = "V_THEORA"
                boolean r5 = r5.equals(r13)
                if (r5 != 0) goto L_0x00d6
                goto L_0x002c
            L_0x00d6:
                r5 = 20
                goto L_0x01df
            L_0x00da:
                java.lang.String r13 = "S_HDMV/PGS"
                boolean r5 = r5.equals(r13)
                if (r5 != 0) goto L_0x00e4
                goto L_0x002c
            L_0x00e4:
                r5 = 19
                goto L_0x01df
            L_0x00e8:
                java.lang.String r13 = "V_VP9"
                boolean r5 = r5.equals(r13)
                if (r5 != 0) goto L_0x00f2
                goto L_0x002c
            L_0x00f2:
                r5 = 18
                goto L_0x01df
            L_0x00f6:
                java.lang.String r13 = "V_VP8"
                boolean r5 = r5.equals(r13)
                if (r5 != 0) goto L_0x0100
                goto L_0x002c
            L_0x0100:
                r5 = 17
                goto L_0x01df
            L_0x0104:
                java.lang.String r13 = "V_AV1"
                boolean r5 = r5.equals(r13)
                if (r5 != 0) goto L_0x010e
                goto L_0x002c
            L_0x010e:
                r5 = r2
                goto L_0x01df
            L_0x0111:
                java.lang.String r13 = "A_DTS"
                boolean r5 = r5.equals(r13)
                if (r5 != 0) goto L_0x011b
                goto L_0x002c
            L_0x011b:
                r5 = 15
                goto L_0x01df
            L_0x011f:
                java.lang.String r13 = "A_AC3"
                boolean r5 = r5.equals(r13)
                if (r5 != 0) goto L_0x0129
                goto L_0x002c
            L_0x0129:
                r5 = 14
                goto L_0x01df
            L_0x012d:
                java.lang.String r13 = "A_AAC"
                boolean r5 = r5.equals(r13)
                if (r5 != 0) goto L_0x0137
                goto L_0x002c
            L_0x0137:
                r5 = 13
                goto L_0x01df
            L_0x013b:
                java.lang.String r13 = "A_DTS/LOSSLESS"
                boolean r5 = r5.equals(r13)
                if (r5 != 0) goto L_0x0145
                goto L_0x002c
            L_0x0145:
                r5 = 12
                goto L_0x01df
            L_0x0149:
                java.lang.String r13 = "S_VOBSUB"
                boolean r5 = r5.equals(r13)
                if (r5 != 0) goto L_0x0153
                goto L_0x002c
            L_0x0153:
                r5 = 11
                goto L_0x01df
            L_0x0157:
                java.lang.String r13 = "V_MPEG4/ISO/AVC"
                boolean r5 = r5.equals(r13)
                if (r5 != 0) goto L_0x0161
                goto L_0x002c
            L_0x0161:
                r5 = 10
                goto L_0x01df
            L_0x0165:
                java.lang.String r13 = "V_MPEG4/ISO/ASP"
                boolean r5 = r5.equals(r13)
                if (r5 != 0) goto L_0x016f
                goto L_0x002c
            L_0x016f:
                r5 = 9
                goto L_0x01df
            L_0x0173:
                java.lang.String r13 = "S_DVBSUB"
                boolean r5 = r5.equals(r13)
                if (r5 != 0) goto L_0x017d
                goto L_0x002c
            L_0x017d:
                r5 = r14
                goto L_0x01df
            L_0x0180:
                java.lang.String r13 = "V_MS/VFW/FOURCC"
                boolean r5 = r5.equals(r13)
                if (r5 != 0) goto L_0x018a
                goto L_0x002c
            L_0x018a:
                r5 = 7
                goto L_0x01df
            L_0x018c:
                java.lang.String r13 = "A_MPEG/L3"
                boolean r5 = r5.equals(r13)
                if (r5 != 0) goto L_0x0196
                goto L_0x002c
            L_0x0196:
                r5 = 6
                goto L_0x01df
            L_0x0198:
                java.lang.String r13 = "A_MPEG/L2"
                boolean r5 = r5.equals(r13)
                if (r5 != 0) goto L_0x01a2
                goto L_0x002c
            L_0x01a2:
                r5 = 5
                goto L_0x01df
            L_0x01a4:
                java.lang.String r13 = "A_VORBIS"
                boolean r5 = r5.equals(r13)
                if (r5 != 0) goto L_0x01ae
                goto L_0x002c
            L_0x01ae:
                r5 = 4
                goto L_0x01df
            L_0x01b0:
                java.lang.String r13 = "A_TRUEHD"
                boolean r5 = r5.equals(r13)
                if (r5 != 0) goto L_0x01ba
                goto L_0x002c
            L_0x01ba:
                r5 = r15
                goto L_0x01df
            L_0x01bc:
                java.lang.String r13 = "A_MS/ACM"
                boolean r5 = r5.equals(r13)
                if (r5 != 0) goto L_0x01c6
                goto L_0x002c
            L_0x01c6:
                r5 = 2
                goto L_0x01df
            L_0x01c8:
                java.lang.String r13 = "V_MPEG4/ISO/SP"
                boolean r5 = r5.equals(r13)
                if (r5 != 0) goto L_0x01d2
                goto L_0x002c
            L_0x01d2:
                r5 = 1
                goto L_0x01df
            L_0x01d4:
                java.lang.String r13 = "V_MPEG4/ISO/AP"
                boolean r5 = r5.equals(r13)
                if (r5 != 0) goto L_0x01de
                goto L_0x002c
            L_0x01de:
                r5 = 0
            L_0x01df:
                switch(r5) {
                    case 0: goto L_0x0403;
                    case 1: goto L_0x0403;
                    case 2: goto L_0x03c3;
                    case 3: goto L_0x03b5;
                    case 4: goto L_0x03a3;
                    case 5: goto L_0x039f;
                    case 6: goto L_0x0396;
                    case 7: goto L_0x0379;
                    case 8: goto L_0x0363;
                    case 9: goto L_0x0403;
                    case 10: goto L_0x0347;
                    case 11: goto L_0x0338;
                    case 12: goto L_0x0334;
                    case 13: goto L_0x0316;
                    case 14: goto L_0x0312;
                    case 15: goto L_0x030e;
                    case 16: goto L_0x0309;
                    case 17: goto L_0x0304;
                    case 18: goto L_0x02ff;
                    case 19: goto L_0x02f9;
                    case 20: goto L_0x02f4;
                    case 21: goto L_0x030e;
                    case 22: goto L_0x02d2;
                    case 23: goto L_0x02ae;
                    case 24: goto L_0x0285;
                    case 25: goto L_0x0273;
                    case 26: goto L_0x024f;
                    case 27: goto L_0x024a;
                    case 28: goto L_0x0245;
                    case 29: goto L_0x0241;
                    case 30: goto L_0x023c;
                    case 31: goto L_0x022c;
                    case 32: goto L_0x01e9;
                    default: goto L_0x01e2;
                }
            L_0x01e2:
                java.lang.String r0 = "Unrecognized codec identifier."
                com.appsamurai.storyly.exoplayer2.common.ParserException r0 = com.appsamurai.storyly.exoplayer2.common.ParserException.createForMalformedContainer(r0, r3)
                throw r0
            L_0x01e9:
                java.util.ArrayList r1 = new java.util.ArrayList
                r1.<init>(r15)
                java.lang.String r2 = r0.codecId
                byte[] r2 = r0.getCodecPrivate(r2)
                r1.add(r2)
                java.nio.ByteBuffer r2 = java.nio.ByteBuffer.allocate(r14)
                java.nio.ByteOrder r4 = java.nio.ByteOrder.LITTLE_ENDIAN
                java.nio.ByteBuffer r2 = r2.order(r4)
                long r5 = r0.codecDelayNs
                java.nio.ByteBuffer r2 = r2.putLong(r5)
                byte[] r2 = r2.array()
                r1.add(r2)
                java.nio.ByteBuffer r2 = java.nio.ByteBuffer.allocate(r14)
                java.nio.ByteBuffer r2 = r2.order(r4)
                long r4 = r0.seekPreRollNs
                java.nio.ByteBuffer r2 = r2.putLong(r4)
                byte[] r2 = r2.array()
                r1.add(r2)
                java.lang.String r16 = "audio/opus"
                r6 = 5760(0x1680, float:8.071E-42)
                r2 = r3
                r5 = 0
            L_0x0229:
                r13 = -1
                goto L_0x0412
            L_0x022c:
                java.lang.String r1 = r0.codecId
                byte[] r1 = r0.getCodecPrivate(r1)
                java.util.List r1 = java.util.Collections.singletonList(r1)
                java.lang.String r16 = "audio/flac"
                r2 = r3
            L_0x0239:
                r5 = 0
            L_0x023a:
                r6 = -1
                goto L_0x0229
            L_0x023c:
                java.lang.String r16 = "audio/eac3"
            L_0x023e:
                r1 = r3
                r2 = r1
                goto L_0x0239
            L_0x0241:
                java.lang.String r16 = "video/mpeg2"
                goto L_0x023e
            L_0x0245:
                r1 = r3
                r2 = r1
                r16 = r12
                goto L_0x0239
            L_0x024a:
                r1 = r3
                r2 = r1
                r16 = r11
                goto L_0x0239
            L_0x024f:
                com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r1 = new com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray
                java.lang.String r2 = r0.codecId
                byte[] r2 = r0.getCodecPrivate(r2)
                r1.<init>((byte[]) r2)
                com.appsamurai.storyly.exoplayer2.extractor.video.HevcConfig r1 = com.appsamurai.storyly.exoplayer2.extractor.video.HevcConfig.parse(r1)
                java.util.List<byte[]> r2 = r1.initializationData
                int r4 = r1.nalUnitLengthFieldLength
                r0.nalUnitLengthFieldLength = r4
                java.lang.String r1 = r1.codecs
                java.lang.String r16 = "video/hevc"
            L_0x0269:
                r5 = 0
                r6 = -1
                r13 = -1
                r19 = r2
                r2 = r1
                r1 = r19
                goto L_0x0412
            L_0x0273:
                byte[] r1 = com.appsamurai.storyly.exoplayer2.extractor.extractor.mkv.MatroskaExtractor.SSA_DIALOGUE_FORMAT
                java.lang.String r2 = r0.codecId
                byte[] r2 = r0.getCodecPrivate(r2)
                com.google.common.collect.ImmutableList r1 = com.google.common.collect.ImmutableList.of(r1, r2)
                r2 = r3
                r16 = r10
                goto L_0x0239
            L_0x0285:
                int r1 = r0.audioBitDepth
                int r13 = com.appsamurai.storyly.exoplayer2.common.util.Util.getPcmEncoding(r1)
                if (r13 != 0) goto L_0x02a8
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r2 = "Unsupported little endian PCM bit depth: "
                r1.<init>(r2)
                int r2 = r0.audioBitDepth
                r1.append(r2)
                r1.append(r4)
                java.lang.String r1 = r1.toString()
                com.appsamurai.storyly.exoplayer2.common.util.Log.w(r6, r1)
            L_0x02a3:
                r1 = r3
                r2 = r1
                r16 = r17
                goto L_0x0239
            L_0x02a8:
                r1 = r3
                r2 = r1
            L_0x02aa:
                r5 = 0
            L_0x02ab:
                r6 = -1
                goto L_0x0412
            L_0x02ae:
                int r1 = r0.audioBitDepth
                if (r1 != r14) goto L_0x02b6
                r1 = r3
                r2 = r1
                r13 = r15
                goto L_0x02aa
            L_0x02b6:
                if (r1 != r2) goto L_0x02bb
                r13 = 268435456(0x10000000, float:2.5243549E-29)
                goto L_0x02a8
            L_0x02bb:
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r2 = "Unsupported big endian PCM bit depth: "
                r1.<init>(r2)
                int r2 = r0.audioBitDepth
                r1.append(r2)
                r1.append(r4)
                java.lang.String r1 = r1.toString()
                com.appsamurai.storyly.exoplayer2.common.util.Log.w(r6, r1)
                goto L_0x02a3
            L_0x02d2:
                int r2 = r0.audioBitDepth
                if (r2 != r1) goto L_0x02dd
                r1 = r3
                r2 = r1
                r5 = 0
                r6 = -1
                r13 = 4
                goto L_0x0412
            L_0x02dd:
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r2 = "Unsupported floating point PCM bit depth: "
                r1.<init>(r2)
                int r2 = r0.audioBitDepth
                r1.append(r2)
                r1.append(r4)
                java.lang.String r1 = r1.toString()
                com.appsamurai.storyly.exoplayer2.common.util.Log.w(r6, r1)
                goto L_0x02a3
            L_0x02f4:
                java.lang.String r16 = "video/x-unknown"
                goto L_0x023e
            L_0x02f9:
                r1 = r3
                r2 = r1
                r16 = r9
                goto L_0x0239
            L_0x02ff:
                java.lang.String r16 = "video/x-vnd.on2.vp9"
                goto L_0x023e
            L_0x0304:
                java.lang.String r16 = "video/x-vnd.on2.vp8"
                goto L_0x023e
            L_0x0309:
                java.lang.String r16 = "video/av01"
                goto L_0x023e
            L_0x030e:
                java.lang.String r16 = "audio/vnd.dts"
                goto L_0x023e
            L_0x0312:
                java.lang.String r16 = "audio/ac3"
                goto L_0x023e
            L_0x0316:
                java.lang.String r1 = r0.codecId
                byte[] r1 = r0.getCodecPrivate(r1)
                java.util.List r1 = java.util.Collections.singletonList(r1)
                byte[] r2 = r0.codecPrivate
                com.appsamurai.storyly.exoplayer2.extractor.audio.AacUtil$Config r2 = com.appsamurai.storyly.exoplayer2.extractor.audio.AacUtil.parseAudioSpecificConfig(r2)
                int r4 = r2.sampleRateHz
                r0.sampleRate = r4
                int r4 = r2.channelCount
                r0.channelCount = r4
                java.lang.String r2 = r2.codecs
                java.lang.String r16 = "audio/mp4a-latm"
                goto L_0x0239
            L_0x0334:
                java.lang.String r16 = "audio/vnd.dts.hd"
                goto L_0x023e
            L_0x0338:
                java.lang.String r1 = r0.codecId
                byte[] r1 = r0.getCodecPrivate(r1)
                com.google.common.collect.ImmutableList r1 = com.google.common.collect.ImmutableList.of(r1)
                r2 = r3
                r16 = r8
                goto L_0x0239
            L_0x0347:
                com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r1 = new com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray
                java.lang.String r2 = r0.codecId
                byte[] r2 = r0.getCodecPrivate(r2)
                r1.<init>((byte[]) r2)
                com.appsamurai.storyly.exoplayer2.extractor.video.AvcConfig r1 = com.appsamurai.storyly.exoplayer2.extractor.video.AvcConfig.parse(r1)
                java.util.List<byte[]> r2 = r1.initializationData
                int r4 = r1.nalUnitLengthFieldLength
                r0.nalUnitLengthFieldLength = r4
                java.lang.String r1 = r1.codecs
                java.lang.String r16 = "video/avc"
                goto L_0x0269
            L_0x0363:
                r1 = 4
                byte[] r2 = new byte[r1]
                java.lang.String r4 = r0.codecId
                byte[] r4 = r0.getCodecPrivate(r4)
                r5 = 0
                java.lang.System.arraycopy(r4, r5, r2, r5, r1)
                com.google.common.collect.ImmutableList r1 = com.google.common.collect.ImmutableList.of(r2)
                r2 = r3
                r16 = r7
                goto L_0x023a
            L_0x0379:
                r5 = 0
                com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r1 = new com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray
                java.lang.String r2 = r0.codecId
                byte[] r2 = r0.getCodecPrivate(r2)
                r1.<init>((byte[]) r2)
                android.util.Pair r1 = parseFourCcPrivate(r1)
                java.lang.Object r2 = r1.first
                r16 = r2
                java.lang.String r16 = (java.lang.String) r16
                java.lang.Object r1 = r1.second
                java.util.List r1 = (java.util.List) r1
            L_0x0393:
                r2 = r3
                goto L_0x023a
            L_0x0396:
                r5 = 0
                java.lang.String r16 = "audio/mpeg"
            L_0x0399:
                r1 = r3
                r2 = r1
                r6 = 4096(0x1000, float:5.74E-42)
                goto L_0x0229
            L_0x039f:
                r5 = 0
                java.lang.String r16 = "audio/mpeg-L2"
                goto L_0x0399
            L_0x03a3:
                r5 = 0
                java.lang.String r1 = r0.codecId
                byte[] r1 = r0.getCodecPrivate(r1)
                java.util.List r1 = parseVorbisCodecPrivate(r1)
                java.lang.String r16 = "audio/vorbis"
                r6 = 8192(0x2000, float:1.14794E-41)
                r2 = r3
                goto L_0x0229
            L_0x03b5:
                r5 = 0
                com.appsamurai.storyly.exoplayer2.extractor.extractor.TrueHdSampleRechunker r1 = new com.appsamurai.storyly.exoplayer2.extractor.extractor.TrueHdSampleRechunker
                r1.<init>()
                r0.trueHdSampleRechunker = r1
                java.lang.String r16 = "audio/true-hd"
                r1 = r3
                r2 = r1
                goto L_0x023a
            L_0x03c3:
                r5 = 0
                com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r1 = new com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray
                java.lang.String r2 = r0.codecId
                byte[] r2 = r0.getCodecPrivate(r2)
                r1.<init>((byte[]) r2)
                boolean r1 = parseMsAcmCodecPrivate(r1)
                if (r1 == 0) goto L_0x03fd
                int r1 = r0.audioBitDepth
                int r13 = com.appsamurai.storyly.exoplayer2.common.util.Util.getPcmEncoding(r1)
                if (r13 != 0) goto L_0x03f9
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r2 = "Unsupported PCM bit depth: "
                r1.<init>(r2)
                int r2 = r0.audioBitDepth
                r1.append(r2)
                r1.append(r4)
                java.lang.String r1 = r1.toString()
                com.appsamurai.storyly.exoplayer2.common.util.Log.w(r6, r1)
            L_0x03f3:
                r1 = r3
                r2 = r1
                r16 = r17
                goto L_0x023a
            L_0x03f9:
                r1 = r3
                r2 = r1
                goto L_0x02ab
            L_0x03fd:
                java.lang.String r1 = "Non-PCM MS/ACM is unsupported. Setting mimeType to audio/x-unknown"
                com.appsamurai.storyly.exoplayer2.common.util.Log.w(r6, r1)
                goto L_0x03f3
            L_0x0403:
                r5 = 0
                byte[] r1 = r0.codecPrivate
                if (r1 != 0) goto L_0x040a
                r1 = r3
                goto L_0x040e
            L_0x040a:
                java.util.List r1 = java.util.Collections.singletonList(r1)
            L_0x040e:
                java.lang.String r16 = "video/mp4v-es"
                goto L_0x0393
            L_0x0412:
                byte[] r4 = r0.dolbyVisionConfigBytes
                if (r4 == 0) goto L_0x0426
                com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r14 = new com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray
                r14.<init>((byte[]) r4)
                com.appsamurai.storyly.exoplayer2.extractor.video.DolbyVisionConfig r4 = com.appsamurai.storyly.exoplayer2.extractor.video.DolbyVisionConfig.parse(r14)
                if (r4 == 0) goto L_0x0426
                java.lang.String r2 = r4.codecs
                java.lang.String r16 = "video/dolby-vision"
            L_0x0426:
                r4 = r16
                boolean r14 = r0.flagDefault
                boolean r5 = r0.flagForced
                if (r5 == 0) goto L_0x0430
                r5 = 2
                goto L_0x0431
            L_0x0430:
                r5 = 0
            L_0x0431:
                r5 = r5 | r14
                com.appsamurai.storyly.exoplayer2.common.Format$Builder r14 = new com.appsamurai.storyly.exoplayer2.common.Format$Builder
                r14.<init>()
                boolean r16 = com.appsamurai.storyly.exoplayer2.common.util.MimeTypes.isAudio(r4)
                if (r16 == 0) goto L_0x044f
                int r3 = r0.channelCount
                com.appsamurai.storyly.exoplayer2.common.Format$Builder r3 = r14.setChannelCount(r3)
                int r7 = r0.sampleRate
                com.appsamurai.storyly.exoplayer2.common.Format$Builder r3 = r3.setSampleRate(r7)
                r3.setPcmEncoding(r13)
                r3 = 1
                goto L_0x0556
            L_0x044f:
                boolean r13 = com.appsamurai.storyly.exoplayer2.common.util.MimeTypes.isVideo(r4)
                if (r13 == 0) goto L_0x0529
                int r7 = r0.displayUnit
                if (r7 != 0) goto L_0x046b
                int r7 = r0.displayWidth
                r8 = -1
                if (r7 != r8) goto L_0x0460
                int r7 = r0.width
            L_0x0460:
                r0.displayWidth = r7
                int r7 = r0.displayHeight
                if (r7 != r8) goto L_0x0468
                int r7 = r0.height
            L_0x0468:
                r0.displayHeight = r7
                goto L_0x046c
            L_0x046b:
                r8 = -1
            L_0x046c:
                int r7 = r0.displayWidth
                if (r7 == r8) goto L_0x047e
                int r9 = r0.displayHeight
                if (r9 == r8) goto L_0x047e
                int r10 = r0.height
                int r10 = r10 * r7
                float r7 = (float) r10
                int r10 = r0.width
                int r10 = r10 * r9
                float r9 = (float) r10
                float r7 = r7 / r9
                goto L_0x0480
            L_0x047e:
                r7 = -1082130432(0xffffffffbf800000, float:-1.0)
            L_0x0480:
                boolean r9 = r0.hasColorInfo
                if (r9 == 0) goto L_0x0494
                byte[] r3 = r20.getHdrStaticInfo()
                com.appsamurai.storyly.exoplayer2.common.video.ColorInfo r9 = new com.appsamurai.storyly.exoplayer2.common.video.ColorInfo
                int r10 = r0.colorSpace
                int r11 = r0.colorRange
                int r12 = r0.colorTransfer
                r9.<init>(r10, r11, r12, r3)
                r3 = r9
            L_0x0494:
                java.lang.String r9 = r0.name
                if (r9 == 0) goto L_0x04b4
                java.util.Map r9 = com.appsamurai.storyly.exoplayer2.extractor.extractor.mkv.MatroskaExtractor.TRACK_NAME_TO_ROTATION_DEGREES
                java.lang.String r10 = r0.name
                boolean r9 = r9.containsKey(r10)
                if (r9 == 0) goto L_0x04b4
                java.util.Map r8 = com.appsamurai.storyly.exoplayer2.extractor.extractor.mkv.MatroskaExtractor.TRACK_NAME_TO_ROTATION_DEGREES
                java.lang.String r9 = r0.name
                java.lang.Object r8 = r8.get(r9)
                java.lang.Integer r8 = (java.lang.Integer) r8
                int r8 = r8.intValue()
            L_0x04b4:
                int r9 = r0.projectionType
                if (r9 != 0) goto L_0x0504
                float r9 = r0.projectionPoseYaw
                r10 = 0
                int r9 = java.lang.Float.compare(r9, r10)
                if (r9 != 0) goto L_0x0504
                float r9 = r0.projectionPosePitch
                int r9 = java.lang.Float.compare(r9, r10)
                if (r9 != 0) goto L_0x0504
                float r9 = r0.projectionPoseRoll
                int r9 = java.lang.Float.compare(r9, r10)
                if (r9 != 0) goto L_0x04d3
                r8 = 0
                goto L_0x0504
            L_0x04d3:
                float r9 = r0.projectionPosePitch
                r10 = 1119092736(0x42b40000, float:90.0)
                int r9 = java.lang.Float.compare(r9, r10)
                if (r9 != 0) goto L_0x04e0
                r8 = 90
                goto L_0x0504
            L_0x04e0:
                float r9 = r0.projectionPosePitch
                r10 = -1020002304(0xffffffffc3340000, float:-180.0)
                int r9 = java.lang.Float.compare(r9, r10)
                if (r9 == 0) goto L_0x0502
                float r9 = r0.projectionPosePitch
                r10 = 1127481344(0x43340000, float:180.0)
                int r9 = java.lang.Float.compare(r9, r10)
                if (r9 != 0) goto L_0x04f5
                goto L_0x0502
            L_0x04f5:
                float r9 = r0.projectionPosePitch
                r10 = -1028390912(0xffffffffc2b40000, float:-90.0)
                int r9 = java.lang.Float.compare(r9, r10)
                if (r9 != 0) goto L_0x0504
                r8 = 270(0x10e, float:3.78E-43)
                goto L_0x0504
            L_0x0502:
                r8 = 180(0xb4, float:2.52E-43)
            L_0x0504:
                int r9 = r0.width
                com.appsamurai.storyly.exoplayer2.common.Format$Builder r9 = r14.setWidth(r9)
                int r10 = r0.height
                com.appsamurai.storyly.exoplayer2.common.Format$Builder r9 = r9.setHeight(r10)
                com.appsamurai.storyly.exoplayer2.common.Format$Builder r7 = r9.setPixelWidthHeightRatio(r7)
                com.appsamurai.storyly.exoplayer2.common.Format$Builder r7 = r7.setRotationDegrees(r8)
                byte[] r8 = r0.projectionData
                com.appsamurai.storyly.exoplayer2.common.Format$Builder r7 = r7.setProjectionData(r8)
                int r8 = r0.stereoMode
                com.appsamurai.storyly.exoplayer2.common.Format$Builder r7 = r7.setStereoMode(r8)
                r7.setColorInfo(r3)
                r3 = 2
                goto L_0x0556
            L_0x0529:
                boolean r12 = r12.equals(r4)
                if (r12 != 0) goto L_0x0555
                boolean r10 = r10.equals(r4)
                if (r10 != 0) goto L_0x0555
                boolean r10 = r11.equals(r4)
                if (r10 != 0) goto L_0x0555
                boolean r8 = r8.equals(r4)
                if (r8 != 0) goto L_0x0555
                boolean r8 = r9.equals(r4)
                if (r8 != 0) goto L_0x0555
                boolean r7 = r7.equals(r4)
                if (r7 == 0) goto L_0x054e
                goto L_0x0555
            L_0x054e:
                java.lang.String r0 = "Unexpected MIME type."
                com.appsamurai.storyly.exoplayer2.common.ParserException r0 = com.appsamurai.storyly.exoplayer2.common.ParserException.createForMalformedContainer(r0, r3)
                throw r0
            L_0x0555:
                r3 = r15
            L_0x0556:
                java.lang.String r7 = r0.name
                if (r7 == 0) goto L_0x056b
                java.util.Map r7 = com.appsamurai.storyly.exoplayer2.extractor.extractor.mkv.MatroskaExtractor.TRACK_NAME_TO_ROTATION_DEGREES
                java.lang.String r8 = r0.name
                boolean r7 = r7.containsKey(r8)
                if (r7 != 0) goto L_0x056b
                java.lang.String r7 = r0.name
                r14.setLabel(r7)
            L_0x056b:
                r7 = r22
                com.appsamurai.storyly.exoplayer2.common.Format$Builder r7 = r14.setId((int) r7)
                com.appsamurai.storyly.exoplayer2.common.Format$Builder r4 = r7.setSampleMimeType(r4)
                com.appsamurai.storyly.exoplayer2.common.Format$Builder r4 = r4.setMaxInputSize(r6)
                java.lang.String r6 = r0.language
                com.appsamurai.storyly.exoplayer2.common.Format$Builder r4 = r4.setLanguage(r6)
                com.appsamurai.storyly.exoplayer2.common.Format$Builder r4 = r4.setSelectionFlags(r5)
                com.appsamurai.storyly.exoplayer2.common.Format$Builder r1 = r4.setInitializationData(r1)
                com.appsamurai.storyly.exoplayer2.common.Format$Builder r1 = r1.setCodecs(r2)
                com.appsamurai.storyly.exoplayer2.common.drm.DrmInitData r2 = r0.drmInitData
                com.appsamurai.storyly.exoplayer2.common.Format$Builder r1 = r1.setDrmInitData(r2)
                com.appsamurai.storyly.exoplayer2.common.Format r1 = r1.build()
                int r2 = r0.number
                r4 = r21
                com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput r2 = r4.track(r2, r3)
                r0.output = r2
                r2.format(r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.extractor.extractor.mkv.MatroskaExtractor.Track.initializeOutput(com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorOutput, int):void");
        }

        @RequiresNonNull({"output"})
        public void outputPendingSampleMetadata() {
            TrueHdSampleRechunker trueHdSampleRechunker2 = this.trueHdSampleRechunker;
            if (trueHdSampleRechunker2 != null) {
                trueHdSampleRechunker2.outputPendingSampleMetadata(this.output, this.cryptoData);
            }
        }

        public void reset() {
            TrueHdSampleRechunker trueHdSampleRechunker2 = this.trueHdSampleRechunker;
            if (trueHdSampleRechunker2 != null) {
                trueHdSampleRechunker2.reset();
            }
        }
    }

    static {
        HashMap hashMap = new HashMap();
        hashMap.put("htc_video_rotA-000", 0);
        hashMap.put("htc_video_rotA-090", 90);
        hashMap.put("htc_video_rotA-180", 180);
        hashMap.put("htc_video_rotA-270", 270);
        TRACK_NAME_TO_ROTATION_DEGREES = Collections.unmodifiableMap(hashMap);
    }

    public MatroskaExtractor() {
        this(0);
    }

    @EnsuresNonNull({"cueTimesUs", "cueClusterPositions"})
    private void assertInCues(int i3) throws ParserException {
        if (this.cueTimesUs == null || this.cueClusterPositions == null) {
            throw ParserException.createForMalformedContainer("Element " + i3 + " must be in a Cues", (Throwable) null);
        }
    }

    @EnsuresNonNull({"currentTrack"})
    private void assertInTrackEntry(int i3) throws ParserException {
        if (this.currentTrack == null) {
            throw ParserException.createForMalformedContainer("Element " + i3 + " must be in a TrackEntry", (Throwable) null);
        }
    }

    @EnsuresNonNull({"extractorOutput"})
    private void assertInitialized() {
        Assertions.checkStateNotNull(this.extractorOutput);
    }

    private SeekMap buildSeekMap(@Nullable LongArray longArray, @Nullable LongArray longArray2) {
        int i3;
        if (this.segmentContentPosition == -1 || this.durationUs == C.TIME_UNSET || longArray == null || longArray.size() == 0 || longArray2 == null || longArray2.size() != longArray.size()) {
            return new SeekMap.Unseekable(this.durationUs);
        }
        int size = longArray.size();
        int[] iArr = new int[size];
        long[] jArr = new long[size];
        long[] jArr2 = new long[size];
        long[] jArr3 = new long[size];
        int i4 = 0;
        for (int i5 = 0; i5 < size; i5++) {
            jArr3[i5] = longArray.get(i5);
            jArr[i5] = longArray2.get(i5) + this.segmentContentPosition;
        }
        while (true) {
            i3 = size - 1;
            if (i4 >= i3) {
                break;
            }
            int i6 = i4 + 1;
            iArr[i4] = (int) (jArr[i6] - jArr[i4]);
            jArr2[i4] = jArr3[i6] - jArr3[i4];
            i4 = i6;
        }
        iArr[i3] = (int) ((this.segmentContentPosition + this.segmentContentSize) - jArr[i3]);
        long j2 = this.durationUs - jArr3[i3];
        jArr2[i3] = j2;
        if (j2 <= 0) {
            Log.w(TAG, "Discarding last cue point with unexpected duration: " + j2);
            iArr = Arrays.copyOf(iArr, i3);
            jArr = Arrays.copyOf(jArr, i3);
            jArr2 = Arrays.copyOf(jArr2, i3);
            jArr3 = Arrays.copyOf(jArr3, i3);
        }
        return new ChunkIndex(iArr, jArr, jArr2, jArr3);
    }

    @RequiresNonNull({"#1.output"})
    private void commitSampleToOutput(Track track, long j2, int i3, int i4, int i5) {
        TrueHdSampleRechunker trueHdSampleRechunker = track.trueHdSampleRechunker;
        if (trueHdSampleRechunker != null) {
            trueHdSampleRechunker.sampleMetadata(track.output, j2, i3, i4, i5, track.cryptoData);
        } else {
            if (CODEC_ID_SUBRIP.equals(track.codecId) || CODEC_ID_ASS.equals(track.codecId) || CODEC_ID_VTT.equals(track.codecId)) {
                if (this.blockSampleCount > 1) {
                    Log.w(TAG, "Skipping subtitle sample in laced block.");
                } else {
                    long j3 = this.blockDurationUs;
                    if (j3 == C.TIME_UNSET) {
                        Log.w(TAG, "Skipping subtitle sample with no duration.");
                    } else {
                        setSubtitleEndTime(track.codecId, j3, this.subtitleSample.getData());
                        int position = this.subtitleSample.getPosition();
                        while (true) {
                            if (position >= this.subtitleSample.limit()) {
                                break;
                            } else if (this.subtitleSample.getData()[position] == 0) {
                                this.subtitleSample.setLimit(position);
                                break;
                            } else {
                                position++;
                            }
                        }
                        TrackOutput trackOutput = track.output;
                        ParsableByteArray parsableByteArray = this.subtitleSample;
                        trackOutput.sampleData(parsableByteArray, parsableByteArray.limit());
                        i4 += this.subtitleSample.limit();
                    }
                }
            }
            if ((268435456 & i3) != 0) {
                if (this.blockSampleCount > 1) {
                    this.supplementalData.reset(0);
                } else {
                    int limit = this.supplementalData.limit();
                    track.output.sampleData(this.supplementalData, limit, 2);
                    i4 += limit;
                }
            }
            track.output.sampleMetadata(j2, i3, i4, i5, track.cryptoData);
        }
        this.haveOutputSample = true;
    }

    private static int[] ensureArrayCapacity(@Nullable int[] iArr, int i3) {
        return iArr == null ? new int[i3] : iArr.length >= i3 ? iArr : new int[Math.max(iArr.length * 2, i3)];
    }

    private int finishWriteSampleData() {
        int i3 = this.sampleBytesWritten;
        resetWriteSampleData();
        return i3;
    }

    private static byte[] formatSubtitleTimecode(long j2, String str, long j3) {
        Assertions.checkArgument(j2 != C.TIME_UNSET);
        int i3 = (int) (j2 / 3600000000L);
        long j4 = j2 - (((long) i3) * 3600000000L);
        int i4 = (int) (j4 / 60000000);
        long j5 = j4 - (((long) i4) * 60000000);
        int i5 = (int) (j5 / 1000000);
        return Util.getUtf8Bytes(String.format(Locale.US, str, new Object[]{Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf((int) ((j5 - (((long) i5) * 1000000)) / j3))}));
    }

    private static boolean isCodecSupported(String str) {
        str.getClass();
        char c3 = 65535;
        switch (str.hashCode()) {
            case -2095576542:
                if (str.equals(CODEC_ID_MPEG4_AP)) {
                    c3 = 0;
                    break;
                }
                break;
            case -2095575984:
                if (str.equals(CODEC_ID_MPEG4_SP)) {
                    c3 = 1;
                    break;
                }
                break;
            case -1985379776:
                if (str.equals(CODEC_ID_ACM)) {
                    c3 = 2;
                    break;
                }
                break;
            case -1784763192:
                if (str.equals(CODEC_ID_TRUEHD)) {
                    c3 = 3;
                    break;
                }
                break;
            case -1730367663:
                if (str.equals(CODEC_ID_VORBIS)) {
                    c3 = 4;
                    break;
                }
                break;
            case -1482641358:
                if (str.equals(CODEC_ID_MP2)) {
                    c3 = 5;
                    break;
                }
                break;
            case -1482641357:
                if (str.equals(CODEC_ID_MP3)) {
                    c3 = 6;
                    break;
                }
                break;
            case -1373388978:
                if (str.equals(CODEC_ID_FOURCC)) {
                    c3 = 7;
                    break;
                }
                break;
            case -933872740:
                if (str.equals(CODEC_ID_DVBSUB)) {
                    c3 = 8;
                    break;
                }
                break;
            case -538363189:
                if (str.equals(CODEC_ID_MPEG4_ASP)) {
                    c3 = 9;
                    break;
                }
                break;
            case -538363109:
                if (str.equals(CODEC_ID_H264)) {
                    c3 = 10;
                    break;
                }
                break;
            case -425012669:
                if (str.equals(CODEC_ID_VOBSUB)) {
                    c3 = 11;
                    break;
                }
                break;
            case -356037306:
                if (str.equals(CODEC_ID_DTS_LOSSLESS)) {
                    c3 = 12;
                    break;
                }
                break;
            case 62923557:
                if (str.equals(CODEC_ID_AAC)) {
                    c3 = CharUtils.CR;
                    break;
                }
                break;
            case 62923603:
                if (str.equals(CODEC_ID_AC3)) {
                    c3 = 14;
                    break;
                }
                break;
            case 62927045:
                if (str.equals(CODEC_ID_DTS)) {
                    c3 = 15;
                    break;
                }
                break;
            case 82318131:
                if (str.equals(CODEC_ID_AV1)) {
                    c3 = 16;
                    break;
                }
                break;
            case 82338133:
                if (str.equals(CODEC_ID_VP8)) {
                    c3 = 17;
                    break;
                }
                break;
            case 82338134:
                if (str.equals(CODEC_ID_VP9)) {
                    c3 = 18;
                    break;
                }
                break;
            case 99146302:
                if (str.equals(CODEC_ID_PGS)) {
                    c3 = 19;
                    break;
                }
                break;
            case 444813526:
                if (str.equals(CODEC_ID_THEORA)) {
                    c3 = 20;
                    break;
                }
                break;
            case 542569478:
                if (str.equals(CODEC_ID_DTS_EXPRESS)) {
                    c3 = 21;
                    break;
                }
                break;
            case 635596514:
                if (str.equals(CODEC_ID_PCM_FLOAT)) {
                    c3 = 22;
                    break;
                }
                break;
            case 725948237:
                if (str.equals(CODEC_ID_PCM_INT_BIG)) {
                    c3 = 23;
                    break;
                }
                break;
            case 725957860:
                if (str.equals(CODEC_ID_PCM_INT_LIT)) {
                    c3 = 24;
                    break;
                }
                break;
            case 738597099:
                if (str.equals(CODEC_ID_ASS)) {
                    c3 = 25;
                    break;
                }
                break;
            case 855502857:
                if (str.equals(CODEC_ID_H265)) {
                    c3 = 26;
                    break;
                }
                break;
            case 1045209816:
                if (str.equals(CODEC_ID_VTT)) {
                    c3 = 27;
                    break;
                }
                break;
            case 1422270023:
                if (str.equals(CODEC_ID_SUBRIP)) {
                    c3 = 28;
                    break;
                }
                break;
            case 1809237540:
                if (str.equals(CODEC_ID_MPEG2)) {
                    c3 = 29;
                    break;
                }
                break;
            case 1950749482:
                if (str.equals(CODEC_ID_E_AC3)) {
                    c3 = 30;
                    break;
                }
                break;
            case 1950789798:
                if (str.equals(CODEC_ID_FLAC)) {
                    c3 = 31;
                    break;
                }
                break;
            case 1951062397:
                if (str.equals(CODEC_ID_OPUS)) {
                    c3 = ' ';
                    break;
                }
                break;
        }
        switch (c3) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case ' ':
                return true;
            default:
                return false;
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Extractor[] lambda$static$0() {
        return new Extractor[]{new MatroskaExtractor()};
    }

    private boolean maybeSeekForCues(PositionHolder positionHolder, long j2) {
        if (this.seekForCues) {
            this.seekPositionAfterBuildingCues = j2;
            positionHolder.position = this.cuesContentPosition;
            this.seekForCues = false;
            return true;
        }
        if (this.sentSeekMap) {
            long j3 = this.seekPositionAfterBuildingCues;
            if (j3 != -1) {
                positionHolder.position = j3;
                this.seekPositionAfterBuildingCues = -1;
                return true;
            }
        }
        return false;
    }

    private void readScratch(ExtractorInput extractorInput, int i3) throws IOException {
        if (this.scratch.limit() < i3) {
            if (this.scratch.capacity() < i3) {
                ParsableByteArray parsableByteArray = this.scratch;
                parsableByteArray.ensureCapacity(Math.max(parsableByteArray.capacity() * 2, i3));
            }
            extractorInput.readFully(this.scratch.getData(), this.scratch.limit(), i3 - this.scratch.limit());
            this.scratch.setLimit(i3);
        }
    }

    private void resetWriteSampleData() {
        this.sampleBytesRead = 0;
        this.sampleBytesWritten = 0;
        this.sampleCurrentNalBytesRemaining = 0;
        this.sampleEncodingHandled = false;
        this.sampleSignalByteRead = false;
        this.samplePartitionCountRead = false;
        this.samplePartitionCount = 0;
        this.sampleSignalByte = 0;
        this.sampleInitializationVectorRead = false;
        this.sampleStrippedBytes.reset(0);
    }

    private long scaleTimecodeToUs(long j2) throws ParserException {
        long j3 = this.timecodeScale;
        if (j3 != C.TIME_UNSET) {
            return Util.scaleLargeTimestamp(j2, j3, 1000);
        }
        throw ParserException.createForMalformedContainer("Can't scale timecode prior to timecodeScale being set.", (Throwable) null);
    }

    private static void setSubtitleEndTime(String str, long j2, byte[] bArr) {
        int i3;
        byte[] bArr2;
        str.getClass();
        char c3 = 65535;
        switch (str.hashCode()) {
            case 738597099:
                if (str.equals(CODEC_ID_ASS)) {
                    c3 = 0;
                    break;
                }
                break;
            case 1045209816:
                if (str.equals(CODEC_ID_VTT)) {
                    c3 = 1;
                    break;
                }
                break;
            case 1422270023:
                if (str.equals(CODEC_ID_SUBRIP)) {
                    c3 = 2;
                    break;
                }
                break;
        }
        switch (c3) {
            case 0:
                bArr2 = formatSubtitleTimecode(j2, SSA_TIMECODE_FORMAT, 10000);
                i3 = 21;
                break;
            case 1:
                bArr2 = formatSubtitleTimecode(j2, VTT_TIMECODE_FORMAT, 1000);
                i3 = 25;
                break;
            case 2:
                bArr2 = formatSubtitleTimecode(j2, SUBRIP_TIMECODE_FORMAT, 1000);
                i3 = 19;
                break;
            default:
                throw new IllegalArgumentException();
        }
        System.arraycopy(bArr2, 0, bArr, i3, bArr2.length);
    }

    @RequiresNonNull({"#2.output"})
    private int writeSampleData(ExtractorInput extractorInput, Track track, int i3, boolean z2) throws IOException {
        int i4;
        if (CODEC_ID_SUBRIP.equals(track.codecId)) {
            writeSubtitleSampleData(extractorInput, SUBRIP_PREFIX, i3);
            return finishWriteSampleData();
        } else if (CODEC_ID_ASS.equals(track.codecId)) {
            writeSubtitleSampleData(extractorInput, SSA_PREFIX, i3);
            return finishWriteSampleData();
        } else if (CODEC_ID_VTT.equals(track.codecId)) {
            writeSubtitleSampleData(extractorInput, VTT_PREFIX, i3);
            return finishWriteSampleData();
        } else {
            TrackOutput trackOutput = track.output;
            boolean z3 = true;
            if (!this.sampleEncodingHandled) {
                if (track.hasContentEncryption) {
                    this.blockFlags &= -1073741825;
                    int i5 = 128;
                    if (!this.sampleSignalByteRead) {
                        extractorInput.readFully(this.scratch.getData(), 0, 1);
                        this.sampleBytesRead++;
                        if ((this.scratch.getData()[0] & 128) != 128) {
                            this.sampleSignalByte = this.scratch.getData()[0];
                            this.sampleSignalByteRead = true;
                        } else {
                            throw ParserException.createForMalformedContainer("Extension bit is set in signal byte", (Throwable) null);
                        }
                    }
                    byte b3 = this.sampleSignalByte;
                    if ((b3 & 1) == 1) {
                        boolean z4 = (b3 & 2) == 2;
                        this.blockFlags |= 1073741824;
                        if (!this.sampleInitializationVectorRead) {
                            extractorInput.readFully(this.encryptionInitializationVector.getData(), 0, 8);
                            this.sampleBytesRead += 8;
                            this.sampleInitializationVectorRead = true;
                            byte[] data = this.scratch.getData();
                            if (!z4) {
                                i5 = 0;
                            }
                            data[0] = (byte) (i5 | 8);
                            this.scratch.setPosition(0);
                            trackOutput.sampleData(this.scratch, 1, 1);
                            this.sampleBytesWritten++;
                            this.encryptionInitializationVector.setPosition(0);
                            trackOutput.sampleData(this.encryptionInitializationVector, 8, 1);
                            this.sampleBytesWritten += 8;
                        }
                        if (z4) {
                            if (!this.samplePartitionCountRead) {
                                extractorInput.readFully(this.scratch.getData(), 0, 1);
                                this.sampleBytesRead++;
                                this.scratch.setPosition(0);
                                this.samplePartitionCount = this.scratch.readUnsignedByte();
                                this.samplePartitionCountRead = true;
                            }
                            int i6 = this.samplePartitionCount * 4;
                            this.scratch.reset(i6);
                            extractorInput.readFully(this.scratch.getData(), 0, i6);
                            this.sampleBytesRead += i6;
                            short s3 = (short) ((this.samplePartitionCount / 2) + 1);
                            int i7 = (s3 * 6) + 2;
                            ByteBuffer byteBuffer = this.encryptionSubsampleDataBuffer;
                            if (byteBuffer == null || byteBuffer.capacity() < i7) {
                                this.encryptionSubsampleDataBuffer = ByteBuffer.allocate(i7);
                            }
                            this.encryptionSubsampleDataBuffer.position(0);
                            this.encryptionSubsampleDataBuffer.putShort(s3);
                            int i8 = 0;
                            int i9 = 0;
                            while (true) {
                                i4 = this.samplePartitionCount;
                                if (i8 >= i4) {
                                    break;
                                }
                                int readUnsignedIntToInt = this.scratch.readUnsignedIntToInt();
                                if (i8 % 2 == 0) {
                                    this.encryptionSubsampleDataBuffer.putShort((short) (readUnsignedIntToInt - i9));
                                } else {
                                    this.encryptionSubsampleDataBuffer.putInt(readUnsignedIntToInt - i9);
                                }
                                i8++;
                                i9 = readUnsignedIntToInt;
                            }
                            int i10 = (i3 - this.sampleBytesRead) - i9;
                            if (i4 % 2 == 1) {
                                this.encryptionSubsampleDataBuffer.putInt(i10);
                            } else {
                                this.encryptionSubsampleDataBuffer.putShort((short) i10);
                                this.encryptionSubsampleDataBuffer.putInt(0);
                            }
                            this.encryptionSubsampleData.reset(this.encryptionSubsampleDataBuffer.array(), i7);
                            trackOutput.sampleData(this.encryptionSubsampleData, i7, 1);
                            this.sampleBytesWritten += i7;
                        }
                    }
                } else {
                    byte[] bArr = track.sampleStrippedBytes;
                    if (bArr != null) {
                        this.sampleStrippedBytes.reset(bArr, bArr.length);
                    }
                }
                if (track.samplesHaveSupplementalData(z2)) {
                    this.blockFlags |= 268435456;
                    this.supplementalData.reset(0);
                    int limit = (this.sampleStrippedBytes.limit() + i3) - this.sampleBytesRead;
                    this.scratch.reset(4);
                    this.scratch.getData()[0] = (byte) ((limit >> 24) & 255);
                    this.scratch.getData()[1] = (byte) ((limit >> 16) & 255);
                    this.scratch.getData()[2] = (byte) ((limit >> 8) & 255);
                    this.scratch.getData()[3] = (byte) (limit & 255);
                    trackOutput.sampleData(this.scratch, 4, 2);
                    this.sampleBytesWritten += 4;
                }
                this.sampleEncodingHandled = true;
            }
            int limit2 = this.sampleStrippedBytes.limit() + i3;
            if (!CODEC_ID_H264.equals(track.codecId) && !CODEC_ID_H265.equals(track.codecId)) {
                if (track.trueHdSampleRechunker != null) {
                    if (this.sampleStrippedBytes.limit() != 0) {
                        z3 = false;
                    }
                    Assertions.checkState(z3);
                    track.trueHdSampleRechunker.startSample(extractorInput);
                }
                while (true) {
                    int i11 = this.sampleBytesRead;
                    if (i11 >= limit2) {
                        break;
                    }
                    int writeToOutput = writeToOutput(extractorInput, trackOutput, limit2 - i11);
                    this.sampleBytesRead += writeToOutput;
                    this.sampleBytesWritten += writeToOutput;
                }
            } else {
                byte[] data2 = this.nalLength.getData();
                data2[0] = 0;
                data2[1] = 0;
                data2[2] = 0;
                int i12 = track.nalUnitLengthFieldLength;
                int i13 = 4 - i12;
                while (this.sampleBytesRead < limit2) {
                    int i14 = this.sampleCurrentNalBytesRemaining;
                    if (i14 == 0) {
                        writeToTarget(extractorInput, data2, i13, i12);
                        this.sampleBytesRead += i12;
                        this.nalLength.setPosition(0);
                        this.sampleCurrentNalBytesRemaining = this.nalLength.readUnsignedIntToInt();
                        this.nalStartCode.setPosition(0);
                        trackOutput.sampleData(this.nalStartCode, 4);
                        this.sampleBytesWritten += 4;
                    } else {
                        int writeToOutput2 = writeToOutput(extractorInput, trackOutput, i14);
                        this.sampleBytesRead += writeToOutput2;
                        this.sampleBytesWritten += writeToOutput2;
                        this.sampleCurrentNalBytesRemaining -= writeToOutput2;
                    }
                }
            }
            if (CODEC_ID_VORBIS.equals(track.codecId)) {
                this.vorbisNumPageSamples.setPosition(0);
                trackOutput.sampleData(this.vorbisNumPageSamples, 4);
                this.sampleBytesWritten += 4;
            }
            return finishWriteSampleData();
        }
    }

    private void writeSubtitleSampleData(ExtractorInput extractorInput, byte[] bArr, int i3) throws IOException {
        int length = bArr.length + i3;
        if (this.subtitleSample.capacity() < length) {
            this.subtitleSample.reset(Arrays.copyOf(bArr, length + i3));
        } else {
            System.arraycopy(bArr, 0, this.subtitleSample.getData(), 0, bArr.length);
        }
        extractorInput.readFully(this.subtitleSample.getData(), bArr.length, i3);
        this.subtitleSample.setPosition(0);
        this.subtitleSample.setLimit(length);
    }

    private int writeToOutput(ExtractorInput extractorInput, TrackOutput trackOutput, int i3) throws IOException {
        int bytesLeft = this.sampleStrippedBytes.bytesLeft();
        if (bytesLeft <= 0) {
            return trackOutput.sampleData((DataReader) extractorInput, i3, false);
        }
        int min = Math.min(i3, bytesLeft);
        trackOutput.sampleData(this.sampleStrippedBytes, min);
        return min;
    }

    private void writeToTarget(ExtractorInput extractorInput, byte[] bArr, int i3, int i4) throws IOException {
        int min = Math.min(i4, this.sampleStrippedBytes.bytesLeft());
        extractorInput.readFully(bArr, i3 + min, i4 - min);
        if (min > 0) {
            this.sampleStrippedBytes.readBytes(bArr, i3, min);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:82:0x022e, code lost:
        throw com.appsamurai.storyly.exoplayer2.common.ParserException.createForMalformedContainer("EBML lacing sample size out of range.", (java.lang.Throwable) null);
     */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x027d  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x027f  */
    @androidx.annotation.CallSuper
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void binaryElement(int r22, int r23, com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput r24) throws java.io.IOException {
        /*
            r21 = this;
            r7 = r21
            r0 = r22
            r1 = r23
            r8 = r24
            r2 = 161(0xa1, float:2.26E-43)
            r3 = 163(0xa3, float:2.28E-43)
            r4 = 0
            r5 = 2
            r9 = 0
            r10 = 1
            if (r0 == r2) goto L_0x00c2
            if (r0 == r3) goto L_0x00c2
            r2 = 165(0xa5, float:2.31E-43)
            if (r0 == r2) goto L_0x00ac
            r2 = 16877(0x41ed, float:2.365E-41)
            if (r0 == r2) goto L_0x00a3
            r2 = 16981(0x4255, float:2.3795E-41)
            if (r0 == r2) goto L_0x0095
            r2 = 18402(0x47e2, float:2.5787E-41)
            if (r0 == r2) goto L_0x0083
            r2 = 21419(0x53ab, float:3.0014E-41)
            if (r0 == r2) goto L_0x005f
            r2 = 25506(0x63a2, float:3.5742E-41)
            if (r0 == r2) goto L_0x0051
            r2 = 30322(0x7672, float:4.249E-41)
            if (r0 != r2) goto L_0x003e
            r21.assertInTrackEntry(r22)
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mkv.MatroskaExtractor$Track r0 = r7.currentTrack
            byte[] r2 = new byte[r1]
            r0.projectionData = r2
            r8.readFully(r2, r9, r1)
            goto L_0x02e6
        L_0x003e:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Unexpected id: "
            r1.<init>(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            com.appsamurai.storyly.exoplayer2.common.ParserException r0 = com.appsamurai.storyly.exoplayer2.common.ParserException.createForMalformedContainer(r0, r4)
            throw r0
        L_0x0051:
            r21.assertInTrackEntry(r22)
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mkv.MatroskaExtractor$Track r0 = r7.currentTrack
            byte[] r2 = new byte[r1]
            r0.codecPrivate = r2
            r8.readFully(r2, r9, r1)
            goto L_0x02e6
        L_0x005f:
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r0 = r7.seekEntryIdBytes
            byte[] r0 = r0.getData()
            java.util.Arrays.fill(r0, r9)
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r0 = r7.seekEntryIdBytes
            byte[] r0 = r0.getData()
            int r2 = 4 - r1
            r8.readFully(r0, r2, r1)
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r0 = r7.seekEntryIdBytes
            r0.setPosition(r9)
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r0 = r7.seekEntryIdBytes
            long r0 = r0.readUnsignedInt()
            int r0 = (int) r0
            r7.seekEntryId = r0
            goto L_0x02e6
        L_0x0083:
            byte[] r2 = new byte[r1]
            r8.readFully(r2, r9, r1)
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mkv.MatroskaExtractor$Track r0 = r21.getCurrentTrack(r22)
            com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput$CryptoData r1 = new com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput$CryptoData
            r1.<init>(r10, r2, r9, r9)
            r0.cryptoData = r1
            goto L_0x02e6
        L_0x0095:
            r21.assertInTrackEntry(r22)
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mkv.MatroskaExtractor$Track r0 = r7.currentTrack
            byte[] r2 = new byte[r1]
            r0.sampleStrippedBytes = r2
            r8.readFully(r2, r9, r1)
            goto L_0x02e6
        L_0x00a3:
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mkv.MatroskaExtractor$Track r0 = r21.getCurrentTrack(r22)
            r7.handleBlockAddIDExtraData(r0, r8, r1)
            goto L_0x02e6
        L_0x00ac:
            int r0 = r7.blockState
            if (r0 == r5) goto L_0x00b1
            return
        L_0x00b1:
            android.util.SparseArray<com.appsamurai.storyly.exoplayer2.extractor.extractor.mkv.MatroskaExtractor$Track> r0 = r7.tracks
            int r2 = r7.blockTrackNumber
            java.lang.Object r0 = r0.get(r2)
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mkv.MatroskaExtractor$Track r0 = (com.appsamurai.storyly.exoplayer2.extractor.extractor.mkv.MatroskaExtractor.Track) r0
            int r2 = r7.blockAdditionalId
            r7.handleBlockAdditionalData(r0, r2, r8, r1)
            goto L_0x02e6
        L_0x00c2:
            int r2 = r7.blockState
            r6 = 8
            if (r2 != 0) goto L_0x00e7
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mkv.VarintReader r2 = r7.varintReader
            long r11 = r2.readUnsignedVarint(r8, r9, r10, r6)
            int r2 = (int) r11
            r7.blockTrackNumber = r2
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mkv.VarintReader r2 = r7.varintReader
            int r2 = r2.getLastLength()
            r7.blockTrackNumberLength = r2
            r11 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r7.blockDurationUs = r11
            r7.blockState = r10
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r2 = r7.scratch
            r2.reset((int) r9)
        L_0x00e7:
            android.util.SparseArray<com.appsamurai.storyly.exoplayer2.extractor.extractor.mkv.MatroskaExtractor$Track> r2 = r7.tracks
            int r11 = r7.blockTrackNumber
            java.lang.Object r2 = r2.get(r11)
            r11 = r2
            com.appsamurai.storyly.exoplayer2.extractor.extractor.mkv.MatroskaExtractor$Track r11 = (com.appsamurai.storyly.exoplayer2.extractor.extractor.mkv.MatroskaExtractor.Track) r11
            if (r11 != 0) goto L_0x00fe
            int r0 = r7.blockTrackNumberLength
            int r0 = r1 - r0
            r8.skipFully(r0)
            r7.blockState = r9
            return
        L_0x00fe:
            r11.assertOutputInitialized()
            int r2 = r7.blockState
            if (r2 != r10) goto L_0x029d
            r2 = 3
            r7.readScratch(r8, r2)
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r12 = r7.scratch
            byte[] r12 = r12.getData()
            byte r12 = r12[r5]
            r12 = r12 & 6
            int r12 = r12 >> r10
            r13 = 255(0xff, float:3.57E-43)
            if (r12 != 0) goto L_0x012c
            r7.blockSampleCount = r10
            int[] r4 = r7.blockSampleSizes
            int[] r4 = ensureArrayCapacity(r4, r10)
            r7.blockSampleSizes = r4
            int r12 = r7.blockTrackNumberLength
            int r1 = r1 - r12
            int r1 = r1 - r2
            r4[r9] = r1
        L_0x0128:
            r18 = r11
            goto L_0x0243
        L_0x012c:
            r14 = 4
            r7.readScratch(r8, r14)
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r15 = r7.scratch
            byte[] r15 = r15.getData()
            byte r15 = r15[r2]
            r15 = r15 & r13
            int r15 = r15 + r10
            r7.blockSampleCount = r15
            int[] r3 = r7.blockSampleSizes
            int[] r3 = ensureArrayCapacity(r3, r15)
            r7.blockSampleSizes = r3
            if (r12 != r5) goto L_0x0151
            int r2 = r7.blockTrackNumberLength
            int r1 = r1 - r2
            int r1 = r1 - r14
            int r2 = r7.blockSampleCount
            int r1 = r1 / r2
            java.util.Arrays.fill(r3, r9, r2, r1)
            goto L_0x0128
        L_0x0151:
            if (r12 != r10) goto L_0x0188
            r2 = r9
            r3 = r2
        L_0x0155:
            int r4 = r7.blockSampleCount
            int r12 = r4 + -1
            if (r2 >= r12) goto L_0x017d
            int[] r4 = r7.blockSampleSizes
            r4[r2] = r9
        L_0x015f:
            int r4 = r14 + 1
            r7.readScratch(r8, r4)
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r12 = r7.scratch
            byte[] r12 = r12.getData()
            byte r12 = r12[r14]
            r12 = r12 & r13
            int[] r14 = r7.blockSampleSizes
            r15 = r14[r2]
            int r15 = r15 + r12
            r14[r2] = r15
            if (r12 == r13) goto L_0x017b
            int r3 = r3 + r15
            int r2 = r2 + 1
            r14 = r4
            goto L_0x0155
        L_0x017b:
            r14 = r4
            goto L_0x015f
        L_0x017d:
            int[] r2 = r7.blockSampleSizes
            int r4 = r4 - r10
            int r12 = r7.blockTrackNumberLength
            int r1 = r1 - r12
            int r1 = r1 - r14
            int r1 = r1 - r3
            r2[r4] = r1
            goto L_0x0128
        L_0x0188:
            if (r12 != r2) goto L_0x028a
            r2 = r9
            r3 = r2
        L_0x018c:
            int r12 = r7.blockSampleCount
            int r15 = r12 + -1
            if (r2 >= r15) goto L_0x0236
            int[] r12 = r7.blockSampleSizes
            r12[r2] = r9
            int r12 = r14 + 1
            r7.readScratch(r8, r12)
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r15 = r7.scratch
            byte[] r15 = r15.getData()
            byte r15 = r15[r14]
            if (r15 == 0) goto L_0x022f
            r15 = r9
        L_0x01a6:
            if (r15 >= r6) goto L_0x01fc
            int r16 = 7 - r15
            int r5 = r10 << r16
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r9 = r7.scratch
            byte[] r9 = r9.getData()
            byte r9 = r9[r14]
            r9 = r9 & r5
            if (r9 == 0) goto L_0x01f2
            int r12 = r12 + r15
            r7.readScratch(r8, r12)
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r9 = r7.scratch
            byte[] r9 = r9.getData()
            int r17 = r14 + 1
            byte r9 = r9[r14]
            r9 = r9 & r13
            int r5 = ~r5
            r5 = r5 & r9
            r18 = r11
            long r10 = (long) r5
            r5 = r17
        L_0x01cd:
            if (r5 >= r12) goto L_0x01e2
            long r10 = r10 << r6
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r14 = r7.scratch
            byte[] r14 = r14.getData()
            int r17 = r5 + 1
            byte r5 = r14[r5]
            r5 = r5 & r13
            long r13 = (long) r5
            long r10 = r10 | r13
            r5 = r17
            r13 = 255(0xff, float:3.57E-43)
            goto L_0x01cd
        L_0x01e2:
            if (r2 <= 0) goto L_0x01f0
            int r15 = r15 * 7
            int r15 = r15 + 6
            r13 = 1
            long r19 = r13 << r15
            long r19 = r19 - r13
            long r10 = r10 - r19
        L_0x01f0:
            r14 = r12
            goto L_0x0201
        L_0x01f2:
            r18 = r11
            int r15 = r15 + 1
            r5 = 2
            r9 = 0
            r10 = 1
            r13 = 255(0xff, float:3.57E-43)
            goto L_0x01a6
        L_0x01fc:
            r18 = r11
            r10 = 0
            goto L_0x01f0
        L_0x0201:
            r12 = -2147483648(0xffffffff80000000, double:NaN)
            int r5 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r5 < 0) goto L_0x0228
            r12 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r5 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r5 > 0) goto L_0x0228
            int r5 = (int) r10
            int[] r10 = r7.blockSampleSizes
            if (r2 != 0) goto L_0x0215
            goto L_0x021a
        L_0x0215:
            int r11 = r2 + -1
            r11 = r10[r11]
            int r5 = r5 + r11
        L_0x021a:
            r10[r2] = r5
            int r3 = r3 + r5
            int r2 = r2 + 1
            r11 = r18
            r5 = 2
            r9 = 0
            r10 = 1
            r13 = 255(0xff, float:3.57E-43)
            goto L_0x018c
        L_0x0228:
            java.lang.String r0 = "EBML lacing sample size out of range."
            com.appsamurai.storyly.exoplayer2.common.ParserException r0 = com.appsamurai.storyly.exoplayer2.common.ParserException.createForMalformedContainer(r0, r4)
            throw r0
        L_0x022f:
            java.lang.String r0 = "No valid varint length mask found"
            com.appsamurai.storyly.exoplayer2.common.ParserException r0 = com.appsamurai.storyly.exoplayer2.common.ParserException.createForMalformedContainer(r0, r4)
            throw r0
        L_0x0236:
            r18 = r11
            int[] r2 = r7.blockSampleSizes
            r4 = 1
            int r12 = r12 - r4
            int r4 = r7.blockTrackNumberLength
            int r1 = r1 - r4
            int r1 = r1 - r14
            int r1 = r1 - r3
            r2[r12] = r1
        L_0x0243:
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r1 = r7.scratch
            byte[] r1 = r1.getData()
            r2 = 0
            byte r1 = r1[r2]
            int r1 = r1 << r6
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r2 = r7.scratch
            byte[] r2 = r2.getData()
            r3 = 1
            byte r2 = r2[r3]
            r3 = 255(0xff, float:3.57E-43)
            r2 = r2 & r3
            r1 = r1 | r2
            long r2 = r7.clusterTimecodeUs
            long r4 = (long) r1
            long r4 = r7.scaleTimecodeToUs(r4)
            long r2 = r2 + r4
            r7.blockTimeUs = r2
            r10 = r18
            int r1 = r10.type
            r2 = 2
            if (r1 == r2) goto L_0x027f
            r1 = 163(0xa3, float:2.28E-43)
            if (r0 != r1) goto L_0x027d
            com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray r1 = r7.scratch
            byte[] r1 = r1.getData()
            byte r1 = r1[r2]
            r3 = 128(0x80, float:1.794E-43)
            r1 = r1 & r3
            if (r1 != r3) goto L_0x027d
            goto L_0x027f
        L_0x027d:
            r1 = 0
            goto L_0x0280
        L_0x027f:
            r1 = 1
        L_0x0280:
            r7.blockFlags = r1
            r7.blockState = r2
            r1 = 0
            r7.blockSampleIndex = r1
            r1 = 163(0xa3, float:2.28E-43)
            goto L_0x029f
        L_0x028a:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Unexpected lacing value: "
            r0.<init>(r1)
            r0.append(r12)
            java.lang.String r0 = r0.toString()
            com.appsamurai.storyly.exoplayer2.common.ParserException r0 = com.appsamurai.storyly.exoplayer2.common.ParserException.createForMalformedContainer(r0, r4)
            throw r0
        L_0x029d:
            r10 = r11
            r1 = r3
        L_0x029f:
            if (r0 != r1) goto L_0x02cf
        L_0x02a1:
            int r0 = r7.blockSampleIndex
            int r1 = r7.blockSampleCount
            if (r0 >= r1) goto L_0x02cb
            int[] r1 = r7.blockSampleSizes
            r0 = r1[r0]
            r1 = 0
            int r5 = r7.writeSampleData(r8, r10, r0, r1)
            long r0 = r7.blockTimeUs
            int r2 = r7.blockSampleIndex
            int r3 = r10.defaultSampleDurationNs
            int r2 = r2 * r3
            int r2 = r2 / 1000
            long r2 = (long) r2
            long r2 = r2 + r0
            int r4 = r7.blockFlags
            r6 = 0
            r0 = r21
            r1 = r10
            r0.commitSampleToOutput(r1, r2, r4, r5, r6)
            int r0 = r7.blockSampleIndex
            r1 = 1
            int r0 = r0 + r1
            r7.blockSampleIndex = r0
            goto L_0x02a1
        L_0x02cb:
            r0 = 0
            r7.blockState = r0
            goto L_0x02e6
        L_0x02cf:
            r1 = 1
        L_0x02d0:
            int r0 = r7.blockSampleIndex
            int r2 = r7.blockSampleCount
            if (r0 >= r2) goto L_0x02e6
            int[] r2 = r7.blockSampleSizes
            r3 = r2[r0]
            int r3 = r7.writeSampleData(r8, r10, r3, r1)
            r2[r0] = r3
            int r0 = r7.blockSampleIndex
            int r0 = r0 + r1
            r7.blockSampleIndex = r0
            goto L_0x02d0
        L_0x02e6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.extractor.extractor.mkv.MatroskaExtractor.binaryElement(int, int, com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput):void");
    }

    @CallSuper
    public void endMasterElement(int i3) throws ParserException {
        assertInitialized();
        if (i3 != 160) {
            if (i3 == 174) {
                Track track = (Track) Assertions.checkStateNotNull(this.currentTrack);
                String str = track.codecId;
                if (str != null) {
                    if (isCodecSupported(str)) {
                        track.initializeOutput(this.extractorOutput, track.number);
                        this.tracks.put(track.number, track);
                    }
                    this.currentTrack = null;
                    return;
                }
                throw ParserException.createForMalformedContainer("CodecId is missing in TrackEntry element", (Throwable) null);
            } else if (i3 == ID_SEEK) {
                int i4 = this.seekEntryId;
                if (i4 != -1) {
                    long j2 = this.seekEntryPosition;
                    if (j2 != -1) {
                        if (i4 == ID_CUES) {
                            this.cuesContentPosition = j2;
                            return;
                        }
                        return;
                    }
                }
                throw ParserException.createForMalformedContainer("Mandatory element SeekID or SeekPosition not found", (Throwable) null);
            } else if (i3 == ID_CONTENT_ENCODING) {
                assertInTrackEntry(i3);
                Track track2 = this.currentTrack;
                if (!track2.hasContentEncryption) {
                    return;
                }
                if (track2.cryptoData != null) {
                    track2.drmInitData = new DrmInitData(new DrmInitData.SchemeData(C.UUID_NIL, MimeTypes.VIDEO_WEBM, this.currentTrack.cryptoData.encryptionKey));
                    return;
                }
                throw ParserException.createForMalformedContainer("Encrypted Track found but ContentEncKeyID was not found", (Throwable) null);
            } else if (i3 == ID_CONTENT_ENCODINGS) {
                assertInTrackEntry(i3);
                Track track3 = this.currentTrack;
                if (track3.hasContentEncryption && track3.sampleStrippedBytes != null) {
                    throw ParserException.createForMalformedContainer("Combining encryption and compression is not supported", (Throwable) null);
                }
            } else if (i3 == 357149030) {
                if (this.timecodeScale == C.TIME_UNSET) {
                    this.timecodeScale = 1000000;
                }
                long j3 = this.durationTimecode;
                if (j3 != C.TIME_UNSET) {
                    this.durationUs = scaleTimecodeToUs(j3);
                }
            } else if (i3 != ID_TRACKS) {
                if (i3 == ID_CUES) {
                    if (!this.sentSeekMap) {
                        this.extractorOutput.seekMap(buildSeekMap(this.cueTimesUs, this.cueClusterPositions));
                        this.sentSeekMap = true;
                    }
                    this.cueTimesUs = null;
                    this.cueClusterPositions = null;
                }
            } else if (this.tracks.size() != 0) {
                this.extractorOutput.endTracks();
            } else {
                throw ParserException.createForMalformedContainer("No valid tracks were found", (Throwable) null);
            }
        } else if (this.blockState == 2) {
            Track track4 = this.tracks.get(this.blockTrackNumber);
            track4.assertOutputInitialized();
            if (this.blockGroupDiscardPaddingNs > 0 && CODEC_ID_OPUS.equals(track4.codecId)) {
                this.supplementalData.reset(ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putLong(this.blockGroupDiscardPaddingNs).array());
            }
            int i5 = 0;
            for (int i6 = 0; i6 < this.blockSampleCount; i6++) {
                i5 += this.blockSampleSizes[i6];
            }
            int i7 = 0;
            while (i7 < this.blockSampleCount) {
                long j4 = this.blockTimeUs + ((long) ((track4.defaultSampleDurationNs * i7) / 1000));
                int i8 = this.blockFlags;
                if (i7 == 0 && !this.blockHasReferenceBlock) {
                    i8 |= 1;
                }
                int i9 = this.blockSampleSizes[i7];
                int i10 = i5 - i9;
                commitSampleToOutput(track4, j4, i8, i9, i10);
                i7++;
                i5 = i10;
            }
            this.blockState = 0;
        }
    }

    @CallSuper
    public void floatElement(int i3, double d2) throws ParserException {
        if (i3 == 181) {
            getCurrentTrack(i3).sampleRate = (int) d2;
        } else if (i3 != ID_DURATION) {
            switch (i3) {
                case ID_PRIMARY_R_CHROMATICITY_X /*21969*/:
                    getCurrentTrack(i3).primaryRChromaticityX = (float) d2;
                    return;
                case ID_PRIMARY_R_CHROMATICITY_Y /*21970*/:
                    getCurrentTrack(i3).primaryRChromaticityY = (float) d2;
                    return;
                case ID_PRIMARY_G_CHROMATICITY_X /*21971*/:
                    getCurrentTrack(i3).primaryGChromaticityX = (float) d2;
                    return;
                case ID_PRIMARY_G_CHROMATICITY_Y /*21972*/:
                    getCurrentTrack(i3).primaryGChromaticityY = (float) d2;
                    return;
                case ID_PRIMARY_B_CHROMATICITY_X /*21973*/:
                    getCurrentTrack(i3).primaryBChromaticityX = (float) d2;
                    return;
                case ID_PRIMARY_B_CHROMATICITY_Y /*21974*/:
                    getCurrentTrack(i3).primaryBChromaticityY = (float) d2;
                    return;
                case ID_WHITE_POINT_CHROMATICITY_X /*21975*/:
                    getCurrentTrack(i3).whitePointChromaticityX = (float) d2;
                    return;
                case ID_WHITE_POINT_CHROMATICITY_Y /*21976*/:
                    getCurrentTrack(i3).whitePointChromaticityY = (float) d2;
                    return;
                case ID_LUMNINANCE_MAX /*21977*/:
                    getCurrentTrack(i3).maxMasteringLuminance = (float) d2;
                    return;
                case ID_LUMNINANCE_MIN /*21978*/:
                    getCurrentTrack(i3).minMasteringLuminance = (float) d2;
                    return;
                default:
                    switch (i3) {
                        case ID_PROJECTION_POSE_YAW /*30323*/:
                            getCurrentTrack(i3).projectionPoseYaw = (float) d2;
                            return;
                        case ID_PROJECTION_POSE_PITCH /*30324*/:
                            getCurrentTrack(i3).projectionPosePitch = (float) d2;
                            return;
                        case ID_PROJECTION_POSE_ROLL /*30325*/:
                            getCurrentTrack(i3).projectionPoseRoll = (float) d2;
                            return;
                        default:
                            return;
                    }
            }
        } else {
            this.durationTimecode = (long) d2;
        }
    }

    public Track getCurrentTrack(int i3) throws ParserException {
        assertInTrackEntry(i3);
        return this.currentTrack;
    }

    @CallSuper
    public int getElementType(int i3) {
        switch (i3) {
            case 131:
            case 136:
            case 155:
            case 159:
            case 176:
            case 179:
            case 186:
            case 215:
            case 231:
            case ID_BLOCK_ADD_ID /*238*/:
            case ID_CUE_CLUSTER_POSITION /*241*/:
            case ID_REFERENCE_BLOCK /*251*/:
            case ID_BLOCK_ADD_ID_TYPE /*16871*/:
            case ID_CONTENT_COMPRESSION_ALGORITHM /*16980*/:
            case ID_DOC_TYPE_READ_VERSION /*17029*/:
            case ID_EBML_READ_VERSION /*17143*/:
            case ID_CONTENT_ENCRYPTION_ALGORITHM /*18401*/:
            case ID_CONTENT_ENCRYPTION_AES_SETTINGS_CIPHER_MODE /*18408*/:
            case ID_CONTENT_ENCODING_ORDER /*20529*/:
            case ID_CONTENT_ENCODING_SCOPE /*20530*/:
            case ID_SEEK_POSITION /*21420*/:
            case ID_STEREO_MODE /*21432*/:
            case ID_DISPLAY_WIDTH /*21680*/:
            case ID_DISPLAY_UNIT /*21682*/:
            case ID_DISPLAY_HEIGHT /*21690*/:
            case ID_FLAG_FORCED /*21930*/:
            case ID_COLOUR_RANGE /*21945*/:
            case ID_COLOUR_TRANSFER /*21946*/:
            case ID_COLOUR_PRIMARIES /*21947*/:
            case ID_MAX_CLL /*21948*/:
            case ID_MAX_FALL /*21949*/:
            case ID_MAX_BLOCK_ADDITION_ID /*21998*/:
            case ID_CODEC_DELAY /*22186*/:
            case ID_SEEK_PRE_ROLL /*22203*/:
            case ID_AUDIO_BIT_DEPTH /*25188*/:
            case ID_DISCARD_PADDING /*30114*/:
            case ID_PROJECTION_TYPE /*30321*/:
            case ID_DEFAULT_DURATION /*2352003*/:
            case ID_TIMECODE_SCALE /*2807729*/:
                return 2;
            case 134:
            case 17026:
            case ID_NAME /*21358*/:
            case ID_LANGUAGE /*2274716*/:
                return 3;
            case 160:
            case 166:
            case 174:
            case 183:
            case 187:
            case 224:
            case 225:
            case ID_BLOCK_ADDITION_MAPPING /*16868*/:
            case ID_CONTENT_ENCRYPTION_AES_SETTINGS /*18407*/:
            case ID_SEEK /*19899*/:
            case ID_CONTENT_COMPRESSION /*20532*/:
            case ID_CONTENT_ENCRYPTION /*20533*/:
            case ID_COLOUR /*21936*/:
            case ID_MASTERING_METADATA /*21968*/:
            case ID_CONTENT_ENCODING /*25152*/:
            case ID_CONTENT_ENCODINGS /*28032*/:
            case ID_BLOCK_ADDITIONS /*30113*/:
            case ID_PROJECTION /*30320*/:
            case ID_SEEK_HEAD /*290298740*/:
            case 357149030:
            case ID_TRACKS /*374648427*/:
            case ID_SEGMENT /*408125543*/:
            case ID_EBML /*440786851*/:
            case ID_CUES /*475249515*/:
            case ID_CLUSTER /*524531317*/:
                return 1;
            case 161:
            case 163:
            case 165:
            case ID_BLOCK_ADD_ID_EXTRA_DATA /*16877*/:
            case ID_CONTENT_COMPRESSION_SETTINGS /*16981*/:
            case ID_CONTENT_ENCRYPTION_KEY_ID /*18402*/:
            case ID_SEEK_ID /*21419*/:
            case ID_CODEC_PRIVATE /*25506*/:
            case ID_PROJECTION_PRIVATE /*30322*/:
                return 4;
            case 181:
            case ID_DURATION /*17545*/:
            case ID_PRIMARY_R_CHROMATICITY_X /*21969*/:
            case ID_PRIMARY_R_CHROMATICITY_Y /*21970*/:
            case ID_PRIMARY_G_CHROMATICITY_X /*21971*/:
            case ID_PRIMARY_G_CHROMATICITY_Y /*21972*/:
            case ID_PRIMARY_B_CHROMATICITY_X /*21973*/:
            case ID_PRIMARY_B_CHROMATICITY_Y /*21974*/:
            case ID_WHITE_POINT_CHROMATICITY_X /*21975*/:
            case ID_WHITE_POINT_CHROMATICITY_Y /*21976*/:
            case ID_LUMNINANCE_MAX /*21977*/:
            case ID_LUMNINANCE_MIN /*21978*/:
            case ID_PROJECTION_POSE_YAW /*30323*/:
            case ID_PROJECTION_POSE_PITCH /*30324*/:
            case ID_PROJECTION_POSE_ROLL /*30325*/:
                return 5;
            default:
                return 0;
        }
    }

    public void handleBlockAddIDExtraData(Track track, ExtractorInput extractorInput, int i3) throws IOException {
        if (track.blockAddIdType == 1685485123 || track.blockAddIdType == 1685480259) {
            byte[] bArr = new byte[i3];
            track.dolbyVisionConfigBytes = bArr;
            extractorInput.readFully(bArr, 0, i3);
            return;
        }
        extractorInput.skipFully(i3);
    }

    public void handleBlockAdditionalData(Track track, int i3, ExtractorInput extractorInput, int i4) throws IOException {
        if (i3 != 4 || !CODEC_ID_VP9.equals(track.codecId)) {
            extractorInput.skipFully(i4);
            return;
        }
        this.supplementalData.reset(i4);
        extractorInput.readFully(this.supplementalData.getData(), 0, i4);
    }

    public final void init(ExtractorOutput extractorOutput2) {
        this.extractorOutput = extractorOutput2;
    }

    @CallSuper
    public void integerElement(int i3, long j2) throws ParserException {
        if (i3 != ID_CONTENT_ENCODING_ORDER) {
            if (i3 != ID_CONTENT_ENCODING_SCOPE) {
                boolean z2 = false;
                switch (i3) {
                    case 131:
                        getCurrentTrack(i3).type = (int) j2;
                        return;
                    case 136:
                        Track currentTrack2 = getCurrentTrack(i3);
                        if (j2 == 1) {
                            z2 = true;
                        }
                        currentTrack2.flagDefault = z2;
                        return;
                    case 155:
                        this.blockDurationUs = scaleTimecodeToUs(j2);
                        return;
                    case 159:
                        getCurrentTrack(i3).channelCount = (int) j2;
                        return;
                    case 176:
                        getCurrentTrack(i3).width = (int) j2;
                        return;
                    case 179:
                        assertInCues(i3);
                        this.cueTimesUs.add(scaleTimecodeToUs(j2));
                        return;
                    case 186:
                        getCurrentTrack(i3).height = (int) j2;
                        return;
                    case 215:
                        getCurrentTrack(i3).number = (int) j2;
                        return;
                    case 231:
                        this.clusterTimecodeUs = scaleTimecodeToUs(j2);
                        return;
                    case ID_BLOCK_ADD_ID /*238*/:
                        this.blockAdditionalId = (int) j2;
                        return;
                    case ID_CUE_CLUSTER_POSITION /*241*/:
                        if (!this.seenClusterPositionForCurrentCuePoint) {
                            assertInCues(i3);
                            this.cueClusterPositions.add(j2);
                            this.seenClusterPositionForCurrentCuePoint = true;
                            return;
                        }
                        return;
                    case ID_REFERENCE_BLOCK /*251*/:
                        this.blockHasReferenceBlock = true;
                        return;
                    case ID_BLOCK_ADD_ID_TYPE /*16871*/:
                        int unused = getCurrentTrack(i3).blockAddIdType = (int) j2;
                        return;
                    case ID_CONTENT_COMPRESSION_ALGORITHM /*16980*/:
                        if (j2 != 3) {
                            throw ParserException.createForMalformedContainer("ContentCompAlgo " + j2 + " not supported", (Throwable) null);
                        }
                        return;
                    case ID_DOC_TYPE_READ_VERSION /*17029*/:
                        if (j2 < 1 || j2 > 2) {
                            throw ParserException.createForMalformedContainer("DocTypeReadVersion " + j2 + " not supported", (Throwable) null);
                        }
                        return;
                    case ID_EBML_READ_VERSION /*17143*/:
                        if (j2 != 1) {
                            throw ParserException.createForMalformedContainer("EBMLReadVersion " + j2 + " not supported", (Throwable) null);
                        }
                        return;
                    case ID_CONTENT_ENCRYPTION_ALGORITHM /*18401*/:
                        if (j2 != 5) {
                            throw ParserException.createForMalformedContainer("ContentEncAlgo " + j2 + " not supported", (Throwable) null);
                        }
                        return;
                    case ID_CONTENT_ENCRYPTION_AES_SETTINGS_CIPHER_MODE /*18408*/:
                        if (j2 != 1) {
                            throw ParserException.createForMalformedContainer("AESSettingsCipherMode " + j2 + " not supported", (Throwable) null);
                        }
                        return;
                    case ID_SEEK_POSITION /*21420*/:
                        this.seekEntryPosition = j2 + this.segmentContentPosition;
                        return;
                    case ID_STEREO_MODE /*21432*/:
                        int i4 = (int) j2;
                        assertInTrackEntry(i3);
                        if (i4 == 0) {
                            this.currentTrack.stereoMode = 0;
                            return;
                        } else if (i4 == 1) {
                            this.currentTrack.stereoMode = 2;
                            return;
                        } else if (i4 == 3) {
                            this.currentTrack.stereoMode = 1;
                            return;
                        } else if (i4 == 15) {
                            this.currentTrack.stereoMode = 3;
                            return;
                        } else {
                            return;
                        }
                    case ID_DISPLAY_WIDTH /*21680*/:
                        getCurrentTrack(i3).displayWidth = (int) j2;
                        return;
                    case ID_DISPLAY_UNIT /*21682*/:
                        getCurrentTrack(i3).displayUnit = (int) j2;
                        return;
                    case ID_DISPLAY_HEIGHT /*21690*/:
                        getCurrentTrack(i3).displayHeight = (int) j2;
                        return;
                    case ID_FLAG_FORCED /*21930*/:
                        Track currentTrack3 = getCurrentTrack(i3);
                        if (j2 == 1) {
                            z2 = true;
                        }
                        currentTrack3.flagForced = z2;
                        return;
                    case ID_MAX_BLOCK_ADDITION_ID /*21998*/:
                        getCurrentTrack(i3).maxBlockAdditionId = (int) j2;
                        return;
                    case ID_CODEC_DELAY /*22186*/:
                        getCurrentTrack(i3).codecDelayNs = j2;
                        return;
                    case ID_SEEK_PRE_ROLL /*22203*/:
                        getCurrentTrack(i3).seekPreRollNs = j2;
                        return;
                    case ID_AUDIO_BIT_DEPTH /*25188*/:
                        getCurrentTrack(i3).audioBitDepth = (int) j2;
                        return;
                    case ID_DISCARD_PADDING /*30114*/:
                        this.blockGroupDiscardPaddingNs = j2;
                        return;
                    case ID_PROJECTION_TYPE /*30321*/:
                        assertInTrackEntry(i3);
                        int i5 = (int) j2;
                        if (i5 == 0) {
                            this.currentTrack.projectionType = 0;
                            return;
                        } else if (i5 == 1) {
                            this.currentTrack.projectionType = 1;
                            return;
                        } else if (i5 == 2) {
                            this.currentTrack.projectionType = 2;
                            return;
                        } else if (i5 == 3) {
                            this.currentTrack.projectionType = 3;
                            return;
                        } else {
                            return;
                        }
                    case ID_DEFAULT_DURATION /*2352003*/:
                        getCurrentTrack(i3).defaultSampleDurationNs = (int) j2;
                        return;
                    case ID_TIMECODE_SCALE /*2807729*/:
                        this.timecodeScale = j2;
                        return;
                    default:
                        switch (i3) {
                            case ID_COLOUR_RANGE /*21945*/:
                                assertInTrackEntry(i3);
                                int i6 = (int) j2;
                                if (i6 == 1) {
                                    this.currentTrack.colorRange = 2;
                                    return;
                                } else if (i6 == 2) {
                                    this.currentTrack.colorRange = 1;
                                    return;
                                } else {
                                    return;
                                }
                            case ID_COLOUR_TRANSFER /*21946*/:
                                assertInTrackEntry(i3);
                                int isoTransferCharacteristicsToColorTransfer = ColorInfo.isoTransferCharacteristicsToColorTransfer((int) j2);
                                if (isoTransferCharacteristicsToColorTransfer != -1) {
                                    this.currentTrack.colorTransfer = isoTransferCharacteristicsToColorTransfer;
                                    return;
                                }
                                return;
                            case ID_COLOUR_PRIMARIES /*21947*/:
                                assertInTrackEntry(i3);
                                this.currentTrack.hasColorInfo = true;
                                int isoColorPrimariesToColorSpace = ColorInfo.isoColorPrimariesToColorSpace((int) j2);
                                if (isoColorPrimariesToColorSpace != -1) {
                                    this.currentTrack.colorSpace = isoColorPrimariesToColorSpace;
                                    return;
                                }
                                return;
                            case ID_MAX_CLL /*21948*/:
                                getCurrentTrack(i3).maxContentLuminance = (int) j2;
                                return;
                            case ID_MAX_FALL /*21949*/:
                                getCurrentTrack(i3).maxFrameAverageLuminance = (int) j2;
                                return;
                            default:
                                return;
                        }
                }
            } else if (j2 != 1) {
                throw ParserException.createForMalformedContainer("ContentEncodingScope " + j2 + " not supported", (Throwable) null);
            }
        } else if (j2 != 0) {
            throw ParserException.createForMalformedContainer("ContentEncodingOrder " + j2 + " not supported", (Throwable) null);
        }
    }

    @CallSuper
    public boolean isLevel1Element(int i3) {
        return i3 == 357149030 || i3 == ID_CLUSTER || i3 == ID_CUES || i3 == ID_TRACKS;
    }

    public final int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        this.haveOutputSample = false;
        boolean z2 = true;
        while (z2 && !this.haveOutputSample) {
            z2 = this.reader.read(extractorInput);
            if (z2 && maybeSeekForCues(positionHolder, extractorInput.getPosition())) {
                return 1;
            }
        }
        if (z2) {
            return 0;
        }
        for (int i3 = 0; i3 < this.tracks.size(); i3++) {
            Track valueAt = this.tracks.valueAt(i3);
            valueAt.assertOutputInitialized();
            valueAt.outputPendingSampleMetadata();
        }
        return -1;
    }

    public final void release() {
    }

    @CallSuper
    public void seek(long j2, long j3) {
        this.clusterTimecodeUs = C.TIME_UNSET;
        this.blockState = 0;
        this.reader.reset();
        this.varintReader.reset();
        resetWriteSampleData();
        for (int i3 = 0; i3 < this.tracks.size(); i3++) {
            this.tracks.valueAt(i3).reset();
        }
    }

    public final boolean sniff(ExtractorInput extractorInput) throws IOException {
        return new Sniffer().sniff(extractorInput);
    }

    @CallSuper
    public void startMasterElement(int i3, long j2, long j3) throws ParserException {
        assertInitialized();
        if (i3 == 160) {
            this.blockHasReferenceBlock = false;
            this.blockGroupDiscardPaddingNs = 0;
        } else if (i3 == 174) {
            this.currentTrack = new Track();
        } else if (i3 == 187) {
            this.seenClusterPositionForCurrentCuePoint = false;
        } else if (i3 == ID_SEEK) {
            this.seekEntryId = -1;
            this.seekEntryPosition = -1;
        } else if (i3 == ID_CONTENT_ENCRYPTION) {
            getCurrentTrack(i3).hasContentEncryption = true;
        } else if (i3 == ID_MASTERING_METADATA) {
            getCurrentTrack(i3).hasColorInfo = true;
        } else if (i3 == ID_SEGMENT) {
            long j4 = this.segmentContentPosition;
            if (j4 == -1 || j4 == j2) {
                this.segmentContentPosition = j2;
                this.segmentContentSize = j3;
                return;
            }
            throw ParserException.createForMalformedContainer("Multiple Segment elements not supported", (Throwable) null);
        } else if (i3 == ID_CUES) {
            this.cueTimesUs = new LongArray();
            this.cueClusterPositions = new LongArray();
        } else if (i3 != ID_CLUSTER || this.sentSeekMap) {
        } else {
            if (!this.seekForCuesEnabled || this.cuesContentPosition == -1) {
                this.extractorOutput.seekMap(new SeekMap.Unseekable(this.durationUs));
                this.sentSeekMap = true;
                return;
            }
            this.seekForCues = true;
        }
    }

    @CallSuper
    public void stringElement(int i3, String str) throws ParserException {
        if (i3 == 134) {
            getCurrentTrack(i3).codecId = str;
        } else if (i3 != 17026) {
            if (i3 == ID_NAME) {
                getCurrentTrack(i3).name = str;
            } else if (i3 == ID_LANGUAGE) {
                String unused = getCurrentTrack(i3).language = str;
            }
        } else if (!DOC_TYPE_WEBM.equals(str) && !DOC_TYPE_MATROSKA.equals(str)) {
            throw ParserException.createForMalformedContainer("DocType " + str + " not supported", (Throwable) null);
        }
    }

    public MatroskaExtractor(int i3) {
        this(new DefaultEbmlReader(), i3);
    }

    public MatroskaExtractor(EbmlReader ebmlReader, int i3) {
        this.segmentContentPosition = -1;
        this.timecodeScale = C.TIME_UNSET;
        this.durationTimecode = C.TIME_UNSET;
        this.durationUs = C.TIME_UNSET;
        this.cuesContentPosition = -1;
        this.seekPositionAfterBuildingCues = -1;
        this.clusterTimecodeUs = C.TIME_UNSET;
        this.reader = ebmlReader;
        ebmlReader.init(new InnerEbmlProcessor());
        this.seekForCuesEnabled = (i3 & 1) == 0;
        this.varintReader = new VarintReader();
        this.tracks = new SparseArray<>();
        this.scratch = new ParsableByteArray(4);
        this.vorbisNumPageSamples = new ParsableByteArray(ByteBuffer.allocate(4).putInt(-1).array());
        this.seekEntryIdBytes = new ParsableByteArray(4);
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalLength = new ParsableByteArray(4);
        this.sampleStrippedBytes = new ParsableByteArray();
        this.subtitleSample = new ParsableByteArray();
        this.encryptionInitializationVector = new ParsableByteArray(8);
        this.encryptionSubsampleData = new ParsableByteArray();
        this.supplementalData = new ParsableByteArray();
        this.blockSampleSizes = new int[1];
    }
}
