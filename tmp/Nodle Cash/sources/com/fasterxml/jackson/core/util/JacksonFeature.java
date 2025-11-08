package com.fasterxml.jackson.core.util;

public interface JacksonFeature {
    boolean enabledByDefault();

    boolean enabledIn(int i3);

    int getMask();
}
