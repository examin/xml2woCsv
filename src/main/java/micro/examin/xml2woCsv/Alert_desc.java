package micro.examin.xml2woCsv;

public class Alert_desc {
    Actual ActualObject;
    Budget BudgetObject;


    // Getter Methods

    public Actual getActual() {
        return ActualObject;
    }

    public Budget getBudget() {
        return BudgetObject;
    }

    // Setter Methods

    public void setActual(Actual actualObject) {
        this.ActualObject = actualObject;
    }

    public void setBudget(Budget budgetObject) {
        this.BudgetObject = budgetObject;
    }
}
