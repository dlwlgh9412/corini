package com.common.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Converter(autoApply = true)
public class ZonedDateConverter implements AttributeConverter<ZonedDateTime, Timestamp> {


    @Override
    public Timestamp convertToDatabaseColumn(ZonedDateTime attribute) {
        if (attribute == null)
            attribute = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));

        if (!attribute.getZone().equals(ZoneId.of("Asia/Seoul")))
            attribute = attribute.withZoneSameInstant(ZoneId.of("Asia/Seoul"));

        return Timestamp.from(attribute.toInstant());
    }

    @Override
    public ZonedDateTime convertToEntityAttribute(Timestamp dbData) {
        LocalDateTime localDateTime = dbData.toLocalDateTime();
        return ZonedDateTime.of(localDateTime, ZoneId.of("Asia/Seoul"));
    }
}
