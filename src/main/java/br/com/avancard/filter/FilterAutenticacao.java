package br.com.avancard.filter;

import br.com.avancard.jpautil.JPAUtil;
import br.com.avancard.model.entity.Pessoa;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})/* é uma anotação usada em Java para declarar um filtro de servlet em aplicações web. Ela faz parte da especificação Java Servlet e permite que você intercepte e modifique requisições e respostas HTTP antes que cheguem a um servlet ou depois que saiam de um servlet. */
public class FilterAutenticacao implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();

        String usuarioLogado = (String) session.getAttribute("usuarioLogado");
        String url = req.getServletPath();
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
        JPAUtil.getEntityManager();
    }

    @Override
    public void destroy() {

    }
}
