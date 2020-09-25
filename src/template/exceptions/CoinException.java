package template.exceptions;

public class CoinException extends Throwable {
    public CoinException(String message) {
        System.out.println(message);
    }
}
