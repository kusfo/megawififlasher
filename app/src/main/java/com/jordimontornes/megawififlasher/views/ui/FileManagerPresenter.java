package com.jordimontornes.megawififlasher.views.ui;

import android.util.Log;

import com.jordimontornes.megawififlasher.views.viewholder.FileItemData;

/**
 * Created by JordiM on 30/05/2017.
 */

public class FileManagerPresenter {
    public void click(FileItemData fileItemData) {
        Log.i("Testing","Clicked on Fileitem " + fileItemData.getName());
    }
}
