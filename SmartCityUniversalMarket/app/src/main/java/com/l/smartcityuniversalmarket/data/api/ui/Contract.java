package com.l.smartcityuniversalmarket.data.api.ui;

public interface Contract {
    interface View {
        void showData(String data);

        void showError(String message);

        void showProgress(boolean isInProgress);
    }

    interface Presenter {
        void onReady();
    }
}
