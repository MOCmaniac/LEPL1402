import java.util.LinkedList;
import java.util.List;

/**
 * Your task is to control a robot using a sequence of textual
 * commands. The robot can move forward, turn left, or turn right. The
 * robot is controlled through the following set of 4 basic commands:
 * 
 * - FORWARD
 *   => Move the robot forward
 *
 * - LEFT
 *   => Turn the robot to the left
 *
 * - RIGHT
 *   => Turn the robot to the right
 *
 * - REPEAT N
 *   ...
 *   END REPEAT
 *   => Repeat "N" times the commands "..."
 *
 * For instance, here is a sample sequence of textual commands:
 * 
 *  FORWARD
 *  REPEAT 3
 *  FORWARD
 *  RIGHT
 *  END REPEAT
 *  FORWARD
 *  FORWARD
 *
 * If applied to a robot that turns at right angles, this sample
 * sequence would generate the following pattern, where "x" denotes
 * the starting point of the robot, and "^" is its final position:
 *
 *      ^
 *      |
 *      |
 * x---->---->
 *      |    |
 *      |    |
 *      <----<
 *
 * Pay attention to the fact that the "REPEAT" commands can be nested
 * (i.e. a "REPEAT" command may recursively contain other "REPEAT"
 * commands).
 * 
 * Using the "Factory" design pattern, you must convert an array of
 * strings containing commands into an "Action" object that can be
 * used to control one instance of the "Robot" interface.
 **/

public class RobotActionFactory {

    /**
     * Interface defining an abstract robot to be controlled.
     **/
    public static interface Robot {

        /**
         * Move the robot forward.
         **/
        void moveForward();

        /**
         * Turn the robot to the left.
         **/
        void turnLeft();

        /**
         * Turn the robot to the right.
         **/
        void turnRight();
    }
    

    /**
     * Interface defining an abstract action that modifies the state
     * of the given robot. An action can correspond to one isolated
     * command (move forward, turn left, or turn right), to one
     * sequence of actions, or to one repetition of an action.
     **/
    public static interface Action {

        /**
         * Apply this action to the given robot.
         * @param robot The robot.
         **/
        void apply(Robot robot);
    }


    /**
     * This type of "Action" moves the robot forward.
     **/
    private static class MoveForwardAction implements Action {
        @Override
        public void apply(Robot robot) {
            robot.moveForward();
        }
    }

    
    /**
     * This type of "Action" turns the robot to the left.
     **/
    private static class TurnLeftAction implements Action {
        @Override
        public void apply(Robot robot) {
            robot.turnLeft();
        }
    }

    
    /**
     * This type of "Action" turns the robot to the right.
     **/
    private static class TurnRightAction implements Action {
        @Override
        public void apply(Robot robot) {
            robot.turnRight();
        }
    }


    /**
     * This type of "Action" represents a sequence of actions to be
     * applied to the robot.
     **/
    private static class SequenceOfActions implements Action {
        private List<Action> actions = new LinkedList<Action>();

        /**
         * Append a new action to the end of the sequence of actions.
         * @param action The action to be added.
         **/
        public void add(Action action) {
            actions.add(action);
        }

        @Override
        public void apply(Robot robot) {
            for(Action a : actions){
                a.apply(robot);
            }
        }
    }


    /**
     * This type of "Action" executes another action, for a given
     * number of times.
     **/
    private static class RepeatAction implements Action {
        private int times;
        private Action action;

        /**
         * Constructor for a repetition of one action.
         * @param times The number of times the action must be executed.
         * @param action The action to be repeated.
         **/
        RepeatAction(int times,
                     Action action) {
            this.times = times;
            this.action = action;
        }

        @Override
        public void apply(Robot robot) {
            for (int i = 0; i < times; i++) {
                action.apply(robot);
            }
        }
    }

    /**
     * The factory method you have to implement.
     *
     * NB 1: In order to parse an integer from some string "s", you
     * can use the standard function "Integer.parseInt(s)".
     *
     * NB 2: If the array of commands cannot be parsed (e.g. because
     * of an unknown action, or because of a "REPEAT" command without
     * an "END REPEAT"), you must throw an exception of class
     * "IllegalArgumentException".
     *
     * @param commands The array of commands to drive the robot.
     * @return An "Action" object that will move the robot
     * according to the commands.
     **/
    public Action parse(String[] commands) {
        SequenceOfActions sequence = new SequenceOfActions();
        for(int i = 0; i<commands.length; i++){
            switch (commands[i]){
                case "FORWARD":
                    sequence.add(new MoveForwardAction());
                    break;
                case "RIGHT":
                    sequence.add(new TurnRightAction());
                    break;
                case "LEFT":
                    sequence.add(new TurnLeftAction());
                    break;
                default:
                    if(commands[i].contains("REPEAT")){
                        String[] split = commands[i].split(" ");
                        try {
                            int step = Integer.parseInt(split[1]);
                            Integer end = getLastRepeat(commands);
                            if(end==null){
                                throw new IllegalArgumentException();
                            }
                            String[] subCommands = new String[end-i-1];
                            System.arraycopy(commands,i+1,subCommands,0,end-i-1);
                            SequenceOfActions repeatSequence = (SequenceOfActions) parse(subCommands);
                            Action repeated = new RepeatAction(step, repeatSequence);
                            sequence.add(repeated);
                            i = end;
                        } catch (NumberFormatException e){
                            throw new IllegalArgumentException(); //Si pas un entier
                        }
                    } else {
                        throw new IllegalArgumentException();
                    }
            }
        }
        return sequence;
    }

    public Integer getLastRepeat(String[] array){
        Integer index = null;
        for (int i = 0; i < array.length; i++) {
            if(array[i].contains("END REPEAT")){
                index = i;
            }
        }
        return index;
    }

}
