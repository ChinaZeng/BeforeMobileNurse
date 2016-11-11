package com.shine.mobilenurse.function.common;

import android.view.View;
import android.view.WindowManager;

import com.shine.mobilenurse.R;
import com.shine.mobilenurse.base.BaseActivity;
import com.shine.mobilenurse.image.ImageLoadClass;
import com.shine.mobilenurse.utils.ViewUtil;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by zzw on 2016/11/11.
 * 描述:
 */

public class ImageActivity extends BaseActivity implements PhotoViewAttacher.OnPhotoTapListener {

    private PhotoView photoView;
    public static final String LOCAL_PATH="local_path";

    @Override
    protected int getLayoutId() {
        toggleFullscreen(true);
        return R.layout.activity_image;
    }


    @Override
    protected void findViewId() {
        super.findViewId();
        photoView= ViewUtil.$(this,R.id.popView);
        photoView.setOnPhotoTapListener(this);
    }

    @Override
    protected void initView() {
        super.initView();
        String path= getIntent().getStringExtra(LOCAL_PATH);
        ImageLoadClass.loadImage(this,path,photoView);
    }

    @Override
    public void onPhotoTap(View view, float x, float y) {
        ImageActivity.this.finish();
    }


    private void toggleFullscreen(boolean fullScreen) {
        // fullScreen为true时全屏，否则相反
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        if (fullScreen) {
            attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        } else {
            attrs.flags &= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        }
        getWindow().setAttributes(attrs);
    }

}
