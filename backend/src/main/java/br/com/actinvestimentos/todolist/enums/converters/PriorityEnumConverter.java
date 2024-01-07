package br.com.actinvestimentos.todolist.enums.converters;

import br.com.actinvestimentos.todolist.enums.PriorityEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;
import java.util.stream.Stream;

@Converter
public final class PriorityEnumConverter
        implements AttributeConverter<PriorityEnum, String> {

    @Override
    public String convertToDatabaseColumn(PriorityEnum objetoEnum) {
        return Objects.nonNull(objetoEnum) ? objetoEnum.name() : null;
    }

    @Override
    public PriorityEnum convertToEntityAttribute(final String name) {
        return findByName(name);
    }

    public static PriorityEnum findByName(final String name) {
        return Stream
                .of(PriorityEnum.values())
                .filter(priorityEnum -> priorityEnum.name().equals(name))
                .findFirst()
                .orElse(null);
    }
}