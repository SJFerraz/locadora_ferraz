package br.com.locadora.enumer;

public enum LocacaoStatus implements BaseEnum{
	NULO((byte)0), ABERTO((byte)1), FECHADO((byte)2), CANCELADO((byte)3);
	
	private final byte value;
	
	private LocacaoStatus(byte value) {
		this.value = value;
	}
	
	public Byte getValue() {
		return value;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.valueOf(value);
	}
	
	
}
