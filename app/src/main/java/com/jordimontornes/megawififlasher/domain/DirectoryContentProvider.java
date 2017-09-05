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
        FileItemData[] fileItemDataArray = new FileItemData[files.length + 1];
        fileItemDataArray[0] = new FileItemData("..", currentDir.getAbsolutePath(),true);
        for(int i = 1; i <= files.length; i++) {
            fileItemDataArray[i] = new FileItemData(files[i - 1].getName(), currentDir.getAbsolutePath(),files[i - 1].isDirectory());
        }
        return fileItemDataArray;
    }

    public void updateCurrentDir(String currentDirPath) {
        this.currentDir = new File(currentDirPath);
    }
}
