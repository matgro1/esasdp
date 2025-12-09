package apsd.classes.containers.collections.concretecollections.bases;

import apsd.classes.containers.sequences.DynCircularVector;
import apsd.classes.utilities.Box;
import apsd.classes.utilities.MutableNatural;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.Chain;
import apsd.interfaces.containers.iterators.BackwardIterator;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.containers.sequences.DynVector;
import apsd.interfaces.containers.sequences.Sequence;
import apsd.interfaces.traits.Predicate;

/** Object: Abstract list base implementation on (dynamic circular) vector. */
abstract public class VChainBase<Data> implements Chain<Data> {

    protected final DynVector<Data> vec;

    protected VChainBase(){
        vec = new apsd.classes.containers.sequences.DynVector<>();
    }
    protected VChainBase(DynVector<Data> vec) {
        this.vec = vec;
    }

    /* ************************************************************************ */
    /* Override specific member functions from Container                        */
    /* ************************************************************************ */
    @Override
    public Natural Size() {
        return vec.Size();
    }

    /* ************************************************************************ */
    /* Override specific member functions from ClearableContainer               */
    /* ************************************************************************ */
    @Override
    public void Clear() {
        vec.Clear();
    }

    /* ************************************************************************ */
    /* Override specific member functions from RemovableContainer               */
    /* ************************************************************************ */
    @Override
    public boolean Remove(Data data) {
        MutableNatural counter = new MutableNatural(0);
        Box<Boolean> found = new Box<>(false);

        vec.TraverseForward(elem -> {
            if (elem.equals(data)) {
                found.Set(true);
                return true;
            }
            counter.Increment();
            return false;
        });

        if (found.Get()) {
            vec.AtNRemove(new Natural(counter));
            return true;
        }
        return false;
    }

    /* ************************************************************************ */
    /* Override specific member functions from Chain (Mancanti!)                */
    /* ************************************************************************ */

    @Override
    public boolean InsertIfAbsent(Data data) {
        boolean exists = false;

        final Box<Boolean> found = new Box<>(false);
        vec.TraverseForward(elem -> {
            if(elem.equals(data)) {
                found.Set(true);
                return true;
            }
            return false;
        });

        if (found.Get()) return false;

        vec.InsertAt(data, vec.Size());
        return true;
    }

    @Override
    public void RemoveOccurrences(Data data) {
        while (Remove(data));
    }

    @Override
    public Natural Search(Data data) {
        MutableNatural counter = new MutableNatural(0);
        Box<Boolean> found = new Box<>(false);

        vec.TraverseForward(elem -> {
            if (elem.equals(data)) {
                found.Set(true);
                return true;
            }
            counter.Increment();
            return false;
        });

        if(found.Get()) return new Natural(counter);
        return vec.Size();
    }

    /* ************************************************************************ */
    /* Override specific member functions from Sequence                         */
    /* ************************************************************************ */
    @Override
    public ForwardIterator<Data> FIterator() {
        return vec.FIterator();
    }

    @Override
    public BackwardIterator<Data> BIterator() {
        return vec.BIterator();
    }

    @Override
    public Data GetAt(Natural pos) {
        return vec.GetAt(pos);
    }

    @Override
    public Sequence<Data> SubSequence(Natural start, Natural end) {
        return vec.SubSequence(start, end);
    }
    @Override
    public boolean Filter(Predicate<Data> filter) {
        boolean modified = false;
        MutableNatural counter = new MutableNatural(0);

        // Usiamo un while sul Size corrente perché Size cambia mentre rimuoviamo!
        while (counter.ToLong() < vec.Size().ToLong()) {
            Data data = vec.GetAt(new Natural(counter));
            if (filter.Apply(data)) {
                counter.Increment(); // Tengo l'elemento, avanzo
            } else {
                modified = true;
                vec.AtNRemove(new Natural(counter)); // Rimuovo, NON avanzo (il prossimo scala qui)
            }
        }
        return modified;
    }
}