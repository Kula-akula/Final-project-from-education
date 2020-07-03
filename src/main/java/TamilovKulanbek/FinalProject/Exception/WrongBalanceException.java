package TamilovKulanbek.FinalProject.Exception;

public class WrongBalanceException extends Exception {
    public static final String message = "There are not enough points on the balance";

    public WrongBalanceException() {
        super(message);
    }
}
