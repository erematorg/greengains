package com.appsamurai.storyly.exoplayer2.core.upstream;

import androidx.compose.ui.autofill.a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SlidingPercentile {
    private static final Comparator<Sample> INDEX_COMPARATOR = new c(0);
    private static final int MAX_RECYCLED_SAMPLES = 5;
    private static final int SORT_ORDER_BY_INDEX = 1;
    private static final int SORT_ORDER_BY_VALUE = 0;
    private static final int SORT_ORDER_NONE = -1;
    private static final Comparator<Sample> VALUE_COMPARATOR = new c(1);
    private int currentSortOrder = -1;
    private final int maxWeight;
    private int nextSampleIndex;
    private int recycledSampleCount;
    private final Sample[] recycledSamples = new Sample[5];
    private final ArrayList<Sample> samples = new ArrayList<>();
    private int totalWeight;

    public static class Sample {
        public int index;
        public float value;
        public int weight;

        private Sample() {
        }
    }

    public SlidingPercentile(int i3) {
        this.maxWeight = i3;
    }

    private void ensureSortedByIndex() {
        if (this.currentSortOrder != 1) {
            Collections.sort(this.samples, INDEX_COMPARATOR);
            this.currentSortOrder = 1;
        }
    }

    private void ensureSortedByValue() {
        if (this.currentSortOrder != 0) {
            Collections.sort(this.samples, VALUE_COMPARATOR);
            this.currentSortOrder = 0;
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$static$0(Sample sample, Sample sample2) {
        return sample.index - sample2.index;
    }

    public void addSample(int i3, float f2) {
        Sample sample;
        ensureSortedByIndex();
        int i4 = this.recycledSampleCount;
        if (i4 > 0) {
            Sample[] sampleArr = this.recycledSamples;
            int i5 = i4 - 1;
            this.recycledSampleCount = i5;
            sample = sampleArr[i5];
        } else {
            sample = new Sample();
        }
        int i6 = this.nextSampleIndex;
        this.nextSampleIndex = i6 + 1;
        sample.index = i6;
        sample.weight = i3;
        sample.value = f2;
        this.samples.add(sample);
        this.totalWeight += i3;
        while (true) {
            int i7 = this.totalWeight;
            int i8 = this.maxWeight;
            if (i7 > i8) {
                int i9 = i7 - i8;
                Sample sample2 = this.samples.get(0);
                int i10 = sample2.weight;
                if (i10 <= i9) {
                    this.totalWeight -= i10;
                    this.samples.remove(0);
                    int i11 = this.recycledSampleCount;
                    if (i11 < 5) {
                        Sample[] sampleArr2 = this.recycledSamples;
                        this.recycledSampleCount = i11 + 1;
                        sampleArr2[i11] = sample2;
                    }
                } else {
                    sample2.weight = i10 - i9;
                    this.totalWeight -= i9;
                }
            } else {
                return;
            }
        }
    }

    public float getPercentile(float f2) {
        ensureSortedByValue();
        float f3 = f2 * ((float) this.totalWeight);
        int i3 = 0;
        for (int i4 = 0; i4 < this.samples.size(); i4++) {
            Sample sample = this.samples.get(i4);
            i3 += sample.weight;
            if (((float) i3) >= f3) {
                return sample.value;
            }
        }
        if (this.samples.isEmpty()) {
            return Float.NaN;
        }
        return ((Sample) a.h(this.samples, 1)).value;
    }

    public void reset() {
        this.samples.clear();
        this.currentSortOrder = -1;
        this.nextSampleIndex = 0;
        this.totalWeight = 0;
    }
}
