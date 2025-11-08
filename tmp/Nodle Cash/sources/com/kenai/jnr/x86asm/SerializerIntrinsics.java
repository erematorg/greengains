package com.kenai.jnr.x86asm;

@Deprecated
public abstract class SerializerIntrinsics extends SerializerCore {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    public final void adc(Register register, Register register2) {
        emitX86(INST_CODE.INST_ADC, register, register2);
    }

    public final void add(Register register, Register register2) {
        emitX86(INST_CODE.INST_ADD, register, register2);
    }

    public final void addpd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_ADDPD, xMMRegister, xMMRegister2);
    }

    public final void addps(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_ADDPS, xMMRegister, xMMRegister2);
    }

    public final void addsd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_ADDSD, xMMRegister, xMMRegister2);
    }

    public final void addss(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_ADDSS, xMMRegister, xMMRegister2);
    }

    public final void addsubpd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_ADDSUBPD, xMMRegister, xMMRegister2);
    }

    public final void addsubps(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_ADDSUBPS, xMMRegister, xMMRegister2);
    }

    public final void amd_prefetch(Mem mem) {
        emitX86(INST_CODE.INST_AMD_PREFETCH, mem);
    }

    public final void amd_prefetchw(Mem mem) {
        emitX86(INST_CODE.INST_AMD_PREFETCHW, mem);
    }

    public final void and_(Register register, Register register2) {
        emitX86(INST_CODE.INST_AND, register, register2);
    }

    public final void andnpd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_ANDNPD, xMMRegister, xMMRegister2);
    }

    public final void andnps(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_ANDNPS, xMMRegister, xMMRegister2);
    }

    public final void andpd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_ANDPD, xMMRegister, xMMRegister2);
    }

    public final void andps(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_ANDPS, xMMRegister, xMMRegister2);
    }

    public final void blendpd(XMMRegister xMMRegister, XMMRegister xMMRegister2, Immediate immediate) {
        emitX86(INST_CODE.INST_BLENDPD, xMMRegister, xMMRegister2, immediate);
    }

    public final void blendps(XMMRegister xMMRegister, XMMRegister xMMRegister2, Immediate immediate) {
        emitX86(INST_CODE.INST_BLENDPS, xMMRegister, xMMRegister2, immediate);
    }

    public final void blendvpd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_BLENDVPD, xMMRegister, xMMRegister2);
    }

    public final void blendvps(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_BLENDVPS, xMMRegister, xMMRegister2);
    }

    public final void bsf(Register register, Register register2) {
        emitX86(INST_CODE.INST_BSF, register, register2);
    }

    public final void bsr(Register register, Register register2) {
        emitX86(INST_CODE.INST_BSR, register, register2);
    }

    public final void bswap(Register register) {
        emitX86(INST_CODE.INST_BSWAP, register);
    }

    public final void bt(Register register, Register register2) {
        emitX86(INST_CODE.INST_BT, register, register2);
    }

    public final void btc(Register register, Register register2) {
        emitX86(INST_CODE.INST_BTC, register, register2);
    }

    public final void btr(Register register, Register register2) {
        emitX86(INST_CODE.INST_BTR, register, register2);
    }

    public final void bts(Register register, Register register2) {
        emitX86(INST_CODE.INST_BTS, register, register2);
    }

    public final void call(Register register) {
        emitX86(INST_CODE.INST_CALL, register);
    }

    public final void cbw() {
        emitX86(INST_CODE.INST_CBW);
    }

    public final void cdqe() {
        emitX86(INST_CODE.INST_CDQE);
    }

    public final void clc() {
        emitX86(INST_CODE.INST_CLC);
    }

    public final void cld() {
        emitX86(INST_CODE.INST_CLD);
    }

    public final void clflush(Mem mem) {
        emitX86(INST_CODE.INST_CLFLUSH, mem);
    }

    public final void cmc() {
        emitX86(INST_CODE.INST_CMC);
    }

    public final void cmov(CONDITION condition, Register register, Register register2) {
        emitX86(SerializerCore.conditionToCMovCC(condition), register, register2);
    }

    public final void cmova(Register register, Register register2) {
        emitX86(INST_CODE.INST_CMOVA, register, register2);
    }

    public final void cmovae(Register register, Register register2) {
        emitX86(INST_CODE.INST_CMOVAE, register, register2);
    }

    public final void cmovb(Register register, Register register2) {
        emitX86(INST_CODE.INST_CMOVB, register, register2);
    }

    public final void cmovbe(Register register, Register register2) {
        emitX86(INST_CODE.INST_CMOVBE, register, register2);
    }

    public final void cmovc(Register register, Register register2) {
        emitX86(INST_CODE.INST_CMOVC, register, register2);
    }

    public final void cmove(Register register, Register register2) {
        emitX86(INST_CODE.INST_CMOVE, register, register2);
    }

    public final void cmovg(Register register, Register register2) {
        emitX86(INST_CODE.INST_CMOVG, register, register2);
    }

    public final void cmovge(Register register, Register register2) {
        emitX86(INST_CODE.INST_CMOVGE, register, register2);
    }

    public final void cmovl(Register register, Register register2) {
        emitX86(INST_CODE.INST_CMOVL, register, register2);
    }

    public final void cmovle(Register register, Register register2) {
        emitX86(INST_CODE.INST_CMOVLE, register, register2);
    }

    public final void cmovna(Register register, Register register2) {
        emitX86(INST_CODE.INST_CMOVNA, register, register2);
    }

    public final void cmovnae(Register register, Register register2) {
        emitX86(INST_CODE.INST_CMOVNAE, register, register2);
    }

    public final void cmovnb(Register register, Register register2) {
        emitX86(INST_CODE.INST_CMOVNB, register, register2);
    }

    public final void cmovnbe(Register register, Register register2) {
        emitX86(INST_CODE.INST_CMOVNBE, register, register2);
    }

    public final void cmovnc(Register register, Register register2) {
        emitX86(INST_CODE.INST_CMOVNC, register, register2);
    }

    public final void cmovne(Register register, Register register2) {
        emitX86(INST_CODE.INST_CMOVNE, register, register2);
    }

    public final void cmovng(Register register, Register register2) {
        emitX86(INST_CODE.INST_CMOVNG, register, register2);
    }

    public final void cmovnge(Register register, Register register2) {
        emitX86(INST_CODE.INST_CMOVNGE, register, register2);
    }

    public final void cmovnl(Register register, Register register2) {
        emitX86(INST_CODE.INST_CMOVNL, register, register2);
    }

    public final void cmovnle(Register register, Register register2) {
        emitX86(INST_CODE.INST_CMOVNLE, register, register2);
    }

    public final void cmovno(Register register, Register register2) {
        emitX86(INST_CODE.INST_CMOVNO, register, register2);
    }

    public final void cmovnp(Register register, Register register2) {
        emitX86(INST_CODE.INST_CMOVNP, register, register2);
    }

    public final void cmovns(Register register, Register register2) {
        emitX86(INST_CODE.INST_CMOVNS, register, register2);
    }

    public final void cmovnz(Register register, Register register2) {
        emitX86(INST_CODE.INST_CMOVNZ, register, register2);
    }

    public final void cmovo(Register register, Register register2) {
        emitX86(INST_CODE.INST_CMOVO, register, register2);
    }

    public final void cmovp(Register register, Register register2) {
        emitX86(INST_CODE.INST_CMOVP, register, register2);
    }

    public final void cmovpe(Register register, Register register2) {
        emitX86(INST_CODE.INST_CMOVPE, register, register2);
    }

    public final void cmovpo(Register register, Register register2) {
        emitX86(INST_CODE.INST_CMOVPO, register, register2);
    }

    public final void cmovs(Register register, Register register2) {
        emitX86(INST_CODE.INST_CMOVS, register, register2);
    }

    public final void cmovz(Register register, Register register2) {
        emitX86(INST_CODE.INST_CMOVZ, register, register2);
    }

    public final void cmp(Register register, Register register2) {
        emitX86(INST_CODE.INST_CMP, register, register2);
    }

    public final void cmppd(XMMRegister xMMRegister, XMMRegister xMMRegister2, Immediate immediate) {
        emitX86(INST_CODE.INST_CMPPD, xMMRegister, xMMRegister2, immediate);
    }

    public final void cmpps(XMMRegister xMMRegister, XMMRegister xMMRegister2, Immediate immediate) {
        emitX86(INST_CODE.INST_CMPPS, xMMRegister, xMMRegister2, immediate);
    }

    public final void cmpsd(XMMRegister xMMRegister, XMMRegister xMMRegister2, Immediate immediate) {
        emitX86(INST_CODE.INST_CMPSD, xMMRegister, xMMRegister2, immediate);
    }

    public final void cmpss(XMMRegister xMMRegister, XMMRegister xMMRegister2, Immediate immediate) {
        emitX86(INST_CODE.INST_CMPSS, xMMRegister, xMMRegister2, immediate);
    }

    public final void cmpxchg(Register register, Register register2) {
        emitX86(INST_CODE.INST_CMPXCHG, register, register2);
    }

    public final void cmpxchg16b(Mem mem) {
        emitX86(INST_CODE.INST_CMPXCHG16B, mem);
    }

    public final void cmpxchg8b(Mem mem) {
        emitX86(INST_CODE.INST_CMPXCHG8B, mem);
    }

    public final void comisd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_COMISD, xMMRegister, xMMRegister2);
    }

    public final void comiss(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_COMISS, xMMRegister, xMMRegister2);
    }

    public final void cpuid() {
        emitX86(INST_CODE.INST_CPUID);
    }

    public final void crc32(Register register, Register register2) {
        emitX86(INST_CODE.INST_CRC32, register, register2);
    }

    public final void cvtdq2pd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_CVTDQ2PD, xMMRegister, xMMRegister2);
    }

    public final void cvtdq2ps(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_CVTDQ2PS, xMMRegister, xMMRegister2);
    }

    public final void cvtpd2dq(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_CVTPD2DQ, xMMRegister, xMMRegister2);
    }

    public final void cvtpd2pi(MMRegister mMRegister, XMMRegister xMMRegister) {
        emitX86(INST_CODE.INST_CVTPD2PI, mMRegister, xMMRegister);
    }

    public final void cvtpd2ps(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_CVTPD2PS, xMMRegister, xMMRegister2);
    }

    public final void cvtpi2pd(XMMRegister xMMRegister, MMRegister mMRegister) {
        emitX86(INST_CODE.INST_CVTPI2PD, xMMRegister, mMRegister);
    }

    public final void cvtpi2ps(XMMRegister xMMRegister, MMRegister mMRegister) {
        emitX86(INST_CODE.INST_CVTPI2PS, xMMRegister, mMRegister);
    }

    public final void cvtps2dq(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_CVTPS2DQ, xMMRegister, xMMRegister2);
    }

    public final void cvtps2pd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_CVTPS2PD, xMMRegister, xMMRegister2);
    }

    public final void cvtps2pi(MMRegister mMRegister, XMMRegister xMMRegister) {
        emitX86(INST_CODE.INST_CVTPS2PI, mMRegister, xMMRegister);
    }

    public final void cvtsd2si(Register register, XMMRegister xMMRegister) {
        emitX86(INST_CODE.INST_CVTSD2SI, register, xMMRegister);
    }

    public final void cvtsd2ss(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_CVTSD2SS, xMMRegister, xMMRegister2);
    }

    public final void cvtsi2sd(XMMRegister xMMRegister, Register register) {
        emitX86(INST_CODE.INST_CVTSI2SD, xMMRegister, register);
    }

    public final void cvtsi2ss(XMMRegister xMMRegister, Register register) {
        emitX86(INST_CODE.INST_CVTSI2SS, xMMRegister, register);
    }

    public final void cvtss2sd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_CVTSS2SD, xMMRegister, xMMRegister2);
    }

    public final void cvtss2si(Register register, XMMRegister xMMRegister) {
        emitX86(INST_CODE.INST_CVTSS2SI, register, xMMRegister);
    }

    public final void cvttpd2dq(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_CVTTPD2DQ, xMMRegister, xMMRegister2);
    }

    public final void cvttpd2pi(MMRegister mMRegister, XMMRegister xMMRegister) {
        emitX86(INST_CODE.INST_CVTTPD2PI, mMRegister, xMMRegister);
    }

    public final void cvttps2dq(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_CVTTPS2DQ, xMMRegister, xMMRegister2);
    }

    public final void cvttps2pi(MMRegister mMRegister, XMMRegister xMMRegister) {
        emitX86(INST_CODE.INST_CVTTPS2PI, mMRegister, xMMRegister);
    }

    public final void cvttsd2si(Register register, XMMRegister xMMRegister) {
        emitX86(INST_CODE.INST_CVTTSD2SI, register, xMMRegister);
    }

    public final void cvttss2si(Register register, XMMRegister xMMRegister) {
        emitX86(INST_CODE.INST_CVTTSS2SI, register, xMMRegister);
    }

    public final void cwde() {
        emitX86(INST_CODE.INST_CWDE);
    }

    public final void daa() {
        emitX86(INST_CODE.INST_DAA);
    }

    public final void das() {
        emitX86(INST_CODE.INST_DAS);
    }

    public final void dec(Register register) {
        emitX86(INST_CODE.INST_DEC, register);
    }

    public final void div(Register register) {
        emitX86(INST_CODE.INST_DIV, register);
    }

    public final void divpd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_DIVPD, xMMRegister, xMMRegister2);
    }

    public final void divps(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_DIVPS, xMMRegister, xMMRegister2);
    }

    public final void divsd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_DIVSD, xMMRegister, xMMRegister2);
    }

    public final void divss(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_DIVSS, xMMRegister, xMMRegister2);
    }

    public final void dppd(XMMRegister xMMRegister, XMMRegister xMMRegister2, Immediate immediate) {
        emitX86(INST_CODE.INST_DPPD, xMMRegister, xMMRegister2, immediate);
    }

    public final void dpps(XMMRegister xMMRegister, XMMRegister xMMRegister2, Immediate immediate) {
        emitX86(INST_CODE.INST_DPPS, xMMRegister, xMMRegister2, immediate);
    }

    public final void emms() {
        emitX86(INST_CODE.INST_EMMS);
    }

    public final void enter(Immediate immediate, Immediate immediate2) {
        emitX86(INST_CODE.INST_ENTER, immediate, immediate2);
    }

    public final void extractps(XMMRegister xMMRegister, XMMRegister xMMRegister2, Immediate immediate) {
        emitX86(INST_CODE.INST_EXTRACTPS, xMMRegister, xMMRegister2, immediate);
    }

    public final void f2xm1() {
        emitX86(INST_CODE.INST_F2XM1);
    }

    public final void fabs() {
        emitX86(INST_CODE.INST_FABS);
    }

    public final void fadd(X87Register x87Register, X87Register x87Register2) {
        emitX86(INST_CODE.INST_FADD, x87Register, x87Register2);
    }

    public final void faddp(X87Register x87Register) {
        emitX86(INST_CODE.INST_FADDP, x87Register);
    }

    public final void fbld(Mem mem) {
        emitX86(INST_CODE.INST_FBLD, mem);
    }

    public final void fbstp(Mem mem) {
        emitX86(INST_CODE.INST_FBSTP, mem);
    }

    public final void fchs() {
        emitX86(INST_CODE.INST_FCHS);
    }

    public final void fclex() {
        emitX86(INST_CODE.INST_FCLEX);
    }

    public final void fcmovb(X87Register x87Register) {
        emitX86(INST_CODE.INST_FCMOVB, x87Register);
    }

    public final void fcmovbe(X87Register x87Register) {
        emitX86(INST_CODE.INST_FCMOVBE, x87Register);
    }

    public final void fcmove(X87Register x87Register) {
        emitX86(INST_CODE.INST_FCMOVE, x87Register);
    }

    public final void fcmovnb(X87Register x87Register) {
        emitX86(INST_CODE.INST_FCMOVNB, x87Register);
    }

    public final void fcmovnbe(X87Register x87Register) {
        emitX86(INST_CODE.INST_FCMOVNBE, x87Register);
    }

    public final void fcmovne(X87Register x87Register) {
        emitX86(INST_CODE.INST_FCMOVNE, x87Register);
    }

    public final void fcmovnu(X87Register x87Register) {
        emitX86(INST_CODE.INST_FCMOVNU, x87Register);
    }

    public final void fcmovu(X87Register x87Register) {
        emitX86(INST_CODE.INST_FCMOVU, x87Register);
    }

    public final void fcom(X87Register x87Register) {
        emitX86(INST_CODE.INST_FCOM, x87Register);
    }

    public final void fcomi(X87Register x87Register) {
        emitX86(INST_CODE.INST_FCOMI, x87Register);
    }

    public final void fcomip(X87Register x87Register) {
        emitX86(INST_CODE.INST_FCOMIP, x87Register);
    }

    public final void fcomp(X87Register x87Register) {
        emitX86(INST_CODE.INST_FCOMP, x87Register);
    }

    public final void fcompp() {
        emitX86(INST_CODE.INST_FCOMPP);
    }

    public final void fcos() {
        emitX86(INST_CODE.INST_FCOS);
    }

    public final void fdecstp() {
        emitX86(INST_CODE.INST_FDECSTP);
    }

    public final void fdiv(X87Register x87Register, X87Register x87Register2) {
        emitX86(INST_CODE.INST_FDIV, x87Register, x87Register2);
    }

    public final void fdivp(X87Register x87Register) {
        emitX86(INST_CODE.INST_FDIVP, x87Register);
    }

    public final void fdivr(X87Register x87Register, X87Register x87Register2) {
        emitX86(INST_CODE.INST_FDIVR, x87Register, x87Register2);
    }

    public final void fdivrp(X87Register x87Register) {
        emitX86(INST_CODE.INST_FDIVRP, x87Register);
    }

    public final void femms() {
        emitX86(INST_CODE.INST_FEMMS);
    }

    public final void ffree(X87Register x87Register) {
        emitX86(INST_CODE.INST_FFREE, x87Register);
    }

    public final void fiadd(Mem mem) {
        emitX86(INST_CODE.INST_FIADD, mem);
    }

    public final void ficom(Mem mem) {
        emitX86(INST_CODE.INST_FICOM, mem);
    }

    public final void ficomp(Mem mem) {
        emitX86(INST_CODE.INST_FICOMP, mem);
    }

    public final void fidiv(Mem mem) {
        emitX86(INST_CODE.INST_FIDIV, mem);
    }

    public final void fidivr(Mem mem) {
        emitX86(INST_CODE.INST_FIDIVR, mem);
    }

    public final void fild(Mem mem) {
        emitX86(INST_CODE.INST_FILD, mem);
    }

    public final void fimul(Mem mem) {
        emitX86(INST_CODE.INST_FIMUL, mem);
    }

    public final void fincstp() {
        emitX86(INST_CODE.INST_FINCSTP);
    }

    public final void finit() {
        emitX86(INST_CODE.INST_FINIT);
    }

    public final void fist(Mem mem) {
        emitX86(INST_CODE.INST_FIST, mem);
    }

    public final void fistp(Mem mem) {
        emitX86(INST_CODE.INST_FISTP, mem);
    }

    public final void fisttp(Mem mem) {
        emitX86(INST_CODE.INST_FISTTP, mem);
    }

    public final void fisub(Mem mem) {
        emitX86(INST_CODE.INST_FISUB, mem);
    }

    public final void fisubr(Mem mem) {
        emitX86(INST_CODE.INST_FISUBR, mem);
    }

    public final void fld(Mem mem) {
        emitX86(INST_CODE.INST_FLD, mem);
    }

    public final void fld1() {
        emitX86(INST_CODE.INST_FLD1);
    }

    public final void fldcw(Mem mem) {
        emitX86(INST_CODE.INST_FLDCW, mem);
    }

    public final void fldenv(Mem mem) {
        emitX86(INST_CODE.INST_FLDENV, mem);
    }

    public final void fldl2e() {
        emitX86(INST_CODE.INST_FLDL2E);
    }

    public final void fldl2t() {
        emitX86(INST_CODE.INST_FLDL2T);
    }

    public final void fldlg2() {
        emitX86(INST_CODE.INST_FLDLG2);
    }

    public final void fldln2() {
        emitX86(INST_CODE.INST_FLDLN2);
    }

    public final void fldpi() {
        emitX86(INST_CODE.INST_FLDPI);
    }

    public final void fldz() {
        emitX86(INST_CODE.INST_FLDZ);
    }

    public final void fmul(X87Register x87Register, X87Register x87Register2) {
        emitX86(INST_CODE.INST_FMUL, x87Register, x87Register2);
    }

    public final void fmulp(X87Register x87Register) {
        emitX86(INST_CODE.INST_FMULP, x87Register);
    }

    public final void fnclex() {
        emitX86(INST_CODE.INST_FNCLEX);
    }

    public final void fninit() {
        emitX86(INST_CODE.INST_FNINIT);
    }

    public final void fnop() {
        emitX86(INST_CODE.INST_FNOP);
    }

    public final void fnsave(Mem mem) {
        emitX86(INST_CODE.INST_FNSAVE, mem);
    }

    public final void fnstcw(Mem mem) {
        emitX86(INST_CODE.INST_FNSTCW, mem);
    }

    public final void fnstenv(Mem mem) {
        emitX86(INST_CODE.INST_FNSTENV, mem);
    }

    public final void fnstsw(Register register) {
        emitX86(INST_CODE.INST_FNSTSW, register);
    }

    public final void fpatan() {
        emitX86(INST_CODE.INST_FPATAN);
    }

    public final void fprem() {
        emitX86(INST_CODE.INST_FPREM);
    }

    public final void fprem1() {
        emitX86(INST_CODE.INST_FPREM1);
    }

    public final void fptan() {
        emitX86(INST_CODE.INST_FPTAN);
    }

    public final void frndint() {
        emitX86(INST_CODE.INST_FRNDINT);
    }

    public final void frstor(Mem mem) {
        emitX86(INST_CODE.INST_FRSTOR, mem);
    }

    public final void fsave(Mem mem) {
        emitX86(INST_CODE.INST_FSAVE, mem);
    }

    public final void fscale() {
        emitX86(INST_CODE.INST_FSCALE);
    }

    public final void fsin() {
        emitX86(INST_CODE.INST_FSIN);
    }

    public final void fsincos() {
        emitX86(INST_CODE.INST_FSINCOS);
    }

    public final void fsqrt() {
        emitX86(INST_CODE.INST_FSQRT);
    }

    public final void fst(Mem mem) {
        emitX86(INST_CODE.INST_FST, mem);
    }

    public final void fstcw(Mem mem) {
        emitX86(INST_CODE.INST_FSTCW, mem);
    }

    public final void fstenv(Mem mem) {
        emitX86(INST_CODE.INST_FSTENV, mem);
    }

    public final void fstp(Mem mem) {
        emitX86(INST_CODE.INST_FSTP, mem);
    }

    public final void fstsw(Register register) {
        emitX86(INST_CODE.INST_FSTSW, register);
    }

    public final void fsub(X87Register x87Register, X87Register x87Register2) {
        emitX86(INST_CODE.INST_FSUB, x87Register, x87Register2);
    }

    public final void fsubp(X87Register x87Register) {
        emitX86(INST_CODE.INST_FSUBP, x87Register);
    }

    public final void fsubr(X87Register x87Register, X87Register x87Register2) {
        emitX86(INST_CODE.INST_FSUBR, x87Register, x87Register2);
    }

    public final void fsubrp(X87Register x87Register) {
        emitX86(INST_CODE.INST_FSUBRP, x87Register);
    }

    public final void ftst() {
        emitX86(INST_CODE.INST_FTST);
    }

    public final void fucom(X87Register x87Register) {
        emitX86(INST_CODE.INST_FUCOM, x87Register);
    }

    public final void fucomi(X87Register x87Register) {
        emitX86(INST_CODE.INST_FUCOMI, x87Register);
    }

    public final void fucomip(X87Register x87Register) {
        emitX86(INST_CODE.INST_FUCOMIP, x87Register);
    }

    public final void fucomp(X87Register x87Register) {
        emitX86(INST_CODE.INST_FUCOMP, x87Register);
    }

    public final void fucompp() {
        emitX86(INST_CODE.INST_FUCOMPP);
    }

    public final void fwait() {
        emitX86(INST_CODE.INST_FWAIT);
    }

    public final void fxam() {
        emitX86(INST_CODE.INST_FXAM);
    }

    public final void fxch(X87Register x87Register) {
        emitX86(INST_CODE.INST_FXCH, x87Register);
    }

    public final void fxrstor(Mem mem) {
        emitX86(INST_CODE.INST_FXRSTOR, mem);
    }

    public final void fxsave(Mem mem) {
        emitX86(INST_CODE.INST_FXSAVE, mem);
    }

    public final void fxtract() {
        emitX86(INST_CODE.INST_FXTRACT);
    }

    public final void fyl2x() {
        emitX86(INST_CODE.INST_FYL2X);
    }

    public final void fyl2xp1() {
        emitX86(INST_CODE.INST_FYL2XP1);
    }

    public final void haddpd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_HADDPD, xMMRegister, xMMRegister2);
    }

    public final void haddps(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_HADDPS, xMMRegister, xMMRegister2);
    }

    public final void hsubpd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_HSUBPD, xMMRegister, xMMRegister2);
    }

    public final void hsubps(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_HSUBPS, xMMRegister, xMMRegister2);
    }

    public final void idiv(Register register) {
        emitX86(INST_CODE.INST_IDIV, register);
    }

    public final void imul(Register register) {
        emitX86(INST_CODE.INST_IMUL, register);
    }

    public final void inc(Register register) {
        emitX86(INST_CODE.INST_INC, register);
    }

    public final void int3() {
        emitX86(INST_CODE.INST_INT3);
    }

    public final void j(CONDITION condition, Label label, int i3) {
        _emitJcc(SerializerCore.conditionToJCC(condition), label, i3);
    }

    public final void j_short(CONDITION condition, Label label, int i3) {
        _emitJcc(INST_CODE.valueOf((INST_CODE.INST_J_SHORT.ordinal() + SerializerCore.conditionToJCC(condition).ordinal()) - INST_CODE.INST_J.ordinal()), label, i3);
    }

    public final void ja(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JA, label, i3);
    }

    public final void ja_short(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JA_SHORT, label, i3);
    }

    public final void jae(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JAE, label, i3);
    }

    public final void jae_short(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JAE_SHORT, label, i3);
    }

    public final void jb(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JB, label, i3);
    }

    public final void jb_short(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JB_SHORT, label, i3);
    }

    public final void jbe(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JBE, label, i3);
    }

    public final void jbe_short(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JBE_SHORT, label, i3);
    }

    public final void jc(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JC, label, i3);
    }

    public final void jc_short(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JC_SHORT, label, i3);
    }

    public final void je(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JE, label, i3);
    }

    public final void je_short(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JE_SHORT, label, i3);
    }

    public final void jg(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JG, label, i3);
    }

    public final void jg_short(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JG_SHORT, label, i3);
    }

    public final void jge(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JGE, label, i3);
    }

    public final void jge_short(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JGE_SHORT, label, i3);
    }

    public final void jl(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JL, label, i3);
    }

    public final void jl_short(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JL_SHORT, label, i3);
    }

    public final void jle(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JLE, label, i3);
    }

    public final void jle_short(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JLE_SHORT, label, i3);
    }

    public final void jmp(Register register) {
        emitX86(INST_CODE.INST_JMP, register);
    }

    public final void jmp_short(Label label) {
        emitX86(INST_CODE.INST_JMP_SHORT, label);
    }

    public final void jna(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JNA, label, i3);
    }

    public final void jna_short(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JNA_SHORT, label, i3);
    }

    public final void jnae(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JNAE, label, i3);
    }

    public final void jnae_short(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JNAE_SHORT, label, i3);
    }

    public final void jnb(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JNB, label, i3);
    }

    public final void jnb_short(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JNB_SHORT, label, i3);
    }

    public final void jnbe(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JNBE, label, i3);
    }

    public final void jnbe_short(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JNBE_SHORT, label, i3);
    }

    public final void jnc(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JNC, label, i3);
    }

    public final void jnc_short(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JNC_SHORT, label, i3);
    }

    public final void jne(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JNE, label, i3);
    }

    public final void jne_short(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JNE_SHORT, label, i3);
    }

    public final void jng(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JNG, label, i3);
    }

    public final void jng_short(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JNG_SHORT, label, i3);
    }

    public final void jnge(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JNGE, label, i3);
    }

    public final void jnge_short(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JNGE_SHORT, label, i3);
    }

    public final void jnl(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JNL, label, i3);
    }

    public final void jnl_short(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JNL_SHORT, label, i3);
    }

    public final void jnle(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JNLE, label, i3);
    }

    public final void jnle_short(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JNLE_SHORT, label, i3);
    }

    public final void jno(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JNO, label, i3);
    }

    public final void jno_short(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JNO_SHORT, label, i3);
    }

    public final void jnp(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JNP, label, i3);
    }

    public final void jnp_short(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JNP_SHORT, label, i3);
    }

    public final void jns(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JNS, label, i3);
    }

    public final void jns_short(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JNS_SHORT, label, i3);
    }

    public final void jnz(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JNZ, label, i3);
    }

    public final void jnz_short(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JNZ_SHORT, label, i3);
    }

    public final void jo(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JO, label, i3);
    }

    public final void jo_short(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JO_SHORT, label, i3);
    }

    public final void jp(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JP, label, i3);
    }

    public final void jp_short(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JP_SHORT, label, i3);
    }

    public final void jpe(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JPE, label, i3);
    }

    public final void jpe_short(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JPE_SHORT, label, i3);
    }

    public final void jpo(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JPO, label, i3);
    }

    public final void jpo_short(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JPO_SHORT, label, i3);
    }

    public final void js(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JS, label, i3);
    }

    public final void js_short(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JS_SHORT, label, i3);
    }

    public final void jz(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JZ, label, i3);
    }

    public final void jz_short(Label label, int i3) {
        _emitJcc(INST_CODE.INST_JZ_SHORT, label, i3);
    }

    public final void lddqu(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_LDDQU, xMMRegister, mem);
    }

    public final void ldmxcsr(Mem mem) {
        emitX86(INST_CODE.INST_LDMXCSR, mem);
    }

    public final void lea(Register register, Mem mem) {
        emitX86(INST_CODE.INST_LEA, register, mem);
    }

    public final void leave() {
        emitX86(INST_CODE.INST_LEAVE);
    }

    public final void lfence() {
        emitX86(INST_CODE.INST_LFENCE);
    }

    public final void lock() {
        emitX86(INST_CODE.INST_LOCK);
    }

    public final void maskmovdqu(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_MASKMOVDQU, xMMRegister, xMMRegister2);
    }

    public final void maskmovq(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_MASKMOVQ, mMRegister, mMRegister2);
    }

    public final void maxpd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_MAXPD, xMMRegister, xMMRegister2);
    }

    public final void maxps(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_MAXPS, xMMRegister, xMMRegister2);
    }

    public final void maxsd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_MAXSD, xMMRegister, xMMRegister2);
    }

    public final void maxss(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_MAXSS, xMMRegister, xMMRegister2);
    }

    public final void mfence() {
        emitX86(INST_CODE.INST_MFENCE);
    }

    public final void minpd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_MINPD, xMMRegister, xMMRegister2);
    }

    public final void minps(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_MINPS, xMMRegister, xMMRegister2);
    }

    public final void minsd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_MINSD, xMMRegister, xMMRegister2);
    }

    public final void minss(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_MINSS, xMMRegister, xMMRegister2);
    }

    public final void monitor() {
        emitX86(INST_CODE.INST_MONITOR);
    }

    public final void mov(Register register, Register register2) {
        emitX86(INST_CODE.INST_MOV, register, register2);
    }

    public final void mov_ptr(Register register, long j2) {
        emitX86(INST_CODE.INST_MOV_PTR, register, Immediate.imm(j2));
    }

    public final void movapd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_MOVAPD, xMMRegister, xMMRegister2);
    }

    public final void movaps(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_MOVAPS, xMMRegister, xMMRegister2);
    }

    public final void movbe(Register register, Mem mem) {
        emitX86(INST_CODE.INST_MOVBE, register, mem);
    }

    public final void movd(Mem mem, MMRegister mMRegister) {
        emitX86(INST_CODE.INST_MOVD, mem, mMRegister);
    }

    public final void movddup(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_MOVDDUP, xMMRegister, xMMRegister2);
    }

    public final void movdq2q(MMRegister mMRegister, XMMRegister xMMRegister) {
        emitX86(INST_CODE.INST_MOVDQ2Q, mMRegister, xMMRegister);
    }

    public final void movdqa(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_MOVDQA, xMMRegister, xMMRegister2);
    }

    public final void movdqu(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_MOVDQU, xMMRegister, xMMRegister2);
    }

    public final void movhlps(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_MOVHLPS, xMMRegister, xMMRegister2);
    }

    public final void movhpd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_MOVHPD, xMMRegister, mem);
    }

    public final void movhps(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_MOVHPS, xMMRegister, mem);
    }

    public final void movlhps(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_MOVLHPS, xMMRegister, xMMRegister2);
    }

    public final void movlpd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_MOVLPD, xMMRegister, mem);
    }

    public final void movlps(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_MOVLPS, xMMRegister, mem);
    }

    public final void movmskpd(Register register, XMMRegister xMMRegister) {
        emitX86(INST_CODE.INST_MOVMSKPD, register, xMMRegister);
    }

    public final void movmskps(Register register, XMMRegister xMMRegister) {
        emitX86(INST_CODE.INST_MOVMSKPS, register, xMMRegister);
    }

    public final void movntdq(Mem mem, XMMRegister xMMRegister) {
        emitX86(INST_CODE.INST_MOVNTDQ, mem, xMMRegister);
    }

    public final void movntdqa(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_MOVNTDQA, xMMRegister, mem);
    }

    public final void movnti(Mem mem, Register register) {
        emitX86(INST_CODE.INST_MOVNTI, mem, register);
    }

    public final void movntpd(Mem mem, XMMRegister xMMRegister) {
        emitX86(INST_CODE.INST_MOVNTPD, mem, xMMRegister);
    }

    public final void movntps(Mem mem, XMMRegister xMMRegister) {
        emitX86(INST_CODE.INST_MOVNTPS, mem, xMMRegister);
    }

    public final void movntq(Mem mem, MMRegister mMRegister) {
        emitX86(INST_CODE.INST_MOVNTQ, mem, mMRegister);
    }

    public final void movq(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_MOVQ, mMRegister, mMRegister2);
    }

    public final void movq2dq(XMMRegister xMMRegister, MMRegister mMRegister) {
        emitX86(INST_CODE.INST_MOVQ2DQ, xMMRegister, mMRegister);
    }

    public final void movsd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_MOVSD, xMMRegister, xMMRegister2);
    }

    public final void movshdup(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_MOVSHDUP, xMMRegister, xMMRegister2);
    }

    public final void movsldup(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_MOVSLDUP, xMMRegister, xMMRegister2);
    }

    public final void movss(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_MOVSS, xMMRegister, xMMRegister2);
    }

    public final void movsx(Register register, Register register2) {
        emitX86(INST_CODE.INST_MOVSX, register, register2);
    }

    public final void movsxd(Register register, Register register2) {
        emitX86(INST_CODE.INST_MOVSXD, register, register2);
    }

    public final void movupd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_MOVUPD, xMMRegister, mem);
    }

    public final void movups(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_MOVUPS, xMMRegister, xMMRegister2);
    }

    public final void movzx(Register register, Register register2) {
        emitX86(INST_CODE.INST_MOVZX, register, register2);
    }

    public final void mpsadbw(XMMRegister xMMRegister, XMMRegister xMMRegister2, Immediate immediate) {
        emitX86(INST_CODE.INST_MPSADBW, xMMRegister, xMMRegister2, immediate);
    }

    public final void mul(Register register) {
        emitX86(INST_CODE.INST_MUL, register);
    }

    public final void mulpd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_MULPD, xMMRegister, xMMRegister2);
    }

    public final void mulps(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_MULPS, xMMRegister, xMMRegister2);
    }

    public final void mulsd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_MULSD, xMMRegister, xMMRegister2);
    }

    public final void mulss(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_MULSS, xMMRegister, xMMRegister2);
    }

    public final void mwait() {
        emitX86(INST_CODE.INST_MWAIT);
    }

    public final void neg(Register register) {
        emitX86(INST_CODE.INST_NEG, register);
    }

    public final void nop() {
        emitX86(INST_CODE.INST_NOP);
    }

    public final void not_(Register register) {
        emitX86(INST_CODE.INST_NOT, register);
    }

    public final void or_(Register register, Register register2) {
        emitX86(INST_CODE.INST_OR, register, register2);
    }

    public final void orpd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_ORPD, xMMRegister, xMMRegister2);
    }

    public final void orps(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_ORPS, xMMRegister, xMMRegister2);
    }

    public final void pabsb(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PABSB, mMRegister, mMRegister2);
    }

    public final void pabsd(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PABSD, mMRegister, mMRegister2);
    }

    public final void pabsw(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PABSW, mMRegister, mMRegister2);
    }

    public final void packssdw(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PACKSSDW, xMMRegister, xMMRegister2);
    }

    public final void packsswb(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PACKSSWB, xMMRegister, xMMRegister2);
    }

    public final void packusdw(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PACKUSDW, xMMRegister, xMMRegister2);
    }

    public final void packuswb(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PACKUSWB, mMRegister, mMRegister2);
    }

    public final void paddb(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PADDB, mMRegister, mMRegister2);
    }

    public final void paddd(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PADDD, mMRegister, mMRegister2);
    }

    public final void paddq(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PADDQ, mMRegister, mMRegister2);
    }

    public final void paddsb(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PADDSB, mMRegister, mMRegister2);
    }

    public final void paddsw(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PADDSW, mMRegister, mMRegister2);
    }

    public final void paddusb(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PADDUSB, mMRegister, mMRegister2);
    }

    public final void paddusw(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PADDUSW, mMRegister, mMRegister2);
    }

    public final void paddw(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PADDW, mMRegister, mMRegister2);
    }

    public final void palignr(MMRegister mMRegister, MMRegister mMRegister2, Immediate immediate) {
        emitX86(INST_CODE.INST_PALIGNR, mMRegister, mMRegister2, immediate);
    }

    public final void pand(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PAND, mMRegister, mMRegister2);
    }

    public final void pandn(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PANDN, mMRegister, mMRegister2);
    }

    public final void pause() {
        emitX86(INST_CODE.INST_PAUSE);
    }

    public final void pavgb(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PAVGB, mMRegister, mMRegister2);
    }

    public final void pavgw(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PAVGW, mMRegister, mMRegister2);
    }

    public final void pblendvb(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PBLENDVB, xMMRegister, xMMRegister2);
    }

    public final void pblendw(XMMRegister xMMRegister, XMMRegister xMMRegister2, Immediate immediate) {
        emitX86(INST_CODE.INST_PBLENDW, xMMRegister, xMMRegister2, immediate);
    }

    public final void pcmpeqb(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PCMPEQB, mMRegister, mMRegister2);
    }

    public final void pcmpeqd(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PCMPEQD, mMRegister, mMRegister2);
    }

    public final void pcmpeqq(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PCMPEQQ, xMMRegister, xMMRegister2);
    }

    public final void pcmpeqw(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PCMPEQW, mMRegister, mMRegister2);
    }

    public final void pcmpestri(XMMRegister xMMRegister, XMMRegister xMMRegister2, Immediate immediate) {
        emitX86(INST_CODE.INST_PCMPESTRI, xMMRegister, xMMRegister2, immediate);
    }

    public final void pcmpestrm(XMMRegister xMMRegister, XMMRegister xMMRegister2, Immediate immediate) {
        emitX86(INST_CODE.INST_PCMPESTRM, xMMRegister, xMMRegister2, immediate);
    }

    public final void pcmpgtb(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PCMPGTB, mMRegister, mMRegister2);
    }

    public final void pcmpgtd(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PCMPGTD, mMRegister, mMRegister2);
    }

    public final void pcmpgtq(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PCMPGTQ, xMMRegister, xMMRegister2);
    }

    public final void pcmpgtw(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PCMPGTW, mMRegister, mMRegister2);
    }

    public final void pcmpistri(XMMRegister xMMRegister, XMMRegister xMMRegister2, Immediate immediate) {
        emitX86(INST_CODE.INST_PCMPISTRI, xMMRegister, xMMRegister2, immediate);
    }

    public final void pcmpistrm(XMMRegister xMMRegister, XMMRegister xMMRegister2, Immediate immediate) {
        emitX86(INST_CODE.INST_PCMPISTRM, xMMRegister, xMMRegister2, immediate);
    }

    public final void pextrb(Register register, XMMRegister xMMRegister, Immediate immediate) {
        emitX86(INST_CODE.INST_PEXTRB, register, xMMRegister, immediate);
    }

    public final void pextrd(Register register, XMMRegister xMMRegister, Immediate immediate) {
        emitX86(INST_CODE.INST_PEXTRD, register, xMMRegister, immediate);
    }

    public final void pextrq(Register register, XMMRegister xMMRegister, Immediate immediate) {
        emitX86(INST_CODE.INST_PEXTRQ, register, xMMRegister, immediate);
    }

    public final void pextrw(Register register, MMRegister mMRegister, Immediate immediate) {
        emitX86(INST_CODE.INST_PEXTRW, register, mMRegister, immediate);
    }

    public final void pf2id(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PF2ID, mMRegister, mMRegister2);
    }

    public final void pf2iw(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PF2IW, mMRegister, mMRegister2);
    }

    public final void pfacc(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PFACC, mMRegister, mMRegister2);
    }

    public final void pfadd(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PFADD, mMRegister, mMRegister2);
    }

    public final void pfcmpeq(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PFCMPEQ, mMRegister, mMRegister2);
    }

    public final void pfcmpge(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PFCMPGE, mMRegister, mMRegister2);
    }

    public final void pfcmpgt(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PFCMPGT, mMRegister, mMRegister2);
    }

    public final void pfmax(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PFMAX, mMRegister, mMRegister2);
    }

    public final void pfmin(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PFMIN, mMRegister, mMRegister2);
    }

    public final void pfmul(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PFMUL, mMRegister, mMRegister2);
    }

    public final void pfnacc(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PFNACC, mMRegister, mMRegister2);
    }

    public final void pfpnacc(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PFPNACC, mMRegister, mem);
    }

    public final void pfpnaxx(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PFPNACC, mMRegister, mMRegister2);
    }

    public final void pfrcp(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PFRCP, mMRegister, mMRegister2);
    }

    public final void pfrcpit1(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PFRCPIT1, mMRegister, mMRegister2);
    }

    public final void pfrcpit2(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PFRCPIT2, mMRegister, mMRegister2);
    }

    public final void pfrsqit1(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PFRSQIT1, mMRegister, mMRegister2);
    }

    public final void pfrsqrt(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PFRSQRT, mMRegister, mMRegister2);
    }

    public final void pfsub(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PFSUB, mMRegister, mMRegister2);
    }

    public final void pfsubr(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PFSUBR, mMRegister, mMRegister2);
    }

    public final void phaddd(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PHADDD, mMRegister, mMRegister2);
    }

    public final void phaddsw(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PHADDSW, mMRegister, mMRegister2);
    }

    public final void phaddw(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PHADDW, mMRegister, mMRegister2);
    }

    public final void phminposuw(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PHMINPOSUW, xMMRegister, xMMRegister2);
    }

    public final void phsubd(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PHSUBD, mMRegister, mMRegister2);
    }

    public final void phsubsw(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PHSUBSW, mMRegister, mMRegister2);
    }

    public final void phsubw(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PHSUBW, mMRegister, mMRegister2);
    }

    public final void pi2fd(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PI2FD, mMRegister, mMRegister2);
    }

    public final void pi2fw(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PI2FW, mMRegister, mMRegister2);
    }

    public final void pinsrb(XMMRegister xMMRegister, Register register, Immediate immediate) {
        emitX86(INST_CODE.INST_PINSRB, xMMRegister, register, immediate);
    }

    public final void pinsrd(XMMRegister xMMRegister, Register register, Immediate immediate) {
        emitX86(INST_CODE.INST_PINSRD, xMMRegister, register, immediate);
    }

    public final void pinsrq(XMMRegister xMMRegister, Register register, Immediate immediate) {
        emitX86(INST_CODE.INST_PINSRQ, xMMRegister, register, immediate);
    }

    public final void pinsrw(MMRegister mMRegister, Register register, Immediate immediate) {
        emitX86(INST_CODE.INST_PINSRW, mMRegister, register, immediate);
    }

    public final void pmaddubsw(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PMADDUBSW, mMRegister, mMRegister2);
    }

    public final void pmaddwd(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PMADDWD, mMRegister, mMRegister2);
    }

    public final void pmaxsb(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PMAXSB, xMMRegister, xMMRegister2);
    }

    public final void pmaxsd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PMAXSD, xMMRegister, xMMRegister2);
    }

    public final void pmaxsw(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PMAXSW, mMRegister, mMRegister2);
    }

    public final void pmaxub(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PMAXUB, mMRegister, mMRegister2);
    }

    public final void pmaxud(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PMAXUD, xMMRegister, xMMRegister2);
    }

    public final void pmaxuw(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PMAXUW, xMMRegister, xMMRegister2);
    }

    public final void pminsb(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PMINSB, xMMRegister, xMMRegister2);
    }

    public final void pminsd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PMINSD, xMMRegister, xMMRegister2);
    }

    public final void pminsw(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PMINSW, mMRegister, mMRegister2);
    }

    public final void pminub(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PMINUB, mMRegister, mMRegister2);
    }

    public final void pminud(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PMINUD, xMMRegister, xMMRegister2);
    }

    public final void pminuw(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PMINUW, xMMRegister, xMMRegister2);
    }

    public final void pmovmskb(Register register, MMRegister mMRegister) {
        emitX86(INST_CODE.INST_PMOVMSKB, register, mMRegister);
    }

    public final void pmovsxbd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PMOVSXBD, xMMRegister, xMMRegister2);
    }

    public final void pmovsxbq(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PMOVSXBQ, xMMRegister, xMMRegister2);
    }

    public final void pmovsxbw(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PMOVSXBW, xMMRegister, xMMRegister2);
    }

    public final void pmovsxdq(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PMOVSXDQ, xMMRegister, xMMRegister2);
    }

    public final void pmovsxwd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PMOVSXWD, xMMRegister, xMMRegister2);
    }

    public final void pmovsxwq(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PMOVSXWQ, xMMRegister, xMMRegister2);
    }

    public final void pmovzxbd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PMOVZXBD, xMMRegister, xMMRegister2);
    }

    public final void pmovzxbq(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PMOVZXBQ, xMMRegister, xMMRegister2);
    }

    public final void pmovzxbw(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PMOVZXBW, xMMRegister, xMMRegister2);
    }

    public final void pmovzxdq(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PMOVZXDQ, xMMRegister, xMMRegister2);
    }

    public final void pmovzxwd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PMOVZXWD, xMMRegister, xMMRegister2);
    }

    public final void pmovzxwq(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PMOVZXWQ, xMMRegister, xMMRegister2);
    }

    public final void pmuldq(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PMULDQ, xMMRegister, xMMRegister2);
    }

    public final void pmulhrsw(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PMULHRSW, mMRegister, mMRegister2);
    }

    public final void pmulhuw(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PMULHUW, mMRegister, mMRegister2);
    }

    public final void pmulhw(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PMULHW, mMRegister, mMRegister2);
    }

    public final void pmulld(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PMULLD, xMMRegister, xMMRegister2);
    }

    public final void pmullw(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PMULLW, mMRegister, mMRegister2);
    }

    public final void pmuludq(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PMULUDQ, mMRegister, mMRegister2);
    }

    public final void pop(Register register) {
        emitX86(INST_CODE.INST_POP, register);
    }

    public final void popad() {
        emitX86(INST_CODE.INST_POPAD);
    }

    public final void popcnt(Register register, Register register2) {
        emitX86(INST_CODE.INST_POPCNT, register, register2);
    }

    public final void popf() {
        if (!is64()) {
            popfd();
        } else {
            popfq();
        }
    }

    public final void popfd() {
        emitX86(INST_CODE.INST_POPFD);
    }

    public final void popfq() {
        emitX86(INST_CODE.INST_POPFQ);
    }

    public final void por(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_POR, mMRegister, mMRegister2);
    }

    public final void prefetch(Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_PREFETCH, mem, immediate);
    }

    public final void psadbw(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PSADBW, mMRegister, mMRegister2);
    }

    public final void pshufb(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PSHUFB, mMRegister, mMRegister2);
    }

    public final void pshufd(XMMRegister xMMRegister, XMMRegister xMMRegister2, Immediate immediate) {
        emitX86(INST_CODE.INST_PSHUFD, xMMRegister, xMMRegister2, immediate);
    }

    public final void pshufhw(XMMRegister xMMRegister, XMMRegister xMMRegister2, Immediate immediate) {
        emitX86(INST_CODE.INST_PSHUFHW, xMMRegister, xMMRegister2, immediate);
    }

    public final void pshuflw(XMMRegister xMMRegister, XMMRegister xMMRegister2, Immediate immediate) {
        emitX86(INST_CODE.INST_PSHUFLW, xMMRegister, xMMRegister2, immediate);
    }

    public final void pshufw(MMRegister mMRegister, MMRegister mMRegister2, Immediate immediate) {
        emitX86(INST_CODE.INST_PSHUFW, mMRegister, mMRegister2, immediate);
    }

    public final void psignb(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PSIGNB, mMRegister, mMRegister2);
    }

    public final void psignd(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PSIGND, mMRegister, mMRegister2);
    }

    public final void psignw(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PSIGNW, mMRegister, mMRegister2);
    }

    public final void pslld(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PSLLD, mMRegister, mMRegister2);
    }

    public final void pslldq(XMMRegister xMMRegister, Immediate immediate) {
        emitX86(INST_CODE.INST_PSLLDQ, xMMRegister, immediate);
    }

    public final void psllq(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PSLLQ, mMRegister, mMRegister2);
    }

    public final void psllw(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PSLLW, mMRegister, mMRegister2);
    }

    public final void psrad(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PSRAD, mMRegister, mMRegister2);
    }

    public final void psraw(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PSRAW, mMRegister, mMRegister2);
    }

    public final void psrld(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PSRLD, mMRegister, mMRegister2);
    }

    public final void psrldq(XMMRegister xMMRegister, Immediate immediate) {
        emitX86(INST_CODE.INST_PSRLDQ, xMMRegister, immediate);
    }

    public final void psrlq(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PSRLQ, mMRegister, mMRegister2);
    }

    public final void psrlw(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PSRLW, mMRegister, mMRegister2);
    }

    public final void psubb(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PSUBB, mMRegister, mMRegister2);
    }

    public final void psubd(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PSUBD, mMRegister, mMRegister2);
    }

    public final void psubq(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PSUBQ, mMRegister, mMRegister2);
    }

    public final void psubsb(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PSUBSB, mMRegister, mMRegister2);
    }

    public final void psubsw(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PSUBSW, mMRegister, mMRegister2);
    }

    public final void psubusb(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PSUBUSB, mMRegister, mMRegister2);
    }

    public final void psubusw(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PSUBUSW, mMRegister, mMRegister2);
    }

    public final void psubw(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PSUBW, mMRegister, mMRegister2);
    }

    public final void pswapd(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PSWAPD, mMRegister, mMRegister2);
    }

    public final void ptest(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PTEST, xMMRegister, xMMRegister2);
    }

    public final void punpckhbw(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PUNPCKHBW, mMRegister, mMRegister2);
    }

    public final void punpckhdq(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PUNPCKHDQ, mMRegister, mMRegister2);
    }

    public final void punpckhqdq(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PUNPCKHQDQ, xMMRegister, xMMRegister2);
    }

    public final void punpckhwd(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PUNPCKHWD, mMRegister, mMRegister2);
    }

    public final void punpcklbw(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PUNPCKLBW, mMRegister, mMRegister2);
    }

    public final void punpckldq(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PUNPCKLDQ, mMRegister, mMRegister2);
    }

    public final void punpcklqdq(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PUNPCKLQDQ, xMMRegister, xMMRegister2);
    }

    public final void punpcklwd(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PUNPCKLWD, mMRegister, mMRegister2);
    }

    public final void push(Register register) {
        emitX86(INST_CODE.INST_PUSH, register);
    }

    public final void pushad() {
        emitX86(INST_CODE.INST_PUSHAD);
    }

    public final void pushf() {
        if (!is64()) {
            pushfd();
        } else {
            pushfq();
        }
    }

    public final void pushfd() {
        emitX86(INST_CODE.INST_PUSHFD);
    }

    public final void pushfq() {
        emitX86(INST_CODE.INST_PUSHFQ);
    }

    public final void pxor(MMRegister mMRegister, MMRegister mMRegister2) {
        emitX86(INST_CODE.INST_PXOR, mMRegister, mMRegister2);
    }

    public final void rcl(Register register, Register register2) {
        emitX86(INST_CODE.INST_RCL, register, register2);
    }

    public final void rcpps(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_RCPPS, xMMRegister, xMMRegister2);
    }

    public final void rcpss(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_RCPSS, xMMRegister, xMMRegister2);
    }

    public final void rcr(Register register, Register register2) {
        emitX86(INST_CODE.INST_RCR, register, register2);
    }

    public final void rdtsc() {
        emitX86(INST_CODE.INST_RDTSC);
    }

    public final void rdtscp() {
        emitX86(INST_CODE.INST_RDTSCP);
    }

    public final void ret() {
        emitX86(INST_CODE.INST_RET);
    }

    public final void rol(Register register, Register register2) {
        emitX86(INST_CODE.INST_ROL, register, register2);
    }

    public final void ror(Register register, Register register2) {
        emitX86(INST_CODE.INST_ROR, register, register2);
    }

    public final void roundpd(XMMRegister xMMRegister, XMMRegister xMMRegister2, Immediate immediate) {
        emitX86(INST_CODE.INST_ROUNDPD, xMMRegister, xMMRegister2, immediate);
    }

    public final void roundps(XMMRegister xMMRegister, XMMRegister xMMRegister2, Immediate immediate) {
        emitX86(INST_CODE.INST_ROUNDPS, xMMRegister, xMMRegister2, immediate);
    }

    public final void roundsd(XMMRegister xMMRegister, XMMRegister xMMRegister2, Immediate immediate) {
        emitX86(INST_CODE.INST_ROUNDSD, xMMRegister, xMMRegister2, immediate);
    }

    public final void roundss(XMMRegister xMMRegister, XMMRegister xMMRegister2, Immediate immediate) {
        emitX86(INST_CODE.INST_ROUNDSS, xMMRegister, xMMRegister2, immediate);
    }

    public final void rsqrtps(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_RSQRTPS, xMMRegister, xMMRegister2);
    }

    public final void rsqrtss(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_RSQRTSS, xMMRegister, xMMRegister2);
    }

    public final void sahf() {
        emitX86(INST_CODE.INST_SAHF);
    }

    public final void sal(Register register, Register register2) {
        emitX86(INST_CODE.INST_SAL, register, register2);
    }

    public final void sar(Register register, Register register2) {
        emitX86(INST_CODE.INST_SAR, register, register2);
    }

    public final void sbb(Register register, Register register2) {
        emitX86(INST_CODE.INST_SBB, register, register2);
    }

    public final void set(CONDITION condition, Register register) {
        emitX86(SerializerCore.conditionToSetCC(condition), register);
    }

    public final void seta(Register register) {
        emitX86(INST_CODE.INST_SETA, register);
    }

    public final void setae(Register register) {
        emitX86(INST_CODE.INST_SETAE, register);
    }

    public final void setb(Register register) {
        emitX86(INST_CODE.INST_SETB, register);
    }

    public final void setbe(Register register) {
        emitX86(INST_CODE.INST_SETBE, register);
    }

    public final void setc(Register register) {
        emitX86(INST_CODE.INST_SETC, register);
    }

    public final void sete(Register register) {
        emitX86(INST_CODE.INST_SETE, register);
    }

    public final void setg(Register register) {
        emitX86(INST_CODE.INST_SETG, register);
    }

    public final void setge(Register register) {
        emitX86(INST_CODE.INST_SETGE, register);
    }

    public final void setl(Register register) {
        emitX86(INST_CODE.INST_SETL, register);
    }

    public final void setle(Register register) {
        emitX86(INST_CODE.INST_SETLE, register);
    }

    public final void setna(Register register) {
        emitX86(INST_CODE.INST_SETNA, register);
    }

    public final void setnae(Register register) {
        emitX86(INST_CODE.INST_SETNAE, register);
    }

    public final void setnb(Register register) {
        emitX86(INST_CODE.INST_SETNB, register);
    }

    public final void setnbe(Register register) {
        emitX86(INST_CODE.INST_SETNBE, register);
    }

    public final void setnc(Register register) {
        emitX86(INST_CODE.INST_SETNC, register);
    }

    public final void setne(Register register) {
        emitX86(INST_CODE.INST_SETNE, register);
    }

    public final void setng(Register register) {
        emitX86(INST_CODE.INST_SETNG, register);
    }

    public final void setnge(Register register) {
        emitX86(INST_CODE.INST_SETNGE, register);
    }

    public final void setnl(Register register) {
        emitX86(INST_CODE.INST_SETNL, register);
    }

    public final void setnle(Register register) {
        emitX86(INST_CODE.INST_SETNLE, register);
    }

    public final void setno(Register register) {
        emitX86(INST_CODE.INST_SETNO, register);
    }

    public final void setnp(Register register) {
        emitX86(INST_CODE.INST_SETNP, register);
    }

    public final void setns(Register register) {
        emitX86(INST_CODE.INST_SETNS, register);
    }

    public final void setnz(Register register) {
        emitX86(INST_CODE.INST_SETNZ, register);
    }

    public final void seto(Register register) {
        emitX86(INST_CODE.INST_SETO, register);
    }

    public final void setp(Register register) {
        emitX86(INST_CODE.INST_SETP, register);
    }

    public final void setpe(Register register) {
        emitX86(INST_CODE.INST_SETPE, register);
    }

    public final void setpo(Register register) {
        emitX86(INST_CODE.INST_SETPO, register);
    }

    public final void sets(Register register) {
        emitX86(INST_CODE.INST_SETS, register);
    }

    public final void setz(Register register) {
        emitX86(INST_CODE.INST_SETZ, register);
    }

    public final void sfence() {
        emitX86(INST_CODE.INST_SFENCE);
    }

    public final void shl(Register register, Register register2) {
        emitX86(INST_CODE.INST_SHL, register, register2);
    }

    public final void shld(Register register, Register register2, Register register3) {
        emitX86(INST_CODE.INST_SHLD, register, register2, register3);
    }

    public final void shr(Register register, Register register2) {
        emitX86(INST_CODE.INST_SHR, register, register2);
    }

    public final void shrd(Register register, Register register2, Register register3) {
        emitX86(INST_CODE.INST_SHRD, register, register2, register3);
    }

    public final void shufps(XMMRegister xMMRegister, XMMRegister xMMRegister2, Immediate immediate) {
        emitX86(INST_CODE.INST_SHUFPS, xMMRegister, xMMRegister2, immediate);
    }

    public final void sqrtpd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_SQRTPD, xMMRegister, xMMRegister2);
    }

    public final void sqrtps(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_SQRTPS, xMMRegister, xMMRegister2);
    }

    public final void sqrtsd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_SQRTSD, xMMRegister, xMMRegister2);
    }

    public final void sqrtss(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_SQRTSS, xMMRegister, xMMRegister2);
    }

    public final void stc() {
        emitX86(INST_CODE.INST_STC);
    }

    public final void std() {
        emitX86(INST_CODE.INST_STD);
    }

    public final void stmxcsr(Mem mem) {
        emitX86(INST_CODE.INST_STMXCSR, mem);
    }

    public final void sub(Register register, Register register2) {
        emitX86(INST_CODE.INST_SUB, register, register2);
    }

    public final void subpd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_SUBPD, xMMRegister, xMMRegister2);
    }

    public final void subps(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_SUBPS, xMMRegister, xMMRegister2);
    }

    public final void subsd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_SUBSD, xMMRegister, xMMRegister2);
    }

    public final void subss(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_SUBSS, xMMRegister, xMMRegister2);
    }

    public final void test(Register register, Register register2) {
        emitX86(INST_CODE.INST_TEST, register, register2);
    }

    public final void ucomisd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_UCOMISD, xMMRegister, xMMRegister2);
    }

    public final void ucomiss(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_UCOMISS, xMMRegister, xMMRegister2);
    }

    public final void ud2() {
        emitX86(INST_CODE.INST_UD2);
    }

    public final void unpckhpd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_UNPCKHPD, xMMRegister, xMMRegister2);
    }

    public final void unpckhps(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_UNPCKHPS, xMMRegister, xMMRegister2);
    }

    public final void unpcklpd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_UNPCKLPD, xMMRegister, xMMRegister2);
    }

    public final void unpcklps(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_UNPCKLPS, xMMRegister, xMMRegister2);
    }

    public final void xadd(Register register, Register register2) {
        emitX86(INST_CODE.INST_XADD, register, register2);
    }

    public final void xchg(Register register, Register register2) {
        emitX86(INST_CODE.INST_XCHG, register, register2);
    }

    public final void xor_(Register register, Register register2) {
        emitX86(INST_CODE.INST_XOR, register, register2);
    }

    public final void xorpd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_XORPD, xMMRegister, xMMRegister2);
    }

    public final void xorps(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_XORPS, xMMRegister, xMMRegister2);
    }

    public final void adc(Register register, Mem mem) {
        emitX86(INST_CODE.INST_ADC, register, mem);
    }

    public final void add(Register register, Mem mem) {
        emitX86(INST_CODE.INST_ADD, register, mem);
    }

    public final void addpd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_ADDPD, xMMRegister, mem);
    }

    public final void addps(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_ADDPS, xMMRegister, mem);
    }

    public final void addsd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_ADDSD, xMMRegister, mem);
    }

    public final void addss(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_ADDSS, xMMRegister, mem);
    }

    public final void addsubpd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_ADDSUBPD, xMMRegister, mem);
    }

    public final void addsubps(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_ADDSUBPS, xMMRegister, mem);
    }

    public final void and_(Register register, Mem mem) {
        emitX86(INST_CODE.INST_AND, register, mem);
    }

    public final void andnpd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_ANDNPD, xMMRegister, mem);
    }

    public final void andnps(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_ANDNPS, xMMRegister, mem);
    }

    public final void andpd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_ANDPD, xMMRegister, mem);
    }

    public final void andps(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_ANDPS, xMMRegister, mem);
    }

    public final void blendpd(XMMRegister xMMRegister, Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_BLENDPD, xMMRegister, mem, immediate);
    }

    public final void blendps(XMMRegister xMMRegister, Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_BLENDPS, xMMRegister, mem, immediate);
    }

    public final void blendvpd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_BLENDVPD, xMMRegister, mem);
    }

    public final void blendvps(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_BLENDVPS, xMMRegister, mem);
    }

    public final void bsf(Register register, Mem mem) {
        emitX86(INST_CODE.INST_BSF, register, mem);
    }

    public final void bsr(Register register, Mem mem) {
        emitX86(INST_CODE.INST_BSR, register, mem);
    }

    public final void bt(Register register, Immediate immediate) {
        emitX86(INST_CODE.INST_BT, register, immediate);
    }

    public final void btc(Register register, Immediate immediate) {
        emitX86(INST_CODE.INST_BTC, register, immediate);
    }

    public final void btr(Register register, Immediate immediate) {
        emitX86(INST_CODE.INST_BTR, register, immediate);
    }

    public final void bts(Register register, Immediate immediate) {
        emitX86(INST_CODE.INST_BTS, register, immediate);
    }

    public final void call(Mem mem) {
        emitX86(INST_CODE.INST_CALL, mem);
    }

    public final void cmov(CONDITION condition, Register register, Mem mem) {
        emitX86(SerializerCore.conditionToCMovCC(condition), register, mem);
    }

    public final void cmova(Register register, Mem mem) {
        emitX86(INST_CODE.INST_CMOVA, register, mem);
    }

    public final void cmovae(Register register, Mem mem) {
        emitX86(INST_CODE.INST_CMOVAE, register, mem);
    }

    public final void cmovb(Register register, Mem mem) {
        emitX86(INST_CODE.INST_CMOVB, register, mem);
    }

    public final void cmovbe(Register register, Mem mem) {
        emitX86(INST_CODE.INST_CMOVBE, register, mem);
    }

    public final void cmovc(Register register, Mem mem) {
        emitX86(INST_CODE.INST_CMOVC, register, mem);
    }

    public final void cmove(Register register, Mem mem) {
        emitX86(INST_CODE.INST_CMOVE, register, mem);
    }

    public final void cmovg(Register register, Mem mem) {
        emitX86(INST_CODE.INST_CMOVG, register, mem);
    }

    public final void cmovge(Register register, Mem mem) {
        emitX86(INST_CODE.INST_CMOVGE, register, mem);
    }

    public final void cmovl(Register register, Mem mem) {
        emitX86(INST_CODE.INST_CMOVL, register, mem);
    }

    public final void cmovle(Register register, Mem mem) {
        emitX86(INST_CODE.INST_CMOVLE, register, mem);
    }

    public final void cmovna(Register register, Mem mem) {
        emitX86(INST_CODE.INST_CMOVNA, register, mem);
    }

    public final void cmovnae(Register register, Mem mem) {
        emitX86(INST_CODE.INST_CMOVNAE, register, mem);
    }

    public final void cmovnb(Register register, Mem mem) {
        emitX86(INST_CODE.INST_CMOVNB, register, mem);
    }

    public final void cmovnbe(Register register, Mem mem) {
        emitX86(INST_CODE.INST_CMOVNBE, register, mem);
    }

    public final void cmovnc(Register register, Mem mem) {
        emitX86(INST_CODE.INST_CMOVNC, register, mem);
    }

    public final void cmovne(Register register, Mem mem) {
        emitX86(INST_CODE.INST_CMOVNE, register, mem);
    }

    public final void cmovng(Register register, Mem mem) {
        emitX86(INST_CODE.INST_CMOVNG, register, mem);
    }

    public final void cmovnge(Register register, Mem mem) {
        emitX86(INST_CODE.INST_CMOVNGE, register, mem);
    }

    public final void cmovnl(Register register, Mem mem) {
        emitX86(INST_CODE.INST_CMOVNL, register, mem);
    }

    public final void cmovnle(Register register, Mem mem) {
        emitX86(INST_CODE.INST_CMOVNLE, register, mem);
    }

    public final void cmovno(Register register, Mem mem) {
        emitX86(INST_CODE.INST_CMOVNO, register, mem);
    }

    public final void cmovnp(Register register, Mem mem) {
        emitX86(INST_CODE.INST_CMOVNP, register, mem);
    }

    public final void cmovns(Register register, Mem mem) {
        emitX86(INST_CODE.INST_CMOVNS, register, mem);
    }

    public final void cmovnz(Register register, Mem mem) {
        emitX86(INST_CODE.INST_CMOVNZ, register, mem);
    }

    public final void cmovo(Register register, Mem mem) {
        emitX86(INST_CODE.INST_CMOVO, register, mem);
    }

    public final void cmovp(Register register, Mem mem) {
        emitX86(INST_CODE.INST_CMOVP, register, mem);
    }

    public final void cmovpe(Register register, Mem mem) {
        emitX86(INST_CODE.INST_CMOVPE, register, mem);
    }

    public final void cmovpo(Register register, Mem mem) {
        emitX86(INST_CODE.INST_CMOVPO, register, mem);
    }

    public final void cmovs(Register register, Mem mem) {
        emitX86(INST_CODE.INST_CMOVS, register, mem);
    }

    public final void cmovz(Register register, Mem mem) {
        emitX86(INST_CODE.INST_CMOVZ, register, mem);
    }

    public final void cmp(Register register, Mem mem) {
        emitX86(INST_CODE.INST_CMP, register, mem);
    }

    public final void cmppd(XMMRegister xMMRegister, Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_CMPPD, xMMRegister, mem, immediate);
    }

    public final void cmpps(XMMRegister xMMRegister, Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_CMPPS, xMMRegister, mem, immediate);
    }

    public final void cmpsd(XMMRegister xMMRegister, Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_CMPSD, xMMRegister, mem, immediate);
    }

    public final void cmpss(XMMRegister xMMRegister, Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_CMPSS, xMMRegister, mem, immediate);
    }

    public final void cmpxchg(Mem mem, Register register) {
        emitX86(INST_CODE.INST_CMPXCHG, mem, register);
    }

    public final void comisd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_COMISD, xMMRegister, mem);
    }

    public final void comiss(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_COMISS, xMMRegister, mem);
    }

    public final void crc32(Register register, Mem mem) {
        emitX86(INST_CODE.INST_CRC32, register, mem);
    }

    public final void cvtdq2pd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_CVTDQ2PD, xMMRegister, mem);
    }

    public final void cvtdq2ps(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_CVTDQ2PS, xMMRegister, mem);
    }

    public final void cvtpd2dq(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_CVTPD2DQ, xMMRegister, mem);
    }

    public final void cvtpd2pi(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_CVTPD2PI, mMRegister, mem);
    }

    public final void cvtpd2ps(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_CVTPD2PS, xMMRegister, mem);
    }

    public final void cvtpi2pd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_CVTPI2PD, xMMRegister, mem);
    }

    public final void cvtpi2ps(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_CVTPI2PS, xMMRegister, mem);
    }

    public final void cvtps2dq(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_CVTPS2DQ, xMMRegister, mem);
    }

    public final void cvtps2pd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_CVTPS2PD, xMMRegister, mem);
    }

    public final void cvtps2pi(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_CVTPS2PI, mMRegister, mem);
    }

    public final void cvtsd2si(Register register, Mem mem) {
        emitX86(INST_CODE.INST_CVTSD2SI, register, mem);
    }

    public final void cvtsd2ss(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_CVTSD2SS, xMMRegister, mem);
    }

    public final void cvtsi2sd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_CVTSI2SD, xMMRegister, mem);
    }

    public final void cvtsi2ss(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_CVTSI2SS, xMMRegister, mem);
    }

    public final void cvtss2sd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_CVTSS2SD, xMMRegister, mem);
    }

    public final void cvtss2si(Register register, Mem mem) {
        emitX86(INST_CODE.INST_CVTSS2SI, register, mem);
    }

    public final void cvttpd2dq(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_CVTTPD2DQ, xMMRegister, mem);
    }

    public final void cvttpd2pi(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_CVTTPD2PI, mMRegister, mem);
    }

    public final void cvttps2dq(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_CVTTPS2DQ, xMMRegister, mem);
    }

    public final void cvttps2pi(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_CVTTPS2PI, mMRegister, mem);
    }

    public final void cvttsd2si(Register register, Mem mem) {
        emitX86(INST_CODE.INST_CVTTSD2SI, register, mem);
    }

    public final void cvttss2si(Register register, Mem mem) {
        emitX86(INST_CODE.INST_CVTTSS2SI, register, mem);
    }

    public final void dec(Mem mem) {
        emitX86(INST_CODE.INST_DEC, mem);
    }

    public final void div(Mem mem) {
        emitX86(INST_CODE.INST_DIV, mem);
    }

    public final void divpd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_DIVPD, xMMRegister, mem);
    }

    public final void divps(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_DIVPS, xMMRegister, mem);
    }

    public final void divsd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_DIVSD, xMMRegister, mem);
    }

    public final void divss(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_DIVSS, xMMRegister, mem);
    }

    public final void dppd(XMMRegister xMMRegister, Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_DPPD, xMMRegister, mem, immediate);
    }

    public final void dpps(XMMRegister xMMRegister, Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_DPPS, xMMRegister, mem, immediate);
    }

    public final void extractps(XMMRegister xMMRegister, Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_EXTRACTPS, xMMRegister, mem, immediate);
    }

    public final void fadd(Mem mem) {
        emitX86(INST_CODE.INST_FADD, mem);
    }

    public final void faddp() {
        faddp(X87Register.st(1));
    }

    public final void fcom() {
        fcom(X87Register.st(1));
    }

    public final void fcomp() {
        fcomp(X87Register.st(1));
    }

    public final void fdiv(Mem mem) {
        emitX86(INST_CODE.INST_FDIV, mem);
    }

    public final void fdivp() {
        fdivp(X87Register.st(1));
    }

    public final void fdivr(Mem mem) {
        emitX86(INST_CODE.INST_FDIVR, mem);
    }

    public final void fdivrp() {
        emitX86(INST_CODE.INST_FDIVRP, X87Register.st(1));
    }

    public final void fld(X87Register x87Register) {
        emitX86(INST_CODE.INST_FLD, x87Register);
    }

    public final void fmul(Mem mem) {
        emitX86(INST_CODE.INST_FMUL, mem);
    }

    public final void fmulp() {
        fmulp(X87Register.st(1));
    }

    public final void fnstsw(Mem mem) {
        emitX86(INST_CODE.INST_FNSTSW, mem);
    }

    public final void fst(X87Register x87Register) {
        emitX86(INST_CODE.INST_FST, x87Register);
    }

    public final void fstp(X87Register x87Register) {
        emitX86(INST_CODE.INST_FSTP, x87Register);
    }

    public final void fstsw(Mem mem) {
        emitX86(INST_CODE.INST_FSTSW, mem);
    }

    public final void fsub(Mem mem) {
        emitX86(INST_CODE.INST_FSUB, mem);
    }

    public final void fsubp() {
        emitX86(INST_CODE.INST_FSUBP, X87Register.st(1));
    }

    public final void fsubr(Mem mem) {
        emitX86(INST_CODE.INST_FSUBR, mem);
    }

    public final void fsubrp() {
        emitX86(INST_CODE.INST_FSUBRP, X87Register.st(1));
    }

    public final void fucom() {
        emitX86(INST_CODE.INST_FUCOM, X87Register.st(1));
    }

    public final void fucomip() {
        emitX86(INST_CODE.INST_FUCOMIP, X87Register.st(1));
    }

    public final void fucomp() {
        emitX86(INST_CODE.INST_FUCOMP, X87Register.st(1));
    }

    public final void fxch() {
        emitX86(INST_CODE.INST_FXCH, X87Register.st(1));
    }

    public final void haddpd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_HADDPD, xMMRegister, mem);
    }

    public final void haddps(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_HADDPS, xMMRegister, mem);
    }

    public final void hsubpd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_HSUBPD, xMMRegister, mem);
    }

    public final void hsubps(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_HSUBPS, xMMRegister, mem);
    }

    public final void idiv(Mem mem) {
        emitX86(INST_CODE.INST_IDIV, mem);
    }

    public final void imul(Mem mem) {
        emitX86(INST_CODE.INST_IMUL, mem);
    }

    public final void inc(Mem mem) {
        emitX86(INST_CODE.INST_INC, mem);
    }

    public final void jmp(Mem mem) {
        emitX86(INST_CODE.INST_JMP, mem);
    }

    public final void maxpd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_MAXPD, xMMRegister, mem);
    }

    public final void maxps(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_MAXPS, xMMRegister, mem);
    }

    public final void maxsd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_MAXSD, xMMRegister, mem);
    }

    public final void maxss(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_MAXSS, xMMRegister, mem);
    }

    public final void minpd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_MINPD, xMMRegister, mem);
    }

    public final void minps(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_MINPS, xMMRegister, mem);
    }

    public final void minsd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_MINSD, xMMRegister, mem);
    }

    public final void minss(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_MINSS, xMMRegister, mem);
    }

    public final void mov(Register register, Mem mem) {
        emitX86(INST_CODE.INST_MOV, register, mem);
    }

    public final void mov_ptr(long j2, Register register) {
        emitX86(INST_CODE.INST_MOV_PTR, Immediate.imm(j2), register);
    }

    public final void movapd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_MOVAPD, xMMRegister, mem);
    }

    public final void movaps(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_MOVAPS, xMMRegister, mem);
    }

    public final void movbe(Mem mem, Register register) {
        emitX86(INST_CODE.INST_MOVBE, mem, register);
    }

    public final void movd(Register register, MMRegister mMRegister) {
        emitX86(INST_CODE.INST_MOVD, register, mMRegister);
    }

    public final void movddup(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_MOVDDUP, xMMRegister, mem);
    }

    public final void movdqa(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_MOVDQA, xMMRegister, mem);
    }

    public final void movdqu(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_MOVDQU, xMMRegister, mem);
    }

    public final void movhpd(Mem mem, XMMRegister xMMRegister) {
        emitX86(INST_CODE.INST_MOVHPD, mem, xMMRegister);
    }

    public final void movhps(Mem mem, XMMRegister xMMRegister) {
        emitX86(INST_CODE.INST_MOVHPS, mem, xMMRegister);
    }

    public final void movlpd(Mem mem, XMMRegister xMMRegister) {
        emitX86(INST_CODE.INST_MOVLPD, mem, xMMRegister);
    }

    public final void movlps(Mem mem, XMMRegister xMMRegister) {
        emitX86(INST_CODE.INST_MOVLPS, mem, xMMRegister);
    }

    public final void movq(Mem mem, MMRegister mMRegister) {
        emitX86(INST_CODE.INST_MOVQ, mem, mMRegister);
    }

    public final void movsd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_MOVSD, xMMRegister, mem);
    }

    public final void movshdup(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_MOVSHDUP, xMMRegister, mem);
    }

    public final void movsldup(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_MOVSLDUP, xMMRegister, mem);
    }

    public final void movss(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_MOVSS, xMMRegister, mem);
    }

    public final void movsx(Register register, Mem mem) {
        emitX86(INST_CODE.INST_MOVSX, register, mem);
    }

    public final void movsxd(Register register, Mem mem) {
        emitX86(INST_CODE.INST_MOVSXD, register, mem);
    }

    public final void movupd(Mem mem, XMMRegister xMMRegister) {
        emitX86(INST_CODE.INST_MOVUPD, mem, xMMRegister);
    }

    public final void movups(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_MOVUPS, xMMRegister, mem);
    }

    public final void movzx(Register register, Mem mem) {
        emitX86(INST_CODE.INST_MOVZX, register, mem);
    }

    public final void mpsadbw(XMMRegister xMMRegister, Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_MPSADBW, xMMRegister, mem, immediate);
    }

    public final void mul(Mem mem) {
        emitX86(INST_CODE.INST_MUL, mem);
    }

    public final void mulpd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_MULPD, xMMRegister, mem);
    }

    public final void mulps(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_MULPS, xMMRegister, mem);
    }

    public final void mulsd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_MULSD, xMMRegister, mem);
    }

    public final void mulss(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_MULSS, xMMRegister, mem);
    }

    public final void neg(Mem mem) {
        emitX86(INST_CODE.INST_NEG, mem);
    }

    public final void not_(Mem mem) {
        emitX86(INST_CODE.INST_NOT, mem);
    }

    public final void or_(Register register, Mem mem) {
        emitX86(INST_CODE.INST_OR, register, mem);
    }

    public final void orpd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_ORPD, xMMRegister, mem);
    }

    public final void orps(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_ORPS, xMMRegister, mem);
    }

    public final void pabsb(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PABSB, mMRegister, mem);
    }

    public final void pabsd(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PABSD, mMRegister, mem);
    }

    public final void pabsw(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PABSW, mMRegister, mem);
    }

    public final void packssdw(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PACKSSDW, xMMRegister, mem);
    }

    public final void packsswb(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PACKSSWB, xMMRegister, mem);
    }

    public final void packusdw(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PACKUSDW, xMMRegister, mem);
    }

    public final void packuswb(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PACKUSWB, mMRegister, mem);
    }

    public final void paddb(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PADDB, mMRegister, mem);
    }

    public final void paddd(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PADDD, mMRegister, mem);
    }

    public final void paddq(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PADDQ, mMRegister, mem);
    }

    public final void paddsb(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PADDSB, mMRegister, mem);
    }

    public final void paddsw(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PADDSW, mMRegister, mem);
    }

    public final void paddusb(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PADDUSB, mMRegister, mem);
    }

    public final void paddusw(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PADDUSW, mMRegister, mem);
    }

    public final void paddw(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PADDW, mMRegister, mem);
    }

    public final void palignr(MMRegister mMRegister, Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_PALIGNR, mMRegister, mem, immediate);
    }

    public final void pand(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PAND, mMRegister, mem);
    }

    public final void pandn(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PANDN, mMRegister, mem);
    }

    public final void pavgb(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PAVGB, mMRegister, mem);
    }

    public final void pavgw(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PAVGW, mMRegister, mem);
    }

    public final void pblendvb(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PBLENDVB, xMMRegister, mem);
    }

    public final void pblendw(XMMRegister xMMRegister, Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_PBLENDW, xMMRegister, mem, immediate);
    }

    public final void pcmpeqb(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PCMPEQB, mMRegister, mem);
    }

    public final void pcmpeqd(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PCMPEQD, mMRegister, mem);
    }

    public final void pcmpeqq(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PCMPEQQ, xMMRegister, mem);
    }

    public final void pcmpeqw(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PCMPEQW, mMRegister, mem);
    }

    public final void pcmpestri(XMMRegister xMMRegister, Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_PCMPESTRI, xMMRegister, mem, immediate);
    }

    public final void pcmpestrm(XMMRegister xMMRegister, Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_PCMPESTRM, xMMRegister, mem, immediate);
    }

    public final void pcmpgtb(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PCMPGTB, mMRegister, mem);
    }

    public final void pcmpgtd(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PCMPGTD, mMRegister, mem);
    }

    public final void pcmpgtq(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PCMPGTQ, xMMRegister, mem);
    }

    public final void pcmpgtw(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PCMPGTW, mMRegister, mem);
    }

    public final void pcmpistri(XMMRegister xMMRegister, Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_PCMPISTRI, xMMRegister, mem, immediate);
    }

    public final void pcmpistrm(XMMRegister xMMRegister, Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_PCMPISTRM, xMMRegister, mem, immediate);
    }

    public final void pextrb(Mem mem, XMMRegister xMMRegister, Immediate immediate) {
        emitX86(INST_CODE.INST_PEXTRB, mem, xMMRegister, immediate);
    }

    public final void pextrd(Mem mem, XMMRegister xMMRegister, Immediate immediate) {
        emitX86(INST_CODE.INST_PEXTRD, mem, xMMRegister, immediate);
    }

    public final void pextrq(Mem mem, XMMRegister xMMRegister, Immediate immediate) {
        emitX86(INST_CODE.INST_PEXTRQ, mem, xMMRegister, immediate);
    }

    public final void pextrw(Register register, XMMRegister xMMRegister, Immediate immediate) {
        emitX86(INST_CODE.INST_PEXTRW, register, xMMRegister, immediate);
    }

    public final void pf2id(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PF2ID, mMRegister, mem);
    }

    public final void pf2iw(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PF2IW, mMRegister, mem);
    }

    public final void pfacc(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PFACC, mMRegister, mem);
    }

    public final void pfadd(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PFADD, mMRegister, mem);
    }

    public final void pfcmpeq(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PFCMPEQ, mMRegister, mem);
    }

    public final void pfcmpge(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PFCMPGE, mMRegister, mem);
    }

    public final void pfcmpgt(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PFCMPGT, mMRegister, mem);
    }

    public final void pfmax(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PFMAX, mMRegister, mem);
    }

    public final void pfmin(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PFMIN, mMRegister, mem);
    }

    public final void pfmul(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PFMUL, mMRegister, mem);
    }

    public final void pfnacc(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PFNACC, mMRegister, mem);
    }

    public final void pfrcp(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PFRCP, mMRegister, mem);
    }

    public final void pfrcpit1(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PFRCPIT1, mMRegister, mem);
    }

    public final void pfrcpit2(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PFRCPIT2, mMRegister, mem);
    }

    public final void pfrsqit1(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PFRSQIT1, mMRegister, mem);
    }

    public final void pfrsqrt(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PFRSQRT, mMRegister, mem);
    }

    public final void pfsub(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PFSUB, mMRegister, mem);
    }

    public final void pfsubr(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PFSUBR, mMRegister, mem);
    }

    public final void phaddd(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PHADDD, mMRegister, mem);
    }

    public final void phaddsw(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PHADDSW, mMRegister, mem);
    }

    public final void phaddw(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PHADDW, mMRegister, mem);
    }

    public final void phminposuw(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PHMINPOSUW, xMMRegister, mem);
    }

    public final void phsubd(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PHSUBD, mMRegister, mem);
    }

    public final void phsubsw(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PHSUBSW, mMRegister, mem);
    }

    public final void phsubw(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PHSUBW, mMRegister, mem);
    }

    public final void pi2fd(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PI2FD, mMRegister, mem);
    }

    public final void pi2fw(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PI2FW, mMRegister, mem);
    }

    public final void pinsrb(XMMRegister xMMRegister, Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_PINSRB, xMMRegister, mem, immediate);
    }

    public final void pinsrd(XMMRegister xMMRegister, Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_PINSRD, xMMRegister, mem, immediate);
    }

    public final void pinsrq(XMMRegister xMMRegister, Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_PINSRQ, xMMRegister, mem, immediate);
    }

    public final void pinsrw(MMRegister mMRegister, Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_PINSRW, mMRegister, mem, immediate);
    }

    public final void pmaddubsw(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PMADDUBSW, mMRegister, mem);
    }

    public final void pmaddwd(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PMADDWD, mMRegister, mem);
    }

    public final void pmaxsb(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PMAXSB, xMMRegister, mem);
    }

    public final void pmaxsd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PMAXSD, xMMRegister, mem);
    }

    public final void pmaxsw(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PMAXSW, mMRegister, mem);
    }

    public final void pmaxub(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PMAXUB, mMRegister, mem);
    }

    public final void pmaxud(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PMAXUD, xMMRegister, mem);
    }

    public final void pmaxuw(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PMAXUW, xMMRegister, mem);
    }

    public final void pminsb(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PMINSB, xMMRegister, mem);
    }

    public final void pminsd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PMINSD, xMMRegister, mem);
    }

    public final void pminsw(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PMINSW, mMRegister, mem);
    }

    public final void pminub(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PMINUB, mMRegister, mem);
    }

    public final void pminud(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PMINUD, xMMRegister, mem);
    }

    public final void pminuw(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PMINUW, xMMRegister, mem);
    }

    public final void pmovmskb(Register register, XMMRegister xMMRegister) {
        emitX86(INST_CODE.INST_PMOVMSKB, register, xMMRegister);
    }

    public final void pmovsxbd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PMOVSXBD, xMMRegister, mem);
    }

    public final void pmovsxbq(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PMOVSXBQ, xMMRegister, mem);
    }

    public final void pmovsxbw(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PMOVSXBW, xMMRegister, mem);
    }

    public final void pmovsxdq(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PMOVSXDQ, xMMRegister, mem);
    }

    public final void pmovsxwd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PMOVSXWD, xMMRegister, mem);
    }

    public final void pmovsxwq(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PMOVSXWQ, xMMRegister, mem);
    }

    public final void pmovzxbd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PMOVZXBD, xMMRegister, mem);
    }

    public final void pmovzxbq(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PMOVZXBQ, xMMRegister, mem);
    }

    public final void pmovzxbw(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PMOVZXBW, xMMRegister, mem);
    }

    public final void pmovzxdq(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PMOVZXDQ, xMMRegister, mem);
    }

    public final void pmovzxwd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PMOVZXWD, xMMRegister, mem);
    }

    public final void pmovzxwq(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PMOVZXWQ, xMMRegister, mem);
    }

    public final void pmuldq(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PMULDQ, xMMRegister, mem);
    }

    public final void pmulhrsw(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PMULHRSW, mMRegister, mem);
    }

    public final void pmulhuw(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PMULHUW, mMRegister, mem);
    }

    public final void pmulhw(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PMULHW, mMRegister, mem);
    }

    public final void pmulld(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PMULLD, xMMRegister, mem);
    }

    public final void pmullw(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PMULLW, mMRegister, mem);
    }

    public final void pmuludq(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PMULUDQ, mMRegister, mem);
    }

    public final void pop(Mem mem) {
        emitX86(INST_CODE.INST_POP, mem);
    }

    public final void popcnt(Register register, Mem mem) {
        emitX86(INST_CODE.INST_POPCNT, register, mem);
    }

    public final void por(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_POR, mMRegister, mem);
    }

    public final void psadbw(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PSADBW, mMRegister, mem);
    }

    public final void pshufb(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PSHUFB, mMRegister, mem);
    }

    public final void pshufd(XMMRegister xMMRegister, Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_PSHUFD, xMMRegister, mem, immediate);
    }

    public final void pshufhw(XMMRegister xMMRegister, Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_PSHUFHW, xMMRegister, mem, immediate);
    }

    public final void pshuflw(XMMRegister xMMRegister, Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_PSHUFLW, xMMRegister, mem, immediate);
    }

    public final void pshufw(MMRegister mMRegister, Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_PSHUFW, mMRegister, mem, immediate);
    }

    public final void psignb(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PSIGNB, mMRegister, mem);
    }

    public final void psignd(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PSIGND, mMRegister, mem);
    }

    public final void psignw(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PSIGNW, mMRegister, mem);
    }

    public final void pslld(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PSLLD, mMRegister, mem);
    }

    public final void psllq(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PSLLQ, mMRegister, mem);
    }

    public final void psllw(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PSLLW, mMRegister, mem);
    }

    public final void psrad(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PSRAD, mMRegister, mem);
    }

    public final void psraw(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PSRAW, mMRegister, mem);
    }

    public final void psrld(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PSRLD, mMRegister, mem);
    }

    public final void psrlq(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PSRLQ, mMRegister, mem);
    }

    public final void psrlw(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PSRLW, mMRegister, mem);
    }

    public final void psubb(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PSUBB, mMRegister, mem);
    }

    public final void psubd(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PSUBD, mMRegister, mem);
    }

    public final void psubq(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PSUBQ, mMRegister, mem);
    }

    public final void psubsb(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PSUBSB, mMRegister, mem);
    }

    public final void psubsw(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PSUBSW, mMRegister, mem);
    }

    public final void psubusb(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PSUBUSB, mMRegister, mem);
    }

    public final void psubusw(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PSUBUSW, mMRegister, mem);
    }

    public final void psubw(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PSUBW, mMRegister, mem);
    }

    public final void pswapd(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PSWAPD, mMRegister, mem);
    }

    public final void ptest(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PTEST, xMMRegister, mem);
    }

    public final void punpckhbw(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PUNPCKHBW, mMRegister, mem);
    }

    public final void punpckhdq(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PUNPCKHDQ, mMRegister, mem);
    }

    public final void punpckhqdq(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PUNPCKHQDQ, xMMRegister, mem);
    }

    public final void punpckhwd(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PUNPCKHWD, mMRegister, mem);
    }

    public final void punpcklbw(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PUNPCKLBW, mMRegister, mem);
    }

    public final void punpckldq(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PUNPCKLDQ, mMRegister, mem);
    }

    public final void punpcklqdq(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PUNPCKLQDQ, xMMRegister, mem);
    }

    public final void punpcklwd(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PUNPCKLWD, mMRegister, mem);
    }

    public final void push(Mem mem) {
        emitX86(INST_CODE.INST_PUSH, mem);
    }

    public final void pxor(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PXOR, mMRegister, mem);
    }

    public final void rcl(Register register, Immediate immediate) {
        emitX86(INST_CODE.INST_RCL, register, immediate);
    }

    public final void rcpps(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_RCPPS, xMMRegister, mem);
    }

    public final void rcpss(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_RCPSS, xMMRegister, mem);
    }

    public final void rcr(Register register, Immediate immediate) {
        emitX86(INST_CODE.INST_RCR, register, immediate);
    }

    public final void ret(Immediate immediate) {
        emitX86(INST_CODE.INST_RET, immediate);
    }

    public final void rol(Register register, Immediate immediate) {
        emitX86(INST_CODE.INST_ROL, register, immediate);
    }

    public final void ror(Register register, Immediate immediate) {
        emitX86(INST_CODE.INST_ROR, register, immediate);
    }

    public final void roundpd(XMMRegister xMMRegister, Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_ROUNDPD, xMMRegister, mem, immediate);
    }

    public final void roundps(XMMRegister xMMRegister, Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_ROUNDPS, xMMRegister, mem, immediate);
    }

    public final void roundsd(XMMRegister xMMRegister, Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_ROUNDSD, xMMRegister, mem, immediate);
    }

    public final void roundss(XMMRegister xMMRegister, Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_ROUNDSS, xMMRegister, mem, immediate);
    }

    public final void rsqrtps(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_RSQRTPS, xMMRegister, mem);
    }

    public final void rsqrtss(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_RSQRTSS, xMMRegister, mem);
    }

    public final void sal(Register register, Immediate immediate) {
        emitX86(INST_CODE.INST_SAL, register, immediate);
    }

    public final void sar(Register register, Immediate immediate) {
        emitX86(INST_CODE.INST_SAR, register, immediate);
    }

    public final void sbb(Register register, Mem mem) {
        emitX86(INST_CODE.INST_SBB, register, mem);
    }

    public final void set(CONDITION condition, Mem mem) {
        emitX86(SerializerCore.conditionToSetCC(condition), mem);
    }

    public final void seta(Mem mem) {
        emitX86(INST_CODE.INST_SETA, mem);
    }

    public final void setae(Mem mem) {
        emitX86(INST_CODE.INST_SETAE, mem);
    }

    public final void setb(Mem mem) {
        emitX86(INST_CODE.INST_SETB, mem);
    }

    public final void setbe(Mem mem) {
        emitX86(INST_CODE.INST_SETBE, mem);
    }

    public final void setc(Mem mem) {
        emitX86(INST_CODE.INST_SETC, mem);
    }

    public final void sete(Mem mem) {
        emitX86(INST_CODE.INST_SETE, mem);
    }

    public final void setg(Mem mem) {
        emitX86(INST_CODE.INST_SETG, mem);
    }

    public final void setge(Mem mem) {
        emitX86(INST_CODE.INST_SETGE, mem);
    }

    public final void setl(Mem mem) {
        emitX86(INST_CODE.INST_SETL, mem);
    }

    public final void setle(Mem mem) {
        emitX86(INST_CODE.INST_SETLE, mem);
    }

    public final void setna(Mem mem) {
        emitX86(INST_CODE.INST_SETNA, mem);
    }

    public final void setnae(Mem mem) {
        emitX86(INST_CODE.INST_SETNAE, mem);
    }

    public final void setnb(Mem mem) {
        emitX86(INST_CODE.INST_SETNB, mem);
    }

    public final void setnbe(Mem mem) {
        emitX86(INST_CODE.INST_SETNBE, mem);
    }

    public final void setnc(Mem mem) {
        emitX86(INST_CODE.INST_SETNC, mem);
    }

    public final void setne(Mem mem) {
        emitX86(INST_CODE.INST_SETNE, mem);
    }

    public final void setng(Mem mem) {
        emitX86(INST_CODE.INST_SETNG, mem);
    }

    public final void setnge(Mem mem) {
        emitX86(INST_CODE.INST_SETNGE, mem);
    }

    public final void setnl(Mem mem) {
        emitX86(INST_CODE.INST_SETNL, mem);
    }

    public final void setnle(Mem mem) {
        emitX86(INST_CODE.INST_SETNLE, mem);
    }

    public final void setno(Mem mem) {
        emitX86(INST_CODE.INST_SETNO, mem);
    }

    public final void setnp(Mem mem) {
        emitX86(INST_CODE.INST_SETNP, mem);
    }

    public final void setns(Mem mem) {
        emitX86(INST_CODE.INST_SETNS, mem);
    }

    public final void setnz(Mem mem) {
        emitX86(INST_CODE.INST_SETNZ, mem);
    }

    public final void seto(Mem mem) {
        emitX86(INST_CODE.INST_SETO, mem);
    }

    public final void setp(Mem mem) {
        emitX86(INST_CODE.INST_SETP, mem);
    }

    public final void setpe(Mem mem) {
        emitX86(INST_CODE.INST_SETPE, mem);
    }

    public final void setpo(Mem mem) {
        emitX86(INST_CODE.INST_SETPO, mem);
    }

    public final void sets(Mem mem) {
        emitX86(INST_CODE.INST_SETS, mem);
    }

    public final void setz(Mem mem) {
        emitX86(INST_CODE.INST_SETZ, mem);
    }

    public final void shl(Register register, Immediate immediate) {
        emitX86(INST_CODE.INST_SHL, register, immediate);
    }

    public final void shld(Register register, Register register2, Immediate immediate) {
        emitX86(INST_CODE.INST_SHLD, register, register2, immediate);
    }

    public final void shr(Register register, Immediate immediate) {
        emitX86(INST_CODE.INST_SHR, register, immediate);
    }

    public final void shrd(Register register, Register register2, Immediate immediate) {
        emitX86(INST_CODE.INST_SHRD, register, register2, immediate);
    }

    public final void shufps(XMMRegister xMMRegister, Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_SHUFPS, xMMRegister, mem, immediate);
    }

    public final void sqrtpd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_SQRTPD, xMMRegister, mem);
    }

    public final void sqrtps(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_SQRTPS, xMMRegister, mem);
    }

    public final void sqrtsd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_SQRTSD, xMMRegister, mem);
    }

    public final void sqrtss(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_SQRTSS, xMMRegister, mem);
    }

    public final void sub(Register register, Mem mem) {
        emitX86(INST_CODE.INST_SUB, register, mem);
    }

    public final void subpd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_SUBPD, xMMRegister, mem);
    }

    public final void subps(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_SUBPS, xMMRegister, mem);
    }

    public final void subsd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_SUBSD, xMMRegister, mem);
    }

    public final void subss(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_SUBSS, xMMRegister, mem);
    }

    public final void test(Register register, Immediate immediate) {
        emitX86(INST_CODE.INST_TEST, register, immediate);
    }

    public final void ucomisd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_UCOMISD, xMMRegister, mem);
    }

    public final void ucomiss(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_UCOMISS, xMMRegister, mem);
    }

    public final void unpckhpd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_UNPCKHPD, xMMRegister, mem);
    }

    public final void unpckhps(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_UNPCKHPS, xMMRegister, mem);
    }

    public final void unpcklpd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_UNPCKLPD, xMMRegister, mem);
    }

    public final void unpcklps(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_UNPCKLPS, xMMRegister, mem);
    }

    public final void xadd(Mem mem, Register register) {
        emitX86(INST_CODE.INST_XADD, mem, register);
    }

    public final void xchg(Mem mem, Register register) {
        emitX86(INST_CODE.INST_XCHG, mem, register);
    }

    public final void xor_(Register register, Mem mem) {
        emitX86(INST_CODE.INST_XOR, register, mem);
    }

    public final void xorpd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_XORPD, xMMRegister, mem);
    }

    public final void xorps(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_XORPS, xMMRegister, mem);
    }

    public final void adc(Register register, Immediate immediate) {
        emitX86(INST_CODE.INST_ADC, register, immediate);
    }

    public final void add(Register register, Immediate immediate) {
        emitX86(INST_CODE.INST_ADD, register, immediate);
    }

    public final void and_(Register register, Immediate immediate) {
        emitX86(INST_CODE.INST_AND, register, immediate);
    }

    public final void bt(Mem mem, Register register) {
        emitX86(INST_CODE.INST_BT, mem, register);
    }

    public final void btc(Mem mem, Register register) {
        emitX86(INST_CODE.INST_BTC, mem, register);
    }

    public final void btr(Mem mem, Register register) {
        emitX86(INST_CODE.INST_BTR, mem, register);
    }

    public final void bts(Mem mem, Register register) {
        emitX86(INST_CODE.INST_BTS, mem, register);
    }

    public final void call(Immediate immediate) {
        emitX86(INST_CODE.INST_CALL, immediate);
    }

    public final void cmp(Register register, Immediate immediate) {
        emitX86(INST_CODE.INST_CMP, register, immediate);
    }

    public final void fcom(Mem mem) {
        emitX86(INST_CODE.INST_FCOM, mem);
    }

    public final void fcomp(Mem mem) {
        emitX86(INST_CODE.INST_FCOMP, mem);
    }

    public final void imul(Register register, Register register2) {
        emitX86(INST_CODE.INST_IMUL, register, register2);
    }

    public final void jmp(Immediate immediate) {
        emitX86(INST_CODE.INST_JMP, immediate);
    }

    public final void mov(Register register, Immediate immediate) {
        emitX86(INST_CODE.INST_MOV, register, immediate);
    }

    public final void movapd(Mem mem, XMMRegister xMMRegister) {
        emitX86(INST_CODE.INST_MOVAPD, mem, xMMRegister);
    }

    public final void movaps(Mem mem, XMMRegister xMMRegister) {
        emitX86(INST_CODE.INST_MOVAPS, mem, xMMRegister);
    }

    public final void movd(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_MOVD, mMRegister, mem);
    }

    public final void movdqa(Mem mem, XMMRegister xMMRegister) {
        emitX86(INST_CODE.INST_MOVDQA, mem, xMMRegister);
    }

    public final void movdqu(Mem mem, XMMRegister xMMRegister) {
        emitX86(INST_CODE.INST_MOVDQU, mem, xMMRegister);
    }

    public final void movq(Register register, MMRegister mMRegister) {
        emitX86(INST_CODE.INST_MOVQ, register, mMRegister);
    }

    public final void movsd(Mem mem, XMMRegister xMMRegister) {
        emitX86(INST_CODE.INST_MOVSD, mem, xMMRegister);
    }

    public final void movss(Mem mem, XMMRegister xMMRegister) {
        emitX86(INST_CODE.INST_MOVSS, mem, xMMRegister);
    }

    public final void movups(Mem mem, XMMRegister xMMRegister) {
        emitX86(INST_CODE.INST_MOVUPS, mem, xMMRegister);
    }

    public final void or_(Register register, Immediate immediate) {
        emitX86(INST_CODE.INST_OR, register, immediate);
    }

    public final void pabsb(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PABSB, xMMRegister, xMMRegister2);
    }

    public final void pabsd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PABSD, xMMRegister, xMMRegister2);
    }

    public final void pabsw(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PABSW, xMMRegister, xMMRegister2);
    }

    public final void packuswb(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PACKUSWB, xMMRegister, xMMRegister2);
    }

    public final void paddb(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PADDB, xMMRegister, xMMRegister2);
    }

    public final void paddd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PADDD, xMMRegister, xMMRegister2);
    }

    public final void paddq(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PADDQ, xMMRegister, xMMRegister2);
    }

    public final void paddsb(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PADDSB, xMMRegister, xMMRegister2);
    }

    public final void paddsw(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PADDSW, xMMRegister, xMMRegister2);
    }

    public final void paddusb(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PADDUSB, xMMRegister, xMMRegister2);
    }

    public final void paddusw(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PADDUSW, xMMRegister, xMMRegister2);
    }

    public final void paddw(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PADDW, xMMRegister, xMMRegister2);
    }

    public final void palignr(XMMRegister xMMRegister, XMMRegister xMMRegister2, Immediate immediate) {
        emitX86(INST_CODE.INST_PALIGNR, xMMRegister, xMMRegister2, immediate);
    }

    public final void pand(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PAND, xMMRegister, xMMRegister2);
    }

    public final void pandn(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PANDN, xMMRegister, xMMRegister2);
    }

    public final void pavgb(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PAVGB, xMMRegister, xMMRegister2);
    }

    public final void pavgw(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PAVGW, xMMRegister, xMMRegister2);
    }

    public final void pcmpeqb(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PCMPEQB, xMMRegister, xMMRegister2);
    }

    public final void pcmpeqd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PCMPEQD, xMMRegister, xMMRegister2);
    }

    public final void pcmpeqw(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PCMPEQW, xMMRegister, xMMRegister2);
    }

    public final void pcmpgtb(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PCMPGTB, xMMRegister, xMMRegister2);
    }

    public final void pcmpgtd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PCMPGTD, xMMRegister, xMMRegister2);
    }

    public final void pcmpgtw(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PCMPGTW, xMMRegister, xMMRegister2);
    }

    public final void pextrw(Mem mem, XMMRegister xMMRegister, Immediate immediate) {
        emitX86(INST_CODE.INST_PEXTRW, mem, xMMRegister, immediate);
    }

    public final void phaddd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PHADDD, xMMRegister, xMMRegister2);
    }

    public final void phaddsw(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PHADDSW, xMMRegister, xMMRegister2);
    }

    public final void phaddw(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PHADDW, xMMRegister, xMMRegister2);
    }

    public final void phsubd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PHSUBD, xMMRegister, xMMRegister2);
    }

    public final void phsubsw(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PHSUBSW, xMMRegister, xMMRegister2);
    }

    public final void phsubw(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PHSUBW, xMMRegister, xMMRegister2);
    }

    public final void pinsrw(XMMRegister xMMRegister, Register register, Immediate immediate) {
        emitX86(INST_CODE.INST_PINSRW, xMMRegister, register, immediate);
    }

    public final void pmaddubsw(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PMADDUBSW, xMMRegister, xMMRegister2);
    }

    public final void pmaddwd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PMADDWD, xMMRegister, xMMRegister2);
    }

    public final void pmaxsw(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PMAXSW, xMMRegister, xMMRegister2);
    }

    public final void pmaxub(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PMAXUB, xMMRegister, xMMRegister2);
    }

    public final void pminsw(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PMINSW, xMMRegister, xMMRegister2);
    }

    public final void pminub(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PMINUB, xMMRegister, xMMRegister2);
    }

    public final void pmulhrsw(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PMULHRSW, xMMRegister, xMMRegister2);
    }

    public final void pmulhuw(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PMULHUW, xMMRegister, xMMRegister2);
    }

    public final void pmulhw(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PMULHW, xMMRegister, xMMRegister2);
    }

    public final void pmullw(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PMULLW, xMMRegister, xMMRegister2);
    }

    public final void pmuludq(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PMULUDQ, xMMRegister, xMMRegister2);
    }

    public final void por(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_POR, xMMRegister, xMMRegister2);
    }

    public final void psadbw(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PSADBW, xMMRegister, xMMRegister2);
    }

    public final void pshufb(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PSHUFB, xMMRegister, xMMRegister2);
    }

    public final void psignb(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PSIGNB, xMMRegister, xMMRegister2);
    }

    public final void psignd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PSIGND, xMMRegister, xMMRegister2);
    }

    public final void psignw(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PSIGNW, xMMRegister, xMMRegister2);
    }

    public final void pslld(MMRegister mMRegister, Immediate immediate) {
        emitX86(INST_CODE.INST_PSLLD, mMRegister, immediate);
    }

    public final void psllq(MMRegister mMRegister, Immediate immediate) {
        emitX86(INST_CODE.INST_PSLLQ, mMRegister, immediate);
    }

    public final void psllw(MMRegister mMRegister, Immediate immediate) {
        emitX86(INST_CODE.INST_PSLLW, mMRegister, immediate);
    }

    public final void psrad(MMRegister mMRegister, Immediate immediate) {
        emitX86(INST_CODE.INST_PSRAD, mMRegister, immediate);
    }

    public final void psraw(MMRegister mMRegister, Immediate immediate) {
        emitX86(INST_CODE.INST_PSRAW, mMRegister, immediate);
    }

    public final void psrld(MMRegister mMRegister, Immediate immediate) {
        emitX86(INST_CODE.INST_PSRLD, mMRegister, immediate);
    }

    public final void psrlq(MMRegister mMRegister, Immediate immediate) {
        emitX86(INST_CODE.INST_PSRLQ, mMRegister, immediate);
    }

    public final void psrlw(MMRegister mMRegister, Immediate immediate) {
        emitX86(INST_CODE.INST_PSRLW, mMRegister, immediate);
    }

    public final void psubb(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PSUBB, xMMRegister, xMMRegister2);
    }

    public final void psubd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PSUBD, xMMRegister, xMMRegister2);
    }

    public final void psubq(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PSUBQ, xMMRegister, xMMRegister2);
    }

    public final void psubsb(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PSUBSB, xMMRegister, xMMRegister2);
    }

    public final void psubsw(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PSUBSW, xMMRegister, xMMRegister2);
    }

    public final void psubusb(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PSUBUSB, xMMRegister, xMMRegister2);
    }

    public final void psubusw(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PSUBUSW, xMMRegister, xMMRegister2);
    }

    public final void psubw(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PSUBW, xMMRegister, xMMRegister2);
    }

    public final void punpckhbw(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PUNPCKHBW, xMMRegister, xMMRegister2);
    }

    public final void punpckhdq(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PUNPCKHDQ, xMMRegister, xMMRegister2);
    }

    public final void punpckhwd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PUNPCKHWD, xMMRegister, xMMRegister2);
    }

    public final void punpcklbw(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PUNPCKLBW, xMMRegister, xMMRegister2);
    }

    public final void punpckldq(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PUNPCKLDQ, xMMRegister, xMMRegister2);
    }

    public final void punpcklwd(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PUNPCKLWD, xMMRegister, xMMRegister2);
    }

    public final void push(Immediate immediate) {
        emitX86(INST_CODE.INST_PUSH, immediate);
    }

    public final void pxor(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PXOR, xMMRegister, xMMRegister2);
    }

    public final void rcl(Mem mem, Register register) {
        emitX86(INST_CODE.INST_RCL, mem, register);
    }

    public final void rcr(Mem mem, Register register) {
        emitX86(INST_CODE.INST_RCR, mem, register);
    }

    public final void rol(Mem mem, Register register) {
        emitX86(INST_CODE.INST_ROL, mem, register);
    }

    public final void ror(Mem mem, Register register) {
        emitX86(INST_CODE.INST_ROR, mem, register);
    }

    public final void sal(Mem mem, Register register) {
        emitX86(INST_CODE.INST_SAL, mem, register);
    }

    public final void sar(Mem mem, Register register) {
        emitX86(INST_CODE.INST_SAR, mem, register);
    }

    public final void sbb(Register register, Immediate immediate) {
        emitX86(INST_CODE.INST_SBB, register, immediate);
    }

    public final void shl(Mem mem, Register register) {
        emitX86(INST_CODE.INST_SHL, mem, register);
    }

    public final void shld(Mem mem, Register register, Register register2) {
        emitX86(INST_CODE.INST_SHLD, mem, register, register2);
    }

    public final void shr(Mem mem, Register register) {
        emitX86(INST_CODE.INST_SHR, mem, register);
    }

    public final void shrd(Mem mem, Register register, Register register2) {
        emitX86(INST_CODE.INST_SHRD, mem, register, register2);
    }

    public final void sub(Register register, Immediate immediate) {
        emitX86(INST_CODE.INST_SUB, register, immediate);
    }

    public final void test(Mem mem, Register register) {
        emitX86(INST_CODE.INST_TEST, mem, register);
    }

    public final void xchg(Register register, Mem mem) {
        emitX86(INST_CODE.INST_XCHG, mem, register);
    }

    public final void xor_(Register register, Immediate immediate) {
        emitX86(INST_CODE.INST_XOR, register, immediate);
    }

    public final void adc(Mem mem, Register register) {
        emitX86(INST_CODE.INST_ADC, mem, register);
    }

    public final void add(Mem mem, Register register) {
        emitX86(INST_CODE.INST_ADD, mem, register);
    }

    public final void and_(Mem mem, Register register) {
        emitX86(INST_CODE.INST_AND, mem, register);
    }

    public final void bt(Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_BT, mem, immediate);
    }

    public final void btc(Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_BTC, mem, immediate);
    }

    public final void btr(Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_BTR, mem, immediate);
    }

    public final void bts(Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_BTS, mem, immediate);
    }

    public final void call(long j2) {
        emitX86(INST_CODE.INST_CALL, Immediate.imm(j2));
    }

    public final void cmp(Mem mem, Register register) {
        emitX86(INST_CODE.INST_CMP, mem, register);
    }

    public final void imul(Register register, Mem mem) {
        emitX86(INST_CODE.INST_IMUL, register, mem);
    }

    public final void jmp(long j2) {
        emitX86(INST_CODE.INST_JMP, Immediate.imm(j2));
    }

    public final void mov(Mem mem, Register register) {
        emitX86(INST_CODE.INST_MOV, mem, register);
    }

    public final void movd(MMRegister mMRegister, Register register) {
        emitX86(INST_CODE.INST_MOVD, mMRegister, register);
    }

    public final void movq(MMRegister mMRegister, Mem mem) {
        emitX86(INST_CODE.INST_MOVQ, mMRegister, mem);
    }

    public final void or_(Mem mem, Register register) {
        emitX86(INST_CODE.INST_OR, mem, register);
    }

    public final void pabsb(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PABSB, xMMRegister, mem);
    }

    public final void pabsd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PABSD, xMMRegister, mem);
    }

    public final void pabsw(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PABSW, xMMRegister, mem);
    }

    public final void packuswb(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PACKUSWB, xMMRegister, mem);
    }

    public final void paddb(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PADDB, xMMRegister, mem);
    }

    public final void paddd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PADDD, xMMRegister, mem);
    }

    public final void paddq(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PADDQ, xMMRegister, mem);
    }

    public final void paddsb(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PADDSB, xMMRegister, mem);
    }

    public final void paddsw(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PADDSW, xMMRegister, mem);
    }

    public final void paddusb(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PADDUSB, xMMRegister, mem);
    }

    public final void paddusw(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PADDUSW, xMMRegister, mem);
    }

    public final void paddw(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PADDW, xMMRegister, mem);
    }

    public final void palignr(XMMRegister xMMRegister, Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_PALIGNR, xMMRegister, mem, immediate);
    }

    public final void pand(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PAND, xMMRegister, mem);
    }

    public final void pandn(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PANDN, xMMRegister, mem);
    }

    public final void pavgb(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PAVGB, xMMRegister, mem);
    }

    public final void pavgw(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PAVGW, xMMRegister, mem);
    }

    public final void pcmpeqb(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PCMPEQB, xMMRegister, mem);
    }

    public final void pcmpeqd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PCMPEQD, xMMRegister, mem);
    }

    public final void pcmpeqw(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PCMPEQW, xMMRegister, mem);
    }

    public final void pcmpgtb(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PCMPGTB, xMMRegister, mem);
    }

    public final void pcmpgtd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PCMPGTD, xMMRegister, mem);
    }

    public final void pcmpgtw(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PCMPGTW, xMMRegister, mem);
    }

    public final void phaddd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PHADDD, xMMRegister, mem);
    }

    public final void phaddsw(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PHADDSW, xMMRegister, mem);
    }

    public final void phaddw(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PHADDW, xMMRegister, mem);
    }

    public final void phsubd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PHSUBD, xMMRegister, mem);
    }

    public final void phsubsw(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PHSUBSW, xMMRegister, mem);
    }

    public final void phsubw(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PHSUBW, xMMRegister, mem);
    }

    public final void pinsrw(XMMRegister xMMRegister, Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_PINSRW, xMMRegister, mem, immediate);
    }

    public final void pmaddubsw(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PMADDUBSW, xMMRegister, mem);
    }

    public final void pmaddwd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PMADDWD, xMMRegister, mem);
    }

    public final void pmaxsw(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PMAXSW, xMMRegister, mem);
    }

    public final void pmaxub(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PMAXUB, xMMRegister, mem);
    }

    public final void pminsw(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PMINSW, xMMRegister, mem);
    }

    public final void pminub(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PMINUB, xMMRegister, mem);
    }

    public final void pmulhrsw(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PMULHRSW, xMMRegister, mem);
    }

    public final void pmulhuw(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PMULHUW, xMMRegister, mem);
    }

    public final void pmulhw(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PMULHW, xMMRegister, mem);
    }

    public final void pmullw(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PMULLW, xMMRegister, mem);
    }

    public final void pmuludq(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PMULUDQ, xMMRegister, mem);
    }

    public final void por(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_POR, xMMRegister, mem);
    }

    public final void psadbw(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PSADBW, xMMRegister, mem);
    }

    public final void pshufb(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PSHUFB, xMMRegister, mem);
    }

    public final void psignb(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PSIGNB, xMMRegister, mem);
    }

    public final void psignd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PSIGND, xMMRegister, mem);
    }

    public final void psignw(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PSIGNW, xMMRegister, mem);
    }

    public final void pslld(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PSLLD, xMMRegister, xMMRegister2);
    }

    public final void psllq(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PSLLQ, xMMRegister, xMMRegister2);
    }

    public final void psllw(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PSLLW, xMMRegister, xMMRegister2);
    }

    public final void psrad(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PSRAD, xMMRegister, xMMRegister2);
    }

    public final void psraw(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PSRAW, xMMRegister, xMMRegister2);
    }

    public final void psrld(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PSRLD, xMMRegister, xMMRegister2);
    }

    public final void psrlq(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PSRLQ, xMMRegister, xMMRegister2);
    }

    public final void psrlw(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_PSRLW, xMMRegister, xMMRegister2);
    }

    public final void psubb(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PSUBB, xMMRegister, mem);
    }

    public final void psubd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PSUBD, xMMRegister, mem);
    }

    public final void psubq(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PSUBQ, xMMRegister, mem);
    }

    public final void psubsb(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PSUBSB, xMMRegister, mem);
    }

    public final void psubsw(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PSUBSW, xMMRegister, mem);
    }

    public final void psubusb(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PSUBUSB, xMMRegister, mem);
    }

    public final void psubusw(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PSUBUSW, xMMRegister, mem);
    }

    public final void psubw(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PSUBW, xMMRegister, mem);
    }

    public final void punpckhbw(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PUNPCKHBW, xMMRegister, mem);
    }

    public final void punpckhdq(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PUNPCKHDQ, xMMRegister, mem);
    }

    public final void punpckhwd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PUNPCKHWD, xMMRegister, mem);
    }

    public final void punpcklbw(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PUNPCKLBW, xMMRegister, mem);
    }

    public final void punpckldq(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PUNPCKLDQ, xMMRegister, mem);
    }

    public final void punpcklwd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PUNPCKLWD, xMMRegister, mem);
    }

    public final void pxor(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PXOR, xMMRegister, mem);
    }

    public final void rcl(Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_RCL, mem, immediate);
    }

    public final void rcr(Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_RCR, mem, immediate);
    }

    public final void rol(Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_ROL, mem, immediate);
    }

    public final void ror(Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_ROR, mem, immediate);
    }

    public final void sal(Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_SAL, mem, immediate);
    }

    public final void sar(Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_SAR, mem, immediate);
    }

    public final void sbb(Mem mem, Register register) {
        emitX86(INST_CODE.INST_SBB, mem, register);
    }

    public final void shl(Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_SHL, mem, immediate);
    }

    public final void shld(Mem mem, Register register, Immediate immediate) {
        emitX86(INST_CODE.INST_SHLD, mem, register, immediate);
    }

    public final void shr(Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_SHR, mem, immediate);
    }

    public final void shrd(Mem mem, Register register, Immediate immediate) {
        emitX86(INST_CODE.INST_SHRD, mem, register, immediate);
    }

    public final void sub(Mem mem, Register register) {
        emitX86(INST_CODE.INST_SUB, mem, register);
    }

    public final void test(Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_TEST, mem, immediate);
    }

    public final void xor_(Mem mem, Register register) {
        emitX86(INST_CODE.INST_XOR, mem, register);
    }

    public final void adc(Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_ADC, mem, immediate);
    }

    public final void add(Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_ADD, mem, immediate);
    }

    public final void and_(Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_AND, mem, immediate);
    }

    public final void call(Label label) {
        emitX86(INST_CODE.INST_CALL, label);
    }

    public final void cmp(Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_CMP, mem, immediate);
    }

    public final void imul(Register register, Immediate immediate) {
        emitX86(INST_CODE.INST_IMUL, register, immediate);
    }

    public final void jmp(Label label) {
        emitX86(INST_CODE.INST_JMP, label);
    }

    public final void mov(Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_MOV, mem, immediate);
    }

    public final void movd(Mem mem, XMMRegister xMMRegister) {
        emitX86(INST_CODE.INST_MOVD, mem, xMMRegister);
    }

    public final void movq(MMRegister mMRegister, Register register) {
        emitX86(INST_CODE.INST_MOVQ, mMRegister, register);
    }

    public final void or_(Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_OR, mem, immediate);
    }

    public final void pslld(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PSLLD, xMMRegister, mem);
    }

    public final void psllq(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PSLLQ, xMMRegister, mem);
    }

    public final void psllw(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PSLLW, xMMRegister, mem);
    }

    public final void psrad(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PSRAD, xMMRegister, mem);
    }

    public final void psraw(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PSRAW, xMMRegister, mem);
    }

    public final void psrld(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PSRLD, xMMRegister, mem);
    }

    public final void psrlq(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PSRLQ, xMMRegister, mem);
    }

    public final void psrlw(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_PSRLW, xMMRegister, mem);
    }

    public final void sbb(Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_SBB, mem, immediate);
    }

    public final void sub(Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_SUB, mem, immediate);
    }

    public final void xor_(Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_XOR, mem, immediate);
    }

    public final void imul(Register register, Register register2, Immediate immediate) {
        emitX86(INST_CODE.INST_IMUL, register, register2, immediate);
    }

    public final void movd(Register register, XMMRegister xMMRegister) {
        emitX86(INST_CODE.INST_MOVD, register, xMMRegister);
    }

    public final void movq(XMMRegister xMMRegister, XMMRegister xMMRegister2) {
        emitX86(INST_CODE.INST_MOVQ, xMMRegister, xMMRegister2);
    }

    public final void pslld(XMMRegister xMMRegister, Immediate immediate) {
        emitX86(INST_CODE.INST_PSLLD, xMMRegister, immediate);
    }

    public final void psllq(XMMRegister xMMRegister, Immediate immediate) {
        emitX86(INST_CODE.INST_PSLLQ, xMMRegister, immediate);
    }

    public final void psllw(XMMRegister xMMRegister, Immediate immediate) {
        emitX86(INST_CODE.INST_PSLLW, xMMRegister, immediate);
    }

    public final void psrad(XMMRegister xMMRegister, Immediate immediate) {
        emitX86(INST_CODE.INST_PSRAD, xMMRegister, immediate);
    }

    public final void psraw(XMMRegister xMMRegister, Immediate immediate) {
        emitX86(INST_CODE.INST_PSRAW, xMMRegister, immediate);
    }

    public final void psrld(XMMRegister xMMRegister, Immediate immediate) {
        emitX86(INST_CODE.INST_PSRLD, xMMRegister, immediate);
    }

    public final void psrlq(XMMRegister xMMRegister, Immediate immediate) {
        emitX86(INST_CODE.INST_PSRLQ, xMMRegister, immediate);
    }

    public final void psrlw(XMMRegister xMMRegister, Immediate immediate) {
        emitX86(INST_CODE.INST_PSRLW, xMMRegister, immediate);
    }

    public final void imul(Register register, Mem mem, Immediate immediate) {
        emitX86(INST_CODE.INST_IMUL, register, mem, immediate);
    }

    public final void movd(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_MOVD, xMMRegister, mem);
    }

    public final void movq(Mem mem, XMMRegister xMMRegister) {
        emitX86(INST_CODE.INST_MOVQ, mem, xMMRegister);
    }

    public final void movd(XMMRegister xMMRegister, Register register) {
        emitX86(INST_CODE.INST_MOVD, xMMRegister, register);
    }

    public final void movq(Register register, XMMRegister xMMRegister) {
        emitX86(INST_CODE.INST_MOVQ, register, xMMRegister);
    }

    public final void movq(XMMRegister xMMRegister, Mem mem) {
        emitX86(INST_CODE.INST_MOVQ, xMMRegister, mem);
    }

    public final void movq(XMMRegister xMMRegister, Register register) {
        emitX86(INST_CODE.INST_MOVQ, xMMRegister, register);
    }
}
