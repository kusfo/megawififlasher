package com.jordimontornes.megawififlasher.views.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jordimontornes.megawififlasher.R;
import com.jordimontornes.megawififlasher.views.ui.FileManagerPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jordimontornes on 21/04/2017.
 */

public class FileItemAdapter  extends RecyclerView.Adapter<FileItemViewHolder> {

    private final FileManagerPresenter fileManagerPresenter;
    private List<FileItemData> fileItemDataArray;

    public FileItemAdapter(FileManagerPresenter fileManagerPresenter) {
        this.fileItemDataArray = new ArrayList<>();
        this.fileManagerPresenter = fileManagerPresenter;
    }

    @Override
    public FileItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View fileItemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.file_item_layout, null);
        return new FileItemViewHolder(fileItemLayoutView, fileManagerPresenter);
    }

    @Override
    public void onBindViewHolder(FileItemViewHolder viewHolder, int position) {
        FileItemData fileItemData = fileItemDataArray.get(position);
        viewHolder.render(fileItemData);
    }

    @Override
    public int getItemCount() {
        return fileItemDataArray.size();
    }

    public void setFileItemDataArray(List<FileItemData> fileItemDataArray) {
        this.fileItemDataArray = fileItemDataArray;
    }
}
