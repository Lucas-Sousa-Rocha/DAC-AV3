package entidades;


import javax.persistence.*;
import java.util.List;

@Entity
public class Campeonato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true) // Garante que o login seja único no banco de dados
    private String nome;

    @OneToMany(mappedBy = "campeonato", cascade = CascadeType.ALL)
    private List<Jogo> jogos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Jogo> getJogos() {
		return jogos;
	}

	public void setJogos(List<Jogo> jogos) {
		this.jogos = jogos;
	}

    // Getters e Setters
    
}

