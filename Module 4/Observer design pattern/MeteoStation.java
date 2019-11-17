public class MeteoStation extends Observable {

    @Override
    public Observer[] getSubscribers() {
        Observer[] obsArray = new Observer[subscribers.size()];
        for (int x = 0; x < obsArray.length; x++) {
            obsArray[x] = subscribers.get(x);
        }
        return obsArray;
    }

    @Override
    public void broadcast(Pair<String, Integer> pair) {
        for (int x = 0; x < subscribers.size(); x++) {
            if (subscribers.get(x).equals(pair)) {
                subscribers.get(x).update(pair);
            }
        }
    }

    @Override
    public void addObserver(Observer sub) {
        if (!subscribers.contains(sub))
            subscribers.add(sub);
    }

    @Override
    public boolean removeObserver(Observer sub) {
        if (sub != null) {
            return subscribers.remove(sub);
        }
        return false;
    }

    @Override
    public void setAlert(String alert, int zone) {
        Observer[] obsList = getSubscribers();
        for (Observer o : obsList) {
            if (o.getZone() == zone) {
                o.update(alert);
            }
        }
    }

}