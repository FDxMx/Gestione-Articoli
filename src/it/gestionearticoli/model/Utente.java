package it.gestionearticoli.model;

public class Utente {
	
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private String ruolo;
	
	public Utente() {}

	public Utente(String nome, String cognome, String username, String password, String ruolo) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		this.ruolo = ruolo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	@Override
	public String toString() {
		return "Utente [nome=" + nome + ", cognome=" + cognome + ", username=" + username + ", password=" + password
				+ ", ruolo=" + ruolo + "]";
	}
	
	
}
