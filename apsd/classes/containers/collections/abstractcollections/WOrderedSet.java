package apsd.classes.containers.collections.abstractcollections;

import apsd.classes.containers.collections.abstractcollections.bases.WOrderedSetBase;
import apsd.classes.containers.collections.concretecollections.VSortedChain;
import apsd.interfaces.containers.base.IterableContainer;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.Chain;
import apsd.interfaces.containers.collections.Set;
import apsd.interfaces.containers.collections.SortedChain;

/** Object: Wrapper ordered set implementation via ordered chain. */
public class WOrderedSet<Data extends Comparable<? super Data>>
        extends WOrderedSetBase<Data, SortedChain<Data>> {

    public WOrderedSet(){
        super();
        ChainAlloc();
    }

    public WOrderedSet(Chain<Data> chn){
        if (chn instanceof SortedChain) {
            this.chn = (SortedChain<Data>) chn;
        } else {
            ChainAlloc();
            if (chn != null) {
                var it = chn.FIterator();
                while (it.IsValid()) {
                    this.Insert(it.GetCurrent());
                    it.Next();
                }
            }
        }
    }

    public WOrderedSet(TraversableContainer<Data> con){
        ChainAlloc();
        if (con != null && !con.IsEmpty()) {
            if (con instanceof IterableContainer) {
                var it = ((IterableContainer<Data>) con).FIterator();
                while (it.IsValid()) {
                    this.Insert(it.GetCurrent());
                    it.Next();
                }
            } else {
                con.TraverseForward(data -> {
                    this.Insert(data);
                    return false;
                });
            }
        }
    }

    public WOrderedSet(Chain<Data> chn, TraversableContainer<Data> con){
        if (chn instanceof SortedChain) {
            this.chn = (SortedChain<Data>) chn;
        } else {
            ChainAlloc();
            if (chn != null) {
                var it = chn.FIterator();
                while (it.IsValid()) {
                    this.Insert(it.GetCurrent());
                    it.Next();
                }
            }
        }

        if (con != null && !con.IsEmpty()) {
            if (con instanceof IterableContainer) {
                var it = ((IterableContainer<Data>) con).FIterator();
                while (it.IsValid()) {
                    this.Insert(it.GetCurrent());
                    it.Next();
                }
            } else {
                con.TraverseForward(data -> {
                    this.Insert(data);
                    return false;
                });
            }
        }
    }

    @SuppressWarnings("unchecked")
    protected void ChainAlloc() { chn = new VSortedChain<>(); }

    public Set<Data> Intersection(Set<Data> first, Set<Data> second){
        Set<Data> result = new WSet<>();

        if (first == null || second == null) {
            return result;
        }

        var it = first.FIterator();
        while (it.IsValid()) {
            Data element = it.GetCurrent();
            if (second.Exists(element)) {
                result.Insert(element);
            }
            it.Next();
        }

        return result;
    }
}