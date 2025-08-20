package br.com.catalogo.produtos.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        // Configura o nível de correspondência
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT) // evita mapeamentos errados
                .setFieldMatchingEnabled(true)                  // habilita leitura direta de campos
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

        // Adicionar mapeamentos personalizados se necessário
        // Exemplo:
        // mapper.typeMap(Origem.class, Destino.class).addMapping(Origem::getCampo, Destino::setOutroCampo);

        return mapper;
    }

}
