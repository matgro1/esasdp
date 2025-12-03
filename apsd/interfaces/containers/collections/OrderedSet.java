package apsd.interfaces.containers.collections;

public interface OrderedSet<Data> extends Set<Data> { // Must extend Set

  Data Min();

  void RemoveMin();

  Data MinNRemove();

  Data Max();

  void RemoveMax();

  Data MaxNRemove();

  Data Predecessor(Data element);

  void RemovePredecessor(Data element);

  Data PredecessorNRemove(Data element);

  Data Successor(Data element);

  void RemoveSuccessor(Data element);

  Data SuccessorNRemove(Data element);

}
