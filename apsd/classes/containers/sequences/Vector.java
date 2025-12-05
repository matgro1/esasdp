package apsd.classes.containers.sequences;

import apsd.classes.containers.sequences.abstractbases.LinearVectorBase;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;

/** Object: Concrete (static linear) vector implementation. */
public class Vector<Data> extends LinearVectorBase<Data> { // Must extend LinearVectorBase

 public Vector(){
  this.arr=null;
 }

 public Vector(Natural inisize){
  ArrayAlloc(inisize);
 }

 public Vector(TraversableContainer<Data> con) {
  ArrayAlloc(con.Size());

  if (!con.IsEmpty()) {
   final int[] idx = {0};

   con.TraverseForward(elem -> {
    this.arr[idx[0]] = elem;
    idx[0]++;
    return false;
   });
  }
 }

 protected Vector(Data[] arr){
  ArrayAlloc(new Natural(arr.length));
  System.arraycopy(arr, 0, this.arr, 0, arr.length);
 }


 @Override
 public apsd.interfaces.containers.sequences.Vector<Data> SubVector(Natural firstPos, Natural secondPos) {
  long len;
  len = secondPos.ToLong() - firstPos.ToLong();

  Vector<Data> sub;
  sub = new Vector<>(new Natural(len));

  long start;
  start = firstPos.ToLong();
  for (int i = 0; i < len; i++) {
   sub.arr[i] = this.arr[(int) (start + i)];
  }

  return sub;
 }

 @Override
 public Natural Size() {
  return Capacity();
 }


}
