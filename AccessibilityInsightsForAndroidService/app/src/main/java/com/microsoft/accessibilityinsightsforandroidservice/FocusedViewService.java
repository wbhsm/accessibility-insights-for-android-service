package com.microsoft.accessibilityinsightsforandroidservice;

import java.util.Calendar;

import io.grpc.stub.StreamObserver;

class FocusedViewService extends FocusedViewServiceGrpc.FocusedViewServiceImplBase {

    FocusedViewService(){}

    @Override
    public void getFocusedView(FocusedViewProto.GetFocusedViewRequest request, StreamObserver<FocusedViewProto.FocusedView> responseObserver) {
        responseObserver.onNext(GetFocusedView((request)));
        responseObserver.onCompleted();
    }

    private FocusedViewProto.FocusedView GetFocusedView(FocusedViewProto.GetFocusedViewRequest request) {
        String timePosingAsType = Calendar.getInstance().getTime().toString();
        return FocusedViewProto.FocusedView.newBuilder().setViewType(timePosingAsType).build();
    }
}
