package com.caverock.androidsvg;

import A.a;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Picture;
import android.graphics.RectF;
import android.util.Log;
import androidx.camera.camera2.internal.C0118y;
import androidx.core.view.ViewCompat;
import com.caverock.androidsvg.CSSParser;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;

public class SVG {
    private static final int DEFAULT_PICTURE_HEIGHT = 512;
    private static final int DEFAULT_PICTURE_WIDTH = 512;
    private static final long SPECIFIED_ALL = -1;
    static final long SPECIFIED_CLIP = 1048576;
    static final long SPECIFIED_CLIP_PATH = 268435456;
    static final long SPECIFIED_CLIP_RULE = 536870912;
    static final long SPECIFIED_COLOR = 4096;
    static final long SPECIFIED_DIRECTION = 68719476736L;
    static final long SPECIFIED_DISPLAY = 16777216;
    static final long SPECIFIED_FILL = 1;
    static final long SPECIFIED_FILL_OPACITY = 4;
    static final long SPECIFIED_FILL_RULE = 2;
    static final long SPECIFIED_FONT_FAMILY = 8192;
    static final long SPECIFIED_FONT_SIZE = 16384;
    static final long SPECIFIED_FONT_STYLE = 65536;
    static final long SPECIFIED_FONT_WEIGHT = 32768;
    static final long SPECIFIED_IMAGE_RENDERING = 137438953472L;
    static final long SPECIFIED_MARKER_END = 8388608;
    static final long SPECIFIED_MARKER_MID = 4194304;
    static final long SPECIFIED_MARKER_START = 2097152;
    static final long SPECIFIED_MASK = 1073741824;
    static final long SPECIFIED_OPACITY = 2048;
    static final long SPECIFIED_OVERFLOW = 524288;
    static final long SPECIFIED_SOLID_COLOR = 2147483648L;
    static final long SPECIFIED_SOLID_OPACITY = 4294967296L;
    static final long SPECIFIED_STOP_COLOR = 67108864;
    static final long SPECIFIED_STOP_OPACITY = 134217728;
    static final long SPECIFIED_STROKE = 8;
    static final long SPECIFIED_STROKE_DASHARRAY = 512;
    static final long SPECIFIED_STROKE_DASHOFFSET = 1024;
    static final long SPECIFIED_STROKE_LINECAP = 64;
    static final long SPECIFIED_STROKE_LINEJOIN = 128;
    static final long SPECIFIED_STROKE_MITERLIMIT = 256;
    static final long SPECIFIED_STROKE_OPACITY = 16;
    static final long SPECIFIED_STROKE_WIDTH = 32;
    static final long SPECIFIED_TEXT_ANCHOR = 262144;
    static final long SPECIFIED_TEXT_DECORATION = 131072;
    static final long SPECIFIED_VECTOR_EFFECT = 34359738368L;
    static final long SPECIFIED_VIEWPORT_FILL = 8589934592L;
    static final long SPECIFIED_VIEWPORT_FILL_OPACITY = 17179869184L;
    static final long SPECIFIED_VISIBILITY = 33554432;
    private static final double SQRT2 = 1.414213562373095d;
    private static final String VERSION = "1.4";
    private static boolean enableInternalEntities = true;
    private static SVGExternalFileResolver externalFileResolver;
    private CSSParser.Ruleset cssRules = new CSSParser.Ruleset();
    private String desc = "";
    private Map<String, SvgElementBase> idToElementMap = new HashMap();
    private float renderDPI = 96.0f;
    private Svg rootElement = null;
    private String title = "";

    /* renamed from: com.caverock.androidsvg.SVG$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$caverock$androidsvg$SVG$Unit;

        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|(3:17|18|20)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.caverock.androidsvg.SVG$Unit[] r0 = com.caverock.androidsvg.SVG.Unit.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$caverock$androidsvg$SVG$Unit = r0
                com.caverock.androidsvg.SVG$Unit r1 = com.caverock.androidsvg.SVG.Unit.px     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$caverock$androidsvg$SVG$Unit     // Catch:{ NoSuchFieldError -> 0x001d }
                com.caverock.androidsvg.SVG$Unit r1 = com.caverock.androidsvg.SVG.Unit.em     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$caverock$androidsvg$SVG$Unit     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.caverock.androidsvg.SVG$Unit r1 = com.caverock.androidsvg.SVG.Unit.ex     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$caverock$androidsvg$SVG$Unit     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.caverock.androidsvg.SVG$Unit r1 = com.caverock.androidsvg.SVG.Unit.in     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$caverock$androidsvg$SVG$Unit     // Catch:{ NoSuchFieldError -> 0x003e }
                com.caverock.androidsvg.SVG$Unit r1 = com.caverock.androidsvg.SVG.Unit.cm     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$caverock$androidsvg$SVG$Unit     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.caverock.androidsvg.SVG$Unit r1 = com.caverock.androidsvg.SVG.Unit.mm     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$caverock$androidsvg$SVG$Unit     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.caverock.androidsvg.SVG$Unit r1 = com.caverock.androidsvg.SVG.Unit.pt     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$com$caverock$androidsvg$SVG$Unit     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.caverock.androidsvg.SVG$Unit r1 = com.caverock.androidsvg.SVG.Unit.pc     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$com$caverock$androidsvg$SVG$Unit     // Catch:{ NoSuchFieldError -> 0x006c }
                com.caverock.androidsvg.SVG$Unit r1 = com.caverock.androidsvg.SVG.Unit.percent     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.caverock.androidsvg.SVG.AnonymousClass1.<clinit>():void");
        }
    }

    public static class CSSClipRect {
        Length bottom;
        Length left;
        Length right;
        Length top;

        public CSSClipRect(Length length, Length length2, Length length3, Length length4) {
            this.top = length;
            this.right = length2;
            this.bottom = length3;
            this.left = length4;
        }
    }

    public static class Circle extends GraphicsElement {
        Length cx;
        Length cy;

        /* renamed from: r  reason: collision with root package name */
        Length f6520r;

        public String getNodeName() {
            return TtmlNode.TEXT_EMPHASIS_MARK_CIRCLE;
        }
    }

    public static class ClipPath extends Group implements NotDirectlyRendered {
        Boolean clipPathUnitsAreUser;

        public String getNodeName() {
            return "clipPath";
        }
    }

    public static class Colour extends SvgPaint {
        static final Colour BLACK = new Colour(ViewCompat.MEASURED_STATE_MASK);
        static final Colour TRANSPARENT = new Colour(0);
        int colour;

        public Colour(int i3) {
            this.colour = i3;
        }

        public String toString() {
            return String.format("#%08x", new Object[]{Integer.valueOf(this.colour)});
        }
    }

    public static class CurrentColor extends SvgPaint {
        private static CurrentColor instance = new CurrentColor();

        private CurrentColor() {
        }

        public static CurrentColor getInstance() {
            return instance;
        }
    }

    public static class Defs extends Group implements NotDirectlyRendered {
        public String getNodeName() {
            return "defs";
        }
    }

    public static class Ellipse extends GraphicsElement {
        Length cx;
        Length cy;
        Length rx;
        Length ry;

        public String getNodeName() {
            return "ellipse";
        }
    }

    public static abstract class GradientElement extends SvgElementBase implements SvgContainer {
        List<SvgObject> children = new ArrayList();
        Matrix gradientTransform;
        Boolean gradientUnitsAreUser;
        String href;
        GradientSpread spreadMethod;

        public void addChild(SvgObject svgObject) throws SVGParseException {
            if (svgObject instanceof Stop) {
                this.children.add(svgObject);
                return;
            }
            throw new SVGParseException("Gradient elements cannot contain " + svgObject + " elements.");
        }

        public List<SvgObject> getChildren() {
            return this.children;
        }
    }

    public enum GradientSpread {
        pad,
        reflect,
        repeat
    }

    public static abstract class GraphicsElement extends SvgConditionalElement implements HasTransform {
        Matrix transform;

        public void setTransform(Matrix matrix) {
            this.transform = matrix;
        }
    }

    public static class Group extends SvgConditionalContainer implements HasTransform {
        Matrix transform;

        public String getNodeName() {
            return "group";
        }

        public void setTransform(Matrix matrix) {
            this.transform = matrix;
        }
    }

    public interface HasTransform {
        void setTransform(Matrix matrix);
    }

    public static class Image extends SvgPreserveAspectRatioContainer implements HasTransform {
        Length height;
        String href;
        Matrix transform;
        Length width;

        /* renamed from: x  reason: collision with root package name */
        Length f6521x;

        /* renamed from: y  reason: collision with root package name */
        Length f6522y;

        public String getNodeName() {
            return "image";
        }

        public void setTransform(Matrix matrix) {
            this.transform = matrix;
        }
    }

    public static class Length implements Cloneable {
        Unit unit;
        float value;

        public Length(float f2, Unit unit2) {
            this.value = f2;
            this.unit = unit2;
        }

        public float floatValue() {
            return this.value;
        }

        public float floatValueX(SVGAndroidRenderer sVGAndroidRenderer) {
            switch (AnonymousClass1.$SwitchMap$com$caverock$androidsvg$SVG$Unit[this.unit.ordinal()]) {
                case 1:
                    return this.value;
                case 2:
                    return this.value * sVGAndroidRenderer.getCurrentFontSize();
                case 3:
                    return this.value * sVGAndroidRenderer.getCurrentFontXHeight();
                case 4:
                    return this.value * sVGAndroidRenderer.getDPI();
                case 5:
                    return (this.value * sVGAndroidRenderer.getDPI()) / 2.54f;
                case 6:
                    return (this.value * sVGAndroidRenderer.getDPI()) / 25.4f;
                case 7:
                    return (this.value * sVGAndroidRenderer.getDPI()) / 72.0f;
                case 8:
                    return (this.value * sVGAndroidRenderer.getDPI()) / 6.0f;
                case 9:
                    Box currentViewPortInUserUnits = sVGAndroidRenderer.getCurrentViewPortInUserUnits();
                    return currentViewPortInUserUnits == null ? this.value : (this.value * currentViewPortInUserUnits.width) / 100.0f;
                default:
                    return this.value;
            }
        }

        public float floatValueY(SVGAndroidRenderer sVGAndroidRenderer) {
            if (this.unit != Unit.percent) {
                return floatValueX(sVGAndroidRenderer);
            }
            Box currentViewPortInUserUnits = sVGAndroidRenderer.getCurrentViewPortInUserUnits();
            return currentViewPortInUserUnits == null ? this.value : (this.value * currentViewPortInUserUnits.height) / 100.0f;
        }

        public boolean isNegative() {
            return this.value < 0.0f;
        }

        public boolean isZero() {
            return this.value == 0.0f;
        }

        public String toString() {
            return String.valueOf(this.value) + this.unit;
        }

        public float floatValue(SVGAndroidRenderer sVGAndroidRenderer) {
            float sqrt;
            if (this.unit != Unit.percent) {
                return floatValueX(sVGAndroidRenderer);
            }
            Box currentViewPortInUserUnits = sVGAndroidRenderer.getCurrentViewPortInUserUnits();
            if (currentViewPortInUserUnits == null) {
                return this.value;
            }
            float f2 = currentViewPortInUserUnits.width;
            float f3 = currentViewPortInUserUnits.height;
            if (f2 == f3) {
                sqrt = this.value * f2;
            } else {
                float f4 = this.value;
                sqrt = f4 * ((float) (Math.sqrt((double) ((f3 * f3) + (f2 * f2))) / SVG.SQRT2));
            }
            return sqrt / 100.0f;
        }

        public Length(float f2) {
            this.value = f2;
            this.unit = Unit.px;
        }

        public float floatValue(SVGAndroidRenderer sVGAndroidRenderer, float f2) {
            if (this.unit == Unit.percent) {
                return (this.value * f2) / 100.0f;
            }
            return floatValueX(sVGAndroidRenderer);
        }

        public float floatValue(float f2) {
            int i3 = AnonymousClass1.$SwitchMap$com$caverock$androidsvg$SVG$Unit[this.unit.ordinal()];
            if (i3 == 1) {
                return this.value;
            }
            switch (i3) {
                case 4:
                    return this.value * f2;
                case 5:
                    return (this.value * f2) / 2.54f;
                case 6:
                    return (this.value * f2) / 25.4f;
                case 7:
                    return (this.value * f2) / 72.0f;
                case 8:
                    return (this.value * f2) / 6.0f;
                default:
                    return this.value;
            }
        }
    }

    public static class Line extends GraphicsElement {
        Length x1;
        Length x2;
        Length y1;
        Length y2;

        public String getNodeName() {
            return "line";
        }
    }

    public static class Marker extends SvgViewBoxContainer implements NotDirectlyRendered {
        Length markerHeight;
        boolean markerUnitsAreUser;
        Length markerWidth;
        Float orient;
        Length refX;
        Length refY;

        public String getNodeName() {
            return "marker";
        }
    }

    public static class Mask extends SvgConditionalContainer implements NotDirectlyRendered {
        Length height;
        Boolean maskContentUnitsAreUser;
        Boolean maskUnitsAreUser;
        Length width;

        /* renamed from: x  reason: collision with root package name */
        Length f6523x;

        /* renamed from: y  reason: collision with root package name */
        Length f6524y;

        public String getNodeName() {
            return "mask";
        }
    }

    public interface NotDirectlyRendered {
    }

    public static class PaintReference extends SvgPaint {
        SvgPaint fallback;
        String href;

        public PaintReference(String str, SvgPaint svgPaint) {
            this.href = str;
            this.fallback = svgPaint;
        }

        public String toString() {
            return this.href + StringUtils.SPACE + this.fallback;
        }
    }

    public static class Path extends GraphicsElement {

        /* renamed from: d  reason: collision with root package name */
        PathDefinition f6525d;
        Float pathLength;

        public String getNodeName() {
            return "path";
        }
    }

    public static class PathDefinition implements PathInterface {
        private static final byte ARCTO = 4;
        private static final byte CLOSE = 8;
        private static final byte CUBICTO = 2;
        private static final byte LINETO = 1;
        private static final byte MOVETO = 0;
        private static final byte QUADTO = 3;
        private byte[] commands = new byte[8];
        private int commandsLength = 0;
        private float[] coords = new float[16];
        private int coordsLength = 0;

        private void addCommand(byte b3) {
            int i3 = this.commandsLength;
            byte[] bArr = this.commands;
            if (i3 == bArr.length) {
                byte[] bArr2 = new byte[(bArr.length * 2)];
                System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
                this.commands = bArr2;
            }
            byte[] bArr3 = this.commands;
            int i4 = this.commandsLength;
            this.commandsLength = i4 + 1;
            bArr3[i4] = b3;
        }

        private void coordsEnsure(int i3) {
            float[] fArr = this.coords;
            if (fArr.length < this.coordsLength + i3) {
                float[] fArr2 = new float[(fArr.length * 2)];
                System.arraycopy(fArr, 0, fArr2, 0, fArr.length);
                this.coords = fArr2;
            }
        }

        public void arcTo(float f2, float f3, float f4, boolean z2, boolean z3, float f5, float f6) {
            addCommand(((z2 ? (char) 2 : 0) | true) | z3 ? (byte) 1 : 0);
            coordsEnsure(5);
            float[] fArr = this.coords;
            int i3 = this.coordsLength;
            int i4 = i3 + 1;
            this.coordsLength = i4;
            fArr[i3] = f2;
            int i5 = i3 + 2;
            this.coordsLength = i5;
            fArr[i4] = f3;
            int i6 = i3 + 3;
            this.coordsLength = i6;
            fArr[i5] = f4;
            int i7 = i3 + 4;
            this.coordsLength = i7;
            fArr[i6] = f5;
            this.coordsLength = i3 + 5;
            fArr[i7] = f6;
        }

        public void close() {
            addCommand((byte) 8);
        }

        public void cubicTo(float f2, float f3, float f4, float f5, float f6, float f7) {
            addCommand((byte) 2);
            coordsEnsure(6);
            float[] fArr = this.coords;
            int i3 = this.coordsLength;
            int i4 = i3 + 1;
            this.coordsLength = i4;
            fArr[i3] = f2;
            int i5 = i3 + 2;
            this.coordsLength = i5;
            fArr[i4] = f3;
            int i6 = i3 + 3;
            this.coordsLength = i6;
            fArr[i5] = f4;
            int i7 = i3 + 4;
            this.coordsLength = i7;
            fArr[i6] = f5;
            int i8 = i3 + 5;
            this.coordsLength = i8;
            fArr[i7] = f6;
            this.coordsLength = i3 + 6;
            fArr[i8] = f7;
        }

        public void enumeratePath(PathInterface pathInterface) {
            int i3 = 0;
            for (int i4 = 0; i4 < this.commandsLength; i4++) {
                byte b3 = this.commands[i4];
                if (b3 == 0) {
                    float[] fArr = this.coords;
                    int i5 = i3 + 1;
                    float f2 = fArr[i3];
                    i3 += 2;
                    pathInterface.moveTo(f2, fArr[i5]);
                } else if (b3 == 1) {
                    float[] fArr2 = this.coords;
                    int i6 = i3 + 1;
                    float f3 = fArr2[i3];
                    i3 += 2;
                    pathInterface.lineTo(f3, fArr2[i6]);
                } else if (b3 == 2) {
                    float[] fArr3 = this.coords;
                    float f4 = fArr3[i3];
                    float f5 = fArr3[i3 + 1];
                    float f6 = fArr3[i3 + 2];
                    float f7 = fArr3[i3 + 3];
                    int i7 = i3 + 5;
                    i3 += 6;
                    pathInterface.cubicTo(f4, f5, f6, f7, fArr3[i3 + 4], fArr3[i7]);
                } else if (b3 == 3) {
                    float[] fArr4 = this.coords;
                    int i8 = i3 + 3;
                    i3 += 4;
                    pathInterface.quadTo(fArr4[i3], fArr4[i3 + 1], fArr4[i3 + 2], fArr4[i8]);
                } else if (b3 != 8) {
                    boolean z2 = (b3 & 2) != 0;
                    boolean z3 = (b3 & 1) != 0;
                    float[] fArr5 = this.coords;
                    float f8 = fArr5[i3];
                    float f9 = fArr5[i3 + 1];
                    float f10 = fArr5[i3 + 2];
                    int i9 = i3 + 4;
                    i3 += 5;
                    pathInterface.arcTo(f8, f9, f10, z2, z3, fArr5[i3 + 3], fArr5[i9]);
                } else {
                    pathInterface.close();
                }
            }
        }

        public boolean isEmpty() {
            return this.commandsLength == 0;
        }

        public void lineTo(float f2, float f3) {
            addCommand((byte) 1);
            coordsEnsure(2);
            float[] fArr = this.coords;
            int i3 = this.coordsLength;
            int i4 = i3 + 1;
            this.coordsLength = i4;
            fArr[i3] = f2;
            this.coordsLength = i3 + 2;
            fArr[i4] = f3;
        }

        public void moveTo(float f2, float f3) {
            addCommand((byte) 0);
            coordsEnsure(2);
            float[] fArr = this.coords;
            int i3 = this.coordsLength;
            int i4 = i3 + 1;
            this.coordsLength = i4;
            fArr[i3] = f2;
            this.coordsLength = i3 + 2;
            fArr[i4] = f3;
        }

        public void quadTo(float f2, float f3, float f4, float f5) {
            addCommand((byte) 3);
            coordsEnsure(4);
            float[] fArr = this.coords;
            int i3 = this.coordsLength;
            int i4 = i3 + 1;
            this.coordsLength = i4;
            fArr[i3] = f2;
            int i5 = i3 + 2;
            this.coordsLength = i5;
            fArr[i4] = f3;
            int i6 = i3 + 3;
            this.coordsLength = i6;
            fArr[i5] = f4;
            this.coordsLength = i3 + 4;
            fArr[i6] = f5;
        }
    }

    public interface PathInterface {
        void arcTo(float f2, float f3, float f4, boolean z2, boolean z3, float f5, float f6);

        void close();

        void cubicTo(float f2, float f3, float f4, float f5, float f6, float f7);

        void lineTo(float f2, float f3);

        void moveTo(float f2, float f3);

        void quadTo(float f2, float f3, float f4, float f5);
    }

    public static class Pattern extends SvgViewBoxContainer implements NotDirectlyRendered {
        Length height;
        String href;
        Boolean patternContentUnitsAreUser;
        Matrix patternTransform;
        Boolean patternUnitsAreUser;
        Length width;

        /* renamed from: x  reason: collision with root package name */
        Length f6526x;

        /* renamed from: y  reason: collision with root package name */
        Length f6527y;

        public String getNodeName() {
            return "pattern";
        }
    }

    public static class PolyLine extends GraphicsElement {
        float[] points;

        public String getNodeName() {
            return "polyline";
        }
    }

    public static class Polygon extends PolyLine {
        public String getNodeName() {
            return "polygon";
        }
    }

    public static class Rect extends GraphicsElement {
        Length height;
        Length rx;
        Length ry;
        Length width;

        /* renamed from: x  reason: collision with root package name */
        Length f6528x;

        /* renamed from: y  reason: collision with root package name */
        Length f6529y;

        public String getNodeName() {
            return "rect";
        }
    }

    public static class SolidColor extends SvgElementBase implements SvgContainer {
        public void addChild(SvgObject svgObject) {
        }

        public List<SvgObject> getChildren() {
            return Collections.emptyList();
        }

        public String getNodeName() {
            return "solidColor";
        }
    }

    public static class Stop extends SvgElementBase implements SvgContainer {
        Float offset;

        public void addChild(SvgObject svgObject) {
        }

        public List<SvgObject> getChildren() {
            return Collections.emptyList();
        }

        public String getNodeName() {
            return "stop";
        }
    }

    public static class Style implements Cloneable {
        static final int FONT_WEIGHT_BOLD = 700;
        static final int FONT_WEIGHT_BOLDER = 1;
        static final int FONT_WEIGHT_LIGHTER = -1;
        static final int FONT_WEIGHT_NORMAL = 400;
        CSSClipRect clip;
        String clipPath;
        FillRule clipRule;
        Colour color;
        TextDirection direction;
        Boolean display;
        SvgPaint fill;
        Float fillOpacity;
        FillRule fillRule;
        List<String> fontFamily;
        Length fontSize;
        FontStyle fontStyle;
        Integer fontWeight;
        RenderQuality imageRendering;
        String markerEnd;
        String markerMid;
        String markerStart;
        String mask;
        Float opacity;
        Boolean overflow;
        SvgPaint solidColor;
        Float solidOpacity;
        long specifiedFlags = 0;
        SvgPaint stopColor;
        Float stopOpacity;
        SvgPaint stroke;
        Length[] strokeDashArray;
        Length strokeDashOffset;
        LineCap strokeLineCap;
        LineJoin strokeLineJoin;
        Float strokeMiterLimit;
        Float strokeOpacity;
        Length strokeWidth;
        TextAnchor textAnchor;
        TextDecoration textDecoration;
        VectorEffect vectorEffect;
        SvgPaint viewportFill;
        Float viewportFillOpacity;
        Boolean visibility;

        public enum FillRule {
            NonZero,
            EvenOdd
        }

        public enum FontStyle {
            Normal,
            Italic,
            Oblique
        }

        public enum LineCap {
            Butt,
            Round,
            Square
        }

        public enum LineJoin {
            Miter,
            Round,
            Bevel
        }

        public enum RenderQuality {
            auto,
            optimizeQuality,
            optimizeSpeed
        }

        public enum TextAnchor {
            Start,
            Middle,
            End
        }

        public enum TextDecoration {
            None,
            Underline,
            Overline,
            LineThrough,
            Blink
        }

        public enum TextDirection {
            LTR,
            RTL
        }

        public enum VectorEffect {
            None,
            NonScalingStroke
        }

        public static Style getDefaultStyle() {
            Style style = new Style();
            style.specifiedFlags = -1;
            Colour colour = Colour.BLACK;
            style.fill = colour;
            FillRule fillRule2 = FillRule.NonZero;
            style.fillRule = fillRule2;
            Float valueOf = Float.valueOf(1.0f);
            style.fillOpacity = valueOf;
            style.stroke = null;
            style.strokeOpacity = valueOf;
            style.strokeWidth = new Length(1.0f);
            style.strokeLineCap = LineCap.Butt;
            style.strokeLineJoin = LineJoin.Miter;
            style.strokeMiterLimit = Float.valueOf(4.0f);
            style.strokeDashArray = null;
            style.strokeDashOffset = new Length(0.0f);
            style.opacity = valueOf;
            style.color = colour;
            style.fontFamily = null;
            style.fontSize = new Length(12.0f, Unit.pt);
            style.fontWeight = 400;
            style.fontStyle = FontStyle.Normal;
            style.textDecoration = TextDecoration.None;
            style.direction = TextDirection.LTR;
            style.textAnchor = TextAnchor.Start;
            Boolean bool = Boolean.TRUE;
            style.overflow = bool;
            style.clip = null;
            style.markerStart = null;
            style.markerMid = null;
            style.markerEnd = null;
            style.display = bool;
            style.visibility = bool;
            style.stopColor = colour;
            style.stopOpacity = valueOf;
            style.clipPath = null;
            style.clipRule = fillRule2;
            style.mask = null;
            style.solidColor = null;
            style.solidOpacity = valueOf;
            style.viewportFill = null;
            style.viewportFillOpacity = valueOf;
            style.vectorEffect = VectorEffect.None;
            style.imageRendering = RenderQuality.auto;
            return style;
        }

        public Object clone() throws CloneNotSupportedException {
            Style style = (Style) super.clone();
            Length[] lengthArr = this.strokeDashArray;
            if (lengthArr != null) {
                style.strokeDashArray = (Length[]) lengthArr.clone();
            }
            return style;
        }

        public void resetNonInheritingProperties(boolean z2) {
            Boolean bool = Boolean.TRUE;
            this.display = bool;
            if (!z2) {
                bool = Boolean.FALSE;
            }
            this.overflow = bool;
            this.clip = null;
            this.clipPath = null;
            this.opacity = Float.valueOf(1.0f);
            this.stopColor = Colour.BLACK;
            this.stopOpacity = Float.valueOf(1.0f);
            this.mask = null;
            this.solidColor = null;
            this.solidOpacity = Float.valueOf(1.0f);
            this.viewportFill = null;
            this.viewportFillOpacity = Float.valueOf(1.0f);
            this.vectorEffect = VectorEffect.None;
        }
    }

    public static class Svg extends SvgViewBoxContainer {
        Length height;
        public String version;
        Length width;

        /* renamed from: x  reason: collision with root package name */
        Length f6530x;

        /* renamed from: y  reason: collision with root package name */
        Length f6531y;

        public String getNodeName() {
            return "svg";
        }
    }

    public interface SvgConditional {
        String getRequiredExtensions();

        Set<String> getRequiredFeatures();

        Set<String> getRequiredFonts();

        Set<String> getRequiredFormats();

        Set<String> getSystemLanguage();

        void setRequiredExtensions(String str);

        void setRequiredFeatures(Set<String> set);

        void setRequiredFonts(Set<String> set);

        void setRequiredFormats(Set<String> set);

        void setSystemLanguage(Set<String> set);
    }

    public static abstract class SvgConditionalContainer extends SvgElement implements SvgContainer, SvgConditional {
        List<SvgObject> children = new ArrayList();
        String requiredExtensions = null;
        Set<String> requiredFeatures = null;
        Set<String> requiredFonts = null;
        Set<String> requiredFormats = null;
        Set<String> systemLanguage = null;

        public void addChild(SvgObject svgObject) throws SVGParseException {
            this.children.add(svgObject);
        }

        public List<SvgObject> getChildren() {
            return this.children;
        }

        public String getRequiredExtensions() {
            return this.requiredExtensions;
        }

        public Set<String> getRequiredFeatures() {
            return this.requiredFeatures;
        }

        public Set<String> getRequiredFonts() {
            return this.requiredFonts;
        }

        public Set<String> getRequiredFormats() {
            return this.requiredFormats;
        }

        public Set<String> getSystemLanguage() {
            return null;
        }

        public void setRequiredExtensions(String str) {
            this.requiredExtensions = str;
        }

        public void setRequiredFeatures(Set<String> set) {
            this.requiredFeatures = set;
        }

        public void setRequiredFonts(Set<String> set) {
            this.requiredFonts = set;
        }

        public void setRequiredFormats(Set<String> set) {
            this.requiredFormats = set;
        }

        public void setSystemLanguage(Set<String> set) {
            this.systemLanguage = set;
        }
    }

    public static abstract class SvgConditionalElement extends SvgElement implements SvgConditional {
        String requiredExtensions = null;
        Set<String> requiredFeatures = null;
        Set<String> requiredFonts = null;
        Set<String> requiredFormats = null;
        Set<String> systemLanguage = null;

        public String getRequiredExtensions() {
            return this.requiredExtensions;
        }

        public Set<String> getRequiredFeatures() {
            return this.requiredFeatures;
        }

        public Set<String> getRequiredFonts() {
            return this.requiredFonts;
        }

        public Set<String> getRequiredFormats() {
            return this.requiredFormats;
        }

        public Set<String> getSystemLanguage() {
            return this.systemLanguage;
        }

        public void setRequiredExtensions(String str) {
            this.requiredExtensions = str;
        }

        public void setRequiredFeatures(Set<String> set) {
            this.requiredFeatures = set;
        }

        public void setRequiredFonts(Set<String> set) {
            this.requiredFonts = set;
        }

        public void setRequiredFormats(Set<String> set) {
            this.requiredFormats = set;
        }

        public void setSystemLanguage(Set<String> set) {
            this.systemLanguage = set;
        }
    }

    public interface SvgContainer {
        void addChild(SvgObject svgObject) throws SVGParseException;

        List<SvgObject> getChildren();
    }

    public static abstract class SvgElement extends SvgElementBase {
        Box boundingBox = null;
    }

    public static abstract class SvgElementBase extends SvgObject {
        Style baseStyle = null;
        List<String> classNames = null;
        String id = null;
        Boolean spacePreserve = null;
        Style style = null;

        public String toString() {
            return getNodeName();
        }
    }

    public static class SvgLinearGradient extends GradientElement {
        Length x1;
        Length x2;
        Length y1;
        Length y2;

        public String getNodeName() {
            return "linearGradient";
        }
    }

    public static class SvgObject {
        SVG document;
        SvgContainer parent;

        public String getNodeName() {
            return "";
        }
    }

    public static abstract class SvgPaint implements Cloneable {
    }

    public static abstract class SvgPreserveAspectRatioContainer extends SvgConditionalContainer {
        PreserveAspectRatio preserveAspectRatio = null;
    }

    public static class SvgRadialGradient extends GradientElement {
        Length cx;
        Length cy;
        Length fx;
        Length fy;

        /* renamed from: r  reason: collision with root package name */
        Length f6532r;

        public String getNodeName() {
            return "radialGradient";
        }
    }

    public static abstract class SvgViewBoxContainer extends SvgPreserveAspectRatioContainer {
        Box viewBox;
    }

    public static class Switch extends Group {
        public String getNodeName() {
            return "switch";
        }
    }

    public static class Symbol extends SvgViewBoxContainer implements NotDirectlyRendered {
        public String getNodeName() {
            return "symbol";
        }
    }

    public static class TRef extends TextContainer implements TextChild {
        String href;
        private TextRoot textRoot;

        public String getNodeName() {
            return "tref";
        }

        public TextRoot getTextRoot() {
            return this.textRoot;
        }

        public void setTextRoot(TextRoot textRoot2) {
            this.textRoot = textRoot2;
        }
    }

    public static class TSpan extends TextPositionedContainer implements TextChild {
        private TextRoot textRoot;

        public String getNodeName() {
            return "tspan";
        }

        public TextRoot getTextRoot() {
            return this.textRoot;
        }

        public void setTextRoot(TextRoot textRoot2) {
            this.textRoot = textRoot2;
        }
    }

    public static class Text extends TextPositionedContainer implements TextRoot, HasTransform {
        Matrix transform;

        public String getNodeName() {
            return "text";
        }

        public void setTransform(Matrix matrix) {
            this.transform = matrix;
        }
    }

    public interface TextChild {
        TextRoot getTextRoot();

        void setTextRoot(TextRoot textRoot);
    }

    public static abstract class TextContainer extends SvgConditionalContainer {
        public void addChild(SvgObject svgObject) throws SVGParseException {
            if (svgObject instanceof TextChild) {
                this.children.add(svgObject);
                return;
            }
            throw new SVGParseException("Text content elements cannot contain " + svgObject + " elements.");
        }
    }

    public static class TextPath extends TextContainer implements TextChild {
        String href;
        Length startOffset;
        private TextRoot textRoot;

        public String getNodeName() {
            return "textPath";
        }

        public TextRoot getTextRoot() {
            return this.textRoot;
        }

        public void setTextRoot(TextRoot textRoot2) {
            this.textRoot = textRoot2;
        }
    }

    public static abstract class TextPositionedContainer extends TextContainer {
        List<Length> dx;
        List<Length> dy;

        /* renamed from: x  reason: collision with root package name */
        List<Length> f6533x;

        /* renamed from: y  reason: collision with root package name */
        List<Length> f6534y;
    }

    public interface TextRoot {
    }

    public static class TextSequence extends SvgObject implements TextChild {
        String text;
        private TextRoot textRoot;

        public TextSequence(String str) {
            this.text = str;
        }

        public TextRoot getTextRoot() {
            return this.textRoot;
        }

        public void setTextRoot(TextRoot textRoot2) {
            this.textRoot = textRoot2;
        }

        public String toString() {
            return a.n(new StringBuilder("TextChild: '"), this.text, "'");
        }
    }

    public enum Unit {
        px,
        em,
        ex,
        in,
        cm,
        mm,
        pt,
        pc,
        percent
    }

    public static class Use extends Group {
        Length height;
        String href;
        Length width;

        /* renamed from: x  reason: collision with root package name */
        Length f6535x;

        /* renamed from: y  reason: collision with root package name */
        Length f6536y;

        public String getNodeName() {
            return "use";
        }
    }

    public static class View extends SvgViewBoxContainer implements NotDirectlyRendered {
        static final String NODE_NAME = "view";

        public String getNodeName() {
            return NODE_NAME;
        }
    }

    private String cssQuotedString(String str) {
        if (str.startsWith("\"") && str.endsWith("\"")) {
            str = str.substring(1, str.length() - 1).replace("\\\"", "\"");
        } else if (str.startsWith("'") && str.endsWith("'")) {
            str = str.substring(1, str.length() - 1).replace("\\'", "'");
        }
        return str.replace("\\\n", "").replace("\\A", "\n");
    }

    public static void deregisterExternalFileResolver() {
        externalFileResolver = null;
    }

    private Box getDocumentDimensions(float f2) {
        Unit unit;
        Unit unit2;
        Unit unit3;
        Unit unit4;
        float f3;
        Unit unit5;
        Svg svg = this.rootElement;
        Length length = svg.width;
        Length length2 = svg.height;
        if (length == null || length.isZero() || (unit = length.unit) == (unit2 = Unit.percent) || unit == (unit3 = Unit.em) || unit == (unit4 = Unit.ex)) {
            return new Box(-1.0f, -1.0f, -1.0f, -1.0f);
        }
        float floatValue = length.floatValue(f2);
        if (length2 == null) {
            Box box = this.rootElement.viewBox;
            f3 = box != null ? (box.height * floatValue) / box.width : floatValue;
        } else if (length2.isZero() || (unit5 = length2.unit) == unit2 || unit5 == unit3 || unit5 == unit4) {
            return new Box(-1.0f, -1.0f, -1.0f, -1.0f);
        } else {
            f3 = length2.floatValue(f2);
        }
        return new Box(0.0f, 0.0f, floatValue, f3);
    }

    private List<SvgObject> getElementsByTagName(String str) {
        ArrayList arrayList = new ArrayList();
        getElementsByTagName(arrayList, this.rootElement, str);
        return arrayList;
    }

    public static SVGExternalFileResolver getFileResolver() {
        return externalFileResolver;
    }

    public static SVG getFromAsset(AssetManager assetManager, String str) throws SVGParseException, IOException {
        SVGParser sVGParser = new SVGParser();
        InputStream open = assetManager.open(str);
        try {
            return sVGParser.parse(open, enableInternalEntities);
        } finally {
            try {
                open.close();
            } catch (IOException unused) {
            }
        }
    }

    public static SVG getFromInputStream(InputStream inputStream) throws SVGParseException {
        return new SVGParser().parse(inputStream, enableInternalEntities);
    }

    public static SVG getFromResource(Context context, int i3) throws SVGParseException {
        return getFromResource(context.getResources(), i3);
    }

    public static SVG getFromString(String str) throws SVGParseException {
        return new SVGParser().parse(new ByteArrayInputStream(str.getBytes()), enableInternalEntities);
    }

    public static String getVersion() {
        return VERSION;
    }

    public static boolean isInternalEntitiesEnabled() {
        return enableInternalEntities;
    }

    public static void registerExternalFileResolver(SVGExternalFileResolver sVGExternalFileResolver) {
        externalFileResolver = sVGExternalFileResolver;
    }

    public static void setInternalEntitiesEnabled(boolean z2) {
        enableInternalEntities = z2;
    }

    public void addCSSRules(CSSParser.Ruleset ruleset) {
        this.cssRules.addAll(ruleset);
    }

    public void clearRenderCSSRules() {
        this.cssRules.removeFromSource(CSSParser.Source.RenderOptions);
    }

    public List<CSSParser.Rule> getCSSRules() {
        return this.cssRules.getRules();
    }

    public float getDocumentAspectRatio() {
        Unit unit;
        Svg svg = this.rootElement;
        if (svg != null) {
            Length length = svg.width;
            Length length2 = svg.height;
            if (length == null || length2 == null || length.unit == (unit = Unit.percent) || length2.unit == unit) {
                Box box = svg.viewBox;
                if (box != null) {
                    float f2 = box.width;
                    if (f2 != 0.0f) {
                        float f3 = box.height;
                        if (f3 != 0.0f) {
                            return f2 / f3;
                        }
                    }
                }
                return -1.0f;
            } else if (length.isZero() || length2.isZero()) {
                return -1.0f;
            } else {
                return length.floatValue(this.renderDPI) / length2.floatValue(this.renderDPI);
            }
        } else {
            throw new IllegalArgumentException("SVG document is empty");
        }
    }

    public String getDocumentDescription() {
        if (this.rootElement != null) {
            return this.desc;
        }
        throw new IllegalArgumentException("SVG document is empty");
    }

    public float getDocumentHeight() {
        if (this.rootElement != null) {
            return getDocumentDimensions(this.renderDPI).height;
        }
        throw new IllegalArgumentException("SVG document is empty");
    }

    public PreserveAspectRatio getDocumentPreserveAspectRatio() {
        Svg svg = this.rootElement;
        if (svg != null) {
            PreserveAspectRatio preserveAspectRatio = svg.preserveAspectRatio;
            if (preserveAspectRatio == null) {
                return null;
            }
            return preserveAspectRatio;
        }
        throw new IllegalArgumentException("SVG document is empty");
    }

    public String getDocumentSVGVersion() {
        Svg svg = this.rootElement;
        if (svg != null) {
            return svg.version;
        }
        throw new IllegalArgumentException("SVG document is empty");
    }

    public String getDocumentTitle() {
        if (this.rootElement != null) {
            return this.title;
        }
        throw new IllegalArgumentException("SVG document is empty");
    }

    public RectF getDocumentViewBox() {
        Svg svg = this.rootElement;
        if (svg != null) {
            Box box = svg.viewBox;
            if (box == null) {
                return null;
            }
            return box.toRectF();
        }
        throw new IllegalArgumentException("SVG document is empty");
    }

    public float getDocumentWidth() {
        if (this.rootElement != null) {
            return getDocumentDimensions(this.renderDPI).width;
        }
        throw new IllegalArgumentException("SVG document is empty");
    }

    public SvgElementBase getElementById(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        if (str.equals(this.rootElement.id)) {
            return this.rootElement;
        }
        if (this.idToElementMap.containsKey(str)) {
            return this.idToElementMap.get(str);
        }
        SvgElementBase elementById = getElementById(this.rootElement, str);
        this.idToElementMap.put(str, elementById);
        return elementById;
    }

    public float getRenderDPI() {
        return this.renderDPI;
    }

    public Svg getRootElement() {
        return this.rootElement;
    }

    public Set<String> getViewList() {
        if (this.rootElement != null) {
            List<SvgObject> elementsByTagName = getElementsByTagName("view");
            HashSet hashSet = new HashSet(elementsByTagName.size());
            Iterator<SvgObject> it = elementsByTagName.iterator();
            while (it.hasNext()) {
                String str = ((View) it.next()).id;
                if (str != null) {
                    hashSet.add(str);
                } else {
                    Log.w("AndroidSVG", "getViewList(): found a <view> without an id attribute");
                }
            }
            return hashSet;
        }
        throw new IllegalArgumentException("SVG document is empty");
    }

    public boolean hasCSSRules() {
        return !this.cssRules.isEmpty();
    }

    public void renderToCanvas(Canvas canvas) {
        renderToCanvas(canvas, (RenderOptions) null);
    }

    public Picture renderToPicture() {
        return renderToPicture((RenderOptions) null);
    }

    public void renderViewToCanvas(String str, Canvas canvas) {
        renderToCanvas(canvas, RenderOptions.create().view(str));
    }

    public Picture renderViewToPicture(String str, int i3, int i4) {
        RenderOptions renderOptions = new RenderOptions();
        renderOptions.view(str).viewPort(0.0f, 0.0f, (float) i3, (float) i4);
        Picture picture = new Picture();
        new SVGAndroidRenderer(picture.beginRecording(i3, i4), this.renderDPI).renderDocument(this, renderOptions);
        picture.endRecording();
        return picture;
    }

    public SvgObject resolveIRI(String str) {
        if (str == null) {
            return null;
        }
        String cssQuotedString = cssQuotedString(str);
        if (cssQuotedString.length() <= 1 || !cssQuotedString.startsWith("#")) {
            return null;
        }
        return getElementById(cssQuotedString.substring(1));
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public void setDocumentHeight(float f2) {
        Svg svg = this.rootElement;
        if (svg != null) {
            svg.height = new Length(f2);
            return;
        }
        throw new IllegalArgumentException("SVG document is empty");
    }

    public void setDocumentPreserveAspectRatio(PreserveAspectRatio preserveAspectRatio) {
        Svg svg = this.rootElement;
        if (svg != null) {
            svg.preserveAspectRatio = preserveAspectRatio;
            return;
        }
        throw new IllegalArgumentException("SVG document is empty");
    }

    public void setDocumentViewBox(float f2, float f3, float f4, float f5) {
        Svg svg = this.rootElement;
        if (svg != null) {
            svg.viewBox = new Box(f2, f3, f4, f5);
            return;
        }
        throw new IllegalArgumentException("SVG document is empty");
    }

    public void setDocumentWidth(float f2) {
        Svg svg = this.rootElement;
        if (svg != null) {
            svg.width = new Length(f2);
            return;
        }
        throw new IllegalArgumentException("SVG document is empty");
    }

    public void setRenderDPI(float f2) {
        this.renderDPI = f2;
    }

    public void setRootElement(Svg svg) {
        this.rootElement = svg;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public static SVG getFromResource(Resources resources, int i3) throws SVGParseException {
        SVGParser sVGParser = new SVGParser();
        InputStream openRawResource = resources.openRawResource(i3);
        try {
            return sVGParser.parse(openRawResource, enableInternalEntities);
        } finally {
            try {
                openRawResource.close();
            } catch (IOException unused) {
            }
        }
    }

    public void renderToCanvas(Canvas canvas, RectF rectF) {
        RenderOptions renderOptions = new RenderOptions();
        if (rectF != null) {
            renderOptions.viewPort(rectF.left, rectF.top, rectF.width(), rectF.height());
        } else {
            renderOptions.viewPort(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight());
        }
        new SVGAndroidRenderer(canvas, this.renderDPI).renderDocument(this, renderOptions);
    }

    public Picture renderToPicture(int i3, int i4) {
        return renderToPicture(i3, i4, (RenderOptions) null);
    }

    public void renderViewToCanvas(String str, Canvas canvas, RectF rectF) {
        RenderOptions view = RenderOptions.create().view(str);
        if (rectF != null) {
            view.viewPort(rectF.left, rectF.top, rectF.width(), rectF.height());
        }
        renderToCanvas(canvas, view);
    }

    private void getElementsByTagName(List<SvgObject> list, SvgObject svgObject, String str) {
        if (svgObject.getNodeName().equals(str)) {
            list.add(svgObject);
        }
        if (svgObject instanceof SvgContainer) {
            for (SvgObject elementsByTagName : ((SvgContainer) svgObject).getChildren()) {
                getElementsByTagName(list, elementsByTagName, str);
            }
        }
    }

    public Picture renderToPicture(RenderOptions renderOptions) {
        Unit unit;
        Length length;
        Box box = (renderOptions == null || !renderOptions.hasViewBox()) ? this.rootElement.viewBox : renderOptions.viewBox;
        if (renderOptions == null || !renderOptions.hasViewPort()) {
            Svg svg = this.rootElement;
            Length length2 = svg.width;
            if (length2 != null && length2.unit != (unit = Unit.percent) && (length = svg.height) != null && length.unit != unit) {
                return renderToPicture((int) Math.ceil((double) length2.floatValue(this.renderDPI)), (int) Math.ceil((double) this.rootElement.height.floatValue(this.renderDPI)), renderOptions);
            } else if (length2 == null || box == null) {
                Length length3 = svg.height;
                if (length3 == null || box == null) {
                    return renderToPicture(512, 512, renderOptions);
                }
                float floatValue = length3.floatValue(this.renderDPI);
                return renderToPicture((int) Math.ceil((double) ((box.width * floatValue) / box.height)), (int) Math.ceil((double) floatValue), renderOptions);
            } else {
                float floatValue2 = length2.floatValue(this.renderDPI);
                return renderToPicture((int) Math.ceil((double) floatValue2), (int) Math.ceil((double) ((box.height * floatValue2) / box.width)), renderOptions);
            }
        } else {
            return renderToPicture((int) Math.ceil((double) renderOptions.viewPort.maxX()), (int) Math.ceil((double) renderOptions.viewPort.maxY()), renderOptions);
        }
    }

    public void setDocumentHeight(String str) throws SVGParseException {
        Svg svg = this.rootElement;
        if (svg != null) {
            svg.height = SVGParser.parseLength(str);
            return;
        }
        throw new IllegalArgumentException("SVG document is empty");
    }

    public void setDocumentWidth(String str) throws SVGParseException {
        Svg svg = this.rootElement;
        if (svg != null) {
            svg.width = SVGParser.parseLength(str);
            return;
        }
        throw new IllegalArgumentException("SVG document is empty");
    }

    public static class Box {
        float height;
        float minX;
        float minY;
        float width;

        public Box(float f2, float f3, float f4, float f5) {
            this.minX = f2;
            this.minY = f3;
            this.width = f4;
            this.height = f5;
        }

        public static Box fromLimits(float f2, float f3, float f4, float f5) {
            return new Box(f2, f3, f4 - f2, f5 - f3);
        }

        public float maxX() {
            return this.minX + this.width;
        }

        public float maxY() {
            return this.minY + this.height;
        }

        public RectF toRectF() {
            return new RectF(this.minX, this.minY, maxX(), maxY());
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("[");
            sb.append(this.minX);
            sb.append(StringUtils.SPACE);
            sb.append(this.minY);
            sb.append(StringUtils.SPACE);
            sb.append(this.width);
            sb.append(StringUtils.SPACE);
            return C0118y.i(sb, "]", this.height);
        }

        public void union(Box box) {
            float f2 = box.minX;
            if (f2 < this.minX) {
                this.minX = f2;
            }
            float f3 = box.minY;
            if (f3 < this.minY) {
                this.minY = f3;
            }
            if (box.maxX() > maxX()) {
                this.width = box.maxX() - this.minX;
            }
            if (box.maxY() > maxY()) {
                this.height = box.maxY() - this.minY;
            }
        }

        public Box(Box box) {
            this.minX = box.minX;
            this.minY = box.minY;
            this.width = box.width;
            this.height = box.height;
        }
    }

    public void renderToCanvas(Canvas canvas, RenderOptions renderOptions) {
        if (renderOptions == null) {
            renderOptions = new RenderOptions();
        }
        if (!renderOptions.hasViewPort()) {
            renderOptions.viewPort(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight());
        }
        new SVGAndroidRenderer(canvas, this.renderDPI).renderDocument(this, renderOptions);
    }

    private SvgElementBase getElementById(SvgContainer svgContainer, String str) {
        SvgElementBase elementById;
        SvgElementBase svgElementBase = (SvgElementBase) svgContainer;
        if (str.equals(svgElementBase.id)) {
            return svgElementBase;
        }
        for (SvgObject next : svgContainer.getChildren()) {
            if (next instanceof SvgElementBase) {
                SvgElementBase svgElementBase2 = (SvgElementBase) next;
                if (str.equals(svgElementBase2.id)) {
                    return svgElementBase2;
                }
                if ((next instanceof SvgContainer) && (elementById = getElementById((SvgContainer) next, str)) != null) {
                    return elementById;
                }
            }
        }
        return null;
    }

    public Picture renderToPicture(int i3, int i4, RenderOptions renderOptions) {
        Picture picture = new Picture();
        Canvas beginRecording = picture.beginRecording(i3, i4);
        if (renderOptions == null || renderOptions.viewPort == null) {
            renderOptions = renderOptions == null ? new RenderOptions() : new RenderOptions(renderOptions);
            renderOptions.viewPort(0.0f, 0.0f, (float) i3, (float) i4);
        }
        new SVGAndroidRenderer(beginRecording, this.renderDPI).renderDocument(this, renderOptions);
        picture.endRecording();
        return picture;
    }
}
