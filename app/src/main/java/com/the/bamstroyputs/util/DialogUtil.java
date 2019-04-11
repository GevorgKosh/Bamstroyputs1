package com.the.bamstroyputs.util;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.the.bamstroyputs.R;
import com.the.bamstroyputs.interfaces.OnEditDialogClickListener;
import com.the.bamstroyputs.interfaces.OnEditDialogTwoLinesClickListener;

public class DialogUtil {
//    public static void showBlackErrorDialog(Context context, String msg) {
//        new AlertDialog.Builder(context)
//                .setTitle(context.getResources().getString(R.string.error))
//                .setMessage(msg)
//                .setPositiveButton(context.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.dismiss();
//                    }
//                })
//                .show();
//    }
//
//    public static void showBlackSuccessDialog(Context context, String msg) {
//        new AlertDialog.Builder(context)
//                .setTitle(context.getResources().getString(R.string.success))
//                .setMessage(msg)
//                .setPositiveButton(context.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.dismiss();
//                    }
//                })
//                .show();
//    }

    public static void showDialog(final Context context, String dialogTitle, String nameHint, int resId, final OnEditDialogClickListener listener){
        // TODO DataBindingUtil binding = DataBindingUtil.inflate();

        final Dialog dialog = new Dialog(context);

        dialog.setContentView(resId);
        //dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //dialog.getWindow().setLayout((6 * 500)/7, (4 * 500)/5);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        final TextView dialog_title = dialog.findViewById(R.id.dialog_title);
        dialog_title.setText(dialogTitle);
        final TextInputLayout edProjectLayout = dialog.findViewById(R.id.edProjectLayout);
        edProjectLayout.setHint(nameHint);
        final TextInputEditText name = dialog.findViewById(R.id.edProjectName);
        final TextView btnCreate = dialog.findViewById(R.id.btnCreate);
        final TextView btnCancel = dialog.findViewById(R.id.btnCancel);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSaveName(name.getText().toString());
                dialog.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public static void showTwoLinesDialog(final Context context, String dialogTitle, String nameHint, int resId, String countHint,
                                          final OnEditDialogTwoLinesClickListener listener){
        // TODO DataBindingUtil binding = DataBindingUtil.inflate(); Design isn`t full

        final Dialog dialog = new Dialog(context);

        dialog.setContentView(resId);
        //dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //dialog.getWindow().setLayout((6 * 500)/7, (4 * 500)/5);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        final TextInputLayout edProjectName2 = dialog.findViewById(R.id.edProjectName2);
        edProjectName2.setHint(nameHint);
        final TextInputLayout edProjectCount = dialog.findViewById(R.id.edProjectCount);
        edProjectCount.setHint(countHint);
        final TextInputEditText name = dialog.findViewById(R.id.edName);
        name.setHint(nameHint);
        final TextInputEditText count = dialog.findViewById(R.id.edCount);
        count.setHint(countHint);
        final TextView title = dialog.findViewById(R.id.dialog_title);
        title.setText(dialogTitle);
        final TextView btnCreate = dialog.findViewById(R.id.btnCreate);
        final TextView btnCancel = dialog.findViewById(R.id.btnCancel);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSaveNameAndCount(name.getText().toString(), count.getText().toString());
                dialog.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
