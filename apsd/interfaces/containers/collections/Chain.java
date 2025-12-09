package apsd.interfaces.containers.collections;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.sequences.RemovableAtSequence;

public interface Chain<Data> extends RemovableAtSequence<Data>,Set<Data>{ // Must extend RemovableAtSequence

    default boolean InsertIfAbsent(Data data) {
        if (!Exists(data)) {
            Insert(data);
            return true;
        }
        return false;
    }

  default void RemoveOccurrences(Data data){
      Natural index;
      while ((index = Search(data)).compareTo(Size()) < 0) {
          RemoveAt(index);
      }
  }

  Chain<Data> SubChain(Natural firstPos, Natural secondPos);

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  @Override
  Natural Search(Data data);

}
