package com.appsamurai.storyly.config.styling;

import android.graphics.Color;
import com.appsamurai.storyly.data.c;
import org.jetbrains.annotations.NotNull;

public enum a {
    COLOR_189FFF("#189FFF"),
    COLOR_434343("#434343"),
    COLOR_EFEFEF("#EFEFEF"),
    COLOR_16C898("#16C898"),
    COLOR_FE3F9C("#FE3F9C"),
    COLOR_262626("#262626"),
    COLOR_141414("#141414"),
    COLOR_6A6A6A("#6A6A6A"),
    COLOR_FF4D50("#FF4D50"),
    COLOR_51C41A("#51C41A"),
    COLOR_7A7A7A("#7A7A7A"),
    COLOR_F1F1F1("#F1F1F1"),
    COLOR_BFBFBF("#BFBFBF"),
    COLOR_C4C4C4("#C4C4C4"),
    COLOR_3D3D3D("#3D3D3D"),
    COLOR_E0E0E0("#E0E0E0"),
    COLOR_ADADAD("#ADADAD"),
    COLOR_212121("#212121"),
    COLOR_FFB8B9("#FFB8B9"),
    COLOR_F5F5F5("#F5F5F5"),
    COLOR_FFFFFF("#FFFFFF"),
    COLOR_616161("#616161"),
    COLOR_EEEEEE("#EEEEEE"),
    COLOR_9E9E9E("#9E9E9E"),
    COLOR_424242("#424242"),
    COLOR_757575("#757575"),
    COLOR_F7F7F7("#F7F7F7"),
    COLOR_F87825("#F87825"),
    COLOR_FED169("#FED169"),
    COLOR_FA7C20("#FA7C20"),
    COLOR_C9287B("#C9287B"),
    COLOR_962EC2("#962EC2"),
    COLOR_DBDBDB("#DBDBDB"),
    COLOR_F26645("#F26645"),
    COLOR_FFC75C("#FFC75C"),
    COLOR_7AC7A3("#7AC7A3"),
    COLOR_4DC2D9("#4DC2D9"),
    COLOR_94638C("#94638C"),
    COLOR_B900B4("#B900B4"),
    COLOR_F50000("#F50000"),
    COLOR_66FFFFFF("#66FFFFFF"),
    COLOR_99FFFFFF("#99FFFFFF"),
    COLOR_CCFFFFFF("#CCFFFFFF"),
    COLOR_CC000000("#CC000000"),
    COLOR_CCCCCCCC("#CCCCCCCC"),
    COLOR_E64C4C4C("#E64C4C4C"),
    COLOR_AD27FF("#AD27FF");
    
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f3589a;

    /* access modifiers changed from: public */
    a(String str) {
        this.f3589a = str;
    }

    public final int a() {
        return Color.parseColor(this.f3589a);
    }

    @NotNull
    public final c b() {
        return new c(Color.parseColor(this.f3589a));
    }
}
