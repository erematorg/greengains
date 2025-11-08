package com.github.mikephil.charting.utils;

import com.github.mikephil.charting.utils.ObjectPool;
import java.util.List;

public class MPPointD extends ObjectPool.Poolable {
    private static ObjectPool<MPPointD> pool;

    /* renamed from: x  reason: collision with root package name */
    public double f6572x;

    /* renamed from: y  reason: collision with root package name */
    public double f6573y;

    static {
        ObjectPool<MPPointD> create = ObjectPool.create(64, new MPPointD(0.0d, 0.0d));
        pool = create;
        create.setReplenishPercentage(0.5f);
    }

    private MPPointD(double d2, double d3) {
        this.f6572x = d2;
        this.f6573y = d3;
    }

    public static MPPointD getInstance(double d2, double d3) {
        MPPointD mPPointD = pool.get();
        mPPointD.f6572x = d2;
        mPPointD.f6573y = d3;
        return mPPointD;
    }

    public static void recycleInstance(MPPointD mPPointD) {
        pool.recycle(mPPointD);
    }

    public static void recycleInstances(List<MPPointD> list) {
        pool.recycle(list);
    }

    public ObjectPool.Poolable instantiate() {
        return new MPPointD(0.0d, 0.0d);
    }

    public String toString() {
        return "MPPointD, x: " + this.f6572x + ", y: " + this.f6573y;
    }
}
