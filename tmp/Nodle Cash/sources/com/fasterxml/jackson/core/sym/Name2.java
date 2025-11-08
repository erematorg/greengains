package com.fasterxml.jackson.core.sym;

public final class Name2 extends Name {

    /* renamed from: q1  reason: collision with root package name */
    private final int f6559q1;
    private final int q2;

    public Name2(String str, int i3, int i4, int i5) {
        super(str, i3);
        this.f6559q1 = i4;
        this.q2 = i5;
    }

    public boolean equals(int i3) {
        return false;
    }

    public boolean equals(int i3, int i4, int i5) {
        return false;
    }

    public boolean equals(int i3, int i4) {
        return i3 == this.f6559q1 && i4 == this.q2;
    }

    public boolean equals(int[] iArr, int i3) {
        return i3 == 2 && iArr[0] == this.f6559q1 && iArr[1] == this.q2;
    }
}
