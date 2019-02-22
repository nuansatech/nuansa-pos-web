package id.nuansa.pos.api.controller.credentials;

import id.nuansa.pos.api.model.request.SignUpRequest;
import id.nuansa.pos.entity.User;
import id.nuansa.pos.repository.UserRepository;
import id.nuansa.pos.security.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;

@Service
public class CredentialServiceImpl implements CredentialService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenGenerator tokenGenerator;

    @Override
    public User checkExistanceUser(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User signUp(SignUpRequest request) throws NoSuchAlgorithmException, InvalidKeyException {
        String secureid = UUID.randomUUID().toString();
        String encryptedPassword = tokenGenerator.generatePassword(secureid, request.getPassword());

        User user = new User();
        user.setSecureId(secureid);
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(encryptedPassword);
        user.setPhone(request.getPhone());
        user.setRoleId(request.getRoleId());
        user.setCompanyId(request.getCompanyId());
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());

        return userRepository.save(user);
    }
}
