package id.nuansa.pos.api.controller.credentials;

import id.nuansa.pos.api.model.request.SignUpRequest;
import id.nuansa.pos.entity.User;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface CredentialService {

    User checkExistanceUser(String email);

    User signUp(SignUpRequest request) throws NoSuchAlgorithmException, InvalidKeyException;
}
