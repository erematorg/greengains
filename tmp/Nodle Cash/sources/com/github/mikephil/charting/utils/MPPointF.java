package com.github.mikephil.charting.utils;

import android.os.Parcel;
import android.os.Parcelable;
import com.github.mikephil.charting.utils.ObjectPool;
import java.util.List;

public class MPPointF extends ObjectPool.Poolable {
    public static final Parcelable.Creator<MPPointF> CREATOR = new Parcelable.Creator<MPPointF>() {
        public MPPointF createFromParcel(Parcel parcel) {
            MPPointF mPPointF = new MPPointF(0.0f, 0.0f);
            mPPointF.my_readFromParcel(parcel);
            return mPPointF;
        }

        public MPPointF[] newArray(int i3) {
            return new MPPointF[i3];
        }
    };
    private static ObjectPool<MPPointF> pool;

    /* renamed from: x  reason: collision with root package name */
    public float f6574x;

    /* renamed from: y  reason: collision with root package name */
    public float f6575y;

    static {
        ObjectPool<MPPointF> create = ObjectPool.create(32, new MPPointF(0.0f, 0.0f));
        pool = create;
        create.setReplenishPercentage(0.5f);
    }

    public MPPointF() {
    }

    public static MPPointF getInstance(float f2, float f3) {
        MPPointF mPPointF = pool.get();
        mPPointF.f6574x = f2;
        mPPointF.f6575y = f3;
        return mPPointF;
    }

    public static void recycleInstance(MPPointF mPPointF) {
        pool.recycle(mPPointF);
    }

    public static void recycleInstances(List<MPPointF> list) {
        pool.recycle(list);
    }

    public float getX() {
        return this.f6574x;
    }

    public float getY() {
        return this.f6575y;
    }

    public ObjectPool.Poolable instantiate() {
        return new MPPointF(0.0f, 0.0f);
    }

    public void my_readFromParcel(Parcel parcel) {
        this.f6574x = parcel.readFloat();
        this.f6575y = parcel.readFloat();
    }

    public MPPointF(float f2, float f3) {
        this.f6574x = f2;
        this.f6575y = f3;
    }

    public static MPPointF getInstance() {
        return pool.get();
    }

    public static MPPointF getInstance(MPPointF mPPointF) {
        MPPointF mPPointF2 = pool.get();
        mPPointF2.f6574x = mPPointF.f6574x;
        mPPointF2.f6575y = mPPointF.f6575y;
        return mPPointF2;
    }
}
