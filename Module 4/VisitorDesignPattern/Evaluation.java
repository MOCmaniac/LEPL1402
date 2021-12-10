// Antonin Oswald

/*
Il faudrait pouvoir créer une nouvelle fonction qui permet si possible d'éviter la répétition entre
les différentes fonction visit

Si quelqu'un a du temps à perdre...
 */


public class Evaluation implements Visitor {

    public static void main(String[] args) {
        Node root = new Add(                                            // ((6-2)/(1+1))+(5*2)
                new Div(                                    // (6-2)/(1+1)
                        new Sub(new Leaf(6), new Leaf(2)),      // 6-2
                        new Add(new Leaf(1), new Leaf(1))),     // 1+1
                new Mult(new Leaf(5), new Leaf(2)));        // 5*2
        Visitor calculator = new Evaluation();

        System.out.println(calculator.visit((Add)root)); // 12
    }
    @Override
    public int visit(Add visitable) {

        int rightMember = 0;
        int leftMember = 0;



        if (visitable.getLeft() instanceof Leaf){
            leftMember = ((Leaf) visitable.getLeft()).value;

        } else if (visitable.getLeft() instanceof Div){
            leftMember =   visit((Div) visitable.getLeft());

        } else if (visitable.getLeft() instanceof Sub){
            leftMember =  visit((Sub) visitable.getLeft());

        }else if (visitable.getLeft() instanceof Add){

            leftMember = visit((Add) visitable.getLeft());
        } else if (visitable.getLeft() instanceof Mult){
            leftMember = visit ((Mult) visitable.getLeft());

        }

        if (visitable.getRight() instanceof Leaf){
            rightMember = ((Leaf) visitable.getRight()).value;

        } else if (visitable.getRight() instanceof Div){
            rightMember =   visit((Div) visitable.getRight());

        } else if (visitable.getRight() instanceof Sub){
            rightMember =  visit((Sub) visitable.getRight());

        }else if (visitable.getRight() instanceof Add){

            rightMember = visit((Add) visitable.getRight());
        } else if (visitable.getRight() instanceof Mult){
            rightMember = visit ((Mult) visitable.getRight());

        }




        return rightMember + leftMember;
    }

    @Override
    public int visit(Mult visitable) {

        int rightMember = 0;
        int leftMember = 0;



        if (visitable.getLeft() instanceof Leaf){
            leftMember = ((Leaf) visitable.getLeft()).value;

        } else if (visitable.getLeft() instanceof Div){
            leftMember =   visit((Div) visitable.getLeft());

        } else if (visitable.getLeft() instanceof Sub){
            leftMember =  visit((Sub) visitable.getLeft());

        }else if (visitable.getLeft() instanceof Add){

            leftMember = visit((Add) visitable.getLeft());
        } else if (visitable.getLeft() instanceof Mult){
            leftMember = visit ((Mult) visitable.getLeft());

        }

        if (visitable.getRight() instanceof Leaf){
            rightMember = ((Leaf) visitable.getRight()).value;

        } else if (visitable.getRight() instanceof Div){
            rightMember =   visit((Div) visitable.getRight());

        } else if (visitable.getRight() instanceof Sub){
            rightMember =  visit((Sub) visitable.getRight());

        }else if (visitable.getRight() instanceof Add){

            rightMember = visit((Add) visitable.getRight());
        } else if (visitable.getRight() instanceof Mult){
            rightMember = visit ((Mult) visitable.getRight());

        }


        return rightMember*leftMember;
    }

    @Override
    public int visit(Div visitable) {

        int rightMember = 0;
        int leftMember = 0;



        if (visitable.getLeft() instanceof Leaf){
            leftMember = ((Leaf) visitable.getLeft()).value;

        } else if (visitable.getLeft() instanceof Div){
            leftMember =   visit((Div) visitable.getLeft());

        } else if (visitable.getLeft() instanceof Sub){
            leftMember =  visit((Sub) visitable.getLeft());

        }else if (visitable.getLeft() instanceof Add){

            leftMember = visit((Add) visitable.getLeft());
        } else if (visitable.getLeft() instanceof Mult){
            leftMember = visit ((Mult) visitable.getLeft());

        }

        if (visitable.getRight() instanceof Leaf){
            rightMember = ((Leaf) visitable.getRight()).value;

        } else if (visitable.getRight() instanceof Div){
            rightMember =   visit((Div) visitable.getRight());

        } else if (visitable.getRight() instanceof Sub){
            rightMember =  visit((Sub) visitable.getRight());

        }else if (visitable.getRight() instanceof Add){

            rightMember = visit((Add) visitable.getRight());
        } else if (visitable.getRight() instanceof Mult){
            rightMember = visit ((Mult) visitable.getRight());

        }


        try{
            return leftMember/rightMember;
        }catch (Exception e){
            throw new IllegalArgumentException("You've tried to divide by zero");
        }

    }

    @Override
    public int visit(Sub visitable) {

        int rightMember = 0;
        int leftMember = 0;



        if (visitable.getLeft() instanceof Leaf){
            leftMember = ((Leaf) visitable.getLeft()).value;

        } else if (visitable.getLeft() instanceof Div){
            leftMember =   visit((Div) visitable.getLeft());

        } else if (visitable.getLeft() instanceof Sub){
            leftMember =  visit((Sub) visitable.getLeft());

        }else if (visitable.getLeft() instanceof Add){

            leftMember = visit((Add) visitable.getLeft());
        } else if (visitable.getLeft() instanceof Mult){
            leftMember = visit ((Mult) visitable.getLeft());

        }

        if (visitable.getRight() instanceof Leaf){
            rightMember = ((Leaf) visitable.getRight()).value;

        } else if (visitable.getRight() instanceof Div){
            rightMember =   visit((Div) visitable.getRight());

        } else if (visitable.getRight() instanceof Sub){
            rightMember =  visit((Sub) visitable.getRight());

        }else if (visitable.getRight() instanceof Add){

            rightMember = visit((Add) visitable.getRight());
        } else if (visitable.getRight() instanceof Mult){
            rightMember = visit ((Mult) visitable.getRight());

        }

        return leftMember-rightMember;
    }

    @Override
    public int visit(Leaf visitable) {
        return visitable.getValue();
    }

}
