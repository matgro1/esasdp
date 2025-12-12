package apsd.interfaces.containers.sequences;


import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.iterators.ForwardIterator;

/** Interface: Sequence con supporto all'inserimento di un dato tramite posizione. */
public interface InsertableAtSequence<Data> extends Sequence<Data>{ // Must extend Sequence

  void InsertAt(Data data, Natural natural);

  default void InsertFirst(Data data){
      InsertAt(data,new Natural(0));
  }

  default void InsertLast(Data data){
      InsertAt(data, Size());
  }

}
