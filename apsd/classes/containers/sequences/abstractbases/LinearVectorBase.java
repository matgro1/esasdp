package apsd.classes.containers.sequences.abstractbases;

 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.base.TraversableContainer;

/** Object: Abstract (static) linear vector base implementation. */
abstract public class LinearVectorBase<Data> extends VectorBase<Data> { // Must extend VectorBase

  protected LinearVectorBase(){}




  /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer             */
  /* ************************************************************************ */


    @Override
    public void ShiftRight(Natural pos, Natural shift) {
        long idx = pos.ToLong();
        long offset = shift.ToLong();
        if (this.arr != null) {
            int len = this.arr.length - (int)offset - (int)idx;
            if (len > 0)
                System.arraycopy(this.arr, (int)idx, this.arr, (int)(idx + offset), len);

            for (int i = 0; i < offset; i++) {
                if (idx + i < this.arr.length) {
                    this.arr[(int)(idx + i)] = null;
                }
            }
        }
    }

    @Override
    public void ShiftLeft(Natural pos, Natural shift) {
        long idx = pos.ToLong();
        long offset = shift.ToLong();
        if (this.arr != null) {
            int len = this.arr.length - (int)offset - (int)idx;
            if (len > 0)
                System.arraycopy(this.arr, (int)(idx + offset), this.arr, (int)idx, len);

            for (int i = this.arr.length - (int)offset; i < this.arr.length; i++) {
                if (i >= 0) {
                    this.arr[i] = null;
                }
            }
        }
    }
    @Override
    public void Realloc(Natural size){

        Data[] initialArrayCopy = this.arr;
        ArrayAlloc(size);
        if(initialArrayCopy == null){
            return;
        }
        int len = Math.min(initialArrayCopy.length, this.arr.length);
        System.arraycopy(initialArrayCopy, 0, this.arr, 0, len);
    }
  // ...

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */
    @Override
    public Data GetAt(Natural pos){
        ExcIfOutOfBound(pos);
        return this.arr[(int) pos.ToLong()];
    }

  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence                  */
  /* ************************************************************************ */
    @Override
    public void SetAt(Data data,Natural pos){
        ExcIfOutOfBound(pos);
        this.arr[(int) pos.ToLong()] = data;
    }
  // ...

}
