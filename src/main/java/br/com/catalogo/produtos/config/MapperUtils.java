package br.com.catalogo.produtos.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe utilitária para conversão de objetos entre entidades e DTOs,
 * bem como serialização e desserialização JSON.
 */
@Component
public class MapperUtils {

    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;

    public MapperUtils(ModelMapper modelMapper, ObjectMapper objectMapper) {
        this.modelMapper = modelMapper;
        this.objectMapper = objectMapper;
    }

    /**
     * Converte uma entidade para um DTO usando ModelMapper.
     *
     * @param entity   A entidade de origem.
     * @param dtoClass A classe do DTO de destino.
     * @param <D>      Tipo do DTO.
     * @param <E>      Tipo da entidade.
     * @return Instância do DTO.
     * @throws MappingException se a conversão falhar.
     */
    public <D, E> D toDTO(E entity, Class<D> dtoClass) {
        try {
            return this.modelMapper.map(entity, dtoClass);
        } catch (MappingException e) {
            List<ErrorMessage> errors = List.of(new ErrorMessage(e.getMessage()));
            throw new MappingException(errors);
        }
    }

    /**
     * Converte uma lista de entidades para uma lista de DTOs.
     *
     * @param entityList Lista de entidades.
     * @param dtoClass   Classe do DTO de destino.
     * @param <D>        Tipo do DTO.
     * @param <E>        Tipo da entidade.
     * @return Lista de DTOs convertidos.
     * @throws MappingException se alguma conversão falhar.
     */
    public <D, E> List<D> toDTO(List<E> entityList, Class<D> dtoClass) {
        try {
            return entityList.stream()
                    .map(entity -> this.toDTO(entity, dtoClass))
                    .collect(Collectors.toList());
        } catch (MappingException e) {
            List<ErrorMessage> errors = List.of(new ErrorMessage(e.getMessage()));
            throw new MappingException(errors);
        }
    }

    /**
     * Converte um DTO para uma entidade.
     *
     * @param dto         Objeto DTO de origem.
     * @param entityClass Classe da entidade de destino.
     * @param <D>         Tipo do DTO.
     * @param <E>         Tipo da entidade.
     * @return Instância da entidade.
     * @throws MappingException se a conversão falhar.
     */
    public <D, E> E toEntity(D dto, Class<E> entityClass) {
        try {
            return this.modelMapper.map(dto, entityClass);
        } catch (MappingException e) {
            List<ErrorMessage> errors = List.of(new ErrorMessage(e.getMessage()));
            throw new MappingException(errors);
        }
    }

    /**
     * Converte um objeto em sua representação JSON.
     *
     * @param object Objeto a ser convertido.
     * @param <T>    Tipo do objeto.
     * @return String JSON do objeto.
     * @throws MappingException se a serialização falhar.
     */
    public <T> String toJson(T object) {
        try {
            return this.objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            List<ErrorMessage> errors = List.of(new ErrorMessage(e.getMessage()));
            throw new MappingException(errors);
        }
    }

    /**
     * Converte um objeto em sua representação JSON formatado.
     *
     * @param object Objeto a ser convertido.
     * @param <T>    Tipo do objeto.
     * @return String JSON do objeto.
     * @throws MappingException se a serialização falhar.
     */
    public <T> String toFormattedJson(T object) {
        try {
            return this.objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            List<ErrorMessage> errors = List.of(new ErrorMessage(e.getMessage()));
            throw new MappingException(errors);
        }
    }

    /**
     * Converte uma String JSON em um objeto Java.
     *
     * @param json  String JSON de entrada.
     * @param clazz Classe do objeto esperado.
     * @param <T>   Tipo do objeto de retorno.
     * @return Objeto desserializado.
     * @throws MappingException se a desserialização falhar.
     */
    public <T> T toObject(String json, Class<T> clazz) {
        try {
            return this.objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            List<ErrorMessage> errors = List.of(new ErrorMessage(e.getMessage()));
            throw new MappingException(errors);
        }
    }
}
