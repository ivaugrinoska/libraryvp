package mk.ukim.finki.library_vp.model.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException{
    public UsernameAlreadyExistsException(String username){
        super(String.format("User with username: %s alredy exists", username));
    }
}