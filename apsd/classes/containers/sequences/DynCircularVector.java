package apsd.classes.containers.sequences;

import apsd.classes.containers.sequences.abstractbases.DynCircularVectorBase;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;

/** Object: Concrete dynamic circular vector implementation. */
public class DynCircularVector<Data> extends DynCircularVectorBase<Data>{ // Must extend DynCircularVectorBase
    @Override
    public DynCircularVector<Data> SubVector(Natural firstPos, Natural secondPos) {

        long len;
        len = secondPos.ToLong() - firstPos.ToLong();

        DynCircularVector<Data> sub;
        sub = new DynCircularVector<>(new Natural(len));

        long start;
        start = firstPos.ToLong();
        for (int i = 0; i < len; i++) {
            sub.arr[i] = this.arr[(int) ((start + i+this.start)%this.arr.length)];
        }

        return sub;
    }

    public DynCircularVector(){

        this.arr=null;
        this.start=0L;
        this.size=0;
    }

    public DynCircularVector(Natural inisize){

        ArrayAlloc(inisize);
        this.start=0L;
        this.size= inisize.ToLong();
    }

    public DynCircularVector(TraversableContainer<Data> con){
        this.start=0L;
        if (!con.IsEmpty()) {
            final int[] idx = {0};

            this.size=con.Size().ToLong();
            con.TraverseForward(elem -> {
                this.arr[idx[0]] = elem;
                idx[0]++;
                return false;
            });
        }
    }

    protected DynCircularVector(Data[] arr){

        ArrayAlloc(new Natural(arr.length));
        size = arr.length;
        start=0L;
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

}
