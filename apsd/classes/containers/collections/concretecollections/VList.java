package apsd.classes.containers.collections.concretecollections;

import apsd.classes.containers.collections.concretecollections.bases.VChainBase;
import apsd.classes.containers.sequences.DynVector;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.IterableContainer;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.Chain;
import apsd.interfaces.containers.collections.List;
import apsd.interfaces.containers.collections.Set;
import apsd.interfaces.containers.sequences.MutableSequence;

/** Object: Concrete list implementation on (dynamic linear) vector. */
public class VList<Data> extends VChainBase<Data> implements List<Data> {

 public VList() {
  super(new DynVector<Data>());
 }

 public VList(TraversableContainer<Data> con) {
  super(new DynVector<Data>(con));
 }
 @Override
 public apsd.interfaces.containers.iterators.MutableForwardIterator<Data> FIterator() {
  return vec.FIterator();
 }

 @Override
 public apsd.interfaces.containers.iterators.MutableBackwardIterator<Data> BIterator() {
  return vec.BIterator();
 }

 @Override
 public apsd.interfaces.containers.sequences.MutableSequence<Data> SubSequence(Natural start, Natural end) {
  return vec.SubSequence(start, end);
 }

 protected VList(DynVector<Data> vec) {
  super(vec);
 }

 /* ************************************************************************ */
 /* Override specific member functions from InsertableAtSequence             */
 /* ************************************************************************ */
 @Override
 public boolean Insert(Data data) {
  InsertFirst(data);
  return true;
 }

 @Override
 public void InsertAt(Data data, Natural pos) {
  vec.InsertAt(data, pos);
 }

 /* ************************************************************************ */
 /* Override specific member functions from List                             */
 /* ************************************************************************ */
 @Override
 public List<Data> SubList(Natural start, Natural end) {
  return new VList<>( (DynVector<Data>) vec.SubVector(start, end) );
 }

 @Override
 public Chain<Data> SubChain(Natural start, Natural end) {
  return SubList(start, end);
 }

 @Override
 public void SetAt(Data data, Natural pos) {
  vec.SetAt(data, pos);
 }



 @Override
 public Data AtNRemove(Natural pos) {
  return vec.AtNRemove(pos);
 }



 @Override
 public boolean IsEqual(IterableContainer<Data> container) {
  return false;
 }


}