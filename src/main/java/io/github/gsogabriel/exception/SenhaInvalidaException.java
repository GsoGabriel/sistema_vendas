package io.github.gsogabriel.exception;

public class SenhaInvalidaException extends RuntimeException {
    public SenhaInvalidaException() {
        super("Senha Inválida.");
    }
}
