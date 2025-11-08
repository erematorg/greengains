package com.google.zxing.oned.rss.expanded;

import androidx.camera.camera2.internal.C0118y;
import java.util.ArrayList;
import java.util.List;

final class ExpandedRow {
    private final List<ExpandedPair> pairs;
    private final int rowNumber;

    public ExpandedRow(List<ExpandedPair> list, int i3) {
        this.pairs = new ArrayList(list);
        this.rowNumber = i3;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ExpandedRow)) {
            return false;
        }
        return this.pairs.equals(((ExpandedRow) obj).pairs);
    }

    public List<ExpandedPair> getPairs() {
        return this.pairs;
    }

    public int getRowNumber() {
        return this.rowNumber;
    }

    public int hashCode() {
        return this.pairs.hashCode();
    }

    public boolean isEquivalent(List<ExpandedPair> list) {
        return this.pairs.equals(list);
    }

    public String toString() {
        return C0118y.h(" }", this.pairs, new StringBuilder("{ "));
    }
}
