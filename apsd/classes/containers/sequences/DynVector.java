package apsd.classes.containers.sequences;

 import apsd.classes.containers.sequences.abstractbases.DynLinearVectorBase;
 import apsd.classes.utilities.Natural;
 import apsd.interfaces.containers.base.TraversableContainer;

/** Object: Concrete dynamic (linear) vector implementation. */
public class DynVector<Data> extends DynLinearVectorBase<Data>{ // Must extend DynLinearVectorBase

  public DynVector(){
      super();
  }

  public DynVector(Natural inisize){

      ArrayAlloc(inisize);
      size = inisize.ToLong();
  }

  public DynVector(TraversableContainer<Data> con){
      ArrayAlloc(con.Size());

      if (!con.IsEmpty()) {
          final int[] idx = {0};
          size = con.Size().ToLong();
          con.TraverseForward(elem -> {
              this.arr[idx[0]] = elem;
              idx[0]++;
              return false;
          });
      }
  }

  protected DynVector(Data[] arr){
      ArrayAlloc(new Natural(arr.length));
      size = arr.length;
      System.arraycopy(arr, 0, this.arr, 0, arr.length);
  }

  protected void NewVector(Data[] data){
      if (data == null) {
          Clear();
          return;
      }

      ArrayAlloc(new Natural(data.length));

      this.size = data.length;

      System.arraycopy(data, 0, this.arr, 0, data.length);
  }
  @Override
  public DynVector<Data> SubVector(Natural firstPos, Natural secondPos) {

      long len;
      len = secondPos.ToLong() - firstPos.ToLong();

      DynVector<Data> sub;
      sub = new DynVector<>(new Natural(len));

      long start;
      start = firstPos.ToLong();
      for (int i = 0; i < len; i++) {
          sub.arr[i] = this.arr[(int) (start + i)];
      }

      return sub;  }

}
