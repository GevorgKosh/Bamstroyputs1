package com.the.bamstroyputs.loader;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.the.bamstroyputs.R;

public class Loader extends ConstraintLayout {
    private ImageView logoImage;
    private Animation objectAnimator;

    public Loader(Context context) {
        super(context);
        init(context);
    }

    public Loader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public Loader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.loader, null);
        logoImage = view.findViewById(R.id.loaderImageView);
        addView(view,new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    @Override
    public void setVisibility(int visibility) {
        setAnimation(visibility);
        super.setVisibility(visibility);
    }

    private void setAnimation(int visibility) {
        if (objectAnimator == null) {
            objectAnimator = AnimationUtils.loadAnimation(getContext(),R.anim.rot);
        }
        if (visibility == INVISIBLE || visibility == GONE){
            objectAnimator.cancel();
            objectAnimator = null;
            return;
        }

        // Aply animation to image view
        logoImage.startAnimation(objectAnimator);
    }
}
