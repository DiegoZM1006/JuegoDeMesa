public class Box {

    private Box next;

    private Player j1;
    private Player j2;

    public Box(Player j1, Player j2) {
        this.j1 = j1;
        this.j2 = j2;
    }

    public Box getNext() {
        return next;
    }

    public void setNext(Box next) {
        this.next = next;
    }

    public void setPlayerj1(String name) {
        j1.setName(name);
    }

    public void setPlayerj2(String name) {
        j2.setName(name);
    }

    public String getPlayerj1() {
        return j1.getName();
    }

    public String getPlayerj2() {
        return j2.getName();
    }

    @Override
    public String toString() {
        return j1.getName() + "," + j2.getName();
    }

}
