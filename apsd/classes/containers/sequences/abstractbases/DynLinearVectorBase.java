package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;

import apsd.interfaces.containers.sequences.DynVector;

import static java.lang.Math.max;

/** Object: Abstract dynamic linear vector base implementation. */
abstract public class DynLinearVectorBase<Data> extends LinearVectorBase<Data> implements DynVector<Data> { // Must extend LinearVectorBase and implement DynVector

    protected long size = 0L;

    protected DynLinearVectorBase(){
        ArrayAlloc(new Natural(10));
        size = 0L;
    }

    public void ShiftRight(Natural pos,Natural shift){
        Expand(shift);
        size+=shift.ToLong();
        super.ShiftRight(pos,shift);
    }

    public void ShiftLeft(Natural pos,Natural shift) {
        super.ShiftLeft(pos,shift);
        size -=shift.ToLong();
        Reduce(shift);
    }
    @Override
    public void InsertAt(Data data, Natural pos){
        ShiftRight(pos,new Natural(1));
        super.SetAt(data,pos);
    }
    @Override
    public Data AtNRemove(Natural pos){
        Data data = super.GetAt(pos);
        ShiftLeft(pos,new Natural(1));
        return data;
    }

    @Override
    public Natural Size(){
        return  new Natural(size);
    }

    @Override
    public void Clear(){
        Reduce(Capacity());
        size = 0L;
    }
    @Override
    public void Realloc(Natural newSize){
        Data[] oldArr = this.arr;
        long newCapacity = newSize.ToLong();

        ArrayAlloc(newSize);

        if (oldArr != null) {
            long elementsToCopy = this.size;

            if (newCapacity < elementsToCopy) {
                elementsToCopy = newCapacity;
            }

            for (int i = 0; i < elementsToCopy; i++) this.arr[i] = oldArr[i];

            if (this.size > newCapacity) {
                this.size = newCapacity;
            }
        }
    }
    @Override
    public void ArrayAlloc(Natural newSize){
        this.arr = (Data[]) new Object[(int) newSize.ToLong()];
        this.size =  newSize.ToLong();
    }
    /* ************************************************************************ */
    /* Override specific member functions from ClearableContainer               */
    /* ************************************************************************ */

    // ...

    /* ************************************************************************ */
    /* Override specific member functions from ReallocableContainer             */
    /* ************************************************************************ */

    // ...

    /* ************************************************************************ */
    /* Override specific member functions from ResizableContainer               */
    /* ************************************************************************ */

    // ...
    @Override
    public void Expand(Natural sizeOffset){
        Realloc(new Natural(this.arr.length+sizeOffset.ToLong()));
    }
    @Override
    public void Expand(){
        Realloc(new Natural((long) (max(this.arr.length*THRESHOLD_FACTOR,1))));
    }
    @Override
    public void Reduce(Natural sizeOffset){
        Realloc(new Natural(max(this.arr.length-sizeOffset.ToLong(),0)));
    }
    public void Reduce(){
        Realloc(new Natural((long) (max(this.arr.length/THRESHOLD_FACTOR,1))));
    }



}
