package com.airbnb.lottie.animation.keyframe;

import android.graphics.Path;
import androidx.annotation.Nullable;
import com.airbnb.lottie.animation.content.ShapeModifierContent;
import com.airbnb.lottie.model.content.ShapeData;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.Keyframe;
import java.util.List;

public class ShapeKeyframeAnimation extends BaseKeyframeAnimation<ShapeData, Path> {
    private List<ShapeModifierContent> shapeModifiers;
    private final Path tempPath = new Path();
    private final ShapeData tempShapeData = new ShapeData();

    public ShapeKeyframeAnimation(List<Keyframe<ShapeData>> list) {
        super(list);
    }

    public void setShapeModifiers(@Nullable List<ShapeModifierContent> list) {
        this.shapeModifiers = list;
    }

    public Path getValue(Keyframe<ShapeData> keyframe, float f2) {
        this.tempShapeData.interpolateBetween((ShapeData) keyframe.startValue, (ShapeData) keyframe.endValue, f2);
        ShapeData shapeData = this.tempShapeData;
        List<ShapeModifierContent> list = this.shapeModifiers;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                shapeData = this.shapeModifiers.get(size).modifyShape(shapeData);
            }
        }
        MiscUtils.getPathFromData(shapeData, this.tempPath);
        return this.tempPath;
    }
}
