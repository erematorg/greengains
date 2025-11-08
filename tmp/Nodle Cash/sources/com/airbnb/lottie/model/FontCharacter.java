package com.airbnb.lottie.model;

import androidx.annotation.RestrictTo;
import androidx.compose.animation.core.a;
import com.airbnb.lottie.model.content.ShapeGroup;
import java.util.List;

@RestrictTo({RestrictTo.Scope.LIBRARY})
public class FontCharacter {
    private final char character;
    private final String fontFamily;
    private final List<ShapeGroup> shapes;
    private final double size;
    private final String style;
    private final double width;

    public FontCharacter(List<ShapeGroup> list, char c3, double d2, double d3, String str, String str2) {
        this.shapes = list;
        this.character = c3;
        this.size = d2;
        this.width = d3;
        this.style = str;
        this.fontFamily = str2;
    }

    public static int hashFor(char c3, String str, String str2) {
        return str2.hashCode() + a.i(str, c3 * 31, 31);
    }

    public List<ShapeGroup> getShapes() {
        return this.shapes;
    }

    public double getWidth() {
        return this.width;
    }

    public int hashCode() {
        return hashFor(this.character, this.fontFamily, this.style);
    }
}
