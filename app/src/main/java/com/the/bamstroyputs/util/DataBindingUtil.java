package com.the.bamstroyputs.util;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

public class DataBindingUtil {

    @BindingAdapter("imageVisibility")
    public static void setImageVisibilty(View image, String number){
        if(number == null || number.equals("0")){
            image.setVisibility(View.GONE);
        }
    }

    @BindingAdapter({"iconRemainder", "iconNoRemains", "orderRemainder"})
    public static void changeIcon(ImageView image, Drawable iconRemainder, Drawable iconNoRemains, int remainder){
        if(remainder > 0){
            image.setImageDrawable(iconRemainder);
        }else {
            image.setImageDrawable(iconNoRemains);
        }
    }

//    @BindingAdapter("android:src")
//    public static void setImageUri(ImageView view, String imageUri) {
//        if (imageUri == null) {
//            view.setImageURI(null);
//        } else {
//            view.setImageURI(Uri.parse(imageUri));
//        }
//    }
//
//    @BindingAdapter("android:src")
//    public static void setImageUri(ImageView view, Uri imageUri) {
//        view.setImageURI(imageUri);
//    }
//
//    @BindingAdapter("android:src")
//    public static void setImageDrawable(ImageView view, Drawable drawable) {
//        view.setImageDrawable(drawable);
//    }
//
//    @BindingAdapter("android:src")
//    public static void setImageResource(ImageView imageView, int resource){
//        imageView.setImageResource(resource);
//    }
}
