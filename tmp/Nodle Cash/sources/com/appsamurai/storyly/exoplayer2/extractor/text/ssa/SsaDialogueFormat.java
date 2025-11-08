package com.appsamurai.storyly.exoplayer2.extractor.text.ssa;

final class SsaDialogueFormat {
    public final int endTimeIndex;
    public final int length;
    public final int startTimeIndex;
    public final int styleIndex;
    public final int textIndex;

    private SsaDialogueFormat(int i3, int i4, int i5, int i6, int i7) {
        this.startTimeIndex = i3;
        this.endTimeIndex = i4;
        this.styleIndex = i5;
        this.textIndex = i6;
        this.length = i7;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.appsamurai.storyly.exoplayer2.extractor.text.ssa.SsaDialogueFormat fromFormatLine(java.lang.String r10) {
        /*
            r0 = 1
            java.lang.String r1 = "Format:"
            boolean r1 = r10.startsWith(r1)
            com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkArgument(r1)
            r1 = 7
            java.lang.String r10 = r10.substring(r1)
            java.lang.String r1 = ","
            java.lang.String[] r10 = android.text.TextUtils.split(r10, r1)
            r1 = -1
            r2 = 0
            r5 = r1
            r6 = r5
            r7 = r6
            r8 = r7
            r3 = r2
        L_0x001c:
            int r4 = r10.length
            if (r3 >= r4) goto L_0x006f
            r4 = r10[r3]
            java.lang.String r4 = r4.trim()
            java.lang.String r4 = com.google.common.base.Ascii.toLowerCase((java.lang.String) r4)
            r4.getClass()
            int r9 = r4.hashCode()
            switch(r9) {
                case 100571: goto L_0x0058;
                case 3556653: goto L_0x004c;
                case 109757538: goto L_0x0041;
                case 109780401: goto L_0x0035;
                default: goto L_0x0033;
            }
        L_0x0033:
            r4 = r1
            goto L_0x0062
        L_0x0035:
            java.lang.String r9 = "style"
            boolean r4 = r4.equals(r9)
            if (r4 != 0) goto L_0x003f
            goto L_0x0033
        L_0x003f:
            r4 = 3
            goto L_0x0062
        L_0x0041:
            java.lang.String r9 = "start"
            boolean r4 = r4.equals(r9)
            if (r4 != 0) goto L_0x004a
            goto L_0x0033
        L_0x004a:
            r4 = 2
            goto L_0x0062
        L_0x004c:
            java.lang.String r9 = "text"
            boolean r4 = r4.equals(r9)
            if (r4 != 0) goto L_0x0056
            goto L_0x0033
        L_0x0056:
            r4 = r0
            goto L_0x0062
        L_0x0058:
            java.lang.String r9 = "end"
            boolean r4 = r4.equals(r9)
            if (r4 != 0) goto L_0x0061
            goto L_0x0033
        L_0x0061:
            r4 = r2
        L_0x0062:
            switch(r4) {
                case 0: goto L_0x006c;
                case 1: goto L_0x006a;
                case 2: goto L_0x0068;
                case 3: goto L_0x0066;
                default: goto L_0x0065;
            }
        L_0x0065:
            goto L_0x006d
        L_0x0066:
            r7 = r3
            goto L_0x006d
        L_0x0068:
            r5 = r3
            goto L_0x006d
        L_0x006a:
            r8 = r3
            goto L_0x006d
        L_0x006c:
            r6 = r3
        L_0x006d:
            int r3 = r3 + r0
            goto L_0x001c
        L_0x006f:
            if (r5 == r1) goto L_0x007d
            if (r6 == r1) goto L_0x007d
            if (r8 == r1) goto L_0x007d
            com.appsamurai.storyly.exoplayer2.extractor.text.ssa.SsaDialogueFormat r0 = new com.appsamurai.storyly.exoplayer2.extractor.text.ssa.SsaDialogueFormat
            int r9 = r10.length
            r4 = r0
            r4.<init>(r5, r6, r7, r8, r9)
            goto L_0x007e
        L_0x007d:
            r0 = 0
        L_0x007e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.extractor.text.ssa.SsaDialogueFormat.fromFormatLine(java.lang.String):com.appsamurai.storyly.exoplayer2.extractor.text.ssa.SsaDialogueFormat");
    }
}
