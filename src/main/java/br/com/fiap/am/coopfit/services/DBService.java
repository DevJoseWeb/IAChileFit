package br.com.fiap.am.coopfit.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.fiap.am.coopfit.domain.Dispositivo;
import br.com.fiap.am.coopfit.domain.DispositivoSensor;
import br.com.fiap.am.coopfit.domain.InformacaoSaude;
import br.com.fiap.am.coopfit.domain.InformeTratativo;
import br.com.fiap.am.coopfit.domain.Notificacao;
import br.com.fiap.am.coopfit.domain.Pessoa;
import br.com.fiap.am.coopfit.domain.Questionario;
import br.com.fiap.am.coopfit.domain.Rotina;
import br.com.fiap.am.coopfit.domain.enums.Genero;
import br.com.fiap.am.coopfit.domain.enums.TipoRotina;
import br.com.fiap.am.coopfit.domain.enums.TipoSensor;
import br.com.fiap.am.coopfit.domain.enums.TipoSentimento;
import br.com.fiap.am.coopfit.domain.enums.TipoUsuario;
import br.com.fiap.am.coopfit.repositories.DispositivoRepository;
import br.com.fiap.am.coopfit.repositories.DispositivoSensorRepository;
import br.com.fiap.am.coopfit.repositories.InformacaoSaudeRepository;
import br.com.fiap.am.coopfit.repositories.InformeTratativoRepository;
import br.com.fiap.am.coopfit.repositories.NotificacaoRepository;
import br.com.fiap.am.coopfit.repositories.PessoaRepository;
import br.com.fiap.am.coopfit.repositories.QuestionarioRepository;
import br.com.fiap.am.coopfit.repositories.RotinaRepository;

@Service
public class DBService {

	@Autowired
	public PessoaRepository pessoaRepo;
	@Autowired
	public InformeTratativoRepository informTratativoRepo;
	@Autowired
	public NotificacaoRepository notificacaoRepo;
	@Autowired
	public RotinaRepository rotinaRepo;
	@Autowired
	public InformacaoSaudeRepository informacaoSaudeRepo;
	@Autowired
	public QuestionarioRepository questionarioRepo;
	@Autowired
	public DispositivoRepository dispositivoRepo;
	@Autowired
	public DispositivoSensorRepository dispositivoSensorRepo;
	@Autowired
	private BCryptPasswordEncoder pe;

	public void instantiateTestDatabase() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pessoa p1 = new Pessoa("Caio", sdf.parse("01/09/1993 00:00"), Genero.MASCULINO, "caiohcris@gmail.com",
				pe.encode("123456"), sdf.parse("11/08/2018 23:46"), null, false, 1.73, 78.5,
				"Não tenho Observações");
		p1.addPerfil(TipoUsuario.ADMINISTRADOR);
		
		

		InformeTratativo info1 = new InformeTratativo("Informativo", sdf.parse("11/08/2018 23:46"), p1);
		Notificacao not1 = new Notificacao("Notificacao", sdf.parse("11/08/2018 23:46"), p1);
		Rotina rot1 = new Rotina("Rotina", sdf.parse("11/08/2018 23:46"), TipoRotina.ATIVO, p1);
		InformacaoSaude infoS1 = new InformacaoSaude("Informarcao Saude", sdf.parse("11/08/2018 23:46"), p1);
		Questionario que1 = new Questionario(2, TipoSentimento.ALEGRIA, 3, sdf.parse("11/08/2018 23:46"), p1);
		Dispositivo dis1 = new Dispositivo("Miband2", p1);
		DispositivoSensor disSe1 = new DispositivoSensor(0.0, TipoSensor.SONO, sdf.parse("11/08/2018 23:46"), dis1, p1);

		p1.getInformativos().addAll(Arrays.asList(info1));
		p1.getNotificacoes().addAll(Arrays.asList(not1));
		p1.getRotinas().addAll(Arrays.asList(rot1));
		p1.getInformacoesSaude().addAll(Arrays.asList(infoS1));
		p1.getQuestionarios().addAll(Arrays.asList(que1));
		p1.getDispositivos().addAll(Arrays.asList(dis1));
		p1.getDispositivoSensores().addAll(Arrays.asList(disSe1));
		dis1.getDispositivoSensores().addAll(Arrays.asList(disSe1));

		pessoaRepo.saveAll(Arrays.asList(p1));
		informTratativoRepo.saveAll(Arrays.asList(info1));
		notificacaoRepo.saveAll(Arrays.asList(not1));
		rotinaRepo.saveAll(Arrays.asList(rot1));
		informacaoSaudeRepo.saveAll(Arrays.asList(infoS1));
		questionarioRepo.saveAll(Arrays.asList(que1));
		dispositivoRepo.saveAll(Arrays.asList(dis1));
		dispositivoSensorRepo.saveAll(Arrays.asList(disSe1));
	}

}
