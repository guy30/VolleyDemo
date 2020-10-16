package com.demo.volleydemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.Nullable;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by Administrator on 2019/12/26
 */

public class XBannerView extends BGABanner {
    private Context context;

    public XBannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public XBannerView(final Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }

    public void fillData(List<String> links) {
        setAdapter(new Adapter() {
            @Override
            public void fillBannerItem(BGABanner banner, View itemView, @Nullable Object model, int position) {
                if (model != null && !"".equals(model)) {
                    Glide.with(context)
                            .load(model)
                            .into((ImageView) itemView);
                }
            }
        });
        setData(links, null);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        float radius = dpToPixel(8);
        Path path = new Path();
        path.moveTo(0, radius);
        path.arcTo(new RectF(0, 0, radius * 2, radius * 2), -180, 90);
        path.lineTo(width - radius, 0);
        path.arcTo(new RectF(width - 2 * radius, 0, width, radius * 2), -90, 90);
        path.lineTo(width, height - radius);
        path.arcTo(new RectF(width - 2 * radius, height - 2 * radius, width, height), 0, 90);
        path.lineTo(radius, height);
        path.arcTo(new RectF(0, height - 2 * radius, radius * 2, height), 90, 90);
        path.close();
        canvas.clipPath(path);
        super.dispatchDraw(canvas);
    }

    public float dpToPixel(float dp) {
        return dp * (getDisplayMetrics().densityDpi / 160F);
    }

    public DisplayMetrics getDisplayMetrics() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay()
                .getMetrics(displaymetrics);
        return displaymetrics;
    }

}
