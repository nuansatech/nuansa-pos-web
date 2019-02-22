package id.nuansa.pos.api.controller.credentials;

import id.nuansa.pos.api.controller.company.CompanyService;
import id.nuansa.pos.api.controller.role.RoleService;
import id.nuansa.pos.api.model.request.SignInRequest;
import id.nuansa.pos.api.model.request.SignUpRequest;
import id.nuansa.pos.api.model.response.BasicData;
import id.nuansa.pos.api.model.response.LoginResponse;
import id.nuansa.pos.config.LocalMessageUtils;
import id.nuansa.pos.entity.Company;
import id.nuansa.pos.entity.Role;
import id.nuansa.pos.entity.User;
import id.nuansa.pos.security.TokenGenerator;
import id.nuansa.pos.security.TokenPayload;
import id.nuansa.starter.base.BaseController;
import id.nuansa.starter.base.ResultResponse;
import id.nuansa.starter.constant.StatusCode;
import id.nuansa.starter.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class CredentialController extends BaseController {

    @Autowired
    private CredentialValidator validator;

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private LocalMessageUtils messageUtils;

    @Autowired
    private TokenGenerator tokenGenerator;

    @PostMapping("/signin")
    public ResponseEntity<ResultResponse> signIn(@RequestBody SignInRequest request) throws InvalidKeyException, NoSuchAlgorithmException {
        String field = validator.validateForm(request);
        if (!field.isEmpty())
            throw new AppException(messageUtils.dataNotValid(field), StatusCode.ERROR, HttpStatus.BAD_REQUEST);

        User user = credentialService.checkExistanceUser(request.getEmail());
        if (null == user)
            throw new AppException(messageUtils.dataNotFound() + ": user", StatusCode.NOT_FOUND, HttpStatus.NOT_FOUND);

        if (!validator.isPasswordMatch(user.getSecureId(), user.getPassword(), request.getPassword()))
            throw new AppException(messageUtils.passwordNotMatch(), StatusCode.ERROR, HttpStatus.BAD_REQUEST);

        LoginResponse response = generateResponse(user);
        return abstractResponseHandler(response).getResult(messageUtils.loginSuccess());
    }

    @PostMapping("/signup")
    public ResponseEntity<ResultResponse> signUp(@RequestBody SignUpRequest request) throws InvalidKeyException, NoSuchAlgorithmException {
        String field = validator.validateForm(request);
        if (!field.isEmpty())
            throw new AppException(messageUtils.dataNotValid(field), StatusCode.ERROR, HttpStatus.BAD_REQUEST);

        User user = credentialService.checkExistanceUser(request.getEmail());
        if (null != user)
            throw new AppException(messageUtils.dataExist(), StatusCode.ERROR, HttpStatus.BAD_REQUEST);

        user = credentialService.signUp(request);
        if (null == user)
            throw new AppException(messageUtils.insertFailed(), StatusCode.ERROR, HttpStatus.BAD_REQUEST);

        LoginResponse response = generateResponse(user);
        return abstractResponseHandler(response).getResult(messageUtils.loginSuccess());
    }

    private LoginResponse generateResponse(User user) {
        Role role = roleService.getOne(user.getRoleId());
        if (null == role)
            throw new AppException(messageUtils.dataNotFound() + ": role", StatusCode.NOT_FOUND, HttpStatus.NOT_FOUND);

        Company company = companyService.getOne(user.getCompanyId());
        if (null == company)
            throw new AppException(messageUtils.dataNotFound() + ": company", StatusCode.NOT_FOUND, HttpStatus.NOT_FOUND);

        TokenPayload payloads = new TokenPayload();
        payloads.setSecureId(user.getSecureId());
        payloads.setEmail(user.getEmail());
        payloads.setCompany(company.getName());
        payloads.setRole(role.getName());
        String accessToken = tokenGenerator.generateToken(user.getName(), payloads);

        LoginResponse response = new LoginResponse();
        response.setAccessToken(accessToken);
        response.setSecureId(user.getSecureId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setCompany(new BasicData(company.getSecureId(), company.getName()));
        response.setRole(new BasicData(role.getSecureId(), role.getName()));
        return response;
    }
}
