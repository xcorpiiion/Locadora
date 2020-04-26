package br.com.unip.domain;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "Locadora")
public class Locadora {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank
	@Size(min = 2, max = 30)
	private String nome;

	@NotNull
	@OneToMany(cascade = CascadeType.PERSIST)
	private Set<Telefone> telefones;

	@NotNull
	@OneToOne(cascade = CascadeType.PERSIST)
	private Endereco endereco;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "locadora")
	private List<Automovel> automoveis;

	public Locadora() {
	}

	public Locadora(@NotBlank @Size(min = 2, max = 30) String nome, @NotNull Set<Telefone> telefones,
			@NotNull Endereco endereco) {
		this.nome = nome;
		this.telefones = telefones;
		this.endereco = endereco;
	}

	public Locadora(@NotBlank @Size(min = 2, max = 30) String nome, @NotNull Set<Telefone> telefones,
			@NotNull Endereco endereco, @NotNull List<Automovel> automoveis) {
		this.nome = nome;
		this.telefones = telefones;
		this.endereco = endereco;
		this.automoveis = automoveis;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<Telefone> telefones) {
		this.telefones = telefones;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Automovel> getAutomoveis() {
		return automoveis;
	}

	public void setAutomoveis(List<Automovel> automoveis) {
		this.automoveis = automoveis;
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
		Locadora other = (Locadora) obj;
		return new EqualsBuilder().append(id, other.id).isEquals();
	}

	@Override
	public String toString() {
		return reflectionToString(this, JSON_STYLE);
	}

}
