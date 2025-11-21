package apsd.interfaces.containers.base;

/** Interface: Container con supporto alla rimozione di un dato. */
public interface RemovableContainer<Data> extends Container { // Must extend Container

  boolean Remove(Data data);

  boolean RemoveAll(TraversableContainer<Data> traversableContainer);

  boolean RemoveSome(TraversableContainer<Data> traversableContainer);

}
