package com.fatec.scell.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.NaturalId;

@Entity(name = "Aluno")
public class Aluno 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NaturalId
	@Column(nullable = false, length = 13)
	@NotEmpty(message = "O ra deve ser preenchido") // o atributo nao pode ser nulo e o tamanho > zero
	@Size(min = 13, max = 13, message = "RA deve conter 13 caracteres")
	private String ra;

	@Column(nullable = false)
	@NotEmpty(message = "O nome deve ser preenchido")
	private String nome;
	
	@Column(nullable = false)
	@NotNull(message = "email invalido")	
	private String email;

	public Aluno() 
	{
		
	}

	public Aluno(String ra, String nome, String email) 
	{
		this.ra = ra;
		this.nome = nome;
		this.email = email;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}

	public void setRa(String ra) 
	{
		this.ra = ra;
	}
	
	public String getRa() 
	{
		return ra;
	}
	
	public void setNome(String nome) 
	{
		this.nome = nome;
	}

	public String getNome() 
	{
		return nome;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getEmail() 
	{
		return email;
	}
}