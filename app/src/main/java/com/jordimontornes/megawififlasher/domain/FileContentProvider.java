package com.jordimontornes.megawififlasher.domain;

import com.jordimontornes.megawififlasher.views.viewholder.FileItemData;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jordimontornes on 21/04/2017.
 */

public class FileContentProvider {

    private File currentDir;

    public FileContentProvider() {
        this.currentDir = new File("/sdcard/");
    }

    public FileItemData[] getDirContents() {
        File[] files = currentDir.listFiles();
        FileItemData[] fileItemDataArray = new FileItemData[files.length + 1];
        if(currentDir.getParent() != null) {
            fileItemDataArray[0] = new FileItemData("..", currentDir.getParent(),true);
            for(int i = 1; i <= files.length; i++) {
                fileItemDataArray[i] = new FileItemData(files[i - 1].getName(), currentDir.getAbsolutePath(),files[i - 1].isDirectory());
            }
        } else {
            for(int i = 0; i < files.length; i++) {
                fileItemDataArray[i] = new FileItemData(files[i].getName(), currentDir.getAbsolutePath(),files[i].isDirectory());
            }
        }
        return fileItemDataArray;
    }

    public Map<String, String> getFileData(String filename) {
        Map<String,String> result = new HashMap<>();
        File file = new File(currentDir.getAbsolutePath() + "/" +  filename);
        result.put("name",file.getName());
        result.put("size",Long.toString(file.length() / 1024) + " Kb");
        return result;
    }

    public void updateCurrentDir(String currentDirPath) {
        this.currentDir = new File(currentDirPath);
    }
}
