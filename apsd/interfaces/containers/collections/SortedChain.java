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
    Chain<Data> SubChain(Natural firstPos, Natural secondPos);

    @Override
    Natural Search(Data data);

    @Override
    Sequence<Data> SubSequence(Natural firstPos, Natural secondPos);

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
    ForwardIterator<Data> FIterator();

    @Override
    BackwardIterator<Data> BIterator();

    @Override
    default boolean IsEqual(IterableContainer<Data> container){
        throw new UnsupportedOperationException(); // da problemi se non metto un'implementazione qua
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
    void Clear();

    @Override
    boolean Insert(Data data);

    @Override
    boolean Remove(Data data);

    @Override
    Data AtNRemove(Natural position);

}