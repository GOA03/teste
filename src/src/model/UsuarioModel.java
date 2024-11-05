package model;

public class UsuarioModel {
    private int id;
    private int ra;
    private String nome;
    private String senha;
    private String token;
    private boolean avisosEsportes;
    private boolean avisosClima;
    private boolean avisosTecnologia;
    private boolean avisosSaude;
    private boolean avisosEconomia;

    // Construtor
    public void Usuario(int id, int ra, String nome, String senha, String token, 
                   boolean avisosEsportes, boolean avisosClima, boolean avisosTecnologia, 
                   boolean avisosSaude, boolean avisosEconomia) {
        this.id = id;
        this.ra = ra;
        this.nome = nome;
        this.senha = senha;
        this.token = token;
        this.avisosEsportes = avisosEsportes;
        this.avisosClima = avisosClima;
        this.avisosTecnologia = avisosTecnologia;
        this.avisosSaude = avisosSaude;
        this.avisosEconomia = avisosEconomia;
    }

    // Getters e Setters
    public int getId() { return id; }
    public int getRa() { return ra; }
    public String getNome() { return nome; }
    public String getSenha() { return senha; }
    public String getToken() { return token; }
    public boolean isAvisosEsportes() { return avisosEsportes; }
    public boolean isAvisosClima() { return avisosClima; }
    public boolean isAvisosTecnologia() { return avisosTecnologia; }
    public boolean isAvisosSaude() { return avisosSaude; }
    public boolean isAvisosEconomia() { return avisosEconomia; }

	public void setToken(String token) {
		this.token = token;
	}
}
