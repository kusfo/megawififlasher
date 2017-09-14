package com.jordimontornes.megawififlasher.data;

import rx.Observable;

public class NetworkActions {

    public Observable<NetworkResult> openConnection(String ip, String port) {
        NetworkResult networkResult = new NetworkResult();
        networkResult.responseCode = "OK";
        return Observable.just(networkResult);
    }

    public void sendByte(int byteToSend) {

    }

    public void closeConnection() {

    }
}
