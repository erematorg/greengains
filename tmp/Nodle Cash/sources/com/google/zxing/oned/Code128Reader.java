package com.google.zxing.oned;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

public final class Code128Reader extends OneDReader {
    private static final int CODE_CODE_A = 101;
    private static final int CODE_CODE_B = 100;
    private static final int CODE_CODE_C = 99;
    private static final int CODE_FNC_1 = 102;
    private static final int CODE_FNC_2 = 97;
    private static final int CODE_FNC_3 = 96;
    private static final int CODE_FNC_4_A = 101;
    private static final int CODE_FNC_4_B = 100;
    static final int[][] CODE_PATTERNS;
    private static final int CODE_SHIFT = 98;
    private static final int CODE_START_A = 103;
    private static final int CODE_START_B = 104;
    private static final int CODE_START_C = 105;
    private static final int CODE_STOP = 106;
    private static final float MAX_AVG_VARIANCE = 0.25f;
    private static final float MAX_INDIVIDUAL_VARIANCE = 0.7f;

    static {
        int[] iArr = new int[6];
        int[] iArr2 = iArr;
        // fill-array-data instruction
        iArr[0] = 2;
        iArr[1] = 1;
        iArr[2] = 2;
        iArr[3] = 2;
        iArr[4] = 2;
        iArr[5] = 2;
        int[] iArr3 = new int[6];
        int[] iArr4 = iArr3;
        // fill-array-data instruction
        iArr3[0] = 2;
        iArr3[1] = 2;
        iArr3[2] = 2;
        iArr3[3] = 1;
        iArr3[4] = 2;
        iArr3[5] = 2;
        int[] iArr5 = new int[6];
        int[] iArr6 = iArr5;
        // fill-array-data instruction
        iArr5[0] = 2;
        iArr5[1] = 2;
        iArr5[2] = 2;
        iArr5[3] = 2;
        iArr5[4] = 2;
        iArr5[5] = 1;
        int[] iArr7 = new int[6];
        int[] iArr8 = iArr7;
        // fill-array-data instruction
        iArr7[0] = 1;
        iArr7[1] = 2;
        iArr7[2] = 1;
        iArr7[3] = 2;
        iArr7[4] = 2;
        iArr7[5] = 3;
        int[] iArr9 = new int[6];
        int[] iArr10 = iArr9;
        // fill-array-data instruction
        iArr9[0] = 1;
        iArr9[1] = 2;
        iArr9[2] = 1;
        iArr9[3] = 3;
        iArr9[4] = 2;
        iArr9[5] = 2;
        int[] iArr11 = new int[6];
        int[] iArr12 = iArr11;
        // fill-array-data instruction
        iArr11[0] = 1;
        iArr11[1] = 3;
        iArr11[2] = 1;
        iArr11[3] = 2;
        iArr11[4] = 2;
        iArr11[5] = 2;
        int[] iArr13 = new int[6];
        int[] iArr14 = iArr13;
        // fill-array-data instruction
        iArr13[0] = 1;
        iArr13[1] = 2;
        iArr13[2] = 2;
        iArr13[3] = 2;
        iArr13[4] = 1;
        iArr13[5] = 3;
        int[] iArr15 = new int[6];
        int[] iArr16 = iArr15;
        // fill-array-data instruction
        iArr15[0] = 1;
        iArr15[1] = 2;
        iArr15[2] = 2;
        iArr15[3] = 3;
        iArr15[4] = 1;
        iArr15[5] = 2;
        int[] iArr17 = new int[6];
        int[] iArr18 = iArr17;
        // fill-array-data instruction
        iArr17[0] = 1;
        iArr17[1] = 3;
        iArr17[2] = 2;
        iArr17[3] = 2;
        iArr17[4] = 1;
        iArr17[5] = 2;
        int[] iArr19 = new int[6];
        int[] iArr20 = iArr19;
        // fill-array-data instruction
        iArr19[0] = 2;
        iArr19[1] = 2;
        iArr19[2] = 1;
        iArr19[3] = 2;
        iArr19[4] = 1;
        iArr19[5] = 3;
        int[] iArr21 = new int[6];
        int[] iArr22 = iArr21;
        // fill-array-data instruction
        iArr21[0] = 2;
        iArr21[1] = 2;
        iArr21[2] = 1;
        iArr21[3] = 3;
        iArr21[4] = 1;
        iArr21[5] = 2;
        int[] iArr23 = new int[6];
        int[] iArr24 = iArr23;
        // fill-array-data instruction
        iArr23[0] = 2;
        iArr23[1] = 3;
        iArr23[2] = 1;
        iArr23[3] = 2;
        iArr23[4] = 1;
        iArr23[5] = 2;
        int[] iArr25 = new int[6];
        int[] iArr26 = iArr25;
        // fill-array-data instruction
        iArr25[0] = 1;
        iArr25[1] = 1;
        iArr25[2] = 2;
        iArr25[3] = 2;
        iArr25[4] = 3;
        iArr25[5] = 2;
        int[] iArr27 = new int[6];
        int[] iArr28 = iArr27;
        // fill-array-data instruction
        iArr27[0] = 1;
        iArr27[1] = 2;
        iArr27[2] = 2;
        iArr27[3] = 1;
        iArr27[4] = 3;
        iArr27[5] = 2;
        int[] iArr29 = new int[6];
        // fill-array-data instruction
        iArr29[0] = 1;
        iArr29[1] = 2;
        iArr29[2] = 2;
        iArr29[3] = 2;
        iArr29[4] = 3;
        iArr29[5] = 1;
        int[] iArr30 = iArr2;
        int[] iArr31 = new int[6];
        int[] iArr32 = iArr31;
        // fill-array-data instruction
        iArr31[0] = 1;
        iArr31[1] = 1;
        iArr31[2] = 3;
        iArr31[3] = 2;
        iArr31[4] = 2;
        iArr31[5] = 2;
        int[] iArr33 = new int[6];
        int[] iArr34 = iArr33;
        // fill-array-data instruction
        iArr33[0] = 1;
        iArr33[1] = 2;
        iArr33[2] = 3;
        iArr33[3] = 1;
        iArr33[4] = 2;
        iArr33[5] = 2;
        int[] iArr35 = new int[6];
        int[] iArr36 = iArr35;
        // fill-array-data instruction
        iArr35[0] = 1;
        iArr35[1] = 2;
        iArr35[2] = 3;
        iArr35[3] = 2;
        iArr35[4] = 2;
        iArr35[5] = 1;
        int[] iArr37 = new int[6];
        int[] iArr38 = iArr37;
        // fill-array-data instruction
        iArr37[0] = 2;
        iArr37[1] = 2;
        iArr37[2] = 3;
        iArr37[3] = 2;
        iArr37[4] = 1;
        iArr37[5] = 1;
        int[] iArr39 = new int[6];
        int[] iArr40 = iArr39;
        // fill-array-data instruction
        iArr39[0] = 2;
        iArr39[1] = 2;
        iArr39[2] = 1;
        iArr39[3] = 1;
        iArr39[4] = 3;
        iArr39[5] = 2;
        int[] iArr41 = new int[6];
        int[] iArr42 = iArr41;
        // fill-array-data instruction
        iArr41[0] = 2;
        iArr41[1] = 2;
        iArr41[2] = 1;
        iArr41[3] = 2;
        iArr41[4] = 3;
        iArr41[5] = 1;
        int[] iArr43 = new int[6];
        int[] iArr44 = iArr43;
        // fill-array-data instruction
        iArr43[0] = 2;
        iArr43[1] = 1;
        iArr43[2] = 3;
        iArr43[3] = 2;
        iArr43[4] = 1;
        iArr43[5] = 2;
        int[] iArr45 = new int[6];
        int[] iArr46 = iArr45;
        // fill-array-data instruction
        iArr45[0] = 2;
        iArr45[1] = 2;
        iArr45[2] = 3;
        iArr45[3] = 1;
        iArr45[4] = 1;
        iArr45[5] = 2;
        int[] iArr47 = new int[6];
        int[] iArr48 = iArr47;
        // fill-array-data instruction
        iArr47[0] = 3;
        iArr47[1] = 1;
        iArr47[2] = 2;
        iArr47[3] = 1;
        iArr47[4] = 3;
        iArr47[5] = 1;
        int[] iArr49 = new int[6];
        int[] iArr50 = iArr49;
        // fill-array-data instruction
        iArr49[0] = 3;
        iArr49[1] = 1;
        iArr49[2] = 1;
        iArr49[3] = 2;
        iArr49[4] = 2;
        iArr49[5] = 2;
        int[] iArr51 = new int[6];
        int[] iArr52 = iArr51;
        // fill-array-data instruction
        iArr51[0] = 3;
        iArr51[1] = 2;
        iArr51[2] = 1;
        iArr51[3] = 1;
        iArr51[4] = 2;
        iArr51[5] = 2;
        int[] iArr53 = new int[6];
        int[] iArr54 = iArr53;
        // fill-array-data instruction
        iArr53[0] = 3;
        iArr53[1] = 2;
        iArr53[2] = 1;
        iArr53[3] = 2;
        iArr53[4] = 2;
        iArr53[5] = 1;
        int[] iArr55 = new int[6];
        int[] iArr56 = iArr55;
        // fill-array-data instruction
        iArr55[0] = 3;
        iArr55[1] = 1;
        iArr55[2] = 2;
        iArr55[3] = 2;
        iArr55[4] = 1;
        iArr55[5] = 2;
        int[] iArr57 = new int[6];
        int[] iArr58 = iArr57;
        // fill-array-data instruction
        iArr57[0] = 3;
        iArr57[1] = 2;
        iArr57[2] = 2;
        iArr57[3] = 1;
        iArr57[4] = 1;
        iArr57[5] = 2;
        int[] iArr59 = new int[6];
        int[] iArr60 = iArr59;
        // fill-array-data instruction
        iArr59[0] = 3;
        iArr59[1] = 2;
        iArr59[2] = 2;
        iArr59[3] = 2;
        iArr59[4] = 1;
        iArr59[5] = 1;
        int[] iArr61 = new int[6];
        int[] iArr62 = iArr61;
        // fill-array-data instruction
        iArr61[0] = 2;
        iArr61[1] = 1;
        iArr61[2] = 2;
        iArr61[3] = 1;
        iArr61[4] = 2;
        iArr61[5] = 3;
        int[] iArr63 = new int[6];
        int[] iArr64 = iArr63;
        // fill-array-data instruction
        iArr63[0] = 2;
        iArr63[1] = 1;
        iArr63[2] = 2;
        iArr63[3] = 3;
        iArr63[4] = 2;
        iArr63[5] = 1;
        int[] iArr65 = new int[6];
        int[] iArr66 = iArr65;
        // fill-array-data instruction
        iArr65[0] = 2;
        iArr65[1] = 3;
        iArr65[2] = 2;
        iArr65[3] = 1;
        iArr65[4] = 2;
        iArr65[5] = 1;
        int[] iArr67 = new int[6];
        int[] iArr68 = iArr67;
        // fill-array-data instruction
        iArr67[0] = 1;
        iArr67[1] = 1;
        iArr67[2] = 1;
        iArr67[3] = 3;
        iArr67[4] = 2;
        iArr67[5] = 3;
        int[] iArr69 = new int[6];
        int[] iArr70 = iArr69;
        // fill-array-data instruction
        iArr69[0] = 1;
        iArr69[1] = 3;
        iArr69[2] = 1;
        iArr69[3] = 1;
        iArr69[4] = 2;
        iArr69[5] = 3;
        int[] iArr71 = new int[6];
        int[] iArr72 = iArr71;
        // fill-array-data instruction
        iArr71[0] = 1;
        iArr71[1] = 3;
        iArr71[2] = 1;
        iArr71[3] = 3;
        iArr71[4] = 2;
        iArr71[5] = 1;
        int[] iArr73 = new int[6];
        int[] iArr74 = iArr73;
        // fill-array-data instruction
        iArr73[0] = 1;
        iArr73[1] = 1;
        iArr73[2] = 2;
        iArr73[3] = 3;
        iArr73[4] = 1;
        iArr73[5] = 3;
        int[] iArr75 = new int[6];
        int[] iArr76 = iArr75;
        // fill-array-data instruction
        iArr75[0] = 1;
        iArr75[1] = 3;
        iArr75[2] = 2;
        iArr75[3] = 1;
        iArr75[4] = 1;
        iArr75[5] = 3;
        int[] iArr77 = new int[6];
        int[] iArr78 = iArr77;
        // fill-array-data instruction
        iArr77[0] = 1;
        iArr77[1] = 3;
        iArr77[2] = 2;
        iArr77[3] = 3;
        iArr77[4] = 1;
        iArr77[5] = 1;
        int[] iArr79 = new int[6];
        int[] iArr80 = iArr79;
        // fill-array-data instruction
        iArr79[0] = 2;
        iArr79[1] = 1;
        iArr79[2] = 1;
        iArr79[3] = 3;
        iArr79[4] = 1;
        iArr79[5] = 3;
        int[] iArr81 = new int[6];
        int[] iArr82 = iArr81;
        // fill-array-data instruction
        iArr81[0] = 2;
        iArr81[1] = 3;
        iArr81[2] = 1;
        iArr81[3] = 1;
        iArr81[4] = 1;
        iArr81[5] = 3;
        int[] iArr83 = new int[6];
        int[] iArr84 = iArr83;
        // fill-array-data instruction
        iArr83[0] = 2;
        iArr83[1] = 3;
        iArr83[2] = 1;
        iArr83[3] = 3;
        iArr83[4] = 1;
        iArr83[5] = 1;
        int[] iArr85 = new int[6];
        int[] iArr86 = iArr85;
        // fill-array-data instruction
        iArr85[0] = 1;
        iArr85[1] = 1;
        iArr85[2] = 2;
        iArr85[3] = 1;
        iArr85[4] = 3;
        iArr85[5] = 3;
        int[] iArr87 = new int[6];
        int[] iArr88 = iArr87;
        // fill-array-data instruction
        iArr87[0] = 1;
        iArr87[1] = 1;
        iArr87[2] = 2;
        iArr87[3] = 3;
        iArr87[4] = 3;
        iArr87[5] = 1;
        int[] iArr89 = new int[6];
        int[] iArr90 = iArr89;
        // fill-array-data instruction
        iArr89[0] = 1;
        iArr89[1] = 3;
        iArr89[2] = 2;
        iArr89[3] = 1;
        iArr89[4] = 3;
        iArr89[5] = 1;
        int[] iArr91 = new int[6];
        int[] iArr92 = iArr91;
        // fill-array-data instruction
        iArr91[0] = 1;
        iArr91[1] = 1;
        iArr91[2] = 3;
        iArr91[3] = 1;
        iArr91[4] = 2;
        iArr91[5] = 3;
        int[] iArr93 = new int[6];
        int[] iArr94 = iArr93;
        // fill-array-data instruction
        iArr93[0] = 1;
        iArr93[1] = 1;
        iArr93[2] = 3;
        iArr93[3] = 3;
        iArr93[4] = 2;
        iArr93[5] = 1;
        int[] iArr95 = new int[6];
        int[] iArr96 = iArr95;
        // fill-array-data instruction
        iArr95[0] = 1;
        iArr95[1] = 3;
        iArr95[2] = 3;
        iArr95[3] = 1;
        iArr95[4] = 2;
        iArr95[5] = 1;
        int[] iArr97 = new int[6];
        int[] iArr98 = iArr97;
        // fill-array-data instruction
        iArr97[0] = 3;
        iArr97[1] = 1;
        iArr97[2] = 3;
        iArr97[3] = 1;
        iArr97[4] = 2;
        iArr97[5] = 1;
        int[] iArr99 = new int[6];
        int[] iArr100 = iArr99;
        // fill-array-data instruction
        iArr99[0] = 2;
        iArr99[1] = 1;
        iArr99[2] = 1;
        iArr99[3] = 3;
        iArr99[4] = 3;
        iArr99[5] = 1;
        int[] iArr101 = new int[6];
        int[] iArr102 = iArr101;
        // fill-array-data instruction
        iArr101[0] = 2;
        iArr101[1] = 3;
        iArr101[2] = 1;
        iArr101[3] = 1;
        iArr101[4] = 3;
        iArr101[5] = 1;
        int[] iArr103 = new int[6];
        int[] iArr104 = iArr103;
        // fill-array-data instruction
        iArr103[0] = 2;
        iArr103[1] = 1;
        iArr103[2] = 3;
        iArr103[3] = 1;
        iArr103[4] = 1;
        iArr103[5] = 3;
        int[] iArr105 = new int[6];
        int[] iArr106 = iArr105;
        // fill-array-data instruction
        iArr105[0] = 2;
        iArr105[1] = 1;
        iArr105[2] = 3;
        iArr105[3] = 3;
        iArr105[4] = 1;
        iArr105[5] = 1;
        int[] iArr107 = new int[6];
        int[] iArr108 = iArr107;
        // fill-array-data instruction
        iArr107[0] = 2;
        iArr107[1] = 1;
        iArr107[2] = 3;
        iArr107[3] = 1;
        iArr107[4] = 3;
        iArr107[5] = 1;
        int[] iArr109 = new int[6];
        int[] iArr110 = iArr109;
        // fill-array-data instruction
        iArr109[0] = 3;
        iArr109[1] = 1;
        iArr109[2] = 1;
        iArr109[3] = 1;
        iArr109[4] = 2;
        iArr109[5] = 3;
        int[] iArr111 = new int[6];
        int[] iArr112 = iArr111;
        // fill-array-data instruction
        iArr111[0] = 3;
        iArr111[1] = 1;
        iArr111[2] = 1;
        iArr111[3] = 3;
        iArr111[4] = 2;
        iArr111[5] = 1;
        int[] iArr113 = new int[6];
        int[] iArr114 = iArr113;
        // fill-array-data instruction
        iArr113[0] = 3;
        iArr113[1] = 3;
        iArr113[2] = 1;
        iArr113[3] = 1;
        iArr113[4] = 2;
        iArr113[5] = 1;
        int[] iArr115 = new int[6];
        int[] iArr116 = iArr115;
        // fill-array-data instruction
        iArr115[0] = 3;
        iArr115[1] = 1;
        iArr115[2] = 2;
        iArr115[3] = 1;
        iArr115[4] = 1;
        iArr115[5] = 3;
        int[] iArr117 = new int[6];
        int[] iArr118 = iArr117;
        // fill-array-data instruction
        iArr117[0] = 3;
        iArr117[1] = 1;
        iArr117[2] = 2;
        iArr117[3] = 3;
        iArr117[4] = 1;
        iArr117[5] = 1;
        int[] iArr119 = new int[6];
        int[] iArr120 = iArr119;
        // fill-array-data instruction
        iArr119[0] = 3;
        iArr119[1] = 3;
        iArr119[2] = 2;
        iArr119[3] = 1;
        iArr119[4] = 1;
        iArr119[5] = 1;
        int[] iArr121 = new int[6];
        int[] iArr122 = iArr121;
        // fill-array-data instruction
        iArr121[0] = 3;
        iArr121[1] = 1;
        iArr121[2] = 4;
        iArr121[3] = 1;
        iArr121[4] = 1;
        iArr121[5] = 1;
        int[] iArr123 = new int[6];
        int[] iArr124 = iArr123;
        // fill-array-data instruction
        iArr123[0] = 2;
        iArr123[1] = 2;
        iArr123[2] = 1;
        iArr123[3] = 4;
        iArr123[4] = 1;
        iArr123[5] = 1;
        int[] iArr125 = new int[6];
        int[] iArr126 = iArr125;
        // fill-array-data instruction
        iArr125[0] = 4;
        iArr125[1] = 3;
        iArr125[2] = 1;
        iArr125[3] = 1;
        iArr125[4] = 1;
        iArr125[5] = 1;
        int[] iArr127 = new int[6];
        int[] iArr128 = iArr127;
        // fill-array-data instruction
        iArr127[0] = 1;
        iArr127[1] = 1;
        iArr127[2] = 1;
        iArr127[3] = 2;
        iArr127[4] = 2;
        iArr127[5] = 4;
        int[] iArr129 = new int[6];
        int[] iArr130 = iArr129;
        // fill-array-data instruction
        iArr129[0] = 1;
        iArr129[1] = 1;
        iArr129[2] = 1;
        iArr129[3] = 4;
        iArr129[4] = 2;
        iArr129[5] = 2;
        int[] iArr131 = new int[6];
        int[] iArr132 = iArr131;
        // fill-array-data instruction
        iArr131[0] = 1;
        iArr131[1] = 2;
        iArr131[2] = 1;
        iArr131[3] = 1;
        iArr131[4] = 2;
        iArr131[5] = 4;
        int[] iArr133 = new int[6];
        int[] iArr134 = iArr133;
        // fill-array-data instruction
        iArr133[0] = 1;
        iArr133[1] = 2;
        iArr133[2] = 1;
        iArr133[3] = 4;
        iArr133[4] = 2;
        iArr133[5] = 1;
        int[] iArr135 = new int[6];
        int[] iArr136 = iArr135;
        // fill-array-data instruction
        iArr135[0] = 1;
        iArr135[1] = 4;
        iArr135[2] = 1;
        iArr135[3] = 1;
        iArr135[4] = 2;
        iArr135[5] = 2;
        int[] iArr137 = new int[6];
        int[] iArr138 = iArr137;
        // fill-array-data instruction
        iArr137[0] = 1;
        iArr137[1] = 4;
        iArr137[2] = 1;
        iArr137[3] = 2;
        iArr137[4] = 2;
        iArr137[5] = 1;
        int[] iArr139 = new int[6];
        int[] iArr140 = iArr139;
        // fill-array-data instruction
        iArr139[0] = 1;
        iArr139[1] = 1;
        iArr139[2] = 2;
        iArr139[3] = 2;
        iArr139[4] = 1;
        iArr139[5] = 4;
        int[] iArr141 = new int[6];
        int[] iArr142 = iArr141;
        // fill-array-data instruction
        iArr141[0] = 1;
        iArr141[1] = 1;
        iArr141[2] = 2;
        iArr141[3] = 4;
        iArr141[4] = 1;
        iArr141[5] = 2;
        int[] iArr143 = new int[6];
        int[] iArr144 = iArr143;
        // fill-array-data instruction
        iArr143[0] = 1;
        iArr143[1] = 2;
        iArr143[2] = 2;
        iArr143[3] = 1;
        iArr143[4] = 1;
        iArr143[5] = 4;
        int[] iArr145 = new int[6];
        int[] iArr146 = iArr145;
        // fill-array-data instruction
        iArr145[0] = 1;
        iArr145[1] = 2;
        iArr145[2] = 2;
        iArr145[3] = 4;
        iArr145[4] = 1;
        iArr145[5] = 1;
        int[] iArr147 = new int[6];
        int[] iArr148 = iArr147;
        // fill-array-data instruction
        iArr147[0] = 1;
        iArr147[1] = 4;
        iArr147[2] = 2;
        iArr147[3] = 1;
        iArr147[4] = 1;
        iArr147[5] = 2;
        int[] iArr149 = new int[6];
        int[] iArr150 = iArr149;
        // fill-array-data instruction
        iArr149[0] = 1;
        iArr149[1] = 4;
        iArr149[2] = 2;
        iArr149[3] = 2;
        iArr149[4] = 1;
        iArr149[5] = 1;
        int[] iArr151 = new int[6];
        int[] iArr152 = iArr151;
        // fill-array-data instruction
        iArr151[0] = 2;
        iArr151[1] = 4;
        iArr151[2] = 1;
        iArr151[3] = 2;
        iArr151[4] = 1;
        iArr151[5] = 1;
        int[] iArr153 = new int[6];
        int[] iArr154 = iArr153;
        // fill-array-data instruction
        iArr153[0] = 2;
        iArr153[1] = 2;
        iArr153[2] = 1;
        iArr153[3] = 1;
        iArr153[4] = 1;
        iArr153[5] = 4;
        int[] iArr155 = new int[6];
        int[] iArr156 = iArr155;
        // fill-array-data instruction
        iArr155[0] = 4;
        iArr155[1] = 1;
        iArr155[2] = 3;
        iArr155[3] = 1;
        iArr155[4] = 1;
        iArr155[5] = 1;
        int[] iArr157 = new int[6];
        int[] iArr158 = iArr157;
        // fill-array-data instruction
        iArr157[0] = 2;
        iArr157[1] = 4;
        iArr157[2] = 1;
        iArr157[3] = 1;
        iArr157[4] = 1;
        iArr157[5] = 2;
        int[] iArr159 = new int[6];
        int[] iArr160 = iArr159;
        // fill-array-data instruction
        iArr159[0] = 1;
        iArr159[1] = 3;
        iArr159[2] = 4;
        iArr159[3] = 1;
        iArr159[4] = 1;
        iArr159[5] = 1;
        int[] iArr161 = new int[6];
        int[] iArr162 = iArr161;
        // fill-array-data instruction
        iArr161[0] = 1;
        iArr161[1] = 1;
        iArr161[2] = 1;
        iArr161[3] = 2;
        iArr161[4] = 4;
        iArr161[5] = 2;
        int[] iArr163 = new int[6];
        int[] iArr164 = iArr163;
        // fill-array-data instruction
        iArr163[0] = 1;
        iArr163[1] = 2;
        iArr163[2] = 1;
        iArr163[3] = 1;
        iArr163[4] = 4;
        iArr163[5] = 2;
        int[] iArr165 = new int[6];
        int[] iArr166 = iArr165;
        // fill-array-data instruction
        iArr165[0] = 1;
        iArr165[1] = 2;
        iArr165[2] = 1;
        iArr165[3] = 2;
        iArr165[4] = 4;
        iArr165[5] = 1;
        int[] iArr167 = new int[6];
        int[] iArr168 = iArr167;
        // fill-array-data instruction
        iArr167[0] = 1;
        iArr167[1] = 1;
        iArr167[2] = 4;
        iArr167[3] = 2;
        iArr167[4] = 1;
        iArr167[5] = 2;
        int[] iArr169 = new int[6];
        int[] iArr170 = iArr169;
        // fill-array-data instruction
        iArr169[0] = 1;
        iArr169[1] = 2;
        iArr169[2] = 4;
        iArr169[3] = 1;
        iArr169[4] = 1;
        iArr169[5] = 2;
        int[] iArr171 = new int[6];
        int[] iArr172 = iArr171;
        // fill-array-data instruction
        iArr171[0] = 1;
        iArr171[1] = 2;
        iArr171[2] = 4;
        iArr171[3] = 2;
        iArr171[4] = 1;
        iArr171[5] = 1;
        int[] iArr173 = new int[6];
        int[] iArr174 = iArr173;
        // fill-array-data instruction
        iArr173[0] = 4;
        iArr173[1] = 1;
        iArr173[2] = 1;
        iArr173[3] = 2;
        iArr173[4] = 1;
        iArr173[5] = 2;
        int[] iArr175 = new int[6];
        int[] iArr176 = iArr175;
        // fill-array-data instruction
        iArr175[0] = 4;
        iArr175[1] = 2;
        iArr175[2] = 1;
        iArr175[3] = 1;
        iArr175[4] = 1;
        iArr175[5] = 2;
        int[] iArr177 = new int[6];
        int[] iArr178 = iArr177;
        // fill-array-data instruction
        iArr177[0] = 4;
        iArr177[1] = 2;
        iArr177[2] = 1;
        iArr177[3] = 2;
        iArr177[4] = 1;
        iArr177[5] = 1;
        int[] iArr179 = new int[6];
        int[] iArr180 = iArr179;
        // fill-array-data instruction
        iArr179[0] = 2;
        iArr179[1] = 1;
        iArr179[2] = 2;
        iArr179[3] = 1;
        iArr179[4] = 4;
        iArr179[5] = 1;
        int[] iArr181 = new int[6];
        int[] iArr182 = iArr181;
        // fill-array-data instruction
        iArr181[0] = 2;
        iArr181[1] = 1;
        iArr181[2] = 4;
        iArr181[3] = 1;
        iArr181[4] = 2;
        iArr181[5] = 1;
        int[] iArr183 = new int[6];
        int[] iArr184 = iArr183;
        // fill-array-data instruction
        iArr183[0] = 4;
        iArr183[1] = 1;
        iArr183[2] = 2;
        iArr183[3] = 1;
        iArr183[4] = 2;
        iArr183[5] = 1;
        int[] iArr185 = new int[6];
        int[] iArr186 = iArr185;
        // fill-array-data instruction
        iArr185[0] = 1;
        iArr185[1] = 1;
        iArr185[2] = 1;
        iArr185[3] = 1;
        iArr185[4] = 4;
        iArr185[5] = 3;
        int[] iArr187 = new int[6];
        int[] iArr188 = iArr187;
        // fill-array-data instruction
        iArr187[0] = 1;
        iArr187[1] = 1;
        iArr187[2] = 1;
        iArr187[3] = 3;
        iArr187[4] = 4;
        iArr187[5] = 1;
        int[] iArr189 = new int[6];
        int[] iArr190 = iArr189;
        // fill-array-data instruction
        iArr189[0] = 1;
        iArr189[1] = 3;
        iArr189[2] = 1;
        iArr189[3] = 1;
        iArr189[4] = 4;
        iArr189[5] = 1;
        int[] iArr191 = new int[6];
        int[] iArr192 = iArr191;
        // fill-array-data instruction
        iArr191[0] = 1;
        iArr191[1] = 1;
        iArr191[2] = 4;
        iArr191[3] = 1;
        iArr191[4] = 1;
        iArr191[5] = 3;
        int[] iArr193 = new int[6];
        int[] iArr194 = iArr193;
        // fill-array-data instruction
        iArr193[0] = 1;
        iArr193[1] = 1;
        iArr193[2] = 4;
        iArr193[3] = 3;
        iArr193[4] = 1;
        iArr193[5] = 1;
        int[] iArr195 = new int[6];
        int[] iArr196 = iArr195;
        // fill-array-data instruction
        iArr195[0] = 4;
        iArr195[1] = 1;
        iArr195[2] = 1;
        iArr195[3] = 1;
        iArr195[4] = 1;
        iArr195[5] = 3;
        int[] iArr197 = new int[6];
        int[] iArr198 = iArr197;
        // fill-array-data instruction
        iArr197[0] = 4;
        iArr197[1] = 1;
        iArr197[2] = 1;
        iArr197[3] = 3;
        iArr197[4] = 1;
        iArr197[5] = 1;
        int[] iArr199 = new int[6];
        int[] iArr200 = iArr199;
        // fill-array-data instruction
        iArr199[0] = 1;
        iArr199[1] = 1;
        iArr199[2] = 3;
        iArr199[3] = 1;
        iArr199[4] = 4;
        iArr199[5] = 1;
        int[] iArr201 = new int[6];
        int[] iArr202 = iArr201;
        // fill-array-data instruction
        iArr201[0] = 1;
        iArr201[1] = 1;
        iArr201[2] = 4;
        iArr201[3] = 1;
        iArr201[4] = 3;
        iArr201[5] = 1;
        int[] iArr203 = new int[6];
        int[] iArr204 = iArr203;
        // fill-array-data instruction
        iArr203[0] = 3;
        iArr203[1] = 1;
        iArr203[2] = 1;
        iArr203[3] = 1;
        iArr203[4] = 4;
        iArr203[5] = 1;
        int[] iArr205 = new int[6];
        int[] iArr206 = iArr205;
        // fill-array-data instruction
        iArr205[0] = 4;
        iArr205[1] = 1;
        iArr205[2] = 1;
        iArr205[3] = 1;
        iArr205[4] = 3;
        iArr205[5] = 1;
        int[] iArr207 = new int[6];
        int[] iArr208 = iArr207;
        // fill-array-data instruction
        iArr207[0] = 2;
        iArr207[1] = 1;
        iArr207[2] = 1;
        iArr207[3] = 4;
        iArr207[4] = 1;
        iArr207[5] = 2;
        int[] iArr209 = new int[6];
        // fill-array-data instruction
        iArr209[0] = 2;
        iArr209[1] = 1;
        iArr209[2] = 1;
        iArr209[3] = 2;
        iArr209[4] = 1;
        iArr209[5] = 4;
        int[] iArr210 = new int[6];
        int[] iArr211 = iArr210;
        // fill-array-data instruction
        iArr210[0] = 2;
        iArr210[1] = 1;
        iArr210[2] = 1;
        iArr210[3] = 2;
        iArr210[4] = 3;
        iArr210[5] = 2;
        int[] iArr212 = new int[7];
        // fill-array-data instruction
        iArr212[0] = 2;
        iArr212[1] = 3;
        iArr212[2] = 3;
        iArr212[3] = 1;
        iArr212[4] = 1;
        iArr212[5] = 1;
        iArr212[6] = 2;
        CODE_PATTERNS = new int[][]{iArr30, iArr4, iArr6, iArr8, iArr10, iArr12, iArr14, iArr16, iArr18, iArr20, iArr22, iArr24, iArr26, iArr28, iArr29, iArr32, iArr34, iArr36, iArr38, iArr40, iArr42, iArr44, iArr46, iArr48, iArr50, iArr52, iArr54, iArr56, iArr58, iArr60, iArr62, iArr64, iArr66, iArr68, iArr70, iArr72, iArr74, iArr76, iArr78, iArr80, iArr82, iArr84, iArr86, iArr88, iArr90, iArr92, iArr94, iArr96, iArr98, iArr100, iArr102, iArr104, iArr106, iArr108, iArr110, iArr112, iArr114, iArr116, iArr118, iArr120, iArr122, iArr124, iArr126, iArr128, iArr130, iArr132, iArr134, iArr136, iArr138, iArr140, iArr142, iArr144, iArr146, iArr148, iArr150, iArr152, iArr154, iArr156, iArr158, iArr160, iArr162, iArr164, iArr166, iArr168, iArr170, iArr172, iArr174, iArr176, iArr178, iArr180, iArr182, iArr184, iArr186, iArr188, iArr190, iArr192, iArr194, iArr196, iArr198, iArr200, iArr202, iArr204, iArr206, iArr208, iArr209, iArr211, iArr212};
    }

    private static int decodeCode(BitArray bitArray, int[] iArr, int i3) throws NotFoundException {
        OneDReader.recordPattern(bitArray, i3, iArr);
        float f2 = MAX_AVG_VARIANCE;
        int i4 = -1;
        int i5 = 0;
        while (true) {
            int[][] iArr2 = CODE_PATTERNS;
            if (i5 >= iArr2.length) {
                break;
            }
            float patternMatchVariance = OneDReader.patternMatchVariance(iArr, iArr2[i5], 0.7f);
            if (patternMatchVariance < f2) {
                i4 = i5;
                f2 = patternMatchVariance;
            }
            i5++;
        }
        if (i4 >= 0) {
            return i4;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int[] findStartPattern(BitArray bitArray) throws NotFoundException {
        int size = bitArray.getSize();
        int nextSet = bitArray.getNextSet(0);
        int[] iArr = new int[6];
        boolean z2 = false;
        int i3 = 0;
        int i4 = nextSet;
        while (nextSet < size) {
            if (bitArray.get(nextSet) != z2) {
                iArr[i3] = iArr[i3] + 1;
            } else {
                if (i3 == 5) {
                    int i5 = -1;
                    float f2 = MAX_AVG_VARIANCE;
                    for (int i6 = 103; i6 <= 105; i6++) {
                        float patternMatchVariance = OneDReader.patternMatchVariance(iArr, CODE_PATTERNS[i6], 0.7f);
                        if (patternMatchVariance < f2) {
                            i5 = i6;
                            f2 = patternMatchVariance;
                        }
                    }
                    if (i5 >= 0 && bitArray.isRange(Math.max(0, i4 - ((nextSet - i4) / 2)), i4, false)) {
                        return new int[]{i4, nextSet, i5};
                    }
                    i4 += iArr[0] + iArr[1];
                    int i7 = i3 - 1;
                    System.arraycopy(iArr, 2, iArr, 0, i7);
                    iArr[i7] = 0;
                    iArr[i3] = 0;
                    i3--;
                } else {
                    i3++;
                }
                iArr[i3] = 1;
                z2 = !z2;
            }
            nextSet++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x01b4, code lost:
        if (r17 == false) goto L_0x01bb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x01b6, code lost:
        if (r14 != 'e') goto L_0x01ba;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x01b8, code lost:
        r14 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x01ba, code lost:
        r14 = 'e';
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x01bb, code lost:
        r17 = r10;
        r2 = true;
        r15 = 6;
        r24 = r12;
        r12 = r8;
        r8 = r20;
        r20 = r18;
        r18 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00a4, code lost:
        r2 = 'd';
        r3 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00a7, code lost:
        r10 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00c5, code lost:
        r2 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00ef, code lost:
        r2 = false;
        r3 = false;
        r5 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00f5, code lost:
        if (r3 != false) goto L_0x00f7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00f7, code lost:
        r2 = false;
        r3 = false;
        r5 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00fb, code lost:
        r2 = false;
        r3 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00ff, code lost:
        r14 = 'd';
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0102, code lost:
        r2 = false;
        r14 = 'c';
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0108, code lost:
        r2 = false;
        r22 = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x010c, code lost:
        r2 = false;
        r16 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x010f, code lost:
        r10 = r2;
        r2 = 'd';
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x0156, code lost:
        r14 = 'e';
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0160, code lost:
        if (r3 != false) goto L_0x00f7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.zxing.Result decodeRow(int r26, com.google.zxing.common.BitArray r27, java.util.Map<com.google.zxing.DecodeHintType, ?> r28) throws com.google.zxing.NotFoundException, com.google.zxing.FormatException, com.google.zxing.ChecksumException {
        /*
            r25 = this;
            r0 = r27
            r1 = r28
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x0012
            com.google.zxing.DecodeHintType r4 = com.google.zxing.DecodeHintType.ASSUME_GS1
            boolean r1 = r1.containsKey(r4)
            if (r1 == 0) goto L_0x0012
            r1 = r2
            goto L_0x0013
        L_0x0012:
            r1 = r3
        L_0x0013:
            int[] r4 = findStartPattern(r27)
            r5 = 2
            r6 = r4[r5]
            java.util.ArrayList r7 = new java.util.ArrayList
            r8 = 20
            r7.<init>(r8)
            byte r9 = (byte) r6
            java.lang.Byte r9 = java.lang.Byte.valueOf(r9)
            r7.add(r9)
            switch(r6) {
                case 103: goto L_0x0037;
                case 104: goto L_0x0034;
                case 105: goto L_0x0031;
                default: goto L_0x002c;
            }
        L_0x002c:
            com.google.zxing.FormatException r0 = com.google.zxing.FormatException.getFormatInstance()
            throw r0
        L_0x0031:
            r12 = 99
            goto L_0x0039
        L_0x0034:
            r12 = 100
            goto L_0x0039
        L_0x0037:
            r12 = 101(0x65, float:1.42E-43)
        L_0x0039:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>(r8)
            r8 = r4[r3]
            r14 = r4[r2]
            r15 = 6
            int[] r9 = new int[r15]
            r21 = r2
            r5 = r3
            r16 = r5
            r17 = r16
            r18 = r17
            r19 = r18
            r20 = r19
            r22 = r20
            r24 = r12
            r12 = r8
            r8 = r14
            r14 = r24
        L_0x005a:
            if (r16 != 0) goto L_0x01cf
            int r12 = decodeCode(r0, r9, r8)
            byte r10 = (byte) r12
            java.lang.Byte r10 = java.lang.Byte.valueOf(r10)
            r7.add(r10)
            r10 = 106(0x6a, float:1.49E-43)
            if (r12 == r10) goto L_0x006e
            r21 = r2
        L_0x006e:
            if (r12 == r10) goto L_0x0078
            int r19 = r19 + 1
            int r20 = r19 * r12
            int r20 = r20 + r6
            r6 = r20
        L_0x0078:
            r20 = r8
            r11 = 0
        L_0x007b:
            if (r11 >= r15) goto L_0x0084
            r23 = r9[r11]
            int r20 = r20 + r23
            int r11 = r11 + 1
            goto L_0x007b
        L_0x0084:
            switch(r12) {
                case 103: goto L_0x01ca;
                case 104: goto L_0x01ca;
                case 105: goto L_0x01ca;
                default: goto L_0x0087;
            }
        L_0x0087:
            r11 = 96
            java.lang.String r15 = "]C1"
            switch(r14) {
                case 99: goto L_0x0165;
                case 100: goto L_0x0114;
                case 101: goto L_0x0091;
                default: goto L_0x008e;
            }
        L_0x008e:
            r2 = 100
            goto L_0x00a7
        L_0x0091:
            r2 = 64
            if (r12 >= r2) goto L_0x00aa
            if (r3 != r5) goto L_0x009e
            int r2 = r12 + 32
            char r2 = (char) r2
            r13.append(r2)
            goto L_0x00a4
        L_0x009e:
            int r2 = r12 + 160
            char r2 = (char) r2
            r13.append(r2)
        L_0x00a4:
            r2 = 100
            r3 = 0
        L_0x00a7:
            r10 = 0
            goto L_0x01b2
        L_0x00aa:
            if (r12 >= r11) goto L_0x00bc
            if (r3 != r5) goto L_0x00b5
            int r2 = r12 + -64
            char r2 = (char) r2
            r13.append(r2)
            goto L_0x00a4
        L_0x00b5:
            int r2 = r12 + 64
            char r2 = (char) r2
            r13.append(r2)
            goto L_0x00a4
        L_0x00bc:
            if (r12 == r10) goto L_0x00c0
            r21 = 0
        L_0x00c0:
            if (r12 == r10) goto L_0x010c
            switch(r12) {
                case 97: goto L_0x0108;
                case 98: goto L_0x0106;
                case 99: goto L_0x0102;
                case 100: goto L_0x00fe;
                case 101: goto L_0x00eb;
                case 102: goto L_0x00c7;
                default: goto L_0x00c5;
            }
        L_0x00c5:
            r2 = 0
            goto L_0x010f
        L_0x00c7:
            int r2 = r13.length()
            if (r2 != 0) goto L_0x00d0
            r22 = 1
            goto L_0x00d9
        L_0x00d0:
            int r2 = r13.length()
            r10 = 1
            if (r2 != r10) goto L_0x00d9
            r22 = 2
        L_0x00d9:
            if (r1 == 0) goto L_0x00c5
            int r2 = r13.length()
            if (r2 != 0) goto L_0x00e5
            r13.append(r15)
            goto L_0x00c5
        L_0x00e5:
            r2 = 29
            r13.append(r2)
            goto L_0x00c5
        L_0x00eb:
            if (r5 != 0) goto L_0x00f3
            if (r3 == 0) goto L_0x00f3
        L_0x00ef:
            r2 = 0
            r3 = 0
            r5 = 1
            goto L_0x010f
        L_0x00f3:
            if (r5 == 0) goto L_0x00fb
            if (r3 == 0) goto L_0x00fb
        L_0x00f7:
            r2 = 0
            r3 = 0
            r5 = 0
            goto L_0x010f
        L_0x00fb:
            r2 = 0
            r3 = 1
            goto L_0x010f
        L_0x00fe:
            r2 = 0
        L_0x00ff:
            r14 = 100
            goto L_0x010f
        L_0x0102:
            r2 = 0
            r14 = 99
            goto L_0x010f
        L_0x0106:
            r2 = 1
            goto L_0x00ff
        L_0x0108:
            r2 = 0
            r22 = 4
            goto L_0x010f
        L_0x010c:
            r2 = 0
            r16 = 1
        L_0x010f:
            r10 = r2
            r2 = 100
            goto L_0x01b2
        L_0x0114:
            if (r12 >= r11) goto L_0x0127
            if (r3 != r5) goto L_0x011f
            int r2 = r12 + 32
            char r2 = (char) r2
            r13.append(r2)
            goto L_0x00a4
        L_0x011f:
            int r2 = r12 + 160
            char r2 = (char) r2
            r13.append(r2)
            goto L_0x00a4
        L_0x0127:
            if (r12 == r10) goto L_0x012b
            r21 = 0
        L_0x012b:
            if (r12 == r10) goto L_0x010c
            switch(r12) {
                case 97: goto L_0x0108;
                case 98: goto L_0x0163;
                case 99: goto L_0x0102;
                case 100: goto L_0x0159;
                case 101: goto L_0x0155;
                case 102: goto L_0x0131;
                default: goto L_0x0130;
            }
        L_0x0130:
            goto L_0x00c5
        L_0x0131:
            int r2 = r13.length()
            if (r2 != 0) goto L_0x013a
            r22 = 1
            goto L_0x0143
        L_0x013a:
            int r2 = r13.length()
            r10 = 1
            if (r2 != r10) goto L_0x0143
            r22 = 2
        L_0x0143:
            if (r1 == 0) goto L_0x00c5
            int r2 = r13.length()
            if (r2 != 0) goto L_0x014f
            r13.append(r15)
            goto L_0x0130
        L_0x014f:
            r2 = 29
            r13.append(r2)
            goto L_0x0130
        L_0x0155:
            r2 = 0
        L_0x0156:
            r14 = 101(0x65, float:1.42E-43)
            goto L_0x010f
        L_0x0159:
            if (r5 != 0) goto L_0x015e
            if (r3 == 0) goto L_0x015e
            goto L_0x00ef
        L_0x015e:
            if (r5 == 0) goto L_0x00fb
            if (r3 == 0) goto L_0x00fb
            goto L_0x00f7
        L_0x0163:
            r2 = 1
            goto L_0x0156
        L_0x0165:
            r2 = 100
            if (r12 >= r2) goto L_0x0177
            r10 = 10
            if (r12 >= r10) goto L_0x0172
            r10 = 48
            r13.append(r10)
        L_0x0172:
            r13.append(r12)
            goto L_0x00a7
        L_0x0177:
            if (r12 == r10) goto L_0x017b
            r21 = 0
        L_0x017b:
            if (r12 == r10) goto L_0x01af
            switch(r12) {
                case 100: goto L_0x01ac;
                case 101: goto L_0x01a8;
                case 102: goto L_0x0182;
                default: goto L_0x0180;
            }
        L_0x0180:
            goto L_0x00a7
        L_0x0182:
            int r10 = r13.length()
            if (r10 != 0) goto L_0x018b
            r22 = 1
            goto L_0x0194
        L_0x018b:
            int r10 = r13.length()
            r11 = 1
            if (r10 != r11) goto L_0x0194
            r22 = 2
        L_0x0194:
            if (r1 == 0) goto L_0x00a7
            int r10 = r13.length()
            if (r10 != 0) goto L_0x01a1
            r13.append(r15)
            goto L_0x00a7
        L_0x01a1:
            r10 = 29
            r13.append(r10)
            goto L_0x00a7
        L_0x01a8:
            r10 = 0
            r14 = 101(0x65, float:1.42E-43)
            goto L_0x01b2
        L_0x01ac:
            r14 = r2
            goto L_0x00a7
        L_0x01af:
            r10 = 0
            r16 = 1
        L_0x01b2:
            r11 = 101(0x65, float:1.42E-43)
            if (r17 == 0) goto L_0x01bb
            if (r14 != r11) goto L_0x01ba
            r14 = r2
            goto L_0x01bb
        L_0x01ba:
            r14 = r11
        L_0x01bb:
            r17 = r10
            r2 = 1
            r15 = 6
            r24 = r12
            r12 = r8
            r8 = r20
            r20 = r18
            r18 = r24
            goto L_0x005a
        L_0x01ca:
            com.google.zxing.FormatException r0 = com.google.zxing.FormatException.getFormatInstance()
            throw r0
        L_0x01cf:
            int r1 = r8 - r12
            int r2 = r0.getNextUnset(r8)
            int r3 = r27.getSize()
            int r5 = r2 - r12
            r8 = 2
            int r5 = r5 / r8
            int r5 = r5 + r2
            int r3 = java.lang.Math.min(r3, r5)
            r5 = 0
            boolean r0 = r0.isRange(r2, r3, r5)
            if (r0 == 0) goto L_0x026f
            r3 = r20
            int r19 = r19 * r3
            int r6 = r6 - r19
            int r6 = r6 % 103
            if (r6 != r3) goto L_0x026a
            int r0 = r13.length()
            if (r0 == 0) goto L_0x0265
            if (r0 <= 0) goto L_0x0206
            if (r21 == 0) goto L_0x0206
            r2 = 99
            if (r14 != r2) goto L_0x0208
            int r2 = r0 + -2
            r13.delete(r2, r0)
        L_0x0206:
            r0 = 1
            goto L_0x020e
        L_0x0208:
            int r2 = r0 + -1
            r13.delete(r2, r0)
            goto L_0x0206
        L_0x020e:
            r0 = r4[r0]
            r2 = 0
            r3 = r4[r2]
            int r0 = r0 + r3
            float r0 = (float) r0
            r3 = 1073741824(0x40000000, float:2.0)
            float r0 = r0 / r3
            float r4 = (float) r12
            float r1 = (float) r1
            float r1 = r1 / r3
            float r1 = r1 + r4
            int r3 = r7.size()
            byte[] r4 = new byte[r3]
        L_0x0222:
            if (r2 >= r3) goto L_0x0233
            java.lang.Object r5 = r7.get(r2)
            java.lang.Byte r5 = (java.lang.Byte) r5
            byte r5 = r5.byteValue()
            r4[r2] = r5
            int r2 = r2 + 1
            goto L_0x0222
        L_0x0233:
            com.google.zxing.Result r2 = new com.google.zxing.Result
            java.lang.String r3 = r13.toString()
            com.google.zxing.ResultPoint r5 = new com.google.zxing.ResultPoint
            r6 = r26
            float r6 = (float) r6
            r5.<init>(r0, r6)
            com.google.zxing.ResultPoint r0 = new com.google.zxing.ResultPoint
            r0.<init>(r1, r6)
            com.google.zxing.ResultPoint[] r0 = new com.google.zxing.ResultPoint[]{r5, r0}
            com.google.zxing.BarcodeFormat r1 = com.google.zxing.BarcodeFormat.CODE_128
            r2.<init>(r3, r4, r0, r1)
            com.google.zxing.ResultMetadataType r0 = com.google.zxing.ResultMetadataType.SYMBOLOGY_IDENTIFIER
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "]C"
            r1.<init>(r3)
            r3 = r22
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            r2.putMetadata(r0, r1)
            return r2
        L_0x0265:
            com.google.zxing.NotFoundException r0 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r0
        L_0x026a:
            com.google.zxing.ChecksumException r0 = com.google.zxing.ChecksumException.getChecksumInstance()
            throw r0
        L_0x026f:
            com.google.zxing.NotFoundException r0 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.Code128Reader.decodeRow(int, com.google.zxing.common.BitArray, java.util.Map):com.google.zxing.Result");
    }
}
