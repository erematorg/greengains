package com.google.zxing.common;

public interface ECIInput {
    char charAt(int i3);

    int getECIValue(int i3);

    boolean haveNCharacters(int i3, int i4);

    boolean isECI(int i3);

    int length();

    CharSequence subSequence(int i3, int i4);
}
