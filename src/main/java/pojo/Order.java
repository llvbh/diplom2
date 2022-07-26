package pojo;

public class Order {
    private String name;
    private boolean success;
    private NewOrder order;

    public String getName() {
        return name;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public NewOrder getOrder() {
        return order;
    }

    public void setOrder(NewOrder order) {
        this.order = order;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Order(String name, boolean success, NewOrder order) {
        this.name = name;
        this.success = success;
        this.order = order;
    }
}
