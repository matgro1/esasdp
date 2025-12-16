package apsd.classes.containers.collections.abstractcollections;

import apsd.classes.containers.collections.abstractcollections.bases.WSetBase;
import apsd.classes.containers.collections.concretecollections.VList;
import apsd.interfaces.containers.base.IterableContainer;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.Chain;
import apsd.interfaces.containers.collections.Set;

/** Object: Wrapper set implementation via chain. */
public class WSet<Data> extends WSetBase<Data, Chain<Data>> {

    public WSet(){
        super();
        ChainAlloc();
    }

    public WSet(Chain<Data> chn){
        this.chn = chn;
    }

    public WSet(TraversableContainer<Data> con) {
        ChainAlloc();
        if(con == null || con.IsEmpty()){ return; }

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

    public WSet(Chain<Data> chn, TraversableContainer<Data> con) {
        if (chn != null) {
            this.chn = chn;
        } else {
            ChainAlloc();
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

    protected void ChainAlloc() { chn = new VList<>(); }

    public Set<Data> Intersection(Set<Data> first, Set<Data> second){
        Set<Data> result = new WSet<>();

        if (first == null || second == null) {
            return result;
        }

        var it = first.FIterator();
        while(it.IsValid()) {
            Data element = it.GetCurrent();
            if (second.Exists(element)) {
                result.Insert(element);
            }
            it.Next();
        }

        return result;
    }
}