package com.fatec.scell.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.fatec.scell.model.Livro;
import com.fatec.scell.model.LivroRepository;

@RestController
@RequestMapping(path = "/livros")
public class LivroController {
//insert into livro values ('1', 'Pressman','aaaa', 'engenharia')
	@Autowired
	private LivroRepository repository;

	/**
	 * quando o usuario digita localhost:8080/api/add
	 *
	 * @param livro
	 * @return o html /CadastraLivro
	 */

	@GetMapping("/consulta")
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("consultarLivro");
		modelAndView.addObject("livros", repository.findAll());
		return modelAndView;
	}

	@GetMapping("/cadastrar")
	public ModelAndView cadastraLivro(Livro livro) {
		ModelAndView mv = new ModelAndView("cadastrarLivro");
		mv.addObject("livro", livro);
		return mv;
	}

	@GetMapping("/edit/{isbn}") // diz ao metodo que ira responder a uma requisicao do tipo get
	public ModelAndView mostraFormAdd(@PathVariable("isbn") String isbn) {
		ModelAndView modelAndView = new ModelAndView("atualizaLivro");
		modelAndView.addObject("livro", repository.findByIsbn(isbn)); // o repositorio e injetado no controller
		return modelAndView; // addObject adiciona objetos para view
	}

	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		repository.deleteById(id);
		ModelAndView modelAndView = new ModelAndView("consultarLivro");
		modelAndView.addObject("livros", repository.findAll());
		return modelAndView;
	}

	@PostMapping("/save")
	public ModelAndView save(@Valid Livro livro, BindingResult result) {
		ModelAndView mv = new ModelAndView("CadastrarLivro");
		if (result.hasErrors()) {
			mv.addObject("fail", "Dados inválidos"); // quando fail nao eh nulo a msg aparece na tela
			return mv;
		}
		try {
			Livro jaExiste = null;
			jaExiste = repository.findByIsbn(livro.getIsbn());
			if (jaExiste == null) {
				repository.save(livro);
				mv.addObject("success", "Livro cadastrado com sucesso"); // success nao eh nulo
				return mv;
			} else {
				mv.addObject("fail", "Livro já cadastrado."); // fail nao eh nulo a msg aparece na tela
				return mv;
			}
		} catch (Exception e) {
			mv.addObject("fail", "erro ===> " + e.getMessage());
			return mv;
		}
	}

	@PostMapping("/update/{id}")
	public ModelAndView atualiza(@PathVariable("id") Long id, @Valid Livro livro, BindingResult result) {

		if (result.hasErrors()) {
			livro.setId(id);
			return new ModelAndView("atualizaLivro");
		}
		Livro umLivro = repository.findById(id).get();
		umLivro.setAutor(livro.getAutor());
		umLivro.setIsbn(livro.getIsbn());
		umLivro.setTitulo(livro.getTitulo());
		repository.save(umLivro);
		ModelAndView modelAndView = new ModelAndView("consultarLivro");
		modelAndView.addObject("livros", repository.findAll());
		return modelAndView;
	}
}