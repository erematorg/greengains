package com.google.android.material.shape;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.google.android.material.shadow.ShadowRenderer;
import java.util.ArrayList;
import java.util.List;

public class ShapePath {
    protected static final float ANGLE_LEFT = 180.0f;
    private static final float ANGLE_UP = 270.0f;
    private boolean containsIncompatibleShadowOp;
    @Deprecated
    public float currentShadowAngle;
    @Deprecated
    public float endShadowAngle;
    @Deprecated
    public float endX;
    @Deprecated
    public float endY;
    private final List<PathOperation> operations = new ArrayList();
    private final List<ShadowCompatOperation> shadowCompatOperations = new ArrayList();
    @Deprecated
    public float startX;
    @Deprecated
    public float startY;

    public static class ArcShadowOperation extends ShadowCompatOperation {
        private final PathArcOperation operation;

        public ArcShadowOperation(PathArcOperation pathArcOperation) {
            this.operation = pathArcOperation;
        }

        public void draw(Matrix matrix, @NonNull ShadowRenderer shadowRenderer, int i3, @NonNull Canvas canvas) {
            float access$800 = this.operation.getStartAngle();
            float access$900 = this.operation.getSweepAngle();
            shadowRenderer.drawCornerShadow(canvas, matrix, new RectF(this.operation.getLeft(), this.operation.getTop(), this.operation.getRight(), this.operation.getBottom()), i3, access$800, access$900);
        }
    }

    public static class InnerCornerShadowOperation extends ShadowCompatOperation {
        private final PathLineOperation operation1;
        private final PathLineOperation operation2;
        private final float startX;
        private final float startY;

        public InnerCornerShadowOperation(PathLineOperation pathLineOperation, PathLineOperation pathLineOperation2, float f2, float f3) {
            this.operation1 = pathLineOperation;
            this.operation2 = pathLineOperation2;
            this.startX = f2;
            this.startY = f3;
        }

        public void draw(Matrix matrix, ShadowRenderer shadowRenderer, int i3, Canvas canvas) {
            Matrix matrix2 = matrix;
            ShadowRenderer shadowRenderer2 = shadowRenderer;
            int i4 = i3;
            Canvas canvas2 = canvas;
            float sweepAngle = getSweepAngle();
            if (sweepAngle <= 0.0f) {
                double hypot = Math.hypot((double) (this.operation1.f6682x - this.startX), (double) (this.operation1.f6683y - this.startY));
                double hypot2 = Math.hypot((double) (this.operation2.f6682x - this.operation1.f6682x), (double) (this.operation2.f6683y - this.operation1.f6683y));
                float min = (float) Math.min((double) i4, Math.min(hypot, hypot2));
                double d2 = (double) min;
                double tan = Math.tan(Math.toRadians((double) ((-sweepAngle) / 2.0f))) * d2;
                if (hypot > tan) {
                    RectF rectF = new RectF(0.0f, 0.0f, (float) (hypot - tan), 0.0f);
                    this.renderMatrix.set(matrix2);
                    this.renderMatrix.preTranslate(this.startX, this.startY);
                    this.renderMatrix.preRotate(getStartAngle());
                    shadowRenderer.drawEdgeShadow(canvas2, this.renderMatrix, rectF, i4);
                } else {
                    ShadowRenderer shadowRenderer3 = shadowRenderer;
                }
                float f2 = 2.0f * min;
                RectF rectF2 = new RectF(0.0f, 0.0f, f2, f2);
                this.renderMatrix.set(matrix2);
                this.renderMatrix.preTranslate(this.operation1.f6682x, this.operation1.f6683y);
                this.renderMatrix.preRotate(getStartAngle());
                this.renderMatrix.preTranslate((float) ((-tan) - d2), -2.0f * min);
                float[] fArr = {(float) (d2 + tan), f2};
                int i5 = (int) min;
                double d3 = tan;
                shadowRenderer.drawInnerCornerShadow(canvas, this.renderMatrix, rectF2, i5, 450.0f, sweepAngle, fArr);
                if (hypot2 > d3) {
                    RectF rectF3 = new RectF(0.0f, 0.0f, (float) (hypot2 - d3), 0.0f);
                    this.renderMatrix.set(matrix2);
                    this.renderMatrix.preTranslate(this.operation1.f6682x, this.operation1.f6683y);
                    this.renderMatrix.preRotate(getEndAngle());
                    this.renderMatrix.preTranslate((float) d3, 0.0f);
                    shadowRenderer.drawEdgeShadow(canvas, this.renderMatrix, rectF3, i3);
                }
            }
        }

        public float getEndAngle() {
            return (float) Math.toDegrees(Math.atan((double) ((this.operation2.f6683y - this.operation1.f6683y) / (this.operation2.f6682x - this.operation1.f6682x))));
        }

        public float getStartAngle() {
            return (float) Math.toDegrees(Math.atan((double) ((this.operation1.f6683y - this.startY) / (this.operation1.f6682x - this.startX))));
        }

        public float getSweepAngle() {
            float endAngle = ((getEndAngle() - getStartAngle()) + 360.0f) % 360.0f;
            return endAngle <= 180.0f ? endAngle : endAngle - 360.0f;
        }
    }

    public static class LineShadowOperation extends ShadowCompatOperation {
        private final PathLineOperation operation;
        private final float startX;
        private final float startY;

        public LineShadowOperation(PathLineOperation pathLineOperation, float f2, float f3) {
            this.operation = pathLineOperation;
            this.startX = f2;
            this.startY = f3;
        }

        public void draw(Matrix matrix, @NonNull ShadowRenderer shadowRenderer, int i3, @NonNull Canvas canvas) {
            RectF rectF = new RectF(0.0f, 0.0f, (float) Math.hypot((double) (this.operation.f6683y - this.startY), (double) (this.operation.f6682x - this.startX)), 0.0f);
            this.renderMatrix.set(matrix);
            this.renderMatrix.preTranslate(this.startX, this.startY);
            this.renderMatrix.preRotate(getAngle());
            shadowRenderer.drawEdgeShadow(canvas, this.renderMatrix, rectF, i3);
        }

        public float getAngle() {
            return (float) Math.toDegrees(Math.atan((double) ((this.operation.f6683y - this.startY) / (this.operation.f6682x - this.startX))));
        }
    }

    public static class PathArcOperation extends PathOperation {
        private static final RectF rectF = new RectF();
        @Deprecated
        public float bottom;
        @Deprecated
        public float left;
        @Deprecated
        public float right;
        @Deprecated
        public float startAngle;
        @Deprecated
        public float sweepAngle;
        @Deprecated
        public float top;

        public PathArcOperation(float f2, float f3, float f4, float f5) {
            setLeft(f2);
            setTop(f3);
            setRight(f4);
            setBottom(f5);
        }

        /* access modifiers changed from: private */
        public float getBottom() {
            return this.bottom;
        }

        /* access modifiers changed from: private */
        public float getLeft() {
            return this.left;
        }

        /* access modifiers changed from: private */
        public float getRight() {
            return this.right;
        }

        /* access modifiers changed from: private */
        public float getStartAngle() {
            return this.startAngle;
        }

        /* access modifiers changed from: private */
        public float getSweepAngle() {
            return this.sweepAngle;
        }

        /* access modifiers changed from: private */
        public float getTop() {
            return this.top;
        }

        private void setBottom(float f2) {
            this.bottom = f2;
        }

        private void setLeft(float f2) {
            this.left = f2;
        }

        private void setRight(float f2) {
            this.right = f2;
        }

        /* access modifiers changed from: private */
        public void setStartAngle(float f2) {
            this.startAngle = f2;
        }

        /* access modifiers changed from: private */
        public void setSweepAngle(float f2) {
            this.sweepAngle = f2;
        }

        private void setTop(float f2) {
            this.top = f2;
        }

        public void applyToPath(@NonNull Matrix matrix, @NonNull Path path) {
            Matrix matrix2 = this.matrix;
            matrix.invert(matrix2);
            path.transform(matrix2);
            RectF rectF2 = rectF;
            rectF2.set(getLeft(), getTop(), getRight(), getBottom());
            path.arcTo(rectF2, getStartAngle(), getSweepAngle(), false);
            path.transform(matrix);
        }
    }

    public static class PathCubicOperation extends PathOperation {
        private float controlX1;
        private float controlX2;
        private float controlY1;
        private float controlY2;
        private float endX;
        private float endY;

        public PathCubicOperation(float f2, float f3, float f4, float f5, float f6, float f7) {
            setControlX1(f2);
            setControlY1(f3);
            setControlX2(f4);
            setControlY2(f5);
            setEndX(f6);
            setEndY(f7);
        }

        private float getControlX1() {
            return this.controlX1;
        }

        private float getControlX2() {
            return this.controlX2;
        }

        private float getControlY1() {
            return this.controlY1;
        }

        private float getControlY2() {
            return this.controlY1;
        }

        private float getEndX() {
            return this.endX;
        }

        private float getEndY() {
            return this.endY;
        }

        private void setControlX1(float f2) {
            this.controlX1 = f2;
        }

        private void setControlX2(float f2) {
            this.controlX2 = f2;
        }

        private void setControlY1(float f2) {
            this.controlY1 = f2;
        }

        private void setControlY2(float f2) {
            this.controlY2 = f2;
        }

        private void setEndX(float f2) {
            this.endX = f2;
        }

        private void setEndY(float f2) {
            this.endY = f2;
        }

        public void applyToPath(@NonNull Matrix matrix, @NonNull Path path) {
            Matrix matrix2 = this.matrix;
            matrix.invert(matrix2);
            path.transform(matrix2);
            path.cubicTo(this.controlX1, this.controlY1, this.controlX2, this.controlY2, this.endX, this.endY);
            path.transform(matrix);
        }
    }

    public static class PathLineOperation extends PathOperation {
        /* access modifiers changed from: private */

        /* renamed from: x  reason: collision with root package name */
        public float f6682x;
        /* access modifiers changed from: private */

        /* renamed from: y  reason: collision with root package name */
        public float f6683y;

        public void applyToPath(@NonNull Matrix matrix, @NonNull Path path) {
            Matrix matrix2 = this.matrix;
            matrix.invert(matrix2);
            path.transform(matrix2);
            path.lineTo(this.f6682x, this.f6683y);
            path.transform(matrix);
        }
    }

    public static abstract class PathOperation {
        protected final Matrix matrix = new Matrix();

        public abstract void applyToPath(Matrix matrix2, Path path);
    }

    public static class PathQuadOperation extends PathOperation {
        @Deprecated
        public float controlX;
        @Deprecated
        public float controlY;
        @Deprecated
        public float endX;
        @Deprecated
        public float endY;

        private float getControlX() {
            return this.controlX;
        }

        private float getControlY() {
            return this.controlY;
        }

        private float getEndX() {
            return this.endX;
        }

        private float getEndY() {
            return this.endY;
        }

        /* access modifiers changed from: private */
        public void setControlX(float f2) {
            this.controlX = f2;
        }

        /* access modifiers changed from: private */
        public void setControlY(float f2) {
            this.controlY = f2;
        }

        /* access modifiers changed from: private */
        public void setEndX(float f2) {
            this.endX = f2;
        }

        /* access modifiers changed from: private */
        public void setEndY(float f2) {
            this.endY = f2;
        }

        public void applyToPath(@NonNull Matrix matrix, @NonNull Path path) {
            Matrix matrix2 = this.matrix;
            matrix.invert(matrix2);
            path.transform(matrix2);
            path.quadTo(getControlX(), getControlY(), getEndX(), getEndY());
            path.transform(matrix);
        }
    }

    public static abstract class ShadowCompatOperation {
        static final Matrix IDENTITY_MATRIX = new Matrix();
        final Matrix renderMatrix = new Matrix();

        public abstract void draw(Matrix matrix, ShadowRenderer shadowRenderer, int i3, Canvas canvas);

        public final void draw(ShadowRenderer shadowRenderer, int i3, Canvas canvas) {
            draw(IDENTITY_MATRIX, shadowRenderer, i3, canvas);
        }
    }

    public ShapePath() {
        reset(0.0f, 0.0f);
    }

    private void addConnectingShadowIfNecessary(float f2) {
        if (getCurrentShadowAngle() != f2) {
            float currentShadowAngle2 = ((f2 - getCurrentShadowAngle()) + 360.0f) % 360.0f;
            if (currentShadowAngle2 <= 180.0f) {
                PathArcOperation pathArcOperation = new PathArcOperation(getEndX(), getEndY(), getEndX(), getEndY());
                pathArcOperation.setStartAngle(getCurrentShadowAngle());
                pathArcOperation.setSweepAngle(currentShadowAngle2);
                this.shadowCompatOperations.add(new ArcShadowOperation(pathArcOperation));
                setCurrentShadowAngle(f2);
            }
        }
    }

    private void addShadowCompatOperation(ShadowCompatOperation shadowCompatOperation, float f2, float f3) {
        addConnectingShadowIfNecessary(f2);
        this.shadowCompatOperations.add(shadowCompatOperation);
        setCurrentShadowAngle(f3);
    }

    private float getCurrentShadowAngle() {
        return this.currentShadowAngle;
    }

    private float getEndShadowAngle() {
        return this.endShadowAngle;
    }

    private void setCurrentShadowAngle(float f2) {
        this.currentShadowAngle = f2;
    }

    private void setEndShadowAngle(float f2) {
        this.endShadowAngle = f2;
    }

    private void setEndX(float f2) {
        this.endX = f2;
    }

    private void setEndY(float f2) {
        this.endY = f2;
    }

    private void setStartX(float f2) {
        this.startX = f2;
    }

    private void setStartY(float f2) {
        this.startY = f2;
    }

    public void addArc(float f2, float f3, float f4, float f5, float f6, float f7) {
        PathArcOperation pathArcOperation = new PathArcOperation(f2, f3, f4, f5);
        pathArcOperation.setStartAngle(f6);
        pathArcOperation.setSweepAngle(f7);
        this.operations.add(pathArcOperation);
        ArcShadowOperation arcShadowOperation = new ArcShadowOperation(pathArcOperation);
        float f8 = f6 + f7;
        boolean z2 = f7 < 0.0f;
        if (z2) {
            f6 = (f6 + 180.0f) % 360.0f;
        }
        addShadowCompatOperation(arcShadowOperation, f6, z2 ? (180.0f + f8) % 360.0f : f8);
        double d2 = (double) f8;
        setEndX((((f4 - f2) / 2.0f) * ((float) Math.cos(Math.toRadians(d2)))) + ((f2 + f4) * 0.5f));
        setEndY((((f5 - f3) / 2.0f) * ((float) Math.sin(Math.toRadians(d2)))) + ((f3 + f5) * 0.5f));
    }

    public void applyToPath(Matrix matrix, Path path) {
        int size = this.operations.size();
        for (int i3 = 0; i3 < size; i3++) {
            this.operations.get(i3).applyToPath(matrix, path);
        }
    }

    public boolean containsIncompatibleShadowOp() {
        return this.containsIncompatibleShadowOp;
    }

    @NonNull
    public ShadowCompatOperation createShadowCompatOperation(Matrix matrix) {
        addConnectingShadowIfNecessary(getEndShadowAngle());
        final Matrix matrix2 = new Matrix(matrix);
        final ArrayList arrayList = new ArrayList(this.shadowCompatOperations);
        return new ShadowCompatOperation() {
            public void draw(Matrix matrix, ShadowRenderer shadowRenderer, int i3, Canvas canvas) {
                for (ShadowCompatOperation draw : arrayList) {
                    draw.draw(matrix2, shadowRenderer, i3, canvas);
                }
            }
        };
    }

    @RequiresApi(21)
    public void cubicToPoint(float f2, float f3, float f4, float f5, float f6, float f7) {
        this.operations.add(new PathCubicOperation(f2, f3, f4, f5, f6, f7));
        this.containsIncompatibleShadowOp = true;
        setEndX(f6);
        setEndY(f7);
    }

    public float getEndX() {
        return this.endX;
    }

    public float getEndY() {
        return this.endY;
    }

    public float getStartX() {
        return this.startX;
    }

    public float getStartY() {
        return this.startY;
    }

    public void lineTo(float f2, float f3) {
        PathLineOperation pathLineOperation = new PathLineOperation();
        float unused = pathLineOperation.f6682x = f2;
        float unused2 = pathLineOperation.f6683y = f3;
        this.operations.add(pathLineOperation);
        LineShadowOperation lineShadowOperation = new LineShadowOperation(pathLineOperation, getEndX(), getEndY());
        addShadowCompatOperation(lineShadowOperation, lineShadowOperation.getAngle() + 270.0f, lineShadowOperation.getAngle() + 270.0f);
        setEndX(f2);
        setEndY(f3);
    }

    @RequiresApi(21)
    public void quadToPoint(float f2, float f3, float f4, float f5) {
        PathQuadOperation pathQuadOperation = new PathQuadOperation();
        pathQuadOperation.setControlX(f2);
        pathQuadOperation.setControlY(f3);
        pathQuadOperation.setEndX(f4);
        pathQuadOperation.setEndY(f5);
        this.operations.add(pathQuadOperation);
        this.containsIncompatibleShadowOp = true;
        setEndX(f4);
        setEndY(f5);
    }

    public void reset(float f2, float f3) {
        reset(f2, f3, 270.0f, 0.0f);
    }

    public void reset(float f2, float f3, float f4, float f5) {
        setStartX(f2);
        setStartY(f3);
        setEndX(f2);
        setEndY(f3);
        setCurrentShadowAngle(f4);
        setEndShadowAngle((f4 + f5) % 360.0f);
        this.operations.clear();
        this.shadowCompatOperations.clear();
        this.containsIncompatibleShadowOp = false;
    }

    public ShapePath(float f2, float f3) {
        reset(f2, f3);
    }

    public void lineTo(float f2, float f3, float f4, float f5) {
        if ((Math.abs(f2 - getEndX()) >= 0.001f || Math.abs(f3 - getEndY()) >= 0.001f) && (Math.abs(f2 - f4) >= 0.001f || Math.abs(f3 - f5) >= 0.001f)) {
            PathLineOperation pathLineOperation = new PathLineOperation();
            float unused = pathLineOperation.f6682x = f2;
            float unused2 = pathLineOperation.f6683y = f3;
            this.operations.add(pathLineOperation);
            PathLineOperation pathLineOperation2 = new PathLineOperation();
            float unused3 = pathLineOperation2.f6682x = f4;
            float unused4 = pathLineOperation2.f6683y = f5;
            this.operations.add(pathLineOperation2);
            InnerCornerShadowOperation innerCornerShadowOperation = new InnerCornerShadowOperation(pathLineOperation, pathLineOperation2, getEndX(), getEndY());
            if (innerCornerShadowOperation.getSweepAngle() > 0.0f) {
                lineTo(f2, f3);
                lineTo(f4, f5);
                return;
            }
            addShadowCompatOperation(innerCornerShadowOperation, innerCornerShadowOperation.getStartAngle() + 270.0f, innerCornerShadowOperation.getEndAngle() + 270.0f);
            setEndX(f4);
            setEndY(f5);
            return;
        }
        lineTo(f4, f5);
    }
}
