package br.com.unip.domain;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	@Column(nullable = false, scale = 30)
	private String nome;
	
	@Email
	@Column(nullable = false, scale = 60)
	private String email;
	
	@Size(min = 8, max = 30)
	@Column(nullable = false, scale = 30)
	private String senha;
	
	private BigDecimal dinheiro;
	
	@OneToOne
	@JoinColumn(name = "automovel_id")
	private Automovel automovelAlugado;
	
	public Cliente() {
		
	}
	
	public Cliente(@NotBlank String nome, @Email String email, @Size(min = 8, max = 30) String senha,
			BigDecimal dinheiro) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.dinheiro = dinheiro;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Automovel getAutomovelAlugado() {
		return automovelAlugado;
	}

	public void setAutomovelAlugado(Automovel automovelAlugado) {
		this.automovelAlugado = automovelAlugado;
	}

	public BigDecimal getDinheiro() {
		return dinheiro;
	}

	public void setDinheiro(BigDecimal dinheiro) {
		this.dinheiro = dinheiro;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(id).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return new EqualsBuilder().append(id, other.id).isEquals();
	}

	@Override
	public String toString() {
		return reflectionToString(this, JSON_STYLE);
	}
	
	
}
