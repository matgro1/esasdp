package apsd.interfaces.containers.collections;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.sequences.RemovableAtSequence;

public interface Chain<Data> extends RemovableAtSequence<Data>{ // Must extend RemovableAtSequence

   boolean InsertIfAbsent(Data data);

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
