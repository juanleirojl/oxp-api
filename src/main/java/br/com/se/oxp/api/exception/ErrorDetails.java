package br.com.se.oxp.api.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDetails {
  private LocalDateTime dataHora;
  private String mensagemUsuario;
  private String mensagemDesenvolvedor;
  private String detalhe;
}