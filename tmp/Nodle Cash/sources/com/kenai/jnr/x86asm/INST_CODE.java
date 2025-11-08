package com.kenai.jnr.x86asm;

@Deprecated
public enum INST_CODE {
    INST_ADC,
    INST_ADD,
    INST_ADDPD,
    INST_ADDPS,
    INST_ADDSD,
    INST_ADDSS,
    INST_ADDSUBPD,
    INST_ADDSUBPS,
    INST_AMD_PREFETCH,
    INST_AMD_PREFETCHW,
    INST_AND,
    INST_ANDNPD,
    INST_ANDNPS,
    INST_ANDPD,
    INST_ANDPS,
    INST_BLENDPD,
    INST_BLENDPS,
    INST_BLENDVPD,
    INST_BLENDVPS,
    INST_BSF,
    INST_BSR,
    INST_BSWAP,
    INST_BT,
    INST_BTC,
    INST_BTR,
    INST_BTS,
    INST_CALL,
    INST_CBW,
    INST_CDQE,
    INST_CLC,
    INST_CLD,
    INST_CLFLUSH,
    INST_CMC,
    INST_CMOVA,
    INST_CMOVAE,
    INST_CMOVB,
    INST_CMOVBE,
    INST_CMOVC,
    INST_CMOVE,
    INST_CMOVG,
    INST_CMOVGE,
    INST_CMOVL,
    INST_CMOVLE,
    INST_CMOVNA,
    INST_CMOVNAE,
    INST_CMOVNB,
    INST_CMOVNBE,
    INST_CMOVNC,
    INST_CMOVNE,
    INST_CMOVNG,
    INST_CMOVNGE,
    INST_CMOVNL,
    INST_CMOVNLE,
    INST_CMOVNO,
    INST_CMOVNP,
    INST_CMOVNS,
    INST_CMOVNZ,
    INST_CMOVO,
    INST_CMOVP,
    INST_CMOVPE,
    INST_CMOVPO,
    INST_CMOVS,
    INST_CMOVZ,
    INST_CMP,
    INST_CMPPD,
    INST_CMPPS,
    INST_CMPSD,
    INST_CMPSS,
    INST_CMPXCHG,
    INST_CMPXCHG16B,
    INST_CMPXCHG8B,
    INST_COMISD,
    INST_COMISS,
    INST_CPUID,
    INST_CRC32,
    INST_CVTDQ2PD,
    INST_CVTDQ2PS,
    INST_CVTPD2DQ,
    INST_CVTPD2PI,
    INST_CVTPD2PS,
    INST_CVTPI2PD,
    INST_CVTPI2PS,
    INST_CVTPS2DQ,
    INST_CVTPS2PD,
    INST_CVTPS2PI,
    INST_CVTSD2SI,
    INST_CVTSD2SS,
    INST_CVTSI2SD,
    INST_CVTSI2SS,
    INST_CVTSS2SD,
    INST_CVTSS2SI,
    INST_CVTTPD2DQ,
    INST_CVTTPD2PI,
    INST_CVTTPS2DQ,
    INST_CVTTPS2PI,
    INST_CVTTSD2SI,
    INST_CVTTSS2SI,
    INST_CWDE,
    INST_DAA,
    INST_DAS,
    INST_DEC,
    INST_DIV,
    INST_DIVPD,
    INST_DIVPS,
    INST_DIVSD,
    INST_DIVSS,
    INST_DPPD,
    INST_DPPS,
    INST_EMMS,
    INST_ENTER,
    INST_EXTRACTPS,
    INST_F2XM1,
    INST_FABS,
    INST_FADD,
    INST_FADDP,
    INST_FBLD,
    INST_FBSTP,
    INST_FCHS,
    INST_FCLEX,
    INST_FCMOVB,
    INST_FCMOVBE,
    INST_FCMOVE,
    INST_FCMOVNB,
    INST_FCMOVNBE,
    INST_FCMOVNE,
    INST_FCMOVNU,
    INST_FCMOVU,
    INST_FCOM,
    INST_FCOMI,
    INST_FCOMIP,
    INST_FCOMP,
    INST_FCOMPP,
    INST_FCOS,
    INST_FDECSTP,
    INST_FDIV,
    INST_FDIVP,
    INST_FDIVR,
    INST_FDIVRP,
    INST_FEMMS,
    INST_FFREE,
    INST_FIADD,
    INST_FICOM,
    INST_FICOMP,
    INST_FIDIV,
    INST_FIDIVR,
    INST_FILD,
    INST_FIMUL,
    INST_FINCSTP,
    INST_FINIT,
    INST_FIST,
    INST_FISTP,
    INST_FISTTP,
    INST_FISUB,
    INST_FISUBR,
    INST_FLD,
    INST_FLD1,
    INST_FLDCW,
    INST_FLDENV,
    INST_FLDL2E,
    INST_FLDL2T,
    INST_FLDLG2,
    INST_FLDLN2,
    INST_FLDPI,
    INST_FLDZ,
    INST_FMUL,
    INST_FMULP,
    INST_FNCLEX,
    INST_FNINIT,
    INST_FNOP,
    INST_FNSAVE,
    INST_FNSTCW,
    INST_FNSTENV,
    INST_FNSTSW,
    INST_FPATAN,
    INST_FPREM,
    INST_FPREM1,
    INST_FPTAN,
    INST_FRNDINT,
    INST_FRSTOR,
    INST_FSAVE,
    INST_FSCALE,
    INST_FSIN,
    INST_FSINCOS,
    INST_FSQRT,
    INST_FST,
    INST_FSTCW,
    INST_FSTENV,
    INST_FSTP,
    INST_FSTSW,
    INST_FSUB,
    INST_FSUBP,
    INST_FSUBR,
    INST_FSUBRP,
    INST_FTST,
    INST_FUCOM,
    INST_FUCOMI,
    INST_FUCOMIP,
    INST_FUCOMP,
    INST_FUCOMPP,
    INST_FWAIT,
    INST_FXAM,
    INST_FXCH,
    INST_FXRSTOR,
    INST_FXSAVE,
    INST_FXTRACT,
    INST_FYL2X,
    INST_FYL2XP1,
    INST_HADDPD,
    INST_HADDPS,
    INST_HSUBPD,
    INST_HSUBPS,
    INST_IDIV,
    INST_IMUL,
    INST_INC,
    INST_INT3,
    INST_JA,
    INST_JAE,
    INST_JB,
    INST_JBE,
    INST_JC,
    INST_JE,
    INST_JG,
    INST_JGE,
    INST_JL,
    INST_JLE,
    INST_JNA,
    INST_JNAE,
    INST_JNB,
    INST_JNBE,
    INST_JNC,
    INST_JNE,
    INST_JNG,
    INST_JNGE,
    INST_JNL,
    INST_JNLE,
    INST_JNO,
    INST_JNP,
    INST_JNS,
    INST_JNZ,
    INST_JO,
    INST_JP,
    INST_JPE,
    INST_JPO,
    INST_JS,
    INST_JZ,
    INST_JMP,
    INST_JA_SHORT,
    INST_JAE_SHORT,
    INST_JB_SHORT,
    INST_JBE_SHORT,
    INST_JC_SHORT,
    INST_JE_SHORT,
    INST_JG_SHORT,
    INST_JGE_SHORT,
    INST_JL_SHORT,
    INST_JLE_SHORT,
    INST_JNA_SHORT,
    INST_JNAE_SHORT,
    INST_JNB_SHORT,
    INST_JNBE_SHORT,
    INST_JNC_SHORT,
    INST_JNE_SHORT,
    INST_JNG_SHORT,
    INST_JNGE_SHORT,
    INST_JNL_SHORT,
    INST_JNLE_SHORT,
    INST_JNO_SHORT,
    INST_JNP_SHORT,
    INST_JNS_SHORT,
    INST_JNZ_SHORT,
    INST_JO_SHORT,
    INST_JP_SHORT,
    INST_JPE_SHORT,
    INST_JPO_SHORT,
    INST_JS_SHORT,
    INST_JZ_SHORT,
    INST_JMP_SHORT,
    INST_LDDQU,
    INST_LDMXCSR,
    INST_LEA,
    INST_LEAVE,
    INST_LFENCE,
    INST_LOCK,
    INST_MASKMOVDQU,
    INST_MASKMOVQ,
    INST_MAXPD,
    INST_MAXPS,
    INST_MAXSD,
    INST_MAXSS,
    INST_MFENCE,
    INST_MINPD,
    INST_MINPS,
    INST_MINSD,
    INST_MINSS,
    INST_MONITOR,
    INST_MOV,
    INST_MOVAPD,
    INST_MOVAPS,
    INST_MOVBE,
    INST_MOVD,
    INST_MOVDDUP,
    INST_MOVDQ2Q,
    INST_MOVDQA,
    INST_MOVDQU,
    INST_MOVHLPS,
    INST_MOVHPD,
    INST_MOVHPS,
    INST_MOVLHPS,
    INST_MOVLPD,
    INST_MOVLPS,
    INST_MOVMSKPD,
    INST_MOVMSKPS,
    INST_MOVNTDQ,
    INST_MOVNTDQA,
    INST_MOVNTI,
    INST_MOVNTPD,
    INST_MOVNTPS,
    INST_MOVNTQ,
    INST_MOVQ,
    INST_MOVQ2DQ,
    INST_MOVSD,
    INST_MOVSHDUP,
    INST_MOVSLDUP,
    INST_MOVSS,
    INST_MOVSX,
    INST_MOVSXD,
    INST_MOVUPD,
    INST_MOVUPS,
    INST_MOVZX,
    INST_MOV_PTR,
    INST_MPSADBW,
    INST_MUL,
    INST_MULPD,
    INST_MULPS,
    INST_MULSD,
    INST_MULSS,
    INST_MWAIT,
    INST_NEG,
    INST_NOP,
    INST_NOT,
    INST_OR,
    INST_ORPD,
    INST_ORPS,
    INST_PABSB,
    INST_PABSD,
    INST_PABSW,
    INST_PACKSSDW,
    INST_PACKSSWB,
    INST_PACKUSDW,
    INST_PACKUSWB,
    INST_PADDB,
    INST_PADDD,
    INST_PADDQ,
    INST_PADDSB,
    INST_PADDSW,
    INST_PADDUSB,
    INST_PADDUSW,
    INST_PADDW,
    INST_PALIGNR,
    INST_PAND,
    INST_PANDN,
    INST_PAUSE,
    INST_PAVGB,
    INST_PAVGW,
    INST_PBLENDVB,
    INST_PBLENDW,
    INST_PCMPEQB,
    INST_PCMPEQD,
    INST_PCMPEQQ,
    INST_PCMPEQW,
    INST_PCMPESTRI,
    INST_PCMPESTRM,
    INST_PCMPGTB,
    INST_PCMPGTD,
    INST_PCMPGTQ,
    INST_PCMPGTW,
    INST_PCMPISTRI,
    INST_PCMPISTRM,
    INST_PEXTRB,
    INST_PEXTRD,
    INST_PEXTRQ,
    INST_PEXTRW,
    INST_PF2ID,
    INST_PF2IW,
    INST_PFACC,
    INST_PFADD,
    INST_PFCMPEQ,
    INST_PFCMPGE,
    INST_PFCMPGT,
    INST_PFMAX,
    INST_PFMIN,
    INST_PFMUL,
    INST_PFNACC,
    INST_PFPNACC,
    INST_PFRCP,
    INST_PFRCPIT1,
    INST_PFRCPIT2,
    INST_PFRSQIT1,
    INST_PFRSQRT,
    INST_PFSUB,
    INST_PFSUBR,
    INST_PHADDD,
    INST_PHADDSW,
    INST_PHADDW,
    INST_PHMINPOSUW,
    INST_PHSUBD,
    INST_PHSUBSW,
    INST_PHSUBW,
    INST_PI2FD,
    INST_PI2FW,
    INST_PINSRB,
    INST_PINSRD,
    INST_PINSRQ,
    INST_PINSRW,
    INST_PMADDUBSW,
    INST_PMADDWD,
    INST_PMAXSB,
    INST_PMAXSD,
    INST_PMAXSW,
    INST_PMAXUB,
    INST_PMAXUD,
    INST_PMAXUW,
    INST_PMINSB,
    INST_PMINSD,
    INST_PMINSW,
    INST_PMINUB,
    INST_PMINUD,
    INST_PMINUW,
    INST_PMOVMSKB,
    INST_PMOVSXBD,
    INST_PMOVSXBQ,
    INST_PMOVSXBW,
    INST_PMOVSXDQ,
    INST_PMOVSXWD,
    INST_PMOVSXWQ,
    INST_PMOVZXBD,
    INST_PMOVZXBQ,
    INST_PMOVZXBW,
    INST_PMOVZXDQ,
    INST_PMOVZXWD,
    INST_PMOVZXWQ,
    INST_PMULDQ,
    INST_PMULHRSW,
    INST_PMULHUW,
    INST_PMULHW,
    INST_PMULLD,
    INST_PMULLW,
    INST_PMULUDQ,
    INST_POP,
    INST_POPAD,
    INST_POPCNT,
    INST_POPFD,
    INST_POPFQ,
    INST_POR,
    INST_PREFETCH,
    INST_PSADBW,
    INST_PSHUFB,
    INST_PSHUFD,
    INST_PSHUFW,
    INST_PSHUFHW,
    INST_PSHUFLW,
    INST_PSIGNB,
    INST_PSIGND,
    INST_PSIGNW,
    INST_PSLLD,
    INST_PSLLDQ,
    INST_PSLLQ,
    INST_PSLLW,
    INST_PSRAD,
    INST_PSRAW,
    INST_PSRLD,
    INST_PSRLDQ,
    INST_PSRLQ,
    INST_PSRLW,
    INST_PSUBB,
    INST_PSUBD,
    INST_PSUBQ,
    INST_PSUBSB,
    INST_PSUBSW,
    INST_PSUBUSB,
    INST_PSUBUSW,
    INST_PSUBW,
    INST_PSWAPD,
    INST_PTEST,
    INST_PUNPCKHBW,
    INST_PUNPCKHDQ,
    INST_PUNPCKHQDQ,
    INST_PUNPCKHWD,
    INST_PUNPCKLBW,
    INST_PUNPCKLDQ,
    INST_PUNPCKLQDQ,
    INST_PUNPCKLWD,
    INST_PUSH,
    INST_PUSHAD,
    INST_PUSHFD,
    INST_PUSHFQ,
    INST_PXOR,
    INST_RCL,
    INST_RCPPS,
    INST_RCPSS,
    INST_RCR,
    INST_RDTSC,
    INST_RDTSCP,
    INST_RET,
    INST_ROL,
    INST_ROR,
    INST_ROUNDPD,
    INST_ROUNDPS,
    INST_ROUNDSD,
    INST_ROUNDSS,
    INST_RSQRTPS,
    INST_RSQRTSS,
    INST_SAHF,
    INST_SAL,
    INST_SAR,
    INST_SBB,
    INST_SETA,
    INST_SETAE,
    INST_SETB,
    INST_SETBE,
    INST_SETC,
    INST_SETE,
    INST_SETG,
    INST_SETGE,
    INST_SETL,
    INST_SETLE,
    INST_SETNA,
    INST_SETNAE,
    INST_SETNB,
    INST_SETNBE,
    INST_SETNC,
    INST_SETNE,
    INST_SETNG,
    INST_SETNGE,
    INST_SETNL,
    INST_SETNLE,
    INST_SETNO,
    INST_SETNP,
    INST_SETNS,
    INST_SETNZ,
    INST_SETO,
    INST_SETP,
    INST_SETPE,
    INST_SETPO,
    INST_SETS,
    INST_SETZ,
    INST_SFENCE,
    INST_SHL,
    INST_SHLD,
    INST_SHR,
    INST_SHRD,
    INST_SHUFPS,
    INST_SQRTPD,
    INST_SQRTPS,
    INST_SQRTSD,
    INST_SQRTSS,
    INST_STC,
    INST_STD,
    INST_STMXCSR,
    INST_SUB,
    INST_SUBPD,
    INST_SUBPS,
    INST_SUBSD,
    INST_SUBSS,
    INST_TEST,
    INST_UCOMISD,
    INST_UCOMISS,
    INST_UD2,
    INST_UNPCKHPD,
    INST_UNPCKHPS,
    INST_UNPCKLPD,
    INST_UNPCKLPS,
    INST_XADD,
    INST_XCHG,
    INST_XOR,
    INST_XORPD,
    INST_XORPS;
    
    public static final INST_CODE INST_J = null;
    public static final INST_CODE INST_J_SHORT = null;

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v566, resolved type: com.kenai.jnr.x86asm.INST_CODE[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    static {
        /*
            com.kenai.jnr.x86asm.INST_CODE r0 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r1 = "INST_ADC"
            r2 = 0
            r0.<init>(r1, r2)
            INST_ADC = r0
            com.kenai.jnr.x86asm.INST_CODE r1 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r3 = "INST_ADD"
            r4 = 1
            r1.<init>(r3, r4)
            INST_ADD = r1
            com.kenai.jnr.x86asm.INST_CODE r3 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r5 = "INST_ADDPD"
            r6 = 2
            r3.<init>(r5, r6)
            INST_ADDPD = r3
            com.kenai.jnr.x86asm.INST_CODE r5 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r7 = "INST_ADDPS"
            r8 = 3
            r5.<init>(r7, r8)
            INST_ADDPS = r5
            com.kenai.jnr.x86asm.INST_CODE r7 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r9 = "INST_ADDSD"
            r10 = 4
            r7.<init>(r9, r10)
            INST_ADDSD = r7
            com.kenai.jnr.x86asm.INST_CODE r9 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r11 = "INST_ADDSS"
            r12 = 5
            r9.<init>(r11, r12)
            INST_ADDSS = r9
            com.kenai.jnr.x86asm.INST_CODE r11 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r13 = "INST_ADDSUBPD"
            r14 = 6
            r11.<init>(r13, r14)
            INST_ADDSUBPD = r11
            com.kenai.jnr.x86asm.INST_CODE r13 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r15 = "INST_ADDSUBPS"
            r14 = 7
            r13.<init>(r15, r14)
            INST_ADDSUBPS = r13
            com.kenai.jnr.x86asm.INST_CODE r15 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r14 = "INST_AMD_PREFETCH"
            r12 = 8
            r15.<init>(r14, r12)
            INST_AMD_PREFETCH = r15
            com.kenai.jnr.x86asm.INST_CODE r14 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r12 = "INST_AMD_PREFETCHW"
            r10 = 9
            r14.<init>(r12, r10)
            INST_AMD_PREFETCHW = r14
            com.kenai.jnr.x86asm.INST_CODE r12 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r10 = "INST_AND"
            r8 = 10
            r12.<init>(r10, r8)
            INST_AND = r12
            com.kenai.jnr.x86asm.INST_CODE r10 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r8 = "INST_ANDNPD"
            r6 = 11
            r10.<init>(r8, r6)
            INST_ANDNPD = r10
            com.kenai.jnr.x86asm.INST_CODE r8 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_ANDNPS"
            r4 = 12
            r8.<init>(r6, r4)
            INST_ANDNPS = r8
            com.kenai.jnr.x86asm.INST_CODE r6 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r4 = "INST_ANDPD"
            r2 = 13
            r6.<init>(r4, r2)
            INST_ANDPD = r6
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r2 = "INST_ANDPS"
            r16 = r6
            r6 = 14
            r4.<init>(r2, r6)
            INST_ANDPS = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_BLENDPD"
            r17 = r4
            r4 = 15
            r2.<init>(r6, r4)
            INST_BLENDPD = r2
            com.kenai.jnr.x86asm.INST_CODE r6 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r4 = "INST_BLENDPS"
            r18 = r2
            r2 = 16
            r6.<init>(r4, r2)
            INST_BLENDPS = r6
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r2 = "INST_BLENDVPD"
            r19 = r6
            r6 = 17
            r4.<init>(r2, r6)
            INST_BLENDVPD = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_BLENDVPS"
            r20 = r4
            r4 = 18
            r2.<init>(r6, r4)
            INST_BLENDVPS = r2
            com.kenai.jnr.x86asm.INST_CODE r6 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r4 = "INST_BSF"
            r21 = r2
            r2 = 19
            r6.<init>(r4, r2)
            INST_BSF = r6
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r2 = "INST_BSR"
            r22 = r6
            r6 = 20
            r4.<init>(r2, r6)
            INST_BSR = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_BSWAP"
            r23 = r4
            r4 = 21
            r2.<init>(r6, r4)
            INST_BSWAP = r2
            com.kenai.jnr.x86asm.INST_CODE r6 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r4 = "INST_BT"
            r24 = r2
            r2 = 22
            r6.<init>(r4, r2)
            INST_BT = r6
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r4 = "INST_BTC"
            r25 = r6
            r6 = 23
            r2.<init>(r4, r6)
            INST_BTC = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_BTR"
            r26 = r2
            r2 = 24
            r4.<init>(r6, r2)
            INST_BTR = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_BTS"
            r27 = r4
            r4 = 25
            r2.<init>(r6, r4)
            INST_BTS = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CALL"
            r28 = r2
            r2 = 26
            r4.<init>(r6, r2)
            INST_CALL = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CBW"
            r29 = r4
            r4 = 27
            r2.<init>(r6, r4)
            INST_CBW = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CDQE"
            r30 = r2
            r2 = 28
            r4.<init>(r6, r2)
            INST_CDQE = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CLC"
            r31 = r4
            r4 = 29
            r2.<init>(r6, r4)
            INST_CLC = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CLD"
            r32 = r2
            r2 = 30
            r4.<init>(r6, r2)
            INST_CLD = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CLFLUSH"
            r33 = r4
            r4 = 31
            r2.<init>(r6, r4)
            INST_CLFLUSH = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CMC"
            r34 = r2
            r2 = 32
            r4.<init>(r6, r2)
            INST_CMC = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CMOVA"
            r35 = r4
            r4 = 33
            r2.<init>(r6, r4)
            INST_CMOVA = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CMOVAE"
            r36 = r2
            r2 = 34
            r4.<init>(r6, r2)
            INST_CMOVAE = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CMOVB"
            r37 = r4
            r4 = 35
            r2.<init>(r6, r4)
            INST_CMOVB = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CMOVBE"
            r38 = r2
            r2 = 36
            r4.<init>(r6, r2)
            INST_CMOVBE = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CMOVC"
            r39 = r4
            r4 = 37
            r2.<init>(r6, r4)
            INST_CMOVC = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CMOVE"
            r40 = r2
            r2 = 38
            r4.<init>(r6, r2)
            INST_CMOVE = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CMOVG"
            r41 = r4
            r4 = 39
            r2.<init>(r6, r4)
            INST_CMOVG = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CMOVGE"
            r42 = r2
            r2 = 40
            r4.<init>(r6, r2)
            INST_CMOVGE = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CMOVL"
            r43 = r4
            r4 = 41
            r2.<init>(r6, r4)
            INST_CMOVL = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CMOVLE"
            r44 = r2
            r2 = 42
            r4.<init>(r6, r2)
            INST_CMOVLE = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CMOVNA"
            r45 = r4
            r4 = 43
            r2.<init>(r6, r4)
            INST_CMOVNA = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CMOVNAE"
            r46 = r2
            r2 = 44
            r4.<init>(r6, r2)
            INST_CMOVNAE = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CMOVNB"
            r47 = r4
            r4 = 45
            r2.<init>(r6, r4)
            INST_CMOVNB = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CMOVNBE"
            r48 = r2
            r2 = 46
            r4.<init>(r6, r2)
            INST_CMOVNBE = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CMOVNC"
            r49 = r4
            r4 = 47
            r2.<init>(r6, r4)
            INST_CMOVNC = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CMOVNE"
            r50 = r2
            r2 = 48
            r4.<init>(r6, r2)
            INST_CMOVNE = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CMOVNG"
            r51 = r4
            r4 = 49
            r2.<init>(r6, r4)
            INST_CMOVNG = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CMOVNGE"
            r52 = r2
            r2 = 50
            r4.<init>(r6, r2)
            INST_CMOVNGE = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CMOVNL"
            r53 = r4
            r4 = 51
            r2.<init>(r6, r4)
            INST_CMOVNL = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CMOVNLE"
            r54 = r2
            r2 = 52
            r4.<init>(r6, r2)
            INST_CMOVNLE = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CMOVNO"
            r55 = r4
            r4 = 53
            r2.<init>(r6, r4)
            INST_CMOVNO = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CMOVNP"
            r56 = r2
            r2 = 54
            r4.<init>(r6, r2)
            INST_CMOVNP = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CMOVNS"
            r57 = r4
            r4 = 55
            r2.<init>(r6, r4)
            INST_CMOVNS = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CMOVNZ"
            r58 = r2
            r2 = 56
            r4.<init>(r6, r2)
            INST_CMOVNZ = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CMOVO"
            r59 = r4
            r4 = 57
            r2.<init>(r6, r4)
            INST_CMOVO = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CMOVP"
            r60 = r2
            r2 = 58
            r4.<init>(r6, r2)
            INST_CMOVP = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CMOVPE"
            r61 = r4
            r4 = 59
            r2.<init>(r6, r4)
            INST_CMOVPE = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CMOVPO"
            r62 = r2
            r2 = 60
            r4.<init>(r6, r2)
            INST_CMOVPO = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CMOVS"
            r63 = r4
            r4 = 61
            r2.<init>(r6, r4)
            INST_CMOVS = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CMOVZ"
            r64 = r2
            r2 = 62
            r4.<init>(r6, r2)
            INST_CMOVZ = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CMP"
            r65 = r4
            r4 = 63
            r2.<init>(r6, r4)
            INST_CMP = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CMPPD"
            r66 = r2
            r2 = 64
            r4.<init>(r6, r2)
            INST_CMPPD = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CMPPS"
            r67 = r4
            r4 = 65
            r2.<init>(r6, r4)
            INST_CMPPS = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CMPSD"
            r68 = r2
            r2 = 66
            r4.<init>(r6, r2)
            INST_CMPSD = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CMPSS"
            r69 = r4
            r4 = 67
            r2.<init>(r6, r4)
            INST_CMPSS = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CMPXCHG"
            r70 = r2
            r2 = 68
            r4.<init>(r6, r2)
            INST_CMPXCHG = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CMPXCHG16B"
            r71 = r4
            r4 = 69
            r2.<init>(r6, r4)
            INST_CMPXCHG16B = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CMPXCHG8B"
            r72 = r2
            r2 = 70
            r4.<init>(r6, r2)
            INST_CMPXCHG8B = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_COMISD"
            r73 = r4
            r4 = 71
            r2.<init>(r6, r4)
            INST_COMISD = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_COMISS"
            r74 = r2
            r2 = 72
            r4.<init>(r6, r2)
            INST_COMISS = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CPUID"
            r75 = r4
            r4 = 73
            r2.<init>(r6, r4)
            INST_CPUID = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CRC32"
            r76 = r2
            r2 = 74
            r4.<init>(r6, r2)
            INST_CRC32 = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CVTDQ2PD"
            r77 = r4
            r4 = 75
            r2.<init>(r6, r4)
            INST_CVTDQ2PD = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CVTDQ2PS"
            r78 = r2
            r2 = 76
            r4.<init>(r6, r2)
            INST_CVTDQ2PS = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CVTPD2DQ"
            r79 = r4
            r4 = 77
            r2.<init>(r6, r4)
            INST_CVTPD2DQ = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CVTPD2PI"
            r80 = r2
            r2 = 78
            r4.<init>(r6, r2)
            INST_CVTPD2PI = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CVTPD2PS"
            r81 = r4
            r4 = 79
            r2.<init>(r6, r4)
            INST_CVTPD2PS = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CVTPI2PD"
            r82 = r2
            r2 = 80
            r4.<init>(r6, r2)
            INST_CVTPI2PD = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CVTPI2PS"
            r83 = r4
            r4 = 81
            r2.<init>(r6, r4)
            INST_CVTPI2PS = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CVTPS2DQ"
            r84 = r2
            r2 = 82
            r4.<init>(r6, r2)
            INST_CVTPS2DQ = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CVTPS2PD"
            r85 = r4
            r4 = 83
            r2.<init>(r6, r4)
            INST_CVTPS2PD = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CVTPS2PI"
            r86 = r2
            r2 = 84
            r4.<init>(r6, r2)
            INST_CVTPS2PI = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CVTSD2SI"
            r87 = r4
            r4 = 85
            r2.<init>(r6, r4)
            INST_CVTSD2SI = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CVTSD2SS"
            r88 = r2
            r2 = 86
            r4.<init>(r6, r2)
            INST_CVTSD2SS = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CVTSI2SD"
            r89 = r4
            r4 = 87
            r2.<init>(r6, r4)
            INST_CVTSI2SD = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CVTSI2SS"
            r90 = r2
            r2 = 88
            r4.<init>(r6, r2)
            INST_CVTSI2SS = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CVTSS2SD"
            r91 = r4
            r4 = 89
            r2.<init>(r6, r4)
            INST_CVTSS2SD = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CVTSS2SI"
            r92 = r2
            r2 = 90
            r4.<init>(r6, r2)
            INST_CVTSS2SI = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CVTTPD2DQ"
            r93 = r4
            r4 = 91
            r2.<init>(r6, r4)
            INST_CVTTPD2DQ = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CVTTPD2PI"
            r94 = r2
            r2 = 92
            r4.<init>(r6, r2)
            INST_CVTTPD2PI = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CVTTPS2DQ"
            r95 = r4
            r4 = 93
            r2.<init>(r6, r4)
            INST_CVTTPS2DQ = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CVTTPS2PI"
            r96 = r2
            r2 = 94
            r4.<init>(r6, r2)
            INST_CVTTPS2PI = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CVTTSD2SI"
            r97 = r4
            r4 = 95
            r2.<init>(r6, r4)
            INST_CVTTSD2SI = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CVTTSS2SI"
            r98 = r2
            r2 = 96
            r4.<init>(r6, r2)
            INST_CVTTSS2SI = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_CWDE"
            r99 = r4
            r4 = 97
            r2.<init>(r6, r4)
            INST_CWDE = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_DAA"
            r100 = r2
            r2 = 98
            r4.<init>(r6, r2)
            INST_DAA = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_DAS"
            r101 = r4
            r4 = 99
            r2.<init>(r6, r4)
            INST_DAS = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_DEC"
            r102 = r2
            r2 = 100
            r4.<init>(r6, r2)
            INST_DEC = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_DIV"
            r103 = r4
            r4 = 101(0x65, float:1.42E-43)
            r2.<init>(r6, r4)
            INST_DIV = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_DIVPD"
            r104 = r2
            r2 = 102(0x66, float:1.43E-43)
            r4.<init>(r6, r2)
            INST_DIVPD = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_DIVPS"
            r105 = r4
            r4 = 103(0x67, float:1.44E-43)
            r2.<init>(r6, r4)
            INST_DIVPS = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_DIVSD"
            r106 = r2
            r2 = 104(0x68, float:1.46E-43)
            r4.<init>(r6, r2)
            INST_DIVSD = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_DIVSS"
            r107 = r4
            r4 = 105(0x69, float:1.47E-43)
            r2.<init>(r6, r4)
            INST_DIVSS = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_DPPD"
            r108 = r2
            r2 = 106(0x6a, float:1.49E-43)
            r4.<init>(r6, r2)
            INST_DPPD = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_DPPS"
            r109 = r4
            r4 = 107(0x6b, float:1.5E-43)
            r2.<init>(r6, r4)
            INST_DPPS = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_EMMS"
            r110 = r2
            r2 = 108(0x6c, float:1.51E-43)
            r4.<init>(r6, r2)
            INST_EMMS = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_ENTER"
            r111 = r4
            r4 = 109(0x6d, float:1.53E-43)
            r2.<init>(r6, r4)
            INST_ENTER = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_EXTRACTPS"
            r112 = r2
            r2 = 110(0x6e, float:1.54E-43)
            r4.<init>(r6, r2)
            INST_EXTRACTPS = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_F2XM1"
            r113 = r4
            r4 = 111(0x6f, float:1.56E-43)
            r2.<init>(r6, r4)
            INST_F2XM1 = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FABS"
            r114 = r2
            r2 = 112(0x70, float:1.57E-43)
            r4.<init>(r6, r2)
            INST_FABS = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FADD"
            r115 = r4
            r4 = 113(0x71, float:1.58E-43)
            r2.<init>(r6, r4)
            INST_FADD = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FADDP"
            r116 = r2
            r2 = 114(0x72, float:1.6E-43)
            r4.<init>(r6, r2)
            INST_FADDP = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FBLD"
            r117 = r4
            r4 = 115(0x73, float:1.61E-43)
            r2.<init>(r6, r4)
            INST_FBLD = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FBSTP"
            r118 = r2
            r2 = 116(0x74, float:1.63E-43)
            r4.<init>(r6, r2)
            INST_FBSTP = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FCHS"
            r119 = r4
            r4 = 117(0x75, float:1.64E-43)
            r2.<init>(r6, r4)
            INST_FCHS = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FCLEX"
            r120 = r2
            r2 = 118(0x76, float:1.65E-43)
            r4.<init>(r6, r2)
            INST_FCLEX = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FCMOVB"
            r121 = r4
            r4 = 119(0x77, float:1.67E-43)
            r2.<init>(r6, r4)
            INST_FCMOVB = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FCMOVBE"
            r122 = r2
            r2 = 120(0x78, float:1.68E-43)
            r4.<init>(r6, r2)
            INST_FCMOVBE = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FCMOVE"
            r123 = r4
            r4 = 121(0x79, float:1.7E-43)
            r2.<init>(r6, r4)
            INST_FCMOVE = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FCMOVNB"
            r124 = r2
            r2 = 122(0x7a, float:1.71E-43)
            r4.<init>(r6, r2)
            INST_FCMOVNB = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FCMOVNBE"
            r125 = r4
            r4 = 123(0x7b, float:1.72E-43)
            r2.<init>(r6, r4)
            INST_FCMOVNBE = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FCMOVNE"
            r126 = r2
            r2 = 124(0x7c, float:1.74E-43)
            r4.<init>(r6, r2)
            INST_FCMOVNE = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FCMOVNU"
            r127 = r4
            r4 = 125(0x7d, float:1.75E-43)
            r2.<init>(r6, r4)
            INST_FCMOVNU = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FCMOVU"
            r128 = r2
            r2 = 126(0x7e, float:1.77E-43)
            r4.<init>(r6, r2)
            INST_FCMOVU = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FCOM"
            r129 = r4
            r4 = 127(0x7f, float:1.78E-43)
            r2.<init>(r6, r4)
            INST_FCOM = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FCOMI"
            r130 = r2
            r2 = 128(0x80, float:1.794E-43)
            r4.<init>(r6, r2)
            INST_FCOMI = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FCOMIP"
            r131 = r4
            r4 = 129(0x81, float:1.81E-43)
            r2.<init>(r6, r4)
            INST_FCOMIP = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FCOMP"
            r132 = r2
            r2 = 130(0x82, float:1.82E-43)
            r4.<init>(r6, r2)
            INST_FCOMP = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FCOMPP"
            r133 = r4
            r4 = 131(0x83, float:1.84E-43)
            r2.<init>(r6, r4)
            INST_FCOMPP = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FCOS"
            r134 = r2
            r2 = 132(0x84, float:1.85E-43)
            r4.<init>(r6, r2)
            INST_FCOS = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FDECSTP"
            r135 = r4
            r4 = 133(0x85, float:1.86E-43)
            r2.<init>(r6, r4)
            INST_FDECSTP = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FDIV"
            r136 = r2
            r2 = 134(0x86, float:1.88E-43)
            r4.<init>(r6, r2)
            INST_FDIV = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FDIVP"
            r137 = r4
            r4 = 135(0x87, float:1.89E-43)
            r2.<init>(r6, r4)
            INST_FDIVP = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FDIVR"
            r138 = r2
            r2 = 136(0x88, float:1.9E-43)
            r4.<init>(r6, r2)
            INST_FDIVR = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FDIVRP"
            r139 = r4
            r4 = 137(0x89, float:1.92E-43)
            r2.<init>(r6, r4)
            INST_FDIVRP = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FEMMS"
            r140 = r2
            r2 = 138(0x8a, float:1.93E-43)
            r4.<init>(r6, r2)
            INST_FEMMS = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FFREE"
            r141 = r4
            r4 = 139(0x8b, float:1.95E-43)
            r2.<init>(r6, r4)
            INST_FFREE = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FIADD"
            r142 = r2
            r2 = 140(0x8c, float:1.96E-43)
            r4.<init>(r6, r2)
            INST_FIADD = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FICOM"
            r143 = r4
            r4 = 141(0x8d, float:1.98E-43)
            r2.<init>(r6, r4)
            INST_FICOM = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FICOMP"
            r144 = r2
            r2 = 142(0x8e, float:1.99E-43)
            r4.<init>(r6, r2)
            INST_FICOMP = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FIDIV"
            r145 = r4
            r4 = 143(0x8f, float:2.0E-43)
            r2.<init>(r6, r4)
            INST_FIDIV = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FIDIVR"
            r146 = r2
            r2 = 144(0x90, float:2.02E-43)
            r4.<init>(r6, r2)
            INST_FIDIVR = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FILD"
            r147 = r4
            r4 = 145(0x91, float:2.03E-43)
            r2.<init>(r6, r4)
            INST_FILD = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FIMUL"
            r148 = r2
            r2 = 146(0x92, float:2.05E-43)
            r4.<init>(r6, r2)
            INST_FIMUL = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FINCSTP"
            r149 = r4
            r4 = 147(0x93, float:2.06E-43)
            r2.<init>(r6, r4)
            INST_FINCSTP = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FINIT"
            r150 = r2
            r2 = 148(0x94, float:2.07E-43)
            r4.<init>(r6, r2)
            INST_FINIT = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FIST"
            r151 = r4
            r4 = 149(0x95, float:2.09E-43)
            r2.<init>(r6, r4)
            INST_FIST = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FISTP"
            r152 = r2
            r2 = 150(0x96, float:2.1E-43)
            r4.<init>(r6, r2)
            INST_FISTP = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FISTTP"
            r153 = r4
            r4 = 151(0x97, float:2.12E-43)
            r2.<init>(r6, r4)
            INST_FISTTP = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FISUB"
            r154 = r2
            r2 = 152(0x98, float:2.13E-43)
            r4.<init>(r6, r2)
            INST_FISUB = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FISUBR"
            r155 = r4
            r4 = 153(0x99, float:2.14E-43)
            r2.<init>(r6, r4)
            INST_FISUBR = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FLD"
            r156 = r2
            r2 = 154(0x9a, float:2.16E-43)
            r4.<init>(r6, r2)
            INST_FLD = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FLD1"
            r157 = r4
            r4 = 155(0x9b, float:2.17E-43)
            r2.<init>(r6, r4)
            INST_FLD1 = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FLDCW"
            r158 = r2
            r2 = 156(0x9c, float:2.19E-43)
            r4.<init>(r6, r2)
            INST_FLDCW = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FLDENV"
            r159 = r4
            r4 = 157(0x9d, float:2.2E-43)
            r2.<init>(r6, r4)
            INST_FLDENV = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FLDL2E"
            r160 = r2
            r2 = 158(0x9e, float:2.21E-43)
            r4.<init>(r6, r2)
            INST_FLDL2E = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FLDL2T"
            r161 = r4
            r4 = 159(0x9f, float:2.23E-43)
            r2.<init>(r6, r4)
            INST_FLDL2T = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FLDLG2"
            r162 = r2
            r2 = 160(0xa0, float:2.24E-43)
            r4.<init>(r6, r2)
            INST_FLDLG2 = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FLDLN2"
            r163 = r4
            r4 = 161(0xa1, float:2.26E-43)
            r2.<init>(r6, r4)
            INST_FLDLN2 = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FLDPI"
            r164 = r2
            r2 = 162(0xa2, float:2.27E-43)
            r4.<init>(r6, r2)
            INST_FLDPI = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FLDZ"
            r165 = r4
            r4 = 163(0xa3, float:2.28E-43)
            r2.<init>(r6, r4)
            INST_FLDZ = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FMUL"
            r166 = r2
            r2 = 164(0xa4, float:2.3E-43)
            r4.<init>(r6, r2)
            INST_FMUL = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FMULP"
            r167 = r4
            r4 = 165(0xa5, float:2.31E-43)
            r2.<init>(r6, r4)
            INST_FMULP = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FNCLEX"
            r168 = r2
            r2 = 166(0xa6, float:2.33E-43)
            r4.<init>(r6, r2)
            INST_FNCLEX = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FNINIT"
            r169 = r4
            r4 = 167(0xa7, float:2.34E-43)
            r2.<init>(r6, r4)
            INST_FNINIT = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FNOP"
            r170 = r2
            r2 = 168(0xa8, float:2.35E-43)
            r4.<init>(r6, r2)
            INST_FNOP = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FNSAVE"
            r171 = r4
            r4 = 169(0xa9, float:2.37E-43)
            r2.<init>(r6, r4)
            INST_FNSAVE = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FNSTCW"
            r172 = r2
            r2 = 170(0xaa, float:2.38E-43)
            r4.<init>(r6, r2)
            INST_FNSTCW = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FNSTENV"
            r173 = r4
            r4 = 171(0xab, float:2.4E-43)
            r2.<init>(r6, r4)
            INST_FNSTENV = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FNSTSW"
            r174 = r2
            r2 = 172(0xac, float:2.41E-43)
            r4.<init>(r6, r2)
            INST_FNSTSW = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FPATAN"
            r175 = r4
            r4 = 173(0xad, float:2.42E-43)
            r2.<init>(r6, r4)
            INST_FPATAN = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FPREM"
            r176 = r2
            r2 = 174(0xae, float:2.44E-43)
            r4.<init>(r6, r2)
            INST_FPREM = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FPREM1"
            r177 = r4
            r4 = 175(0xaf, float:2.45E-43)
            r2.<init>(r6, r4)
            INST_FPREM1 = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FPTAN"
            r178 = r2
            r2 = 176(0xb0, float:2.47E-43)
            r4.<init>(r6, r2)
            INST_FPTAN = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FRNDINT"
            r179 = r4
            r4 = 177(0xb1, float:2.48E-43)
            r2.<init>(r6, r4)
            INST_FRNDINT = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FRSTOR"
            r180 = r2
            r2 = 178(0xb2, float:2.5E-43)
            r4.<init>(r6, r2)
            INST_FRSTOR = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FSAVE"
            r181 = r4
            r4 = 179(0xb3, float:2.51E-43)
            r2.<init>(r6, r4)
            INST_FSAVE = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FSCALE"
            r182 = r2
            r2 = 180(0xb4, float:2.52E-43)
            r4.<init>(r6, r2)
            INST_FSCALE = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FSIN"
            r183 = r4
            r4 = 181(0xb5, float:2.54E-43)
            r2.<init>(r6, r4)
            INST_FSIN = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FSINCOS"
            r184 = r2
            r2 = 182(0xb6, float:2.55E-43)
            r4.<init>(r6, r2)
            INST_FSINCOS = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FSQRT"
            r185 = r4
            r4 = 183(0xb7, float:2.56E-43)
            r2.<init>(r6, r4)
            INST_FSQRT = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FST"
            r186 = r2
            r2 = 184(0xb8, float:2.58E-43)
            r4.<init>(r6, r2)
            INST_FST = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FSTCW"
            r187 = r4
            r4 = 185(0xb9, float:2.59E-43)
            r2.<init>(r6, r4)
            INST_FSTCW = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FSTENV"
            r188 = r2
            r2 = 186(0xba, float:2.6E-43)
            r4.<init>(r6, r2)
            INST_FSTENV = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FSTP"
            r189 = r4
            r4 = 187(0xbb, float:2.62E-43)
            r2.<init>(r6, r4)
            INST_FSTP = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FSTSW"
            r190 = r2
            r2 = 188(0xbc, float:2.63E-43)
            r4.<init>(r6, r2)
            INST_FSTSW = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FSUB"
            r191 = r4
            r4 = 189(0xbd, float:2.65E-43)
            r2.<init>(r6, r4)
            INST_FSUB = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FSUBP"
            r192 = r2
            r2 = 190(0xbe, float:2.66E-43)
            r4.<init>(r6, r2)
            INST_FSUBP = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FSUBR"
            r193 = r4
            r4 = 191(0xbf, float:2.68E-43)
            r2.<init>(r6, r4)
            INST_FSUBR = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FSUBRP"
            r194 = r2
            r2 = 192(0xc0, float:2.69E-43)
            r4.<init>(r6, r2)
            INST_FSUBRP = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FTST"
            r195 = r4
            r4 = 193(0xc1, float:2.7E-43)
            r2.<init>(r6, r4)
            INST_FTST = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FUCOM"
            r196 = r2
            r2 = 194(0xc2, float:2.72E-43)
            r4.<init>(r6, r2)
            INST_FUCOM = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FUCOMI"
            r197 = r4
            r4 = 195(0xc3, float:2.73E-43)
            r2.<init>(r6, r4)
            INST_FUCOMI = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FUCOMIP"
            r198 = r2
            r2 = 196(0xc4, float:2.75E-43)
            r4.<init>(r6, r2)
            INST_FUCOMIP = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FUCOMP"
            r199 = r4
            r4 = 197(0xc5, float:2.76E-43)
            r2.<init>(r6, r4)
            INST_FUCOMP = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FUCOMPP"
            r200 = r2
            r2 = 198(0xc6, float:2.77E-43)
            r4.<init>(r6, r2)
            INST_FUCOMPP = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FWAIT"
            r201 = r4
            r4 = 199(0xc7, float:2.79E-43)
            r2.<init>(r6, r4)
            INST_FWAIT = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FXAM"
            r202 = r2
            r2 = 200(0xc8, float:2.8E-43)
            r4.<init>(r6, r2)
            INST_FXAM = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FXCH"
            r203 = r4
            r4 = 201(0xc9, float:2.82E-43)
            r2.<init>(r6, r4)
            INST_FXCH = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FXRSTOR"
            r204 = r2
            r2 = 202(0xca, float:2.83E-43)
            r4.<init>(r6, r2)
            INST_FXRSTOR = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FXSAVE"
            r205 = r4
            r4 = 203(0xcb, float:2.84E-43)
            r2.<init>(r6, r4)
            INST_FXSAVE = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FXTRACT"
            r206 = r2
            r2 = 204(0xcc, float:2.86E-43)
            r4.<init>(r6, r2)
            INST_FXTRACT = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FYL2X"
            r207 = r4
            r4 = 205(0xcd, float:2.87E-43)
            r2.<init>(r6, r4)
            INST_FYL2X = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_FYL2XP1"
            r208 = r2
            r2 = 206(0xce, float:2.89E-43)
            r4.<init>(r6, r2)
            INST_FYL2XP1 = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_HADDPD"
            r209 = r4
            r4 = 207(0xcf, float:2.9E-43)
            r2.<init>(r6, r4)
            INST_HADDPD = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_HADDPS"
            r210 = r2
            r2 = 208(0xd0, float:2.91E-43)
            r4.<init>(r6, r2)
            INST_HADDPS = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_HSUBPD"
            r211 = r4
            r4 = 209(0xd1, float:2.93E-43)
            r2.<init>(r6, r4)
            INST_HSUBPD = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_HSUBPS"
            r212 = r2
            r2 = 210(0xd2, float:2.94E-43)
            r4.<init>(r6, r2)
            INST_HSUBPS = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_IDIV"
            r213 = r4
            r4 = 211(0xd3, float:2.96E-43)
            r2.<init>(r6, r4)
            INST_IDIV = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_IMUL"
            r214 = r2
            r2 = 212(0xd4, float:2.97E-43)
            r4.<init>(r6, r2)
            INST_IMUL = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_INC"
            r215 = r4
            r4 = 213(0xd5, float:2.98E-43)
            r2.<init>(r6, r4)
            INST_INC = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_INT3"
            r216 = r2
            r2 = 214(0xd6, float:3.0E-43)
            r4.<init>(r6, r2)
            INST_INT3 = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JA"
            r217 = r4
            r4 = 215(0xd7, float:3.01E-43)
            r2.<init>(r6, r4)
            INST_JA = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JAE"
            r218 = r2
            r2 = 216(0xd8, float:3.03E-43)
            r4.<init>(r6, r2)
            INST_JAE = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JB"
            r219 = r4
            r4 = 217(0xd9, float:3.04E-43)
            r2.<init>(r6, r4)
            INST_JB = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JBE"
            r220 = r2
            r2 = 218(0xda, float:3.05E-43)
            r4.<init>(r6, r2)
            INST_JBE = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JC"
            r221 = r4
            r4 = 219(0xdb, float:3.07E-43)
            r2.<init>(r6, r4)
            INST_JC = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JE"
            r222 = r2
            r2 = 220(0xdc, float:3.08E-43)
            r4.<init>(r6, r2)
            INST_JE = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JG"
            r223 = r4
            r4 = 221(0xdd, float:3.1E-43)
            r2.<init>(r6, r4)
            INST_JG = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JGE"
            r224 = r2
            r2 = 222(0xde, float:3.11E-43)
            r4.<init>(r6, r2)
            INST_JGE = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JL"
            r225 = r4
            r4 = 223(0xdf, float:3.12E-43)
            r2.<init>(r6, r4)
            INST_JL = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JLE"
            r226 = r2
            r2 = 224(0xe0, float:3.14E-43)
            r4.<init>(r6, r2)
            INST_JLE = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JNA"
            r227 = r4
            r4 = 225(0xe1, float:3.15E-43)
            r2.<init>(r6, r4)
            INST_JNA = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JNAE"
            r228 = r2
            r2 = 226(0xe2, float:3.17E-43)
            r4.<init>(r6, r2)
            INST_JNAE = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JNB"
            r229 = r4
            r4 = 227(0xe3, float:3.18E-43)
            r2.<init>(r6, r4)
            INST_JNB = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JNBE"
            r230 = r2
            r2 = 228(0xe4, float:3.2E-43)
            r4.<init>(r6, r2)
            INST_JNBE = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JNC"
            r231 = r4
            r4 = 229(0xe5, float:3.21E-43)
            r2.<init>(r6, r4)
            INST_JNC = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JNE"
            r232 = r2
            r2 = 230(0xe6, float:3.22E-43)
            r4.<init>(r6, r2)
            INST_JNE = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JNG"
            r233 = r4
            r4 = 231(0xe7, float:3.24E-43)
            r2.<init>(r6, r4)
            INST_JNG = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JNGE"
            r234 = r2
            r2 = 232(0xe8, float:3.25E-43)
            r4.<init>(r6, r2)
            INST_JNGE = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JNL"
            r235 = r4
            r4 = 233(0xe9, float:3.27E-43)
            r2.<init>(r6, r4)
            INST_JNL = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JNLE"
            r236 = r2
            r2 = 234(0xea, float:3.28E-43)
            r4.<init>(r6, r2)
            INST_JNLE = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JNO"
            r237 = r4
            r4 = 235(0xeb, float:3.3E-43)
            r2.<init>(r6, r4)
            INST_JNO = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JNP"
            r238 = r2
            r2 = 236(0xec, float:3.31E-43)
            r4.<init>(r6, r2)
            INST_JNP = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JNS"
            r239 = r4
            r4 = 237(0xed, float:3.32E-43)
            r2.<init>(r6, r4)
            INST_JNS = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JNZ"
            r240 = r2
            r2 = 238(0xee, float:3.34E-43)
            r4.<init>(r6, r2)
            INST_JNZ = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JO"
            r241 = r4
            r4 = 239(0xef, float:3.35E-43)
            r2.<init>(r6, r4)
            INST_JO = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JP"
            r242 = r2
            r2 = 240(0xf0, float:3.36E-43)
            r4.<init>(r6, r2)
            INST_JP = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JPE"
            r243 = r4
            r4 = 241(0xf1, float:3.38E-43)
            r2.<init>(r6, r4)
            INST_JPE = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JPO"
            r244 = r2
            r2 = 242(0xf2, float:3.39E-43)
            r4.<init>(r6, r2)
            INST_JPO = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JS"
            r245 = r4
            r4 = 243(0xf3, float:3.4E-43)
            r2.<init>(r6, r4)
            INST_JS = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JZ"
            r246 = r2
            r2 = 244(0xf4, float:3.42E-43)
            r4.<init>(r6, r2)
            INST_JZ = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JMP"
            r247 = r4
            r4 = 245(0xf5, float:3.43E-43)
            r2.<init>(r6, r4)
            INST_JMP = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JA_SHORT"
            r248 = r2
            r2 = 246(0xf6, float:3.45E-43)
            r4.<init>(r6, r2)
            INST_JA_SHORT = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JAE_SHORT"
            r249 = r4
            r4 = 247(0xf7, float:3.46E-43)
            r2.<init>(r6, r4)
            INST_JAE_SHORT = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JB_SHORT"
            r250 = r2
            r2 = 248(0xf8, float:3.48E-43)
            r4.<init>(r6, r2)
            INST_JB_SHORT = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JBE_SHORT"
            r251 = r4
            r4 = 249(0xf9, float:3.49E-43)
            r2.<init>(r6, r4)
            INST_JBE_SHORT = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JC_SHORT"
            r252 = r2
            r2 = 250(0xfa, float:3.5E-43)
            r4.<init>(r6, r2)
            INST_JC_SHORT = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JE_SHORT"
            r253 = r4
            r4 = 251(0xfb, float:3.52E-43)
            r2.<init>(r6, r4)
            INST_JE_SHORT = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JG_SHORT"
            r254 = r2
            r2 = 252(0xfc, float:3.53E-43)
            r4.<init>(r6, r2)
            INST_JG_SHORT = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JGE_SHORT"
            r255 = r4
            r4 = 253(0xfd, float:3.55E-43)
            r2.<init>(r6, r4)
            INST_JGE_SHORT = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JL_SHORT"
            r256 = r2
            r2 = 254(0xfe, float:3.56E-43)
            r4.<init>(r6, r2)
            INST_JL_SHORT = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JLE_SHORT"
            r257 = r4
            r4 = 255(0xff, float:3.57E-43)
            r2.<init>(r6, r4)
            INST_JLE_SHORT = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JNA_SHORT"
            r258 = r2
            r2 = 256(0x100, float:3.59E-43)
            r4.<init>(r6, r2)
            INST_JNA_SHORT = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JNAE_SHORT"
            r259 = r4
            r4 = 257(0x101, float:3.6E-43)
            r2.<init>(r6, r4)
            INST_JNAE_SHORT = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JNB_SHORT"
            r260 = r2
            r2 = 258(0x102, float:3.62E-43)
            r4.<init>(r6, r2)
            INST_JNB_SHORT = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JNBE_SHORT"
            r261 = r4
            r4 = 259(0x103, float:3.63E-43)
            r2.<init>(r6, r4)
            INST_JNBE_SHORT = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JNC_SHORT"
            r262 = r2
            r2 = 260(0x104, float:3.64E-43)
            r4.<init>(r6, r2)
            INST_JNC_SHORT = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JNE_SHORT"
            r263 = r4
            r4 = 261(0x105, float:3.66E-43)
            r2.<init>(r6, r4)
            INST_JNE_SHORT = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JNG_SHORT"
            r264 = r2
            r2 = 262(0x106, float:3.67E-43)
            r4.<init>(r6, r2)
            INST_JNG_SHORT = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JNGE_SHORT"
            r265 = r4
            r4 = 263(0x107, float:3.69E-43)
            r2.<init>(r6, r4)
            INST_JNGE_SHORT = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JNL_SHORT"
            r266 = r2
            r2 = 264(0x108, float:3.7E-43)
            r4.<init>(r6, r2)
            INST_JNL_SHORT = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JNLE_SHORT"
            r267 = r4
            r4 = 265(0x109, float:3.71E-43)
            r2.<init>(r6, r4)
            INST_JNLE_SHORT = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JNO_SHORT"
            r268 = r2
            r2 = 266(0x10a, float:3.73E-43)
            r4.<init>(r6, r2)
            INST_JNO_SHORT = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JNP_SHORT"
            r269 = r4
            r4 = 267(0x10b, float:3.74E-43)
            r2.<init>(r6, r4)
            INST_JNP_SHORT = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JNS_SHORT"
            r270 = r2
            r2 = 268(0x10c, float:3.76E-43)
            r4.<init>(r6, r2)
            INST_JNS_SHORT = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JNZ_SHORT"
            r271 = r4
            r4 = 269(0x10d, float:3.77E-43)
            r2.<init>(r6, r4)
            INST_JNZ_SHORT = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JO_SHORT"
            r272 = r2
            r2 = 270(0x10e, float:3.78E-43)
            r4.<init>(r6, r2)
            INST_JO_SHORT = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JP_SHORT"
            r273 = r4
            r4 = 271(0x10f, float:3.8E-43)
            r2.<init>(r6, r4)
            INST_JP_SHORT = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JPE_SHORT"
            r274 = r2
            r2 = 272(0x110, float:3.81E-43)
            r4.<init>(r6, r2)
            INST_JPE_SHORT = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JPO_SHORT"
            r275 = r4
            r4 = 273(0x111, float:3.83E-43)
            r2.<init>(r6, r4)
            INST_JPO_SHORT = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JS_SHORT"
            r276 = r2
            r2 = 274(0x112, float:3.84E-43)
            r4.<init>(r6, r2)
            INST_JS_SHORT = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JZ_SHORT"
            r277 = r4
            r4 = 275(0x113, float:3.85E-43)
            r2.<init>(r6, r4)
            INST_JZ_SHORT = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_JMP_SHORT"
            r278 = r2
            r2 = 276(0x114, float:3.87E-43)
            r4.<init>(r6, r2)
            INST_JMP_SHORT = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_LDDQU"
            r279 = r4
            r4 = 277(0x115, float:3.88E-43)
            r2.<init>(r6, r4)
            INST_LDDQU = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_LDMXCSR"
            r280 = r2
            r2 = 278(0x116, float:3.9E-43)
            r4.<init>(r6, r2)
            INST_LDMXCSR = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_LEA"
            r281 = r4
            r4 = 279(0x117, float:3.91E-43)
            r2.<init>(r6, r4)
            INST_LEA = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_LEAVE"
            r282 = r2
            r2 = 280(0x118, float:3.92E-43)
            r4.<init>(r6, r2)
            INST_LEAVE = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_LFENCE"
            r283 = r4
            r4 = 281(0x119, float:3.94E-43)
            r2.<init>(r6, r4)
            INST_LFENCE = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_LOCK"
            r284 = r2
            r2 = 282(0x11a, float:3.95E-43)
            r4.<init>(r6, r2)
            INST_LOCK = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MASKMOVDQU"
            r285 = r4
            r4 = 283(0x11b, float:3.97E-43)
            r2.<init>(r6, r4)
            INST_MASKMOVDQU = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MASKMOVQ"
            r286 = r2
            r2 = 284(0x11c, float:3.98E-43)
            r4.<init>(r6, r2)
            INST_MASKMOVQ = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MAXPD"
            r287 = r4
            r4 = 285(0x11d, float:4.0E-43)
            r2.<init>(r6, r4)
            INST_MAXPD = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MAXPS"
            r288 = r2
            r2 = 286(0x11e, float:4.01E-43)
            r4.<init>(r6, r2)
            INST_MAXPS = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MAXSD"
            r289 = r4
            r4 = 287(0x11f, float:4.02E-43)
            r2.<init>(r6, r4)
            INST_MAXSD = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MAXSS"
            r290 = r2
            r2 = 288(0x120, float:4.04E-43)
            r4.<init>(r6, r2)
            INST_MAXSS = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MFENCE"
            r291 = r4
            r4 = 289(0x121, float:4.05E-43)
            r2.<init>(r6, r4)
            INST_MFENCE = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MINPD"
            r292 = r2
            r2 = 290(0x122, float:4.06E-43)
            r4.<init>(r6, r2)
            INST_MINPD = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MINPS"
            r293 = r4
            r4 = 291(0x123, float:4.08E-43)
            r2.<init>(r6, r4)
            INST_MINPS = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MINSD"
            r294 = r2
            r2 = 292(0x124, float:4.09E-43)
            r4.<init>(r6, r2)
            INST_MINSD = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MINSS"
            r295 = r4
            r4 = 293(0x125, float:4.1E-43)
            r2.<init>(r6, r4)
            INST_MINSS = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MONITOR"
            r296 = r2
            r2 = 294(0x126, float:4.12E-43)
            r4.<init>(r6, r2)
            INST_MONITOR = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MOV"
            r297 = r4
            r4 = 295(0x127, float:4.13E-43)
            r2.<init>(r6, r4)
            INST_MOV = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MOVAPD"
            r298 = r2
            r2 = 296(0x128, float:4.15E-43)
            r4.<init>(r6, r2)
            INST_MOVAPD = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MOVAPS"
            r299 = r4
            r4 = 297(0x129, float:4.16E-43)
            r2.<init>(r6, r4)
            INST_MOVAPS = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MOVBE"
            r300 = r2
            r2 = 298(0x12a, float:4.18E-43)
            r4.<init>(r6, r2)
            INST_MOVBE = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MOVD"
            r301 = r4
            r4 = 299(0x12b, float:4.19E-43)
            r2.<init>(r6, r4)
            INST_MOVD = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MOVDDUP"
            r302 = r2
            r2 = 300(0x12c, float:4.2E-43)
            r4.<init>(r6, r2)
            INST_MOVDDUP = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MOVDQ2Q"
            r303 = r4
            r4 = 301(0x12d, float:4.22E-43)
            r2.<init>(r6, r4)
            INST_MOVDQ2Q = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MOVDQA"
            r304 = r2
            r2 = 302(0x12e, float:4.23E-43)
            r4.<init>(r6, r2)
            INST_MOVDQA = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MOVDQU"
            r305 = r4
            r4 = 303(0x12f, float:4.25E-43)
            r2.<init>(r6, r4)
            INST_MOVDQU = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MOVHLPS"
            r306 = r2
            r2 = 304(0x130, float:4.26E-43)
            r4.<init>(r6, r2)
            INST_MOVHLPS = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MOVHPD"
            r307 = r4
            r4 = 305(0x131, float:4.27E-43)
            r2.<init>(r6, r4)
            INST_MOVHPD = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MOVHPS"
            r308 = r2
            r2 = 306(0x132, float:4.29E-43)
            r4.<init>(r6, r2)
            INST_MOVHPS = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MOVLHPS"
            r309 = r4
            r4 = 307(0x133, float:4.3E-43)
            r2.<init>(r6, r4)
            INST_MOVLHPS = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MOVLPD"
            r310 = r2
            r2 = 308(0x134, float:4.32E-43)
            r4.<init>(r6, r2)
            INST_MOVLPD = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MOVLPS"
            r311 = r4
            r4 = 309(0x135, float:4.33E-43)
            r2.<init>(r6, r4)
            INST_MOVLPS = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MOVMSKPD"
            r312 = r2
            r2 = 310(0x136, float:4.34E-43)
            r4.<init>(r6, r2)
            INST_MOVMSKPD = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MOVMSKPS"
            r313 = r4
            r4 = 311(0x137, float:4.36E-43)
            r2.<init>(r6, r4)
            INST_MOVMSKPS = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MOVNTDQ"
            r314 = r2
            r2 = 312(0x138, float:4.37E-43)
            r4.<init>(r6, r2)
            INST_MOVNTDQ = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MOVNTDQA"
            r315 = r4
            r4 = 313(0x139, float:4.39E-43)
            r2.<init>(r6, r4)
            INST_MOVNTDQA = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MOVNTI"
            r316 = r2
            r2 = 314(0x13a, float:4.4E-43)
            r4.<init>(r6, r2)
            INST_MOVNTI = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MOVNTPD"
            r317 = r4
            r4 = 315(0x13b, float:4.41E-43)
            r2.<init>(r6, r4)
            INST_MOVNTPD = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MOVNTPS"
            r318 = r2
            r2 = 316(0x13c, float:4.43E-43)
            r4.<init>(r6, r2)
            INST_MOVNTPS = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MOVNTQ"
            r319 = r4
            r4 = 317(0x13d, float:4.44E-43)
            r2.<init>(r6, r4)
            INST_MOVNTQ = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MOVQ"
            r320 = r2
            r2 = 318(0x13e, float:4.46E-43)
            r4.<init>(r6, r2)
            INST_MOVQ = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MOVQ2DQ"
            r321 = r4
            r4 = 319(0x13f, float:4.47E-43)
            r2.<init>(r6, r4)
            INST_MOVQ2DQ = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MOVSD"
            r322 = r2
            r2 = 320(0x140, float:4.48E-43)
            r4.<init>(r6, r2)
            INST_MOVSD = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MOVSHDUP"
            r323 = r4
            r4 = 321(0x141, float:4.5E-43)
            r2.<init>(r6, r4)
            INST_MOVSHDUP = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MOVSLDUP"
            r324 = r2
            r2 = 322(0x142, float:4.51E-43)
            r4.<init>(r6, r2)
            INST_MOVSLDUP = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MOVSS"
            r325 = r4
            r4 = 323(0x143, float:4.53E-43)
            r2.<init>(r6, r4)
            INST_MOVSS = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MOVSX"
            r326 = r2
            r2 = 324(0x144, float:4.54E-43)
            r4.<init>(r6, r2)
            INST_MOVSX = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MOVSXD"
            r327 = r4
            r4 = 325(0x145, float:4.55E-43)
            r2.<init>(r6, r4)
            INST_MOVSXD = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MOVUPD"
            r328 = r2
            r2 = 326(0x146, float:4.57E-43)
            r4.<init>(r6, r2)
            INST_MOVUPD = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MOVUPS"
            r329 = r4
            r4 = 327(0x147, float:4.58E-43)
            r2.<init>(r6, r4)
            INST_MOVUPS = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MOVZX"
            r330 = r2
            r2 = 328(0x148, float:4.6E-43)
            r4.<init>(r6, r2)
            INST_MOVZX = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MOV_PTR"
            r331 = r4
            r4 = 329(0x149, float:4.61E-43)
            r2.<init>(r6, r4)
            INST_MOV_PTR = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MPSADBW"
            r332 = r2
            r2 = 330(0x14a, float:4.62E-43)
            r4.<init>(r6, r2)
            INST_MPSADBW = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MUL"
            r333 = r4
            r4 = 331(0x14b, float:4.64E-43)
            r2.<init>(r6, r4)
            INST_MUL = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MULPD"
            r334 = r2
            r2 = 332(0x14c, float:4.65E-43)
            r4.<init>(r6, r2)
            INST_MULPD = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MULPS"
            r335 = r4
            r4 = 333(0x14d, float:4.67E-43)
            r2.<init>(r6, r4)
            INST_MULPS = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MULSD"
            r336 = r2
            r2 = 334(0x14e, float:4.68E-43)
            r4.<init>(r6, r2)
            INST_MULSD = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MULSS"
            r337 = r4
            r4 = 335(0x14f, float:4.7E-43)
            r2.<init>(r6, r4)
            INST_MULSS = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_MWAIT"
            r338 = r2
            r2 = 336(0x150, float:4.71E-43)
            r4.<init>(r6, r2)
            INST_MWAIT = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_NEG"
            r339 = r4
            r4 = 337(0x151, float:4.72E-43)
            r2.<init>(r6, r4)
            INST_NEG = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_NOP"
            r340 = r2
            r2 = 338(0x152, float:4.74E-43)
            r4.<init>(r6, r2)
            INST_NOP = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_NOT"
            r341 = r4
            r4 = 339(0x153, float:4.75E-43)
            r2.<init>(r6, r4)
            INST_NOT = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_OR"
            r342 = r2
            r2 = 340(0x154, float:4.76E-43)
            r4.<init>(r6, r2)
            INST_OR = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_ORPD"
            r343 = r4
            r4 = 341(0x155, float:4.78E-43)
            r2.<init>(r6, r4)
            INST_ORPD = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_ORPS"
            r344 = r2
            r2 = 342(0x156, float:4.79E-43)
            r4.<init>(r6, r2)
            INST_ORPS = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PABSB"
            r345 = r4
            r4 = 343(0x157, float:4.8E-43)
            r2.<init>(r6, r4)
            INST_PABSB = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PABSD"
            r346 = r2
            r2 = 344(0x158, float:4.82E-43)
            r4.<init>(r6, r2)
            INST_PABSD = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PABSW"
            r347 = r4
            r4 = 345(0x159, float:4.83E-43)
            r2.<init>(r6, r4)
            INST_PABSW = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PACKSSDW"
            r348 = r2
            r2 = 346(0x15a, float:4.85E-43)
            r4.<init>(r6, r2)
            INST_PACKSSDW = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PACKSSWB"
            r349 = r4
            r4 = 347(0x15b, float:4.86E-43)
            r2.<init>(r6, r4)
            INST_PACKSSWB = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PACKUSDW"
            r350 = r2
            r2 = 348(0x15c, float:4.88E-43)
            r4.<init>(r6, r2)
            INST_PACKUSDW = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PACKUSWB"
            r351 = r4
            r4 = 349(0x15d, float:4.89E-43)
            r2.<init>(r6, r4)
            INST_PACKUSWB = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PADDB"
            r352 = r2
            r2 = 350(0x15e, float:4.9E-43)
            r4.<init>(r6, r2)
            INST_PADDB = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PADDD"
            r353 = r4
            r4 = 351(0x15f, float:4.92E-43)
            r2.<init>(r6, r4)
            INST_PADDD = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PADDQ"
            r354 = r2
            r2 = 352(0x160, float:4.93E-43)
            r4.<init>(r6, r2)
            INST_PADDQ = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PADDSB"
            r355 = r4
            r4 = 353(0x161, float:4.95E-43)
            r2.<init>(r6, r4)
            INST_PADDSB = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PADDSW"
            r356 = r2
            r2 = 354(0x162, float:4.96E-43)
            r4.<init>(r6, r2)
            INST_PADDSW = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PADDUSB"
            r357 = r4
            r4 = 355(0x163, float:4.97E-43)
            r2.<init>(r6, r4)
            INST_PADDUSB = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PADDUSW"
            r358 = r2
            r2 = 356(0x164, float:4.99E-43)
            r4.<init>(r6, r2)
            INST_PADDUSW = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PADDW"
            r359 = r4
            r4 = 357(0x165, float:5.0E-43)
            r2.<init>(r6, r4)
            INST_PADDW = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PALIGNR"
            r360 = r2
            r2 = 358(0x166, float:5.02E-43)
            r4.<init>(r6, r2)
            INST_PALIGNR = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PAND"
            r361 = r4
            r4 = 359(0x167, float:5.03E-43)
            r2.<init>(r6, r4)
            INST_PAND = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PANDN"
            r362 = r2
            r2 = 360(0x168, float:5.04E-43)
            r4.<init>(r6, r2)
            INST_PANDN = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PAUSE"
            r363 = r4
            r4 = 361(0x169, float:5.06E-43)
            r2.<init>(r6, r4)
            INST_PAUSE = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PAVGB"
            r364 = r2
            r2 = 362(0x16a, float:5.07E-43)
            r4.<init>(r6, r2)
            INST_PAVGB = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PAVGW"
            r365 = r4
            r4 = 363(0x16b, float:5.09E-43)
            r2.<init>(r6, r4)
            INST_PAVGW = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PBLENDVB"
            r366 = r2
            r2 = 364(0x16c, float:5.1E-43)
            r4.<init>(r6, r2)
            INST_PBLENDVB = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PBLENDW"
            r367 = r4
            r4 = 365(0x16d, float:5.11E-43)
            r2.<init>(r6, r4)
            INST_PBLENDW = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PCMPEQB"
            r368 = r2
            r2 = 366(0x16e, float:5.13E-43)
            r4.<init>(r6, r2)
            INST_PCMPEQB = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PCMPEQD"
            r369 = r4
            r4 = 367(0x16f, float:5.14E-43)
            r2.<init>(r6, r4)
            INST_PCMPEQD = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PCMPEQQ"
            r370 = r2
            r2 = 368(0x170, float:5.16E-43)
            r4.<init>(r6, r2)
            INST_PCMPEQQ = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PCMPEQW"
            r371 = r4
            r4 = 369(0x171, float:5.17E-43)
            r2.<init>(r6, r4)
            INST_PCMPEQW = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PCMPESTRI"
            r372 = r2
            r2 = 370(0x172, float:5.18E-43)
            r4.<init>(r6, r2)
            INST_PCMPESTRI = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PCMPESTRM"
            r373 = r4
            r4 = 371(0x173, float:5.2E-43)
            r2.<init>(r6, r4)
            INST_PCMPESTRM = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PCMPGTB"
            r374 = r2
            r2 = 372(0x174, float:5.21E-43)
            r4.<init>(r6, r2)
            INST_PCMPGTB = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PCMPGTD"
            r375 = r4
            r4 = 373(0x175, float:5.23E-43)
            r2.<init>(r6, r4)
            INST_PCMPGTD = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PCMPGTQ"
            r376 = r2
            r2 = 374(0x176, float:5.24E-43)
            r4.<init>(r6, r2)
            INST_PCMPGTQ = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PCMPGTW"
            r377 = r4
            r4 = 375(0x177, float:5.25E-43)
            r2.<init>(r6, r4)
            INST_PCMPGTW = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PCMPISTRI"
            r378 = r2
            r2 = 376(0x178, float:5.27E-43)
            r4.<init>(r6, r2)
            INST_PCMPISTRI = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PCMPISTRM"
            r379 = r4
            r4 = 377(0x179, float:5.28E-43)
            r2.<init>(r6, r4)
            INST_PCMPISTRM = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PEXTRB"
            r380 = r2
            r2 = 378(0x17a, float:5.3E-43)
            r4.<init>(r6, r2)
            INST_PEXTRB = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PEXTRD"
            r381 = r4
            r4 = 379(0x17b, float:5.31E-43)
            r2.<init>(r6, r4)
            INST_PEXTRD = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PEXTRQ"
            r382 = r2
            r2 = 380(0x17c, float:5.32E-43)
            r4.<init>(r6, r2)
            INST_PEXTRQ = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PEXTRW"
            r383 = r4
            r4 = 381(0x17d, float:5.34E-43)
            r2.<init>(r6, r4)
            INST_PEXTRW = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PF2ID"
            r384 = r2
            r2 = 382(0x17e, float:5.35E-43)
            r4.<init>(r6, r2)
            INST_PF2ID = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PF2IW"
            r385 = r4
            r4 = 383(0x17f, float:5.37E-43)
            r2.<init>(r6, r4)
            INST_PF2IW = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PFACC"
            r386 = r2
            r2 = 384(0x180, float:5.38E-43)
            r4.<init>(r6, r2)
            INST_PFACC = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PFADD"
            r387 = r4
            r4 = 385(0x181, float:5.4E-43)
            r2.<init>(r6, r4)
            INST_PFADD = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PFCMPEQ"
            r388 = r2
            r2 = 386(0x182, float:5.41E-43)
            r4.<init>(r6, r2)
            INST_PFCMPEQ = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PFCMPGE"
            r389 = r4
            r4 = 387(0x183, float:5.42E-43)
            r2.<init>(r6, r4)
            INST_PFCMPGE = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PFCMPGT"
            r390 = r2
            r2 = 388(0x184, float:5.44E-43)
            r4.<init>(r6, r2)
            INST_PFCMPGT = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PFMAX"
            r391 = r4
            r4 = 389(0x185, float:5.45E-43)
            r2.<init>(r6, r4)
            INST_PFMAX = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PFMIN"
            r392 = r2
            r2 = 390(0x186, float:5.47E-43)
            r4.<init>(r6, r2)
            INST_PFMIN = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PFMUL"
            r393 = r4
            r4 = 391(0x187, float:5.48E-43)
            r2.<init>(r6, r4)
            INST_PFMUL = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PFNACC"
            r394 = r2
            r2 = 392(0x188, float:5.5E-43)
            r4.<init>(r6, r2)
            INST_PFNACC = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PFPNACC"
            r395 = r4
            r4 = 393(0x189, float:5.51E-43)
            r2.<init>(r6, r4)
            INST_PFPNACC = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PFRCP"
            r396 = r2
            r2 = 394(0x18a, float:5.52E-43)
            r4.<init>(r6, r2)
            INST_PFRCP = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PFRCPIT1"
            r397 = r4
            r4 = 395(0x18b, float:5.54E-43)
            r2.<init>(r6, r4)
            INST_PFRCPIT1 = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PFRCPIT2"
            r398 = r2
            r2 = 396(0x18c, float:5.55E-43)
            r4.<init>(r6, r2)
            INST_PFRCPIT2 = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PFRSQIT1"
            r399 = r4
            r4 = 397(0x18d, float:5.56E-43)
            r2.<init>(r6, r4)
            INST_PFRSQIT1 = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PFRSQRT"
            r400 = r2
            r2 = 398(0x18e, float:5.58E-43)
            r4.<init>(r6, r2)
            INST_PFRSQRT = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PFSUB"
            r401 = r4
            r4 = 399(0x18f, float:5.59E-43)
            r2.<init>(r6, r4)
            INST_PFSUB = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PFSUBR"
            r402 = r2
            r2 = 400(0x190, float:5.6E-43)
            r4.<init>(r6, r2)
            INST_PFSUBR = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PHADDD"
            r403 = r4
            r4 = 401(0x191, float:5.62E-43)
            r2.<init>(r6, r4)
            INST_PHADDD = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PHADDSW"
            r404 = r2
            r2 = 402(0x192, float:5.63E-43)
            r4.<init>(r6, r2)
            INST_PHADDSW = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PHADDW"
            r405 = r4
            r4 = 403(0x193, float:5.65E-43)
            r2.<init>(r6, r4)
            INST_PHADDW = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PHMINPOSUW"
            r406 = r2
            r2 = 404(0x194, float:5.66E-43)
            r4.<init>(r6, r2)
            INST_PHMINPOSUW = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PHSUBD"
            r407 = r4
            r4 = 405(0x195, float:5.68E-43)
            r2.<init>(r6, r4)
            INST_PHSUBD = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PHSUBSW"
            r408 = r2
            r2 = 406(0x196, float:5.69E-43)
            r4.<init>(r6, r2)
            INST_PHSUBSW = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PHSUBW"
            r409 = r4
            r4 = 407(0x197, float:5.7E-43)
            r2.<init>(r6, r4)
            INST_PHSUBW = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PI2FD"
            r410 = r2
            r2 = 408(0x198, float:5.72E-43)
            r4.<init>(r6, r2)
            INST_PI2FD = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PI2FW"
            r411 = r4
            r4 = 409(0x199, float:5.73E-43)
            r2.<init>(r6, r4)
            INST_PI2FW = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PINSRB"
            r412 = r2
            r2 = 410(0x19a, float:5.75E-43)
            r4.<init>(r6, r2)
            INST_PINSRB = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PINSRD"
            r413 = r4
            r4 = 411(0x19b, float:5.76E-43)
            r2.<init>(r6, r4)
            INST_PINSRD = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PINSRQ"
            r414 = r2
            r2 = 412(0x19c, float:5.77E-43)
            r4.<init>(r6, r2)
            INST_PINSRQ = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PINSRW"
            r415 = r4
            r4 = 413(0x19d, float:5.79E-43)
            r2.<init>(r6, r4)
            INST_PINSRW = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PMADDUBSW"
            r416 = r2
            r2 = 414(0x19e, float:5.8E-43)
            r4.<init>(r6, r2)
            INST_PMADDUBSW = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PMADDWD"
            r417 = r4
            r4 = 415(0x19f, float:5.82E-43)
            r2.<init>(r6, r4)
            INST_PMADDWD = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PMAXSB"
            r418 = r2
            r2 = 416(0x1a0, float:5.83E-43)
            r4.<init>(r6, r2)
            INST_PMAXSB = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PMAXSD"
            r419 = r4
            r4 = 417(0x1a1, float:5.84E-43)
            r2.<init>(r6, r4)
            INST_PMAXSD = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PMAXSW"
            r420 = r2
            r2 = 418(0x1a2, float:5.86E-43)
            r4.<init>(r6, r2)
            INST_PMAXSW = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PMAXUB"
            r421 = r4
            r4 = 419(0x1a3, float:5.87E-43)
            r2.<init>(r6, r4)
            INST_PMAXUB = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PMAXUD"
            r422 = r2
            r2 = 420(0x1a4, float:5.89E-43)
            r4.<init>(r6, r2)
            INST_PMAXUD = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PMAXUW"
            r423 = r4
            r4 = 421(0x1a5, float:5.9E-43)
            r2.<init>(r6, r4)
            INST_PMAXUW = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PMINSB"
            r424 = r2
            r2 = 422(0x1a6, float:5.91E-43)
            r4.<init>(r6, r2)
            INST_PMINSB = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PMINSD"
            r425 = r4
            r4 = 423(0x1a7, float:5.93E-43)
            r2.<init>(r6, r4)
            INST_PMINSD = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PMINSW"
            r426 = r2
            r2 = 424(0x1a8, float:5.94E-43)
            r4.<init>(r6, r2)
            INST_PMINSW = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PMINUB"
            r427 = r4
            r4 = 425(0x1a9, float:5.96E-43)
            r2.<init>(r6, r4)
            INST_PMINUB = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PMINUD"
            r428 = r2
            r2 = 426(0x1aa, float:5.97E-43)
            r4.<init>(r6, r2)
            INST_PMINUD = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PMINUW"
            r429 = r4
            r4 = 427(0x1ab, float:5.98E-43)
            r2.<init>(r6, r4)
            INST_PMINUW = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PMOVMSKB"
            r430 = r2
            r2 = 428(0x1ac, float:6.0E-43)
            r4.<init>(r6, r2)
            INST_PMOVMSKB = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PMOVSXBD"
            r431 = r4
            r4 = 429(0x1ad, float:6.01E-43)
            r2.<init>(r6, r4)
            INST_PMOVSXBD = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PMOVSXBQ"
            r432 = r2
            r2 = 430(0x1ae, float:6.03E-43)
            r4.<init>(r6, r2)
            INST_PMOVSXBQ = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PMOVSXBW"
            r433 = r4
            r4 = 431(0x1af, float:6.04E-43)
            r2.<init>(r6, r4)
            INST_PMOVSXBW = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PMOVSXDQ"
            r434 = r2
            r2 = 432(0x1b0, float:6.05E-43)
            r4.<init>(r6, r2)
            INST_PMOVSXDQ = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PMOVSXWD"
            r435 = r4
            r4 = 433(0x1b1, float:6.07E-43)
            r2.<init>(r6, r4)
            INST_PMOVSXWD = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PMOVSXWQ"
            r436 = r2
            r2 = 434(0x1b2, float:6.08E-43)
            r4.<init>(r6, r2)
            INST_PMOVSXWQ = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PMOVZXBD"
            r437 = r4
            r4 = 435(0x1b3, float:6.1E-43)
            r2.<init>(r6, r4)
            INST_PMOVZXBD = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PMOVZXBQ"
            r438 = r2
            r2 = 436(0x1b4, float:6.11E-43)
            r4.<init>(r6, r2)
            INST_PMOVZXBQ = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PMOVZXBW"
            r439 = r4
            r4 = 437(0x1b5, float:6.12E-43)
            r2.<init>(r6, r4)
            INST_PMOVZXBW = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PMOVZXDQ"
            r440 = r2
            r2 = 438(0x1b6, float:6.14E-43)
            r4.<init>(r6, r2)
            INST_PMOVZXDQ = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PMOVZXWD"
            r441 = r4
            r4 = 439(0x1b7, float:6.15E-43)
            r2.<init>(r6, r4)
            INST_PMOVZXWD = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PMOVZXWQ"
            r442 = r2
            r2 = 440(0x1b8, float:6.17E-43)
            r4.<init>(r6, r2)
            INST_PMOVZXWQ = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PMULDQ"
            r443 = r4
            r4 = 441(0x1b9, float:6.18E-43)
            r2.<init>(r6, r4)
            INST_PMULDQ = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PMULHRSW"
            r444 = r2
            r2 = 442(0x1ba, float:6.2E-43)
            r4.<init>(r6, r2)
            INST_PMULHRSW = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PMULHUW"
            r445 = r4
            r4 = 443(0x1bb, float:6.21E-43)
            r2.<init>(r6, r4)
            INST_PMULHUW = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PMULHW"
            r446 = r2
            r2 = 444(0x1bc, float:6.22E-43)
            r4.<init>(r6, r2)
            INST_PMULHW = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PMULLD"
            r447 = r4
            r4 = 445(0x1bd, float:6.24E-43)
            r2.<init>(r6, r4)
            INST_PMULLD = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PMULLW"
            r448 = r2
            r2 = 446(0x1be, float:6.25E-43)
            r4.<init>(r6, r2)
            INST_PMULLW = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PMULUDQ"
            r449 = r4
            r4 = 447(0x1bf, float:6.26E-43)
            r2.<init>(r6, r4)
            INST_PMULUDQ = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_POP"
            r450 = r2
            r2 = 448(0x1c0, float:6.28E-43)
            r4.<init>(r6, r2)
            INST_POP = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_POPAD"
            r451 = r4
            r4 = 449(0x1c1, float:6.29E-43)
            r2.<init>(r6, r4)
            INST_POPAD = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_POPCNT"
            r452 = r2
            r2 = 450(0x1c2, float:6.3E-43)
            r4.<init>(r6, r2)
            INST_POPCNT = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_POPFD"
            r453 = r4
            r4 = 451(0x1c3, float:6.32E-43)
            r2.<init>(r6, r4)
            INST_POPFD = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_POPFQ"
            r454 = r2
            r2 = 452(0x1c4, float:6.33E-43)
            r4.<init>(r6, r2)
            INST_POPFQ = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_POR"
            r455 = r4
            r4 = 453(0x1c5, float:6.35E-43)
            r2.<init>(r6, r4)
            INST_POR = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PREFETCH"
            r456 = r2
            r2 = 454(0x1c6, float:6.36E-43)
            r4.<init>(r6, r2)
            INST_PREFETCH = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PSADBW"
            r457 = r4
            r4 = 455(0x1c7, float:6.38E-43)
            r2.<init>(r6, r4)
            INST_PSADBW = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PSHUFB"
            r458 = r2
            r2 = 456(0x1c8, float:6.39E-43)
            r4.<init>(r6, r2)
            INST_PSHUFB = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PSHUFD"
            r459 = r4
            r4 = 457(0x1c9, float:6.4E-43)
            r2.<init>(r6, r4)
            INST_PSHUFD = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PSHUFW"
            r460 = r2
            r2 = 458(0x1ca, float:6.42E-43)
            r4.<init>(r6, r2)
            INST_PSHUFW = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PSHUFHW"
            r461 = r4
            r4 = 459(0x1cb, float:6.43E-43)
            r2.<init>(r6, r4)
            INST_PSHUFHW = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PSHUFLW"
            r462 = r2
            r2 = 460(0x1cc, float:6.45E-43)
            r4.<init>(r6, r2)
            INST_PSHUFLW = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PSIGNB"
            r463 = r4
            r4 = 461(0x1cd, float:6.46E-43)
            r2.<init>(r6, r4)
            INST_PSIGNB = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PSIGND"
            r464 = r2
            r2 = 462(0x1ce, float:6.47E-43)
            r4.<init>(r6, r2)
            INST_PSIGND = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PSIGNW"
            r465 = r4
            r4 = 463(0x1cf, float:6.49E-43)
            r2.<init>(r6, r4)
            INST_PSIGNW = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PSLLD"
            r466 = r2
            r2 = 464(0x1d0, float:6.5E-43)
            r4.<init>(r6, r2)
            INST_PSLLD = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PSLLDQ"
            r467 = r4
            r4 = 465(0x1d1, float:6.52E-43)
            r2.<init>(r6, r4)
            INST_PSLLDQ = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PSLLQ"
            r468 = r2
            r2 = 466(0x1d2, float:6.53E-43)
            r4.<init>(r6, r2)
            INST_PSLLQ = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PSLLW"
            r469 = r4
            r4 = 467(0x1d3, float:6.54E-43)
            r2.<init>(r6, r4)
            INST_PSLLW = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PSRAD"
            r470 = r2
            r2 = 468(0x1d4, float:6.56E-43)
            r4.<init>(r6, r2)
            INST_PSRAD = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PSRAW"
            r471 = r4
            r4 = 469(0x1d5, float:6.57E-43)
            r2.<init>(r6, r4)
            INST_PSRAW = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PSRLD"
            r472 = r2
            r2 = 470(0x1d6, float:6.59E-43)
            r4.<init>(r6, r2)
            INST_PSRLD = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PSRLDQ"
            r473 = r4
            r4 = 471(0x1d7, float:6.6E-43)
            r2.<init>(r6, r4)
            INST_PSRLDQ = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PSRLQ"
            r474 = r2
            r2 = 472(0x1d8, float:6.61E-43)
            r4.<init>(r6, r2)
            INST_PSRLQ = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PSRLW"
            r475 = r4
            r4 = 473(0x1d9, float:6.63E-43)
            r2.<init>(r6, r4)
            INST_PSRLW = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PSUBB"
            r476 = r2
            r2 = 474(0x1da, float:6.64E-43)
            r4.<init>(r6, r2)
            INST_PSUBB = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PSUBD"
            r477 = r4
            r4 = 475(0x1db, float:6.66E-43)
            r2.<init>(r6, r4)
            INST_PSUBD = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PSUBQ"
            r478 = r2
            r2 = 476(0x1dc, float:6.67E-43)
            r4.<init>(r6, r2)
            INST_PSUBQ = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PSUBSB"
            r479 = r4
            r4 = 477(0x1dd, float:6.68E-43)
            r2.<init>(r6, r4)
            INST_PSUBSB = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PSUBSW"
            r480 = r2
            r2 = 478(0x1de, float:6.7E-43)
            r4.<init>(r6, r2)
            INST_PSUBSW = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PSUBUSB"
            r481 = r4
            r4 = 479(0x1df, float:6.71E-43)
            r2.<init>(r6, r4)
            INST_PSUBUSB = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PSUBUSW"
            r482 = r2
            r2 = 480(0x1e0, float:6.73E-43)
            r4.<init>(r6, r2)
            INST_PSUBUSW = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PSUBW"
            r483 = r4
            r4 = 481(0x1e1, float:6.74E-43)
            r2.<init>(r6, r4)
            INST_PSUBW = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PSWAPD"
            r484 = r2
            r2 = 482(0x1e2, float:6.75E-43)
            r4.<init>(r6, r2)
            INST_PSWAPD = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PTEST"
            r485 = r4
            r4 = 483(0x1e3, float:6.77E-43)
            r2.<init>(r6, r4)
            INST_PTEST = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PUNPCKHBW"
            r486 = r2
            r2 = 484(0x1e4, float:6.78E-43)
            r4.<init>(r6, r2)
            INST_PUNPCKHBW = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PUNPCKHDQ"
            r487 = r4
            r4 = 485(0x1e5, float:6.8E-43)
            r2.<init>(r6, r4)
            INST_PUNPCKHDQ = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PUNPCKHQDQ"
            r488 = r2
            r2 = 486(0x1e6, float:6.81E-43)
            r4.<init>(r6, r2)
            INST_PUNPCKHQDQ = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PUNPCKHWD"
            r489 = r4
            r4 = 487(0x1e7, float:6.82E-43)
            r2.<init>(r6, r4)
            INST_PUNPCKHWD = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PUNPCKLBW"
            r490 = r2
            r2 = 488(0x1e8, float:6.84E-43)
            r4.<init>(r6, r2)
            INST_PUNPCKLBW = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PUNPCKLDQ"
            r491 = r4
            r4 = 489(0x1e9, float:6.85E-43)
            r2.<init>(r6, r4)
            INST_PUNPCKLDQ = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PUNPCKLQDQ"
            r492 = r2
            r2 = 490(0x1ea, float:6.87E-43)
            r4.<init>(r6, r2)
            INST_PUNPCKLQDQ = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PUNPCKLWD"
            r493 = r4
            r4 = 491(0x1eb, float:6.88E-43)
            r2.<init>(r6, r4)
            INST_PUNPCKLWD = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PUSH"
            r494 = r2
            r2 = 492(0x1ec, float:6.9E-43)
            r4.<init>(r6, r2)
            INST_PUSH = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PUSHAD"
            r495 = r4
            r4 = 493(0x1ed, float:6.91E-43)
            r2.<init>(r6, r4)
            INST_PUSHAD = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PUSHFD"
            r496 = r2
            r2 = 494(0x1ee, float:6.92E-43)
            r4.<init>(r6, r2)
            INST_PUSHFD = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PUSHFQ"
            r497 = r4
            r4 = 495(0x1ef, float:6.94E-43)
            r2.<init>(r6, r4)
            INST_PUSHFQ = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_PXOR"
            r498 = r2
            r2 = 496(0x1f0, float:6.95E-43)
            r4.<init>(r6, r2)
            INST_PXOR = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_RCL"
            r499 = r4
            r4 = 497(0x1f1, float:6.96E-43)
            r2.<init>(r6, r4)
            INST_RCL = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_RCPPS"
            r500 = r2
            r2 = 498(0x1f2, float:6.98E-43)
            r4.<init>(r6, r2)
            INST_RCPPS = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_RCPSS"
            r501 = r4
            r4 = 499(0x1f3, float:6.99E-43)
            r2.<init>(r6, r4)
            INST_RCPSS = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_RCR"
            r502 = r2
            r2 = 500(0x1f4, float:7.0E-43)
            r4.<init>(r6, r2)
            INST_RCR = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_RDTSC"
            r503 = r4
            r4 = 501(0x1f5, float:7.02E-43)
            r2.<init>(r6, r4)
            INST_RDTSC = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_RDTSCP"
            r504 = r2
            r2 = 502(0x1f6, float:7.03E-43)
            r4.<init>(r6, r2)
            INST_RDTSCP = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_RET"
            r505 = r4
            r4 = 503(0x1f7, float:7.05E-43)
            r2.<init>(r6, r4)
            INST_RET = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_ROL"
            r506 = r2
            r2 = 504(0x1f8, float:7.06E-43)
            r4.<init>(r6, r2)
            INST_ROL = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_ROR"
            r507 = r4
            r4 = 505(0x1f9, float:7.08E-43)
            r2.<init>(r6, r4)
            INST_ROR = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_ROUNDPD"
            r508 = r2
            r2 = 506(0x1fa, float:7.09E-43)
            r4.<init>(r6, r2)
            INST_ROUNDPD = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_ROUNDPS"
            r509 = r4
            r4 = 507(0x1fb, float:7.1E-43)
            r2.<init>(r6, r4)
            INST_ROUNDPS = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_ROUNDSD"
            r510 = r2
            r2 = 508(0x1fc, float:7.12E-43)
            r4.<init>(r6, r2)
            INST_ROUNDSD = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_ROUNDSS"
            r511 = r4
            r4 = 509(0x1fd, float:7.13E-43)
            r2.<init>(r6, r4)
            INST_ROUNDSS = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_RSQRTPS"
            r512 = r2
            r2 = 510(0x1fe, float:7.15E-43)
            r4.<init>(r6, r2)
            INST_RSQRTPS = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_RSQRTSS"
            r513 = r4
            r4 = 511(0x1ff, float:7.16E-43)
            r2.<init>(r6, r4)
            INST_RSQRTSS = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SAHF"
            r514 = r2
            r2 = 512(0x200, float:7.175E-43)
            r4.<init>(r6, r2)
            INST_SAHF = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SAL"
            r515 = r4
            r4 = 513(0x201, float:7.19E-43)
            r2.<init>(r6, r4)
            INST_SAL = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SAR"
            r516 = r2
            r2 = 514(0x202, float:7.2E-43)
            r4.<init>(r6, r2)
            INST_SAR = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SBB"
            r517 = r4
            r4 = 515(0x203, float:7.22E-43)
            r2.<init>(r6, r4)
            INST_SBB = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SETA"
            r518 = r2
            r2 = 516(0x204, float:7.23E-43)
            r4.<init>(r6, r2)
            INST_SETA = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SETAE"
            r519 = r4
            r4 = 517(0x205, float:7.24E-43)
            r2.<init>(r6, r4)
            INST_SETAE = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SETB"
            r520 = r2
            r2 = 518(0x206, float:7.26E-43)
            r4.<init>(r6, r2)
            INST_SETB = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SETBE"
            r521 = r4
            r4 = 519(0x207, float:7.27E-43)
            r2.<init>(r6, r4)
            INST_SETBE = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SETC"
            r522 = r2
            r2 = 520(0x208, float:7.29E-43)
            r4.<init>(r6, r2)
            INST_SETC = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SETE"
            r523 = r4
            r4 = 521(0x209, float:7.3E-43)
            r2.<init>(r6, r4)
            INST_SETE = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SETG"
            r524 = r2
            r2 = 522(0x20a, float:7.31E-43)
            r4.<init>(r6, r2)
            INST_SETG = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SETGE"
            r525 = r4
            r4 = 523(0x20b, float:7.33E-43)
            r2.<init>(r6, r4)
            INST_SETGE = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SETL"
            r526 = r2
            r2 = 524(0x20c, float:7.34E-43)
            r4.<init>(r6, r2)
            INST_SETL = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SETLE"
            r527 = r4
            r4 = 525(0x20d, float:7.36E-43)
            r2.<init>(r6, r4)
            INST_SETLE = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SETNA"
            r528 = r2
            r2 = 526(0x20e, float:7.37E-43)
            r4.<init>(r6, r2)
            INST_SETNA = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SETNAE"
            r529 = r4
            r4 = 527(0x20f, float:7.38E-43)
            r2.<init>(r6, r4)
            INST_SETNAE = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SETNB"
            r530 = r2
            r2 = 528(0x210, float:7.4E-43)
            r4.<init>(r6, r2)
            INST_SETNB = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SETNBE"
            r531 = r4
            r4 = 529(0x211, float:7.41E-43)
            r2.<init>(r6, r4)
            INST_SETNBE = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SETNC"
            r532 = r2
            r2 = 530(0x212, float:7.43E-43)
            r4.<init>(r6, r2)
            INST_SETNC = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SETNE"
            r533 = r4
            r4 = 531(0x213, float:7.44E-43)
            r2.<init>(r6, r4)
            INST_SETNE = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SETNG"
            r534 = r2
            r2 = 532(0x214, float:7.45E-43)
            r4.<init>(r6, r2)
            INST_SETNG = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SETNGE"
            r535 = r4
            r4 = 533(0x215, float:7.47E-43)
            r2.<init>(r6, r4)
            INST_SETNGE = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SETNL"
            r536 = r2
            r2 = 534(0x216, float:7.48E-43)
            r4.<init>(r6, r2)
            INST_SETNL = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SETNLE"
            r537 = r4
            r4 = 535(0x217, float:7.5E-43)
            r2.<init>(r6, r4)
            INST_SETNLE = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SETNO"
            r538 = r2
            r2 = 536(0x218, float:7.51E-43)
            r4.<init>(r6, r2)
            INST_SETNO = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SETNP"
            r539 = r4
            r4 = 537(0x219, float:7.52E-43)
            r2.<init>(r6, r4)
            INST_SETNP = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SETNS"
            r540 = r2
            r2 = 538(0x21a, float:7.54E-43)
            r4.<init>(r6, r2)
            INST_SETNS = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SETNZ"
            r541 = r4
            r4 = 539(0x21b, float:7.55E-43)
            r2.<init>(r6, r4)
            INST_SETNZ = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SETO"
            r542 = r2
            r2 = 540(0x21c, float:7.57E-43)
            r4.<init>(r6, r2)
            INST_SETO = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SETP"
            r543 = r4
            r4 = 541(0x21d, float:7.58E-43)
            r2.<init>(r6, r4)
            INST_SETP = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SETPE"
            r544 = r2
            r2 = 542(0x21e, float:7.6E-43)
            r4.<init>(r6, r2)
            INST_SETPE = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SETPO"
            r545 = r4
            r4 = 543(0x21f, float:7.61E-43)
            r2.<init>(r6, r4)
            INST_SETPO = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SETS"
            r546 = r2
            r2 = 544(0x220, float:7.62E-43)
            r4.<init>(r6, r2)
            INST_SETS = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SETZ"
            r547 = r4
            r4 = 545(0x221, float:7.64E-43)
            r2.<init>(r6, r4)
            INST_SETZ = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SFENCE"
            r548 = r2
            r2 = 546(0x222, float:7.65E-43)
            r4.<init>(r6, r2)
            INST_SFENCE = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SHL"
            r549 = r4
            r4 = 547(0x223, float:7.67E-43)
            r2.<init>(r6, r4)
            INST_SHL = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SHLD"
            r550 = r2
            r2 = 548(0x224, float:7.68E-43)
            r4.<init>(r6, r2)
            INST_SHLD = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SHR"
            r551 = r4
            r4 = 549(0x225, float:7.7E-43)
            r2.<init>(r6, r4)
            INST_SHR = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SHRD"
            r552 = r2
            r2 = 550(0x226, float:7.71E-43)
            r4.<init>(r6, r2)
            INST_SHRD = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SHUFPS"
            r553 = r4
            r4 = 551(0x227, float:7.72E-43)
            r2.<init>(r6, r4)
            INST_SHUFPS = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SQRTPD"
            r554 = r2
            r2 = 552(0x228, float:7.74E-43)
            r4.<init>(r6, r2)
            INST_SQRTPD = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SQRTPS"
            r555 = r4
            r4 = 553(0x229, float:7.75E-43)
            r2.<init>(r6, r4)
            INST_SQRTPS = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SQRTSD"
            r556 = r2
            r2 = 554(0x22a, float:7.76E-43)
            r4.<init>(r6, r2)
            INST_SQRTSD = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SQRTSS"
            r557 = r4
            r4 = 555(0x22b, float:7.78E-43)
            r2.<init>(r6, r4)
            INST_SQRTSS = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_STC"
            r558 = r2
            r2 = 556(0x22c, float:7.79E-43)
            r4.<init>(r6, r2)
            INST_STC = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_STD"
            r559 = r4
            r4 = 557(0x22d, float:7.8E-43)
            r2.<init>(r6, r4)
            INST_STD = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_STMXCSR"
            r560 = r2
            r2 = 558(0x22e, float:7.82E-43)
            r4.<init>(r6, r2)
            INST_STMXCSR = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SUB"
            r561 = r4
            r4 = 559(0x22f, float:7.83E-43)
            r2.<init>(r6, r4)
            INST_SUB = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SUBPD"
            r562 = r2
            r2 = 560(0x230, float:7.85E-43)
            r4.<init>(r6, r2)
            INST_SUBPD = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SUBPS"
            r563 = r4
            r4 = 561(0x231, float:7.86E-43)
            r2.<init>(r6, r4)
            INST_SUBPS = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SUBSD"
            r564 = r2
            r2 = 562(0x232, float:7.88E-43)
            r4.<init>(r6, r2)
            INST_SUBSD = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_SUBSS"
            r565 = r4
            r4 = 563(0x233, float:7.89E-43)
            r2.<init>(r6, r4)
            INST_SUBSS = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_TEST"
            r566 = r2
            r2 = 564(0x234, float:7.9E-43)
            r4.<init>(r6, r2)
            INST_TEST = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_UCOMISD"
            r567 = r4
            r4 = 565(0x235, float:7.92E-43)
            r2.<init>(r6, r4)
            INST_UCOMISD = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_UCOMISS"
            r568 = r2
            r2 = 566(0x236, float:7.93E-43)
            r4.<init>(r6, r2)
            INST_UCOMISS = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_UD2"
            r569 = r4
            r4 = 567(0x237, float:7.95E-43)
            r2.<init>(r6, r4)
            INST_UD2 = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_UNPCKHPD"
            r570 = r2
            r2 = 568(0x238, float:7.96E-43)
            r4.<init>(r6, r2)
            INST_UNPCKHPD = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_UNPCKHPS"
            r571 = r4
            r4 = 569(0x239, float:7.97E-43)
            r2.<init>(r6, r4)
            INST_UNPCKHPS = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_UNPCKLPD"
            r572 = r2
            r2 = 570(0x23a, float:7.99E-43)
            r4.<init>(r6, r2)
            INST_UNPCKLPD = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_UNPCKLPS"
            r573 = r4
            r4 = 571(0x23b, float:8.0E-43)
            r2.<init>(r6, r4)
            INST_UNPCKLPS = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_XADD"
            r574 = r2
            r2 = 572(0x23c, float:8.02E-43)
            r4.<init>(r6, r2)
            INST_XADD = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_XCHG"
            r575 = r4
            r4 = 573(0x23d, float:8.03E-43)
            r2.<init>(r6, r4)
            INST_XCHG = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_XOR"
            r576 = r2
            r2 = 574(0x23e, float:8.04E-43)
            r4.<init>(r6, r2)
            INST_XOR = r4
            com.kenai.jnr.x86asm.INST_CODE r2 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_XORPD"
            r577 = r4
            r4 = 575(0x23f, float:8.06E-43)
            r2.<init>(r6, r4)
            INST_XORPD = r2
            com.kenai.jnr.x86asm.INST_CODE r4 = new com.kenai.jnr.x86asm.INST_CODE
            java.lang.String r6 = "INST_XORPS"
            r578 = r2
            r2 = 576(0x240, float:8.07E-43)
            r4.<init>(r6, r2)
            INST_XORPS = r4
            r2 = 577(0x241, float:8.09E-43)
            com.kenai.jnr.x86asm.INST_CODE[] r2 = new com.kenai.jnr.x86asm.INST_CODE[r2]
            r6 = 0
            r2[r6] = r0
            r0 = 1
            r2[r0] = r1
            r0 = 2
            r2[r0] = r3
            r0 = 3
            r2[r0] = r5
            r0 = 4
            r2[r0] = r7
            r0 = 5
            r2[r0] = r9
            r0 = 6
            r2[r0] = r11
            r0 = 7
            r2[r0] = r13
            r0 = 8
            r2[r0] = r15
            r0 = 9
            r2[r0] = r14
            r0 = 10
            r2[r0] = r12
            r0 = 11
            r2[r0] = r10
            r0 = 12
            r2[r0] = r8
            r0 = 13
            r2[r0] = r16
            r0 = 14
            r2[r0] = r17
            r0 = 15
            r2[r0] = r18
            r0 = 16
            r2[r0] = r19
            r0 = 17
            r2[r0] = r20
            r0 = 18
            r2[r0] = r21
            r0 = 19
            r2[r0] = r22
            r0 = 20
            r2[r0] = r23
            r0 = 21
            r2[r0] = r24
            r0 = 22
            r2[r0] = r25
            r0 = 23
            r2[r0] = r26
            r0 = 24
            r2[r0] = r27
            r0 = 25
            r2[r0] = r28
            r0 = 26
            r2[r0] = r29
            r0 = 27
            r2[r0] = r30
            r0 = 28
            r2[r0] = r31
            r0 = 29
            r2[r0] = r32
            r0 = 30
            r2[r0] = r33
            r0 = 31
            r2[r0] = r34
            r0 = 32
            r2[r0] = r35
            r0 = 33
            r2[r0] = r36
            r0 = 34
            r2[r0] = r37
            r0 = 35
            r2[r0] = r38
            r0 = 36
            r2[r0] = r39
            r0 = 37
            r2[r0] = r40
            r0 = 38
            r2[r0] = r41
            r0 = 39
            r2[r0] = r42
            r0 = 40
            r2[r0] = r43
            r0 = 41
            r2[r0] = r44
            r0 = 42
            r2[r0] = r45
            r0 = 43
            r2[r0] = r46
            r0 = 44
            r2[r0] = r47
            r0 = 45
            r2[r0] = r48
            r0 = 46
            r2[r0] = r49
            r0 = 47
            r2[r0] = r50
            r0 = 48
            r2[r0] = r51
            r0 = 49
            r2[r0] = r52
            r0 = 50
            r2[r0] = r53
            r0 = 51
            r2[r0] = r54
            r0 = 52
            r2[r0] = r55
            r0 = 53
            r2[r0] = r56
            r0 = 54
            r2[r0] = r57
            r0 = 55
            r2[r0] = r58
            r0 = 56
            r2[r0] = r59
            r0 = 57
            r2[r0] = r60
            r0 = 58
            r2[r0] = r61
            r0 = 59
            r2[r0] = r62
            r0 = 60
            r2[r0] = r63
            r0 = 61
            r2[r0] = r64
            r0 = 62
            r2[r0] = r65
            r0 = 63
            r2[r0] = r66
            r0 = 64
            r2[r0] = r67
            r0 = 65
            r2[r0] = r68
            r0 = 66
            r2[r0] = r69
            r0 = 67
            r2[r0] = r70
            r0 = 68
            r2[r0] = r71
            r0 = 69
            r2[r0] = r72
            r0 = 70
            r2[r0] = r73
            r0 = 71
            r2[r0] = r74
            r0 = 72
            r2[r0] = r75
            r0 = 73
            r2[r0] = r76
            r0 = 74
            r2[r0] = r77
            r0 = 75
            r2[r0] = r78
            r0 = 76
            r2[r0] = r79
            r0 = 77
            r2[r0] = r80
            r0 = 78
            r2[r0] = r81
            r0 = 79
            r2[r0] = r82
            r0 = 80
            r2[r0] = r83
            r0 = 81
            r2[r0] = r84
            r0 = 82
            r2[r0] = r85
            r0 = 83
            r2[r0] = r86
            r0 = 84
            r2[r0] = r87
            r0 = 85
            r2[r0] = r88
            r0 = 86
            r2[r0] = r89
            r0 = 87
            r2[r0] = r90
            r0 = 88
            r2[r0] = r91
            r0 = 89
            r2[r0] = r92
            r0 = 90
            r2[r0] = r93
            r0 = 91
            r2[r0] = r94
            r0 = 92
            r2[r0] = r95
            r0 = 93
            r2[r0] = r96
            r0 = 94
            r2[r0] = r97
            r0 = 95
            r2[r0] = r98
            r0 = 96
            r2[r0] = r99
            r0 = 97
            r2[r0] = r100
            r0 = 98
            r2[r0] = r101
            r0 = 99
            r2[r0] = r102
            r0 = 100
            r2[r0] = r103
            r0 = 101(0x65, float:1.42E-43)
            r2[r0] = r104
            r0 = 102(0x66, float:1.43E-43)
            r2[r0] = r105
            r0 = 103(0x67, float:1.44E-43)
            r2[r0] = r106
            r0 = 104(0x68, float:1.46E-43)
            r2[r0] = r107
            r0 = 105(0x69, float:1.47E-43)
            r2[r0] = r108
            r0 = 106(0x6a, float:1.49E-43)
            r2[r0] = r109
            r0 = 107(0x6b, float:1.5E-43)
            r2[r0] = r110
            r0 = 108(0x6c, float:1.51E-43)
            r2[r0] = r111
            r0 = 109(0x6d, float:1.53E-43)
            r2[r0] = r112
            r0 = 110(0x6e, float:1.54E-43)
            r2[r0] = r113
            r0 = 111(0x6f, float:1.56E-43)
            r2[r0] = r114
            r0 = 112(0x70, float:1.57E-43)
            r2[r0] = r115
            r0 = 113(0x71, float:1.58E-43)
            r2[r0] = r116
            r0 = 114(0x72, float:1.6E-43)
            r2[r0] = r117
            r0 = 115(0x73, float:1.61E-43)
            r2[r0] = r118
            r0 = 116(0x74, float:1.63E-43)
            r2[r0] = r119
            r0 = 117(0x75, float:1.64E-43)
            r2[r0] = r120
            r0 = 118(0x76, float:1.65E-43)
            r2[r0] = r121
            r0 = 119(0x77, float:1.67E-43)
            r2[r0] = r122
            r0 = 120(0x78, float:1.68E-43)
            r2[r0] = r123
            r0 = 121(0x79, float:1.7E-43)
            r2[r0] = r124
            r0 = 122(0x7a, float:1.71E-43)
            r2[r0] = r125
            r0 = 123(0x7b, float:1.72E-43)
            r2[r0] = r126
            r0 = 124(0x7c, float:1.74E-43)
            r2[r0] = r127
            r0 = 125(0x7d, float:1.75E-43)
            r2[r0] = r128
            r0 = 126(0x7e, float:1.77E-43)
            r2[r0] = r129
            r0 = 127(0x7f, float:1.78E-43)
            r2[r0] = r130
            r0 = 128(0x80, float:1.794E-43)
            r2[r0] = r131
            r0 = 129(0x81, float:1.81E-43)
            r2[r0] = r132
            r0 = 130(0x82, float:1.82E-43)
            r2[r0] = r133
            r0 = 131(0x83, float:1.84E-43)
            r2[r0] = r134
            r0 = 132(0x84, float:1.85E-43)
            r2[r0] = r135
            r0 = 133(0x85, float:1.86E-43)
            r2[r0] = r136
            r0 = 134(0x86, float:1.88E-43)
            r2[r0] = r137
            r0 = 135(0x87, float:1.89E-43)
            r2[r0] = r138
            r0 = 136(0x88, float:1.9E-43)
            r2[r0] = r139
            r0 = 137(0x89, float:1.92E-43)
            r2[r0] = r140
            r0 = 138(0x8a, float:1.93E-43)
            r2[r0] = r141
            r0 = 139(0x8b, float:1.95E-43)
            r2[r0] = r142
            r0 = 140(0x8c, float:1.96E-43)
            r2[r0] = r143
            r0 = 141(0x8d, float:1.98E-43)
            r2[r0] = r144
            r0 = 142(0x8e, float:1.99E-43)
            r2[r0] = r145
            r0 = 143(0x8f, float:2.0E-43)
            r2[r0] = r146
            r0 = 144(0x90, float:2.02E-43)
            r2[r0] = r147
            r0 = 145(0x91, float:2.03E-43)
            r2[r0] = r148
            r0 = 146(0x92, float:2.05E-43)
            r2[r0] = r149
            r0 = 147(0x93, float:2.06E-43)
            r2[r0] = r150
            r0 = 148(0x94, float:2.07E-43)
            r2[r0] = r151
            r0 = 149(0x95, float:2.09E-43)
            r2[r0] = r152
            r0 = 150(0x96, float:2.1E-43)
            r2[r0] = r153
            r0 = 151(0x97, float:2.12E-43)
            r2[r0] = r154
            r0 = 152(0x98, float:2.13E-43)
            r2[r0] = r155
            r0 = 153(0x99, float:2.14E-43)
            r2[r0] = r156
            r0 = 154(0x9a, float:2.16E-43)
            r2[r0] = r157
            r0 = 155(0x9b, float:2.17E-43)
            r2[r0] = r158
            r0 = 156(0x9c, float:2.19E-43)
            r2[r0] = r159
            r0 = 157(0x9d, float:2.2E-43)
            r2[r0] = r160
            r0 = 158(0x9e, float:2.21E-43)
            r2[r0] = r161
            r0 = 159(0x9f, float:2.23E-43)
            r2[r0] = r162
            r0 = 160(0xa0, float:2.24E-43)
            r2[r0] = r163
            r0 = 161(0xa1, float:2.26E-43)
            r2[r0] = r164
            r0 = 162(0xa2, float:2.27E-43)
            r2[r0] = r165
            r0 = 163(0xa3, float:2.28E-43)
            r2[r0] = r166
            r0 = 164(0xa4, float:2.3E-43)
            r2[r0] = r167
            r0 = 165(0xa5, float:2.31E-43)
            r2[r0] = r168
            r0 = 166(0xa6, float:2.33E-43)
            r2[r0] = r169
            r0 = 167(0xa7, float:2.34E-43)
            r2[r0] = r170
            r0 = 168(0xa8, float:2.35E-43)
            r2[r0] = r171
            r0 = 169(0xa9, float:2.37E-43)
            r2[r0] = r172
            r0 = 170(0xaa, float:2.38E-43)
            r2[r0] = r173
            r0 = 171(0xab, float:2.4E-43)
            r2[r0] = r174
            r0 = 172(0xac, float:2.41E-43)
            r2[r0] = r175
            r0 = 173(0xad, float:2.42E-43)
            r2[r0] = r176
            r0 = 174(0xae, float:2.44E-43)
            r2[r0] = r177
            r0 = 175(0xaf, float:2.45E-43)
            r2[r0] = r178
            r0 = 176(0xb0, float:2.47E-43)
            r2[r0] = r179
            r0 = 177(0xb1, float:2.48E-43)
            r2[r0] = r180
            r0 = 178(0xb2, float:2.5E-43)
            r2[r0] = r181
            r0 = 179(0xb3, float:2.51E-43)
            r2[r0] = r182
            r0 = 180(0xb4, float:2.52E-43)
            r2[r0] = r183
            r0 = 181(0xb5, float:2.54E-43)
            r2[r0] = r184
            r0 = 182(0xb6, float:2.55E-43)
            r2[r0] = r185
            r0 = 183(0xb7, float:2.56E-43)
            r2[r0] = r186
            r0 = 184(0xb8, float:2.58E-43)
            r2[r0] = r187
            r0 = 185(0xb9, float:2.59E-43)
            r2[r0] = r188
            r0 = 186(0xba, float:2.6E-43)
            r2[r0] = r189
            r0 = 187(0xbb, float:2.62E-43)
            r2[r0] = r190
            r0 = 188(0xbc, float:2.63E-43)
            r2[r0] = r191
            r0 = 189(0xbd, float:2.65E-43)
            r2[r0] = r192
            r0 = 190(0xbe, float:2.66E-43)
            r2[r0] = r193
            r0 = 191(0xbf, float:2.68E-43)
            r2[r0] = r194
            r0 = 192(0xc0, float:2.69E-43)
            r2[r0] = r195
            r0 = 193(0xc1, float:2.7E-43)
            r2[r0] = r196
            r0 = 194(0xc2, float:2.72E-43)
            r2[r0] = r197
            r0 = 195(0xc3, float:2.73E-43)
            r2[r0] = r198
            r0 = 196(0xc4, float:2.75E-43)
            r2[r0] = r199
            r0 = 197(0xc5, float:2.76E-43)
            r2[r0] = r200
            r0 = 198(0xc6, float:2.77E-43)
            r2[r0] = r201
            r0 = 199(0xc7, float:2.79E-43)
            r2[r0] = r202
            r0 = 200(0xc8, float:2.8E-43)
            r2[r0] = r203
            r0 = 201(0xc9, float:2.82E-43)
            r2[r0] = r204
            r0 = 202(0xca, float:2.83E-43)
            r2[r0] = r205
            r0 = 203(0xcb, float:2.84E-43)
            r2[r0] = r206
            r0 = 204(0xcc, float:2.86E-43)
            r2[r0] = r207
            r0 = 205(0xcd, float:2.87E-43)
            r2[r0] = r208
            r0 = 206(0xce, float:2.89E-43)
            r2[r0] = r209
            r0 = 207(0xcf, float:2.9E-43)
            r2[r0] = r210
            r0 = 208(0xd0, float:2.91E-43)
            r2[r0] = r211
            r0 = 209(0xd1, float:2.93E-43)
            r2[r0] = r212
            r0 = 210(0xd2, float:2.94E-43)
            r2[r0] = r213
            r0 = 211(0xd3, float:2.96E-43)
            r2[r0] = r214
            r0 = 212(0xd4, float:2.97E-43)
            r2[r0] = r215
            r0 = 213(0xd5, float:2.98E-43)
            r2[r0] = r216
            r0 = 214(0xd6, float:3.0E-43)
            r2[r0] = r217
            r0 = 215(0xd7, float:3.01E-43)
            r2[r0] = r218
            r0 = 216(0xd8, float:3.03E-43)
            r2[r0] = r219
            r0 = 217(0xd9, float:3.04E-43)
            r2[r0] = r220
            r0 = 218(0xda, float:3.05E-43)
            r2[r0] = r221
            r0 = 219(0xdb, float:3.07E-43)
            r2[r0] = r222
            r0 = 220(0xdc, float:3.08E-43)
            r2[r0] = r223
            r0 = 221(0xdd, float:3.1E-43)
            r2[r0] = r224
            r0 = 222(0xde, float:3.11E-43)
            r2[r0] = r225
            r0 = 223(0xdf, float:3.12E-43)
            r2[r0] = r226
            r0 = 224(0xe0, float:3.14E-43)
            r2[r0] = r227
            r0 = 225(0xe1, float:3.15E-43)
            r2[r0] = r228
            r0 = 226(0xe2, float:3.17E-43)
            r2[r0] = r229
            r0 = 227(0xe3, float:3.18E-43)
            r2[r0] = r230
            r0 = 228(0xe4, float:3.2E-43)
            r2[r0] = r231
            r0 = 229(0xe5, float:3.21E-43)
            r2[r0] = r232
            r0 = 230(0xe6, float:3.22E-43)
            r2[r0] = r233
            r0 = 231(0xe7, float:3.24E-43)
            r1 = r234
            r2[r0] = r1
            r0 = 232(0xe8, float:3.25E-43)
            r1 = r235
            r2[r0] = r1
            r0 = 233(0xe9, float:3.27E-43)
            r1 = r236
            r2[r0] = r1
            r0 = 234(0xea, float:3.28E-43)
            r1 = r237
            r2[r0] = r1
            r0 = 235(0xeb, float:3.3E-43)
            r1 = r238
            r2[r0] = r1
            r0 = 236(0xec, float:3.31E-43)
            r1 = r239
            r2[r0] = r1
            r0 = 237(0xed, float:3.32E-43)
            r1 = r240
            r2[r0] = r1
            r0 = 238(0xee, float:3.34E-43)
            r1 = r241
            r2[r0] = r1
            r0 = 239(0xef, float:3.35E-43)
            r1 = r242
            r2[r0] = r1
            r0 = 240(0xf0, float:3.36E-43)
            r1 = r243
            r2[r0] = r1
            r0 = 241(0xf1, float:3.38E-43)
            r1 = r244
            r2[r0] = r1
            r0 = 242(0xf2, float:3.39E-43)
            r1 = r245
            r2[r0] = r1
            r0 = 243(0xf3, float:3.4E-43)
            r1 = r246
            r2[r0] = r1
            r0 = 244(0xf4, float:3.42E-43)
            r1 = r247
            r2[r0] = r1
            r0 = 245(0xf5, float:3.43E-43)
            r1 = r248
            r2[r0] = r1
            r0 = 246(0xf6, float:3.45E-43)
            r1 = r249
            r2[r0] = r1
            r0 = 247(0xf7, float:3.46E-43)
            r3 = r250
            r2[r0] = r3
            r0 = 248(0xf8, float:3.48E-43)
            r3 = r251
            r2[r0] = r3
            r0 = 249(0xf9, float:3.49E-43)
            r3 = r252
            r2[r0] = r3
            r0 = 250(0xfa, float:3.5E-43)
            r3 = r253
            r2[r0] = r3
            r0 = 251(0xfb, float:3.52E-43)
            r3 = r254
            r2[r0] = r3
            r0 = 252(0xfc, float:3.53E-43)
            r3 = r255
            r2[r0] = r3
            r0 = 253(0xfd, float:3.55E-43)
            r3 = r256
            r2[r0] = r3
            r0 = 254(0xfe, float:3.56E-43)
            r3 = r257
            r2[r0] = r3
            r0 = 255(0xff, float:3.57E-43)
            r3 = r258
            r2[r0] = r3
            r0 = 256(0x100, float:3.59E-43)
            r3 = r259
            r2[r0] = r3
            r0 = 257(0x101, float:3.6E-43)
            r3 = r260
            r2[r0] = r3
            r0 = 258(0x102, float:3.62E-43)
            r3 = r261
            r2[r0] = r3
            r0 = 259(0x103, float:3.63E-43)
            r3 = r262
            r2[r0] = r3
            r0 = 260(0x104, float:3.64E-43)
            r3 = r263
            r2[r0] = r3
            r0 = 261(0x105, float:3.66E-43)
            r3 = r264
            r2[r0] = r3
            r0 = 262(0x106, float:3.67E-43)
            r3 = r265
            r2[r0] = r3
            r0 = 263(0x107, float:3.69E-43)
            r3 = r266
            r2[r0] = r3
            r0 = 264(0x108, float:3.7E-43)
            r3 = r267
            r2[r0] = r3
            r0 = 265(0x109, float:3.71E-43)
            r3 = r268
            r2[r0] = r3
            r0 = 266(0x10a, float:3.73E-43)
            r3 = r269
            r2[r0] = r3
            r0 = 267(0x10b, float:3.74E-43)
            r3 = r270
            r2[r0] = r3
            r0 = 268(0x10c, float:3.76E-43)
            r3 = r271
            r2[r0] = r3
            r0 = 269(0x10d, float:3.77E-43)
            r3 = r272
            r2[r0] = r3
            r0 = 270(0x10e, float:3.78E-43)
            r3 = r273
            r2[r0] = r3
            r0 = 271(0x10f, float:3.8E-43)
            r3 = r274
            r2[r0] = r3
            r0 = 272(0x110, float:3.81E-43)
            r3 = r275
            r2[r0] = r3
            r0 = 273(0x111, float:3.83E-43)
            r3 = r276
            r2[r0] = r3
            r0 = 274(0x112, float:3.84E-43)
            r3 = r277
            r2[r0] = r3
            r0 = 275(0x113, float:3.85E-43)
            r3 = r278
            r2[r0] = r3
            r0 = 276(0x114, float:3.87E-43)
            r3 = r279
            r2[r0] = r3
            r0 = 277(0x115, float:3.88E-43)
            r3 = r280
            r2[r0] = r3
            r0 = 278(0x116, float:3.9E-43)
            r3 = r281
            r2[r0] = r3
            r0 = 279(0x117, float:3.91E-43)
            r3 = r282
            r2[r0] = r3
            r0 = 280(0x118, float:3.92E-43)
            r3 = r283
            r2[r0] = r3
            r0 = 281(0x119, float:3.94E-43)
            r3 = r284
            r2[r0] = r3
            r0 = 282(0x11a, float:3.95E-43)
            r3 = r285
            r2[r0] = r3
            r0 = 283(0x11b, float:3.97E-43)
            r3 = r286
            r2[r0] = r3
            r0 = 284(0x11c, float:3.98E-43)
            r3 = r287
            r2[r0] = r3
            r0 = 285(0x11d, float:4.0E-43)
            r3 = r288
            r2[r0] = r3
            r0 = 286(0x11e, float:4.01E-43)
            r3 = r289
            r2[r0] = r3
            r0 = 287(0x11f, float:4.02E-43)
            r3 = r290
            r2[r0] = r3
            r0 = 288(0x120, float:4.04E-43)
            r3 = r291
            r2[r0] = r3
            r0 = 289(0x121, float:4.05E-43)
            r3 = r292
            r2[r0] = r3
            r0 = 290(0x122, float:4.06E-43)
            r3 = r293
            r2[r0] = r3
            r0 = 291(0x123, float:4.08E-43)
            r3 = r294
            r2[r0] = r3
            r0 = 292(0x124, float:4.09E-43)
            r3 = r295
            r2[r0] = r3
            r0 = 293(0x125, float:4.1E-43)
            r3 = r296
            r2[r0] = r3
            r0 = 294(0x126, float:4.12E-43)
            r3 = r297
            r2[r0] = r3
            r0 = 295(0x127, float:4.13E-43)
            r3 = r298
            r2[r0] = r3
            r0 = 296(0x128, float:4.15E-43)
            r3 = r299
            r2[r0] = r3
            r0 = 297(0x129, float:4.16E-43)
            r3 = r300
            r2[r0] = r3
            r0 = 298(0x12a, float:4.18E-43)
            r3 = r301
            r2[r0] = r3
            r0 = 299(0x12b, float:4.19E-43)
            r3 = r302
            r2[r0] = r3
            r0 = 300(0x12c, float:4.2E-43)
            r3 = r303
            r2[r0] = r3
            r0 = 301(0x12d, float:4.22E-43)
            r3 = r304
            r2[r0] = r3
            r0 = 302(0x12e, float:4.23E-43)
            r3 = r305
            r2[r0] = r3
            r0 = 303(0x12f, float:4.25E-43)
            r3 = r306
            r2[r0] = r3
            r0 = 304(0x130, float:4.26E-43)
            r3 = r307
            r2[r0] = r3
            r0 = 305(0x131, float:4.27E-43)
            r3 = r308
            r2[r0] = r3
            r0 = 306(0x132, float:4.29E-43)
            r3 = r309
            r2[r0] = r3
            r0 = 307(0x133, float:4.3E-43)
            r3 = r310
            r2[r0] = r3
            r0 = 308(0x134, float:4.32E-43)
            r3 = r311
            r2[r0] = r3
            r0 = 309(0x135, float:4.33E-43)
            r3 = r312
            r2[r0] = r3
            r0 = 310(0x136, float:4.34E-43)
            r3 = r313
            r2[r0] = r3
            r0 = 311(0x137, float:4.36E-43)
            r3 = r314
            r2[r0] = r3
            r0 = 312(0x138, float:4.37E-43)
            r3 = r315
            r2[r0] = r3
            r0 = 313(0x139, float:4.39E-43)
            r3 = r316
            r2[r0] = r3
            r0 = 314(0x13a, float:4.4E-43)
            r3 = r317
            r2[r0] = r3
            r0 = 315(0x13b, float:4.41E-43)
            r3 = r318
            r2[r0] = r3
            r0 = 316(0x13c, float:4.43E-43)
            r3 = r319
            r2[r0] = r3
            r0 = 317(0x13d, float:4.44E-43)
            r3 = r320
            r2[r0] = r3
            r0 = 318(0x13e, float:4.46E-43)
            r3 = r321
            r2[r0] = r3
            r0 = 319(0x13f, float:4.47E-43)
            r3 = r322
            r2[r0] = r3
            r0 = 320(0x140, float:4.48E-43)
            r3 = r323
            r2[r0] = r3
            r0 = 321(0x141, float:4.5E-43)
            r3 = r324
            r2[r0] = r3
            r0 = 322(0x142, float:4.51E-43)
            r3 = r325
            r2[r0] = r3
            r0 = 323(0x143, float:4.53E-43)
            r3 = r326
            r2[r0] = r3
            r0 = 324(0x144, float:4.54E-43)
            r3 = r327
            r2[r0] = r3
            r0 = 325(0x145, float:4.55E-43)
            r3 = r328
            r2[r0] = r3
            r0 = 326(0x146, float:4.57E-43)
            r3 = r329
            r2[r0] = r3
            r0 = 327(0x147, float:4.58E-43)
            r3 = r330
            r2[r0] = r3
            r0 = 328(0x148, float:4.6E-43)
            r3 = r331
            r2[r0] = r3
            r0 = 329(0x149, float:4.61E-43)
            r3 = r332
            r2[r0] = r3
            r0 = 330(0x14a, float:4.62E-43)
            r3 = r333
            r2[r0] = r3
            r0 = 331(0x14b, float:4.64E-43)
            r3 = r334
            r2[r0] = r3
            r0 = 332(0x14c, float:4.65E-43)
            r3 = r335
            r2[r0] = r3
            r0 = 333(0x14d, float:4.67E-43)
            r3 = r336
            r2[r0] = r3
            r0 = 334(0x14e, float:4.68E-43)
            r3 = r337
            r2[r0] = r3
            r0 = 335(0x14f, float:4.7E-43)
            r3 = r338
            r2[r0] = r3
            r0 = 336(0x150, float:4.71E-43)
            r3 = r339
            r2[r0] = r3
            r0 = 337(0x151, float:4.72E-43)
            r3 = r340
            r2[r0] = r3
            r0 = 338(0x152, float:4.74E-43)
            r3 = r341
            r2[r0] = r3
            r0 = 339(0x153, float:4.75E-43)
            r3 = r342
            r2[r0] = r3
            r0 = 340(0x154, float:4.76E-43)
            r3 = r343
            r2[r0] = r3
            r0 = 341(0x155, float:4.78E-43)
            r3 = r344
            r2[r0] = r3
            r0 = 342(0x156, float:4.79E-43)
            r3 = r345
            r2[r0] = r3
            r0 = 343(0x157, float:4.8E-43)
            r3 = r346
            r2[r0] = r3
            r0 = 344(0x158, float:4.82E-43)
            r3 = r347
            r2[r0] = r3
            r0 = 345(0x159, float:4.83E-43)
            r3 = r348
            r2[r0] = r3
            r0 = 346(0x15a, float:4.85E-43)
            r3 = r349
            r2[r0] = r3
            r0 = 347(0x15b, float:4.86E-43)
            r3 = r350
            r2[r0] = r3
            r0 = 348(0x15c, float:4.88E-43)
            r3 = r351
            r2[r0] = r3
            r0 = 349(0x15d, float:4.89E-43)
            r3 = r352
            r2[r0] = r3
            r0 = 350(0x15e, float:4.9E-43)
            r3 = r353
            r2[r0] = r3
            r0 = 351(0x15f, float:4.92E-43)
            r3 = r354
            r2[r0] = r3
            r0 = 352(0x160, float:4.93E-43)
            r3 = r355
            r2[r0] = r3
            r0 = 353(0x161, float:4.95E-43)
            r3 = r356
            r2[r0] = r3
            r0 = 354(0x162, float:4.96E-43)
            r3 = r357
            r2[r0] = r3
            r0 = 355(0x163, float:4.97E-43)
            r3 = r358
            r2[r0] = r3
            r0 = 356(0x164, float:4.99E-43)
            r3 = r359
            r2[r0] = r3
            r0 = 357(0x165, float:5.0E-43)
            r3 = r360
            r2[r0] = r3
            r0 = 358(0x166, float:5.02E-43)
            r3 = r361
            r2[r0] = r3
            r0 = 359(0x167, float:5.03E-43)
            r3 = r362
            r2[r0] = r3
            r0 = 360(0x168, float:5.04E-43)
            r3 = r363
            r2[r0] = r3
            r0 = 361(0x169, float:5.06E-43)
            r3 = r364
            r2[r0] = r3
            r0 = 362(0x16a, float:5.07E-43)
            r3 = r365
            r2[r0] = r3
            r0 = 363(0x16b, float:5.09E-43)
            r3 = r366
            r2[r0] = r3
            r0 = 364(0x16c, float:5.1E-43)
            r3 = r367
            r2[r0] = r3
            r0 = 365(0x16d, float:5.11E-43)
            r3 = r368
            r2[r0] = r3
            r0 = 366(0x16e, float:5.13E-43)
            r3 = r369
            r2[r0] = r3
            r0 = 367(0x16f, float:5.14E-43)
            r3 = r370
            r2[r0] = r3
            r0 = 368(0x170, float:5.16E-43)
            r3 = r371
            r2[r0] = r3
            r0 = 369(0x171, float:5.17E-43)
            r3 = r372
            r2[r0] = r3
            r0 = 370(0x172, float:5.18E-43)
            r3 = r373
            r2[r0] = r3
            r0 = 371(0x173, float:5.2E-43)
            r3 = r374
            r2[r0] = r3
            r0 = 372(0x174, float:5.21E-43)
            r3 = r375
            r2[r0] = r3
            r0 = 373(0x175, float:5.23E-43)
            r3 = r376
            r2[r0] = r3
            r0 = 374(0x176, float:5.24E-43)
            r3 = r377
            r2[r0] = r3
            r0 = 375(0x177, float:5.25E-43)
            r3 = r378
            r2[r0] = r3
            r0 = 376(0x178, float:5.27E-43)
            r3 = r379
            r2[r0] = r3
            r0 = 377(0x179, float:5.28E-43)
            r3 = r380
            r2[r0] = r3
            r0 = 378(0x17a, float:5.3E-43)
            r3 = r381
            r2[r0] = r3
            r0 = 379(0x17b, float:5.31E-43)
            r3 = r382
            r2[r0] = r3
            r0 = 380(0x17c, float:5.32E-43)
            r3 = r383
            r2[r0] = r3
            r0 = 381(0x17d, float:5.34E-43)
            r3 = r384
            r2[r0] = r3
            r0 = 382(0x17e, float:5.35E-43)
            r3 = r385
            r2[r0] = r3
            r0 = 383(0x17f, float:5.37E-43)
            r3 = r386
            r2[r0] = r3
            r0 = 384(0x180, float:5.38E-43)
            r3 = r387
            r2[r0] = r3
            r0 = 385(0x181, float:5.4E-43)
            r3 = r388
            r2[r0] = r3
            r0 = 386(0x182, float:5.41E-43)
            r3 = r389
            r2[r0] = r3
            r0 = 387(0x183, float:5.42E-43)
            r3 = r390
            r2[r0] = r3
            r0 = 388(0x184, float:5.44E-43)
            r3 = r391
            r2[r0] = r3
            r0 = 389(0x185, float:5.45E-43)
            r3 = r392
            r2[r0] = r3
            r0 = 390(0x186, float:5.47E-43)
            r3 = r393
            r2[r0] = r3
            r0 = 391(0x187, float:5.48E-43)
            r3 = r394
            r2[r0] = r3
            r0 = 392(0x188, float:5.5E-43)
            r3 = r395
            r2[r0] = r3
            r0 = 393(0x189, float:5.51E-43)
            r3 = r396
            r2[r0] = r3
            r0 = 394(0x18a, float:5.52E-43)
            r3 = r397
            r2[r0] = r3
            r0 = 395(0x18b, float:5.54E-43)
            r3 = r398
            r2[r0] = r3
            r0 = 396(0x18c, float:5.55E-43)
            r3 = r399
            r2[r0] = r3
            r0 = 397(0x18d, float:5.56E-43)
            r3 = r400
            r2[r0] = r3
            r0 = 398(0x18e, float:5.58E-43)
            r3 = r401
            r2[r0] = r3
            r0 = 399(0x18f, float:5.59E-43)
            r3 = r402
            r2[r0] = r3
            r0 = 400(0x190, float:5.6E-43)
            r3 = r403
            r2[r0] = r3
            r0 = 401(0x191, float:5.62E-43)
            r3 = r404
            r2[r0] = r3
            r0 = 402(0x192, float:5.63E-43)
            r3 = r405
            r2[r0] = r3
            r0 = 403(0x193, float:5.65E-43)
            r3 = r406
            r2[r0] = r3
            r0 = 404(0x194, float:5.66E-43)
            r3 = r407
            r2[r0] = r3
            r0 = 405(0x195, float:5.68E-43)
            r3 = r408
            r2[r0] = r3
            r0 = 406(0x196, float:5.69E-43)
            r3 = r409
            r2[r0] = r3
            r0 = 407(0x197, float:5.7E-43)
            r3 = r410
            r2[r0] = r3
            r0 = 408(0x198, float:5.72E-43)
            r3 = r411
            r2[r0] = r3
            r0 = 409(0x199, float:5.73E-43)
            r3 = r412
            r2[r0] = r3
            r0 = 410(0x19a, float:5.75E-43)
            r3 = r413
            r2[r0] = r3
            r0 = 411(0x19b, float:5.76E-43)
            r3 = r414
            r2[r0] = r3
            r0 = 412(0x19c, float:5.77E-43)
            r3 = r415
            r2[r0] = r3
            r0 = 413(0x19d, float:5.79E-43)
            r3 = r416
            r2[r0] = r3
            r0 = 414(0x19e, float:5.8E-43)
            r3 = r417
            r2[r0] = r3
            r0 = 415(0x19f, float:5.82E-43)
            r3 = r418
            r2[r0] = r3
            r0 = 416(0x1a0, float:5.83E-43)
            r3 = r419
            r2[r0] = r3
            r0 = 417(0x1a1, float:5.84E-43)
            r3 = r420
            r2[r0] = r3
            r0 = 418(0x1a2, float:5.86E-43)
            r3 = r421
            r2[r0] = r3
            r0 = 419(0x1a3, float:5.87E-43)
            r3 = r422
            r2[r0] = r3
            r0 = 420(0x1a4, float:5.89E-43)
            r3 = r423
            r2[r0] = r3
            r0 = 421(0x1a5, float:5.9E-43)
            r3 = r424
            r2[r0] = r3
            r0 = 422(0x1a6, float:5.91E-43)
            r3 = r425
            r2[r0] = r3
            r0 = 423(0x1a7, float:5.93E-43)
            r3 = r426
            r2[r0] = r3
            r0 = 424(0x1a8, float:5.94E-43)
            r3 = r427
            r2[r0] = r3
            r0 = 425(0x1a9, float:5.96E-43)
            r3 = r428
            r2[r0] = r3
            r0 = 426(0x1aa, float:5.97E-43)
            r3 = r429
            r2[r0] = r3
            r0 = 427(0x1ab, float:5.98E-43)
            r3 = r430
            r2[r0] = r3
            r0 = 428(0x1ac, float:6.0E-43)
            r3 = r431
            r2[r0] = r3
            r0 = 429(0x1ad, float:6.01E-43)
            r3 = r432
            r2[r0] = r3
            r0 = 430(0x1ae, float:6.03E-43)
            r3 = r433
            r2[r0] = r3
            r0 = 431(0x1af, float:6.04E-43)
            r3 = r434
            r2[r0] = r3
            r0 = 432(0x1b0, float:6.05E-43)
            r3 = r435
            r2[r0] = r3
            r0 = 433(0x1b1, float:6.07E-43)
            r3 = r436
            r2[r0] = r3
            r0 = 434(0x1b2, float:6.08E-43)
            r3 = r437
            r2[r0] = r3
            r0 = 435(0x1b3, float:6.1E-43)
            r3 = r438
            r2[r0] = r3
            r0 = 436(0x1b4, float:6.11E-43)
            r3 = r439
            r2[r0] = r3
            r0 = 437(0x1b5, float:6.12E-43)
            r3 = r440
            r2[r0] = r3
            r0 = 438(0x1b6, float:6.14E-43)
            r3 = r441
            r2[r0] = r3
            r0 = 439(0x1b7, float:6.15E-43)
            r3 = r442
            r2[r0] = r3
            r0 = 440(0x1b8, float:6.17E-43)
            r3 = r443
            r2[r0] = r3
            r0 = 441(0x1b9, float:6.18E-43)
            r3 = r444
            r2[r0] = r3
            r0 = 442(0x1ba, float:6.2E-43)
            r3 = r445
            r2[r0] = r3
            r0 = 443(0x1bb, float:6.21E-43)
            r3 = r446
            r2[r0] = r3
            r0 = 444(0x1bc, float:6.22E-43)
            r3 = r447
            r2[r0] = r3
            r0 = 445(0x1bd, float:6.24E-43)
            r3 = r448
            r2[r0] = r3
            r0 = 446(0x1be, float:6.25E-43)
            r3 = r449
            r2[r0] = r3
            r0 = 447(0x1bf, float:6.26E-43)
            r3 = r450
            r2[r0] = r3
            r0 = 448(0x1c0, float:6.28E-43)
            r3 = r451
            r2[r0] = r3
            r0 = 449(0x1c1, float:6.29E-43)
            r3 = r452
            r2[r0] = r3
            r0 = 450(0x1c2, float:6.3E-43)
            r3 = r453
            r2[r0] = r3
            r0 = 451(0x1c3, float:6.32E-43)
            r3 = r454
            r2[r0] = r3
            r0 = 452(0x1c4, float:6.33E-43)
            r3 = r455
            r2[r0] = r3
            r0 = 453(0x1c5, float:6.35E-43)
            r3 = r456
            r2[r0] = r3
            r0 = 454(0x1c6, float:6.36E-43)
            r3 = r457
            r2[r0] = r3
            r0 = 455(0x1c7, float:6.38E-43)
            r3 = r458
            r2[r0] = r3
            r0 = 456(0x1c8, float:6.39E-43)
            r3 = r459
            r2[r0] = r3
            r0 = 457(0x1c9, float:6.4E-43)
            r3 = r460
            r2[r0] = r3
            r0 = 458(0x1ca, float:6.42E-43)
            r3 = r461
            r2[r0] = r3
            r0 = 459(0x1cb, float:6.43E-43)
            r3 = r462
            r2[r0] = r3
            r0 = 460(0x1cc, float:6.45E-43)
            r3 = r463
            r2[r0] = r3
            r0 = 461(0x1cd, float:6.46E-43)
            r3 = r464
            r2[r0] = r3
            r0 = 462(0x1ce, float:6.47E-43)
            r3 = r465
            r2[r0] = r3
            r0 = 463(0x1cf, float:6.49E-43)
            r3 = r466
            r2[r0] = r3
            r0 = 464(0x1d0, float:6.5E-43)
            r3 = r467
            r2[r0] = r3
            r0 = 465(0x1d1, float:6.52E-43)
            r3 = r468
            r2[r0] = r3
            r0 = 466(0x1d2, float:6.53E-43)
            r3 = r469
            r2[r0] = r3
            r0 = 467(0x1d3, float:6.54E-43)
            r3 = r470
            r2[r0] = r3
            r0 = 468(0x1d4, float:6.56E-43)
            r3 = r471
            r2[r0] = r3
            r0 = 469(0x1d5, float:6.57E-43)
            r3 = r472
            r2[r0] = r3
            r0 = 470(0x1d6, float:6.59E-43)
            r3 = r473
            r2[r0] = r3
            r0 = 471(0x1d7, float:6.6E-43)
            r3 = r474
            r2[r0] = r3
            r0 = 472(0x1d8, float:6.61E-43)
            r3 = r475
            r2[r0] = r3
            r0 = 473(0x1d9, float:6.63E-43)
            r3 = r476
            r2[r0] = r3
            r0 = 474(0x1da, float:6.64E-43)
            r3 = r477
            r2[r0] = r3
            r0 = 475(0x1db, float:6.66E-43)
            r3 = r478
            r2[r0] = r3
            r0 = 476(0x1dc, float:6.67E-43)
            r3 = r479
            r2[r0] = r3
            r0 = 477(0x1dd, float:6.68E-43)
            r3 = r480
            r2[r0] = r3
            r0 = 478(0x1de, float:6.7E-43)
            r3 = r481
            r2[r0] = r3
            r0 = 479(0x1df, float:6.71E-43)
            r3 = r482
            r2[r0] = r3
            r0 = 480(0x1e0, float:6.73E-43)
            r3 = r483
            r2[r0] = r3
            r0 = 481(0x1e1, float:6.74E-43)
            r3 = r484
            r2[r0] = r3
            r0 = 482(0x1e2, float:6.75E-43)
            r3 = r485
            r2[r0] = r3
            r0 = 483(0x1e3, float:6.77E-43)
            r3 = r486
            r2[r0] = r3
            r0 = 484(0x1e4, float:6.78E-43)
            r3 = r487
            r2[r0] = r3
            r0 = 485(0x1e5, float:6.8E-43)
            r3 = r488
            r2[r0] = r3
            r0 = 486(0x1e6, float:6.81E-43)
            r3 = r489
            r2[r0] = r3
            r0 = 487(0x1e7, float:6.82E-43)
            r3 = r490
            r2[r0] = r3
            r0 = 488(0x1e8, float:6.84E-43)
            r3 = r491
            r2[r0] = r3
            r0 = 489(0x1e9, float:6.85E-43)
            r3 = r492
            r2[r0] = r3
            r0 = 490(0x1ea, float:6.87E-43)
            r3 = r493
            r2[r0] = r3
            r0 = 491(0x1eb, float:6.88E-43)
            r3 = r494
            r2[r0] = r3
            r0 = 492(0x1ec, float:6.9E-43)
            r3 = r495
            r2[r0] = r3
            r0 = 493(0x1ed, float:6.91E-43)
            r3 = r496
            r2[r0] = r3
            r0 = 494(0x1ee, float:6.92E-43)
            r3 = r497
            r2[r0] = r3
            r0 = 495(0x1ef, float:6.94E-43)
            r3 = r498
            r2[r0] = r3
            r0 = 496(0x1f0, float:6.95E-43)
            r3 = r499
            r2[r0] = r3
            r0 = 497(0x1f1, float:6.96E-43)
            r3 = r500
            r2[r0] = r3
            r0 = 498(0x1f2, float:6.98E-43)
            r3 = r501
            r2[r0] = r3
            r0 = 499(0x1f3, float:6.99E-43)
            r3 = r502
            r2[r0] = r3
            r0 = 500(0x1f4, float:7.0E-43)
            r3 = r503
            r2[r0] = r3
            r0 = 501(0x1f5, float:7.02E-43)
            r3 = r504
            r2[r0] = r3
            r0 = 502(0x1f6, float:7.03E-43)
            r3 = r505
            r2[r0] = r3
            r0 = 503(0x1f7, float:7.05E-43)
            r3 = r506
            r2[r0] = r3
            r0 = 504(0x1f8, float:7.06E-43)
            r3 = r507
            r2[r0] = r3
            r0 = 505(0x1f9, float:7.08E-43)
            r3 = r508
            r2[r0] = r3
            r0 = 506(0x1fa, float:7.09E-43)
            r3 = r509
            r2[r0] = r3
            r0 = 507(0x1fb, float:7.1E-43)
            r3 = r510
            r2[r0] = r3
            r0 = 508(0x1fc, float:7.12E-43)
            r3 = r511
            r2[r0] = r3
            r0 = 509(0x1fd, float:7.13E-43)
            r3 = r512
            r2[r0] = r3
            r0 = 510(0x1fe, float:7.15E-43)
            r3 = r513
            r2[r0] = r3
            r0 = 511(0x1ff, float:7.16E-43)
            r3 = r514
            r2[r0] = r3
            r0 = 512(0x200, float:7.175E-43)
            r3 = r515
            r2[r0] = r3
            r0 = 513(0x201, float:7.19E-43)
            r3 = r516
            r2[r0] = r3
            r0 = 514(0x202, float:7.2E-43)
            r3 = r517
            r2[r0] = r3
            r0 = 515(0x203, float:7.22E-43)
            r3 = r518
            r2[r0] = r3
            r0 = 516(0x204, float:7.23E-43)
            r3 = r519
            r2[r0] = r3
            r0 = 517(0x205, float:7.24E-43)
            r3 = r520
            r2[r0] = r3
            r0 = 518(0x206, float:7.26E-43)
            r3 = r521
            r2[r0] = r3
            r0 = 519(0x207, float:7.27E-43)
            r3 = r522
            r2[r0] = r3
            r0 = 520(0x208, float:7.29E-43)
            r3 = r523
            r2[r0] = r3
            r0 = 521(0x209, float:7.3E-43)
            r3 = r524
            r2[r0] = r3
            r0 = 522(0x20a, float:7.31E-43)
            r3 = r525
            r2[r0] = r3
            r0 = 523(0x20b, float:7.33E-43)
            r3 = r526
            r2[r0] = r3
            r0 = 524(0x20c, float:7.34E-43)
            r3 = r527
            r2[r0] = r3
            r0 = 525(0x20d, float:7.36E-43)
            r3 = r528
            r2[r0] = r3
            r0 = 526(0x20e, float:7.37E-43)
            r3 = r529
            r2[r0] = r3
            r0 = 527(0x20f, float:7.38E-43)
            r3 = r530
            r2[r0] = r3
            r0 = 528(0x210, float:7.4E-43)
            r3 = r531
            r2[r0] = r3
            r0 = 529(0x211, float:7.41E-43)
            r3 = r532
            r2[r0] = r3
            r0 = 530(0x212, float:7.43E-43)
            r3 = r533
            r2[r0] = r3
            r0 = 531(0x213, float:7.44E-43)
            r3 = r534
            r2[r0] = r3
            r0 = 532(0x214, float:7.45E-43)
            r3 = r535
            r2[r0] = r3
            r0 = 533(0x215, float:7.47E-43)
            r3 = r536
            r2[r0] = r3
            r0 = 534(0x216, float:7.48E-43)
            r3 = r537
            r2[r0] = r3
            r0 = 535(0x217, float:7.5E-43)
            r3 = r538
            r2[r0] = r3
            r0 = 536(0x218, float:7.51E-43)
            r3 = r539
            r2[r0] = r3
            r0 = 537(0x219, float:7.52E-43)
            r3 = r540
            r2[r0] = r3
            r0 = 538(0x21a, float:7.54E-43)
            r3 = r541
            r2[r0] = r3
            r0 = 539(0x21b, float:7.55E-43)
            r3 = r542
            r2[r0] = r3
            r0 = 540(0x21c, float:7.57E-43)
            r3 = r543
            r2[r0] = r3
            r0 = 541(0x21d, float:7.58E-43)
            r3 = r544
            r2[r0] = r3
            r0 = 542(0x21e, float:7.6E-43)
            r3 = r545
            r2[r0] = r3
            r0 = 543(0x21f, float:7.61E-43)
            r3 = r546
            r2[r0] = r3
            r0 = 544(0x220, float:7.62E-43)
            r3 = r547
            r2[r0] = r3
            r0 = 545(0x221, float:7.64E-43)
            r3 = r548
            r2[r0] = r3
            r0 = 546(0x222, float:7.65E-43)
            r3 = r549
            r2[r0] = r3
            r0 = 547(0x223, float:7.67E-43)
            r3 = r550
            r2[r0] = r3
            r0 = 548(0x224, float:7.68E-43)
            r3 = r551
            r2[r0] = r3
            r0 = 549(0x225, float:7.7E-43)
            r3 = r552
            r2[r0] = r3
            r0 = 550(0x226, float:7.71E-43)
            r3 = r553
            r2[r0] = r3
            r0 = 551(0x227, float:7.72E-43)
            r3 = r554
            r2[r0] = r3
            r0 = 552(0x228, float:7.74E-43)
            r3 = r555
            r2[r0] = r3
            r0 = 553(0x229, float:7.75E-43)
            r3 = r556
            r2[r0] = r3
            r0 = 554(0x22a, float:7.76E-43)
            r3 = r557
            r2[r0] = r3
            r0 = 555(0x22b, float:7.78E-43)
            r3 = r558
            r2[r0] = r3
            r0 = 556(0x22c, float:7.79E-43)
            r3 = r559
            r2[r0] = r3
            r0 = 557(0x22d, float:7.8E-43)
            r3 = r560
            r2[r0] = r3
            r0 = 558(0x22e, float:7.82E-43)
            r3 = r561
            r2[r0] = r3
            r0 = 559(0x22f, float:7.83E-43)
            r3 = r562
            r2[r0] = r3
            r0 = 560(0x230, float:7.85E-43)
            r3 = r563
            r2[r0] = r3
            r0 = 561(0x231, float:7.86E-43)
            r3 = r564
            r2[r0] = r3
            r0 = 562(0x232, float:7.88E-43)
            r3 = r565
            r2[r0] = r3
            r0 = 563(0x233, float:7.89E-43)
            r3 = r566
            r2[r0] = r3
            r0 = 564(0x234, float:7.9E-43)
            r3 = r567
            r2[r0] = r3
            r0 = 565(0x235, float:7.92E-43)
            r3 = r568
            r2[r0] = r3
            r0 = 566(0x236, float:7.93E-43)
            r3 = r569
            r2[r0] = r3
            r0 = 567(0x237, float:7.95E-43)
            r3 = r570
            r2[r0] = r3
            r0 = 568(0x238, float:7.96E-43)
            r3 = r571
            r2[r0] = r3
            r0 = 569(0x239, float:7.97E-43)
            r3 = r572
            r2[r0] = r3
            r0 = 570(0x23a, float:7.99E-43)
            r3 = r573
            r2[r0] = r3
            r0 = 571(0x23b, float:8.0E-43)
            r3 = r574
            r2[r0] = r3
            r0 = 572(0x23c, float:8.02E-43)
            r3 = r575
            r2[r0] = r3
            r0 = 573(0x23d, float:8.03E-43)
            r3 = r576
            r2[r0] = r3
            r0 = 574(0x23e, float:8.04E-43)
            r3 = r577
            r2[r0] = r3
            r0 = 575(0x23f, float:8.06E-43)
            r3 = r578
            r2[r0] = r3
            r0 = 576(0x240, float:8.07E-43)
            r2[r0] = r4
            $VALUES = r2
            INST_J = r218
            INST_J_SHORT = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kenai.jnr.x86asm.INST_CODE.<clinit>():void");
    }

    public final boolean isShortJump() {
        return compareTo(INST_J_SHORT) >= 0 && compareTo(INST_JMP_SHORT) <= 0;
    }

    public static final INST_CODE valueOf(int i3) {
        return values()[i3];
    }
}
