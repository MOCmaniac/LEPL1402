import java.util.ArrayList;

/**
 * This class implements a bank account.
 * You can deposit and withdraw money from the account.
 * Read carefully the comments of each method and fill the missing
 * parts marked with "TODO".
 * You can ADD any code you like in this class (new members, new methods, etc.).
 * but do not add a package
 */

public class ObservableAccount {

    int balance = 0;


    ArrayList<AccountObserver> observers = new ArrayList<>();
    ArrayList<Integer> maxOfObservers = new ArrayList<>();


    public interface AccountObserver {
        void accountHasChanged();

    }

    public int relatedMax(AccountObserver ac){

        int index = this.observers.indexOf(ac);
        return this.maxOfObservers.get(index);

    }

    /**
     * Get the account balance  (franc. "solde")
     * The initial balance of the account is 0.
     *
     * @return The balance
     */
    public int getBalance() {
        // TODO
        return this.balance;
    }

    /**
     * Deposit an amount into the account
     *
     * @param amount The amount to deposit
     */
    public void deposit(int amount) {
        this.balance += amount;

        for (AccountObserver o:
             observers) {
            if (relatedMax(o) <= amount){
                o.accountHasChanged();
            }
        }


    }

    /**
     * Withdraw an amount from the account.
     * An account cannot become negative.
     * If you try to withdraw 1000 Euro from an account that has
     * only 500 Euro, the withdrawal is NOT executed.
     *
     * @param amount The sum to withdraw
     */
    public void withdraw(int amount) {
        if (this.getBalance() > amount) {
            this.balance = this.balance - amount;

            for (AccountObserver o :
                    observers) {
                if (relatedMax(o) <= amount) {
                    o.accountHasChanged();
                }
            }

        }
    }


    /**
     * Add an observer to the account.
     * The observer will be notified if an amount is deposited or withdrawn
     * that is greater than the specified maximum.
     * The observer must NOT be notified if the withdrawal is not executed
     * (see comment of the method 'withdraw')
     *
     * The user of this class can change the maximum for an added observer by calling
     * this method again with the same observer. Example:
     *      account.addObserver(myObserver,1000);
     *      account.addObserver(myObserver,2000); // change maximum for this observer
     *
     * @param observer The observer to add.
     * @param maximum The observer will be notified if the deposited or withdrawn
     *                amount is greater than "maximum".
     *
     */
    public void addObserver(AccountObserver observer, int maximum) {




        if (!observers.isEmpty()) {

            for (AccountObserver o: observers) {
                if(observer == o){
                    if(maximum != relatedMax(o) ){
                        int index = maxOfObservers.indexOf(relatedMax(o));
                        maxOfObservers.set(index, maximum);
                    }

                    return;

                }
            }

        }

        observers.add(observer);
        maxOfObservers.add(maximum);

    }




}

