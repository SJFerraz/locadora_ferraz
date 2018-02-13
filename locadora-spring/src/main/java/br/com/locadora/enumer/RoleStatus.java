package br.com.locadora.enumer;

public enum RoleStatus implements BaseEnum{
	NENHUMA((byte)0,"NENHUMA"), CLIENTE((byte)1,"CLIENTE"), FUNCIONARIO((byte)2,"FUNCIONARIO"), ADMIN((byte)3,"ADMIN");
	
	private final byte value;
	
	private final String string;
	
	
	private RoleStatus(byte value, String string) {
		this.value = value;
		this.string = string;
	}
	
	public Byte getValue() {
		return value;
	}
	
	public String getString() {
		return string;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.valueOf(value);
	}
	
	
}
