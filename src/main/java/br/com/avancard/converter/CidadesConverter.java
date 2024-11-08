package br.com.avancard.converter;

import br.com.avancard.jpautil.JPAUtil;
import br.com.avancard.model.entity.Cidades;

import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.io.Serializable;


@FacesConverter(forClass = Cidades.class, value = "cidadeConverter")
@Named
public class CidadesConverter implements Converter, Serializable {

    private static final long serialVersionUID = 1L;


    @Override
    /*Converter de String para Cidades*/

    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String codigoCidade) {
        EntityManager entityManager = CDI.current().select(EntityManager.class).get();
        Cidades cidades = (Cidades) entityManager.find(Cidades.class, Long.parseLong(codigoCidade));
        return cidades;
    }

    @Override
    /*Converter de Cidades para String*/
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object cidadeObj) {
        if (cidadeObj == null) {
            return "";  // Retorna uma string vazia se o objeto for nulo
        }

        if (cidadeObj instanceof Cidades) {
            Cidades cidade = (Cidades) cidadeObj;
            return String.valueOf(cidade.getId());  // Retorna o ID da cidade como string
        } else {
            // O objeto não é do tipo esperado, lança uma exceção com mais detalhes
            throw new IllegalArgumentException("Objeto não é do tipo Cidades: " + cidadeObj.getClass().getName());
        }
    }
}
