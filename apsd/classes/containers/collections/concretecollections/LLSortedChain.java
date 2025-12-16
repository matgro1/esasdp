package apsd.classes.containers.collections.concretecollections;

import apsd.classes.containers.collections.concretecollections.bases.LLChainBase;
import apsd.classes.containers.collections.concretecollections.bases.LLNode;
import apsd.classes.utilities.Box;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.SortedChain;

/** Object: Concrete sorted chain implementation on linked-list. */
public class LLSortedChain<Data extends Comparable<? super Data>> extends LLChainBase<Data> implements SortedChain<Data>{ // Must extend LLChainBase and implement SortedChain

    public LLSortedChain(){
        super();
    }

    public LLSortedChain(LLSortedChain<Data> chn){
        super(chn);
    }

    public LLSortedChain(TraversableContainer<Data> con){
        super(con);
        if (con != null) {
            con.TraverseForward(this::Insert);
        }
    }

    protected LLSortedChain(long size, LLNode<Data> head, LLNode<Data> tail){
        super(Natural.Of(size),head,tail);
    }

    @Override
    public LLSortedChain<Data> NewChain(long lenght,LLNode<Data> head, LLNode<Data> tail) {
        return new LLSortedChain<>(lenght,head,tail);
    }

    /* ************************************************************************ */
    /* Specific member functions of LLSortedChain                               */
    /* ************************************************************************ */

    // ...

    /* ************************************************************************ */
    /* Override specific member functions from InsertableContainer              */
    /* ************************************************************************ */

    @Override
    public boolean Insert(Data data){
        if (data == null) return false;

        LLNode<Data> newNode = new LLNode<>(data);

        if (headref.IsNull() || headref.Get().Get().compareTo(data) > 0) {
            newNode.SetNext(headref.Get());
            headref.Set(newNode);

            if (tailref.IsNull()) {
                tailref.Set(newNode);
            }

            size.Increment();
            return true;
        }

        LLNode<Data> current = headref.Get();

        while (!current.GetNext().IsNull() &&
                current.GetNext().Get().Get().compareTo(data) < 0) {
            current = current.GetNext().Get();
        }


        newNode.SetNext(current.GetNext().Get());

        current.GetNext().Set(newNode);

        if (newNode.GetNext().IsNull()) {
            tailref.Set(newNode);
        }

        size.Increment();
        return true;

    }
    @Override
    public void Clear(){
        this.headref.Set(null);
        size.Assign(0);
    }
    @Override
    public boolean InsertIfAbsent(Data data) {

        if (Exists(data)) return false;
        return Insert(data);
    }
    public boolean Remove(Data data){
        if (data == null || headref.IsNull()) return false;

        int headCmp = headref.Get().Get().compareTo(data);

        if (headCmp > 0) return false;

        if (headCmp == 0) {
            headref.Set(headref.Get().GetNext().Get());

            if (headref.IsNull()) {
                tailref.Set(null);
            }

            size.Decrement();
            return true;
        }

        LLNode<Data> current = headref.Get();

        while (!current.GetNext().IsNull() &&
                current.GetNext().Get().Get().compareTo(data) < 0) {
            current = current.GetNext().Get();
        }


        if (!current.GetNext().IsNull() &&
                current.GetNext().Get().Get().compareTo(data) == 0) {

            LLNode<Data> nodeToRemove = current.GetNext().Get();

            current.GetNext().Set(nodeToRemove.GetNext().Get());

            if (current.GetNext().IsNull()) {
                tailref.Set(current);
            }

            size.Decrement();
            return true;
        }

        return false;
    }

    @Override
    public Natural SearchPredecessor(Data element) {

        if (headref.IsNull()) return null;

        if (headref.Get().Get().compareTo(element) >= 0) return null;

        LLNode<Data> current = headref.Get();
        long index = 0;

        while (!current.GetNext().IsNull() &&
                current.GetNext().Get().Get().compareTo(element) < 0) {
            current = current.GetNext().Get();
            index++;
        }

        return new Natural(index);
    }

    @Override
    public Natural SearchSuccessor(Data element) {

        LLNode<Data> current = headref.Get();
        long index = 0;

        while (current != null) {
            if (current.Get().compareTo(element) > 0) {
                return new Natural(index);
            }
            if (!current.GetNext().IsNull()) {
                current = current.GetNext().Get();
                index++;
            } else {
                break;
            }
        }

        return null;
    }

    @Override
    public Data Predecessor(Data element) {
        Natural pos = SearchPredecessor(element);
        if (pos == null) return null;
        return GetAt(pos);
    }

    @Override
    public void RemovePredecessor(Data element) {
        Natural pos = SearchPredecessor(element);
        if (pos != null) RemoveAt(pos);
    }

    @Override
    public Data PredecessorNRemove(Data element) {
        Natural pos = SearchPredecessor(element);
        if (pos == null) return null;
        return AtNRemove(pos);
    }

    @Override
    public Data Successor(Data element) {
        Natural pos = SearchSuccessor(element);
        if (pos == null) return null;
        return GetAt(pos);
    }

    @Override
    public void RemoveSuccessor(Data element) {
        Natural pos = SearchSuccessor(element);
        if (pos != null) RemoveAt(pos);
    }

    @Override
    public Data SuccessorNRemove(Data element) {
        Natural pos = SearchSuccessor(element);
        if (pos == null) return null;
        return AtNRemove(pos);
    }
    @Override
    public Natural Search(Data target) {
        LLNode<Data> current = headref.Get();
        long index = 0;

        while (current != null) {
            int cmp = current.Get().compareTo(target);
            if (cmp == 0) return new Natural(index);
            if (cmp > 0) return Size();
            if (!current.GetNext().IsNull()) {
                current = current.GetNext().Get();
                index++;
            } else {
                break;
            }
        }
        return Size();
    }
    /* ************************************************************************ */
    /* Override specific member functions from RemovableContainer               */
    /* ************************************************************************ */

    // ...

    /* ************************************************************************ */
    /* Override specific member functions from Sequence                         */
    /* ************************************************************************ */

    // ...

    /* ************************************************************************ */
    /* Override specific member functions from SortedSequence                   */
    /* ************************************************************************ */

    // ...

    /* ************************************************************************ */
    /* Override specific member functions from OrderedSet                       */
    /* ************************************************************************ */

    // ...

    /* ************************************************************************ */
    /* Override specific member functions from Chain                            */
    /* ************************************************************************ */

    // ...

}
