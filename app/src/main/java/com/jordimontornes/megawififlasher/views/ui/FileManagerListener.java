package com.jordimontornes.megawififlasher.views.ui;

public interface FileManagerListener {
    void onClickCommonFile();
    void onClickSegaFile();
    void onClickDirectory();
    void setDirectoryPath(String fullPath);
}
