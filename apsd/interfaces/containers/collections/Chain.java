package apsd.interfaces.containers.collections;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.sequences.RemovableAtSequence;

public interface Chain<Data> extends RemovableAtSequence<Data>{ // Must extend RemovableAtSequence

   boolean InsertIfAbsent(Data data);

  void RemoveOccurrences(Data data);

  Chain<Data> SubChain(Natural firstPos, Natural secondPos);

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  @Override
  Natural Search(Data data);

}
