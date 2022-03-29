package projeto;

import javax.swing.JOptionPane;

public class projetoJava {
	
	int jogadorHP;
	String nomeJogador;
	String jogadorArma;
	String escolha;
	int monstroHP;
	int anelMagico;

	public static void main(String[] args) {
	
		projetoJava inicio;
		inicio = new projetoJava();
		
		inicio.playerSetUp();
		inicio.subsolo();
		}
	
	public void playerSetUp() {
		jogadorHP = 10;
		monstroHP = 15;
		jogadorArma = "Bastão";
		
		while (nomeJogador == null || nomeJogador.isEmpty()) {
			nomeJogador = JOptionPane.showInputDialog("Sua vida: "+ jogadorHP + "\nSua arma: " + jogadorArma + "\nPor favor, digite o seu nome: ");
		}
		JOptionPane.showMessageDialog(null, "Olá, "+nomeJogador+". Vamos começar sua jornada...");
	}
	
	public void subsolo() {
		escolha = JOptionPane.showInputDialog("Você encontra-se agora na entrada de um lugar chamado SUBSOLO."
				+ "\nHu.. É.. é um guarda ali?"
				+ "\nO que você deseja fazer?"
				+ "\n1: Conversar com o guarda"
				+ "\n2: Atacar o guarda"
				+ "\n3: Continuar...");
		int intEscolha = Integer.parseInt(escolha);
		
		if (intEscolha == 1) {
			if (anelMagico == 1) {
				fimJogo();
			}else {
				JOptionPane.showMessageDialog(null, "Guarda: Olá visitante.. Então seu nome é "+nomeJogador+"?"
						+ "\nDesculpe, mas não sou autorizado a conversar com estranho."
						+ "\nMas, caso tenha algum item especial, me procure de novo");
				subsolo();
			}
		}else if (intEscolha == 2) {
			jogadorHP = jogadorHP - 2;
			if (jogadorHP < 1) {
				jogadorMorto();
			}else {
				JOptionPane.showMessageDialog(null, "Guarda: Hey, não seja estúpido..."
						+ "\nO guarda se defendeu e te atacou."
						+ "\n(Você recebeu um dano de 2)"
						+ "\nSua vida: "+jogadorHP);
				subsolo();
			}
		}else if (intEscolha == 3) {
			seguirCaminho();
		}else {
			subsolo();
		}
	}
	
	public void seguirCaminho() {
		escolha = JOptionPane.showInputDialog("Você está na estrada, sem rumo... Logo a frente tem uma placa: "
				+ "\n1: Seguir para o NORTE"
				+ "\n2: Seguir para o LESTE"
				+ "\n3: Seguir para o OESTE"
				+ "\n4: Seguir para o SUL"
				+ "\nSe você voltar para o SUL, irá para o Subsolo novamente.");
		int intEscolha = Integer.parseInt(escolha);
		if (intEscolha == 1) {
			norte();
		}else if (intEscolha == 2) {
			leste();
		}else if (intEscolha == 3) {
			oeste();
		}else if (intEscolha == 4) {
			subsolo();
		}else {
			seguirCaminho();
		}
	}
	
	public void norte() {
		jogadorHP = jogadorHP + 1;
		JOptionPane.showMessageDialog(null, "Ah, é aqui que fica a árvore da vida! Vou comer um de seus frutos"
				+ "\n(Sua vida foi recuperada em +1)"
				+ "\nSua vida: "+jogadorHP);
		seguirCaminho();
	}
	public void leste() {
		if (jogadorArma != "Bastão") {
			JOptionPane.showMessageDialog(null, "Hum... Parece que você já visitou esse lugar antes..."
					+ "\nMelhor voltar...");
			seguirCaminho();
		}else {
			JOptionPane.showMessageDialog(null, "Você andou pela floresta e encontrou a Espada Afiada!"
					+ "\nSua arma agora é: Espada Afiada");
			jogadorArma = "Espada Afiada";
			seguirCaminho();
		}
	}
	public void oeste() {
		escolha = JOptionPane.showInputDialog("Você encontrou o Urso Gigante!"
				+ "\n1:Lutar"
				+ "\n2:Correr");
		int intEscolha = Integer.parseInt(escolha);
		if (intEscolha == 1) {
			lutar();
		}else if (intEscolha == 2) {
			seguirCaminho();
		}else {
			oeste();
		}
	}
	
	public void lutar() {
		escolha = JOptionPane.showInputDialog("Sua arma: "+jogadorArma+""
				+ "\nSua vida: "+jogadorHP+""
				+ "\nVida do Urso: "+monstroHP+""
				+ "\n1:Atacar"
				+ "\n2:Correr");
		int intEscolha = Integer.parseInt(escolha);
		if (intEscolha == 1) {
			atacar();
		}else if (intEscolha == 2) {
			seguirCaminho();
		}else {
			lutar();
		}
	}
	
	public void atacar() {
		int jogadorDano = 0;
		if (jogadorArma.equals("Bastão")) {
			jogadorDano = new java.util.Random().nextInt(4);
		}else if (jogadorArma.equals("Espada Afiada")) {
			jogadorDano = new java.util.Random().nextInt(6);
		}
		monstroHP = monstroHP - jogadorDano;
		JOptionPane.showMessageDialog(null, "Você atacou o Urso Gigante e causou "+jogadorDano+" de dano!"
				+ "\nVida do Urso: "+monstroHP);
		
		if (monstroHP < 1) {
			venceu();
		}else if (monstroHP > 0) {
			int monstroDano = 0;
			monstroDano = new java.util.Random().nextInt(6);
			jogadorHP = jogadorHP - monstroDano;
			JOptionPane.showMessageDialog(null, "O Urso Gigante te atacou e causou "+monstroDano+" de dano!"
					+ "\nSua vida: "+jogadorHP);
			if (jogadorHP < 1) {
				jogadorMorto();
			}else if (jogadorHP > 0) {
				lutar();
			}
		}
	}
	
	public void venceu() {
		anelMagico = 1;
		JOptionPane.showMessageDialog(null, "Alguém finalmente conseguiu matar o Urso Gigante!"
				+ "\nParece que ele deixou cair um Anel Mágico!"
				+ "\n(Você obteve o Anel Mágico), retorne para a estrada!");
		seguirCaminho();
	}
	
	public void jogadorMorto() {
		JOptionPane.showConfirmDialog(null, "Você está morto!"
				+ "\nFIM DE JOGO!");
	}
	public void fimJogo() {
		JOptionPane.showMessageDialog(null, "Guarda: O que?! Foi você quem matou o Urso Gigante??"
				+ "\nGuarda: Talvez eu estivesse enganado.. Isso prova que você é uma boa pessoa."
				+ "\nSeja bem-vindo à nossa cidade!");
		terminou();
	}
	public void terminou() {
		JOptionPane.showMessageDialog(null, "Você conseguiu."
				+ "\nFIM DE JOGO!");
	}
}
