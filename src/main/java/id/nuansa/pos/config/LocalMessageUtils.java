package id.nuansa.pos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class LocalMessageUtils {

    @Autowired
    private MessageSource messageSource;

    public String insertSuccess() {
        return messageSource.getMessage("insert.success", null, LocaleContextHolder.getLocale());
    }

    public String insertFailed() {
        return messageSource.getMessage("insert.failed", null, LocaleContextHolder.getLocale());
    }

    public String updateSuccess() {
        return messageSource.getMessage("update.success", null, LocaleContextHolder.getLocale());
    }

    public String updateFailed() {
        return messageSource.getMessage("update.failed", null, LocaleContextHolder.getLocale());
    }

    public String dataNotFound() {
        return messageSource.getMessage("data.notFound", null, LocaleContextHolder.getLocale());
    }

    public String dataFetched() {
        return messageSource.getMessage("data.fetched", null, LocaleContextHolder.getLocale());
    }

    public String dataExist() {
        return messageSource.getMessage("data.exist", null, LocaleContextHolder.getLocale());
    }

    public String dataMoreThanOne() {
        return messageSource.getMessage("data.moreThanOne", null, LocaleContextHolder.getLocale());
    }

    public String dataNotValid(String name) {
        String message = messageSource.getMessage("data.invalid", null, LocaleContextHolder.getLocale());
        return String.format(message, name);
    }

    public String missingHeader() {
        return messageSource.getMessage("data.missingHeader", null, LocaleContextHolder.getLocale());
    }

    public String passwordNotMatch() {
        return messageSource.getMessage("auth.passwordNotFound", null, LocaleContextHolder.getLocale());
    }

    public String tokenExpired() {
        return messageSource.getMessage("auth.tokenExpired", null, LocaleContextHolder.getLocale());
    }

    public String invalidSignature() {
        return messageSource.getMessage("auth.invalidSignature", null, LocaleContextHolder.getLocale());
    }

    public String loginSuccess() {
        return messageSource.getMessage("auth.loginSuccess", null, LocaleContextHolder.getLocale());
    }

    public String failedCreateSignature() {
        return messageSource.getMessage("error.failed.signature", null, LocaleContextHolder.getLocale());
    }
}