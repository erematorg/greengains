package com.google.android.material.shape;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.UiThread;

public class ShapeAppearancePathProvider {
    private final Path boundsPath = new Path();
    private final Path cornerPath = new Path();
    private final ShapePath[] cornerPaths = new ShapePath[4];
    private final Matrix[] cornerTransforms = new Matrix[4];
    private boolean edgeIntersectionCheckEnabled = true;
    private final Path edgePath = new Path();
    private final Matrix[] edgeTransforms = new Matrix[4];
    private final Path overlappedEdgePath = new Path();
    private final PointF pointF = new PointF();
    private final float[] scratch = new float[2];
    private final float[] scratch2 = new float[2];
    private final ShapePath shapePath = new ShapePath();

    public static class Lazy {
        static final ShapeAppearancePathProvider INSTANCE = new ShapeAppearancePathProvider();

        private Lazy() {
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public interface PathListener {
        void onCornerPathCreated(ShapePath shapePath, Matrix matrix, int i3);

        void onEdgePathCreated(ShapePath shapePath, Matrix matrix, int i3);
    }

    public static final class ShapeAppearancePathSpec {
        @NonNull
        public final RectF bounds;
        public final float interpolation;
        @NonNull
        public final Path path;
        @Nullable
        public final PathListener pathListener;
        @NonNull
        public final ShapeAppearanceModel shapeAppearanceModel;

        public ShapeAppearancePathSpec(@NonNull ShapeAppearanceModel shapeAppearanceModel2, float f2, RectF rectF, @Nullable PathListener pathListener2, Path path2) {
            this.pathListener = pathListener2;
            this.shapeAppearanceModel = shapeAppearanceModel2;
            this.interpolation = f2;
            this.bounds = rectF;
            this.path = path2;
        }
    }

    public ShapeAppearancePathProvider() {
        for (int i3 = 0; i3 < 4; i3++) {
            this.cornerPaths[i3] = new ShapePath();
            this.cornerTransforms[i3] = new Matrix();
            this.edgeTransforms[i3] = new Matrix();
        }
    }

    private float angleOfEdge(int i3) {
        return (float) (((i3 + 1) % 4) * 90);
    }

    private void appendCornerPath(@NonNull ShapeAppearancePathSpec shapeAppearancePathSpec, int i3) {
        this.scratch[0] = this.cornerPaths[i3].getStartX();
        this.scratch[1] = this.cornerPaths[i3].getStartY();
        this.cornerTransforms[i3].mapPoints(this.scratch);
        if (i3 == 0) {
            Path path = shapeAppearancePathSpec.path;
            float[] fArr = this.scratch;
            path.moveTo(fArr[0], fArr[1]);
        } else {
            Path path2 = shapeAppearancePathSpec.path;
            float[] fArr2 = this.scratch;
            path2.lineTo(fArr2[0], fArr2[1]);
        }
        this.cornerPaths[i3].applyToPath(this.cornerTransforms[i3], shapeAppearancePathSpec.path);
        PathListener pathListener = shapeAppearancePathSpec.pathListener;
        if (pathListener != null) {
            pathListener.onCornerPathCreated(this.cornerPaths[i3], this.cornerTransforms[i3], i3);
        }
    }

    private void appendEdgePath(@NonNull ShapeAppearancePathSpec shapeAppearancePathSpec, int i3) {
        int i4 = (i3 + 1) % 4;
        this.scratch[0] = this.cornerPaths[i3].getEndX();
        this.scratch[1] = this.cornerPaths[i3].getEndY();
        this.cornerTransforms[i3].mapPoints(this.scratch);
        this.scratch2[0] = this.cornerPaths[i4].getStartX();
        this.scratch2[1] = this.cornerPaths[i4].getStartY();
        this.cornerTransforms[i4].mapPoints(this.scratch2);
        float[] fArr = this.scratch;
        float f2 = fArr[0];
        float[] fArr2 = this.scratch2;
        float max = Math.max(((float) Math.hypot((double) (f2 - fArr2[0]), (double) (fArr[1] - fArr2[1]))) - 0.001f, 0.0f);
        float edgeCenterForIndex = getEdgeCenterForIndex(shapeAppearancePathSpec.bounds, i3);
        this.shapePath.reset(0.0f, 0.0f);
        EdgeTreatment edgeTreatmentForIndex = getEdgeTreatmentForIndex(i3, shapeAppearancePathSpec.shapeAppearanceModel);
        edgeTreatmentForIndex.getEdgePath(max, edgeCenterForIndex, shapeAppearancePathSpec.interpolation, this.shapePath);
        this.edgePath.reset();
        this.shapePath.applyToPath(this.edgeTransforms[i3], this.edgePath);
        if (!this.edgeIntersectionCheckEnabled || (!edgeTreatmentForIndex.forceIntersection() && !pathOverlapsCorner(this.edgePath, i3) && !pathOverlapsCorner(this.edgePath, i4))) {
            this.shapePath.applyToPath(this.edgeTransforms[i3], shapeAppearancePathSpec.path);
        } else {
            Path path = this.edgePath;
            path.op(path, this.boundsPath, Path.Op.DIFFERENCE);
            this.scratch[0] = this.shapePath.getStartX();
            this.scratch[1] = this.shapePath.getStartY();
            this.edgeTransforms[i3].mapPoints(this.scratch);
            Path path2 = this.overlappedEdgePath;
            float[] fArr3 = this.scratch;
            path2.moveTo(fArr3[0], fArr3[1]);
            this.shapePath.applyToPath(this.edgeTransforms[i3], this.overlappedEdgePath);
        }
        PathListener pathListener = shapeAppearancePathSpec.pathListener;
        if (pathListener != null) {
            pathListener.onEdgePathCreated(this.shapePath, this.edgeTransforms[i3], i3);
        }
    }

    private void getCoordinatesOfCorner(int i3, @NonNull RectF rectF, @NonNull PointF pointF2) {
        if (i3 == 1) {
            pointF2.set(rectF.right, rectF.bottom);
        } else if (i3 == 2) {
            pointF2.set(rectF.left, rectF.bottom);
        } else if (i3 != 3) {
            pointF2.set(rectF.right, rectF.top);
        } else {
            pointF2.set(rectF.left, rectF.top);
        }
    }

    private CornerSize getCornerSizeForIndex(int i3, @NonNull ShapeAppearanceModel shapeAppearanceModel) {
        return i3 != 1 ? i3 != 2 ? i3 != 3 ? shapeAppearanceModel.getTopRightCornerSize() : shapeAppearanceModel.getTopLeftCornerSize() : shapeAppearanceModel.getBottomLeftCornerSize() : shapeAppearanceModel.getBottomRightCornerSize();
    }

    private CornerTreatment getCornerTreatmentForIndex(int i3, @NonNull ShapeAppearanceModel shapeAppearanceModel) {
        return i3 != 1 ? i3 != 2 ? i3 != 3 ? shapeAppearanceModel.getTopRightCorner() : shapeAppearanceModel.getTopLeftCorner() : shapeAppearanceModel.getBottomLeftCorner() : shapeAppearanceModel.getBottomRightCorner();
    }

    private float getEdgeCenterForIndex(@NonNull RectF rectF, int i3) {
        float[] fArr = this.scratch;
        ShapePath shapePath2 = this.cornerPaths[i3];
        fArr[0] = shapePath2.endX;
        fArr[1] = shapePath2.endY;
        this.cornerTransforms[i3].mapPoints(fArr);
        return (i3 == 1 || i3 == 3) ? Math.abs(rectF.centerX() - this.scratch[0]) : Math.abs(rectF.centerY() - this.scratch[1]);
    }

    private EdgeTreatment getEdgeTreatmentForIndex(int i3, @NonNull ShapeAppearanceModel shapeAppearanceModel) {
        return i3 != 1 ? i3 != 2 ? i3 != 3 ? shapeAppearanceModel.getRightEdge() : shapeAppearanceModel.getTopEdge() : shapeAppearanceModel.getLeftEdge() : shapeAppearanceModel.getBottomEdge();
    }

    @UiThread
    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static ShapeAppearancePathProvider getInstance() {
        return Lazy.INSTANCE;
    }

    @RequiresApi(19)
    private boolean pathOverlapsCorner(Path path, int i3) {
        this.cornerPath.reset();
        this.cornerPaths[i3].applyToPath(this.cornerTransforms[i3], this.cornerPath);
        RectF rectF = new RectF();
        path.computeBounds(rectF, true);
        this.cornerPath.computeBounds(rectF, true);
        path.op(this.cornerPath, Path.Op.INTERSECT);
        path.computeBounds(rectF, true);
        if (rectF.isEmpty()) {
            return rectF.width() > 1.0f && rectF.height() > 1.0f;
        }
        return true;
    }

    private void setCornerPathAndTransform(@NonNull ShapeAppearancePathSpec shapeAppearancePathSpec, int i3) {
        getCornerTreatmentForIndex(i3, shapeAppearancePathSpec.shapeAppearanceModel).getCornerPath(this.cornerPaths[i3], 90.0f, shapeAppearancePathSpec.interpolation, shapeAppearancePathSpec.bounds, getCornerSizeForIndex(i3, shapeAppearancePathSpec.shapeAppearanceModel));
        float angleOfEdge = angleOfEdge(i3);
        this.cornerTransforms[i3].reset();
        getCoordinatesOfCorner(i3, shapeAppearancePathSpec.bounds, this.pointF);
        Matrix matrix = this.cornerTransforms[i3];
        PointF pointF2 = this.pointF;
        matrix.setTranslate(pointF2.x, pointF2.y);
        this.cornerTransforms[i3].preRotate(angleOfEdge);
    }

    private void setEdgePathAndTransform(int i3) {
        this.scratch[0] = this.cornerPaths[i3].getEndX();
        this.scratch[1] = this.cornerPaths[i3].getEndY();
        this.cornerTransforms[i3].mapPoints(this.scratch);
        float angleOfEdge = angleOfEdge(i3);
        this.edgeTransforms[i3].reset();
        Matrix matrix = this.edgeTransforms[i3];
        float[] fArr = this.scratch;
        matrix.setTranslate(fArr[0], fArr[1]);
        this.edgeTransforms[i3].preRotate(angleOfEdge);
    }

    public void calculatePath(ShapeAppearanceModel shapeAppearanceModel, float f2, RectF rectF, @NonNull Path path) {
        calculatePath(shapeAppearanceModel, f2, rectF, (PathListener) null, path);
    }

    public void setEdgeIntersectionCheckEnable(boolean z2) {
        this.edgeIntersectionCheckEnabled = z2;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void calculatePath(ShapeAppearanceModel shapeAppearanceModel, float f2, RectF rectF, PathListener pathListener, @NonNull Path path) {
        path.rewind();
        this.overlappedEdgePath.rewind();
        this.boundsPath.rewind();
        this.boundsPath.addRect(rectF, Path.Direction.CW);
        ShapeAppearancePathSpec shapeAppearancePathSpec = new ShapeAppearancePathSpec(shapeAppearanceModel, f2, rectF, pathListener, path);
        for (int i3 = 0; i3 < 4; i3++) {
            setCornerPathAndTransform(shapeAppearancePathSpec, i3);
            setEdgePathAndTransform(i3);
        }
        for (int i4 = 0; i4 < 4; i4++) {
            appendCornerPath(shapeAppearancePathSpec, i4);
            appendEdgePath(shapeAppearancePathSpec, i4);
        }
        path.close();
        this.overlappedEdgePath.close();
        if (!this.overlappedEdgePath.isEmpty()) {
            path.op(this.overlappedEdgePath, Path.Op.UNION);
        }
    }
}
