package com.caverock.androidsvg;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Base64;
import android.util.Log;
import androidx.camera.camera2.internal.C0118y;
import androidx.core.view.ViewCompat;
import com.caverock.androidsvg.CSSParser;
import com.caverock.androidsvg.SVG;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.Stack;
import jnr.constants.platform.dragonflybsd.OpenFlags;
import org.apache.commons.lang3.StringUtils;

class SVGAndroidRenderer {
    private static final float BEZIER_ARC_FACTOR = 0.5522848f;
    private static final String DEFAULT_FONT_FAMILY = "serif";
    public static final float LUMINANCE_TO_ALPHA_BLUE = 0.0722f;
    public static final float LUMINANCE_TO_ALPHA_GREEN = 0.7151f;
    public static final float LUMINANCE_TO_ALPHA_RED = 0.2127f;
    private static final String TAG = "SVGAndroidRenderer";
    private static HashSet<String> supportedFeatures;
    /* access modifiers changed from: private */
    public Canvas canvas;
    private SVG document;
    private float dpi;
    private Stack<Matrix> matrixStack;
    private Stack<SVG.SvgContainer> parentStack;
    private CSSParser.RuleMatchContext ruleMatchContext = null;
    /* access modifiers changed from: private */
    public RendererState state;
    private Stack<RendererState> stateStack;

    /* renamed from: com.caverock.androidsvg.SVGAndroidRenderer$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$caverock$androidsvg$PreserveAspectRatio$Alignment;
        static final /* synthetic */ int[] $SwitchMap$com$caverock$androidsvg$SVG$Style$LineCap;
        static final /* synthetic */ int[] $SwitchMap$com$caverock$androidsvg$SVG$Style$LineJoin;

        /* JADX WARNING: Can't wrap try/catch for region: R(32:0|(2:1|2)|3|(2:5|6)|7|9|10|11|13|14|15|16|17|18|19|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|38) */
        /* JADX WARNING: Can't wrap try/catch for region: R(33:0|1|2|3|(2:5|6)|7|9|10|11|13|14|15|16|17|18|19|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|38) */
        /* JADX WARNING: Can't wrap try/catch for region: R(34:0|1|2|3|5|6|7|9|10|11|13|14|15|16|17|18|19|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|38) */
        /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0039 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0043 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x005e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0068 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0072 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x007d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0088 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x0093 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x009e */
        static {
            /*
                com.caverock.androidsvg.SVG$Style$LineJoin[] r0 = com.caverock.androidsvg.SVG.Style.LineJoin.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$caverock$androidsvg$SVG$Style$LineJoin = r0
                r1 = 1
                com.caverock.androidsvg.SVG$Style$LineJoin r2 = com.caverock.androidsvg.SVG.Style.LineJoin.Miter     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$caverock$androidsvg$SVG$Style$LineJoin     // Catch:{ NoSuchFieldError -> 0x001d }
                com.caverock.androidsvg.SVG$Style$LineJoin r3 = com.caverock.androidsvg.SVG.Style.LineJoin.Round     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$com$caverock$androidsvg$SVG$Style$LineJoin     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.caverock.androidsvg.SVG$Style$LineJoin r4 = com.caverock.androidsvg.SVG.Style.LineJoin.Bevel     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                com.caverock.androidsvg.SVG$Style$LineCap[] r3 = com.caverock.androidsvg.SVG.Style.LineCap.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$com$caverock$androidsvg$SVG$Style$LineCap = r3
                com.caverock.androidsvg.SVG$Style$LineCap r4 = com.caverock.androidsvg.SVG.Style.LineCap.Butt     // Catch:{ NoSuchFieldError -> 0x0039 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0039 }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0039 }
            L_0x0039:
                int[] r3 = $SwitchMap$com$caverock$androidsvg$SVG$Style$LineCap     // Catch:{ NoSuchFieldError -> 0x0043 }
                com.caverock.androidsvg.SVG$Style$LineCap r4 = com.caverock.androidsvg.SVG.Style.LineCap.Round     // Catch:{ NoSuchFieldError -> 0x0043 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0043 }
                r3[r4] = r0     // Catch:{ NoSuchFieldError -> 0x0043 }
            L_0x0043:
                int[] r3 = $SwitchMap$com$caverock$androidsvg$SVG$Style$LineCap     // Catch:{ NoSuchFieldError -> 0x004d }
                com.caverock.androidsvg.SVG$Style$LineCap r4 = com.caverock.androidsvg.SVG.Style.LineCap.Square     // Catch:{ NoSuchFieldError -> 0x004d }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x004d }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x004d }
            L_0x004d:
                com.caverock.androidsvg.PreserveAspectRatio$Alignment[] r3 = com.caverock.androidsvg.PreserveAspectRatio.Alignment.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$com$caverock$androidsvg$PreserveAspectRatio$Alignment = r3
                com.caverock.androidsvg.PreserveAspectRatio$Alignment r4 = com.caverock.androidsvg.PreserveAspectRatio.Alignment.xMidYMin     // Catch:{ NoSuchFieldError -> 0x005e }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x005e }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x005e }
            L_0x005e:
                int[] r1 = $SwitchMap$com$caverock$androidsvg$PreserveAspectRatio$Alignment     // Catch:{ NoSuchFieldError -> 0x0068 }
                com.caverock.androidsvg.PreserveAspectRatio$Alignment r3 = com.caverock.androidsvg.PreserveAspectRatio.Alignment.xMidYMid     // Catch:{ NoSuchFieldError -> 0x0068 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0068 }
                r1[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0068 }
            L_0x0068:
                int[] r0 = $SwitchMap$com$caverock$androidsvg$PreserveAspectRatio$Alignment     // Catch:{ NoSuchFieldError -> 0x0072 }
                com.caverock.androidsvg.PreserveAspectRatio$Alignment r1 = com.caverock.androidsvg.PreserveAspectRatio.Alignment.xMidYMax     // Catch:{ NoSuchFieldError -> 0x0072 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0072 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0072 }
            L_0x0072:
                int[] r0 = $SwitchMap$com$caverock$androidsvg$PreserveAspectRatio$Alignment     // Catch:{ NoSuchFieldError -> 0x007d }
                com.caverock.androidsvg.PreserveAspectRatio$Alignment r1 = com.caverock.androidsvg.PreserveAspectRatio.Alignment.xMaxYMin     // Catch:{ NoSuchFieldError -> 0x007d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x007d }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x007d }
            L_0x007d:
                int[] r0 = $SwitchMap$com$caverock$androidsvg$PreserveAspectRatio$Alignment     // Catch:{ NoSuchFieldError -> 0x0088 }
                com.caverock.androidsvg.PreserveAspectRatio$Alignment r1 = com.caverock.androidsvg.PreserveAspectRatio.Alignment.xMaxYMid     // Catch:{ NoSuchFieldError -> 0x0088 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0088 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0088 }
            L_0x0088:
                int[] r0 = $SwitchMap$com$caverock$androidsvg$PreserveAspectRatio$Alignment     // Catch:{ NoSuchFieldError -> 0x0093 }
                com.caverock.androidsvg.PreserveAspectRatio$Alignment r1 = com.caverock.androidsvg.PreserveAspectRatio.Alignment.xMaxYMax     // Catch:{ NoSuchFieldError -> 0x0093 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0093 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0093 }
            L_0x0093:
                int[] r0 = $SwitchMap$com$caverock$androidsvg$PreserveAspectRatio$Alignment     // Catch:{ NoSuchFieldError -> 0x009e }
                com.caverock.androidsvg.PreserveAspectRatio$Alignment r1 = com.caverock.androidsvg.PreserveAspectRatio.Alignment.xMinYMid     // Catch:{ NoSuchFieldError -> 0x009e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009e }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009e }
            L_0x009e:
                int[] r0 = $SwitchMap$com$caverock$androidsvg$PreserveAspectRatio$Alignment     // Catch:{ NoSuchFieldError -> 0x00aa }
                com.caverock.androidsvg.PreserveAspectRatio$Alignment r1 = com.caverock.androidsvg.PreserveAspectRatio.Alignment.xMinYMax     // Catch:{ NoSuchFieldError -> 0x00aa }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00aa }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00aa }
            L_0x00aa:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.caverock.androidsvg.SVGAndroidRenderer.AnonymousClass1.<clinit>():void");
        }
    }

    public class MarkerPositionCalculator implements SVG.PathInterface {
        private boolean closepathReAdjustPending;
        private MarkerVector lastPos = null;
        private List<MarkerVector> markers = new ArrayList();
        private boolean normalCubic = true;
        private boolean startArc = false;
        private float startX;
        private float startY;
        private int subpathStartIndex = -1;

        public MarkerPositionCalculator(SVG.PathDefinition pathDefinition) {
            if (pathDefinition != null) {
                pathDefinition.enumeratePath(this);
                if (this.closepathReAdjustPending) {
                    this.lastPos.add(this.markers.get(this.subpathStartIndex));
                    this.markers.set(this.subpathStartIndex, this.lastPos);
                    this.closepathReAdjustPending = false;
                }
                MarkerVector markerVector = this.lastPos;
                if (markerVector != null) {
                    this.markers.add(markerVector);
                }
            }
        }

        public void arcTo(float f2, float f3, float f4, boolean z2, boolean z3, float f5, float f6) {
            this.startArc = true;
            this.normalCubic = false;
            MarkerVector markerVector = this.lastPos;
            SVGAndroidRenderer.arcTo(markerVector.f6537x, markerVector.f6538y, f2, f3, f4, z2, z3, f5, f6, this);
            this.normalCubic = true;
            this.closepathReAdjustPending = false;
        }

        public void close() {
            this.markers.add(this.lastPos);
            lineTo(this.startX, this.startY);
            this.closepathReAdjustPending = true;
        }

        public void cubicTo(float f2, float f3, float f4, float f5, float f6, float f7) {
            if (this.normalCubic || this.startArc) {
                this.lastPos.add(f2, f3);
                this.markers.add(this.lastPos);
                this.startArc = false;
            }
            this.lastPos = new MarkerVector(f6, f7, f6 - f4, f7 - f5);
            this.closepathReAdjustPending = false;
        }

        public List<MarkerVector> getMarkers() {
            return this.markers;
        }

        public void lineTo(float f2, float f3) {
            this.lastPos.add(f2, f3);
            this.markers.add(this.lastPos);
            SVGAndroidRenderer sVGAndroidRenderer = SVGAndroidRenderer.this;
            MarkerVector markerVector = this.lastPos;
            this.lastPos = new MarkerVector(f2, f3, f2 - markerVector.f6537x, f3 - markerVector.f6538y);
            this.closepathReAdjustPending = false;
        }

        public void moveTo(float f2, float f3) {
            if (this.closepathReAdjustPending) {
                this.lastPos.add(this.markers.get(this.subpathStartIndex));
                this.markers.set(this.subpathStartIndex, this.lastPos);
                this.closepathReAdjustPending = false;
            }
            MarkerVector markerVector = this.lastPos;
            if (markerVector != null) {
                this.markers.add(markerVector);
            }
            this.startX = f2;
            this.startY = f3;
            this.lastPos = new MarkerVector(f2, f3, 0.0f, 0.0f);
            this.subpathStartIndex = this.markers.size();
        }

        public void quadTo(float f2, float f3, float f4, float f5) {
            this.lastPos.add(f2, f3);
            this.markers.add(this.lastPos);
            this.lastPos = new MarkerVector(f4, f5, f4 - f2, f5 - f3);
            this.closepathReAdjustPending = false;
        }
    }

    public class PathConverter implements SVG.PathInterface {
        float lastX;
        float lastY;
        Path path = new Path();

        public PathConverter(SVG.PathDefinition pathDefinition) {
            if (pathDefinition != null) {
                pathDefinition.enumeratePath(this);
            }
        }

        public void arcTo(float f2, float f3, float f4, boolean z2, boolean z3, float f5, float f6) {
            SVGAndroidRenderer.arcTo(this.lastX, this.lastY, f2, f3, f4, z2, z3, f5, f6, this);
            this.lastX = f5;
            this.lastY = f6;
        }

        public void close() {
            this.path.close();
        }

        public void cubicTo(float f2, float f3, float f4, float f5, float f6, float f7) {
            this.path.cubicTo(f2, f3, f4, f5, f6, f7);
            this.lastX = f6;
            this.lastY = f7;
        }

        public Path getPath() {
            return this.path;
        }

        public void lineTo(float f2, float f3) {
            this.path.lineTo(f2, f3);
            this.lastX = f2;
            this.lastY = f3;
        }

        public void moveTo(float f2, float f3) {
            this.path.moveTo(f2, f3);
            this.lastX = f2;
            this.lastY = f3;
        }

        public void quadTo(float f2, float f3, float f4, float f5) {
            this.path.quadTo(f2, f3, f4, f5);
            this.lastX = f4;
            this.lastY = f5;
        }
    }

    public class PathTextDrawer extends PlainTextDrawer {
        private Path path;

        public PathTextDrawer(Path path2, float f2, float f3) {
            super(f2, f3);
            this.path = path2;
        }

        public void processText(String str) {
            if (SVGAndroidRenderer.this.visible()) {
                if (SVGAndroidRenderer.this.state.hasFill) {
                    SVGAndroidRenderer.this.canvas.drawTextOnPath(str, this.path, this.f6539x, this.f6540y, SVGAndroidRenderer.this.state.fillPaint);
                }
                if (SVGAndroidRenderer.this.state.hasStroke) {
                    SVGAndroidRenderer.this.canvas.drawTextOnPath(str, this.path, this.f6539x, this.f6540y, SVGAndroidRenderer.this.state.strokePaint);
                }
            }
            this.f6539x = SVGAndroidRenderer.this.state.fillPaint.measureText(str) + this.f6539x;
        }
    }

    public class PlainTextDrawer extends TextProcessor {

        /* renamed from: x  reason: collision with root package name */
        float f6539x;

        /* renamed from: y  reason: collision with root package name */
        float f6540y;

        public PlainTextDrawer(float f2, float f3) {
            super(SVGAndroidRenderer.this, (AnonymousClass1) null);
            this.f6539x = f2;
            this.f6540y = f3;
        }

        public void processText(String str) {
            SVGAndroidRenderer.debug("TextSequence render", new Object[0]);
            if (SVGAndroidRenderer.this.visible()) {
                if (SVGAndroidRenderer.this.state.hasFill) {
                    SVGAndroidRenderer.this.canvas.drawText(str, this.f6539x, this.f6540y, SVGAndroidRenderer.this.state.fillPaint);
                }
                if (SVGAndroidRenderer.this.state.hasStroke) {
                    SVGAndroidRenderer.this.canvas.drawText(str, this.f6539x, this.f6540y, SVGAndroidRenderer.this.state.strokePaint);
                }
            }
            this.f6539x = SVGAndroidRenderer.this.state.fillPaint.measureText(str) + this.f6539x;
        }
    }

    public class PlainTextToPath extends TextProcessor {
        Path textAsPath;

        /* renamed from: x  reason: collision with root package name */
        float f6541x;

        /* renamed from: y  reason: collision with root package name */
        float f6542y;

        public PlainTextToPath(float f2, float f3, Path path) {
            super(SVGAndroidRenderer.this, (AnonymousClass1) null);
            this.f6541x = f2;
            this.f6542y = f3;
            this.textAsPath = path;
        }

        public boolean doTextContainer(SVG.TextContainer textContainer) {
            if (!(textContainer instanceof SVG.TextPath)) {
                return true;
            }
            SVGAndroidRenderer.warn("Using <textPath> elements in a clip path is not supported.", new Object[0]);
            return false;
        }

        public void processText(String str) {
            if (SVGAndroidRenderer.this.visible()) {
                Path path = new Path();
                SVGAndroidRenderer.this.state.fillPaint.getTextPath(str, 0, str.length(), this.f6541x, this.f6542y, path);
                this.textAsPath.addPath(path);
            }
            this.f6541x = SVGAndroidRenderer.this.state.fillPaint.measureText(str) + this.f6541x;
        }
    }

    public class TextBoundsCalculator extends TextProcessor {
        RectF bbox = new RectF();

        /* renamed from: x  reason: collision with root package name */
        float f6543x;

        /* renamed from: y  reason: collision with root package name */
        float f6544y;

        public TextBoundsCalculator(float f2, float f3) {
            super(SVGAndroidRenderer.this, (AnonymousClass1) null);
            this.f6543x = f2;
            this.f6544y = f3;
        }

        public boolean doTextContainer(SVG.TextContainer textContainer) {
            if (!(textContainer instanceof SVG.TextPath)) {
                return true;
            }
            SVG.TextPath textPath = (SVG.TextPath) textContainer;
            SVG.SvgObject resolveIRI = textContainer.document.resolveIRI(textPath.href);
            if (resolveIRI == null) {
                SVGAndroidRenderer.error("TextPath path reference '%s' not found", textPath.href);
                return false;
            }
            SVG.Path path = (SVG.Path) resolveIRI;
            Path path2 = new PathConverter(path.f6525d).getPath();
            Matrix matrix = path.transform;
            if (matrix != null) {
                path2.transform(matrix);
            }
            RectF rectF = new RectF();
            path2.computeBounds(rectF, true);
            this.bbox.union(rectF);
            return false;
        }

        public void processText(String str) {
            if (SVGAndroidRenderer.this.visible()) {
                Rect rect = new Rect();
                SVGAndroidRenderer.this.state.fillPaint.getTextBounds(str, 0, str.length(), rect);
                RectF rectF = new RectF(rect);
                rectF.offset(this.f6543x, this.f6544y);
                this.bbox.union(rectF);
            }
            this.f6543x = SVGAndroidRenderer.this.state.fillPaint.measureText(str) + this.f6543x;
        }
    }

    public abstract class TextProcessor {
        private TextProcessor() {
        }

        public boolean doTextContainer(SVG.TextContainer textContainer) {
            return true;
        }

        public abstract void processText(String str);

        public /* synthetic */ TextProcessor(SVGAndroidRenderer sVGAndroidRenderer, AnonymousClass1 r2) {
            this();
        }
    }

    public SVGAndroidRenderer(Canvas canvas2, float f2) {
        this.canvas = canvas2;
        this.dpi = f2;
    }

    private void addObjectToClip(SVG.SvgObject svgObject, boolean z2, Path path, Matrix matrix) {
        if (display()) {
            clipStatePush();
            if (svgObject instanceof SVG.Use) {
                if (z2) {
                    addObjectToClip((SVG.Use) svgObject, path, matrix);
                } else {
                    error("<use> elements inside a <clipPath> cannot reference another <use>", new Object[0]);
                }
            } else if (svgObject instanceof SVG.Path) {
                addObjectToClip((SVG.Path) svgObject, path, matrix);
            } else if (svgObject instanceof SVG.Text) {
                addObjectToClip((SVG.Text) svgObject, path, matrix);
            } else if (svgObject instanceof SVG.GraphicsElement) {
                addObjectToClip((SVG.GraphicsElement) svgObject, path, matrix);
            } else {
                error("Invalid %s element found in clipPath definition", svgObject.toString());
            }
            clipStatePop();
        }
    }

    /* access modifiers changed from: private */
    public static void arcTo(float f2, float f3, float f4, float f5, float f6, boolean z2, boolean z3, float f7, float f8, SVG.PathInterface pathInterface) {
        float f9 = f6;
        boolean z4 = z3;
        float f10 = f7;
        float f11 = f8;
        if (f2 != f10 || f3 != f11) {
            if (f4 == 0.0f || f5 == 0.0f) {
                pathInterface.lineTo(f10, f11);
                return;
            }
            float abs = Math.abs(f4);
            float abs2 = Math.abs(f5);
            double radians = Math.toRadians(((double) f9) % 360.0d);
            double cos = Math.cos(radians);
            double sin = Math.sin(radians);
            double d2 = ((double) (f2 - f10)) / 2.0d;
            double d3 = ((double) (f3 - f11)) / 2.0d;
            double d4 = (sin * d3) + (cos * d2);
            double d5 = (d3 * cos) + ((-sin) * d2);
            double d6 = (double) (abs * abs);
            double d7 = (double) (abs2 * abs2);
            double d8 = d4 * d4;
            double d9 = d5 * d5;
            double d10 = (d9 / d7) + (d8 / d6);
            if (d10 > 0.99999d) {
                double sqrt = Math.sqrt(d10) * 1.00001d;
                abs = (float) (((double) abs) * sqrt);
                abs2 = (float) (sqrt * ((double) abs2));
                d6 = (double) (abs * abs);
                d7 = (double) (abs2 * abs2);
            }
            double d11 = 1.0d;
            double d12 = z2 == z4 ? -1.0d : 1.0d;
            double d13 = d6 * d7;
            double d14 = d6 * d9;
            double d15 = d7 * d8;
            double d16 = ((d13 - d14) - d15) / (d14 + d15);
            if (d16 < 0.0d) {
                d16 = 0.0d;
            }
            double sqrt2 = Math.sqrt(d16) * d12;
            double d17 = (double) abs;
            double d18 = (double) abs2;
            double d19 = ((d17 * d5) / d18) * sqrt2;
            float f12 = abs;
            float f13 = abs2;
            double d20 = sqrt2 * (-((d18 * d4) / d17));
            double d21 = d18;
            double d22 = ((cos * d19) - (sin * d20)) + (((double) (f2 + f7)) / 2.0d);
            double d23 = (cos * d20) + (sin * d19) + (((double) (f3 + f8)) / 2.0d);
            double d24 = (d4 - d19) / d17;
            double d25 = d21;
            double d26 = (d5 - d20) / d25;
            double d27 = ((-d4) - d19) / d17;
            double d28 = ((-d5) - d20) / d25;
            double d29 = (d26 * d26) + (d24 * d24);
            double acos = Math.acos(d24 / Math.sqrt(d29)) * (d26 < 0.0d ? -1.0d : 1.0d);
            double sqrt3 = Math.sqrt(((d28 * d28) + (d27 * d27)) * d29);
            double d30 = (d26 * d28) + (d24 * d27);
            if ((d24 * d28) - (d26 * d27) < 0.0d) {
                d11 = -1.0d;
            }
            double checkedArcCos = d11 * checkedArcCos(d30 / sqrt3);
            if (!z3 && checkedArcCos > 0.0d) {
                checkedArcCos -= 6.283185307179586d;
            } else if (z3 && checkedArcCos < 0.0d) {
                checkedArcCos += 6.283185307179586d;
            }
            float[] arcToBeziers = arcToBeziers(acos % 6.283185307179586d, checkedArcCos % 6.283185307179586d);
            Matrix matrix = new Matrix();
            matrix.postScale(f12, f13);
            matrix.postRotate(f6);
            matrix.postTranslate((float) d22, (float) d23);
            matrix.mapPoints(arcToBeziers);
            arcToBeziers[arcToBeziers.length - 2] = f7;
            arcToBeziers[arcToBeziers.length - 1] = f8;
            for (int i3 = 0; i3 < arcToBeziers.length; i3 += 6) {
                pathInterface.cubicTo(arcToBeziers[i3], arcToBeziers[i3 + 1], arcToBeziers[i3 + 2], arcToBeziers[i3 + 3], arcToBeziers[i3 + 4], arcToBeziers[i3 + 5]);
            }
        }
    }

    private static float[] arcToBeziers(double d2, double d3) {
        int ceil = (int) Math.ceil((Math.abs(d3) * 2.0d) / 3.141592653589793d);
        double d4 = d3 / ((double) ceil);
        double d5 = d4 / 2.0d;
        double sin = (Math.sin(d5) * 1.3333333333333333d) / (Math.cos(d5) + 1.0d);
        float[] fArr = new float[(ceil * 6)];
        int i3 = 0;
        int i4 = 0;
        while (i3 < ceil) {
            double d6 = (((double) i3) * d4) + d2;
            double cos = Math.cos(d6);
            double sin2 = Math.sin(d6);
            fArr[i4] = (float) (cos - (sin * sin2));
            fArr[i4 + 1] = (float) ((cos * sin) + sin2);
            double d7 = d4;
            double d8 = d6 + d7;
            double cos2 = Math.cos(d8);
            double sin3 = Math.sin(d8);
            fArr[i4 + 2] = (float) ((sin * sin3) + cos2);
            fArr[i4 + 3] = (float) (sin3 - (sin * cos2));
            int i5 = i4 + 5;
            fArr[i4 + 4] = (float) cos2;
            i4 += 6;
            fArr[i5] = (float) sin3;
            i3++;
            d4 = d7;
        }
        return fArr;
    }

    @TargetApi(19)
    private Path calculateClipPath(SVG.SvgElement svgElement, SVG.Box box) {
        Path objectToPath;
        SVG.SvgObject resolveIRI = svgElement.document.resolveIRI(this.state.style.clipPath);
        if (resolveIRI == null) {
            error("ClipPath reference '%s' not found", this.state.style.clipPath);
            return null;
        }
        SVG.ClipPath clipPath = (SVG.ClipPath) resolveIRI;
        this.stateStack.push(this.state);
        this.state = findInheritFromAncestorState(clipPath);
        Boolean bool = clipPath.clipPathUnitsAreUser;
        boolean z2 = bool == null || bool.booleanValue();
        Matrix matrix = new Matrix();
        if (!z2) {
            matrix.preTranslate(box.minX, box.minY);
            matrix.preScale(box.width, box.height);
        }
        Matrix matrix2 = clipPath.transform;
        if (matrix2 != null) {
            matrix.preConcat(matrix2);
        }
        Path path = new Path();
        for (SVG.SvgObject next : clipPath.children) {
            if ((next instanceof SVG.SvgElement) && (objectToPath = objectToPath((SVG.SvgElement) next, true)) != null) {
                path.op(objectToPath, Path.Op.UNION);
            }
        }
        if (this.state.style.clipPath != null) {
            if (clipPath.boundingBox == null) {
                clipPath.boundingBox = calculatePathBounds(path);
            }
            Path calculateClipPath = calculateClipPath(clipPath, clipPath.boundingBox);
            if (calculateClipPath != null) {
                path.op(calculateClipPath, Path.Op.INTERSECT);
            }
        }
        path.transform(matrix);
        this.state = this.stateStack.pop();
        return path;
    }

    private List<MarkerVector> calculateMarkerPositions(SVG.Line line) {
        SVG.Length length = line.x1;
        float f2 = 0.0f;
        float floatValueX = length != null ? length.floatValueX(this) : 0.0f;
        SVG.Length length2 = line.y1;
        float floatValueY = length2 != null ? length2.floatValueY(this) : 0.0f;
        SVG.Length length3 = line.x2;
        float floatValueX2 = length3 != null ? length3.floatValueX(this) : 0.0f;
        SVG.Length length4 = line.y2;
        if (length4 != null) {
            f2 = length4.floatValueY(this);
        }
        float f3 = f2;
        ArrayList arrayList = new ArrayList(2);
        float f4 = floatValueX2 - floatValueX;
        float f5 = f3 - floatValueY;
        arrayList.add(new MarkerVector(floatValueX, floatValueY, f4, f5));
        arrayList.add(new MarkerVector(floatValueX2, f3, f4, f5));
        return arrayList;
    }

    private SVG.Box calculatePathBounds(Path path) {
        RectF rectF = new RectF();
        path.computeBounds(rectF, true);
        return new SVG.Box(rectF.left, rectF.top, rectF.width(), rectF.height());
    }

    private float calculateTextWidth(SVG.TextContainer textContainer) {
        TextWidthCalculator textWidthCalculator = new TextWidthCalculator(this, (AnonymousClass1) null);
        enumerateTextSpans(textContainer, textWidthCalculator);
        return textWidthCalculator.f6545x;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0061, code lost:
        r2 = r2 - r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0083, code lost:
        if (r11 != 8) goto L_0x0090;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.Matrix calculateViewBoxTransform(com.caverock.androidsvg.SVG.Box r9, com.caverock.androidsvg.SVG.Box r10, com.caverock.androidsvg.PreserveAspectRatio r11) {
        /*
            r8 = this;
            android.graphics.Matrix r8 = new android.graphics.Matrix
            r8.<init>()
            if (r11 == 0) goto L_0x009d
            com.caverock.androidsvg.PreserveAspectRatio$Alignment r0 = r11.getAlignment()
            if (r0 != 0) goto L_0x000f
            goto L_0x009d
        L_0x000f:
            float r0 = r9.width
            float r1 = r10.width
            float r0 = r0 / r1
            float r1 = r9.height
            float r2 = r10.height
            float r1 = r1 / r2
            float r2 = r10.minX
            float r2 = -r2
            float r3 = r10.minY
            float r3 = -r3
            com.caverock.androidsvg.PreserveAspectRatio r4 = com.caverock.androidsvg.PreserveAspectRatio.STRETCH
            boolean r4 = r11.equals(r4)
            if (r4 == 0) goto L_0x0035
            float r10 = r9.minX
            float r9 = r9.minY
            r8.preTranslate(r10, r9)
            r8.preScale(r0, r1)
            r8.preTranslate(r2, r3)
            return r8
        L_0x0035:
            com.caverock.androidsvg.PreserveAspectRatio$Scale r4 = r11.getScale()
            com.caverock.androidsvg.PreserveAspectRatio$Scale r5 = com.caverock.androidsvg.PreserveAspectRatio.Scale.slice
            if (r4 != r5) goto L_0x0042
            float r0 = java.lang.Math.max(r0, r1)
            goto L_0x0046
        L_0x0042:
            float r0 = java.lang.Math.min(r0, r1)
        L_0x0046:
            float r1 = r9.width
            float r1 = r1 / r0
            float r4 = r9.height
            float r4 = r4 / r0
            int[] r5 = com.caverock.androidsvg.SVGAndroidRenderer.AnonymousClass1.$SwitchMap$com$caverock$androidsvg$PreserveAspectRatio$Alignment
            com.caverock.androidsvg.PreserveAspectRatio$Alignment r6 = r11.getAlignment()
            int r6 = r6.ordinal()
            r6 = r5[r6]
            r7 = 1073741824(0x40000000, float:2.0)
            switch(r6) {
                case 1: goto L_0x0063;
                case 2: goto L_0x0063;
                case 3: goto L_0x0063;
                case 4: goto L_0x005e;
                case 5: goto L_0x005e;
                case 6: goto L_0x005e;
                default: goto L_0x005d;
            }
        L_0x005d:
            goto L_0x0068
        L_0x005e:
            float r6 = r10.width
            float r6 = r6 - r1
        L_0x0061:
            float r2 = r2 - r6
            goto L_0x0068
        L_0x0063:
            float r6 = r10.width
            float r6 = r6 - r1
            float r6 = r6 / r7
            goto L_0x0061
        L_0x0068:
            com.caverock.androidsvg.PreserveAspectRatio$Alignment r11 = r11.getAlignment()
            int r11 = r11.ordinal()
            r11 = r5[r11]
            r1 = 2
            if (r11 == r1) goto L_0x008b
            r1 = 3
            if (r11 == r1) goto L_0x0086
            r1 = 5
            if (r11 == r1) goto L_0x008b
            r1 = 6
            if (r11 == r1) goto L_0x0086
            r1 = 7
            if (r11 == r1) goto L_0x008b
            r1 = 8
            if (r11 == r1) goto L_0x0086
            goto L_0x0090
        L_0x0086:
            float r10 = r10.height
            float r10 = r10 - r4
        L_0x0089:
            float r3 = r3 - r10
            goto L_0x0090
        L_0x008b:
            float r10 = r10.height
            float r10 = r10 - r4
            float r10 = r10 / r7
            goto L_0x0089
        L_0x0090:
            float r10 = r9.minX
            float r9 = r9.minY
            r8.preTranslate(r10, r9)
            r8.preScale(r0, r0)
            r8.preTranslate(r2, r3)
        L_0x009d:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.caverock.androidsvg.SVGAndroidRenderer.calculateViewBoxTransform(com.caverock.androidsvg.SVG$Box, com.caverock.androidsvg.SVG$Box, com.caverock.androidsvg.PreserveAspectRatio):android.graphics.Matrix");
    }

    private void checkForClipPath(SVG.SvgElement svgElement) {
        checkForClipPath(svgElement, svgElement.boundingBox);
    }

    private void checkForClipPath_OldStyle(SVG.SvgElement svgElement, SVG.Box box) {
        SVG.SvgObject resolveIRI = svgElement.document.resolveIRI(this.state.style.clipPath);
        if (resolveIRI == null) {
            error("ClipPath reference '%s' not found", this.state.style.clipPath);
            return;
        }
        SVG.ClipPath clipPath = (SVG.ClipPath) resolveIRI;
        boolean z2 = false;
        if (clipPath.children.isEmpty()) {
            this.canvas.clipRect(0, 0, 0, 0);
            return;
        }
        Boolean bool = clipPath.clipPathUnitsAreUser;
        if (bool == null || bool.booleanValue()) {
            z2 = true;
        }
        if (!(svgElement instanceof SVG.Group) || z2) {
            clipStatePush();
            if (!z2) {
                Matrix matrix = new Matrix();
                matrix.preTranslate(box.minX, box.minY);
                matrix.preScale(box.width, box.height);
                this.canvas.concat(matrix);
            }
            Matrix matrix2 = clipPath.transform;
            if (matrix2 != null) {
                this.canvas.concat(matrix2);
            }
            this.state = findInheritFromAncestorState(clipPath);
            checkForClipPath(clipPath);
            Path path = new Path();
            for (SVG.SvgObject addObjectToClip : clipPath.children) {
                addObjectToClip(addObjectToClip, true, path, new Matrix());
            }
            this.canvas.clipPath(path);
            clipStatePop();
            return;
        }
        warn("<clipPath clipPathUnits=\"objectBoundingBox\"> is not supported when referenced from container elements (like %s)", svgElement.getNodeName());
    }

    private void checkForGradientsAndPatterns(SVG.SvgElement svgElement) {
        SVG.SvgPaint svgPaint = this.state.style.fill;
        if (svgPaint instanceof SVG.PaintReference) {
            decodePaintReference(true, svgElement.boundingBox, (SVG.PaintReference) svgPaint);
        }
        SVG.SvgPaint svgPaint2 = this.state.style.stroke;
        if (svgPaint2 instanceof SVG.PaintReference) {
            decodePaintReference(false, svgElement.boundingBox, (SVG.PaintReference) svgPaint2);
        }
    }

    private Bitmap checkForImageDataURL(String str) {
        int indexOf;
        if (!str.startsWith("data:") || str.length() < 14 || (indexOf = str.indexOf(44)) < 12 || !";base64".equals(str.substring(indexOf - 7, indexOf))) {
            return null;
        }
        try {
            byte[] decode = Base64.decode(str.substring(indexOf + 1), 0);
            return BitmapFactory.decodeByteArray(decode, 0, decode.length);
        } catch (Exception e3) {
            Log.e(TAG, "Could not decode bad Data URL", e3);
            return null;
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0047, code lost:
        if (r5.equals("fantasy") == false) goto L_0x0029;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.Typeface checkGenericFont(java.lang.String r5, java.lang.Integer r6, com.caverock.androidsvg.SVG.Style.FontStyle r7) {
        /*
            r4 = this;
            r4 = 2
            r0 = 3
            com.caverock.androidsvg.SVG$Style$FontStyle r1 = com.caverock.androidsvg.SVG.Style.FontStyle.Italic
            r2 = 0
            r3 = 1
            if (r7 != r1) goto L_0x000a
            r7 = r3
            goto L_0x000b
        L_0x000a:
            r7 = r2
        L_0x000b:
            int r6 = r6.intValue()
            r1 = 500(0x1f4, float:7.0E-43)
            if (r6 <= r1) goto L_0x0019
            if (r7 == 0) goto L_0x0017
            r6 = r0
            goto L_0x001e
        L_0x0017:
            r6 = r3
            goto L_0x001e
        L_0x0019:
            if (r7 == 0) goto L_0x001d
            r6 = r4
            goto L_0x001e
        L_0x001d:
            r6 = r2
        L_0x001e:
            r5.getClass()
            r7 = -1
            int r1 = r5.hashCode()
            switch(r1) {
                case -1536685117: goto L_0x0055;
                case -1431958525: goto L_0x004a;
                case -1081737434: goto L_0x0041;
                case 109326717: goto L_0x0036;
                case 1126973893: goto L_0x002b;
                default: goto L_0x0029;
            }
        L_0x0029:
            r4 = r7
            goto L_0x005f
        L_0x002b:
            java.lang.String r4 = "cursive"
            boolean r4 = r5.equals(r4)
            if (r4 != 0) goto L_0x0034
            goto L_0x0029
        L_0x0034:
            r4 = 4
            goto L_0x005f
        L_0x0036:
            java.lang.String r4 = "serif"
            boolean r4 = r5.equals(r4)
            if (r4 != 0) goto L_0x003f
            goto L_0x0029
        L_0x003f:
            r4 = r0
            goto L_0x005f
        L_0x0041:
            java.lang.String r0 = "fantasy"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x005f
            goto L_0x0029
        L_0x004a:
            java.lang.String r4 = "monospace"
            boolean r4 = r5.equals(r4)
            if (r4 != 0) goto L_0x0053
            goto L_0x0029
        L_0x0053:
            r4 = r3
            goto L_0x005f
        L_0x0055:
            java.lang.String r4 = "sans-serif"
            boolean r4 = r5.equals(r4)
            if (r4 != 0) goto L_0x005e
            goto L_0x0029
        L_0x005e:
            r4 = r2
        L_0x005f:
            switch(r4) {
                case 0: goto L_0x0080;
                case 1: goto L_0x0079;
                case 2: goto L_0x0072;
                case 3: goto L_0x006b;
                case 4: goto L_0x0064;
                default: goto L_0x0062;
            }
        L_0x0062:
            r4 = 0
            goto L_0x0086
        L_0x0064:
            android.graphics.Typeface r4 = android.graphics.Typeface.SANS_SERIF
            android.graphics.Typeface r4 = android.graphics.Typeface.create(r4, r6)
            goto L_0x0086
        L_0x006b:
            android.graphics.Typeface r4 = android.graphics.Typeface.SERIF
            android.graphics.Typeface r4 = android.graphics.Typeface.create(r4, r6)
            goto L_0x0086
        L_0x0072:
            android.graphics.Typeface r4 = android.graphics.Typeface.SANS_SERIF
            android.graphics.Typeface r4 = android.graphics.Typeface.create(r4, r6)
            goto L_0x0086
        L_0x0079:
            android.graphics.Typeface r4 = android.graphics.Typeface.MONOSPACE
            android.graphics.Typeface r4 = android.graphics.Typeface.create(r4, r6)
            goto L_0x0086
        L_0x0080:
            android.graphics.Typeface r4 = android.graphics.Typeface.SANS_SERIF
            android.graphics.Typeface r4 = android.graphics.Typeface.create(r4, r6)
        L_0x0086:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.caverock.androidsvg.SVGAndroidRenderer.checkGenericFont(java.lang.String, java.lang.Integer, com.caverock.androidsvg.SVG$Style$FontStyle):android.graphics.Typeface");
    }

    private void checkXMLSpaceAttribute(SVG.SvgObject svgObject) {
        Boolean bool;
        if ((svgObject instanceof SVG.SvgElementBase) && (bool = ((SVG.SvgElementBase) svgObject).spacePreserve) != null) {
            this.state.spacePreserve = bool.booleanValue();
        }
    }

    private static double checkedArcCos(double d2) {
        if (d2 < -1.0d) {
            return 3.141592653589793d;
        }
        if (d2 > 1.0d) {
            return 0.0d;
        }
        return Math.acos(d2);
    }

    private static int clamp255(float f2) {
        int i3 = (int) (f2 * 256.0f);
        if (i3 < 0) {
            return 0;
        }
        if (i3 > 255) {
            return 255;
        }
        return i3;
    }

    private void clipStatePop() {
        this.canvas.restore();
        this.state = this.stateStack.pop();
    }

    private void clipStatePush() {
        CanvasLegacy.save(this.canvas, CanvasLegacy.MATRIX_SAVE_FLAG);
        this.stateStack.push(this.state);
        this.state = new RendererState(this.state);
    }

    private static int colourWithOpacity(int i3, float f2) {
        int i4 = 255;
        int round = Math.round(((float) ((i3 >> 24) & 255)) * f2);
        if (round < 0) {
            i4 = 0;
        } else if (round <= 255) {
            i4 = round;
        }
        return (i3 & ViewCompat.MEASURED_SIZE_MASK) | (i4 << 24);
    }

    /* access modifiers changed from: private */
    public static void debug(String str, Object... objArr) {
    }

    private void decodePaintReference(boolean z2, SVG.Box box, SVG.PaintReference paintReference) {
        SVG.SvgObject resolveIRI = this.document.resolveIRI(paintReference.href);
        if (resolveIRI == null) {
            error("%s reference '%s' not found", z2 ? "Fill" : "Stroke", paintReference.href);
            SVG.SvgPaint svgPaint = paintReference.fallback;
            if (svgPaint != null) {
                setPaintColour(this.state, z2, svgPaint);
            } else if (z2) {
                this.state.hasFill = false;
            } else {
                this.state.hasStroke = false;
            }
        } else if (resolveIRI instanceof SVG.SvgLinearGradient) {
            makeLinearGradient(z2, box, (SVG.SvgLinearGradient) resolveIRI);
        } else if (resolveIRI instanceof SVG.SvgRadialGradient) {
            makeRadialGradient(z2, box, (SVG.SvgRadialGradient) resolveIRI);
        } else if (resolveIRI instanceof SVG.SolidColor) {
            setSolidColor(z2, (SVG.SolidColor) resolveIRI);
        }
    }

    private boolean display() {
        Boolean bool = this.state.style.display;
        if (bool != null) {
            return bool.booleanValue();
        }
        return true;
    }

    private void doFilledPath(SVG.SvgElement svgElement, Path path) {
        SVG.SvgPaint svgPaint = this.state.style.fill;
        if (svgPaint instanceof SVG.PaintReference) {
            SVG.SvgObject resolveIRI = this.document.resolveIRI(((SVG.PaintReference) svgPaint).href);
            if (resolveIRI instanceof SVG.Pattern) {
                fillWithPattern(svgElement, path, (SVG.Pattern) resolveIRI);
                return;
            }
        }
        this.canvas.drawPath(path, this.state.fillPaint);
    }

    private void doStroke(Path path) {
        RendererState rendererState = this.state;
        if (rendererState.style.vectorEffect == SVG.Style.VectorEffect.NonScalingStroke) {
            Matrix matrix = this.canvas.getMatrix();
            Path path2 = new Path();
            path.transform(matrix, path2);
            this.canvas.setMatrix(new Matrix());
            Shader shader = this.state.strokePaint.getShader();
            Matrix matrix2 = new Matrix();
            if (shader != null) {
                shader.getLocalMatrix(matrix2);
                Matrix matrix3 = new Matrix(matrix2);
                matrix3.postConcat(matrix);
                shader.setLocalMatrix(matrix3);
            }
            this.canvas.drawPath(path2, this.state.strokePaint);
            this.canvas.setMatrix(matrix);
            if (shader != null) {
                shader.setLocalMatrix(matrix2);
                return;
            }
            return;
        }
        this.canvas.drawPath(path, rendererState.strokePaint);
    }

    private float dotProduct(float f2, float f3, float f4, float f5) {
        return (f3 * f5) + (f2 * f4);
    }

    private void enumerateTextSpans(SVG.TextContainer textContainer, TextProcessor textProcessor) {
        if (display()) {
            Iterator<SVG.SvgObject> it = textContainer.children.iterator();
            boolean z2 = true;
            while (it.hasNext()) {
                SVG.SvgObject next = it.next();
                if (next instanceof SVG.TextSequence) {
                    textProcessor.processText(textXMLSpaceTransform(((SVG.TextSequence) next).text, z2, !it.hasNext()));
                } else {
                    processTextChild(next, textProcessor);
                }
                z2 = false;
            }
        }
    }

    /* access modifiers changed from: private */
    public static void error(String str, Object... objArr) {
        Log.e(TAG, String.format(str, objArr));
    }

    private void extractRawText(SVG.TextContainer textContainer, StringBuilder sb) {
        Iterator<SVG.SvgObject> it = textContainer.children.iterator();
        boolean z2 = true;
        while (it.hasNext()) {
            SVG.SvgObject next = it.next();
            if (next instanceof SVG.TextContainer) {
                extractRawText((SVG.TextContainer) next, sb);
            } else if (next instanceof SVG.TextSequence) {
                sb.append(textXMLSpaceTransform(((SVG.TextSequence) next).text, z2, !it.hasNext()));
            }
            z2 = false;
        }
    }

    private void fillInChainedGradientFields(SVG.GradientElement gradientElement, String str) {
        SVG.SvgObject resolveIRI = gradientElement.document.resolveIRI(str);
        if (resolveIRI == null) {
            warn("Gradient reference '%s' not found", str);
        } else if (!(resolveIRI instanceof SVG.GradientElement)) {
            error("Gradient href attributes must point to other gradient elements", new Object[0]);
        } else if (resolveIRI == gradientElement) {
            error("Circular reference in gradient href attribute '%s'", str);
        } else {
            SVG.GradientElement gradientElement2 = (SVG.GradientElement) resolveIRI;
            if (gradientElement.gradientUnitsAreUser == null) {
                gradientElement.gradientUnitsAreUser = gradientElement2.gradientUnitsAreUser;
            }
            if (gradientElement.gradientTransform == null) {
                gradientElement.gradientTransform = gradientElement2.gradientTransform;
            }
            if (gradientElement.spreadMethod == null) {
                gradientElement.spreadMethod = gradientElement2.spreadMethod;
            }
            if (gradientElement.children.isEmpty()) {
                gradientElement.children = gradientElement2.children;
            }
            try {
                if (gradientElement instanceof SVG.SvgLinearGradient) {
                    fillInChainedGradientFields((SVG.SvgLinearGradient) gradientElement, (SVG.SvgLinearGradient) resolveIRI);
                } else {
                    fillInChainedGradientFields((SVG.SvgRadialGradient) gradientElement, (SVG.SvgRadialGradient) resolveIRI);
                }
            } catch (ClassCastException unused) {
            }
            String str2 = gradientElement2.href;
            if (str2 != null) {
                fillInChainedGradientFields(gradientElement, str2);
            }
        }
    }

    private void fillInChainedPatternFields(SVG.Pattern pattern, String str) {
        SVG.SvgObject resolveIRI = pattern.document.resolveIRI(str);
        if (resolveIRI == null) {
            warn("Pattern reference '%s' not found", str);
        } else if (!(resolveIRI instanceof SVG.Pattern)) {
            error("Pattern href attributes must point to other pattern elements", new Object[0]);
        } else if (resolveIRI == pattern) {
            error("Circular reference in pattern href attribute '%s'", str);
        } else {
            SVG.Pattern pattern2 = (SVG.Pattern) resolveIRI;
            if (pattern.patternUnitsAreUser == null) {
                pattern.patternUnitsAreUser = pattern2.patternUnitsAreUser;
            }
            if (pattern.patternContentUnitsAreUser == null) {
                pattern.patternContentUnitsAreUser = pattern2.patternContentUnitsAreUser;
            }
            if (pattern.patternTransform == null) {
                pattern.patternTransform = pattern2.patternTransform;
            }
            if (pattern.f6526x == null) {
                pattern.f6526x = pattern2.f6526x;
            }
            if (pattern.f6527y == null) {
                pattern.f6527y = pattern2.f6527y;
            }
            if (pattern.width == null) {
                pattern.width = pattern2.width;
            }
            if (pattern.height == null) {
                pattern.height = pattern2.height;
            }
            if (pattern.children.isEmpty()) {
                pattern.children = pattern2.children;
            }
            if (pattern.viewBox == null) {
                pattern.viewBox = pattern2.viewBox;
            }
            if (pattern.preserveAspectRatio == null) {
                pattern.preserveAspectRatio = pattern2.preserveAspectRatio;
            }
            String str2 = pattern2.href;
            if (str2 != null) {
                fillInChainedPatternFields(pattern, str2);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:101:0x0212  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0190  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x01f6 A[LOOP:3: B:95:0x01f0->B:97:0x01f6, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void fillWithPattern(com.caverock.androidsvg.SVG.SvgElement r20, android.graphics.Path r21, com.caverock.androidsvg.SVG.Pattern r22) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            r2 = r22
            java.lang.Boolean r5 = r2.patternUnitsAreUser
            if (r5 == 0) goto L_0x0012
            boolean r5 = r5.booleanValue()
            if (r5 == 0) goto L_0x0012
            r5 = 1
            goto L_0x0013
        L_0x0012:
            r5 = 0
        L_0x0013:
            java.lang.String r8 = r2.href
            if (r8 == 0) goto L_0x001a
            r0.fillInChainedPatternFields(r2, r8)
        L_0x001a:
            r8 = 0
            if (r5 == 0) goto L_0x0046
            com.caverock.androidsvg.SVG$Length r5 = r2.f6526x
            if (r5 == 0) goto L_0x0026
            float r5 = r5.floatValueX(r0)
            goto L_0x0027
        L_0x0026:
            r5 = r8
        L_0x0027:
            com.caverock.androidsvg.SVG$Length r9 = r2.f6527y
            if (r9 == 0) goto L_0x0030
            float r9 = r9.floatValueY(r0)
            goto L_0x0031
        L_0x0030:
            r9 = r8
        L_0x0031:
            com.caverock.androidsvg.SVG$Length r10 = r2.width
            if (r10 == 0) goto L_0x003a
            float r10 = r10.floatValueX(r0)
            goto L_0x003b
        L_0x003a:
            r10 = r8
        L_0x003b:
            com.caverock.androidsvg.SVG$Length r11 = r2.height
            if (r11 == 0) goto L_0x0044
            float r11 = r11.floatValueY(r0)
            goto L_0x0086
        L_0x0044:
            r11 = r8
            goto L_0x0086
        L_0x0046:
            com.caverock.androidsvg.SVG$Length r5 = r2.f6526x
            r9 = 1065353216(0x3f800000, float:1.0)
            if (r5 == 0) goto L_0x0051
            float r5 = r5.floatValue(r0, r9)
            goto L_0x0052
        L_0x0051:
            r5 = r8
        L_0x0052:
            com.caverock.androidsvg.SVG$Length r10 = r2.f6527y
            if (r10 == 0) goto L_0x005b
            float r10 = r10.floatValue(r0, r9)
            goto L_0x005c
        L_0x005b:
            r10 = r8
        L_0x005c:
            com.caverock.androidsvg.SVG$Length r11 = r2.width
            if (r11 == 0) goto L_0x0065
            float r11 = r11.floatValue(r0, r9)
            goto L_0x0066
        L_0x0065:
            r11 = r8
        L_0x0066:
            com.caverock.androidsvg.SVG$Length r12 = r2.height
            if (r12 == 0) goto L_0x006f
            float r9 = r12.floatValue(r0, r9)
            goto L_0x0070
        L_0x006f:
            r9 = r8
        L_0x0070:
            com.caverock.androidsvg.SVG$Box r12 = r1.boundingBox
            float r13 = r12.minX
            float r14 = r12.width
            float r5 = r5 * r14
            float r5 = r5 + r13
            float r13 = r12.minY
            float r12 = r12.height
            float r10 = r10 * r12
            float r10 = r10 + r13
            float r11 = r11 * r14
            float r9 = r9 * r12
            r18 = r11
            r11 = r9
            r9 = r10
            r10 = r18
        L_0x0086:
            int r12 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
            if (r12 == 0) goto L_0x0218
            int r12 = (r11 > r8 ? 1 : (r11 == r8 ? 0 : -1))
            if (r12 != 0) goto L_0x0090
            goto L_0x0218
        L_0x0090:
            com.caverock.androidsvg.PreserveAspectRatio r12 = r2.preserveAspectRatio
            if (r12 == 0) goto L_0x0095
            goto L_0x0097
        L_0x0095:
            com.caverock.androidsvg.PreserveAspectRatio r12 = com.caverock.androidsvg.PreserveAspectRatio.LETTERBOX
        L_0x0097:
            r19.statePush()
            android.graphics.Canvas r13 = r0.canvas
            r14 = r21
            r13.clipPath(r14)
            com.caverock.androidsvg.SVGAndroidRenderer$RendererState r13 = new com.caverock.androidsvg.SVGAndroidRenderer$RendererState
            r13.<init>()
            com.caverock.androidsvg.SVG$Style r14 = com.caverock.androidsvg.SVG.Style.getDefaultStyle()
            r0.updateStyle(r13, r14)
            com.caverock.androidsvg.SVG$Style r14 = r13.style
            java.lang.Boolean r15 = java.lang.Boolean.FALSE
            r14.overflow = r15
            com.caverock.androidsvg.SVGAndroidRenderer$RendererState r13 = r0.findInheritFromAncestorState(r2, r13)
            r0.state = r13
            com.caverock.androidsvg.SVG$Box r13 = r1.boundingBox
            android.graphics.Matrix r14 = r2.patternTransform
            if (r14 == 0) goto L_0x015d
            android.graphics.Canvas r15 = r0.canvas
            r15.concat(r14)
            android.graphics.Matrix r14 = new android.graphics.Matrix
            r14.<init>()
            android.graphics.Matrix r15 = r2.patternTransform
            boolean r15 = r15.invert(r14)
            if (r15 == 0) goto L_0x015d
            com.caverock.androidsvg.SVG$Box r13 = r1.boundingBox
            float r15 = r13.minX
            float r8 = r13.minY
            float r13 = r13.maxX()
            com.caverock.androidsvg.SVG$Box r3 = r1.boundingBox
            float r4 = r3.minY
            float r3 = r3.maxX()
            com.caverock.androidsvg.SVG$Box r7 = r1.boundingBox
            float r7 = r7.maxY()
            com.caverock.androidsvg.SVG$Box r6 = r1.boundingBox
            float r1 = r6.minX
            float r6 = r6.maxY()
            r17 = r12
            r12 = 8
            float[] r12 = new float[r12]
            r16 = 0
            r12[r16] = r15
            r15 = 1
            r12[r15] = r8
            r8 = 2
            r12[r8] = r13
            r8 = 3
            r12[r8] = r4
            r4 = 4
            r12[r4] = r3
            r3 = 5
            r12[r3] = r7
            r3 = 6
            r12[r3] = r1
            r1 = 7
            r12[r1] = r6
            r14.mapPoints(r12)
            android.graphics.RectF r1 = new android.graphics.RectF
            r16 = 0
            r4 = r12[r16]
            r6 = 1
            r7 = r12[r6]
            r1.<init>(r4, r7, r4, r7)
            r8 = 2
        L_0x0120:
            if (r8 > r3) goto L_0x014c
            r4 = r12[r8]
            float r6 = r1.left
            int r6 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r6 >= 0) goto L_0x012c
            r1.left = r4
        L_0x012c:
            float r6 = r1.right
            int r6 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r6 <= 0) goto L_0x0134
            r1.right = r4
        L_0x0134:
            r4 = 1
            int r7 = r8 + 1
            r6 = r12[r7]
            float r7 = r1.top
            int r7 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r7 >= 0) goto L_0x0141
            r1.top = r6
        L_0x0141:
            float r7 = r1.bottom
            int r7 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r7 <= 0) goto L_0x0149
            r1.bottom = r6
        L_0x0149:
            r6 = 2
            int r8 = r8 + r6
            goto L_0x0120
        L_0x014c:
            r4 = 1
            com.caverock.androidsvg.SVG$Box r13 = new com.caverock.androidsvg.SVG$Box
            float r3 = r1.left
            float r6 = r1.top
            float r7 = r1.right
            float r7 = r7 - r3
            float r1 = r1.bottom
            float r1 = r1 - r6
            r13.<init>(r3, r6, r7, r1)
            goto L_0x0162
        L_0x015d:
            r17 = r12
            r4 = 1
            r16 = 0
        L_0x0162:
            float r1 = r13.minX
            float r1 = r1 - r5
            float r1 = r1 / r10
            double r6 = (double) r1
            double r6 = java.lang.Math.floor(r6)
            float r1 = (float) r6
            float r1 = r1 * r10
            float r1 = r1 + r5
            float r3 = r13.minY
            float r3 = r3 - r9
            float r3 = r3 / r11
            double r5 = (double) r3
            double r5 = java.lang.Math.floor(r5)
            float r3 = (float) r5
            float r3 = r3 * r11
            float r3 = r3 + r9
            float r5 = r13.maxX()
            float r6 = r13.maxY()
            com.caverock.androidsvg.SVG$Box r7 = new com.caverock.androidsvg.SVG$Box
            r8 = 0
            r7.<init>(r8, r8, r10, r11)
            boolean r8 = r19.pushLayer()
        L_0x018c:
            int r9 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r9 >= 0) goto L_0x0210
            r9 = r1
        L_0x0191:
            int r12 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
            if (r12 >= 0) goto L_0x0208
            r7.minX = r9
            r7.minY = r3
            r19.statePush()
            com.caverock.androidsvg.SVGAndroidRenderer$RendererState r12 = r0.state
            com.caverock.androidsvg.SVG$Style r12 = r12.style
            java.lang.Boolean r12 = r12.overflow
            boolean r12 = r12.booleanValue()
            if (r12 != 0) goto L_0x01b3
            float r12 = r7.minX
            float r13 = r7.minY
            float r14 = r7.width
            float r15 = r7.height
            r0.setClipRect(r12, r13, r14, r15)
        L_0x01b3:
            com.caverock.androidsvg.SVG$Box r12 = r2.viewBox
            if (r12 == 0) goto L_0x01c5
            android.graphics.Canvas r13 = r0.canvas
            r14 = r17
            android.graphics.Matrix r12 = r0.calculateViewBoxTransform(r7, r12, r14)
            r13.concat(r12)
        L_0x01c2:
            r13 = r20
            goto L_0x01ea
        L_0x01c5:
            r14 = r17
            java.lang.Boolean r12 = r2.patternContentUnitsAreUser
            if (r12 == 0) goto L_0x01d5
            boolean r12 = r12.booleanValue()
            if (r12 == 0) goto L_0x01d2
            goto L_0x01d5
        L_0x01d2:
            r12 = r16
            goto L_0x01d6
        L_0x01d5:
            r12 = r4
        L_0x01d6:
            android.graphics.Canvas r13 = r0.canvas
            r13.translate(r9, r3)
            if (r12 != 0) goto L_0x01c2
            android.graphics.Canvas r12 = r0.canvas
            r13 = r20
            com.caverock.androidsvg.SVG$Box r15 = r13.boundingBox
            float r4 = r15.width
            float r15 = r15.height
            r12.scale(r4, r15)
        L_0x01ea:
            java.util.List<com.caverock.androidsvg.SVG$SvgObject> r4 = r2.children
            java.util.Iterator r4 = r4.iterator()
        L_0x01f0:
            boolean r12 = r4.hasNext()
            if (r12 == 0) goto L_0x0200
            java.lang.Object r12 = r4.next()
            com.caverock.androidsvg.SVG$SvgObject r12 = (com.caverock.androidsvg.SVG.SvgObject) r12
            r0.render((com.caverock.androidsvg.SVG.SvgObject) r12)
            goto L_0x01f0
        L_0x0200:
            r19.statePop()
            float r9 = r9 + r10
            r17 = r14
            r4 = 1
            goto L_0x0191
        L_0x0208:
            r13 = r20
            r14 = r17
            float r3 = r3 + r11
            r4 = 1
            goto L_0x018c
        L_0x0210:
            if (r8 == 0) goto L_0x0215
            r0.popLayer(r2)
        L_0x0215:
            r19.statePop()
        L_0x0218:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.caverock.androidsvg.SVGAndroidRenderer.fillWithPattern(com.caverock.androidsvg.SVG$SvgElement, android.graphics.Path, com.caverock.androidsvg.SVG$Pattern):void");
    }

    private RendererState findInheritFromAncestorState(SVG.SvgObject svgObject) {
        RendererState rendererState = new RendererState();
        updateStyle(rendererState, SVG.Style.getDefaultStyle());
        return findInheritFromAncestorState(svgObject, rendererState);
    }

    private SVG.Style.TextAnchor getAnchorPosition() {
        SVG.Style.TextAnchor textAnchor;
        SVG.Style style = this.state.style;
        if (style.direction == SVG.Style.TextDirection.LTR || (textAnchor = style.textAnchor) == SVG.Style.TextAnchor.Middle) {
            return style.textAnchor;
        }
        SVG.Style.TextAnchor textAnchor2 = SVG.Style.TextAnchor.Start;
        return textAnchor == textAnchor2 ? SVG.Style.TextAnchor.End : textAnchor2;
    }

    private Path.FillType getClipRuleFromState() {
        SVG.Style.FillRule fillRule = this.state.style.clipRule;
        return (fillRule == null || fillRule != SVG.Style.FillRule.EvenOdd) ? Path.FillType.WINDING : Path.FillType.EVEN_ODD;
    }

    private Path.FillType getFillTypeFromState() {
        SVG.Style.FillRule fillRule = this.state.style.fillRule;
        return (fillRule == null || fillRule != SVG.Style.FillRule.EvenOdd) ? Path.FillType.WINDING : Path.FillType.EVEN_ODD;
    }

    private static synchronized void initialiseSupportedFeaturesMap() {
        synchronized (SVGAndroidRenderer.class) {
            HashSet<String> hashSet = new HashSet<>();
            supportedFeatures = hashSet;
            hashSet.add("Structure");
            supportedFeatures.add("BasicStructure");
            supportedFeatures.add("ConditionalProcessing");
            supportedFeatures.add("Image");
            supportedFeatures.add("Style");
            supportedFeatures.add("ViewportAttribute");
            supportedFeatures.add("Shape");
            supportedFeatures.add("BasicText");
            supportedFeatures.add("PaintAttribute");
            supportedFeatures.add("BasicPaintAttribute");
            supportedFeatures.add("OpacityAttribute");
            supportedFeatures.add("BasicGraphicsAttribute");
            supportedFeatures.add("Marker");
            supportedFeatures.add("Gradient");
            supportedFeatures.add("Pattern");
            supportedFeatures.add("Clip");
            supportedFeatures.add("BasicClip");
            supportedFeatures.add("Mask");
            supportedFeatures.add("View");
        }
    }

    private boolean isSpecified(SVG.Style style, long j2) {
        return (style.specifiedFlags & j2) != 0;
    }

    private void makeLinearGradient(boolean z2, SVG.Box box, SVG.SvgLinearGradient svgLinearGradient) {
        float f2;
        float f3;
        float f4;
        float f5;
        SVG.Box box2 = box;
        SVG.SvgLinearGradient svgLinearGradient2 = svgLinearGradient;
        String str = svgLinearGradient2.href;
        if (str != null) {
            fillInChainedGradientFields((SVG.GradientElement) svgLinearGradient2, str);
        }
        Boolean bool = svgLinearGradient2.gradientUnitsAreUser;
        int i3 = 0;
        boolean z3 = bool != null && bool.booleanValue();
        RendererState rendererState = this.state;
        Paint paint = z2 ? rendererState.fillPaint : rendererState.strokePaint;
        if (z3) {
            SVG.Box currentViewPortInUserUnits = getCurrentViewPortInUserUnits();
            SVG.Length length = svgLinearGradient2.x1;
            float floatValueX = length != null ? length.floatValueX(this) : 0.0f;
            SVG.Length length2 = svgLinearGradient2.y1;
            float floatValueY = length2 != null ? length2.floatValueY(this) : 0.0f;
            SVG.Length length3 = svgLinearGradient2.x2;
            float floatValueX2 = length3 != null ? length3.floatValueX(this) : currentViewPortInUserUnits.width;
            SVG.Length length4 = svgLinearGradient2.y2;
            f3 = floatValueX2;
            f5 = floatValueX;
            f4 = floatValueY;
            f2 = length4 != null ? length4.floatValueY(this) : 0.0f;
        } else {
            SVG.Length length5 = svgLinearGradient2.x1;
            float floatValue = length5 != null ? length5.floatValue(this, 1.0f) : 0.0f;
            SVG.Length length6 = svgLinearGradient2.y1;
            float floatValue2 = length6 != null ? length6.floatValue(this, 1.0f) : 0.0f;
            SVG.Length length7 = svgLinearGradient2.x2;
            float floatValue3 = length7 != null ? length7.floatValue(this, 1.0f) : 1.0f;
            SVG.Length length8 = svgLinearGradient2.y2;
            f5 = floatValue;
            f2 = length8 != null ? length8.floatValue(this, 1.0f) : 0.0f;
            f4 = floatValue2;
            f3 = floatValue3;
        }
        statePush();
        this.state = findInheritFromAncestorState(svgLinearGradient2);
        Matrix matrix = new Matrix();
        if (!z3) {
            matrix.preTranslate(box2.minX, box2.minY);
            matrix.preScale(box2.width, box2.height);
        }
        Matrix matrix2 = svgLinearGradient2.gradientTransform;
        if (matrix2 != null) {
            matrix.preConcat(matrix2);
        }
        int size = svgLinearGradient2.children.size();
        if (size == 0) {
            statePop();
            if (z2) {
                this.state.hasFill = false;
            } else {
                this.state.hasStroke = false;
            }
        } else {
            int[] iArr = new int[size];
            float[] fArr = new float[size];
            Iterator<SVG.SvgObject> it = svgLinearGradient2.children.iterator();
            float f6 = -1.0f;
            while (it.hasNext()) {
                SVG.Stop stop = (SVG.Stop) it.next();
                Float f7 = stop.offset;
                float floatValue4 = f7 != null ? f7.floatValue() : 0.0f;
                if (i3 == 0 || floatValue4 >= f6) {
                    fArr[i3] = floatValue4;
                    f6 = floatValue4;
                } else {
                    fArr[i3] = f6;
                }
                statePush();
                updateStyleForElement(this.state, stop);
                SVG.Style style = this.state.style;
                SVG.Colour colour = (SVG.Colour) style.stopColor;
                if (colour == null) {
                    colour = SVG.Colour.BLACK;
                }
                iArr[i3] = colourWithOpacity(colour.colour, style.stopOpacity.floatValue());
                i3++;
                statePop();
            }
            if ((f5 == f3 && f4 == f2) || size == 1) {
                statePop();
                paint.setColor(iArr[size - 1]);
                return;
            }
            Shader.TileMode tileMode = Shader.TileMode.CLAMP;
            SVG.GradientSpread gradientSpread = svgLinearGradient2.spreadMethod;
            if (gradientSpread != null) {
                if (gradientSpread == SVG.GradientSpread.reflect) {
                    tileMode = Shader.TileMode.MIRROR;
                } else if (gradientSpread == SVG.GradientSpread.repeat) {
                    tileMode = Shader.TileMode.REPEAT;
                }
            }
            Shader.TileMode tileMode2 = tileMode;
            statePop();
            LinearGradient linearGradient = new LinearGradient(f5, f4, f3, f2, iArr, fArr, tileMode2);
            linearGradient.setLocalMatrix(matrix);
            paint.setShader(linearGradient);
            paint.setAlpha(clamp255(this.state.style.fillOpacity.floatValue()));
        }
    }

    private Path makePathAndBoundingBox(SVG.Line line) {
        SVG.Length length = line.x1;
        float f2 = 0.0f;
        float floatValueX = length == null ? 0.0f : length.floatValueX(this);
        SVG.Length length2 = line.y1;
        float floatValueY = length2 == null ? 0.0f : length2.floatValueY(this);
        SVG.Length length3 = line.x2;
        float floatValueX2 = length3 == null ? 0.0f : length3.floatValueX(this);
        SVG.Length length4 = line.y2;
        if (length4 != null) {
            f2 = length4.floatValueY(this);
        }
        if (line.boundingBox == null) {
            line.boundingBox = new SVG.Box(Math.min(floatValueX, floatValueX2), Math.min(floatValueY, f2), Math.abs(floatValueX2 - floatValueX), Math.abs(f2 - floatValueY));
        }
        Path path = new Path();
        path.moveTo(floatValueX, floatValueY);
        path.lineTo(floatValueX2, f2);
        return path;
    }

    private void makeRadialGradient(boolean z2, SVG.Box box, SVG.SvgRadialGradient svgRadialGradient) {
        float f2;
        float f3;
        float f4;
        SVG.Box box2 = box;
        SVG.SvgRadialGradient svgRadialGradient2 = svgRadialGradient;
        String str = svgRadialGradient2.href;
        if (str != null) {
            fillInChainedGradientFields((SVG.GradientElement) svgRadialGradient2, str);
        }
        Boolean bool = svgRadialGradient2.gradientUnitsAreUser;
        int i3 = 0;
        boolean z3 = bool != null && bool.booleanValue();
        RendererState rendererState = this.state;
        Paint paint = z2 ? rendererState.fillPaint : rendererState.strokePaint;
        if (z3) {
            SVG.Length length = new SVG.Length(50.0f, SVG.Unit.percent);
            SVG.Length length2 = svgRadialGradient2.cx;
            float floatValueX = length2 != null ? length2.floatValueX(this) : length.floatValueX(this);
            SVG.Length length3 = svgRadialGradient2.cy;
            float floatValueY = length3 != null ? length3.floatValueY(this) : length.floatValueY(this);
            SVG.Length length4 = svgRadialGradient2.f6532r;
            f2 = length4 != null ? length4.floatValue(this) : length.floatValue(this);
            f4 = floatValueX;
            f3 = floatValueY;
        } else {
            SVG.Length length5 = svgRadialGradient2.cx;
            float f5 = 0.5f;
            float floatValue = length5 != null ? length5.floatValue(this, 1.0f) : 0.5f;
            SVG.Length length6 = svgRadialGradient2.cy;
            float floatValue2 = length6 != null ? length6.floatValue(this, 1.0f) : 0.5f;
            SVG.Length length7 = svgRadialGradient2.f6532r;
            if (length7 != null) {
                f5 = length7.floatValue(this, 1.0f);
            }
            f4 = floatValue;
            f2 = f5;
            f3 = floatValue2;
        }
        statePush();
        this.state = findInheritFromAncestorState(svgRadialGradient2);
        Matrix matrix = new Matrix();
        if (!z3) {
            matrix.preTranslate(box2.minX, box2.minY);
            matrix.preScale(box2.width, box2.height);
        }
        Matrix matrix2 = svgRadialGradient2.gradientTransform;
        if (matrix2 != null) {
            matrix.preConcat(matrix2);
        }
        int size = svgRadialGradient2.children.size();
        if (size == 0) {
            statePop();
            if (z2) {
                this.state.hasFill = false;
            } else {
                this.state.hasStroke = false;
            }
        } else {
            int[] iArr = new int[size];
            float[] fArr = new float[size];
            Iterator<SVG.SvgObject> it = svgRadialGradient2.children.iterator();
            float f6 = -1.0f;
            while (true) {
                float f7 = 0.0f;
                if (!it.hasNext()) {
                    break;
                }
                SVG.Stop stop = (SVG.Stop) it.next();
                Float f8 = stop.offset;
                if (f8 != null) {
                    f7 = f8.floatValue();
                }
                if (i3 == 0 || f7 >= f6) {
                    fArr[i3] = f7;
                    f6 = f7;
                } else {
                    fArr[i3] = f6;
                }
                statePush();
                updateStyleForElement(this.state, stop);
                SVG.Style style = this.state.style;
                SVG.Colour colour = (SVG.Colour) style.stopColor;
                if (colour == null) {
                    colour = SVG.Colour.BLACK;
                }
                iArr[i3] = colourWithOpacity(colour.colour, style.stopOpacity.floatValue());
                i3++;
                statePop();
            }
            if (f2 == 0.0f || size == 1) {
                statePop();
                paint.setColor(iArr[size - 1]);
                return;
            }
            Shader.TileMode tileMode = Shader.TileMode.CLAMP;
            SVG.GradientSpread gradientSpread = svgRadialGradient2.spreadMethod;
            if (gradientSpread != null) {
                if (gradientSpread == SVG.GradientSpread.reflect) {
                    tileMode = Shader.TileMode.MIRROR;
                } else if (gradientSpread == SVG.GradientSpread.repeat) {
                    tileMode = Shader.TileMode.REPEAT;
                }
            }
            Shader.TileMode tileMode2 = tileMode;
            statePop();
            RadialGradient radialGradient = new RadialGradient(f4, f3, f2, iArr, fArr, tileMode2);
            radialGradient.setLocalMatrix(matrix);
            paint.setShader(radialGradient);
            paint.setAlpha(clamp255(this.state.style.fillOpacity.floatValue()));
        }
    }

    private SVG.Box makeViewPort(SVG.Length length, SVG.Length length2, SVG.Length length3, SVG.Length length4) {
        float f2 = 0.0f;
        float floatValueX = length != null ? length.floatValueX(this) : 0.0f;
        if (length2 != null) {
            f2 = length2.floatValueY(this);
        }
        SVG.Box currentViewPortInUserUnits = getCurrentViewPortInUserUnits();
        return new SVG.Box(floatValueX, f2, length3 != null ? length3.floatValueX(this) : currentViewPortInUserUnits.width, length4 != null ? length4.floatValueY(this) : currentViewPortInUserUnits.height);
    }

    @TargetApi(19)
    private Path objectToPath(SVG.SvgElement svgElement, boolean z2) {
        Path path;
        Path calculateClipPath;
        this.stateStack.push(this.state);
        RendererState rendererState = new RendererState(this.state);
        this.state = rendererState;
        updateStyleForElement(rendererState, svgElement);
        if (!display() || !visible()) {
            this.state = this.stateStack.pop();
            return null;
        }
        if (svgElement instanceof SVG.Use) {
            if (!z2) {
                error("<use> elements inside a <clipPath> cannot reference another <use>", new Object[0]);
            }
            SVG.Use use = (SVG.Use) svgElement;
            SVG.SvgObject resolveIRI = svgElement.document.resolveIRI(use.href);
            if (resolveIRI == null) {
                error("Use reference '%s' not found", use.href);
                this.state = this.stateStack.pop();
                return null;
            } else if (!(resolveIRI instanceof SVG.SvgElement)) {
                this.state = this.stateStack.pop();
                return null;
            } else {
                path = objectToPath((SVG.SvgElement) resolveIRI, false);
                if (path == null) {
                    return null;
                }
                if (use.boundingBox == null) {
                    use.boundingBox = calculatePathBounds(path);
                }
                Matrix matrix = use.transform;
                if (matrix != null) {
                    path.transform(matrix);
                }
            }
        } else if (svgElement instanceof SVG.GraphicsElement) {
            SVG.GraphicsElement graphicsElement = (SVG.GraphicsElement) svgElement;
            if (svgElement instanceof SVG.Path) {
                path = new PathConverter(((SVG.Path) svgElement).f6525d).getPath();
                if (svgElement.boundingBox == null) {
                    svgElement.boundingBox = calculatePathBounds(path);
                }
            } else {
                path = svgElement instanceof SVG.Rect ? makePathAndBoundingBox((SVG.Rect) svgElement) : svgElement instanceof SVG.Circle ? makePathAndBoundingBox((SVG.Circle) svgElement) : svgElement instanceof SVG.Ellipse ? makePathAndBoundingBox((SVG.Ellipse) svgElement) : svgElement instanceof SVG.PolyLine ? makePathAndBoundingBox((SVG.PolyLine) svgElement) : null;
            }
            if (path == null) {
                return null;
            }
            if (graphicsElement.boundingBox == null) {
                graphicsElement.boundingBox = calculatePathBounds(path);
            }
            Matrix matrix2 = graphicsElement.transform;
            if (matrix2 != null) {
                path.transform(matrix2);
            }
            path.setFillType(getClipRuleFromState());
        } else if (svgElement instanceof SVG.Text) {
            SVG.Text text = (SVG.Text) svgElement;
            path = makePathAndBoundingBox(text);
            if (path == null) {
                return null;
            }
            Matrix matrix3 = text.transform;
            if (matrix3 != null) {
                path.transform(matrix3);
            }
            path.setFillType(getClipRuleFromState());
        } else {
            error("Invalid %s element found in clipPath definition", svgElement.getNodeName());
            return null;
        }
        if (!(this.state.style.clipPath == null || (calculateClipPath = calculateClipPath(svgElement, svgElement.boundingBox)) == null)) {
            path.op(calculateClipPath, Path.Op.INTERSECT);
        }
        this.state = this.stateStack.pop();
        return path;
    }

    private void parentPop() {
        this.parentStack.pop();
        this.matrixStack.pop();
    }

    private void parentPush(SVG.SvgContainer svgContainer) {
        this.parentStack.push(svgContainer);
        this.matrixStack.push(this.canvas.getMatrix());
    }

    private void popLayer(SVG.SvgElement svgElement) {
        popLayer(svgElement, svgElement.boundingBox);
    }

    private void processTextChild(SVG.SvgObject svgObject, TextProcessor textProcessor) {
        float f2;
        float f3;
        float f4;
        SVG.Style.TextAnchor anchorPosition;
        if (textProcessor.doTextContainer((SVG.TextContainer) svgObject)) {
            if (svgObject instanceof SVG.TextPath) {
                statePush();
                renderTextPath((SVG.TextPath) svgObject);
                statePop();
            } else if (svgObject instanceof SVG.TSpan) {
                debug("TSpan render", new Object[0]);
                statePush();
                SVG.TSpan tSpan = (SVG.TSpan) svgObject;
                updateStyleForElement(this.state, tSpan);
                if (display()) {
                    List<SVG.Length> list = tSpan.f6533x;
                    boolean z2 = list != null && list.size() > 0;
                    boolean z3 = textProcessor instanceof PlainTextDrawer;
                    float f5 = 0.0f;
                    if (z3) {
                        float floatValueX = !z2 ? ((PlainTextDrawer) textProcessor).f6539x : tSpan.f6533x.get(0).floatValueX(this);
                        List<SVG.Length> list2 = tSpan.f6534y;
                        f3 = (list2 == null || list2.size() == 0) ? ((PlainTextDrawer) textProcessor).f6540y : tSpan.f6534y.get(0).floatValueY(this);
                        List<SVG.Length> list3 = tSpan.dx;
                        f2 = (list3 == null || list3.size() == 0) ? 0.0f : tSpan.dx.get(0).floatValueX(this);
                        List<SVG.Length> list4 = tSpan.dy;
                        if (!(list4 == null || list4.size() == 0)) {
                            f5 = tSpan.dy.get(0).floatValueY(this);
                        }
                        f4 = f5;
                        f5 = floatValueX;
                    } else {
                        f4 = 0.0f;
                        f3 = 0.0f;
                        f2 = 0.0f;
                    }
                    if (z2 && (anchorPosition = getAnchorPosition()) != SVG.Style.TextAnchor.Start) {
                        float calculateTextWidth = calculateTextWidth(tSpan);
                        if (anchorPosition == SVG.Style.TextAnchor.Middle) {
                            calculateTextWidth /= 2.0f;
                        }
                        f5 -= calculateTextWidth;
                    }
                    checkForGradientsAndPatterns((SVG.SvgElement) tSpan.getTextRoot());
                    if (z3) {
                        PlainTextDrawer plainTextDrawer = (PlainTextDrawer) textProcessor;
                        plainTextDrawer.f6539x = f5 + f2;
                        plainTextDrawer.f6540y = f3 + f4;
                    }
                    boolean pushLayer = pushLayer();
                    enumerateTextSpans(tSpan, textProcessor);
                    if (pushLayer) {
                        popLayer(tSpan);
                    }
                }
                statePop();
            } else if (svgObject instanceof SVG.TRef) {
                statePush();
                SVG.TRef tRef = (SVG.TRef) svgObject;
                updateStyleForElement(this.state, tRef);
                if (display()) {
                    checkForGradientsAndPatterns((SVG.SvgElement) tRef.getTextRoot());
                    SVG.SvgObject resolveIRI = svgObject.document.resolveIRI(tRef.href);
                    if (resolveIRI == null || !(resolveIRI instanceof SVG.TextContainer)) {
                        error("Tref reference '%s' not found", tRef.href);
                    } else {
                        StringBuilder sb = new StringBuilder();
                        extractRawText((SVG.TextContainer) resolveIRI, sb);
                        if (sb.length() > 0) {
                            textProcessor.processText(sb.toString());
                        }
                    }
                }
                statePop();
            }
        }
    }

    private boolean pushLayer() {
        SVG.SvgObject resolveIRI;
        if (!requiresCompositing()) {
            return false;
        }
        this.canvas.saveLayerAlpha((RectF) null, clamp255(this.state.style.opacity.floatValue()), 31);
        this.stateStack.push(this.state);
        RendererState rendererState = new RendererState(this.state);
        this.state = rendererState;
        String str = rendererState.style.mask;
        if (str != null && ((resolveIRI = this.document.resolveIRI(str)) == null || !(resolveIRI instanceof SVG.Mask))) {
            error("Mask reference '%s' not found", this.state.style.mask);
            this.state.style.mask = null;
        }
        return true;
    }

    private MarkerVector realignMarkerMid(MarkerVector markerVector, MarkerVector markerVector2, MarkerVector markerVector3) {
        float dotProduct = dotProduct(markerVector2.dx, markerVector2.dy, markerVector2.f6537x - markerVector.f6537x, markerVector2.f6538y - markerVector.f6538y);
        if (dotProduct == 0.0f) {
            dotProduct = dotProduct(markerVector2.dx, markerVector2.dy, markerVector3.f6537x - markerVector2.f6537x, markerVector3.f6538y - markerVector2.f6538y);
        }
        int i3 = (dotProduct > 0.0f ? 1 : (dotProduct == 0.0f ? 0 : -1));
        if (i3 > 0) {
            return markerVector2;
        }
        if (i3 == 0 && (markerVector2.dx > 0.0f || markerVector2.dy >= 0.0f)) {
            return markerVector2;
        }
        markerVector2.dx = -markerVector2.dx;
        markerVector2.dy = -markerVector2.dy;
        return markerVector2;
    }

    private void render(SVG.SvgObject svgObject) {
        if (!(svgObject instanceof SVG.NotDirectlyRendered)) {
            statePush();
            checkXMLSpaceAttribute(svgObject);
            if (svgObject instanceof SVG.Svg) {
                render((SVG.Svg) svgObject);
            } else if (svgObject instanceof SVG.Use) {
                render((SVG.Use) svgObject);
            } else if (svgObject instanceof SVG.Switch) {
                render((SVG.Switch) svgObject);
            } else if (svgObject instanceof SVG.Group) {
                render((SVG.Group) svgObject);
            } else if (svgObject instanceof SVG.Image) {
                render((SVG.Image) svgObject);
            } else if (svgObject instanceof SVG.Path) {
                render((SVG.Path) svgObject);
            } else if (svgObject instanceof SVG.Rect) {
                render((SVG.Rect) svgObject);
            } else if (svgObject instanceof SVG.Circle) {
                render((SVG.Circle) svgObject);
            } else if (svgObject instanceof SVG.Ellipse) {
                render((SVG.Ellipse) svgObject);
            } else if (svgObject instanceof SVG.Line) {
                render((SVG.Line) svgObject);
            } else if (svgObject instanceof SVG.Polygon) {
                render((SVG.Polygon) svgObject);
            } else if (svgObject instanceof SVG.PolyLine) {
                render((SVG.PolyLine) svgObject);
            } else if (svgObject instanceof SVG.Text) {
                render((SVG.Text) svgObject);
            }
            statePop();
        }
    }

    private void renderChildren(SVG.SvgContainer svgContainer, boolean z2) {
        if (z2) {
            parentPush(svgContainer);
        }
        for (SVG.SvgObject render : svgContainer.getChildren()) {
            render(render);
        }
        if (z2) {
            parentPop();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00df, code lost:
        r0 = 0.0f - r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00e6, code lost:
        r7 = r8[r7.getAlignment().ordinal()];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00f1, code lost:
        if (r7 == 2) goto L_0x0108;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00f4, code lost:
        if (r7 == 3) goto L_0x0104;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00f7, code lost:
        if (r7 == 5) goto L_0x0108;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00fa, code lost:
        if (r7 == 6) goto L_0x0104;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00fd, code lost:
        if (r7 == 7) goto L_0x0108;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0101, code lost:
        if (r7 == 8) goto L_0x0104;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0104, code lost:
        r13 = r4 - r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0106, code lost:
        r1 = 0.0f - r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0108, code lost:
        r13 = (r4 - r13) / 2.0f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0116, code lost:
        if (r11.state.style.overflow.booleanValue() != false) goto L_0x011b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0118, code lost:
        setClipRect(r0, r1, r2, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x011b, code lost:
        r3.reset();
        r3.preScale(r6, r5);
        r11.canvas.concat(r3);
     */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0127  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x014a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void renderMarker(com.caverock.androidsvg.SVG.Marker r12, com.caverock.androidsvg.SVGAndroidRenderer.MarkerVector r13) {
        /*
            r11 = this;
            r11.statePush()
            java.lang.Float r0 = r12.orient
            r1 = 0
            if (r0 == 0) goto L_0x0033
            float r0 = r0.floatValue()
            boolean r0 = java.lang.Float.isNaN(r0)
            if (r0 == 0) goto L_0x002c
            float r0 = r13.dx
            int r2 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r2 != 0) goto L_0x001e
            float r2 = r13.dy
            int r2 = (r2 > r1 ? 1 : (r2 == r1 ? 0 : -1))
            if (r2 == 0) goto L_0x0033
        L_0x001e:
            float r2 = r13.dy
            double r2 = (double) r2
            double r4 = (double) r0
            double r2 = java.lang.Math.atan2(r2, r4)
            double r2 = java.lang.Math.toDegrees(r2)
            float r0 = (float) r2
            goto L_0x0034
        L_0x002c:
            java.lang.Float r0 = r12.orient
            float r0 = r0.floatValue()
            goto L_0x0034
        L_0x0033:
            r0 = r1
        L_0x0034:
            boolean r2 = r12.markerUnitsAreUser
            if (r2 == 0) goto L_0x003b
            r2 = 1065353216(0x3f800000, float:1.0)
            goto L_0x0047
        L_0x003b:
            com.caverock.androidsvg.SVGAndroidRenderer$RendererState r2 = r11.state
            com.caverock.androidsvg.SVG$Style r2 = r2.style
            com.caverock.androidsvg.SVG$Length r2 = r2.strokeWidth
            float r3 = r11.dpi
            float r2 = r2.floatValue((float) r3)
        L_0x0047:
            com.caverock.androidsvg.SVGAndroidRenderer$RendererState r3 = r11.findInheritFromAncestorState(r12)
            r11.state = r3
            android.graphics.Matrix r3 = new android.graphics.Matrix
            r3.<init>()
            float r4 = r13.f6537x
            float r13 = r13.f6538y
            r3.preTranslate(r4, r13)
            r3.preRotate(r0)
            r3.preScale(r2, r2)
            com.caverock.androidsvg.SVG$Length r13 = r12.refX
            if (r13 == 0) goto L_0x0068
            float r13 = r13.floatValueX(r11)
            goto L_0x0069
        L_0x0068:
            r13 = r1
        L_0x0069:
            com.caverock.androidsvg.SVG$Length r0 = r12.refY
            if (r0 == 0) goto L_0x0072
            float r0 = r0.floatValueY(r11)
            goto L_0x0073
        L_0x0072:
            r0 = r1
        L_0x0073:
            com.caverock.androidsvg.SVG$Length r2 = r12.markerWidth
            r4 = 1077936128(0x40400000, float:3.0)
            if (r2 == 0) goto L_0x007e
            float r2 = r2.floatValueX(r11)
            goto L_0x007f
        L_0x007e:
            r2 = r4
        L_0x007f:
            com.caverock.androidsvg.SVG$Length r5 = r12.markerHeight
            if (r5 == 0) goto L_0x0087
            float r4 = r5.floatValueY(r11)
        L_0x0087:
            com.caverock.androidsvg.SVG$Box r5 = r12.viewBox
            if (r5 == 0) goto L_0x0127
            float r6 = r5.width
            float r6 = r2 / r6
            float r5 = r5.height
            float r5 = r4 / r5
            com.caverock.androidsvg.PreserveAspectRatio r7 = r12.preserveAspectRatio
            if (r7 == 0) goto L_0x0098
            goto L_0x009a
        L_0x0098:
            com.caverock.androidsvg.PreserveAspectRatio r7 = com.caverock.androidsvg.PreserveAspectRatio.LETTERBOX
        L_0x009a:
            com.caverock.androidsvg.PreserveAspectRatio r8 = com.caverock.androidsvg.PreserveAspectRatio.STRETCH
            boolean r8 = r7.equals(r8)
            if (r8 != 0) goto L_0x00b6
            com.caverock.androidsvg.PreserveAspectRatio$Scale r8 = r7.getScale()
            com.caverock.androidsvg.PreserveAspectRatio$Scale r9 = com.caverock.androidsvg.PreserveAspectRatio.Scale.slice
            if (r8 != r9) goto L_0x00b0
            float r5 = java.lang.Math.max(r6, r5)
        L_0x00ae:
            r6 = r5
            goto L_0x00b5
        L_0x00b0:
            float r5 = java.lang.Math.min(r6, r5)
            goto L_0x00ae
        L_0x00b5:
            r5 = r6
        L_0x00b6:
            float r13 = -r13
            float r13 = r13 * r6
            float r0 = -r0
            float r0 = r0 * r5
            r3.preTranslate(r13, r0)
            android.graphics.Canvas r13 = r11.canvas
            r13.concat(r3)
            com.caverock.androidsvg.SVG$Box r13 = r12.viewBox
            float r0 = r13.width
            float r0 = r0 * r6
            float r13 = r13.height
            float r13 = r13 * r5
            int[] r8 = com.caverock.androidsvg.SVGAndroidRenderer.AnonymousClass1.$SwitchMap$com$caverock$androidsvg$PreserveAspectRatio$Alignment
            com.caverock.androidsvg.PreserveAspectRatio$Alignment r9 = r7.getAlignment()
            int r9 = r9.ordinal()
            r9 = r8[r9]
            r10 = 1073741824(0x40000000, float:2.0)
            switch(r9) {
                case 1: goto L_0x00e2;
                case 2: goto L_0x00e2;
                case 3: goto L_0x00e2;
                case 4: goto L_0x00dd;
                case 5: goto L_0x00dd;
                case 6: goto L_0x00dd;
                default: goto L_0x00db;
            }
        L_0x00db:
            r0 = r1
            goto L_0x00e6
        L_0x00dd:
            float r0 = r2 - r0
        L_0x00df:
            float r0 = r1 - r0
            goto L_0x00e6
        L_0x00e2:
            float r0 = r2 - r0
            float r0 = r0 / r10
            goto L_0x00df
        L_0x00e6:
            com.caverock.androidsvg.PreserveAspectRatio$Alignment r7 = r7.getAlignment()
            int r7 = r7.ordinal()
            r7 = r8[r7]
            r8 = 2
            if (r7 == r8) goto L_0x0108
            r8 = 3
            if (r7 == r8) goto L_0x0104
            r8 = 5
            if (r7 == r8) goto L_0x0108
            r8 = 6
            if (r7 == r8) goto L_0x0104
            r8 = 7
            if (r7 == r8) goto L_0x0108
            r8 = 8
            if (r7 == r8) goto L_0x0104
            goto L_0x010c
        L_0x0104:
            float r13 = r4 - r13
        L_0x0106:
            float r1 = r1 - r13
            goto L_0x010c
        L_0x0108:
            float r13 = r4 - r13
            float r13 = r13 / r10
            goto L_0x0106
        L_0x010c:
            com.caverock.androidsvg.SVGAndroidRenderer$RendererState r13 = r11.state
            com.caverock.androidsvg.SVG$Style r13 = r13.style
            java.lang.Boolean r13 = r13.overflow
            boolean r13 = r13.booleanValue()
            if (r13 != 0) goto L_0x011b
            r11.setClipRect(r0, r1, r2, r4)
        L_0x011b:
            r3.reset()
            r3.preScale(r6, r5)
            android.graphics.Canvas r13 = r11.canvas
            r13.concat(r3)
            goto L_0x0140
        L_0x0127:
            float r13 = -r13
            float r0 = -r0
            r3.preTranslate(r13, r0)
            android.graphics.Canvas r13 = r11.canvas
            r13.concat(r3)
            com.caverock.androidsvg.SVGAndroidRenderer$RendererState r13 = r11.state
            com.caverock.androidsvg.SVG$Style r13 = r13.style
            java.lang.Boolean r13 = r13.overflow
            boolean r13 = r13.booleanValue()
            if (r13 != 0) goto L_0x0140
            r11.setClipRect(r1, r1, r2, r4)
        L_0x0140:
            boolean r13 = r11.pushLayer()
            r0 = 0
            r11.renderChildren(r12, r0)
            if (r13 == 0) goto L_0x014d
            r11.popLayer(r12)
        L_0x014d:
            r11.statePop()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.caverock.androidsvg.SVGAndroidRenderer.renderMarker(com.caverock.androidsvg.SVG$Marker, com.caverock.androidsvg.SVGAndroidRenderer$MarkerVector):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x009d A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x009e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void renderMarkers(com.caverock.androidsvg.SVG.GraphicsElement r10) {
        /*
            r9 = this;
            com.caverock.androidsvg.SVGAndroidRenderer$RendererState r0 = r9.state
            com.caverock.androidsvg.SVG$Style r0 = r0.style
            java.lang.String r1 = r0.markerStart
            if (r1 != 0) goto L_0x0011
            java.lang.String r2 = r0.markerMid
            if (r2 != 0) goto L_0x0011
            java.lang.String r0 = r0.markerEnd
            if (r0 != 0) goto L_0x0011
            return
        L_0x0011:
            java.lang.String r0 = "Marker reference '%s' not found"
            r2 = 0
            if (r1 == 0) goto L_0x002e
            com.caverock.androidsvg.SVG r3 = r10.document
            com.caverock.androidsvg.SVG$SvgObject r1 = r3.resolveIRI(r1)
            if (r1 == 0) goto L_0x0021
            com.caverock.androidsvg.SVG$Marker r1 = (com.caverock.androidsvg.SVG.Marker) r1
            goto L_0x002f
        L_0x0021:
            com.caverock.androidsvg.SVGAndroidRenderer$RendererState r1 = r9.state
            com.caverock.androidsvg.SVG$Style r1 = r1.style
            java.lang.String r1 = r1.markerStart
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            error(r0, r1)
        L_0x002e:
            r1 = r2
        L_0x002f:
            com.caverock.androidsvg.SVGAndroidRenderer$RendererState r3 = r9.state
            com.caverock.androidsvg.SVG$Style r3 = r3.style
            java.lang.String r3 = r3.markerMid
            if (r3 == 0) goto L_0x004f
            com.caverock.androidsvg.SVG r4 = r10.document
            com.caverock.androidsvg.SVG$SvgObject r3 = r4.resolveIRI(r3)
            if (r3 == 0) goto L_0x0042
            com.caverock.androidsvg.SVG$Marker r3 = (com.caverock.androidsvg.SVG.Marker) r3
            goto L_0x0050
        L_0x0042:
            com.caverock.androidsvg.SVGAndroidRenderer$RendererState r3 = r9.state
            com.caverock.androidsvg.SVG$Style r3 = r3.style
            java.lang.String r3 = r3.markerMid
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            error(r0, r3)
        L_0x004f:
            r3 = r2
        L_0x0050:
            com.caverock.androidsvg.SVGAndroidRenderer$RendererState r4 = r9.state
            com.caverock.androidsvg.SVG$Style r4 = r4.style
            java.lang.String r4 = r4.markerEnd
            if (r4 == 0) goto L_0x0070
            com.caverock.androidsvg.SVG r5 = r10.document
            com.caverock.androidsvg.SVG$SvgObject r4 = r5.resolveIRI(r4)
            if (r4 == 0) goto L_0x0063
            com.caverock.androidsvg.SVG$Marker r4 = (com.caverock.androidsvg.SVG.Marker) r4
            goto L_0x0071
        L_0x0063:
            com.caverock.androidsvg.SVGAndroidRenderer$RendererState r4 = r9.state
            com.caverock.androidsvg.SVG$Style r4 = r4.style
            java.lang.String r4 = r4.markerEnd
            java.lang.Object[] r4 = new java.lang.Object[]{r4}
            error(r0, r4)
        L_0x0070:
            r4 = r2
        L_0x0071:
            boolean r0 = r10 instanceof com.caverock.androidsvg.SVG.Path
            if (r0 == 0) goto L_0x0083
            com.caverock.androidsvg.SVGAndroidRenderer$MarkerPositionCalculator r0 = new com.caverock.androidsvg.SVGAndroidRenderer$MarkerPositionCalculator
            com.caverock.androidsvg.SVG$Path r10 = (com.caverock.androidsvg.SVG.Path) r10
            com.caverock.androidsvg.SVG$PathDefinition r10 = r10.f6525d
            r0.<init>(r10)
            java.util.List r10 = r0.getMarkers()
            goto L_0x0094
        L_0x0083:
            boolean r0 = r10 instanceof com.caverock.androidsvg.SVG.Line
            if (r0 == 0) goto L_0x008e
            com.caverock.androidsvg.SVG$Line r10 = (com.caverock.androidsvg.SVG.Line) r10
            java.util.List r10 = r9.calculateMarkerPositions((com.caverock.androidsvg.SVG.Line) r10)
            goto L_0x0094
        L_0x008e:
            com.caverock.androidsvg.SVG$PolyLine r10 = (com.caverock.androidsvg.SVG.PolyLine) r10
            java.util.List r10 = r9.calculateMarkerPositions((com.caverock.androidsvg.SVG.PolyLine) r10)
        L_0x0094:
            if (r10 != 0) goto L_0x0097
            return
        L_0x0097:
            int r0 = r10.size()
            if (r0 != 0) goto L_0x009e
            return
        L_0x009e:
            com.caverock.androidsvg.SVGAndroidRenderer$RendererState r5 = r9.state
            com.caverock.androidsvg.SVG$Style r5 = r5.style
            r5.markerEnd = r2
            r5.markerMid = r2
            r5.markerStart = r2
            r2 = 0
            if (r1 == 0) goto L_0x00b4
            java.lang.Object r5 = r10.get(r2)
            com.caverock.androidsvg.SVGAndroidRenderer$MarkerVector r5 = (com.caverock.androidsvg.SVGAndroidRenderer.MarkerVector) r5
            r9.renderMarker(r1, r5)
        L_0x00b4:
            r1 = 1
            if (r3 == 0) goto L_0x00e6
            int r5 = r10.size()
            r6 = 2
            if (r5 <= r6) goto L_0x00e6
            java.lang.Object r2 = r10.get(r2)
            com.caverock.androidsvg.SVGAndroidRenderer$MarkerVector r2 = (com.caverock.androidsvg.SVGAndroidRenderer.MarkerVector) r2
            java.lang.Object r5 = r10.get(r1)
            com.caverock.androidsvg.SVGAndroidRenderer$MarkerVector r5 = (com.caverock.androidsvg.SVGAndroidRenderer.MarkerVector) r5
            r6 = r1
        L_0x00cb:
            int r7 = r0 + -1
            if (r6 >= r7) goto L_0x00e6
            int r6 = r6 + 1
            java.lang.Object r7 = r10.get(r6)
            com.caverock.androidsvg.SVGAndroidRenderer$MarkerVector r7 = (com.caverock.androidsvg.SVGAndroidRenderer.MarkerVector) r7
            boolean r8 = r5.isAmbiguous
            if (r8 == 0) goto L_0x00e0
            com.caverock.androidsvg.SVGAndroidRenderer$MarkerVector r2 = r9.realignMarkerMid(r2, r5, r7)
            goto L_0x00e1
        L_0x00e0:
            r2 = r5
        L_0x00e1:
            r9.renderMarker(r3, r2)
            r5 = r7
            goto L_0x00cb
        L_0x00e6:
            if (r4 == 0) goto L_0x00f2
            int r0 = r0 - r1
            java.lang.Object r10 = r10.get(r0)
            com.caverock.androidsvg.SVGAndroidRenderer$MarkerVector r10 = (com.caverock.androidsvg.SVGAndroidRenderer.MarkerVector) r10
            r9.renderMarker(r4, r10)
        L_0x00f2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.caverock.androidsvg.SVGAndroidRenderer.renderMarkers(com.caverock.androidsvg.SVG$GraphicsElement):void");
    }

    private void renderMask(SVG.Mask mask, SVG.SvgElement svgElement, SVG.Box box) {
        float f2;
        float f3;
        debug("Mask render", new Object[0]);
        Boolean bool = mask.maskUnitsAreUser;
        if (bool == null || !bool.booleanValue()) {
            SVG.Length length = mask.width;
            float f4 = 1.2f;
            float floatValue = length != null ? length.floatValue(this, 1.0f) : 1.2f;
            SVG.Length length2 = mask.height;
            if (length2 != null) {
                f4 = length2.floatValue(this, 1.0f);
            }
            f3 = floatValue * box.width;
            f2 = f4 * box.height;
        } else {
            SVG.Length length3 = mask.width;
            f3 = length3 != null ? length3.floatValueX(this) : box.width;
            SVG.Length length4 = mask.height;
            f2 = length4 != null ? length4.floatValueY(this) : box.height;
        }
        if (f3 != 0.0f && f2 != 0.0f) {
            statePush();
            RendererState findInheritFromAncestorState = findInheritFromAncestorState(mask);
            this.state = findInheritFromAncestorState;
            findInheritFromAncestorState.style.opacity = Float.valueOf(1.0f);
            boolean pushLayer = pushLayer();
            this.canvas.save();
            Boolean bool2 = mask.maskContentUnitsAreUser;
            if (bool2 != null && !bool2.booleanValue()) {
                this.canvas.translate(box.minX, box.minY);
                this.canvas.scale(box.width, box.height);
            }
            renderChildren(mask, false);
            this.canvas.restore();
            if (pushLayer) {
                popLayer(svgElement, box);
            }
            statePop();
        }
    }

    private void renderSwitchChild(SVG.Switch switchR) {
        Set<String> systemLanguage;
        String language = Locale.getDefault().getLanguage();
        SVGExternalFileResolver fileResolver = SVG.getFileResolver();
        for (SVG.SvgObject next : switchR.getChildren()) {
            if (next instanceof SVG.SvgConditional) {
                SVG.SvgConditional svgConditional = (SVG.SvgConditional) next;
                if (svgConditional.getRequiredExtensions() == null && ((systemLanguage = svgConditional.getSystemLanguage()) == null || (!systemLanguage.isEmpty() && systemLanguage.contains(language)))) {
                    Set<String> requiredFeatures = svgConditional.getRequiredFeatures();
                    if (requiredFeatures != null) {
                        if (supportedFeatures == null) {
                            initialiseSupportedFeaturesMap();
                        }
                        if (requiredFeatures.isEmpty()) {
                            continue;
                        } else if (!supportedFeatures.containsAll(requiredFeatures)) {
                            continue;
                        }
                    }
                    Set<String> requiredFormats = svgConditional.getRequiredFormats();
                    if (requiredFormats != null) {
                        if (!requiredFormats.isEmpty() && fileResolver != null) {
                            Iterator<String> it = requiredFormats.iterator();
                            while (true) {
                                if (it.hasNext()) {
                                    if (!fileResolver.isFormatSupported(it.next())) {
                                        break;
                                    }
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                    Set<String> requiredFonts = svgConditional.getRequiredFonts();
                    if (requiredFonts != null) {
                        if (!requiredFonts.isEmpty() && fileResolver != null) {
                            for (String resolveFont : requiredFonts) {
                                if (fileResolver.resolveFont(resolveFont, this.state.style.fontWeight.intValue(), String.valueOf(this.state.style.fontStyle)) == null) {
                                }
                            }
                        }
                    }
                    render(next);
                    return;
                }
            }
        }
    }

    private void renderTextPath(SVG.TextPath textPath) {
        debug("TextPath render", new Object[0]);
        updateStyleForElement(this.state, textPath);
        if (display() && visible()) {
            SVG.SvgObject resolveIRI = textPath.document.resolveIRI(textPath.href);
            if (resolveIRI == null) {
                error("TextPath reference '%s' not found", textPath.href);
                return;
            }
            SVG.Path path = (SVG.Path) resolveIRI;
            Path path2 = new PathConverter(path.f6525d).getPath();
            Matrix matrix = path.transform;
            if (matrix != null) {
                path2.transform(matrix);
            }
            PathMeasure pathMeasure = new PathMeasure(path2, false);
            SVG.Length length = textPath.startOffset;
            float floatValue = length != null ? length.floatValue(this, pathMeasure.getLength()) : 0.0f;
            SVG.Style.TextAnchor anchorPosition = getAnchorPosition();
            if (anchorPosition != SVG.Style.TextAnchor.Start) {
                float calculateTextWidth = calculateTextWidth(textPath);
                if (anchorPosition == SVG.Style.TextAnchor.Middle) {
                    calculateTextWidth /= 2.0f;
                }
                floatValue -= calculateTextWidth;
            }
            checkForGradientsAndPatterns((SVG.SvgElement) textPath.getTextRoot());
            boolean pushLayer = pushLayer();
            enumerateTextSpans(textPath, new PathTextDrawer(path2, floatValue, 0.0f));
            if (pushLayer) {
                popLayer(textPath);
            }
        }
    }

    private boolean requiresCompositing() {
        return this.state.style.opacity.floatValue() < 1.0f || this.state.style.mask != null;
    }

    private void resetState() {
        this.state = new RendererState();
        this.stateStack = new Stack<>();
        updateStyle(this.state, SVG.Style.getDefaultStyle());
        RendererState rendererState = this.state;
        rendererState.viewPort = null;
        rendererState.spacePreserve = false;
        this.stateStack.push(new RendererState(rendererState));
        this.matrixStack = new Stack<>();
        this.parentStack = new Stack<>();
    }

    private void setClipRect(float f2, float f3, float f4, float f5) {
        float f6 = f4 + f2;
        float f7 = f5 + f3;
        SVG.CSSClipRect cSSClipRect = this.state.style.clip;
        if (cSSClipRect != null) {
            f2 += cSSClipRect.left.floatValueX(this);
            f3 += this.state.style.clip.top.floatValueY(this);
            f6 -= this.state.style.clip.right.floatValueX(this);
            f7 -= this.state.style.clip.bottom.floatValueY(this);
        }
        this.canvas.clipRect(f2, f3, f6, f7);
    }

    private void setPaintColour(RendererState rendererState, boolean z2, SVG.SvgPaint svgPaint) {
        int i3;
        SVG.Style style = rendererState.style;
        float floatValue = (z2 ? style.fillOpacity : style.strokeOpacity).floatValue();
        if (svgPaint instanceof SVG.Colour) {
            i3 = ((SVG.Colour) svgPaint).colour;
        } else if (svgPaint instanceof SVG.CurrentColor) {
            i3 = rendererState.style.color.colour;
        } else {
            return;
        }
        int colourWithOpacity = colourWithOpacity(i3, floatValue);
        if (z2) {
            rendererState.fillPaint.setColor(colourWithOpacity);
        } else {
            rendererState.strokePaint.setColor(colourWithOpacity);
        }
    }

    private void setSolidColor(boolean z2, SVG.SolidColor solidColor) {
        boolean z3 = false;
        if (z2) {
            if (isSpecified(solidColor.baseStyle, 2147483648L)) {
                RendererState rendererState = this.state;
                SVG.Style style = rendererState.style;
                SVG.SvgPaint svgPaint = solidColor.baseStyle.solidColor;
                style.fill = svgPaint;
                if (svgPaint != null) {
                    z3 = true;
                }
                rendererState.hasFill = z3;
            }
            if (isSpecified(solidColor.baseStyle, 4294967296L)) {
                this.state.style.fillOpacity = solidColor.baseStyle.solidOpacity;
            }
            if (isSpecified(solidColor.baseStyle, 6442450944L)) {
                RendererState rendererState2 = this.state;
                setPaintColour(rendererState2, z2, rendererState2.style.fill);
                return;
            }
            return;
        }
        if (isSpecified(solidColor.baseStyle, 2147483648L)) {
            RendererState rendererState3 = this.state;
            SVG.Style style2 = rendererState3.style;
            SVG.SvgPaint svgPaint2 = solidColor.baseStyle.solidColor;
            style2.stroke = svgPaint2;
            if (svgPaint2 != null) {
                z3 = true;
            }
            rendererState3.hasStroke = z3;
        }
        if (isSpecified(solidColor.baseStyle, 4294967296L)) {
            this.state.style.strokeOpacity = solidColor.baseStyle.solidOpacity;
        }
        if (isSpecified(solidColor.baseStyle, 6442450944L)) {
            RendererState rendererState4 = this.state;
            setPaintColour(rendererState4, z2, rendererState4.style.stroke);
        }
    }

    private void statePop() {
        this.canvas.restore();
        this.state = this.stateStack.pop();
    }

    private void statePush() {
        this.canvas.save();
        this.stateStack.push(this.state);
        this.state = new RendererState(this.state);
    }

    private String textXMLSpaceTransform(String str, boolean z2, boolean z3) {
        if (this.state.spacePreserve) {
            return str.replaceAll("[\\n\\t]", StringUtils.SPACE);
        }
        String replaceAll = str.replaceAll("\\n", "").replaceAll("\\t", StringUtils.SPACE);
        if (z2) {
            replaceAll = replaceAll.replaceAll("^\\s+", "");
        }
        if (z3) {
            replaceAll = replaceAll.replaceAll("\\s+$", "");
        }
        return replaceAll.replaceAll("\\s{2,}", StringUtils.SPACE);
    }

    private void updateParentBoundingBox(SVG.SvgElement svgElement) {
        if (svgElement.parent != null && svgElement.boundingBox != null) {
            Matrix matrix = new Matrix();
            if (this.matrixStack.peek().invert(matrix)) {
                SVG.Box box = svgElement.boundingBox;
                float f2 = box.minX;
                float f3 = box.minY;
                float maxX = box.maxX();
                SVG.Box box2 = svgElement.boundingBox;
                float f4 = box2.minY;
                float maxX2 = box2.maxX();
                float maxY = svgElement.boundingBox.maxY();
                SVG.Box box3 = svgElement.boundingBox;
                float[] fArr = {f2, f3, maxX, f4, maxX2, maxY, box3.minX, box3.maxY()};
                matrix.preConcat(this.canvas.getMatrix());
                matrix.mapPoints(fArr);
                float f5 = fArr[0];
                float f6 = fArr[1];
                RectF rectF = new RectF(f5, f6, f5, f6);
                for (int i3 = 2; i3 <= 6; i3 += 2) {
                    float f7 = fArr[i3];
                    if (f7 < rectF.left) {
                        rectF.left = f7;
                    }
                    if (f7 > rectF.right) {
                        rectF.right = f7;
                    }
                    float f8 = fArr[i3 + 1];
                    if (f8 < rectF.top) {
                        rectF.top = f8;
                    }
                    if (f8 > rectF.bottom) {
                        rectF.bottom = f8;
                    }
                }
                SVG.SvgElement svgElement2 = (SVG.SvgElement) this.parentStack.peek();
                SVG.Box box4 = svgElement2.boundingBox;
                if (box4 == null) {
                    svgElement2.boundingBox = SVG.Box.fromLimits(rectF.left, rectF.top, rectF.right, rectF.bottom);
                } else {
                    box4.union(SVG.Box.fromLimits(rectF.left, rectF.top, rectF.right, rectF.bottom));
                }
            }
        }
    }

    private void updateStyle(RendererState rendererState, SVG.Style style) {
        if (isSpecified(style, PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM)) {
            rendererState.style.color = style.color;
        }
        if (isSpecified(style, 2048)) {
            rendererState.style.opacity = style.opacity;
        }
        boolean z2 = false;
        if (isSpecified(style, 1)) {
            rendererState.style.fill = style.fill;
            SVG.SvgPaint svgPaint = style.fill;
            rendererState.hasFill = (svgPaint == null || svgPaint == SVG.Colour.TRANSPARENT) ? false : true;
        }
        if (isSpecified(style, 4)) {
            rendererState.style.fillOpacity = style.fillOpacity;
        }
        if (isSpecified(style, 6149)) {
            setPaintColour(rendererState, true, rendererState.style.fill);
        }
        if (isSpecified(style, 2)) {
            rendererState.style.fillRule = style.fillRule;
        }
        if (isSpecified(style, 8)) {
            rendererState.style.stroke = style.stroke;
            SVG.SvgPaint svgPaint2 = style.stroke;
            rendererState.hasStroke = (svgPaint2 == null || svgPaint2 == SVG.Colour.TRANSPARENT) ? false : true;
        }
        if (isSpecified(style, 16)) {
            rendererState.style.strokeOpacity = style.strokeOpacity;
        }
        if (isSpecified(style, 6168)) {
            setPaintColour(rendererState, false, rendererState.style.stroke);
        }
        if (isSpecified(style, 34359738368L)) {
            rendererState.style.vectorEffect = style.vectorEffect;
        }
        if (isSpecified(style, 32)) {
            SVG.Style style2 = rendererState.style;
            SVG.Length length = style.strokeWidth;
            style2.strokeWidth = length;
            rendererState.strokePaint.setStrokeWidth(length.floatValue(this));
        }
        if (isSpecified(style, 64)) {
            rendererState.style.strokeLineCap = style.strokeLineCap;
            int i3 = AnonymousClass1.$SwitchMap$com$caverock$androidsvg$SVG$Style$LineCap[style.strokeLineCap.ordinal()];
            if (i3 == 1) {
                rendererState.strokePaint.setStrokeCap(Paint.Cap.BUTT);
            } else if (i3 == 2) {
                rendererState.strokePaint.setStrokeCap(Paint.Cap.ROUND);
            } else if (i3 == 3) {
                rendererState.strokePaint.setStrokeCap(Paint.Cap.SQUARE);
            }
        }
        if (isSpecified(style, 128)) {
            rendererState.style.strokeLineJoin = style.strokeLineJoin;
            int i4 = AnonymousClass1.$SwitchMap$com$caverock$androidsvg$SVG$Style$LineJoin[style.strokeLineJoin.ordinal()];
            if (i4 == 1) {
                rendererState.strokePaint.setStrokeJoin(Paint.Join.MITER);
            } else if (i4 == 2) {
                rendererState.strokePaint.setStrokeJoin(Paint.Join.ROUND);
            } else if (i4 == 3) {
                rendererState.strokePaint.setStrokeJoin(Paint.Join.BEVEL);
            }
        }
        if (isSpecified(style, 256)) {
            rendererState.style.strokeMiterLimit = style.strokeMiterLimit;
            rendererState.strokePaint.setStrokeMiter(style.strokeMiterLimit.floatValue());
        }
        if (isSpecified(style, 512)) {
            rendererState.style.strokeDashArray = style.strokeDashArray;
        }
        if (isSpecified(style, 1024)) {
            rendererState.style.strokeDashOffset = style.strokeDashOffset;
        }
        Typeface typeface = null;
        if (isSpecified(style, 1536)) {
            SVG.Length[] lengthArr = rendererState.style.strokeDashArray;
            if (lengthArr == null) {
                rendererState.strokePaint.setPathEffect((PathEffect) null);
            } else {
                int length2 = lengthArr.length;
                int i5 = length2 % 2 == 0 ? length2 : length2 * 2;
                float[] fArr = new float[i5];
                float f2 = 0.0f;
                for (int i6 = 0; i6 < i5; i6++) {
                    float floatValue = rendererState.style.strokeDashArray[i6 % length2].floatValue(this);
                    fArr[i6] = floatValue;
                    f2 += floatValue;
                }
                if (f2 == 0.0f) {
                    rendererState.strokePaint.setPathEffect((PathEffect) null);
                } else {
                    float floatValue2 = rendererState.style.strokeDashOffset.floatValue(this);
                    if (floatValue2 < 0.0f) {
                        floatValue2 = (floatValue2 % f2) + f2;
                    }
                    rendererState.strokePaint.setPathEffect(new DashPathEffect(fArr, floatValue2));
                }
            }
        }
        if (isSpecified(style, 16384)) {
            float currentFontSize = getCurrentFontSize();
            rendererState.style.fontSize = style.fontSize;
            rendererState.fillPaint.setTextSize(style.fontSize.floatValue(this, currentFontSize));
            rendererState.strokePaint.setTextSize(style.fontSize.floatValue(this, currentFontSize));
        }
        if (isSpecified(style, PlaybackStateCompat.ACTION_PLAY_FROM_URI)) {
            rendererState.style.fontFamily = style.fontFamily;
        }
        if (isSpecified(style, 32768)) {
            if (style.fontWeight.intValue() == -1 && rendererState.style.fontWeight.intValue() > 100) {
                SVG.Style style3 = rendererState.style;
                style3.fontWeight = Integer.valueOf(style3.fontWeight.intValue() - 100);
            } else if (style.fontWeight.intValue() != 1 || rendererState.style.fontWeight.intValue() >= 900) {
                rendererState.style.fontWeight = style.fontWeight;
            } else {
                SVG.Style style4 = rendererState.style;
                style4.fontWeight = Integer.valueOf(style4.fontWeight.intValue() + 100);
            }
        }
        if (isSpecified(style, PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH)) {
            rendererState.style.fontStyle = style.fontStyle;
        }
        if (isSpecified(style, 106496)) {
            if (rendererState.style.fontFamily != null && this.document != null) {
                SVGExternalFileResolver fileResolver = SVG.getFileResolver();
                for (String next : rendererState.style.fontFamily) {
                    SVG.Style style5 = rendererState.style;
                    Typeface checkGenericFont = checkGenericFont(next, style5.fontWeight, style5.fontStyle);
                    if (checkGenericFont != null || fileResolver == null) {
                        typeface = checkGenericFont;
                        continue;
                    } else {
                        typeface = fileResolver.resolveFont(next, rendererState.style.fontWeight.intValue(), String.valueOf(rendererState.style.fontStyle));
                        continue;
                    }
                    if (typeface != null) {
                        break;
                    }
                }
            }
            if (typeface == null) {
                SVG.Style style6 = rendererState.style;
                typeface = checkGenericFont("serif", style6.fontWeight, style6.fontStyle);
            }
            rendererState.fillPaint.setTypeface(typeface);
            rendererState.strokePaint.setTypeface(typeface);
        }
        if (isSpecified(style, 131072)) {
            rendererState.style.textDecoration = style.textDecoration;
            Paint paint = rendererState.fillPaint;
            SVG.Style.TextDecoration textDecoration = style.textDecoration;
            SVG.Style.TextDecoration textDecoration2 = SVG.Style.TextDecoration.LineThrough;
            paint.setStrikeThruText(textDecoration == textDecoration2);
            Paint paint2 = rendererState.fillPaint;
            SVG.Style.TextDecoration textDecoration3 = style.textDecoration;
            SVG.Style.TextDecoration textDecoration4 = SVG.Style.TextDecoration.Underline;
            paint2.setUnderlineText(textDecoration3 == textDecoration4);
            rendererState.strokePaint.setStrikeThruText(style.textDecoration == textDecoration2);
            Paint paint3 = rendererState.strokePaint;
            if (style.textDecoration == textDecoration4) {
                z2 = true;
            }
            paint3.setUnderlineText(z2);
        }
        if (isSpecified(style, 68719476736L)) {
            rendererState.style.direction = style.direction;
        }
        if (isSpecified(style, PlaybackStateCompat.ACTION_SET_REPEAT_MODE)) {
            rendererState.style.textAnchor = style.textAnchor;
        }
        if (isSpecified(style, 524288)) {
            rendererState.style.overflow = style.overflow;
        }
        if (isSpecified(style, 2097152)) {
            rendererState.style.markerStart = style.markerStart;
        }
        if (isSpecified(style, 4194304)) {
            rendererState.style.markerMid = style.markerMid;
        }
        if (isSpecified(style, 8388608)) {
            rendererState.style.markerEnd = style.markerEnd;
        }
        if (isSpecified(style, 16777216)) {
            rendererState.style.display = style.display;
        }
        if (isSpecified(style, 33554432)) {
            rendererState.style.visibility = style.visibility;
        }
        if (isSpecified(style, 1048576)) {
            rendererState.style.clip = style.clip;
        }
        if (isSpecified(style, 268435456)) {
            rendererState.style.clipPath = style.clipPath;
        }
        if (isSpecified(style, 536870912)) {
            rendererState.style.clipRule = style.clipRule;
        }
        if (isSpecified(style, 1073741824)) {
            rendererState.style.mask = style.mask;
        }
        if (isSpecified(style, 67108864)) {
            rendererState.style.stopColor = style.stopColor;
        }
        if (isSpecified(style, OpenFlags.MAX_VALUE)) {
            rendererState.style.stopOpacity = style.stopOpacity;
        }
        if (isSpecified(style, 8589934592L)) {
            rendererState.style.viewportFill = style.viewportFill;
        }
        if (isSpecified(style, 17179869184L)) {
            rendererState.style.viewportFillOpacity = style.viewportFillOpacity;
        }
        if (isSpecified(style, 137438953472L)) {
            rendererState.style.imageRendering = style.imageRendering;
        }
    }

    private void updateStyleForElement(RendererState rendererState, SVG.SvgElementBase svgElementBase) {
        rendererState.style.resetNonInheritingProperties(svgElementBase.parent == null);
        SVG.Style style = svgElementBase.baseStyle;
        if (style != null) {
            updateStyle(rendererState, style);
        }
        if (this.document.hasCSSRules()) {
            for (CSSParser.Rule next : this.document.getCSSRules()) {
                if (CSSParser.ruleMatch(this.ruleMatchContext, next.selector, svgElementBase)) {
                    updateStyle(rendererState, next.style);
                }
            }
        }
        SVG.Style style2 = svgElementBase.style;
        if (style2 != null) {
            updateStyle(rendererState, style2);
        }
    }

    private void viewportFill() {
        int i3;
        SVG.Style style = this.state.style;
        SVG.SvgPaint svgPaint = style.viewportFill;
        if (svgPaint instanceof SVG.Colour) {
            i3 = ((SVG.Colour) svgPaint).colour;
        } else if (svgPaint instanceof SVG.CurrentColor) {
            i3 = style.color.colour;
        } else {
            return;
        }
        Float f2 = style.viewportFillOpacity;
        if (f2 != null) {
            i3 = colourWithOpacity(i3, f2.floatValue());
        }
        this.canvas.drawColor(i3);
    }

    /* access modifiers changed from: private */
    public boolean visible() {
        Boolean bool = this.state.style.visibility;
        if (bool != null) {
            return bool.booleanValue();
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static void warn(String str, Object... objArr) {
        Log.w(TAG, String.format(str, objArr));
    }

    public float getCurrentFontSize() {
        return this.state.fillPaint.getTextSize();
    }

    public float getCurrentFontXHeight() {
        return this.state.fillPaint.getTextSize() / 2.0f;
    }

    public SVG.Box getCurrentViewPortInUserUnits() {
        RendererState rendererState = this.state;
        SVG.Box box = rendererState.viewBox;
        return box != null ? box : rendererState.viewPort;
    }

    public float getDPI() {
        return this.dpi;
    }

    public void renderDocument(SVG svg, RenderOptions renderOptions) {
        SVG.Box box;
        PreserveAspectRatio preserveAspectRatio;
        if (renderOptions != null) {
            this.document = svg;
            SVG.Svg rootElement = svg.getRootElement();
            if (rootElement == null) {
                warn("Nothing to render. Document is empty.", new Object[0]);
                return;
            }
            if (renderOptions.hasView()) {
                SVG.SvgElementBase elementById = this.document.getElementById(renderOptions.viewId);
                if (elementById == null || !(elementById instanceof SVG.View)) {
                    String str = renderOptions.viewId;
                    Log.w(TAG, "View element with id \"" + str + "\" not found.");
                    return;
                }
                SVG.View view = (SVG.View) elementById;
                box = view.viewBox;
                if (box == null) {
                    String str2 = renderOptions.viewId;
                    Log.w(TAG, "View element with id \"" + str2 + "\" is missing a viewBox attribute.");
                    return;
                }
                preserveAspectRatio = view.preserveAspectRatio;
            } else {
                box = renderOptions.hasViewBox() ? renderOptions.viewBox : rootElement.viewBox;
                preserveAspectRatio = renderOptions.hasPreserveAspectRatio() ? renderOptions.preserveAspectRatio : rootElement.preserveAspectRatio;
            }
            if (renderOptions.hasCss()) {
                svg.addCSSRules(renderOptions.css);
            }
            if (renderOptions.hasTarget()) {
                CSSParser.RuleMatchContext ruleMatchContext2 = new CSSParser.RuleMatchContext();
                this.ruleMatchContext = ruleMatchContext2;
                ruleMatchContext2.targetElement = svg.getElementById(renderOptions.targetId);
            }
            resetState();
            checkXMLSpaceAttribute(rootElement);
            statePush();
            SVG.Box box2 = new SVG.Box(renderOptions.viewPort);
            SVG.Length length = rootElement.width;
            if (length != null) {
                box2.width = length.floatValue(this, box2.width);
            }
            SVG.Length length2 = rootElement.height;
            if (length2 != null) {
                box2.height = length2.floatValue(this, box2.height);
            }
            render(rootElement, box2, box, preserveAspectRatio);
            statePop();
            if (renderOptions.hasCss()) {
                svg.clearRenderCSSRules();
                return;
            }
            return;
        }
        throw new NullPointerException("renderOptions shouldn't be null");
    }

    public class TextWidthCalculator extends TextProcessor {

        /* renamed from: x  reason: collision with root package name */
        float f6545x;

        private TextWidthCalculator() {
            super(SVGAndroidRenderer.this, (AnonymousClass1) null);
            this.f6545x = 0.0f;
        }

        public void processText(String str) {
            this.f6545x = SVGAndroidRenderer.this.state.fillPaint.measureText(str) + this.f6545x;
        }

        public /* synthetic */ TextWidthCalculator(SVGAndroidRenderer sVGAndroidRenderer, AnonymousClass1 r2) {
            this();
        }
    }

    private void checkForClipPath(SVG.SvgElement svgElement, SVG.Box box) {
        Path calculateClipPath;
        if (this.state.style.clipPath != null && (calculateClipPath = calculateClipPath(svgElement, box)) != null) {
            this.canvas.clipPath(calculateClipPath);
        }
    }

    private void popLayer(SVG.SvgElement svgElement, SVG.Box box) {
        if (this.state.style.mask != null) {
            Paint paint = new Paint();
            PorterDuff.Mode mode = PorterDuff.Mode.DST_IN;
            paint.setXfermode(new PorterDuffXfermode(mode));
            this.canvas.saveLayer((RectF) null, paint, 31);
            Paint paint2 = new Paint();
            paint2.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.2127f, 0.7151f, 0.0722f, 0.0f, 0.0f})));
            this.canvas.saveLayer((RectF) null, paint2, 31);
            SVG.Mask mask = (SVG.Mask) this.document.resolveIRI(this.state.style.mask);
            renderMask(mask, svgElement, box);
            this.canvas.restore();
            Paint paint3 = new Paint();
            paint3.setXfermode(new PorterDuffXfermode(mode));
            this.canvas.saveLayer((RectF) null, paint3, 31);
            renderMask(mask, svgElement, box);
            this.canvas.restore();
            this.canvas.restore();
        }
        statePop();
    }

    private RendererState findInheritFromAncestorState(SVG.SvgObject svgObject, RendererState rendererState) {
        ArrayList arrayList = new ArrayList();
        while (true) {
            if (svgObject instanceof SVG.SvgElementBase) {
                arrayList.add(0, (SVG.SvgElementBase) svgObject);
            }
            SVG.SvgContainer svgContainer = svgObject.parent;
            if (svgContainer == null) {
                break;
            }
            svgObject = (SVG.SvgObject) svgContainer;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            updateStyleForElement(rendererState, (SVG.SvgElementBase) it.next());
        }
        RendererState rendererState2 = this.state;
        rendererState.viewBox = rendererState2.viewBox;
        rendererState.viewPort = rendererState2.viewPort;
        return rendererState;
    }

    private List<MarkerVector> calculateMarkerPositions(SVG.PolyLine polyLine) {
        SVG.PolyLine polyLine2 = polyLine;
        int length = polyLine2.points.length;
        int i3 = 2;
        if (length < 2) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        float[] fArr = polyLine2.points;
        MarkerVector markerVector = new MarkerVector(fArr[0], fArr[1], 0.0f, 0.0f);
        float f2 = 0.0f;
        float f3 = 0.0f;
        while (i3 < length) {
            float[] fArr2 = polyLine2.points;
            float f4 = fArr2[i3];
            float f5 = fArr2[i3 + 1];
            markerVector.add(f4, f5);
            arrayList.add(markerVector);
            i3 += 2;
            markerVector = new MarkerVector(f4, f5, f4 - markerVector.f6537x, f5 - markerVector.f6538y);
            float f6 = f4;
            f3 = f5;
            f2 = f6;
        }
        if (polyLine2 instanceof SVG.Polygon) {
            float[] fArr3 = polyLine2.points;
            float f7 = fArr3[0];
            if (f2 != f7) {
                float f8 = fArr3[1];
                if (f3 != f8) {
                    markerVector.add(f7, f8);
                    arrayList.add(markerVector);
                    MarkerVector markerVector2 = new MarkerVector(f7, f8, f7 - markerVector.f6537x, f8 - markerVector.f6538y);
                    markerVector2.add((MarkerVector) arrayList.get(0));
                    arrayList.add(markerVector2);
                    arrayList.set(0, markerVector2);
                }
            }
        } else {
            arrayList.add(markerVector);
        }
        return arrayList;
    }

    public class MarkerVector {
        float dx = 0.0f;
        float dy = 0.0f;
        boolean isAmbiguous = false;

        /* renamed from: x  reason: collision with root package name */
        float f6537x;

        /* renamed from: y  reason: collision with root package name */
        float f6538y;

        public MarkerVector(float f2, float f3, float f4, float f5) {
            this.f6537x = f2;
            this.f6538y = f3;
            double sqrt = Math.sqrt((double) ((f5 * f5) + (f4 * f4)));
            if (sqrt != 0.0d) {
                this.dx = (float) (((double) f4) / sqrt);
                this.dy = (float) (((double) f5) / sqrt);
            }
        }

        public void add(float f2, float f3) {
            float f4 = f2 - this.f6537x;
            float f5 = f3 - this.f6538y;
            double sqrt = Math.sqrt((double) ((f5 * f5) + (f4 * f4)));
            if (sqrt != 0.0d) {
                f4 = (float) (((double) f4) / sqrt);
                f5 = (float) (((double) f5) / sqrt);
            }
            float f6 = this.dx;
            if (f4 == (-f6) && f5 == (-this.dy)) {
                this.isAmbiguous = true;
                this.dx = -f5;
                this.dy = f4;
                return;
            }
            this.dx = f6 + f4;
            this.dy += f5;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("(");
            sb.append(this.f6537x);
            sb.append(",");
            sb.append(this.f6538y);
            sb.append(StringUtils.SPACE);
            sb.append(this.dx);
            sb.append(",");
            return C0118y.i(sb, ")", this.dy);
        }

        public void add(MarkerVector markerVector) {
            float f2 = markerVector.dx;
            float f3 = this.dx;
            if (f2 == (-f3)) {
                float f4 = markerVector.dy;
                if (f4 == (-this.dy)) {
                    this.isAmbiguous = true;
                    this.dx = -f4;
                    this.dy = markerVector.dx;
                    return;
                }
            }
            this.dx = f3 + f2;
            this.dy += markerVector.dy;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00da  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.Path makePathAndBoundingBox(com.caverock.androidsvg.SVG.Rect r23) {
        /*
            r22 = this;
            r0 = r22
            r1 = r23
            com.caverock.androidsvg.SVG$Length r2 = r1.rx
            r3 = 0
            if (r2 != 0) goto L_0x0010
            com.caverock.androidsvg.SVG$Length r4 = r1.ry
            if (r4 != 0) goto L_0x0010
            r2 = r3
        L_0x000e:
            r4 = r2
            goto L_0x002c
        L_0x0010:
            if (r2 != 0) goto L_0x0019
            com.caverock.androidsvg.SVG$Length r2 = r1.ry
            float r2 = r2.floatValueY(r0)
            goto L_0x000e
        L_0x0019:
            com.caverock.androidsvg.SVG$Length r4 = r1.ry
            if (r4 != 0) goto L_0x0022
            float r2 = r2.floatValueX(r0)
            goto L_0x000e
        L_0x0022:
            float r2 = r2.floatValueX(r0)
            com.caverock.androidsvg.SVG$Length r4 = r1.ry
            float r4 = r4.floatValueY(r0)
        L_0x002c:
            com.caverock.androidsvg.SVG$Length r5 = r1.width
            float r5 = r5.floatValueX(r0)
            r6 = 1073741824(0x40000000, float:2.0)
            float r5 = r5 / r6
            float r2 = java.lang.Math.min(r2, r5)
            com.caverock.androidsvg.SVG$Length r5 = r1.height
            float r5 = r5.floatValueY(r0)
            float r5 = r5 / r6
            float r4 = java.lang.Math.min(r4, r5)
            com.caverock.androidsvg.SVG$Length r5 = r1.f6528x
            if (r5 == 0) goto L_0x004d
            float r5 = r5.floatValueX(r0)
            goto L_0x004e
        L_0x004d:
            r5 = r3
        L_0x004e:
            com.caverock.androidsvg.SVG$Length r6 = r1.f6529y
            if (r6 == 0) goto L_0x0058
            float r6 = r6.floatValueY(r0)
            r13 = r6
            goto L_0x0059
        L_0x0058:
            r13 = r3
        L_0x0059:
            com.caverock.androidsvg.SVG$Length r6 = r1.width
            float r6 = r6.floatValueX(r0)
            com.caverock.androidsvg.SVG$Length r7 = r1.height
            float r0 = r7.floatValueY(r0)
            com.caverock.androidsvg.SVG$Box r7 = r1.boundingBox
            if (r7 != 0) goto L_0x0070
            com.caverock.androidsvg.SVG$Box r7 = new com.caverock.androidsvg.SVG$Box
            r7.<init>(r5, r13, r6, r0)
            r1.boundingBox = r7
        L_0x0070:
            float r15 = r5 + r6
            float r0 = r0 + r13
            android.graphics.Path r1 = new android.graphics.Path
            r1.<init>()
            int r6 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r6 == 0) goto L_0x00da
            int r3 = (r4 > r3 ? 1 : (r4 == r3 ? 0 : -1))
            if (r3 != 0) goto L_0x0082
            goto L_0x00da
        L_0x0082:
            r3 = 1057841801(0x3f0d6289, float:0.5522848)
            float r14 = r2 * r3
            float r3 = r3 * r4
            float r12 = r13 + r4
            r1.moveTo(r5, r12)
            float r16 = r12 - r3
            float r11 = r5 + r2
            float r21 = r11 - r14
            r6 = r1
            r7 = r5
            r8 = r16
            r9 = r21
            r10 = r13
            r22 = r11
            r23 = r12
            r12 = r13
            r6.cubicTo(r7, r8, r9, r10, r11, r12)
            float r2 = r15 - r2
            r1.lineTo(r2, r13)
            float r17 = r2 + r14
            r7 = r1
            r8 = r17
            r9 = r13
            r10 = r15
            r11 = r16
            r12 = r15
            r13 = r23
            r7.cubicTo(r8, r9, r10, r11, r12, r13)
            float r12 = r0 - r4
            r1.lineTo(r15, r12)
            float r10 = r12 + r3
            r14 = r1
            r16 = r10
            r18 = r0
            r19 = r2
            r20 = r0
            r14.cubicTo(r15, r16, r17, r18, r19, r20)
            r2 = r22
            r1.lineTo(r2, r0)
            r7 = r21
            r8 = r0
            r9 = r5
            r11 = r5
            r6.cubicTo(r7, r8, r9, r10, r11, r12)
            r1.lineTo(r5, r13)
            goto L_0x00e9
        L_0x00da:
            r1.moveTo(r5, r13)
            r1.lineTo(r15, r13)
            r1.lineTo(r15, r0)
            r1.lineTo(r5, r0)
            r1.lineTo(r5, r13)
        L_0x00e9:
            r1.close()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.caverock.androidsvg.SVGAndroidRenderer.makePathAndBoundingBox(com.caverock.androidsvg.SVG$Rect):android.graphics.Path");
    }

    public class RendererState {
        Paint fillPaint;
        boolean hasFill;
        boolean hasStroke;
        boolean spacePreserve;
        Paint strokePaint;
        SVG.Style style;
        SVG.Box viewBox;
        SVG.Box viewPort;

        public RendererState() {
            Paint paint = new Paint();
            this.fillPaint = paint;
            paint.setFlags(193);
            this.fillPaint.setHinting(0);
            this.fillPaint.setStyle(Paint.Style.FILL);
            Paint paint2 = this.fillPaint;
            Typeface typeface = Typeface.DEFAULT;
            paint2.setTypeface(typeface);
            Paint paint3 = new Paint();
            this.strokePaint = paint3;
            paint3.setFlags(193);
            this.strokePaint.setHinting(0);
            this.strokePaint.setStyle(Paint.Style.STROKE);
            this.strokePaint.setTypeface(typeface);
            this.style = SVG.Style.getDefaultStyle();
        }

        public RendererState(RendererState rendererState) {
            this.hasFill = rendererState.hasFill;
            this.hasStroke = rendererState.hasStroke;
            this.fillPaint = new Paint(rendererState.fillPaint);
            this.strokePaint = new Paint(rendererState.strokePaint);
            SVG.Box box = rendererState.viewPort;
            if (box != null) {
                this.viewPort = new SVG.Box(box);
            }
            SVG.Box box2 = rendererState.viewBox;
            if (box2 != null) {
                this.viewBox = new SVG.Box(box2);
            }
            this.spacePreserve = rendererState.spacePreserve;
            try {
                this.style = (SVG.Style) rendererState.style.clone();
            } catch (CloneNotSupportedException e3) {
                Log.e(SVGAndroidRenderer.TAG, "Unexpected clone error", e3);
                this.style = SVG.Style.getDefaultStyle();
            }
        }
    }

    private void addObjectToClip(SVG.Path path, Path path2, Matrix matrix) {
        updateStyleForElement(this.state, path);
        if (display() && visible()) {
            Matrix matrix2 = path.transform;
            if (matrix2 != null) {
                matrix.preConcat(matrix2);
            }
            Path path3 = new PathConverter(path.f6525d).getPath();
            if (path.boundingBox == null) {
                path.boundingBox = calculatePathBounds(path3);
            }
            checkForClipPath(path);
            path2.setFillType(getClipRuleFromState());
            path2.addPath(path3, matrix);
        }
    }

    private void fillInChainedGradientFields(SVG.SvgLinearGradient svgLinearGradient, SVG.SvgLinearGradient svgLinearGradient2) {
        if (svgLinearGradient.x1 == null) {
            svgLinearGradient.x1 = svgLinearGradient2.x1;
        }
        if (svgLinearGradient.y1 == null) {
            svgLinearGradient.y1 = svgLinearGradient2.y1;
        }
        if (svgLinearGradient.x2 == null) {
            svgLinearGradient.x2 = svgLinearGradient2.x2;
        }
        if (svgLinearGradient.y2 == null) {
            svgLinearGradient.y2 = svgLinearGradient2.y2;
        }
    }

    private void addObjectToClip(SVG.GraphicsElement graphicsElement, Path path, Matrix matrix) {
        Path path2;
        updateStyleForElement(this.state, graphicsElement);
        if (display() && visible()) {
            Matrix matrix2 = graphicsElement.transform;
            if (matrix2 != null) {
                matrix.preConcat(matrix2);
            }
            if (graphicsElement instanceof SVG.Rect) {
                path2 = makePathAndBoundingBox((SVG.Rect) graphicsElement);
            } else if (graphicsElement instanceof SVG.Circle) {
                path2 = makePathAndBoundingBox((SVG.Circle) graphicsElement);
            } else if (graphicsElement instanceof SVG.Ellipse) {
                path2 = makePathAndBoundingBox((SVG.Ellipse) graphicsElement);
            } else if (graphicsElement instanceof SVG.PolyLine) {
                path2 = makePathAndBoundingBox((SVG.PolyLine) graphicsElement);
            } else {
                return;
            }
            checkForClipPath(graphicsElement);
            path.setFillType(getClipRuleFromState());
            path.addPath(path2, matrix);
        }
    }

    private void fillInChainedGradientFields(SVG.SvgRadialGradient svgRadialGradient, SVG.SvgRadialGradient svgRadialGradient2) {
        if (svgRadialGradient.cx == null) {
            svgRadialGradient.cx = svgRadialGradient2.cx;
        }
        if (svgRadialGradient.cy == null) {
            svgRadialGradient.cy = svgRadialGradient2.cy;
        }
        if (svgRadialGradient.f6532r == null) {
            svgRadialGradient.f6532r = svgRadialGradient2.f6532r;
        }
        if (svgRadialGradient.fx == null) {
            svgRadialGradient.fx = svgRadialGradient2.fx;
        }
        if (svgRadialGradient.fy == null) {
            svgRadialGradient.fy = svgRadialGradient2.fy;
        }
    }

    private void render(SVG.Svg svg) {
        render(svg, makeViewPort(svg.f6530x, svg.f6531y, svg.width, svg.height), svg.viewBox, svg.preserveAspectRatio);
    }

    private void render(SVG.Svg svg, SVG.Box box) {
        render(svg, box, svg.viewBox, svg.preserveAspectRatio);
    }

    private void render(SVG.Svg svg, SVG.Box box, SVG.Box box2, PreserveAspectRatio preserveAspectRatio) {
        debug("Svg render", new Object[0]);
        if (box.width != 0.0f && box.height != 0.0f) {
            if (preserveAspectRatio == null && (preserveAspectRatio = svg.preserveAspectRatio) == null) {
                preserveAspectRatio = PreserveAspectRatio.LETTERBOX;
            }
            updateStyleForElement(this.state, svg);
            if (display()) {
                RendererState rendererState = this.state;
                rendererState.viewPort = box;
                if (!rendererState.style.overflow.booleanValue()) {
                    SVG.Box box3 = this.state.viewPort;
                    setClipRect(box3.minX, box3.minY, box3.width, box3.height);
                }
                checkForClipPath(svg, this.state.viewPort);
                if (box2 != null) {
                    this.canvas.concat(calculateViewBoxTransform(this.state.viewPort, box2, preserveAspectRatio));
                    this.state.viewBox = svg.viewBox;
                } else {
                    Canvas canvas2 = this.canvas;
                    SVG.Box box4 = this.state.viewPort;
                    canvas2.translate(box4.minX, box4.minY);
                }
                boolean pushLayer = pushLayer();
                viewportFill();
                renderChildren(svg, true);
                if (pushLayer) {
                    popLayer(svg);
                }
                updateParentBoundingBox(svg);
            }
        }
    }

    private Path makePathAndBoundingBox(SVG.Circle circle) {
        SVG.Circle circle2 = circle;
        SVG.Length length = circle2.cx;
        float f2 = 0.0f;
        float floatValueX = length != null ? length.floatValueX(this) : 0.0f;
        SVG.Length length2 = circle2.cy;
        if (length2 != null) {
            f2 = length2.floatValueY(this);
        }
        float floatValue = circle2.f6520r.floatValue(this);
        float f3 = floatValueX - floatValue;
        float f4 = f2 - floatValue;
        float f5 = floatValueX + floatValue;
        float f6 = f2 + floatValue;
        if (circle2.boundingBox == null) {
            float f7 = 2.0f * floatValue;
            circle2.boundingBox = new SVG.Box(f3, f4, f7, f7);
        }
        float f8 = floatValue * BEZIER_ARC_FACTOR;
        Path path = new Path();
        path.moveTo(floatValueX, f4);
        float f9 = floatValueX + f8;
        float f10 = f2 - f8;
        Path path2 = path;
        path2.cubicTo(f9, f4, f5, f10, f5, f2);
        float f11 = f2 + f8;
        path2.cubicTo(f5, f11, f9, f6, floatValueX, f6);
        float f12 = floatValueX - f8;
        path2.cubicTo(f12, f6, f3, f11, f3, f2);
        path2.cubicTo(f3, f10, f12, f4, floatValueX, f4);
        path.close();
        return path;
    }

    private void addObjectToClip(SVG.Use use, Path path, Matrix matrix) {
        updateStyleForElement(this.state, use);
        if (display() && visible()) {
            Matrix matrix2 = use.transform;
            if (matrix2 != null) {
                matrix.preConcat(matrix2);
            }
            SVG.SvgObject resolveIRI = use.document.resolveIRI(use.href);
            if (resolveIRI == null) {
                error("Use reference '%s' not found", use.href);
                return;
            }
            checkForClipPath(use);
            addObjectToClip(resolveIRI, false, path, matrix);
        }
    }

    private void addObjectToClip(SVG.Text text, Path path, Matrix matrix) {
        updateStyleForElement(this.state, text);
        if (display()) {
            Matrix matrix2 = text.transform;
            if (matrix2 != null) {
                matrix.preConcat(matrix2);
            }
            List<SVG.Length> list = text.f6533x;
            float f2 = 0.0f;
            float floatValueX = (list == null || list.size() == 0) ? 0.0f : text.f6533x.get(0).floatValueX(this);
            List<SVG.Length> list2 = text.f6534y;
            float floatValueY = (list2 == null || list2.size() == 0) ? 0.0f : text.f6534y.get(0).floatValueY(this);
            List<SVG.Length> list3 = text.dx;
            float floatValueX2 = (list3 == null || list3.size() == 0) ? 0.0f : text.dx.get(0).floatValueX(this);
            List<SVG.Length> list4 = text.dy;
            if (!(list4 == null || list4.size() == 0)) {
                f2 = text.dy.get(0).floatValueY(this);
            }
            if (this.state.style.textAnchor != SVG.Style.TextAnchor.Start) {
                float calculateTextWidth = calculateTextWidth(text);
                if (this.state.style.textAnchor == SVG.Style.TextAnchor.Middle) {
                    calculateTextWidth /= 2.0f;
                }
                floatValueX -= calculateTextWidth;
            }
            if (text.boundingBox == null) {
                TextBoundsCalculator textBoundsCalculator = new TextBoundsCalculator(floatValueX, floatValueY);
                enumerateTextSpans(text, textBoundsCalculator);
                RectF rectF = textBoundsCalculator.bbox;
                text.boundingBox = new SVG.Box(rectF.left, rectF.top, rectF.width(), textBoundsCalculator.bbox.height());
            }
            checkForClipPath(text);
            Path path2 = new Path();
            enumerateTextSpans(text, new PlainTextToPath(floatValueX + floatValueX2, floatValueY + f2, path2));
            path.setFillType(getClipRuleFromState());
            path.addPath(path2, matrix);
        }
    }

    private void render(SVG.Group group) {
        debug("Group render", new Object[0]);
        updateStyleForElement(this.state, group);
        if (display()) {
            Matrix matrix = group.transform;
            if (matrix != null) {
                this.canvas.concat(matrix);
            }
            checkForClipPath(group);
            boolean pushLayer = pushLayer();
            renderChildren(group, true);
            if (pushLayer) {
                popLayer(group);
            }
            updateParentBoundingBox(group);
        }
    }

    private Path makePathAndBoundingBox(SVG.Ellipse ellipse) {
        SVG.Ellipse ellipse2 = ellipse;
        SVG.Length length = ellipse2.cx;
        float f2 = 0.0f;
        float floatValueX = length != null ? length.floatValueX(this) : 0.0f;
        SVG.Length length2 = ellipse2.cy;
        if (length2 != null) {
            f2 = length2.floatValueY(this);
        }
        float floatValueX2 = ellipse2.rx.floatValueX(this);
        float floatValueY = ellipse2.ry.floatValueY(this);
        float f3 = floatValueX - floatValueX2;
        float f4 = f2 - floatValueY;
        float f5 = floatValueX + floatValueX2;
        float f6 = f2 + floatValueY;
        if (ellipse2.boundingBox == null) {
            ellipse2.boundingBox = new SVG.Box(f3, f4, floatValueX2 * 2.0f, 2.0f * floatValueY);
        }
        float f7 = floatValueX2 * BEZIER_ARC_FACTOR;
        float f8 = floatValueY * BEZIER_ARC_FACTOR;
        Path path = new Path();
        path.moveTo(floatValueX, f4);
        float f9 = floatValueX + f7;
        float f10 = f2 - f8;
        Path path2 = path;
        path2.cubicTo(f9, f4, f5, f10, f5, f2);
        float f11 = f8 + f2;
        path2.cubicTo(f5, f11, f9, f6, floatValueX, f6);
        float f12 = floatValueX - f7;
        path2.cubicTo(f12, f6, f3, f11, f3, f2);
        path2.cubicTo(f3, f10, f12, f4, floatValueX, f4);
        path.close();
        return path;
    }

    private void render(SVG.Switch switchR) {
        debug("Switch render", new Object[0]);
        updateStyleForElement(this.state, switchR);
        if (display()) {
            Matrix matrix = switchR.transform;
            if (matrix != null) {
                this.canvas.concat(matrix);
            }
            checkForClipPath(switchR);
            boolean pushLayer = pushLayer();
            renderSwitchChild(switchR);
            if (pushLayer) {
                popLayer(switchR);
            }
            updateParentBoundingBox(switchR);
        }
    }

    private Path makePathAndBoundingBox(SVG.PolyLine polyLine) {
        Path path = new Path();
        float[] fArr = polyLine.points;
        path.moveTo(fArr[0], fArr[1]);
        int i3 = 2;
        while (true) {
            float[] fArr2 = polyLine.points;
            if (i3 >= fArr2.length) {
                break;
            }
            path.lineTo(fArr2[i3], fArr2[i3 + 1]);
            i3 += 2;
        }
        if (polyLine instanceof SVG.Polygon) {
            path.close();
        }
        if (polyLine.boundingBox == null) {
            polyLine.boundingBox = calculatePathBounds(path);
        }
        return path;
    }

    private void render(SVG.Use use) {
        debug("Use render", new Object[0]);
        SVG.Length length = use.width;
        if (length == null || !length.isZero()) {
            SVG.Length length2 = use.height;
            if (length2 == null || !length2.isZero()) {
                updateStyleForElement(this.state, use);
                if (display()) {
                    SVG.SvgObject resolveIRI = use.document.resolveIRI(use.href);
                    if (resolveIRI == null) {
                        error("Use reference '%s' not found", use.href);
                        return;
                    }
                    Matrix matrix = use.transform;
                    if (matrix != null) {
                        this.canvas.concat(matrix);
                    }
                    SVG.Length length3 = use.f6535x;
                    float f2 = 0.0f;
                    float floatValueX = length3 != null ? length3.floatValueX(this) : 0.0f;
                    SVG.Length length4 = use.f6536y;
                    if (length4 != null) {
                        f2 = length4.floatValueY(this);
                    }
                    this.canvas.translate(floatValueX, f2);
                    checkForClipPath(use);
                    boolean pushLayer = pushLayer();
                    parentPush(use);
                    if (resolveIRI instanceof SVG.Svg) {
                        SVG.Box makeViewPort = makeViewPort((SVG.Length) null, (SVG.Length) null, use.width, use.height);
                        statePush();
                        render((SVG.Svg) resolveIRI, makeViewPort);
                        statePop();
                    } else if (resolveIRI instanceof SVG.Symbol) {
                        SVG.Length length5 = use.width;
                        if (length5 == null) {
                            length5 = new SVG.Length(100.0f, SVG.Unit.percent);
                        }
                        SVG.Length length6 = use.height;
                        if (length6 == null) {
                            length6 = new SVG.Length(100.0f, SVG.Unit.percent);
                        }
                        SVG.Box makeViewPort2 = makeViewPort((SVG.Length) null, (SVG.Length) null, length5, length6);
                        statePush();
                        render((SVG.Symbol) resolveIRI, makeViewPort2);
                        statePop();
                    } else {
                        render(resolveIRI);
                    }
                    parentPop();
                    if (pushLayer) {
                        popLayer(use);
                    }
                    updateParentBoundingBox(use);
                }
            }
        }
    }

    private Path makePathAndBoundingBox(SVG.Text text) {
        List<SVG.Length> list = text.f6533x;
        float f2 = 0.0f;
        float floatValueX = (list == null || list.size() == 0) ? 0.0f : text.f6533x.get(0).floatValueX(this);
        List<SVG.Length> list2 = text.f6534y;
        float floatValueY = (list2 == null || list2.size() == 0) ? 0.0f : text.f6534y.get(0).floatValueY(this);
        List<SVG.Length> list3 = text.dx;
        float floatValueX2 = (list3 == null || list3.size() == 0) ? 0.0f : text.dx.get(0).floatValueX(this);
        List<SVG.Length> list4 = text.dy;
        if (!(list4 == null || list4.size() == 0)) {
            f2 = text.dy.get(0).floatValueY(this);
        }
        if (this.state.style.textAnchor != SVG.Style.TextAnchor.Start) {
            float calculateTextWidth = calculateTextWidth(text);
            if (this.state.style.textAnchor == SVG.Style.TextAnchor.Middle) {
                calculateTextWidth /= 2.0f;
            }
            floatValueX -= calculateTextWidth;
        }
        if (text.boundingBox == null) {
            TextBoundsCalculator textBoundsCalculator = new TextBoundsCalculator(floatValueX, floatValueY);
            enumerateTextSpans(text, textBoundsCalculator);
            RectF rectF = textBoundsCalculator.bbox;
            text.boundingBox = new SVG.Box(rectF.left, rectF.top, rectF.width(), textBoundsCalculator.bbox.height());
        }
        Path path = new Path();
        enumerateTextSpans(text, new PlainTextToPath(floatValueX + floatValueX2, floatValueY + f2, path));
        return path;
    }

    private void render(SVG.Path path) {
        debug("Path render", new Object[0]);
        if (path.f6525d != null) {
            updateStyleForElement(this.state, path);
            if (display() && visible()) {
                RendererState rendererState = this.state;
                if (rendererState.hasStroke || rendererState.hasFill) {
                    Matrix matrix = path.transform;
                    if (matrix != null) {
                        this.canvas.concat(matrix);
                    }
                    Path path2 = new PathConverter(path.f6525d).getPath();
                    if (path.boundingBox == null) {
                        path.boundingBox = calculatePathBounds(path2);
                    }
                    updateParentBoundingBox(path);
                    checkForGradientsAndPatterns(path);
                    checkForClipPath(path);
                    boolean pushLayer = pushLayer();
                    if (this.state.hasFill) {
                        path2.setFillType(getFillTypeFromState());
                        doFilledPath(path, path2);
                    }
                    if (this.state.hasStroke) {
                        doStroke(path2);
                    }
                    renderMarkers(path);
                    if (pushLayer) {
                        popLayer(path);
                    }
                }
            }
        }
    }

    private void render(SVG.Rect rect) {
        debug("Rect render", new Object[0]);
        SVG.Length length = rect.width;
        if (length != null && rect.height != null && !length.isZero() && !rect.height.isZero()) {
            updateStyleForElement(this.state, rect);
            if (display() && visible()) {
                Matrix matrix = rect.transform;
                if (matrix != null) {
                    this.canvas.concat(matrix);
                }
                Path makePathAndBoundingBox = makePathAndBoundingBox(rect);
                updateParentBoundingBox(rect);
                checkForGradientsAndPatterns(rect);
                checkForClipPath(rect);
                boolean pushLayer = pushLayer();
                if (this.state.hasFill) {
                    doFilledPath(rect, makePathAndBoundingBox);
                }
                if (this.state.hasStroke) {
                    doStroke(makePathAndBoundingBox);
                }
                if (pushLayer) {
                    popLayer(rect);
                }
            }
        }
    }

    private void render(SVG.Circle circle) {
        debug("Circle render", new Object[0]);
        SVG.Length length = circle.f6520r;
        if (length != null && !length.isZero()) {
            updateStyleForElement(this.state, circle);
            if (display() && visible()) {
                Matrix matrix = circle.transform;
                if (matrix != null) {
                    this.canvas.concat(matrix);
                }
                Path makePathAndBoundingBox = makePathAndBoundingBox(circle);
                updateParentBoundingBox(circle);
                checkForGradientsAndPatterns(circle);
                checkForClipPath(circle);
                boolean pushLayer = pushLayer();
                if (this.state.hasFill) {
                    doFilledPath(circle, makePathAndBoundingBox);
                }
                if (this.state.hasStroke) {
                    doStroke(makePathAndBoundingBox);
                }
                if (pushLayer) {
                    popLayer(circle);
                }
            }
        }
    }

    private void render(SVG.Ellipse ellipse) {
        debug("Ellipse render", new Object[0]);
        SVG.Length length = ellipse.rx;
        if (length != null && ellipse.ry != null && !length.isZero() && !ellipse.ry.isZero()) {
            updateStyleForElement(this.state, ellipse);
            if (display() && visible()) {
                Matrix matrix = ellipse.transform;
                if (matrix != null) {
                    this.canvas.concat(matrix);
                }
                Path makePathAndBoundingBox = makePathAndBoundingBox(ellipse);
                updateParentBoundingBox(ellipse);
                checkForGradientsAndPatterns(ellipse);
                checkForClipPath(ellipse);
                boolean pushLayer = pushLayer();
                if (this.state.hasFill) {
                    doFilledPath(ellipse, makePathAndBoundingBox);
                }
                if (this.state.hasStroke) {
                    doStroke(makePathAndBoundingBox);
                }
                if (pushLayer) {
                    popLayer(ellipse);
                }
            }
        }
    }

    private void render(SVG.Line line) {
        debug("Line render", new Object[0]);
        updateStyleForElement(this.state, line);
        if (display() && visible() && this.state.hasStroke) {
            Matrix matrix = line.transform;
            if (matrix != null) {
                this.canvas.concat(matrix);
            }
            Path makePathAndBoundingBox = makePathAndBoundingBox(line);
            updateParentBoundingBox(line);
            checkForGradientsAndPatterns(line);
            checkForClipPath(line);
            boolean pushLayer = pushLayer();
            doStroke(makePathAndBoundingBox);
            renderMarkers(line);
            if (pushLayer) {
                popLayer(line);
            }
        }
    }

    private void render(SVG.PolyLine polyLine) {
        debug("PolyLine render", new Object[0]);
        updateStyleForElement(this.state, polyLine);
        if (display() && visible()) {
            RendererState rendererState = this.state;
            if (rendererState.hasStroke || rendererState.hasFill) {
                Matrix matrix = polyLine.transform;
                if (matrix != null) {
                    this.canvas.concat(matrix);
                }
                if (polyLine.points.length >= 2) {
                    Path makePathAndBoundingBox = makePathAndBoundingBox(polyLine);
                    updateParentBoundingBox(polyLine);
                    makePathAndBoundingBox.setFillType(getFillTypeFromState());
                    checkForGradientsAndPatterns(polyLine);
                    checkForClipPath(polyLine);
                    boolean pushLayer = pushLayer();
                    if (this.state.hasFill) {
                        doFilledPath(polyLine, makePathAndBoundingBox);
                    }
                    if (this.state.hasStroke) {
                        doStroke(makePathAndBoundingBox);
                    }
                    renderMarkers(polyLine);
                    if (pushLayer) {
                        popLayer(polyLine);
                    }
                }
            }
        }
    }

    private void render(SVG.Polygon polygon) {
        debug("Polygon render", new Object[0]);
        updateStyleForElement(this.state, polygon);
        if (display() && visible()) {
            RendererState rendererState = this.state;
            if (rendererState.hasStroke || rendererState.hasFill) {
                Matrix matrix = polygon.transform;
                if (matrix != null) {
                    this.canvas.concat(matrix);
                }
                if (polygon.points.length >= 2) {
                    Path makePathAndBoundingBox = makePathAndBoundingBox((SVG.PolyLine) polygon);
                    updateParentBoundingBox(polygon);
                    checkForGradientsAndPatterns(polygon);
                    checkForClipPath(polygon);
                    boolean pushLayer = pushLayer();
                    if (this.state.hasFill) {
                        doFilledPath(polygon, makePathAndBoundingBox);
                    }
                    if (this.state.hasStroke) {
                        doStroke(makePathAndBoundingBox);
                    }
                    renderMarkers(polygon);
                    if (pushLayer) {
                        popLayer(polygon);
                    }
                }
            }
        }
    }

    private void render(SVG.Text text) {
        debug("Text render", new Object[0]);
        updateStyleForElement(this.state, text);
        if (display()) {
            Matrix matrix = text.transform;
            if (matrix != null) {
                this.canvas.concat(matrix);
            }
            List<SVG.Length> list = text.f6533x;
            float f2 = 0.0f;
            float floatValueX = (list == null || list.size() == 0) ? 0.0f : text.f6533x.get(0).floatValueX(this);
            List<SVG.Length> list2 = text.f6534y;
            float floatValueY = (list2 == null || list2.size() == 0) ? 0.0f : text.f6534y.get(0).floatValueY(this);
            List<SVG.Length> list3 = text.dx;
            float floatValueX2 = (list3 == null || list3.size() == 0) ? 0.0f : text.dx.get(0).floatValueX(this);
            List<SVG.Length> list4 = text.dy;
            if (!(list4 == null || list4.size() == 0)) {
                f2 = text.dy.get(0).floatValueY(this);
            }
            SVG.Style.TextAnchor anchorPosition = getAnchorPosition();
            if (anchorPosition != SVG.Style.TextAnchor.Start) {
                float calculateTextWidth = calculateTextWidth(text);
                if (anchorPosition == SVG.Style.TextAnchor.Middle) {
                    calculateTextWidth /= 2.0f;
                }
                floatValueX -= calculateTextWidth;
            }
            if (text.boundingBox == null) {
                TextBoundsCalculator textBoundsCalculator = new TextBoundsCalculator(floatValueX, floatValueY);
                enumerateTextSpans(text, textBoundsCalculator);
                RectF rectF = textBoundsCalculator.bbox;
                text.boundingBox = new SVG.Box(rectF.left, rectF.top, rectF.width(), textBoundsCalculator.bbox.height());
            }
            updateParentBoundingBox(text);
            checkForGradientsAndPatterns(text);
            checkForClipPath(text);
            boolean pushLayer = pushLayer();
            enumerateTextSpans(text, new PlainTextDrawer(floatValueX + floatValueX2, floatValueY + f2));
            if (pushLayer) {
                popLayer(text);
            }
        }
    }

    private void render(SVG.Symbol symbol, SVG.Box box) {
        debug("Symbol render", new Object[0]);
        if (box.width != 0.0f && box.height != 0.0f) {
            PreserveAspectRatio preserveAspectRatio = symbol.preserveAspectRatio;
            if (preserveAspectRatio == null) {
                preserveAspectRatio = PreserveAspectRatio.LETTERBOX;
            }
            updateStyleForElement(this.state, symbol);
            RendererState rendererState = this.state;
            rendererState.viewPort = box;
            if (!rendererState.style.overflow.booleanValue()) {
                SVG.Box box2 = this.state.viewPort;
                setClipRect(box2.minX, box2.minY, box2.width, box2.height);
            }
            SVG.Box box3 = symbol.viewBox;
            if (box3 != null) {
                this.canvas.concat(calculateViewBoxTransform(this.state.viewPort, box3, preserveAspectRatio));
                this.state.viewBox = symbol.viewBox;
            } else {
                Canvas canvas2 = this.canvas;
                SVG.Box box4 = this.state.viewPort;
                canvas2.translate(box4.minX, box4.minY);
            }
            boolean pushLayer = pushLayer();
            renderChildren(symbol, true);
            if (pushLayer) {
                popLayer(symbol);
            }
            updateParentBoundingBox(symbol);
        }
    }

    private void render(SVG.Image image) {
        SVG.Length length;
        String str;
        int i3 = 0;
        debug("Image render", new Object[0]);
        SVG.Length length2 = image.width;
        if (length2 != null && !length2.isZero() && (length = image.height) != null && !length.isZero() && (str = image.href) != null) {
            PreserveAspectRatio preserveAspectRatio = image.preserveAspectRatio;
            if (preserveAspectRatio == null) {
                preserveAspectRatio = PreserveAspectRatio.LETTERBOX;
            }
            Bitmap checkForImageDataURL = checkForImageDataURL(str);
            if (checkForImageDataURL == null) {
                SVGExternalFileResolver fileResolver = SVG.getFileResolver();
                if (fileResolver != null) {
                    checkForImageDataURL = fileResolver.resolveImage(image.href);
                } else {
                    return;
                }
            }
            if (checkForImageDataURL == null) {
                error("Could not locate image '%s'", image.href);
                return;
            }
            SVG.Box box = new SVG.Box(0.0f, 0.0f, (float) checkForImageDataURL.getWidth(), (float) checkForImageDataURL.getHeight());
            updateStyleForElement(this.state, image);
            if (display() && visible()) {
                Matrix matrix = image.transform;
                if (matrix != null) {
                    this.canvas.concat(matrix);
                }
                SVG.Length length3 = image.f6521x;
                float floatValueX = length3 != null ? length3.floatValueX(this) : 0.0f;
                SVG.Length length4 = image.f6522y;
                this.state.viewPort = new SVG.Box(floatValueX, length4 != null ? length4.floatValueY(this) : 0.0f, image.width.floatValueX(this), image.height.floatValueX(this));
                if (!this.state.style.overflow.booleanValue()) {
                    SVG.Box box2 = this.state.viewPort;
                    setClipRect(box2.minX, box2.minY, box2.width, box2.height);
                }
                image.boundingBox = this.state.viewPort;
                updateParentBoundingBox(image);
                checkForClipPath(image);
                boolean pushLayer = pushLayer();
                viewportFill();
                this.canvas.save();
                this.canvas.concat(calculateViewBoxTransform(this.state.viewPort, box, preserveAspectRatio));
                if (this.state.style.imageRendering != SVG.Style.RenderQuality.optimizeSpeed) {
                    i3 = 2;
                }
                this.canvas.drawBitmap(checkForImageDataURL, 0.0f, 0.0f, new Paint(i3));
                this.canvas.restore();
                if (pushLayer) {
                    popLayer(image);
                }
            }
        }
    }
}
