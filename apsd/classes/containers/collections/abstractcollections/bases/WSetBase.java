package apsd.classes.containers.collections.abstractcollections.bases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.IterableContainer;
import apsd.interfaces.containers.collections.Chain;
import apsd.interfaces.containers.collections.Set;
import apsd.interfaces.containers.iterators.MutableBackwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;
import apsd.interfaces.traits.Accumulator;
import apsd.interfaces.traits.Predicate;

/** Object: Abstract wrapper set base implementation via chain. */
abstract public class WSetBase<Data, Chn extends Chain<Data>> implements Set<Data>  {

    protected Chn chn;

    protected WSetBase(){ }

    /* ************************************************************************ */
    /* Override specific member functions from Container                        */
    /* ************************************************************************ */

    @Override
    public Natural Size(){
        return chn.Size();
    }

    /* ************************************************************************ */
    /* Override specific member functions from ClearableContainer               */
    /* ************************************************************************ */

    @Override
    public void Clear(){
        chn.Clear();
    }

    /* ************************************************************************ */
    /* Override specific member functions from InsertableContainer              */
    /* ************************************************************************ */

    @Override
    public boolean Insert(Data data){
        return chn.InsertIfAbsent(data);
    }

    /* ************************************************************************ */
    /* Override specific member functions from RemovableContainer               */
    /* ************************************************************************ */

    public boolean Remove(Data data){
        return chn.Remove(data);
    }

    /* ************************************************************************ */
    /* Override specific member functions from IterableContainer                */
    /* ************************************************************************ */

    @Override
    public MutableBackwardIterator<Data> BIterator() {
        return (MutableBackwardIterator<Data>) chn.BIterator();
    }

    @Override
    public MutableForwardIterator<Data> FIterator() {
        return (MutableForwardIterator<Data>) chn.FIterator();
    }



    @Override
    public boolean TraverseForward(Predicate<Data> predicate) {
        MutableForwardIterator<Data> it = FIterator();
        while (it.IsValid()) {
            if (predicate.Apply(it.GetCurrent())) {
                return true;
            }
            it.Next();
        }
        return false;
    }

    @Override
    public boolean TraverseBackward(Predicate<Data> predicate) {
        MutableBackwardIterator<Data> it = BIterator();
        while (it.IsValid()) {
            if (predicate.Apply(it.GetCurrent())) {
                return true;
            }
            it.Prev();
        }
        return false;
    }

    @Override
    public <Acc> Acc FoldForward(Accumulator<Data, Acc> fun, Acc ini) {

        MutableForwardIterator<Data> it = FIterator();


        Acc accumulator = ini;
        while (it.IsValid()) {
            accumulator = fun.Apply(it.GetCurrent(), accumulator);
            it.Next();
        }
        return accumulator;
    }

    @Override
    public <Acc> Acc FoldBackward(Accumulator<Data, Acc> fun, Acc ini) {
        MutableBackwardIterator<Data> it = BIterator();
        Acc accumulator = ini;
        while (it.IsValid()) {
            accumulator = fun.Apply(it.GetCurrent(), accumulator);
            it.Prev();
        }
        return accumulator;
    }

    /* ************************************************************************ */
    /* Override specific member functions from Collection                       */
    /* ************************************************************************ */

    @Override
    public boolean Filter(Predicate<Data> fun) {
        return chn.Filter(fun);
    }

    /* ************************************************************************ */
    /* Override specific member functions from Set                              */
    /* ************************************************************************ */
    abstract public Set<Data> Intersection(Set<Data> First, Set<Data> Second);

    @Override
    public boolean IsEqual(IterableContainer<Data> container) {
        return chn.IsEqual(container);
    }
}