package br.com.se.oxp.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioInexistenteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsuarioInexistenteException(String exception) {
		super(exception);
	}

}