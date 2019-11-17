public class Client extends Observer {

    public Client(int z){
        news = "";
        zone = z;
    }

    @Override
    public void update(Object o) { // o represent a String alert, news
        if(o instanceof String){
            this.news = (String) o;
        }
    }

    @Override
    public int getZone() {
        return this.zone;
    }

    @Override
    public String getNews() {
        return this.news;
    }

}