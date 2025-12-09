package apsd.classes.containers.collections.concretecollections.bases;
import apsd.classes.containers.sequences.Vector;
import apsd.classes.utilities.Box;
import apsd.classes.utilities.MutableNatural;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.Chain;
import apsd.interfaces.containers.iterators.BackwardIterator;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.containers.iterators.MutableBackwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;
import apsd.interfaces.containers.sequences.Sequence;
import apsd.interfaces.traits.Predicate;

/** Object: Abstract chain base implementation on linked-list. */
abstract public class LLChainBase<Data> implements Chain<Data> { // Must implement Chain

    protected final MutableNatural size = new MutableNatural();
    protected final Box<LLNode<Data>> headref = new Box<>();
    protected final Box<LLNode<Data>> tailref = new Box<>();

    protected LLChainBase(Natural size, LLNode<Data> head, LLNode<Data> tail){
        this.size.Assign(size);
        this.headref.Set(head);
        this.tailref.Set(tail);
    }

    public LLChainBase(TraversableContainer<Data> con) {
        size.Assign(con.Size());
        final Box<Boolean> first = new Box<>(true);
        con.TraverseForward(dat -> {
            LLNode<Data> node = new LLNode<>(dat);
            if (first.Get()) {
                headref.Set(node);
                first.Set(false);
            } else {
                tailref.Get().SetNext(node);
            }
            tailref.Set(node);
            return false;
        });
    }

    protected MutableForwardIterator<Box<LLNode<Data>>> FRefIterator() {
        return new MutableForwardIterator<Box<LLNode<Data>>>() {

            Box<LLNode<Data>> current = headref;

            @Override
            public boolean IsValid() {
                return current != null && !current.IsNull();
            }

            @Override
            public void Reset() {
                current = headref;
            }

            @Override
            public Box<LLNode<Data>> GetCurrent() {
                return current;
            }

            @Override
            public void SetCurrent(Box<LLNode<Data>> box) {
                throw new UnsupportedOperationException("Usa GetCurrent().Set(...) per modificare il link.");
            }

            @Override
            public Box<LLNode<Data>> DataNNext() {
                Box<LLNode<Data>> ret = current;
                if (IsValid()) {
                    current = current.Get().GetNext();
                }
                return ret;
            }
        };
    }


    protected MutableBackwardIterator<Box<LLNode<Data>>> BRefIterator() {
        return new MutableBackwardIterator<Box<LLNode<Data>>>() {

            Box<LLNode<Data>> current = findBoxFor(tailref.Get());

            private Box<LLNode<Data>> findBoxFor(LLNode<Data> targetNode) {
                if (targetNode == null) return null;

                Box<LLNode<Data>> scan = headref;
                if (scan.Get() == targetNode) return scan;

                while (scan != null && !scan.IsNull()) {
                    if (scan.Get().GetNext().Get() == targetNode) {
                        return scan.Get().GetNext();
                    }
                    scan = scan.Get().GetNext();
                }
                return null; // Non trovato
            }

            private LLNode<Data> findPrevNode(LLNode<Data> targetNode) {
                if (targetNode == null || targetNode == headref.Get()) return null;

                LLNode<Data> curr = headref.Get();
                while (curr != null && !curr.GetNext().IsNull()) {
                    if (curr.GetNext().Get() == targetNode) {
                        return curr;
                    }
                    curr = curr.GetNext().Get();
                }
                return null;
            }

            @Override
            public boolean IsValid() {
                return current != null && !current.IsNull();
            }

            @Override
            public void Reset() {
                current = findBoxFor(tailref.Get());
            }

            @Override
            public Box<LLNode<Data>> GetCurrent() {
                return current;
            }

            @Override
            public void SetCurrent(Box<LLNode<Data>> data) {
                throw new UnsupportedOperationException("Usa GetCurrent().Set(...) per modificare il link.");
            }

            @Override
            public Box<LLNode<Data>> DataNPrev() {
                Box<LLNode<Data>> ret = current;
                if (IsValid()) {
                    LLNode<Data> prevNode = findPrevNode(current.Get());
                    current = findBoxFor(prevNode);
                }
                return ret;
            }
        };
    }
    @Override
    public Sequence<Data> SubSequence(Natural start, Natural end) {
        long startIndex = start.ToLong();
        long endIndex = end.ToLong();

        if (startIndex > endIndex || endIndex > size.ToLong()) {
            return null;
        }
        LLNode<Data> newHead = headref.Get();
        for (long i = 0; i < startIndex; i++) {
            newHead = newHead.GetNext().Get();
        }

        long newSize = endIndex - startIndex;


        LLNode<Data> currentOriginal = newHead;
        LLNode<Data> headCopy = null;
        LLNode<Data> tailCopy = null;

        for (long i = 0; i < newSize; i++) {
            LLNode<Data> nodeCopy = new LLNode<>(currentOriginal.Get());

            if (headCopy == null) {
                headCopy = nodeCopy;
                tailCopy = nodeCopy;
            } else {
                tailCopy.SetNext(nodeCopy);
                tailCopy = nodeCopy;
            }

            currentOriginal = currentOriginal.GetNext().Get();
        }

        return NewChain(newSize, headCopy, tailCopy);
    }

    @Override
    public MutableForwardIterator<Data> FIterator() {
        return new MutableForwardIterator<Data>() {
            LLNode<Data> current = headref.Get();

            @Override
            public boolean IsValid() {
                return current != null;
            }

            @Override
            public Data GetCurrent() {
                return current.Get();
            }

            @Override
            public void SetCurrent(Data data) {
                current.Set(data);
            }

            @Override
            public Data DataNNext() {
                Data d = current.Get();
                current = current.GetNext().Get();
                return d;
            }

            @Override
            public void Reset() {
                current = headref.Get();
            }
        };
    }

    @Override
    public MutableBackwardIterator<Data> BIterator() {

        return new MutableBackwardIterator<Data>() {
            private final MutableBackwardIterator<Box<LLNode<Data>>> refIter = BRefIterator();

            @Override
            public boolean IsValid() {
                return refIter.IsValid();
            }

            @Override
            public Data GetCurrent() {
                return refIter.GetCurrent().Get().Get();
            }

            @Override
            public void SetCurrent(Data data) {
                refIter.GetCurrent().Get().Set(data);
            }

            @Override
            public Data DataNPrev() {
                Data d = GetCurrent();
                refIter.Prev();
                return d;
            }

            @Override
            public void Reset() {
                refIter.Reset();
            }
        };
    }
    @Override
    public Data GetFirst() {
        if (headref.IsNull()) return null;
        return headref.Get().Get();
    }

    @Override
    public Data GetLast() {
        if (tailref.IsNull()) return null;
        return tailref.Get().Get();
    }
    @Override
    public void RemoveFirst() {
        if (!headref.IsNull() && !headref.Get().GetNext().IsNull()) {
            headref.Set( headref.Get().GetNext().Get() );
            size.Decrement();

        } else {
            headref.Set(null);
            tailref.Set(null);
            size.Assign(0);
        }
    }

    @Override
    public void RemoveLast() {
        if (headref.IsNull()) return;
        if (headref.Get() == tailref.Get()) {
            headref.Set(null);
            tailref.Set(null);
            size.Assign(0);
            return;
        }
        LLNode<Data> current = headref.Get();
        while (current.GetNext().Get() != tailref.Get()) {
            current = current.GetNext().Get();
        }
        current.GetNext().Set(null);
        tailref.Set(current);
        size.Decrement();
    }
    @Override
    public Data FirstNRemove(){
        Data ret = GetFirst();
        RemoveFirst();
        return ret;
    }
    @Override
    public Data LastNRemove(){
        Data ret = GetLast();
        RemoveLast();
        return ret;
    }
    @Override
    public Data AtNRemove(Natural n) {
        long index = n.ToLong();

        if (index == 0) {
            if (headref.IsNull()) return null;
            Data ret = headref.Get().Get();

            headref.Set(headref.Get().GetNext().Get());

            if (headref.IsNull()) {
                tailref.Set(null);
            }

            size.Decrement();
            return ret;
        }

        LLNode<Data> prev = headref.Get();

        for (long i = 0; i < index - 1; i++) {
            prev = prev.GetNext().Get();
        }

        Box<LLNode<Data>> linkToToRemove = prev.GetNext();
        LLNode<Data> toRemove = linkToToRemove.Get();

        Data ret = toRemove.Get();

        linkToToRemove.Set(toRemove.GetNext().Get());

        if (linkToToRemove.IsNull()) {
            tailref.Set(prev);
        }

        size.Decrement();
        return ret;
    }
    protected abstract LLChainBase<Data> NewChain(long lenght, LLNode<Data> head,LLNode<Data> tail);

    /* ************************************************************************ */
    /* Specific member functions from LLChainBase                               */
    /* ************************************************************************ */

    // ...

    /* ************************************************************************ */
    /* Override specific member functions from Container                        */
    /* ************************************************************************ */

    // ...

    /* ************************************************************************ */
    /* Override specific member functions from ClearableContainer               */
    /* ************************************************************************ */

    // ...

    /* ************************************************************************ */
    /* Override specific member functions from RemovableContainer               */
    /* ************************************************************************ */

    @Override
    public boolean Remove(Data dat) {
        if (dat == null) return false;
        final Box<LLNode<Data>> prd = new Box<>();
        return FRefIterator().ForEachForward(cur -> {
            LLNode<Data> node = cur.Get();
            if (node.Get().equals(dat)) {
                cur.Set(node.GetNext().Get());
                if (tailref.Get() == node) { tailref.Set(prd.Get()); }
                size.Decrement();
                return true;
            }
            prd.Set(node);
            return false;
        });
    }
    @Override
    public Chain<Data> SubChain(Natural firstPos, Natural secondPos) {
        long start = firstPos.ToLong();
        long end = secondPos.ToLong();

        if (start > end || end > size.ToLong()) {
            return null;
        }

        if (start == end) {
            return NewChain(0, null, null);
        }

        LLNode<Data> curr = headref.Get();
        for (long i = 0; i < start; i++) {
            curr = curr.GetNext().Get();
        }

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

        return NewChain(newLen, newHead, newTail);
    }

    /* ************************************************************************ */
    /* Override specific member functions from IterableContainer                */
    /* ************************************************************************ */

    // ...

    /* ************************************************************************ */
    /* Override specific member functions from Sequence                         */
    /* ************************************************************************ */

    // ...


    /* ************************************************************************ */
    /* Override specific member functions from RemovableAtSequence              */
    /* ************************************************************************ */

    // ...

    /* ************************************************************************ */
    /* Override specific member functions from Collection                       */
    /* ************************************************************************ */

    // ...

}
