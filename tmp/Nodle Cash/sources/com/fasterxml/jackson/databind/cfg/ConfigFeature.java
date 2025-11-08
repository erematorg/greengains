package com.fasterxml.jackson.databind.cfg;

public interface ConfigFeature {
    boolean enabledByDefault();

    boolean enabledIn(int i3);

    int getMask();
}
