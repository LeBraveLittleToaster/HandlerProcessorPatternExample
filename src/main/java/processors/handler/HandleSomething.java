package processors.handler;

import processors.IHandler;
import processors.rtypses.HandleSomethingResult;

public class HandleSomething implements IHandler<HandleSomethingResult> {

  @Override
  public void prepare() {

  }

  @Override
  public HandleSomethingResult process() {
    return null;
  }

  @Override
  public void cleanup() {

  }
}
