package br.com.fiap.am.coopfit.domain.enums;

public enum TipoUsuario {
	
	PACIENTE(1, "ROLE_FREE"),
	DOUTOR(2, "ROLE_PREMIUM"),
	ADMINISTRADOR(3, "ROLE_ADMINISTRADOR");
	
	private Integer codigo;
	private String descricao;
	
	private TipoUsuario(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoUsuario toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}
		
		for(TipoUsuario tu : TipoUsuario.values()) {
			if (codigo.equals(tu.getCodigo())) {
				return tu;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + codigo);
	}
	
//	public static TipoUsuario toEnum(String desc) {
//		if (desc == null) {
//			return null;
//		}
//		
//		for(TipoUsuario tu : TipoUsuario.values()) {
//			if (desc.equals(tu.getDescricao())) {
//				return tu;
//			}
//		}
//		
//		throw new IllegalArgumentException("Descrição inválido: " + desc);
//	}

}
