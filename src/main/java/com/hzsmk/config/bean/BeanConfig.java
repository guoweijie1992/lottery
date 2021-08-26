package com.hzsmk.config.bean;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * 配置一些bean
 *
 * @author jiangjh
 * @date 2019/11/22 10:24
 */
@Slf4j
@Configuration
public class BeanConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        // 配置接口数据返回, long转string
        Jackson2ObjectMapperBuilderCustomizer customizer = (Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) -> {
            jacksonObjectMapperBuilder.serializerByType(Long.class, ToStringSerializer.instance)
                    .serializerByType(Long.TYPE, ToStringSerializer.instance);
        };
        return customizer;
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        // 配置json数据返回时空数组返回[],null返回""
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        objectMapper.setSerializerFactory(objectMapper.getSerializerFactory()
                .withSerializerModifier(new MyBeanSerializerModifier()));
        return objectMapper;
    }

    public static class MyNullArrayJsonSerializer extends JsonSerializer {
        @Override
        public void serialize(Object value, JsonGenerator generator, SerializerProvider provider) throws IOException {
            if (value == null) {
                generator.writeStartArray();
                generator.writeEndArray();
            }
        }
    }

    public static class MyNullJsonSerializer extends JsonSerializer {
        @Override
        public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
                throws IOException {
            jsonGenerator.writeString("");
        }
    }

    public static class MyBeanSerializerModifier extends BeanSerializerModifier {
        // 数组,集合类型 null -> []
        private JsonSerializer nullArrayJsonSerializer = new MyNullArrayJsonSerializer();
        // 字符串等类型 null -> ""
        private JsonSerializer nullJsonSerializer = new MyNullJsonSerializer();

        @Override
        public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc,
                                                         List beanProperties) {
            for (Object beanProperty : beanProperties) {
                BeanPropertyWriter writer = (BeanPropertyWriter) beanProperty;
                //判断字段的类型，如果是array，list，set则注册nullSerializer
                if (isArrayType(writer)) {
                    writer.assignNullSerializer(this.nullArrayJsonSerializer);
                } else {
                    writer.assignNullSerializer(this.nullJsonSerializer);
                }
            }
            return beanProperties;
        }

        boolean isArrayType(BeanPropertyWriter writer) {
            Class clazz = writer.getPropertyType();
            return clazz.isArray() || clazz.equals(List.class) || clazz.equals(Set.class);
        }
    }


}
