package processors.handler;

import processors.IHandler;

public class GetGameStateHandler implements IHandler<String> {

  private final String name;
  private final int handlerId;

  public GetGameStateHandler(String name, int handlerId) {
    this.name = name;
    this.handlerId = handlerId;
  }

  @Override
  public void prepare() {
    System.out.println("PREPARING: " + handlerId);
  }

  @Override
  public String process() {
    System.out.println("PROCESSING: " + handlerId);
    return name;
  }

  @Override
  public void cleanup() {
    System.out.println("CLEANUP: " + handlerId);
  }
}
