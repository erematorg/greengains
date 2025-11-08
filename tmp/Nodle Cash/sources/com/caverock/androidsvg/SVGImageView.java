package com.caverock.androidsvg;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import androidx.constraintlayout.core.state.b;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

public class SVGImageView extends ImageView {
    private static Method setLayerTypeMethod;
    private RenderOptions renderOptions = new RenderOptions();
    /* access modifiers changed from: private */
    public SVG svg = null;

    public class LoadResourceTask extends AsyncTask<Integer, Integer, SVG> {
        private Context context;
        private int resourceId;

        public LoadResourceTask(Context context2, int i3) {
            this.context = context2;
            this.resourceId = i3;
        }

        public SVG doInBackground(Integer... numArr) {
            try {
                return SVG.getFromResource(this.context, this.resourceId);
            } catch (SVGParseException e3) {
                Log.e("SVGImageView", String.format("Error loading resource 0x%x: %s", new Object[]{Integer.valueOf(this.resourceId), e3.getMessage()}));
                return null;
            }
        }

        public void onPostExecute(SVG svg) {
            SVG unused = SVGImageView.this.svg = svg;
            SVGImageView.this.doRender();
        }
    }

    public class LoadURITask extends AsyncTask<InputStream, Integer, SVG> {
        private LoadURITask() {
        }

        public SVG doInBackground(InputStream... inputStreamArr) {
            try {
                SVG fromInputStream = SVG.getFromInputStream(inputStreamArr[0]);
                try {
                    inputStreamArr[0].close();
                } catch (IOException unused) {
                }
                return fromInputStream;
            } catch (SVGParseException e3) {
                Log.e("SVGImageView", "Parse error loading URI: " + e3.getMessage());
                try {
                    inputStreamArr[0].close();
                    return null;
                } catch (IOException unused2) {
                    return null;
                }
            } catch (Throwable th) {
                try {
                    inputStreamArr[0].close();
                } catch (IOException unused3) {
                }
                throw th;
            }
        }

        public void onPostExecute(SVG svg) {
            SVG unused = SVGImageView.this.svg = svg;
            SVGImageView.this.doRender();
        }
    }

    static {
        try {
            setLayerTypeMethod = View.class.getMethod("setLayerType", new Class[]{Integer.TYPE, Paint.class});
        } catch (NoSuchMethodException unused) {
        }
    }

    public SVGImageView(Context context) {
        super(context);
    }

    /* access modifiers changed from: private */
    public void doRender() {
        SVG svg2 = this.svg;
        if (svg2 != null) {
            Picture renderToPicture = svg2.renderToPicture(this.renderOptions);
            setSoftwareLayerType();
            setImageDrawable(new PictureDrawable(renderToPicture));
        }
    }

    private void init(AttributeSet attributeSet, int i3) {
        if (!isInEditMode()) {
            TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(attributeSet, R.styleable.SVGImageView, i3, 0);
            try {
                String string = obtainStyledAttributes.getString(R.styleable.SVGImageView_css);
                if (string != null) {
                    this.renderOptions.css(string);
                }
                int i4 = R.styleable.SVGImageView_svg;
                int resourceId = obtainStyledAttributes.getResourceId(i4, -1);
                if (resourceId != -1) {
                    setImageResource(resourceId);
                    obtainStyledAttributes.recycle();
                    return;
                }
                String string2 = obtainStyledAttributes.getString(i4);
                if (string2 != null) {
                    if (internalSetImageURI(Uri.parse(string2))) {
                        obtainStyledAttributes.recycle();
                        return;
                    } else if (internalSetImageAsset(string2)) {
                        obtainStyledAttributes.recycle();
                        return;
                    } else {
                        setFromString(string2);
                    }
                }
                obtainStyledAttributes.recycle();
            } catch (Throwable th) {
                obtainStyledAttributes.recycle();
                throw th;
            }
        }
    }

    private boolean internalSetImageAsset(String str) {
        try {
            new LoadURITask().execute(new InputStream[]{getContext().getAssets().open(str)});
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    private boolean internalSetImageURI(Uri uri) {
        try {
            new LoadURITask().execute(new InputStream[]{getContext().getContentResolver().openInputStream(uri)});
            return true;
        } catch (FileNotFoundException unused) {
            return false;
        }
    }

    private void setFromString(String str) {
        try {
            this.svg = SVG.getFromString(str);
            doRender();
        } catch (SVGParseException unused) {
            b.u("Could not find SVG at: ", str, "SVGImageView");
        }
    }

    private void setSoftwareLayerType() {
        if (setLayerTypeMethod != null) {
            try {
                setLayerTypeMethod.invoke(this, new Object[]{Integer.valueOf(View.class.getField("LAYER_TYPE_SOFTWARE").getInt(new View(getContext()))), null});
            } catch (Exception e3) {
                Log.w("SVGImageView", "Unexpected failure calling setLayerType", e3);
            }
        }
    }

    public void setCSS(String str) {
        this.renderOptions.css(str);
        doRender();
    }

    public void setImageAsset(String str) {
        if (!internalSetImageAsset(str)) {
            b.u("File not found: ", str, "SVGImageView");
        }
    }

    public void setImageResource(int i3) {
        new LoadResourceTask(getContext(), i3).execute(new Integer[0]);
    }

    public void setImageURI(Uri uri) {
        if (!internalSetImageURI(uri)) {
            Log.e("SVGImageView", "File not found: " + uri);
        }
    }

    public void setSVG(SVG svg2) {
        if (svg2 != null) {
            this.svg = svg2;
            doRender();
            return;
        }
        throw new IllegalArgumentException("Null value passed to setSVG()");
    }

    public SVGImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        init(attributeSet, 0);
    }

    public void setSVG(SVG svg2, String str) {
        if (svg2 != null) {
            this.svg = svg2;
            this.renderOptions.css(str);
            doRender();
            return;
        }
        throw new IllegalArgumentException("Null value passed to setSVG()");
    }

    public SVGImageView(Context context, AttributeSet attributeSet, int i3) {
        super(context, attributeSet, i3);
        init(attributeSet, i3);
    }
}
