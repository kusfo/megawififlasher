package com.jordimontornes.megawififlasher.views.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jordimontornes.megawififlasher.R;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RomDialogFragment extends DialogFragment {

    private RomDialogFragmentInterface romDialogFragmentListener;
    private Map<String, String> romFileData;

    @BindView(R.id.rom_name)
    TextView romName;

    @BindView(R.id.rom_size)
    TextView romSize;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View dialogView = layoutInflater.inflate(R.layout.file_dialog_layout, null);
        ButterKnife.bind(this, dialogView);
        romName.setText(romFileData.get("name"));
        romSize.setText(romFileData.get("size"));
        builder.setTitle(R.string.dialog_sega_rom)
                .setView(dialogView)
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

    public void setRomFileData(Map<String, String> romFileData) {
        this.romFileData = romFileData;
    }

    public interface RomDialogFragmentInterface {
        void onYesRomClick();
        void onNoRomClick();
    }
}
