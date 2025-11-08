package com.phrase.android.sdk.inject;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.IdRes;
import androidx.annotation.MenuRes;
import androidx.annotation.StringRes;
import com.phrase.android.sdk.R;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001\fB\u000f\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\u0018\u0010\u0007\u001a\u00020\u00062\b\b\u0001\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004¨\u0006\r"}, d2 = {"Lcom/phrase/android/sdk/inject/PhraseMenuParser;", "", "", "menuRes", "Landroid/view/Menu;", "menu", "", "parseAndApply", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "a", "sdk_release"}, k = 1, mv = {1, 7, 1})
public final class PhraseMenuParser {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f7265a;

    /* renamed from: b  reason: collision with root package name */
    public final int f7266b = -1;

    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public final int f7267a;

        /* renamed from: b  reason: collision with root package name */
        public final int f7268b;

        /* renamed from: c  reason: collision with root package name */
        public final int f7269c;

        public a(@IdRes int i3, @StringRes int i4, @StringRes int i5) {
            this.f7267a = i3;
            this.f7268b = i4;
            this.f7269c = i5;
        }

        public final int a() {
            return this.f7267a;
        }

        public final int b() {
            return this.f7268b;
        }

        public final int c() {
            return this.f7269c;
        }

        public final boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.f7267a == aVar.f7267a && this.f7268b == aVar.f7268b && this.f7269c == aVar.f7269c;
        }

        public final int hashCode() {
            return Integer.hashCode(this.f7269c) + androidx.compose.animation.core.a.c(this.f7268b, Integer.hashCode(this.f7267a) * 31, 31);
        }

        @NotNull
        public final String toString() {
            StringBuilder sb = new StringBuilder("IdsPack(id=");
            sb.append(this.f7267a);
            sb.append(", title=");
            sb.append(this.f7268b);
            sb.append(", titleCondensed=");
            return android.support.v4.media.session.a.p(sb, this.f7269c, ')');
        }
    }

    public PhraseMenuParser(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f7265a = context;
    }

    public final void parseAndApply(@MenuRes int i3, @NotNull Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        Context context = this.f7265a;
        ArrayList arrayList = new ArrayList();
        XmlResourceParser xmlResourceParser = null;
        try {
            XmlResourceParser layout = context.getResources().getLayout(i3);
            AttributeSet asAttributeSet = Xml.asAttributeSet(layout);
            for (int eventType = layout.getEventType(); eventType != 1; eventType = layout.next()) {
                if (layout.getEventType() == 2 && Intrinsics.areEqual((Object) layout.getName(), (Object) "item")) {
                    TypedArray obtainStyledAttributes = context.obtainStyledAttributes(asAttributeSet, R.styleable.MenuItem);
                    Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "context.obtainStyledAttr…rs, R.styleable.MenuItem)");
                    int resourceId = obtainStyledAttributes.getResourceId(R.styleable.MenuItem_android_id, this.f7266b);
                    int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.MenuItem_android_title, this.f7266b);
                    int resourceId3 = obtainStyledAttributes.getResourceId(R.styleable.MenuItem_android_titleCondensed, this.f7266b);
                    int i4 = this.f7266b;
                    if (!(resourceId == i4 || (resourceId2 == i4 && resourceId3 == i4))) {
                        arrayList.add(new a(resourceId, resourceId2, resourceId3));
                    }
                    Unit unit = Unit.INSTANCE;
                    obtainStyledAttributes.recycle();
                }
            }
            layout.close();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                MenuItem findItem = menu.findItem(aVar.a());
                if (findItem != null) {
                    Intrinsics.checkNotNullExpressionValue(findItem, "findItem(pack.id)");
                    if (aVar.b() != this.f7266b) {
                        findItem.setTitle(this.f7265a.getResources().getText(aVar.b()));
                    }
                    if (aVar.c() != this.f7266b) {
                        findItem.setTitleCondensed(this.f7265a.getResources().getText(aVar.c()));
                    }
                }
            }
        } catch (Throwable th) {
            if (xmlResourceParser != null) {
                xmlResourceParser.close();
            }
            throw th;
        }
    }
}
