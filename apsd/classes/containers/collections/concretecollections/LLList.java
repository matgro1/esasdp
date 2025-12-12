package apsd.classes.containers.collections.concretecollections;

import apsd.classes.containers.collections.concretecollections.bases.LLChainBase;
import apsd.classes.containers.collections.concretecollections.bases.LLNode;
import apsd.classes.utilities.Box;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.IterableContainer;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.List;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.containers.iterators.MutableBackwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;
import apsd.interfaces.containers.sequences.MutableSequence;

/** Object: Concrete list implementation on linked-list. */
public class LLList<Data> extends LLChainBase<Data> implements List<Data> {


    public LLList() {
        super();
    }

    public LLList(TraversableContainer<Data> con) {
        super();
        if (con != null) {
            con.TraverseForward(this::Insert);
        }
    }

    protected LLList(long size, LLNode<Data> head, LLNode<Data> tail) {
        super(new Natural(size), head, tail);
    }


    @Override
    protected LLChainBase<Data> NewChain(long size, LLNode<Data> head, LLNode<Data> tail) {
        return new LLList<>(size, head, tail);
    }


    @Override
    public void InsertAt(Data data, Natural pos) {
        long index = pos.ToLong();
        long currentSize = size.ToLong();

        if (index > currentSize) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size " + currentSize);
        }

        LLNode<Data> newNode = new LLNode<>(data);

        if (index == 0) {
            newNode.SetNext(headref.Get());
            headref.Set(newNode);
            if (tailref.IsNull()) {
                tailref.Set(newNode);
            }
        }
        else if (index == currentSize) {
            tailref.Get().SetNext(newNode);
            tailref.Set(newNode);
        }
        else {
            LLNode<Data> prev = headref.Get();
            for (long i = 0; i < index - 1; i++) {
                prev = prev.GetNext().Get();
            }
            newNode.SetNext(prev.GetNext().Get());
            prev.GetNext().Set(newNode);
        }

        size.Increment();
    }

    @Override
    public boolean Insert(Data data) {
        InsertLast(data);
        return true;
    }


    @Override
    public boolean InsertIfAbsent(Data data) {
        if (!Exists(data)) {
            Insert(data);
            return true;
        }
        return false;
    }


    @Override
    public List<Data> SubList(Natural firstPos, Natural secondPos) {

        long start = firstPos.ToLong();
        long end = secondPos.ToLong();

        if (start > end || end > size.ToLong()) return null;
        if (start == end) return new LLList<>();

        LLNode<Data> curr = headref.Get();
        for (long i = 0; i < start; i++) curr = curr.GetNext().Get();

        LLNode<Data> newHead = null;
        LLNode<Data> newTail = null;
        long newLen = end - start;

        for (long i = 0; i < newLen; i++) {
            LLNode<Data> newNode = new LLNode<>(curr.Get());
            if (newHead == null) {
                newHead = newNode;
                newTail = newNode;
            } else {
                newTail.SetNext(newNode);
                newTail = newNode;
            }
            curr = curr.GetNext().Get();
        }

        return new LLList<>(newLen, newHead, newTail);
    }

    @Override
    public MutableSequence<Data> SubSequence(Natural start, Natural end) {
        return SubList(start, end);
    }


    @Override
    public Natural Search(Data data) {
        LLNode<Data> current = headref.Get();
        long index = 0;

        while (current != null) {
            if ((data == null && current.Get() == null) ||
                    (current.Get() != null && current.Get().equals(data))) {
                return new Natural(index);
            }
            if (!current.GetNext().IsNull()) {
                current = current.GetNext().Get();
                index++;
            } else {
                break;
            }
        }
        return Size();
    }

    @Override
    public boolean IsEqual(IterableContainer<Data> container) {
        if (container == null) return false;
        if (this == container) return true;
        if (this.Size().ToLong() != container.Size().ToLong()) return false;

        var myIter = this.FIterator();
        var otherIter = container.FIterator();

        while (myIter.IsValid() && otherIter.IsValid()) {
            Data myData = myIter.GetCurrent();
            Data otherData = otherIter.GetCurrent();

            if (myData == null) {
                if (otherData != null) return false;
            } else if (!myData.equals(otherData)) {
                return false;
            }
            myIter.Next();
            otherIter.Next();
        }
        return true;
    }

    @Override
    public void Clear() {
        headref.Set(null);
        tailref.Set(null);
        size.Zero();
    }
}
