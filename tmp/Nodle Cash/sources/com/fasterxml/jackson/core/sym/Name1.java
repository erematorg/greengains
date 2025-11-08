package com.fasterxml.jackson.core.sym;

public final class Name1 extends Name {
    private static final Name1 EMPTY = new Name1("", 0, 0);

    /* renamed from: q  reason: collision with root package name */
    private final int f6558q;

    public Name1(String str, int i3, int i4) {
        super(str, i3);
        this.f6558q = i4;
    }

    public static Name1 getEmptyName() {
        return EMPTY;
    }

    public boolean equals(int i3, int i4, int i5) {
        return false;
    }

    public boolean equals(int i3) {
        return i3 == this.f6558q;
    }

    public boolean equals(int i3, int i4) {
        return i3 == this.f6558q && i4 == 0;
    }

    public boolean equals(int[] iArr, int i3) {
        return i3 == 1 && iArr[0] == this.f6558q;
    }
}
