package bean;

public class ResumoTime {
    private String time;
    private int pontos;
    private int vitorias;
    private int derrotas;
    private int empates;
    private int golsMarcados;
    private int golsSofridos;

    public ResumoTime(String time) {
        this.time = time;
    }

    public void adicionarVitoria() {
        vitorias++;
        pontos += 3;
    }

    public void adicionarEmpate() {
        empates++;
        pontos += 1;
    }

    public void adicionarDerrota() {
        derrotas++;
    }

    public void adicionarGols(int marcados, int sofridos) {
        golsMarcados += marcados;
        golsSofridos += sofridos;
    }

    public int getSaldoGols() {
        return golsMarcados - golsSofridos;
    }

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

	public int getVitorias() {
		return vitorias;
	}

	public void setVitorias(int vitorias) {
		this.vitorias = vitorias;
	}

	public int getDerrotas() {
		return derrotas;
	}

	public void setDerrotas(int derrotas) {
		this.derrotas = derrotas;
	}

	public int getEmpates() {
		return empates;
	}

	public void setEmpates(int empates) {
		this.empates = empates;
	}

	public int getGolsMarcados() {
		return golsMarcados;
	}

	public void setGolsMarcados(int golsMarcados) {
		this.golsMarcados = golsMarcados;
	}

	public int getGolsSofridos() {
		return golsSofridos;
	}

	public void setGolsSofridos(int golsSofridos) {
		this.golsSofridos = golsSofridos;
	}

    // Getters e setters...
    
    
}
