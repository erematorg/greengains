package com.fasterxml.jackson.core.sym;

public final class Name3 extends Name {

    /* renamed from: q1  reason: collision with root package name */
    private final int f6560q1;
    private final int q2;
    private final int q3;

    public Name3(String str, int i3, int i4, int i5, int i6) {
        super(str, i3);
        this.f6560q1 = i4;
        this.q2 = i5;
        this.q3 = i6;
    }

    public boolean equals(int i3) {
        return false;
    }

    public boolean equals(int i3, int i4) {
        return false;
    }

    public boolean equals(int i3, int i4, int i5) {
        return this.f6560q1 == i3 && this.q2 == i4 && this.q3 == i5;
    }

    public boolean equals(int[] iArr, int i3) {
        return i3 == 3 && iArr[0] == this.f6560q1 && iArr[1] == this.q2 && iArr[2] == this.q3;
    }
}
