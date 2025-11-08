package com.kenai.jnr.x86asm;

@Deprecated
public abstract class SerializerCore {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static INST_CODE[] _cmovcctable = {INST_CODE.INST_CMOVO, INST_CODE.INST_CMOVNO, INST_CODE.INST_CMOVB, INST_CODE.INST_CMOVAE, INST_CODE.INST_CMOVE, INST_CODE.INST_CMOVNE, INST_CODE.INST_CMOVBE, INST_CODE.INST_CMOVA, INST_CODE.INST_CMOVS, INST_CODE.INST_CMOVNS, INST_CODE.INST_CMOVPE, INST_CODE.INST_CMOVPO, INST_CODE.INST_CMOVL, INST_CODE.INST_CMOVGE, INST_CODE.INST_CMOVLE, INST_CODE.INST_CMOVG};
    static INST_CODE[] _jcctable = {INST_CODE.INST_JO, INST_CODE.INST_JNO, INST_CODE.INST_JB, INST_CODE.INST_JAE, INST_CODE.INST_JE, INST_CODE.INST_JNE, INST_CODE.INST_JBE, INST_CODE.INST_JA, INST_CODE.INST_JS, INST_CODE.INST_JNS, INST_CODE.INST_JPE, INST_CODE.INST_JPO, INST_CODE.INST_JL, INST_CODE.INST_JGE, INST_CODE.INST_JLE, INST_CODE.INST_JG};
    static final Operand _none = new Operand(0, 0) {
    };
    static final INST_CODE[] _setcctable = {INST_CODE.INST_SETO, INST_CODE.INST_SETNO, INST_CODE.INST_SETB, INST_CODE.INST_SETAE, INST_CODE.INST_SETE, INST_CODE.INST_SETNE, INST_CODE.INST_SETBE, INST_CODE.INST_SETA, INST_CODE.INST_SETS, INST_CODE.INST_SETNS, INST_CODE.INST_SETPE, INST_CODE.INST_SETPO, INST_CODE.INST_SETL, INST_CODE.INST_SETGE, INST_CODE.INST_SETLE, INST_CODE.INST_SETG};

    public static INST_CODE conditionToCMovCC(CONDITION condition) {
        return _cmovcctable[condition.value()];
    }

    public static INST_CODE conditionToJCC(CONDITION condition) {
        return _jcctable[condition.value()];
    }

    public static INST_CODE conditionToSetCC(CONDITION condition) {
        return _setcctable[condition.value()];
    }

    public void _emitJcc(INST_CODE inst_code, Label label, int i3) {
        if (i3 == 0) {
            emitX86(inst_code, label);
        } else {
            emitX86(inst_code, label, Immediate.imm((long) i3));
        }
    }

    public abstract void _emitX86(INST_CODE inst_code, Operand operand, Operand operand2, Operand operand3);

    public void emitX86(INST_CODE inst_code) {
        Operand operand = _none;
        _emitX86(inst_code, operand, operand, operand);
    }

    public abstract boolean is64();

    public void emitX86(INST_CODE inst_code, Operand operand) {
        Operand operand2 = _none;
        _emitX86(inst_code, operand, operand2, operand2);
    }

    public void _emitJcc(INST_CODE inst_code, Label label, HINT hint) {
        if (hint == HINT.HINT_NONE) {
            emitX86(inst_code, label);
        } else {
            emitX86(inst_code, label, Immediate.imm((long) hint.value()));
        }
    }

    public void emitX86(INST_CODE inst_code, Operand operand, Operand operand2) {
        _emitX86(inst_code, operand, operand2, _none);
    }

    public void emitX86(INST_CODE inst_code, Operand operand, Operand operand2, Operand operand3) {
        _emitX86(inst_code, operand, operand2, operand3);
    }
}
