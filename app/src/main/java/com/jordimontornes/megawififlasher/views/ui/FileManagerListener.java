package com.jordimontornes.megawififlasher.views.ui;

import java.util.Map;

public interface FileManagerListener {
    void onClickCommonFile();
    void onClickSegaFile(Map<String, String> filedata);
    void onClickDirectory();
    void setDirectoryPath(String fullPath);
}
