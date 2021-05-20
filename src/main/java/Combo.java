public class Combo {
    Item object;
    Item subject;
    Item result;

    public Combo(Item object, Item subject, Item result) {
        this.object = object;
        this.subject = subject;
        this.result = result;
    }

    public Item getObject() { return object; }
    public Item getSubject() { return subject; }
    public Item getResult() { return result; }
}
