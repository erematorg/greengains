package com.appsamurai.storyly.exoplayer2.core.audio;

import androidx.compose.runtime.b;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import java.nio.ShortBuffer;
import java.util.Arrays;

final class Sonic {
    private static final int AMDF_FREQUENCY = 4000;
    private static final int BYTES_PER_SAMPLE = 2;
    private static final int MAXIMUM_PITCH = 400;
    private static final int MINIMUM_PITCH = 65;
    private final int channelCount;
    private final short[] downSampleBuffer;
    private short[] inputBuffer;
    private int inputFrameCount;
    private final int inputSampleRateHz;
    private int maxDiff;
    private final int maxPeriod;
    private final int maxRequiredFrameCount;
    private int minDiff;
    private final int minPeriod;
    private int newRatePosition;
    private int oldRatePosition;
    private short[] outputBuffer;
    private int outputFrameCount;
    private final float pitch;
    private short[] pitchBuffer;
    private int pitchFrameCount;
    private int prevMinDiff;
    private int prevPeriod;
    private final float rate;
    private int remainingInputToCopyFrameCount;
    private final float speed;

    public Sonic(int i3, int i4, float f2, float f3, int i5) {
        this.inputSampleRateHz = i3;
        this.channelCount = i4;
        this.speed = f2;
        this.pitch = f3;
        this.rate = ((float) i3) / ((float) i5);
        this.minPeriod = i3 / 400;
        int i6 = i3 / 65;
        this.maxPeriod = i6;
        int i7 = i6 * 2;
        this.maxRequiredFrameCount = i7;
        this.downSampleBuffer = new short[i7];
        this.inputBuffer = new short[(i7 * i4)];
        this.outputBuffer = new short[(i7 * i4)];
        this.pitchBuffer = new short[(i7 * i4)];
    }

    private void adjustRate(float f2, int i3) {
        int i4;
        int i5;
        if (this.outputFrameCount != i3) {
            int i6 = this.inputSampleRateHz;
            int i7 = (int) (((float) i6) / f2);
            while (true) {
                if (i7 <= 16384 && i6 <= 16384) {
                    break;
                }
                i7 /= 2;
                i6 /= 2;
            }
            moveNewSamplesToPitchBuffer(i3);
            int i8 = 0;
            while (true) {
                int i9 = this.pitchFrameCount;
                boolean z2 = true;
                if (i8 < i9 - 1) {
                    while (true) {
                        i4 = this.oldRatePosition;
                        int i10 = (i4 + 1) * i7;
                        i5 = this.newRatePosition;
                        if (i10 <= i5 * i6) {
                            break;
                        }
                        this.outputBuffer = ensureSpaceForAdditionalFrames(this.outputBuffer, this.outputFrameCount, 1);
                        int i11 = 0;
                        while (true) {
                            int i12 = this.channelCount;
                            if (i11 >= i12) {
                                break;
                            }
                            this.outputBuffer[(this.outputFrameCount * i12) + i11] = interpolate(this.pitchBuffer, (i12 * i8) + i11, i6, i7);
                            i11++;
                        }
                        this.newRatePosition++;
                        this.outputFrameCount++;
                    }
                    int i13 = i4 + 1;
                    this.oldRatePosition = i13;
                    if (i13 == i6) {
                        this.oldRatePosition = 0;
                        if (i5 != i7) {
                            z2 = false;
                        }
                        Assertions.checkState(z2);
                        this.newRatePosition = 0;
                    }
                    i8++;
                } else {
                    removePitchFrames(i9 - 1);
                    return;
                }
            }
        }
    }

    private void changeSpeed(float f2) {
        int insertPitchPeriod;
        int i3 = this.inputFrameCount;
        if (i3 >= this.maxRequiredFrameCount) {
            int i4 = 0;
            do {
                if (this.remainingInputToCopyFrameCount > 0) {
                    insertPitchPeriod = copyInputToOutput(i4);
                } else {
                    int findPitchPeriod = findPitchPeriod(this.inputBuffer, i4);
                    if (((double) f2) > 1.0d) {
                        i4 = findPitchPeriod + skipPitchPeriod(this.inputBuffer, i4, f2, findPitchPeriod) + i4;
                    } else {
                        insertPitchPeriod = insertPitchPeriod(this.inputBuffer, i4, f2, findPitchPeriod);
                    }
                }
                i4 += insertPitchPeriod;
            } while (this.maxRequiredFrameCount + i4 <= i3);
            removeProcessedInputFrames(i4);
        }
    }

    private int copyInputToOutput(int i3) {
        int min = Math.min(this.maxRequiredFrameCount, this.remainingInputToCopyFrameCount);
        copyToOutput(this.inputBuffer, i3, min);
        this.remainingInputToCopyFrameCount -= min;
        return min;
    }

    private void copyToOutput(short[] sArr, int i3, int i4) {
        short[] ensureSpaceForAdditionalFrames = ensureSpaceForAdditionalFrames(this.outputBuffer, this.outputFrameCount, i4);
        this.outputBuffer = ensureSpaceForAdditionalFrames;
        int i5 = this.channelCount;
        System.arraycopy(sArr, i3 * i5, ensureSpaceForAdditionalFrames, this.outputFrameCount * i5, i5 * i4);
        this.outputFrameCount += i4;
    }

    private void downSampleInput(short[] sArr, int i3, int i4) {
        int i5 = this.maxRequiredFrameCount / i4;
        int i6 = this.channelCount;
        int i7 = i4 * i6;
        int i8 = i3 * i6;
        for (int i9 = 0; i9 < i5; i9++) {
            int i10 = 0;
            for (int i11 = 0; i11 < i7; i11++) {
                i10 += sArr[b.a(i9, i7, i8, i11)];
            }
            this.downSampleBuffer[i9] = (short) (i10 / i7);
        }
    }

    private short[] ensureSpaceForAdditionalFrames(short[] sArr, int i3, int i4) {
        int length = sArr.length;
        int i5 = this.channelCount;
        int i6 = length / i5;
        return i3 + i4 <= i6 ? sArr : Arrays.copyOf(sArr, (((i6 * 3) / 2) + i4) * i5);
    }

    private int findPitchPeriod(short[] sArr, int i3) {
        int i4;
        int i5 = this.inputSampleRateHz;
        int i6 = i5 > 4000 ? i5 / 4000 : 1;
        if (this.channelCount == 1 && i6 == 1) {
            i4 = findPitchPeriodInRange(sArr, i3, this.minPeriod, this.maxPeriod);
        } else {
            downSampleInput(sArr, i3, i6);
            int findPitchPeriodInRange = findPitchPeriodInRange(this.downSampleBuffer, 0, this.minPeriod / i6, this.maxPeriod / i6);
            if (i6 != 1) {
                int i7 = findPitchPeriodInRange * i6;
                int i8 = i6 * 4;
                int i9 = i7 - i8;
                int i10 = i7 + i8;
                int i11 = this.minPeriod;
                if (i9 < i11) {
                    i9 = i11;
                }
                int i12 = this.maxPeriod;
                if (i10 > i12) {
                    i10 = i12;
                }
                if (this.channelCount == 1) {
                    i4 = findPitchPeriodInRange(sArr, i3, i9, i10);
                } else {
                    downSampleInput(sArr, i3, 1);
                    i4 = findPitchPeriodInRange(this.downSampleBuffer, 0, i9, i10);
                }
            } else {
                i4 = findPitchPeriodInRange;
            }
        }
        int i13 = previousPeriodBetter(this.minDiff, this.maxDiff) ? this.prevPeriod : i4;
        this.prevMinDiff = this.minDiff;
        this.prevPeriod = i4;
        return i13;
    }

    private int findPitchPeriodInRange(short[] sArr, int i3, int i4, int i5) {
        int i6 = i3 * this.channelCount;
        int i7 = 255;
        int i8 = 1;
        int i9 = 0;
        int i10 = 0;
        while (i4 <= i5) {
            int i11 = 0;
            for (int i12 = 0; i12 < i4; i12++) {
                i11 += Math.abs(sArr[i6 + i12] - sArr[(i6 + i4) + i12]);
            }
            if (i11 * i9 < i8 * i4) {
                i9 = i4;
                i8 = i11;
            }
            if (i11 * i7 > i10 * i4) {
                i7 = i4;
                i10 = i11;
            }
            i4++;
        }
        this.minDiff = i8 / i9;
        this.maxDiff = i10 / i7;
        return i9;
    }

    private int insertPitchPeriod(short[] sArr, int i3, float f2, int i4) {
        int i5;
        if (f2 < 0.5f) {
            i5 = (int) ((((float) i4) * f2) / (1.0f - f2));
        } else {
            this.remainingInputToCopyFrameCount = (int) ((((2.0f * f2) - 1.0f) * ((float) i4)) / (1.0f - f2));
            i5 = i4;
        }
        int i6 = i4 + i5;
        short[] ensureSpaceForAdditionalFrames = ensureSpaceForAdditionalFrames(this.outputBuffer, this.outputFrameCount, i6);
        this.outputBuffer = ensureSpaceForAdditionalFrames;
        int i7 = this.channelCount;
        System.arraycopy(sArr, i3 * i7, ensureSpaceForAdditionalFrames, this.outputFrameCount * i7, i7 * i4);
        overlapAdd(i5, this.channelCount, this.outputBuffer, this.outputFrameCount + i4, sArr, i3 + i4, sArr, i3);
        this.outputFrameCount += i6;
        return i5;
    }

    private short interpolate(short[] sArr, int i3, int i4, int i5) {
        short s3 = sArr[i3];
        short s4 = sArr[i3 + this.channelCount];
        int i6 = this.newRatePosition * i4;
        int i7 = this.oldRatePosition;
        int i8 = i7 * i5;
        int i9 = (i7 + 1) * i5;
        int i10 = i9 - i6;
        int i11 = i9 - i8;
        return (short) ((((i11 - i10) * s4) + (s3 * i10)) / i11);
    }

    private void moveNewSamplesToPitchBuffer(int i3) {
        int i4 = this.outputFrameCount - i3;
        short[] ensureSpaceForAdditionalFrames = ensureSpaceForAdditionalFrames(this.pitchBuffer, this.pitchFrameCount, i4);
        this.pitchBuffer = ensureSpaceForAdditionalFrames;
        short[] sArr = this.outputBuffer;
        int i5 = this.channelCount;
        System.arraycopy(sArr, i3 * i5, ensureSpaceForAdditionalFrames, this.pitchFrameCount * i5, i5 * i4);
        this.outputFrameCount = i3;
        this.pitchFrameCount += i4;
    }

    private static void overlapAdd(int i3, int i4, short[] sArr, int i5, short[] sArr2, int i6, short[] sArr3, int i7) {
        for (int i8 = 0; i8 < i4; i8++) {
            int i9 = (i5 * i4) + i8;
            int i10 = (i7 * i4) + i8;
            int i11 = (i6 * i4) + i8;
            for (int i12 = 0; i12 < i3; i12++) {
                sArr[i9] = (short) (((sArr3[i10] * i12) + ((i3 - i12) * sArr2[i11])) / i3);
                i9 += i4;
                i11 += i4;
                i10 += i4;
            }
        }
    }

    private boolean previousPeriodBetter(int i3, int i4) {
        return i3 != 0 && this.prevPeriod != 0 && i4 <= i3 * 3 && i3 * 2 > this.prevMinDiff * 3;
    }

    private void processStreamInput() {
        int i3 = this.outputFrameCount;
        float f2 = this.speed;
        float f3 = this.pitch;
        float f4 = f2 / f3;
        float f5 = this.rate * f3;
        double d2 = (double) f4;
        if (d2 > 1.00001d || d2 < 0.99999d) {
            changeSpeed(f4);
        } else {
            copyToOutput(this.inputBuffer, 0, this.inputFrameCount);
            this.inputFrameCount = 0;
        }
        if (f5 != 1.0f) {
            adjustRate(f5, i3);
        }
    }

    private void removePitchFrames(int i3) {
        if (i3 != 0) {
            short[] sArr = this.pitchBuffer;
            int i4 = this.channelCount;
            System.arraycopy(sArr, i3 * i4, sArr, 0, (this.pitchFrameCount - i3) * i4);
            this.pitchFrameCount -= i3;
        }
    }

    private void removeProcessedInputFrames(int i3) {
        int i4 = this.inputFrameCount - i3;
        short[] sArr = this.inputBuffer;
        int i5 = this.channelCount;
        System.arraycopy(sArr, i3 * i5, sArr, 0, i5 * i4);
        this.inputFrameCount = i4;
    }

    private int skipPitchPeriod(short[] sArr, int i3, float f2, int i4) {
        int i5;
        if (f2 >= 2.0f) {
            i5 = (int) (((float) i4) / (f2 - 1.0f));
        } else {
            this.remainingInputToCopyFrameCount = (int) (((2.0f - f2) * ((float) i4)) / (f2 - 1.0f));
            i5 = i4;
        }
        short[] ensureSpaceForAdditionalFrames = ensureSpaceForAdditionalFrames(this.outputBuffer, this.outputFrameCount, i5);
        this.outputBuffer = ensureSpaceForAdditionalFrames;
        overlapAdd(i5, this.channelCount, ensureSpaceForAdditionalFrames, this.outputFrameCount, sArr, i3, sArr, i3 + i4);
        this.outputFrameCount += i5;
        return i5;
    }

    public void flush() {
        this.inputFrameCount = 0;
        this.outputFrameCount = 0;
        this.pitchFrameCount = 0;
        this.oldRatePosition = 0;
        this.newRatePosition = 0;
        this.remainingInputToCopyFrameCount = 0;
        this.prevPeriod = 0;
        this.prevMinDiff = 0;
        this.minDiff = 0;
        this.maxDiff = 0;
    }

    public void getOutput(ShortBuffer shortBuffer) {
        int min = Math.min(shortBuffer.remaining() / this.channelCount, this.outputFrameCount);
        shortBuffer.put(this.outputBuffer, 0, this.channelCount * min);
        int i3 = this.outputFrameCount - min;
        this.outputFrameCount = i3;
        short[] sArr = this.outputBuffer;
        int i4 = this.channelCount;
        System.arraycopy(sArr, min * i4, sArr, 0, i3 * i4);
    }

    public int getOutputSize() {
        return this.outputFrameCount * this.channelCount * 2;
    }

    public int getPendingInputBytes() {
        return this.inputFrameCount * this.channelCount * 2;
    }

    public void queueEndOfStream() {
        int i3;
        int i4 = this.inputFrameCount;
        float f2 = this.speed;
        float f3 = this.pitch;
        int i5 = this.outputFrameCount + ((int) ((((((float) i4) / (f2 / f3)) + ((float) this.pitchFrameCount)) / (this.rate * f3)) + 0.5f));
        this.inputBuffer = ensureSpaceForAdditionalFrames(this.inputBuffer, i4, (this.maxRequiredFrameCount * 2) + i4);
        int i6 = 0;
        while (true) {
            i3 = this.maxRequiredFrameCount;
            int i7 = this.channelCount;
            if (i6 >= i3 * 2 * i7) {
                break;
            }
            this.inputBuffer[(i7 * i4) + i6] = 0;
            i6++;
        }
        this.inputFrameCount = (i3 * 2) + this.inputFrameCount;
        processStreamInput();
        if (this.outputFrameCount > i5) {
            this.outputFrameCount = i5;
        }
        this.inputFrameCount = 0;
        this.remainingInputToCopyFrameCount = 0;
        this.pitchFrameCount = 0;
    }

    public void queueInput(ShortBuffer shortBuffer) {
        int remaining = shortBuffer.remaining();
        int i3 = this.channelCount;
        int i4 = remaining / i3;
        short[] ensureSpaceForAdditionalFrames = ensureSpaceForAdditionalFrames(this.inputBuffer, this.inputFrameCount, i4);
        this.inputBuffer = ensureSpaceForAdditionalFrames;
        shortBuffer.get(ensureSpaceForAdditionalFrames, this.inputFrameCount * this.channelCount, ((i3 * i4) * 2) / 2);
        this.inputFrameCount += i4;
        processStreamInput();
    }
}
