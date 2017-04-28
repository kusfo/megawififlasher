package com.jordimontornes.megawififlasher.views.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jordimontornes.megawififlasher.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jordimontornes on 21/04/2017.
 */

public class FileItemAdapter  extends RecyclerView.Adapter<FileItemAdapter.FileItemViewHolder>{

    private FileItemData[] fileItemDataArray;

    public FileItemAdapter(FileItemData[] fileItemDataArray) {
        this.fileItemDataArray = fileItemDataArray;
    }

    @Override
    public FileItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View fileItemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.file_item_layout, null);

        return new FileItemViewHolder(fileItemLayoutView);
    }

    @Override
    public void onBindViewHolder(FileItemViewHolder viewHolder, int position) {
        viewHolder.fileName.setText(fileItemDataArray[position].getName());
        viewHolder.fileIcon.setImageResource(fileItemDataArray[position].isDirectory() ? R.drawable.ic_folder_black_24dp : R.drawable.ic_file_black_24dp);
    }

    @Override
    public int getItemCount() {
        return fileItemDataArray.length;
    }

    public static class FileItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.file_name) TextView fileName;
        @BindView(R.id.file_icon) ImageView fileIcon;

        public FileItemViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            ButterKnife.bind(this, itemLayoutView);
        }

    }
}
