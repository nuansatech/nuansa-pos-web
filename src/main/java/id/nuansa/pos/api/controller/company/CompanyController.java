package id.nuansa.pos.api.controller.company;

import id.nuansa.pos.api.controller.companytype.CompanyTypeService;
import id.nuansa.pos.api.model.request.AddCompanyRequest;
import id.nuansa.pos.api.model.response.AddCompanyResponse;
import id.nuansa.pos.api.model.response.BasicData;
import id.nuansa.pos.config.LocalMessageUtils;
import id.nuansa.pos.entity.Company;
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
@RequestMapping(value = "/company", produces = MediaType.APPLICATION_JSON_VALUE)
public class CompanyController extends BaseController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyTypeService companyTypeService;

    @Autowired
    private CompanyValidator validator;

    @Autowired
    private LocalMessageUtils messageUtils;

    @Autowired
    private TokenChecker checker;

    @PostMapping
    public ResponseEntity<ResultResponse> insert(@RequestHeader("Authorization") String accessToken,
                                                 @RequestBody AddCompanyRequest request) {
        if (!checker.check(accessToken))
            throw new AppException(messageUtils.tokenExpired(), StatusCode.ERROR, HttpStatus.BAD_REQUEST);

        String field = validator.validateForm(request);
        if (!field.isEmpty())
            throw new AppException(messageUtils.dataNotValid(field), StatusCode.ERROR, HttpStatus.BAD_REQUEST);

        Company company = companyService.checkExistanceCompany(request.getName(), request.getEmail(), request.getTypeId());
        if (null != company)
            throw new AppException(messageUtils.dataExist(), StatusCode.ERROR, HttpStatus.BAD_REQUEST);

        CompanyType companyType = companyTypeService.getOne(request.getTypeId());
        if (null == companyType)
            throw new AppException(messageUtils.dataNotFound() + ": company type", StatusCode.NOT_FOUND, HttpStatus.NOT_FOUND);

        company = companyService.insert(request);
        if (null == company)
            throw new AppException(messageUtils.insertFailed(), StatusCode.ERROR, HttpStatus.BAD_REQUEST);

        AddCompanyResponse response = new AddCompanyResponse();
        response.setSecureId(company.getSecureId());
        response.setName(company.getName());
        response.setEmail(company.getEmail());
        response.setAddress(company.getAddress());
        response.setPhone(company.getPhone());
        response.setType(new BasicData(companyType.getSecureId(), companyType.getName()));

        return abstractResponseHandler(response).getResult(messageUtils.insertSuccess());
    }
}

