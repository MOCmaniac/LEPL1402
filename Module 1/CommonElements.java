public class CommonElements {

    

    /**

     * @param tab1 is a non null array
     * @param tab2 is a non null array
     * @return the number of elements that are the same at the same index
     *         more exactly the size of set {i such that tab1[i] == tab2[i]}
     *         for instance count([1,3,5,5],[1,2,5,5,6]) = 3
     */
    public static int count(int[] tab1, int[] tab2) {
        int max;
        int count = 0;
        if(tab1.length < tab2.length ){
            max = tab1.length;
        }else {
            max = tab2.length;
        }

        for (int i = 0; i < max; i++) {
            if (tab1[i] == tab2[i]) {
                count++;
            }
        }
        return count;
    }
}