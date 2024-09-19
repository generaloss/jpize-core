package jpize.app;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BooleanSupplier;

public class SyncExecutor {

    private final List<Task> list;
    private final Queue<Runnable> queue;
    private int tasks;

    public SyncExecutor() {
        this.list = new CopyOnWriteArrayList<>();
        this.queue = new ConcurrentLinkedQueue<>();
    }


    protected void sync() {
        list.removeIf(Task::tryToExec);
        while(!queue.isEmpty())
            queue.poll().run();
    }

    public void exec(Runnable runnable) {
        queue.add(runnable);
    }

    public void execLater(long delayMillis, Runnable runnable) {
        final long time = System.currentTimeMillis();
        list.add(new Task(runnable, () -> System.currentTimeMillis() - time > delayMillis));
    }

    public void execLater(double delaySeconds, Runnable runnable) {
        execLater(Math.round(delaySeconds * 1000D), runnable);
    }


    public void execWhen(BooleanSupplier condition, Runnable runnable) {
        list.add(new Task(runnable, condition));
    }


    private static class Task {

        private final Runnable runnable;
        private final BooleanSupplier runCondition;

        public Task(Runnable runnable, BooleanSupplier runCondition) {
            this.runnable = runnable;
            this.runCondition = runCondition;
        }

        public boolean tryToExec() {
            if(!runCondition.getAsBoolean())
                return false;

            runnable.run();
            return true;
        }

    }

}
