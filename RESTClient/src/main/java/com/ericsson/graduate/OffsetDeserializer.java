package com.ericsson.graduate;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import java.time.format.DateTimeFormatter;

public class OffsetDeserializer extends LocalDateTimeDeserializer {

    public OffsetDeserializer(DateTimeFormatter formatter) {
        super(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    public OffsetDeserializer() {
        super(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }
}
