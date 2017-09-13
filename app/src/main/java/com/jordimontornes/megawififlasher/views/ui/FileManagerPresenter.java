package com.jordimontornes.megawififlasher.views.ui;

import com.jordimontornes.megawififlasher.domain.FileContentProvider;
import com.jordimontornes.megawififlasher.views.viewholder.FileItemData;

/**
 * Created by JordiM on 30/05/2017.
 */

public class FileManagerPresenter {

    private final FileContentProvider fileContentProvider;
    private FileManagerListener fileManagerListener;

    public FileManagerPresenter(FileContentProvider fileContentProvider) {
        this.fileContentProvider = fileContentProvider;
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
                fileContentProvider.updateCurrentDir(fullPath);
                fileManagerListener.setDirectoryPath(fullPath);
                fileManagerListener.onClickDirectory();
            } else {
                if(fileItemData.isSegaRom()) {
                    fileManagerListener.onClickSegaFile(fileContentProvider.getFileData(fileItemData.getName()));
                } else {
                    fileManagerListener.onClickCommonFile();
                }
            }
        }
    }

    public FileItemData[] retrieveDirectoryContents() {
        return fileContentProvider.getDirContents();
    }
}
