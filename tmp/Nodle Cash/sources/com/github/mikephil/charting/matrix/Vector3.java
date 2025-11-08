package com.github.mikephil.charting.matrix;

public final class Vector3 {
    public static final Vector3 UNIT_X = new Vector3(1.0f, 0.0f, 0.0f);
    public static final Vector3 UNIT_Y = new Vector3(0.0f, 1.0f, 0.0f);
    public static final Vector3 UNIT_Z = new Vector3(0.0f, 0.0f, 1.0f);
    public static final Vector3 ZERO = new Vector3(0.0f, 0.0f, 0.0f);

    /* renamed from: x  reason: collision with root package name */
    public float f6569x;

    /* renamed from: y  reason: collision with root package name */
    public float f6570y;

    /* renamed from: z  reason: collision with root package name */
    public float f6571z;

    public Vector3() {
    }

    public final void add(Vector3 vector3) {
        this.f6569x += vector3.f6569x;
        this.f6570y += vector3.f6570y;
        this.f6571z += vector3.f6571z;
    }

    public final Vector3 cross(Vector3 vector3) {
        float f2 = this.f6570y;
        float f3 = vector3.f6571z;
        float f4 = this.f6571z;
        float f5 = vector3.f6570y;
        float f6 = vector3.f6569x;
        float f7 = this.f6569x;
        return new Vector3((f2 * f3) - (f4 * f5), (f4 * f6) - (f3 * f7), (f7 * f5) - (f2 * f6));
    }

    public final float distance2(Vector3 vector3) {
        float f2 = this.f6569x - vector3.f6569x;
        float f3 = this.f6570y - vector3.f6570y;
        float f4 = this.f6571z - vector3.f6571z;
        return (f4 * f4) + (f3 * f3) + (f2 * f2);
    }

    public final void divide(float f2) {
        if (f2 != 0.0f) {
            this.f6569x /= f2;
            this.f6570y /= f2;
            this.f6571z /= f2;
        }
    }

    public final float dot(Vector3 vector3) {
        float f2 = this.f6569x * vector3.f6569x;
        return (this.f6571z * vector3.f6571z) + (this.f6570y * vector3.f6570y) + f2;
    }

    public final float length() {
        return (float) Math.sqrt((double) length2());
    }

    public final float length2() {
        float f2 = this.f6569x;
        float f3 = this.f6570y;
        float f4 = f3 * f3;
        float f5 = this.f6571z;
        return (f5 * f5) + f4 + (f2 * f2);
    }

    public final void multiply(float f2) {
        this.f6569x *= f2;
        this.f6570y *= f2;
        this.f6571z *= f2;
    }

    public final float normalize() {
        float length = length();
        if (length != 0.0f) {
            this.f6569x /= length;
            this.f6570y /= length;
            this.f6571z /= length;
        }
        return length;
    }

    public final boolean pointsInSameDirection(Vector3 vector3) {
        return dot(vector3) > 0.0f;
    }

    public final void set(Vector3 vector3) {
        this.f6569x = vector3.f6569x;
        this.f6570y = vector3.f6570y;
        this.f6571z = vector3.f6571z;
    }

    public final void subtract(Vector3 vector3) {
        this.f6569x -= vector3.f6569x;
        this.f6570y -= vector3.f6570y;
        this.f6571z -= vector3.f6571z;
    }

    public final void subtractMultiple(Vector3 vector3, float f2) {
        this.f6569x -= vector3.f6569x * f2;
        this.f6570y -= vector3.f6570y * f2;
        this.f6571z -= vector3.f6571z * f2;
    }

    public final void zero() {
        set(0.0f, 0.0f, 0.0f);
    }

    public Vector3(float[] fArr) {
        set(fArr[0], fArr[1], fArr[2]);
    }

    public Vector3(float f2, float f3, float f4) {
        set(f2, f3, f4);
    }

    public final void add(float f2, float f3, float f4) {
        this.f6569x += f2;
        this.f6570y += f3;
        this.f6571z += f4;
    }

    public final void multiply(Vector3 vector3) {
        this.f6569x *= vector3.f6569x;
        this.f6570y *= vector3.f6570y;
        this.f6571z *= vector3.f6571z;
    }

    public final void set(float f2, float f3, float f4) {
        this.f6569x = f2;
        this.f6570y = f3;
        this.f6571z = f4;
    }

    public Vector3(Vector3 vector3) {
        set(vector3);
    }
}
