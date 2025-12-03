package apsd.interfaces.containers.collections;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.sequences.SortedSequence;

public interface SortedChain<Data extends Comparable<? super Data>>
        extends OrderedChain<Data>, SortedSequence<Data> {

    Natural SearchPredecessor(Data element);
    Natural SearchSuccessor(Data element);


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

}