public class PhysicsSolver {

    /**
     * An object of this class represents a "slot" in memory that can
     * either contain one floating-point value, or be undefined
     * (i.e. contain no value at all). It is used to store the input
     * or the output or a mathematical relation that occurs in a
     * physics equation.
     *
     * You must modify this class to make it work. You are not allowed
     * to modify the signature of the existing methods.
     **/
    public static class Slot {
        Double value;

        /**
         * The constructor must initialize the slot so that it
         * contains no value.
         **/
        Slot() {
        }

        /**
         * Checks whether two floating-point numbers are equal, given
         * a small tolerance.
         * @param a The first number.
         * @param b The second number.
         * @return <code>true</code> iff. the numbers are equal.
         **/
        static public final boolean areSameDoubles(double a,
                                                   double b) {
            final double THRESHOLD = 0.0001; 
            return Math.abs(a - b) < THRESHOLD;
        }

        /**
         * Whether the slot has been affected with a value.
         * @return <code>true</code> iff. the slot contains a value.
         **/
        public boolean hasValue() {
            if (value==null){
                return false;
            }
            return true;
        }

        /**
         * Get the value stored in the slot. 
         * @return The value.
         * @throws RuntimeException if the slot doesn't contain a value.
         **/
        public double getValue() {
             if (!hasValue()){
                 throw new RuntimeException("slot without a value");
             }
             return value;
        }

        /**
         * Set the value of this slot. If the slot already contains
         * the same value, the slot is not updated. If the slot
         * already contains a different value, an exception must be
         * raised.
         * @param value The value to be stored in the slot.
         * @return <code>true</code> if the value has been changed, or
         * <code>false</code> if the slot already contained the same
         * value.
         * @throws RuntimeException if trying to store a different
         * value in the slot.
         **/
        public boolean setValue(double value) {
             if(!hasValue()){
                 this.value = value;
                 return true;
             } else{
                 if(value == this.value){
                     return false;
                 } else {
                     throw new RuntimeException("Different value");
                 }
             }
        }

        /**
         * Clear the value stored in the slot. After a call to this
         * method, the slot doesn't contain a value.
         **/
        public void clearValue() {
            this.value = null;
        }
    }


    /**
     * This abstract class represents a mathematical relation between
     * different variables. The variables are read from slots and
     * written into slots.
     **/
    public abstract static class Relation {

        /**
         * This method tries to derive the value of unknown variables
         * in the relation, given the values of the known variables.
         * @return <code>true</code> iff. at least one slot has been
         * updated.
         **/
        public abstract boolean update();

        /**
         * This method clears the values of all the variables of the
         * relation, by calling method <code>clearValue</code> on all
         * the slots of the relation.
         **/
        public abstract void clearValues();
    }


    /**
     * This class represents a multiplication, i.e. the mathematical
     * relation of the form <code>a = b * c</code>. This relation is
     * associated with three slots, respectively for the variables
     * <code>a</code>, <code>b</code> and <code>c</code>.
     *
     * You must modify this class to make it work. You are not allowed
     * to modify the signature of the existing methods.
     **/
    public static class Multiplication extends Relation {
        private Slot product_;
        private Slot factor1_;
        private Slot factor2_;

        public Multiplication() {
            this.product_ = new Slot();
            this.factor1_ = new Slot();
            this.factor2_ = new Slot();
        }

        /**
         * Get the slot associated with variable <code>a</code>.
         * @return The slot.
         **/
        public Slot getProduct() {
            return product_;
        }

        /**
         * Set the slot associated with variable <code>a</code>.
         * @param slot The slot. 
         **/
        public void setProduct(Slot slot) {
            product_ = slot;
        }

        /**
         * Get the slot associated with variable <code>b</code>.
         * @return The slot.
         **/
        public Slot getFactor1() {
            return factor1_;
        }

        /**
         * Set the slot associated with variable <code>b</code>.
         * @param slot The slot. 
         **/
        public void setFactor1(Slot slot) {
            factor1_ = slot;
        }

        /**
         * Get the slot associated with variable <code>c</code>.
         * @return The slot.
         **/
        public Slot getFactor2() {
            return factor2_;
        }

        /**
         * Set the slot associated with variable <code>c</code>.
         * @param slot The slot. 
         **/
        public void setFactor2(Slot slot) {
            factor2_ = slot;
        }

        @Override
        public boolean update() {
            if(factor1_.hasValue() && factor2_.hasValue()){
                Double result = factor1_.getValue()*factor2_.getValue();
                if(!product_.hasValue() || result!=product_.getValue()){
                    product_.clearValue();
                    product_.setValue(result);
                    return true;
                }
            }
            if(product_.hasValue() && factor2_.hasValue()){
                Double result = product_.getValue()/factor2_.getValue();
                if(!factor1_.hasValue() || result!=factor1_.getValue()){
                    factor1_.clearValue();
                    factor1_.setValue(result);
                    return true;
                }
            }
            if(product_.hasValue() && factor1_.hasValue()){
                Double result = product_.getValue()/factor1_.getValue();
                if(!factor2_.hasValue() || result!=factor2_.getValue()){
                    factor2_.clearValue();
                    factor2_.setValue(result);
                    return true;
                }
            }
            return false;
        }

        @Override
        public void clearValues() {
            product_.clearValue();
            factor1_.clearValue();
            factor2_.clearValue();
        }
    }

  
    /**
     * This class represents the square operation, i.e. the
     * mathematical relation of the form <code>a = b^2</code>. This
     * relation is associated with two slots, respectively for the
     * variables <code>a</code> and <code>b</code>.
     *
     * You must modify this class to make it work. You are not allowed
     * to modify the signature of the existing methods.
     **/
    public static class Squaring extends Relation {
        private Slot square_;
        private Slot number_;

        public Squaring() {
            this.square_ = new Slot();
            this.number_ = new Slot();
        }

        /**
         * Get the slot associated with variable <code>a</code>.
         * @return The slot.
         **/
        public Slot getSquare() {
            return square_;
        }

        /**
         * Set the slot associated with variable <code>a</code>.
         * @param slot The slot. 
         **/
        public void setSquare(Slot slot) {
            square_ = slot;
        }

        /**
         * Get the slot associated with variable <code>b</code>.
         * @return The slot.
         **/
        public Slot getNumber() {
            return number_;
        }

        /**
         * Set the slot associated with variable <code>b</code>.
         * @param slot The slot. 
         **/
        public void setNumber(Slot slot) {
            number_ = slot;
        }

        @Override
        public boolean update() {
            if(number_.hasValue()){
                Double result = number_.getValue()*number_.getValue();
                if(!square_.hasValue() || result!=square_.getValue()){
                    square_.clearValue();
                    square_.setValue(result);
                    return true;
                }
            }
            if(square_.hasValue()){
                Double result = Math.sqrt(square_.getValue());
                if(!number_.hasValue() || result!=number_.getValue()){
                    number_.clearValue();
                    number_.setValue(result);
                    return true;
                }
            }
            return false;
        }

        @Override
        public void clearValues() {
            square_.clearValue();
            number_.clearValue();
        }
    }


    /**
     * Solve a system of two mathematical relations. It updates the
     * individual relations until no more variables can be
     * updated. You are not allowed to modify this function.
     * @param r1 The first relation.
     * @param r2 The second relation.
     **/
    public static void solve(Relation r1,
                             Relation r2) {
        while (r1.update() ||
               r2.update()) {
            // Repeat until convergence
        }
    }


    /**
     * Solve a system of three mathematical relations. It updates the
     * individual relations until no more variables can be
     * updated. You are not allowed to modify this function.
     * @param r1 The first relation.
     * @param r2 The second relation.
     * @param r3 The third relation.
     **/
    public static void solve(Relation r1,
                             Relation r2,
                             Relation r3) {
        while (r1.update() ||
               r2.update() ||
               r3.update()) {
            // Repeat until convergence
        }
    }


    /**
     * Solve a system of four mathematical relations. It updates the
     * individual relations until no more variables can be
     * updated. You are not allowed to modify this function.
     * @param r1 The first relation.
     * @param r2 The second relation.
     * @param r3 The third relation.
     * @param r4 The fourth relation.
     **/
    public static void solve(Relation r1,
                             Relation r2,
                             Relation r3,
                             Relation r4) {
        while (r1.update() ||
               r2.update() ||
               r3.update() ||
               r4.update()) {
            // Repeat until convergence
        }
    }

}
