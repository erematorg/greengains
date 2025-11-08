package com.fasterxml.jackson.dataformat.cbor;

public class CBORSimpleValue {
    protected final int _value;

    public CBORSimpleValue(int i3) {
        this._value = i3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CBORSimpleValue) {
            return this._value == ((CBORSimpleValue) obj)._value;
        }
        return false;
    }

    public int getValue() {
        return this._value;
    }

    public int hashCode() {
        return this._value;
    }

    public String toString() {
        return Integer.valueOf(this._value).toString();
    }
}
