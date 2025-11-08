package com.adjust.sdk;

public enum TrackingState {
    OPTED_OUT(1);
    
    private int value;

    private TrackingState(int i3) {
        this.value = i3;
    }

    public int getValue() {
        return this.value;
    }
}
