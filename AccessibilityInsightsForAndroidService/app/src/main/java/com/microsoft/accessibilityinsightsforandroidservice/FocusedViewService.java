package com.microsoft.accessibilityinsightsforandroidservice;

import java.util.Calendar;

import io.grpc.stub.StreamObserver;

class FocusedViewService extends FocusedViewServiceGrpc.FocusedViewServiceImplBase {

    FocusedViewService(){}

    @Override
    public void getFocusedView(FocusedViewProto.Empty request, StreamObserver<FocusedViewProto.FocusedView> responseObserver) {
        while(true) {
            responseObserver.onNext(GetFocusedView(request));
        }
    }

    private FocusedViewProto.FocusedView GetFocusedView(FocusedViewProto.Empty request) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String timePosingAsType = Calendar.getInstance().getTime().toString();
        return FocusedViewProto.FocusedView.newBuilder().setViewType(timePosingAsType).build();
    }
}
