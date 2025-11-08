package com.appsamurai.storyly.exoplayer2.core.audio;

import com.appsamurai.storyly.exoplayer2.core.audio.AudioProcessor;

final class ResamplingAudioProcessor extends BaseAudioProcessor {
    public AudioProcessor.AudioFormat onConfigure(AudioProcessor.AudioFormat audioFormat) throws AudioProcessor.UnhandledAudioFormatException {
        int i3 = audioFormat.encoding;
        if (i3 == 3 || i3 == 2 || i3 == 268435456 || i3 == 536870912 || i3 == 805306368 || i3 == 4) {
            return i3 != 2 ? new AudioProcessor.AudioFormat(audioFormat.sampleRate, audioFormat.channelCount, 2) : AudioProcessor.AudioFormat.NOT_SET;
        }
        throw new AudioProcessor.UnhandledAudioFormatException(audioFormat);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00ae A[LOOP:4: B:27:0x00ae->B:28:0x00b0, LOOP_START, PHI: r0 
      PHI: (r0v1 int) = (r0v0 int), (r0v2 int) binds: [B:12:0x0036, B:28:0x00b0] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void queueInput(java.nio.ByteBuffer r10) {
        /*
            r9 = this;
            int r0 = r10.position()
            int r1 = r10.limit()
            int r2 = r1 - r0
            com.appsamurai.storyly.exoplayer2.core.audio.AudioProcessor$AudioFormat r3 = r9.inputAudioFormat
            int r3 = r3.encoding
            r4 = 805306368(0x30000000, float:4.656613E-10)
            r5 = 536870912(0x20000000, float:1.0842022E-19)
            r6 = 268435456(0x10000000, float:2.5243549E-29)
            r7 = 4
            r8 = 3
            if (r3 == r8) goto L_0x0029
            if (r3 == r7) goto L_0x002c
            if (r3 == r6) goto L_0x002e
            if (r3 == r5) goto L_0x0027
            if (r3 != r4) goto L_0x0021
            goto L_0x002c
        L_0x0021:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            r9.<init>()
            throw r9
        L_0x0027:
            int r2 = r2 / 3
        L_0x0029:
            int r2 = r2 * 2
            goto L_0x002e
        L_0x002c:
            int r2 = r2 / 2
        L_0x002e:
            java.nio.ByteBuffer r2 = r9.replaceOutputBuffer(r2)
            com.appsamurai.storyly.exoplayer2.core.audio.AudioProcessor$AudioFormat r9 = r9.inputAudioFormat
            int r9 = r9.encoding
            if (r9 == r8) goto L_0x00ae
            if (r9 == r7) goto L_0x0089
            if (r9 == r6) goto L_0x0074
            if (r9 == r5) goto L_0x005d
            if (r9 != r4) goto L_0x0057
        L_0x0040:
            if (r0 >= r1) goto L_0x00c3
            int r9 = r0 + 2
            byte r9 = r10.get(r9)
            r2.put(r9)
            int r9 = r0 + 3
            byte r9 = r10.get(r9)
            r2.put(r9)
            int r0 = r0 + 4
            goto L_0x0040
        L_0x0057:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            r9.<init>()
            throw r9
        L_0x005d:
            if (r0 >= r1) goto L_0x00c3
            int r9 = r0 + 1
            byte r9 = r10.get(r9)
            r2.put(r9)
            int r9 = r0 + 2
            byte r9 = r10.get(r9)
            r2.put(r9)
            int r0 = r0 + 3
            goto L_0x005d
        L_0x0074:
            if (r0 >= r1) goto L_0x00c3
            int r9 = r0 + 1
            byte r9 = r10.get(r9)
            r2.put(r9)
            byte r9 = r10.get(r0)
            r2.put(r9)
            int r0 = r0 + 2
            goto L_0x0074
        L_0x0089:
            if (r0 >= r1) goto L_0x00c3
            float r9 = r10.getFloat(r0)
            r3 = -1082130432(0xffffffffbf800000, float:-1.0)
            r4 = 1065353216(0x3f800000, float:1.0)
            float r9 = com.appsamurai.storyly.exoplayer2.common.util.Util.constrainValue((float) r9, (float) r3, (float) r4)
            r3 = 1191181824(0x46fffe00, float:32767.0)
            float r9 = r9 * r3
            int r9 = (int) r9
            short r9 = (short) r9
            r3 = r9 & 255(0xff, float:3.57E-43)
            byte r3 = (byte) r3
            r2.put(r3)
            int r9 = r9 >> 8
            r9 = r9 & 255(0xff, float:3.57E-43)
            byte r9 = (byte) r9
            r2.put(r9)
            int r0 = r0 + 4
            goto L_0x0089
        L_0x00ae:
            if (r0 >= r1) goto L_0x00c3
            r9 = 0
            r2.put(r9)
            byte r9 = r10.get(r0)
            r9 = r9 & 255(0xff, float:3.57E-43)
            int r9 = r9 + -128
            byte r9 = (byte) r9
            r2.put(r9)
            int r0 = r0 + 1
            goto L_0x00ae
        L_0x00c3:
            int r9 = r10.limit()
            r10.position(r9)
            r2.flip()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.audio.ResamplingAudioProcessor.queueInput(java.nio.ByteBuffer):void");
    }
}
