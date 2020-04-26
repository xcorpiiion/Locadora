package br.com.unip.domain;

public enum Modelo {
	UM_PONTO_ZERO("1.0"),
	UM_PONTO_UM("1.1"),
	UM_PONTO_DOIS("1.2"),
	UM_PONTO_TRES("1.3"),
	UM_PONTO_QUATRO("1.4"),
	UM_PONTO_CINCO("1.5"),
	UM_PONTO_SEIS("1.6"),
	DOIS_PONTO_ZEO("2.0"),
	DOIS_PONTO_UM("2.1");
	
	private String modelo;

	private Modelo(String modelo) {
		this.modelo = modelo;
	}

	public String getModelo() {
		return modelo;
	}
	
	
}
