package br.com.fiap.am.coopfit.domain.enums;

public enum TipoSensor {
	
	BATIMENTO_CARDIACO(1, "Batimento Cardiaco"),
	PASSOS(2, "Passos"),
	SONO(3, "Sono");
	
	private Integer codigo;
	private String descricao;
	
	private TipoSensor(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
//	public static TipoSensor toEnum(Integer codigo) {
//		if (codigo == null) {
//			return null;
//		}
//		
//		for(TipoSensor td : TipoSensor.values()) {
//			if (codigo.equals(td.getCodigo())) {
//				return td;
//			}
//		}
//		
//		throw new IllegalArgumentException("Id inválido: " + codigo);
//	}
	
	public static TipoSensor toEnum(String desc) {
		if (desc == null) {
			return null;
		}
		
		for(TipoSensor td : TipoSensor.values()) {
			if (desc.equals(td.getDescricao())) {
				return td;
			}
		}
		
		throw new IllegalArgumentException("Descrição inválido: " + desc);
	}

}
