package com.kenai.jnr.x86asm;

@Deprecated
public final class Mem extends Operand {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final int base;
    private final long displacement;
    private final int index;
    private final Label label;
    private final SEGMENT segmentPrefix;
    private final int shift;
    private final long target;

    public Mem(Label label2, long j2, int i3) {
        this(255, 255, 0, SEGMENT.SEGMENT_NONE, label2, 0, j2, i3);
    }

    public final int base() {
        return this.base;
    }

    public final long displacement() {
        return this.displacement;
    }

    public final boolean hasBase() {
        return this.base != 255;
    }

    public boolean hasIndex() {
        return this.index != 255;
    }

    public final boolean hasLabel() {
        return this.label != null;
    }

    public final int index() {
        return this.index;
    }

    public final Label label() {
        return this.label;
    }

    public final SEGMENT segmentPrefix() {
        return this.segmentPrefix;
    }

    public final int shift() {
        return this.shift;
    }

    public final long target() {
        return this.target;
    }

    public Mem(Register register, long j2, int i3) {
        this(register.index(), 255, 0, SEGMENT.SEGMENT_NONE, (Label) null, 0, j2, i3);
    }

    public Mem(Register register, Register register2, int i3, long j2, int i4) {
        this(register.index(), register2.index(), i3, SEGMENT.SEGMENT_NONE, (Label) null, 0, j2, i4);
    }

    public Mem(Label label2, Register register, int i3, long j2, int i4) {
        this(0, register.index(), i3, SEGMENT.SEGMENT_NONE, label2, 0, j2, i4);
    }

    public Mem(long j2, long j3, SEGMENT segment, int i3) {
        this(255, 255, 0, segment, (Label) null, j2, j3, i3);
    }

    public Mem(long j2, Register register, int i3, SEGMENT segment, long j3, int i4) {
        this(255, register.index(), i3, segment, (Label) null, j2, j3, i4);
    }

    private Mem(int i3, int i4, int i5, SEGMENT segment, Label label2, long j2, long j3, int i6) {
        super(2, i6);
        this.base = i3;
        this.index = i4;
        this.shift = i5;
        this.segmentPrefix = segment;
        this.label = label2;
        this.target = j2;
        this.displacement = j3;
    }
}
