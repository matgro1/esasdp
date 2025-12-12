package apsd.interfaces.containers.sequences;

 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.base.MutableIterableContainer;
 import apsd.interfaces.containers.iterators.MutableForwardIterator;

/** Interface: Sequence & MutableIterableContainer con supporto alla scrittura tramite posizione. */
public interface MutableSequence<Data> extends Sequence<Data>, MutableIterableContainer<Data> { // Must extend Sequence and MutableIterableContainer

  default void SetAt(Data data,Natural position){

    ExcIfOutOfBound(position);
    MutableForwardIterator<Data> it  = FIterator();
    it.Next(position.ToLong());
    it.SetCurrent(data);
  }

  default Data GetNSetAt(Data data,Natural position){
    Data dataGot=GetAt(position);
    SetAt(data, position);
    return dataGot;
  }

  default void SetFirst(Data data){
    SetAt(data,new Natural(0));
  }

  default Data GetNSetFirst(Data data){
    Data dataGot=GetFirst();
    SetFirst(data);
    return dataGot;
  }

  default void SetLast(Data data){

    long size = Size().ToLong();
    if (size > 0) {
      SetAt(data, new Natural(size-1));
    } else {
      SetAt(data, new Natural(0));
    }
  }

  default Data GetNSetLast(Data data){
    Data dataGot=GetLast();
    SetLast(data);
    return dataGot;
  }

  default void Swap(Natural first, Natural second){
    if(IsInBound(first) && IsInBound(second)){
      Data firstData = GetAt(first);
      Data secondData = GetAt(second);
      SetAt(firstData, first);
      SetAt(secondData, second);
    }
  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  @Override
  MutableSequence<Data> SubSequence(Natural firstPos, Natural secondPos);

}
