package apsd.interfaces.containers.base;

/** Interface: Container con supporto alla rimozione di un dato. */
public interface RemovableContainer<Data> extends Container { // Must extend Container

  boolean Remove(Data data);

  default boolean RemoveAll(TraversableContainer<Data> container){
    boolean failureFound = container.TraverseForward( (elem) -> {
      boolean success = this.Remove(elem);
      return !success;
    });

    return !failureFound;
  }

  default boolean RemoveSome(TraversableContainer<Data> container){
    return container.FoldForward(
            (elem, acc) -> {
              boolean res = this.Remove(elem);
              return acc || res;
            },
            false
    );
  }

}
