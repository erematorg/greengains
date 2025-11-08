package com.appsamurai.storyly.exoplayer2.common.util;

import androidx.annotation.GuardedBy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class CopyOnWriteMultiset<E> implements Iterable<E> {
    @GuardedBy("lock")
    private final Map<E, Integer> elementCounts = new HashMap();
    @GuardedBy("lock")
    private Set<E> elementSet = Collections.emptySet();
    @GuardedBy("lock")
    private List<E> elements = Collections.emptyList();
    private final Object lock = new Object();

    public void add(E e3) {
        synchronized (this.lock) {
            try {
                ArrayList arrayList = new ArrayList(this.elements);
                arrayList.add(e3);
                this.elements = Collections.unmodifiableList(arrayList);
                Integer num = this.elementCounts.get(e3);
                if (num == null) {
                    HashSet hashSet = new HashSet(this.elementSet);
                    hashSet.add(e3);
                    this.elementSet = Collections.unmodifiableSet(hashSet);
                }
                Map<E, Integer> map = this.elementCounts;
                int i3 = 1;
                if (num != null) {
                    i3 = 1 + num.intValue();
                }
                map.put(e3, Integer.valueOf(i3));
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public int count(E e3) {
        int intValue;
        synchronized (this.lock) {
            try {
                intValue = this.elementCounts.containsKey(e3) ? this.elementCounts.get(e3).intValue() : 0;
            } catch (Throwable th) {
                throw th;
            }
        }
        return intValue;
    }

    public Set<E> elementSet() {
        Set<E> set;
        synchronized (this.lock) {
            set = this.elementSet;
        }
        return set;
    }

    public Iterator<E> iterator() {
        Iterator<E> it;
        synchronized (this.lock) {
            it = this.elements.iterator();
        }
        return it;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004d, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void remove(E r5) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.lock
            monitor-enter(r0)
            java.util.Map<E, java.lang.Integer> r1 = r4.elementCounts     // Catch:{ all -> 0x000f }
            java.lang.Object r1 = r1.get(r5)     // Catch:{ all -> 0x000f }
            java.lang.Integer r1 = (java.lang.Integer) r1     // Catch:{ all -> 0x000f }
            if (r1 != 0) goto L_0x0011
            monitor-exit(r0)     // Catch:{ all -> 0x000f }
            return
        L_0x000f:
            r4 = move-exception
            goto L_0x004e
        L_0x0011:
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x000f }
            java.util.List<E> r3 = r4.elements     // Catch:{ all -> 0x000f }
            r2.<init>(r3)     // Catch:{ all -> 0x000f }
            r2.remove(r5)     // Catch:{ all -> 0x000f }
            java.util.List r2 = java.util.Collections.unmodifiableList(r2)     // Catch:{ all -> 0x000f }
            r4.elements = r2     // Catch:{ all -> 0x000f }
            int r2 = r1.intValue()     // Catch:{ all -> 0x000f }
            r3 = 1
            if (r2 != r3) goto L_0x003e
            java.util.Map<E, java.lang.Integer> r1 = r4.elementCounts     // Catch:{ all -> 0x000f }
            r1.remove(r5)     // Catch:{ all -> 0x000f }
            java.util.HashSet r1 = new java.util.HashSet     // Catch:{ all -> 0x000f }
            java.util.Set<E> r2 = r4.elementSet     // Catch:{ all -> 0x000f }
            r1.<init>(r2)     // Catch:{ all -> 0x000f }
            r1.remove(r5)     // Catch:{ all -> 0x000f }
            java.util.Set r5 = java.util.Collections.unmodifiableSet(r1)     // Catch:{ all -> 0x000f }
            r4.elementSet = r5     // Catch:{ all -> 0x000f }
            goto L_0x004c
        L_0x003e:
            java.util.Map<E, java.lang.Integer> r4 = r4.elementCounts     // Catch:{ all -> 0x000f }
            int r1 = r1.intValue()     // Catch:{ all -> 0x000f }
            int r1 = r1 - r3
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x000f }
            r4.put(r5, r1)     // Catch:{ all -> 0x000f }
        L_0x004c:
            monitor-exit(r0)     // Catch:{ all -> 0x000f }
            return
        L_0x004e:
            monitor-exit(r0)     // Catch:{ all -> 0x000f }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.common.util.CopyOnWriteMultiset.remove(java.lang.Object):void");
    }
}
