// LEPL1402 - Informatique II
// Vincent Bauffe - 2020-2021

package Module4.ListFiltering;

abstract class Visitable {

    protected Object [] elements;

    abstract void accept(Visitor visitor);

}
