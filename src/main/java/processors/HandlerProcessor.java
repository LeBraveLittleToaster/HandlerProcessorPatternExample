package processors;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class HandlerProcessor {

  ExecutorService executorService = Executors.newFixedThreadPool(10);

  public <T> T runHandlerSync(IHandler<T> handler, int timeoutSeconds)
      throws ExecutionException, InterruptedException, TimeoutException {
    var resultFuture = executorService.submit(() -> processHandler(handler));
    return resultFuture.get(timeoutSeconds, TimeUnit.SECONDS);
  }

  public <T> Future<T> runHandlerAsync(IHandler<T> handler)  {
    return executorService.submit(() -> processHandler(handler));
  }

  private <T> T processHandler(IHandler<T> handler) {
    handler.prepare();
    var result = handler.process();
    handler.cleanup();
    return result;
  }

  public void close(){
    executorService.shutdownNow();
  }
}
