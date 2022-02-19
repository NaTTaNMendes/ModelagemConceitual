package com.NaTTaNMendes.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.NaTTaNMendes.cursomc.domain.Categoria;
import com.NaTTaNMendes.cursomc.domain.Cidade;
import com.NaTTaNMendes.cursomc.domain.Cliente;
import com.NaTTaNMendes.cursomc.domain.Endereco;
import com.NaTTaNMendes.cursomc.domain.Estado;
import com.NaTTaNMendes.cursomc.domain.Produto;
import com.NaTTaNMendes.cursomc.domain.enums.TipoCliente;
import com.NaTTaNMendes.cursomc.repositories.CategoriaRepository;
import com.NaTTaNMendes.cursomc.repositories.CidadeRepository;
import com.NaTTaNMendes.cursomc.repositories.ClienteRepository;
import com.NaTTaNMendes.cursomc.repositories.EnderecoRepository;
import com.NaTTaNMendes.cursomc.repositories.EstadoRepository;
import com.NaTTaNMendes.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	CidadeRepository cidadeRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.0);
		Produto p2 = new Produto(null, "Impressora", 800.0);
		Produto p3 = new Produto(null, "Mouse", 80.0);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
				
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "151650561", TipoCliente.PESSOA_FISICA);
		cli1.getTelefones().addAll(Arrays.asList("5641154646", "1561561658"));
		
		Endereco e1 = new Endereco(null, "Rua Armando", "511", "Casa", "Nova", "89216521", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua Pedro", "865", "Apartamento", "Estrada Armada", "87496125", cli1, c2);
	
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
	}

}
