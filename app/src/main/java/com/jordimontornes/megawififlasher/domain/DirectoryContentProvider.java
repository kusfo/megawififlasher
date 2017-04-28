package com.jordimontornes.megawififlasher.domain;

import com.jordimontornes.megawififlasher.views.viewholder.FileItemData;

import java.io.File;

/**
 * Created by jordimontornes on 21/04/2017.
 */

public class DirectoryContentProvider {

    private File currentDir;

    public DirectoryContentProvider() {
        this.currentDir = new File("/sdcard/");
    }

    public FileItemData[] getContents() {
        File[] files = currentDir.listFiles();
        FileItemData[] fileItemDataArray = new FileItemData[files.length];
        for(int i = 0; i < files.length; i++) {
            fileItemDataArray[i] = new FileItemData(files[i].getName(), files[i].isDirectory());
        }
        return fileItemDataArray;
    }
}
