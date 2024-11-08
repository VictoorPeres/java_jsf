package br.com.avancard.filter;

import br.com.avancard.jpautil.JPAUtil;
import br.com.avancard.model.entity.Pessoa;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})/* é uma anotação usada em Java para declarar um filtro de servlet em aplicações web. Ela faz parte da especificação Java Servlet e permite que você intercepte e modifique requisições e respostas HTTP antes que cheguem a um servlet ou depois que saiam de um servlet. */
public class FilterAutenticacao implements Filter {

    @Inject
    private JPAUtil jpaUtil;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request; /* Aqui, a requisição é convertida para HttpServletRequest, que fornece métodos adicionais específicos para aplicações web, como obter informações sobre a sessão e parâmetros. */
        HttpSession session = req.getSession(); /* O método getSession() obtém a sessão do usuário atual. Se não existir uma sessão, uma nova será criada. Isso permite armazenar informações sobre o estado do usuário durante a navegação. */

        Pessoa usuarioLogado = (Pessoa) session.getAttribute("usuarioLogado"); /* Esta linha busca o atributo usuarioLogado na sessão. Este atributo deve conter as informações do usuário autenticado (se o usuário estiver logado) ou null se não estiver logado. A conversão para String sugere que o nome de usuário está sendo armazenado como uma String. */
        String url = req.getServletPath(); /* O método getServletPath() obtém o caminho do servlet que foi chamado. Isso é útil para saber qual página ou recurso está sendo acessado pelo usuário. */
        if (!url.equalsIgnoreCase("index.jsf") && usuarioLogado == null) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsf");
            dispatcher.forward(request, response);
            return;
        }else{
            /* Executa as ações do request e do response */
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        jpaUtil.getEntityManager();
    }

    @Override
    public void destroy() {

    }
}
