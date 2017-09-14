package com.jordimontornes.megawififlasher.domain;

import com.jordimontornes.megawififlasher.data.NetworkActions;
import com.jordimontornes.megawififlasher.data.NetworkResult;
import com.jordimontornes.megawififlasher.views.ui.FileProgressListener;

import java.io.FileInputStream;
import java.io.IOException;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MegaWifiComunicator {

    private final NetworkActions networkActions;

    public MegaWifiComunicator(NetworkActions networkActions) {
        this.networkActions = networkActions;
    }

    public void sendEraseRom(FileProgressListener fileProgressListenerprogressListener) {
        Observable<NetworkResult> networkResultObservable = networkActions.openConnection("192.168.1.1", "89");
        networkResultObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
        //networkActions.sendByte();
        networkActions.closeConnection();
    }

    public void sendFile(FileProgressListener fileProgressListenerprogressListener, FileInputStream inputStream) {
        try {
            int value = inputStream.read();
            while(value != -1) {
                value = inputStream.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void startUp() {

    }
}
