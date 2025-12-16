package apsd.classes.containers.sequences.abstractbases;

 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.base.TraversableContainer;
 import apsd.interfaces.containers.sequences.DynVector;

 import static java.lang.Math.max;

/** Object: Abstract dynamic circular vector base implementation. */
abstract public class DynCircularVectorBase<Data> extends CircularVectorBase<Data> implements DynVector<Data> { // Must extend CircularVectorBase and implement DynVector

  protected long size = 0L;

   protected DynCircularVectorBase(){
       ArrayAlloc(new Natural(10));
       size=0L;
   }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  @Override
    public Natural Size(){
      return new Natural(size);
  }

  @Override
  public void Realloc(Natural newSize){
      Data[] oldArr = this.arr;
      long oldCapacity = (oldArr != null) ? oldArr.length : 0;
      long newCapacity = newSize.ToLong();

      // 1. Alloca il nuovo array
      ArrayAlloc(newSize);

      if (oldArr != null) {
          long limit = Math.min(this.size, newCapacity);

          for (int i = 0; i < limit; i++) {
              int oldIndex = (int)((this.start + i) % oldCapacity);

              this.arr[i] = oldArr[oldIndex];
          }

          if (this.size > newCapacity) {
              this.size = newCapacity;
          }

          this.start = 0;
      } else {
          this.start = 0;
          this.size = 0;
      }
  }
  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

    @Override
    public void Clear() {
        Realloc(new Natural(10));
        size = 0L;
    }

    /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer             */
  /* ************************************************************************ */

    @Override
    public void Expand(Natural sizeOffset){
        Realloc(new Natural(this.arr.length+sizeOffset.ToLong()));
        size= size+sizeOffset.ToLong();
    }
    @Override
    public void Expand(){
        Realloc(new Natural((long) (max(this.arr.length*THRESHOLD_FACTOR,1))));
    }
    @Override
    public void Reduce(Natural sizeOffset){
        long newCap = max(this.arr.length - sizeOffset.ToLong(), size);
        Realloc(new Natural(newCap));
    }
    public void Reduce(){
        Realloc(new Natural((long) (max(this.arr.length/THRESHOLD_FACTOR,1))));
    }
    @Override
    public void ArrayAlloc(Natural newSize) {
        this.arr = (Data[]) new Object[(int) newSize.ToLong()];
    }
    @Override
    public void InsertLast(Data data){
        if (this.size == this. arr.length) {
            Expand(Natural.ONE);
        } else {
            this.size++;
        }
        long physicalIndex = (this.start + this.size - 1) % this.arr.length;
        this.arr[(int)physicalIndex] = data;
    }

    public void ShiftLeft(Natural pos,Natural shift) {
        super.ShiftLeft(pos, shift);
        this.size -= shift.ToLong();
        if (this.size < this.arr.length / 2) {
            Realloc(new Natural(this.size));
        }
    }
    @Override
    public void ShiftRight(Natural pos, Natural shift) {
        long shiftVal = shift.ToLong();

        if (size + shiftVal > arr.length) {
            Realloc(new Natural(size + shiftVal));
        }

        size += shiftVal;

        super.ShiftRight(pos, shift);

        long startPos = pos.ToLong();
        for (long i = 0; i < shiftVal; i++) {
            SetAt( null,new Natural(startPos + i));
        }
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
  // ...

  /* ************************************************************************ */
  /* Override specific member functions from ResizableContainer               */
  /* ************************************************************************ */

  // ...

  /* ************************************************************************ */
  /* Specific member functions of Vector                                      */
  /* ************************************************************************ */

  // ...

}
