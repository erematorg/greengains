package com.github.mikephil.charting.utils;

import com.github.mikephil.charting.data.Entry;
import java.util.Comparator;

public class EntryXComparator implements Comparator<Entry> {
    public int compare(Entry entry, Entry entry2) {
        int i3 = ((entry.getX() - entry2.getX()) > 0.0f ? 1 : ((entry.getX() - entry2.getX()) == 0.0f ? 0 : -1));
        if (i3 == 0) {
            return 0;
        }
        return i3 > 0 ? 1 : -1;
    }
}
