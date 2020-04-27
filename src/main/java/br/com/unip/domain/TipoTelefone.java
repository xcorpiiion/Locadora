package br.com.unip.domain;

/**
 * The Enum TipoTelefone.
 */
public enum TipoTelefone {

	/** The fixo. */
	FIXO("Fixo"),

	/** The movel. */
	MOVEL("Movel");
	
	private String tipoTelefone;

	private TipoTelefone(String tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}

	public String getTipoTelefone() {
		return tipoTelefone;
	}
	
}
