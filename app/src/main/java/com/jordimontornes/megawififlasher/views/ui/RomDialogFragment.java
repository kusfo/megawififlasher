package com.jordimontornes.megawififlasher.views.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.jordimontornes.megawififlasher.R;

public class RomDialogFragment extends DialogFragment {

    private RomDialogFragmentInterface romDialogFragmentListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_sega_rom)
                .setPositiveButton(R.string.common_yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        romDialogFragmentListener.onYesRomClick();
                    }
                })
                .setNegativeButton(R.string.common_no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        romDialogFragmentListener.onNoRomClick();
                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            romDialogFragmentListener = (RomDialogFragmentInterface) context;
        } catch (ClassCastException exception) {
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    public interface RomDialogFragmentInterface {
        void onYesRomClick();
        void onNoRomClick();
    }
}
