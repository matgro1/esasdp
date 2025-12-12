package apsd.classes.containers.collections.abstractcollections;

 import apsd.classes.containers.collections.abstractcollections.bases.WSetBase;
 import apsd.classes.containers.collections.concretecollections.VList;
 import apsd.interfaces.containers.base.TraversableContainer;
 import apsd.interfaces.containers.collections.Chain;
 import apsd.interfaces.containers.collections.Set;

/** Object: Wrapper set implementation via chain. */
public class WSet<Data> extends WSetBase<Data,Chain<Data>>{ // Must extend WSetBase

  public WSet(){
      super();
  }

  public WSet(Chain<Data> chn){
      this.chn = chn;
  }

    public WSet(TraversableContainer<Data> con) {
        ChainAlloc();
        if (con != null) {
            con.TraverseForward(this::Insert);
        }
    }

    public WSet(Chain<Data> chn, TraversableContainer<Data> con) {
        if (chn != null) {
            this.chn =  chn;
        } else {
            ChainAlloc();
        }

        if (con != null) {
            con.TraverseForward(this::Insert);
        }
    }

  // @Override
  protected void ChainAlloc() { chn = new VList<>(); }
    public  Set<Data> Intersection(Set<Data> first, Set<Data> second){

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

    }

}
