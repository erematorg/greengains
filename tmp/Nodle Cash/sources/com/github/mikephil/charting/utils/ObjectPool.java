package com.github.mikephil.charting.utils;

import com.github.mikephil.charting.utils.ObjectPool.Poolable;
import java.util.List;

public class ObjectPool<T extends Poolable> {
    private static int ids;
    private int desiredCapacity;
    private T modelObject;
    private Object[] objects;
    private int objectsPointer;
    private int poolId;
    private float replenishPercentage;

    public static abstract class Poolable {
        public static int NO_OWNER = -1;
        int currentOwnerId = NO_OWNER;

        public abstract Poolable instantiate();
    }

    private ObjectPool(int i3, T t2) {
        if (i3 > 0) {
            this.desiredCapacity = i3;
            this.objects = new Object[i3];
            this.objectsPointer = 0;
            this.modelObject = t2;
            this.replenishPercentage = 1.0f;
            refillPool();
            return;
        }
        throw new IllegalArgumentException("Object Pool must be instantiated with a capacity greater than 0!");
    }

    public static synchronized ObjectPool create(int i3, Poolable poolable) {
        ObjectPool objectPool;
        synchronized (ObjectPool.class) {
            objectPool = new ObjectPool(i3, poolable);
            int i4 = ids;
            objectPool.poolId = i4;
            ids = i4 + 1;
        }
        return objectPool;
    }

    private void refillPool() {
        refillPool(this.replenishPercentage);
    }

    private void resizePool() {
        int i3 = this.desiredCapacity;
        int i4 = i3 * 2;
        this.desiredCapacity = i4;
        Object[] objArr = new Object[i4];
        for (int i5 = 0; i5 < i3; i5++) {
            objArr[i5] = this.objects[i5];
        }
        this.objects = objArr;
    }

    public synchronized T get() {
        T t2;
        try {
            if (this.objectsPointer == -1 && this.replenishPercentage > 0.0f) {
                refillPool();
            }
            T[] tArr = this.objects;
            int i3 = this.objectsPointer;
            t2 = (Poolable) tArr[i3];
            t2.currentOwnerId = Poolable.NO_OWNER;
            this.objectsPointer = i3 - 1;
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return t2;
    }

    public int getPoolCapacity() {
        return this.objects.length;
    }

    public int getPoolCount() {
        return this.objectsPointer + 1;
    }

    public int getPoolId() {
        return this.poolId;
    }

    public float getReplenishPercentage() {
        return this.replenishPercentage;
    }

    public synchronized void recycle(T t2) {
        try {
            int i3 = t2.currentOwnerId;
            if (i3 == Poolable.NO_OWNER) {
                int i4 = this.objectsPointer + 1;
                this.objectsPointer = i4;
                if (i4 >= this.objects.length) {
                    resizePool();
                }
                t2.currentOwnerId = this.poolId;
                this.objects[this.objectsPointer] = t2;
            } else if (i3 == this.poolId) {
                throw new IllegalArgumentException("The object passed is already stored in this pool!");
            } else {
                throw new IllegalArgumentException("The object to recycle already belongs to poolId " + t2.currentOwnerId + ".  Object cannot belong to two different pool instances simultaneously!");
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000b, code lost:
        if (r3 < 0.0f) goto L_0x0006;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setReplenishPercentage(float r3) {
        /*
            r2 = this;
            r0 = 1065353216(0x3f800000, float:1.0)
            int r1 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r1 <= 0) goto L_0x0008
        L_0x0006:
            r3 = r0
            goto L_0x000e
        L_0x0008:
            r0 = 0
            int r1 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r1 >= 0) goto L_0x000e
            goto L_0x0006
        L_0x000e:
            r2.replenishPercentage = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.mikephil.charting.utils.ObjectPool.setReplenishPercentage(float):void");
    }

    private void refillPool(float f2) {
        int i3 = this.desiredCapacity;
        int i4 = (int) (((float) i3) * f2);
        if (i4 < 1) {
            i3 = 1;
        } else if (i4 <= i3) {
            i3 = i4;
        }
        for (int i5 = 0; i5 < i3; i5++) {
            this.objects[i5] = this.modelObject.instantiate();
        }
        this.objectsPointer = i3 - 1;
    }

    public synchronized void recycle(List<T> list) {
        while (list.size() + this.objectsPointer + 1 > this.desiredCapacity) {
            try {
                resizePool();
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        int size = list.size();
        int i3 = 0;
        while (i3 < size) {
            Poolable poolable = (Poolable) list.get(i3);
            int i4 = poolable.currentOwnerId;
            if (i4 == Poolable.NO_OWNER) {
                poolable.currentOwnerId = this.poolId;
                this.objects[this.objectsPointer + 1 + i3] = poolable;
                i3++;
            } else if (i4 == this.poolId) {
                throw new IllegalArgumentException("The object passed is already stored in this pool!");
            } else {
                throw new IllegalArgumentException("The object to recycle already belongs to poolId " + poolable.currentOwnerId + ".  Object cannot belong to two different pool instances simultaneously!");
            }
        }
        this.objectsPointer += size;
    }
}
