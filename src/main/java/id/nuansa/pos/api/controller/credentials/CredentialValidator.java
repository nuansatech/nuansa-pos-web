package id.nuansa.pos.api.controller.credentials;

import id.nuansa.pos.api.model.request.SignInRequest;
import id.nuansa.pos.api.model.request.SignUpRequest;
import id.nuansa.pos.security.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component
public class CredentialValidator {

    @Autowired
    private TokenGenerator tokenGenerator;

    public String validateForm(SignInRequest request) {
        if (null == request.getEmail() || request.getEmail().isEmpty()) return "email";
        if (null == request.getPassword() || request.getPassword().isEmpty()) return "password";
        return "";
    }

    public String validateForm(SignUpRequest request) {
        if (null == request.getName() || request.getName().isEmpty()) return "name";
        if (null == request.getEmail() || request.getEmail().isEmpty()) return "email";
        if (null == request.getPassword() || request.getPassword().isEmpty()) return "password";
        if (null == request.getPhone()) return "phone";
        if (null == request.getRoleId() || request.getRoleId().isEmpty()) return "role";
        if (null == request.getCompanyId() || request.getCompanyId().isEmpty()) return "company";
        return "";
    }

    public Boolean isPasswordMatch(String secureId, String storedPassword, String requestedPassword) throws NoSuchAlgorithmException, InvalidKeyException {
        String encryptedRequestedPassword = tokenGenerator.generatePassword(secureId, requestedPassword);
        return storedPassword.equals(encryptedRequestedPassword);
    }

}
