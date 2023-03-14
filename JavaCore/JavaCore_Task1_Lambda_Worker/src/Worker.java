public class Worker {
    private OnTaskDoneListener callback;
    private OnTaskErrorListener errorCallback;

    public Worker(OnTaskDoneListener callback, OnTaskErrorListener errorCallback) {
        this.callback = callback;
        this.errorCallback = errorCallback;
    }

    public void start(int errorTask) {
        for (int i = 0; i < 100; i++) {
            if (i == errorTask) {
                errorCallback.onError("Task " + errorTask + " not completed");
            } else {
                callback.onDone("Task " + i + " is done");
            }
        }
    }
}
