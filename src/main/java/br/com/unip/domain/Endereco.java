package br.com.unip.domain;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import br.com.contmatic.constantes.Mensagem;

/**
 * The Class Endereco.
 */
@Entity
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
    /** The rua. */
    @NotBlank(message = Mensagem.VALOR_ESTA_VAZIO)
    @Size(min = 3, max = 50, message = Mensagem.VALOR_NAO_E_VALIDO)
    private String rua;

    /** The bairro. */
    @NotBlank(message = Mensagem.VALOR_ESTA_VAZIO)
    //@Pattern(regexp = Constante.ILLEGAL_WORD, message = Mensagem.VALOR_NAO_E_VALIDO)
    private String bairro;

    /** The cep. */
    @NotBlank(message = Mensagem.VALOR_ESTA_VAZIO)
    //@Pattern(regexp = Constante.ILLEGAL_WORD, message = Mensagem.VALOR_NAO_E_VALIDO)
    private String cep;

    /** The numero residencia. */
    @Min(value = 1, message = Mensagem.PRECISA_SER_UM_VALOR_MAIOR)
    private int numeroResidencia;

    /** The cidade. */
    @NotBlank(message = Mensagem.VALOR_ESTA_VAZIO)
    //@Pattern(regexp = Constante.ILLEGAL_NUMBER, message = Mensagem.VALOR_NAO_E_VALIDO)
    private String cidade;

    /** The estado. */
    @NotNull(message = Mensagem.VALOR_ESTA_NULLO)
    @Enumerated(EnumType.STRING)
    private EstadosBrasil estado;

    public Endereco() {
	}

	public Endereco(
			@NotBlank(message = "O valor esta vazio") @Size(min = 3, max = 50, message = "O valor não é valido") String rua,
			@NotBlank(message = "O valor esta vazio") @Size(min = 3, max = 50, message = "O valor não é valido") @Pattern(regexp = "^[a-zA-ZÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü]*$", message = "O valor não é valido") String bairro,
			@NotBlank(message = "O valor esta vazio") @Pattern(regexp = "^[a-zA-ZÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü]*$", message = "O valor não é valido") String cep,
			@Min(value = 1, message = "O precisa ser um valor maior") int numeroResidencia,
			@NotBlank(message = "O valor esta vazio") @Min(value = 3, message = "O precisa ser um valor maior") @Pattern(regexp = "^[0-9]*$", message = "O valor não é valido") String cidade,
			@NotNull(message = "O valor esta nullo") EstadosBrasil estado) {
		this.rua = rua;
		this.bairro = bairro;
		this.cep = cep;
		this.numeroResidencia = numeroResidencia;
		this.cidade = cidade;
		this.estado = estado;
	}

	/**
     * Gets the rua.
     *
     * @return the rua
     */
    public String getRua() {
        return rua;
    }

    /**
     * Sets the rua.
     *
     * @param rua the new rua
     */
    public void setRua(String rua) {
        this.rua = rua;
    }

    /**
     * Gets the bairro.
     *
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * Sets the bairro.
     *
     * @param bairro the new bairro
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * Gets the cep.
     *
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * Sets the cep.
     *
     * @param cep the new cep
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * Gets the cidade.
     *
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * Sets the cidade.
     *
     * @param cidade the new cidade
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * Gets the estado.
     *
     * @return the estado
     */
    public EstadosBrasil getEstado() {
        return estado;
    }

    /**
     * Sets the estado.
     *
     * @param estado the new estado
     */
    public void setEstado(EstadosBrasil estado) {
        this.estado = estado;
    }

    /**
     * Gets the numero residencia.
     *
     * @return the numero residencia
     */
    public int getNumeroResidencia() {
        return numeroResidencia;
    }

    /**
     * Sets the numero residencia.
     *
     * @param numeroResidencia the new numero residencia
     */
    public void setNumeroResidencia(int numeroResidencia) {
        this.numeroResidencia = numeroResidencia;
    }

    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(cep).append(numeroResidencia).toHashCode();
    }

    /**
     * Equals.
     *
     * @param obj the obj
     * @return true, if successful
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Endereco other = (Endereco) obj;
        return new EqualsBuilder().append(cep, other.cep).append(numeroResidencia, other.numeroResidencia).isEquals();
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return reflectionToString(this, JSON_STYLE);
    }

}
