package com.jordimontornes.megawififlasher.views.ui;

import com.jordimontornes.megawififlasher.domain.DirectoryContentProvider;
import com.jordimontornes.megawififlasher.views.viewholder.FileItemData;

/**
 * Created by JordiM on 30/05/2017.
 */

public class FileManagerPresenter {

    private final DirectoryContentProvider directoryContentProvider;
    private FileManagerListener fileManagerListener;

    public FileManagerPresenter(DirectoryContentProvider directoryContentProvider) {
        this.directoryContentProvider = directoryContentProvider;
    }

    public void attachView(FileManagerListener fileManagerListener) {
        this.fileManagerListener = fileManagerListener;
    }

    public void detachView() {
        fileManagerListener = null;
    }

    public void itemClick(FileItemData fileItemData) {
        String fullPath = fileItemData.getFullPath();
        if (fileManagerListener != null) {
            if (fileItemData.isDirectory()) {
                directoryContentProvider.updateCurrentDir(fullPath);
                fileManagerListener.setDirectoryPath(fullPath);
                fileManagerListener.onClickDirectory();
            } else {
                if(fileItemData.isSegaRom()) {
                    fileManagerListener.onClickSegaFile();
                } else {
                    fileManagerListener.onClickCommonFile();
                }
            }
        }
    }

    public FileItemData[] retrieveDirectoryContents() {
        return directoryContentProvider.getContents();
    }
}
