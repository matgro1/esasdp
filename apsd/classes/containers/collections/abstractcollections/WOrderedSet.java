package apsd.classes.containers.collections.abstractcollections;

import apsd.classes.containers.collections.abstractcollections.bases.WOrderedSetBase;
import apsd.classes.containers.collections.concretecollections.VList;
import apsd.classes.containers.collections.concretecollections.VSortedChain;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.Chain;
import apsd.interfaces.containers.collections.Set;
import apsd.interfaces.containers.collections.SortedChain;

/** Object: Wrapper ordered set implementation via ordered chain. */
public class WOrderedSet<Data extends Comparable<? super Data>> extends WOrderedSetBase<Data, SortedChain<Data>> { // Must extend WOrderedSetBase

    public WOrderedSet(){
        super();
    }

    public WOrderedSet(Chain<Data> chn){
        if (chn instanceof SortedChain) {

            this.chn = (SortedChain<Data>) chn;
        } else {
            ChainAlloc();
            if (chn != null) {
                chn.TraverseForward(this::Insert);
            }
        }
    }

    public WOrderedSet(TraversableContainer<Data> con){

        ChainAlloc();
        if (con != null) {
            con.TraverseForward(this::Insert);
        }    }

    public WOrderedSet(Chain<Data> chn, TraversableContainer<Data> con){
        if (chn != null) {
            this.chn =  chn;
        } else {
            ChainAlloc();
        }

        if (con != null) {
            con.TraverseForward(this::Insert);
        }
    }


    protected void ChainAlloc() { chn = new VSortedChain<>(); }

    public Set<Data> Intersection(Set<Data> first, Set<Data> second){

        Set<Data> result = new WSet<>();

        if (first == null || second == null) {
            return result;
        }

        first.TraverseForward(element -> {
            if (second.Exists(element)) {
                result.Insert(element);
            }
            return false;
        });

        return result;

    }  // ...

}
