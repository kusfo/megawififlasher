package com.jordimontornes.megawififlasher.views.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jordimontornes.megawififlasher.R;
import com.jordimontornes.megawififlasher.views.ui.FileManagerPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JordiM on 30/05/2017.
 */
public class FileItemViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.file_name)
    TextView fileName;
    @BindView(R.id.file_icon)
    ImageView fileIcon;
    private final FileManagerPresenter fileManagerPresenter;

    public FileItemViewHolder(View itemLayoutView, FileManagerPresenter fileManagerPresenter) {
        super(itemLayoutView);
        ButterKnife.bind(this, itemLayoutView);
        this.fileManagerPresenter = fileManagerPresenter;
    }

    public void render(FileItemData fileItemData) {
        attachListeners(fileItemData);
        fileName.setText(fileItemData.getName());
        fileIcon.setImageResource(fileItemData.isDirectory() ? R.drawable.ic_folder_black_24dp : R.drawable.ic_file_black_24dp);
    }

    private void attachListeners(final FileItemData fileItemData) {
        itemView.setOnClickListener( new View.OnClickListener() {
            @Override public void onClick(View v) {
                fileManagerPresenter.click(fileItemData);
            }
        });
    }

}
