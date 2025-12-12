package apsd.classes.containers.sequences;

import apsd.classes.containers.sequences.abstractbases.CircularVectorBase;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.sequences.Vector;

/** Object: Concrete (static) circular vector implementation. */
public class CircularVector<Data> extends CircularVectorBase<Data>{ // Must extend CircularVectorBase

  public CircularVector(){
      super();
      ArrayAlloc(new Natural(0));
      this.start=0L;
  }

   public CircularVector(Natural inisize){

       ArrayAlloc(inisize);
       this.start=0L;
   }

   public CircularVector(TraversableContainer<Data> con){
       ArrayAlloc(con.Size());

       this.start=0L;
       if (!con.IsEmpty()) {
           final int[] idx = {0};

           con.TraverseForward(elem -> {
               this.arr[idx[0]] = elem;
               idx[0]++;
               return false;
           });
       }
   }

   protected CircularVector(Data[] arr){

   }
@Override
  public void NewVector(Data[] arr){

    if (arr == null) {
        Clear();
        return;
    }

    ArrayAlloc(new Natural(arr.length));
    this.start = 0L;

    System.arraycopy(arr, 0, this.arr, 0, arr.length);
  }

    @Override
    public Vector<Data> SubVector(Natural firstPos, Natural secondPos) {
        long len;
        len = secondPos.ToLong() - firstPos.ToLong();

        CircularVector<Data> sub;
        sub = new CircularVector<>(new Natural(len));

        long start;
        start = firstPos.ToLong();
        for (int i = 0; i < len; i++) {
            sub.arr[i] = this.arr[(int) ((start + i+this.start)%this.arr.length)];
        }

        return sub;
    }

    @Override
    public Natural Size() {
      return new Natural(this.arr.length);
    }
    @Override
    public void Clear() {
        Realloc(new Natural(0));
    }

}
