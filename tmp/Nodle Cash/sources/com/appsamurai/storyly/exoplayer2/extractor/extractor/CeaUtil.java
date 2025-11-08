package com.appsamurai.storyly.exoplayer2.extractor.extractor;

import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput;

public final class CeaUtil {
    private static final int COUNTRY_CODE = 181;
    private static final int PAYLOAD_TYPE_CC = 4;
    private static final int PROVIDER_CODE_ATSC = 49;
    private static final int PROVIDER_CODE_DIRECTV = 47;
    private static final String TAG = "CeaUtil";
    public static final int USER_DATA_IDENTIFIER_GA94 = 1195456820;
    public static final int USER_DATA_TYPE_CODE_MPEG_CC = 3;

    private CeaUtil() {
    }

    public static void consume(long j2, ParsableByteArray parsableByteArray, TrackOutput[] trackOutputArr) {
        while (true) {
            boolean z2 = true;
            if (parsableByteArray.bytesLeft() > 1) {
                int readNon255TerminatedValue = readNon255TerminatedValue(parsableByteArray);
                int readNon255TerminatedValue2 = readNon255TerminatedValue(parsableByteArray);
                int position = parsableByteArray.getPosition() + readNon255TerminatedValue2;
                if (readNon255TerminatedValue2 == -1 || readNon255TerminatedValue2 > parsableByteArray.bytesLeft()) {
                    Log.w(TAG, "Skipping remainder of malformed SEI NAL unit.");
                    position = parsableByteArray.limit();
                } else if (readNon255TerminatedValue == 4 && readNon255TerminatedValue2 >= 8) {
                    int readUnsignedByte = parsableByteArray.readUnsignedByte();
                    int readUnsignedShort = parsableByteArray.readUnsignedShort();
                    int readInt = readUnsignedShort == 49 ? parsableByteArray.readInt() : 0;
                    int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
                    if (readUnsignedShort == 47) {
                        parsableByteArray.skipBytes(1);
                    }
                    boolean z3 = readUnsignedByte == 181 && (readUnsignedShort == 49 || readUnsignedShort == 47) && readUnsignedByte2 == 3;
                    if (readUnsignedShort == 49) {
                        if (readInt != 1195456820) {
                            z2 = false;
                        }
                        z3 &= z2;
                    }
                    if (z3) {
                        consumeCcData(j2, parsableByteArray, trackOutputArr);
                    }
                }
                parsableByteArray.setPosition(position);
            } else {
                return;
            }
        }
    }

    public static void consumeCcData(long j2, ParsableByteArray parsableByteArray, TrackOutput[] trackOutputArr) {
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        if ((readUnsignedByte & 64) != 0) {
            parsableByteArray.skipBytes(1);
            int i3 = (readUnsignedByte & 31) * 3;
            int position = parsableByteArray.getPosition();
            for (TrackOutput trackOutput : trackOutputArr) {
                parsableByteArray.setPosition(position);
                trackOutput.sampleData(parsableByteArray, i3);
                if (j2 != C.TIME_UNSET) {
                    trackOutput.sampleMetadata(j2, 1, i3, 0, (TrackOutput.CryptoData) null);
                }
            }
        }
    }

    private static int readNon255TerminatedValue(ParsableByteArray parsableByteArray) {
        int i3 = 0;
        while (parsableByteArray.bytesLeft() != 0) {
            int readUnsignedByte = parsableByteArray.readUnsignedByte();
            i3 += readUnsignedByte;
            if (readUnsignedByte != 255) {
                return i3;
            }
        }
        return -1;
    }
}
