package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;

/** Object: Abstract (static) circular vector base implementation. */
abstract public class CircularVectorBase<Data> extends VectorBase<Data> { // Must extend VectorBase
    protected long start = 0L;

    protected CircularVectorBase(){
        this.start = 0L;
    }

    /* ************************************************************************ */
    /* Override specific member functions from ReallocableContainer             */
    /* ************************************************************************ */
    public void ArrayAlloc(Natural newSize) {
        this.arr = (Data[]) new Object[(int) newSize.ToLong()];
    }
    // ...

    /* ************************************************************************ */
    /* Override specific member functions from Sequence                         */
    /* ************************************************************************ */
    @Override
    public Data GetAt(Natural offset){
        ExcIfOutOfBound(offset);
        return arr[Math.toIntExact((offset.ToLong() + start) % arr.length)];
    }

    @Override

    public void ShiftLeft(Natural pos,Natural shift) {
        long p = pos.ToLong();
        long s = shift.ToLong();
        long cap = arr.length;

        if (p == 0) {
            for (long i = 0; i < s; i++) {
                int idx = (int) ((start + i) % cap);
                arr[idx] = null;
            }

            start = (start + s) % cap;

        }
        else {

            long logicalSize = Size().ToLong();

            for (long i = p; i < logicalSize - s; i++) {
                int toIndex = (int) ((start + i) % cap);
                int fromIndex = (int) ((start + i + s) % cap);
                arr[toIndex] = arr[fromIndex];
            }

            for (long i = logicalSize - s; i < logicalSize; i++) {
                int idx = (int) ((start + i) % cap);
                arr[idx] = null;
            }
        }

    }
    @Override
    public void ShiftRight(Natural pos, Natural shift) {
        long p = pos.ToLong();
        long s = shift.ToLong();
        long cap = arr.length;
        long currentSize = Size().ToLong();

        if (p == 0) {
            start = (start - s + cap) % cap;
        }

        else {
            for (long i = currentSize - 1; i >= p; i--) {

                int fromIndex = Math.toIntExact((start + i) % cap);

                int toIndex = Math.toIntExact((start + i + s) % cap);

                arr[toIndex] = arr[fromIndex];

            }
        }
    }
    /* ************************************************************************ */
    /* Override specific member functions from MutableSequence                  */
    /* ************************************************************************ */
    @Override
    public void SetAt(Data data,Natural offset){
        ExcIfOutOfBound(offset);
        arr[Math.toIntExact((offset.ToLong() + start) % arr.length)]=data;
    }


    // ...

    /* ************************************************************************ */
    /* Specific member functions of Vector                                      */
    /* ************************************************************************ */
    @Override
    public void Realloc(Natural newSize) {
        Data[] oldArr = this.arr;
        long newCapacity = newSize.ToLong();
        long currentSize = Size().ToLong();
        long oldStart = this.start;

        ArrayAlloc(newSize);

        if (oldArr != null && currentSize > 0) {

            long elementsToCopy = Math.min(currentSize, newCapacity);

            for (int i = 0; i < elementsToCopy; i++) {
                int oldIndex = (int) ((oldStart + i) % oldArr.length);
                this.arr[i] = oldArr[oldIndex];
            }

            this.start = 0L;
        }
    }
    // ...

}
