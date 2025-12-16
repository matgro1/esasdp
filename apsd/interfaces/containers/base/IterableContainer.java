package apsd.interfaces.containers.base;

import apsd.interfaces.containers.iterators.BackwardIterator;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.traits.Predicate;

/** Interface: TraversableContainer con supporto all'iterazione. */
public interface IterableContainer<Data> extends TraversableContainer<Data>{ // Must extend TraversableContainer

  ForwardIterator<Data> FIterator();
  BackwardIterator<Data> BIterator();

  default boolean IsEqual(IterableContainer<Data> other){
    ForwardIterator<Data> otherIter = other.FIterator();
    boolean mismatchFound = this.FIterator().ForEachForward((currData) -> {

      if (!otherIter.IsValid()) {
        return true;
      }

      Data otherData = otherIter.DataNNext();
      if (!java.util.Objects.equals(currData, otherData)) {
        return true;
      }

      return false;
    });
    if (mismatchFound) return false;
    return !otherIter.IsValid();
  }

  /* ************************************************************************ */
  /* Override specific member functions from TraversableContainer             */
  /* ************************************************************************ */

  @Override
  default boolean TraverseForward(Predicate<Data> predicate){
    return FIterator().ForEachForward(predicate);
  }
  @Override
  default boolean TraverseBackward(Predicate<Data> predicate){

    return BIterator().ForEachBackward(predicate);
  }
  // ...

}
