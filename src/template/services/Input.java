package template.services;

import java.util.Scanner;

public class Input {

    private final Scanner s;

    public Input() {
        this.s = new Scanner(System.in);
    }

    public int getIntInputWithMessage(String message) {
        System.out.println(message);
        return s.nextInt();
    }
}
