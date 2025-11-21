package apsd.interfaces.containers.base;

/** Interface: Container con supporto all'inserimento di un dato. */
public interface InsertableContainer<Data> extends Container { // Must extend Container

  boolean Insert(Data data);

  boolean InsertAll(TraversableContainer<Data> data);

  boolean InsertSome(TraversableContainer<Data> data);

}
