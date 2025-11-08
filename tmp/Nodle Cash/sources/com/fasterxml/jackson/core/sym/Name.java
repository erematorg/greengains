package com.fasterxml.jackson.core.sym;

public abstract class Name {
    protected final int _hashCode;
    protected final String _name;

    public Name(String str, int i3) {
        this._name = str;
        this._hashCode = i3;
    }

    public abstract boolean equals(int i3);

    public abstract boolean equals(int i3, int i4);

    public abstract boolean equals(int i3, int i4, int i5);

    public boolean equals(Object obj) {
        return obj == this;
    }

    public abstract boolean equals(int[] iArr, int i3);

    public String getName() {
        return this._name;
    }

    public final int hashCode() {
        return this._hashCode;
    }

    public String toString() {
        return this._name;
    }
}
