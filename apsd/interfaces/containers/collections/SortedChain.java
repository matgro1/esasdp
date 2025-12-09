package apsd.interfaces.containers.collections;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.IterableContainer;
import apsd.interfaces.containers.iterators.BackwardIterator;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.containers.sequences.Sequence;
import apsd.interfaces.containers.sequences.SortedSequence;

public interface SortedChain<Data extends Comparable<? super Data>>
        extends OrderedChain<Data>, SortedSequence<Data> {

    Natural SearchPredecessor(Data element);
    Natural SearchSuccessor(Data element);

    @Override
    default Data Predecessor(Data element) {
        Natural pos = SearchPredecessor(element);
        if (pos == null || !IsInBound(pos)) return null;
        return GetAt(pos);
    }

    @Override
    default void RemovePredecessor(Data element) {
        Natural pos = SearchPredecessor(element);
        if (pos != null && IsInBound(pos)) {
            RemoveAt(pos);
        }
    }

    @Override
    default Data PredecessorNRemove(Data element) {
        Data predecessor = Predecessor(element);
        RemovePredecessor(element);
        return predecessor;
    }

    @Override
    default Data Successor(Data element) {
        Natural pos = SearchSuccessor(element);
        if (pos == null || !IsInBound(pos)) return null;
        return GetAt(pos);
    }

    @Override
    default void RemoveSuccessor(Data element) {
        Natural pos = SearchSuccessor(element);
        if (pos != null && IsInBound(pos)) {
            RemoveAt(pos);
        }
    }

    @Override
    default Data SuccessorNRemove(Data element) {
        Data successor = Successor(element);
        RemoveSuccessor(element);
        return successor;
    }

    @Override
    default Data Min() {
        if (IsEmpty()) return null;
        return GetFirst();
    }

    @Override
    default void RemoveMin() {
        if (!IsEmpty()) RemoveFirst();
    }

    @Override
    default Data MinNRemove() {
        if (IsEmpty()) return null;
        return FirstNRemove();
    }

    @Override
    default Data Max() {
        if (IsEmpty()) return null;
        return GetLast();
    }

    @Override
    default void RemoveMax() {
        if (!IsEmpty()) RemoveLast();
    }

    @Override
    default Data MaxNRemove() {
        if (IsEmpty()) return null;
        return LastNRemove();
    }


    @Override
    default boolean InsertIfAbsent(Data data) {
        if (!Exists(data)) {
            return Insert(data);
        }
        return false;
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
    default void Intersection(Set<Data> set) {

        SortedChain<Data> chn = (SortedChain<Data>) set;
        Natural i = Natural.ZERO;
        Natural j = Natural.ZERO;

        while (i.compareTo(Size()) < 0 && j.compareTo(chn.Size()) < 0) {
            int cmp = GetAt(i).compareTo(chn.GetAt(j));
            if (cmp < 0) {
                RemoveAt(i);
            } else if (cmp > 0) {
                j = j.Increment();
            } else {
                i = i.Increment();
                j = j.Increment();
            }
        }
        while (i.compareTo(Size()) < 0) {
            RemoveAt(i);
        }
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
    default void Difference(Set<Data> set) {

        SortedChain<Data> chn = (SortedChain<Data>) set;
        Natural i = Natural.ZERO;
        Natural j = Natural.ZERO;

        while (i.compareTo(Size()) < 0 && j.compareTo(chn.Size()) < 0) {
            int cmp = GetAt(i).compareTo(chn.GetAt(j));

            if (cmp < 0) {
                i = i.Increment();
            } else if (cmp > 0) {
                j = j.Increment();
            } else {
                RemoveAt(i);
                j = j.Increment();
            }
        }
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
}