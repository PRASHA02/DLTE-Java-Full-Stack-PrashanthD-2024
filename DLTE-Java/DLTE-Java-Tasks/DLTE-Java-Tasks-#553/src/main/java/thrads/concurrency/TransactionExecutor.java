package thrads.concurrency;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class TransactionExecutor {
    public static void main(String[] args) {
        TransactionClass transactionAnalysis = new TransactionClass();
        Executor executor = Executors.newCachedThreadPool();
        executor.execute(transactionAnalysis);
        ThreadPoolExecutor poolExecutor= (ThreadPoolExecutor) executor;
        poolExecutor.shutdown();
    }

}
