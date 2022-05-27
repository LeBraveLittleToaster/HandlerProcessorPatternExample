package processors;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import processors.handler.GetGameStateHandler;

public class Main {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    AtomicInteger handlerIdProvider = new AtomicInteger();

    var processor = new HandlerProcessor();

    GetGameStateHandler handler = new GetGameStateHandler("HelloWorld", handlerIdProvider.incrementAndGet());
    try {
      processor.runHandlerSync(handler, 6000);
    } catch (ExecutionException | TimeoutException | InterruptedException e) {
      e.printStackTrace();
    }

    List<Future<String>> futures = new LinkedList<>();
    for(int i = 0; i < 5000; i++){
      var handlerLul = new GetGameStateHandler("HelloWorld", handlerIdProvider.incrementAndGet());
      futures.add(processor.runHandlerAsync(handlerLul));
    }

    for (Future<String> future : futures) {
      System.out.println("RESULT: " + future.get());
    }

    processor.close();
  }
}
