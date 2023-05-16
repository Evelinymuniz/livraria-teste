package br.com.livrariateste.livrariateste.service.exceptionsService;

public class ObjectNotFoundException extends RuntimeException{
    public ObjectNotFoundException(String message) {
        super(message);
    }
}
