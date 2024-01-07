package br.com.actinvestimentos.todolist.enums.converters;

import br.com.actinvestimentos.todolist.enums.StatusEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;
import java.util.stream.Stream;

@Converter
public final class StatusEnumConverter
        implements AttributeConverter<StatusEnum, String> {

    @Override
    public String convertToDatabaseColumn(StatusEnum objetoEnum) {
        return Objects.nonNull(objetoEnum) ? objetoEnum.name() : null;
    }

    @Override
    public StatusEnum convertToEntityAttribute(final String name) {
        return findByName(name);
    }

    public static StatusEnum findByName(final String name) {
        return Stream
                .of(StatusEnum.values())
                .filter(statusEnum -> statusEnum.name().equals(name))
                .findFirst()
                .orElse(null);
    }
}
