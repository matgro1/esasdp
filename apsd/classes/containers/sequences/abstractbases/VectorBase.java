package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.iterators.MutableBackwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;
import apsd.interfaces.containers.sequences.Vector;



/** Object: Abstract vector base implementation. */
abstract public class VectorBase<Data> implements Vector<Data> { // Must implement Vector

    protected Data[] arr;

    protected VectorBase() {
        this.arr = null;
    }
    protected void NewVector(Data[] arr){
        if (arr == null) {
            Clear();
            return;
        }

        ArrayAlloc(new Natural(arr.length));


        System.arraycopy(arr, 0, this.arr, 0, arr.length);
    }

    @SuppressWarnings("unchecked")
    protected void ArrayAlloc(Natural newsize) {
        long size = newsize.ToLong();
        if (size >= Integer.MAX_VALUE) { throw new ArithmeticException("Overflow: size cannot exceed Integer.MAX_VALUE!"); }
        arr = (Data[]) new Object[(int) size];
    }


    /* ************************************************************************ */
    /* Override specific member functions from ClearableContainer               */
    /* ************************************************************************ */


    @Override
    public void Clear() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = null;
        }
    }
    /* ************************************************************************ */
    /* Override specific member functions from ResizableContainer               */
    /* ************************************************************************ */
    @Override
    public Natural Capacity() {
        if (arr == null) return Natural.ZERO;
        return new Natural(arr.length);
    }
    // ...

    /* ************************************************************************ */
    /* Override specific member functions from IterableContainer                */
    /* ************************************************************************ */

    // ...

    /* ************************************************************************ */
    /* Override specific member functions from Sequence                         */
    /* ************************************************************************ */

    /* ************************************************************************ */
    /* Override specific member functions from MutableSequence                  */
    /* ************************************************************************ */


    @Override
    public MutableForwardIterator<Data> FIterator() {
        return new MutableForwardIterator<Data>() {
            long current = 0;

            @Override
            public boolean IsValid() {
                return current < Size().ToLong();
            }

            @Override
            public void Reset() {
                current = 0;
            }

            @Override
            public Data GetCurrent() {
                return GetAt(Natural.Of(current));
            }

            @Override
            public void SetCurrent(Data data) {
                SetAt(data, Natural.Of(current));
            }

            @Override
            public Data DataNNext() {
                Data data = GetAt(Natural.Of(current));
                current++;
                return data;
            }
        };
    }
    @Override
    public MutableBackwardIterator<Data> BIterator() {
        return new MutableBackwardIterator<Data>() {
            long current = Size().ToLong() - 1;

            @Override
            public boolean IsValid() {
                return current >= 0;
            }

            @Override
            public void Reset() {
                current = Size().ToLong() - 1;
            }

            @Override
            public Data GetCurrent() {
                return GetAt(Natural.Of(current));
            }

            @Override
            public void SetCurrent(Data data) {
                SetAt(data, Natural.Of(current));
            }

            @Override
            public Data DataNPrev() {
                Data data = GetAt(Natural.Of(current));
                current--;
                return data;
            }
        };
    }
}
