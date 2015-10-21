/**
 * Describes transaction
 */
public class Transaction {
    private Long value;
    private String info;
    //add time field?

    public Transaction(Long value, String info) {
        this.info = info;
        this.value = value;
    }


    public Long getValue() {
        return value;
    }

    public String getInfo() {
        return info;
    }
}
