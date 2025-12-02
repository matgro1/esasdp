package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.SortedIterableContainer;


/** Interface: Sequence & SortedIterableContainer. */
public interface SortedSequence<Data extends Comparable<? super Data>> extends Sequence<Data>, SortedIterableContainer<Data>{ // Must extend Sequence and SortedIterableContainer

  /* ************************************************************************ */
  /* Override specific member functions from MembershipContainer              */
  /* ************************************************************************ */
    @Override
    default boolean Exists(Data target){
        Natural index = Search(target);
        return index.ToLong() < Size().ToLong();
    }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */
  @Override
  default Natural Search(Data target) {
      long low = 0;
      long high = Size().ToLong() - 1;

      while (low <= high) {
          long mid = (low + high) / 2;
          Data midVal = GetAt(new Natural(mid));

          int cmp = midVal.compareTo(target);

          if (cmp == 0) {
              return new Natural(mid);
          } else if (cmp < 0) {
              low = mid + 1;
          } else {
              high = mid - 1;
          }
      }

      return Size();
  }
  // ...

}
