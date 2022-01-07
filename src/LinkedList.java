public abstract class LinkedList<T> {
    private LinkedList<T> next;
    private T content;
    protected String separator;

    LinkedList(String separator) {
        this(null, separator);
    }

    LinkedList(T first, String separator) {
        this.content = first;
        next = null;
        this.separator = separator;
    }

    void append(T o) {
        LinkedList current = this;
        if (this.content == null) {
            this.content = o;
        } else {
            while (current.next != null) {
                current = current.next;
            }
            //current.next = new LinkedList(o, separator);
            current.next = current.create(o);
        }
    }

    protected abstract LinkedList create(T content);

    @Override
    public String toString() {
        LinkedList current = this;
        StringBuilder sb = new StringBuilder();
        if (content != null) {
            while (current != null) {
                sb.append(current.content.toString());
                sb.append(separator);
                current = current.next;
            }
        }
        return sb.toString();
    }
}
