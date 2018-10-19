package com.chen.ellen.im.core.process;

import java.util.ArrayList;
import java.util.List;

public class DaemonTaskManager {

    private List<DaemonTask> daemonServices = new ArrayList<>();

    public void addTask(DaemonTask task) {
        daemonServices.add(task);
    }

    public void startTasks() {
        for (DaemonTask task : daemonServices) {
            task.setDaemon(true);
            task.start();
        }
    }
}
