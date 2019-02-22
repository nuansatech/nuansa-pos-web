package id.nuansa.pos.api.controller.admin;

import id.nuansa.pos.api.model.response.InitialResponse;
import id.nuansa.pos.config.LocalMessageUtils;
import id.nuansa.starter.base.BaseController;
import id.nuansa.starter.base.ResultResponse;
import id.nuansa.starter.constant.StatusCode;
import id.nuansa.starter.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping(value = "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController extends BaseController {

    @Autowired
    private AdminValidator adminValidator;

    @Autowired
    private AdminService adminService;

    @Autowired
    private LocalMessageUtils messageUtils;

    @PostMapping("/init")
    public ResponseEntity<ResultResponse> init(@RequestHeader("X-Nuansa-Key") String nuansaKey) throws NoSuchAlgorithmException, InvalidKeyException {
        if (!adminValidator.isValidKey(nuansaKey))
            throw new AppException("Key not valid", StatusCode.ERROR, HttpStatus.BAD_REQUEST);

        if (!adminService.isEmptyTables())
            throw new AppException("Cannot generate initial data. Tables are not empty", StatusCode.ERROR, HttpStatus.BAD_REQUEST);

        InitialResponse response = adminService.initData();
        return abstractResponseHandler(response).getResult(messageUtils.insertSuccess());
    }
}
