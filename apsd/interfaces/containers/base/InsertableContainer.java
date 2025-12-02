package apsd.interfaces.containers.base;

/** Interface: Container con supporto all'inserimento di un dato. */
public interface InsertableContainer<Data> extends Container { // Must extend Container

  boolean Insert(Data data);

  default boolean InsertAll(TraversableContainer<Data> container){

    boolean failureFound = container.TraverseForward( (elem) -> {

      boolean success = this.Insert(elem);

      return !success;
    });

    return !failureFound;
  }

  default boolean InsertSome(TraversableContainer<Data> container){
    return container.FoldForward(
            (elem, acc) -> {
              boolean res = this.Insert(elem);
              return acc || res;
            },
            false
    );
  }

}
