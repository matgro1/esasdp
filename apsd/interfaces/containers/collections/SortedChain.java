package apsd.interfaces.containers.collections;

// import apsd.classes.utilities.Natural;
// import apsd.interfaces.containers.sequences.SortedSequence;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.IterableContainer;
import apsd.interfaces.containers.iterators.BackwardIterator;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.containers.sequences.Sequence;
import apsd.interfaces.containers.sequences.SortedSequence;

public interface SortedChain<Data extends Comparable<? super Data>> extends OrderedChain<Data>, SortedSequence<Data> { // Must extend OrderedChain and SortedSequence

  Natural SearchPredecessor(Data element);

  Natural SearchSuccessor(Data element);

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // ...

  /* ************************************************************************ */
  /* Override specific member functions from Set                              */
  /* ************************************************************************ */

   default void Intersection(SortedChain<Data> chn) {
     Natural i = Natural.ZERO, j = Natural.ZERO;
     while (i.compareTo(Size()) < 0 && j.compareTo(chn.Size()) < 0) {
       int cmp = GetAt(i).compareTo(chn.GetAt(j));
       if (cmp < 0) {
         RemoveAt(i);
       } else {
         j = j.Increment();
         if (cmp == 0) { i = i.Increment(); }
       }
     }
     while (i.compareTo(Size()) < 0) {
       RemoveAt(i);
     }
   }

    @Override
    default boolean InsertIfAbsent(Data data) {
        return false;
    }

    @Override
    default void RemoveOccurrences(Data data) {

    }

    @Override
    default Chain<Data> SubChain(Natural firstPos, Natural secondPos) {
        return null;
    }

    @Override
    default Natural Search(Data data) {
        return null;
    }

    @Override
    default Sequence<Data> SubSequence(Natural firstPos, Natural secondPos) {
        return null;
    }

    @Override
    default Data Min() {
        return null;
    }

    @Override
    default void RemoveMin() {

    }

    @Override
    default Data MinNRemove() {
        return null;
    }

    @Override
    default Data Max() {
        return null;
    }

    @Override
    default void RemoveMax() {

    }

    @Override
    default Data MaxNRemove() {
        return null;
    }

    @Override
    default Data Predecessor(Data element) {
        return null;
    }

    @Override
    default void RemovePredecessor(Data element) {

    }

    @Override
    default Data PredecessorNRemove(Data element) {
        return null;
    }

    @Override
    default Data Successor(Data element) {
        return null;
    }

    @Override
    default void RemoveSuccessor(Data element) {

    }

    @Override
    default Data SuccessorNRemove(Data element) {
        return null;
    }

    @Override
    default void Union(Set<Data> set) {

    }

    @Override
    default void Difference(Set<Data> set) {

    }

    @Override
    default void Intersection(Set<Data> set) {

    }

    @Override
    default ForwardIterator<Data> FIterator() {
        return null;
    }

    @Override
    default BackwardIterator<Data> BIterator() {
        return null;
    }

    @Override
    default boolean IsEqual(IterableContainer<Data> container) {
        return false;
    }

    @Override
    default void Clear() {

    }

    @Override
    default boolean Insert(Data data) {
        return false;
    }

    @Override
    default boolean Remove(Data data) {
        return false;
    }

    @Override
    default Data AtNRemove(Natural position) {
        return null;
    }

    /* ************************************************************************ */
  /* Override specific member functions from OrderedSet                       */
  /* ************************************************************************ */

  // ...

}
