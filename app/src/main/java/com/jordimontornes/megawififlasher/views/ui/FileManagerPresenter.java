package com.jordimontornes.megawififlasher.views.ui;

import android.util.Log;

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
        if(fileItemData.isDirectory()) {
            directoryContentProvider.updateCurrentDir(fullPath);
            if(fileManagerListener != null) {
                fileManagerListener.onClickDirectory();
            }
        } else {
            Log.d("Presenter","This is a file: " + fullPath);
            if(fileManagerListener != null) {
                fileManagerListener.onClickFile();
            }
        }

    }

    public FileItemData[] retrieveDirectoryContents() {
        return directoryContentProvider.getContents();
    }
}
