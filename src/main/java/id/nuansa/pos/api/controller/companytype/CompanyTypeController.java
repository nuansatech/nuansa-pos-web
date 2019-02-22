package id.nuansa.pos.api.controller.companytype;

import id.nuansa.pos.api.model.request.AddCompanyTypeRequest;
import id.nuansa.pos.config.LocalMessageUtils;
import id.nuansa.pos.entity.CompanyType;
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
@RequestMapping(value = "/company-type", produces = MediaType.APPLICATION_JSON_VALUE)
public class CompanyTypeController extends BaseController {

    @Autowired
    private CompanyTypeService companyTypeService;

    @Autowired
    private CompanyTypeValidator validator;

    @Autowired
    private LocalMessageUtils messageUtils;

    @Autowired
    private TokenChecker checker;

    @PostMapping
    public ResponseEntity<ResultResponse> addCompanyType(@RequestHeader("Authorization") String accessToken,
                                                         @RequestBody AddCompanyTypeRequest request) {
        if (!checker.check(accessToken))
            throw new AppException(messageUtils.tokenExpired(), StatusCode.ERROR, HttpStatus.BAD_REQUEST);

        String field = validator.validateForm(request);
        if (!field.isEmpty())
            throw new AppException(messageUtils.dataNotValid(field), StatusCode.ERROR, HttpStatus.BAD_REQUEST);

        CompanyType companyType = companyTypeService.getOneByName(request.getName());
        if (null != companyType)
            throw new AppException(messageUtils.dataExist(), StatusCode.ERROR, HttpStatus.BAD_REQUEST);

        companyType = companyTypeService.add(request);
        if (null == companyType)
            throw new AppException(messageUtils.insertFailed(), StatusCode.ERROR, HttpStatus.BAD_REQUEST);

        return abstractResponseHandler(companyType).getResult(messageUtils.insertSuccess());
    }

    @PutMapping("/{secure-id}")
    public ResponseEntity<ResultResponse> updateCompanyRole(@RequestHeader("Authorization") String accessToken,
                                                            @PathVariable("secure-id") String secureId,
                                                            @RequestBody AddCompanyTypeRequest request) {
        if (!checker.check(accessToken))
            throw new AppException(messageUtils.tokenExpired(), StatusCode.ERROR, HttpStatus.BAD_REQUEST);

        String field = validator.validateForm(request);
        if (!field.isEmpty())
            throw new AppException(messageUtils.dataNotValid(field), StatusCode.ERROR, HttpStatus.BAD_REQUEST);

        CompanyType companyType = companyTypeService.getOneByName(request.getName());
        if (null != companyType)
            throw new AppException(messageUtils.dataExist(), StatusCode.ERROR, HttpStatus.BAD_REQUEST);

        companyType = companyTypeService.update(secureId, request);
        if (null == companyType)
            throw new AppException(messageUtils.updateFailed(), StatusCode.ERROR, HttpStatus.BAD_REQUEST);

        return abstractResponseHandler(companyType).getResult(messageUtils.insertSuccess());
    }
}
