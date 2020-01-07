public class FListMerge {

    /*
     * This method receives an FList and returns the FList containing the same values but sorted with the smallest value to the highest one.
     * Thanks to Bruno P. for providing his code
     */
    public static FList<Integer> mergeSort(FList<Integer> fList){
        int n = fList.length();
        if(n <= 1) {
            return fList;
        } else {
            FList<Integer> firstHalf = fList;
            while(!firstHalf.isEmpty()) firstHalf = firstHalf.tail();
            for(int i = 0; i < n/2; ++i) {
                firstHalf = firstHalf.cons(fList.head());
                fList = fList.tail();
            }
            return merge(mergeSort(firstHalf), mergeSort(fList));
        }
    }

    //TO Complete if needed
    private static FList<Integer> merge(FList<Integer> first, FList<Integer> second) {
        FList<Integer> output = FList.nil();
        while(!first.isEmpty() && !second.isEmpty()) {
            if(first.head() < second.head()) {
                output = output.cons(first.head());
                first = first.tail();
            } else {
                output = output.cons(second.head());
                second = second.tail();
            }
        }
        if(first.isEmpty()) {
            while(!second.isEmpty()) {
                output = output.cons(second.head());
                second = second.tail();
            }
        } else {
            while(!first.isEmpty()) {
                output = output.cons(first.head());
                first = first.tail();
            }
        }
        FList<Integer> actualOutput = FList.nil();
        while(!output.isEmpty()) {
            actualOutput = actualOutput.cons(output.head());
            output = output.tail();
        }
        return actualOutput;
    }
}
