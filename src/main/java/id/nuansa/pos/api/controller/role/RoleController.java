package id.nuansa.pos.api.controller.role;

import id.nuansa.pos.api.model.request.AddRoleRequest;
import id.nuansa.pos.config.LocalMessageUtils;
import id.nuansa.pos.entity.Role;
import id.nuansa.pos.security.TokenChecker;
import id.nuansa.starter.base.BaseController;
import id.nuansa.starter.base.ResultResponse;
import id.nuansa.starter.constant.StatusCode;
import id.nuansa.starter.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/role", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleController extends BaseController {

    @Autowired
    private RoleValidator validator;

    @Autowired
    private RoleService roleService;

    @Autowired
    private LocalMessageUtils messageUtils;

    @Autowired
    private TokenChecker checker;

    @PostMapping
    public ResponseEntity<ResultResponse> addRole(@RequestHeader("Authorization") String accessToken,
                                                  @RequestBody AddRoleRequest request) {
        if (!checker.check(accessToken))
            throw new AppException(messageUtils.tokenExpired(), StatusCode.ERROR, HttpStatus.BAD_REQUEST);

        String field = validator.validateForm(request);
        if (!field.isEmpty())
            throw new AppException(messageUtils.dataNotValid(field), StatusCode.ERROR, HttpStatus.BAD_REQUEST);

        Role role = roleService.getOneByName(request.getName());
        if (null != role)
            throw new AppException(messageUtils.dataExist(), StatusCode.ERROR, HttpStatus.BAD_REQUEST);

        role = roleService.add(request);
        if (null == role)
            throw new AppException(messageUtils.insertFailed(), StatusCode.ERROR, HttpStatus.BAD_REQUEST);

        return abstractResponseHandler(role).getResult(messageUtils.insertSuccess());
    }

    @PutMapping("/{secure-id}")
    public ResponseEntity<ResultResponse> updateRole(@RequestHeader("Authorization") String accessToken,
                                                     @PathVariable("secure-id") String secureId,
                                                     @RequestBody AddRoleRequest request) {
        if (!checker.check(accessToken))
            throw new AppException(messageUtils.tokenExpired(), StatusCode.ERROR, HttpStatus.BAD_REQUEST);

        String field = validator.validateForm(request);
        if (!field.isEmpty())
            throw new AppException(messageUtils.dataNotValid(field), StatusCode.ERROR, HttpStatus.BAD_REQUEST);

        Role role = roleService.getOneByName(request.getName());
        if (null != role)
            throw new AppException(messageUtils.dataExist(), StatusCode.ERROR, HttpStatus.BAD_REQUEST);

        role = roleService.update(secureId, request);
        if (null == role)
            throw new AppException(messageUtils.updateFailed(), StatusCode.ERROR, HttpStatus.BAD_REQUEST);

        return abstractResponseHandler(role).getResult(messageUtils.insertSuccess());
    }
}
