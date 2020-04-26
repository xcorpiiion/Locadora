package br.com.unip.domain;

public enum Marca {
	Chevrolet("Chevrolet"),
	Volkswagen("Volkswagen"),
	Fiat("Fiat"),
	Renault("Renault"),
	Ford("Ford"),
	Toyota("Toyota"),
	Hyundai("Hyundai"),
	Jeep("Jeep"),
	Honda("Honda"),
	Nissan("Nissan"),
	Citroën("Citroën"),
	Mitsubishi("Mitsubishi"),
	Peugeot("Peugeot"),
	Chery("Chery"),
	BMW("BMW"),
	Mercedes_Benz("Mercedes_Benz"),
	Kia("Kia"),
	Audi("Audi"),
	Volvo("Volvo"),
	Land_Rover("Land_Rover");

	private String marca;
	
	private Marca(String marca) {
		this.marca = marca;
	}

	public String getMarca() {
		return marca;
	}

}
