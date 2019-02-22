package id.nuansa.pos.api.controller.admin;

import id.nuansa.pos.api.model.response.InitialResponse;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface AdminService {

    Boolean isEmptyTables();

    InitialResponse initData() throws InvalidKeyException, NoSuchAlgorithmException;
}
