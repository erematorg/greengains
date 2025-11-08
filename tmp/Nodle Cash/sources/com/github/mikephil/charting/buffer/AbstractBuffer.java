package com.github.mikephil.charting.buffer;

public abstract class AbstractBuffer<T> {
    public final float[] buffer;
    protected int index = 0;
    protected int mFrom = 0;
    protected int mTo = 0;
    protected float phaseX = 1.0f;
    protected float phaseY = 1.0f;

    public AbstractBuffer(int i3) {
        this.buffer = new float[i3];
    }

    public abstract void feed(T t2);

    public void limitFrom(int i3) {
        if (i3 < 0) {
            i3 = 0;
        }
        this.mFrom = i3;
    }

    public void limitTo(int i3) {
        if (i3 < 0) {
            i3 = 0;
        }
        this.mTo = i3;
    }

    public void reset() {
        this.index = 0;
    }

    public void setPhases(float f2, float f3) {
        this.phaseX = f2;
        this.phaseY = f3;
    }

    public int size() {
        return this.buffer.length;
    }
}
