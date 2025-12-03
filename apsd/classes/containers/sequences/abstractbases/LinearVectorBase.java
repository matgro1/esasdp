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
