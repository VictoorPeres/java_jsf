package br.com.avancard.bean;

import br.com.avancard.jpautil.JPAUtil;
import br.com.avancard.model.dao.DaoGeneric;
import br.com.avancard.model.entity.Cidades;
import br.com.avancard.model.entity.Estados;
import br.com.avancard.model.entity.Pessoa;
import br.com.avancard.repository.IDaoPessoa;
import br.com.avancard.repository.IDaoPessoaImpl;
import com.google.gson.Gson;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@ManagedBean (name = "pessoaBean")
public class PessoaBean {

    private Pessoa pessoa = new Pessoa();
    DaoGeneric<Pessoa> dao = new DaoGeneric<Pessoa>();
    private List<Pessoa> pessoas = new ArrayList<Pessoa>();
    private List<Estados> estados = new ArrayList<Estados>();
    private List<Cidades> cidades = null;
   // private Estados estados = null;
    private Long codigoEstado;
    private Long codigoCidade;

    IDaoPessoa iDaoPessoa = new IDaoPessoaImpl();


    public String logout(){
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        externalContext.getSessionMap().remove("usuarioLogado");

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        request.getSession().invalidate();
        return "index.jsf";
    }

    public String login(){

        Pessoa pessoaUser = iDaoPessoa.consultarPessoa(pessoa.getLogin(), pessoa.getSenha());

        if(pessoaUser != null){/*Achou o usuário*/

            /**/
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();
            externalContext.getSessionMap().put("usuarioLogado", pessoaUser);

            return "primeirapagina.jsf";
        }
        return "index.jsf";
    }

    public boolean permiteAcesso(String acesso){
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        Pessoa pessoaUser = (Pessoa) externalContext.getSessionMap().get("usuarioLogado");
        return pessoaUser.getPerfilUsuario().equals(acesso);
    }

    public String salvar(){
        System.out.println(pessoa.getCidades());
        pessoa = dao.merge(pessoa);
        carregarPessoas();
        mostrarMsg("Cadastrado com sucesso!");
        return "";
    }

    private void mostrarMsg(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(msg);
        context.addMessage(null, message);
    }

    public String novo(){

        pessoa = new Pessoa();
        return "";
    }

    public String limpar(){

        pessoa = new Pessoa();
        return "";
    }

    public void excluir(){
        dao.delete(pessoa);
        pessoa = new Pessoa();
        carregarPessoas();
        mostrarMsg("Removido com sucesso!");

    }

    @PostConstruct
    public void carregarPessoas(){
        pessoas = dao.getEntityList(Pessoa.class);
    }

    /* Metodo para acessar uma api de CEP e retornar para a tela do sistema */
    public void pesquisaCep(AjaxBehaviorEvent event){
        try {
            URL url = new URL( "https://viacep.com.br/ws/"+pessoa.getCep()+"/json/");
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            String cep = "";
            StringBuilder json = new StringBuilder();

            while ((cep = bufferedReader.readLine()) != null) {
                json.append(cep);
            }

            Pessoa gson = new Gson().fromJson(json.toString(), Pessoa.class);

            pessoa.setLogradouro(gson.getLogradouro());
            pessoa.setBairro(gson.getBairro());
            pessoa.setLocalidade(gson.getLocalidade());
            pessoa.setUf(gson.getUf());
            pessoa.setComplemento(gson.getComplemento());

        }catch (Exception e) {
            e.printStackTrace();
            mostrarMsg("Erro ao cosultar o cep: " + e.getMessage());
        }
    }


    public void carregaCidades() {
            cidades = iDaoPessoa.listaCidadesPorId(this.getCodigoEstado());
        System.out.println(getCodigoEstado());
        System.out.println(cidades);

    }

    public void editar(){
        if(pessoa.getCidades() != null){
            this.codigoEstado =  pessoa.getCidades().getEstados().getId();
            cidades = iDaoPessoa.listaCidadesPorId(this.getCodigoEstado());
        }
        System.out.println(codigoEstado);
    }


    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public DaoGeneric<Pessoa> getDao() {
        return dao;
    }

    public void setDao(DaoGeneric<Pessoa> dao) {
        this.dao = dao;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public List<Estados> getEstados() {
        estados = iDaoPessoa.listaEstados();
        return estados;
    }

    public void setEstados(List<Estados> estados) {
        this.estados = estados;
    }

    public List<Cidades> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidades> cidades) {
        this.cidades = cidades;
    }

    public Long getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(Long codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    public Long getCodigoCidade() {
        return codigoCidade;
    }

    public void setCodigoCidade(Long codigoCidade) {
        this.codigoCidade = codigoCidade;
    }
}
