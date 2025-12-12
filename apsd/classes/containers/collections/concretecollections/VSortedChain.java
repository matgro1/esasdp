package apsd.classes.containers.collections.concretecollections;

import apsd.classes.containers.collections.concretecollections.bases.VChainBase;
import apsd.classes.containers.sequences.DynVector;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.IterableContainer;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.Chain;
import apsd.interfaces.containers.collections.SortedChain;

/** Object: Concrete sorted chain implementation via (dynamic linear) vector. */
public class VSortedChain<Data extends Comparable<? super Data>> extends VChainBase<Data> implements SortedChain<Data> {



    public VSortedChain() {
        super(new DynVector<>());
    }

    public VSortedChain(VSortedChain<Data> chn) {
        super(new DynVector<>(chn));
    }

    public VSortedChain(TraversableContainer<Data> con) {
        super(new DynVector<>());
        if (con != null) {
            con.TraverseForward(this::Insert);
        }
    }

    protected VSortedChain(DynVector<Data> vec) {
        super(vec);
    }

    protected VSortedChain<Data> NewChain(DynVector<Data> vec) {
        return new VSortedChain<>(vec);
    }

    /* ************************************************************************ */
    /* Override specific member functions from InsertableContainer              */
    /* ************************************************************************ */

    @Override
    public boolean Insert(Data element) {
        if (element == null) return false;

        long low = 0;
        long high = vec.Size().ToLong() - 1;

        while (low <= high) {
            long mid = (low + high) / 2;
            Data midVal = vec.GetAt(new Natural(mid));
            int cmp = midVal.compareTo(element);

            if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        vec.InsertAt(element, new Natural(low));
        return true;
    }

    @Override
    public boolean InsertIfAbsent(Data element) {
        if (Exists(element)) return false;
        return Insert(element);
    }

    /* ************************************************************************ */
    /* Override specific member functions from Chain                            */
    /* ************************************************************************ */
    @Override
    public Data GetFirst() {
        if (vec.IsEmpty()) throw new IndexOutOfBoundsException("Empty sequence");
        return vec.GetAt(new Natural(0));
    }

    @Override
    public Data GetLast() {
        if (vec.IsEmpty()) throw new IndexOutOfBoundsException("Empty sequence");
        return vec.GetAt(new Natural(vec.Size().ToLong() - 1));
    }

    @Override
    public void RemoveOccurrences(Data data) {
        if (data == null || IsEmpty()) return;

        Natural posObj = Search(data);
        long index = posObj.ToLong();
        long size = vec.Size().ToLong();

        if (index >= size || vec.GetAt(posObj).compareTo(data) != 0) {
            return;
        }

        long start = index;
        long end = index;

        while (start > 0 && vec.GetAt(new Natural(start - 1)).compareTo(data) == 0) {
            start--;
        }
        while (end < size - 1 && vec.GetAt(new Natural(end + 1)).compareTo(data) == 0) {
            end++;
        }

        long count = end - start + 1;
        vec.ShiftLeft(new Natural(start), new Natural(count));
    }

    @Override
    public Chain<Data> SubChain(Natural firstPos, Natural secondPos) {
        return new VSortedChain<>((DynVector<Data>) vec.SubVector(firstPos, secondPos));
    }

    /* ************************************************************************ */
    /* Override specific member functions from SortedChain                      */
    /* ************************************************************************ */

    @Override
    public Natural SearchPredecessor(Data element) {
        long low = 0;
        long high = vec.Size().ToLong() - 1;
        long index = -1;

        while (low <= high) {
            long mid = (low + high) / 2;
            Data midVal = vec.GetAt(new Natural(mid));
            int cmp = midVal.compareTo(element);

            if (cmp < 0) {
                index = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        if (index == -1) return null;
        return new Natural(index);
    }

    @Override
    public Natural SearchSuccessor(Data element) {
        long low = 0;
        long high = vec.Size().ToLong() - 1;
        long index = -1;

        while (low <= high) {
            long mid = (low + high) / 2;
            Data midVal = vec.GetAt(new Natural(mid));
            int cmp = midVal.compareTo(element);

            if (cmp > 0) {
                index = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        if (index == -1) return null;
        return new Natural(index);
    }

    @Override
    public Data Predecessor(Data element) {
        Natural pos = SearchPredecessor(element);
        if (pos == null) return null;
        return vec.GetAt(pos);
    }

    @Override
    public void RemovePredecessor(Data element) {
        Natural pos = SearchPredecessor(element);
        if (pos != null) {
            vec.AtNRemove(pos);
        }
    }

    @Override
    public Data PredecessorNRemove(Data element) {
        Natural pos = SearchPredecessor(element);
        if (pos == null) return null;
        return vec.AtNRemove(pos);
    }

    @Override
    public Data Successor(Data element) {
        Natural pos = SearchSuccessor(element);
        if (pos == null) return null;
        return vec.GetAt(pos);
    }

    @Override
    public void RemoveSuccessor(Data element) {
        Natural pos = SearchSuccessor(element);
        if (pos != null) {
            vec.AtNRemove(pos);
        }
    }

    @Override
    public Data SuccessorNRemove(Data element) {
        Natural pos = SearchSuccessor(element);
        if (pos == null) return null;
        return vec.AtNRemove(pos);
    }

    /* ************************************************************************ */
    /* Override specific member functions from IterableContainer                */
    /* ************************************************************************ */

    @Override
    public boolean IsEqual(IterableContainer<Data> container) {
        if (container == null) return false;
        if (this == container) return true;

        if (this.Size().ToLong() != container.Size().ToLong()) {
            return false;
        }

        var myIter = this.FIterator();
        var otherIter = container.FIterator();

        while (myIter.IsValid() && otherIter.IsValid()) {
            if (!myIter.GetCurrent().equals(otherIter.GetCurrent())) {
                return false;
            }
            myIter.Next();
            otherIter.Next();
        }

        return true;
    }

    /* ************************************************************************ */
    /* Override specific member functions from RemovableAtSequence              */
    /* ************************************************************************ */

    @Override
    public Data AtNRemove(Natural position) {
        return vec.AtNRemove(position);
    }
}