package br.com.se.oxp.api.auditoria;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.se.oxp.api.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable implements Serializable{
	
	private static final long serialVersionUID = 1L;

//	@CreatedBy
//	@ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "idt_usr_cri",updatable=false)
//    protected Usuario usuario;

//    @CreatedDate
//    @Temporal(TIMESTAMP)
//    @Column(name="dth_ult_cri",updatable=false)
//    protected Date dataCriacao;

    
    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idt_usr_alt")
    @JsonBackReference
    protected Usuario ultimoUsuario;

    @LastModifiedDate
   // @Temporal(TIMESTAMP)
    @Column(name="dth_ult_alt")
    protected LocalDateTime dataAlteracao;
    


}
