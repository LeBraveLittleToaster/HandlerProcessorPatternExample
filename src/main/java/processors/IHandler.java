package processors;

public interface IHandler <T> {
  void prepare();
  T process();
  void cleanup();
}
