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

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

    @Override
    public void Clear() {
        Reduce(Capacity());
    }

    /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer             */
  /* ************************************************************************ */

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
    @Override
    public void ArrayAlloc(Natural newSize) {
        this.arr = (Data[]) new Object[(int) newSize.ToLong()];
        this.start = 0L;
    }
    @Override

    public void ShiftLeft(Natural pos,Natural shift) {
        super.ShiftLeft(pos, shift);
        this.size -= shift.ToLong();
        Reduce(shift);
    }
    @Override
    public void ShiftRight(Natural pos, Natural shift) {
       Expand(shift);
        super.ShiftRight(pos, shift);
        this.size += shift.ToLong();
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
